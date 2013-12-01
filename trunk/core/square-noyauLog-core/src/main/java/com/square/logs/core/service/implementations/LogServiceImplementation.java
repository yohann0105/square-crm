/*
 * This file is a part of Square, Customer Relationship Management Software for insurance's companies
 * Copyright (C) 2010-2012  SCUB <square@scub.net> - Mutuelle SMATIS FRANCE  <square@smatis.fr >
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.square.logs.core.service.implementations;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.logs.core.dao.interfaces.ArgumentDao;
import com.square.logs.core.dao.interfaces.LogDao;
import com.square.logs.core.model.Argument;
import com.square.logs.core.model.Log;
import com.square.logs.core.model.dto.LogDto;
import com.square.logs.core.util.MessageKeyUtil;
import com.square.logs.service.interfaces.LogService;

/**
 * implémentaion de service de gestion de logs.
 * @author Manuel Godbert (manuel.godbert@scub.net) - SCUB
 */
@Transactional(propagation = Propagation.REQUIRED)
public class LogServiceImplementation extends Thread implements LogService {
    /** Le nombre maximum de logs en attente avant de s'en inquiéter. */
    private static final int MAX_WAITING_LOGS = 1000;

    /** Le logger de la classe. */
    private final Logger logger = Logger.getLogger(getClass());

    /** Formateur de date. */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat();

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Le dao des logs. */
    private LogDao logDao;

    /** Le dao des arguments. */
    private ArgumentDao argumentDao;

    /** Ce service même, on en a besoin pour bénéficier de la transaction par AOP. */
    private LogService logService;

    /** Le thread auquel on délègue l'enregistrement des logs. */
    private ConcurrentLinkedQueue<LogDto> logsEnAttente;


    private MessageSourceUtil messageSourceUtil;

    /**
     * Constructeur.
     */
    public LogServiceImplementation() {
        logsEnAttente = new ConcurrentLinkedQueue<LogDto>();
        setName("Logger");
        start();
    }

    /**
     * Définit le dao des logs.
     * @param logDao le dao des logs
     */
    public void setLogDao(LogDao logDao) {
        this.logDao = logDao;
    }

    /**
     * Définit le dao des arguments.
     * @param argumentDao le dao des arguments
     */
    public void setArgumentDao(ArgumentDao argumentDao) {
        this.argumentDao = argumentDao;
    }

    /**
     * Définit le logService, on en a besoin pour bénéficier des transactions par AOP.
     * @param logService ce service
     */
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @Override
    public void run() {
        LogDto logDto;
        // On dépile les logs en attente tant qu'il y en a, après quoi on se met en attente
        while (true) {
            try {
                while ((logDto = logsEnAttente.poll()) != null) {
                    // On passe par l'interface pour bénéficier de la transaction par AOP
                    logService.persister(logDto);
                }
                synchronized (this) {
                    wait();
                }
            } catch (Exception e) {
                logger.warn(messageSourceUtil.get(MessageKeyUtil.LOGGER_WARN_EXCEPTION_THREAD_BUT_ALIVE, new String[] {e.getMessage()}));
            }
        }
    }

    @Override
    public void ajouterLog(LogDto logDto) {
        if (logDto == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DTO_NULL));
        }
        empilerLog(logDto);
    }

    /**
     * Ajoute un log à la queue en attente de sauvegarde.
     * @param logDto le log à sauvegarder
     */
    private synchronized void empilerLog(LogDto logDto) {
        if (logsEnAttente.size() < MAX_WAITING_LOGS) {
            logsEnAttente.add(logDto);
            // débloque le thread en attente de nouveaux logs
            notifyAll();
        } else {
            logger.warn(messageSourceUtil.get(MessageKeyUtil.LOGGER_WARN_CLEAR_QUEUE_MAXIMUN_REACHED, new String[] {String.valueOf(logsEnAttente.size())}));
            logsEnAttente.clear();
        }
    }

    @Override
    public LogDto persister(LogDto logDto) {
        if (logDto == null) {
            throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERROR_DTO_NULL));
        }

        final Log log = mapperDozerBean.map(logDto, Log.class);

        // Construction des arguments de la méthode appelée
        final List<Argument> parametres = new ArrayList<Argument>();
        int numParam = 0;
        if (logDto.getParametres() != null) {
            for (Object param : logDto.getParametres()) {
                final Argument arg = new Argument();
                arg.setNom("Paramètre[" + (numParam++) + "]");
                arg.setValeur(decrire(param, logDto.getProfondeur()));
                argumentDao.creer(arg);
                parametres.add(arg);
            }
        }
        // Construction de son résultat
        final Argument resultArg = new Argument();
        resultArg.setNom("Résultat");
        resultArg.setValeur(decrire(logDto.getResultat(), logDto.getProfondeur()));
        argumentDao.creer(resultArg);
        parametres.add(resultArg);

        log.setParametresFormates(parametres);
        logDao.creer(log);

        return mapperDozerBean.map(log, LogDto.class);
    }

    /**
     * Construit une chaîne de caractères décrivant un bean.
     * @param bean le bean à décrire
     * @param profondeur le niveau d'introspection maximum
     * @return la description du bean
     */
    private String decrire(Object bean, int profondeur) {
        final StringBuilder sb = new StringBuilder();
        decrire(sb, bean, profondeur);
        final String result = sb.toString();
        logger.debug("Log du parametre " + result);
        return result;
    }

    /**
     * Construit une chaîne de caractères décrivant un bean.
     * @param sb le StringBuilder auquel concaténer la description du bean
     * @param bean le bean à décrire
     * @param profondeur le niveau d'introspection maximum
     * @return la description du bean
     */
    @SuppressWarnings("unchecked")
	private void decrire(StringBuilder sb, Object bean, int profondeur) {
        try {
            if (bean == null) {
                sb.append("null");
            } else if (bean instanceof Calendar) {
                // description d'une date
                sb.append(dateFormat.format(((Calendar) bean).getTime()));
            } else if (bean instanceof Date) {
                // description d'une date
                sb.append(dateFormat.format((Date) bean));
            } else if (profondeur == 0) {
                // en dessous de la profondeur fixée on s'en remet au toString() de l'objet
                sb.append(bean);
            } else if (bean.getClass().isArray()) {
                // description d'un tableau
                sb.append("[");
                for (Object item : (Object[]) bean) {
                    decrire(sb, item, profondeur);
                }
                sb.append("]");
            } else if (Collection.class.isAssignableFrom(bean.getClass())) {
                // description d'une collection
                sb.append("[");
                for (Object item : (Collection) bean) {
                    decrire(sb, item, profondeur);
                }
                sb.append("]");
            } else if (!bean.getClass().getMethod("toString", new Class[0]).getDeclaringClass().equals(Object.class)) {
                // description d'un element dont la méthode toString a été implémentée
                sb.append(bean);
            } else {
                // description d'un objet complexe
                sb.append("{");
                final Method[] methods = bean.getClass().getDeclaredMethods();
                for (Method method : methods) {
                    if ((method.getModifiers() & Modifier.PUBLIC) != 0 && method.getParameterTypes().length == 0
                        && (method.getName().startsWith("get") || method.getName().startsWith("is"))) {
                        final String nom = method.getName().replaceAll("[a-z]*([A-Z].*)", "$1");
                        try {
                            final Object valeur = method.invoke(bean, new Object[0]);
                            // inutile de mettre les propriétés null qui affectent la lisibilité
                            if (valeur != null) {
                                sb.append(nom).append("=");
                                decrire(sb, valeur, profondeur - 1);
                                sb.append(", ");
                            }
                        } catch (Exception e) {
                            // ne devrait jamais arriver
                            sb.append("?, ");
                        }
                    }
                }
                if (sb.length() > 2 && sb.substring(sb.length() - 2).equals(", ")) {
                    sb.deleteCharAt(sb.length() - 1);
                    sb.deleteCharAt(sb.length() - 1);
                }
                sb.append("}");
            }
        } catch (Exception e) {
            // ne devrait jamais arriver
            sb.append("?");
        }
    }

    @Override
    public synchronized List<LogDto> getLogsEnAttente() {
        return new ArrayList<LogDto>(logsEnAttente);
    }

    @Override
    public List<Long> getIdsLogsBeforeDate(Calendar date, int pagination) {
        return logDao.getIdsLogsBeforeDate(date, pagination);
    }

    @Override
    public int supprimerLogsByIds(List<Long> idsASupprimer) {
        return logDao.supprimerLogsByIds(idsASupprimer);
    }

    @Override
    public int getNbLogsBeforeDate(Calendar date) {
        return logDao.getNbLogsBeforeDate(date);
    }

    /**
     * setter de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

	/**Set messageSourceUtil.
	 * @param messageSourceUtil the messageSourceUtil to set
	 */
	public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
		this.messageSourceUtil = messageSourceUtil;
	}
}

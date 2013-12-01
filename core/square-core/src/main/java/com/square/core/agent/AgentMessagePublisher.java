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
package com.square.core.agent;

import static com.square.core.util.AgentMessagePublisherKeyUtil.MESSAGE_PUBLISHER_CONTENU;
import static com.square.core.util.AgentMessagePublisherKeyUtil.MESSAGE_PUBLISHER_TITRE;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.ActionRechercheDto;
import com.square.core.model.dto.MessagePublishDto;
import com.square.core.model.plugin.NotificateurSquarePlugin;
import com.square.core.service.interfaces.ActionService;
import com.square.core.util.AgentJmxKeyUtil;

/**
 * Agent de publication de messages de notification.
 * @author mohamed - SCUB
 */
public class AgentMessagePublisher extends QuartzJobBean {

    private ActionService actionService;

    private NotificateurSquarePlugin notificateurSquarePlugin;

    private MessageSourceUtil messageSourceUtil;

    private String url;

    private Logger logger = RootLogger.getLogger(AgentMessagePublisher.class);

    private static final String OPEN_PERSON_EVENT_NAME = "OpenPersonEvent";

    private static final String OPEN_PERSON_MORALE_EVENT_NAME = "OpenPersonneMoraleEvent";

    private static final String PARAM_ID_ACTION = "idAction";

    private static final String PARAM_ID_PERSON = "id";

    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        logger.debug(messageSourceUtil.get(AgentJmxKeyUtil.MESSAGE_LANCEMENT_AGENT_QUARTS));
        final int pagination = 100;
        final char[] delimiters = {' ', '-', '_'};

        if (notificateurSquarePlugin == null) {
            logger.error(messageSourceUtil.get(AgentJmxKeyUtil.MESSAGE_ERROR_NOTIFICATION_SQUARE_PLUGIN));
        } else {
            final ActionCritereRechercheDto criteres = new ActionCritereRechercheDto();
            criteres.setDateNotification(Calendar.getInstance());
            try {
                RemotePagingCriteriasDto<ActionCritereRechercheDto> criterias =
                    new RemotePagingCriteriasDto<ActionCritereRechercheDto>(criteres, 0, pagination);
                RemotePagingResultsDto<ActionRechercheDto> resultPagination = actionService.rechercherActionParCritere(criterias);
                List<ActionRechercheDto> result = resultPagination.getListResults();
                logger.debug(messageSourceUtil.get(AgentJmxKeyUtil.MESSAGE_NOMBRE_MESSAGE_TROUVES) + " = " + result.size());

                final String titre = messageSourceUtil.get(MESSAGE_PUBLISHER_TITRE);

                while (result.size() > 0) {
                    for (ActionRechercheDto action : result) {
                        final StringBuffer urlMessage = new StringBuffer(url);
                        if (StringUtils.isNotBlank(action.getRaisonSociale())) {
                            urlMessage.append(OPEN_PERSON_MORALE_EVENT_NAME);
                        } else {
                            urlMessage.append(OPEN_PERSON_EVENT_NAME);
                        }
                        urlMessage.append("&amp;").append(PARAM_ID_PERSON).append("=").append(action.getIdpersonne());
                        urlMessage.append("&amp;").append(PARAM_ID_ACTION).append("=").append(action.getId());

                        final StringBuffer nomAffichage = new StringBuffer();
                        if (StringUtils.isNotBlank(action.getPrenomPersonne())) {
                            final String prenomMisEnForme = WordUtils.capitalizeFully(action.getPrenomPersonne(), delimiters);
                            nomAffichage.append(prenomMisEnForme);
                        }
                        if (StringUtils.isNotBlank(action.getPrenomPersonne()) && StringUtils.isNotBlank(action.getNomPersonne())) {
                            nomAffichage.append(" ");
                        }
                        if (StringUtils.isNotBlank(action.getNomPersonne())) {
                            nomAffichage.append(action.getNomPersonne().toUpperCase());
                        }
                        if (StringUtils.isNotBlank(action.getRaisonSociale())) {
                            nomAffichage.append(action.getRaisonSociale().toUpperCase());
                        }

                        final String contenu = messageSourceUtil.get(MESSAGE_PUBLISHER_CONTENU, new String[] {urlMessage.toString(), nomAffichage.toString()});

                        final MessagePublishDto message = new MessagePublishDto();
                        message.setTitre(titre);
                        message.setTexte(contenu);
                        message.setIdUtilisateur(action.getCommercial().getIdentifiant());
                        logger.debug(messageSourceUtil.get(AgentJmxKeyUtil.MESSAGE_APPEL_PLUGIN_NOTIFICATEUR));
                        notificateurSquarePlugin.publierMessage(message);
                        actionService.notifier(action.getId());
                    }

                    criterias = new RemotePagingCriteriasDto<ActionCritereRechercheDto>(criteres, 0, pagination);
                    resultPagination = actionService.rechercherActionParCritere(criterias);
                    result = resultPagination.getListResults();
                    logger.debug(messageSourceUtil.get(AgentJmxKeyUtil.MESSAGE_NOMBRE_MESSAGE_TROUVES) + " = " + result.size());
                }
            } catch (Exception e) {
                logger.error(messageSourceUtil.get(AgentJmxKeyUtil.MESSAGE_ERROR_EXCEPTION_SURVENUE_DURANT_TRAITEMENT) + " : " + e.getMessage(), e);
            }
        }
        logger.debug(messageSourceUtil.get(AgentJmxKeyUtil.MESSAGE_FIN_AGENT_QUART));
    }

    /**
     * setter de notificateur plugin.
     * @param notificateurSquarePlugin the notificateurSquarePlugin to set.
     */
    public void setNotificateurSquarePlugin(NotificateurSquarePlugin notificateurSquarePlugin) {
        this.notificateurSquarePlugin = notificateurSquarePlugin;
    }

    /**
     * setter de actionService.
     * @param actionService the actionService to set
     */
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }

    /**
     * setter de url.
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Set the value of messageSourceUtil.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

}

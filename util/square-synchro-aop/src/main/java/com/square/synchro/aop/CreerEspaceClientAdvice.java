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
package com.square.synchro.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.adherent.noyau.dto.espace.client.EspaceClientInternetDto;
import com.square.adherent.noyau.service.interfaces.EspaceClientInternetService;
import com.square.core.model.dto.PersonneDto;
import com.square.synchro.aop.util.MessageKeyUtil;

/**
 * AOP pour la création d'un espace client à la création d'une PP.
 * @author jgoncalves - SCUB
 */
public class CreerEspaceClientAdvice {

    /** Logger. */
    private Logger logger = Logger.getLogger(CreerEspaceClientAdvice.class);

    /** MessageSourceUtil. */
    private MessageSourceUtil messageSourceUtil;

    private EspaceClientInternetService espaceClientInternetService;

    /**
     * {@inheritDoc}
     */
    public void creationPersonne(Object returnValue) throws Throwable {
        try {
            if (returnValue instanceof PersonneDto) {
                final PersonneDto personne = (PersonneDto) returnValue;
                creerEspaceClient(personne.getIdentifiant());
            }
        } catch (Exception e) {
            logger.fatal(messageSourceUtil.get(MessageKeyUtil.LOGGER_FATAL_CREATION_ESPACE_CLIENT_PP,
              		 new String[] {e.getMessage()}), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void modificationPersonne(Object returnValue) throws Throwable {
        try {
            if (returnValue instanceof PersonneDto) {
                final PersonneDto personne = (PersonneDto) returnValue;
                // si la personne n'a pas d'espace client, on en cree un
                logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_VERIFICATION_ESPACE_CLIENT_PP,
                 		 new String[] {String.valueOf(personne.getNumClient())}));
                if (espaceClientInternetService.getEspaceClientInternet(personne.getIdentifiant()) == null) {
                    creerEspaceClient(personne.getIdentifiant());
                }
            }
        } catch (Exception e) {
            logger.fatal(messageSourceUtil.get(MessageKeyUtil.LOGGER_FATAL_CREATION_ESPACE_CLIENT_PP,
             		 new String[] {e.getMessage()}), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object transformationVivier(ProceedingJoinPoint pjp) throws Throwable {
        // Récupération du paramètre & analyse des synchros à lancer :
        final Long idPersonne = (Long) pjp.getArgs()[0];
        final Object retVal = pjp.proceed();
        try {
            creerEspaceClient(idPersonne);
        } catch (Exception e) {
            logger.fatal(messageSourceUtil.get(MessageKeyUtil.LOGGER_FATAL_CREATION_ESPACE_CLIENT_PP,
             		 new String[] {e.getMessage()}), e);
        }
        return retVal;
    }

    private void creerEspaceClient(Long idPersonne) {
        if (espaceClientInternetService.getEspaceClientInternet(idPersonne) == null) {
            logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_CREATION_ESPACE_CLIENT,
            		 new String[] {String.valueOf(idPersonne)}));
            espaceClientInternetService.creerEspaceClient(new EspaceClientInternetDto(idPersonne));
        }
    }

    /**
     * Définition du service de gestion de l'espace client.
     * @param espaceClientInternetService le service
     */
    public void setEspaceClientInternetService(EspaceClientInternetService espaceClientInternetService) {
        this.espaceClientInternetService = espaceClientInternetService;
    }

	/**Setter.
	 * @param logger the logger to set
	 */
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	/**Setter.
	 * @param messageSourceUtil the messageSourceUtil to set
	 */
	public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
		this.messageSourceUtil = messageSourceUtil;
	}

}

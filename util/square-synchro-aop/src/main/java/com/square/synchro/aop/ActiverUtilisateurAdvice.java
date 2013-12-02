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

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.synchro.aop.util.MessageKeyUtil;
import com.square.synchro.app.noyau.dto.DefaultMessageSynchroAppDto;
import com.square.synchro.app.noyau.services.interfaces.SynchroAppJmsSender;

/**
 * AOP pour l'activation ou la désactivation d'un utilisateur.
 * @author nnadeau - SCUB
 */
public class ActiverUtilisateurAdvice implements AfterReturningAdvice {

    /** Logger. */
    private Logger logger = Logger.getLogger(ActiverUtilisateurAdvice.class);

    /** MessageSourceUtil. */
    private MessageSourceUtil messageSourceUtil;

    /** Service pour poster dans la file JMS. */
    private SynchroAppJmsSender synchroAppJmsSender;

    /**
     * {@inheritDoc}
     */
    public void afterReturning(Object returnValue, Method method, Object[] params, Object target) throws Throwable {
        try {
            if (params[0] instanceof Long) {
                final Long idUtilisateur = (Long) params[0];
                logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_ACTIV_DESACTIV_RESSOURCE,
               		 new String[] {String.valueOf(idUtilisateur)}));
                lancerSynchro(idUtilisateur.toString(), DefaultMessageSynchroAppDto.MSOH_HABILITATIONS_ACTIVER_UTILISATEUR);
            }
        }
        catch (Exception e) {
            logger.fatal(messageSourceUtil.get(MessageKeyUtil.LOGGER_FATAL_SYNCHRONISATION_ACTIV_DESACTIV_POURSUITE_TRAITEMENT,
              		 new String[] {e.getMessage()}), e);
        }
    }

    /**
     * Creation d'un message pour une mise à jour ou une creation de personne.
     */
    private void lancerSynchro(String msgSynchroIdObjet, String msgSynchroOrigineHeader) {
        logger.debug(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_ELIGIBLE_SYNCHRO_HABILITATION,
         		 new String[] {msgSynchroIdObjet, msgSynchroOrigineHeader}));
        final DefaultMessageSynchroAppDto message = new DefaultMessageSynchroAppDto();
        message.setMsgSynchroIdObjet(msgSynchroIdObjet);
        message.setMsgSynchroOrigineHeader(msgSynchroOrigineHeader);
        synchroAppJmsSender.envoyerMessageSynchro(message);
    }

    /**
     * Définit la valeur de synchroAppJmsSender.
     * @param synchroAppJmsSender la nouvelle valeur de synchroAppJmsSender
     */
    public void setSynchroAppJmsSender(SynchroAppJmsSender synchroAppJmsSender) {
        this.synchroAppJmsSender = synchroAppJmsSender;
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

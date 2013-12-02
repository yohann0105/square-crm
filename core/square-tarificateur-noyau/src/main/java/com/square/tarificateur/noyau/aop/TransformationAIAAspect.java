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
package com.square.tarificateur.noyau.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.synchro.app.noyau.dto.DefaultMessageSynchroAppDto;
import com.square.synchro.app.noyau.services.interfaces.SynchroAppJmsSender;
import com.square.tarificateur.noyau.dto.transfo.aia.DemandeTransformationAiaDto;
import com.square.tarificateur.noyau.dto.transfo.aia.ResultatTransformationAiaDto;
import com.square.tarificateur.noyau.util.MessageKeyUtil;

/**
 * Aspect permettant de poster dans une file JMS les demandes de transformations de devis vers AIA.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
@Aspect
public class TransformationAIAAspect {

    /** Logger. */
    private final Logger logger = Logger.getLogger(this.getClass());

    /** Service pour poster dans la file JMS. */
    private SynchroAppJmsSender synchroAppJmsSender;
    /**Message util.*/
    private MessageSourceUtil messageSourceUtil;

    /**
     * Poste la demande de transformation de devis vers AIA si aucune erreur bloquante n'a été détectée.
     * @param jointPoint point de jointure
     * @param retVal objet retourné
     */
    public void posterTransformationAIA(JoinPoint jointPoint, Object retVal) {
        final ResultatTransformationAiaDto resultat = (ResultatTransformationAiaDto) retVal;
        final DemandeTransformationAiaDto demandeTransformationAia = (DemandeTransformationAiaDto) jointPoint.getArgs()[0];
        if (resultat.getListeErreursBloquantes().isEmpty()) {
            // Création d'un message pour l'envoi de la transfo AIA
            final DefaultMessageSynchroAppDto message = new DefaultMessageSynchroAppDto();
            message.setMsgSynchroIdObjet(demandeTransformationAia.getIdDevis().toString());
            message.setMsgSynchroOrigineHeader(DefaultMessageSynchroAppDto.MSOH_SQUARE_TRANSFO_AIA);
            message.setMsgSynchroSecurityLogin(demandeTransformationAia.getLoginUtilisateurConnecte());
            message.setMsgSynchroActionPost(demandeTransformationAia.getCreateActionSuivi().toString());
            synchroAppJmsSender.envoyerMessageSynchro(message);
            logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_TRANSFO_DEVIS_VERS_AIA_POSTER_FILE,
            		new String[] {String.valueOf(demandeTransformationAia.getIdDevis())}));
        } else {
            final StringBuffer erreursBloquantes = new StringBuffer();
            for (String erreur : resultat.getListeErreursBloquantes()) {
                erreursBloquantes.append(messageSourceUtil.get(MessageKeyUtil.LOGGER_WARN_ERREUR_FORMAT,
                		new String[] {erreur}));
            }
            logger.warn(messageSourceUtil.get(MessageKeyUtil.LOGGER_WARN_TRANSFO_DEVIS_VERS_AIA_NON_POSTER_ERREUR_BLOQUANTE,
            		new String[] {String.valueOf(demandeTransformationAia.getIdDevis()), erreursBloquantes.toString()}));
        }
    }

    /**
     * Setter function.
     * @param synchroAppJmsSender the synchroAppJmsSender to set
     */
    public void setSynchroAppJmsSender(SynchroAppJmsSender synchroAppJmsSender) {
        this.synchroAppJmsSender = synchroAppJmsSender;
    }

}

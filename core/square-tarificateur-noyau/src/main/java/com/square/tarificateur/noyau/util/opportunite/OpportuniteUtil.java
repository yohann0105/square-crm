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
package com.square.tarificateur.noyau.util.opportunite;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.core.model.dto.OpportuniteCriteresRechercheDto;
import com.square.core.model.dto.OpportuniteSimpleDto;
import com.square.core.service.interfaces.OpportuniteService;
import com.square.tarificateur.noyau.bean.opportunite.InfosOpportuniteSquareBean;
import com.square.tarificateur.noyau.util.MessageKeyUtil;

/**
 * Classe utilitaire pour les opportunités.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class OpportuniteUtil {

    /** Service des opportunités de square. */
    private OpportuniteService opportuniteServiceSquare;

    /** MessageSourceUtil. */
    private MessageSourceUtil messageSourceUtil;

    /** Logger. */
    private Logger logger = Logger.getLogger(OpportuniteUtil.class);

    /**
     * Récupère une opportunité simple par son identifiant Square.
     * @param eidOpportunite l'identifiant Square de l'opportunité
     * @param infosOpportuniteSquare les infos des opportunité Square
     * @return l'opportunité
     */
    public OpportuniteSimpleDto getOpportuniteSimple(Long eidOpportunite, InfosOpportuniteSquareBean infosOpportuniteSquare) {
        // Recherche dans le cache
        OpportuniteSimpleDto opportuniteSquare = infosOpportuniteSquare.getMapOpportunites().get(eidOpportunite);
        if (opportuniteSquare == null) {
            final OpportuniteCriteresRechercheDto criteres = new OpportuniteCriteresRechercheDto();
            final List<Long> listeIdsOpps = new ArrayList<Long>();
            listeIdsOpps.add(eidOpportunite);
            criteres.setListeIds(listeIdsOpps);
            final List<OpportuniteSimpleDto> listeOpportunites = opportuniteServiceSquare.rechercherOpportuniteParCriteres(criteres);
            if (listeOpportunites == null || listeOpportunites.size() != 1) {
                logger.error(messageSourceUtil.get(MessageKeyUtil.LOGGER_ERROR_RECUPERATION_OPPORTUNITE_INEXISTANTE,
                		new String[] {String.valueOf(eidOpportunite)}));
                throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.ERREUR_RECUPERATION_OPPORTUNITE_SQUARE));
            }
            opportuniteSquare = listeOpportunites.get(0);
            // Ajout dans le cache
            infosOpportuniteSquare.getMapOpportunites().put(eidOpportunite, opportuniteSquare);
        }
        return opportuniteSquare;
    }

    /**
     * Définit la valeur de opportuniteServiceSquare.
     * @param opportuniteServiceSquare la nouvelle valeur de opportuniteServiceSquare
     */
    public void setOpportuniteServiceSquare(OpportuniteService opportuniteServiceSquare) {
        this.opportuniteServiceSquare = opportuniteServiceSquare;
    }

    /**
     * Définit la valeur de messageSourceUtil.
     * @param messageSourceUtil la nouvelle valeur de messageSourceUtil
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

}

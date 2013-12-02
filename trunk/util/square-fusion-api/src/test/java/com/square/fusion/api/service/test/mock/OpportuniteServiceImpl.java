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
package com.square.fusion.api.service.test.mock;

import java.util.List;

import com.square.core.model.dto.OpportuniteCriteresRechercheDto;
import com.square.core.model.dto.OpportuniteDto;
import com.square.core.model.dto.OpportuniteMaJStatutDto;
import com.square.core.model.dto.OpportuniteModificationDto;
import com.square.core.model.dto.OpportuniteSimpleDto;
import com.square.core.service.interfaces.OpportuniteService;

/**
 * Mock du service des opportunités (Square).
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class OpportuniteServiceImpl implements OpportuniteService {

    /** Flag de transfert des opportunités de la personne. */
    private boolean transfertOpportunitesEffectue = false;

    @Override
    public OpportuniteDto creerOpportunite(OpportuniteDto opportuniteDto) {
        return null;
    }

    @Override
    public boolean hasPersonneOpportunite(Long idPersonne) {
        return false;
    }

    @Override
    public void modifierOpportunite(OpportuniteModificationDto opportuniteModificationDto) {

    }

    @Override
    public List<OpportuniteSimpleDto> rechercherOpportuniteParCriteres(OpportuniteCriteresRechercheDto criteres) {
        return null;
    }

    @Override
    public void transfererOpportunites(Long idPersonneSource, Long idPersonneCible) {
        final Long idPersonne1 = 1L;
        final Long idPersonne2 = 2L;
        if (idPersonne2.equals(idPersonneSource) && idPersonne1.equals(idPersonneCible)) {
            transfertOpportunitesEffectue = true;
        }
    }

    /**
     * Récupère la valeur de transfertOpportunitesEffectue.
     * @return la valeur de transfertOpportunitesEffectue
     */
    public boolean isTransfertOpportunitesEffectue() {
        return transfertOpportunitesEffectue;
    }

    @Override
    public boolean isFamilleEligiblePourOpportunite(Long idPersonnePrincipale) {
        return false;
    }

    @Override
    public void mettreAJourStatutOpportunite(OpportuniteMaJStatutDto opportuniteMaJStatutDto) {
    }

    @Override
    public void supprimerOpportunite(Long idOpportunite) {
    }

    @Override
    public int countOpportuniteParCriteres(OpportuniteCriteresRechercheDto criteres) {
        return 0;
    }

    @Override
    public boolean hasPersonneOpportuniteByStatuts(Long idPersonne, List<Long> listeIdsStatuts) {
        return false;
    }

}

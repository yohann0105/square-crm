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
import java.util.Stack;

import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;

import com.square.core.model.dto.ActionCreationDto;
import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.ActionDto;
import com.square.core.model.dto.ActionModificationDto;
import com.square.core.model.dto.ActionRechercheDto;
import com.square.core.model.dto.ActionResultatDto;
import com.square.core.model.dto.ActionSyntheseDto;
import com.square.core.model.dto.CritereActionSyntheseDto;
import com.square.core.model.dto.DocumentDto;
import com.square.core.model.dto.HistoriqueCommentaireDto;
import com.square.core.service.interfaces.ActionService;

/**
 * Mock du service des actions (Square).
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ActionServiceImpl implements ActionService {

    /** Flag de transfert des actions de la personne. */
    private boolean transfertActionsEffectue = false;

    @Override
    public ActionCreationDto creerAction(ActionCreationDto action) {
        return null;
    }

    @Override
    public ActionResultatDto modifierAction(ActionModificationDto actionModificationDto) {
        return null;
    }

    @Override
    public RemotePagingResultsDto<ActionRechercheDto> rechercherActionParCritere(RemotePagingCriteriasDto<ActionCritereRechercheDto> criteres) {
        return null;
    }

    @Override
    public ActionDto rechercherActionParIdentifiant(Long idAction) {
        return null;
    }

    @Override
    public List<HistoriqueCommentaireDto> rechercherNotesActions(Long idAction) {
        return null;
    }

    @Override
    public List<Stack<ActionSyntheseDto>> recupererActionsSynthese(CritereActionSyntheseDto critere) {
        return null;
    }

    @Override
    public void transfererActionsPersonne(Long idPersonneSource, Long idPersonneCible) {
        final Long idPersonne1 = 1L;
        final Long idPersonne2 = 2L;
        if (idPersonne2.equals(idPersonneSource) && idPersonne1.equals(idPersonneCible)) {
            transfertActionsEffectue = true;
        }
    }

    /**
     * Récupère la valeur de transfertActionsEffectue.
     * @return la valeur de transfertActionsEffectue
     */
    public boolean isTransfertActionsEffectue() {
        return transfertActionsEffectue;
    }

    @Override
    public ActionDto modifierAction(ActionDto actionDto) {
        return null;
    }

    @Override
    public ActionDto rechercherActionParIdentifiantExterieur(String idExterieur) {
        return null;
    }

    @Override
    public void attacherDocuments(Long idAction, List<DocumentDto> documents) {
    }

    @Override
    public FichierDto exporterRechercheActionsParCriteres(RemotePagingCriteriasDto<ActionCritereRechercheDto> criteres) {
        return null;
    }

    @Override
    public void transfererActionsParCritere(ActionCritereRechercheDto criteres, Long idAgenceCible, Long idRessourceCible) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void supprimerAction(Long idAction) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifier(Long idAction) {
    }

    @Override
    public int countActionsParCriteres(ActionCritereRechercheDto criteres) {
        return 0;
    }

    @Override
    public void supprimerActionsPersonne(Long idPersonne) {
    }

}

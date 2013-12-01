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
package com.square.client.gwt.client.service;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.client.gwt.client.model.ActionCreationModel;
import com.square.client.gwt.client.model.ActionCritereRechercheModel;
import com.square.client.gwt.client.model.ActionModel;
import com.square.client.gwt.client.model.ActionModificationModel;
import com.square.client.gwt.client.model.ActionRechercheModel;
import com.square.client.gwt.client.model.ActionResultatModel;
import com.square.client.gwt.client.model.ActionSyntheseModel;
import com.square.client.gwt.client.model.CritereActionSyntheseModel;
import com.square.client.gwt.client.model.HistoriqueCommentaireModel;
import com.square.client.gwt.client.model.StackModel;

/**
 * Interface synchrone des services GWT pour le service des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/actionService")
public interface ActionServiceGwt extends RemoteService {

    /**
     * Recherche des actions par critère.
     * @param criteres les critères
     * @return les actions trouvées
     * @throws GwtRunTimeExceptionGwt
     */
    RemotePagingResultsGwt<ActionRechercheModel> rechercherActionParCritere(RemotePagingCriteriasGwt<ActionCritereRechercheModel> criteres)
        throws GwtRunTimeExceptionGwt;

    /**
     * Transfere des actions par critères.
     * @param criteres les critères
     * @param idAgenceCible id de l'agence cible
     * @param idRessourceCible id de la ressource cible
     * @throws GwtRunTimeExceptionGwt
     */
    void transfererActionsParCritere(ActionCritereRechercheModel criteres, Long idAgenceCible, Long idRessourceCible) throws GwtRunTimeExceptionGwt;

    /**
     * La création d'une action.
     * @param action l'action à enregistrer
     * @return l'action enregistrée
     */
    ActionCreationModel creerAction(ActionCreationModel action) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche les actions synthèse d'une personne.
     * @param critere les critères de recherche
     * @return la liste des piles d'actions
     */
    List<StackModel<ActionSyntheseModel>> recupererActionsSynthese(CritereActionSyntheseModel critere) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche une action par son identifiant.
     * @param idAction l'identifiant de l'action à rechercher
     * @return action correspondant à l'identifiant
     */
    ActionModel rechercherActionParIdentifiant(Long idAction) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche les messages d'une action.
     * @param idAction l'identifiant de l'action
     * @return l'historique des notes à partir de cette action
     */
    List<HistoriqueCommentaireModel> rechercherNotesActions(Long idAction) throws GwtRunTimeExceptionGwt;

    /**
     * Modifie une action.
     * @param actionModificationDto les nouvelles informations de l'action
     * @return le résultat de la modification
     */
    ActionResultatModel modifierAction(ActionModificationModel actionModificationDto) throws GwtRunTimeExceptionGwt;
}

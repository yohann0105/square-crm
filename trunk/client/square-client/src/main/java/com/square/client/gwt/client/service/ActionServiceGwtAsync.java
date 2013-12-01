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

import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.google.gwt.user.client.rpc.AsyncCallback;
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
 * Interface asynchrone des services GWT pour le service des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface ActionServiceGwtAsync {

    /**
     * Recherche des actions par critère.
     * @param criteres les critères
     * @param asyncCallback le callback
     */
    void rechercherActionParCritere(RemotePagingCriteriasGwt<ActionCritereRechercheModel> criteres,
        AsyncCallback<RemotePagingResultsGwt<ActionRechercheModel>> asyncCallback);

    /**
     * Recherche des actions par critère.
     * @param criteres les critères
     * @param idAgenceCible id de l'agence cible
     * @param idRessourceCible id de la ressource cible
     * @param asyncCallback le callback
     */
    void transfererActionsParCritere(ActionCritereRechercheModel criteres, Long idAgenceCible, Long idRessourceCible, AsyncCallback<Object> asyncCallback);

    /**
     * La création d'une action.
     * @param action l'action à enregistrer
     * @param asyncCallback l'action enregistrée
     */
    void creerAction(ActionCreationModel action, AsyncCallback<ActionCreationModel> asyncCallback);

    /**
     * Recherche les actions synthèse d'une personne.
     * @param critere les critères de recherche
     * @param asyncCallback la liste des piles d'actions
     */
    void recupererActionsSynthese(CritereActionSyntheseModel critere, AsyncCallback<List<StackModel<ActionSyntheseModel>>> asyncCallback);

    /**
     * Recherche une action par son identifiant.
     * @param idAction l'identifiant de l'action à rechercher
     * @param asyncCallback action correspondant à l'identifiant
     */
    void rechercherActionParIdentifiant(Long idAction, AsyncCallback<ActionModel> asyncCallback);

    /**
     * Recherche les messages d'une action.
     * @param idAction l'identifiant de l'action
     * @param asyncCallback l'historique des notes à partir de cette action
     */
    void rechercherNotesActions(Long idAction, AsyncCallback<List<HistoriqueCommentaireModel>> asyncCallback);

    /**
     * Modifie une action.
     * @param actionModificationModel les nouvelles informations de l'action
     * @param asyncCallback le résultat
     */
    void modifierAction(ActionModificationModel actionModificationModel, AsyncCallback<ActionResultatModel> asyncCallback);

}

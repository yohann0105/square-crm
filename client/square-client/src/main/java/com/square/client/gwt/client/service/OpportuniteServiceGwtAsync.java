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

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.client.gwt.client.model.InfosCreationOpportuniteModel;
import com.square.client.gwt.client.model.OpportuniteCriteresRechercheModel;
import com.square.client.gwt.client.model.OpportuniteModel;
import com.square.client.gwt.client.model.OpportuniteModificationModel;
import com.square.client.gwt.client.model.OpportuniteSimpleModel;

/**
 * Interface asynchrone des services GWT pour le service des opportunités.
 * @author cblanchard - SCUB
 */
public interface OpportuniteServiceGwtAsync {

    /**
     * Service de création d'opportunité.
     * @param opportuniteModel le dto contenant les informations de création
     * @param asyncCallback l'opportunité créée
     */
    void creerOpportunite(OpportuniteModel opportuniteModel, AsyncCallback<OpportuniteModel> asyncCallback);

    /**
     * Service de récupération d'opportunité.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste des opportunités correspondant
     */
    void rechercherOpportuniteParCriteres(OpportuniteCriteresRechercheModel criteres, AsyncCallback<List<OpportuniteSimpleModel>> asyncCallback);

    /**
     * Service de modification d'une opportunité.
     * @param opportuniteModificationModel les informations de modification
     * @param asyncCallback void
     */
    void modifierOpportunite(OpportuniteModificationModel opportuniteModificationModel, AsyncCallback<Object> asyncCallback);

    /**
     * Récupère les informations nécessaire avant de créer une opportunité.
     * @param idPersonne l'identifiant de la personne.
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void getInfosCreationOpportunite(Long idPersonne, AsyncCallback<InfosCreationOpportuniteModel> asyncCallback);

    /**
     * Teste si la famille de la personne principale est éligible pour la création d'une opportunité.
     * <br/>Teste si la personne principale ou son conjoint possède une adresse principale.
     * @param idPersonnePrincipale l'identifiant de la personne principale
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void isFamilleEligiblePourOpportunite(Long idPersonnePrincipale, AsyncCallback<Boolean> asyncCallback);

    /**
     * Service de suppression d'une opportunite.
     * @param idOpportuniteASupprimer l'id de l'opportunite a supprimer.
     * @param asyncCallback Callback permettant de reagir au succes ou a la faillite de la methode.
     */
	void supprimerOpportunite(Long idOpportuniteASupprimer, AsyncCallback<Object> asyncCallback);
}

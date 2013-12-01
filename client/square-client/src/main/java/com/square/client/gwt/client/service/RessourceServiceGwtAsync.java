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
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.RessourceCriteresRechercheModel;
import com.square.client.gwt.client.model.RessourceModel;

/**
 * Interface asynchrone des services GWT pour le service des ressources.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface RessourceServiceGwtAsync {

    /**
     * Récupère les informations de la ressource actuellement connectée.
     * @param asyncCallback les informations de la ressource connectée.
     */
    void getRessourceConnectee(AsyncCallback<DimensionRessourceModel> asyncCallback);

    /**
     * Récupération de la liste des ressources selon des critères.
     * @param params les critères de recherche avec les informations de pagination et de tri
     * @param callback le callback
     */
    void rechercherRessourceFullTextParCriteres(RemotePagingCriteriasGwt<RessourceCriteresRechercheModel> params,
        AsyncCallback<RemotePagingResultsGwt<RessourceModel>> callback);

    /**
     * Recherche une ressource par identifiant.
     * @param idRessource l'identifiant de la ressource à rechercher
     * @param asyncCallback la ressource correspondant
     */
    void rechercherRessourceParId(Long idRessource, AsyncCallback<RessourceModel> asyncCallback);

    /**
     * Recherche les identifiants des ressources correspondant aux critères de recherche.
     * @param criteres les critères de recherche.
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void rechercherIdsRessourcesFullTextParCriteres(RessourceCriteresRechercheModel criteres, AsyncCallback<List<Long>> asyncCallback);

}

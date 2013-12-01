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

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.client.gwt.client.model.CampagneCriteresRechercheLibelleModel;
import com.square.client.gwt.client.model.CampagneCriteresRechercheModel;
import com.square.client.gwt.client.model.CampagneModel;
import com.square.client.gwt.client.model.CampagneRechercheModel;

/**
 * Interface asynchrone des services GWT pour le service des campagnes.
 * @author cblanchard - SCUB
 */
public interface CampagneServiceGwtAsync {

    /**
     * Recherche les campagnes par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback les campagnes correspondant
     */
    void rechercherCampagnesParCriteres(RemotePagingCriteriasGwt<CampagneCriteresRechercheModel> criteres,
        AsyncCallback<RemotePagingResultsGwt<CampagneRechercheModel>> asyncCallback);

    /**
     * Recherche les campagnes par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback les campagnes correspondant
     */
    void rechercherIdLibelleCampagnesParCriteres(CampagneCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche les campagnes par libelle.
     * @param criteres les critères de recherche
     * @param asyncCallback les informations de la campagne correspondant
     */
    void rechercherCampagnesParLibelle(CampagneCriteresRechercheLibelleModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Creer une campagne.
     * @param campagneModel la campagne à créer
     * @param asyncCallback la campagne crée
     */
    void creerCampagne(CampagneModel campagneModel, AsyncCallback<CampagneModel> asyncCallback);

    /**
     * Recherche une campagne par identifiant.
     * @param id l'identifiant de la campagne à rechercher
     * @param asyncCallback la campagne correspondant
     */
    void rechercherCampagnesParId(Long id, AsyncCallback<CampagneModel> asyncCallback);

    /**
     * Modification d'une campagne.
     * @param campagneModel la campagne contenant les nouvelles informations
     * @param asyncCallback la campagne modifiée
     */
    void modifierCampagne(CampagneModel campagneModel, AsyncCallback<CampagneModel> asyncCallback);
}

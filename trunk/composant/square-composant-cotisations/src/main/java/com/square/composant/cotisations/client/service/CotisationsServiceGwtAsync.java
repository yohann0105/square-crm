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
package com.square.composant.cotisations.client.service;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.composant.cotisations.client.model.CodeAiaLibelleModel;
import com.square.composant.cotisations.client.model.CotisationsCriteresRechercheModel;
import com.square.composant.cotisations.client.model.RetourCotisationModel;

/**
 * Interface asynchrone des services GWT pour le service des cotisations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface CotisationsServiceGwtAsync {

    /**
     * Récupère des cotisations.
     * @param criteresCotisations les criteres de recherche de cotisations
     * @param asyncCallback le callback
     */
    void recupererCotisations(RemotePagingCriteriasGwt<CotisationsCriteresRechercheModel> criteresCotisations,
        AsyncCallback<RetourCotisationModel> asyncCallback);

    /**
     * Récupération de la liste des contrats d'un assure.
     * @param uidAssure identifiant de l'assure.
     * @param libelle le libelle de recherche
     * @param asyncCallback le callback
     */
    void recupererContrats(Long uidAssure, String libelle, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des modes de paiements par le libelle.
     * @param libelle le libelle de recherche
     * @param asyncCallback le callback
     */
    void rechercherModesPaiementParCriteres(String libelle, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des situations par criteres.
     * @param libelle le libelle de recherche
     * @param asyncCallback le callback
     */
    void rechercherSituationsParCriteres(String libelle, AsyncCallback<List<CodeAiaLibelleModel>> asyncCallback);

}

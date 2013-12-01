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
import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.composant.cotisations.client.model.CodeAiaLibelleModel;
import com.square.composant.cotisations.client.model.CotisationsCriteresRechercheModel;
import com.square.composant.cotisations.client.model.RetourCotisationModel;

/**
 * Interface synchrone des services GWT pour le service des cotisations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/cotisationsService")
public interface CotisationsServiceGwt extends RemoteService {

    /**
     * Récupère des cotisations.
     * @param criteresCotisations les criteres de recherche de cotisations
     * @return les cotisations
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    RetourCotisationModel recupererCotisations(RemotePagingCriteriasGwt<CotisationsCriteresRechercheModel> criteresCotisations)
        throws GwtRunTimeExceptionGwt;

    /**
     * Récupére la liste des contrats.
     * @param uidAssure identifiant de l'assure.
     * @param libelle le libelle de recherche
     * @return la liste des contrats.
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    List<IdentifiantLibelleGwt> recupererContrats(Long uidAssure, String libelle) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des modes de paiement par le libelle.
     * @param libelle le libelle de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    List<IdentifiantLibelleGwt> rechercherModesPaiementParCriteres(String libelle) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des situations par criteres.
     * @param libelle le libelle de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    List<CodeAiaLibelleModel> rechercherSituationsParCriteres(String libelle) throws GwtRunTimeExceptionGwt;

}

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
package com.square.composant.prestations.square.client.service;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.composant.prestations.square.client.model.EntetePrestationModel;
import com.square.composant.prestations.square.client.model.IdentifiantEidLibelleGwt;
import com.square.composant.prestations.square.client.model.LigneDecompteModel;
import com.square.composant.prestations.square.client.model.PrestationCriteresRechercheModel;

/**
 * Interface synchrone des services GWT pour le service des ressources.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/prestationService")
public interface PrestationServiceGwt extends RemoteService {

    /**
     * Récupére la liste des prestations.
     * @param uidAssure identifiant de l'assure.
     * @param prenom une partie du prenom de l'assure.
     * @return la liste des beneficiaires.
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    List<IdentifiantLibelleGwt> recupererBenefPrestations(Long uidAssure, String prenom) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des origines de decomptes par le libelle.
     * @param libelle le libelle de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    List<IdentifiantLibelleGwt> rechercherOriginesDecompteParCriteres(String libelle) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des actes de decomptes par criteres.
     * @param libelle le libelle de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    List<IdentifiantEidLibelleGwt> rechercherActesDecompteParCriteres(String libelle) throws GwtRunTimeExceptionGwt;

    /**
     * Récupére la liste des prestations selon des critères.
     * @param criteres les critères de recherche avec les informations de pagination et de tri
     * @return le resultat de la recherche.
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    RemotePagingResultsGwt<EntetePrestationModel> rechercherEntetesPrestationParCritreres(
            RemotePagingCriteriasGwt<PrestationCriteresRechercheModel> criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Récupére les lignes d'un decompte de prestation par son numéro.
     * @param idAdherent l'id de l'adherent
     * @param numeroDecompte le numero du decompte
     * @param colonneTri la colonne pour le tri
     * @param triAsc indique si tri ascendant ou descendant
     * @return les lignes
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    List<LigneDecompteModel> recupererLignesPrestationsByNumeroDecompte(Long idAdherent, String numeroDecompte, String colonneTri, int triAsc)
    throws GwtRunTimeExceptionGwt;

}

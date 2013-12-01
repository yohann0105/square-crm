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
import com.square.client.gwt.client.model.BeneficiaireModel;
import com.square.client.gwt.client.model.CoordonneesModel;
import com.square.client.gwt.client.model.PersonneCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneDoublonModel;
import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.model.PersonnePermissionOngletModel;
import com.square.client.gwt.client.model.PersonnePhysiqueCopieModel;
import com.square.client.gwt.client.model.PersonneSimpleModel;
import com.square.client.gwt.client.model.TelephoneModel;
import com.square.composant.fusion.square.client.model.RechercheDoublonCritereModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosPersonneSyncModel;

/**
 * Interface asynchrone des services GWT pour le service des personnes physique.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface PersonnePhysiqueServiceGwtAsync extends HasCompteurServiceGwtAsync {

    /**
     * Récupération de la liste des personnes physique selon des critères.
     * @param criteres les critères de recherche avec les informations de pagination et de tri
     * @param asyncCallback le callback
     */
    void rechercherPersonneFullTextParCriteres(RemotePagingCriteriasGwt<PersonneCriteresRechercheModel> criteres,
        AsyncCallback<RemotePagingResultsGwt<PersonneSimpleModel>> asyncCallback);

    /**
     * Création ou de mise à jour d'une personne.
     * @param personne la personne à créer ou à mettre à jour.
     * @param listeBeneficiaire la liste des beneficiaires.
     * @param coordonnees coordonnées de la personne.
     * @param asyncCallback le callback
     */
    void creerPersonnePhysique(PersonneModel personne, List<BeneficiaireModel> listeBeneficiaire, CoordonneesModel coordonnees,
        AsyncCallback<PersonneModel> asyncCallback);

    /**
     * Recherche d'une personneSimple par son identifiant.
     * @param id l'identifiant de la personne à retrouver.
     * @param asyncCallback le callback
     */
    void rechercherPersonneSimpleParIdentifiant(Long id, AsyncCallback<PersonneSimpleModel> asyncCallback);

    /**
     * Recherche une personne par son identifiant et renvoi toutes les informations de cette personne.
     * @param id l'identifiant de la personne à retrouver
     * @param asyncCallback la personne apres la copie.
     */
    void rechercherPersonneParIdentifiant(Long id, AsyncCallback<PersonneModel> asyncCallback);

    /**
     * Modification d'une personne.
     * @param personne la personne à modifier.
     * @param asyncCallback la personne apres la copie.
     */
    void modifierPersonnePhysique(PersonneModel personne, AsyncCallback<PersonneModel> asyncCallback);

    /**
     * Creer une Copie d'une personne à partir d'une personne source.
     * @param infosCopie les informations de la nouvelle personne avec l'identifiant de la personne source.
     * @param asyncCallback la personne apres la copie.
     */
    void creerUneCopiePersonne(PersonnePhysiqueCopieModel infosCopie, AsyncCallback<PersonneModel> asyncCallback);

    /**
     * Construit le modèle d'une personne pour le tarificateur Square.
     * @param idPersonne identifiant de la personne.
     * @param asyncCallback la personne adaptée pour le tarificateur.
     */
    void construirePersonneTarificateur(Long idPersonne,
        AsyncCallback<com.square.composant.tarificateur.square.client.model.personne.PersonneModel> asyncCallback);

    /**
     * Met a jour les informations de paiement de la famille.
     * @param listeInfos les infos de paiement
     * @param asyncCallback la personne apres la copie.
     */
    void mettreAJourInfosAdhesion(List<InfosPersonneSyncModel> listeInfos, AsyncCallback<Object> asyncCallback);

    /**
     * Charge les possibilités pour une personne d'ouvrir certains onglets.
     * @param uidPersonne l'identifiant de la personne.
     * @param asyncCallback le résultat de l'appel asynchrone.
     */
    void chargerPermissionOngletPersonne(Long uidPersonne, AsyncCallback<PersonnePermissionOngletModel> asyncCallback);

    /**
     * Controler le format de telephone.
     * @param telephone le telephone
     * @param asyncCallback le callback
     */
    void controlerTelephone(TelephoneModel telephone, AsyncCallback<CoordonneesModel> asyncCallback);

    /**
     * Recherche de personne en doublons.
     * @param criteres les critères de recherche.
     * @param asyncCallback le callback.
     */
    void rechercherDoublonsPersonneParCriteres(RechercheDoublonCritereModel criteres, AsyncCallback<List<PersonneDoublonModel>> asyncCallback);

    /**
     * Détermine si la personne passée en aprametre possède au moins un contrat.
     * @param uidPersonne l'identifant de la personne.
     * @param asyncCallback le callback.
     */
    void hasPersonneContrats(Long uidPersonne, AsyncCallback<Boolean> asyncCallback);
}

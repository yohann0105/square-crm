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

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
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
 * Interface synchrone des services GWT pour le service des personnes.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/personnePhysiqueService")
public interface PersonnePhysiqueServiceGwt extends HasCompteurServiceGwt {

    /**
     * Récupération de la liste des personnes selon des critères.
     * @param criteres les critères de recherche avec les informations de pagination et de tri
     * @return la liste des personnes correspondant aux critères
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    RemotePagingResultsGwt<PersonneSimpleModel> rechercherPersonneFullTextParCriteres(RemotePagingCriteriasGwt<PersonneCriteresRechercheModel> criteres)
        throws GwtRunTimeExceptionGwt;

    /**
     * Création ou de mise à jour d'une personne.
     * @param personne la personne à créer ou à mettre à jour.
     * @param listeBeneficiaire la liste des beneficiaires.
     * @param coordonnees coordonnées de la personne.
     * @return Personne la personne créée.
     * @throws GwtRunTimeExceptionGwt
     */
    PersonneModel creerPersonnePhysique(PersonneModel personne, List<BeneficiaireModel> listeBeneficiaire, CoordonneesModel coordonnees)
        throws GwtRunTimeExceptionGwt;

    /**
     * Recherche d'une personneSimple par son identifiant.
     * @param id l'identifiant de la personne à retrouver.
     * @return la personne simple dto contenant les informations de la personne.
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    PersonneSimpleModel rechercherPersonneSimpleParIdentifiant(Long id) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche une personne par son identifiant et renvoi toutes les informations de cette personne.
     * @param id l'identifiant de la personne à retrouver
     * @return la personne contenant tous les informations de la personne
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    PersonneModel rechercherPersonneParIdentifiant(Long id) throws GwtRunTimeExceptionGwt;

    /**
     * Modification d'une personne.
     * @param personne la personne à modifier.
     * @return la personne modifier.
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    PersonneModel modifierPersonnePhysique(PersonneModel personne) throws GwtRunTimeExceptionGwt;

    /**
     * Creer une Copie d'une personne à partir d'une personne source.
     * @param infosCopie les informations à copier
     * @return la personne apres la copie.
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    PersonneModel creerUneCopiePersonne(PersonnePhysiqueCopieModel infosCopie) throws GwtRunTimeExceptionGwt;

    /**
     * Construit le modèle d'une personne pour le tarificateur Square.
     * @param idPersonne identifiant de la personne.
     * @return la personne adaptée pour le tarificateur.
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    com.square.composant.tarificateur.square.client.model.personne.PersonneModel construirePersonneTarificateur(Long idPersonne) throws GwtRunTimeExceptionGwt;

    /**
     * Met a jour les informations de paiement de la famille.
     * @param listeInfos les infos de paiement
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    void mettreAJourInfosAdhesion(List<InfosPersonneSyncModel> listeInfos) throws GwtRunTimeExceptionGwt;

    /**
     * Charge les possibilités pour une personne d'ouvrir certains onglets.
     * @param uidPersonne l'identifiant de la personne.
     * @return l'ensemble des permissions.
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    PersonnePermissionOngletModel chargerPermissionOngletPersonne(Long uidPersonne) throws GwtRunTimeExceptionGwt;

    /**
     * Controler le format de telephone.
     * @param telephone le telephone
     */
    void controlerTelephone(TelephoneModel telephone) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche de personne en doublons.
     * @param criteres les critères de recherche.
     * @return la liste des doublons.
     */
    List<PersonneDoublonModel> rechercherDoublonsPersonneParCriteres(RechercheDoublonCritereModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Détermine si la personne passée en aprametre possède au moins un contrat.
     * @param uidPersonne l'identifant de la personne.
     * @return vrai si la personne a au moins un contrat, faux sinon.
     */
    Boolean hasPersonneContrats(Long uidPersonne) throws GwtRunTimeExceptionGwt;
}

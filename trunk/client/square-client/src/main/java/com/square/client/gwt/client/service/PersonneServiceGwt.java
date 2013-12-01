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

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.client.gwt.client.model.AdresseCreationModel;
import com.square.client.gwt.client.model.AdresseModel;
import com.square.client.gwt.client.model.CoordonneesModel;
import com.square.client.gwt.client.model.PersonneBaseModel;
import com.square.client.gwt.client.model.PersonneRelationModel;
import com.square.client.gwt.client.model.RelationCriteresRechercheModel;
import com.square.client.gwt.client.model.RelationInfosModel;
import com.square.client.gwt.client.model.RelationModel;

/**
 * Interface synchrone des services GWT pour le service des personnes physique.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/personneService")
public interface PersonneServiceGwt extends RemoteService {

    /**
     * Enregistre ou modifie coordonnées d'une personne.
     * @param coordonnees les coordonnées
     * @param impacterFamille précise si les coordonnées mises à jour doivent aussi impacter les coordonnées des membres de la famille de la personne
     * @param forcerDesactivationEservices force la desactivation du eservice
     * @return les coordonnees enregistrée
     * @throws GwtRunTimeExceptionGwt
     */
    CoordonneesModel creerOuMettreAJourCoordonnees(CoordonneesModel coordonnees, Boolean impacterFamille, Boolean forcerDesactivationEservices)
        throws GwtRunTimeExceptionGwt;

    /**
     * Recherche les coordonnées d'une personne.
     * @param idPersonne l'identifiant de la personne
     * @return les coordonnées de la personne
     * @throws GwtRunTimeExceptionGwt
     */
    CoordonneesModel rechercherCoordonneesParIdPersonne(Long idPersonne) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche les relations par critères.
     * @param criteres les critères de recherche
     * @return la liste des relations correspondant
     */
    List<RelationInfosModel<? extends PersonneRelationModel>> rechercherRelationsParCritreres(RelationCriteresRechercheModel criteres)
        throws GwtRunTimeExceptionGwt;

    /**
     * Creer une relation.
     * @param relation la relation à créer
     * @return la relation créée
     */
    RelationModel creerRelation(RelationModel relation) throws GwtRunTimeExceptionGwt;

    /**
     * Modifie des relations.
     * @param relations la liste des relations à modifier
     */
    void modifierRelations(List<RelationModel> relations) throws GwtRunTimeExceptionGwt;

    /**
     * Active ou desactive une relation.
     * @param idRelation l'id de la relation à activer/desactiver
     * @param active le flag pour activer/desactiver
     */
    void activerRelation(Long idRelation, boolean active) throws GwtRunTimeExceptionGwt;

    /**
     * Recuperer une personne indiférement du type par identifiant.
     * @param id l'ientifiant.
     * @return la personne trouvé null si aucune occurence.
     */
    PersonneBaseModel rechercherPersonneParId(final Long id) throws GwtRunTimeExceptionGwt;

    /**
     * Data store pour alimenter les modes graphique des relations.
     * @param idPersonne identifiant de la personne.
     * @param filtreGroupement filtre sur les groupements.
     * @param filtrePasDansGroupement non filtre sur les groupements.
     * @return les données.
     * @throws GwtRunTimeExceptionGwt si probléme.
     */
    String[][] relationOrgChartDataStore(final Long idPersonne, final List<Long> filtreGroupement, final List<Long> filtrePasDansGroupement)
        throws GwtRunTimeExceptionGwt;

    /**
     * Détermine si une personne possède déjà une relation conjoint.
     * @param idPersonne l'identifiant de la personne.
     * @return true si la personne a déjà une relation conjoint, false si non.
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    Boolean hasConjoint(Long idPersonne) throws GwtRunTimeExceptionGwt;

    /**
     * Renvoie l'id du conjoint si il existe.
     * @param idPersonne l'identifiant de la personne
     * @return l'id du conjoint de la personne, null sinon
     * @throws GwtRunTimeExceptionGwt
     */
    Long getIdConjoint(Long idPersonne) throws GwtRunTimeExceptionGwt;

    /**
     * Renvoie le libelle a pour enfant.
     * @return le libelle a pour enfant
     * @throws GwtRunTimeExceptionGwt
     */
    String getLibelleAPourEnfant() throws GwtRunTimeExceptionGwt;

    /**
     * Ajoute une nouvelle adresse à une personne.
     * @param idPersonne l'identifiant de la personne.
     * @param nouvelleAdresse la nouvelle adresse.
     * @param impacterFamille précise si l'adresse doit être ajoutée aussi aux membres de la famille de la personne
     * @return les informations de la création de l'adresse.
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    AdresseCreationModel ajouterNouvelleAdresse(Long idPersonne, AdresseModel nouvelleAdresse, Boolean impacterFamille) throws GwtRunTimeExceptionGwt;
}

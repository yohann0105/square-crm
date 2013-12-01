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
import com.square.client.gwt.client.model.AdresseCreationModel;
import com.square.client.gwt.client.model.AdresseModel;
import com.square.client.gwt.client.model.CoordonneesModel;
import com.square.client.gwt.client.model.PersonneBaseModel;
import com.square.client.gwt.client.model.PersonneRelationModel;
import com.square.client.gwt.client.model.RelationCriteresRechercheModel;
import com.square.client.gwt.client.model.RelationInfosModel;
import com.square.client.gwt.client.model.RelationModel;

/**
 * Interface asynchrone des services GWT pour le service des personnes physique.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface PersonneServiceGwtAsync {

    /**
     * Enregistre ou modifie coordonnées d'une personne.
     * @param coordonnees les coordonnées
     * @param impacterFamille précise si les coordonnées mises à jour doivent aussi impacter les coordonnées des membres de la famille de la personne
     * @param forcerDesactivationEservices force la desactivation du eservice
     * @param asyncCallback le callback
     */
    void creerOuMettreAJourCoordonnees(CoordonneesModel coordonnees, Boolean impacterFamille, Boolean forcerDesactivationEservices,
        AsyncCallback<CoordonneesModel> asyncCallback);

    /**
     * Recherche les coordonnées d'une personne.
     * @param idPersonne l'identifiant de la personne
     * @param asyncCallback le callback
     */
    void rechercherCoordonneesParIdPersonne(Long idPersonne, AsyncCallback<CoordonneesModel> asyncCallback);

    /**
     * Recherche les relations par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherRelationsParCritreres(RelationCriteresRechercheModel criteres,
        AsyncCallback<List<RelationInfosModel<? extends PersonneRelationModel>>> asyncCallback);

    /**
     * Creer une relation.
     * @param relation la relation à créer
     * @param asyncCallback le callback
     */
    void creerRelation(RelationModel relation, AsyncCallback<RelationModel> asyncCallback);

    /**
     * Modifie des relations.
     * @param relations la liste des relations à modifier.
     * @param asyncCallback void .
     */
    void modifierRelations(List<RelationModel> relations, AsyncCallback<Void> asyncCallback);

    /**
     * Active ou desactive une relation.
     * @param idRelation l'id de la relation à activer/desactiver
     * @param active le flag pour activer/desactiver
     * @param asyncCallback void .
     */
    void activerRelation(Long idRelation, boolean active, AsyncCallback<Void> asyncCallback);

    /**
     * Recuperer une personne indiférement du type par identifiant.
     * @param id l'ientifiant.
     * @param asyncCallback callback
     */
    void rechercherPersonneParId(final Long id, AsyncCallback<PersonneBaseModel> asyncCallback);

    /**
     * Data store pour alimenter les modes graphique des relations.
     * @param idPersonne identifiant de la personne.
     * @param filtreGroupement filtre sur les groupements.
     * @param filtrePasDansGroupement non filtre sur les groupements.
     * @param asyncCallback void .
     */
    void relationOrgChartDataStore(final Long idPersonne, final List<Long> filtreGroupement, final List<Long> filtrePasDansGroupement,
        AsyncCallback<String[][]> asyncCallback);

    /**
     * Détermine si une personne possède déjà une relation conjoint.
     * @param idPersonne l'identifiant de la personne.
     * @param asyncCallback l'objet du retour asynchrone.
     */
    void hasConjoint(Long idPersonne, AsyncCallback<Boolean> asyncCallback);

    /**
     * Renvoie l'id du conjoint si il existe.
     * @param idPersonne l'identifiant de la personne
     * @param asyncCallback l'objet du retour asynchrone.
     */
    void getIdConjoint(Long idPersonne, AsyncCallback<Long> asyncCallback);

    /**
     * Renvoie le libelle a pour enfant.
     * @param asyncCallback l'objet du retour asynchrone.
     */
    void getLibelleAPourEnfant(AsyncCallback<String> asyncCallback);

    /**
     * Ajoute une nouvelle adresse à une personne.
     * @param idPersonne l'identifiant de la personne.
     * @param nouvelleAdresse la nouvelle adresse.
     * @param impacterFamille précise si l'adresse doit être ajoutée aussi aux membres de la famille de la personne
     * @param asyncCallback l'objet du retour asynchrone.
     */
    void ajouterNouvelleAdresse(Long idPersonne, AdresseModel nouvelleAdresse, Boolean impacterFamille, AsyncCallback<AdresseCreationModel> asyncCallback);
}

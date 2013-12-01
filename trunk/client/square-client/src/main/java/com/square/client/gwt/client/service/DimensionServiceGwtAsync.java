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

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.client.gwt.client.model.ActionNatureResultatCriteresRechercheModel;
import com.square.client.gwt.client.model.CaisseSimpleModel;
import com.square.client.gwt.client.model.CodePostalCommuneModel;
import com.square.client.gwt.client.model.DimensionCritereCodePostalCommuneModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheDepartementModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheObjetModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheRessourceFonctionModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheResultatActionModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheSousObjetModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheTypeRelationModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheCaisseModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRegimeModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRessourceModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.IdentifiantLibelleBooleanModel;
import com.square.client.gwt.client.model.IdentifiantLibelleDepartementCodeModel;
import com.square.client.gwt.client.model.IdentifiantLibelleTypeRelationModel;
import com.square.client.gwt.client.model.ItemValueModel;
import com.square.client.gwt.client.model.PaysSimpleModel;

/**
 * Interface asynchrone des services GWT pour le service des dimensions.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface DimensionServiceGwtAsync {

    /**
     * Recherche des natures de personnes physiques par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherNaturePersonnePhysiqueParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des natures de personnes physiques par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherNaturePersonneMoraleParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des natures de voies par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherNatureVoieParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des code Postaux par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherCodePostauxParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des villes par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherCommuneParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des civilités par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherCiviliteParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche de la nature des telephones par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherNatureTelephoneParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des professions par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherProfessionParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleBooleanModel>> asyncCallback);

    /**
     * Recherche des pays par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherPaysParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleBooleanModel>> asyncCallback);

    /**
     * Recherche des pays par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherSimplePaysParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<PaysSimpleModel>> asyncCallback);

    /**
     * Recherche des codesPostaux - Commune par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherCodesPostauxCommunes(DimensionCritereCodePostalCommuneModel criteres, AsyncCallback<List<CodePostalCommuneModel>> asyncCallback);

    /**
     * Recherche des caisses par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherCaisseParCriteres(DimensionCriteresRechercheCaisseModel criteres, AsyncCallback<List<CaisseSimpleModel>> asyncCallback);

    /**
     * Recherche des regimes par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherRegimeParCriteres(DimensionCriteresRechercheRegimeModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des Types d'adresses par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherTypeAdresseParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des Segments par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste d'identifiant libelle correspondant aux critères de recherche .
     */
    void rechercherSegmentParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des réseaux de vente par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste d'identifiant libelle correspondant aux critères de recherche
     */
    void rechercherReseauVenteParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des CSP par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste d'identifiant libelle correspondant aux critères de recherche
     */
    void rechercherCSPParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des situations familialles par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste d'identifiant libelle correspondant aux critères de recherche
     */
    void rechercherSitFamParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des départements par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherDepartementParCriteres(DimensionCritereRechercheDepartementModel criteres,
        AsyncCallback<List<IdentifiantLibelleDepartementCodeModel>> asyncCallback);

    /**
     * Recherche des formes juridiques.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherFormesJuridiques(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des types de relations.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherTypesRelations(DimensionCritereRechercheTypeRelationModel criteres, AsyncCallback<List<IdentifiantLibelleTypeRelationModel>> asyncCallback);

    /**
     * Recherche des types de campagnes.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste des types de campagne
     */
    void rechercherTypesCampagnes(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des status de campagnes.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste des status de campagne
     */
    void rechercherStatutsCampagnes(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche les types des actions.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste des types des actions
     */
    void rechercherTypesActions(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche les natures des actions.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste des natures des actions
     */
    void rechercherNatureActions(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche les natures des résultats des actions.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste des natures des résultats des actions
     */
    void rechercherNatureResultatActions(ActionNatureResultatCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche les priorité des actions.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste des priorités des actions
     */
    void rechercherPrioriteActions(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche les résultats des actions.
     * @param criteres les critères de recherche.
     * @param asyncCallback la liste des résultats des actions
     */
    void rechercherResultatActions(DimensionCritereRechercheResultatActionModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche les statuts des actions.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste des statuts des actions
     */
    void rechercherStatutActions(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche les objets des actions.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste des statuts des actions
     */
    void rechercherObjetActions(DimensionCritereRechercheObjetModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche les sous objets des actions.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste des sous objets des actions
     */
    void rechercherSousObjetActions(DimensionCritereRechercheSousObjetModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche de l'agence.
     * @param criteres les criteres de recherche.
     * @param asyncCallback l'agence trouvée.
     */
    void rechercherAgenceParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche d'une agence par ressource.
     * @param idRessource la ressource sélectionnée.
     * @param asyncCallback l'agence trouvée.
     */
    void rechercherAgenceParIdRessource(Long idRessource, AsyncCallback<IdentifiantLibelleGwt> asyncCallback);

    /**
     * Recherche de la fonction.
     * @param criteres les criteres de recherche.
     * @param asyncCallback la fonction trouvée.
     */
    void rechercherRessourceFonctionParCriteres(DimensionCritereRechercheRessourceFonctionModel criteres,
        AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche du service.
     * @param criteres les criteres de recherche.
     * @param asyncCallback le service trouvé.
     */
    void rechercherRessourceServiceParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche de l'etat.
     * @param criteres les criteres de recherche.
     * @param asyncCallback l'etat trouvé.
     */
    void rechercherRessourceEtatParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des ressources.
     * @param criteres les critères de recherche
     * @param asyncCallback les ressources trouvées.
     */
    void rechercherRessourceParCriteres(DimensionCriteresRechercheRessourceModel criteres, AsyncCallback<List<DimensionRessourceModel>> asyncCallback);

    /**
     * Rechercher la liste du nombre d'enfants.
     * @param nombreEnfant le nombre d'enfants
     * @param libNoEnfants le libelle si pas d'enfant
     * @param libEnfant le libelle si un enfant
     * @param libEnfants le libelle si plusieurs enfants
     * @param asyncCallback la liste du nombre d'enfants
     * @param suggest la suggesstion
     */
    void rechercherListeNombreEnfants(int nombreEnfant, String libNoEnfants, String libEnfant, String libEnfants, String suggest,
        AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Charge la liste des types de relation en fonction de critères.
     * @param criteres les critères de recherche
     * @param asyncCallback la liste de type correspondant
     */
    void chargerListeRelation(DimensionCritereRechercheTypeRelationModel criteres, AsyncCallback<List<ItemValueModel>> asyncCallback);

    /**
     * Charge la liste des conjoints.
     * @param pasConjoint le libelel pas de conjoint
     * @param unConjoint le libelle un conjoint
     * @param suggest la suggestion
     * @param asyncCallback la liste de conjoints
     */
    void rechercherListeConjoint(String pasConjoint, String unConjoint, String suggest, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Charge la liste des types de relations.
     * @param liste la liste
     * @param suggest la suggestion
     * @param asyncCallback la liste des types de relations correspondant
     */
    void rechercherListeTypesRelations(List<IdentifiantLibelleGwt> liste, String suggest, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des durées d'action par critères.
     * @param criteres les critères de recherche.
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void rechercherDureeActionParCriteres(DimensionCriteresRechercheModel criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);
}

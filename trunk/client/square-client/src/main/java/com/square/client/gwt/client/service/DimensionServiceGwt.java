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
import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
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
 * Interface synchrone des services GWT pour le service des dimensions.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/dimensionService")
public interface DimensionServiceGwt extends RemoteService {

    /**
     * Recherche des natures de personnes physiques par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherNaturePersonnePhysiqueParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des natures de personnes morales par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherNaturePersonneMoraleParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des natures de voies par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherNatureVoieParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des code Postaux par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherCodePostauxParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des villes par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherCommuneParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des civilités par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherCiviliteParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche de la nature des telephones par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherNatureTelephoneParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des professions par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleBooleanModel> rechercherProfessionParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des pays par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleBooleanModel> rechercherPaysParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des pays par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<PaysSimpleModel> rechercherSimplePaysParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des codesPostaux - Commune par critères.
     * @param criteres les critères de recherche
     * @return la liste des des codesPostaux - Commune
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<CodePostalCommuneModel> rechercherCodesPostauxCommunes(DimensionCritereCodePostalCommuneModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des caisses par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<CaisseSimpleModel> rechercherCaisseParCriteres(DimensionCriteresRechercheCaisseModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des regimes par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherRegimeParCriteres(DimensionCriteresRechercheRegimeModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des Types d'adresses par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherTypeAdresseParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des Segments par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherSegmentParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des réseaux de vente par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherReseauVenteParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des CSP par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherCSPParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des situations familialles par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherSitFamParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des départements par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleDepartementCodeModel> rechercherDepartementParCriteres(DimensionCritereRechercheDepartementModel criteres)
        throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des formes juridiques par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherFormesJuridiques(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des types de relations.
     * @param criteres les critères de recherche
     * @return la liste des identifiants libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleTypeRelationModel> rechercherTypesRelations(DimensionCritereRechercheTypeRelationModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Charge la liste des conjoints.
     * @param pasConjoint le libelel pas de conjoint
     * @param unConjoint le libelle un conjoint
     * @param suggest la suggestion
     * @return la liste de conjoints
     */
    List<IdentifiantLibelleGwt> rechercherListeConjoint(String pasConjoint, String unConjoint, String suggest);

    /**
     * Charge la liste des types de relations.
     * @param liste la liste
     * @param suggest la suggestion
     * @return la liste des types de relations correspondant
     */
    List<IdentifiantLibelleGwt> rechercherListeTypesRelations(List<IdentifiantLibelleGwt> liste, String suggest);

    /**
     * Recherche les types des actions.
     * @param criteres les critères de recherche.
     * @return la liste des types d'actions trouvées.
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherTypesActions(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recheche les natures des actions.
     * @param criteres les critères de recherche
     * @return la liste des natures des actions
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherNatureActions(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche les natures des résultats des actions.
     * @param criteres les critère de recherche
     * @return la liste des natures des résultats des actions
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherNatureResultatActions(ActionNatureResultatCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche les priorités des actions.
     * @param criteres les critères de recherche
     * @return la liste des priorités des actions
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherPrioriteActions(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche les résultats des actions.
     * @param criteres les critères de recherche
     * @return la liste des résultats des actions
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherResultatActions(DimensionCritereRechercheResultatActionModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche les status des actions.
     * @param criteres les critères de recherche
     * @return la liste des statuts des actions.
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherStatutActions(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * recherche les statuts des actions.
     * @param criteres les critères de recherche
     * @return la liste des objets des actions.
     * @throws GwtRunTimeExceptionGwt
     */
    List<IdentifiantLibelleGwt> rechercherObjetActions(DimensionCritereRechercheObjetModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des sous objets des actions.
     * @param criteres les critères de recherche
     * @return la liste des sous objets des actions
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherSousObjetActions(DimensionCritereRechercheSousObjetModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des types de campagnes.
     * @param criteres les critères de recherche
     * @return la liste des types de campagne
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherTypesCampagnes(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des status de campagnes.
     * @param criteres les critères de recherche
     * @return la liste des status de campagne
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherStatutsCampagnes(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche de l'agence.
     * @param criteres les criteres de recherche.
     * @return l'agence trouvée.
     */
    List<IdentifiantLibelleGwt> rechercherAgenceParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche d'une agence par ressource.
     * @param idRessource la ressource sélectionnée.
     * @return agence trouvé
     */
    IdentifiantLibelleGwt rechercherAgenceParIdRessource(Long idRessource);

    /**
     * Recherche de la fonction.
     * @param criteres les criteres de recherche.
     * @return la fonction trouvée.
     */
    List<IdentifiantLibelleGwt> rechercherRessourceFonctionParCriteres(DimensionCritereRechercheRessourceFonctionModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche du service.
     * @param criteres les criteres de recherche.
     * @return le service trouvée.
     */
    List<IdentifiantLibelleGwt> rechercherRessourceServiceParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche de l'etat.
     * @param criteres les criteres de recherche.
     * @return l'etat trouvé.
     */
    List<IdentifiantLibelleGwt> rechercherRessourceEtatParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des ressources.
     * @param criteres les critères de recherche
     * @return la ressource trouvée.
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<DimensionRessourceModel> rechercherRessourceParCriteres(DimensionCriteresRechercheRessourceModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Rechercher la liste du nombre d'enfants.
     * @param nombreEnfant le nombre d'enfants
     * @param libNoEnfants le libelle si pas d'enfant
     * @param libEnfant le libelle si un enfant
     * @param libEnfants le libelle si plusieurs enfants
     * @param suggest la suggestion
     * @return la liste du nombre d'enfants
     */
    List<IdentifiantLibelleGwt> rechercherListeNombreEnfants(int nombreEnfant, String libNoEnfants, String libEnfant, String libEnfants, String suggest);

    /**
     * Charge la liste des types de relation en fonction de critères.
     * @param criteres les critères de recherche
     * @return la liste de type correspondant
     */
    List<ItemValueModel> chargerListeRelation(DimensionCritereRechercheTypeRelationModel criteres);

    /**
     * Recherche des durées d'action par critères.
     * @param criteres les critères de recherche.
     * @return la liste des durées retrouvées.
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherDureeActionParCriteres(DimensionCriteresRechercheModel criteres) throws GwtRunTimeExceptionGwt;
}

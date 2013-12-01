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
package com.square.core.service.interfaces;

import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

import com.square.core.model.dto.ActionNatureResultatCriteresRechercheDto;
import com.square.core.model.dto.CaisseDto;
import com.square.core.model.dto.CaisseSimpleDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.CommuneCriteresRechercherDto;
import com.square.core.model.dto.DimensionCritereRechercheCodePostalCommuneDto;
import com.square.core.model.dto.DimensionCritereRechercheDepartementDto;
import com.square.core.model.dto.DimensionCritereRechercheObjetDto;
import com.square.core.model.dto.DimensionCritereRechercheRessourceFonctionDto;
import com.square.core.model.dto.DimensionCritereRechercheResultatActionDto;
import com.square.core.model.dto.DimensionCritereRechercheSousObjetDto;
import com.square.core.model.dto.DimensionCritereRechercheTypeRelationDto;
import com.square.core.model.dto.DimensionCriteresRechercheCaisseDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.model.dto.DimensionCriteresRechercheRegimeDto;
import com.square.core.model.dto.DimensionCriteresRechercheRessourceDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.IdentifiantEidLibelleDto;
import com.square.core.model.dto.IdentifiantLibelleBooleanDto;
import com.square.core.model.dto.IdentifiantLibelleCodePostalCommuneDto;
import com.square.core.model.dto.IdentifiantLibelleDepartementCodeDto;
import com.square.core.model.dto.IdentifiantLibelleTypeRelationDto;
import com.square.core.model.dto.ModeleEmailDto;
import com.square.core.model.dto.PaysSimpleDto;

/**
 * Service de récupération des listes des tables de dimensions.
 * @author cblanchard - SCUB
 */
public interface DimensionService {

    /**
     * Recherche des natures de personnes physiques par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherNaturePersonnePhysiqueParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des natures de personnes physiques par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherNaturePersonneMoraleParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des natures de voies par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherNatureVoieParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des code Postaux par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherCodePostauxParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des villes par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherCommuneParCriteres(CommuneCriteresRechercherDto criteres);

    /**
     * Recherche des civilités par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherCiviliteParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche de la nature des telephones par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherNatureTelephoneParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des professions par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherProfessionParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des professions par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleBooleanDto> rechercherProfessionBooleanParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des pays par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherPaysParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des pays par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleBooleanDto> rechercherPaysBooleanParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des régime par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherRegimeParCriteres(DimensionCriteresRechercheRegimeDto criteres);

    /**
     * Recherche des CSP par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherCSPParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des Segments par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherSegmentParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des situations familialles par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherSitFamParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des Types d'adresses par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherTypeAdresseParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des départements par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDepartementCodeDto> rechercherDepartementParCriteres(DimensionCritereRechercheDepartementDto criteres);

    /**
     * Recherche des réseaux de vente par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherReseauVenteParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des status des personnes par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherPersonneStatutParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des codesPostaux - Commune par critères.
     * @param criteres les critères de recherche
     * @return la liste des des codesPostaux - Commune
     */
    List<IdentifiantLibelleCodePostalCommuneDto> rechercherCodesPostauxCommunes(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des communes - codePostaux par critères.
     * @param criteres les critères de recherche
     * @return la liste des communes - code postaux
     */
    List<IdentifiantLibelleCodePostalCommuneDto> rechercherCommunesCodesPostaux(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des formes juridiques.
     * @param criteres les critères de recherche
     * @return la liste des formes juridiques
     */
    List<IdentifiantLibelleDto> rechercherFormesJuridiques(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des pays par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<PaysSimpleDto> rechercherSimplePaysParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des caisses par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<CaisseSimpleDto> rechercherCaisseParCriteres(DimensionCriteresRechercheCaisseDto criteres);

    /**
     * Recherche des types de relations.
     * @param criteres les critères de recherche
     * @return la liste des types de relations
     */
    List<IdentifiantLibelleTypeRelationDto> rechercherTypesRelations(DimensionCritereRechercheTypeRelationDto criteres);

    /**
     * Recherche des types d'actions.
     * @param criteres les critères de recherche
     * @return la liste des types d'action
     */
    List<IdentifiantLibelleDto> rechercherTypesActionsParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des objets d'action.
     * @param criteres les critères de recherche
     * @return la liste des objets d'action
     */
    List<IdentifiantLibelleDto> rechercherObetsActionsParCriteres(DimensionCritereRechercheObjetDto criteres);

    /**
     * Recherche des sous objets d'action.
     * @param criteres les critères de recherche
     * @return la liste des sous objets d'action
     */
    List<IdentifiantLibelleDto> rechercherSousObetsActionsParCriteres(DimensionCritereRechercheSousObjetDto criteres);

    /**
     * Recherche des statuts d'action.
     * @param criteres les critères de recherche
     * @return la liste des statuts des actions
     */
    List<IdentifiantLibelleDto> rechercherStatutActionsParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des natures d'action.
     * @param criteres les critères de recherche
     * @return la liste des natures des actions
     */
    List<IdentifiantLibelleDto> rechercherNatureActionsParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des résultats d'action.
     * @param criteres les critères de recherche
     * @return la liste des résultats d'actions
     */
    List<IdentifiantLibelleDto> rechercherResultatActionsParCriteres(DimensionCritereRechercheResultatActionDto criteres);

    /**
     * Recherche des résultats d'action.
     * @param criteres les critères de recherche
     * @return la liste des résultats d'actions
     */
    List<IdentifiantLibelleDto> rechercherNatureResultatActionsParCriteres(ActionNatureResultatCriteresRechercheDto criteres);

    /**
     * Recherche des prioriétés d'action.
     * @param criteres les critères de recherche
     * @return la liste des priorités d'actions
     */
    List<IdentifiantLibelleDto> rechercherPrioriteActionsParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des types de campagnes.
     * @param criteres les critères de recherche
     * @return la liste des types de campagne
     */
    List<IdentifiantLibelleDto> rechercherTypesCampagnes(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des status de campagnes.
     * @param criteres les critères de recherche
     * @return la liste des status de campagne
     */
    List<IdentifiantLibelleDto> rechercherStatutsCampagnes(DimensionCriteresRechercheDto criteres);

    /**
     * Récupère les informations de la ressource actuellement connectée.
     * @return les informations de la ressource connectée.
     */
    DimensionRessourceDto getRessourceConnectee();

    /**
     * Recherche des ressources.
     * @param criteres les critères de recherche
     * @return la ressource trouvée.
     */
    List<DimensionRessourceDto> rechercherRessourceParCriteres(DimensionCriteresRechercheRessourceDto criteres);

    /**
     * Recherche de l'agence.
     * @param criteres les criteres de recherche.
     * @return l'agence trouvée.
     */
    List<IdentifiantLibelleDto> rechercherAgenceParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche l'agence correspondant à l'identifiant externe passé en paramètre.
     * @param eid identifiant externe de l'agence recherchée.
     * @return l'agence trouvée.
     */
    IdentifiantEidLibelleDto rechercherAgenceParEid(String eid);

    /**
     * Recupère l'agence par l'identifiant de la ressource.
     * @param idRessource l'id de la ressource
     * @return l'agence
     */
    IdentifiantLibelleDto rechercherAgenceParIdRessource(Long idRessource);

    /**
     * Recupère un département par l'identifiant de la commune.
     * @param idCommune l'id de la commune
     * @return le département
     */
    IdentifiantLibelleDepartementCodeDto rechercherDepartementParIdCommune(Long idCommune);

    /**
     * Recherche une civilité par son identifiant.
     * @param idCivilite l'identifiant de la civilité
     * @return la civilité avec l'EID
     */
    IdentifiantEidLibelleDto rechercherCiviliteParId(Long idCivilite);

    /**
     * Recherche une catégorie socio-professionnelle par son identifiant.
     * @param idCSP l'identifiant de la CSP
     * @return la CSP
     */
    IdentifiantEidLibelleDto rechercherCSPParId(Long idCSP);

    /**
     * Recherche une situation familiale par son identifiant.
     * @param idSitFam l'identifiant de la situation familiale
     * @return la situation familiale
     */
    IdentifiantEidLibelleDto rechercherSitFamParId(Long idSitFam);

    /**
     * Recherche un régime par son identifiant.
     * @param idRegime l'identifiant du régime
     * @return le régime
     */
    IdentifiantEidLibelleDto rechercherRegimeParId(Long idRegime);

    /**
     * Recherche une caisse par son identifiant.
     * @param idCaisse l'identifiant de la caisse
     * @return la caisse
     */
    CaisseDto rechercherCaisseParId(Long idCaisse);

    /**
     * Recherche un type de voie par son identifiant.
     * @param idTypeVoie l'identifiant du type de voie
     * @return le type de voie
     */
    IdentifiantEidLibelleDto rechercherTypeVoieParId(Long idTypeVoie);

    /**
     * Recherche un statut par son identifiant.
     * @param idStatut l'identifiant du statut
     * @return le statut
     */
    IdentifiantEidLibelleDto rechercherStatutParId(Long idStatut);

    /**
     * Recherche un pays par son identifiant.
     * @param idPays l'identifiant du pays
     * @return le pays
     */
    IdentifiantEidLibelleDto rechercherPaysParId(Long idPays);

    /**
     * Recherche une commune par son identifiant.
     * @param idCommune l'identifiant de la commune.
     * @return la commune.
     */
    IdentifiantEidLibelleDto rechercherCommuneParId(Long idCommune);

    /**
     * Recherche un code postal par son identifiant.
     * @param idCodePostal l'identifiant du code postal.
     * @return le code postal.
     */
    IdentifiantEidLibelleDto rechercherCodePostalParId(Long idCodePostal);

    /**
     * Recherche de la dimension Fonction.
     * @param criteres les critères de recherche.
     * @return la liste des fonctions trouvée.
     */
    List<IdentifiantLibelleDto> rechercherRessourceFonctionParCriteres(DimensionCritereRechercheRessourceFonctionDto criteres);

    /**
     * Recherche de la dimension Service.
     * @param criteres les critères de recherche.
     * @return la liste des services trouvée.
     */
    List<IdentifiantLibelleDto> rechercherRessourceServiceParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche de la dimension Etat.
     * @param criteres les critères de recherche.
     * @return la liste des etats trouvée.
     */
    List<IdentifiantLibelleDto> rechercherRessourceEtatParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche de la dimension CodePostalCommune.
     * @param criteres les critères de recherche.
     * @return la liste des codes postaux - communes trouvés.
     */
    List<CodePostalCommuneDto> rechercherCodesPostauxCommunesParCriteres(DimensionCritereRechercheCodePostalCommuneDto criteres);

    /**
     * Recherche un code postal - commune par son identifiant.
     * @param idCodePostalCommune l'identifiant du code postal - commune
     * @return le code postal - commune
     */
    CodePostalCommuneDto rechercherCodePostalCommuneParId(Long idCodePostalCommune);

    /**
     * Détermine si la ressource connectée a un certains rôle.
     * @param idRole l'identifiant du rôle.
     * @return true si elle a le rôle, false si non.
     */
    boolean hasRessourceConnecteeRole(Long idRole);

    /**
     * Détermine si une ressource a un certains rôle.
     * @param idRessource l'identifiant de la ressource.
     * @param idRole l'identifiant du rôle.
     * @return true si elle a le rôle, false si non.
     */
    boolean hasRessourceRole(Long idRessource, Long idRole);

    /**
     * Recherche de la dimension ModeleEmail.
     * @param criteres les critères de recherche.
     * @return la liste des modèles d'e-mail trouvés.
     */
    List<IdentifiantLibelleDto> rechercherListeModelesEmails(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche de la dimension ModeleEmail.
     * @param criteres les critères de recherche.
     * @return la liste des modèles d'e-mail trouvés.
     */
    List<ModeleEmailDto> rechercherModelesEmails(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche des durées d'action par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherDureeActionParCriteres(DimensionCriteresRechercheDto criteres);
}

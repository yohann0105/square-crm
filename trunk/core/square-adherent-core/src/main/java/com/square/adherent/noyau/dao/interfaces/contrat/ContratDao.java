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
package com.square.adherent.noyau.dao.interfaces.contrat;

import java.util.Calendar;
import java.util.List;

import com.square.adherent.noyau.dto.adherent.contrat.ContratCollectifCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.CritereRechercheContratDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBeneficiaireDto;
import com.square.adherent.noyau.dto.adherent.contrat.PopulationCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.PopulationDto;
import com.square.adherent.noyau.dto.adherent.contrat.ProduitCollectifAdherentCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.ProduitCollectifAdherentDto;
import com.square.adherent.noyau.dto.adherent.contrat.RatioPrestationCotisationDto;
import com.square.adherent.noyau.dto.adherent.contrat.RecapitulatifPopulationDto;
import com.square.adherent.noyau.dto.adherent.contrat.TypePayeurCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.TypePayeurDto;
import com.square.adherent.noyau.model.data.contrat.AjustementTarif;
import com.square.adherent.noyau.model.data.contrat.Contrat;
import com.square.adherent.noyau.model.data.contrat.SyntheseContrat;

/**
 * Interface d'accès aux données liées aux contrats.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface ContratDao {

    /**
     * Récupération du contrat santé de la personne, contrat en cours.
     * @param idPersonne l'identifiant de la personne pour laquelle on recherche
     * @return le contrat
     */
    Contrat getContratSantePersonneEnCours(Long idPersonne);

    /**
     * Récupération du contrat sante de la personne, contrat désactivé mais toujours dans le délai accessible.
     * @param idPersonne l'identifiant de la personne pour laquelle on recherche
     * @param dateInactiviteGarantieSante date pour considérer une garantie santé comme inactive
     * @return le contrat
     */
    Contrat getContratSantePersonneInactifXMois(Long idPersonne, Calendar dateInactiviteGarantieSante);

    /**
     * Récupération du contrat sante de la personne, contrat à venir.
     * @param idPersonne l'identifiant de la personne pour laquelle on recherche
     * @return le contrat
     */
    Contrat getContratSantePersonneAVenir(Long idPersonne);

    /**
     * Récupère un contrat par son identifiant (uid).
     * @param uidContrat l'identifiant du contrat.
     * @return le contrat.
     */
    Contrat getContratById(Long uidContrat);

    /**
     * Récupère la liste des bénéficiaires des contrats en cours d'un assuré.
     * @param listeUidContrats la liste des identifiants des contrats.
     * @param uidAssure l'identifiant de l'assuré.
     * @param filtrerContratEnCOurs booléen indiquant si on veut filtrer sur les contrats en cours.
     * @return la liste des bénéficiaires des contrats.
     */
    List<GarantieBeneficiaireDto> getListeBeneficiairesFromContrats(List<Long> listeUidContrats, Long uidAssure, boolean filtrerContratEnCOurs);

    /**
     * Recherche les contrats correspondants à des critères de recherche.
     * @param criteres les critères de recherche.
     * @return la liste des contrats correspondant aux critères de recherche.
     */
    List<Contrat> getContratsByCriteres(CritereRechercheContratDto criteres);

    /**
     * compte les contrats correspondants à des critères de recherche.
     * @param criteres les critères de recherche.
     * @return le nombre
     */
    int countContratsByCriteres(CritereRechercheContratDto criteres);

    /**
     * Récupère le dernier contrat d'une personne de la nature spécifiée.
     * @param uidPersonne l'identifiant de la personne.
     * @param uidNatureContrat l'identifiant de la nature.
     * @param uidStatutContrat l'identifiant du statut (optionnel)
     * @return le dernier contrat santé de la personne.
     */
    Contrat getDernierContratPersonneByNatureContrat(Long uidPersonne, Long uidNatureContrat, Long uidStatutContrat);

    /**
     * Récupère la synthèse d'un contrat.
     * @param uidPersonne l'identifiant de la personne du contrat.
     * @return la synthèse du contrat.
     */
    SyntheseContrat getSyntheseContrat(Long uidPersonne);

    /**
     * Récupère le ratio des cotisations d'une personne sur une année.
     * @param uidPersonne l'identifiant de la personne.
     * @param annee l'année de calcul.
     * @return la liste de ratio.
     */
    List<RatioPrestationCotisationDto> getRatioPrestationCotisationPersonne(Long uidPersonne);

    /**
     * Récupère les ajustements d'un contrat.
     * @param uidContrat l'identifiant du contrat.
     * @return la liste des récapitulatifs du contrat.
     */
    List<AjustementTarif> getListeAjustementsFromContrat(Long uidContrat);

    /**
     * Récupère le dernier contrat radié d'une personne.
     * @param uidPersonne l'identifiant de la personne.
     * @return le dernier contrat radié.
     */
    Contrat getDernierContratRadiePersonne(Long uidPersonne);

    /**
     * Récupère le gestionnaire d'un contrat collectif.
     * @param uidPersonne l'uid de la personne.
     * @return le nom du gestionnaire
     */
    String getGestionnaireContratCollectif(Long uidPersonne);

    /**
     * Récupération de la liste des populations de tous les contrats santé en cours d'une personne morale.
     * @param uidPersonneMorale l'identifiant de la personne morale.
     * @return la liste des populations.
     */
    List<PopulationDto> getListePopulations(Long uidPersonneMorale);

    /**
     * Récupère la liste des contrats d'une personne morale.
     * @param uidPersonneMorale l'identifiant de la personne morale.
     * @return la liste des contrats.
     */
    List<Contrat> getListeContratsPersonneMorale(Long uidPersonneMorale);

    /**
     * Récupère la listes des produits de gestion rattachés à un contrat.
     * @param eidContrat l'identifiant extérieur du contrat.
     * @return la liste des produits de gestion(libellés).
     */
    List<String> getListeProduitsGestionFromContrat(String eidContrat);

    /**
     * Récupère le nombre d'adhérents appartenant à un contrat.
     * @param eidContrat l'identifiant extérieur du contrat.
     * @return le nombre d'adhérents appartenant au contrat.
     */
    Integer getNombreAdherentsContrat(String eidContrat);

    /**
     * Récupère le nombre de bénéficiaires appartenant à un contrat.
     * @param eidContrat l'identifiant extérieur du contrat.
     * @return le nombre de bénéficiaires appartenant au contrat.
     */
    Integer getNombreBeneficiairesContrat(String eidContrat);

    /**
     * Récupère le récapitulatif des populations d'un contrat d'une personne morale.
     * @param eidContrat l'identifiant extérieur du contrat.
     * @return le récapitulatif.
     */
    RecapitulatifPopulationDto getRecapitulatifPopulationContrat(String eidContrat);

    /**
     * Récupère le dernier contrat radié d'une personne morale.
     * @param uidPersonneMorale l'identifiant de la personne morale.
     * @return le dernier contrat radié de la personne morale.
     */
    Contrat getDernierContratRadiePersonneMorale(Long uidPersonneMorale);

    /**
     * Récupère le dernier contrat d'une personne morale de la nature spécifiée.
     * @param uidPersonneMorale l'identifiant de la personne morale.
     * @param uidNatureContrat l'identifiant de la nature.
     * @param uidStatutContrat l'identifiant du statut.
     * @return le dernier contrat de la nature spécifiée de la personne morale.
     */
    Contrat getDernierContratPersonneMoraleByCriteres(Long uidPersonneMorale, Long uidNatureContrat, Long uidStatutContrat);

    /**
     * Détermine si une personne a des contrats.
     * @param idPersonne l'identifiant de la personne.
     * @return true si la personne a des contrats, false si non.
     */
    Boolean hasPersonneContrats(Long idPersonne);

    /**
     * Récupère la liste des contrats d'une entreprise en fonction de critères.
     * @param criteres les critères de recherche
     * @return la liste des contrats
     */
    List<String> getListeContratsCollectifsByCriteres(ContratCollectifCriteresDto criteres);

    /**
     * Récupère la liste des populations des contrats d'une entreprise.
     * @param criteres les critères de recherche
     * @return la liste des populations
     */
    List<String> getListePopulationsByCriteres(PopulationCriteresDto criteres);

    /**
     * Récupère la liste des populations des contrats d'une entreprise.
     * @param criteres les critères de recherche
     * @param idRoleGarantieAssure idRoleGarantieAssure
     * @param idStatutGarantieEnCours idStatutGarantieEnCours
     * @param idStatutGarantieFutur idStatutGarantieFutur
     * @return la liste des populations
     */
    List<ProduitCollectifAdherentDto> getListeProduitsCollectifsAdherent(ProduitCollectifAdherentCriteresDto criteres, Long idRoleGarantieAssure,
        Long idStatutGarantieEnCours, Long idStatutGarantieFutur);

    /**
     * Récupère la liste des types payeurs en fonction de critères.
     * @param criteres les critères de recherche
     * @param idRoleGarantieAssure idRoleGarantieAssure
     * @return la liste des types payeurs
     */
    List<TypePayeurDto> getListeTypesPayeurs(TypePayeurCriteresDto criteres, Long idRoleGarantieAssure);
}

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
package com.square.adherent.noyau.service.interfaces;

import java.util.List;

import com.square.adherent.noyau.dto.adherent.contrat.ContratCollectifCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratPersonneMoraleDto;
import com.square.adherent.noyau.dto.adherent.contrat.ContratSimpleDto;
import com.square.adherent.noyau.dto.adherent.contrat.CoordonneesBancairesDto;
import com.square.adherent.noyau.dto.adherent.contrat.CritereRechercheContratDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosContratsBeneficiaireDto;
import com.square.adherent.noyau.dto.adherent.contrat.InfosContratsPersonneMoraleDto;
import com.square.adherent.noyau.dto.adherent.contrat.ListeContratsDto;
import com.square.adherent.noyau.dto.adherent.contrat.PopulationCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.ProduitCollectifAdherentCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.ProduitCollectifAdherentDto;
import com.square.adherent.noyau.dto.adherent.contrat.TypePayeurCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.TypePayeurDto;
import com.square.adherent.noyau.dto.produit.CriteresInfosProduitsDto;
import com.square.adherent.noyau.dto.produit.InfosProduitDto;

/**
 * Interfaces des services liés aux contrats.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface ContratService {

    /**
     * Récupère le contrat santé d'une personne.
     * @param idPersonne l'identifiant de la personne.
     * @return le contrat trouvé.
     */
    ContratSimpleDto getContratSantePersonne(Long idPersonne);

    /**
     * Récupères les informations d'un produit à partir de critères.
     * @param criteresInfosProduitsDto les critères de recherche.
     * @return les informations sur les produits.
     */
    List<InfosProduitDto> getInfosProduits(CriteresInfosProduitsDto criteresInfosProduitsDto);

    /**
     * Récupère la liste des contrats d'un adhérent.
     * @param uidPersonne l'identifiant unique de l'adhérent.
     * @return les contrats de l'adhérent.
     */
    ListeContratsDto getListeContrats(Long uidPersonne);

    /**
     * Récupère les informations d'un contrat.
     * @param uidContrat l'identifiant unique du contrat à récupérer.
     * @return le contrat.
     */
    ContratDto getContrat(Long uidContrat);

    /**
     * Détermine s'il existe des contrats entre un assuré et un bénéficiaire.
     * @param idAssure l'identifiant de l'assuré.
     * @param idBeneficiaire l'identifiant du bénéficiaire.
     * @return informations calculées sur les contrats entre l'assuré et le bénéficiaire.
     */
    InfosContratsBeneficiaireDto hasContratAssureBeneficiaire(Long idAssure, Long idBeneficiaire);

    /**
     * Récupère le gestionnaire d'un contrat collectif.
     * @param uidPersonne l'uid de la personne
     * @return le gestionnaire
     */
    String getGestionnaireContratCollectif(Long uidPersonne);

    /**
     * Récupère les informations des contrats d'une personne morale.
     * @param uidPersonneMorale l'identifiant de la personne morale.
     * @return les informations des contrats de la personne morale.
     */
    InfosContratsPersonneMoraleDto getInfosContratPersonneMorale(Long uidPersonneMorale);

    /**
     * Récupère les informations d'un contrat d'une personne morale.
     * @param uidContrat l'identifiant du contrat.
     * @return les informations du contrat.
     */
    ContratPersonneMoraleDto getContratPersonneMorale(Long uidContrat);

    /**
     * Détermine si une personne a des contrats.
     * @param idPersonne l'identifiant de la personne.
     * @return true si la personne a des contrats, false si non.
     */
    Boolean hasPersonneContrats(Long idPersonne);

    /**
     * Récupére la liste des contrats simples par criteres.
     * @param criteres les criteres
     * @return la liste des contrats.
     */
    List<ContratSimpleDto> getContratsSimpleByCriteres(CritereRechercheContratDto criteres);

    /**
     * Récupére la liste des coordonnées bancaires d'une personne.
     * @param idPersonne l'id de la personne
     * @return la liste des coordonnées bancaires
     */
    List<CoordonneesBancairesDto> getListeCoordonneesBancaires(Long idPersonne);

    /**
     * Détermine si une personne a les droits d'affichage de l'onglet cotisation.
     * @param idPersonne l'identifiant de la personne.
     * @return true si la personne a les droits, false si non.
     */
    Boolean hasDroitAffichageCotisation(Long idPersonne);

    /**
     * Compte le nombre de contrats d'une personne.
     * @param idPersonne l'id
     * @param personneMorale est une PM
     * @return le nombre de contrats de la personne
     */
    int countContrats(Long idPersonne, boolean personneMorale);

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
     * Récupère la liste des produits collectifs adherents en fonction de critères.
     * @param criteres les critères de recherche
     * @return la liste des produits collectifs
     */
    List<ProduitCollectifAdherentDto> getListeProduitsCollectifsAdherent(ProduitCollectifAdherentCriteresDto criteres);

    /**
     * Récupère la liste des types payeurs en fonction de critères.
     * @param criteres les critères de recherche
     * @return la liste des types payeurs
     */
    List<TypePayeurDto> getListeTypesPayeurs(TypePayeurCriteresDto criteres);
}

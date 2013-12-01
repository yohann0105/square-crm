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

import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;

import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.BeneficiaireDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.InfosPersonneSyncDto;
import com.square.core.model.dto.PersonneCreationAssureSocialDto;
import com.square.core.model.dto.PersonneCreationVivierDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonnePhysiqueCopieDto;
import com.square.core.model.dto.PersonnePhysiqueIdCriteresRechercheDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.TelephoneDto;

/**
 * Service de gestion des personnes physique.
 * @author cblanchard - SCUB
 */
public interface PersonnePhysiqueService {

    /**
     * Création ou de mise à jour d'une personne.
     * @param personneDto la personne à créer ou à mettre à jour.
     * @param listeBeneficiaire la liste des beneficiaires.
     * @param adresse l'adresse de la personne.
     * @param email l'email de la personne.
     * @param telephone le téléphone de la personne.
     * @return PersonneDto la personne créée.
     * @author mlamine - SCUB
     */
    PersonneDto creerPersonnePhysique(PersonneDto personneDto, List<BeneficiaireDto> listeBeneficiaire, AdresseDto adresse, EmailDto email,
        TelephoneDto telephone);

    /**
     * Création ou de mise à jour d'une personne.
     * @param personneDto la personne à créer ou à mettre à jour.
     * @param listeBeneficiaire la liste des beneficiaires.
     * @param coordonnees coordonnées de la personne.
     * @return PersonneDto la personne
     */
    PersonneDto creerPersonnePhysique(PersonneDto personneDto, List<BeneficiaireDto> listeBeneficiaire, CoordonneesDto coordonnees);

    /**
     * Création ou de mise à jour d'une personne.
     * @param vivierDto le vivier à créer
     * @return PersonneDto la personne
     */
    PersonneDto creerPersonnePhysiqueVivier(PersonneCreationVivierDto vivierDto);

    /**
     * Création d'une personne assure social.
     * @param assureSocialDto l'assure social à créer
     * @return PersonneDto la personne
     */
    PersonneDto creerPersonnePhysiqueAssureSocial(PersonneCreationAssureSocialDto assureSocialDto);

    /**
     * Modification d'une personne.
     * @param personne la personne à modifier.
     * @return la personne modifier.
     * @author mlamine - SCUB
     */
    PersonneDto modifierPersonnePhysique(PersonneDto personne);

    /**
     * Recherche une personne par son identifiant et renvoi toutes les informations de cette personne.
     * @param id l'identifiant de la personne à retrouver
     * @return la personne contenant tous les informations de la personne
     */
    PersonneDto rechercherPersonneParIdentifiant(Long id);

    /**
     * Recherche une personne par son identifiant extérieur et renvoi toutes les informations de cette personne.
     * @param id l'identifiant de la personne à retrouver
     * @return la personne contenant tous les informations de la personne
     */
    PersonneDto rechercherPersonneParIdentifiantExterieur(String id);

    /**
     * Recherche d'une personneSimple par son identifiant.
     * @param id l'identifiant de la personne à retrouver.
     * @return la personne simple dto contenant les informations de la personne.
     */
    PersonneSimpleDto rechercherPersonneSimpleParIdentifiant(Long id);

    /**
     * Recherche full text de message.
     * @param criteres le texte à rechercher.
     * @return la liste des personnes.
     */
    RemotePagingResultsDto<PersonneSimpleDto> rechercherPersonneFullTextParCriteres(RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres);

    /**
     * Recherche une liste de personne par une requête.
     * @param requete la requete.
     * @return la liste des Personnes Physiques.
     */
    List<PersonneDto> rechercherPersonneParRequete(String requete);

    /**
     * Retourne un fichier contenant les resultats de la recherche.
     * @param criteres le texte à rechercher.
     * @return le contenu du fichier
     */
    FichierDto exporterRecherchePersonneFullTextParCriteres(RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres);

    /**
     * Creer une Copie d'une personne physique à partir d'une personne source (PP ou PM).
     * <br/>Récupère l'adresse principale et le mail de la personne source.
     * <br/>Si la personne source est une PP, récupération du téléphone fixe.
     * <br/>Si la personne source est une PM, le téléphone est celui passé en paramètre.
     * @param infosCopie la personne source
     * @return la personne apres la copie.
     */
    PersonneDto creerUneCopiePersonne(PersonnePhysiqueCopieDto infosCopie);

    /**
     * Met a jour les informations de paiement de la famille.
     * @param listeInfos les infos de paiement
     */
    void mettreAJourInfosAdhesion(List<InfosPersonneSyncDto> listeInfos);

    /**
     * Controler le format de telephone.
     * @param telephoneDto telephone saisi
     */
    void controlerTelephone(TelephoneDto telephoneDto);

    /**
     * Transforme un vivier en prospect après vérification des contraintes.
     * @param uidPersonne uid de la personne
     */
    void transformerVivierEnProspect(Long uidPersonne);

    /**
     * Crée un personne physique de nature vivier ou prospect en fonction des champs remplis.
     * @param personneDto les informations de la personne.
     * @param listeBeneficiaire la liste des bénéficiaires à rattacher à la personne.
     * @param adresse l'adresse de la personne.
     * @param email l'email de la personne.
     * @param telephone le téléphone de la personne.
     * @return la personne créée.
     */
    PersonneDto creerPersonnePhysiqueGestionVivier(PersonneDto personneDto, List<BeneficiaireDto> listeBeneficiaire, AdresseDto adresse,
        EmailDto email, TelephoneDto telephone);

    /**
     * Détermine si un moins un des membres de la famille d'une personne a pour nature une des natures recherchées.
     * @param idPersonne l'identifiant de la personne.
     * @param listeIdsNaturesPersonne la liste des identifiants des natures recherchées.
     * @return true si au moins un des membres de la famille possède une des natures recherchées, false si non.
     */
    boolean hasMembreFamilleNaturePersonne(Long idPersonne, List<Long> listeIdsNaturesPersonne);

    /**
     * Indexe une liste de personnes physiques.
     * @param listeIdsPersonnesAIndexer liste des identifiants des personnes physiques à indexer.
     */
    void indexerListePersonnesPhysiques(List<Long> listeIdsPersonnesAIndexer);

    /**
     * Renvoie une liste d'id de personne par critère.
     * @param criteres les critères de recherche.
     * @return la liste des ids de personnes physiques remplissant ces critères.
     */
    List<Long> rechercherIdPersonneParCriteres(PersonnePhysiqueIdCriteresRechercheDto criteres);
}

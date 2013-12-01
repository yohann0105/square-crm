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

import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;

import com.square.core.model.dto.AdresseCreationDto;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.AdresseSimpleDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.PersonneBaseDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonneRelationDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.dto.RelationDto;
import com.square.core.model.dto.RelationInfosDto;
import com.square.core.model.dto.TelephoneDto;

/**
 * Service de gestion des relations.
 * @author cblanchard - SCUB
 */
public interface PersonneService {

    /**
     * Recherche les relations par critères.
     * @param criteres les critères de recherche
     * @return la liste des relations correspondant
     */
    RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> rechercherRelationsParCritreres(
        RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteres);

    /**
     * Compte le nb de relations par critères.
     * @param criteres les criteres de recherche .
     * @return la liste des relations correspondant .
     */
    int countRelationsParCriteres(RelationCriteresRechercheDto criteres);

    /**
     * Recherche les adressesSimple d'une personne.
     * @param idPersonne l'identifiant de la personne
     * @return la liste des adresse simple
     */
    List<AdresseSimpleDto> rechercherAdresseSimpleParIdPersonne(Long idPersonne);

    /**
     * Creer une relation.
     * @param relation la relation à créer
     * @return la relation créée
     */
    RelationDto creerRelation(RelationDto relation);

    /**
     * Recuperer une relation par son identifiant.
     * @param idRelation l'identifiant de la relation.
     * @return la relation csp à l'identifiant null si aucune occurence.
     */
    RelationDto rechercherRelationParId(Long idRelation);

    /**
     * Modifie des relations.
     * @param relation la liste des relations à modifier
     */
    void modifierRelation(RelationDto relation);

    /**
     * Permet de modifier une liste de relation.
     * @param relations la liste des relations à modifier.
     */
    void modifierRelations(List<RelationDto> relations);

    /**
     * Recherche les relations familiales entre une personne et son assuré dans les 2 sens.
     * @param criteres les criteres
     * @return la liste des relations.
     */
    List<RelationDto> rechercherRelationDoubleSens(RelationCriteresRechercheDto criteres);

    /**
     * Recherche les coordonnées d'une personne.
     * @param idPersonne l'identifiant de la personne
     * @return les coordonnées de la personne.
     */
    CoordonneesDto rechercherCoordonneesParIdPersonne(Long idPersonne);

    /**
     * Enregistre ou modifie coordonnées d'une personne.
     * @param coordonnees les nouvelles coordonnées
     * @param impacterFamille précise si les coordonnées mises à jour doivent aussi impacter les coordonnées des membres de la famille de la personne
     * @param forcerDesactivationEservices force la désactivation des eservices
     * @return les coordonnées enregistrée
     */
    CoordonneesDto creerOuMettreAJourCoordonnees(CoordonneesDto coordonnees, Boolean impacterFamille, Boolean forcerDesactivationEservices);

    /**
     * Recuperer une personne indiférement du type par identifiant.
     * @param id l'ientifiant.
     * @return la personne trouvé null si aucune occurence.
     */
    PersonneBaseDto rechercherPersonneParId(final Long id);

    /**
     * Supprime (logiquement) une personne.
     * @param idPersonne l'identifiant de la personne
     */
    void supprimerPersonne(Long idPersonne);

    /**
     * Transfère une personne à un commercial (modifie l'attribution).
     * @param idPersonne l'identifiant de la personne
     * @param idCommercial l'identifiant du commercial
     */
    void transfererPersonneACommercial(Long idPersonne, Long idCommercial);

    /**
     * Transfère une personne à une agence (modifie l'attribution).
     * @param idPersonne l'identifiant de la personne
     * @param idAgence l'identifiant de l'agence
     */
    void transfererPersonneAAgence(Long idPersonne, Long idAgence);

    /**
     * Transfert l'ensemble des relations de la personne source vers la personne cible, que la personne source soit source ou cible de la relation.
     * @param idPersonneSource l'identifiant de la personne source
     * @param idPersonneCible l'identifiant de la personne cible
     */
    void transfererRelations(Long idPersonneSource, Long idPersonneCible);

    /**
     * Modification des extId des coordonnées (seules les infos passées en paramètre sont mises à jour).
     * @param coordonnees les coordonnées a mettre a jour
     */
    void mettreAJourCoordonneesExtId(CoordonneesDto coordonnees);

    /**
     * Ajoute une nouvelle adresse à une personne.
     * @param idPersonne l'identifiant de la personne.
     * @param adresseDto la nouvelle adresse.
     * @param impacterFamille précise si l'adresse doit être ajoutée aussi aux membres de la famille de la personne
     * correspondant à l'identifiant passé en paramètre
     * @return une synthèse des modifications réalisées
     */
    AdresseCreationDto ajouterNouvelleAdresse(Long idPersonne, AdresseDto adresseDto, Boolean impacterFamille);

    /**
     * Liste les personnes ayant un email donné.
     * @param emailId l'identifiant de l'email
     * @return la liste des personnes ayant cet email
     */
    List<PersonneDto> rechercherPersonnesParEmail(Long emailId);

    /**
     * Liste les personnes ayant un téléphone donné.
     * @param telephoneId l'identifiant du téléphone
     * @return la liste des personnes ayant ce téléphone
     */
    List<PersonneDto> rechercherPersonnesParTelephone(Long telephoneId);

    /**
     * Liste les personnes ayant une adresse donnée.
     * @param adresseId l'identifiant de l'adresse
     * @return la liste des personnes ayant cette adresse
     */
    List<PersonneDto> rechercherPersonnesParAdresse(Long adresseId);

    /**
     * Enregistre ou modifie l'email d'une personne.
     * @param idPersonne l'identifiant de la personne.
     * @param email le nouvel email.
     * @param impacterFamille précise si l'email mis à jour doit aussi impacter l'email des membres de la famille de la personne.
     * @param forcerDesactivationEservices force la désactivation des eservices
     * @return l'email enregistré.
     */
    EmailDto creerOuMettreAJourEmail(Long idPersonne, EmailDto email, Boolean impacterFamille, Boolean forcerDesactivationEservices);

    /**
     * Enregistre ou modifie le téléphone d'une personne.
     * @param idPersonne l'identifiant de la personne.
     * @param telephone le nouveau téléphone.
     * @param impacterFamille précise si le téléphone mis à jour doivent aussi impacter le téléphone des membres de la famille de la personne.
     * @param forcerDesactivationEservices force la désactivation des eservices
     * @return le telephone enregistré.
     */
    TelephoneDto creerOuMettreAJourTelephone(Long idPersonne, TelephoneDto telephone, Boolean impacterFamille, Boolean forcerDesactivationEservices);

    /**
     * Désactive une liste de relations.
     * @param idsRelationsADesactiver idsRelationsADesactiver
     * @return le nombre de désactivées
     */
    int desactiverRelations(List<Long> idsRelationsADesactiver);

    /**
     * Recherche les ids relations par critères.
     * @param date la date de desactivation
     * @param pagination le nombre à retourner
     * @return la liste des ids relations correspondant
     */
    List<Long> rechercherIdsRelationsADesactiver(Calendar date, int pagination);
}

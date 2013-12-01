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
package com.square.tarificateur.noyau.service.interfaces;

import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

import com.square.tarificateur.noyau.dto.personne.DimensionCriteresLienFamilialRechercheDto;
import com.square.tarificateur.noyau.dto.personne.PersonneDto;
import com.square.tarificateur.noyau.dto.personne.PersonneTarificateurDto;

/**
 * Interfaces des services liés aux personnes du tarificateur Square.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface TarificateurPersonneService {

    /**
     * Transfère l'EID d'une personne source vers une personne cible.
     * @param eidPersonneSource l'EID (Square) de la personne source
     * @param eidPersonneCible l'EID (Square) de la personne cible
     */
    void transfererEidPersonne(Long eidPersonneSource, Long eidPersonneCible);

    /**
     * Récupère une personne par son identifiant.
     * @param idPersonne l'identifiant de la personne
     * @return la personne
     */
    PersonneDto getPersonneById(Long idPersonne);

    /**
     * Récupère une personne par son identifiant externe.
     * @param eidPersonne l'identifiant externe de la personne
     * @return la personne
     */
    PersonneDto getPersonneByEid(Long eidPersonne);

    /**
     * Crée ou modifie une personne.
     * @param personne la personne à sauvegarder
     * @return la personne sauvegardée
     */
    PersonneDto createOrUpdatePersonne(PersonneTarificateurDto personne);

    /**
     * Crée un assuré social.
     * @param assureSocial l'assure social à sauvegarder
     * @param idDevis id du devis porteur de l'assure social
     * @param fromOuvertureOpp indique si l'appel provient de l'ouverture d'une opportunité
     * @return l'assure social sauvegardé
     */
    PersonneDto createAssureSocial(PersonneDto assureSocial, Long idDevis, boolean fromOuvertureOpp);

    /**
     * Recupere la liste des assures sociaux d'un devis.
     * @param idDevis id du devis porteur des assures sociaux
     * @return la liste des assures sociaux
     */
    List<PersonneDto> getAssuresSociauxByIdDevis(Long idDevis);

    /**
     * Recherche la relation entre une personne et son assuré social.
     * @param idPersonne l'identifiant de la personne
     * @return la relation
     */
    IdentifiantLibelleDto rechercherRelationAssureSocial(Long idPersonne);

    /**
     * Recherche des liens familiaux par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherLiensFamiliauxParCriteres(DimensionCriteresLienFamilialRechercheDto criteres);
}

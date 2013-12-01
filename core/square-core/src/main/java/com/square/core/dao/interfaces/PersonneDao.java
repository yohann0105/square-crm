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
package com.square.core.dao.interfaces;

import java.util.List;

import com.square.core.model.Personne;
import com.square.core.model.dto.AdresseSimpleDto;

/**
 * Iterface recherche de personne.
 * @author Goumard Stephane (Scub) - SCUB
 */
public interface PersonneDao {
    /**
     * Recuperer une personne par son identifiant.
     * @param id l'identifiant de la personne à retourner.
     * @return la personne.
     */
    Personne rechercherPersonneParId(final Long id);

    /**
     * Réchercher les adresses simple d'une personne par l'identifiant de la personne.
     * @param idPersonne l'identifiant de la personne
     * @return la liste des adresse simple de la personne
     */
    List<AdresseSimpleDto> rechercherAdressesSimplesParIdPersonne(Long idPersonne);

    /**
     * Liste les personnes ayant un email donné.
     * @param emailId l'identifiant de l'email
     * @return la liste des personnes ayant cet email
     */
    List<Personne> rechercherPersonnesParEmail(Long emailId);

    /**
     * Liste les personnes ayant un téléphone donné.
     * @param telephoneId l'identifiant du téléphone
     * @return la liste des personnes ayant ce téléphone
     */
    List<Personne> rechercherPersonnesParTelephone(Long telephoneId);

    /**
     * Liste les personnes ayant une adresse donnée.
     * @param adresseId l'identifiant de l'adresse
     * @return la liste des personnes ayant cette adresse
     */
    List<Personne> rechercherPersonnesParAdresse(Long adresseId);
}

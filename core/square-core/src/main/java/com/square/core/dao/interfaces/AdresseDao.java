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

import com.square.core.model.Adresse;
import com.square.core.model.dto.AdresseCriteresRechercheDto;

/**
 * Interface recherche d'adresse.
 * @author Juanito Goncalves (juanito.goncalves@scub.net) - SCUB
 */
public interface AdresseDao {
    /**
     * Recuperer une adresse par son identifiant.
     * @param id l'identifiant de l'adresse à retourner.
     * @return l'adresse
     */
    Adresse rechercherAdresseParId(final Long id);

    /**
     * Recherche des adresses par critère.
     * @param criteres les criteres de recherche
     * @return la liste des adresses correspondant aux criteres
     */
    List<Long> rechercherIdAdressesParCriteres(final AdresseCriteresRechercheDto criteres);

    /**
     * Rechercher Adresse pour une personne donnée et par nature.
     * @param criteres l'identifiant de la personne
     * @return la liste d'adresse trouvée.
     */
    List<Adresse> rechercherAdresseParCritere(final AdresseCriteresRechercheDto criteres);

    /**
     * Ajout d'une adresse.
     * @param adresse adresse
     */
    void ajouterAdresse(Adresse adresse);

}

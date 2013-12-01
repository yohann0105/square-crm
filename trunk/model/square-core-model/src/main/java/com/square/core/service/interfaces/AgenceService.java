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

import com.square.core.model.dto.AgenceDto;
import com.square.core.model.dto.IdentifiantEidLibelleDto;

/**
 * Service de gestion des agences.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface AgenceService {

    /**
     * Recherche une agence par son identifiant externe.
     * @param eidAgence l'identifiant externe de l'agence.
     * @return l'agence trouvée.
     */
    AgenceDto rechercherAgenceParEid(String eidAgence);

    /**
     * Crée une nouvelle agence.
     * @param agence l'agence à créer.
     * @return l'agence créée.
     */
    AgenceDto creerAgence(AgenceDto agence);

    /**
     * Modifie une agence.
     * @param agence l'agence à modifier.
     * @return l'agence modifiée.
     */
    AgenceDto modifierAgence(AgenceDto agence);

    /**
     * Supprime (logiquement) une agence.
     * @param eidAgence l'identifiant externe de l'agence à supprimer.
     */
    void supprimerAgence(String eidAgence);

    /**
     * Recherche une région commerciale par son identifiant externe.
     * @param eidRegion l'identifiant externe de la région.
     * @return la région commerciale trouvée.
     */
    IdentifiantEidLibelleDto rechercherRegionCommercialeParEid(String eidRegion);

    /**
     * Recherche les agences correspondantes aux identifiants passés en paramètre.
     * @param listeIds la liste des identifiants des agences.
     * @return la liste des agences.
     */
    List<AgenceDto> rechercherAgencesParIds(List<Long> listeIds);
}

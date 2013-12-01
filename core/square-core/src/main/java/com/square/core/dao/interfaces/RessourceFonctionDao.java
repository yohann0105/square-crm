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

import com.square.core.model.Ressources.RessourceFonction;
import com.square.core.model.dto.DimensionCritereRechercheRessourceFonctionDto;

/**
 * Dao pour la fonction de la ressource.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface RessourceFonctionDao {
    /**
     * Recherche de la fonction par identifiant.
     * @param identifiant l'identifiant.
     * @return la fonction de la ressource.
     */
    RessourceFonction rechercherFonctionRessParId(Long identifiant);

    /**
     * Recherche de la fonction par Critères.
     * @param criteres critères de la recherche de la fonction.
     * @return la liste des fontions trouvée.
     */
    List<RessourceFonction> rechercherRessourceFonctionParCriteres(DimensionCritereRechercheRessourceFonctionDto criteres);

}

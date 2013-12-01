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

import com.square.core.model.ActionNatureResultat;
import com.square.core.model.dto.ActionNatureResultatCriteresRechercheDto;
import com.square.core.model.dto.DimensionCriteresRechercheDto;

/**
 * Dao pour la nature des résultats des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface ActionNatureResultatDao {

    /**
     * Recherche la nature des résultats des actions par critères.
     * @param criteres les criteres de recherche
     * @return la liste des natures des résultats des actions correspondant correspondant aux criteres
     */
    List<ActionNatureResultat> rechercherActionNatureResultatParCritere(ActionNatureResultatCriteresRechercheDto criteres);

    /**
     * Recherche le résultat d'une nature d'action par son identifiant.
     * @param idNatureResultatAction l'identifiant
     * @return le résultat de la nature d'action
     */
    ActionNatureResultat rechercherNatureResultatActionById(Long idNatureResultatAction);

}

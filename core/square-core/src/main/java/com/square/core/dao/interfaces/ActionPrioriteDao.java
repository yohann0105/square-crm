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

import com.square.core.model.ActionPriorite;
import com.square.core.model.dto.DimensionCriteresRechercheDto;

/**
 * Dao pour les priorités des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface ActionPrioriteDao {
    /**
     * Recherche la priorité des actions par critères.
     * @param criteres les criteres de recherche
     * @return la liste des priorités des actions correspondant correspondant aux criteres
     */
    List<ActionPriorite> rechercherPrioriteActionParCritere(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche la priorité par identifiant.
     * @param idPriorite l'identifiant de la priorité
     * @return la priorité correspondant
     */
    ActionPriorite rechercherPrioriteActionParId(Long idPriorite);
}

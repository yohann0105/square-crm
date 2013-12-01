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
package com.square.synchro.app.noyau.bean;

import java.util.HashMap;
import java.util.Map;

import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonneSimpleDto;

/**
 * Classe permettant de stocker les informations de personnes.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class InfosPersonneSquareBean {

    /** Map des personnes physiques (clé : idPersonne Square, valeur : DTO de la personne). */
    private Map<Long, PersonneDto> mapPersonnesPhysiques;

    /** Map des personnes simples (clé : idPersonne Square, valeur : DTO de la personne). */
    private Map<Long, PersonneSimpleDto> mapPersonnesSimples;

    /** Map des coordonnées des personnes (clé : idPersonne Square, valeur : DTO des coordonnées de la personne). */
    private Map<Long, CoordonneesDto> mapCoordonnees;

    /**
     * Récupère la valeur de mapPersonnesPhysiques.
     * @return la valeur de mapPersonnesPhysiques
     */
    public Map<Long, PersonneDto> getMapPersonnesPhysiques() {
        if (mapPersonnesPhysiques == null) {
            mapPersonnesPhysiques = new HashMap<Long, PersonneDto>();
        }
        return mapPersonnesPhysiques;
    }

    /**
     * Récupère la valeur de mapPersonnesSimples.
     * @return la valeur de mapPersonnesSimples
     */
    public Map<Long, PersonneSimpleDto> getMapPersonnesSimples() {
        if (mapPersonnesSimples == null) {
            mapPersonnesSimples = new HashMap<Long, PersonneSimpleDto>();
        }
        return mapPersonnesSimples;
    }

    /**
     * Récupère la valeur de mapCoordonnees.
     * @return la valeur de mapCoordonnees
     */
    public Map<Long, CoordonneesDto> getMapCoordonnees() {
        if (mapCoordonnees == null) {
            mapCoordonnees = new HashMap<Long, CoordonneesDto>();
        }
        return mapCoordonnees;
    }
}

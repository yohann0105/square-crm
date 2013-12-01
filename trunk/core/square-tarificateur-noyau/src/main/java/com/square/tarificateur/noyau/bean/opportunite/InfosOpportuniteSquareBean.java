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
package com.square.tarificateur.noyau.bean.opportunite;

import java.util.HashMap;
import java.util.Map;

import com.square.core.model.dto.OpportuniteSimpleDto;

/**
 * Classe permettant de stocker les informations d'une opportunité.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class InfosOpportuniteSquareBean {

    /** Map des opportunités Square (clé : idOpportunité Square, valeur : DTO de l'opportunité). */
    private Map<Long, OpportuniteSimpleDto> mapOpportunites;

    /**
     * Récupère la valeur de mapOpportunites.
     * @return la valeur de mapOpportunites
     */
    public Map<Long, OpportuniteSimpleDto> getMapOpportunites() {
        if (mapOpportunites == null) {
            mapOpportunites = new HashMap<Long, OpportuniteSimpleDto>();
        }
        return mapOpportunites;
    }

    /**
     * Définit la valeur de mapOpportunites.
     * @param mapOpportunites la nouvelle valeur de mapOpportunites
     */
    public void setMapOpportunites(Map<Long, OpportuniteSimpleDto> mapOpportunites) {
        this.mapOpportunites = mapOpportunites;
    }
}

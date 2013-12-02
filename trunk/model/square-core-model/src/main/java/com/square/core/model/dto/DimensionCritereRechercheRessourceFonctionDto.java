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
package com.square.core.model.dto;

import java.util.List;

/**
 * Critère de recherche pour la fonction d'une ressource.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class DimensionCritereRechercheRessourceFonctionDto extends DimensionCriteresRechercheDto {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 8219276428562082730L;

    /** Liste des services sur lesquels filtrés. */
    private List<Long> listeIdsServices;

    /**
     * Récupère la valeur de listeIdsServices.
     * @return la valeur de listeIdsServices
     */
    public List<Long> getListeIdsServices() {
        return listeIdsServices;
    }

    /**
     * Définit la valeur de listeIdsServices.
     * @param listeIdsServices la nouvelle valeur de listeIdsServices
     */
    public void setListeIdsServices(List<Long> listeIdsServices) {
        this.listeIdsServices = listeIdsServices;
    }

}

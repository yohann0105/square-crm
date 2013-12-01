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
package com.square.client.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model pour les critères de recherche du résultat des actions.
 * @author cblanchard - SCUB
 */
public class DimensionCritereRechercheResultatActionModel implements IsSerializable {

    /** Criteres de recherche sur les dimensions. */
    private DimensionCriteresRechercheModel dimensionCriteres;

    /** Critere pour filtrer la recherche sur les résultat opportunité. */
    private Boolean recuperationOpportunite;

    /** Constructeur par defaut. */
    public DimensionCritereRechercheResultatActionModel() {
    }

    /**
     * Constructeur.
     * @param dimensionCriteres les critères générique de recherche
     */
    public DimensionCritereRechercheResultatActionModel(DimensionCriteresRechercheModel dimensionCriteres) {
        this.dimensionCriteres = dimensionCriteres;
    }

    /**
     * Constructeur.
     * @param dimensionCriteres les critères générique de recherche
     * @param recuperationOpportunite boolean permettant de spécifier si on veut remonter l'opportunité
     */
    public DimensionCritereRechercheResultatActionModel(DimensionCriteresRechercheModel dimensionCriteres, Boolean recuperationOpportunite) {
        this.dimensionCriteres = dimensionCriteres;
        this.recuperationOpportunite = recuperationOpportunite;
    }

    /**
     * Renvoi la valeur de dimensionCriteres.
     * @return dimensionCriteres
     */
    public DimensionCriteresRechercheModel getDimensionCriteres() {
        return dimensionCriteres;
    }

    /**
     * Modifie dimensionCriteres.
     * @param dimensionCriteres la nouvelle valeur de dimensionCriteres
     */
    public void setDimensionCriteres(DimensionCriteresRechercheModel dimensionCriteres) {
        this.dimensionCriteres = dimensionCriteres;
    }

    /**
     * Renvoi la valeur de recuperationOpportunite.
     * @return recuperationOpportunite
     */
    public Boolean getRecuperationOpportunite() {
        return recuperationOpportunite;
    }

    /**
     * Modifie recuperationOpportunite.
     * @param recuperationOpportunite la nouvelle valeur de recuperationOpportunite
     */
    public void setRecuperationOpportunite(Boolean recuperationOpportunite) {
        this.recuperationOpportunite = recuperationOpportunite;
    }

}

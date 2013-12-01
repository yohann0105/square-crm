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
/**
 * 
 */
package com.square.composant.tarificateur.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Classe Model.
 * @author Goumard Stephane (Scub) - SCUB
 */
public class DimensionCriteresRechercheRegimeModel implements IsSerializable {

    /**
     * Criteres de recherche sur les dimensions.
     */
    private DimensionCriteresRechercheModel dimensionCriteres;

    /**
     * Indique si on recherche les dimensions visibles pour certains applicatifs.
     * true on recherche les éléments visible
     * false les éléments non visibles
     * null pas de recherche sur la visibilité.
     */
    private Boolean visibleApplicatif;

    /**
     * Constructeur.
     */
    public DimensionCriteresRechercheRegimeModel() {
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
     * Get the visibleApplicatif value.
     * @return the visibleApplicatif
     */
    public Boolean getVisibleApplicatif() {
        return visibleApplicatif;
    }

    /**
     * Set the visibleApplicatif value.
     * @param visibleApplicatif the visibleApplicatif to set
     */
    public void setVisibleApplicatif(Boolean visibleApplicatif) {
        this.visibleApplicatif = visibleApplicatif;
    }
}

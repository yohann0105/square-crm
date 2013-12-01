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
public class DimensionCriteresRechercheCaisseModel implements IsSerializable {

    /**
     * Criteres de recherche sur les dimensions.
     */
    private DimensionCriteresRechercheModel dimensionCriteres;

    /**
     * Critère sur le régime de la caisse.
     */
    private Long idRegime;

    /**
     * Recherche sur le departement.
     */
    private Long idDepartement;

    /**
     * Constructeur.
     */
    public DimensionCriteresRechercheCaisseModel() {
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
     * Renvoi la valeur de idRegime.
     * @return idRegime
     */
    public Long getIdRegime() {
        return idRegime;
    }

    /**
     * Modifie idRegime.
     * @param idRegime la nouvelle valeur de idRegime
     */
    public void setIdRegime(Long idRegime) {
        this.idRegime = idRegime;
    }

    /**
     * Fixer le filtre sur le departement.
     * @return the idDepartement .
     */
    public Long getIdDepartement() {
        return idDepartement;
    }

    /**
     * Recuperer le filtre sur le departement.
     * @param idDepartement the idDepartement to set .
     */
    public void setIdDepartement(Long idDepartement) {
        this.idDepartement = idDepartement;
    }
}

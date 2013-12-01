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
package com.square.composant.tarificateur.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Critères de recherche pour les tables de dimension.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class DimensionCriteresRechercheModel implements IsSerializable {

    /** Identifiant. */
    private Long id;

    /** Libelle. */
    private String libelle;

    /**
     * Indique si on recherche les dimensions visibles. True on recherche les éléments visible, false les éléments non visibles null pas de recherche sur la
     * visibilité.
     */
    private Boolean visible;

    /** Nombre de résultats max. */
    private Integer maxResults;

    /**
     * Constructeur de critère avec un identifiant.
     * @param id l'identifiant à rechercher.
     */
    public DimensionCriteresRechercheModel(Long id) {
        this.id = id;
    }

    /**
     * Constructeur par défaut.
     */
    public DimensionCriteresRechercheModel() {
        this.id = null;
        this.libelle = null;
        this.visible = true;
    }

    /**
     * Renvoi la valeur de id.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Renvoi la valeur de libelle.
     * @return libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Modifie libelle.
     * @param libelle la nouvelle valeur de libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Modifie visible.
     * @param visible la nouvelle valeur de visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * Get the maxResults value.
     * @return the maxResults
     */
    public Integer getMaxResults() {
        return maxResults;
    }

    /**
     * Set the maxResults value.
     * @param maxResults the maxResults to set
     */
    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    /**
     * Get the visible value.
     * @return the visible
     */
    public Boolean getVisible() {
        return visible;
    }

}

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
package com.square.adherent.noyau.dto;

import java.io.Serializable;

/**
 * Critères de recherche pour les tables de dimension.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class DimensionAdherentCriteresRechercheDto implements Serializable {

    /** Serial Version uid. */
    private static final long serialVersionUID = -8541815185295638241L;

    /** Identifiant. */
    private Long id;

    /** Libelle. */
    private String libelle;

    /** Eid. */
    private String identifiantExterieur;

    /** Indique si on recherche les dimensions visibles. */
    private Boolean visible = true;

    /** Nombre de résultats max. */
    private Integer maxResults;

    /**
     * Constructeur par défaut.
     */
    public DimensionAdherentCriteresRechercheDto() {
    }

    /**
     * Constructeur de critère avec un identifiant.
     * @param id l'identifiant à rechercher.
     */
    public DimensionAdherentCriteresRechercheDto(Long id) {
        this.id = id;
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

    /**
     * Récupération de identifiantExterieur.
     * @return the identifiantExterieur
     */
    public String getIdentifiantExterieur() {
        return identifiantExterieur;
    }

    /**
     * Définition de identifiantExterieur.
     * @param identifiantExterieur the identifiantExterieur to set
     */
    public void setIdentifiantExterieur(String identifiantExterieur) {
        this.identifiantExterieur = identifiantExterieur;
    }

}

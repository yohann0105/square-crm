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
package com.square.composant.emails.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO GWT représentant un modèle de mail et la catégorie à laquelle il appartient.
 * @author Nicolas Prouteau - SCUB
 *
 */
public class CategorieEtModeleEmailGwt implements IsSerializable {
    private Long id;

    private Long idCategorie;

    private String libelleCategorie;

    private Long idService;

    private String nom;

    private String texte;

    private Boolean active;

    /**
     * Get the id value.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the idCategorie value.
     * @return the idCategorie
     */
    public Long getIdCategorie() {
        return idCategorie;
    }

    /**
     * Set the idCategorie value.
     * @param idCategorie the idCategorie to set
     */
    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    /**
     * Get the libelleCategorie value.
     * @return the libelleCategorie
     */
    public String getLibelleCategorie() {
        return libelleCategorie;
    }

    /**
     * Set the libelleCategorie value.
     * @param libelleCategorie the libelleCategorie to set
     */
    public void setLibelleCategorie(String libelleCategorie) {
        this.libelleCategorie = libelleCategorie;
    }

    /**
     * Get the idService value.
     * @return the idService
     */
    public Long getIdService() {
        return idService;
    }

    /**
     * Set the idService value.
     * @param idService the idService to set
     */
    public void setIdService(Long idService) {
        this.idService = idService;
    }

    /**
     * Set the id value.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the nom value.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the nom value.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get the texte value.
     * @return the texte
     */
    public String getTexte() {
        return texte;
    }

    /**
     * Set the texte value.
     * @param texte the texte to set
     */
    public void setTexte(String texte) {
        this.texte = texte;
    }

    /**
     * Get the active value.
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Set the active value.
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }
}

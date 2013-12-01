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
 * DTO encapsulant les informations simplifiées d'une caisse.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class CaisseSimpleModel implements IsSerializable {

    /** Identifiant de la caisse. */
    private Long id;

    /** Nom de la caisse. */
    private String nom;

    /** Code de la caisse. */
    private String code;

    /** Centre de la caisse. */
    private String centre;

    /**
     * Constructeur par défaut.
     */
    public CaisseSimpleModel() {
        super();
    }

    /**
     * Constructeur simplifié.
     * @param id identifiant de la caisse
     */
    public CaisseSimpleModel(Long id) {
        super();
        this.id = id;
    }

    /**
     * Constructeur complet.
     * @param id identifiant de la caisse
     * @param nom nom de la caisse
     * @param code code de la caisse
     */
    public CaisseSimpleModel(Long id, String nom, String code) {
        super();
        this.id = id;
        this.nom = nom;
        this.code = code;
    }

    /**
     * Getter function.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter function.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter function.
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter function.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Setter function.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter function.
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get the centre value.
     * @return the centre
     */
    public String getCentre() {
        return centre;
    }

    /**
     * Set the centre value.
     * @param centre the centre to set
     */
    public void setCentre(String centre) {
        this.centre = centre;
    }

}

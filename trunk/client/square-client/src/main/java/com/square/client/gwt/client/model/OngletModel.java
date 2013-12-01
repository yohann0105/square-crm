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

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Un objet représentant un onglet.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class OngletModel implements IsSerializable {

    /** Identifiant de l'onglet. */
    private String id;

    /** Libelle de l'onglet. */
    private String libelle;

    /** L'image de l'onglet. */
    private ImageResource image;

    /**
     * Constructeur.
     */
    public OngletModel() {
        super();
    }

    /**
     * Constructeur.
     * @param id l'id
     */
    public OngletModel(String id) {
        super();
        this.id = id;
    }

    /**
     * Constructeur.
     * @param id l'id
     * @param image l'image
     */
    public OngletModel(String id, ImageResource image) {
        super();
        this.id = id;
        this.image = image;
    }

    /**
     * Constructeur.
     * @param id l'identifiant
     * @param libelle le libelle
     * @param image l'image
     */
    public OngletModel(String id, String libelle, ImageResource image) {
        super();
        this.id = id;
        this.libelle = libelle;
        this.image = image;
    }

    /**
     * Constructeur.
     * @param id l'identifiant
     * @param libelle le libelle
     */
    public OngletModel(String id, String libelle) {
        super();
        this.id = id;
        this.libelle = libelle;
    }

    /**
     * Get the libelle value.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Set the libelle value.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Get the image value.
     * @return the image
     */
    public ImageResource getImage() {
        return image;
    }

    /**
     * Set the image value.
     * @param image the image to set
     */
    public void setImage(ImageResource image) {
        this.image = image;
    }

    /**
     * Get the id value.
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id value.
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}

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
package com.square.price.core.dto;

import java.io.Serializable;

/**
 * Critères de recherche d'une image de produit.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class CriteresImageProduitDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 3612640735449754850L;

    /** Type d'image "rond". */
    public static final String TYPE_IMAGE_ROND = "rond";

    /** Type d'image "allongé". */
    public static final String TYPE_IMAGE_ALLONGE = "allonge";

    /** Identifiant du produit. */
    private Integer idProduit;

    /** Type de l'image. */
    private String typeImage = TYPE_IMAGE_ROND;

    /**
     * Constructeur avec paramètres.
     * @param idProduit l'identifiant du produit
     * @param typeImage le type de l'image
     */
    public CriteresImageProduitDto(Integer idProduit, String typeImage) {
        this.idProduit = idProduit;
        this.typeImage = typeImage;
    }

    /**
     * Récupère la valeur de idProduit.
     * @return la valeur de idProduit
     */
    public Integer getIdProduit() {
        return idProduit;
    }

    /**
     * Définit la valeur de idProduit.
     * @param idProduit la nouvelle valeur de idProduit
     */
    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    /**
     * Récupère la valeur de typeImage.
     * @return la valeur de typeImage
     */
    public String getTypeImage() {
        return typeImage;
    }

    /**
     * Définit la valeur de typeImage.
     * @param typeImage la nouvelle valeur de typeImage
     */
    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }

}

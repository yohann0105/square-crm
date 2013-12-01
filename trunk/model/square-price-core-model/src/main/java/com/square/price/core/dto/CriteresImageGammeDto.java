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
 * Critères de recherche d'une image de gamme.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class CriteresImageGammeDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -1402747434074825353L;

    /** Type d'image "rond". */
    public static final String TYPE_IMAGE_ROND = "rond";

    /** Type d'image "allongé". */
    public static final String TYPE_IMAGE_ALLONGE = "allonge";

    /** Identifiant de la gamme. */
    private Integer idGamme;

    /** Type de l'image. */
    private String typeImage = TYPE_IMAGE_ROND;

    /**
     * Constructeur avec paramètres.
     * @param idGamme l'identifiant de la gamme
     * @param typeImage le type de l'image
     */
    public CriteresImageGammeDto(Integer idGamme, String typeImage) {
        this.idGamme = idGamme;
        this.typeImage = typeImage;
    }

    /**
     * Récupère la valeur de idGamme.
     * @return la valeur de idGamme
     */
    public Integer getIdGamme() {
        return idGamme;
    }

    /**
     * Définit la valeur de idGamme.
     * @param idGamme la nouvelle valeur de idGamme
     */
    public void setIdGamme(Integer idGamme) {
        this.idGamme = idGamme;
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

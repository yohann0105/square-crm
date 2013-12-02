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
package com.square.core.model.dto;

import java.io.Serializable;

/**
 * DTO représentant une pièce jointe d'un email.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PieceJointeFileDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -4548400368560407524L;

    /** Nom du fichier. */
    private String nom;

    /**  Type mime du fichier. */
    private String typeMime;

    /** Données. */
    private byte[] data;

    /**
     * Récupère la valeur de nom.
     * @return la valeur de nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit la valeur de nom.
     * @param nom la nouvelle valeur de nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la valeur de typeMime.
     * @return la valeur de typeMime
     */
    public String getTypeMime() {
        return typeMime;
    }

    /**
     * Définit la valeur de typeMime.
     * @param typeMime la nouvelle valeur de typeMime
     */
    public void setTypeMime(String typeMime) {
        this.typeMime = typeMime;
    }

    /**
     * Récupère la valeur de data.
     * @return la valeur de data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Définit la valeur de data.
     * @param data la nouvelle valeur de data
     */
    public void setData(byte[] data) {
        this.data = data;
    }
}

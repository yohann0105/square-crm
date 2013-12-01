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
package com.square.adherent.noyau.dto.fichier;

import java.io.Serializable;

/**
 * DTO d'un fichier.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class FichierDto implements Serializable {

    /** Identificateur de séialisation. */
    private static final long serialVersionUID = 2149297418595963002L;

    /** Nom du fichier. */
    private String nomFichier;

    /** Contenu du fichier. */
    private byte[] contenu;

    /** Type Mime du fichier. */
    private String typeMime;

    /**
     * Retourne la valeur de nomFichier.
     * @return la valeur de nomFichier
     */
    public String getNomFichier() {
        return nomFichier;
    }

    /**
     * Définit la valeur de nomFichier.
     * @param nomFichier la nouvelle valeur de nomFichier
     */
    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    /**
     * Retourne la valeur de contenu.
     * @return la valeur de contenu
     */
    public byte[] getContenu() {
        return contenu;
    }

    /**
     * Définit la valeur de contenu.
     * @param contenu la nouvelle valeur de contenu
     */
    public void setContenu(byte[] contenu) {
        this.contenu = contenu;
    }

    /**
     * Get the typeMime value.
     * @return the typeMime
     */
    public String getTypeMime() {
        return typeMime;
    }

    /**
     * Set the typeMime value.
     * @param typeMime the typeMime to set
     */
    public void setTypeMime(String typeMime) {
        this.typeMime = typeMime;
    }

}

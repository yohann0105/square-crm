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
package com.square.composant.ged.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * .
 * @author jgoncalves - SCUB
 */
public class CodeLibelleModel implements IsSerializable {
    /** Code. */
    private String code;

    /** Libellé. */
    private String libelle;

    /** Constructeur par défaut. */
    public CodeLibelleModel() {
    }

    /**
     * .
     * @param code .
     */
    public CodeLibelleModel(String code) {
        this.code = code;
    }

    /**
     * .
     * @param code .
     * @param libelle .
     */
    public CodeLibelleModel(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    /**
     * Récupération de code.
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Définition de code.
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Récupération de libelle.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Définition de libelle.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

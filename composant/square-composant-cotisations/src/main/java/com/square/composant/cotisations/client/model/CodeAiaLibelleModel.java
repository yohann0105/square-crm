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
package com.square.composant.cotisations.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model qui représente une situation de cotisation.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class CodeAiaLibelleModel implements IsSerializable {

    /** Code AIA. */
    private String codeAia;

    /** Libellé. */
    private String libelle;

    /**
     * Constructeur.
     */
    public CodeAiaLibelleModel() {
        super();
    }

    /**
     * Constructeur.
     * @param codeAia codeAia
     * @param libelle libelle
     */
    public CodeAiaLibelleModel(String codeAia, String libelle) {
        super();
        this.codeAia = codeAia;
        this.libelle = libelle;
    }

    /**
     * Get the codeAia value.
     * @return the codeAia
     */
    public String getCodeAia() {
        return codeAia;
    }

    /**
     * Set the codeAia value.
     * @param codeAia the codeAia to set
     */
    public void setCodeAia(String codeAia) {
        this.codeAia = codeAia;
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

}

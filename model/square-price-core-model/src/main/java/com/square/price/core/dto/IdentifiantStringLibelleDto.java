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
 * Utilitaire pour former les listes lors de l'affichage.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class IdentifiantStringLibelleDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6436623547259324804L;

    /** Clé. */
    private String identifiant;

    /** Valeur. */
    private String libelle;

    /**
     * Constructeur.
     */
    public IdentifiantStringLibelleDto() {
    }

    /**
     * Constructeur.
     * @param identifiant identifiant
     */
    public IdentifiantStringLibelleDto(String identifiant) {
        this.identifiant = identifiant;
        this.libelle = "";
    }

    /**
     * Constructeur.
     * @param identifiant identifiant
     * @param libelle libelle
     */
    public IdentifiantStringLibelleDto(String identifiant, String libelle) {
        this.identifiant = identifiant;
        this.libelle = libelle;
    }

    /**
     * Get the identifiant value.
     * @return the identifiant
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Set the identifiant value.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
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

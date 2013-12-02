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
 * Document numérique.
 * @author jgoncalves - SCUB
 */
public class DocumentDto implements Serializable {

    private static final long serialVersionUID = -5240679963261252948L;
    private Long identifiant;
    private String identifiantExterieur;

    /** Constructeur par défaut. */
    public DocumentDto() { }

    /**
     * Constructeur paramétré.
     * @param identifiantExterieur l'id exterieur
     */
    public DocumentDto(String identifiantExterieur) {
        this.identifiantExterieur = identifiantExterieur;
    }

    /**
     * Récupération de identifiant.
     * @return the identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }
    /**
     * Définition de identifiant.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }
    /**
     * Récupération de identifiantExterieur.
     * @return the identifiantExterieur
     */
    public String getIdentifiantExterieur() {
        return identifiantExterieur;
    }
    /**
     * Définition de identifiantExterieur.
     * @param identifiantExterieur the identifiantExterieur to set
     */
    public void setIdentifiantExterieur(String identifiantExterieur) {
        this.identifiantExterieur = identifiantExterieur;
    }
}

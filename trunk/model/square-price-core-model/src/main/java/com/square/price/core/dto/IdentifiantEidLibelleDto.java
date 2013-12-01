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

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO permettant de récupérer l'EID d'un objet en plus de son identifiant et de son libellé.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net)
 */
public class IdentifiantEidLibelleDto extends IdentifiantLibelleDto {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6129487921605794663L;

    /** Identifiant extérieur. */
    private String identifiantExterieur;

    /**
     * Constructeur.
     */
    public IdentifiantEidLibelleDto() {
        super();
    }

    /**
     * Constructeur.
     * @param identifiant identifiant
     */
    public IdentifiantEidLibelleDto(Long identifiant) {
        super(identifiant);
    }

    /**
     * Constructeur.
     * @param identifiant identifiant
     * @param libelle libelle
     */
    public IdentifiantEidLibelleDto(Long identifiant, String libelle) {
        super(identifiant, libelle);
    }

    /**
     * Récupère la valeur de identifiantExterieur.
     * @return la valeur de identifiantExterieur
     */
    public String getIdentifiantExterieur() {
        return identifiantExterieur;
    }

    /**
     * Définit la valeur de identifiantExterieur.
     * @param identifiantExterieur la nouvelle valeur de identifiantExterieur
     */
    public void setIdentifiantExterieur(String identifiantExterieur) {
        this.identifiantExterieur = identifiantExterieur;
    }

}

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
package com.square.composant.prestations.square.client.model;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

/**
 * DTO permettant de récupérer également l'EID d'une dimension.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class IdentifiantEidLibelleGwt extends IdentifiantLibelleGwt {

    /** Identifiant extérieur. */
    private String identifiantExterieur;

    /**
     * Constructeur.
     */
    public IdentifiantEidLibelleGwt() {
        super();
    }

    /**
     * Constructeur.
     * @param identifiant identifiant
     */
    public IdentifiantEidLibelleGwt(Long identifiant) {
        super(identifiant);
    }

    /**
     * Constructeur.
     * @param identifiant identifiant
     * @param libelle libelle
     */
    public IdentifiantEidLibelleGwt(Long identifiant, String libelle) {
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

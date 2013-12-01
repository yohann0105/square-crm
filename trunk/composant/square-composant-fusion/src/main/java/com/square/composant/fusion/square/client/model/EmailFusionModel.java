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
package com.square.composant.fusion.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle d'un email pour la fusion.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class EmailFusionModel implements IsSerializable {

    /** Identifiant de l'email. */
    private Long identifiant;

    /** Adresse email. */
    private String adresse;

    /** Nature de l'email. */
    private IdentifiantLibelleModel nature;

    /**
     * Récupère la valeur de identifiant.
     * @return la valeur de identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Définit la valeur de identifiant.
     * @param identifiant la nouvelle valeur de identifiant
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Récupère la valeur de adresse.
     * @return la valeur de adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Définit la valeur de adresse.
     * @param adresse la nouvelle valeur de adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Récupère la valeur de nature.
     * @return la valeur de nature
     */
    public IdentifiantLibelleModel getNature() {
        return nature;
    }

    /**
     * Définit la valeur de nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(IdentifiantLibelleModel nature) {
        this.nature = nature;
    }
}

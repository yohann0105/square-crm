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
package com.square.composant.tarificateur.square.client.model.doublons;

import org.scub.foundation.framework.gwt.module.client.util.DateUtil;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle pour les critères de recherche des doublons de personnes.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class RechercheDoublonCritereModel implements IsSerializable {

    /** Nom. */
    private String nom;

    /** Prénom. */
    private String prenom;

    /** Date de naissance. */
    private String dateNaissance;

    /**
     * Getter.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Getter.
     * @return the dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Setter.
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Verifie si les criteres de recherche sont complets.
     * @return flag
     */
    public boolean isComplete() {
        return this.getDateNaissance() != null && this.getNom() != null && this.getPrenom() != null && !this.getDateNaissance().equals("")
            && !this.getNom().equals("") && !this.getPrenom().equals("");
    }

}

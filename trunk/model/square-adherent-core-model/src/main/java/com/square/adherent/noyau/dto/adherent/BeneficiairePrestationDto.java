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
package com.square.adherent.noyau.dto.adherent;

import java.io.Serializable;

/**
 * Représente les infos d'un bénéficiaire de prestation.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class BeneficiairePrestationDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 9003971312692594081L;

    /** Identifiant du benef. */
    private Long identifiant;

    /** Nom. */
    private String nom;

    /** Prenom. */
    private String prenom;

    /**
     * Constructeur.
     */
    public BeneficiairePrestationDto() {
        super();
    }

    /**
     * Constructeur.
     * @param identifiant identifiant
     * @param nom nom
     * @param prenom prenom
     */
    public BeneficiairePrestationDto(Long identifiant, String nom, String prenom) {
        super();
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Get the identifiant value.
     * @return the identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Set the identifiant value.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Get the nom value.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the nom value.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get the prenom value.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Set the prenom value.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}

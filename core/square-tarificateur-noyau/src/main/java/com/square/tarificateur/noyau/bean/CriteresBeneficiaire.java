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
package com.square.tarificateur.noyau.bean;

import java.util.Calendar;

/**
 * DTO modélisant un bénéficiaire.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class CriteresBeneficiaire {

    /** Nom. */
    private String nom;

    /** Prénom. */
    private String prenom;

    /** Date de naissance. */
    private Calendar dateNaissance;

    /** Identifiant de la personne source. */
    private Long idPersonneSource;

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

    /**
     * Get the dateNaissance value.
     * @return the dateNaissance
     */
    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Set the dateNaissance value.
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Get the idPersonneSource value.
     * @return the idPersonneSource
     */
    public Long getIdPersonneSource() {
        return idPersonneSource;
    }

    /**
     * Set the idPersonneSource value.
     * @param idPersonneSource the idPersonneSource to set
     */
    public void setIdPersonneSource(Long idPersonneSource) {
        this.idPersonneSource = idPersonneSource;
    }

}

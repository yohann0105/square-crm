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
package com.square.tarificateur.noyau.model.personne;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Modèle d'un assuré social.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_ASSURE_SOCIAL")
public class AssureSocial extends BaseModel {

    /** serialVersionUID. */
    private static final long serialVersionUID = 7741735447560089334L;

    /** Personne. */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ASSURE_SOCIAL_PERSONNE_UID")
    private Personne personne;

    /** Nom. */
    @Column(name = "ASSURE_SOCIAL_NOM", nullable = false)
    private String nom;

    /** Prenom. */
    @Column(name = "ASSURE_SOCIAL_PRENOM", nullable = false)
    private String prenom;

    /** Civilite. */
    @Column(name = "ASSURE_SOCIAL_CIVILITE_EID", nullable = false)
    private Long eidCivilite;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof AssureSocial)) {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Get the personne value.
     * @return the personne
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * Set the personne value.
     * @param personne the personne to set
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;
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

    /**
     * Get the eidCivilite value.
     * @return the eidCivilite
     */
    public Long getEidCivilite() {
        return eidCivilite;
    }

    /**
     * Set the eidCivilite value.
     * @param eidCivilite the eidCivilite to set
     */
    public void setEidCivilite(Long eidCivilite) {
        this.eidCivilite = eidCivilite;
    }
}

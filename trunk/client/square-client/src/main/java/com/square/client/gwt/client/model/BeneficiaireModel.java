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
package com.square.client.gwt.client.model;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

/**
 * Un objet contenant le bénéficiaire.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class BeneficiaireModel extends PersonneBaseModel {

    /** L'identifiant du bénéficiaire. */
    private Long identifiant;

    /** Civilité de la personne. */
    private IdentifiantLibelleGwt civilite;

    /** Prénom de la personne. */
    private String prenom;

    /** Nom de la personne. */
    private String nom;

    /** Date de naissance de la personne. */
    private String dateNaissance;

    /** Type de relation. */
    private IdentifiantLibelleGwt typeRelation;

    /** Index du bénéficiaire. */
    private int index;

    /** Indique si l'enfant doit être rattaché aux 2 parents. */
    private Boolean rattacherAuxParents;

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
     * Get the civilite value.
     * @return the civilite
     */
    public IdentifiantLibelleGwt getCivilite() {
        return civilite;
    }

    /**
     * Set the civilite value.
     * @param civilite the civilite to set
     */
    public void setCivilite(IdentifiantLibelleGwt civilite) {
        this.civilite = civilite;
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
     * Get the dateNaissance value.
     * @return the dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Set the dateNaissance value.
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Get the typeRelation value.
     * @return the typeRelation
     */
    public IdentifiantLibelleGwt getTypeRelation() {
        return typeRelation;
    }

    /**
     * Set the typeRelation value.
     * @param typeRelation the typeRelation to set
     */
    public void setTypeRelation(IdentifiantLibelleGwt typeRelation) {
        this.typeRelation = typeRelation;
    }

    /**
     * Getter function.
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Setter function.
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Get the rattacherAuxParents value.
     * @return the rattacherAuxParents
     */
    public Boolean getRattacherAuxParents() {
        return rattacherAuxParents;
    }

    /**
     * Set the rattacherAuxParents value.
     * @param rattacherAuxParents the rattacherAuxParents to set
     */
    public void setRattacherAuxParents(Boolean rattacherAuxParents) {
        this.rattacherAuxParents = rattacherAuxParents;
    }

}

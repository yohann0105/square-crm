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

import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Un objet contenant une personne assuré social.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class PersonneCreationAssureSocialDto extends PersonneBaseDto {

    /** Serial Version UID. */
    private static final long serialVersionUID = -4809968483340882085L;

    /** Civilité de la personne. */
    private IdentifiantLibelleDto civilite;

    /** Prénom de la personne. */
    private String prenom;

    /** Nom de la personne. */
    private String nom;

    /** Date de naissance de la personne. */
    private Calendar dateNaissance;

    /** Info santé. */
    private InfoSanteDto infoSante;

    /**
     * Get the civilite value.
     * @return the civilite
     */
    public IdentifiantLibelleDto getCivilite() {
        return civilite;
    }

    /**
     * Set the civilite value.
     * @param civilite the civilite to set
     */
    public void setCivilite(IdentifiantLibelleDto civilite) {
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
     * Get the infoSante value.
     * @return the infoSante
     */
    public InfoSanteDto getInfoSante() {
        return infoSante;
    }

    /**
     * Set the infoSante value.
     * @param infoSante the infoSante to set
     */
    public void setInfoSante(InfoSanteDto infoSante) {
        this.infoSante = infoSante;
    }

}

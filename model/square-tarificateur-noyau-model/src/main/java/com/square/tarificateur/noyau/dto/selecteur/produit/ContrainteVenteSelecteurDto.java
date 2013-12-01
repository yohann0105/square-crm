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
package com.square.tarificateur.noyau.dto.selecteur.produit;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Dto réprésentant une contrainte de ventes d'un produit pour le selecteur de produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ContrainteVenteSelecteurDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 4696422831174794690L;

    /** Identifiant. */
    private Integer identifiant;

    /** Age minimum d'un adherent. */
    private Integer ageMinAdherent;

    /** Age Maximum adherent. */
    private Integer ageMaxAdherent;

    /** Age Minimum prospect. */
    private Integer ageMinProspect;

    /** Age Maximum prospect. */
    private Integer ageMaxProspect;

    /** Age Millesime. */
    private Boolean ageMillesime;

    /** Date minimum date effet. */
    private Calendar dateMinEffet;

    /** Date maximum date effet. */
    private Calendar dateMaxEffet;

    /**
     * Get the ageMaxAdherent value.
     * @return the ageMaxAdherent
     */
    public Integer getAgeMaxAdherent() {
        return ageMaxAdherent;
    }

    /**
     * Set the ageMaxAdherent value.
     * @param ageMaxAdherent the ageMaxAdherent to set
     */
    public void setAgeMaxAdherent(Integer ageMaxAdherent) {
        this.ageMaxAdherent = ageMaxAdherent;
    }

    /**
     * Get the ageMaxProspect value.
     * @return the ageMaxProspect
     */
    public Integer getAgeMaxProspect() {
        return ageMaxProspect;
    }

    /**
     * Set the ageMaxProspect value.
     * @param ageMaxProspect the ageMaxProspect to set
     */
    public void setAgeMaxProspect(Integer ageMaxProspect) {
        this.ageMaxProspect = ageMaxProspect;
    }

    /**
     * Get the ageMillesime value.
     * @return the ageMillesime
     */
    public Boolean getAgeMillesime() {
        return ageMillesime;
    }

    /**
     * Set the ageMillesime value.
     * @param ageMillesime the ageMillesime to set
     */
    public void setAgeMillesime(Boolean ageMillesime) {
        this.ageMillesime = ageMillesime;
    }

    /**
     * Get the ageMinAdherent value.
     * @return the ageMinAdherent
     */
    public Integer getAgeMinAdherent() {
        return ageMinAdherent;
    }

    /**
     * Set the ageMinAdherent value.
     * @param ageMinAdherent the ageMinAdherent to set
     */
    public void setAgeMinAdherent(Integer ageMinAdherent) {
        this.ageMinAdherent = ageMinAdherent;
    }

    /**
     * Get the ageMinProspect value.
     * @return the ageMinProspect
     */
    public Integer getAgeMinProspect() {
        return ageMinProspect;
    }

    /**
     * Set the ageMinProspect value.
     * @param ageMinProspect the ageMinProspect to set
     */
    public void setAgeMinProspect(Integer ageMinProspect) {
        this.ageMinProspect = ageMinProspect;
    }

    /**
     * Get the dateMaxEffet value.
     * @return the dateMaxEffet
     */
    public Calendar getDateMaxEffet() {
        return dateMaxEffet;
    }

    /**
     * Set the dateMaxEffet value.
     * @param dateMaxEffet the dateMaxEffet to set
     */
    public void setDateMaxEffet(Calendar dateMaxEffet) {
        this.dateMaxEffet = dateMaxEffet;
    }

    /**
     * Get the dateMinEffet value.
     * @return the dateMinEffet
     */
    public Calendar getDateMinEffet() {
        return dateMinEffet;
    }

    /**
     * Set the dateMinEffet value.
     * @param dateMinEffet the dateMinEffet to set
     */
    public void setDateMinEffet(Calendar dateMinEffet) {
        this.dateMinEffet = dateMinEffet;
    }

    /**
     * Get the identifiant value.
     * @return the identifiant
     */
    public Integer getIdentifiant() {
        return identifiant;
    }

    /**
     * Set the identifiant value.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

}

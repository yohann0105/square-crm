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

import java.io.Serializable;
import java.util.Calendar;
/**
 * Dto Contrainte de ventes d'un produit.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public class ContrainteVenteDto implements Serializable
{
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 7470506572026187237L;

    /**
     * Identifiant.
     */
    private Integer identifiant;

    /**
     * Age minimum d'un adherent.
     */
    private Integer ageMinAdherent;

    /**
     * Age Maximum adherent.
     */
    private Integer ageMaxAdherent;

    /**
     * Age Minimum prospect.
     */
    private Integer ageMinProspect;

    /**
     * Age Maximum prospect.
     */
    private Integer ageMaxProspect;

    /**
     * Age Maximum bénéficiaire.
     */
    private Integer ageMaxBeneficiaire;

    /**
     * Age Minimum bénéficiaire.
     */
    private Integer ageMinBeneficiaire;

    /**
     * Age Millesime.
     */
    private Boolean ageMillesime;

    /**
     * Date minimum date effet.
     */
    private Calendar dateMinEffet;

    /**
     * Date maximum date effet.
     */
    private Calendar dateMaxEffet;

    /**
     * Affichage (texte supplémentaire à afficher pour un produit).
     */
    private String affichage;

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

    /**
     * Get the ageMaxBeneficiaire value.
     * @return the ageMaxBeneficiaire
     */
    public Integer getAgeMaxBeneficiaire() {
        return ageMaxBeneficiaire;
    }

    /**
     * Set the ageMaxBeneficiaire value.
     * @param ageMaxBeneficiaire the ageMaxBeneficiaire to set
     */
    public void setAgeMaxBeneficiaire(Integer ageMaxBeneficiaire) {
        this.ageMaxBeneficiaire = ageMaxBeneficiaire;
    }

    /**
     * Get the ageMinBeneficiaire value.
     * @return the ageMinBeneficiaire
     */
    public Integer getAgeMinBeneficiaire() {
        return ageMinBeneficiaire;
    }

    /**
     * Set the ageMinBeneficiaire value.
     * @param ageMinBeneficiaire the ageMinBeneficiaire to set
     */
    public void setAgeMinBeneficiaire(Integer ageMinBeneficiaire) {
        this.ageMinBeneficiaire = ageMinBeneficiaire;
    }

    /**
     * Récupère la valeur de affichage.
     * @return la valeur de affichage
     */
    public String getAffichage() {
        return affichage;
    }

    /**
     * Définit la valeur de affichage.
     * @param affichage la nouvelle valeur de affichage
     */
    public void setAffichage(String affichage) {
        this.affichage = affichage;
    }
}

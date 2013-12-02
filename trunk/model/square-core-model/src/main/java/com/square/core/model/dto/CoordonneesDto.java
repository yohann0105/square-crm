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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Dto contenant les coordonnées d'une personne.
 */
public class CoordonneesDto implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 4270658941157700768L;

    /**
     * Identifiant de la personne.
     */
    private Long idPersonne;

    /** Flag indiquant si la nature de la personne a changé. */
    private Boolean hasNaturePersonneChanged;

    /** Ancienne nature de la personne. */
    private String ancienneNaturePersonne;

    /** Nouvelle nature de la personne. */
    private String nouvelleNaturePersonne;

    /**
     * Adresses de la personne.
     */
    private List<AdresseDto> adresses = new ArrayList<AdresseDto>();

    /**
     * Téléphones de la personne.
     */
    private List<TelephoneDto> telephones = new ArrayList<TelephoneDto>();

    /**
     * Emails de la personne.
     */
    private List<EmailDto> emails = new ArrayList<EmailDto>();

    /**
     * Renvoi la valeur de idPersonne.
     * @return idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Modifie idPersonne.
     * @param idPersonne la nouvelle valeur de idPersonne
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    /**
     * Get the adresses value.
     * @return the adresses
     */
    public List<AdresseDto> getAdresses() {
        return adresses;
    }

    /**
     * Set the adresses value.
     * @param adresses the adresses to set
     */
    public void setAdresses(List<AdresseDto> adresses) {
        this.adresses = adresses;
    }

    /**
     * Get the telephones value.
     * @return the telephones
     */
    public List<TelephoneDto> getTelephones() {
        return telephones;
    }

    /**
     * Set the telephones value.
     * @param telephones the telephones to set
     */
    public void setTelephones(List<TelephoneDto> telephones) {
        this.telephones = telephones;
    }

    /**
     * Get the emails value.
     * @return the emails
     */
    public List<EmailDto> getEmails() {
        return emails;
    }

    /**
     * Set the emails value.
     * @param emails the emails to set
     */
    public void setEmails(List<EmailDto> emails) {
        this.emails = emails;
    }

    /**
     * Récupère la valeur de hasNaturePersonneChanged.
     * @return la valeur de hasNaturePersonneChanged
     */
    public Boolean getHasNaturePersonneChanged() {
        return hasNaturePersonneChanged;
    }

    /**
     * Définit la valeur de hasNaturePersonneChanged.
     * @param hasNaturePersonneChanged la nouvelle valeur de hasNaturePersonneChanged
     */
    public void setHasNaturePersonneChanged(Boolean hasNaturePersonneChanged) {
        this.hasNaturePersonneChanged = hasNaturePersonneChanged;
    }

    /**
     * Récupère la valeur de ancienneNaturePersonne.
     * @return la valeur de ancienneNaturePersonne
     */
    public String getAncienneNaturePersonne() {
        return ancienneNaturePersonne;
    }

    /**
     * Définit la valeur de ancienneNaturePersonne.
     * @param ancienneNaturePersonne la nouvelle valeur de ancienneNaturePersonne
     */
    public void setAncienneNaturePersonne(String ancienneNaturePersonne) {
        this.ancienneNaturePersonne = ancienneNaturePersonne;
    }

    /**
     * Récupère la valeur de nouvelleNaturePersonne.
     * @return la valeur de nouvelleNaturePersonne
     */
    public String getNouvelleNaturePersonne() {
        return nouvelleNaturePersonne;
    }

    /**
     * Définit la valeur de nouvelleNaturePersonne.
     * @param nouvelleNaturePersonne la nouvelle valeur de nouvelleNaturePersonne
     */
    public void setNouvelleNaturePersonne(String nouvelleNaturePersonne) {
        this.nouvelleNaturePersonne = nouvelleNaturePersonne;
    }

}

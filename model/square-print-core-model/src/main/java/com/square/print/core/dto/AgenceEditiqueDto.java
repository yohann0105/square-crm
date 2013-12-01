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
package com.square.print.core.dto;

import java.io.Serializable;


/**
 * DTO représentant une agence qui s'occupe du devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class AgenceEditiqueDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -5870464879827011230L;

    /** Identifiant. */
    private Long id;

    /** Dénomination. */
    private String denomination;

    /** Adresse. */
    private AdresseDto adresse;

    /** E-mail. */
    private String email;

    /** Numéro de téléphone. */
    private String numeroTelephone;

    /** Constructeur. */
    public AgenceEditiqueDto() { }

    /**
     * Constructeur.
     * @param id l'identifiant de l'agence
     * @param denomination la dénomination de l'agence
     * @param adresse l'adresse de l'agence
     * @param email l'e-mail
     */
    public AgenceEditiqueDto(Long id, String denomination, AdresseDto adresse, String email) {
        this.id = id;
        this.denomination = denomination;
        this.adresse = adresse;
        this.email = email;
    }

    /**
     * Définit la valeur de id.
     * @return la valeur de id
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Définit la valeur de denomination.
     * @return la valeur de denomination
     */
    public String getDenomination() {
        return denomination;
    }

    /**
     * Définit la valeur de denomination.
     * @param denomination la nouvelle valeur de denomination
     */
    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    /**
     * Définit la valeur de adresse.
     * @return la valeur de adresse
     */
    public AdresseDto getAdresse() {
        if (adresse == null) {
            adresse = new AdresseDto();
        }
        return adresse;
    }

    /**
     * Définit la valeur de adresse.
     * @param adresse la nouvelle valeur de adresse
     */
    public void setAdresse(AdresseDto adresse) {
        this.adresse = adresse;
    }

    /**
     * Retourne la valeur de email.
     * @return la valeur de email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit la valeur de email.
     * @param email la nouvelle valeur de email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the numeroTelephone value.
     * @return the numeroTelephone
     */
    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    /**
     * Set the numeroTelephone value.
     * @param numeroTelephone the numeroTelephone to set
     */
    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

}

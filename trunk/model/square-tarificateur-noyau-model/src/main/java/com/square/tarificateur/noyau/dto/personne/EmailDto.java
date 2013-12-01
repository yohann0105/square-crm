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
package com.square.tarificateur.noyau.dto.personne;

import java.io.Serializable;

/**
 * DTO modélisant une adresse email.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class EmailDto implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = -8245322714119324912L;

    /** Identifiant de l'email. */
    private Long idEmail;

    /** EID de l'email (Square). */
    private Long eidEmail;

    /** Adresse. */
    private String adresse;

    /** Code AIA. */
    private String codeAia;

    /** Booléen indiquant si l'email est invalide. */
    private boolean isInvalide = false;

    /**
     * Getter function.
     * @return the idEmail
     */
    public Long getIdEmail() {
        return idEmail;
    }

    /**
     * Getter function.
     * @return the eidEmail
     */
    public Long getEidEmail() {
        return eidEmail;
    }

    /**
     * Getter function.
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Setter function.
     * @param idEmail the idEmail to set
     */
    public void setIdEmail(Long idEmail) {
        this.idEmail = idEmail;
    }

    /**
     * Setter function.
     * @param eidEmail the eidEmail to set
     */
    public void setEidEmail(Long eidEmail) {
        this.eidEmail = eidEmail;
    }

    /**
     * Setter function.
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Récupère la valeur de codeAia.
     * @return la valeur de codeAia
     */
    public String getCodeAia() {
        return codeAia;
    }

    /**
     * Définit la valeur de codeAia.
     * @param codeAia la nouvelle valeur de codeAia
     */
    public void setCodeAia(String codeAia) {
        this.codeAia = codeAia;
    }

    /**
     * Récupère la valeur de isInvalide.
     * @return la valeur de isInvalide
     */
    public boolean getIsInvalide() {
        return isInvalide;
    }

    /**
     * Définit la valeur de isInvalide.
     * @param isInvalide la nouvelle valeur de isInvalide
     */
    public void setIsInvalide(boolean isInvalide) {
        this.isInvalide = isInvalide;
    }

}

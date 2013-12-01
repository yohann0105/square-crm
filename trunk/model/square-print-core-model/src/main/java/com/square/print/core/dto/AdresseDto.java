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
 * DTO représentant une adresse.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class AdresseDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 7257890777851624555L;

    /** Numéro de voie. */
    private String numeroVoie;

    /** Code Bis. */
    private String codeBis;

    /** Nature de la voie. */
    private String natureVoie;

    /** Libellé de la voie. */
    private String libelleVoie;

    /** Complément de l'adresse. */
    private String complementAdresse;

    /** Complément de l'adresse. */
    private String complementAdresse2;

    /** Code postal. */
    private String codePostal;

    /** Ville. */
    private String ville;

    /**
     * Définit la valeur de codePostal.
     * @return la valeur de codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Définit la valeur de codePostal.
     * @param codePostal la nouvelle valeur de codePostal
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Définit la valeur de ville.
     * @return la valeur de ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Définit la valeur de ville.
     * @param ville la nouvelle valeur de ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Retourne la valeur de numeroVoie.
     * @return la valeur de numeroVoie
     */
    public String getNumeroVoie() {
        return numeroVoie;
    }

    /**
     * Définit la valeur de numeroVoie.
     * @param numeroVoie la nouvelle valeur de numeroVoie
     */
    public void setNumeroVoie(String numeroVoie) {
        this.numeroVoie = numeroVoie;
    }

    /**
     * Retourne la valeur de natureVoie.
     * @return la valeur de natureVoie
     */
    public String getNatureVoie() {
        return natureVoie;
    }

    /**
     * Définit la valeur de natureVoie.
     * @param natureVoie la nouvelle valeur de natureVoie
     */
    public void setNatureVoie(String natureVoie) {
        this.natureVoie = natureVoie;
    }

    /**
     * Retourne la valeur de libelleVoie.
     * @return la valeur de libelleVoie
     */
    public String getLibelleVoie() {
        return libelleVoie;
    }

    /**
     * Définit la valeur de libelleVoie.
     * @param libelleVoie la nouvelle valeur de libelleVoie
     */
    public void setLibelleVoie(String libelleVoie) {
        this.libelleVoie = libelleVoie;
    }

    /**
     * Retourne la valeur de complementAdresse.
     * @return la valeur de complementAdresse
     */
    public String getComplementAdresse() {
        return complementAdresse;
    }

    /**
     * Définit la valeur de complementAdresse.
     * @param complementAdresse la nouvelle valeur de complementAdresse
     */
    public void setComplementAdresse(String complementAdresse) {
        this.complementAdresse = complementAdresse;
    }

    /**
     * Retourne la valeur de complementAdresse2.
     * @return la valeur de complementAdresse2
     */
    public String getComplementAdresse2() {
        return complementAdresse2;
    }

    /**
     * Définit la valeur de complementAdresse2.
     * @param complementAdresse2 la nouvelle valeur de complementAdresse2
     */
    public void setComplementAdresse2(String complementAdresse2) {
        this.complementAdresse2 = complementAdresse2;
    }

    /**
     * Retourne la valeur de codeBis.
     * @return la valeur de codeBis
     */
    public String getCodeBis() {
        return codeBis;
    }

    /**
     * Définit la valeur de codeBis.
     * @param codeBis la nouvelle valeur de codeBis
     */
    public void setCodeBis(String codeBis) {
        this.codeBis = codeBis;
    }

}

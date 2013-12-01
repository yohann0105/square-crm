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
package com.square.adherent.noyau.dto.adherent;

import java.io.Serializable;

/**
 * DTO regroupant les informations d'une adresse.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class AdresseDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 8003420415557871631L;

    /** Numéro de la voie. */
    private String numeroVoie;

    /** Complément du nom. */
    private String complementNom;

    /** Identifiant de la nature de la voie. */
    private String idNatureVoie;

    /** Libellé de la nature de la voie. */
    private String libelleNatureVoie;

    /** Adresse. */
    private String adresse;

    /** Complément de l'adresse. */
    private String complementAdresse;

    /** Autre complément. */
    private String autreComplement;

    /** Code postal. */
    private String codePostal;

    /** Ville. */
    private String ville;

    /**
     * Récupère la valeur de numeroVoie.
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
     * Récupère la valeur de complementNom.
     * @return la valeur de complementNom
     */
    public String getComplementNom() {
        return complementNom;
    }

    /**
     * Définit la valeur de complementNom.
     * @param complementNom la nouvelle valeur de complementNom
     */
    public void setComplementNom(String complementNom) {
        this.complementNom = complementNom;
    }

    /**
     * Récupère la valeur de idNatureVoie.
     * @return la valeur de idNatureVoie
     */
    public String getIdNatureVoie() {
        return idNatureVoie;
    }

    /**
     * Définit la valeur de idNatureVoie.
     * @param idNatureVoie la nouvelle valeur de idNatureVoie
     */
    public void setIdNatureVoie(String idNatureVoie) {
        this.idNatureVoie = idNatureVoie;
    }

    /**
     * Récupère la valeur de libelleNatureVoie.
     * @return la valeur de libelleNatureVoie
     */
    public String getLibelleNatureVoie() {
        return libelleNatureVoie;
    }

    /**
     * Définit la valeur de libelleNatureVoie.
     * @param libelleNatureVoie la nouvelle valeur de libelleNatureVoie
     */
    public void setLibelleNatureVoie(String libelleNatureVoie) {
        this.libelleNatureVoie = libelleNatureVoie;
    }

    /**
     * Récupère la valeur de adresse.
     * @return la valeur de adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Définit la valeur de adresse.
     * @param adresse la nouvelle valeur de adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Récupère la valeur de complementAdresse.
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
     * Récupère la valeur de autreComplement.
     * @return la valeur de autreComplement
     */
    public String getAutreComplement() {
        return autreComplement;
    }

    /**
     * Définit la valeur de autreComplement.
     * @param autreComplement la nouvelle valeur de autreComplement
     */
    public void setAutreComplement(String autreComplement) {
        this.autreComplement = autreComplement;
    }

    /**
     * Récupère la valeur de codePostal.
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
     * Récupère la valeur de ville.
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
}

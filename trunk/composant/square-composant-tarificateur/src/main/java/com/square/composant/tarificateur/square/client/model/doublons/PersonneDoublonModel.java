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
package com.square.composant.tarificateur.square.client.model.doublons;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.composant.tarificateur.square.client.model.personne.InfoSanteModel;

/**
 * Modèle pour les personnes en doublon.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class PersonneDoublonModel implements IsSerializable {

    /** Identifiant du client. */
    private Long id;

    /** Civilité. */
    private IdentifiantLibelleGwt civilite;

    /** Nom du client. */
    private String nom;

    /** Prénom du client. */
    private String prenom;

    /** Date de naissance. */
    private String dateNaissance;

    /** Adresse. */
    private String adresse;

    /** Code postal. */
    private String codePostal;

    /** Ville. */
    private String commune;

    /** Composition familiale. */
    private String compoFamiliale;

    /** Info santé. */
    private InfoSanteModel infoSante;

    /**
     * Récupère la valeur de id.
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
     * Récupère la valeur de nom.
     * @return la valeur de nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit la valeur de nom.
     * @param nom la nouvelle valeur de nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la valeur de prenom.
     * @return la valeur de prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit la valeur de prenom.
     * @param prenom la nouvelle valeur de prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupère la valeur de dateNaissance.
     * @return la valeur de dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définit la valeur de dateNaissance.
     * @param dateNaissance la nouvelle valeur de dateNaissance
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
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
     * Récupère la valeur de commune.
     * @return la valeur de commune
     */
    public String getCommune() {
        return commune;
    }

    /**
     * Définit la valeur de commune.
     * @param commune la nouvelle valeur de commune
     */
    public void setCommune(String commune) {
        this.commune = commune;
    }

    /**
     * Récupère la valeur de compoFamiliale.
     * @return la valeur de compoFamiliale
     */
    public String getCompoFamiliale() {
        return compoFamiliale;
    }

    /**
     * Définit la valeur de compoFamiliale.
     * @param compoFamiliale la nouvelle valeur de compoFamiliale
     */
    public void setCompoFamiliale(String compoFamiliale) {
        this.compoFamiliale = compoFamiliale;
    }

    /**
     * Get the infoSante value.
     * @return the infoSante
     */
    public InfoSanteModel getInfoSante() {
        return infoSante;
    }

    /**
     * Set the infoSante value.
     * @param infoSante the infoSante to set
     */
    public void setInfoSante(InfoSanteModel infoSante) {
        this.infoSante = infoSante;
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
}

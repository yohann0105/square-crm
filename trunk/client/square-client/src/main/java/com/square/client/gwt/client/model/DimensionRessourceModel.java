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

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle contenant le résultat de recherche des ressources.
 * @author cblanchard - SCUB
 */
public class DimensionRessourceModel implements IsSerializable {

    /** Identifiant de la ressource. */
    private Long identifiant;

    /** Identifiant extérieur. */
    private String identifiantExterieur;

    /** Prénom de la ressource. */
    private String prenom;

    /** Nom de la ressource. */
    private String nom;

    /** Identifiant de connexion de la ressource. */
    private String login;

    /** L'agence à laquelle la ressource est assignée. */
    private IdentifiantLibelleGwt agence;

    /** L'état de la ressource. */
    private IdentifiantLibelleGwt etat;

    /** La fonction. */
    private IdentifiantLibelleGwt fonction;

    /**
     * Renvoi la valeur de identifiant.
     * @return identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Modifie identifiant.
     * @param identifiant la nouvelle valeur de identifiant
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Renvoi la valeur de prenom.
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie prenom.
     * @param prenom la nouvelle valeur de prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Renvoi la valeur de nom.
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie nom.
     * @param nom la nouvelle valeur de nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter function.
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter function.
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter function.
     * @return the agence
     */
    public IdentifiantLibelleGwt getAgence() {
        return agence;
    }

    /**
     * Setter function.
     * @param agence the agence to set
     */
    public void setAgence(IdentifiantLibelleGwt agence) {
        this.agence = agence;
    }

    /**
     * Récupère la valeur de identifiantExterieur.
     * @return la valeur de identifiantExterieur
     */
    public String getIdentifiantExterieur() {
        return identifiantExterieur;
    }

    /**
     * Définit la valeur de identifiantExterieur.
     * @param identifiantExterieur la nouvelle valeur de identifiantExterieur
     */
    public void setIdentifiantExterieur(String identifiantExterieur) {
        this.identifiantExterieur = identifiantExterieur;
    }

    /**
     * Getter function.
     * @return the etat
     */
    public IdentifiantLibelleGwt getEtat() {
        return etat;
    }

    /**
     * Setter function.
     * @param etat the etat to set
     */
    public void setEtat(IdentifiantLibelleGwt etat) {
        this.etat = etat;
    }

    /**
     * Get the value of fonction.
     * @return the fonction
     */
    public IdentifiantLibelleGwt getFonction() {
        return fonction;
    }

    /**
     * Set the value of fonction.
     * @param fonction the fonction to set
     */
    public void setFonction(IdentifiantLibelleGwt fonction) {
        this.fonction = fonction;
    }

}

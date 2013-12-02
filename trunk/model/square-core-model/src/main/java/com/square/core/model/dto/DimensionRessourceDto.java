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

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Objet contenant le résultat de recherche des ressources.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class DimensionRessourceDto implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -2000375341612691466L;

    /** Identifiant de la ressource. */
    private Long identifiant;

    /** Identifiant extérieur. */
    private String identifiantExterieur;

    /** Prénom de la ressource. */
    private String prenom;

    /** Nom de la ressource. */
    private String nom;

    /** L'agence à laquelle la ressource est assignée. */
    private IdentifiantLibelleDto agence;

    /** L'email de la ressource. */
    private String email;

    /** L'état de la ressource. */
    private IdentifiantLibelleDto etat;

    /** La fonction. */
    private IdentifiantLibelleDto fonction;

    /**
     * Constructeur par défaut.
     */
    public DimensionRessourceDto() {
        super();
    }

    /**
     * Constructeur.
     * @param identifiant l'identifiant de la ressource.
     */
    public DimensionRessourceDto(Long identifiant) {
        super();
        this.identifiant = identifiant;
    }

    /**
     * Retourne la valeur de identifiant.
     * @return the identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Modifie la valeur de identifiant.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Retourne la valeur de prenom.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie la valeur de prenom.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne la valeur de nom.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie la valeur de nom.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter function.
     * @return the agence
     */
    public IdentifiantLibelleDto getAgence() {
        return agence;
    }

    /**
     * Setter function.
     * @param agence the agence to set
     */
    public void setAgence(IdentifiantLibelleDto agence) {
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
     * Récupère la valeur de email.
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
     * Getter function.
     * @return the etat
     */
    public IdentifiantLibelleDto getEtat() {
        return etat;
    }

    /**
     * Setter function.
     * @param etat the etat to set
     */
    public void setEtat(IdentifiantLibelleDto etat) {
        this.etat = etat;
    }

    /**
     * Get the value of fonction.
     * @return the fonction
     */
    public IdentifiantLibelleDto getFonction() {
        return fonction;
    }

    /**
     * Set the value of fonction.
     * @param fonction the fonction to set
     */
    public void setFonction(IdentifiantLibelleDto fonction) {
        this.fonction = fonction;
    }

}

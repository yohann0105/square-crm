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
package com.square.composant.fusion.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle pour les résultats de la recherche pour la sélection de doublons.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class RechercheDoublonResultatModel implements IsSerializable {

    /** Identifiant du client. */
    private Long id;

    /** Numéro du client. */
    private String numeroClient;

    /** Nom du client. */
    private String nom;

    /** Prénom du client. */
    private String prenom;

    /** Date de naissance. */
    private String dateNaissance;

    /** Code postal. */
    private String codePostal;

    /** Ville. */
    private String commune;

    /** Nature. */
    private String nature;

    /** Date de création. */
    private String dateCreation;

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
     * Récupère la valeur de numeroClient.
     * @return la valeur de numeroClient
     */
    public String getNumeroClient() {
        return numeroClient;
    }

    /**
     * Définit la valeur de numeroClient.
     * @param numeroClient la nouvelle valeur de numeroClient
     */
    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
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
     * Récupère la valeur de nature.
     * @return la valeur de nature
     */
    public String getNature() {
        return nature;
    }

    /**
     * Définit la valeur de nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * Récupère la valeur de dateCreation.
     * @return la valeur de dateCreation
     */
    public String getDateCreation() {
        return dateCreation;
    }

    /**
     * Définit la valeur de dateCreation.
     * @param dateCreation la nouvelle valeur de dateCreation
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

}

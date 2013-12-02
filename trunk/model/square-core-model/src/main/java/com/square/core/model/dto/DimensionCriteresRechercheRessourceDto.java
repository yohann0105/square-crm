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
import java.util.List;

/**
 * Objet conténant les critères de recherche des ressources.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class DimensionCriteresRechercheRessourceDto implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = -1710000907181795997L;

    /** Nom de la ressource. */
    private String nom;

    /** Prenom de la ressource. */
    private String prenom;

    /** Identifiant de l'agence. */
    private Long idAgence;

    /** Nombre de résultats max. */
    private Integer maxResults;

    /** Services du client. */
    private List<Long> idServices;

    /** Fonctions du client. */
    private List<Long> idFonctions;

    /** Etats du client. */
    private List<Long> idEtats;

    /** Agences du client. */
    private List<Long> idAgences;

    /** Regions du client. */
    private List<Long> idRegions;

    /** Recherche sur nom + prénom. */
    private String nomPrenom;

    /** Recherche sur prénom + nom. */
    private String prenomNom;

    /** Indique si on recherche des ressources supprimées ou non. */
    private Boolean isSupprime;

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
     * Retourne la valeur du prenom.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie la valeur du prenom.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne la valeur de idAgence.
     * @return the idAgence
     */
    public Long getIdAgence() {
        return idAgence;
    }

    /**
     * Modifie la valeur de idAgence.
     * @param idAgence the idAgence to set
     */
    public void setIdAgence(Long idAgence) {
        this.idAgence = idAgence;
    }

    /**
     * Retourne la valeur de maxResults.
     * @return the maxResults
     */
    public Integer getMaxResults() {
        return maxResults;
    }

    /**
     * Modifie la valeur de maxResults.
     * @param maxResults the maxResults to set
     */
    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    /**
     * Retourne la valeur de idServices.
     * @return the idServices
     */
    public List<Long> getIdServices() {
        return idServices;
    }

    /**
     * Modifie la valeur de maxResults.
     * @param idServices the idServices to set
     */
    public void setIdServices(List<Long> idServices) {
        this.idServices = idServices;
    }

    /**
     * Retourne la valeur de idFonctions.
     * @return the idFonctions
     */
    public List<Long> getIdFonctions() {
        return idFonctions;
    }

    /**
     * Modifie la valeur de idFonctions.
     * @param idFonctions the idFonctions to set
     */
    public void setIdFonctions(List<Long> idFonctions) {
        this.idFonctions = idFonctions;
    }

    /**
     * Retourne la valeur de idEtats.
     * @return the idEtats
     */
    public List<Long> getIdEtats() {
        return idEtats;
    }

    /**
     * Modifie la valeur de idEtats.
     * @param idEtats the idEtats to set
     */
    public void setIdEtats(List<Long> idEtats) {
        this.idEtats = idEtats;
    }

    /**
     * Retourne la valeur de idAgences.
     * @return the idAgences
     */
    public List<Long> getIdAgences() {
        return idAgences;
    }

    /**
     * Modifie la valeur de idAgences.
     * @param idAgences the idAgences to set
     */
    public void setIdAgences(List<Long> idAgences) {
        this.idAgences = idAgences;
    }

    /**
     * Récupère la valeur de nomPrenom.
     * @return la valeur de nomPrenom
     */
    public String getNomPrenom() {
        return nomPrenom;
    }

    /**
     * Définit la valeur de nomPrenom.
     * @param nomPrenom la nouvelle valeur de nomPrenom
     */
    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    /**
     * Récupère la valeur de prenomNom.
     * @return la valeur de prenomNom
     */
    public String getPrenomNom() {
        return prenomNom;
    }

    /**
     * Définit la valeur de prenomNom.
     * @param prenomNom la nouvelle valeur de prenomNom
     */
    public void setPrenomNom(String prenomNom) {
        this.prenomNom = prenomNom;
    }

    /**
     * Get the isSupprime value.
     * @return the isSupprime
     */
    public Boolean getIsSupprime() {
        return isSupprime;
    }

    /**
     * Définit la valeur de isSupprime.
     * @param isSupprime la nouvelle valeur de isSupprime
     */
    public void setIsSupprime(Boolean isSupprime) {
        this.isSupprime = isSupprime;
    }

    /**
     * Get the value of idRegions.
     * @return the idRegions
     */
    public List<Long> getIdRegions() {
        return idRegions;
    }

    /**
     * Set the value of idRegions.
     * @param idRegions the idRegions to set
     */
    public void setIdRegions(List<Long> idRegions) {
        this.idRegions = idRegions;
    }
}

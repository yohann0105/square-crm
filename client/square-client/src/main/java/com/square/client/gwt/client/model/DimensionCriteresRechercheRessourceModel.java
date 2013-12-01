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

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Critères de recherche de ressources.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class DimensionCriteresRechercheRessourceModel implements IsSerializable {

    /**
     * Nom de la ressource.
     */
    private String nom;

    /**
     * Prenom de la ressource.
     */
    private String prenom;

    /**
     * Identifiant de l'agence.
     */
    private Long idAgence;

    /** Nombre de résultats max. */
    private Integer maxResults;

    /**
     * Agences du client.
     */
    private List<Long> idAgences;

    /**
     * Identifiants de états de la ressource.
     */
    private List<Long> idEtats;

    /**
     * Indique si on recherche des ressources supprimées ou non.
     */
    private Boolean isSupprime;

    /**
     * Getter function.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter function.
     * @return the idAgence
     */
    public Long getIdAgence() {
        return idAgence;
    }

    /**
     * Getter function.
     * @return the maxResults
     */
    public Integer getMaxResults() {
        return maxResults;
    }

    /**
     * Setter function.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter function.
     * @param idAgence the idAgence to set
     */
    public void setIdAgence(Long idAgence) {
        this.idAgence = idAgence;
    }

    /**
     * Setter function.
     * @param maxResults the maxResults to set
     */
    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
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
     * Récupère la valeur de idAgences.
     * @return la valeur de idAgences
     */
    public List<Long> getIdAgences() {
        return idAgences;
    }

    /**
     * Définit la valeur de idAgences.
     * @param idAgences la nouvelle valeur de idAgences
     */
    public void setIdAgences(List<Long> idAgences) {
        this.idAgences = idAgences;
    }

    /**
     * Récupère la valeur de idEtats.
     * @return la valeur de idEtats
     */
    public List<Long> getIdEtats() {
        return idEtats;
    }

    /**
     * Définit la valeur de idEtats.
     * @param idEtats la nouvelle valeur de idEtats
     */
    public void setIdEtats(List<Long> idEtats) {
        this.idEtats = idEtats;
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
}

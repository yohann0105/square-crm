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
import java.util.ArrayList;
import java.util.List;

/**
 * Objet conténant les critères de recherche des ressources.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class RessourceCriteresRechercheDto implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 2212124512362195072L;

    /**
     * Nom de la ressource.
     */
    private String nom;

    /**
     * Prenom de la ressource.
     */
    private String prenom;

    /**
     * Nombre de résultats max.
     */
    private Integer maxResults;

    /**
     * Services du client.
     */
    List<Long> idServices;

    /**
     * Fonctions du client.
     */
    List<Long> idFonctions;

    /**
     * Etats du client.
     */
    List<Long> idEtats;

    /**
     * Agences du client.
     */
    List<Long> idAgences;

    /** Liste des identifiant extérieurs des fonctions. */
    List<String> listeIdsExtsFonctions;

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
     * Récupère la valeur de listeIdsExtsFonctions.
     * @return la valeur de listeIdsExtsFonctions
     */
    public List<String> getListeIdsExtsFonctions() {
        if (listeIdsExtsFonctions == null) {
            listeIdsExtsFonctions = new ArrayList<String>();
        }
        return listeIdsExtsFonctions;
    }

    /**
     * Définit la valeur de listeIdsExtsFonctions.
     * @param listeIdsExtsFonctions la nouvelle valeur de listeIdsExtsFonctions
     */
    public void setListeIdsExtsFonctions(List<String> listeIdsExtsFonctions) {
        this.listeIdsExtsFonctions = listeIdsExtsFonctions;
    }

}

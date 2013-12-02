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
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class RessourceDto implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -8113007851737757246L;

    /**
     * Identifiant de la ressource.
     */
    private Long id;

    /**
     * Identifiant extérieur de la ressource.
     */
    private String idExt;

    /**
     * Prénom de la ressource.
     */
    private String prenom;

    /**
     * Nom de la ressource.
     */
    private String nom;

    /**
     * Agence de la ressource.
     */
    private IdentifiantLibelleDto agence;

    /**
     * Etat de la ressource.
     */
    private IdentifiantLibelleDto etat;

    /**
     * Fonction de la ressource.
     */
    private IdentifiantLibelleDto fonction;

    /**
     * Service de la ressource.
     */
    private IdentifiantLibelleDto service;

    /**
     * L'email de la ressource.
     */
    private String email;

    /**
     * Constructeur de .
     */
    public RessourceDto() {

    }

    /**
     * Constructeur.
     * @param identifiant identifiant
     */
    public RessourceDto(Long identifiant) {
        this.id = identifiant;
    }

    /**
     * Constructeur.
     * @param identifiant l'identifiant
     * @param nom le nom
     * @param prenom le prénom
     */
    public RessourceDto(Long identifiant, String nom, String prenom) {
        this.id = identifiant;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Retourne la valeur de id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie la valeur de id.
     * @param identifiant the id to set
     */
    public void setId(Long identifiant) {
        this.id = identifiant;
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
     * Retourne la valeur de l'agence.
     * @return the agence
     */
    public IdentifiantLibelleDto getAgence() {
        return agence;
    }

    /**Set the agence.
     * @param agence the agence to set
     */
    public void setAgence(IdentifiantLibelleDto agence) {
        this.agence = agence;
    }

    /**Get the Etat.
     * Retourne la valeur de l'état
     * @return the etat
     */
    public IdentifiantLibelleDto getEtat() {
        return etat;
    }

    /**Set the Etat.
     * @param etat the etat to set
     */
    public void setEtat(IdentifiantLibelleDto etat) {
        this.etat = etat;
    }

    /**Get the Fonction.
     * Retourne la valeur de la fonction
     * @return the fonction
     */
    public IdentifiantLibelleDto getFonction() {
        return fonction;
    }

    /**Set the Fonction.
     * @param fonction the fonction to set
     */
    public void setFonction(IdentifiantLibelleDto fonction) {
        this.fonction = fonction;
    }

    /**Get the service.
     * Retourne la valeur du service
     * @return the service
     */
    public IdentifiantLibelleDto getService() {
        return service;
    }

    /**Set the service.
     * @param service the service to set
     */
    public void setService(IdentifiantLibelleDto service) {
        this.service = service;
    }

    /**
     * Get the email value.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email value.
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Récupère la valeur de idExt.
     * @return la valeur de idExt
     */
    public String getIdExt() {
        return idExt;
    }

    /**
     * Définit la valeur de idExt.
     * @param idExt la nouvelle valeur de idExt
     */
    public void setIdExt(String idExt) {
        this.idExt = idExt;
    }

}

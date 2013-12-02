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
package com.square.composant.emails.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO GWT représentant les données d'un utilisateur.
 * @author nnadeau - SCUB
 */
public class UtilisateurSimpleModel implements IsSerializable {

    /** Identifiant. */
    private Long id;

    /** Login.*/
    private String login;

    /** Nom. */
    private String nom;

    /** Prénom. */
    private String prenom;

    /** Email. */
    private String email;

    /**
     * Get the id value.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id value.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the login value.
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the login value.
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get the nom value.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the nom value.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get the prenom value.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Set the prenom value.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
}

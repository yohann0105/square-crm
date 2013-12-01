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
 * Objet contenant les critères de recherche sur les ressources.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class RessourceCriteresRechercheModel implements IsSerializable {

    /** Nom du client. */
    private String nom;

    /** Prénom du client. */
    private String prenom;

    /** Agence qui traite le client. */
    List<Long> idAgences;

    /** Service client. */
    List<Long> idServices;

    /** Foction du client. */
    List<Long> idFonctions;

    /** Etat du client. */
    List<Long> idEtats;

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
     * Getter function.
     * @return the idAgences
     */
    public List<Long> getIdAgences() {
        return idAgences;
    }

    /**
     * Setter function.
     * @param idAgences the idAgences to set
     */
    public void setIdAgences(List<Long> idAgences) {
        this.idAgences = idAgences;
    }

    /**
     * @return the idServices
     */
    public List<Long> getIdServices() {
        return idServices;
    }

    /**
     * @param idServices the idServices to set
     */
    public void setIdServices(List<Long> idServices) {
        this.idServices = idServices;
    }

    /**
     * @return the idFonctions
     */
    public List<Long> getIdFonctions() {
        return idFonctions;
    }

    /**
     * @param idFonctions the idFonctions to set
     */
    public void setIdFonctions(List<Long> idFonctions) {
        this.idFonctions = idFonctions;
    }

    /**
     * @return the idEtats
     */
    public List<Long> getIdEtats() {
        return idEtats;
    }

    /**
     * @param idEtats the idEtats to set
     */
    public void setIdEtats(List<Long> idEtats) {
        this.idEtats = idEtats;
    }
}

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
 * Dto pour les résultats de la recherche de ressource.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class RessourceModel implements IsSerializable {

    /** Identifiant du client. */
    private Long id;

    /** Nom du client. */
    private String nom;

    /** Prénom du client. */
    private String prenom;

    /** Agence. */
    private IdentifiantLibelleGwt agence;

    /** Agence. */
    private IdentifiantLibelleGwt fonction;

    /** Agence. */
    private IdentifiantLibelleGwt service;

    /** Agence. */
    private IdentifiantLibelleGwt etat;

    /**
     * Renvoi la valeur de id.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
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
     * Revoi la valeur de agence.
     * @return agence
     */
    public IdentifiantLibelleGwt getAgence() {
        return agence;
    }

    /**
     * Modifie agence.
     * @param agence dans agence
     */
    public void setAgence(IdentifiantLibelleGwt agence) {
        this.agence = agence;
    }

    /**
     * @return the fonction
     */
    public IdentifiantLibelleGwt getFonction() {
        return fonction;
    }

    /**
     * @param fonction the fonction to set
     */
    public void setFonction(IdentifiantLibelleGwt fonction) {
        this.fonction = fonction;
    }

    /**
     * @return the service
     */
    public IdentifiantLibelleGwt getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(IdentifiantLibelleGwt service) {
        this.service = service;
    }

    /**
     * @return the etat
     */
    public IdentifiantLibelleGwt getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(IdentifiantLibelleGwt etat) {
        this.etat = etat;
    }

}

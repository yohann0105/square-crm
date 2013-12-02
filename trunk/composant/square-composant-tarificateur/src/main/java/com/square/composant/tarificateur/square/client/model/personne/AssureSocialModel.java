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
package com.square.composant.tarificateur.square.client.model.personne;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model modélisant un assuré social.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class AssureSocialModel implements IsSerializable {

    /** Identifiant de l'assure social. */
    private Long id;

    /** EID de la personne (Square). */
    private Long eidPersonne;

    /** Civilité. */
    private IdentifiantLibelleGwt civilite;

    /** Nom. */
    private String nom;

    /** Prénom. */
    private String prenom;

    /** Numéro de client. */
    private String numClient;

    /** Date de naissance. */
    private String dateNaissance;

    /** Info santé. */
    private InfoSanteModel infoSante;

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
     * Get the dateNaissance value.
     * @return the dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Set the dateNaissance value.
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Get the infoSante value.
     * @return the infoSante
     */
    public InfoSanteModel getInfoSante() {
        return infoSante;
    }

    /**
     * Set the infoSante value.
     * @param infoSante the infoSante to set
     */
    public void setInfoSante(InfoSanteModel infoSante) {
        this.infoSante = infoSante;
    }

    /**
     * Get the civilite value.
     * @return the civilite
     */
    public IdentifiantLibelleGwt getCivilite() {
        return civilite;
    }

    /**
     * Set the civilite value.
     * @param civilite the civilite to set
     */
    public void setCivilite(IdentifiantLibelleGwt civilite) {
        this.civilite = civilite;
    }

    /**
     * Get the eidPersonne value.
     * @return the eidPersonne
     */
    public Long getEidPersonne() {
        return eidPersonne;
    }

    /**
     * Set the eidPersonne value.
     * @param eidPersonne the eidPersonne to set
     */
    public void setEidPersonne(Long eidPersonne) {
        this.eidPersonne = eidPersonne;
    }

    /**
     * Retourne la valeur de numClient.
     * @return the numClient
     */
    public String getNumClient() {
        return numClient;
    }

    /**
     * Modifie la valeur de numClient.
     * @param numClient the numClient to set
     */
    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

}

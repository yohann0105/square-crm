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
 * Dto pour les résultats de la recherche de personnes physique.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class PersonneSimpleModel implements IsSerializable {

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

    /** Date de naissance. */
    private String age;

    /**
     * Situation familiale.
     */
    private String situationFamiliale;

    /** Agence. */
    private IdentifiantLibelleGwt agence;

    /** Commercial. */
    private DimensionRessourceModel commercial;

    /** Code postal - Commune. */
    private CodePostalCommuneModel codePostalCommune;

    /** Nature. */
    private IdentifiantLibelleGwt nature;

    /** Segment. */
    private IdentifiantLibelleGwt segment;

    /** Indique si la personne est un doublon. */
    private boolean doublon;

    /** Civilité. */
    private IdentifiantLibelleGwt civilite;

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
     * Renvoi la valeur de numeroClient.
     * @return numeroClient
     */
    public String getNumeroClient() {
        return numeroClient;
    }

    /**
     * Modifie numeroClient.
     * @param numeroClient la nouvelle valeur de numeroClient
     */
    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
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
     * Renvoi la valeur de nature.
     * @return nature
     */
    public IdentifiantLibelleGwt getNature() {
        return nature;
    }

    /**
     * Modifie nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(IdentifiantLibelleGwt nature) {
        this.nature = nature;
    }

    /**
     * Renvoi la valeur de segment.
     * @return segment
     */
    public IdentifiantLibelleGwt getSegment() {
        return segment;
    }

    /**
     * Modifie segment.
     * @param segment la nouvelle valeur de segment
     */
    public void setSegment(IdentifiantLibelleGwt segment) {
        this.segment = segment;
    }

    /**
     * Renvoi la valeur de doublon.
     * @return doublon
     */
    public boolean isDoublon() {
        return doublon;
    }

    /**
     * Modifie doublon.
     * @param doublon la nouvelle valeur de doublon
     */
    public void setDoublon(boolean doublon) {
        this.doublon = doublon;
    }

    /**
     * Renvoi la valeur de dateNaissance.
     * @return dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Modifie dateNaissance.
     * @param dateNaissance la nouvelle valeur de dateNaissance
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Renvoi la valeur de commercial.
     * @return commercial
     */
    public DimensionRessourceModel getCommercial() {
        return commercial;
    }

    /**
     * Modifie commercial.
     * @param commercial la nouvelle valeur de commercial
     */
    public void setCommercial(DimensionRessourceModel commercial) {
        this.commercial = commercial;
    }

    /**
     * Récupération de la civilité.
     * @return the civilite
     */
    public IdentifiantLibelleGwt getCivilite() {
        return civilite;
    }

    /**
     * Définition de la civilité.
     * @param civilite the civilite to set
     */
    public void setCivilite(IdentifiantLibelleGwt civilite) {
        this.civilite = civilite;
    }

    /**
     * Renvoi la valeur de situationFamiliale.
     * @return situationFamiliale
     */
    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    /**
     * Modifie situationFamiliale.
     * @param situationFamiliale la nouvelle valeur de situationFamiliale
     */
    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
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
     * Retourne la valeur de age.
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * Modifie la valeur de age.
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

	/**
	 * Récupère la valeur codePostalCommune.
	 * @return the codePostalCommune
	 */
	public CodePostalCommuneModel getCodePostalCommune() {
		return codePostalCommune;
	}

	/**
	 * Définit la valeur de codePostalCommune.
	 * @param codePostalCommune the codePostalCommune to set
	 */
	public void setCodePostalCommune(CodePostalCommuneModel codePostalCommune) {
		this.codePostalCommune = codePostalCommune;
	}

}

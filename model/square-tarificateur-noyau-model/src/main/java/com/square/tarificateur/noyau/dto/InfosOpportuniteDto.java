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
package com.square.tarificateur.noyau.dto;

import java.io.Serializable;

import com.square.tarificateur.noyau.dto.personne.PersonneTarificateurDto;

/**
 * DTO encapsulant les informations nécessaires à la création/récupération d'une opportunité.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class InfosOpportuniteDto implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = -2999060739680470133L;

    /** Identifiant externe de l'opportunité (Square). */
    private Long eidOpportunite;

    /** Identifiant externe du créateur de l'opportunité. */
    private Long eidCreateur;

    /** Personne ciblée par l'opportunité. */
    private PersonneTarificateurDto personne;

    /** Origine site internet pour l'ajout de devis. */
    private Boolean origineSiteWeb = Boolean.FALSE;

    /** EId de la relation dans le cadre d'un parrainage. */
    private Long eidRelationParrain;

    /** Flag indiquant de créer un premier devis lors de la création de l'opportunité. */
    private Boolean creerPremierDevis = Boolean.TRUE;

    /** Code campagne. */
    private String codeCampagne;

    /**
     * Getter function.
     * @return the eidOpportunite
     */
    public Long getEidOpportunite() {
        return eidOpportunite;
    }

    /**
     * Setter function.
     * @param eidOpportunite the eidOpportunite to set
     */
    public void setEidOpportunite(Long eidOpportunite) {
        this.eidOpportunite = eidOpportunite;
    }

    /**
     * Getter function.
     * @return the eidCreateur
     */
    public Long getEidCreateur() {
        return eidCreateur;
    }

    /**
     * Setter function.
     * @param eidCreateur the eidCreateur to set
     */
    public void setEidCreateur(Long eidCreateur) {
        this.eidCreateur = eidCreateur;
    }

    /**
     * Recuperer la valeur.
     * @return the origineSiteWeb
     */
    public Boolean getOrigineSiteWeb() {
        return origineSiteWeb;
    }

    /**
     * Fixer la valeur.
     * @param origineSiteWeb the origineSiteWeb to set
     */
    public void setOrigineSiteWeb(Boolean origineSiteWeb) {
        this.origineSiteWeb = origineSiteWeb;
    }

    /**
     * Get the eidRelationParrain value.
     * @return the eidRelationParrain
     */
    public Long getEidRelationParrain() {
        return eidRelationParrain;
    }

    /**
     * Set the eidRelationParrain value.
     * @param eidRelationParrain the eidRelationParrain to set
     */
    public void setEidRelationParrain(Long eidRelationParrain) {
        this.eidRelationParrain = eidRelationParrain;
    }

    /**
     * Récupère la valeur de creerPremierDevis.
     * @return la valeur de creerPremierDevis
     */
    public Boolean getCreerPremierDevis() {
        return creerPremierDevis;
    }

    /**
     * Définit la valeur de creerPremierDevis.
     * @param creerPremierDevis la nouvelle valeur de creerPremierDevis
     */
    public void setCreerPremierDevis(Boolean creerPremierDevis) {
        this.creerPremierDevis = creerPremierDevis;
    }

    /**
     * Récupère la valeur de personne.
     * @return la valeur de personne
     */
    public PersonneTarificateurDto getPersonne() {
        return personne;
    }

    /**
     * Définit la valeur de personne.
     * @param personne la nouvelle valeur de personne
     */
    public void setPersonne(PersonneTarificateurDto personne) {
        this.personne = personne;
    }

	/**
	 * Récupère la valeur de codeCampagne.
	 * @return la valeur de codeCampagne
	 */
	public String getCodeCampagne() {
		return codeCampagne;
	}

	/**
	 * Définit la valeur de codeCampagne.
	 * @param codeCampagne la nouvelle valeur de codeCampagne
	 */
	public void setCodeCampagne(String codeCampagne) {
		this.codeCampagne = codeCampagne;
	}
}

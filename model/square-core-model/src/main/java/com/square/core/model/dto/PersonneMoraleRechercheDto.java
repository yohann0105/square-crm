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
 * Dto permettant de récupérer les informations d'une personne moral pour la recherche.
 * @author cblanchard - SCUB
 * */
public class PersonneMoraleRechercheDto implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 6943186586083351L;

	/**
	 * Identifiant de l'entreprise.
	 */
	private Long id;

	/**
	 * Numéro de l'entrepise.
	 */
	private String numeroEntreprise;

	/**
	 * Raison sociale.
	 */
	private String raisonSociale;

	/**
	 * Enseigne commerciale.
	 */
	private String enseigneCommercial;

	/**
	 * Nature.
	 */
	private IdentifiantLibelleDto nature;

	/**
	 * Code postal - Commune.
	 */
	private CodePostalCommuneDto codePostalCommune;

	/**
	 * L'agence.
	 */
	private IdentifiantLibelleDto agence;

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
	 * Renvoi la valeur de numeroEntreprise.
	 * @return numeroEntreprise
	 */
	public String getNumeroEntreprise() {
		return numeroEntreprise;
	}

	/**
	 * Modifie numeroEntreprise.
	 * @param numeroEntreprise la nouvelle valeur de numeroEntreprise
	 */
	public void setNumeroEntreprise(String numeroEntreprise) {
		this.numeroEntreprise = numeroEntreprise;
	}

	/**
	 * Renvoi la valeur de raisonSociale.
	 * @return raisonSociale
	 */
	public String getRaisonSociale() {
		return raisonSociale;
	}

	/**
	 * Modifie raisonSociale.
	 * @param raisonSociale la nouvelle valeur de raisonSociale
	 */
	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	/**
	 * Renvoi la valeur de nature.
	 * @return nature
	 */
	public IdentifiantLibelleDto getNature() {
		return nature;
	}

	/**
	 * Modifie nature.
	 * @param nature la nouvelle valeur de nature
	 */
	public void setNature(IdentifiantLibelleDto nature) {
		this.nature = nature;
	}

    /**
     * Récupération de enseigneCommercial.
     * @return the enseigneCommercial
     */
    public String getEnseigneCommercial() {
        return enseigneCommercial;
    }

    /**
     * Définition de enseigneCommercial.
     * @param enseigneCommercial the enseigneCommercial to set
     */
    public void setEnseigneCommercial(String enseigneCommercial) {
        this.enseigneCommercial = enseigneCommercial;
    }

    /**
     * Récupération de agence.
     * @return the agence
     */
    public IdentifiantLibelleDto getAgence() {
        return agence;
    }

    /**
     * Définition de agence.
     * @param agence the agence to set
     */
    public void setAgence(IdentifiantLibelleDto agence) {
        this.agence = agence;
    }

	/**
	 * Récupère la valeur codePostalCommune.
	 * @return the codePostalCommune
	 */
	public CodePostalCommuneDto getCodePostalCommune() {
		return codePostalCommune;
	}

	/**
	 * Définit la valeur de codePostalCommune.
	 * @param codePostalCommune the codePostalCommune to set
	 */
	public void setCodePostalCommune(CodePostalCommuneDto codePostalCommune) {
		this.codePostalCommune = codePostalCommune;
	}
}

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

import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Un objet conténant une personne morale.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonneMoraleDto extends PersonneBaseDto {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 8489814727480159684L;

	/**
	 * L'identifiant de l'entreprise.
	 */
	private Long id;

	/**
	 * La raison sociale.
	 */
	private String raisonSociale;

	/**
	 * Le numéro d'entreprise.
	 */
	private String numEntreprise;

	/**
	 * L'enseigne commercial.
	 */
	private String enseigneCommercial;

	/**
	 * La nature.
	 */
	private IdentifiantLibelleDto nature;

	/**
	 * La forme juridique.
	 */
	private IdentifiantLibelleDto formeJuridique;

	/**
	 * Numéro siret.
	 */
	private String numSiret;

	/**
	 * NAF.
	 */
	private String naf;

	/**
	 * La date de création.
	 */
	private Calendar dateCreation;

	/**
	 * Le commercial.
	 */
	private DimensionRessourceDto commercial;

	/**
	 * L'agence.
	 */
	private IdentifiantLibelleDto agence;

	/**
	 * Le créateur.
	 */
	private DimensionRessourceDto createur;

	/**
	 * Retourne la valeur de id.
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Modifie la valeur de id.
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retourne la valeur de numEntreprise.
	 * @return the numEntreprise
	 */
	public String getNumEntreprise() {
		return numEntreprise;
	}

	/**
	 * Modifie la valeur de numEntreprise.
	 * @param numEntreprise the numEntreprise to set
	 */
	public void setNumEntreprise(String numEntreprise) {
		this.numEntreprise = numEntreprise;
	}

	/**
	 * Retourne la valeur de enseigneCommercial.
	 * @return the enseigneCommercial
	 */
	public String getEnseigneCommercial() {
		return enseigneCommercial;
	}

	/**
	 * Modifie la valeur de enseigneCommercial.
	 * @param enseigneCommercial the enseigneCommercial to set
	 */
	public void setEnseigneCommercial(String enseigneCommercial) {
		this.enseigneCommercial = enseigneCommercial;
	}

	/**
	 * Retourne la valeur de nature.
	 * @return the nature
	 */
	public IdentifiantLibelleDto getNature() {
		return nature;
	}

	/**
	 * Modifie la valeur de nature.
	 * @param nature the nature to set
	 */
	public void setNature(IdentifiantLibelleDto nature) {
		this.nature = nature;
	}

	/**
	 * Retourne la valeur de formeJuridique.
	 * @return the formeJuridique
	 */
	public IdentifiantLibelleDto getFormeJuridique() {
		return formeJuridique;
	}

	/**
	 * Modifie la valeur de formeJuridique.
	 * @param formeJuridique the formeJuridique to set
	 */
	public void setFormeJuridique(IdentifiantLibelleDto formeJuridique) {
		this.formeJuridique = formeJuridique;
	}

	/**
	 * Retourne la valeur de numSiret.
	 * @return the numSiret
	 */
	public String getNumSiret() {
		return numSiret;
	}

	/**
	 * Modifie la valeur de numSiret.
	 * @param numSiret the numSiret to set
	 */
	public void setNumSiret(String numSiret) {
		this.numSiret = numSiret;
	}

	/**
	 * Retourne la valeur de naf.
	 * @return the naf
	 */
	public String getNaf() {
		return naf;
	}

	/**
	 * Modifie la valeur de naf.
	 * @param naf the naf to set
	 */
	public void setNaf(String naf) {
		this.naf = naf;
	}

	/**
	 * Retourne la valeur de dateCreation.
	 * @return the dateCreation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * Modifie la valeur de dateCreation.
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Retourne la valeur de raisonSociale.
	 * @return the raisonSociale
	 */
	public String getRaisonSociale() {
		return raisonSociale;
	}

	/**
	 * Modifie la valeur de raisonSociale.
	 * @param raisonSociale the raisonSociale to set
	 */
	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	/**
	 * Retourne la valeur de commercial.
	 * @return the commercial
	 */
	public DimensionRessourceDto getCommercial() {
		return commercial;
	}

	/**
	 * Modifie la valeur de commercial.
	 * @param commercial the commercial to set
	 */
	public void setCommercial(DimensionRessourceDto commercial) {
		this.commercial = commercial;
	}

	/**
	 * Retourne la valeur de agence.
	 * @return the agence
	 */
	public IdentifiantLibelleDto getAgence() {
		return agence;
	}

	/**
	 * Modifie la valeur de agence.
	 * @param agence the agence to set
	 */
	public void setAgence(IdentifiantLibelleDto agence) {
		this.agence = agence;
	}

	/**
	 * Retourne la valeur de createur.
	 * @return the createur
	 */
	public DimensionRessourceDto getCreateur() {
		return createur;
	}

	/**
	 * Modifie la valeur de createur.
	 * @param createur the createur to set
	 */
	public void setCreateur(DimensionRessourceDto createur) {
		this.createur = createur;
	}

}


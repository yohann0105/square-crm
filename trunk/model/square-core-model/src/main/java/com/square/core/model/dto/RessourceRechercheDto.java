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
public class RessourceRechercheDto implements Serializable {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = -1951289473069028150L;

	/**
	 * Identifiant de la ressource.
	 */
	private Long id;

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
	 * Constructeur de .
	 */
	public RessourceRechercheDto() {

	}

	/**
	 *
	 * Constructeur.
	 * @param identifiant identifiant
	 */
	public RessourceRechercheDto(Long identifiant) {
		this.id = identifiant;
	}

	/**
	 * Constructeur.
	 * @param identifiant l'identifiant
	 * @param nom le nom
	 * @param prenom le prénom
	 */
	public RessourceRechercheDto(Long identifiant, String nom, String prenom) {
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

	/**Get agence.
	 * @return the agence
	 */
	public IdentifiantLibelleDto getAgence() {
		return agence;
	}

	/**Set agence.
	 * @param agence the agence to set
	 */
	public void setAgence(IdentifiantLibelleDto agence) {
		this.agence = agence;
	}

	/**Get etat.
	 * @return the etat
	 */
	public IdentifiantLibelleDto getEtat() {
		return etat;
	}

	/**Set etat.
	 * @param etat the etat to set
	 */
	public void setEtat(IdentifiantLibelleDto etat) {
		this.etat = etat;
	}

	/**Get fonction.
	 * @return the fonction
	 */
	public IdentifiantLibelleDto getFonction() {
		return fonction;
	}

	/**Set fonction.
	 * @param fonction the fonction to set
	 */
	public void setFonction(IdentifiantLibelleDto fonction) {
		this.fonction = fonction;
	}

	/**Get service.
	 * @return the service
	 */
	public IdentifiantLibelleDto getService() {
		return service;
	}

	/**Set service.
	 * @param service the service to set
	 */
	public void setService(IdentifiantLibelleDto service) {
		this.service = service;
	}

}

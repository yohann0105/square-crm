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
 * Objet contenant les critères de recherche sur les prestations.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class PersonnePrestationCriteresRechercheModel implements IsSerializable {
	/**
	 * Operateur egale.
	 */
	public static final int OPERATEUR_EGALE = 0;

	/**
	 * Operateur superieure.
	 */
	public static final int OPERATEUR_SUP = 1;

	/**
	 * Operateur inferieure.
	 */
	public static final int OPERATEUR_INF = 2;

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = -600317715897289444L;

	/**
	 * Numéro d'identifiation du bénéficiare.
	 * */
	private Long idBeneficiaire;

	/**
	 * Numero de l'assure.
	 */
	private Long idAssure;

	/**
	 * N Décomptes.
	 * */
	private String numero;

	/**
	 * opérateur de comparaison >, < ou =.
	 */
	private String dateDebutDesSoinsOperateur;

	/**
	 * Date de debut des Soins.
	 */
	private String dateDebutDesSoins;

	/**
	 * @return the idBeneficiaire
	 */
	public Long getIdBeneficiaire() {
		return idBeneficiaire;
	}

	/**
	 * @param idBeneficiaire the idBeneficiaire to set
	 */
	public void setIdBeneficiaire(Long idBeneficiaire) {
		this.idBeneficiaire = idBeneficiaire;
	}

	/**
	 * @return the idAssure
	 */
	public Long getIdAssure() {
		return idAssure;
	}

	/**
	 * @param idAssure the idAssure to set
	 */
	public void setIdAssure(Long idAssure) {
		this.idAssure = idAssure;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the dateDebutDesSoinsOperateur
	 */
	public String getDateDebutDesSoinsOperateur() {
		return dateDebutDesSoinsOperateur;
	}

	/**
	 * @param dateDebutDesSoinsOperateur the dateDebutDesSoinsOperateur to set
	 */
	public void setDateDebutDesSoinsOperateur(String dateDebutDesSoinsOperateur) {
		this.dateDebutDesSoinsOperateur = dateDebutDesSoinsOperateur;
	}

	/**
	 * @return the dateDebutDesSoins
	 */
	public String getDateDebutDesSoins() {
		return dateDebutDesSoins;
	}

	/**
	 * @param dateDebutDesSoins the dateDebutDesSoins to set
	 */
	public void setDateDebutDesSoins(String dateDebutDesSoins) {
		this.dateDebutDesSoins = dateDebutDesSoins;
	}

}

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

/**
 * Critères de recherche pour la table de dimension Departement.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class DimensionCritereRechercheDepartementDto implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -725326051876222999L;

	/**
	 * Criteres de recherche sur les dimensions.
	 * */
	private DimensionCriteresRechercheDto dimensionCriteres;

	/**
	 * Commune.
	 */
	private Long idCommune;

	/**
	 * Code postal.
	 */
	private Long idCodePostal;

	/**
	 * Constructeur.
	 */
	public DimensionCritereRechercheDepartementDto() {
	}

	/**
	 * Constructeur.
	 * @param dimensionCriteres les critères génériques
	 */
	public DimensionCritereRechercheDepartementDto(DimensionCriteresRechercheDto dimensionCriteres) {
		this.dimensionCriteres = dimensionCriteres;
	}

	/**
	 * Retourne la valeur de dimensionCriteres.
	 * @return the dimensionCriteres
	 */
	public DimensionCriteresRechercheDto getDimensionCriteres() {
		return dimensionCriteres;
	}

	/**
	 * Modifie la valeur de dimensionCriteres.
	 * @param dimensionCriteres the dimensionCriteres to set
	 */
	public void setDimensionCriteres(DimensionCriteresRechercheDto dimensionCriteres) {
		this.dimensionCriteres = dimensionCriteres;
	}

	/**
	 * Retourne la valeur de idCommune.
	 * @return the idCommune
	 */
	public Long getIdCommune() {
		return idCommune;
	}

	/**
	 * Modifie la valeur de idCommune.
	 * @param idCommune the idCommune to set
	 */
	public void setIdCommune(Long idCommune) {
		this.idCommune = idCommune;
	}

	/**
	 * Recuperer la valeur.
	 * @return the idCodePostal
	 */
	public Long getIdCodePostal() {
		return idCodePostal;
	}

	/**
	 * Fixer la valeur.
	 * @param idCodePostal the idCodePostal to set
	 */
	public void setIdCodePostal(Long idCodePostal) {
		this.idCodePostal = idCodePostal;
	}
}

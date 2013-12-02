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
 * Objet contenant le critère de recherche de l'objet d'une action.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class DimensionCritereRechercheObjetDto implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 8805298914265200236L;

	/**
	 * Criteres de recherche sur les dimensions.
	 * */
	private DimensionCriteresRechercheDto dimensionCriteres;

	/**
	 * L'identifiant du type de l'action.
	 */
	private Long idType;

	/**
	 * Constructeur.
	 */
	public DimensionCritereRechercheObjetDto() {
	}

	/**
	 * Constructeur.
	 * @param dimensionCriteres les critères génériques
	 */
	public DimensionCritereRechercheObjetDto(DimensionCriteresRechercheDto dimensionCriteres) {
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
	 * Retourne la valeur de idType.
	 * @return the idType
	 */
	public Long getIdType() {
		return idType;
	}

	/**
	 * Modifie la valeur de idType.
	 * @param idType the idType to set
	 */
	public void setIdType(Long idType) {
		this.idType = idType;
	}

}

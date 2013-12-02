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
 * Objet contenant le critère de recherche du sous objet d'une action.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class DimensionCritereRechercheSousObjetDto implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 322288270438832757L;

	/**
	 * Criteres de recherche sur les dimensions.
	 * */
	private DimensionCriteresRechercheDto dimensionCriteres;

	/**
	 * L'identifiant de l'objet de l'action.
	 */
	private Long idObjet;

	/**
	 * Constructeur.
	 */
	public DimensionCritereRechercheSousObjetDto() {
	}

	/**
	 * Constructeur.
	 * @param dimensionCriteres les critères génériques
	 */
	public DimensionCritereRechercheSousObjetDto(DimensionCriteresRechercheDto dimensionCriteres) {
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
	 * Retourne la valeur de idObjet.
	 * @return the idObjet
	 */
	public Long getIdObjet() {
		return idObjet;
	}

	/**
	 * Modifie la valeur de idObjet.
	 * @param idObjet the idObjet to set
	 */
	public void setIdObjet(Long idObjet) {
		this.idObjet = idObjet;
	}

}

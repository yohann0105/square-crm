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
 * Dto contenant les critères de recherche pour le résultat des actions.
 * @author cblanchard - SCUB
 */
public class DimensionCritereRechercheResultatActionDto implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 3440758244987766543L;

	/** Criteres de recherche sur les dimensions. */
	private DimensionCriteresRechercheDto dimensionCriteres;

	/** Critere pour filtrer la recherche sur les résultat opportunité. */
	private Boolean recuperationOpportunite;

	/** Constructeur par defaut. */
	public DimensionCritereRechercheResultatActionDto() {
		super();
	}

	/**
	 * Constructeur.
	 * @param dimensionCriteres les critères générique de recherche
	 */
	public DimensionCritereRechercheResultatActionDto(DimensionCriteresRechercheDto dimensionCriteres) {
		super();
		this.dimensionCriteres = dimensionCriteres;
	}

	/**
	 * Constructeur.
	 * @param dimensionCriteres les critères générique de recherche
	 * @param recuperationOpportunite boolean permettant de spécifier si on veut remonter l'opportunité
	 */
	public DimensionCritereRechercheResultatActionDto(DimensionCriteresRechercheDto dimensionCriteres, Boolean recuperationOpportunite) {
		super();
		this.dimensionCriteres = dimensionCriteres;
		this.recuperationOpportunite = recuperationOpportunite;
	}

	/**
	 * Renvoi la valeur de dimensionCriteres.
	 * @return dimensionCriteres
	 */
	public DimensionCriteresRechercheDto getDimensionCriteres() {
		return dimensionCriteres;
	}

	/**
	 * Modifie dimensionCriteres.
	 * @param dimensionCriteres la nouvelle valeur de dimensionCriteres
	 */
	public void setDimensionCriteres(DimensionCriteresRechercheDto dimensionCriteres) {
		this.dimensionCriteres = dimensionCriteres;
	}

	/**
	 * Renvoi la valeur de recuperationOpportunite.
	 * @return recuperationOpportunite
	 */
	public Boolean getRecuperationOpportunite() {
		return recuperationOpportunite;
	}

	/**
	 * Modifie recuperationOpportunite.
	 * @param recuperationOpportunite la nouvelle valeur de recuperationOpportunite
	 */
	public void setRecuperationOpportunite(Boolean recuperationOpportunite) {
		this.recuperationOpportunite = recuperationOpportunite;
	}

}

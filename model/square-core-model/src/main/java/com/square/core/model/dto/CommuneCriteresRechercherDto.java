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
 * Criteres de recherche pour les communes.
 * @author cblanchard - SCUB
 * */
public class CommuneCriteresRechercherDto implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Criteres de recherche sur les dimensions.
	 * */
	private DimensionCriteresRechercheDto dimensionCriteres;

	/**
	 * Critères sur le codePostal.
	 * */
	private Long idCodePostal;

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
	 * Renvoi la valeur de idCodePostal.
	 * @return idCodePostal
	 */
	public Long getIdCodePostal() {
		return idCodePostal;
	}

	/**
	 * Modifie idCodePostal.
	 * @param idCodePostal la nouvelle valeur de idCodePostal
	 */
	public void setIdCodePostal(Long idCodePostal) {
		this.idCodePostal = idCodePostal;
	}

}

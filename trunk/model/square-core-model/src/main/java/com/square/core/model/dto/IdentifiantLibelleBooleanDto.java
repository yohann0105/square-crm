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
/**
 * 
 */
package com.square.core.model.dto;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Identifiant Libelle Dto specifique pour la gestion des types de dimension complétés de booléens.
 * @author Benoit Thomas (benoit.thomas@scub.net) - SCUB
 */
public class IdentifiantLibelleBooleanDto extends IdentifiantLibelleDto
{
	/**
	 * SVUID.
	 */
	private static final long serialVersionUID = -3400235732055168917L;
	/**
	 * Libelle Inverse.
	 */
	private Boolean bool;

	/**
	 * Constructeur par défaut.
	 */
	public IdentifiantLibelleBooleanDto() {
		super();
	}

	/**
	 * Constructeur d'initialisation.
	 * @param id identifiant
	 * @param lib libelle
	 */
	public IdentifiantLibelleBooleanDto(Long id, String lib) {
		super(id, lib);
	}

	/**
	 * Constructeur d'initialisation.
	 * @param id identifiant
	 */
	public IdentifiantLibelleBooleanDto(Long id) {
		super(id);
	}

	/**
	 * Constructeur d'initialisation.
	 * @param id identifiant
	 * @param lib libelle
	 * @param bool booléen
	 */
	public IdentifiantLibelleBooleanDto(Long id, String lib, Boolean bool) {
		super(id, lib);
		this.bool = bool;
	}

	/**
	 * Renvoie la valeur de bool.
	 * @return bool
	 */
	public Boolean getBool() {
		return bool;
	}
	/**
	 * Modifie bool.
	 * @param bool la nouvelle valeur de bool
	 */
	public void setBool(Boolean bool) {
		this.bool = bool;
	}
}

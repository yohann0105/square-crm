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
 * Dto permettant de regrouper les communes et les codes postaux.
 * @author cblanchard - SCUB
 */
public class IdentifiantLibelleCodePostalCommuneDto implements Serializable {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = -3716048212193299173L;

	/**
	 * Identifiant du code postal.
	 * */
	private Long idCodePostal;

	/**
	 * Identifiant du département.
	 */
	private Long idCommune;

	/**
	 * Libelle du code postal.
	 */
	private String libelleCodePostal;

	/**
	 * Libelle de la commune.
	 */
	private String libelleCommune;

	/**
	 * Constructeur par défaut.
	 */
	public IdentifiantLibelleCodePostalCommuneDto() {
	}

	/**
	 * Constructeur complet.
	 * @param idCodePostal l'id du code postal
	 * @param idCommune l'id de la commune
	 * @param libelleCodePostal le libelle du code postal
	 * @param libelleCommune le libelle de la commune
	 */
	public IdentifiantLibelleCodePostalCommuneDto(Long idCodePostal, Long idCommune, String libelleCodePostal, String libelleCommune) {
		super();
		this.idCodePostal = idCodePostal;
		this.idCommune = idCommune;
		this.libelleCodePostal = libelleCodePostal;
		this.libelleCommune = libelleCommune;
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

	/**
	 * Renvoi la valeur de idCommune.
	 * @return idCommune
	 */
	public Long getIdCommune() {
		return idCommune;
	}

	/**
	 * Modifie idCommune.
	 * @param idCommune la nouvelle valeur de idCommune
	 */
	public void setIdCommune(Long idCommune) {
		this.idCommune = idCommune;
	}

	/**
	 * Renvoi la valeur de libelleCodePostal.
	 * @return libelleCodePostal
	 */
	public String getLibelleCodePostal() {
		return libelleCodePostal;
	}

	/**
	 * Modifie libelleCodePostal.
	 * @param libelleCodePostal la nouvelle valeur de libelleCodePostal
	 */
	public void setLibelleCodePostal(String libelleCodePostal) {
		this.libelleCodePostal = libelleCodePostal;
	}

	/**
	 * Renvoi la valeur de libelleCommune.
	 * @return libelleCommune
	 */
	public String getLibelleCommune() {
		return libelleCommune;
	}

	/**
	 * Modifie libelleCommune.
	 * @param libelleCommune la nouvelle valeur de libelleCommune
	 */
	public void setLibelleCommune(String libelleCommune) {
		this.libelleCommune = libelleCommune;
	}

}

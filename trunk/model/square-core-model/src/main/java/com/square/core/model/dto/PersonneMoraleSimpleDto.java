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
 * Un objet contenant une personne morale simple.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonneMoraleSimpleDto implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 6159041346070596683L;

	/**
	 * Le numéro de la personne.
	 */
	private String num;

	/**
	 * La raison sociale de l'entreprise.
	 */
	private String raisonSociale;

	/**
	 * Le numéro de l'entreprise.
	 */
	private String numeroEntreprise;

	/**
	 * La nature de l'entreprise.
	 */
	private IdentifiantLibelleDto nature;

	/**
	 * Retourne la valeur de num.
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * Modifie la valeur de num.
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
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
	 * Retourne la valeur de numeroEntreprise.
	 * @return the numeroEntreprise
	 */
	public String getNumeroEntreprise() {
		return numeroEntreprise;
	}

	/**
	 * Modifie la valeur de numeroEntreprise.
	 * @param numeroEntreprise the numeroEntreprise to set
	 */
	public void setNumeroEntreprise(String numeroEntreprise) {
		this.numeroEntreprise = numeroEntreprise;
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

}


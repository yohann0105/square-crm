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
import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto pour les adresses simple.
 * @author cblanchard - SCUB
 */
public class AdresseSimpleDto implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 8002071070404256341L;

	/**
     * Identifiant extérieur.
     * */
    private String identifiantExterieur;

	/** Le type de l'adresse. */
	private IdentifiantLibelleDto typeAdresse;

	/** Indique si la personne habite à l'adresse indiquée. */
	private boolean npai;

	/** Date de début. */
	private Calendar dateDebut;

	/** Date de fin. */
	private Calendar dateFin;

	/**
	 * Renvoi la valeur de typeAdresse.
	 * @return typeAdresse
	 */
	public IdentifiantLibelleDto getTypeAdresse() {
		return typeAdresse;
	}

	/**
	 * Modifie typeAdresse.
	 * @param typeAdresse la nouvelle valeur de typeAdresse
	 */
	public void setTypeAdresse(IdentifiantLibelleDto typeAdresse) {
		this.typeAdresse = typeAdresse;
	}

	/**
	 * Renvoi la valeur de npai.
	 * @return npai
	 */
	public boolean isNpai() {
		return npai;
	}

	/**
	 * Modifie npai.
	 * @param npai la nouvelle valeur de npai
	 */
	public void setNpai(boolean npai) {
		this.npai = npai;
	}

	/**
	 * Renvoi la valeur de dateDebut.
	 * @return dateDebut
	 */
	public Calendar getDateDebut() {
		return dateDebut;
	}

	/**
	 * Modifie dateDebut.
	 * @param dateDebut la nouvelle valeur de dateDebut
	 */
	public void setDateDebut(Calendar dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Renvoi la valeur de dateFin.
	 * @return dateFin
	 */
	public Calendar getDateFin() {
		return dateFin;
	}

	/**
	 * Modifie dateFin.
	 * @param dateFin la nouvelle valeur de dateFin
	 */
	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
	}

    /**
     * Récupère la valeur de identifiantExterieur.
     * @return la valeur de identifiantExterieur
     */
    public String getIdentifiantExterieur() {
        return identifiantExterieur;
    }

    /**
     * Définit la valeur de identifiantExterieur.
     * @param identifiantExterieur la nouvelle valeur de identifiantExterieur
     */
    public void setIdentifiantExterieur(String identifiantExterieur) {
        this.identifiantExterieur = identifiantExterieur;
    }

}

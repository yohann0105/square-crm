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
 * Dto des relations.
 * @author "Arnaud Brochain (arnaud.brochain@scub.net)" 7 sept. 2012
 *
 */
public class RelationDto implements Serializable {

	/**
	 * SVUID.
	 */
	private static final long serialVersionUID = -6812687554298922051L;

	/**
	 * Identifiant du la relation.
	 */
	private Long id;

	/**
	 * Personne source de la relation.
	 */
	private Long idPersonnePrincipale;

	/**
	 * Personne source de la relation.
	 */
	private Long idPersonne;

	/**
	 * Date de début de la relation.
	 */
	private Calendar dateDebut;

	/**
	 * Date de fin de la relation.
	 */
	private Calendar dateFin;

	/**
	 * Type de la relation.
	 */
	private IdentifiantLibelleDto type;

	/**
	 * Type de la relation.
	 */
	private IdentifiantLibelleDto ancienType;

	/**
	 * Groupement de la relation.
	 */
	private IdentifiantLibelleDto groupement;

	/**
	 * Renvoi la valeur de id.
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Modifie id.
	 * @param id la nouvelle valeur de id
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * Renvoi la valeur de idPersonnePrincipale.
	 * @return idPersonnePrincipale
	 */
	public Long getIdPersonnePrincipale() {
		return idPersonnePrincipale;
	}

	/**
	 * Modifie idPersonnePrincipale.
	 * @param idPersonnePrincipale la nouvelle valeur de idPersonnePrincipale
	 */
	public void setIdPersonnePrincipale(Long idPersonnePrincipale) {
		this.idPersonnePrincipale = idPersonnePrincipale;
	}
	/**Get the type.
	 * @return the type
	 */
	public IdentifiantLibelleDto getType() {
		return type;
	}

	/**Set the type.
	 * @param type the type to set
	 */
	public void setType(IdentifiantLibelleDto type) {
		this.type = type;
	}

	/**Get groupement.
	 * @return the groupement
	 */
	public IdentifiantLibelleDto getGroupement() {
		return groupement;
	}

	/**Set groupement.
	 * @param groupement the groupement to set
	 */
	public void setGroupement(IdentifiantLibelleDto groupement) {
		this.groupement = groupement;
	}

	/**get id personne.
	 * @return the idPersonne
	 */
	public Long getIdPersonne() {
		return idPersonne;
	}

	/**Set id perosnne.
	 * @param idPersonne the idPersonne to set
	 */
	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	/**
	 * Recuperer la valeur.
	 * @return the ancienType
	 */
	public IdentifiantLibelleDto getAncienType() {
		return ancienType;
	}

	/**
	 * Fixer la valeur.
	 * @param ancienType the ancienType to set
	 */
	public void setAncienType(IdentifiantLibelleDto ancienType) {
		this.ancienType = ancienType;
	}
}

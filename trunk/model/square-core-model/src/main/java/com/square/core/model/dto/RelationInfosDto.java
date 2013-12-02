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
 * Dto pour les relations.
 * @param <TypePersonne> le type de la personne.
 * @author cblanchard - SCUB
 */
public class RelationInfosDto < TypePersonne extends PersonneRelationDto > extends RelationDto implements Serializable {

	private static final long serialVersionUID = 5310817462948269280L;

	/** Le type de personne cible de la relation. */
	private TypePersonne personne;

	/** Flag actif. */
    private boolean actif;

	/**
	 * Retourne le type de personne.
	 * @return the personne
	 */
	public TypePersonne getPersonne() {
		return personne;
	}

	/**
	 * Modifie le type de personne.
	 * @param personne the personne to set
	 */
	public void setPersonne(TypePersonne personne) {
		this.personne = personne;
	}

    /**
     * Get the value of actif.
     * @return the actif
     */
    public boolean isActif() {
        return actif;
    }

    /**
     * Set the value of actif.
     * @param actif the actif to set
     */
    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
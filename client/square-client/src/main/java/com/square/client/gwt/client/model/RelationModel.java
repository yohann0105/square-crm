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
package com.square.client.gwt.client.model;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model des relations.
 * @author cblanchard - SCUB
 */
public class RelationModel implements IsSerializable {

    /** Identifiant du la relation. */
    private Long id;

    /** Personne source de la relation. */
    private Long idPersonnePrincipale;

    /** Personne source de la relation. */
    private Long idPersonne;

    /** Date de début de la relation. */
    private String dateDebut;

    /** Date de fin de la relation. */
    private String dateFin;

    /** Type de la relation. */
    private IdentifiantLibelleGwt type;

    /** Type de la relation. */
    private IdentifiantLibelleGwt ancienType;

    /** Groupement de la relation. */
    private IdentifiantLibelleGwt groupement;

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

    /**
     * Renvoi la valeur de idPersonne.
     * @return idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Modifie idPersonne.
     * @param idPersonne la nouvelle valeur de idPersonne
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    /**
     * Renvoi la valeur de dateDebut.
     * @return dateDebut
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Modifie dateDebut.
     * @param dateDebut la nouvelle valeur de dateDebut
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Renvoi la valeur de dateFin.
     * @return dateFin
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * Modifie dateFin.
     * @param dateFin la nouvelle valeur de dateFin
     */
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Renvoi la valeur de type.
     * @return type
     */
    public IdentifiantLibelleGwt getType() {
        return type;
    }

    /**
     * Modifie type.
     * @param type la nouvelle valeur de type
     */
    public void setType(IdentifiantLibelleGwt type) {
        this.type = type;
    }

    /**
     * Renvoi la valeur de groupement.
     * @return groupement
     */
    public IdentifiantLibelleGwt getGroupement() {
        return groupement;
    }

    /**
     * Modifie groupement.
     * @param groupement la nouvelle valeur de groupement
     */
    public void setGroupement(IdentifiantLibelleGwt groupement) {
        this.groupement = groupement;
    }

	/**
	 * Recuperer la valeur.
	 * @return the ancienType
	 */
	public IdentifiantLibelleGwt getAncienType() {
		return ancienType;
	}

	/**
	 * Fixer la valeur.
	 * @param ancienType the ancienType to set
	 */
	public void setAncienType(IdentifiantLibelleGwt ancienType) {
		this.ancienType = ancienType;
	}
}
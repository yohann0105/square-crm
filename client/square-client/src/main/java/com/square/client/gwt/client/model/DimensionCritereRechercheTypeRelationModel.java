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

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model du critère de recherche pour les types de relations.
 * @author cblanchard - SCUB
 */
public class DimensionCritereRechercheTypeRelationModel implements IsSerializable {

    /**
     * L'identifiant du type de relation à rechercher.
     */
    private Long id;

    /**
     * La visibilité du type de relation.
     */
    private Boolean visible;

    /**
     * Identifiant du type de groupement.
     */
    private List<Long> groupements;

    /**
     * Identifiant du type de groupement.
     */
    private List<Long> pasDansGroupements;

    /** Libelle du type. */
    private String libelle;

    /** Inverse Permet de specivier que l'on souhaite ou pas le relations inverse. **/
    private Boolean inverse = Boolean.FALSE;

    /**
     * Constructeur.
     */
    public DimensionCritereRechercheTypeRelationModel() {
    }

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
     * Renvoi la valeur de visible.
     * @return visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * Modifie visible.
     * @param visible la nouvelle valeur de visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * Filtre groupements.
     * @return the groupements
     */
    public List<Long> getGroupements() {
        return groupements;
    }

    /**
     * Filtre groupements.
     * @param groupements the groupements to set
     */
    public void setGroupements(List<Long> groupements) {
        this.groupements = groupements;
    }

    /**
     * Filtre groupements.
     * @return the pasDansGroupements
     */
    public List<Long> getPasDansGroupements() {
        return pasDansGroupements;
    }

    /**
     * Filtre groupements.
     * @param pasDansGroupements the pasDansGroupements to set
     */
    public void setPasDansGroupements(List<Long> pasDansGroupements) {
        this.pasDansGroupements = pasDansGroupements;
    }

    /**
     * Renvoi la valeur de libelle.
     * @return libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Modifie libelle.
     * @param libelle la nouvelle valeur de libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

	/**
	 * Recuperer la valeur.
	 * @return the inverse
	 */
	public Boolean getInverse() {
		return inverse;
	}

	/**
	 * Fixer la valeur.
	 * @param inverse the inverse to set
	 */
	public void setInverse(Boolean inverse) {
		this.inverse = inverse;
	}
}

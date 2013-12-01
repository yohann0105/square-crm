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
package com.square.composant.tarificateur.square.client.model;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Critères de recherche pour les liens familiaux.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class DimensionCriteresLienFamilialRechercheModel implements IsSerializable {

    /** Libelle. */
    private String libelle;

    /** Liste des types. */
    private List<Long> listeIds;

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
	 * Récupère le listeIds.
	 * @return le listeIds
	 */
	public List<Long> getListeIds() {
		return listeIds;
	}

	/**
	 * Définit le listeIds.
	 * @param listeIds le nouveau listeIds
	 */
	public void setListeIds(List<Long> listeIds) {
		this.listeIds = listeIds;
	}
}

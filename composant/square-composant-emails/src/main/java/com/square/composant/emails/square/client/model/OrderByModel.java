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
package com.square.composant.emails.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * DTO GWT pour définir l'ordre sur une colonne.
 * @author nnadeau - SCUB
 */
public class OrderByModel implements IsSerializable {

    /**
     * Nom de la colonne.
     */
    private String nomColonne;

    /**
     * Demande le tri croissant.
     */
    private Boolean ascending;

    /**
     * Get the nomColonne value.
     * @return the nomColonne
     */
    public String getNomColonne() {
        return nomColonne;
    }

    /**
     * Set the nomColonne value.
     * @param nomColonne the nomColonne to set
     */
    public void setNomColonne(String nomColonne) {
        this.nomColonne = nomColonne;
    }

    /**
     * Get the ascending value.
     * @return the ascending
     */
    public Boolean getAscending() {
        return ascending;
    }

    /**
     * Set the ascending value.
     * @param ascending the ascending to set
     */
    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }
}

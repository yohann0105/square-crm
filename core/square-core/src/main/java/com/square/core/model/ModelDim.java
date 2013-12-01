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
package com.square.core.model;

import javax.persistence.MappedSuperclass;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;

/**
 * Classe repésentant le libelle, l'ordre et la visibilité d'une table de dimension.
 * @author cblanchard - SCUB
 */
@MappedSuperclass
public abstract class ModelDim extends ModelEID {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -3084703293100646945L;

    /**
     * Libelle.
     */
    @Field(index = Index.UN_TOKENIZED)
    private String libelle;

    /**
     * Ordre.
     */
    private int ordre;

    /**
     * Visibilité.
     */
    private boolean visible;

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
     * Renvoi la valeur de ordre.
     * @return ordre
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * Modifie ordre.
     * @param ordre la nouvelle valeur de ordre
     */
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    /**
     * Renvoi la valeur de visible.
     * @return visible
     */
    public boolean getVisible() {
        return visible;
    }

    /**
     * Modifie visible.
     * @param visible la nouvelle valeur de visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}

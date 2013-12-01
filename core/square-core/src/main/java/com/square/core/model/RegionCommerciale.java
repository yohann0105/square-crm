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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

/**
 * Entité persistante modélisant les régions commerciales.
 * @author straumat - SCUB
 */
@Entity
@Table(name = "DATA_REGION_COMMERCIALE")
@Indexed
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "REGION_COMMERCIALE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "REGION_COMMERCIALE_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "REGION_COMMERCIALE_EID", unique = true)),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "REGION_COMMERCIALE_DATE_CREATION", nullable = false)),
    @AttributeOverride(name = "dateModification", column = @Column(name = "REGION_COMMERCIALE_DATE_MODIFICATION")),
    @AttributeOverride(name = "dateSuppression", column = @Column(name = "REGION_COMMERCIALE_DATE_SUPPRESSION")),
    @AttributeOverride(name = "supprime", column = @Column(name = "REGION_COMMERCIALE_SUPPRIME", nullable = false)) })
public class RegionCommerciale extends ModelData {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 2484985382449560207L;

    /**
     * Libelle de l'agence.
     */
    @Column(name = "REGION_COMMERCIALE_LIB", nullable = false)
    private String libelle;

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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RegionCommerciale)) {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

}

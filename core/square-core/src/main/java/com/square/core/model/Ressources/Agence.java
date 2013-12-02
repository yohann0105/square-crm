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
package com.square.core.model.Ressources;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

import com.square.core.model.ModelData;
import com.square.core.model.RegionCommerciale;

/**
 * Entité persistante des agences.
 * @author cblanchard - SCUB
 */
@Entity
@Table(name = "DATA_AGENCE")
@Indexed
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "AGENCE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "AGENCE_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "AGENCE_EID", unique = true)),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "AGENCE_DATE_CREATION", nullable = false)),
    @AttributeOverride(name = "dateModification", column = @Column(name = "AGENCE_DATE_MODIFICATION")),
    @AttributeOverride(name = "dateSuppression", column = @Column(name = "AGENCE_DATE_SUPPRESSION")),
    @AttributeOverride(name = "supprime", column = @Column(name = "AGENCE_SUPPRIME", nullable = false)) })
public class Agence extends ModelData {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -3183811737609359589L;

    /**
     * Libelle de l'agence.
     */
    @Column(name = "AGENCE_LIB", nullable = false)
    @Field(index = Index.TOKENIZED)
    private String libelle;

    /**
     * Région commerciale de l'agence.
     */
    @ManyToOne()
    @JoinColumn(name = "REGION_COMMERCIALE_UID")
    @ForeignKey(name = "FK_DATA_AGENCE_DATA_REGION_COMMERCIALE_REGION_COMMERCIALE_UID")
    private RegionCommerciale region;

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
     * Retourne la valeur de region.
     * @return the region
     */
    public RegionCommerciale getRegion() {
        return region;
    }

    /**
     * Modifie la valeur de region.
     * @param region the region to set
     */
    public void setRegion(RegionCommerciale region) {
        this.region = region;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Agence)) {
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

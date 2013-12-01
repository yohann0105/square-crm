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

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.search.annotations.Indexed;

/**
 * Entité persistante modélisant le département.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
@Entity
@Table(name = "DIM_DEPARTEMENT")
@Indexed
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "DEPARTEMENT_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "DEPARTEMENT_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "DEPARTEMENT_EID", unique = true)),
    @AttributeOverride(name = "libelle", column = @Column(name = "DEPARTEMENT_LIB", nullable = false)),
    @AttributeOverride(name = "ordre", column = @Column(name = "DEPARTEMENT_ORDRE", nullable = false)),
    @AttributeOverride(name = "visible", column = @Column(name = "DEPARTEMENT_VISIBLE", nullable = false)) })
public class Departement extends ModelDim {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 3874455886927311298L;

    /**
     * Code du département.
     */
    @Column(name = "DEPARTEMENT_CODE_INSEE", nullable = false)
    private String codeDepartement;

    /**
     * Clé étrangère vers la table REGION.
     */
    @ManyToOne
    @JoinColumn(name = "REGION_UID", nullable = false)
    @ForeignKey(name = "FK_DIM_DEPARTEMENT_DIM_REGION_REGION_UID")
    private Region region;

    /**
     * Liste des communes.
     */
    @OneToMany(mappedBy = "departement")
    private Set<Commune> communes;

    /**
     * Retourne la valeur de region.
     * @return the region
     */
    public Region getRegion() {
        return region;
    }

    /**
     * Modifie la valeur de region.
     * @param region the region to set
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * Recuperer la liste des communes par departement.
     * @return the communes .
     */
    public Set<Commune> getCommunes() {
        return communes;
    }

    /**
     * Fixee la liste des Communes du departement.
     * @param communes the communes to set .
     */
    public void setCommunes(Set<Commune> communes) {
        this.communes = communes;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Departement)) {
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

    /**
     * Renvoi la valeur de codeDepartement.
     * @return codeDepartement
     */
    public String getCodeDepartement() {
        return codeDepartement;
    }

    /**
     * Modifie codeDepartement.
     * @param codeDepartement la nouvelle valeur de codeDepartement
     */
    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }
}

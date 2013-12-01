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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.square.core.model.Ressources.Agence;
import com.square.core.model.Ressources.Ressource;

/**
 * Entité persistante modélisant les attributions des personnes.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
@Entity
@Table(name = "DATA_PERSONNE_ATTRIBUTION")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "PERSONNE_ATTRIBUTION_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "PERSONNE_ATTRIBUTION_VERSION", unique = false)) })
public class PersonneAttribution extends ModelData {

    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 6382499869336078758L;

    /**
     * L'agence attribuée.
     */
    @ManyToOne()
    @JoinColumn(name = "AGENCE_UID", nullable = false)
    @IndexedEmbedded
    @ForeignKey(name = "FK_DATA_PERSONNE_ATTRIBUTION_DATA_AGENCE_AGENCE_UID")
    private Agence agence;

    /**
     * La ressource attribuée.
     */
    @ManyToOne()
    @JoinColumn(name = "RESSOURCE_UID")
    @IndexedEmbedded
    @ForeignKey(name = "FK_DATA_PERSONNE_ATTRIBUTION_DATA_RESSOURCE_RESSOURCE_UID")
    private Ressource ressource;

    /**
     * Retourne la valeur de agence.
     * @return the agence
     */
    public Agence getAgence() {
        return agence;
    }

    /**
     * Modifie la valeur de agence.
     * @param agence the agence to set
     */
    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    /**
     * Retourne la valeur de ressource.
     * @return the ressource
     */
    public Ressource getRessource() {
        return ressource;
    }

    /**
     * Modifie la valeur de ressource.
     * @param ressource the ressource to set
     */
    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PersonneAttribution)) {
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

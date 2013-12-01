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

import com.square.core.model.Ressources.Agence;
import com.square.core.model.Ressources.Ressource;

/**
 * Entité persistante modélisant les attributions des opportunités.
 * @author straumat - SCUB
 */
@Entity
@Table(name = "DATA_OPPORTUNITE_ATTRIBUTION")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "OPPORTUNITE_ATTRIBUTION_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "OPPORTUNITE_ATTRIBUTION_VERSION", unique = false))
// @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "OPPORTUNITE_ATTRIBUTION_EID", unique = true)),
// @AttributeOverride(name = "dateCreation", column = @Column(name = "OPPORTUNITE_ATTRIBUTION_DATE_CREATION", nullable = false)),
// @AttributeOverride(name = "dateModification", column = @Column(name = "OPPORTUNITE_ATTRIBUTION_DATE_MODIFICATION")),
// @AttributeOverride(name = "dateSuppression", column = @Column(name = "OPPORTUNITE_ATTRIBUTION_DATE_SUPPRESSION")),
// @AttributeOverride(name = "supprime", column = @Column(name = "OPPORTUNITE_ATTRIBUTION_SUPPRIME", nullable = false))
})
public class OpportuniteAttribution extends ModelData {

    /**
     * Serial Vesion UID.
     */
    private static final long serialVersionUID = 7204321068672673182L;

    /**
     * L'agence de l'action.
     */
    @ManyToOne()
    @JoinColumn(name = "AGENCE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_OPP_ATTRIBUTION_DATA_AGENCE_AGENCE_UID")
    private Agence agence;

    /**
     * La ressource de l'action.
     */
    @ManyToOne()
    @JoinColumn(name = "RESSOURCE_UID")
    @ForeignKey(name = "FK_DATA_OPP_ATTRIBUTION_DATA_RESSOURCE_RESSOURCE_UID")
    private Ressource ressource;

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OpportuniteAttribution)) {
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
     * Renvoi la valeur de agence.
     * @return agence
     */
    public Agence getAgence() {
        return agence;
    }

    /**
     * Modifie agence.
     * @param agence la nouvelle valeur de agence
     */
    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    /**
     * Renvoi la valeur de ressource.
     * @return ressource
     */
    public Ressource getRessource() {
        return ressource;
    }

    /**
     * Modifie ressource.
     * @param ressource la nouvelle valeur de ressource
     */
    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

}

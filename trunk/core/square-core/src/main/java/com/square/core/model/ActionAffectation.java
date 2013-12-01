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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * Entité persistante modélisant les affectations des actions.
 * @author straumat - SCUB
 */
@Entity
@Table(name = "DATA_ACTION_AFFECTATION")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "ACTION_AFFECTATION_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "ACTION_AFFECTATION_VERSION", unique = false)) })
public class ActionAffectation extends ModelData {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 7204321068672673182L;

    /**
     * Personne.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACTION_AFFECTATION_PERSONNE_UID")
    @ForeignKey(name = "FK_DATA_ACTION_AFFECTATION_DATA_PERSONNE_PERSONNE_UID")
    private Personne personne;

    /**
     * Opportunité.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTION_AFFECTATION_OPPORTUNITE_UID")
    @ForeignKey(name = "FK_DATA_ACTION_AFFECTATION_DATA_OPPORTUNITE_OPPORTUNITE_UID")
    private Opportunite opportunite;

    /**
     * Renvoi la valeur de personne.
     * @return personne
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * Modifie personne.
     * @param personne la nouvelle valeur de personne
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    /**
     * Renvoi la valeur de opportunite.
     * @return opportunite
     */
    public Opportunite getOpportunite() {
        return opportunite;
    }

    /**
     * Modifie opportunite.
     * @param opportunite la nouvelle valeur de opportunite
     */
    public void setOpportunite(Opportunite opportunite) {
        this.opportunite = opportunite;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionAffectation)) {
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

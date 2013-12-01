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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.square.core.model.Ressources.Ressource;

/**
 * Entité persistante modélisant les opportunités.
 * @author straumat - SCUB
 */
@Entity
@Table(name = "DATA_OPPORTUNITE")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "OPPORTUNITE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "OPPORTUNITE_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "OPPORTUNITE_EID", unique = true)),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "OPPORTUNITE_DATE_CREATION", nullable = false)),
    @AttributeOverride(name = "dateModification", column = @Column(name = "OPPORTUNITE_DATE_MODIFICATION")),
    @AttributeOverride(name = "dateSuppression", column = @Column(name = "OPPORTUNITE_DATE_SUPPRESSION")),
    @AttributeOverride(name = "supprime", column = @Column(name = "OPPORTUNITE_SUPPRIME", nullable = false)) })
public class Opportunite extends ModelData {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -2046319273240645225L;

    /**
     * Statut de la campagne.
     */
    @ManyToOne
    @JoinColumn(name = "PERSONNE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_OPPORTUNITE_DATA_PERSONNE_PHYSIQUE_PERSONNE_UID")
    private PersonnePhysique personnePhysique;

    /**
     * Ressource.
     */
    @ManyToOne
    @JoinColumn(name = "RESSOURCE_UID", nullable = true)
    @ForeignKey(name = "FK_DATA_OPPORTUNITE_DATA_RESSOURCE_RESSOURCE_UID")
    private Ressource ressource;

    /**
     * Attribution.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OPP_ATTRIBUTION_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_OPPORTUNITE_DATA_OPP_ATTRIBUTION_OPP_ATTRIBUTION_UID")
    private OpportuniteAttribution opportuniteAttribution;

    /**
     * Statut.
     */
    @ManyToOne
    @JoinColumn(name = "OPP_STATUT_UID")
    @ForeignKey(name = "FK_DATA_OPP_DIM_OPP_STATUT_OPP_STATUT_UID")
    private OpportuniteStatut statut;

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Opportunite)) {
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
     * Getter / Setter de la propriété.
     * @return the personnePhysique
     */
    public PersonnePhysique getPersonnePhysique() {
        return personnePhysique;
    }

    /**
     * Getter / Setter de la propriété.
     * @param personnePhysique the personnePhysique to set
     */
    public void setPersonnePhysique(PersonnePhysique personnePhysique) {
        this.personnePhysique = personnePhysique;
    }

    /**
     * Getter / Setter de la propriété.
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * Getter / Setter de la propriété.
     * @return the ressource
     */
    public Ressource getRessource() {
        return ressource;
    }

    /**
     * Getter / Setter de la propriété.
     * @param ressource the ressource to set
     */
    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    /**
     * Renvoi la valeur de statut.
     * @return statut
     */
    public OpportuniteStatut getStatut() {
        return statut;
    }

    /**
     * Modifie statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(OpportuniteStatut statut) {
        this.statut = statut;
    }

    /**
     * Renvoi la valeur de opportuniteAttribution.
     * @return opportuniteAttribution
     */
    public OpportuniteAttribution getOpportuniteAttribution() {
        return opportuniteAttribution;
    }

    /**
     * Modifie opportuniteAttribution.
     * @param opportuniteAttribution la nouvelle valeur de opportuniteAttribution
     */
    public void setOpportuniteAttribution(OpportuniteAttribution opportuniteAttribution) {
        this.opportuniteAttribution = opportuniteAttribution;
    }

}

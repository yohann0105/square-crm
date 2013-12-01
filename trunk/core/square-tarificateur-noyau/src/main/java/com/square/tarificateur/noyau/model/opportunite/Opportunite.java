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
package com.square.tarificateur.noyau.model.opportunite;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Entité persistante qui modélise les opportunités.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_OPPORTUNITE")
public class Opportunite extends BaseModel {

    /** SerialVersionUID. */
    private static final long serialVersionUID = 1636512065872983316L;

    /** Identifiant externe de l'opportunité (Square). */
    @Column(name = "OPPORTUNITE_EID", nullable = false)
    private Long eidOpportunite;

    /** Identifiant externe du créateur de l'opportunité. */
    @Column(name = "CREATEUR_EID", nullable = false)
    private Long eidCreateur;

    /** Date de création de l'opportunité. */
    @Column(name = "DATE_CREATION", nullable = false)
    private Calendar dateCreation;

    /** Date à laquelle l'opportunité a été cloturée. */
    @Column(name = "DATE_CLOTURE")
    private Calendar dateCloture;

    /** Finalité de l'opportunité. */
    @ManyToOne
    @JoinColumn(name = "FINALITE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_OPPORTUNITE_DIM_FINALITE_UID")
    private Finalite finalite;

    /** Informations de l'adhésion. */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADHESION_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_OPPORTUNITE_DATA_ADHESION_UID")
    private Adhesion adhesion;

    /** EID Nature (Square) de la personne principale à la création de l'opportunité. */
    @Column(name = "NATURE_PERSONNE_EID")
    private Long eidNaturePersonne;

    /**
     * Getter function.
     * @return the eidOpportunite
     */
    public Long getEidOpportunite() {
        return eidOpportunite;
    }

    /**
     * Getter function.
     * @return the eidCreateur
     */
    public Long getEidCreateur() {
        return eidCreateur;
    }

    /**
     * Getter function.
     * @return the dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Getter function.
     * @return the dateCloture
     */
    public Calendar getDateCloture() {
        return dateCloture;
    }

    /**
     * Getter function.
     * @return the finalite
     */
    public Finalite getFinalite() {
        return finalite;
    }

    /**
     * Getter function.
     * @return the adhesion
     */
    public Adhesion getAdhesion() {
        return adhesion;
    }

    /**
     * Setter function.
     * @param eidOpportunite the eidOpportunite to set
     */
    public void setEidOpportunite(Long eidOpportunite) {
        this.eidOpportunite = eidOpportunite;
    }

    /**
     * Setter function.
     * @param eidCreateur the eidCreateur to set
     */
    public void setEidCreateur(Long eidCreateur) {
        this.eidCreateur = eidCreateur;
    }

    /**
     * Setter function.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Setter function.
     * @param dateCloture the dateCloture to set
     */
    public void setDateCloture(Calendar dateCloture) {
        this.dateCloture = dateCloture;
    }

    /**
     * Setter function.
     * @param finalite the finalite to set
     */
    public void setFinalite(Finalite finalite) {
        this.finalite = finalite;
    }

    /**
     * Setter function.
     * @param adhesion the adhesion to set
     */
    public void setAdhesion(Adhesion adhesion) {
        this.adhesion = adhesion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((adhesion == null) ? 0 : adhesion.hashCode());
        result = prime * result + ((dateCloture == null) ? 0 : dateCloture.hashCode());
        result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
        result = prime * result + ((eidCreateur == null) ? 0 : eidCreateur.hashCode());
        result = prime * result + ((eidOpportunite == null) ? 0 : eidOpportunite.hashCode());
        result = prime * result + ((finalite == null) ? 0 : finalite.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equalsUtil(obj)) {
            return false;
        }
        if (!(obj instanceof Opportunite)) {
            return false;
        }
        final Opportunite other = (Opportunite) obj;
        if (adhesion == null) {
            if (other.adhesion != null) {
                return false;
            }
        }
        else if (!adhesion.equals(other.adhesion)) {
            return false;
        }
        if (dateCloture == null) {
            if (other.dateCloture != null) {
                return false;
            }
        }
        else if (!dateCloture.equals(other.dateCloture)) {
            return false;
        }
        if (dateCreation == null) {
            if (other.dateCreation != null) {
                return false;
            }
        }
        else if (!dateCreation.equals(other.dateCreation)) {
            return false;
        }
        if (eidCreateur == null) {
            if (other.eidCreateur != null) {
                return false;
            }
        }
        else if (!eidCreateur.equals(other.eidCreateur)) {
            return false;
        }
        if (eidOpportunite == null) {
            if (other.eidOpportunite != null) {
                return false;
            }
        }
        else if (!eidOpportunite.equals(other.eidOpportunite)) {
            return false;
        }
        if (finalite == null) {
            if (other.finalite != null) {
                return false;
            }
        }
        else if (!finalite.equals(other.finalite)) {
            return false;
        }
        return true;
    }

    /**
     * Récupère la valeur eidNaturePersonne.
     * @return the eidNaturePersonne
     */
    public Long getEidNaturePersonne() {
        return eidNaturePersonne;
    }

    /**
     * Définit la valeur de eidNaturePersonne.
     * @param eidNaturePersonne the eidNaturePersonne to set
     */
    public void setEidNaturePersonne(Long eidNaturePersonne) {
        this.eidNaturePersonne = eidNaturePersonne;
    }

}

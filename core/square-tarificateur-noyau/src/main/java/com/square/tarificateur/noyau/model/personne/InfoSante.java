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
package com.square.tarificateur.noyau.model.personne;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Modèle des infos de santé.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_INFO_SANTE")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "INFO_SANTE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "INFO_SANTE_VERSION", unique = false)) })
public class InfoSante extends BaseModel {

    /** serialVersionUID. */
    private static final long serialVersionUID = -1534161731470876728L;

    /** Numéro de sécurité sociale. */
    @Column(name = "INFO_SANTE_NUM_SS")
    private String numSecuriteSocial;

    /** Clé de sécurité sociale. */
    @Column(name = "INFO_SANTE_CLE_SS")
    private String cleSecuriteSocial;

    /** EID de la caisse (Square). */
    @Column(name = "INFO_SANTE_CAISSE_EID")
    private Long eidCaisse;

    /** Référent du numero RO. */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INFO_SANTE_REFERENT_UID")
    @ForeignKey(name = "FK_DATA_INFO_SANTE_REFERENT_UID")
    private Personne referent;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof InfoSante)) {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Get the numSecuriteSocial value.
     * @return the numSecuriteSocial
     */
    public String getNumSecuriteSocial() {
        return numSecuriteSocial;
    }

    /**
     * Set the numSecuriteSocial value.
     * @param numSecuriteSocial the numSecuriteSocial to set
     */
    public void setNumSecuriteSocial(String numSecuriteSocial) {
        this.numSecuriteSocial = numSecuriteSocial;
    }

    /**
     * Get the cleSecuriteSocial value.
     * @return the cleSecuriteSocial
     */
    public String getCleSecuriteSocial() {
        return cleSecuriteSocial;
    }

    /**
     * Set the cleSecuriteSocial value.
     * @param cleSecuriteSocial the cleSecuriteSocial to set
     */
    public void setCleSecuriteSocial(String cleSecuriteSocial) {
        this.cleSecuriteSocial = cleSecuriteSocial;
    }

    /**
     * Get the eidCaisse value.
     * @return the eidCaisse
     */
    public Long getEidCaisse() {
        return eidCaisse;
    }

    /**
     * Set the eidCaisse value.
     * @param eidCaisse the eidCaisse to set
     */
    public void setEidCaisse(Long eidCaisse) {
        this.eidCaisse = eidCaisse;
    }

    /**
     * Get the referent value.
     * @return the referent
     */
    public Personne getReferent() {
        return referent;
    }

    /**
     * Set the referent value.
     * @param referent the referent to set
     */
    public void setReferent(Personne referent) {
        this.referent = referent;
    }
}

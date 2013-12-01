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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Modèle de la relation entre les lignes pour représenter les lignes liées.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_LIGNE_DEVIS_LIEE")
public class LigneDevisLiee extends BaseModel {

    /** SerialVersionUID. */
    private static final long serialVersionUID = 6796926086425736230L;

    /** La ligne de devis source. */
    @ManyToOne
    @JoinColumn(name = "LIGNE_DEVIS_SOURCE_UID")
    @ForeignKey(name = "FK_DATA_LIGNE_DEVIS_LIEE_DATA_LIGNE_DEVIS_SOURCE")
    private LigneDevis ligneDevisSource;

    /** La ligne de devis liée. */
    @ManyToOne (cascade = CascadeType.REMOVE)
    @JoinColumn(name = "LIGNE_DEVIS_LIEE_UID")
    @ForeignKey(name = "FK_DATA_LIGNE_DEVIS_LIEE_DATA_LIGNE_DEVIS_LIEE")
    private LigneDevis ligneDevisLiee;

    /**
     * Get the ligneDevisLiee value.
     * @return the ligneDevisLiee
     */
    public LigneDevis getLigneDevisLiee() {
        return ligneDevisLiee;
    }

    /**
     * Set the ligneDevisLiee value.
     * @param ligneDevisLiee the ligneDevisLiee to set
     */
    public void setLigneDevisLiee(LigneDevis ligneDevisLiee) {
        this.ligneDevisLiee = ligneDevisLiee;
    }

    /**
     * Get the ligneDevisSource value.
     * @return the ligneDevisSource
     */
    public LigneDevis getLigneDevisSource() {
        return ligneDevisSource;
    }

    /**
     * Set the ligneDevisSource value.
     * @param ligneDevisSource the ligneDevisSource to set
     */
    public void setLigneDevisSource(LigneDevis ligneDevisSource) {
        this.ligneDevisSource = ligneDevisSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other)
    {
        if (other == null || !(other instanceof LigneDevisLiee))
        {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        return super.hashCode();
    }
}

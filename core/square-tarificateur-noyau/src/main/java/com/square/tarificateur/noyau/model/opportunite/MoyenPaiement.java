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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Moyen de paiement.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@Entity
@Table(name = "moyens_paiement")
public class MoyenPaiement extends BaseModel
{
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -7817825357178338408L;

    /** Nom du statut. */
    @Column(name = "nom")
    private String nom;

    /** Ordre du statut. */
    @Column(name = "ordre")
    private Long ordre;

    /**
     * Get the nom value.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the nom value.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get the ordre value.
     * @return the ordre
     */
    public Long getOrdre() {
        return ordre;
    }

    /**
     * Set the ordre value.
     * @param ordre the ordre to set
     */
    public void setOrdre(Long ordre) {
        this.ordre = ordre;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other)
    {
        if (other == null || !(other instanceof MoyenPaiement))
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

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
 * Valeur de régle.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_REGLE_VALEUR")
public class RegleValeur extends BaseModel {

    /** SerialVersionUID. */
    private static final long serialVersionUID = -130451950029347281L;

    /** Identifiant de la règle. */
    @Column(name = "REGLE_UID")
    private Integer idRegle;

    /** Identifiant de la valeur de règle. */
    @Column(name = "VALEUR_REGLE_UID")
    private Integer idValeurRegle;

    /** Valeur de la règle (immuable). */
    @Column(name = "VALEUR_REGLE")
    private String valeurRegle;

    /**
     * Définit la valeur de idRegle.
     * @return la valeur de idRegle
     */
    public Integer getIdRegle() {
        return idRegle;
    }

    /**
     * Définit la valeur de idRegle.
     * @param idRegle la nouvelle valeur de idRegle
     */
    public void setIdRegle(Integer idRegle) {
        this.idRegle = idRegle;
    }

    /**
     * Définit la valeur de valeurRegle.
     * @return la valeur de valeurRegle
     */
    public String getValeurRegle() {
        return valeurRegle;
    }

    /**
     * Définit la valeur de valeurRegle.
     * @param valeurRegle la nouvelle valeur de valeurRegle
     */
    public void setValeurRegle(String valeurRegle) {
        this.valeurRegle = valeurRegle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof RegleValeur)) { return false; }
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
     * Get the idValeurRegle value.
     * @return the idValeurRegle
     */
    public Integer getIdValeurRegle() {
        return idValeurRegle;
    }

    /**
     * Set the idValeurRegle value.
     * @param idValeurRegle the idValeurRegle to set
     */
    public void setIdValeurRegle(Integer idValeurRegle) {
        this.idValeurRegle = idValeurRegle;
    }
}

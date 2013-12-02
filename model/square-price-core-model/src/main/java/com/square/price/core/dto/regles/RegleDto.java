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
package com.square.price.core.dto.regles;

import java.io.Serializable;
import java.util.Calendar;

/**
 * DTO représentant une règle.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net)
 */
public final class RegleDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 7370320959545879147L;

    private Integer identifiant;

    private String libelle;

    private Calendar debutEffet;

    private Calendar finEffet;

    /**
     * Get the identifiant value.
     * @return the identifiant
     */
    public Integer getIdentifiant() {
        return identifiant;
    }

    /**
     * Set the identifiant value.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Get the libelle value.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Set the libelle value.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Get the debutEffet value.
     * @return the debutEffet
     */
    public Calendar getDebutEffet() {
        return debutEffet;
    }

    /**
     * Set the debutEffet value.
     * @param debutEffet the debutEffet to set
     */
    public void setDebutEffet(Calendar debutEffet) {
        this.debutEffet = debutEffet;
    }

    /**
     * Get the finEffet value.
     * @return the finEffet
     */
    public Calendar getFinEffet() {
        return finEffet;
    }

    /**
     * Set the finEffet value.
     * @param finEffet the finEffet to set
     */
    public void setFinEffet(Calendar finEffet) {
        this.finEffet = finEffet;
    }

}

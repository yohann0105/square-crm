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
 * Dto qui représente une valeur possible d'une règle.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net)
 */
public final class ValeurRegleDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 2588105975388778319L;

    private Integer identifiant;

    private String valeur;

    private Integer priorite;

    private Calendar debutEffet;

    private Calendar finEffet;

    private Boolean impacteTarif;

    private String typeValeur;

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
     * Get the valeur value.
     * @return the valeur
     */
    public String getValeur() {
        return valeur;
    }

    /**
     * Set the valeur value.
     * @param valeur the valeur to set
     */
    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    /**
     * Get the priorite value.
     * @return the priorite
     */
    public Integer getPriorite() {
        return priorite;
    }

    /**
     * Set the priorite value.
     * @param priorite the priorite to set
     */
    public void setPriorite(Integer priorite) {
        this.priorite = priorite;
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

    /**
     * Get the impacteTarif value.
     * @return the impacteTarif
     */
    public Boolean getImpacteTarif() {
        return impacteTarif;
    }

    /**
     * Set the impacteTarif value.
     * @param impacteTarif the impacteTarif to set
     */
    public void setImpacteTarif(Boolean impacteTarif) {
        this.impacteTarif = impacteTarif;
    }

    /**
     * Get the typeValeur value.
     * @return the typeValeur
     */
    public String getTypeValeur() {
        return typeValeur;
    }

    /**
     * Set the typeValeur value.
     * @param typeValeur the typeValeur to set
     */
    public void setTypeValeur(String typeValeur) {
        this.typeValeur = typeValeur;
    }

}

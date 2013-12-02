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
package com.square.core.model.dto;

import java.io.Serializable;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto des infos de santé.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class InfoSanteDto implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = -1066468563384547861L;

    /** Numéro de sécurité social + clé. */
    private String nro;

    /** Caisse. */
    private CaisseSimpleDto caisse;

    /** Regime. */
    private IdentifiantLibelleDto caisseRegime;

    /** Identifiant du référent. */
    private Long idReferent;

    /**
     * Get the nro value.
     * @return the nro
     */
    public String getNro() {
        return nro;
    }

    /**
     * Set the nro value.
     * @param nro the nro to set
     */
    public void setNro(String nro) {
        this.nro = nro;
    }

    /**
     * Get the caisse value.
     * @return the caisse
     */
    public CaisseSimpleDto getCaisse() {
        return caisse;
    }

    /**
     * Set the caisse value.
     * @param caisse the caisse to set
     */
    public void setCaisse(CaisseSimpleDto caisse) {
        this.caisse = caisse;
    }

    /**
     * Get the idReferent value.
     * @return the idReferent
     */
    public Long getIdReferent() {
        return idReferent;
    }

    /**
     * Set the idReferent value.
     * @param idReferent the idReferent to set
     */
    public void setIdReferent(Long idReferent) {
        this.idReferent = idReferent;
    }

    /**
     * Get the caisseRegime value.
     * @return the caisseRegime
     */
    public IdentifiantLibelleDto getCaisseRegime() {
        return caisseRegime;
    }

    /**
     * Set the caisseRegime value.
     * @param caisseRegime the caisseRegime to set
     */
    public void setCaisseRegime(IdentifiantLibelleDto caisseRegime) {
        this.caisseRegime = caisseRegime;
    }
}

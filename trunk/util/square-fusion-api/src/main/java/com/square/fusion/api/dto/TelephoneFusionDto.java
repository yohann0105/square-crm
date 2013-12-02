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
package com.square.fusion.api.dto;

import java.io.Serializable;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO d'un téléphone pour la fusion.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class TelephoneFusionDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 920911667081000775L;

    /** Identifiant. */
    private Long identifiant;

    /**
     * Numéro du téléphone.
     * */
    private String numero;

    /**
     * Nature du téléphone (fixe, portable).
     * */
    private IdentifiantLibelleDto nature;

    /**
     * Récupère la valeur de identifiant.
     * @return la valeur de identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Définit la valeur de identifiant.
     * @param identifiant la nouvelle valeur de identifiant
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Récupère la valeur de numero.
     * @return la valeur de numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Définit la valeur de numero.
     * @param numero la nouvelle valeur de numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Récupère la valeur de nature.
     * @return la valeur de nature
     */
    public IdentifiantLibelleDto getNature() {
        return nature;
    }

    /**
     * Définit la valeur de nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(IdentifiantLibelleDto nature) {
        this.nature = nature;
    }

}

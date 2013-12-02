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
 * DTO d'une adresse pour la fusion.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class AdresseFusionDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -8418984949960787781L;

    /** Identifiant. */
    private Long identifiant;

    /** Nature. */
    private IdentifiantLibelleDto nature;

    /** Libellé complet de l'adresse. */
    private String libelleAdresse;

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
     * Récupère la valeur de libelleAdresse.
     * @return la valeur de libelleAdresse
     */
    public String getLibelleAdresse() {
        return libelleAdresse;
    }

    /**
     * Définit la valeur de libelleAdresse.
     * @param libelleAdresse la nouvelle valeur de libelleAdresse
     */
    public void setLibelleAdresse(String libelleAdresse) {
        this.libelleAdresse = libelleAdresse;
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

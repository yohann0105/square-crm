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
 * DTO représentant un code postal, une commune et le libellé d'acheminement.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class CodePostalCommuneDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -879460421445053451L;

    /** Identifiant du code postal - commune. */
    private Long identifiant;

    /** Code postal. */
    private IdentifiantLibelleDto codePostal;

    /** Commune. */
    private IdentifiantLibelleDto commune;

    /** Libellé d'acheminement. */
    private String libelleAcheminement;

    /** Constructeur. */
    public CodePostalCommuneDto() { }

    /**
     * Constructeur avec paramètre.
     * @param identifiant l'identifiant du code postal - commune
     */
    public CodePostalCommuneDto(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Récupère la valeur codePostal.
     * @return the codePostal
     */
    public IdentifiantLibelleDto getCodePostal() {
        return codePostal;
    }

    /**
     * Définit la valeur de codePostal.
     * @param codePostal the codePostal to set
     */
    public void setCodePostal(IdentifiantLibelleDto codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Récupère la valeur commune.
     * @return the commune
     */
    public IdentifiantLibelleDto getCommune() {
        return commune;
    }

    /**
     * Définit la valeur de commune.
     * @param commune the commune to set
     */
    public void setCommune(IdentifiantLibelleDto commune) {
        this.commune = commune;
    }

    /**
     * Récupère la valeur libelleAcheminement.
     * @return the libelleAcheminement
     */
    public String getLibelleAcheminement() {
        return libelleAcheminement;
    }

    /**
     * Définit la valeur de libelleAcheminement.
     * @param libelleAcheminement the libelleAcheminement to set
     */
    public void setLibelleAcheminement(String libelleAcheminement) {
        this.libelleAcheminement = libelleAcheminement;
    }

    /**
     * Récupère la valeur identifiant.
     * @return the identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Définit la valeur de identifiant.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

}

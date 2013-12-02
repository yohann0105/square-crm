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
package com.square.adherent.noyau.dto;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO permettant de récupérer également l'ordre d'une dimension.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class IdentifiantLibelleOrdreDto extends IdentifiantLibelleDto {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6129487921605794663L;

    /** Ordre. */
    private Integer ordre;

    /**
     * Constructeur.
     */
    public IdentifiantLibelleOrdreDto() {
        super();
    }

    /**
     * Constructeur.
     * @param identifiant identifiant
     */
    public IdentifiantLibelleOrdreDto(Long identifiant) {
        super(identifiant);
    }

    /**
     * Constructeur.
     * @param identifiant identifiant
     * @param libelle libelle
     */
    public IdentifiantLibelleOrdreDto(Long identifiant, String libelle) {
        super(identifiant, libelle);
    }

    /**
     * Get the ordre value.
     * @return the ordre
     */
    public Integer getOrdre() {
        return ordre;
    }

    /**
     * Set the ordre value.
     * @param ordre the ordre to set
     */
    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

}

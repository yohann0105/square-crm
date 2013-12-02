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
package com.square.print.core.dto.devis;

import java.io.Serializable;

import com.square.print.core.dto.ProspectDevisDto;

/**
 * DTO super-classe représentant un devis.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net)
 */
public class DevisPdfDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -2332812774788626550L;

    /** Référence du devis. */
    private String reference;

    /** Prospect associé au devis. */
    private ProspectDevisDto prospect;

    /**
     * Constructeur.
     */
    protected DevisPdfDto() {
        super();
    }

    /**
     * Constructeur.
     * @param reference la référence
     * @param prospect le prospect
     */
    public DevisPdfDto(String reference, ProspectDevisDto prospect) {
        super();
        this.reference = reference;
        this.prospect = prospect;
    }

    /**
     * Get the prospect value.
     * @return the prospect
     */
    public ProspectDevisDto getProspect() {
        return prospect;
    }

    /**
     * Set the prospect value.
     * @param prospect the prospect to set
     */
    public void setProspect(ProspectDevisDto prospect) {
        this.prospect = prospect;
    }

    /**
     * Get the reference value.
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * Set the reference value.
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

}

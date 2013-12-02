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
package com.square.adherent.noyau.dto.adherent.contrat;

import java.io.Serializable;

/**
 * Dto de couple bareme.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class CoupleBaremeDto implements Serializable {

    private static final long serialVersionUID = -2483904391813196547L;

    /** Eid bareme 1. */
    private String bareme1Eid;

    /** Eid bareme 2. */
    private String bareme2Eid;

    /**
     * Constructeur.
     */
    public CoupleBaremeDto() {
        super();
    }

    /**
     * Constructeur.
     * @param bareme1Eid bareme1Eid
     * @param bareme2Eid bareme2Eid
     */
    public CoupleBaremeDto(String bareme1Eid, String bareme2Eid) {
        super();
        this.bareme1Eid = bareme1Eid;
        this.bareme2Eid = bareme2Eid;
    }

    /**
     * Get the value of bareme1Eid.
     * @return the bareme1Eid
     */
    public String getBareme1Eid() {
        return bareme1Eid;
    }

    /**
     * Set the value of bareme1Eid.
     * @param bareme1Eid the bareme1Eid to set
     */
    public void setBareme1Eid(String bareme1Eid) {
        this.bareme1Eid = bareme1Eid;
    }

    /**
     * Get the value of bareme2Eid.
     * @return the bareme2Eid
     */
    public String getBareme2Eid() {
        return bareme2Eid;
    }

    /**
     * Set the value of bareme2Eid.
     * @param bareme2Eid the bareme2Eid to set
     */
    public void setBareme2Eid(String bareme2Eid) {
        this.bareme2Eid = bareme2Eid;
    }

}

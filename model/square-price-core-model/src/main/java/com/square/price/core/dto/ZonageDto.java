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
package com.square.price.core.dto;

import java.io.Serializable;

/**
 * Dto Reference Zonier.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class ZonageDto implements Serializable {

    private static final long serialVersionUID = 819896408521158767L;

    private String codeDepartement;

    private String libelleDepartement;

    private Integer codeZone;

    private String libelleZone;

    private String codeZonier;

    private String libelleZonier;

    /**
     * Get the codeDepartement value.
     * @return the codeDepartement
     */
    public String getCodeDepartement() {
        return codeDepartement;
    }

    /**
     * Set the codeDepartement value.
     * @param codeDepartement the codeDepartement to set
     */
    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    /**
     * Get the libelleDepartement value.
     * @return the libelleDepartement
     */
    public String getLibelleDepartement() {
        return libelleDepartement;
    }

    /**
     * Set the libelleDepartement value.
     * @param libelleDepartement the libelleDepartement to set
     */
    public void setLibelleDepartement(String libelleDepartement) {
        this.libelleDepartement = libelleDepartement;
    }

    /**
     * Set the codeZone value.
     * @param codeZone the codeZone to set
     */
    public void setCodeZone(Integer codeZone) {
        this.codeZone = codeZone;
    }

    /**
     * Set the libelleZone value.
     * @param libelleZone the libelleZone to set
     */
    public void setLibelleZone(String libelleZone) {
        this.libelleZone = libelleZone;
    }

    /**
     * Get the codeZone value.
     * @return the codeZone
     */
    public Integer getCodeZone() {
        return codeZone;
    }

    /**
     * Get the libelleZone value.
     * @return the libelleZone
     */
    public String getLibelleZone() {
        return libelleZone;
    }

    /**
     * Get the value of codeZonier.
     * @return the codeZonier
     */
    public String getCodeZonier() {
        return codeZonier;
    }

    /**
     * Set the value of codeZonier.
     * @param codeZonier the codeZonier to set
     */
    public void setCodeZonier(String codeZonier) {
        this.codeZonier = codeZonier;
    }

    /**
     * Get the value of libelleZonier.
     * @return the libelleZonier
     */
    public String getLibelleZonier() {
        return libelleZonier;
    }

    /**
     * Set the value of libelleZonier.
     * @param libelleZonier the libelleZonier to set
     */
    public void setLibelleZonier(String libelleZonier) {
        this.libelleZonier = libelleZonier;
    }
}

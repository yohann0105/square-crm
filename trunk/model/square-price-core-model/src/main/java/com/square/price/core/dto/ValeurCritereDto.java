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
 * Classe pour regroupés les valeurs des criteres.
 * @author Goumard Stephane.
 */
public final class ValeurCritereDto implements Serializable {

    /** Serial Verision UID. */
    private static final long serialVersionUID = -891739567627150551L;

    /** Cod_critere. */
    private String codeCritere;

    /** Libelle du critere. */
    private String libelleCritere;

    /**
     * Constructeur.
     */
    public ValeurCritereDto() {
        super();
    }

    /**
     * Constructeur.
     * @param codeCritere code
     * @param libelleCritere libelle
     */
    public ValeurCritereDto(String codeCritere, String libelleCritere) {
        super();
        this.codeCritere = codeCritere;
        this.libelleCritere = libelleCritere;
    }

    /**
     * Get the codeCritere value.
     * @return the codeCritere
     */
    public String getCodeCritere() {
        return codeCritere;
    }

    /**
     * Set the codeCritere value.
     * @param codeCritere the codeCritere to set
     */
    public void setCodeCritere(String codeCritere) {
        this.codeCritere = codeCritere;
    }

    /**
     * Get the libelleCritere value.
     * @return the libelleCritere
     */
    public String getLibelleCritere() {
        return libelleCritere;
    }

    /**
     * Set the libelleCritere value.
     * @param libelleCritere the libelleCritere to set
     */
    public void setLibelleCritere(String libelleCritere) {
        this.libelleCritere = libelleCritere;
    }
}

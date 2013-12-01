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
package com.square.tarificateur.noyau.dto.selecteur.produit;

import java.io.Serializable;

/**
 * Dto representant une valeur de criteres pour le selecteur de produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class ValeurCritereSelecteurDto implements Serializable, Comparable<ValeurCritereSelecteurDto> {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 2773953816224477888L;

    /** Code du critère. */
    private String codeCritere;

    /** Libelle du critère. */
    private String libelleCritere;

    /** Constructeur. */
    public ValeurCritereSelecteurDto() {
    }

    /**
     * Constructeur.
     * @param codeCritere code du critere
     * @param libelleCritere libelle du critere
     */
    public ValeurCritereSelecteurDto(String codeCritere, String libelleCritere) {
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

    /**
     * {@inheritDoc}
     */
    public int compareTo(ValeurCritereSelecteurDto obj) {
        if (this.getLibelleCritere() == null && obj.getLibelleCritere() == null) {
            return 0;
        } else if (this.getLibelleCritere() != null && obj.getLibelleCritere() == null) {
            return 1;
        } else if (this.getLibelleCritere() == null && obj.getLibelleCritere() != null) { return -1; }
        return this.getLibelleCritere().compareTo(obj.getLibelleCritere());
    }

}

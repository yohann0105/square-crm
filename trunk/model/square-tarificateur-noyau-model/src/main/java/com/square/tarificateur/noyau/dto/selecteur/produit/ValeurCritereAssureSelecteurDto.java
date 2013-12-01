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
 * Dto representant une valeur de criteres pour un assuré pour le selecteur de produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class ValeurCritereAssureSelecteurDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 1550157247486796450L;

    /** Identifiant du critère. */
    private Integer identifiantCritere;

    /** Valeur du critère. */
    private String valeur;

    /** Constructeur par défaut. */
    public ValeurCritereAssureSelecteurDto() {
    }

    /**
     * Constructeur par défaut.
     * @param identifiantCritere id du critere
     */
    public ValeurCritereAssureSelecteurDto(Integer identifiantCritere) {
        this.identifiantCritere = identifiantCritere;
    }

    /**
     * Constructeur par défaut.
     * @param identifiantCritere id du critere
     * @param valeur valeur du critere
     */
    public ValeurCritereAssureSelecteurDto(Integer identifiantCritere, String valeur) {
        this.identifiantCritere = identifiantCritere;
        this.valeur = valeur;
    }

    /**
     * Get the identifiantCritere value.
     * @return the identifiantCritere
     */
    public Integer getIdentifiantCritere() {
        return identifiantCritere;
    }

    /**
     * Set the identifiantCritere value.
     * @param identifiantCritere the identifiantCritere to set
     */
    public void setIdentifiantCritere(Integer identifiantCritere) {
        this.identifiantCritere = identifiantCritere;
    }

    /**
     * Get the valeur value.
     * @return the valeur
     */
    public String getValeur() {
        return valeur;
    }

    /**
     * Set the valeur value.
     * @param valeur the valeur to set
     */
    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

}

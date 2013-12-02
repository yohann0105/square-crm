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
package com.square.tarificateur.noyau.dto.devis;

import java.io.Serializable;
/**
 * Valeur critere d'une ligne de devis.
 * @author Goumard stephane (stephane.goumard@scub.net) - SCUB
 */
public class ValeurCritereLigneDevisDto implements Serializable
{
    /** Serial Version UID. */
    private static final long serialVersionUID = -1848984161095414798L;

    /** Critère associé. */
    private Integer identifiantCritere;

    /** Valeur du critère. */
    private String valeur;

    /** Affichage de la valeur. */
    private String affichageValeur;

    /** Libelle du critère associé. */
    private String libelleCritere;

    /**
     * Get the affichageValeur value.
     * @return the affichageValeur
     */
    public String getAffichageValeur() {
        return affichageValeur;
    }

    /**
     * Set the affichageValeur value.
     * @param affichageValeur the affichageValeur to set
     */
    public void setAffichageValeur(String affichageValeur) {
        this.affichageValeur = affichageValeur;
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

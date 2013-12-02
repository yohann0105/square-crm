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
/**
 * 
 */
package com.square.tarificateur.noyau.dto.devis;

import java.io.Serializable;

/**
 * Ligne pour la demande de cloture du devis.
 * @author Goumard stephane (stephane.goumard@scub.net) - SCUB
 */
public class ClotureDevisQueryLigneDto implements Serializable
{
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -7335711918935966933L;

    /**
     * Identifiant ligne devis.
     */
    private Long identifaintLigneDevis;

    /**
     * Identifiant finalite ligne devis.
     */
    private Long identifiantFinaliteLigneDevis;

    /**
     * Boolean selection pour adhesion.
     */
    private Boolean selectionPourAdhesion;

    /**
     * Constructeur.
     * @param identifaintLigneDevis .
     * @param identifiantFinaliteLigneDevis .
     */
    public ClotureDevisQueryLigneDto(Long identifaintLigneDevis,
            Long identifiantFinaliteLigneDevis)
    {
        super();
        this.identifaintLigneDevis = identifaintLigneDevis;
        this.identifiantFinaliteLigneDevis = identifiantFinaliteLigneDevis;
    }

    /**
     * Get the identifaintLigneDevis value.
     * @return the identifaintLigneDevis
     */
    public Long getIdentifaintLigneDevis() {
        return identifaintLigneDevis;
    }

    /**
     * Set the identifaintLigneDevis value.
     * @param identifaintLigneDevis the identifaintLigneDevis to set
     */
    public void setIdentifaintLigneDevis(Long identifaintLigneDevis) {
        this.identifaintLigneDevis = identifaintLigneDevis;
    }

    /**
     * Get the identifiantFinaliteLigneDevis value.
     * @return the identifiantFinaliteLigneDevis
     */
    public Long getIdentifiantFinaliteLigneDevis() {
        return identifiantFinaliteLigneDevis;
    }

    /**
     * Set the identifiantFinaliteLigneDevis value.
     * @param identifiantFinaliteLigneDevis the identifiantFinaliteLigneDevis to set
     */
    public void setIdentifiantFinaliteLigneDevis(Long identifiantFinaliteLigneDevis) {
        this.identifiantFinaliteLigneDevis = identifiantFinaliteLigneDevis;
    }

    /**
     * Get the selectionPourAdhesion value.
     * @return the selectionPourAdhesion
     */
    public Boolean getSelectionPourAdhesion() {
        return selectionPourAdhesion;
    }

    /**
     * Set the selectionPourAdhesion value.
     * @param selectionPourAdhesion the selectionPourAdhesion to set
     */
    public void setSelectionPourAdhesion(Boolean selectionPourAdhesion) {
        this.selectionPourAdhesion = selectionPourAdhesion;
    }
}

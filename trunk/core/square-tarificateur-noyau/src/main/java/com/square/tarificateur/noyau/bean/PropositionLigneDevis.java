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
package com.square.tarificateur.noyau.bean;

import com.square.tarificateur.noyau.dto.devis.LigneDevisDto;

/**
 * Dto pour proposition ajout ligne de devis.
 * @author Goumard stephane (stephane.goumard@scub.net) - SCUB
 */
public class PropositionLigneDevis {

    /** Ligne de devis. */
    private LigneDevisDto ligneDevis;

    /** Identifiant du devis. */
    private Long identifiantDevis;

    /**
     * Get the ligneDevis value.
     * @return the ligneDevis
     */
    public LigneDevisDto getLigneDevis() {
        return ligneDevis;
    }

    /**
     * Set the ligneDevis value.
     * @param ligneDevis the ligneDevis to set
     */
    public void setLigneDevis(LigneDevisDto ligneDevis) {
        this.ligneDevis = ligneDevis;
    }

    /**
     * Get the identifiantDevis value.
     * @return the identifiantDevis
     */
    public Long getIdentifiantDevis() {
        return identifiantDevis;
    }

    /**
     * Set the identifiantDevis value.
     * @param identifiantDevis the identifiantDevis to set
     */
    public void setIdentifiantDevis(Long identifiantDevis) {
        this.identifiantDevis = identifiantDevis;
    }
}

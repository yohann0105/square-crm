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
package com.square.composant.tarificateur.square.client.model.opportunite.devis;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model Gwt pour enregistrer la finalité d'une ligne de devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class EnregistrementFinaliteLigneDevisModel implements IsSerializable {

    /** Identifiant ligne devis. */
    private Long identifiantLigneDevis;

    /** Identifiant finalite ligne devis. */
    private Long identifiantFinaliteLigneDevis;

    /**
     * Constructeur.
     */
    public EnregistrementFinaliteLigneDevisModel() {
        super();
    }

    /**
     * Constructeur.
     * @param identifiantLigneDevis .
     * @param identifiantFinaliteLigneDevis .
     */
    public EnregistrementFinaliteLigneDevisModel(Long identifiantLigneDevis, Long identifiantFinaliteLigneDevis) {
        super();
        this.identifiantLigneDevis = identifiantLigneDevis;
        this.identifiantFinaliteLigneDevis = identifiantFinaliteLigneDevis;
    }

    /**
     * Récupère la valeur de identifiantLigneDevis.
     * @return the identifiantLigneDevis
     */
    public Long getIdentifiantLigneDevis() {
        return identifiantLigneDevis;
    }

    /**
     * Définit la valeur de identifiantLigneDevis.
     * @param identifiantLigneDevis the identifiantLigneDevis to set
     */
    public void setIdentifiantLigneDevis(Long identifiantLigneDevis) {
        this.identifiantLigneDevis = identifiantLigneDevis;
    }

    /**
     * Récupère la valeur de identifiantFinaliteLigneDevis.
     * @return the identifiantFinaliteLigneDevis
     */
    public Long getIdentifiantFinaliteLigneDevis() {
        return identifiantFinaliteLigneDevis;
    }

    /**
     * Définit la valeur de identifiantFinaliteLigneDevis.
     * @param identifiantFinaliteLigneDevis the identifiantFinaliteLigneDevis to set
     */
    public void setIdentifiantFinaliteLigneDevis(Long identifiantFinaliteLigneDevis) {
        this.identifiantFinaliteLigneDevis = identifiantFinaliteLigneDevis;
    }
}

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
 * DTO qui encapsule les coordonnées bancaires.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class CoordonneesBancairesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Le numéro du contrat. */
    private String numeroContrat;

    /** Informations de paiement. */
    private InfosPaiementDto infosPaiement;

    /** Informations de banque. */
    private InfosBanqueDto infosBanque;

    /**
     * Get the numeroContrat value.
     * @return the numeroContrat
     */
    public String getNumeroContrat() {
        return numeroContrat;
    }

    /**
     * Set the numeroContrat value.
     * @param numeroContrat the numeroContrat to set
     */
    public void setNumeroContrat(String numeroContrat) {
        this.numeroContrat = numeroContrat;
    }

    /**
     * Get the infosBanque value.
     * @return the infosBanque
     */
    public InfosBanqueDto getInfosBanque() {
        return infosBanque;
    }

    /**
     * Set the infosBanque value.
     * @param infosBanque the infosBanque to set
     */
    public void setInfosBanque(InfosBanqueDto infosBanque) {
        this.infosBanque = infosBanque;
    }

    /**
     * Get the infosPaiement value.
     * @return the infosPaiement
     */
    public InfosPaiementDto getInfosPaiement() {
        return infosPaiement;
    }

    /**
     * Set the infosPaiement value.
     * @param infosPaiement the infosPaiement to set
     */
    public void setInfosPaiement(InfosPaiementDto infosPaiement) {
        this.infosPaiement = infosPaiement;
    }
}

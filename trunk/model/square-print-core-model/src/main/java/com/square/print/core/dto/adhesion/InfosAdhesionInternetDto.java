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
package com.square.print.core.dto.adhesion;

import java.io.Serializable;

/**
 * Modélise les informations supplémentaires lors d'une adhésion à partir du site Web de la SMATIS.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net)
 */
public class InfosAdhesionInternetDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6750911754733922806L;

    /** Type de paiement par carte bancaire. */
    public static final String TYPE_PAIEMENT_CARTE_BANCAIRE = "TYPE_PAIEMENT_CARTE_BANCAIRE";

    /** Type de paiement par chèque. */
    public static final String TYPE_PAIEMENT_CHEQUE = "TYPE_PAIEMENT_CHEQUE";

    /** Montant premier reglement. **/
    private Float montantPremierReglement;

    /** Type de paiement pour le premier règlement. */
    private String typePaiement;

    /** Détermine si la télétransmission a été demandée. */
    private Boolean teletransmission;

    /**
     * Constructeur.
     * @param montantPremierReglement le montant du premier règlement effectué
     * @param typePaiement le type de paiement pour le premier règlement
     * @param teletransmission demande de télétransmission oui/non
     */
    public InfosAdhesionInternetDto(Float montantPremierReglement, String typePaiement, Boolean teletransmission) {
        super();
        this.montantPremierReglement = montantPremierReglement;
        this.typePaiement = typePaiement;
        this.teletransmission = teletransmission;
    }

    /**
     * Get the montantPremierReglement value.
     * @return the montantPremierReglement
     */
    public Float getMontantPremierReglement() {
        return montantPremierReglement;
    }

    /**
     * Set the montantPremierReglement value.
     * @param montantPremierReglement the montantPremierReglement to set
     */
    public void setMontantPremierReglement(Float montantPremierReglement) {
        this.montantPremierReglement = montantPremierReglement;
    }

    /**
     * Get the typePaiement value.
     * @return the typePaiement
     */
    public String getTypePaiement() {
        return typePaiement;
    }

    /**
     * Set the typePaiement value.
     * @param typePaiement the typePaiement to set
     */
    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    /**
     * Get the teletransmission value.
     * @return the teletransmission
     */
    public Boolean getTeletransmission() {
        return teletransmission;
    }

    /**
     * Set the teletransmission value.
     * @param teletransmission the teletransmission to set
     */
    public void setTeletransmission(Boolean teletransmission) {
        this.teletransmission = teletransmission;
    }
}

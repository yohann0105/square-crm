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
package com.square.composant.tarificateur.square.client.model.adhesion;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model des infos de paiement.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class InfosPaiementModel implements IsSerializable {

    /** Montant du paiement. */
    private Float montantPaiement;

    /** Numéro de la transaction. */
    private String numeroTransaction;

    /** Identifiant du moyen de paiement utilisé. */
    private IdentifiantLibelleGwt moyenPaiement;

    /** Identifiant de la périodicité de paiement choisie. */
    private IdentifiantLibelleGwt periodicitePaiement;

    /** Identifiant du jour du mois pour le paiement choisi. */
    private IdentifiantLibelleGwt jourPaiement;

    /** Date de signature de l'adhésion. */
    private String dateSignature;

    /**
     * Get the montantPaiement value.
     * @return the montantPaiement
     */
    public Float getMontantPaiement() {
        return montantPaiement;
    }

    /**
     * Set the montantPaiement value.
     * @param montantPaiement the montantPaiement to set
     */
    public void setMontantPaiement(Float montantPaiement) {
        this.montantPaiement = montantPaiement;
    }

    /**
     * Get the numeroTransaction value.
     * @return the numeroTransaction
     */
    public String getNumeroTransaction() {
        return numeroTransaction;
    }

    /**
     * Set the numeroTransaction value.
     * @param numeroTransaction the numeroTransaction to set
     */
    public void setNumeroTransaction(String numeroTransaction) {
        this.numeroTransaction = numeroTransaction;
    }

    /**
     * Get the moyenPaiement value.
     * @return the moyenPaiement
     */
    public IdentifiantLibelleGwt getMoyenPaiement() {
        return moyenPaiement;
    }

    /**
     * Set the moyenPaiement value.
     * @param moyenPaiement the moyenPaiement to set
     */
    public void setMoyenPaiement(IdentifiantLibelleGwt moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    /**
     * Get the periodicitePaiement value.
     * @return the periodicitePaiement
     */
    public IdentifiantLibelleGwt getPeriodicitePaiement() {
        return periodicitePaiement;
    }

    /**
     * Set the periodicitePaiement value.
     * @param periodicitePaiement the periodicitePaiement to set
     */
    public void setPeriodicitePaiement(IdentifiantLibelleGwt periodicitePaiement) {
        this.periodicitePaiement = periodicitePaiement;
    }

    /**
     * Get the jourPaiement value.
     * @return the jourPaiement
     */
    public IdentifiantLibelleGwt getJourPaiement() {
        return jourPaiement;
    }

    /**
     * Set the jourPaiement value.
     * @param jourPaiement the jourPaiement to set
     */
    public void setJourPaiement(IdentifiantLibelleGwt jourPaiement) {
        this.jourPaiement = jourPaiement;
    }

    /**
     * Get the dateSignature value.
     * @return the dateSignature
     */
    public String getDateSignature() {
        return dateSignature;
    }

    /**
     * Set the dateSignature value.
     * @param dateSignature the dateSignature to set
     */
    public void setDateSignature(String dateSignature) {
        this.dateSignature = dateSignature;
    }

}

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
package com.square.tarificateur.noyau.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 * DTO modélisant les informations de paiement nécessaires à une adhésion.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class InfosPaiementDto implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 859617790281113459L;

    /** Montant du paiement. */
    private Float montantPaiement;

    /** Identifiant du moyen de paiement utilisé. */
    private Long idMoyenPaiement;

    /** Identifiant de la périodicité de paiement choisie. */
    private Long idPeriodicitePaiement;

    /** Identifiant du jour du mois pour le paiement choisi. */
    private Long idJourPaiement;

    /** Date de signature de l'adhésion. */
    private Calendar dateSignature;

    /** Identifiant du moyen de paiement choisi pour le premier règlement lors de l'adhésion. */
    private Long idMoyenPaiementPremierReglement;

    /** Numéro de la transaction. */
    private String numeroTransaction;

    /**
     * Getter function.
     * @return the montantPaiement
     */
    public Float getMontantPaiement() {
        return montantPaiement;
    }

    /**
     * Getter function.
     * @return the numeroTransaction
     */
    public String getNumeroTransaction() {
        return numeroTransaction;
    }

    /**
     * Getter function.
     * @return the idMoyenPaiement
     */
    public Long getIdMoyenPaiement() {
        return idMoyenPaiement;
    }

    /**
     * Getter function.
     * @return the idPeriodicitePaiement
     */
    public Long getIdPeriodicitePaiement() {
        return idPeriodicitePaiement;
    }

    /**
     * Getter function.
     * @return the idJourPaiement
     */
    public Long getIdJourPaiement() {
        return idJourPaiement;
    }

    /**
     * Getter function.
     * @return the dateSignature
     */
    public Calendar getDateSignature() {
        return dateSignature;
    }

    /**
     * Setter function.
     * @param montantPaiement the montantPaiement to set
     */
    public void setMontantPaiement(Float montantPaiement) {
        this.montantPaiement = montantPaiement;
    }

    /**
     * Setter function.
     * @param numeroTransaction the numeroTransaction to set
     */
    public void setNumeroTransaction(String numeroTransaction) {
        this.numeroTransaction = numeroTransaction;
    }

    /**
     * Setter function.
     * @param idMoyenPaiement the idMoyenPaiement to set
     */
    public void setIdMoyenPaiement(Long idMoyenPaiement) {
        this.idMoyenPaiement = idMoyenPaiement;
    }

    /**
     * Setter function.
     * @param idPeriodicitePaiement the idPeriodicitePaiement to set
     */
    public void setIdPeriodicitePaiement(Long idPeriodicitePaiement) {
        this.idPeriodicitePaiement = idPeriodicitePaiement;
    }

    /**
     * Setter function.
     * @param idJourPaiement the idJourPaiement to set
     */
    public void setIdJourPaiement(Long idJourPaiement) {
        this.idJourPaiement = idJourPaiement;
    }

    /**
     * Setter function.
     * @param dateSignature the dateSignature to set
     */
    public void setDateSignature(Calendar dateSignature) {
        this.dateSignature = dateSignature;
    }

    /**
     * Getter function.
     * @return the idMoyenPaiementPremierReglement
     */
    public Long getIdMoyenPaiementPremierReglement() {
        return idMoyenPaiementPremierReglement;
    }

    /**
     * Setter function.
     * @param idMoyenPaiementPremierReglement the idMoyenPaiementPremierReglement to set
     */
    public void setIdMoyenPaiementPremierReglement(Long idMoyenPaiementPremierReglement) {
        this.idMoyenPaiementPremierReglement = idMoyenPaiementPremierReglement;
    }

}

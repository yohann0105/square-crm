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
package com.square.adherent.noyau.dto.cotisation;

import java.io.Serializable;
import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto qui représente un détail d'encaissement.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class DetailEncaissementDto implements Serializable {

    private static final long serialVersionUID = -8521660612118470935L;

    /** Montant. */
    private Float montant;

    /** Montant non affecté. */
    private Float montantNonAffecte;

    /** Identifiant de la garantie. */
    private Calendar date;

    /** Banque. */
    private String banque;

    /** Compte. */
    private String compte;

    /** Moyen de paiement. */
    private IdentifiantLibelleDto moyenPaiement;

    /** Jour de paiement. */
    private Calendar jourPaiement;

    /** Numero du cheque. */
    private String numeroCheque;

    /** Motif du rejet. */
    private String motifRejet;

    /** Date du rejet. */
    private Calendar dateRejet;

    /** Statut. */
    private CodeAiaLibelleDto statut;

    /**
     * Get the montant value.
     * @return the montant
     */
    public Float getMontant() {
        return montant;
    }

    /**
     * Set the montant value.
     * @param montant the montant to set
     */
    public void setMontant(Float montant) {
        this.montant = montant;
    }

    /**
     * Get the montantNonAffecte value.
     * @return the montantNonAffecte
     */
    public Float getMontantNonAffecte() {
        return montantNonAffecte;
    }

    /**
     * Set the montantNonAffecte value.
     * @param montantNonAffecte the montantNonAffecte to set
     */
    public void setMontantNonAffecte(Float montantNonAffecte) {
        this.montantNonAffecte = montantNonAffecte;
    }

    /**
     * Get the date value.
     * @return the date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * Set the date value.
     * @param date the date to set
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Get the banque value.
     * @return the banque
     */
    public String getBanque() {
        return banque;
    }

    /**
     * Set the banque value.
     * @param banque the banque to set
     */
    public void setBanque(String banque) {
        this.banque = banque;
    }

    /**
     * Get the compte value.
     * @return the compte
     */
    public String getCompte() {
        return compte;
    }

    /**
     * Set the compte value.
     * @param compte the compte to set
     */
    public void setCompte(String compte) {
        this.compte = compte;
    }

    /**
     * Get the moyenPaiement value.
     * @return the moyenPaiement
     */
    public IdentifiantLibelleDto getMoyenPaiement() {
        return moyenPaiement;
    }

    /**
     * Set the moyenPaiement value.
     * @param moyenPaiement the moyenPaiement to set
     */
    public void setMoyenPaiement(IdentifiantLibelleDto moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    /**
     * Get the jourPaiement value.
     * @return the jourPaiement
     */
    public Calendar getJourPaiement() {
        return jourPaiement;
    }

    /**
     * Set the jourPaiement value.
     * @param jourPaiement the jourPaiement to set
     */
    public void setJourPaiement(Calendar jourPaiement) {
        this.jourPaiement = jourPaiement;
    }

    /**
     * Get the numeroCheque value.
     * @return the numeroCheque
     */
    public String getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * Set the numeroCheque value.
     * @param numeroCheque the numeroCheque to set
     */
    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    /**
     * Get the motifRejet value.
     * @return the motifRejet
     */
    public String getMotifRejet() {
        return motifRejet;
    }

    /**
     * Set the motifRejet value.
     * @param motifRejet the motifRejet to set
     */
    public void setMotifRejet(String motifRejet) {
        this.motifRejet = motifRejet;
    }

    /**
     * Get the dateRejet value.
     * @return the dateRejet
     */
    public Calendar getDateRejet() {
        return dateRejet;
    }

    /**
     * Set the dateRejet value.
     * @param dateRejet the dateRejet to set
     */
    public void setDateRejet(Calendar dateRejet) {
        this.dateRejet = dateRejet;
    }

    /**
     * Get the statut value.
     * @return the statut
     */
    public CodeAiaLibelleDto getStatut() {
        return statut;
    }

    /**
     * Set the statut value.
     * @param statut the statut to set
     */
    public void setStatut(CodeAiaLibelleDto statut) {
        this.statut = statut;
    }

}

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
package com.square.adherent.noyau.dto.prestation;

import java.io.Serializable;
import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto pour les entetes de prestations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class EntetePrestationResultatRechercheDto implements Serializable {

    /** SVUID. */
    private static final long serialVersionUID = -7111674668106076994L;

    /** Le numero de decompte. */
    private String numero;

    /** Date du reglement. */
    private Calendar dateReglement;

    /** Date de debut des soins. */
    private Calendar dateDebutSoins;

    /** Date de fin des soins. */
    private Calendar dateFinSoins;

    /** Montant du remboursement Smatis. */
    private Double remboursementSmatis;

    /** Origine. */
    private IdentifiantLibelleDto origine;

    /** Numero du cheque. */
    private String numeroCheque;

    /** Domiciliation de la banque. */
    private String domiciliation;

    /** Numero de compte. */
    private String compte;

    /** Professionnel de santé. */
    private String professionnelSante;

    /** L'identifiant / le nom / prénom du bénéficiaire des soins. */
    private IdentifiantLibelleDto beneficiaireSoins;

    /** Nom du destinataire des relevés de prestations. */
    private String nomDestinataire;

    /** Nom du destinataire du règlement. */
    private String nomDestinataireReglement;

    /** Statut du paiement. */
    private String statutPaiement;

    /**
     * Get the numero value.
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Set the numero value.
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Get the dateReglement value.
     * @return the dateReglement
     */
    public Calendar getDateReglement() {
        return dateReglement;
    }

    /**
     * Set the dateReglement value.
     * @param dateReglement the dateReglement to set
     */
    public void setDateReglement(Calendar dateReglement) {
        this.dateReglement = dateReglement;
    }

    /**
     * Get the remboursementSmatis value.
     * @return the remboursementSmatis
     */
    public Double getRemboursementSmatis() {
        return remboursementSmatis;
    }

    /**
     * Set the remboursementSmatis value.
     * @param remboursementSmatis the remboursementSmatis to set
     */
    public void setRemboursementSmatis(Double remboursementSmatis) {
        this.remboursementSmatis = remboursementSmatis;
    }

    /**
     * Get the origine value.
     * @return the origine
     */
    public IdentifiantLibelleDto getOrigine() {
        return origine;
    }

    /**
     * Set the origine value.
     * @param origine the origine to set
     */
    public void setOrigine(IdentifiantLibelleDto origine) {
        this.origine = origine;
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
     * Get the domiciliation value.
     * @return the domiciliation
     */
    public String getDomiciliation() {
        return domiciliation;
    }

    /**
     * Set the domiciliation value.
     * @param domiciliation the domiciliation to set
     */
    public void setDomiciliation(String domiciliation) {
        this.domiciliation = domiciliation;
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
     * Get the nomDestinataire value.
     * @return the nomDestinataire
     */
    public String getNomDestinataire() {
        return nomDestinataire;
    }

    /**
     * Set the nomDestinataire value.
     * @param nomDestinataire the nomDestinataire to set
     */
    public void setNomDestinataire(String nomDestinataire) {
        this.nomDestinataire = nomDestinataire;
    }

    /**
     * Get the professionnelSante value.
     * @return the professionnelSante
     */
    public String getProfessionnelSante() {
        return professionnelSante;
    }

    /**
     * Set the professionnelSante value.
     * @param professionnelSante the professionnelSante to set
     */
    public void setProfessionnelSante(String professionnelSante) {
        this.professionnelSante = professionnelSante;
    }

    /**
     * Get the dateDebutSoins value.
     * @return the dateDebutSoins
     */
    public Calendar getDateDebutSoins() {
        return dateDebutSoins;
    }

    /**
     * Set the dateDebutSoins value.
     * @param dateDebutSoins the dateDebutSoins to set
     */
    public void setDateDebutSoins(Calendar dateDebutSoins) {
        this.dateDebutSoins = dateDebutSoins;
    }

    /**
     * Get the dateFinSoins value.
     * @return the dateFinSoins
     */
    public Calendar getDateFinSoins() {
        return dateFinSoins;
    }

    /**
     * Set the dateFinSoins value.
     * @param dateFinSoins the dateFinSoins to set
     */
    public void setDateFinSoins(Calendar dateFinSoins) {
        this.dateFinSoins = dateFinSoins;
    }

	/**
	 * Récupère la valeur nomDestinataireReglement.
	 * @return the nomDestinataireReglement
	 */
	public String getNomDestinataireReglement() {
		return nomDestinataireReglement;
	}

	/**
	 * Définit la valeur de nomDestinataireReglement.
	 * @param nomDestinataireReglement the nomDestinataireReglement to set
	 */
	public void setNomDestinataireReglement(String nomDestinataireReglement) {
		this.nomDestinataireReglement = nomDestinataireReglement;
	}

    /**
     * Get the statutPaiement value.
     * @return the statutPaiement
     */
    public String getStatutPaiement() {
        return statutPaiement;
    }

    /**
     * Set the statutPaiement value.
     * @param statutPaiement the statutPaiement to set
     */
    public void setStatutPaiement(String statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    /**
     * Getter function.
     * @return the beneficiaireSoins
     */
    public IdentifiantLibelleDto getBeneficiaireSoins() {
        return beneficiaireSoins;
    }

    /**
     * Setter function.
     * @param beneficiaireSoins the beneficiaireSoins to set
     */
    public void setBeneficiaireSoins(IdentifiantLibelleDto beneficiaireSoins) {
        this.beneficiaireSoins = beneficiaireSoins;
    }
}

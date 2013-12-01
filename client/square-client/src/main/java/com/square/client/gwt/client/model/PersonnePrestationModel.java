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
package com.square.client.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model pour les résultats de la recherche de prestations.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class PersonnePrestationModel implements IsSerializable {
	
	/**
	 * Serial Version UID.
	 */	
	private static final long serialVersionUID = -7111674668106076994L;
	
	/** Identifiant. */
	private Long id;
   
	/**
     * le numero de decompte.
     */
    private String numero;

    /**
     * Date de debut des soins.
     */
    private String dateDebutSoins;

    /**
     * Date du reglement.
     */
    private String dateReglement;

    /**
     * Date de fin des soins.
     */
    private String dateFinSoins;

    /**
     * Nom et prenom beneficiaire des soins.
     */
    private String nomBeneficiaire;

    /**
     * Prenom beneficiaire.
     */
    private String prenomBeneficiaire;

    /**
     * Montant du decompte.
     */
    private Double montant;

    /**
     * Acte.
     */
    private String libelleActe;

    /** Libelle de l'ORIGINE. */
    private String libelleOrigine;

    /**
     * Montant du rembrousement Securite sociale.
     */
    private Double remboursementSecu;

    /**
     * Montant du rembrousement Securite sociale.
     */
    private Double remboursementCompl;

    /**
     * Montant du rembrousement Securite sociale.
     */
    private Double resteACharge;

    /**
     * Numero du cheque.
     */
    private String numeroCheque;

    /**
     * Domiciliation de la banque.
     */
    private String domiciliation;

    /**
     * numero de compte.
     */
    private String compte;

    /**
     * Identifiant unique de l'assuré.
     */
    private Long uidAssure;

    /**
     * Identifiant unique de patient.
     */
    private Long uidLigPatient;

    /**
     * Statut du paiement.
     */
    private String statutPaiement;

    /** Nom du destinataire. */
    private String nomDestinataire;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * @return the dateDebutSoins
	 */
	public String getDateDebutSoins() {
		return dateDebutSoins;
	}
	/**
	 * @param dateDebutSoins the dateDebutSoins to set
	 */
	public void setDateDebutSoins(String dateDebutSoins) {
		this.dateDebutSoins = dateDebutSoins;
	}
	/**
	 * @return the dateReglement
	 */
	public String getDateReglement() {
		return dateReglement;
	}
	/**
	 * @param dateReglement the dateReglement to set
	 */
	public void setDateReglement(String dateReglement) {
		this.dateReglement = dateReglement;
	}
	/**
	 * @return the dateFinSoins
	 */
	public String getDateFinSoins() {
		return dateFinSoins;
	}
	/**
	 * @param dateFinSoins the dateFinSoins to set
	 */
	public void setDateFinSoins(String dateFinSoins) {
		this.dateFinSoins = dateFinSoins;
	}
	/**
	 * @return the nomBeneficiaire
	 */
	public String getNomBeneficiaire() {
		return nomBeneficiaire;
	}
	/**
	 * @param nomBeneficiaire the nomBeneficiaire to set
	 */
	public void setNomBeneficiaire(String nomBeneficiaire) {
		this.nomBeneficiaire = nomBeneficiaire;
	}
	/**
	 * @return the prenomBeneficiaire
	 */
	public String getPrenomBeneficiaire() {
		return prenomBeneficiaire;
	}
	/**
	 * @param prenomBeneficiaire the prenomBeneficiaire to set
	 */
	public void setPrenomBeneficiaire(String prenomBeneficiaire) {
		this.prenomBeneficiaire = prenomBeneficiaire;
	}
	/**
	 * @return the montant
	 */
	public Double getMontant() {
		return montant;
	}
	/**
	 * @param montant the montant to set
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	/**
	 * @return the libelleActe
	 */
	public String getLibelleActe() {
		return libelleActe;
	}
	/**
	 * @param libelleActe the libelleActe to set
	 */
	public void setLibelleActe(String libelleActe) {
		this.libelleActe = libelleActe;
	}
	/**
	 * @return the libelleOrigine
	 */
	public String getLibelleOrigine() {
		return libelleOrigine;
	}
	/**
	 * @param libelleOrigine the libelleOrigine to set
	 */
	public void setLibelleOrigine(String libelleOrigine) {
		this.libelleOrigine = libelleOrigine;
	}
	/**
	 * @return the remboursementSecu
	 */
	public Double getRemboursementSecu() {
		return remboursementSecu;
	}
	/**
	 * @param remboursementSecu the remboursementSecu to set
	 */
	public void setRemboursementSecu(Double remboursementSecu) {
		this.remboursementSecu = remboursementSecu;
	}
	/**
	 * @return the remboursementCompl
	 */
	public Double getRemboursementCompl() {
		return remboursementCompl;
	}
	/**
	 * @param remboursementCompl the remboursementCompl to set
	 */
	public void setRemboursementCompl(Double remboursementCompl) {
		this.remboursementCompl = remboursementCompl;
	}
	/**
	 * @return the resteACharge
	 */
	public Double getResteACharge() {
		return resteACharge;
	}
	/**
	 * @param resteACharge the resteACharge to set
	 */
	public void setResteACharge(Double resteACharge) {
		this.resteACharge = resteACharge;
	}
	/**
	 * @return the numeroCheque
	 */
	public String getNumeroCheque() {
		return numeroCheque;
	}
	/**
	 * @param numeroCheque the numeroCheque to set
	 */
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	/**
	 * @return the domiciliation
	 */
	public String getDomiciliation() {
		return domiciliation;
	}
	/**
	 * @param domiciliation the domiciliation to set
	 */
	public void setDomiciliation(String domiciliation) {
		this.domiciliation = domiciliation;
	}
	/**
	 * @return the compte
	 */
	public String getCompte() {
		return compte;
	}
	/**
	 * @param compte the compte to set
	 */
	public void setCompte(String compte) {
		this.compte = compte;
	}
	/**
	 * @return the uidAssure
	 */
	public Long getUidAssure() {
		return uidAssure;
	}
	/**
	 * @param uidAssure the uidAssure to set
	 */
	public void setUidAssure(Long uidAssure) {
		this.uidAssure = uidAssure;
	}
	/**
	 * @return the uidLigPatient
	 */
	public Long getUidLigPatient() {
		return uidLigPatient;
	}
	/**
	 * @param uidLigPatient the uidLigPatient to set
	 */
	public void setUidLigPatient(Long uidLigPatient) {
		this.uidLigPatient = uidLigPatient;
	}
	/**
	 * @return the statutPaiement
	 */
	public String getStatutPaiement() {
		return statutPaiement;
	}
	/**
	 * @param statutPaiement the statutPaiement to set
	 */
	public void setStatutPaiement(String statutPaiement) {
		this.statutPaiement = statutPaiement;
	}
    /**
     * Getter function.
     * @return the nomDestinataire
     */
    public String getNomDestinataire() {
        return nomDestinataire;
    }
    /**
     * Setter function.
     * @param nomDestinataire the nomDestinataire to set
     */
    public void setNomDestinataire(String nomDestinataire) {
        this.nomDestinataire = nomDestinataire;
    }

}

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
package com.square.adherent.noyau.model.data.prestation;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.square.adherent.noyau.model.dimension.prestation.DecompteActe;
import com.square.adherent.noyau.model.dimension.prestation.DecompteOrigine;

/**
 * Entité persistante modélisant une décompte.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
@Entity
@Table(name = "DATA_DECOMPTE")
public class Decompte implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6314432474358256920L;

    /** Identifiant. */
    @Id
    @Column(name = "DECOMPTE_UID")
    private Long id;

    /**
     * le numero de decompte.
     */
    @Column(name = "DECOMPTE_NUMERO")
    private String numero;

    /**
     * Identifiant unique de l'assuré.
     */
    @Column(name = "DECOMPTE_ASSURE_UID")
    private Long uidAssure;

    /**
     * Identifiant unique de patient.
     */
    @Column(name = "DECOMPTE_LIG_PATIENT_UID")
    private Long uidLigPatient;

    /**
     * Date de debut des soins.
     */
    @Column(name = "DECOMPTE_LIG_DATE_DEBUT_SOIN")
    private Calendar dateDebutSoins;

    /**
     * Date du reglement.
     */
    @Column(name = "DECOMPTE_DATE_REGLEMENT")
    private Calendar dateReglement;

    /**
     * Date de fin des soins.
     */
    @Column(name = "DECOMPTE_LIG_DATE_FIN_SOIN")
    private Calendar dateFinSoins;

    /**
     * Montant du decompte.
     */
    @Column(name = "DECOMPTE_LIG_MONTANT")
    private Double montant;

    /**
     * Montant du remboursement Securite sociale.
     */
    @Column(name = "DECOMPTE_LIG_REMB_SECU")
    private Double remboursementSecu;

    /**
     * Montant du remboursement Complementaire.
     */
    @Column(name = "DECOMPTE_LIG_REMB_COMPL")
    private Double remboursementCompl;

    /**
     * Base de remboursement du Regime Obligatoire.
     */
    @Column(name = "DECOMPTE_LIG_MT_BASEREMB_RO")
    private Double baseRemboursementRO;

    /**
     * Taux de remboursement du Regime Obligatoire.
     */
    @Column(name = "DECOMPTE_LIG_TX_REMB_RO")
    private Double tauxRemboursementRO;

    /**
     * Numero du cheque.
     */
    @Column(name = "DECOMPTE_REGLEMENT_NUMERO_CHEQUE")
    private String numeroCheque;

    /**
     * Acte du decompte.
     */
    @ManyToOne
    @JoinColumn(name = "DECOMPTE_LIG_ACTE_UID")
    private DecompteActe acte;

    /**
     * Origine du decompte.
     */
    @ManyToOne
    @JoinColumn(name = "DECOMPTE_ORIGINE_UID")
    private DecompteOrigine origine;

    /**
     * Banque associé au reglement du decompte.
     */
    @ManyToOne
    @JoinColumn(name = "DECOMPTE_REGLEMENT_BANQUE_UID")
    private Banque banque;

    /**
     * Statut du paiement.
     */
    @Column(name = "DECOMPTE_STATUT_PAIEMENT")
    private String statutPaiement;

    /**
     * Professionnel de santé.
     */
    @Column(name = "DECOMPTE_REGLEMENT_PROFESSIONNEL_SANTE")
    private String professionnelSante;

    /**
     * Nom du destinataire du relevé de prestation.
     */
    @Column(name = "DECOMPTE_DESTINATAIRE_NOM")
    private String nomDestinataire;

    /**
     * Nom du destinataire du règlement.
     */
    @Column(name = "DECOMPTE_BENEF_REGLEMENT_NOM")
    private String nomDestinataireReglement;

    /**
     * Numero qui permet de coupler une ligne de decompte à sa ligne "NonRemboursable".
     */
    @Column(name = "DECOMPTE_LIG_C_NUM")
    private Long numeroLigneDecompteCouplage;

    /** Identifiant de la nature de règlement. */
    @Column(name = "DECOMPTE_BENEF_REGLMT_NATURE_ID")
    private String idNatureReglement;

    /**
     * Recuperer la valeur.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Fixer la valeur.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Recuperer la valeur.
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Fixer la valeur.
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Recuperer la valeur.
     * @return the uidAssure
     */
    public Long getUidAssure() {
        return uidAssure;
    }

    /**
     * Fixer la valeur.
     * @param uidAssure the uidAssure to set
     */
    public void setUidAssure(Long uidAssure) {
        this.uidAssure = uidAssure;
    }

    /**
     * Recuperer la valeur.
     * @return the uidLigPatient
     */
    public Long getUidLigPatient() {
        return uidLigPatient;
    }

    /**
     * Fixer la valeur.
     * @param uidLigPatient the uidLigPatient to set
     */
    public void setUidLigPatient(Long uidLigPatient) {
        this.uidLigPatient = uidLigPatient;
    }

    /**
     * Recuperer la valeur.
     * @return the dateDebutSoins
     */
    public Calendar getDateDebutSoins() {
        return dateDebutSoins;
    }

    /**
     * Fixer la valeur.
     * @param dateDebutSoins the dateDebutSoins to set
     */
    public void setDateDebutSoins(Calendar dateDebutSoins) {
        this.dateDebutSoins = dateDebutSoins;
    }

    /**
     * Recuperer la valeur.
     * @return the dateReglement
     */
    public Calendar getDateReglement() {
        return dateReglement;
    }

    /**
     * Fixer la valeur.
     * @param dateReglement the dateReglement to set
     */
    public void setDateReglement(Calendar dateReglement) {
        this.dateReglement = dateReglement;
    }

    /**
     * Recuperer la valeur.
     * @return the dateFinSoins
     */
    public Calendar getDateFinSoins() {
        return dateFinSoins;
    }

    /**
     * Fixer la valeur.
     * @param dateFinSoins the dateFinSoins to set
     */
    public void setDateFinSoins(Calendar dateFinSoins) {
        this.dateFinSoins = dateFinSoins;
    }

    /**
     * Recuperer la valeur.
     * @return the montant
     */
    public Double getMontant() {
        return montant;
    }

    /**
     * Fixer la valeur.
     * @param montant the montant to set
     */
    public void setMontant(Double montant) {
        this.montant = montant;
    }

    /**
     * Recuperer la valeur.
     * @return the remboursementSecu
     */
    public Double getRemboursementSecu() {
        return remboursementSecu;
    }

    /**
     * Fixer la valeur.
     * @param remboursementSecu the remboursementSecu to set
     */
    public void setRemboursementSecu(Double remboursementSecu) {
        this.remboursementSecu = remboursementSecu;
    }

    /**
     * Recuperer la valeur.
     * @return the remboursementCompl
     */
    public Double getRemboursementCompl() {
        return remboursementCompl;
    }

    /**
     * Fixer la valeur.
     * @param remboursementCompl the remboursementCompl to set
     */
    public void setRemboursementCompl(Double remboursementCompl) {
        this.remboursementCompl = remboursementCompl;
    }

    /**
     * Recuperer la valeur.
     * @return the numeroCheque
     */
    public String getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * Fixer la valeur.
     * @param numeroCheque the numeroCheque to set
     */
    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    /**
     * Recuperer la valeur.
     * @return the acte
     */
    public DecompteActe getActe() {
        return acte;
    }

    /**
     * Fixer la valeur.
     * @param acte the acte to set
     */
    public void setActe(DecompteActe acte) {
        this.acte = acte;
    }

    /**
     * Recuperer la valeur.
     * @return the origine
     */
    public DecompteOrigine getOrigine() {
        return origine;
    }

    /**
     * Fixer la valeur.
     * @param origine the origine to set
     */
    public void setOrigine(DecompteOrigine origine) {
        this.origine = origine;
    }

    /**
     * Recuperer la valeur.
     * @return the banque
     */
    public Banque getBanque() {
        return banque;
    }

    /**
     * Fixer la valeur.
     * @param banque the banque to set
     */
    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    /**
     * Recuperer la valeur.
     * @return the statutPaiement
     */
    public String getStatutPaiement() {
        return statutPaiement;
    }

    /**
     * Fixer la valeur.
     * @param statutPaiement the statutPaiement to set
     */
    public void setStatutPaiement(String statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    /**
     * Récupère la valeur de professionnelSante.
     * @return la valeur de professionnelSante
     */
    public String getProfessionnelSante() {
        return professionnelSante;
    }

    /**
     * Définit la valeur de professionnelSante.
     * @param professionnelSante la nouvelle valeur de professionnelSante
     */
    public void setProfessionnelSante(String professionnelSante) {
        this.professionnelSante = professionnelSante;
    }

    /**
     * Récupère la valeur de nomDestinataire.
     * @return la valeur de nomDestinataire
     */
    public String getNomDestinataire() {
        return nomDestinataire;
    }

    /**
     * Définit la valeur de nomDestinataire.
     * @param nomDestinataire la nouvelle valeur de nomDestinataire
     */
    public void setNomDestinataire(String nomDestinataire) {
        this.nomDestinataire = nomDestinataire;
    }

    /**
     * Get the baseRemboursementRO value.
     * @return the baseRemboursementRO
     */
    public Double getBaseRemboursementRO() {
        return baseRemboursementRO;
    }

    /**
     * Set the baseRemboursementRO value.
     * @param baseRemboursementRO the baseRemboursementRO to set
     */
    public void setBaseRemboursementRO(Double baseRemboursementRO) {
        this.baseRemboursementRO = baseRemboursementRO;
    }

    /**
     * Get the tauxRemboursementRO value.
     * @return the tauxRemboursementRO
     */
    public Double getTauxRemboursementRO() {
        return tauxRemboursementRO;
    }

    /**
     * Set the tauxRemboursementRO value.
     * @param tauxRemboursementRO the tauxRemboursementRO to set
     */
    public void setTauxRemboursementRO(Double tauxRemboursementRO) {
        this.tauxRemboursementRO = tauxRemboursementRO;
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
     * Get the numeroLigneDecompteCouplage value.
     * @return the numeroLigneDecompteCouplage
     */
    public Long getNumeroLigneDecompteCouplage() {
        return numeroLigneDecompteCouplage;
    }

    /**
     * Set the numeroLigneDecompteCouplage value.
     * @param numeroLigneDecompteCouplage the numeroLigneDecompteCouplage to set
     */
    public void setNumeroLigneDecompteCouplage(Long numeroLigneDecompteCouplage) {
        this.numeroLigneDecompteCouplage = numeroLigneDecompteCouplage;
    }

    /**
     * Récupère la valeur de idNatureReglement.
     * @return la valeur de idNatureReglement
     */
    public String getIdNatureReglement() {
        return idNatureReglement;
    }

    /**
     * Définit la valeur de idNatureReglement.
     * @param idNatureReglement la nouvelle valeur de idNatureReglement
     */
    public void setIdNatureReglement(String idNatureReglement) {
        this.idNatureReglement = idNatureReglement;
    }
}

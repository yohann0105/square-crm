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
 * Dto pour les résultats de la recherche.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class PrestationResultatRechercheDto implements Serializable {

    /** SVUID. */
    private static final long serialVersionUID = -7111674668106076994L;

    /** Identifiant. */
    private Long id;

    /** Le numero de decompte. */
    private String numero;

    /** Date de debut des soins. */
    private Calendar dateDebutSoins;

    /** Date du reglement. */
    private Calendar dateReglement;

    /** Date de fin des soins. */
    private Calendar dateFinSoins;

    /** Nom et prenom beneficiaire des soins. */
    private String nomBeneficiaire;

    /** Prenom beneficiaire. */
    private String prenomBeneficiaire;

    /** Montant du decompte. */
    private Double montant;

    /** Acte. */
    private String libelleActe;

    /** Libelle de l'origine. */
    private String libelleOrigine;

    /** Montant du remboursement Securite sociale. */
    private Double remboursementSecu;

    /** Montant du remboursement Complementaire. */
    private Double remboursementCompl;

    /** Base de remboursement du Regime Obligatoire. */
    private Double baseRemboursementRO;

    /** Taux de remboursement du Regime Obligatoire. */
    private Double tauxRemboursementRO;

    /** Montant du reste a charge. */
    private Double resteACharge;

    /** Numero du cheque. */
    private String numeroCheque;

    /** Domiciliation de la banque. */
    private String domiciliation;

    /** Numero de compte. */
    private String compte;

    /** Identifiant unique de l'assuré. */
    private Long uidAssure;

    /** Identifiant unique de patient. */
    private Long uidLigPatient;

    /** Statut du paiement. */
    private String statutPaiement;

    /** Professionnel de santé. */
    private String professionnelSante;

    /** Nom du destinataire du relevé de prestation. */
    private String nomDestinataire;

    /** Nom du destinataire du règlement de la prestation. */
    private String nomDestinataireReglement;

    /** Origine. */
    private IdentifiantLibelleDto origine;

    /** Acte. */
    private IdentifiantLibelleDto acte;

    /** Identifiant de la nature de règlement. */
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
     * @return the nomBeneficiaire
     */
    public String getNomBeneficiaire() {
        return nomBeneficiaire;
    }

    /**
     * Fixer la valeur.
     * @param nomBeneficiaire the nomBeneficiaire to set
     */
    public void setNomBeneficiaire(String nomBeneficiaire) {
        this.nomBeneficiaire = nomBeneficiaire;
    }

    /**
     * Recuperer la valeur.
     * @return the prenomBeneficiaire
     */
    public String getPrenomBeneficiaire() {
        return prenomBeneficiaire;
    }

    /**
     * Fixer la valeur.
     * @param prenomBeneficiaire the prenomBeneficiaire to set
     */
    public void setPrenomBeneficiaire(String prenomBeneficiaire) {
        this.prenomBeneficiaire = prenomBeneficiaire;
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
     * @return the resteACharge
     */
    public Double getResteACharge() {
        return resteACharge;
    }

    /**
     * Fixer la valeur.
     * @param resteACharge the resteACharge to set
     */
    public void setResteACharge(Double resteACharge) {
        this.resteACharge = resteACharge;
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
     * @return the domiciliation
     */
    public String getDomiciliation() {
        return domiciliation;
    }

    /**
     * Fixer la valeur.
     * @param domiciliation the domiciliation to set
     */
    public void setDomiciliation(String domiciliation) {
        this.domiciliation = domiciliation;
    }

    /**
     * Recuperer la valeur.
     * @return the compte
     */
    public String getCompte() {
        return compte;
    }

    /**
     * Fixer la valeur.
     * @param compte the compte to set
     */
    public void setCompte(String compte) {
        this.compte = compte;
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
     * Récupère la valeur de origine.
     * @return la valeur de origine
     */
    public IdentifiantLibelleDto getOrigine() {
        return origine;
    }

    /**
     * Définit la valeur de origine.
     * @param origine la nouvelle valeur de origine
     */
    public void setOrigine(IdentifiantLibelleDto origine) {
        this.origine = origine;
    }

    /**
     * Récupère la valeur de acte.
     * @return la valeur de acte
     */
    public IdentifiantLibelleDto getActe() {
        return acte;
    }

    /**
     * Définit la valeur de acte.
     * @param acte la nouvelle valeur de acte
     */
    public void setActe(IdentifiantLibelleDto acte) {
        this.acte = acte;
    }

    /**
     * Récupère la valeur de libelleActe.
     * @return la valeur de libelleActe
     */
    public String getLibelleActe() {
        return libelleActe;
    }

    /**
     * Définit la valeur de libelleActe.
     * @param libelleActe la nouvelle valeur de libelleActe
     */
    public void setLibelleActe(String libelleActe) {
        this.libelleActe = libelleActe;
    }

    /**
     * Récupère la valeur de libelleOrigine.
     * @return la valeur de libelleOrigine
     */
    public String getLibelleOrigine() {
        return libelleOrigine;
    }

    /**
     * Définit la valeur de libelleOrigine.
     * @param libelleOrigine la nouvelle valeur de libelleOrigine
     */
    public void setLibelleOrigine(String libelleOrigine) {
        this.libelleOrigine = libelleOrigine;
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
     * Getter function.
     * @return the nomDestinataireReglement
     */
    public String getNomDestinataireReglement() {
        return nomDestinataireReglement;
    }

    /**
     * Setter function.
     * @param nomDestinataireReglement the nomDestinataireReglement to set
     */
    public void setNomDestinataireReglement(String nomDestinataireReglement) {
        this.nomDestinataireReglement = nomDestinataireReglement;
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

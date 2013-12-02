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
package com.square.composant.prestations.square.client.model;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model pour les entetes de prestations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class LigneDecompteModel implements IsSerializable {

    /** Identifiant. */
    private Long id;

    /** Le numero de decompte. */
    private String numero;

    /** Date de debut des soins. */
    private String dateDebutSoins;

    /** Nom et prenom beneficiaire des soins. */
    private String beneficiaire;

    /** Montant du decompte. */
    private Double montant;

    /** Montant du remboursement Securite sociale. */
    private Double remboursementSecu;

    /** Montant du remboursement Complementaire. */
    private Double remboursementCompl;

    /** Montant du reste a charge. */
    private Double resteACharge;

    /** Taux du remboursement Securite sociale. */
    private Integer tauxRemboursementSecu;

    /** Taux du remboursement Complementaire. */
    private Integer tauxRemboursementCompl;

    /** Base de remboursement du Regime Obligatoire. */
    private Double baseRemboursementRO;

    /** Taux de remboursement du Regime Obligatoire. */
    private Double tauxRemboursementRO;

    /** Acte. */
    private IdentifiantLibelleGwt acte;

    /** Professionnel de santé. */
    private String professionnelSante;

    /** Identifiant de la nature de règlement. */
    private String idNatureReglement;

    /**
     * Get the id value.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id value.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * Get the dateDebutSoins value.
     * @return the dateDebutSoins
     */
    public String getDateDebutSoins() {
        return dateDebutSoins;
    }

    /**
     * Set the dateDebutSoins value.
     * @param dateDebutSoins the dateDebutSoins to set
     */
    public void setDateDebutSoins(String dateDebutSoins) {
        this.dateDebutSoins = dateDebutSoins;
    }

    /**
     * Get the montant value.
     * @return the montant
     */
    public Double getMontant() {
        return montant;
    }

    /**
     * Set the montant value.
     * @param montant the montant to set
     */
    public void setMontant(Double montant) {
        this.montant = montant;
    }

    /**
     * Get the remboursementSecu value.
     * @return the remboursementSecu
     */
    public Double getRemboursementSecu() {
        return remboursementSecu;
    }

    /**
     * Set the remboursementSecu value.
     * @param remboursementSecu the remboursementSecu to set
     */
    public void setRemboursementSecu(Double remboursementSecu) {
        this.remboursementSecu = remboursementSecu;
    }

    /**
     * Get the remboursementCompl value.
     * @return the remboursementCompl
     */
    public Double getRemboursementCompl() {
        return remboursementCompl;
    }

    /**
     * Set the remboursementCompl value.
     * @param remboursementCompl the remboursementCompl to set
     */
    public void setRemboursementCompl(Double remboursementCompl) {
        this.remboursementCompl = remboursementCompl;
    }

    /**
     * Get the resteACharge value.
     * @return the resteACharge
     */
    public Double getResteACharge() {
        return resteACharge;
    }

    /**
     * Set the resteACharge value.
     * @param resteACharge the resteACharge to set
     */
    public void setResteACharge(Double resteACharge) {
        this.resteACharge = resteACharge;
    }

    /**
     * Get the acte value.
     * @return the acte
     */
    public IdentifiantLibelleGwt getActe() {
        return acte;
    }

    /**
     * Set the acte value.
     * @param acte the acte to set
     */
    public void setActe(IdentifiantLibelleGwt acte) {
        this.acte = acte;
    }

    /**
     * Get the beneficiaire value.
     * @return the beneficiaire
     */
    public String getBeneficiaire() {
        return beneficiaire;
    }

    /**
     * Set the beneficiaire value.
     * @param beneficiaire the beneficiaire to set
     */
    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
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
     * Get the tauxRemboursementSecu value.
     * @return the tauxRemboursementSecu
     */
    public Integer getTauxRemboursementSecu() {
        return tauxRemboursementSecu;
    }

    /**
     * Set the tauxRemboursementSecu value.
     * @param tauxRemboursementSecu the tauxRemboursementSecu to set
     */
    public void setTauxRemboursementSecu(Integer tauxRemboursementSecu) {
        this.tauxRemboursementSecu = tauxRemboursementSecu;
    }

    /**
     * Get the tauxRemboursementCompl value.
     * @return the tauxRemboursementCompl
     */
    public Integer getTauxRemboursementCompl() {
        return tauxRemboursementCompl;
    }

    /**
     * Set the tauxRemboursementCompl value.
     * @param tauxRemboursementCompl the tauxRemboursementCompl to set
     */
    public void setTauxRemboursementCompl(Integer tauxRemboursementCompl) {
        this.tauxRemboursementCompl = tauxRemboursementCompl;
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

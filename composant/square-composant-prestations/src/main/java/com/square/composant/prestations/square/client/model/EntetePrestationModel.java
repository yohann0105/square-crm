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
public class EntetePrestationModel implements IsSerializable {

    /** Le numero de decompte. */
    private String numero;

    /** Date du reglement. */
    private String dateReglement;

    /** Date de debut des soins. */
    private String dateDebutSoins;

    /** Date de fin des soins. */
    private String dateFinSoins;

    /** Montant du remboursement Smatis. */
    private Double remboursementSmatis;

    /** Origine. */
    private IdentifiantLibelleGwt origine;

    /** Numero du cheque. */
    private String numeroCheque;

    /** Domiciliation de la banque. */
    private String domiciliation;

    /** Numero de compte. */
    private String compte;

    /** Professionnel de santé. */
    private String professionnelSante;

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
    public String getDateReglement() {
        return dateReglement;
    }

    /**
     * Set the dateReglement value.
     * @param dateReglement the dateReglement to set
     */
    public void setDateReglement(String dateReglement) {
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
    public IdentifiantLibelleGwt getOrigine() {
        return origine;
    }

    /**
     * Set the origine value.
     * @param origine the origine to set
     */
    public void setOrigine(IdentifiantLibelleGwt origine) {
        this.origine = origine;
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
     * Get the dateFinSoins value.
     * @return the dateFinSoins
     */
    public String getDateFinSoins() {
        return dateFinSoins;
    }

    /**
     * Set the dateFinSoins value.
     * @param dateFinSoins the dateFinSoins to set
     */
    public void setDateFinSoins(String dateFinSoins) {
        this.dateFinSoins = dateFinSoins;
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
}

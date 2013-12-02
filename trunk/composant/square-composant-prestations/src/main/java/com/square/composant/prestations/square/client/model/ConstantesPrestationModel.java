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

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Constantes pour les prestations.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ConstantesPrestationModel implements IsSerializable {

    /** Nom de la propriété pour le tri sur la colonne "Date du soin". */
    private String orderDecompteByDateSoin;

    /** Nom de la propriété pour le tri sur la colonne "Bénéficiaire". */
    private String orderDecompteByBeneficiaire;

    /** Nom de la propriété pour le tri sur la colonne "Acte". */
    private String orderDecompteByActe;

    /** Nom de la propriété pour le tri sur la colonne "Dépense". */
    private String orderDecompteByDepense;

    /** Nom de la propriété pour le tri sur la colonne "Base". */
    private String orderDecompteByBase;

    /** Nom de la propriété pour le tri sur la colonne "Taux". */
    private String orderDecompteByTaux;

    /** Nom de la propriété pour le tri sur la colonne "RbtRO". */
    private String orderDecompteByRbtRO;

    /** Nom de la propriété pour le tri sur la colonne "RbtSmatis". */
    private String orderDecompteByRbtSmatis;

    /** Nom de la propriété pour le tri sur la colonne "RbtProf". */
    private String orderDecompteByRbtProf;

    /** Nom de la propriété pour le tri sur la colonne "RaC". */
    private String orderDecompteByRaC;

    /** Id de l'origine de decompte indu. */
    private Long idOrigineDecompteIndu;

    /** Statut de paiement de decompte payé. */
    private String idStatutPaiementDecomptePaye;

    /** Statut de paiement de decompte non payé. */
    private String idStatutPaiementDecompteNonPaye;

    /** Identifiant de la nature de règlement "Tiers Payant". */
    private String idNatureReglementTiersSante;

    /**
     * Récupère la valeur de orderDecompteByDateSoin.
     * @return la valeur de orderDecompteByDateSoin
     */
    public String getOrderDecompteByDateSoin() {
        return orderDecompteByDateSoin;
    }

    /**
     * Définit la valeur de orderDecompteByDateSoin.
     * @param orderDecompteByDateSoin la nouvelle valeur de orderDecompteByDateSoin
     */
    public void setOrderDecompteByDateSoin(String orderDecompteByDateSoin) {
        this.orderDecompteByDateSoin = orderDecompteByDateSoin;
    }

    /**
     * Récupère la valeur de orderDecompteByBeneficiaire.
     * @return la valeur de orderDecompteByBeneficiaire
     */
    public String getOrderDecompteByBeneficiaire() {
        return orderDecompteByBeneficiaire;
    }

    /**
     * Définit la valeur de orderDecompteByBeneficiaire.
     * @param orderDecompteByBeneficiaire la nouvelle valeur de orderDecompteByBeneficiaire
     */
    public void setOrderDecompteByBeneficiaire(String orderDecompteByBeneficiaire) {
        this.orderDecompteByBeneficiaire = orderDecompteByBeneficiaire;
    }

    /**
     * Récupère la valeur de orderDecompteByActe.
     * @return la valeur de orderDecompteByActe
     */
    public String getOrderDecompteByActe() {
        return orderDecompteByActe;
    }

    /**
     * Définit la valeur de orderDecompteByActe.
     * @param orderDecompteByActe la nouvelle valeur de orderDecompteByActe
     */
    public void setOrderDecompteByActe(String orderDecompteByActe) {
        this.orderDecompteByActe = orderDecompteByActe;
    }

    /**
     * Récupère la valeur de orderDecompteByDepense.
     * @return la valeur de orderDecompteByDepense
     */
    public String getOrderDecompteByDepense() {
        return orderDecompteByDepense;
    }

    /**
     * Définit la valeur de orderDecompteByDepense.
     * @param orderDecompteByDepense la nouvelle valeur de orderDecompteByDepense
     */
    public void setOrderDecompteByDepense(String orderDecompteByDepense) {
        this.orderDecompteByDepense = orderDecompteByDepense;
    }

    /**
     * Récupère la valeur de orderDecompteByBase.
     * @return la valeur de orderDecompteByBase
     */
    public String getOrderDecompteByBase() {
        return orderDecompteByBase;
    }

    /**
     * Définit la valeur de orderDecompteByBase.
     * @param orderDecompteByBase la nouvelle valeur de orderDecompteByBase
     */
    public void setOrderDecompteByBase(String orderDecompteByBase) {
        this.orderDecompteByBase = orderDecompteByBase;
    }

    /**
     * Récupère la valeur de orderDecompteByTaux.
     * @return la valeur de orderDecompteByTaux
     */
    public String getOrderDecompteByTaux() {
        return orderDecompteByTaux;
    }

    /**
     * Définit la valeur de orderDecompteByTaux.
     * @param orderDecompteByTaux la nouvelle valeur de orderDecompteByTaux
     */
    public void setOrderDecompteByTaux(String orderDecompteByTaux) {
        this.orderDecompteByTaux = orderDecompteByTaux;
    }

    /**
     * Récupère la valeur de orderDecompteByRbtRO.
     * @return la valeur de orderDecompteByRbtRO
     */
    public String getOrderDecompteByRbtRO() {
        return orderDecompteByRbtRO;
    }

    /**
     * Définit la valeur de orderDecompteByRbtRO.
     * @param orderDecompteByRbtRO la nouvelle valeur de orderDecompteByRbtRO
     */
    public void setOrderDecompteByRbtRO(String orderDecompteByRbtRO) {
        this.orderDecompteByRbtRO = orderDecompteByRbtRO;
    }

    /**
     * Récupère la valeur de orderDecompteByRbtSmatis.
     * @return la valeur de orderDecompteByRbtSmatis
     */
    public String getOrderDecompteByRbtSmatis() {
        return orderDecompteByRbtSmatis;
    }

    /**
     * Définit la valeur de orderDecompteByRbtSmatis.
     * @param orderDecompteByRbtSmatis la nouvelle valeur de orderDecompteByRbtSmatis
     */
    public void setOrderDecompteByRbtSmatis(String orderDecompteByRbtSmatis) {
        this.orderDecompteByRbtSmatis = orderDecompteByRbtSmatis;
    }

    /**
     * Récupère la valeur de orderDecompteByRbtProf.
     * @return la valeur de orderDecompteByRbtProf
     */
    public String getOrderDecompteByRbtProf() {
        return orderDecompteByRbtProf;
    }

    /**
     * Définit la valeur de orderDecompteByRbtProf.
     * @param orderDecompteByRbtProf la nouvelle valeur de orderDecompteByRbtProf
     */
    public void setOrderDecompteByRbtProf(String orderDecompteByRbtProf) {
        this.orderDecompteByRbtProf = orderDecompteByRbtProf;
    }

    /**
     * Récupère la valeur de orderDecompteByRaC.
     * @return la valeur de orderDecompteByRaC
     */
    public String getOrderDecompteByRaC() {
        return orderDecompteByRaC;
    }

    /**
     * Définit la valeur de orderDecompteByRaC.
     * @param orderDecompteByRaC la nouvelle valeur de orderDecompteByRaC
     */
    public void setOrderDecompteByRaC(String orderDecompteByRaC) {
        this.orderDecompteByRaC = orderDecompteByRaC;
    }

    /**
     * Get the idOrigineDecompteIndu value.
     * @return the idOrigineDecompteIndu
     */
    public Long getIdOrigineDecompteIndu() {
        return idOrigineDecompteIndu;
    }

    /**
     * Set the idOrigineDecompteIndu value.
     * @param idOrigineDecompteIndu the idOrigineDecompteIndu to set
     */
    public void setIdOrigineDecompteIndu(Long idOrigineDecompteIndu) {
        this.idOrigineDecompteIndu = idOrigineDecompteIndu;
    }

    /**
     * Get the idStatutPaiementDecomptePaye value.
     * @return the idStatutPaiementDecomptePaye
     */
    public String getIdStatutPaiementDecomptePaye() {
        return idStatutPaiementDecomptePaye;
    }

    /**
     * Set the idStatutPaiementDecomptePaye value.
     * @param idStatutPaiementDecomptePaye the idStatutPaiementDecomptePaye to set
     */
    public void setIdStatutPaiementDecomptePaye(String idStatutPaiementDecomptePaye) {
        this.idStatutPaiementDecomptePaye = idStatutPaiementDecomptePaye;
    }

    /**
     * Get the idStatutPaiementDecompteNonPaye value.
     * @return the idStatutPaiementDecompteNonPaye
     */
    public String getIdStatutPaiementDecompteNonPaye() {
        return idStatutPaiementDecompteNonPaye;
    }

    /**
     * Set the idStatutPaiementDecompteNonPaye value.
     * @param idStatutPaiementDecompteNonPaye the idStatutPaiementDecompteNonPaye to set
     */
    public void setIdStatutPaiementDecompteNonPaye(String idStatutPaiementDecompteNonPaye) {
        this.idStatutPaiementDecompteNonPaye = idStatutPaiementDecompteNonPaye;
    }

    /**
     * Récupère la valeur de idNatureReglementTiersSante.
     * @return la valeur de idNatureReglementTiersSante
     */
    public String getIdNatureReglementTiersSante() {
        return idNatureReglementTiersSante;
    }

    /**
     * Définit la valeur de idNatureReglementTiersSante.
     * @param idNatureReglementTiersSante la nouvelle valeur de idNatureReglementTiersSante
     */
    public void setIdNatureReglementTiersSante(String idNatureReglementTiersSante) {
        this.idNatureReglementTiersSante = idNatureReglementTiersSante;
    }
}

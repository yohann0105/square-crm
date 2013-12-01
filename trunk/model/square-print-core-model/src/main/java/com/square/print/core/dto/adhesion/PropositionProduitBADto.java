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
package com.square.print.core.dto.adhesion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO représentant une proposition pour un produit.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class PropositionProduitBADto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -1990053521881887579L;

    /** Mode de paiement unique. */
    public static final String FREQUENCE_COTISATION_UNIQUE = "Unique";

    /** Mode de paiement mensuel. */
    public static final String FREQUENCE_COTISATION_MENSUELLE = "Mensuel";

    /** Identifiant du produit. */
    private Integer idProduit;

    /** Libellé du produit. */
    private String libelleProduit;

    /** Image du logo du produit. */
    private byte[] logoProduit;

    /** Fréquence de cotisation. */
    private String frequenceCotisation;

    /** Liste des bénéficiaires de ce produit avec leur cotisation. */
    private List < BeneficiaireMontantBADto > listeBeneficiaires;

    /** Montant total sans la remise pour le produit. */
    private Float montantTotalProduitSansRemise;

    /** Montant total de la remise pour le produit. */
    private Float montantTotalRemiseProduit;

    /** Constructeur. */
    public PropositionProduitBADto() { }

    /**
     * Constructeur.
     * @param idProduit l'identifiant du produit
     * @param libelleProduit le libellé du produit
     * @param logoProduit image du logo du produit
     * @param frequenceCotisation la fréquence de cotisation du produit
     * @param listeBeneficiaires la liste des bénéficiaires
     * @param montantTotalProduitSansRemise le montant total sans la remise pour le produit
     * @param montantTotalRemiseProduit le montant total de la remise pour le produit
     */
    public PropositionProduitBADto(Integer idProduit, String libelleProduit, byte[] logoProduit, String frequenceCotisation,
            List < BeneficiaireMontantBADto > listeBeneficiaires, Float montantTotalProduitSansRemise, Float montantTotalRemiseProduit) {
        this.idProduit = idProduit;
        this.libelleProduit = libelleProduit;
        this.logoProduit = logoProduit;
        this.frequenceCotisation = frequenceCotisation;
        this.listeBeneficiaires = listeBeneficiaires;
        this.montantTotalProduitSansRemise = montantTotalProduitSansRemise;
        this.montantTotalRemiseProduit = montantTotalRemiseProduit;
    }

    /**
     * Constructeur.
     * @param idProduit l'identifiant du produit
     * @param libelleProduit le libellé du produit
     * @param logoProduit image du logo du produit
     * @param frequenceCotisation la fréquence de cotisation du produit
     * @param listeBeneficiaires la liste des bénéficiaires
     */
    public PropositionProduitBADto(Integer idProduit, String libelleProduit, byte[] logoProduit, String frequenceCotisation,
            List < BeneficiaireMontantBADto > listeBeneficiaires) {
        this(idProduit, libelleProduit, logoProduit, frequenceCotisation, listeBeneficiaires, null, null);
    }

    /**
     * Définit la valeur de idProduit.
     * @return la valeur de idProduit
     */
    public Integer getIdProduit() {
        return idProduit;
    }

    /**
     * Définit la valeur de idProduit.
     * @param idProduit la nouvelle valeur de idProduit
     */
    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    /**
     * Définit la valeur de libelleProduit.
     * @return la valeur de libelleProduit
     */
    public String getLibelleProduit() {
        return libelleProduit;
    }

    /**
     * Définit la valeur de libelleProduit.
     * @param libelleProduit la nouvelle valeur de libelleProduit
     */
    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

    /**
     * Get the logoProduit value.
     * @return the logoProduit
     */
    public byte[] getLogoProduit() {
        return logoProduit;
    }

    /**
     * Set the logoProduit value.
     * @param logoProduit the logoProduit to set
     */
    public void setLogoProduit(byte[] logoProduit) {
        this.logoProduit = logoProduit;
    }

    /**
     * Définit la valeur de listeBeneficiaires.
     * @return la valeur de listeBeneficiaires
     */
    public List < BeneficiaireMontantBADto > getListeBeneficiaires() {
        if (listeBeneficiaires == null) {
            listeBeneficiaires = new ArrayList < BeneficiaireMontantBADto > ();
        }
        return listeBeneficiaires;
    }

    /**
     * Définit la valeur de listeBeneficiaires.
     * @param listeBeneficiaires la nouvelle valeur de listeBeneficiaires
     */
    public void setListeBeneficiaires(List <  BeneficiaireMontantBADto > listeBeneficiaires) {
        this.listeBeneficiaires = listeBeneficiaires;
    }

    /**
     * Récupère la valeur de frequenceCotisation.
     * @return la valeur de frequenceCotisation
     */
    public String getFrequenceCotisation() {
        return frequenceCotisation;
    }

    /**
     * Définit la valeur de frequenceCotisation.
     * @param frequenceCotisation la nouvelle valeur de frequenceCotisation
     */
    public void setFrequenceCotisation(String frequenceCotisation) {
        this.frequenceCotisation = frequenceCotisation;
    }

    /**
     * Récupère la valeur de montantTotalProduitSansRemise.
     * @return la valeur de montantTotalProduitSansRemise
     */
    public Float getMontantTotalProduitSansRemise() {
        return montantTotalProduitSansRemise;
    }

    /**
     * Définit la valeur de montantTotalProduitSansRemise.
     * @param montantTotalProduitSansRemise la nouvelle valeur de montantTotalProduitSansRemise
     */
    public void setMontantTotalProduitSansRemise(Float montantTotalProduitSansRemise) {
        this.montantTotalProduitSansRemise = montantTotalProduitSansRemise;
    }

    /**
     * Récupère la valeur de montantTotalRemiseProduit.
     * @return la valeur de montantTotalRemiseProduit
     */
    public Float getMontantTotalRemiseProduit() {
        return montantTotalRemiseProduit;
    }

    /**
     * Définit la valeur de montantTotalRemiseProduit.
     * @param montantTotalRemiseProduit la nouvelle valeur de montantTotalRemiseProduit
     */
    public void setMontantTotalRemiseProduit(Float montantTotalRemiseProduit) {
        this.montantTotalRemiseProduit = montantTotalRemiseProduit;
    }

    /**
     * Calcule le montant total du produit avec la remise.
     * @return le montant avec la remise
     */
    public Float getMontantTotalProduitAvecRemise() {
        if (montantTotalProduitSansRemise != null) {
            float montantTotalProduitAvecRemise = 0f;
            montantTotalProduitAvecRemise += montantTotalProduitSansRemise;
            if (montantTotalRemiseProduit != null) {
                montantTotalProduitAvecRemise -= montantTotalRemiseProduit;
            }
            return montantTotalProduitAvecRemise;
        } else {
            return null;
        }
    }

}

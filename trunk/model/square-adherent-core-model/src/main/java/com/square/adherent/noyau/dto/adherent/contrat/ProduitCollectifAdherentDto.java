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
package com.square.adherent.noyau.dto.adherent.contrat;

import java.io.Serializable;

/**
 * DTO d'un produit collectif adherent.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ProduitCollectifAdherentDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 5463346099373570396L;

    /** Garantie Aia. */
    private String garantieGestion;

    /** Produit AIA. */
    private String produitGestion;

    /** Code tarif. */
    private String codeTarif;

    /** Montant souscrit. */
    private Double montantSouscrit;

    /** Nombre d'adhérents à ce produit. */
    private Integer nbAdherents;

    /**
     * Constructeur.
     */
    public ProduitCollectifAdherentDto() {
        super();
    }

    /**
     * Constructeur.
     * @param garantieGestion garantieGestion
     * @param produitGestion produitGestion
     * @param codeTarif codeTarif
     * @param montantSouscrit montantSouscrit
     * @param nbAdherents nbAdherents
     */
    public ProduitCollectifAdherentDto(String produitGestion, String garantieGestion, String codeTarif, Double montantSouscrit, Integer nbAdherents) {
        super();
        this.garantieGestion = garantieGestion;
        this.produitGestion = produitGestion;
        this.codeTarif = codeTarif;
        this.montantSouscrit = montantSouscrit;
        this.nbAdherents = nbAdherents;
    }

    /**
     * Get the codeTarif value.
     * @return the codeTarif
     */
    public String getCodeTarif() {
        return codeTarif;
    }

    /**
     * Set the codeTarif value.
     * @param codeTarif the codeTarif to set
     */
    public void setCodeTarif(String codeTarif) {
        this.codeTarif = codeTarif;
    }

    /**
     * Get the montantSouscrit value.
     * @return the montantSouscrit
     */
    public Double getMontantSouscrit() {
        return montantSouscrit;
    }

    /**
     * Set the montantSouscrit value.
     * @param montantSouscrit the montantSouscrit to set
     */
    public void setMontantSouscrit(Double montantSouscrit) {
        this.montantSouscrit = montantSouscrit;
    }

    /**
     * Get the nbAdherents value.
     * @return the nbAdherents
     */
    public Integer getNbAdherents() {
        return nbAdherents;
    }

    /**
     * Set the nbAdherents value.
     * @param nbAdherents the nbAdherents to set
     */
    public void setNbAdherents(Integer nbAdherents) {
        this.nbAdherents = nbAdherents;
    }

    /**
     * Get the value of garantieGestion.
     * @return the garantieGestion
     */
    public String getGarantieGestion() {
        return garantieGestion;
    }

    /**
     * Set the value of garantieGestion.
     * @param garantieGestion the garantieGestion to set
     */
    public void setGarantieGestion(String garantieGestion) {
        this.garantieGestion = garantieGestion;
    }

    /**
     * Get the value of produitGestion.
     * @return the produitGestion
     */
    public String getProduitGestion() {
        return produitGestion;
    }

    /**
     * Set the value of produitGestion.
     * @param produitGestion the produitGestion to set
     */
    public void setProduitGestion(String produitGestion) {
        this.produitGestion = produitGestion;
    }

}

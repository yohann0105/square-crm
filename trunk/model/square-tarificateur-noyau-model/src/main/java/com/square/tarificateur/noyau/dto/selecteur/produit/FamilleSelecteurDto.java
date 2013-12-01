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
package com.square.tarificateur.noyau.dto.selecteur.produit;

import java.io.Serializable;

/**
 * Dto representant une famille de produits pour le selecteur de produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class FamilleSelecteurDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -1736320916959267606L;

    /** Identifiant. */
    private Integer identifiant;

    /** Libelle. */
    private String libelle;

    /** Parametre optionnel de la famille. */
    private Boolean optionnel;

    /** Libelle de la gamme. */
    private String libelleGamme;

    /** Ordre dans la gamme. */
    private Integer ordreGamme;

    /** Ordre de la famille. */
    private Integer ordreFamille;

    /** Flag de selection. */
    private Boolean isSelection;

    /**
     * Get the identifiant value.
     * @return the identifiant
     */
    public Integer getIdentifiant() {
        return identifiant;
    }

    /**
     * Set the identifiant value.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Get the libelle value.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Set the libelle value.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Get the optionnel value.
     * @return the optionnel
     */
    public Boolean getOptionnel() {
        return optionnel;
    }

    /**
     * Set the optionnel value.
     * @param optionnel the optionnel to set
     */
    public void setOptionnel(Boolean optionnel) {
        this.optionnel = optionnel;
    }

    /**
     * Get the ordreGamme value.
     * @return the ordreGamme
     */
    public Integer getOrdreGamme() {
        return ordreGamme;
    }

    /**
     * Set the ordreGamme value.
     * @param ordreGamme the ordreGamme to set
     */
    public void setOrdreGamme(Integer ordreGamme) {
        this.ordreGamme = ordreGamme;
    }

    /**
     * Recupere la valeur de libelleGamme.
     * @return la valeur de libelleGamme
     */
    public String getLibelleGamme() {
        return libelleGamme;
    }

    /**
     * Definit la valeur de libelleGamme.
     * @param libelleGamme la nouvelle valeur de libelleGamme
     */
    public void setLibelleGamme(String libelleGamme) {
        this.libelleGamme = libelleGamme;
    }

    /**
     * Recupere la valeur de isSelection.
     * @return la valeur de isSelection
     */
    public Boolean getIsSelection() {
        return isSelection;
    }

    /**
     * Definit la valeur de isSelection.
     * @param isSelection la nouvelle valeur de isSelection
     */
    public void setIsSelection(Boolean isSelection) {
        this.isSelection = isSelection;
    }

    /**
     * Get the ordreFamille value.
     * @return the ordreFamille
     */
    public Integer getOrdreFamille() {
        return ordreFamille;
    }

    /**
     * Set the ordreFamille value.
     * @param ordreFamille the ordreFamille to set
     */
    public void setOrdreFamille(Integer ordreFamille) {
        this.ordreFamille = ordreFamille;
    }

}

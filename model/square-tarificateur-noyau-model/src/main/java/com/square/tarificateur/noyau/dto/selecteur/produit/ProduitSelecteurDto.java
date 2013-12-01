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
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto representant un produit pour le selecteur de produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class ProduitSelecteurDto implements Serializable, Comparable<ProduitSelecteurDto> {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -5569456169572353876L;

    /** Identifiant. */
    private Integer identifiant;

    /** Libelle. */
    private String libelle;

    /** Ordre dans la famille. */
    private Integer ordreFamille;

    /** Assure Principal. */
    private AssureSelecteurDto assurePrincipal;

    /** Mode tarification. */
    private IdentifiantLibelleDto modeTarification;

    /** Liste des bénéficiaires. */
    private List<AssureSelecteurDto> listeBeneficiaires;

    /** Liste des criteres. */
    private List<CritereSelecteurDto> listeCriteres;

    /** Contrainte de vente. */
    private ContrainteVenteSelecteurDto contraintesVente;

    /** Flag de selection. */
    private Boolean isSelection;

    /** Flag actif/non actif. */
    private Boolean isActif = true;

    /** Flag indiquant si le produit est ouvert à la vente ou non. */
    private Boolean isProduitOuvertVente;

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

    /**
     * Get the assurePrincipal value.
     * @return the assurePrincipal
     */
    public AssureSelecteurDto getAssurePrincipal() {
        return assurePrincipal;
    }

    /**
     * Set the assurePrincipal value.
     * @param assurePrincipal the assurePrincipal to set
     */
    public void setAssurePrincipal(AssureSelecteurDto assurePrincipal) {
        this.assurePrincipal = assurePrincipal;
    }

    /**
     * Get the listeBeneficiaires value.
     * @return the listeBeneficiaires
     */
    public List<AssureSelecteurDto> getListeBeneficiaires() {
        return listeBeneficiaires;
    }

    /**
     * Set the listeBeneficiaires value.
     * @param listeBeneficiaires the listeBeneficiaires to set
     */
    public void setListeBeneficiaires(List<AssureSelecteurDto> listeBeneficiaires) {
        this.listeBeneficiaires = listeBeneficiaires;
    }

    /**
     * Get the listeCriteres value.
     * @return the listeCriteres
     */
    public List<CritereSelecteurDto> getListeCriteres() {
        return listeCriteres;
    }

    /**
     * Set the listeCriteres value.
     * @param listeCriteres the listeCriteres to set
     */
    public void setListeCriteres(List<CritereSelecteurDto> listeCriteres) {
        this.listeCriteres = listeCriteres;
    }

    /**
     * Recupere la valeur de contraintesVente.
     * @return la valeur de contraintesVente
     */
    public ContrainteVenteSelecteurDto getContraintesVente() {
        return contraintesVente;
    }

    /**
     * Definit la valeur de contraintesVente.
     * @param contraintesVente la nouvelle valeur de contraintesVente
     */
    public void setContraintesVente(ContrainteVenteSelecteurDto contraintesVente) {
        this.contraintesVente = contraintesVente;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(ProduitSelecteurDto obj) {
        if (this.getOrdreFamille() == null && obj.getOrdreFamille() == null) {
            return 0;
        } else if (this.getOrdreFamille() != null && obj.getOrdreFamille() == null) {
            return 1;
        } else if (this.getOrdreFamille() == null && obj.getOrdreFamille() != null) {
            return -1;
        }
        return this.getOrdreFamille().compareTo(obj.getOrdreFamille());
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
     * Get the isActif value.
     * @return the isActif
     */
    public Boolean getIsActif() {
        return isActif;
    }

    /**
     * Set the isActif value.
     * @param isActif the isActif to set
     */
    public void setIsActif(Boolean isActif) {
        this.isActif = isActif;
    }

    /**
     * Getter.
     * @return the isProduitOuvertVente
     */
    public Boolean getIsProduitOuvertVente() {
        return isProduitOuvertVente;
    }

    /**
     * Setter.
     * @param isProduitOuvertVente the isProduitOuvertVente to set
     */
    public void setIsProduitOuvertVente(Boolean isProduitOuvertVente) {
        this.isProduitOuvertVente = isProduitOuvertVente;
    }

    /**
     * Get the value of modeTarification.
     * @return the modeTarification
     */
    public IdentifiantLibelleDto getModeTarification() {
        return modeTarification;
    }

    /**
     * Set the value of modeTarification.
     * @param modeTarification the modeTarification to set
     */
    public void setModeTarification(IdentifiantLibelleDto modeTarification) {
        this.modeTarification = modeTarification;
    }

}

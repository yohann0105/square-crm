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
package com.square.price.core.dto;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto pour les produits.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class ProduitDto extends ProduitSimpleDto {
    private static final long serialVersionUID = -7735747152714562820L;

    private String libelle;

    private IdentifiantStringLibelleDto formulePresta;

    private Integer ordreAffichage;

    private GammeProduitDto gamme;

    private Integer identifiantFamille;

    private String libelleFamille;

    private Integer ordreAffichageFamille;

    private Integer ordreFamille;

    private String numeroGarantieAia;

    /** Mode tarification. */
    private IdentifiantLibelleDto modeTarification;

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
     * Get the ordreAffichage value.
     * @return the ordreAffichage
     */
    public Integer getOrdreAffichage() {
        return ordreAffichage;
    }

    /**
     * Set the ordreAffichage value.
     * @param ordreAffichage the ordreAffichage to set
     */
    public void setOrdreAffichage(Integer ordreAffichage) {
        this.ordreAffichage = ordreAffichage;
    }

    /**
     * Get the gamme value.
     * @return the gamme
     */
    public GammeProduitDto getGamme() {
        return gamme;
    }

    /**
     * Set the gamme value.
     * @param gamme the gamme to set
     */
    public void setGamme(GammeProduitDto gamme) {
        this.gamme = gamme;
    }

    /**
     * Get the identifiantFamille value.
     * @return the identifiantFamille
     */
    public Integer getIdentifiantFamille() {
        return identifiantFamille;
    }

    /**
     * Set the identifiantFamille value.
     * @param identifiantFamille the identifiantFamille to set
     */
    public void setIdentifiantFamille(Integer identifiantFamille) {
        this.identifiantFamille = identifiantFamille;
    }

    /**
     * Get the libelleFamille value.
     * @return the libelleFamille
     */
    public String getLibelleFamille() {
        return libelleFamille;
    }

    /**
     * Set the libelleFamille value.
     * @param libelleFamille the libelleFamille to set
     */
    public void setLibelleFamille(String libelleFamille) {
        this.libelleFamille = libelleFamille;
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
     * Get the numeroGarantieAia value.
     * @return the numeroGarantieAia
     */
    public String getNumeroGarantieAia() {
        return numeroGarantieAia;
    }

    /**
     * Set the numeroGarantieAia value.
     * @param numeroGarantieAia the numeroGarantieAia to set
     */
    public void setNumeroGarantieAia(String numeroGarantieAia) {
        this.numeroGarantieAia = numeroGarantieAia;
    }

    /**
     * Get the ordreAffichageFamille value.
     * @return the ordreAffichageFamille
     */
    public Integer getOrdreAffichageFamille() {
        return ordreAffichageFamille;
    }

    /**
     * Set the ordreAffichageFamille value.
     * @param ordreAffichageFamille the ordreAffichageFamille to set
     */
    public void setOrdreAffichageFamille(Integer ordreAffichageFamille) {
        this.ordreAffichageFamille = ordreAffichageFamille;
    }

    /**
     * Get the value of formulePresta.
     * @return the formulePresta
     */
    public IdentifiantStringLibelleDto getFormulePresta() {
        return formulePresta;
    }

    /**
     * Set the value of formulePresta.
     * @param formulePresta the formulePresta to set
     */
    public void setFormulePresta(IdentifiantStringLibelleDto formulePresta) {
        this.formulePresta = formulePresta;
    }

    /**
     * Récupère la valeur de modeTarification.
     * @return la valeur de modeTarification
     */
    public IdentifiantLibelleDto getModeTarification() {
        return modeTarification;
    }

    /**
     * Définit la valeur de modeTarification.
     * @param modeTarification la nouvelle valeur de modeTarification
     */
    public void setModeTarification(IdentifiantLibelleDto modeTarification) {
        this.modeTarification = modeTarification;
    }
}

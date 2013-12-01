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

import java.io.Serializable;

/**
 * DTO représentant une version simplifié d'un produit.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class ProduitSimpleDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -2792670527785786661L;

    /** Identifiant du produit. */
    private Integer identifiant;

    /** Libellé commercial du produit. */
    private String libelleCommercial;

    /** Produit aia. */
    private String produitAia;

    /** Garantie aia. */
    private String garantieAia;

    /**
     * Récupère la valeur de identifiant.
     * @return la valeur de identifiant
     */
    public Integer getIdentifiant() {
        return identifiant;
    }

    /**
     * Définit la valeur de identifiant.
     * @param identifiant la nouvelle valeur de identifiant
     */
    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Récupère la valeur de libelleCommercial.
     * @return la valeur de libelleCommercial
     */
    public String getLibelleCommercial() {
        return libelleCommercial;
    }

    /**
     * Définit la valeur de libelleCommercial.
     * @param libelleCommercial la nouvelle valeur de libelleCommercial
     */
    public void setLibelleCommercial(String libelleCommercial) {
        this.libelleCommercial = libelleCommercial;
    }

    /**
     * Récupère la valeur de produitAia.
     * @return la valeur de produitAia
     */
    public String getProduitAia() {
        return produitAia;
    }

    /**
     * Définit la valeur de produitAia.
     * @param produitAia la nouvelle valeur de produitAia
     */
    public void setProduitAia(String produitAia) {
        this.produitAia = produitAia;
    }

    /**
     * Récupère la valeur de garantieAia.
     * @return la valeur de garantieAia
     */
    public String getGarantieAia() {
        return garantieAia;
    }

    /**
     * Définit la valeur de garantieAia.
     * @param garantieAia la nouvelle valeur de garantieAia
     */
    public void setGarantieAia(String garantieAia) {
        this.garantieAia = garantieAia;
    }

}

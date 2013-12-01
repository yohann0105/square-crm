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
package com.square.composant.tarificateur.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model pour les critères de recherche de produits.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ProduitCriteresModel implements IsSerializable {

    /** Identifiant Gamme Produit. */
    private Integer identifiantGammeProduit;

    /** Libellé du produit. */
    private String libelleProduit;

    /** Identifiant du devis (permet de filtrer les produits CNP). */
    private Long idDevis;

    /** Flag indiquant si l'on filtre sur les produits actifs. */
    private Boolean actif;

    /**
     * Get the identifiantGammeProduit value.
     * @return the identifiantGammeProduit
     */
    public Integer getIdentifiantGammeProduit() {
        return identifiantGammeProduit;
    }

    /**
     * Set the identifiantGammeProduit value.
     * @param identifiantGammeProduit the identifiantGammeProduit to set
     */
    public void setIdentifiantGammeProduit(Integer identifiantGammeProduit) {
        this.identifiantGammeProduit = identifiantGammeProduit;
    }

    /**
     * Retourne la valeur de libelleProduit.
     * @return libelleProduit
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
     * Retourne la valeur de idDevis.
     * @return idDevis
     */
    public Long getIdDevis() {
        return idDevis;
    }

    /**
     * Définit la valeur de idDevis.
     * @param idDevis la nouvelle valeur de idDevis
     */
    public void setIdDevis(Long idDevis) {
        this.idDevis = idDevis;
    }

    /**
     * Récupère la valeur de actif.
     * @return la valeur de actif
     */
    public Boolean getActif() {
        return actif;
    }

    /**
     * Définit la valeur de actif.
     * @param actif la nouvelle valeur de actif
     */
    public void setActif(Boolean actif) {
        this.actif = actif;
    }

}

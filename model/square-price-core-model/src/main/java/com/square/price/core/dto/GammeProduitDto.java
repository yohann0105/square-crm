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
 * Dto pour les gammes produits.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class GammeProduitDto implements Serializable
{
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 6894037826467255005L;

    /**
     * Identifiant gamme produit.
     */
    private Integer identifiant;

    /**
     * Libelle Gamme Produit.
     */
    private String libelle;

    /**
     * Ordre d'affichage.
     */
    private Integer ordreAffichage;

    /** Identifiant de la catégorie de la gamme. */
    private Integer idCategorie;

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
     * Récupère la valeur de idCategorie.
     * @return la valeur de idCategorie
     */
    public Integer getIdCategorie() {
        return idCategorie;
    }

    /**
     * Définit la valeur de idCategorie.
     * @param idCategorie la nouvelle valeur de idCategorie
     */
    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }
}

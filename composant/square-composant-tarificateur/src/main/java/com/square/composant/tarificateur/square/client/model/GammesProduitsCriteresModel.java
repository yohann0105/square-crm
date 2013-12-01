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
 * Model pour les critères de recherches de gammes de produit.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class GammesProduitsCriteresModel implements IsSerializable {

    /** Identifiant du réseau de la gamme. */
    private Integer idReseauGamme;

    /** Identifiant du type de la gamme. */
    private Integer idVetusteGamme;

    /** Identifiant de la catégorie de la gamme. */
    private Integer idCategorieGamme;

    /** Libellé de la gamme. */
    private String libelleGamme;

    /** Type de devis. */
    private String typeDevis;

    /** Flag indiquant si l'on filtre sur les produits SIKI Gamme (utilisé pour la tarification courtier). */
    private Boolean produitSikiGamme;

    /**
     * Get the produitSikiGamme value.
     * @return the produitSikiGamme
     */
    public Boolean getProduitSikiGamme() {
        return produitSikiGamme;
    }

    /**
     * Set the produitSikiGamme value.
     * @param produitSikiGamme the produitSikiGamme to set
     */
    public void setProduitSikiGamme(Boolean produitSikiGamme) {
        this.produitSikiGamme = produitSikiGamme;
    }

    /**
     * Retourne la valeur de idCategorieGamme.
     * @return idCategorieGamme
     */
    public Integer getIdCategorieGamme() {
        return idCategorieGamme;
    }

    /**
     * Définit la valeur de idCategorieGamme.
     * @param idCategorieGamme la nouvelle valeur de idCategorieGamme
     */
    public void setIdCategorieGamme(Integer idCategorieGamme) {
        this.idCategorieGamme = idCategorieGamme;
    }

    /**
     * Retourne la valeur de idReseauGamme.
     * @return idReseauGamme
     */
    public Integer getIdReseauGamme() {
        return idReseauGamme;
    }

    /**
     * Définit la valeur de idReseauGamme.
     * @param idReseauGamme la nouvelle valeur de idReseauGamme
     */
    public void setIdReseauGamme(Integer idReseauGamme) {
        this.idReseauGamme = idReseauGamme;
    }

    /**
     * Retourne la valeur de idVetusteGamme.
     * @return idVetusteGamme
     */
    public Integer getIdVetusteGamme() {
        return idVetusteGamme;
    }

    /**
     * Définit la valeur de idVetusteGamme.
     * @param idVetusteGamme la nouvelle valeur de idVetusteGamme
     */
    public void setIdVetusteGamme(Integer idVetusteGamme) {
        this.idVetusteGamme = idVetusteGamme;
    }

    /**
     * Retourne la valeur de libelleGamme.
     * @return libelleGamme
     */
    public String getLibelleGamme() {
        return libelleGamme;
    }

    /**
     * Définit la valeur de libelleGamme.
     * @param libelleGamme la nouvelle valeur de libelleGamme
     */
    public void setLibelleGamme(String libelleGamme) {
        this.libelleGamme = libelleGamme;
    }

    /**
     * Get the typeDevis value.
     * @return the typeDevis
     */
    public String getTypeDevis() {
        return typeDevis;
    }

    /**
     * Set the typeDevis value.
     * @param typeDevis the typeDevis to set
     */
    public void setTypeDevis(String typeDevis) {
        this.typeDevis = typeDevis;
    }
}

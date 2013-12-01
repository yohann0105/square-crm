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
 * Dto pour regrouper les criteres pour la selection de produit.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class ProduitCriteresDto implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -4046219924855754750L;

    /** Identifiant Gamme Produit. */
    private Integer identifiantGammeProduit;

    /** Identifiant produit. */
    private Integer identifiantProduit;

    /** Libelle produit. */
    private String libelle;

    /**
     * Vendu seul. <br>
     * Pour les courtiers, cela correspond à récupérer les produits principaux (true) ou tous les produits (null ou false)
     */
    private Boolean venduSeul;

    /** Produit aia. */
    private String produitAia;

    /** Garantie aia. */
    private String garantieAia;

    /** Visible pour courtier. */
    private Boolean visiblePourCourtier;

    /** Ouvert à la vente pour courtier. */
    private Boolean ouvertALaVentePourCourtier;

    /**
     * Identifiant du courtier si récupération des produits d'un courtier<br>
     * TODO Lors migration Square : A remplir par le client. Si non rempli, recherche hors courtier
     */
    private Long idCourtier;

    private String idPayeurType;

    private String idTarifType;

    /** Actif. */
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
     * Get the identifiantProduit value.
     * @return the identifiantProduit
     */
    public Integer getIdentifiantProduit() {
        return identifiantProduit;
    }

    /**
     * Set the identifiantProduit value.
     * @param identifiantProduit the identifiantProduit to set
     */
    public void setIdentifiantProduit(Integer identifiantProduit) {
        this.identifiantProduit = identifiantProduit;
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
     * Get the venduSeul value.
     * @return the venduSeul
     */
    public Boolean getVenduSeul() {
        return venduSeul;
    }

    /**
     * Set the venduSeul value.
     * @param venduSeul the venduSeul to set
     */
    public void setVenduSeul(Boolean venduSeul) {
        this.venduSeul = venduSeul;
    }

    /**
     * Get the garantieAia value.
     * @return the garantieAia
     */
    public String getGarantieAia() {
        return garantieAia;
    }

    /**
     * Set the garantieAia value.
     * @param garantieAia the garantieAia to set
     */
    public void setGarantieAia(String garantieAia) {
        this.garantieAia = garantieAia;
    }

    /**
     * Get the produitAia value.
     * @return the produitAia
     */
    public String getProduitAia() {
        return produitAia;
    }

    /**
     * Set the produitAia value.
     * @param produitAia the produitAia to set
     */
    public void setProduitAia(String produitAia) {
        this.produitAia = produitAia;
    }

    /**
     * Récupère la valeur de visiblePourCourtier.
     * @return la valeur de visiblePourCourtier
     */
    public Boolean getVisiblePourCourtier() {
        return visiblePourCourtier;
    }

    /**
     * Définit la valeur de visiblePourCourtier.
     * @param visiblePourCourtier la nouvelle valeur de visiblePourCourtier
     */
    public void setVisiblePourCourtier(Boolean visiblePourCourtier) {
        this.visiblePourCourtier = visiblePourCourtier;
    }

    /**
     * Récupère la valeur de ouvertALaVentePourCourtier.
     * @return la valeur de ouvertALaVentePourCourtier
     */
    public Boolean getOuvertALaVentePourCourtier() {
        return ouvertALaVentePourCourtier;
    }

    /**
     * Définit la valeur de ouvertALaVentePourCourtier.
     * @param ouvertALaVentePourCourtier la nouvelle valeur de ouvertALaVentePourCourtier
     */
    public void setOuvertALaVentePourCourtier(Boolean ouvertALaVentePourCourtier) {
        this.ouvertALaVentePourCourtier = ouvertALaVentePourCourtier;
    }

    /**
     * Récupère la valeur de idCourtier.
     * @return the idCourtier
     */
    public Long getIdCourtier() {
        return idCourtier;
    }

    /**
     * Définit la valeur de idCourtier.
     * @param idCourtier the idCourtier to set
     */
    public void setIdCourtier(Long idCourtier) {
        this.idCourtier = idCourtier;
    }

    /**
     * Get the value of idPayeurType.
     * @return the idPayeurType
     */
    public String getIdPayeurType() {
        return idPayeurType;
    }

    /**
     * Set the value of idPayeurType.
     * @param idPayeurType the idPayeurType to set
     */
    public void setIdPayeurType(String idPayeurType) {
        this.idPayeurType = idPayeurType;
    }

    /**
     * Get the value of idTarifType.
     * @return the idTarifType
     */
    public String getIdTarifType() {
        return idTarifType;
    }

    /**
     * Set the value of idTarifType.
     * @param idTarifType the idTarifType to set
     */
    public void setIdTarifType(String idTarifType) {
        this.idTarifType = idTarifType;
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

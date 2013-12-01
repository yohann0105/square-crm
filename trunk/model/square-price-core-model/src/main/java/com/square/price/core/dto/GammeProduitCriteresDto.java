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
import java.util.List;

/**
 * Dto pour regrouper les criteres pour la selection de gamme produit.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class GammeProduitCriteresDto implements Serializable {
    /** Serial Version UID. */
    private static final long serialVersionUID = 6877176426431909079L;

    /** Selection Gamme Produit Par domaine. */
    private SelectionDomaineGammeProduitDto domaine;

    /** Liste des codes Apporteur (si courtier). */
    private List < String > listeCodesApporteurCourtier;

    /** Identifiant Gamme Produit. */
    private Integer identifiantGammeProduit;

    /** Liste d'identifiants Gamme Produit. */
    private List < Integer > listeIdentifiantsGammeProduit;

    /** Identifiant Produit. */
    private Integer identifiantProduit;

    /** Nom de la gamme. */
    private String libelleGammeProduit;

    /**
     * Identifiant du courtier si récupération de gamme par courtier<br>
     * TODO Lors migration Square : A remplir par le client. Si non rempli, recherche hors courtier
     */
    private Long idCourtier;

    /** Flag indiquant si l'on filtre sur les produits SIKI Gamme (utilisé pour la tarification courtier). */
    private Boolean produitSikiGamme;

    /**
     * Flag indiquant si l'on filtre sur les gammes des produits principaux ou produits liés du courtier (utilisé pour la tarification courtier).
     * <br>true pour produits principaux, false pour les produits liés, null pour ne pas filtrer
     */
    private Boolean gammesProduitsPrincipauxCourtier;

    /** Filtre sur les gammes de produits ouverts à la vente pour courtier. */
    private Boolean gammeProduitOuvertALaVentePourCourtier;

    /** Colonne de tri (par défaut libellé). */
    private String colonneTri;

    /**
     * Get the domaine value.
     * @return the domaine
     */
    public SelectionDomaineGammeProduitDto getDomaine() {
        if (domaine == null) {
            domaine = new SelectionDomaineGammeProduitDto();
        }
        return domaine;
    }

    /**
     * Set the domaine value.
     * @param domaine the domaine to set
     */
    public void setDomaine(SelectionDomaineGammeProduitDto domaine) {
        this.domaine = domaine;
    }

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
     * Get the libelleGammeProduit value.
     * @return the libelleGammeProduit
     */
    public String getLibelleGammeProduit() {
        return libelleGammeProduit;
    }

    /**
     * Set the libelleGammeProduit value.
     * @param libelleGammeProduit the libelleGammeProduit to set
     */
    public void setLibelleGammeProduit(String libelleGammeProduit) {
        this.libelleGammeProduit = libelleGammeProduit;
    }

    /**
     * Get the listeIdentifiantsGammeProduit value.
     * @return the listeIdentifiantsGammeProduit
     */
    public List < Integer > getListeIdentifiantsGammeProduit() {
        return listeIdentifiantsGammeProduit;
    }

    /**
     * Set the listeIdentifiantsGammeProduit value.
     * @param listeIdentifiantsGammeProduit the listeIdentifiantsGammeProduit to set
     */
    public void setListeIdentifiantsGammeProduit(List < Integer > listeIdentifiantsGammeProduit) {
        this.listeIdentifiantsGammeProduit = listeIdentifiantsGammeProduit;
    }

    /**
     * Get the listeCodesApporteurCourtier value.
     * @return the listeCodesApporteurCourtier
     */
    public List < String > getListeCodesApporteurCourtier() {
        return listeCodesApporteurCourtier;
    }

    /**
     * Set the listeCodesApporteurCourtier value.
     * @param listeCodesApporteurCourtier the listeCodesApporteurCourtier to set
     */
    public void setListeCodesApporteurCourtier(List < String > listeCodesApporteurCourtier) {
        this.listeCodesApporteurCourtier = listeCodesApporteurCourtier;
    }

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
     * Récupère la valeur de idCourtier.
     * @return la valeur de idCourtier
     */
    public Long getIdCourtier() {
        return idCourtier;
    }

    /**
     * Définit la valeur de idCourtier.
     * @param idCourtier la nouvelle valeur de idCourtier
     */
    public void setIdCourtier(Long idCourtier) {
        this.idCourtier = idCourtier;
    }

    /**
     * Récupère la valeur de gammesProduitsPrincipauxCourtier.
     * @return la valeur de gammesProduitsPrincipauxCourtier
     */
    public Boolean getGammesProduitsPrincipauxCourtier() {
        return gammesProduitsPrincipauxCourtier;
    }

    /**
     * Définit la valeur de gammesProduitsPrincipauxCourtier.
     * @param gammesProduitsPrincipauxCourtier la nouvelle valeur de gammesProduitsPrincipauxCourtier
     */
    public void setGammesProduitsPrincipauxCourtier(Boolean gammesProduitsPrincipauxCourtier) {
        this.gammesProduitsPrincipauxCourtier = gammesProduitsPrincipauxCourtier;
    }

    /**
     * Récupère la valeur de gammeProduitOuvertALaVentePourCourtier.
     * @return la valeur de gammeProduitOuvertALaVentePourCourtier
     */
    public Boolean getGammeProduitOuvertALaVentePourCourtier() {
        return gammeProduitOuvertALaVentePourCourtier;
    }

    /**
     * Définit la valeur de gammeProduitOuvertALaVentePourCourtier.
     * @param gammeProduitOuvertALaVentePourCourtier la nouvelle valeur de gammeProduitOuvertALaVentePourCourtier
     */
    public void setGammeProduitOuvertALaVentePourCourtier(Boolean gammeProduitOuvertALaVentePourCourtier) {
        this.gammeProduitOuvertALaVentePourCourtier = gammeProduitOuvertALaVentePourCourtier;
    }

    /**
     * Récupère la valeur de colonneTri.
     * @return la valeur de colonneTri
     */
    public String getColonneTri() {
        return colonneTri;
    }

    /**
     * Définit la valeur de colonneTri.
     * @param colonneTri la nouvelle valeur de colonneTri
     */
    public void setColonneTri(String colonneTri) {
        this.colonneTri = colonneTri;
    }
}

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
package com.square.print.core.dto.adhesion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Dto représentant une proposition de devis pour une famille de produits.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class PropositionFamilleProduitsBADto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 5528577709739893848L;

    /** Libellé de la famille de produits. */
    private String libelleFamille;

    /** Liste des propositions de devis pour les produits. */
    private List < PropositionProduitBADto > listePropositionsProduit;

    /** Libellé de la gamme. */
    private String libelleGamme;

    /** Image du logo de la gamme. */
    private byte[] logoGamme;

    /** Libellé du produit principal (uniquement pour la famille Santé). */
    private String libelleProduitPrincipal;

    /** Le montant total mensuel de la famille sans la remise. */
    private Float montantTotalMensuelFamilleSansRemise;

    /** Le montant total mensuel de la remise pour la famille. */
    private Float montantTotalMensuelRemiseFamille;

    /** Le montant total unique de la famille sans la remise. */
    private Float montantTotalUniqueFamilleSansRemise;

    /** Le montant total unique de la remise pour la famille. */
    private Float montantTotalUniqueRemiseFamille;

    /** Constructeur. */
    public PropositionFamilleProduitsBADto() { }

    /**
     * Constructeur.
     * @param libelleFamille le libellé
     * @param listePropositionsProduit la liste des propositions de produit
     * @param libelleGamme le libellé de la gamme
     * @param logoGamme le logo de la gamme
     * @param libelleProduitPrincipal le libellé du produit principal
     * @param montantTotalMensuelFamilleSansRemise le montant total mensuel de la famille sans la remise
     * @param montantTotalMensuelRemiseFamille le montant total mensuel de la remise pour la famille
     * @param montantTotalUniqueFamilleSansRemise le montant total unique de la famille sans la remise
     * @param montantTotalUniqueRemiseFamille le montant total unique de la remise pour la famill
     */
    public PropositionFamilleProduitsBADto(String libelleFamille, List < PropositionProduitBADto > listePropositionsProduit, String libelleGamme,
            byte[] logoGamme, String libelleProduitPrincipal, Float montantTotalMensuelFamilleSansRemise, Float montantTotalMensuelRemiseFamille,
            Float montantTotalUniqueFamilleSansRemise, Float montantTotalUniqueRemiseFamille) {
        this.libelleFamille = libelleFamille;
        this.listePropositionsProduit = listePropositionsProduit;
        this.libelleGamme = libelleGamme;
        this.logoGamme = logoGamme;
        this.libelleProduitPrincipal = libelleProduitPrincipal;
        this.montantTotalMensuelFamilleSansRemise = montantTotalMensuelFamilleSansRemise;
        this.montantTotalMensuelRemiseFamille = montantTotalMensuelRemiseFamille;
        this.montantTotalUniqueFamilleSansRemise = montantTotalUniqueFamilleSansRemise;
        this.montantTotalUniqueRemiseFamille = montantTotalUniqueRemiseFamille;
    }

    /**
     * Constructeur.
     * @param libelleFamille le libellé
     * @param listePropositionsProduit la liste des propositions de produit
     * @param libelleGamme le libellé de la gamme
     * @param logoGamme le logo de la gamme
     * @param libelleProduitPrincipal le libellé du produit principal
     */
    public PropositionFamilleProduitsBADto(String libelleFamille, List < PropositionProduitBADto > listePropositionsProduit, String libelleGamme,
            byte[] logoGamme, String libelleProduitPrincipal) {
        this(libelleFamille, listePropositionsProduit, libelleGamme, logoGamme, libelleProduitPrincipal, null, null, null, null);
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
     * Get the logoGamme value.
     * @return the logoGamme
     */
    public byte[] getLogoGamme() {
        return logoGamme;
    }

    /**
     * Set the logoGamme value.
     * @param logoGamme the logoGamme to set
     */
    public void setLogoGamme(byte[] logoGamme) {
        this.logoGamme = logoGamme;
    }

    /**
     * Définit la valeur de listePropositionsProduit.
     * @return la valeur de listePropositionsProduit
     */
    public List < PropositionProduitBADto > getListePropositionsProduit() {
        if (listePropositionsProduit == null) {
            listePropositionsProduit = new ArrayList < PropositionProduitBADto > ();
        }
        return listePropositionsProduit;
    }

    /**
     * Définit la valeur de listePropositionsProduit.
     * @param listePropositionsProduit la nouvelle valeur de listePropositionsProduit
     */
    public void setListePropositionsProduit(List < PropositionProduitBADto > listePropositionsProduit) {
        this.listePropositionsProduit = listePropositionsProduit;
    }

    /**
     * Récupère la valeur de libelleGamme.
     * @return la valeur de libelleGamme
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
     * Récupère la valeur de libelleProduitPrincipal.
     * @return la valeur de libelleProduitPrincipal
     */
    public String getLibelleProduitPrincipal() {
        return libelleProduitPrincipal;
    }

    /**
     * Définit la valeur de libelleProduitPrincipal.
     * @param libelleProduitPrincipal la nouvelle valeur de libelleProduitPrincipal
     */
    public void setLibelleProduitPrincipal(String libelleProduitPrincipal) {
        this.libelleProduitPrincipal = libelleProduitPrincipal;
    }

    /**
     * Récupère la valeur de montantTotalMensuelFamilleSansRemise.
     * @return la valeur de montantTotalMensuelFamilleSansRemise
     */
    public Float getMontantTotalMensuelFamilleSansRemise() {
        return montantTotalMensuelFamilleSansRemise;
    }

    /**
     * Définit la valeur de montantTotalMensuelFamilleSansRemise.
     * @param montantTotalMensuelFamilleSansRemise la nouvelle valeur de montantTotalMensuelFamilleSansRemise
     */
    public void setMontantTotalMensuelFamilleSansRemise(Float montantTotalMensuelFamilleSansRemise) {
        this.montantTotalMensuelFamilleSansRemise = montantTotalMensuelFamilleSansRemise;
    }

    /**
     * Récupère la valeur de montantTotalMensuelRemiseFamille.
     * @return la valeur de montantTotalMensuelRemiseFamille
     */
    public Float getMontantTotalMensuelRemiseFamille() {
        return montantTotalMensuelRemiseFamille;
    }

    /**
     * Définit la valeur de montantTotalMensuelRemiseFamille.
     * @param montantTotalMensuelRemiseFamille la nouvelle valeur de montantTotalMensuelRemiseFamille
     */
    public void setMontantTotalMensuelRemiseFamille(Float montantTotalMensuelRemiseFamille) {
        this.montantTotalMensuelRemiseFamille = montantTotalMensuelRemiseFamille;
    }

    /**
     * Récupère la valeur de montantTotalUniqueFamilleSansRemise.
     * @return la valeur de montantTotalUniqueFamilleSansRemise
     */
    public Float getMontantTotalUniqueFamilleSansRemise() {
        return montantTotalUniqueFamilleSansRemise;
    }

    /**
     * Définit la valeur de montantTotalUniqueFamilleSansRemise.
     * @param montantTotalUniqueFamilleSansRemise la nouvelle valeur de montantTotalUniqueFamilleSansRemise
     */
    public void setMontantTotalUniqueFamilleSansRemise(Float montantTotalUniqueFamilleSansRemise) {
        this.montantTotalUniqueFamilleSansRemise = montantTotalUniqueFamilleSansRemise;
    }

    /**
     * Récupère la valeur de montantTotalUniqueRemiseFamille.
     * @return la valeur de montantTotalUniqueRemiseFamille
     */
    public Float getMontantTotalUniqueRemiseFamille() {
        return montantTotalUniqueRemiseFamille;
    }

    /**
     * Définit la valeur de montantTotalUniqueRemiseFamille.
     * @param montantTotalUniqueRemiseFamille la nouvelle valeur de montantTotalUniqueRemiseFamille
     */
    public void setMontantTotalUniqueRemiseFamille(Float montantTotalUniqueRemiseFamille) {
        this.montantTotalUniqueRemiseFamille = montantTotalUniqueRemiseFamille;
    }

    /**
     * Calcule le montant total mensuel de la famille avec la remise.
     * @return le montant avec la remise
     */
    public Float getMontantTotalMensuelFamilleAvecRemise() {
        if (montantTotalMensuelFamilleSansRemise != null) {
            float montantTotalMensuelFamilleAvecRemis = 0f;
            montantTotalMensuelFamilleAvecRemis += montantTotalMensuelFamilleSansRemise;
            if (montantTotalMensuelRemiseFamille != null) {
                montantTotalMensuelFamilleAvecRemis -= montantTotalMensuelRemiseFamille;
            }
            return montantTotalMensuelFamilleAvecRemis;
        } else {
            return null;
        }
    }

    /**
     * Calcule le montant total unique de la famille avec la remise.
     * @return le montant avec la remise
     */
    public Float getMontantTotalUniqueFamilleAvecRemise() {
        if (montantTotalUniqueFamilleSansRemise != null) {
            float montantTotalUniqueFamilleAvecRemise = 0f;
            montantTotalUniqueFamilleAvecRemise += montantTotalUniqueFamilleSansRemise;
            if (montantTotalUniqueRemiseFamille != null) {
                montantTotalUniqueFamilleAvecRemise -= montantTotalUniqueRemiseFamille;
            }
            return montantTotalUniqueFamilleAvecRemise;
        } else {
            return null;
        }
    }

}

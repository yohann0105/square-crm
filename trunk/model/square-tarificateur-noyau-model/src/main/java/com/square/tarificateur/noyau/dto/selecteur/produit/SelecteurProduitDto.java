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

/**
 * Dto representant le selecteur de produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class SelecteurProduitDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 7795191619200665535L;

    /** Identifiant du devis. */
    private Long identifiantDevis;

    /** Identifiant de la ligne de devis principale. */
    private Long identifiantLigneDevisPrincipale;

    /** Famille principale du sélecteur. */
    private FamillePrincipaleSelecteurDto famillePrincipale;

    /** Liste des familles liées du sélecteur. */
    private List<FamilleLieeSelecteurDto> listeFamillesLiees;

    /** Identifiant application externe, permet de transporter une information complémentaire pour les autres systèmes (ie Selligent). */
    private Long identifiantExterne;

    /** Identifiant de l'auteur de la ligne de devis pour l'ajout d'une nouvelle ligne. */
    private Long eidAuteur;

    /**
     * Get the identifiantDevis value.
     * @return the identifiantDevis
     */
    public Long getIdentifiantDevis() {
        return identifiantDevis;
    }

    /**
     * Set the identifiantDevis value.
     * @param identifiantDevis the identifiantDevis to set
     */
    public void setIdentifiantDevis(Long identifiantDevis) {
        this.identifiantDevis = identifiantDevis;
    }

    /**
     * Get the famillePrincipale value.
     * @return the famillePrincipale
     */
    public FamillePrincipaleSelecteurDto getFamillePrincipale() {
        return famillePrincipale;
    }

    /**
     * Set the famillePrincipale value.
     * @param famillePrincipale the famillePrincipale to set
     */
    public void setFamillePrincipale(FamillePrincipaleSelecteurDto famillePrincipale) {
        this.famillePrincipale = famillePrincipale;
    }

    /**
     * Get the listeFamillesLiees value.
     * @return the listeFamillesLiees
     */
    public List<FamilleLieeSelecteurDto> getListeFamillesLiees() {
        return listeFamillesLiees;
    }

    /**
     * Set the listeFamillesLiees value.
     * @param listeFamillesLiees the listeFamillesLiees to set
     */
    public void setListeFamillesLiees(List<FamilleLieeSelecteurDto> listeFamillesLiees) {
        this.listeFamillesLiees = listeFamillesLiees;
    }

    /**
     * Recupere la valeur de identifiantLigneDevisPrincipale.
     * @return la valeur de identifiantLigneDevisPrincipale
     */
    public Long getIdentifiantLigneDevisPrincipale() {
        return identifiantLigneDevisPrincipale;
    }

    /**
     * Definit la valeur de identifiantLigneDevisPrincipale.
     * @param identifiantLigneDevisPrincipale la nouvelle valeur de identifiantLigneDevisPrincipale
     */
    public void setIdentifiantLigneDevisPrincipale(Long identifiantLigneDevisPrincipale) {
        this.identifiantLigneDevisPrincipale = identifiantLigneDevisPrincipale;
    }

    /**
     * Get the identifiantExterne value.
     * @return the identifiantExterne
     */
    public Long getIdentifiantExterne() {
        return identifiantExterne;
    }

    /**
     * Set the identifiantExterne value.
     * @param identifiantExterne the identifiantExterne to set
     */
    public void setIdentifiantExterne(Long identifiantExterne) {
        this.identifiantExterne = identifiantExterne;
    }

    /**
     * Récupère la valeur de eidAuteur.
     * @return la valeur de eidAuteur
     */
    public Long getEidAuteur() {
        return eidAuteur;
    }

    /**
     * Définit la valeur de eidAuteur.
     * @param eidAuteur la nouvelle valeur de eidAuteur
     */
    public void setEidAuteur(Long eidAuteur) {
        this.eidAuteur = eidAuteur;
    }

}

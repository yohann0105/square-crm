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
/**
 * 
 */
package com.square.tarificateur.noyau.dto.devis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Dto pour la demande de devis PDF.
 * @author Goumard stephane (stephane.goumard@scub.net) - SCUB
 */
public class InfosDevisPdfDto implements Serializable
{
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 7253662296493928252L;

    /**
     * Identifiant du devis.
     */
    private Long identifiantDevis;

    /**
     * Ligne de devis liée selectionnée.
     */
    private List < Long > idsLigneDevisSelection;

    /**
     * Liste des bénéficiaires sélectionnés.
     */
    private List < Long > idsBeneficiairesSelectionnes;

    /**
     * Identifiant du modele de devis.
     */
    private Long identifiantModeleDevis;

    /**
     * Référence de l'opportunité d'un devis.
     */
    private String referenceOpportunite;

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
     * Get the identifiantModeleDevis value.
     * @return the identifiantModeleDevis
     */
    public Long getIdentifiantModeleDevis() {
        return identifiantModeleDevis;
    }

    /**
     * Set the identifiantModeleDevis value.
     * @param identifiantModeleDevis the identifiantModeleDevis to set
     */
    public void setIdentifiantModeleDevis(Long identifiantModeleDevis) {
        this.identifiantModeleDevis = identifiantModeleDevis;
    }

    /**
     * Get the idsLigneDevisLieeSelection value.
     * @return the idsLigneDevisLieeSelection
     */
    public List < Long > getIdsLigneDevisSelection()
    {
        if (idsLigneDevisSelection == null)
        {
            idsLigneDevisSelection = new ArrayList < Long > ();
        }
        return idsLigneDevisSelection;
    }

    /**
     * Set the idsLigneDevisLieeSelection value.
     * @param idsLigneDevisLieeSelection the idsLigneDevisLieeSelection to set
     */
    public void setIdsLigneDevisSelection(List < Long > idsLigneDevisLieeSelection) {
        this.idsLigneDevisSelection = idsLigneDevisLieeSelection;
    }

    /**
     * Get the referenceOpportunite value.
     * @return the referenceOpportunite
     */
    public String getReferenceOpportunite() {
        return referenceOpportunite;
    }

    /**
     * Set the referenceOpportunite value.
     * @param referenceOpportunite the referenceOpportunite to set
     */
    public void setReferenceOpportunite(String referenceOpportunite) {
        this.referenceOpportunite = referenceOpportunite;
    }

    /**
     * Get the idsBeneficiairesSelectionnes value.
     * @return the idsBeneficiairesSelectionnes
     */
    public List < Long > getIdsBeneficiairesSelectionnes() {
        return idsBeneficiairesSelectionnes;
    }

    /**
     * Set the idsBeneficiairesSelectionnes value.
     * @param idsBeneficiairesSelectionnes the idsBeneficiairesSelectionnes to set
     */
    public void setIdsBeneficiairesSelectionnes(List < Long > idsBeneficiairesSelectionnes) {
        this.idsBeneficiairesSelectionnes = idsBeneficiairesSelectionnes;
    }
}

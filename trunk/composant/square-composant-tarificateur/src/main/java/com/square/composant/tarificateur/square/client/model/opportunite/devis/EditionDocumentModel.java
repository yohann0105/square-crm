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
package com.square.composant.tarificateur.square.client.model.opportunite.devis;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle pour l'edition de document.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class EditionDocumentModel implements IsSerializable {

    /** Identifiant du devis. */
    private Long identifiantDevis;

    /** Ligne de devis liée selectionnée. */
    private List<Long> idsLigneDevisSelection;

    /** Liste des bénéficiaires sélectionnés. */
    private List<Long> idsBeneficiairesSelectionnes;

    /** Identifiants des modèles de devis sélectionnés. */
    private List<Long> idsModelesDevisSelectionnes;

    /** Référence de l'opportunité d'un devis. */
    private String referenceOpportunite;

    /**
     * Récupère la valeur de identifiantDevis.
     * @return la valeur de identifiantDevis
     */
    public Long getIdentifiantDevis() {
        return identifiantDevis;
    }

    /**
     * Définit la valeur de identifiantDevis.
     * @param identifiantDevis la nouvelle valeur de identifiantDevis
     */
    public void setIdentifiantDevis(Long identifiantDevis) {
        this.identifiantDevis = identifiantDevis;
    }

    /**
     * Récupère la valeur de idsLigneDevisSelection.
     * @return la valeur de idsLigneDevisSelection
     */
    public List<Long> getIdsLigneDevisSelection() {
        return idsLigneDevisSelection;
    }

    /**
     * Définit la valeur de idsLigneDevisSelection.
     * @param idsLigneDevisSelection la nouvelle valeur de idsLigneDevisSelection
     */
    public void setIdsLigneDevisSelection(List<Long> idsLigneDevisSelection) {
        this.idsLigneDevisSelection = idsLigneDevisSelection;
    }

    /**
     * Récupère la valeur de idsBeneficiairesSelectionnes.
     * @return la valeur de idsBeneficiairesSelectionnes
     */
    public List<Long> getIdsBeneficiairesSelectionnes() {
        return idsBeneficiairesSelectionnes;
    }

    /**
     * Définit la valeur de idsBeneficiairesSelectionnes.
     * @param idsBeneficiairesSelectionnes la nouvelle valeur de idsBeneficiairesSelectionnes
     */
    public void setIdsBeneficiairesSelectionnes(List<Long> idsBeneficiairesSelectionnes) {
        this.idsBeneficiairesSelectionnes = idsBeneficiairesSelectionnes;
    }

    /**
     * Récupère la valeur de idsModelesDevisSelectionnes.
     * @return la valeur de idsModelesDevisSelectionnes
     */
    public List<Long> getIdsModelesDevisSelectionnes() {
        return idsModelesDevisSelectionnes;
    }

    /**
     * Définit la valeur de idsModelesDevisSelectionnes.
     * @param idsModelesDevisSelectionnes la nouvelle valeur de idsModelesDevisSelectionnes
     */
    public void setIdsModelesDevisSelectionnes(List<Long> idsModelesDevisSelectionnes) {
        this.idsModelesDevisSelectionnes = idsModelesDevisSelectionnes;
    }

    /**
     * Récupère la valeur de referenceOpportunite.
     * @return la valeur de referenceOpportunite
     */
    public String getReferenceOpportunite() {
        return referenceOpportunite;
    }

    /**
     * Définit la valeur de referenceOpportunite.
     * @param referenceOpportunite la nouvelle valeur de referenceOpportunite
     */
    public void setReferenceOpportunite(String referenceOpportunite) {
        this.referenceOpportunite = referenceOpportunite;
    }
}

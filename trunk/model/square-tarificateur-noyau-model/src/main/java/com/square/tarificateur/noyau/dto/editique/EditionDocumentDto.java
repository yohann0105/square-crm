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
package com.square.tarificateur.noyau.dto.editique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

import com.square.tarificateur.noyau.dto.InfosRibDto;

/**
 * DTO permettant l'édition d'un document.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class EditionDocumentDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 635851187831988962L;

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

    /** Identifiant du modele de l'email (valeur par défaut si null). */
    private Long idModeleEmail;

    /** Flag pour savoir si le devis vient de la signature numérique. */
    private Boolean fromSignatureNumerique = Boolean.FALSE;

    /** Flag pour savoir si le document a été signé (adhésion validée). */
    private Boolean documentSigneNumeriquement = Boolean.FALSE;

    /** Informations du RIB. */
    private InfosRibDto infosRib;

    /** Date de signature de l'adhésion. */
    private Calendar dateSignature;

    /** Indique si l'adhérent souhaite la télétransmission. */
    private Boolean teletransmission;

    /** Date de l'édition du BA (si null, la date ne sera pas renseignée dans le service). */
    private Calendar dateEditionBa;

    /** Identifiant du créateur pour les actions à créer à l'édition (identifiant des habilitations). */
    private Long eidCreateur;

    /** Jour de paiement. */
    private IdentifiantLibelleDto jourPaiement;

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
     * Get the idsLigneDevisLieeSelection value.
     * @return the idsLigneDevisLieeSelection
     */
    public List<Long> getIdsLigneDevisSelection() {
        if (idsLigneDevisSelection == null) {
            idsLigneDevisSelection = new ArrayList<Long>();
        }
        return idsLigneDevisSelection;
    }

    /**
     * Set the idsLigneDevisLieeSelection value.
     * @param idsLigneDevisLieeSelection the idsLigneDevisLieeSelection to set
     */
    public void setIdsLigneDevisSelection(List<Long> idsLigneDevisLieeSelection) {
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
    public List<Long> getIdsBeneficiairesSelectionnes() {
        return idsBeneficiairesSelectionnes;
    }

    /**
     * Set the idsBeneficiairesSelectionnes value.
     * @param idsBeneficiairesSelectionnes the idsBeneficiairesSelectionnes to set
     */
    public void setIdsBeneficiairesSelectionnes(List<Long> idsBeneficiairesSelectionnes) {
        this.idsBeneficiairesSelectionnes = idsBeneficiairesSelectionnes;
    }

    /**
     * Getter function.
     * @return the idsModelesDevisSelectionnes
     */
    public List<Long> getIdsModelesDevisSelectionnes() {
        return idsModelesDevisSelectionnes;
    }

    /**
     * Setter function.
     * @param idsModelesDevisSelectionnes the idsModelesDevisSelectionnes to set
     */
    public void setIdsModelesDevisSelectionnes(List<Long> idsModelesDevisSelectionnes) {
        this.idsModelesDevisSelectionnes = idsModelesDevisSelectionnes;
    }

    /**
     * Get the idModeleEmail value.
     * @return the idModeleEmail
     */
    public Long getIdModeleEmail() {
        return idModeleEmail;
    }

    /**
     * Set the idModeleEmail value.
     * @param idModeleEmail the idModeleEmail to set
     */
    public void setIdModeleEmail(Long idModeleEmail) {
        this.idModeleEmail = idModeleEmail;
    }

    /**
     * Get the fromSignatureNumerique value.
     * @return the fromSignatureNumerique
     */
    public Boolean getFromSignatureNumerique() {
        return fromSignatureNumerique;
    }

    /**
     * Set the fromSignatureNumerique value.
     * @param fromSignatureNumerique the fromSignatureNumerique to set
     */
    public void setFromSignatureNumerique(Boolean fromSignatureNumerique) {
        this.fromSignatureNumerique = fromSignatureNumerique;
    }

    /**
     * Get the infosRib value.
     * @return the infosRib
     */
    public InfosRibDto getInfosRib() {
        return infosRib;
    }

    /**
     * Set the infosRib value.
     * @param infosRib the infosRib to set
     */
    public void setInfosRib(InfosRibDto infosRib) {
        this.infosRib = infosRib;
    }

    /**
     * Get the documentSigneNumeriquement value.
     * @return the documentSigneNumeriquement
     */
    public Boolean getDocumentSigneNumeriquement() {
        return documentSigneNumeriquement;
    }

    /**
     * Set the documentSigneNumeriquement value.
     * @param documentSigneNumeriquement the documentSigneNumeriquement to set
     */
    public void setDocumentSigneNumeriquement(Boolean documentSigneNumeriquement) {
        this.documentSigneNumeriquement = documentSigneNumeriquement;
    }

    /**
     * Get the teletransmission value.
     * @return the teletransmission
     */
    public Boolean getTeletransmission() {
        return teletransmission;
    }

    /**
     * Set the teletransmission value.
     * @param teletransmission the teletransmission to set
     */
    public void setTeletransmission(Boolean teletransmission) {
        this.teletransmission = teletransmission;
    }

    /**
     * Get the dateSignature value.
     * @return the dateSignature
     */
    public Calendar getDateSignature() {
        return dateSignature;
    }

    /**
     * Set the dateSignature value.
     * @param dateSignature the dateSignature to set
     */
    public void setDateSignature(Calendar dateSignature) {
        this.dateSignature = dateSignature;
    }

    /**
     * Récupère la valeur de dateEditionBa.
     * @return la valeur de dateEditionBa
     */
    public Calendar getDateEditionBa() {
        return dateEditionBa;
    }

    /**
     * Définit la valeur de dateEditionBa.
     * @param dateEditionBa la nouvelle valeur de dateEditionBa
     */
    public void setDateEditionBa(Calendar dateEditionBa) {
        this.dateEditionBa = dateEditionBa;
    }

    /**
     * Récupère la valeur de eidCreateur.
     * @return la valeur de eidCreateur
     */
    public Long getEidCreateur() {
        return eidCreateur;
    }

    /**
     * Définit la valeur de eidCreateur.
     * @param eidCreateur la nouvelle valeur de eidCreateur
     */
    public void setEidCreateur(Long eidCreateur) {
        this.eidCreateur = eidCreateur;
    }

    /**
     * Récupère la valeur de jourPaiement.
     * @return la valeur de jourPaiement
     */
    public IdentifiantLibelleDto getJourPaiement() {
        return jourPaiement;
    }

    /**
     * Définit la valeur de jourPaiement.
     * @param jourPaiement la nouvelle valeur de jourPaiement
     */
    public void setJourPaiement(IdentifiantLibelleDto jourPaiement) {
        this.jourPaiement = jourPaiement;
    }

}

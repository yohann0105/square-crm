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
import java.util.Calendar;
import java.util.List;

/**
 * DTO représentant l'ensemble des données nécessaires à l'édition d'un bulletin d'adhésion / fiche de transfert.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class BulletinAdhesionDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -8296287104711900671L;

    /** Numéro du dossier. */
    private String numeroDossier;

    /** Titre du document de bulletin d'adhesion. */
    private String titreDocumentBA;

    /** Le montant total mensuel sans la remise. */
    private Float montantTotalMensuelSansRemise;

    /** Le montant total mensuel de la remise. */
    private Float montantTotalMensuelRemise;

    /** Libellé du montant total mensuel. */
    private String libelleMontantTotalMensuel;

    /** Le montant total unique sans la remise. */
    private Float montantTotalUniqueSansRemise;

    /** Le montant total unique sans la remise. */
    private Float montantTotalUniqueRemise;

    /** Libellé du montant total unique. */
    private String libelleMontantTotalUnique;

    /** Libellé du bonus Eco. */
    private String libelleBonusEco;

    /** Liste des bénéficiaires de l'adhésion. */
    private List<BeneficiaireBADto> listeBeneficiaires;

    /** La proposition correspondant à la demande d'adhésion. */
    private PropositionBADto proposition;

    /** Indique que l'adhésion contient le produit décés famille. */
    private Boolean possedeDecesFamille;

    /** Indique que l'adhésion contient le produit décés individuel. */
    private Boolean possedeDecesIndividuel;

    /** Indique que l'adhésion possède un produit Garantie obsèques. */
    private Boolean possedeGarantieObseques;

    /** Montant du capital garanti. */
    private Float montantCapitalGaranti;

    /** Date d'effet du Bonus 1. */
    private Calendar dateEffetBonus1;

    /** Numéro d'adhérent du parrain. */
    private String numeroAdherentParrain;

    /** Code génération. */
    private String codeGeneration;

    /** Informations supplémentaires si la demande d'adhésion provient du site Web de la SMATIS. */
    private InfosAdhesionInternetDto infosAdhesionInternet;

    /** Informations concernant le règlement. */
    private InfosReglementDto infosReglement;

    /** Informations concernant le rib. */
    private InfosRibDto infosRib;

    /** Flag pour savoir si l'adhérent ou ses bénefs possède un délai d'attente. */
    private Boolean possedeDelaiAttente = Boolean.FALSE;

    /** Date de signature. */
    private Calendar dateSignature;

    /** Flag pour savoir si le document a été signé (adhésion validée). */
    private Boolean documentSigneNumeriquement = Boolean.FALSE;

    /** Constructeur. */
    public BulletinAdhesionDto() {
    }

    /**
     * Get the numeroDossier value.
     * @return the numeroDossier
     */
    public String getNumeroDossier() {
        return numeroDossier;
    }

    /**
     * Set the numeroDossier value.
     * @param numeroDossier the numeroDossier to set
     */
    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    /**
     * Get the titreDocumentBA value.
     * @return the titreDocumentBA
     */
    public String getTitreDocumentBA() {
        return titreDocumentBA;
    }

    /**
     * Set the titreDocumentBA value.
     * @param titreDocumentBA the titreDocumentBA to set
     */
    public void setTitreDocumentBA(String titreDocumentBA) {
        this.titreDocumentBA = titreDocumentBA;
    }

    /**
     * Get the libelleMontantTotalMensuel value.
     * @return the libelleMontantTotalMensuel
     */
    public String getLibelleMontantTotalMensuel() {
        return libelleMontantTotalMensuel;
    }

    /**
     * Set the libelleMontantTotalMensuel value.
     * @param libelleMontantTotalMensuel the libelleMontantTotalMensuel to set
     */
    public void setLibelleMontantTotalMensuel(String libelleMontantTotalMensuel) {
        this.libelleMontantTotalMensuel = libelleMontantTotalMensuel;
    }

    /**
     * Get the libelleMontantTotalUnique value.
     * @return the libelleMontantTotalUnique
     */
    public String getLibelleMontantTotalUnique() {
        return libelleMontantTotalUnique;
    }

    /**
     * Set the libelleMontantTotalUnique value.
     * @param libelleMontantTotalUnique the libelleMontantTotalUnique to set
     */
    public void setLibelleMontantTotalUnique(String libelleMontantTotalUnique) {
        this.libelleMontantTotalUnique = libelleMontantTotalUnique;
    }

    /**
     * Get the libelleBonusEco value.
     * @return the libelleBonusEco
     */
    public String getLibelleBonusEco() {
        return libelleBonusEco;
    }

    /**
     * Set the libelleBonusEco value.
     * @param libelleBonusEco the libelleBonusEco to set
     */
    public void setLibelleBonusEco(String libelleBonusEco) {
        this.libelleBonusEco = libelleBonusEco;
    }

    /**
     * Get the listeBeneficiaires value.
     * @return the listeBeneficiaires
     */
    public List<BeneficiaireBADto> getListeBeneficiaires() {
        return listeBeneficiaires;
    }

    /**
     * Set the listeBeneficiaires value.
     * @param listeBeneficiaires the listeBeneficiaires to set
     */
    public void setListeBeneficiaires(List<BeneficiaireBADto> listeBeneficiaires) {
        this.listeBeneficiaires = listeBeneficiaires;
    }

    /**
     * Get the proposition value.
     * @return the proposition
     */
    public PropositionBADto getProposition() {
        return proposition;
    }

    /**
     * Set the proposition value.
     * @param proposition the proposition to set
     */
    public void setProposition(PropositionBADto proposition) {
        this.proposition = proposition;
    }

    /**
     * Get the possedeDecesFamille value.
     * @return the possedeDecesFamille
     */
    public Boolean getPossedeDecesFamille() {
        return possedeDecesFamille;
    }

    /**
     * Set the possedeDecesFamille value.
     * @param possedeDecesFamille the possedeDecesFamille to set
     */
    public void setPossedeDecesFamille(Boolean possedeDecesFamille) {
        this.possedeDecesFamille = possedeDecesFamille;
    }

    /**
     * Get the possedeDecesIndividuel value.
     * @return the possedeDecesIndividuel
     */
    public Boolean getPossedeDecesIndividuel() {
        return possedeDecesIndividuel;
    }

    /**
     * Set the possedeDecesIndividuel value.
     * @param possedeDecesIndividuel the possedeDecesIndividuel to set
     */
    public void setPossedeDecesIndividuel(Boolean possedeDecesIndividuel) {
        this.possedeDecesIndividuel = possedeDecesIndividuel;
    }

    /**
     * Get the montantCapitalGaranti value.
     * @return the montantCapitalGaranti
     */
    public Float getMontantCapitalGaranti() {
        return montantCapitalGaranti;
    }

    /**
     * Set the montantCapitalGaranti value.
     * @param montantCapitalGaranti the montantCapitalGaranti to set
     */
    public void setMontantCapitalGaranti(Float montantCapitalGaranti) {
        this.montantCapitalGaranti = montantCapitalGaranti;
    }

    /**
     * Get the infosAdhesionInternet value.
     * @return the infosAdhesionInternet
     */
    public InfosAdhesionInternetDto getInfosAdhesionInternet() {
        return infosAdhesionInternet;
    }

    /**
     * Set the infosAdhesionInternet value.
     * @param infosAdhesionInternet the infosAdhesionInternet to set
     */
    public void setInfosAdhesionInternet(InfosAdhesionInternetDto infosAdhesionInternet) {
        this.infosAdhesionInternet = infosAdhesionInternet;
    }

    /**
     * Récupère la valeur de dateEffetBonus1.
     * @return la valeur de dateEffetBonus1
     */
    public Calendar getDateEffetBonus1() {
        return dateEffetBonus1;
    }

    /**
     * Définit la valeur de dateEffetBonus1.
     * @param dateEffetBonus1 la nouvelle valeur de dateEffetBonus1
     */
    public void setDateEffetBonus1(Calendar dateEffetBonus1) {
        this.dateEffetBonus1 = dateEffetBonus1;
    }

    /**
     * Récupère la valeur de codeGeneration.
     * @return la valeur de codeGeneration
     */
    public String getCodeGeneration() {
        return codeGeneration;
    }

    /**
     * Définit la valeur de codeGeneration.
     * @param codeGeneration la nouvelle valeur de codeGeneration
     */
    public void setCodeGeneration(String codeGeneration) {
        this.codeGeneration = codeGeneration;
    }

    /**
     * Récupère la valeur de infosReglement.
     * @return la valeur de infosReglement
     */
    public InfosReglementDto getInfosReglement() {
        return infosReglement;
    }

    /**
     * Définit la valeur de infosReglement.
     * @param infosReglement la nouvelle valeur de infosReglement
     */
    public void setInfosReglement(InfosReglementDto infosReglement) {
        this.infosReglement = infosReglement;
    }

    /**
     * Récupère la valeur de montantTotalMensuelSansRemise.
     * @return la valeur de montantTotalMensuelSansRemise
     */
    public Float getMontantTotalMensuelSansRemise() {
        return montantTotalMensuelSansRemise;
    }

    /**
     * Définit la valeur de montantTotalMensuelSansRemise.
     * @param montantTotalMensuelSansRemise la nouvelle valeur de montantTotalMensuelSansRemise
     */
    public void setMontantTotalMensuelSansRemise(Float montantTotalMensuelSansRemise) {
        this.montantTotalMensuelSansRemise = montantTotalMensuelSansRemise;
    }

    /**
     * Récupère la valeur de montantTotalMensuelRemise.
     * @return la valeur de montantTotalMensuelRemise
     */
    public Float getMontantTotalMensuelRemise() {
        return montantTotalMensuelRemise;
    }

    /**
     * Définit la valeur de montantTotalMensuelRemise.
     * @param montantTotalMensuelRemise la nouvelle valeur de montantTotalMensuelRemise
     */
    public void setMontantTotalMensuelRemise(Float montantTotalMensuelRemise) {
        this.montantTotalMensuelRemise = montantTotalMensuelRemise;
    }

    /**
     * Récupère la valeur de montantTotalUniqueSansRemise.
     * @return la valeur de montantTotalUniqueSansRemise
     */
    public Float getMontantTotalUniqueSansRemise() {
        return montantTotalUniqueSansRemise;
    }

    /**
     * Définit la valeur de montantTotalUniqueSansRemise.
     * @param montantTotalUniqueSansRemise la nouvelle valeur de montantTotalUniqueSansRemise
     */
    public void setMontantTotalUniqueSansRemise(Float montantTotalUniqueSansRemise) {
        this.montantTotalUniqueSansRemise = montantTotalUniqueSansRemise;
    }

    /**
     * Récupère la valeur de montantTotalUniqueRemise.
     * @return la valeur de montantTotalUniqueRemise
     */
    public Float getMontantTotalUniqueRemise() {
        return montantTotalUniqueRemise;
    }

    /**
     * Définit la valeur de montantTotalUniqueRemise.
     * @param montantTotalUniqueRemise la nouvelle valeur de montantTotalUniqueRemise
     */
    public void setMontantTotalUniqueRemise(Float montantTotalUniqueRemise) {
        this.montantTotalUniqueRemise = montantTotalUniqueRemise;
    }

    /**
     * Calcule le montant total mensuel sans la remise.
     * @return le montant avec la remise
     */
    public Float getMontantTotalMensuelAvecRemise() {
        if (montantTotalMensuelSansRemise != null) {
            float montantTotalMensuelAvecRemise = 0f;
            montantTotalMensuelAvecRemise += montantTotalMensuelSansRemise;
            if (montantTotalMensuelRemise != null) {
                montantTotalMensuelAvecRemise -= montantTotalMensuelRemise;
            }
            return montantTotalMensuelAvecRemise;
        } else {
            return null;
        }
    }

    /**
     * Calcule le montant total unique sans la remise.
     * @return le montant avec la remise
     */
    public Float getMontantTotalUniqueAvecRemise() {
        if (montantTotalUniqueSansRemise != null) {
            float montantTotalUniqueAvecRemise = 0f;
            montantTotalUniqueAvecRemise += montantTotalUniqueSansRemise;
            if (montantTotalUniqueRemise != null) {
                montantTotalUniqueAvecRemise -= montantTotalUniqueRemise;
            }
            return montantTotalUniqueAvecRemise;
        } else {
            return null;
        }
    }

    /**
     * Récupère la valeur de possedeGarantieObseques.
     * @return la valeur de possedeGarantieObseques
     */
    public Boolean getPossedeGarantieObseques() {
        return possedeGarantieObseques;
    }

    /**
     * Définit la valeur de possedeGarantieObseques.
     * @param possedeGarantieObseques la nouvelle valeur de possedeGarantieObseques
     */
    public void setPossedeGarantieObseques(Boolean possedeGarantieObseques) {
        this.possedeGarantieObseques = possedeGarantieObseques;
    }

    /**
     * Getter function.
     * @return the numeroAdherentParrain
     */
    public String getNumeroAdherentParrain() {
        return numeroAdherentParrain;
    }

    /**
     * Setter function.
     * @param numeroAdherentParrain the numeroAdherentParrain to set
     */
    public void setNumeroAdherentParrain(String numeroAdherentParrain) {
        this.numeroAdherentParrain = numeroAdherentParrain;
    }

    /**
     * Get possedeDelaiAttente.
     * @return the possedeDelaiAttente
     */
    public Boolean getPossedeDelaiAttente() {
        return possedeDelaiAttente;
    }

    /**
     * Set possedeDelaiAttente.
     * @param possedeDelaiAttente the possedeDelaiAttente to set
     */
    public void setPossedeDelaiAttente(Boolean possedeDelaiAttente) {
        this.possedeDelaiAttente = possedeDelaiAttente;
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

}

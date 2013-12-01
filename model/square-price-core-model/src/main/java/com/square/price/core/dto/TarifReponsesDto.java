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
 * Dto reponse sur les services recupération des tarifs.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class TarifReponsesDto implements Serializable {
    private static final long serialVersionUID = 358242657240127360L;

    /** Code garantie. */
    private String codeGarantie;

    /** Libelle garantie. */
    private String libelleGarantie;

    /** Code entreprise. */
    private String codeEntreprise;

    /** Libelle entreprise. */
    private String libelleEntreprise;

    /** Code adherent. */
    private String codeAdherent;

    /** Code role. */
    private String codeRole;

    /** Libelle role. */
    private String libelleRole;

    /** Code apporteur. */
    private String codeApporteur;

    /** Libelle apporteur. */
    private String libelleApporteur;

    /** Code Gestion. */
    private String codeGestion;

    /** Libelle Gestion. */
    private String libelleGestion;

    /** Code age min. */
    private Integer codeAgeMin;

    /** Code age max. */
    private Integer codeAgeMax;

    /** Libelle age. */
    private String libelleAge;

    /** Code composition familial. */
    private String codeCompositionFamille;

    /** Libelle Composition Familial. */
    private String libelleCompositionFamille;

    /** Code Zone. */
    private Integer codeZone;

    /** Lbelle Zone. */
    private String libelleZone;

    /** Code Nivgar. */
    private String codeNivgar;

    /** Libelle Nivgar. */
    private String libelleNivgar;

    /** Code taux de couverture. */
    private String codeTauxCouverture;

    /** Lielle taux de couverture. */
    private String libelleTauxCouverture;

    /** Montant. */
    private Float montant;

    /** Montant du tarif arrondis. */
    private String affichageMontant;

    /** Montant. */
    private Float montantBareme1;

    /** Montant du tarif arrondis. */
    private String affichageMontantBareme1;

    /** Montant. */
    private Float montantBareme2;

    /** Montant du tarif arrondis. */
    private String affichageMontantBareme2;

    /** Code Generation. */
    private String codeGeneration;

    /** Libelle Generation. */
    private String libelleGeneration;

    /** Code mois. */
    private String codeMois;

    /** Libellé mois. */
    private String libelleMois;

    /**
     * Get the codeAdherent value.
     * @return the codeAdherent
     */
    public String getCodeAdherent() {
        return codeAdherent;
    }

    /**
     * Set the codeAdherent value.
     * @param codeAdherent the codeAdherent to set
     */
    public void setCodeAdherent(String codeAdherent) {
        this.codeAdherent = codeAdherent;
    }

    /**
     * Get the codeAgeMax value.
     * @return the codeAgeMax
     */
    public Integer getCodeAgeMax() {
        return codeAgeMax;
    }

    /**
     * Set the codeAgeMax value.
     * @param codeAgeMax the codeAgeMax to set
     */
    public void setCodeAgeMax(Integer codeAgeMax) {
        this.codeAgeMax = codeAgeMax;
    }

    /**
     * Get the codeAgeMin value.
     * @return the codeAgeMin
     */
    public Integer getCodeAgeMin() {
        return codeAgeMin;
    }

    /**
     * Set the codeAgeMin value.
     * @param codeAgeMin the codeAgeMin to set
     */
    public void setCodeAgeMin(Integer codeAgeMin) {
        this.codeAgeMin = codeAgeMin;
    }

    /**
     * Get the codeApporteur value.
     * @return the codeApporteur
     */
    public String getCodeApporteur() {
        return codeApporteur;
    }

    /**
     * Set the codeApporteur value.
     * @param codeApporteur the codeApporteur to set
     */
    public void setCodeApporteur(String codeApporteur) {
        this.codeApporteur = codeApporteur;
    }

    /**
     * Get the codeCompositionFamille value.
     * @return the codeCompositionFamille
     */
    public String getCodeCompositionFamille() {
        return codeCompositionFamille;
    }

    /**
     * Set the codeCompositionFamille value.
     * @param codeCompositionFamille the codeCompositionFamille to set
     */
    public void setCodeCompositionFamille(String codeCompositionFamille) {
        this.codeCompositionFamille = codeCompositionFamille;
    }

    /**
     * Get the codeEntreprise value.
     * @return the codeEntreprise
     */
    public String getCodeEntreprise() {
        return codeEntreprise;
    }

    /**
     * Set the codeEntreprise value.
     * @param codeEntreprise the codeEntreprise to set
     */
    public void setCodeEntreprise(String codeEntreprise) {
        this.codeEntreprise = codeEntreprise;
    }

    /**
     * Get the codeGarantie value.
     * @return the codeGarantie
     */
    public String getCodeGarantie() {
        return codeGarantie;
    }

    /**
     * Set the codeGarantie value.
     * @param codeGarantie the codeGarantie to set
     */
    public void setCodeGarantie(String codeGarantie) {
        this.codeGarantie = codeGarantie;
    }

    /**
     * Get the codeGestion value.
     * @return the codeGestion
     */
    public String getCodeGestion() {
        return codeGestion;
    }

    /**
     * Set the codeGestion value.
     * @param codeGestion the codeGestion to set
     */
    public void setCodeGestion(String codeGestion) {
        this.codeGestion = codeGestion;
    }

    /**
     * Get the codeRole value.
     * @return the codeRole
     */
    public String getCodeRole() {
        return codeRole;
    }

    /**
     * Set the codeRole value.
     * @param codeRole the codeRole to set
     */
    public void setCodeRole(String codeRole) {
        this.codeRole = codeRole;
    }

    /**
     * Get the codeZone value.
     * @return the codeZone
     */
    public Integer getCodeZone() {
        return codeZone;
    }

    /**
     * Set the codeZone value.
     * @param codeZone the codeZone to set
     */
    public void setCodeZone(Integer codeZone) {
        this.codeZone = codeZone;
    }

    /**
     * Get the libelleAge value.
     * @return the libelleAge
     */
    public String getLibelleAge() {
        return libelleAge;
    }

    /**
     * Set the libelleAge value.
     * @param libelleAge the libelleAge to set
     */
    public void setLibelleAge(String libelleAge) {
        this.libelleAge = libelleAge;
    }

    /**
     * Get the libelleApporteur value.
     * @return the libelleApporteur
     */
    public String getLibelleApporteur() {
        return libelleApporteur;
    }

    /**
     * Set the libelleApporteur value.
     * @param libelleApporteur the libelleApporteur to set
     */
    public void setLibelleApporteur(String libelleApporteur) {
        this.libelleApporteur = libelleApporteur;
    }

    /**
     * Get the libelleEntreprise value.
     * @return the libelleEntreprise
     */
    public String getLibelleEntreprise() {
        return libelleEntreprise;
    }

    /**
     * Set the libelleEntreprise value.
     * @param libelleEntreprise the libelleEntreprise to set
     */
    public void setLibelleEntreprise(String libelleEntreprise) {
        this.libelleEntreprise = libelleEntreprise;
    }

    /**
     * Get the libelleGarantie value.
     * @return the libelleGarantie
     */
    public String getLibelleGarantie() {
        return libelleGarantie;
    }

    /**
     * Set the libelleGarantie value.
     * @param libelleGarantie the libelleGarantie to set
     */
    public void setLibelleGarantie(String libelleGarantie) {
        this.libelleGarantie = libelleGarantie;
    }

    /**
     * Get the libelleGestion value.
     * @return the libelleGestion
     */
    public String getLibelleGestion() {
        return libelleGestion;
    }

    /**
     * Set the libelleGestion value.
     * @param libelleGestion the libelleGestion to set
     */
    public void setLibelleGestion(String libelleGestion) {
        this.libelleGestion = libelleGestion;
    }

    /**
     * Get the libelleRole value.
     * @return the libelleRole
     */
    public String getLibelleRole() {
        return libelleRole;
    }

    /**
     * Set the libelleRole value.
     * @param libelleRole the libelleRole to set
     */
    public void setLibelleRole(String libelleRole) {
        this.libelleRole = libelleRole;
    }

    /**
     * Get the libelleZone value.
     * @return the libelleZone
     */
    public String getLibelleZone() {
        return libelleZone;
    }

    /**
     * Set the libelleZone value.
     * @param libelleZone the libelleZone to set
     */
    public void setLibelleZone(String libelleZone) {
        this.libelleZone = libelleZone;
    }

    /**
     * Get the montant value.
     * @return the montant
     */
    public Float getMontant() {
        return montant;
    }

    /**
     * Set the montant value.
     * @param montant the montant to set
     */
    public void setMontant(Float montant) {
        this.montant = montant;
    }

    /**
     * Get the libelleCompositionFamille value.
     * @return the libelleCompositionFamille
     */
    public String getLibelleCompositionFamille() {
        return libelleCompositionFamille;
    }

    /**
     * Set the libelleCompositionFamille value.
     * @param libelleCompositionFamille the libelleCompositionFamille to set
     */
    public void setLibelleCompositionFamille(String libelleCompositionFamille) {
        this.libelleCompositionFamille = libelleCompositionFamille;
    }

    /**
     * Get the affichageMontant value.
     * @return the affichageMontant
     */
    public String getAffichageMontant() {
        return affichageMontant;
    }

    /**
     * Set the affichageMontant value.
     * @param affichageMontant the affichageMontant to set
     */
    public void setAffichageMontant(String affichageMontant) {
        this.affichageMontant = affichageMontant;
    }

    /**
     * Get the codeNivgar value.
     * @return the codeNivgar
     */
    public String getCodeNivgar() {
        return codeNivgar;
    }

    /**
     * Set the codeNivgar value.
     * @param codeNivgar the codeNivgar to set
     */
    public void setCodeNivgar(String codeNivgar) {
        this.codeNivgar = codeNivgar;
    }

    /**
     * Get the libelleNivgar value.
     * @return the libelleNivgar
     */
    public String getLibelleNivgar() {
        return libelleNivgar;
    }

    /**
     * Set the libelleNivgar value.
     * @param libelleNivgar the libelleNivgar to set
     */
    public void setLibelleNivgar(String libelleNivgar) {
        this.libelleNivgar = libelleNivgar;
    }

    /**
     * Get the codeTauxCouverture value.
     * @return the codeTauxCouverture
     */
    public String getCodeTauxCouverture() {
        return codeTauxCouverture;
    }

    /**
     * Set the codeTauxCouverture value.
     * @param codeTauxCouverture the codeTauxCouverture to set
     */
    public void setCodeTauxCouverture(String codeTauxCouverture) {
        this.codeTauxCouverture = codeTauxCouverture;
    }

    /**
     * Get the libelleTauxCouverture value.
     * @return the libelleTauxCouverture
     */
    public String getLibelleTauxCouverture() {
        return libelleTauxCouverture;
    }

    /**
     * Set the libelleTauxCouverture value.
     * @param libelleTauxCouverture the libelleTauxCouverture to set
     */
    public void setLibelleTauxCouverture(String libelleTauxCouverture) {
        this.libelleTauxCouverture = libelleTauxCouverture;
    }

    /**
     * Get the codeGeneration value.
     * @return the codeGeneration
     */
    public String getCodeGeneration() {
        return codeGeneration;
    }

    /**
     * Set the codeGeneration value.
     * @param codeGeneration the codeGeneration to set
     */
    public void setCodeGeneration(String codeGeneration) {
        this.codeGeneration = codeGeneration;
    }

    /**
     * Get the libelleGeneration value.
     * @return the libelleGeneration
     */
    public String getLibelleGeneration() {
        return libelleGeneration;
    }

    /**
     * Set the libelleGeneration value.
     * @param libelleGeneration the libelleGeneration to set
     */
    public void setLibelleGeneration(String libelleGeneration) {
        this.libelleGeneration = libelleGeneration;
    }

    /**
     * Recuperer la valeur.
     * @return the codeMois
     */
    public String getCodeMois() {
        return codeMois;
    }

    /**
     * Fixer la valeur.
     * @param codeMois the codeMois to set
     */
    public void setCodeMois(String codeMois) {
        this.codeMois = codeMois;
    }

    /**
     * Recuperer la valeur.
     * @return the libelleMois
     */
    public String getLibelleMois() {
        return libelleMois;
    }

    /**
     * Fixer la valeur.
     * @param libelleMois the libelleMois to set
     */
    public void setLibelleMois(String libelleMois) {
        this.libelleMois = libelleMois;
    }

    /**
     * Get the value of montantBareme1.
     * @return the montantBareme1
     */
    public Float getMontantBareme1() {
        return montantBareme1;
    }

    /**
     * Set the value of montantBareme1.
     * @param montantBareme1 the montantBareme1 to set
     */
    public void setMontantBareme1(Float montantBareme1) {
        this.montantBareme1 = montantBareme1;
    }

    /**
     * Get the value of affichageMontantBareme1.
     * @return the affichageMontantBareme1
     */
    public String getAffichageMontantBareme1() {
        return affichageMontantBareme1;
    }

    /**
     * Set the value of affichageMontantBareme1.
     * @param affichageMontantBareme1 the affichageMontantBareme1 to set
     */
    public void setAffichageMontantBareme1(String affichageMontantBareme1) {
        this.affichageMontantBareme1 = affichageMontantBareme1;
    }

    /**
     * Get the value of montantBareme2.
     * @return the montantBareme2
     */
    public Float getMontantBareme2() {
        return montantBareme2;
    }

    /**
     * Set the value of montantBareme2.
     * @param montantBareme2 the montantBareme2 to set
     */
    public void setMontantBareme2(Float montantBareme2) {
        this.montantBareme2 = montantBareme2;
    }

    /**
     * Get the value of affichageMontantBareme2.
     * @return the affichageMontantBareme2
     */
    public String getAffichageMontantBareme2() {
        return affichageMontantBareme2;
    }

    /**
     * Set the value of affichageMontantBareme2.
     * @param affichageMontantBareme2 the affichageMontantBareme2 to set
     */
    public void setAffichageMontantBareme2(String affichageMontantBareme2) {
        this.affichageMontantBareme2 = affichageMontantBareme2;
    }
}

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
package com.square.adherent.noyau.dto.adherent.contrat;

import java.io.Serializable;
import java.util.Calendar;

import com.square.adherent.noyau.dto.IdentifiantEidLibelleDto;
import com.square.adherent.noyau.dto.IdentifiantEidLibelleOrdreDto;

/**
 * Dto de garantie bareme.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class GarantieBaremeDto implements Serializable {

    private static final long serialVersionUID = -2483904391813196547L;

    private Long id;

    /** Type payeur du bareme. */
    private IdentifiantEidLibelleDto typePayeur;

    /** Tarif type du bareme. */
    private IdentifiantEidLibelleDto tarifType;

    /** Eid bareme 1. */
    private String bareme1Eid;

    /** Code zone bareme 1. */
    private Integer codeZoneBareme1;

    /** Eid bareme 2. */
    private String bareme2Eid;

    /** Code zone bareme 2. */
    private Integer codeZoneBareme2;

    private IdentifiantEidLibelleOrdreDto famille;

    private IdentifiantEidLibelleOrdreDto role;

    private String garantieGestion;

    private String produitGestion;

    private Long uidBeneficiaire;

    /** Code composition familial. */
    private String codeCompositionFamille;

    /** Code entreprise. */
    private String codeEntreprise;

    /** Code capital. */
    private Double codeNivgar;

    /** Code garantie. */
    private String codeGarantie;

    /** Code génération. */
    private String codeGeneration;

    /** Code mois. */
    private String codeMois;

    /** Code role. */
    private String codeRole;

    /** Code Gestion. */
    private String codeGestion;

    /** Code apporteur. */
    private String codeApporteur;

    /** Code age minimum. */
    private Integer codeAgeMin;

    /** Code age minimum. */
    private Integer codeAgeMinMillesime;

    private Calendar dateAdhesion;

    private Calendar dateResiliation;

    /**
     * Get the value of id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of bareme1Eid.
     * @return the bareme1Eid
     */
    public String getBareme1Eid() {
        return bareme1Eid;
    }

    /**
     * Set the value of bareme1Eid.
     * @param bareme1Eid the bareme1Eid to set
     */
    public void setBareme1Eid(String bareme1Eid) {
        this.bareme1Eid = bareme1Eid;
    }

    /**
     * Get the value of bareme2Eid.
     * @return the bareme2Eid
     */
    public String getBareme2Eid() {
        return bareme2Eid;
    }

    /**
     * Set the value of bareme2Eid.
     * @param bareme2Eid the bareme2Eid to set
     */
    public void setBareme2Eid(String bareme2Eid) {
        this.bareme2Eid = bareme2Eid;
    }

    /**
     * Get the value of typePayeur.
     * @return the typePayeur
     */
    public IdentifiantEidLibelleDto getTypePayeur() {
        return typePayeur;
    }

    /**
     * Set the value of typePayeur.
     * @param typePayeur the typePayeur to set
     */
    public void setTypePayeur(IdentifiantEidLibelleDto typePayeur) {
        this.typePayeur = typePayeur;
    }

    /**
     * Get the value of tarifType.
     * @return the tarifType
     */
    public IdentifiantEidLibelleDto getTarifType() {
        return tarifType;
    }

    /**
     * Set the value of tarifType.
     * @param tarifType the tarifType to set
     */
    public void setTarifType(IdentifiantEidLibelleDto tarifType) {
        this.tarifType = tarifType;
    }

    /**
     * Get the value of garantieGestion.
     * @return the garantieGestion
     */
    public String getGarantieGestion() {
        return garantieGestion;
    }

    /**
     * Set the value of garantieGestion.
     * @param garantieGestion the garantieGestion to set
     */
    public void setGarantieGestion(String garantieGestion) {
        this.garantieGestion = garantieGestion;
    }

    /**
     * Get the value of produitGestion.
     * @return the produitGestion
     */
    public String getProduitGestion() {
        return produitGestion;
    }

    /**
     * Set the value of produitGestion.
     * @param produitGestion the produitGestion to set
     */
    public void setProduitGestion(String produitGestion) {
        this.produitGestion = produitGestion;
    }

    /**
     * Get the value of uidBeneficiaire.
     * @return the uidBeneficiaire
     */
    public Long getUidBeneficiaire() {
        return uidBeneficiaire;
    }

    /**
     * Set the value of uidBeneficiaire.
     * @param uidBeneficiaire the uidBeneficiaire to set
     */
    public void setUidBeneficiaire(Long uidBeneficiaire) {
        this.uidBeneficiaire = uidBeneficiaire;
    }

    /**
     * Get the value of codeZoneBareme1.
     * @return the codeZoneBareme1
     */
    public Integer getCodeZoneBareme1() {
        return codeZoneBareme1;
    }

    /**
     * Set the value of codeZoneBareme1.
     * @param codeZoneBareme1 the codeZoneBareme1 to set
     */
    public void setCodeZoneBareme1(Integer codeZoneBareme1) {
        this.codeZoneBareme1 = codeZoneBareme1;
    }

    /**
     * Get the value of codeZoneBareme2.
     * @return the codeZoneBareme2
     */
    public Integer getCodeZoneBareme2() {
        return codeZoneBareme2;
    }

    /**
     * Set the value of codeZoneBareme2.
     * @param codeZoneBareme2 the codeZoneBareme2 to set
     */
    public void setCodeZoneBareme2(Integer codeZoneBareme2) {
        this.codeZoneBareme2 = codeZoneBareme2;
    }

    /**
     * Get the value of codeCompositionFamille.
     * @return the codeCompositionFamille
     */
    public String getCodeCompositionFamille() {
        return codeCompositionFamille;
    }

    /**
     * Set the value of codeCompositionFamille.
     * @param codeCompositionFamille the codeCompositionFamille to set
     */
    public void setCodeCompositionFamille(String codeCompositionFamille) {
        this.codeCompositionFamille = codeCompositionFamille;
    }

    /**
     * Get the value of codeEntreprise.
     * @return the codeEntreprise
     */
    public String getCodeEntreprise() {
        return codeEntreprise;
    }

    /**
     * Set the value of codeEntreprise.
     * @param codeEntreprise the codeEntreprise to set
     */
    public void setCodeEntreprise(String codeEntreprise) {
        this.codeEntreprise = codeEntreprise;
    }

    /**
     * Get the value of codeNivgar.
     * @return the codeNivgar
     */
    public Double getCodeNivgar() {
        return codeNivgar;
    }

    /**
     * Set the value of codeNivgar.
     * @param codeNivgar the codeNivgar to set
     */
    public void setCodeNivgar(Double codeNivgar) {
        this.codeNivgar = codeNivgar;
    }

    /**
     * Get the value of codeGarantie.
     * @return the codeGarantie
     */
    public String getCodeGarantie() {
        return codeGarantie;
    }

    /**
     * Set the value of codeGarantie.
     * @param codeGarantie the codeGarantie to set
     */
    public void setCodeGarantie(String codeGarantie) {
        this.codeGarantie = codeGarantie;
    }

    /**
     * Get the value of codeGeneration.
     * @return the codeGeneration
     */
    public String getCodeGeneration() {
        return codeGeneration;
    }

    /**
     * Set the value of codeGeneration.
     * @param codeGeneration the codeGeneration to set
     */
    public void setCodeGeneration(String codeGeneration) {
        this.codeGeneration = codeGeneration;
    }

    /**
     * Get the value of codeMois.
     * @return the codeMois
     */
    public String getCodeMois() {
        return codeMois;
    }

    /**
     * Set the value of codeMois.
     * @param codeMois the codeMois to set
     */
    public void setCodeMois(String codeMois) {
        this.codeMois = codeMois;
    }

    /**
     * Get the value of codeRole.
     * @return the codeRole
     */
    public String getCodeRole() {
        return codeRole;
    }

    /**
     * Set the value of codeRole.
     * @param codeRole the codeRole to set
     */
    public void setCodeRole(String codeRole) {
        this.codeRole = codeRole;
    }

    /**
     * Get the value of codeGestion.
     * @return the codeGestion
     */
    public String getCodeGestion() {
        return codeGestion;
    }

    /**
     * Set the value of codeGestion.
     * @param codeGestion the codeGestion to set
     */
    public void setCodeGestion(String codeGestion) {
        this.codeGestion = codeGestion;
    }

    /**
     * Get the value of codeApporteur.
     * @return the codeApporteur
     */
    public String getCodeApporteur() {
        return codeApporteur;
    }

    /**
     * Set the value of codeApporteur.
     * @param codeApporteur the codeApporteur to set
     */
    public void setCodeApporteur(String codeApporteur) {
        this.codeApporteur = codeApporteur;
    }

    /**
     * Get the value of codeAgeMin.
     * @return the codeAgeMin
     */
    public Integer getCodeAgeMin() {
        return codeAgeMin;
    }

    /**
     * Set the value of codeAgeMin.
     * @param codeAgeMin the codeAgeMin to set
     */
    public void setCodeAgeMin(Integer codeAgeMin) {
        this.codeAgeMin = codeAgeMin;
    }

    /**
     * Get the value of codeAgeMinMillesime.
     * @return the codeAgeMinMillesime
     */
    public Integer getCodeAgeMinMillesime() {
        return codeAgeMinMillesime;
    }

    /**
     * Set the value of codeAgeMinMillesime.
     * @param codeAgeMinMillesime the codeAgeMinMillesime to set
     */
    public void setCodeAgeMinMillesime(Integer codeAgeMinMillesime) {
        this.codeAgeMinMillesime = codeAgeMinMillesime;
    }

    /**
     * Récupère la valeur de famille.
     * @return la valeur de famille
     */
    public IdentifiantEidLibelleOrdreDto getFamille() {
        return famille;
    }

    /**
     * Définit la valeur de famille.
     * @param famille la nouvelle valeur de famille
     */
    public void setFamille(IdentifiantEidLibelleOrdreDto famille) {
        this.famille = famille;
    }

    /**
     * Récupère la valeur de role.
     * @return la valeur de role
     */
    public IdentifiantEidLibelleOrdreDto getRole() {
        return role;
    }

    /**
     * Définit la valeur de role.
     * @param role la nouvelle valeur de role
     */
    public void setRole(IdentifiantEidLibelleOrdreDto role) {
        this.role = role;
    }

    /**
     * Get the value of dateAdhesion.
     * @return the dateAdhesion
     */
    public Calendar getDateAdhesion() {
        return dateAdhesion;
    }

    /**
     * Set the value of dateAdhesion.
     * @param dateAdhesion the dateAdhesion to set
     */
    public void setDateAdhesion(Calendar dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }

    /**
     * Get the value of dateResiliation.
     * @return the dateResiliation
     */
    public Calendar getDateResiliation() {
        return dateResiliation;
    }

    /**
     * Set the value of dateResiliation.
     * @param dateResiliation the dateResiliation to set
     */
    public void setDateResiliation(Calendar dateResiliation) {
        this.dateResiliation = dateResiliation;
    }

}

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
package com.square.composant.cotisations.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model qui représente un détail de cotisation.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class DetailCotisationModel implements IsSerializable {

    /** Contrat. */
    private String contrat;

    /** Garantie. */
    private String garantie;

    /** Eid de la garantie. */
    private String eidGarantie;

    /** Libellé. */
    private String libelle;

    /** Montant. */
    private Float montant;

    /** Bénéficiaire. */
    private PersonneCotisationModel beneficiaire;

    /** Type de la prime. */
    private String typePrime;

    /** Type de l'échéance. */
    private String typeEcheance;

    /** Date de début. */
    private String dateDebut;

    /** Date de fin. */
    private String dateFin;

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
     * Get the beneficiaire value.
     * @return the beneficiaire
     */
    public PersonneCotisationModel getBeneficiaire() {
        return beneficiaire;
    }

    /**
     * Set the beneficiaire value.
     * @param beneficiaire the beneficiaire to set
     */
    public void setBeneficiaire(PersonneCotisationModel beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    /**
     * Get the typePrime value.
     * @return the typePrime
     */
    public String getTypePrime() {
        return typePrime;
    }

    /**
     * Set the typePrime value.
     * @param typePrime the typePrime to set
     */
    public void setTypePrime(String typePrime) {
        this.typePrime = typePrime;
    }

    /**
     * Get the typeEcheance value.
     * @return the typeEcheance
     */
    public String getTypeEcheance() {
        return typeEcheance;
    }

    /**
     * Set the typeEcheance value.
     * @param typeEcheance the typeEcheance to set
     */
    public void setTypeEcheance(String typeEcheance) {
        this.typeEcheance = typeEcheance;
    }

    /**
     * Get the contrat value.
     * @return the contrat
     */
    public String getContrat() {
        return contrat;
    }

    /**
     * Set the contrat value.
     * @param contrat the contrat to set
     */
    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    /**
     * Get the dateDebut value.
     * @return the dateDebut
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Set the dateDebut value.
     * @param dateDebut the dateDebut to set
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Get the dateFin value.
     * @return the dateFin
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * Set the dateFin value.
     * @param dateFin the dateFin to set
     */
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
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
     * Set the garantie value.
     * @param garantie the garantie to set
     */
    public void setGarantie(String garantie) {
        this.garantie = garantie;
    }

    /**
     * Get the garantie value.
     * @return the garantie
     */
    public String getGarantie() {
        return garantie;
    }

    /**
     * Get the eidGarantie value.
     * @return the eidGarantie
     */
    public String getEidGarantie() {
        return eidGarantie;
    }

    /**
     * Set the eidGarantie value.
     * @param eidGarantie the eidGarantie to set
     */
    public void setEidGarantie(String eidGarantie) {
        this.eidGarantie = eidGarantie;
    }
}

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
package com.square.composant.tarificateur.square.client.model.personne;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model Gwt modélisant une adresse postale.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class AdresseModel implements IsSerializable {

    /** Identifiant de l'adresse. */
    private Long id;

    /** EID de l'adresse (Square). */
    private Long eidAdresse;

    /** Numéro de voie de l'adresse. */
    private String numeroVoie;

    /** Complément de nom de l'adresse. */
    private String complementNom;

    /** Nom de voie de l'adresse. */
    private String nomVoie;

    /** Nom de résidence/Batiment de l'adresse. */
    private String residenceBatiment;

    /** Boite postale de l'adresse. */
    private String boitePostal;

    /** Eid type de voie (Square). */
    private Long eidTypeVoie;

    /** Indique si la personne habite à l'adresse indiquée. */
    private boolean topNpai;

    /** EID du code postal - comune (Square). */
    private Long eidCodePostalCommune;

    /** EID du pays. */
    private Long eidPays;

    /** EID du departement. */
    private Long eidDepartement;

    /**
     * Getter function.
     * @return the eidAdresse
     */
    public Long getEidAdresse() {
        return eidAdresse;
    }

    /**
     * Getter function.
     * @return the numeroVoie
     */
    public String getNumeroVoie() {
        return numeroVoie;
    }

    /**
     * Getter function.
     * @return the complementNom
     */
    public String getComplementNom() {
        return complementNom;
    }

    /**
     * Getter function.
     * @return the nomVoie
     */
    public String getNomVoie() {
        return nomVoie;
    }

    /**
     * Getter function.
     * @return the residenceBatiment
     */
    public String getResidenceBatiment() {
        return residenceBatiment;
    }

    /**
     * Getter function.
     * @return the boitePostal
     */
    public String getBoitePostal() {
        return boitePostal;
    }

    /**
     * Getter function.
     * @return the topNpai
     */
    public boolean isTopNpai() {
        return topNpai;
    }

    /**
     * Getter function.
     * @return the eidPays
     */
    public Long getEidPays() {
        return eidPays;
    }

    /**
     * Setter function.
     * @param eidAdresse the eidAdresse to set
     */
    public void setEidAdresse(Long eidAdresse) {
        this.eidAdresse = eidAdresse;
    }

    /**
     * Setter function.
     * @param numeroVoie the numeroVoie to set
     */
    public void setNumeroVoie(String numeroVoie) {
        this.numeroVoie = numeroVoie;
    }

    /**
     * Setter function.
     * @param complementNom the complementNom to set
     */
    public void setComplementNom(String complementNom) {
        this.complementNom = complementNom;
    }

    /**
     * Setter function.
     * @param nomVoie the nomVoie to set
     */
    public void setNomVoie(String nomVoie) {
        this.nomVoie = nomVoie;
    }

    /**
     * Setter function.
     * @param residenceBatiment the residenceBatiment to set
     */
    public void setResidenceBatiment(String residenceBatiment) {
        this.residenceBatiment = residenceBatiment;
    }

    /**
     * Setter function.
     * @param boitePostal the boitePostal to set
     */
    public void setBoitePostal(String boitePostal) {
        this.boitePostal = boitePostal;
    }

    /**
     * Setter function.
     * @param topNpai the topNpai to set
     */
    public void setTopNpai(boolean topNpai) {
        this.topNpai = topNpai;
    }

    /**
     * Setter function.
     * @param eidPays the eidPays to set
     */
    public void setEidPays(Long eidPays) {
        this.eidPays = eidPays;
    }

    /**
     * Getter function.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter function.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère la valeur de eidTypeVoie.
     * @return la valeur de eidTypeVoie
     */
    public Long getEidTypeVoie() {
        return eidTypeVoie;
    }

    /**
     * Définit la valeur de eidTypeVoie.
     * @param eidTypeVoie la nouvelle valeur de eidTypeVoie
     */
    public void setEidTypeVoie(Long eidTypeVoie) {
        this.eidTypeVoie = eidTypeVoie;
    }

    /**
	 * Récupère la valeur eidCodePostalCommune.
	 * @return the eidCodePostalCommune
	 */
	public Long getEidCodePostalCommune() {
		return eidCodePostalCommune;
	}

	/**
	 * Définit la valeur de eidCodePostalCommune.
	 * @param eidCodePostalCommune the eidCodePostalCommune to set
	 */
	public void setEidCodePostalCommune(Long eidCodePostalCommune) {
		this.eidCodePostalCommune = eidCodePostalCommune;
	}

    /**
     * Get the eidDepartement value.
     * @return the eidDepartement
     */
    public Long getEidDepartement() {
        return eidDepartement;
    }

    /**
     * Set the eidDepartement value.
     * @param eidDepartement the eidDepartement to set
     */
    public void setEidDepartement(Long eidDepartement) {
        this.eidDepartement = eidDepartement;
    }
}

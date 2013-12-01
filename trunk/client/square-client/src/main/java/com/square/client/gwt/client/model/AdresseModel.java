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
package com.square.client.gwt.client.model;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Un objet contenant l'adresse d'une personne.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class AdresseModel implements IsSerializable {

    /** Identifiant de l'adresse. */
    private Long identifiant;

    /** Identifiant externe de l'adresse. */
    private String idext;

    /** Date de début. */
    private String dateDebut;

    /** Date de fin. */
    private String dateFin;

    /** Numéro de voie. */
    private String numVoie;

    /** Nom de la voie. */
    private String nomVoie;

    /** Nature de voie. */
    private IdentifiantLibelleGwt typeVoie;

    /** Complément nom. */
    private String complementNom;

    /** Complément adresse. */
    private String complementAdresse;

    /** Indique si la personne n'habite pas à l'adresse indiquée. */
    private Boolean npai;

    /** Boite postale. */
    private String boitePostal;

    /** Autres compléments. */
    private String autresComplements;

    /** Code postal - Commune. */
    private CodePostalCommuneModel codePostalCommune;

    /** Departement. */
    private IdentifiantLibelleGwt departement;

    /** Pays. */
    private IdentifiantLibelleBooleanModel pays;

    /** Nature de l'adresse. */
    private IdentifiantLibelleGwt nature;

    /** Commune si adresse étrangère. */
    private String communeEtranger;

    /** Code postal si adresse étrangère. */
    private String codePostalEtranger;

    /** Choix de passer l'adresse principle actuelle. */
    private Boolean choixPasserEnSecondaire;

    /** Identifiant du porteur de l'adresse. */
    private Long porteurUid;

    /**
     * Renvoie le choix de passage de l'adresse principale actuelle.
     * @return choixPasserEnSecondaire
     */
    public Boolean getChoixPasserEnSecondaire() {
     return choixPasserEnSecondaire;
    }

    /**
     * Modifie le choix de passage de l'adresse principale actuelle.
     * @param choixPasserEnSecondaire flag pour passer l'adresse en secondaire
     */
    public void setChoixPasserEnSecondaire(Boolean choixPasserEnSecondaire) {
     this.choixPasserEnSecondaire = choixPasserEnSecondaire;
    }

    /**
     * Renvoi la valeur de identifiant.
     * @return identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Modifie identifiant.
     * @param identifiant la nouvelle valeur de identifiant
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Renvoi la valeur de dateDebut.
     * @return dateDebut
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Modifie dateDebut.
     * @param dateDebut la nouvelle valeur de dateDebut
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Renvoi la valeur de dateFin.
     * @return dateFin
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * Modifie dateFin.
     * @param dateFin la nouvelle valeur de dateFin
     */
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Renvoi la valeur de numVoie.
     * @return numVoie
     */
    public String getNumVoie() {
        return numVoie;
    }

    /**
     * Modifie numVoie.
     * @param numVoie la nouvelle valeur de numVoie
     */
    public void setNumVoie(String numVoie) {
        this.numVoie = numVoie;
    }

    /**
     * Renvoi la valeur de nomVoie.
     * @return nomVoie
     */
    public String getNomVoie() {
        return nomVoie;
    }

    /**
     * Modifie nomVoie.
     * @param nomVoie la nouvelle valeur de nomVoie
     */
    public void setNomVoie(String nomVoie) {
        this.nomVoie = nomVoie;
    }

    /**
     * Renvoi la valeur de complementNom.
     * @return complementNom
     */
    public String getComplementNom() {
        return complementNom;
    }

    /**
     * Modifie complementNom.
     * @param complementNom la nouvelle valeur de complementNom
     */
    public void setComplementNom(String complementNom) {
        this.complementNom = complementNom;
    }

    /**
     * Renvoi la valeur de complementAdresse.
     * @return complementAdresse
     */
    public String getComplementAdresse() {
        return complementAdresse;
    }

    /**
     * Modifie complementAdresse.
     * @param complementAdresse la nouvelle valeur de complementAdresse
     */
    public void setComplementAdresse(String complementAdresse) {
        this.complementAdresse = complementAdresse;
    }

    /**
     * Renvoi la valeur de boitePostal.
     * @return boitePostal
     */
    public String getBoitePostal() {
        return boitePostal;
    }

    /**
     * Modifie boitePostal.
     * @param boitePostal la nouvelle valeur de boitePostal
     */
    public void setBoitePostal(String boitePostal) {
        this.boitePostal = boitePostal;
    }

    /**
     * Renvoi la valeur de autresComplements.
     * @return autresComplements
     */
    public String getAutresComplements() {
        return autresComplements;
    }

    /**
     * Modifie autresComplements.
     * @param autresComplements la nouvelle valeur de autresComplements
     */
    public void setAutresComplements(String autresComplements) {
        this.autresComplements = autresComplements;
    }

    /**
     * Renvoi la valeur de typeVoie.
     * @return typeVoie
     */
    public IdentifiantLibelleGwt getTypeVoie() {
        return typeVoie;
    }

    /**
     * Modifie typeVoie.
     * @param typeVoie la nouvelle valeur de typeVoie
     */
    public void setTypeVoie(IdentifiantLibelleGwt typeVoie) {
        this.typeVoie = typeVoie;
    }

    /**
     * Renvoi la valeur de departement.
     * @return departement
     */
    public IdentifiantLibelleGwt getDepartement() {
        return departement;
    }

    /**
     * Modifie departement.
     * @param departement la nouvelle valeur de departement
     */
    public void setDepartement(IdentifiantLibelleGwt departement) {
        this.departement = departement;
    }

    /**
     * Renvoi la valeur de pays.
     * @return pays
     */
    public IdentifiantLibelleBooleanModel getPays() {
        return pays;
    }

    /**
     * Modifie pays.
     * @param pays la nouvelle valeur de pays
     */
    public void setPays(IdentifiantLibelleBooleanModel pays) {
        this.pays = pays;
    }

    /**
     * Renvoi la valeur de nature.
     * @return nature
     */
    public IdentifiantLibelleGwt getNature() {
        return nature;
    }

    /**
     * Modifie nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(IdentifiantLibelleGwt nature) {
        this.nature = nature;
    }

    /**
     * Get the npai value.
     * @return the npai
     */
    public Boolean getNpai() {
        return npai;
    }

    /**
     * Set the npai value.
     * @param npai the npai to set
     */
    public void setNpai(Boolean npai) {
        this.npai = npai;
    }

    /**
     * Récupération du nom de la commune a l'étranger.
     * @return the communeEtranger
     */
    public String getCommuneEtranger() {
        return communeEtranger;
    }

    /**
     * Définition du nom de la commune a l'étranger.
     * @param communeEtranger the communeEtranger to set
     */
    public void setCommuneEtranger(String communeEtranger) {
        this.communeEtranger = communeEtranger;
    }

    /**
     * Récupération du code postal a l'étranger.
     * @return the codePostalEtranger
     */
    public String getCodePostalEtranger() {
        return codePostalEtranger;
    }

    /**
     * Définition du code postal a l'étranger.
     * @param codePostalEtranger the codePostalEtranger to set
     */
    public void setCodePostalEtranger(String codePostalEtranger) {
        this.codePostalEtranger = codePostalEtranger;
    }

	/**
	 * Récupère la valeur codePostalCommune.
	 * @return the codePostalCommune
	 */
	public CodePostalCommuneModel getCodePostalCommune() {
		return codePostalCommune;
	}

	/**
	 * Définit la valeur de codePostalCommune.
	 * @param codePostalCommune the codePostalCommune to set
	 */
	public void setCodePostalCommune(CodePostalCommuneModel codePostalCommune) {
		this.codePostalCommune = codePostalCommune;
	}

    /**
     * Get the value of idext.
     * @return the idext
     */
    public String getIdext() {
        return idext;
    }

    /**
     * Set the value of idext.
     * @param idext the idext to set
     */
    public void setIdext(String idext) {
        this.idext = idext;
    }

    /**
     * Get the value of porteurUid.
     * @return the porteurUid
     */
    public Long getPorteurUid() {
        return porteurUid;
    }

    /**
     * Set the value of porteurUid.
     * @param porteurUid the porteurUid to set
     */
    public void setPorteurUid(Long porteurUid) {
        this.porteurUid = porteurUid;
    }

}

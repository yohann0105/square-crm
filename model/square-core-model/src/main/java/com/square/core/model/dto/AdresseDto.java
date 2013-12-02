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
package com.square.core.model.dto;

import java.io.Serializable;
import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Un objet contenant l'adresse d'une personne.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class AdresseDto implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -1248807543127013433L;

    /**
     * Identifiant de l'adresse.
     */
    private Long identifiant;

    /**
     * Identifiant externe de l'adresse.
     */
    private String idext;

    /**
     * Date de début.
     */
    private Calendar dateDebut;

    /**
     * Date de fin.
     */
    private Calendar dateFin;

    /**
     * Numéro de voie.
     */
    private String numVoie;

    /**
     * Nom de la voie.
     */
    private String nomVoie;

    /**
     * Nature de voie.
     */
    private IdentifiantLibelleDto typeVoie;

    /**
     * Complément nom.
     */
    private String complementNom;

    /**
     * Complément adresse.
     */
    private String complementAdresse;

    /**
     * Indique si la personne n'habite pas à l'adresse indiquée.
     */
    // TODO Passer en Boolean
    private boolean npai;

    /**
     * Boite postale.
     */
    private String boitePostal;

    /**
     * Autres compléments.
     */
    private String autresComplements;

    /**
     * Code postal - Commune.
     */
    private CodePostalCommuneDto codePostalCommune;

    /**
     * Departement.
     */
    private IdentifiantLibelleDto departement;

    /**
     * Pays.
     */
    private IdentifiantLibelleBooleanDto pays;

    /**
     * Nature de l'adresse.
     */
    private IdentifiantLibelleDto nature;

    /**
     * Commune si adresse étrangère.
     */
    private String communeEtranger;

    /**
     * Code postal si adresse étrangère.
     */
    private String codePostalEtranger;

    /** Identifiant du porteur de l'adresse. */
    private Long porteurUid;

    /**
     * Renvoi la valeur de identifiant.
     * @return identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /** Choix de passer l'adresse principle actuelle. */
    private Boolean choixPasserEnSecondaire;

    /**
     * Modifie identifiant.
     * @param identifiant la nouvelle valeur de identifiant
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Renvoi la valeur de idext.
     * @return idext
     */
    public String getIdext() {
        return idext;
    }

    /**
     * Modifie idext.
     * @param idext la nouvelle valeur de idext
     */
    public void setIdext(String idext) {
        this.idext = idext;
    }

    /**
     * Renvoi la valeur de dateDebut.
     * @return dateDebut
     */
    public Calendar getDateDebut() {
        return dateDebut;
    }

    /**
     * Modifie dateDebut.
     * @param dateDebut la nouvelle valeur de dateDebut
     */
    public void setDateDebut(Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Renvoi la valeur de dateFin.
     * @return dateFin
     */
    public Calendar getDateFin() {
        return dateFin;
    }

    /**
     * Modifie dateFin.
     * @param dateFin la nouvelle valeur de dateFin
     */
    public void setDateFin(Calendar dateFin) {
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
     * Renvoi la valeur de npai.
     * @return npai
     */
    public boolean isNpai() {
        return npai;
    }

    /**
     * Modifie npai.
     * @param npai la nouvelle valeur de npai
     */
    public void setNpai(boolean npai) {
        this.npai = npai;
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
    public IdentifiantLibelleDto getTypeVoie() {
        return typeVoie;
    }

    /**
     * Modifie typeVoie.
     * @param typeVoie la nouvelle valeur de typeVoie
     */
    public void setTypeVoie(IdentifiantLibelleDto typeVoie) {
        this.typeVoie = typeVoie;
    }

    /**
     * Renvoi la valeur de departement.
     * @return departement
     */
    public IdentifiantLibelleDto getDepartement() {
        return departement;
    }

    /**
     * Modifie departement.
     * @param departement la nouvelle valeur de departement
     */
    public void setDepartement(IdentifiantLibelleDto departement) {
        this.departement = departement;
    }

    /**
     * Renvoi la valeur de pays.
     * @return pays
     */
    public IdentifiantLibelleBooleanDto getPays() {
        return pays;
    }

    /**
     * Modifie pays.
     * @param pays la nouvelle valeur de pays
     */
    public void setPays(IdentifiantLibelleBooleanDto pays) {
        this.pays = pays;
    }

    /**
     * Renvoi la valeur de nature.
     * @return nature
     */
    public IdentifiantLibelleDto getNature() {
        return nature;
    }

    /**
     * Modifie nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(IdentifiantLibelleDto nature) {
        this.nature = nature;
    }

    /**
     * Renvoi la commune Etranger.
     * @return the communeEtranger
     */
    public String getCommuneEtranger() {
        return communeEtranger;
    }

    /**
     * Modifie la commune Etranger.
     * @param communeEtranger the communeEtranger to set
     */
    public void setCommuneEtranger(String communeEtranger) {
        this.communeEtranger = communeEtranger;
    }

    /**
     * Renvoi le code postal Etranger.
     * @return the codePostalEtranger
     */
    public String getCodePostalEtranger() {
        return codePostalEtranger;
    }

    /**
     * Modifie le code postal étranger.
     * @param codePostalEtranger the codePostalEtranger to set
     */
    public void setCodePostalEtranger(String codePostalEtranger) {
        this.codePostalEtranger = codePostalEtranger;
    }

    /**
     * Renvoie le choix de passage de l'adresse principale actuelle.
     * @return choixPasserEnSecondaire
     */
    public Boolean getChoixPasserEnSecondaire() {
        return choixPasserEnSecondaire;
    }

    /**
     * Modifie le choix de passage de l'adresse principale actuelle.
     * @param choixPasserEnSecondaire indicateur de passage
     */
    public void setChoixPasserEnSecondaire(Boolean choixPasserEnSecondaire) {
        this.choixPasserEnSecondaire = choixPasserEnSecondaire;
    }

    /**
     * Récupère la valeur codePostalCommune.
     * @return the codePostalCommune
     */
    public CodePostalCommuneDto getCodePostalCommune() {
        return codePostalCommune;
    }

    /**
     * Définit la valeur de codePostalCommune.
     * @param codePostalCommune the codePostalCommune to set
     */
    public void setCodePostalCommune(CodePostalCommuneDto codePostalCommune) {
        this.codePostalCommune = codePostalCommune;
    }

    /**
     * Récupère la valeur de porteurUid.
     * @return la valeur de porteurUid
     */
    public Long getPorteurUid() {
        return porteurUid;
    }

    /**
     * Définit la valeur de porteurUid.
     * @param porteurUid la nouvelle valeur de porteurUid
     */
    public void setPorteurUid(Long porteurUid) {
        this.porteurUid = porteurUid;
    }
}

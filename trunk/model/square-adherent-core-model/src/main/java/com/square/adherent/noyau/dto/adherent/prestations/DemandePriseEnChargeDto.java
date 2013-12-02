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
package com.square.adherent.noyau.dto.adherent.prestations;

import java.io.Serializable;
import java.util.Calendar;

/**
 * DTO contenant les informations nécéssaires à une demande de prise en charge de la part d'un adhérent.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class DemandePriseEnChargeDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -2975893899581471834L;

    /** Identifiant de l'adhérent à l'origine de la demande de prise en charge. */
    private Long idAdherent;

    /** Identifiant du bénéficiaire à prendre en charge. */
    private Long idPersonneAPrendreEnCharge;

    /** Nom de l'établissement. */
    private String nomEtablissement;

    /** Numéro de la voie. */
    private String numeroVoieEtablissement;

    /** Complément du nom. */
    private String complementNomEtablissement;

    /** Nature de la voie. */
    private String natureVoieEtablissement;

    /** Clé de la nature de la voie. */
    private String cleNatureVoieEtablissement;

    /** Adresse. */
    private String adresseEtablissement;

    /** Complément de l'adresse. */
    private String complementAdresseEtablissement;

    /** Autre complément. */
    private String autreComplementEtablissement;

    /** Code postal. */
    private String codePostalEtablissement;

    /** Ville. */
    private String villeEtablissement;

    /** Numéro de fax de l'établissement. */
    private String numeroFaxEtablissement;

    /** Date d'entrée. */
    private Calendar dateEntree;

    /** Nature des soins. */
    private String natureSoins;

    /** Code discipline. */
    private String codeDiscipline;

    /** Identifiant de la ressource Square qui créé la demande de prise en charge. */
    private Long idCreateur;

    /**
     * Getter function.
     * @return the idAdherent
     */
    public Long getIdAdherent() {
        return idAdherent;
    }

    /**
     * Getter function.
     * @return the idPersonneAPrendreEnCharge
     */
    public Long getIdPersonneAPrendreEnCharge() {
        return idPersonneAPrendreEnCharge;
    }

    /**
     * Getter function.
     * @return the nomEtablissement
     */
    public String getNomEtablissement() {
        return nomEtablissement;
    }

    /**
     * Getter function.
     * @return the numeroVoieEtablissement
     */
    public String getNumeroVoieEtablissement() {
        return numeroVoieEtablissement;
    }

    /**
     * Getter function.
     * @return the complementNomEtablissement
     */
    public String getComplementNomEtablissement() {
        return complementNomEtablissement;
    }

    /**
     * Getter function.
     * @return the natureVoieEtablissement
     */
    public String getNatureVoieEtablissement() {
        return natureVoieEtablissement;
    }

    /**
     * Getter function.
     * @return the cleNatureVoieEtablissement
     */
    public String getCleNatureVoieEtablissement() {
        return cleNatureVoieEtablissement;
    }

    /**
     * Getter function.
     * @return the adresseEtablissement
     */
    public String getAdresseEtablissement() {
        return adresseEtablissement;
    }

    /**
     * Getter function.
     * @return the complementAdresseEtablissement
     */
    public String getComplementAdresseEtablissement() {
        return complementAdresseEtablissement;
    }

    /**
     * Getter function.
     * @return the autreComplementEtablissement
     */
    public String getAutreComplementEtablissement() {
        return autreComplementEtablissement;
    }

    /**
     * Getter function.
     * @return the codePostalEtablissement
     */
    public String getCodePostalEtablissement() {
        return codePostalEtablissement;
    }

    /**
     * Getter function.
     * @return the villeEtablissement
     */
    public String getVilleEtablissement() {
        return villeEtablissement;
    }

    /**
     * Getter function.
     * @return the numeroFaxEtablissement
     */
    public String getNumeroFaxEtablissement() {
        return numeroFaxEtablissement;
    }

    /**
     * Getter function.
     * @return the dateEntree
     */
    public Calendar getDateEntree() {
        return dateEntree;
    }

    /**
     * Getter function.
     * @return the natureSoins
     */
    public String getNatureSoins() {
        return natureSoins;
    }

    /**
     * Getter function.
     * @return the codeDiscipline
     */
    public String getCodeDiscipline() {
        return codeDiscipline;
    }

    /**
     * Setter function.
     * @param idAdherent the idAdherent to set
     */
    public void setIdAdherent(Long idAdherent) {
        this.idAdherent = idAdherent;
    }

    /**
     * Setter function.
     * @param idPersonneAPrendreEnCharge the idPersonneAPrendreEnCharge to set
     */
    public void setIdPersonneAPrendreEnCharge(Long idPersonneAPrendreEnCharge) {
        this.idPersonneAPrendreEnCharge = idPersonneAPrendreEnCharge;
    }

    /**
     * Setter function.
     * @param nomEtablissement the nomEtablissement to set
     */
    public void setNomEtablissement(String nomEtablissement) {
        this.nomEtablissement = nomEtablissement;
    }

    /**
     * Setter function.
     * @param numeroVoieEtablissement the numeroVoieEtablissement to set
     */
    public void setNumeroVoieEtablissement(String numeroVoieEtablissement) {
        this.numeroVoieEtablissement = numeroVoieEtablissement;
    }

    /**
     * Setter function.
     * @param complementNomEtablissement the complementNomEtablissement to set
     */
    public void setComplementNomEtablissement(String complementNomEtablissement) {
        this.complementNomEtablissement = complementNomEtablissement;
    }

    /**
     * Setter function.
     * @param natureVoieEtablissement the natureVoieEtablissement to set
     */
    public void setNatureVoieEtablissement(String natureVoieEtablissement) {
        this.natureVoieEtablissement = natureVoieEtablissement;
    }

    /**
     * Setter function.
     * @param cleNatureVoieEtablissement the cleNatureVoieEtablissement to set
     */
    public void setCleNatureVoieEtablissement(String cleNatureVoieEtablissement) {
        this.cleNatureVoieEtablissement = cleNatureVoieEtablissement;
    }

    /**
     * Setter function.
     * @param adresseEtablissement the adresseEtablissement to set
     */
    public void setAdresseEtablissement(String adresseEtablissement) {
        this.adresseEtablissement = adresseEtablissement;
    }

    /**
     * Setter function.
     * @param complementAdresseEtablissement the complementAdresseEtablissement to set
     */
    public void setComplementAdresseEtablissement(String complementAdresseEtablissement) {
        this.complementAdresseEtablissement = complementAdresseEtablissement;
    }

    /**
     * Setter function.
     * @param autreComplementEtablissement the autreComplementEtablissement to set
     */
    public void setAutreComplementEtablissement(String autreComplementEtablissement) {
        this.autreComplementEtablissement = autreComplementEtablissement;
    }

    /**
     * Setter function.
     * @param codePostalEtablissement the codePostalEtablissement to set
     */
    public void setCodePostalEtablissement(String codePostalEtablissement) {
        this.codePostalEtablissement = codePostalEtablissement;
    }

    /**
     * Setter function.
     * @param villeEtablissement the villeEtablissement to set
     */
    public void setVilleEtablissement(String villeEtablissement) {
        this.villeEtablissement = villeEtablissement;
    }

    /**
     * Setter function.
     * @param numeroFaxEtablissement the numeroFaxEtablissement to set
     */
    public void setNumeroFaxEtablissement(String numeroFaxEtablissement) {
        this.numeroFaxEtablissement = numeroFaxEtablissement;
    }

    /**
     * Setter function.
     * @param dateEntree the dateEntree to set
     */
    public void setDateEntree(Calendar dateEntree) {
        this.dateEntree = dateEntree;
    }

    /**
     * Setter function.
     * @param natureSoins the natureSoins to set
     */
    public void setNatureSoins(String natureSoins) {
        this.natureSoins = natureSoins;
    }

    /**
     * Setter function.
     * @param codeDiscipline the codeDiscipline to set
     */
    public void setCodeDiscipline(String codeDiscipline) {
        this.codeDiscipline = codeDiscipline;
    }

    /**
     * Getter function.
     * @return the idCreateur
     */
    public Long getIdCreateur() {
        return idCreateur;
    }

    /**
     * Setter function.
     * @param idCreateur the idCreateur to set
     */
    public void setIdCreateur(Long idCreateur) {
        this.idCreateur = idCreateur;
    }

}

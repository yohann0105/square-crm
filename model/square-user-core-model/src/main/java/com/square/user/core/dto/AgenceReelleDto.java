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
package com.square.user.core.dto;

import java.io.Serializable;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO d'une agence réelle.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net)
 */
public class AgenceReelleDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -8106033164430969906L;

    /** Identifiant. */
    private Long id;

    /** Identifiant de la commune. */
    private Long idCommune;

    /** Dénomination commerciale. */
    private String denomination;

    /** Numéro de voie. */
    private String numeroVoie;

    /** Nature de la voie. */
    private String natureVoie;

    /** Libellé de la voie. */
    private String libelleVoie;

    /** Localité. */
    private String localite;

    /** Département. */
    private String departement;

    /** Code postal. */
    private String codePostal;

    /** Numéro de téléphone fixe. */
    private String numeroTelephoneFixe;

    /** Numéro de téléphone portable. */
    private String numeroTelephonePortable;

    /** Numéro de fax. */
    private String numeroFax;

    /** Mail de l'agence. */
    private String mail;

    /** Phrase pour les heures d'ouverture. */
    private String heuresOuverture;

    /** Réseau associé. */
    private IdentifiantLibelleDto reseau;

    /** Inidique si c'est un courtier. */
    private Boolean isCourtier = false;

    /** Image. */
    private String pathImage;

    /** Indique si l'agence est visibles sur le site web. */
    private Boolean isVisibleSiteWeb;

    /** Balise title pour le site web. */
    private String baliseTitle;

    /** Texte descriptif sur le site web. */
    private String descriptif;

    /** URL rewriting. */
    private String urlRewriting;

    /** Adresse pour Google Maps, si l'adresse principale ne marche pas correctement. */
    private String adresseGoogleMaps;

    /** Raison sociale réelle. */
    private String raisonSocialeReelle;

    /** Raison sociale commerciale. */
    private String raisonSocialeCommerciale;

    /** Numéro Orias. */
    private String numeroOrias;

    /** Latitude. */
    private Double latitude;

    /** Longitude. */
    private Double longitude;

    /**
     * Retourne la valeur de denomination.
     * @return la valeur de denomination
     */
    public String getDenomination() {
        return denomination;
    }

    /**
     * Définit la valeur de denomination.
     * @param denomination la nouvelle valeur de denomination
     */
    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    /**
     * Retourne la valeur de numeroVoie.
     * @return la valeur de numeroVoie
     */
    public String getNumeroVoie() {
        return numeroVoie;
    }

    /**
     * Définit la valeur de numeroVoie.
     * @param numeroVoie la nouvelle valeur de numeroVoie
     */
    public void setNumeroVoie(String numeroVoie) {
        this.numeroVoie = numeroVoie;
    }

    /**
     * Retourne la valeur de natureVoie.
     * @return la valeur de natureVoie
     */
    public String getNatureVoie() {
        return natureVoie;
    }

    /**
     * Définit la valeur de natureVoie.
     * @param natureVoie la nouvelle valeur de natureVoie
     */
    public void setNatureVoie(String natureVoie) {
        this.natureVoie = natureVoie;
    }

    /**
     * Retourne la valeur de libelleVoie.
     * @return la valeur de libelleVoie
     */
    public String getLibelleVoie() {
        return libelleVoie;
    }

    /**
     * Définit la valeur de libelleVoie.
     * @param libelleVoie la nouvelle valeur de libelleVoie
     */
    public void setLibelleVoie(String libelleVoie) {
        this.libelleVoie = libelleVoie;
    }

    /**
     * Retourne la valeur de localite.
     * @return la valeur de localite
     */
    public String getLocalite() {
        return localite;
    }

    /**
     * Définit la valeur de localite.
     * @param localite la nouvelle valeur de localite
     */
    public void setLocalite(String localite) {
        this.localite = localite;
    }

    /**
     * Retourne la valeur de departement.
     * @return la valeur de departement
     */
    public String getDepartement() {
        return departement;
    }

    /**
     * Définit la valeur de departement.
     * @param departement la nouvelle valeur de departement
     */
    public void setDepartement(String departement) {
        this.departement = departement;
    }

    /**
     * Retourne la valeur de codePostal.
     * @return la valeur de codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Définit la valeur de codePostal.
     * @param codePostal la nouvelle valeur de codePostal
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Retourne la valeur de numeroTelephoneFixe.
     * @return la valeur de numeroTelephoneFixe
     */
    public String getNumeroTelephoneFixe() {
        return numeroTelephoneFixe;
    }

    /**
     * Définit la valeur de numeroTelephoneFixe.
     * @param numeroTelephoneFixe la nouvelle valeur de numeroTelephoneFixe
     */
    public void setNumeroTelephoneFixe(String numeroTelephoneFixe) {
        this.numeroTelephoneFixe = numeroTelephoneFixe;
    }

    /**
     * Retourne la valeur de numeroTelephonePortable.
     * @return la valeur de numeroTelephonePortable
     */
    public String getNumeroTelephonePortable() {
        return numeroTelephonePortable;
    }

    /**
     * Définit la valeur de numeroTelephonePortable.
     * @param numeroTelephonePortable la nouvelle valeur de numeroTelephonePortable
     */
    public void setNumeroTelephonePortable(String numeroTelephonePortable) {
        this.numeroTelephonePortable = numeroTelephonePortable;
    }

    /**
     * Retourne la valeur de numeroFax.
     * @return la valeur de numeroFax
     */
    public String getNumeroFax() {
        return numeroFax;
    }

    /**
     * Définit la valeur de numeroFax.
     * @param numeroFax la nouvelle valeur de numeroFax
     */
    public void setNumeroFax(String numeroFax) {
        this.numeroFax = numeroFax;
    }

    /**
     * Retourne la valeur de reseau.
     * @return la valeur de reseau
     */
    public IdentifiantLibelleDto getReseau() {
        return reseau;
    }

    /**
     * Définit la valeur de reseau.
     * @param reseau la nouvelle valeur de reseau
     */
    public void setReseau(IdentifiantLibelleDto reseau) {
        this.reseau = reseau;
    }

    /**
     * Retourne la valeur de id.
     * @return la valeur de id
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne la valeur de mail.
     * @return la valeur de mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Définit la valeur de mail.
     * @param mail la nouvelle valeur de mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Retourne la valeur de heuresOuverture.
     * @return la valeur de heuresOuverture
     */
    public String getHeuresOuverture() {
        return heuresOuverture;
    }

    /**
     * Définit la valeur de heuresOuverture.
     * @param heuresOuverture la nouvelle valeur de heuresOuverture
     */
    public void setHeuresOuverture(String heuresOuverture) {
        this.heuresOuverture = heuresOuverture;
    }

    /**
     * Get the isCourtier value.
     * @return the isCourtier
     */
    public Boolean getIsCourtier() {
        return isCourtier;
    }

    /**
     * Set the isCourtier value.
     * @param isCourtier the isCourtier to set
     */
    public void setIsCourtier(Boolean isCourtier) {
        this.isCourtier = isCourtier;
    }

    /**
     * Get the pathImage value.
     * @return the pathImage
     */
    public String getPathImage() {
        return pathImage;
    }

    /**
     * Set the pathImage value.
     * @param pathImage the pathImage to set
     */
    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    /**
     * Get the isVisibleSiteWeb value.
     * @return the isVisibleSiteWeb
     */
    public Boolean getIsVisibleSiteWeb() {
        return isVisibleSiteWeb;
    }

    /**
     * Set the isVisibleSiteWeb value.
     * @param isVisibleSiteWeb the isVisibleSiteWeb to set
     */
    public void setIsVisibleSiteWeb(Boolean isVisibleSiteWeb) {
        this.isVisibleSiteWeb = isVisibleSiteWeb;
    }

    /**
     * Get the baliseTitle value.
     * @return the baliseTitle
     */
    public String getBaliseTitle() {
        return baliseTitle;
    }

    /**
     * Set the baliseTitle value.
     * @param baliseTitle the baliseTitle to set
     */
    public void setBaliseTitle(String baliseTitle) {
        this.baliseTitle = baliseTitle;
    }

    /**
     * Get the descriptif value.
     * @return the descriptif
     */
    public String getDescriptif() {
        return descriptif;
    }

    /**
     * Set the descriptif value.
     * @param descriptif the descriptif to set
     */
    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    /**
     * Get the urlRewriting value.
     * @return the urlRewriting
     */
    public String getUrlRewriting() {
        return urlRewriting;
    }

    /**
     * Set the urlRewriting value.
     * @param urlRewriting the urlRewriting to set
     */
    public void setUrlRewriting(String urlRewriting) {
        this.urlRewriting = urlRewriting;
    }

    /**
     * Get the idCommune value.
     * @return the idCommune
     */
    public Long getIdCommune() {
        return idCommune;
    }

    /**
     * Set the idCommune value.
     * @param idCommune the idCommune to set
     */
    public void setIdCommune(Long idCommune) {
        this.idCommune = idCommune;
    }

    /**
     * Get the adresseGoogleMaps value.
     * @return the adresseGoogleMaps
     */
    public String getAdresseGoogleMaps() {
        return adresseGoogleMaps;
    }

    /**
     * Set the adresseGoogleMaps value.
     * @param adresseGoogleMaps the adresseGoogleMaps to set
     */
    public void setAdresseGoogleMaps(String adresseGoogleMaps) {
        this.adresseGoogleMaps = adresseGoogleMaps;
    }

    /**
     * Get the raisonSocialeReelle value.
     * @return the raisonSocialeReelle
     */
    public String getRaisonSocialeReelle() {
        return raisonSocialeReelle;
    }

    /**
     * Set the raisonSocialeReelle value.
     * @param raisonSocialeReelle the raisonSocialeReelle to set
     */
    public void setRaisonSocialeReelle(String raisonSocialeReelle) {
        this.raisonSocialeReelle = raisonSocialeReelle;
    }

    /**
     * Get the raisonSocialeCommerciale value.
     * @return the raisonSocialeCommerciale
     */
    public String getRaisonSocialeCommerciale() {
        return raisonSocialeCommerciale;
    }

    /**
     * Set the raisonSocialeCommerciale value.
     * @param raisonSocialeCommerciale the raisonSocialeCommerciale to set
     */
    public void setRaisonSocialeCommerciale(String raisonSocialeCommerciale) {
        this.raisonSocialeCommerciale = raisonSocialeCommerciale;
    }

    /**
     * Get the numeroOrias value.
     * @return the numeroOrias
     */
    public String getNumeroOrias() {
        return numeroOrias;
    }

    /**
     * Set the numeroOrias value.
     * @param numeroOrias the numeroOrias to set
     */
    public void setNumeroOrias(String numeroOrias) {
        this.numeroOrias = numeroOrias;
    }

    /**
     * Récupère la valeur de latitude.
     * @return la valeur de latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Définit la valeur de latitude.
     * @param latitude la nouvelle valeur de latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Récupère la valeur de longitude.
     * @return la valeur de longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Définit la valeur de longitude.
     * @param longitude la nouvelle valeur de longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}

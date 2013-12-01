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
package com.square.composant.espace.client.square.client.model.espace.client;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.composant.espace.client.square.client.model.espace.adherent.InfosOptionsAdherentModel;

/**
 * Model modélisant l'espace client d'une personne.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class EspaceClientInternetModel implements IsSerializable {

    /** Identifiant de l'espace client. */
    private Long id;

    /** Identifiant externe de l'espace client. */
    private String eid;

    /** Identifiant unique de la personne associée à l'espace client. */
    private Long uidPersonne;

    /** Identifiant de connexion à l'espace client. */
    private String login;

    /** Mot de passe de connexion à l'espace client. */
    private String motDePasse;

    /** Nature de l'espace client. */
    private IdentifiantLibelleGwt nature;

    /** Indique si l'espace client est activé ou désactivé. */
    private Boolean active;

    /** Indique si la personne a déjà visité son espace client. */
    private Boolean premiereVisite;

    /** Affichage la page de nouveau service. */
    private Boolean afficheNouveauService;

    /** Date de création de l'espace client. */
    private String dateCreation;

    /** Date de modification de l'espace client. */
    private String dateModification;

    /** Date de la première visite de la personne sur son espace client Internet. */
    private String datePremiereVisite;

    /** Date de la dernière visite de la personne sur son espace client Internet. */
    private String dateDerniereVisite;

    /** Le nombre de visites de la personne sur son espace client. */
    private Integer nbVisites;

    /** Date de désactivation. */
    private String dateDesactivation;

    /** Date de réactivation. */
    private String dateReactivation;

    /** Date de dématérialisation. */
    private String dateDerniereDematerialisation;

    /** Email. */
    private String email;

    /** Téléphone. */
    private String telephone;

    /** Infos des options d'un adherent. */
    private InfosOptionsAdherentModel infosOptionsAdherentModel;

    /**
     * Constructeur par défaut.
     */
    public EspaceClientInternetModel() {
        super();
    }

    /**
     * Constructeur.
     * @param uidPersonne identifiant de la personne associée à l'espace client
     */
    public EspaceClientInternetModel(Long uidPersonne) {
        super();
        this.uidPersonne = uidPersonne;
    }

    /**
     * Getter function.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter function.
     * @return the eid
     */
    public String getEid() {
        return eid;
    }

    /**
     * Setter function.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Setter function.
     * @param eid the eid to set
     */
    public void setEid(String eid) {
        this.eid = eid;
    }

    /**
     * Getter function.
     * @return the uidPersonne
     */
    public Long getUidPersonne() {
        return uidPersonne;
    }

    /**
     * Getter function.
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Getter function.
     * @return the motDePasse
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Get the nature value.
     * @return the nature
     */
    public IdentifiantLibelleGwt getNature() {
        return nature;
    }

    /**
     * Set the nature value.
     * @param nature the nature to set
     */
    public void setNature(IdentifiantLibelleGwt nature) {
        this.nature = nature;
    }

    /**
     * Get the active value.
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Set the active value.
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Get the premiereVisite value.
     * @return the premiereVisite
     */
    public Boolean getPremiereVisite() {
        return premiereVisite;
    }

    /**
     * Set the premiereVisite value.
     * @param premiereVisite the premiereVisite to set
     */
    public void setPremiereVisite(Boolean premiereVisite) {
        this.premiereVisite = premiereVisite;
    }

    /**
     * Get the afficheNouveauService value.
     * @return the afficheNouveauService
     */
    public Boolean getAfficheNouveauService() {
        return afficheNouveauService;
    }

    /**
     * Set the afficheNouveauService value.
     * @param afficheNouveauService the afficheNouveauService to set
     */
    public void setAfficheNouveauService(Boolean afficheNouveauService) {
        this.afficheNouveauService = afficheNouveauService;
    }

    /**
     * Get the dateCreation value.
     * @return the dateCreation
     */
    public String getDateCreation() {
        return dateCreation;
    }

    /**
     * Set the dateCreation value.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Get the dateModification value.
     * @return the dateModification
     */
    public String getDateModification() {
        return dateModification;
    }

    /**
     * Set the dateModification value.
     * @param dateModification the dateModification to set
     */
    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Get the datePremiereVisite value.
     * @return the datePremiereVisite
     */
    public String getDatePremiereVisite() {
        return datePremiereVisite;
    }

    /**
     * Set the datePremiereVisite value.
     * @param datePremiereVisite the datePremiereVisite to set
     */
    public void setDatePremiereVisite(String datePremiereVisite) {
        this.datePremiereVisite = datePremiereVisite;
    }

    /**
     * Get the dateDerniereVisite value.
     * @return the dateDerniereVisite
     */
    public String getDateDerniereVisite() {
        return dateDerniereVisite;
    }

    /**
     * Set the dateDerniereVisite value.
     * @param dateDerniereVisite the dateDerniereVisite to set
     */
    public void setDateDerniereVisite(String dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite;
    }

    /**
     * Get the nbVisites value.
     * @return the nbVisites
     */
    public Integer getNbVisites() {
        return nbVisites;
    }

    /**
     * Set the nbVisites value.
     * @param nbVisites the nbVisites to set
     */
    public void setNbVisites(Integer nbVisites) {
        this.nbVisites = nbVisites;
    }

    /**
     * Get the dateDesactivation value.
     * @return the dateDesactivation
     */
    public String getDateDesactivation() {
        return dateDesactivation;
    }

    /**
     * Set the dateDesactivation value.
     * @param dateDesactivation the dateDesactivation to set
     */
    public void setDateDesactivation(String dateDesactivation) {
        this.dateDesactivation = dateDesactivation;
    }

    /**
     * Get the dateReactivation value.
     * @return the dateReactivation
     */
    public String getDateReactivation() {
        return dateReactivation;
    }

    /**
     * Set the dateReactivation value.
     * @param dateReactivation the dateReactivation to set
     */
    public void setDateReactivation(String dateReactivation) {
        this.dateReactivation = dateReactivation;
    }

    /**
     * Get the dateDerniereDematerialisation value.
     * @return the dateDerniereDematerialisation
     */
    public String getDateDerniereDematerialisation() {
        return dateDerniereDematerialisation;
    }

    /**
     * Set the dateDerniereDematerialisation value.
     * @param dateDerniereDematerialisation the dateDerniereDematerialisation to set
     */
    public void setDateDerniereDematerialisation(String dateDerniereDematerialisation) {
        this.dateDerniereDematerialisation = dateDerniereDematerialisation;
    }

    /**
     * Set the uidPersonne value.
     * @param uidPersonne the uidPersonne to set
     */
    public void setUidPersonne(Long uidPersonne) {
        this.uidPersonne = uidPersonne;
    }

    /**
     * Set the login value.
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Set the motDePasse value.
     * @param motDePasse the motDePasse to set
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Get the email value.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email value.
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the infosOptionsAdherentModel value.
     * @return the infosOptionsAdherentModel
     */
    public InfosOptionsAdherentModel getInfosOptionsAdherentModel() {
        return infosOptionsAdherentModel;
    }

    /**
     * Set the infosOptionsAdherentModel value.
     * @param infosOptionsAdherentModel the infosOptionsAdherentModel to set
     */
    public void setInfosOptionsAdherentModel(InfosOptionsAdherentModel infosOptionsAdherentModel) {
        this.infosOptionsAdherentModel = infosOptionsAdherentModel;
    }

    /**
     * Récupère la valeur de telephone.
     * @return la valeur de telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Définit la valeur de telephone.
     * @param telephone la nouvelle valeur de telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}

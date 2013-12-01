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
package com.square.adherent.noyau.dto.espace.client;

import java.io.Serializable;
import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO modélisant l'espace client d'une personne.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class EspaceClientInternetDto implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -6969575510242661325L;

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
    private IdentifiantLibelleDto nature;

    /** Indique si l'espace client est activé ou désactivé. */
    private Boolean active;

    /** Indique si la personne a déjà visité son espace client. */
    private Boolean premiereVisite;

    /** Affichage la page de nouveau service. */
    private Boolean afficheNouveauService;

    /** Date de création de l'espace client. */
    private Calendar dateCreation;

    /** Date de modification de l'espace client. */
    private Calendar dateModification;

    /** Date de la première visite de la personne sur son espace client Internet. */
    private Calendar datePremiereVisite;

    /** Date de la dernière visite de la personne sur son espace client Internet. */
    private Calendar dateDerniereVisite;

    /** Le nombre de visites de la personne sur son espace client. */
    private Integer nbVisites;

    /** Date de désactivation. */
    private Calendar dateDesactivation;

    /** Date de réactivation. */
    private Calendar dateReactivation;

    /** Date de dématérialisation. */
    private Calendar dateDerniereDematerialisation;

    /**
     * Constructeur par défaut.
     */
    public EspaceClientInternetDto() {
        super();
    }

    /**
     * Constructeur.
     * @param uidPersonne identifiant de la personne associée à l'espace client
     */
    public EspaceClientInternetDto(Long uidPersonne) {
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
     * Getter function.
     * @return the nature
     */
    public IdentifiantLibelleDto getNature() {
        return nature;
    }

    /**
     * Getter function.
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Getter function.
     * @return the premiereVisite
     */
    public Boolean getPremiereVisite() {
        return premiereVisite;
    }

    /**
     * Getter function.
     * @return the afficheNouveauService
     */
    public Boolean getAfficheNouveauService() {
        return afficheNouveauService;
    }

    /**
     * Getter function.
     * @return the dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Getter function.
     * @return the dateModification
     */
    public Calendar getDateModification() {
        return dateModification;
    }

    /**
     * Getter function.
     * @return the datePremiereVisite
     */
    public Calendar getDatePremiereVisite() {
        return datePremiereVisite;
    }

    /**
     * Getter function.
     * @return the dateDerniereVisite
     */
    public Calendar getDateDerniereVisite() {
        return dateDerniereVisite;
    }

    /**
     * Getter function.
     * @return the nbVisites
     */
    public Integer getNbVisites() {
        return nbVisites;
    }

    /**
     * Getter function.
     * @return the dateDesactivation
     */
    public Calendar getDateDesactivation() {
        return dateDesactivation;
    }

    /**
     * Getter function.
     * @return the dateReactivation
     */
    public Calendar getDateReactivation() {
        return dateReactivation;
    }

    /**
     * Getter function.
     * @return the dateDerniereDematerialisation
     */
    public Calendar getDateDerniereDematerialisation() {
        return dateDerniereDematerialisation;
    }

    /**
     * Setter function.
     * @param uidPersonne the uidPersonne to set
     */
    public void setUidPersonne(Long uidPersonne) {
        this.uidPersonne = uidPersonne;
    }

    /**
     * Setter function.
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Setter function.
     * @param motDePasse the motDePasse to set
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Setter function.
     * @param nature the nature to set
     */
    public void setNature(IdentifiantLibelleDto nature) {
        this.nature = nature;
    }

    /**
     * Setter function.
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Setter function.
     * @param premiereVisite the premiereVisite to set
     */
    public void setPremiereVisite(Boolean premiereVisite) {
        this.premiereVisite = premiereVisite;
    }

    /**
     * Setter function.
     * @param afficheNouveauService the afficheNouveauService to set
     */
    public void setAfficheNouveauService(Boolean afficheNouveauService) {
        this.afficheNouveauService = afficheNouveauService;
    }

    /**
     * Setter function.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Setter function.
     * @param dateModification the dateModification to set
     */
    public void setDateModification(Calendar dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Setter function.
     * @param datePremiereVisite the datePremiereVisite to set
     */
    public void setDatePremiereVisite(Calendar datePremiereVisite) {
        this.datePremiereVisite = datePremiereVisite;
    }

    /**
     * Setter function.
     * @param dateDerniereVisite the dateDerniereVisite to set
     */
    public void setDateDerniereVisite(Calendar dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite;
    }

    /**
     * Setter function.
     * @param nbVisites the nbVisites to set
     */
    public void setNbVisites(Integer nbVisites) {
        this.nbVisites = nbVisites;
    }

    /**
     * Setter function.
     * @param dateDesactivation the dateDesactivation to set
     */
    public void setDateDesactivation(Calendar dateDesactivation) {
        this.dateDesactivation = dateDesactivation;
    }

    /**
     * Setter function.
     * @param dateReactivation the dateReactivation to set
     */
    public void setDateReactivation(Calendar dateReactivation) {
        this.dateReactivation = dateReactivation;
    }

    /**
     * Setter function.
     * @param dateDerniereDematerialisation the dateDerniereDematerialisation to set
     */
    public void setDateDerniereDematerialisation(Calendar dateDerniereDematerialisation) {
        this.dateDerniereDematerialisation = dateDerniereDematerialisation;
    }

}

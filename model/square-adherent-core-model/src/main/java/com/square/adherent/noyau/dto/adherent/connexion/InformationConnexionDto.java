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
package com.square.adherent.noyau.dto.adherent.connexion;

import java.io.Serializable;
import java.util.Calendar;

/**
 * DTO représentant les informations de connexion d'une personne.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class InformationConnexionDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 2836704689668041387L;

    /** Identifiant de la personne. */
    private Long idPersonne;

    /** Login. */
    private String login;

    /** Mot de passe. */
    private String motDePasse;

    /** Date de désactivation. */
    private Calendar dateDesactivation;

    /** Date de réactivation. */
    private Calendar dateReactivation;

    /** Première visite. */
    private Boolean isPremiereVisite;

    /** Booléen indiquant si on affiche la page de nouveau service. */
    private Boolean afficheNouveauService;

    /** Date de la première visite. */
    private Calendar datePremiereVisite;

    /** Date de la dernière visite. */
    private Calendar dateDerniereVisite;

    /** Date de dématérialisation. */
    private Calendar dateDerniereDematerialisation;

    /** Nombre de visites. */
    private Integer nbVisites;

    /** Active. */
    private Boolean actif;

    /**
     * Récupère la valeur de login.
     * @return la valeur de login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Définit la valeur de login.
     * @param login la nouvelle valeur de login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Récupère la valeur de motDePasse.
     * @return la valeur de motDePasse
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Définit la valeur de motDePasse.
     * @param motDePasse la nouvelle valeur de motDePasse
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Récupère la valeur de dateDesactivation.
     * @return la valeur de dateDesactivation
     */
    public Calendar getDateDesactivation() {
        return dateDesactivation;
    }

    /**
     * Définit la valeur de dateDesactivation.
     * @param dateDesactivation la nouvelle valeur de dateDesactivation
     */
    public void setDateDesactivation(Calendar dateDesactivation) {
        this.dateDesactivation = dateDesactivation;
    }

    /**
     * Récupère la valeur de dateReactivation.
     * @return la valeur de dateReactivation
     */
    public Calendar getDateReactivation() {
        return dateReactivation;
    }

    /**
     * Définit la valeur de dateReactivation.
     * @param dateReactivation la nouvelle valeur de dateReactivation
     */
    public void setDateReactivation(Calendar dateReactivation) {
        this.dateReactivation = dateReactivation;
    }

    /**
     * Récupère la valeur de isPremiereVisite.
     * @return la valeur de isPremiereVisite
     */
    public Boolean getIsPremiereVisite() {
        return isPremiereVisite;
    }

    /**
     * Définit la valeur de isPremiereVisite.
     * @param isPremiereVisite la nouvelle valeur de isPremiereVisite
     */
    public void setIsPremiereVisite(Boolean isPremiereVisite) {
        this.isPremiereVisite = isPremiereVisite;
    }

    /**
     * Récupère la valeur de afficheNouveauService.
     * @return la valeur de afficheNouveauService
     */
    public Boolean getAfficheNouveauService() {
        return afficheNouveauService;
    }

    /**
     * Définit la valeur de afficheNouveauService.
     * @param afficheNouveauService la nouvelle valeur de afficheNouveauService
     */
    public void setAfficheNouveauService(Boolean afficheNouveauService) {
        this.afficheNouveauService = afficheNouveauService;
    }

    /**
     * Récupère la valeur de datePremiereVisite.
     * @return la valeur de datePremiereVisite
     */
    public Calendar getDatePremiereVisite() {
        return datePremiereVisite;
    }

    /**
     * Définit la valeur de datePremiereVisite.
     * @param datePremiereVisite la nouvelle valeur de datePremiereVisite
     */
    public void setDatePremiereVisite(Calendar datePremiereVisite) {
        this.datePremiereVisite = datePremiereVisite;
    }

    /**
     * Récupère la valeur de dateDerniereVisite.
     * @return la valeur de dateDerniereVisite
     */
    public Calendar getDateDerniereVisite() {
        return dateDerniereVisite;
    }

    /**
     * Définit la valeur de dateDerniereVisite.
     * @param dateDerniereVisite la nouvelle valeur de dateDerniereVisite
     */
    public void setDateDerniereVisite(Calendar dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite;
    }

    /**
     * Récupère la valeur de dateDerniereDematerialisation.
     * @return la valeur de dateDerniereDematerialisation
     */
    public Calendar getDateDerniereDematerialisation() {
        return dateDerniereDematerialisation;
    }

    /**
     * Définit la valeur de dateDerniereDematerialisation.
     * @param dateDerniereDematerialisation la nouvelle valeur de dateDerniereDematerialisation
     */
    public void setDateDerniereDematerialisation(Calendar dateDerniereDematerialisation) {
        this.dateDerniereDematerialisation = dateDerniereDematerialisation;
    }

    /**
     * Récupère la valeur de nbVisites.
     * @return la valeur de nbVisites
     */
    public Integer getNbVisites() {
        return nbVisites;
    }

    /**
     * Définit la valeur de nbVisites.
     * @param nbVisites la nouvelle valeur de nbVisites
     */
    public void setNbVisites(Integer nbVisites) {
        this.nbVisites = nbVisites;
    }

    /**
     * Récupère la valeur de actif.
     * @return la valeur de actif
     */
    public Boolean getActif() {
        return actif;
    }

    /**
     * Définit la valeur de actif.
     * @param actif la nouvelle valeur de actif
     */
    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    /**
     * Get the idPersonne value.
     * @return the idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Set the idPersonne value.
     * @param idPersonne the idPersonne to set
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }
}

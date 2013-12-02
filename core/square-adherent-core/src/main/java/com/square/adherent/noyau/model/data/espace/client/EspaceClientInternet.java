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
package com.square.adherent.noyau.model.data.espace.client;

import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate.type.EncryptedStringType;
import org.scub.foundation.framework.core.model.BaseModel;

import com.square.adherent.noyau.model.ModelEID;
import com.square.adherent.noyau.model.dimension.EspaceClientInternetNature;
import com.square.adherent.noyau.model.hibernate.type.OuiNonType;

/**
 * Entité persistante qui modélise les informations de l'espace client Internet d'une personne.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_ESPACE_CLIENT_INTERNET")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "ESPACE_CLIENT_INTERNET_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "ESPACE_CLIENT_INTERNET_EID", unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "ESPACE_CLIENT_INTERNET_VERSION", unique = false))
})
@TypeDefs({
@TypeDef(name = "OuiNonType", typeClass = OuiNonType.class),
@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class,
         parameters = {@Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") }) })
public class EspaceClientInternet extends ModelEID {

    /** serialVersionUID. */
    private static final long serialVersionUID = 7525698383762179984L;

    /** Identifiant unique de l'adhérent. */
    @Column(name = "ESPACE_CLIENT_INTERNET_PERSONNE_UID")
    @ForeignKey(name = "FK_ESPACE_CLIENT_INTERNET_PERSONNE")
    private Long uidPersonne;

    /** Login. */
    @Column(name = "ESPACE_CLIENT_INTERNET_LOGIN", unique = true)
    private String login;

    /** Mot de passe. */
    @Column(name = "ESPACE_CLIENT_INTERNET_MDP")
    @Type(type = "encryptedString")
    private String motDePasse;

    /** Nature de l'espace client. */
    @ManyToOne
    @JoinColumn(name = "ESPACE_CLIENT_INTERNET_NATURE_UID")
    @ForeignKey(name = "FK_ESPACE_CLIENT_INTERNET_NATURE")
    private EspaceClientInternetNature nature;

    /** Indique si l'espace client est activé ou désactivé. */
    @Column(name = "ESPACE_CLIENT_INTERNET_ACTIVE", nullable = false)
    @Type(type = "OuiNonType")
    private boolean active;

    /** Date de la première visite de la personne sur son espace client Internet. */
    @Column(name = "ESPACE_CLIENT_INTERNET_DATE_PREMIERE_VISITE")
    private Calendar datePremiereVisite;

    /** Date de la dernière visite de la personne sur son espace client Internet. */
    @Column(name = "ESPACE_CLIENT_INTERNET_DATE_DERNIERE_VISITE")
    private Calendar dateDerniereVisite;

    /** Le nombre de visites sur l'espace personnel de l'adhérent. */
    @Column(name = "ESPACE_CLIENT_INTERNET_NB_VISITES")
    private Integer nbVisites;

    /** Première visite. */
    @Column(name = "ESPACE_CLIENT_INTERNET_PREMIERE_VISITE")
    @Type(type = "OuiNonType")
    private Boolean premiereVisite;

    /** Affichage la page de nouveau service. */
    @Column(name = "ESPACE_CLIENT_INTERNET_AFFICHE_NOUVEAU_SERVICE")
    @Type(type = "OuiNonType")
    private Boolean afficheNouveauService;

    /** Date de désactivation. */
    @Column(name = "ESPACE_CLIENT_INTERNET_DATE_DESACTIVATION")
    private Calendar dateDesactivation;

    /** Date de réactivation. */
    @Column(name = "ESPACE_CLIENT_INTERNET_DATE_REACTIVATION")
    private Calendar dateReactivation;

    /** Date de dématérialisation. */
    @Column(name = "ESPACE_CLIENT_INTERNET_DATE_DEMATERIALISATION")
    private Calendar dateDerniereDematerialisation;

    /** Date de création de l'espace client. */
    @Column(name = "ESPACE_CLIENT_INTERNET_DATE_CREATION")
    private Calendar dateCreation;

    /** Date de modification de l'espace client. */
    @Column(name = "ESPACE_CLIENT_INTERNET_DATE_MODIFICATION")
    private Calendar dateModification;

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return false;
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
    public EspaceClientInternetNature getNature() {
        return nature;
    }

    /**
     * Getter function.
     * @return the nbVisites
     */
    public Integer getNbVisites() {
        return nbVisites;
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
    public void setNature(EspaceClientInternetNature nature) {
        this.nature = nature;
    }

    /**
     * Setter function.
     * @param nbVisites the nbVisites to set
     */
    public void setNbVisites(Integer nbVisites) {
        this.nbVisites = nbVisites;
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
     * Setter function.
     * @param uidPersonne the uidPersonne to set
     */
    public void setUidPersonne(Long uidPersonne) {
        this.uidPersonne = uidPersonne;
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
     * Récupère la valeur de premiereVisite.
     * @return la valeur de premiereVisite
     */
    public Boolean getPremiereVisite() {
        return premiereVisite;
    }

    /**
     * Définit la valeur de premiereVisite.
     * @param premiereVisite la nouvelle valeur de premiereVisite
     */
    public void setPremiereVisite(Boolean premiereVisite) {
        this.premiereVisite = premiereVisite;
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
     * Getter function.
     * @return the dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Setter function.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Récupère la valeur de dateModification.
     * @return la valeur de dateModification
     */
    public Calendar getDateModification() {
        return dateModification;
    }

    /**
     * Définit la valeur de dateModification.
     * @param dateModification la nouvelle valeur de dateModification
     */
    public void setDateModification(Calendar dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Getter function.
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Setter function.
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

}

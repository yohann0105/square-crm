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
package com.square.adherent.noyau.dto.adherent;

import java.io.Serializable;
import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO regroupant les informations d'une personne.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PersonneDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -7691305281521919993L;

    /** Identifiant. */
    private Long id;

    /** Nom. */
    private String nom;

    /** Prénom. */
    private String prenom;

    /** Numéro de client. */
    private String numeroClient;

    /** Civilité. */
    private IdentifiantLibelleDto civilite;

    /** Date de naissance. */
    private Calendar dateNaissance;

    /** Email de la personne. */
    private String adresseEmail;

    /** Numéro de téléphone fixe. */
    private String numeroTelephoneFixe;

    /** Adresse principale. */
    private AdresseDto adressePrincipale;

    /**
     * Récupère la valeur de id.
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
     * Récupère la valeur de nom.
     * @return la valeur de nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit la valeur de nom.
     * @param nom la nouvelle valeur de nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la valeur de prenom.
     * @return la valeur de prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit la valeur de prenom.
     * @param prenom la nouvelle valeur de prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupère la valeur de numeroClient.
     * @return la valeur de numeroClient
     */
    public String getNumeroClient() {
        return numeroClient;
    }

    /**
     * Définit la valeur de numeroClient.
     * @param numeroClient la nouvelle valeur de numeroClient
     */
    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
    }

    /**
     * Récupère la valeur de civilite.
     * @return la valeur de civilite
     */
    public IdentifiantLibelleDto getCivilite() {
        return civilite;
    }

    /**
     * Définit la valeur de civilite.
     * @param civilite la nouvelle valeur de civilite
     */
    public void setCivilite(IdentifiantLibelleDto civilite) {
        this.civilite = civilite;
    }

    /**
     * Récupère la valeur de dateNaissance.
     * @return la valeur de dateNaissance
     */
    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définit la valeur de dateNaissance.
     * @param dateNaissance la nouvelle valeur de dateNaissance
     */
    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Récupère la valeur de adresseEmail.
     * @return la valeur de adresseEmail
     */
    public String getAdresseEmail() {
        return adresseEmail;
    }

    /**
     * Définit la valeur de adresseEmail.
     * @param adresseEmail la nouvelle valeur de adresseEmail
     */
    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

    /**
     * Récupère la valeur de numeroTelephoneFixe.
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
     * Récupère la valeur de adressePrincipale.
     * @return la valeur de adressePrincipale
     */
    public AdresseDto getAdressePrincipale() {
        return adressePrincipale;
    }

    /**
     * Définit la valeur de adressePrincipale.
     * @param adressePrincipale la nouvelle valeur de adressePrincipale
     */
    public void setAdressePrincipale(AdresseDto adressePrincipale) {
        this.adressePrincipale = adressePrincipale;
    }
}

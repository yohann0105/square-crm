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
package com.square.composant.emails.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO GWT pour stocker les informations d'un mail correspondant au résultat de la recherche.
 * @author nnadeau - SCUB
 */
public class RechercheEmailResultatModel implements IsSerializable {

    /** Identifiant du mail. */
    private Long identifiantMail;

    /** Numéro de l'adhérent. */
    private String numeroAdherent;

    /** Nom de l'expéditeur. */
    private String nomExpediteur;

    /** Prénom de l'expéditeur. */
    private String prenomExpediteur;

    /** Adresse mail de l'expéditeur. */
    private String adresseMailExpediteur;

    /** Libellé de l'utilisateur en charge du mail. */
    private String libelleUtilisateurCharge;

    /** Libellé du service. */
    private String libelleService;

    /** Libellé de l'état. */
    private String libelleEtat;

    /** Sujet du mail. */
    private String sujetMail;

    /** Date d'envoi. */
    private String dateEnvoi;

    /** Identifiant du groupe. */
    private Long idGroupe;

    /**
     * Get the identifiantMail value.
     * @return the identifiantMail
     */
    public Long getIdentifiantMail() {
        return identifiantMail;
    }

    /**
     * Set the identifiantMail value.
     * @param identifiantMail the identifiantMail to set
     */
    public void setIdentifiantMail(Long identifiantMail) {
        this.identifiantMail = identifiantMail;
    }

    /**
     * Get the numeroAdherent value.
     * @return the numeroAdherent
     */
    public String getNumeroAdherent() {
        return numeroAdherent;
    }

    /**
     * Set the numeroAdherent value.
     * @param numeroAdherent the numeroAdherent to set
     */
    public void setNumeroAdherent(String numeroAdherent) {
        this.numeroAdherent = numeroAdherent;
    }

    /**
     * Get the nomExpediteur value.
     * @return the nomExpediteur
     */
    public String getNomExpediteur() {
        return nomExpediteur;
    }

    /**
     * Set the nomExpediteur value.
     * @param nomExpediteur the nomExpediteur to set
     */
    public void setNomExpediteur(String nomExpediteur) {
        this.nomExpediteur = nomExpediteur;
    }

    /**
     * Get the prenomExpediteur value.
     * @return the prenomExpediteur
     */
    public String getPrenomExpediteur() {
        return prenomExpediteur;
    }

    /**
     * Set the prenomExpediteur value.
     * @param prenomExpediteur the prenomExpediteur to set
     */
    public void setPrenomExpediteur(String prenomExpediteur) {
        this.prenomExpediteur = prenomExpediteur;
    }

    /**
     * Get the libelleUtilisateurCharge value.
     * @return the libelleUtilisateurCharge
     */
    public String getLibelleUtilisateurCharge() {
        return libelleUtilisateurCharge;
    }

    /**
     * Set the libelleUtilisateurCharge value.
     * @param libelleUtilisateurCharge the libelleUtilisateurCharge to set
     */
    public void setLibelleUtilisateurCharge(String libelleUtilisateurCharge) {
        this.libelleUtilisateurCharge = libelleUtilisateurCharge;
    }

    /**
     * Get the libelleService value.
     * @return the libelleService
     */
    public String getLibelleService() {
        return libelleService;
    }

    /**
     * Set the libelleService value.
     * @param libelleService the libelleService to set
     */
    public void setLibelleService(String libelleService) {
        this.libelleService = libelleService;
    }

    /**
     * Get the libelleEtat value.
     * @return the libelleEtat
     */
    public String getLibelleEtat() {
        return libelleEtat;
    }

    /**
     * Set the libelleEtat value.
     * @param libelleEtat the libelleEtat to set
     */
    public void setLibelleEtat(String libelleEtat) {
        this.libelleEtat = libelleEtat;
    }

    /**
     * Get the sujetMail value.
     * @return the sujetMail
     */
    public String getSujetMail() {
        return sujetMail;
    }

    /**
     * Set the sujetMail value.
     * @param sujetMail the sujetMail to set
     */
    public void setSujetMail(String sujetMail) {
        this.sujetMail = sujetMail;
    }

    /**
     * Get the dateEnvoi value.
     * @return the dateEnvoi
     */
    public String getDateEnvoi() {
        return dateEnvoi;
    }

    /**
     * Set the dateEnvoi value.
     * @param dateEnvoi the dateEnvoi to set
     */
    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    /**
     * Retourne la valeur de idGroupe.
     * @return la valeur de idGroupe
     */
    public Long getIdGroupe() {
        return idGroupe;
    }

    /**
     * Définit la valeur de idGroupe.
     * @param idGroupe la nouvelle valeur de idGroupe
     */
    public void setIdGroupe(Long idGroupe) {
        this.idGroupe = idGroupe;
    }

    /**
     * Récupère la valeur de adresseMailExpediteur.
     * @return la valeur de adresseMailExpediteur
     */
    public String getAdresseMailExpediteur() {
        return adresseMailExpediteur;
    }

    /**
     * Définit la valeur de adresseMailExpediteur.
     * @param adresseMailExpediteur la nouvelle valeur de adresseMailExpediteur
     */
    public void setAdresseMailExpediteur(String adresseMailExpediteur) {
        this.adresseMailExpediteur = adresseMailExpediteur;
    }
}

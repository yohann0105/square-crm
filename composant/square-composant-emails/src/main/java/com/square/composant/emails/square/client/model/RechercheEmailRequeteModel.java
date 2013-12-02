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
 * DTO GWT pour les critères de recherche de mail.
 * @author nnadeau - SCUB
 */
public class RechercheEmailRequeteModel implements IsSerializable {

    /** Nom. */
    private String nom;

    /** Prénom. */
    private String prenom;

    /** Numéro d'adhérent (recherche exacte). */
    private String numeroAdherent;

    /** Sujet du mail (recherche de type like). */
    private String sujetMail;

    /** Date de début d'envoi. */
    private String dateDebutEnvoi;

    /** Date de fin d'envoi. */
    private String dateFinEnvoi;

    /** Recherche Full Text. */
    private String rechercheFullText;

    /** Identifiant de l'état du mail. */
    private Long idEtat;

    /** Identifiant du service (utilisateurs chargés du mail). */
    private Long idService;

    /** Identifiant de l'utilisateur (chargé du mail). */
    private Long idUtilisateur;

    /** Ordre de tri. */
    private Boolean ordreAscendant;

    /** Colonne sur laquelle trier. */
    private String ordreColonne;

    /** Pagination. */
    private PagingModel pagination;

    /** Booléen pour la recherche FullText pour savoir si c'est une archive ou non. */
    private Boolean isArchive;

    /** Booléen pour indiquer si on est dans le cas d'une recherche fullText. */
    private Boolean isRechercheFullText;

    /**
     * Get the nom value.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the nom value.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get the prenom value.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Set the prenom value.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
     * Get the dateDebutEnvoi value.
     * @return the dateDebutEnvoi
     */
    public String getDateDebutEnvoi() {
        return dateDebutEnvoi;
    }

    /**
     * Set the dateDebutEnvoi value.
     * @param dateDebutEnvoi the dateDebutEnvoi to set
     */
    public void setDateDebutEnvoi(String dateDebutEnvoi) {
        this.dateDebutEnvoi = dateDebutEnvoi;
    }

    /**
     * Get the dateFinEnvoi value.
     * @return the dateFinEnvoi
     */
    public String getDateFinEnvoi() {
        return dateFinEnvoi;
    }

    /**
     * Set the dateFinEnvoi value.
     * @param dateFinEnvoi the dateFinEnvoi to set
     */
    public void setDateFinEnvoi(String dateFinEnvoi) {
        this.dateFinEnvoi = dateFinEnvoi;
    }

    /**
     * Get the rechercheFullText value.
     * @return the rechercheFullText
     */
    public String getRechercheFullText() {
        return rechercheFullText;
    }

    /**
     * Set the rechercheFullText value.
     * @param rechercheFullText the rechercheFullText to set
     */
    public void setRechercheFullText(String rechercheFullText) {
        this.rechercheFullText = rechercheFullText;
    }

    /**
     * Get the idEtat value.
     * @return the idEtat
     */
    public Long getIdEtat() {
        return idEtat;
    }

    /**
     * Set the idEtat value.
     * @param idEtat the idEtat to set
     */
    public void setIdEtat(Long idEtat) {
        this.idEtat = idEtat;
    }

    /**
     * Get the idService value.
     * @return the idService
     */
    public Long getIdService() {
        return idService;
    }

    /**
     * Set the idService value.
     * @param idService the idService to set
     */
    public void setIdService(Long idService) {
        this.idService = idService;
    }

    /**
     * Get the idUtilisateur value.
     * @return the idUtilisateur
     */
    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * Set the idUtilisateur value.
     * @param idUtilisateur the idUtilisateur to set
     */
    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    /**
     * Get the ordreAscendant value.
     * @return the ordreAscendant
     */
    public Boolean getOrdreAscendant() {
        return ordreAscendant;
    }

    /**
     * Set the ordreAscendant value.
     * @param ordreAscendant the ordreAscendant to set
     */
    public void setOrdreAscendant(Boolean ordreAscendant) {
        this.ordreAscendant = ordreAscendant;
    }

    /**
     * Get the ordreColonne value.
     * @return the ordreColonne
     */
    public String getOrdreColonne() {
        return ordreColonne;
    }

    /**
     * Set the ordreColonne value.
     * @param ordreColonne the ordreColonne to set
     */
    public void setOrdreColonne(String ordreColonne) {
        this.ordreColonne = ordreColonne;
    }

    /**
     * Get the pagination value.
     * @return the pagination
     */
    public PagingModel getPagination() {
        return pagination;
    }

    /**
     * Set the pagination value.
     * @param pagination the pagination to set
     */
    public void setPagination(PagingModel pagination) {
        this.pagination = pagination;
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
     * Get the isArchive value.
     * @return the isArchive
     */
    public Boolean getIsArchive() {
        return isArchive;
    }

    /**
     * Set the isArchive value.
     * @param isArchive the isArchive to set
     */
    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
    }

    /**
     * Get the isRechercheFullText value.
     * @return the isRechercheFullText
     */
    public Boolean getIsRechercheFullText() {
        return isRechercheFullText;
    }

    /**
     * Set the isRechercheFullText value.
     * @param isRechercheFullText the isRechercheFullText to set
     */
    public void setIsRechercheFullText(Boolean isRechercheFullText) {
        this.isRechercheFullText = isRechercheFullText;
    }
}

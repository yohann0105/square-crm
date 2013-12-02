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
 * Model permettant de centraliser les constantes nécessaires à l'application.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ConstantesModel implements IsSerializable {

    private Long idEtatNouveau;

    private Long idEtatArchive;

    private Long idEtatVerrouille;

    /** Constante pour la propriété "Nom". */
    private String constanteProprieteNom;

    /** Constante pour la propriété "Prénom". */
    private String constanteProprietePrenom;

    /** Constante pour la propriété "Numéro d'adhérent". */
    private String constanteProprieteNumeroAdherent;

    /** Constante pour la propriété "Sujet". */
    private String constanteProprieteSujet;

    /** Constante pour la propriété "Date d'envoi". */
    private String constanteProprieteDateEnvoi;

    /** Constante pour la propriété "Utilisateur". */
    private String constanteProprieteUtilisateur;

    /** Constante pour la propriété "Service". */
    private String constanteProprieteService;

    /** Constante pour la propriété "Etat". */
    private String constanteProprieteEtat;

    /** Constante pour la propriété "Nom" des utilisateurs. */
    private String constanteProprieteNomUtilisateur;

    /**
     * Get the constanteProprieteNom value.
     * @return the constanteProprieteNom
     */
    public String getConstanteProprieteNom() {
        return constanteProprieteNom;
    }

    /**
     * Set the constanteProprieteNom value.
     * @param constanteProprieteNom the constanteProprieteNom to set
     */
    public void setConstanteProprieteNom(String constanteProprieteNom) {
        this.constanteProprieteNom = constanteProprieteNom;
    }

    /**
     * Get the constanteProprietePrenom value.
     * @return the constanteProprietePrenom
     */
    public String getConstanteProprietePrenom() {
        return constanteProprietePrenom;
    }

    /**
     * Set the constanteProprietePrenom value.
     * @param constanteProprietePrenom the constanteProprietePrenom to set
     */
    public void setConstanteProprietePrenom(String constanteProprietePrenom) {
        this.constanteProprietePrenom = constanteProprietePrenom;
    }

    /**
     * Get the constanteProprieteNumeroAdherent value.
     * @return the constanteProprieteNumeroAdherent
     */
    public String getConstanteProprieteNumeroAdherent() {
        return constanteProprieteNumeroAdherent;
    }

    /**
     * Set the constanteProprieteNumeroAdherent value.
     * @param constanteProprieteNumeroAdherent the constanteProprieteNumeroAdherent to set
     */
    public void setConstanteProprieteNumeroAdherent(String constanteProprieteNumeroAdherent) {
        this.constanteProprieteNumeroAdherent = constanteProprieteNumeroAdherent;
    }

    /**
     * Get the constanteProprieteSujet value.
     * @return the constanteProprieteSujet
     */
    public String getConstanteProprieteSujet() {
        return constanteProprieteSujet;
    }

    /**
     * Set the constanteProprieteSujet value.
     * @param constanteProprieteSujet the constanteProprieteSujet to set
     */
    public void setConstanteProprieteSujet(String constanteProprieteSujet) {
        this.constanteProprieteSujet = constanteProprieteSujet;
    }

    /**
     * Get the constanteProprieteDateEnvoi value.
     * @return the constanteProprieteDateEnvoi
     */
    public String getConstanteProprieteDateEnvoi() {
        return constanteProprieteDateEnvoi;
    }

    /**
     * Set the constanteProprieteDateEnvoi value.
     * @param constanteProprieteDateEnvoi the constanteProprieteDateEnvoi to set
     */
    public void setConstanteProprieteDateEnvoi(String constanteProprieteDateEnvoi) {
        this.constanteProprieteDateEnvoi = constanteProprieteDateEnvoi;
    }

    /**
     * Get the constanteProprieteUtilisateur value.
     * @return the constanteProprieteUtilisateur
     */
    public String getConstanteProprieteUtilisateur() {
        return constanteProprieteUtilisateur;
    }

    /**
     * Set the constanteProprieteUtilisateur value.
     * @param constanteProprieteUtilisateur the constanteProprieteUtilisateur to set
     */
    public void setConstanteProprieteUtilisateur(String constanteProprieteUtilisateur) {
        this.constanteProprieteUtilisateur = constanteProprieteUtilisateur;
    }

    /**
     * Get the constanteProprieteService value.
     * @return the constanteProprieteService
     */
    public String getConstanteProprieteService() {
        return constanteProprieteService;
    }

    /**
     * Set the constanteProprieteService value.
     * @param constanteProprieteService the constanteProprieteService to set
     */
    public void setConstanteProprieteService(String constanteProprieteService) {
        this.constanteProprieteService = constanteProprieteService;
    }

    /**
     * Get the constanteProprieteEtat value.
     * @return the constanteProprieteEtat
     */
    public String getConstanteProprieteEtat() {
        return constanteProprieteEtat;
    }

    /**
     * Set the constanteProprieteEtat value.
     * @param constanteProprieteEtat the constanteProprieteEtat to set
     */
    public void setConstanteProprieteEtat(String constanteProprieteEtat) {
        this.constanteProprieteEtat = constanteProprieteEtat;
    }

    /**
     * Get the idEtatArchive value.
     * @return the idEtatArchive
     */
    public Long getIdEtatArchive() {
        return idEtatArchive;
    }

    /**
     * Set the idEtatArchive value.
     * @param idEtatArchive the idEtatArchive to set
     */
    public void setIdEtatArchive(Long idEtatArchive) {
        this.idEtatArchive = idEtatArchive;
    }

    /**
     * Get the idEtatNouveau value.
     * @return the idEtatNouveau
     */
    public Long getIdEtatNouveau() {
        return idEtatNouveau;
    }

    /**
     * Set the idEtatNouveau value.
     * @param idEtatNouveau the idEtatNouveau to set
     */
    public void setIdEtatNouveau(Long idEtatNouveau) {
        this.idEtatNouveau = idEtatNouveau;
    }

    /**
     * Get the idEtatVerrouille value.
     * @return the idEtatVerrouille
     */
    public Long getIdEtatVerrouille() {
        return idEtatVerrouille;
    }

    /**
     * Set the idEtatVerrouille value.
     * @param idEtatVerrouille the idEtatVerrouille to set
     */
    public void setIdEtatVerrouille(Long idEtatVerrouille) {
        this.idEtatVerrouille = idEtatVerrouille;
    }

    /**
     * Get the constanteProprieteNomUtilisateur value.
     * @return the constanteProprieteNomUtilisateur
     */
    public String getConstanteProprieteNomUtilisateur() {
        return constanteProprieteNomUtilisateur;
    }

    /**
     * Set the constanteProprieteNomUtilisateur value.
     * @param constanteProprieteNomUtilisateur the constanteProprieteNomUtilisateur to set
     */
    public void setConstanteProprieteNomUtilisateur(String constanteProprieteNomUtilisateur) {
        this.constanteProprieteNomUtilisateur = constanteProprieteNomUtilisateur;
    }
}

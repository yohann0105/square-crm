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
package com.square.core.model.dto;

import java.io.Serializable;

/**
 * Dto contenant les informations d'une opportunite.
 * @author cblanchard - SCUB
 */
public class OpportuniteDto implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -1038506277144057825L;

    /** Identifiant de l'opportunité. */
    private Long idOpportunite;

    /** Identifiant externe de l'opportunité. */
    private String eidOpportunite;

    /** Identifiant de l'action source. */
    private Long idActionSource;

    /** Identifiant de la personne physique. */
    private Long idPersonnePhysique;

    /** Identifiant de la nature de l'action source. */
    private Long idNature;

    /** Identifiant de la campagne de l'action source. */
    private Long idCampagne;

    /** Identifiant du créateur (optionnel pour la création). */
    private Long idCreateur;

    /** Identifiant de l'agence. */
    private Long idAgence;

    /** Identifiant de la ressource. */
    private Long idRessource;

    /** Identifiant du type de l'action source. */
    private Long idType;

    /** Identifiant de l'objet de l'action source. */
    private Long idObjet;

    /** Identifiant du sous-objet de l'action source. */
    private Long idSousObjet;

    /** Indique si l'on force la création de l'opportunité. */
    private Boolean creationForcee;

    /** Indique si l'on ne souhaite pas la création d'une action de relance. */
    private Boolean pasDeRelance;

    /** Indique si l'opportunité est créée depuis le site web. */
    private Boolean isFromSiteWeb = false;

    /**
     * Renvoi la valeur de idOpportunite.
     * @return idOpportunite
     */
    public Long getIdOpportunite() {
        return idOpportunite;
    }

    /**
     * Modifie idOpportunite.
     * @param idOpportunite la nouvelle valeur de idOpportunite
     */
    public void setIdOpportunite(Long idOpportunite) {
        this.idOpportunite = idOpportunite;
    }

    /**
     * Renvoi la valeur de idActionSource.
     * @return idActionSource
     */
    public Long getIdActionSource() {
        return idActionSource;
    }

    /**
     * Modifie idActionSource.
     * @param idActionSource la nouvelle valeur de idActionSource
     */
    public void setIdActionSource(Long idActionSource) {
        this.idActionSource = idActionSource;
    }

    /**
     * Renvoi la valeur de idPersonnePhysique.
     * @return idPersonnePhysique
     */
    public Long getIdPersonnePhysique() {
        return idPersonnePhysique;
    }

    /**
     * Modifie idPersonnePhysique.
     * @param idPersonnePhysique la nouvelle valeur de idPersonnePhysique
     */
    public void setIdPersonnePhysique(Long idPersonnePhysique) {
        this.idPersonnePhysique = idPersonnePhysique;
    }

    /**
     * Renvoi la valeur de idNature.
     * @return idNature
     */
    public Long getIdNature() {
        return idNature;
    }

    /**
     * Modifie idNature.
     * @param idNature la nouvelle valeur de idNature
     */
    public void setIdNature(Long idNature) {
        this.idNature = idNature;
    }

    /**
     * Renvoi la valeur de idCampagne.
     * @return idCampagne
     */
    public Long getIdCampagne() {
        return idCampagne;
    }

    /**
     * Modifie idCampagne.
     * @param idCampagne la nouvelle valeur de idCampagne
     */
    public void setIdCampagne(Long idCampagne) {
        this.idCampagne = idCampagne;
    }

    /**
     * Renvoi la valeur de idAgence.
     * @return idAgence
     */
    public Long getIdAgence() {
        return idAgence;
    }

    /**
     * Modifie idAgence.
     * @param idAgence la nouvelle valeur de idAgence
     */
    public void setIdAgence(Long idAgence) {
        this.idAgence = idAgence;
    }

    /**
     * Renvoi la valeur de idRessource.
     * @return idRessource
     */
    public Long getIdRessource() {
        return idRessource;
    }

    /**
     * Modifie idRessource.
     * @param idRessource la nouvelle valeur de idRessource
     */
    public void setIdRessource(Long idRessource) {
        this.idRessource = idRessource;
    }

    /**
     * Renvoi la valeur de idType.
     * @return idType
     */
    public Long getIdType() {
        return idType;
    }

    /**
     * Modifie idType.
     * @param idType la nouvelle valeur de idType
     */
    public void setIdType(Long idType) {
        this.idType = idType;
    }

    /**
     * Renvoi la valeur de idObjet.
     * @return idObjet
     */
    public Long getIdObjet() {
        return idObjet;
    }

    /**
     * Modifie idObjet.
     * @param idObjet la nouvelle valeur de idObjet
     */
    public void setIdObjet(Long idObjet) {
        this.idObjet = idObjet;
    }

    /**
     * Renvoi la valeur de idSousObjet.
     * @return idSousObjet
     */
    public Long getIdSousObjet() {
        return idSousObjet;
    }

    /**
     * Modifie idSousObjet.
     * @param idSousObjet la nouvelle valeur de idSousObjet
     */
    public void setIdSousObjet(Long idSousObjet) {
        this.idSousObjet = idSousObjet;
    }

    /**
     * Renvoi la valeur de creationForcee.
     * @return creationForcee
     */
    public Boolean getCreationForcee() {
        return creationForcee;
    }

    /**
     * Modifie creationForcee.
     * @param creationForcee la nouvelle valeur de creationForcee
     */
    public void setCreationForcee(Boolean creationForcee) {
        this.creationForcee = creationForcee;
    }

    /**
     * Renvoi la valeur de eidOpportunite.
     * @return eidOpportunite
     */
    public String getEidOpportunite() {
        return eidOpportunite;
    }

    /**
     * Modifie eidOpportunite.
     * @param eidOpportunite la nouvelle valeur de eidOpportunite
     */
    public void setEidOpportunite(String eidOpportunite) {
        this.eidOpportunite = eidOpportunite;
    }

    /**
     * Récupération de pasDeRelance.
     * @return the pasDeRelance
     */
    public Boolean getPasDeRelance() {
        return pasDeRelance;
    }

    /**
     * Définition de pasDeRelance.
     * @param pasDeRelance the pasDeRelance to set
     */
    public void setPasDeRelance(Boolean pasDeRelance) {
        this.pasDeRelance = pasDeRelance;
    }

    /**
     * Récupère la valeur de idCreateur.
     * @return la valeur de idCreateur
     */
    public Long getIdCreateur() {
        return idCreateur;
    }

    /**
     * Définit la valeur de idCreateur.
     * @param idCreateur la nouvelle valeur de idCreateur
     */
    public void setIdCreateur(Long idCreateur) {
        this.idCreateur = idCreateur;
    }

    /**
     * Récupère la valeur de isFromSiteWeb.
     * @return la valeur de isFromSiteWeb
     */
    public Boolean getIsFromSiteWeb() {
        return isFromSiteWeb;
    }

    /**
     * Définit la valeur de isFromSiteWeb.
     * @param isFromSiteWeb la nouvelle valeur de isFromSiteWeb
     */
    public void setIsFromSiteWeb(Boolean isFromSiteWeb) {
        this.isFromSiteWeb = isFromSiteWeb;
    }

}

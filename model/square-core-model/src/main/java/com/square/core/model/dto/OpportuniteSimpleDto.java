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
import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto pour les informations "résumé" d'une opportunité.
 * @author cblanchard - SCUB
 */
public class OpportuniteSimpleDto implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -7181120203878853969L;

    /** Identifiant de l'opportunité. */
    private Long idOpportunite;

    /** Personne physique. */
    private Long idPersonnePhysique;

    /** Eid de l'opportunité. */
    private String eidOpportunite;

    /** Créateur. */
    private IdentifiantEidLibelleDto createur;

    /** Date de création. */
    private Calendar dateCreation;

    /** Ressource à laquel l'opportunité est attribuée. */
    private DimensionRessourceDto ressource;

    /** L'agence à laquelle l'opportunité est attribué. */
    private IdentifiantEidLibelleDto agence;

    /** Type. */
    private String type;

    /** Objet. */
    private String objet;

    /** Sous objet. */
    private String sousObjet;

    /** Statut. */
    private IdentifiantLibelleDto statut;

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
     * Renvoi la valeur de createur.
     * @return createur
     */
    public IdentifiantEidLibelleDto getCreateur() {
        return createur;
    }

    /**
     * Modifie createur.
     * @param createur la nouvelle valeur de createur
     */
    public void setCreateur(IdentifiantEidLibelleDto createur) {
        this.createur = createur;
    }

    /**
     * Renvoi la valeur de dateCreation.
     * @return dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Modifie dateCreation.
     * @param dateCreation la nouvelle valeur de dateCreation
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Renvoi la valeur de type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Modifie type.
     * @param type la nouvelle valeur de type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Renvoi la valeur de objet.
     * @return objet
     */
    public String getObjet() {
        return objet;
    }

    /**
     * Modifie objet.
     * @param objet la nouvelle valeur de objet
     */
    public void setObjet(String objet) {
        this.objet = objet;
    }

    /**
     * Renvoi la valeur de sousObjet.
     * @return sousObjet
     */
    public String getSousObjet() {
        return sousObjet;
    }

    /**
     * Modifie sousObjet.
     * @param sousObjet la nouvelle valeur de sousObjet
     */
    public void setSousObjet(String sousObjet) {
        this.sousObjet = sousObjet;
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
     * Renvoi la valeur de ressource.
     * @return ressource
     */
    public DimensionRessourceDto getRessource() {
        return ressource;
    }

    /**
     * Modifie ressource.
     * @param ressource la nouvelle valeur de ressource
     */
    public void setRessource(DimensionRessourceDto ressource) {
        this.ressource = ressource;
    }

    /**
     * Renvoi la valeur de agence.
     * @return agence
     */
    public IdentifiantEidLibelleDto getAgence() {
        return agence;
    }

    /**
     * Modifie agence.
     * @param agence la nouvelle valeur de agence
     */
    public void setAgence(IdentifiantEidLibelleDto agence) {
        this.agence = agence;
    }

    /**
     * Renvoi la valeur de statut.
     * @return statut
     */
    public IdentifiantLibelleDto getStatut() {
        return statut;
    }

    /**
     * Modifie statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(IdentifiantLibelleDto statut) {
        this.statut = statut;
    }

}

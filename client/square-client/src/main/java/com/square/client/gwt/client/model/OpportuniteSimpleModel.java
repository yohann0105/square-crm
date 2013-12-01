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
package com.square.client.gwt.client.model;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model pour les informations "résumé" d'une opportunité.
 * @author cblanchard - SCUB
 */
public class OpportuniteSimpleModel implements IsSerializable {
    /** Identifiant de l'opportunité. */
    private Long idOpportunite;

    /** Personne physique. */
    private Long idPersonnePhysique;

    /** Eid de l'opportunité. */
    private String eidOpportunite;

    /** Créateur. */
    private IdentifiantEidLibelleModel createur;

    /** Date de création. */
    private String dateCreation;

    /** Ressource à laquel l'opportunité est attribuée. */
    private DimensionRessourceModel ressource;

    /** L'agence à laquelle l'opportunité est attribué. */
    private IdentifiantEidLibelleModel agence;

    /** Type. */
    private String type;

    /** Objet. */
    private String objet;

    /** Sous objet. */
    private String sousObjet;

    /** Statut. */
    private IdentifiantLibelleGwt statut;

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
     * Renvoi la valeur de createur.
     * @return createur
     */
    public IdentifiantEidLibelleModel getCreateur() {
        return createur;
    }

    /**
     * Modifie createur.
     * @param createur la nouvelle valeur de createur
     */
    public void setCreateur(IdentifiantEidLibelleModel createur) {
        this.createur = createur;
    }

    /**
     * Renvoi la valeur de dateCreation.
     * @return dateCreation
     */
    public String getDateCreation() {
        return dateCreation;
    }

    /**
     * Modifie dateCreation.
     * @param dateCreation la nouvelle valeur de dateCreation
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Renvoi la valeur de ressource.
     * @return ressource
     */
    public DimensionRessourceModel getRessource() {
        return ressource;
    }

    /**
     * Modifie ressource.
     * @param ressource la nouvelle valeur de ressource
     */
    public void setRessource(DimensionRessourceModel ressource) {
        this.ressource = ressource;
    }

    /**
     * Renvoi la valeur de agence.
     * @return agence
     */
    public IdentifiantEidLibelleModel getAgence() {
        return agence;
    }

    /**
     * Modifie agence.
     * @param agence la nouvelle valeur de agence
     */
    public void setAgence(IdentifiantEidLibelleModel agence) {
        this.agence = agence;
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
     * Renvoi la valeur de statut.
     * @return statut
     */
    public IdentifiantLibelleGwt getStatut() {
        return statut;
    }

    /**
     * Modifie statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(IdentifiantLibelleGwt statut) {
        this.statut = statut;
    }

}

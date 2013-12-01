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
 * Objet contenant le résultat de la recherche.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionRechercheModel implements IsSerializable {
    /**
     * identifiant.
     */
    private Long id;

    /**
     * L'identifiant du commerciale.
     */
    private Long idpersonne;

    /**
     * Date de réalisation de l'action.
     */
    private String dateAction;

    /**
     * L'heure de l'action.
     */
    private String heureAction;

    /**
     * Date de création de l'action.
     */
    private String dateCreation;

    /**
     * Date de fin de l'action.
     */
    private String dateTerminee;

    /**
     * Le type d'action.
     */
    private String type;

    /**
     * Objet de l'action.
     */
    private String objet;

    /**
     * Sous objet de l'action.
     */
    private String sousObjet;

    /**
     * La priorité de l'action.
     */
    private String priorite;

    /**
     * Le statut de l'action.
     */
    private String statut;

    /** Agence. */
    private IdentifiantLibelleGwt agence;

    /** Commercial. */
    private DimensionRessourceModel commercial;

    /**
     * Retourne la valeur de idpersonne.
     * @return the idpersonne
     */
    public Long getIdpersonne() {
        return idpersonne;
    }

    /**
     * Modifie la valeur de idpersonne.
     * @param idpersonne the idpersonne to set
     */
    public void setIdpersonne(Long idpersonne) {
        this.idpersonne = idpersonne;
    }

    /**
     * Retourne la valeur de dateAction.
     * @return the dateAction
     */
    public String getDateAction() {
        return dateAction;
    }

    /**
     * Modifie la valeur de dateAction.
     * @param dateAction the dateAction to set
     */
    public void setDateAction(String dateAction) {
        this.dateAction = dateAction;
    }

    /**
     * Retourne la valeur de type.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Modifie la valeur de type.
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retourne la valeur de objet.
     * @return the objet
     */
    public String getObjet() {
        return objet;
    }

    /**
     * Modifie la valeur de objet.
     * @param objet the objet to set
     */
    public void setObjet(String objet) {
        this.objet = objet;
    }

    /**
     * Retourne la valeur de sousObjet.
     * @return the sousObjet
     */
    public String getSousObjet() {
        return sousObjet;
    }

    /**
     * Modifie la valeur de sousObjet.
     * @param sousObjet the sousObjet to set
     */
    public void setSousObjet(String sousObjet) {
        this.sousObjet = sousObjet;
    }

    /**
     * Retourne la valeur de priorite.
     * @return the priorite
     */
    public String getPriorite() {
        return priorite;
    }

    /**
     * Modifie la valeur de priorite.
     * @param priorite the priorite to set
     */
    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    /**
     * Retourne la valeur de statut.
     * @return the statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     * Modifie la valeur de statut.
     * @param statut the statut to set
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * Revoi la valeur de agence.
     * @return agence
     */
    public IdentifiantLibelleGwt getAgence() {
        return agence;
    }

    /**
     * Modifie agence.
     * @param agence dans agence
     */
    public void setAgence(IdentifiantLibelleGwt agence) {
        this.agence = agence;
    }

    /**
     * Renvoi la valeur de commercial.
     * @return commercial
     */
    public DimensionRessourceModel getCommercial() {
        return commercial;
    }

    /**
     * Modifie commercial.
     * @param commercial la nouvelle valeur de commercial
     */
    public void setCommercial(DimensionRessourceModel commercial) {
        this.commercial = commercial;
    }

    /**
     * Get the param value.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the param value.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne la valeur de dateCreation.
     * @return the dateCreation
     */
    public String getDateCreation() {
        return dateCreation;
    }

    /**
     * Modifie la valeur de dateCreation.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Retourne la valeur de dateTerminee.
     * @return the dateTerminee
     */
    public String getDateTerminee() {
        return dateTerminee;
    }

    /**
     * Modifie la valeur de dateTerminee.
     * @param dateTerminee the dateTerminee to set
     */
    public void setDateTerminee(String dateTerminee) {
        this.dateTerminee = dateTerminee;
    }

    /**
     * Récupère la valeur de heureAction.
     * @return la valeur de heureAction
     */
    public String getHeureAction() {
        return heureAction;
    }

    /**
     * Définit la valeur de heureAction.
     * @param heureAction la nouvelle valeur de heureAction
     */
    public void setHeureAction(String heureAction) {
        this.heureAction = heureAction;
    }
}

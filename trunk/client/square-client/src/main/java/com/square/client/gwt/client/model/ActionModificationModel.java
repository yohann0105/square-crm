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
 * Modèle pour les informations de modifications des actions.
 * @author cblanchard - SCUB
 */
public class ActionModificationModel implements IsSerializable {

    /** Identifiant de l'action. */
    private Long idAction;

    /** Nature de l'action. */
    private IdentifiantLibelleGwt natureAction;

    /** Statut. */
    private IdentifiantLibelleGwt statut;

    /** Resultat. */
    private IdentifiantLibelleGwt resultat;

    /** Type. */
    private IdentifiantLibelleGwt type;

    /** Objet. */
    private IdentifiantLibelleGwt objet;

    /** Sous objet. */
    private IdentifiantLibelleGwt sousObjet;

    /** Campagne. */
    private IdentifiantLibelleGwt campagne;

    /** Commentaire. */
    private HistoriqueCommentaireModel commentaire;

    /** Demande de rappel. */
    private Boolean rappel;

    /** Identifiant de la notification. */
    private Long idNotification;

    /** Rappel par mail. */
    private Boolean rappelMail;

    /** La date d'action. */
    private String dateAction;

    /** L'heure de l'action. */
    private String heureAction;

    /** Résultat de la nature de l'action. */
    private IdentifiantLibelleGwt natureResultat;

    /** Ressource. */
    private DimensionRessourceModel ressource;

    /** Agence. */
    private IdentifiantLibelleGwt agence;

    /** Priorité de l'action. */
    private IdentifiantLibelleGwt priorite;

    /** Reclamation. */
    private Boolean reclamation;

    /**
     * Flag permettant de vérifier la règle de gestion sur une opportunité ouverte. <br/>
     * On ne peut pas terminer une action de type "relance" si elle est associée à une opportunité en cours.
     */
    private Boolean verifierRegleGestionOpportuniteEnCours = Boolean.TRUE;

    /** Durée de l'action. */
    private IdentifiantLibelleGwt duree;

    /** Indique si l'action est visible dans l'agenda. */
    private Boolean visibleAgenda;

    /**
     * Renvoi la valeur de idAction.
     * @return idAction
     */
    public Long getIdAction() {
        return idAction;
    }

    /**
     * Modifie idAction.
     * @param idAction la nouvelle valeur de idAction
     */
    public void setIdAction(Long idAction) {
        this.idAction = idAction;
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

    /**
     * Renvoi la valeur de resultat.
     * @return resultat
     */
    public IdentifiantLibelleGwt getResultat() {
        return resultat;
    }

    /**
     * Modifie resultat.
     * @param resultat la nouvelle valeur de resultat
     */
    public void setResultat(IdentifiantLibelleGwt resultat) {
        this.resultat = resultat;
    }

    /**
     * Renvoi la valeur de commentaire.
     * @return commentaire
     */
    public HistoriqueCommentaireModel getCommentaire() {
        return commentaire;
    }

    /**
     * Modifie commentaire.
     * @param commentaire la nouvelle valeur de commentaire
     */
    public void setCommentaire(HistoriqueCommentaireModel commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Renvoi la valeur de rappel.
     * @return rappel
     */
    public Boolean getRappel() {
        return rappel;
    }

    /**
     * Modifie rappel.
     * @param rappel la nouvelle valeur de rappel
     */
    public void setRappel(Boolean rappel) {
        this.rappel = rappel;
    }

    /**
     * Renvoi la valeur de idNotification.
     * @return idNotification
     */
    public Long getIdNotification() {
        return idNotification;
    }

    /**
     * Modifie idNotification.
     * @param idNotification la nouvelle valeur de idNotification
     */
    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    /**
     * Renvoi la valeur de rappelMail.
     * @return rappelMail
     */
    public Boolean getRappelMail() {
        return rappelMail;
    }

    /**
     * Modifie rappelMail.
     * @param rappelMail la nouvelle valeur de rappelMail
     */
    public void setRappelMail(Boolean rappelMail) {
        this.rappelMail = rappelMail;
    }

    /**
     * Renvoi la valeur de dateAction.
     * @return dateAction
     */
    public String getDateAction() {
        return dateAction;
    }

    /**
     * Modifie dateAction.
     * @param dateAction la nouvelle valeur de dateAction
     */
    public void setDateAction(String dateAction) {
        this.dateAction = dateAction;
    }

    /**
     * Renvoi la valeur de heureAction.
     * @return heureAction
     */
    public String getHeureAction() {
        return heureAction;
    }

    /**
     * Modifie heureAction.
     * @param heureAction la nouvelle valeur de heureAction
     */
    public void setHeureAction(String heureAction) {
        this.heureAction = heureAction;
    }

    /**
     * Récupère la valeur de natureResultat.
     * @return la valeur de natureResultat
     */
    public IdentifiantLibelleGwt getNatureResultat() {
        return natureResultat;
    }

    /**
     * Définit la valeur de natureResultat.
     * @param natureResultat la nouvelle valeur de natureResultat
     */
    public void setNatureResultat(IdentifiantLibelleGwt natureResultat) {
        this.natureResultat = natureResultat;
    }

    /**
     * Getter function.
     * @return the natureAction
     */
    public IdentifiantLibelleGwt getNatureAction() {
        return natureAction;
    }

    /**
     * Setter function.
     * @param natureAction the natureAction to set
     */
    public void setNatureAction(IdentifiantLibelleGwt natureAction) {
        this.natureAction = natureAction;
    }

    /**
     * Récupère la valeur de verifierRegleGestionOpportuniteEnCours.
     * @return la valeur de verifierRegleGestionOpportuniteEnCours
     */
    public Boolean getVerifierRegleGestionOpportuniteEnCours() {
        return verifierRegleGestionOpportuniteEnCours;
    }

    /**
     * Définit la valeur de verifierRegleGestionOpportuniteEnCours.
     * @param verifierRegleGestionOpportuniteEnCours la nouvelle valeur de verifierRegleGestionOpportuniteEnCours
     */
    public void setVerifierRegleGestionOpportuniteEnCours(Boolean verifierRegleGestionOpportuniteEnCours) {
        this.verifierRegleGestionOpportuniteEnCours = verifierRegleGestionOpportuniteEnCours;
    }

    /**
     * Récupère la valeur de priorite.
     * @return la valeur de priorite
     */
    public IdentifiantLibelleGwt getPriorite() {
        return priorite;
    }

    /**
     * Définit la valeur de priorite.
     * @param priorite la nouvelle valeur de priorite
     */
    public void setPriorite(IdentifiantLibelleGwt priorite) {
        this.priorite = priorite;
    }

    /**
     * Get the value of type.
     * @return the type
     */
    public IdentifiantLibelleGwt getType() {
        return type;
    }

    /**
     * Set the value of type.
     * @param type the type to set
     */
    public void setType(IdentifiantLibelleGwt type) {
        this.type = type;
    }

    /**
     * Get the value of objet.
     * @return the objet
     */
    public IdentifiantLibelleGwt getObjet() {
        return objet;
    }

    /**
     * Set the value of objet.
     * @param objet the objet to set
     */
    public void setObjet(IdentifiantLibelleGwt objet) {
        this.objet = objet;
    }

    /**
     * Get the value of sousObjet.
     * @return the sousObjet
     */
    public IdentifiantLibelleGwt getSousObjet() {
        return sousObjet;
    }

    /**
     * Set the value of sousObjet.
     * @param sousObjet the sousObjet to set
     */
    public void setSousObjet(IdentifiantLibelleGwt sousObjet) {
        this.sousObjet = sousObjet;
    }

    /**
     * Get the value of campagne.
     * @return the campagne
     */
    public IdentifiantLibelleGwt getCampagne() {
        return campagne;
    }

    /**
     * Set the value of campagne.
     * @param campagne the campagne to set
     */
    public void setCampagne(IdentifiantLibelleGwt campagne) {
        this.campagne = campagne;
    }

    /**
     * Get the value of ressource.
     * @return the ressource
     */
    public DimensionRessourceModel getRessource() {
        return ressource;
    }

    /**
     * Set the value of ressource.
     * @param ressource the ressource to set
     */
    public void setRessource(DimensionRessourceModel ressource) {
        this.ressource = ressource;
    }

    /**
     * Get the value of agence.
     * @return the agence
     */
    public IdentifiantLibelleGwt getAgence() {
        return agence;
    }

    /**
     * Set the value of agence.
     * @param agence the agence to set
     */
    public void setAgence(IdentifiantLibelleGwt agence) {
        this.agence = agence;
    }

    /**
     * Get the value of reclamation.
     * @return the reclamation
     */
    public Boolean getReclamation() {
        return reclamation;
    }

    /**
     * Set the value of reclamation.
     * @param reclamation the reclamation to set
     */
    public void setReclamation(Boolean reclamation) {
        this.reclamation = reclamation;
    }

    /**
     * Récupère la valeur de duree.
     * @return la valeur de duree
     */
    public IdentifiantLibelleGwt getDuree() {
        return duree;
    }

    /**
     * Définit la valeur de duree.
     * @param duree la nouvelle valeur de duree
     */
    public void setDuree(IdentifiantLibelleGwt duree) {
        this.duree = duree;
    }

    /**
     * Get the value of visibleAgenda.
     * @return the visibleAgenda
     */
    public Boolean getVisibleAgenda() {
        return visibleAgenda;
    }

    /**
     * Set the value of visibleAgenda.
     * @param visibleAgenda the visibleAgenda to set
     */
    public void setVisibleAgenda(Boolean visibleAgenda) {
        this.visibleAgenda = visibleAgenda;
    }

}

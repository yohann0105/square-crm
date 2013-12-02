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
 * Dto pour les informations de modifications des actions.
 * @author cblanchard - SCUB
 */
public class ActionModificationDto implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -9182444124702530135L;

    /** Identifiant de l'action. */
    private Long idAction;

    /** Nature de l'action. */
    private IdentifiantLibelleDto natureAction;

    /** Statut. */
    private IdentifiantLibelleDto statut;

    /** Resultat. */
    private IdentifiantLibelleDto resultat;

    /** Commentaire. */
    private HistoriqueCommentaireDto commentaire;

    /** Demande de rappel. */
    private Boolean rappel;

    /** Identifiant de la notification. */
    private Long idNotification;

    /** Rappel par mail. */
    private Boolean rappelMail;

    /** Date action. */
    private Calendar dateAction;

    /** Résultat de la nature de l'action. */
    private IdentifiantLibelleDto natureResultat;

    /** Ressource. */
    private DimensionRessourceDto ressource;

    /** Agence. */
    private IdentifiantLibelleDto agence;

    /** Priorité de l'action. */
    private IdentifiantLibelleDto priorite;

    /** Identifiant de la durée. */
    private Long idDuree;

    /** Indique si l'action est visible dans l'agenda. */
    private Boolean visibleAgenda;

    /** Type de l'action. */
    private IdentifiantLibelleDto typeAction;

    /** Objet de l'action. */
    private IdentifiantLibelleDto objetAction;

    /** Affectation au pot commun. */
    private Boolean affectationPotCommun;

    /** Identifiant de la personne connectée, utilisée pour les web services. */
    private Long idUtilisateurConnecte;

    /**
     * Flag permettant de vérifier la règle de gestion sur une opportunité ouverte.
     * <br/>On ne peut pas terminer une action de type "relance" si elle est associée à une opportunité en cours.
     */
    private Boolean verifierRegleGestionOpportuniteEnCours = Boolean.TRUE;

    /** Flag permettant de vérifier le resultat de nature. */
    private Boolean verifierResultatNatureAction = Boolean.TRUE;

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

    /**
     * Renvoi la valeur de resultat.
     * @return resultat
     */
    public IdentifiantLibelleDto getResultat() {
        return resultat;
    }

    /**
     * Modifie resultat.
     * @param resultat la nouvelle valeur de resultat
     */
    public void setResultat(IdentifiantLibelleDto resultat) {
        this.resultat = resultat;
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
     * Renvoi la valeur de commentaire.
     * @return commentaire
     */
    public HistoriqueCommentaireDto getCommentaire() {
        return commentaire;
    }

    /**
     * Modifie commentaire.
     * @param commentaire la nouvelle valeur de commentaire
     */
    public void setCommentaire(HistoriqueCommentaireDto commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Renvoi la valeur de dateAction.
     * @return dateAction
     */
    public Calendar getDateAction() {
        return dateAction;
    }

    /**
     * Modifie dateAction.
     * @param dateAction la nouvelle valeur de dateAction
     */
    public void setDateAction(Calendar dateAction) {
        this.dateAction = dateAction;
    }

    /**
     * Récupère la valeur de natureResultat.
     * @return la valeur de natureResultat
     */
    public IdentifiantLibelleDto getNatureResultat() {
        return natureResultat;
    }

    /**
     * Définit la valeur de natureResultat.
     * @param natureResultat la nouvelle valeur de natureResultat
     */
    public void setNatureResultat(IdentifiantLibelleDto natureResultat) {
        this.natureResultat = natureResultat;
    }

    /**
     * Getter function.
     * @return the natureAction
     */
    public IdentifiantLibelleDto getNatureAction() {
        return natureAction;
    }

    /**
     * Setter function.
     * @param natureAction the natureAction to set
     */
    public void setNatureAction(IdentifiantLibelleDto natureAction) {
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
    public IdentifiantLibelleDto getPriorite() {
        return priorite;
    }

    /**
     * Définit la valeur de priorite.
     * @param priorite la nouvelle valeur de priorite
     */
    public void setPriorite(IdentifiantLibelleDto priorite) {
        this.priorite = priorite;
    }

    /**
     * Get the value of ressource.
     * @return the ressource
     */
    public DimensionRessourceDto getRessource() {
        return ressource;
    }

    /**
     * Set the value of ressource.
     * @param ressource the ressource to set
     */
    public void setRessource(DimensionRessourceDto ressource) {
        this.ressource = ressource;
    }

    /**
     * Get the value of agence.
     * @return the agence
     */
    public IdentifiantLibelleDto getAgence() {
        return agence;
    }

    /**
     * Set the value of agence.
     * @param agence the agence to set
     */
    public void setAgence(IdentifiantLibelleDto agence) {
        this.agence = agence;
    }

    /**
     * Get the value of idDuree.
     * @return the idDuree
     */
    public Long getIdDuree() {
        return idDuree;
    }

    /**
     * Set the value of idDuree.
     * @param idDuree the idDuree to set
     */
    public void setIdDuree(Long idDuree) {
        this.idDuree = idDuree;
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

    /**
     * Get the value of typeAction.
     * @return the typeAction
     */
    public IdentifiantLibelleDto getTypeAction() {
        return typeAction;
    }

    /**
     * Set the value of typeAction.
     * @param typeAction the typeAction to set
     */
    public void setTypeAction(IdentifiantLibelleDto typeAction) {
        this.typeAction = typeAction;
    }

    /**
     * Get the value of objetAction.
     * @return the objetAction
     */
    public IdentifiantLibelleDto getObjetAction() {
        return objetAction;
    }

    /**
     * Set the value of objetAction.
     * @param objetAction the objetAction to set
     */
    public void setObjetAction(IdentifiantLibelleDto objetAction) {
        this.objetAction = objetAction;
    }

    /**
     * Get the value of affectationPotCommun.
     * @return the affectationPotCommun
     */
    public Boolean getAffectationPotCommun() {
        return affectationPotCommun;
    }

    /**
     * Set the value of affectationPotCommun.
     * @param affectationPotCommun the affectationPotCommun to set
     */
    public void setAffectationPotCommun(Boolean affectationPotCommun) {
        this.affectationPotCommun = affectationPotCommun;
    }

    /**
     * Get the value of idUtilisateurConnecte.
     * @return the idUtilisateurConnecte
     */
    public Long getIdUtilisateurConnecte() {
        return idUtilisateurConnecte;
    }

    /**
     * Set the value of idUtilisateurConnecte.
     * @param idUtilisateurConnecte the idUtilisateurConnecte to set
     */
    public void setIdUtilisateurConnecte(Long idUtilisateurConnecte) {
        this.idUtilisateurConnecte = idUtilisateurConnecte;
    }

    /**
     * Get the value of verifierResultatNatureAction.
     * @return the verifierResultatNatureAction
     */
    public Boolean getVerifierResultatNatureAction() {
        return verifierResultatNatureAction;
    }

    /**
     * Set the value of verifierResultatNatureAction.
     * @param verifierResultatNatureAction the verifierResultatNatureAction to set
     */
    public void setVerifierResultatNatureAction(Boolean verifierResultatNatureAction) {
        this.verifierResultatNatureAction = verifierResultatNatureAction;
    }

}

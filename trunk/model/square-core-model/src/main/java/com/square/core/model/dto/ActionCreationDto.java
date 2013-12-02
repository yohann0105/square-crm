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
import java.util.List;

/**
 * Dto pour la création d'une action.
 * @author cblanchard - SCUB
 */
public class ActionCreationDto implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 2048548655045692268L;

    /**
     * L'identifiant de l'action.
     */
    private Long identifiant;

    /**
     * La date d'action.
     */
    private Calendar dateAction;

    /**
     * La date d'affichage de l'action.
     */
    private Calendar dateAffichageAction;

    /**
     * L'identifiant de la personne.
     */
    private Long idPersonne;

    /**
     * L'indentifiant de la nature de l'action.
     */
    private Long idNatureAction;

    /**
     * L'identifiant du type de l'action.
     */
    private Long idType;

    /**
     * L'identifiant de l'objet de l'action.
     */
    private Long idObjet;

    /**
     * L'identifiant du sous objet de l'action.
     */
    private Long idSousObjet;

    /**
     * L'identifiant de la priorité.
     */
    private Long idPriorite;

    /**
     * La reclamation.
     */
    private Boolean reclamation;

    /**
     * Indique si un rappel a été demandé.
     */
    private Boolean rappel;

    /**
     * L'identifiant de la liste de notification de l'action.
     */
    private Long idNotificationList;

    /**
     * Le booleen.
     */
    private Boolean mePrevenirParMail;

    /**
     * La campagne.
     */
    private Long idCampagne;

    /**
     * L'agence.
     */
    private Long idAgence;

    /**
     * Le commercial.
     */
    private Long idCommercial;

    /**
     * Action source.
     */
    private Long idActionSource;

    /**
     * Opportunite liée à l'action.
     */
    private Long idOpportunite;

    /**
     * Statut de l'action.
     */
    private Long idStatut;

    /**
     * Le créateur.
     */
    private Long idCreateur;

    /**
     * Liste des commentaires.
     */
    private List<HistoriqueCommentaireDto> commentaires;

    /**
     * Liste de documents.
     */
    private List<DocumentDto> documents;

    /** Identifiant de la durée. */
    private Long idDuree;

    /** Indique si l'action est visible dans l'agenda. */
    private Boolean visibleAgenda;

    /**
     * Renvoi la valeur de identifiant.
     * @return identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Modifie identifiant.
     * @param identifiant la nouvelle valeur de identifiant
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
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
     * Renvoi la valeur de idPersonne.
     * @return idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Modifie idPersonne.
     * @param idPersonne la nouvelle valeur de idPersonne
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    /**
     * Renvoi la valeur de idNatureAction.
     * @return idNatureAction
     */
    public Long getIdNatureAction() {
        return idNatureAction;
    }

    /**
     * Modifie idNatureAction.
     * @param idNatureAction la nouvelle valeur de idNatureAction
     */
    public void setIdNatureAction(Long idNatureAction) {
        this.idNatureAction = idNatureAction;
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
     * Renvoi la valeur de idPriorite.
     * @return idPriorite
     */
    public Long getIdPriorite() {
        return idPriorite;
    }

    /**
     * Modifie idPriorite.
     * @param idPriorite la nouvelle valeur de idPriorite
     */
    public void setIdPriorite(Long idPriorite) {
        this.idPriorite = idPriorite;
    }

    /**
     * Renvoi la valeur de reclamation.
     * @return reclamation
     */
    public Boolean getReclamation() {
        return reclamation;
    }

    /**
     * Modifie reclamation.
     * @param reclamation la nouvelle valeur de reclamation
     */
    public void setReclamation(Boolean reclamation) {
        this.reclamation = reclamation;
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
     * Renvoi la valeur de idNotificationList.
     * @return idNotificationList
     */
    public Long getIdNotificationList() {
        return idNotificationList;
    }

    /**
     * Modifie idNotificationList.
     * @param idNotificationList la nouvelle valeur de idNotificationList
     */
    public void setIdNotificationList(Long idNotificationList) {
        this.idNotificationList = idNotificationList;
    }

    /**
     * Renvoi la valeur de mePrevenirParMail.
     * @return mePrevenirParMail
     */
    public Boolean getMePrevenirParMail() {
        return mePrevenirParMail;
    }

    /**
     * Modifie mePrevenirParMail.
     * @param mePrevenirParMail la nouvelle valeur de mePrevenirParMail
     */
    public void setMePrevenirParMail(Boolean mePrevenirParMail) {
        this.mePrevenirParMail = mePrevenirParMail;
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
     * Retourne la valeur de idAgence.
     * @return the idAgence
     */
    public Long getIdAgence() {
        return idAgence;
    }

    /**
     * Modifie la valeur de idAgence.
     * @param idAgence the idAgence to set
     */
    public void setIdAgence(Long idAgence) {
        this.idAgence = idAgence;
    }

    /**
     * Retourne la valeur de idCommercial.
     * @return the idCommercial
     */
    public Long getIdCommercial() {
        return idCommercial;
    }

    /**
     * Modifie la valeur de idCommercial.
     * @param idCommercial the idCommercial to set
     */
    public void setIdCommercial(Long idCommercial) {
        this.idCommercial = idCommercial;
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
     * Get the idStatut value.
     * @return the idStatut
     */
    public Long getIdStatut() {
        return idStatut;
    }

    /**
     * Set the idStatut value.
     * @param idStatut the idStatut to set
     */
    public void setIdStatut(Long idStatut) {
        this.idStatut = idStatut;
    }

    /**
     * Get the idCreateur value.
     * @return the idCreateur
     */
    public Long getIdCreateur() {
        return idCreateur;
    }

    /**
     * Set the idCreateur value.
     * @param idCreateur the idCreateur to set
     */
    public void setIdCreateur(Long idCreateur) {
        this.idCreateur = idCreateur;
    }

    /**
     * Get the commentaires value.
     * @return the commentaires
     */
    public List<HistoriqueCommentaireDto> getCommentaires() {
        return commentaires;
    }

    /**
     * Set the commentaires value.
     * @param commentaires the commentaires to set
     */
    public void setCommentaires(List<HistoriqueCommentaireDto> commentaires) {
        this.commentaires = commentaires;
    }

    /**
     * Récupération de documents.
     * @return the documents
     */
    public List<DocumentDto> getDocuments() {
        return documents;
    }

    /**
     * Définition de documents.
     * @param documents the documents to set
     */
    public void setDocuments(List<DocumentDto> documents) {
        this.documents = documents;
    }

    /**
     * Get the dateAffichageAction value.
     * @return the dateAffichageAction
     */
    public Calendar getDateAffichageAction() {
        return dateAffichageAction;
    }

    /**
     * Set the dateAffichageAction value.
     * @param dateAffichageAction the dateAffichageAction to set
     */
    public void setDateAffichageAction(Calendar dateAffichageAction) {
        this.dateAffichageAction = dateAffichageAction;
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

}

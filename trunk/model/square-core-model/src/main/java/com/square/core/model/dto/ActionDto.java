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

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Objet contenant des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionDto implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -6460988843471230189L;

    /**
     * L'identifiant de l'action.
     */
    private Long identifiant;

    /**
     * Identifiant Externe.
     */
    private String identifiantExterieur;

    /**
     * Statut de l'action.
     */
    private IdentifiantLibelleDto statut;

    /**
     * La date d'action.
     */
    private Calendar dateAction;

    /**
     * La date d'affichage de l'action.
     */
    private Calendar dateAffichage;

    /**
     * Date à laquelle l'action a été terminée.
     */
    private Calendar dateTerminee;

    /**
     * L'identifiant de la personne.
     */
    private Long idPersonne;

    /**
     * nature de l'action.
     */
    private IdentifiantLibelleDto natureAction;

    /**
     * L'identifiant du type de l'action.
     */
    private IdentifiantLibelleDto type;

    /**
     * L'identifiant de l'objet de l'action.
     */
    private IdentifiantLibelleDto objet;

    /**
     * L'identifiant du sous objet de l'action.
     */
    private IdentifiantLibelleDto sousObjet;

    /**
     * L'identifiant de la priorité.
     */
    private IdentifiantLibelleDto priorite;

    /**
     * Identifiant du statut.
     */
    private Long idStatut;

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
    private IdentifiantLibelleDto campagne;

    /**
     * la ressource.
     */
    private DimensionRessourceDto ressource;

    /**
     * l'agence.
     */
    private IdentifiantLibelleDto agence;

    /**
     * Le créateur.
     */
    private DimensionRessourceDto createur;

    /**
     * La date de création.
     */
    private Calendar dateCreation;

    /**
     * La nature du résultat.
     */
    private IdentifiantLibelleDto natureResultat;

    /**
     * Résultat.
     */
    private IdentifiantLibelleDto resultat;

    /**
     * Commentaires.
     */
    private List<HistoriqueCommentaireDto> commentaires;

    /**
     * Identifiant de l'opportunite.
     */
    private Long idOpportunite;

    /**
     * EID de l'opportunite.
     */
    private String eidOpportunite;

    /**
     * Identifiant de l'action source.
     */
    private Long idActionSource;

    /**
     * Liste des documents associés.
     */
    private List<DocumentDto> documents;

    /** Durée de l'action. */
    private IdentifiantLibelleDto duree;

    /** Booléen indiquant si l'action est visible dans l'agenda. */
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
     * Renvoi la valeur de natureAction.
     * @return natureAction
     */
    public IdentifiantLibelleDto getNatureAction() {
        return natureAction;
    }

    /**
     * Modifie natureAction.
     * @param natureAction la nouvelle valeur de natureAction
     */
    public void setNatureAction(IdentifiantLibelleDto natureAction) {
        this.natureAction = natureAction;
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
     * Renvoi la valeur de createur.
     * @return createur
     */
    public DimensionRessourceDto getCreateur() {
        return createur;
    }

    /**
     * Modifie createur.
     * @param createur la nouvelle valeur de createur
     */
    public void setCreateur(DimensionRessourceDto createur) {
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
     * Renvoi la valeur de natureResultat.
     * @return natureResultat
     */
    public IdentifiantLibelleDto getNatureResultat() {
        return natureResultat;
    }

    /**
     * Modifie natureResultat.
     * @param natureResultat la nouvelle valeur de natureResultat
     */
    public void setNatureResultat(IdentifiantLibelleDto natureResultat) {
        this.natureResultat = natureResultat;
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
     * Renvoi la valeur de commentaires.
     * @return commentaires
     */
    public List<HistoriqueCommentaireDto> getCommentaires() {
        return commentaires;
    }

    /**
     * Modifie commentaires.
     * @param commentaires la nouvelle valeur de commentaires
     */
    public void setCommentaires(List<HistoriqueCommentaireDto> commentaires) {
        this.commentaires = commentaires;
    }

    /**
     * Renvoi la valeur de agence.
     * @return agence
     */
    public IdentifiantLibelleDto getAgence() {
        return agence;
    }

    /**
     * Modifie agence.
     * @param agence la nouvelle valeur de agence
     */
    public void setAgence(IdentifiantLibelleDto agence) {
        this.agence = agence;
    }

    /**
     * Renvoi la valeur de type.
     * @return type
     */
    public IdentifiantLibelleDto getType() {
        return type;
    }

    /**
     * Modifie type.
     * @param type la nouvelle valeur de type
     */
    public void setType(IdentifiantLibelleDto type) {
        this.type = type;
    }

    /**
     * Renvoi la valeur de objet.
     * @return objet
     */
    public IdentifiantLibelleDto getObjet() {
        return objet;
    }

    /**
     * Modifie objet.
     * @param objet la nouvelle valeur de objet
     */
    public void setObjet(IdentifiantLibelleDto objet) {
        this.objet = objet;
    }

    /**
     * Renvoi la valeur de sousObjet.
     * @return sousObjet
     */
    public IdentifiantLibelleDto getSousObjet() {
        return sousObjet;
    }

    /**
     * Modifie sousObjet.
     * @param sousObjet la nouvelle valeur de sousObjet
     */
    public void setSousObjet(IdentifiantLibelleDto sousObjet) {
        this.sousObjet = sousObjet;
    }

    /**
     * Renvoi la valeur de priorite.
     * @return priorite
     */
    public IdentifiantLibelleDto getPriorite() {
        return priorite;
    }

    /**
     * Modifie priorite.
     * @param priorite la nouvelle valeur de priorite
     */
    public void setPriorite(IdentifiantLibelleDto priorite) {
        this.priorite = priorite;
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
     * Renvoi la valeur de idStatut.
     * @return idStatut
     */
    public Long getIdStatut() {
        return idStatut;
    }

    /**
     * Modifie idStatut.
     * @param idStatut la nouvelle valeur de idStatut
     */
    public void setIdStatut(Long idStatut) {
        this.idStatut = idStatut;
    }

    /**
     * Getter function.
     * @return the campagne
     */
    public IdentifiantLibelleDto getCampagne() {
        return campagne;
    }

    /**
     * Setter function.
     * @param campagne the campagne to set
     */
    public void setCampagne(IdentifiantLibelleDto campagne) {
        this.campagne = campagne;
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
     * Récupération de identifiantExterieur.
     * @return the identifiantExterieur
     */
    public String getIdentifiantExterieur() {
        return identifiantExterieur;
    }

    /**
     * Définition de identifiantExterieur.
     * @param identifiantExterieur the identifiantExterieur to set
     */
    public void setIdentifiantExterieur(String identifiantExterieur) {
        this.identifiantExterieur = identifiantExterieur;
    }

    /**
     * Récupération de idActionSource.
     * @return the idActionSource
     */
    public Long getIdActionSource() {
        return idActionSource;
    }

    /**
     * Définition de idActionSource.
     * @param idActionSource the idActionSource to set
     */
    public void setIdActionSource(Long idActionSource) {
        this.idActionSource = idActionSource;
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
     * Renvoie la valeur de dateAffichage.
     * @return dateAffichage
     */
	public Calendar getDateAffichage() {
		return dateAffichage;
	}

    /**
     * Définit la valeur de dateAffichage.
     * @param dateAffichage la date d'affichage
     */
	public void setDateAffichage(Calendar dateAffichage) {
		this.dateAffichage = dateAffichage;
	}

    /**
     * Accesseur pour l'attribut dateTerminee.
     * @return l'attribut dateTerminee
     */
    public Calendar getDateTerminee() {
        return dateTerminee;
    }

    /**
     * Définit la valeur de dateTerminee.
     * @param dateTerminee la nouvelle valeur de dateTerminee
     */
    public void setDateTerminee(Calendar dateTerminee) {
        this.dateTerminee = dateTerminee;
    }

    /**
     * Get the value of duree.
     * @return the duree
     */
    public IdentifiantLibelleDto getDuree() {
        return duree;
    }

    /**
     * Set the value of duree.
     * @param duree the duree to set
     */
    public void setDuree(IdentifiantLibelleDto duree) {
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

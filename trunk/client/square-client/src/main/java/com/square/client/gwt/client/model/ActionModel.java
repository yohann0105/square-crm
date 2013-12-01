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

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Un objet contenant l'action.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionModel implements IsSerializable {

    /**
     * L'identifiant de l'action.
     */
    private Long identifiant;

    /**
     * Statut de l'action.
     */
    private IdentifiantLibelleGwt statut;

    /**
     * La date d'action.
     */
    private String dateAction;

    /**
     * L'heure de l'action.
     */
    private String heureAction;

    /**
     * La date d'action.
     */
    private String dateTerminee;

    /**
     * L'identifiant de la personne.
     */
    private Long idPersonne;

    /**
     * nature de l'action.
     */
    private IdentifiantLibelleGwt natureAction;

    /**
     * L'identifiant du type de l'action.
     */
    private IdentifiantLibelleGwt type;

    /**
     * L'identifiant de l'objet de l'action.
     */
    private IdentifiantLibelleGwt objet;

    /**
     * L'identifiant du sous objet de l'action.
     */
    private IdentifiantLibelleGwt sousObjet;

    /**
     * L'identifiant de la priorité.
     */
    private IdentifiantLibelleGwt priorite;

    /**
     * L'identifiant du statut.
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
    private IdentifiantLibelleGwt campagne;

    /**
     * la ressource.
     */
    private DimensionRessourceModel ressource;

    /**
     * l'agence.
     */
    private IdentifiantLibelleGwt agence;

    /**
     * Le créateur.
     */
    private DimensionRessourceModel createur;

    /**
     * La date de création.
     */
    private String dateCreation;

    /**
     * La nature du résultat.
     */
    private IdentifiantLibelleGwt natureResultat;

    /**
     * Résultat.
     */
    private IdentifiantLibelleGwt resultat;

    /**
     * Commentaires.
     */
    private List<HistoriqueCommentaireModel> commentaires;

    /**
     * Identifiant de l'opportunite.
     */
    private Long idOpportunite;

    /**
     * EID de l'opportunite.
     */
    private String eidOpportunite;

    /** Durée de l'action. */
    private IdentifiantLibelleGwt duree;

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
    public IdentifiantLibelleGwt getNatureAction() {
        return natureAction;
    }

    /**
     * Modifie natureAction.
     * @param natureAction la nouvelle valeur de natureAction
     */
    public void setNatureAction(IdentifiantLibelleGwt natureAction) {
        this.natureAction = natureAction;
    }

    /**
     * Renvoi la valeur de type.
     * @return type
     */
    public IdentifiantLibelleGwt getType() {
        return type;
    }

    /**
     * Modifie type.
     * @param type la nouvelle valeur de type
     */
    public void setType(IdentifiantLibelleGwt type) {
        this.type = type;
    }

    /**
     * Renvoi la valeur de objet.
     * @return objet
     */
    public IdentifiantLibelleGwt getObjet() {
        return objet;
    }

    /**
     * Modifie objet.
     * @param objet la nouvelle valeur de objet
     */
    public void setObjet(IdentifiantLibelleGwt objet) {
        this.objet = objet;
    }

    /**
     * Renvoi la valeur de sousObjet.
     * @return sousObjet
     */
    public IdentifiantLibelleGwt getSousObjet() {
        return sousObjet;
    }

    /**
     * Modifie sousObjet.
     * @param sousObjet la nouvelle valeur de sousObjet
     */
    public void setSousObjet(IdentifiantLibelleGwt sousObjet) {
        this.sousObjet = sousObjet;
    }

    /**
     * Renvoi la valeur de priorite.
     * @return priorite
     */
    public IdentifiantLibelleGwt getPriorite() {
        return priorite;
    }

    /**
     * Modifie priorite.
     * @param priorite la nouvelle valeur de priorite
     */
    public void setPriorite(IdentifiantLibelleGwt priorite) {
        this.priorite = priorite;
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
     * Renvoi la valeur de natureResultat.
     * @return natureResultat
     */
    public IdentifiantLibelleGwt getNatureResultat() {
        return natureResultat;
    }

    /**
     * Modifie natureResultat.
     * @param natureResultat la nouvelle valeur de natureResultat
     */
    public void setNatureResultat(IdentifiantLibelleGwt natureResultat) {
        this.natureResultat = natureResultat;
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
     * Renvoi la valeur de commentaires.
     * @return commentaires
     */
    public List<HistoriqueCommentaireModel> getCommentaires() {
        return commentaires;
    }

    /**
     * Modifie commentaires.
     * @param commentaires la nouvelle valeur de commentaires
     */
    public void setCommentaires(List<HistoriqueCommentaireModel> commentaires) {
        this.commentaires = commentaires;
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
     * @return the heureAction
     */
    public String getHeureAction() {
        return heureAction;
    }

    /**
     * Setter function.
     * @param heureAction the heureAction to set
     */
    public void setHeureAction(String heureAction) {
        this.heureAction = heureAction;
    }

    /**
     * Getter function.
     * @return the agence
     */
    public IdentifiantLibelleGwt getAgence() {
        return agence;
    }

    /**
     * Setter function.
     * @param agence the agence to set
     */
    public void setAgence(IdentifiantLibelleGwt agence) {
        this.agence = agence;
    }

    /**
     * Getter function.
     * @return the createur
     */
    public DimensionRessourceModel getCreateur() {
        return createur;
    }

    /**
     * Setter function.
     * @param createur the createur to set
     */
    public void setCreateur(DimensionRessourceModel createur) {
        this.createur = createur;
    }

    /**
     * Getter function.
     * @return the campagne
     */
    public IdentifiantLibelleGwt getCampagne() {
        return campagne;
    }

    /**
     * Setter function.
     * @param campagne the campagne to set
     */
    public void setCampagne(IdentifiantLibelleGwt campagne) {
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
     * Get the value of dateTerminee.
     * @return the dateTerminee
     */
    public String getDateTerminee() {
        return dateTerminee;
    }

    /**
     * Set the value of dateTerminee.
     * @param dateTerminee the dateTerminee to set
     */
    public void setDateTerminee(String dateTerminee) {
        this.dateTerminee = dateTerminee;
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

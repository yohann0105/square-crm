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
package com.square.core.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

import com.square.core.model.Ressources.Ressource;

/**
 * Entité action.
 * @author straumat - SCUB
 */
@Entity
@Table(name = "DATA_ACTION")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "ACTION_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "ACTION_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "ACTION_EID", unique = true)),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "ACTION_DATE_CREATION", nullable = false)),
    @AttributeOverride(name = "dateModification", column = @Column(name = "ACTION_DATE_MODIFICATION")),
    @AttributeOverride(name = "dateSuppression", column = @Column(name = "ACTION_DATE_SUPPRESSION")),
    @AttributeOverride(name = "supprime", column = @Column(name = "ACTION_SUPPRIME", nullable = false)) })
public class Action extends ModelData {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -7419346374633219563L;

    /**
     * Indique si la personne habite à l'adresse indiquée.
     */
    @Column(name = "ACTION_RECLAMATION", nullable = false)
    private boolean reclamation;

    /**
     * Date à laquelle l'action doit être réalisée.
     */
    @Column(name = "ACTION_DATE", nullable = false)
    private Calendar date;

    /**
     * Date à laquelle l'action sera affichée.
     */
    @Column(name = "ACTION_DATE_AFFICHAGE")
    private Calendar dateAffichage;

    /**
     * Date à laquelle l'action a été terminée.
     */
    @Column(name = "ACTION_DATE_TERMINEE")
    private Calendar dateTerminee;

    /**
     * Date de notification de l'action.
     */
    @Column(name = "ACTION_DATE_NOTIFICATION")
    private Calendar dateNotification;

    /**
     * Mail de notification.
     */
    @Column(name = "ACTION_MAIL_NOTIFICATION", nullable = false)
    private boolean mailNotification;

    /**
     * Action.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTION_ACTION_SOURCE", nullable = true)
    @ForeignKey(name = "FK_DATA_ACTION_DATA_ACTION_ACTION_ACTION_SOURCE_UID")
    @Index(name = "IDX_SEARCH_ACTION_SOURCE_UID")
    private Action actionSource;

    /**
     * Résultat de l'action.
     */
    @ManyToOne
    @JoinColumn(name = "ACTION_RESULTAT_UID")
    @ForeignKey(name = "FK_DATA_ACTION_DIM_ACTION_RESULTAT_ACTION_RESULTAT_UID")
    private ActionResultat resultat;

    /**
     * Statut de l'action.
     */
    @ManyToOne
    @JoinColumn(name = "ACTION_STATUT_UID")
    @ForeignKey(name = "FK_DATA_ACTION_DIM_ACTION_STATUT_ACTION_STATUT_UID")
    private ActionStatut statut;

    /**
     * Nature de l'action.
     */
    @ManyToOne
    @JoinColumn(name = "ACTION_NATURE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_ACTION_DIM_ACTION_NATURE_ACTION_NATURE_UID")
    private ActionNature nature;

    /**
     * Nature du resultat.
     */
    @ManyToOne
    @JoinColumn(name = "ACTION_NATURE_RESULTAT_UID")
    @ForeignKey(name = "FK_DATA_ACTION_DIM_ACTION_NATURE_RESULTAT_ACTION_NATURE_RESULTAT_UID")
    private ActionNatureResultat natureResultat;

    /**
     * Type.
     */
    @ManyToOne
    @JoinColumn(name = "ACTION_TYPE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_ACTION_DIM_ACTION_TYPE_ACTION_TYPE_UID")
    private ActionType type;

    /**
     * Objet.
     */
    @ManyToOne
    @JoinColumn(name = "ACTION_OBJET_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_ACTION_DIM_ACTION_OBJET_ACTION_OBJET_UID")
    private ActionObjet objet;

    /**
     * Sous objet.
     */
    @ManyToOne
    @JoinColumn(name = "ACTION_SOUS_OBJET_UID")
    @ForeignKey(name = "FK_DATA_ACTION_DIM_ACTION_SOUS_OBJET_ACTION_SOUS_OBJET_UID")
    private ActionSousObjet sousObjet;

    /**
     * Priorité.
     */
    @ManyToOne()
    @JoinColumn(name = "ACTION_PRIORITE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_ACTION_DIM_ACTION_PRIORITE_ACTION_PRIORITE_UID")
    private ActionPriorite priorite;

    /**
     * Campagne.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAMPAGNE_UID")
    @ForeignKey(name = "FK_DATA_ACTION_DATA_CAMPAGNE_CAMPAGNE_UID")
    private Campagne campagne;

    /**
     * Affectation de l'action.
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ACTION_AFFECTATION_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_ACTION_DATA_ACTION_AFFECTATION_ACTION_AFFECTATION_UID")
    private ActionAffectation actionAffectation;

    /**
     * Liste des commentaires.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ACTION_UID")
    private List<Commentaire> commentaires;

    /**
     * Ressource (Créateur de l'action).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESSOURCE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_ACTION_DATA_RESSOURCE_RESSOURCE_UID")
    private Ressource ressource;

    /**
     * Attribution de l'action.
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ACTION_ATTRIBUTION_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_ACTION_DATA_ACTION_ATTRIBUTION_ACTION_ATTRIBUTION_UID")
    private ActionAttribution actionAttribution;

    /**
     * Liste des documents.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ACTION_UID")
    private List<Document> documents;

    /**
     *l'action à notifier ou non.
     */
    @Column(name = "NOTIFIER")
    private Boolean notifier = false;

    /**
     * Flag indiquant si l'action est visible ou non dans l'agenda.
     */
    @Column(name = "ACTION_VISIBLE_AGENDA", nullable = false)
    private Boolean visibleAgenda = false;

    /**
     * Durée.
     */
    @ManyToOne
    @JoinColumn(name = "ACTION_DUREE_UID")
    @ForeignKey(name = "FK_DATA_ACTION_DIM_ACTION_DUREE_ACTION_DUREE_UID")
    private ActionDuree duree;

    /**
     * Getter / Setter de la propriété.
     * @return the reclamation
     */
    public boolean isReclamation() {
        return reclamation;
    }

    /**
     * Getter / Setter de la propriété.
     * @param reclamation the reclamation to set
     */
    public void setReclamation(boolean reclamation) {
        this.reclamation = reclamation;
    }

    /**
     * Getter / Setter de la propriété.
     * @return the date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * Getter / Setter de la propriété.
     * @param date the date to set
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Getter / Setter de la propriété.
     * @return the dateNotification
     */
    public Calendar getDateNotification() {
        return dateNotification;
    }

    /**
     * Getter / Setter de la propriété.
     * @param dateNotification the dateNotification to set
     */
    public void setDateNotification(Calendar dateNotification) {
        this.dateNotification = dateNotification;
    }

    /**
     * Getter / Setter de la propriété.
     * @return the mailNotification
     */
    public boolean isMailNotification() {
        return mailNotification;
    }

    /**
     * Getter / Setter de la propriété.
     * @param mailNotification the mailNotification to set
     */
    public void setMailNotification(boolean mailNotification) {
        this.mailNotification = mailNotification;
    }

    /**
     * Getter / Setter de la propriété.
     * @return the resultat
     */
    public ActionResultat getResultat() {
        return resultat;
    }

    /**
     * Getter / Setter de la propriété.
     * @param resultat the resultat to set
     */
    public void setResultat(ActionResultat resultat) {
        this.resultat = resultat;
    }

    /**
     * Getter / Setter de la propriété.
     * @return the statut
     */
    public ActionStatut getStatut() {
        return statut;
    }

    /**
     * Getter / Setter de la propriété.
     * @param statut the statut to set
     */
    public void setStatut(ActionStatut statut) {
        this.statut = statut;
    }

    /**
     * Getter / Setter de la propriété.
     * @return the nature
     */
    public ActionNature getNature() {
        return nature;
    }

    /**
     * Getter / Setter de la propriété.
     * @param nature the nature to set
     */
    public void setNature(ActionNature nature) {
        this.nature = nature;
    }

    /**
     * Getter / Setter de la propriété.
     * @return the type
     */
    public ActionType getType() {
        return type;
    }

    /**
     * Getter / Setter de la propriété.
     * @param type the type to set
     */
    public void setType(ActionType type) {
        this.type = type;
    }

    /**
     * Getter / Setter de la propriété.
     * @return the objet
     */
    public ActionObjet getObjet() {
        return objet;
    }

    /**
     * Getter / Setter de la propriété.
     * @param objet the objet to set
     */
    public void setObjet(ActionObjet objet) {
        this.objet = objet;
    }

    /**
     * Getter / Setter de la propriété.
     * @return the sousObjet
     */
    public ActionSousObjet getSousObjet() {
        return sousObjet;
    }

    /**
     * Getter / Setter de la propriété.
     * @param sousObjet the sousObjet to set
     */
    public void setSousObjet(ActionSousObjet sousObjet) {
        this.sousObjet = sousObjet;
    }

    /**
     * Getter / Setter de la propriété.
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * Getter / Setter de la propriété.
     * @return the natureResultat
     */
    public ActionNatureResultat getNatureResultat() {
        return natureResultat;
    }

    /**
     * Getter / Setter de la propriété.
     * @param natureResultat the natureResultat to set
     */
    public void setNatureResultat(ActionNatureResultat natureResultat) {
        this.natureResultat = natureResultat;
    }

    /**
     * Renvoi la valeur de priorite.
     * @return priorite
     */
    public ActionPriorite getPriorite() {
        return priorite;
    }

    /**
     * Modifie priorite.
     * @param priorite la nouvelle valeur de priorite
     */
    public void setPriorite(ActionPriorite priorite) {
        this.priorite = priorite;
    }

    /**
     * Renvoi la valeur de campagne.
     * @return campagne
     */
    public Campagne getCampagne() {
        return campagne;
    }

    /**
     * Modifie campagne.
     * @param campagne la nouvelle valeur de campagne
     */
    public void setCampagne(Campagne campagne) {
        this.campagne = campagne;
    }

    /**
     * Renvoi la valeur de actionAffectation.
     * @return actionAffectation
     */
    public ActionAffectation getActionAffectation() {
        return actionAffectation;
    }

    /**
     * Modifie actionAffectation.
     * @param actionAffectation la nouvelle valeur de actionAffectation
     */
    public void setActionAffectation(ActionAffectation actionAffectation) {
        this.actionAffectation = actionAffectation;
    }

    /**
     * Renvoi la valeur de actionSource.
     * @return actionSource
     */
    public Action getActionSource() {
        return actionSource;
    }

    /**
     * Modifie actionSource.
     * @param actionSource la nouvelle valeur de actionSource
     */
    public void setActionSource(Action actionSource) {
        this.actionSource = actionSource;
    }

    /**
     * Renvoi la valeur de commentaires.
     * @return commentaires
     */
    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    /**
     * Modifie commentaires.
     * @param commentaires la nouvelle valeur de commentaires
     */
    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    /**
     * Retourne la valeur de actionAttribution.
     * @return the actionAttribution
     */
    public ActionAttribution getActionAttribution() {
        return actionAttribution;
    }

    /**
     * Modifie la valeur de actionAttribution.
     * @param actionAttribution the actionAttribution to set
     */
    public void setActionAttribution(ActionAttribution actionAttribution) {
        this.actionAttribution = actionAttribution;
    }

    /**
     * Retourne la valeur de ressource.
     * @return the ressource
     */
    public Ressource getRessource() {
        return ressource;
    }

    /**
     * Modifie la valeur de ressource.
     * @param ressource the ressource to set
     */
    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Action)) {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Récupération de documents.
     * @return the documents
     */
    public List<Document> getDocuments() {
        return documents;
    }

    /**
     * Définition de documents.
     * @param documents the documents to set
     */
    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    /**
     * Get the dateTerminee value.
     * @return the dateTerminee
     */
    public Calendar getDateTerminee() {
        return dateTerminee;
    }

    /**
     * Set the dateTerminee value.
     * @param dateTerminee the dateTerminee to set
     */
    public void setDateTerminee(Calendar dateTerminee) {
        this.dateTerminee = dateTerminee;
    }

    /**
     * Get the dateAffichage value.
     * @return the dateAffichage
     */
    public Calendar getDateAffichage() {
        return dateAffichage;
    }

    /**
     * Set the dateAffichage value.
     * @param dateAffichage the dateAffichage to set
     */
    public void setDateAffichage(Calendar dateAffichage) {
        this.dateAffichage = dateAffichage;
    }

	/**
	 * Set the notifier value.
	 * @param notifier the notifier to set
	 */
	public void setNotifier(Boolean notifier) {
		this.notifier = notifier;
	}

	/**
     * Get the notifier value.
	 * @return the notifier
	 */
	public Boolean getNotifier() {
		return notifier;
	}

    /**
     * Get the value of visibleAgenda.
     * @return the visibleAgenda
     */
    public boolean isVisibleAgenda() {
        return visibleAgenda;
    }

    /**
     * Set the value of visibleAgenda.
     * @param visibleAgenda the visibleAgenda to set
     */
    public void setVisibleAgenda(boolean visibleAgenda) {
        this.visibleAgenda = visibleAgenda;
    }

    /**
     * Get the value of duree.
     * @return the duree
     */
    public ActionDuree getDuree() {
        return duree;
    }

    /**
     * Set the value of duree.
     * @param duree the duree to set
     */
    public void setDuree(ActionDuree duree) {
        this.duree = duree;
    }

}

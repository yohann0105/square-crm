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
import java.util.Set;

/**
 * Objet contenant les critère de recherche d'une action.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionCritereRechercheDto implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 9014000832821528998L;

    /**
     * Identifiants des actions recherchées.
     */
    private Set<Long> idsActions;

    /**
     * L'identifiant du type de l'action.
     */
    private Long idType;

    /**
     * L'identifiant de l'objet de l'action.
     */
    private Long idObjet;

    /**
     * Le sous objet de l'action.
     */
    private Long idSousObjet;

    /**
     * La date de début l'action.
     */
    private Calendar dateDebutAction;

    /**
     * La date de fin de l'action.
     */
    private Calendar dateFinAction;

    /**
     * L'identifiant du statut de l'action.
     */
    private List<Long> listeStatuts;

    /**
     * L'identifiant de la nature de l'action.
     */
    private List<Long> listeNatureActions;

    /**
     * L'identifiant du résultat de l'action.
     */
    private List<Long> listeResultats;

    /**
     * L'identifiant de la nature du résultat.
     */
    private List<Long> listeNatureResultats;

    /**
     * L'identifiant de la campagne de l'action.
     */
    private List<Long> listeCampagnes;

    /**
     * L'identifiant de la priorité de l'action.
     */
    private List<Long> listePriorites;

    /**
     * Booléen qui indique s'il y a reclamation ou pas. true: il y a une reclamation false: il n'y a pas de reclamation
     */
    private Boolean reclamation;

    /**
     * L'identifiant du créateur de l'action.
     */
    private List<Long> idCreateurs;

    /**
     * L'identifiant du responsable de l'action.
     */
    private List<Long> idCommerciaux;

    /**
     * L'identifiant de l'agence responsable de l'action.
     */
    private List<Long> idAgences;

    /**
     * Identifiants des agences à laquelle l'action est attribuée à exclure lors de la recherche.
     */
    private Set<Long> idsAgencesExclues;

    /**
     * L'identifiant de l'opportunité.
     */
    private Long idOpportunite;

    /**
     * L'identifiant de la personne.
     */
    private Long idPersonne;

    /**
     * Liste de documents rattachés à l'action.
     */
    private List<String> idDocuments;

    /** Flag indiquant que l'on fait une recherche de type "ET" entre agences et commerciaux. */
    private Boolean rechercheEtEntreAgencesEtCommerciaux = Boolean.TRUE;

    /** Désactive le filtre sur la date d'affichage. */
    private Boolean desactiverFiltreAffichage;

    /** notifie l'action. */
    private Boolean notifier;

    /** la date de notification.  */
    private Calendar dateNotification;

    /**
     * Retourne la valeur de idType.
     * @return the idType
     */
    public Long getIdType() {
        return idType;
    }

    /**
     * Modifie la valeur de idType.
     * @param idType the idType to set
     */
    public void setIdType(Long idType) {
        this.idType = idType;
    }

    /**
     * Retourne la valeur de idObjet.
     * @return the idObjet
     */
    public Long getIdObjet() {
        return idObjet;
    }

    /**
     * Modifie la valeur de idObjet.
     * @param idObjet the idObjet to set
     */
    public void setIdObjet(Long idObjet) {
        this.idObjet = idObjet;
    }

    /**
     * Retourne la valeur de idSousObjet.
     * @return the idSousObjet
     */
    public Long getIdSousObjet() {
        return idSousObjet;
    }

    /**
     * Modifie la valeur de idSousObjet.
     * @param idSousObjet the idSousObjet to set
     */
    public void setIdSousObjet(Long idSousObjet) {
        this.idSousObjet = idSousObjet;
    }

    /**
     * Renvoi la valeur de dateDebutAction.
     * @return dateDebutAction
     */
    public Calendar getDateDebutAction() {
        return dateDebutAction;
    }

    /**
     * Modifie dateDebutAction.
     * @param dateDebutAction la nouvelle valeur de dateDebutAction
     */
    public void setDateDebutAction(Calendar dateDebutAction) {
        this.dateDebutAction = dateDebutAction;
    }

    /**
     * Renvoi la valeur de dateFinAction.
     * @return dateFinAction
     */
    public Calendar getDateFinAction() {
        return dateFinAction;
    }

    /**
     * Modifie dateFinAction.
     * @param dateFinAction la nouvelle valeur de dateFinAction
     */
    public void setDateFinAction(Calendar dateFinAction) {
        this.dateFinAction = dateFinAction;
    }

    /**
     * Renvoi la valeur de listeStatuts.
     * @return listeStatuts
     */
    public List<Long> getListeStatuts() {
        return listeStatuts;
    }

    /**
     * Modifie listeStatuts.
     * @param listeStatuts la nouvelle valeur de listeStatuts
     */
    public void setListeStatuts(List<Long> listeStatuts) {
        this.listeStatuts = listeStatuts;
    }

    /**
     * Renvoi la valeur de listeNatureActions.
     * @return listeNatureActions
     */
    public List<Long> getListeNatureActions() {
        return listeNatureActions;
    }

    /**
     * Modifie listeNatureActions.
     * @param listeNatureActions la nouvelle valeur de listeNatureActions
     */
    public void setListeNatureActions(List<Long> listeNatureActions) {
        this.listeNatureActions = listeNatureActions;
    }

    /**
     * Renvoi la valeur de listeResultats.
     * @return listeResultats
     */
    public List<Long> getListeResultats() {
        return listeResultats;
    }

    /**
     * Modifie listeResultats.
     * @param listeResultats la nouvelle valeur de listeResultats
     */
    public void setListeResultats(List<Long> listeResultats) {
        this.listeResultats = listeResultats;
    }

    /**
     * Renvoi la valeur de listeNatureResultats.
     * @return listeNatureResultats
     */
    public List<Long> getListeNatureResultats() {
        return listeNatureResultats;
    }

    /**
     * Modifie listeNatureResultats.
     * @param listeNatureResultats la nouvelle valeur de listeNatureResultats
     */
    public void setListeNatureResultats(List<Long> listeNatureResultats) {
        this.listeNatureResultats = listeNatureResultats;
    }

    /**
     * Renvoi la valeur de listeCampagnes.
     * @return listeCampagnes
     */
    public List<Long> getListeCampagnes() {
        return listeCampagnes;
    }

    /**
     * Modifie listeCampagnes.
     * @param listeCampagnes la nouvelle valeur de listeCampagnes
     */
    public void setListeCampagnes(List<Long> listeCampagnes) {
        this.listeCampagnes = listeCampagnes;
    }

    /**
     * Renvoi la valeur de listePriorites.
     * @return listePriorites
     */
    public List<Long> getListePriorites() {
        return listePriorites;
    }

    /**
     * Modifie listePriorites.
     * @param listePriorites la nouvelle valeur de listePriorites
     */
    public void setListePriorites(List<Long> listePriorites) {
        this.listePriorites = listePriorites;
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
     * Retourne la valeur de idCreateurs.
     * @return the idCreateurs
     */
    public List<Long> getIdCreateurs() {
        return idCreateurs;
    }

    /**
     * Modifie la valeur de idCreateurs.
     * @param idCreateurs the idCreateurs to set
     */
    public void setIdCreateurs(List<Long> idCreateurs) {
        this.idCreateurs = idCreateurs;
    }

    /**
     * Retourne la valeur de idCommerciaux.
     * @return the idCommerciaux
     */
    public List<Long> getIdCommerciaux() {
        return idCommerciaux;
    }

    /**
     * Modifie la valeur de idCommerciaux.
     * @param idCommerciaux the idCommerciaux to set
     */
    public void setIdCommerciaux(List<Long> idCommerciaux) {
        this.idCommerciaux = idCommerciaux;
    }

    /**
     * Retourne la valeur de idAgences.
     * @return the idAgences
     */
    public List<Long> getIdAgences() {
        return idAgences;
    }

    /**
     * Modifie la valeur de idAgences.
     * @param idAgences the idAgences to set
     */
    public void setIdAgences(List<Long> idAgences) {
        this.idAgences = idAgences;
    }

    /**
     * Récupération de idOpportunite.
     * @return the idOpportunite
     */
    public Long getIdOpportunite() {
        return idOpportunite;
    }

    /**
     * Définition de idOpportunite.
     * @param idOpportunite the idOpportunite to set
     */
    public void setIdOpportunite(Long idOpportunite) {
        this.idOpportunite = idOpportunite;
    }

    /**
     * Récupération de idPersonne.
     * @return the idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Définition de idPersonne.
     * @param idPersonne the idPersonne to set
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    /**
     * Récupération de idDocuments.
     * @return the idDocuments
     */
    public List<String> getIdDocuments() {
        return idDocuments;
    }

    /**
     * Définition de idDocuments.
     * @param idDocuments the idDocuments to set
     */
    public void setIdDocuments(List<String> idDocuments) {
        this.idDocuments = idDocuments;
    }

    /**
     * Récupère la valeur de rechercheEtEntreAgencesEtCommerciaux.
     * @return la valeur de rechercheEtEntreAgencesEtCommerciaux
     */
    public Boolean getRechercheEtEntreAgencesEtCommerciaux() {
        return rechercheEtEntreAgencesEtCommerciaux;
    }

    /**
     * Définit la valeur de rechercheEtEntreAgencesEtCommerciaux.
     * @param rechercheEtEntreAgencesEtCommerciaux la nouvelle valeur de rechercheEtEntreAgencesEtCommerciaux
     */
    public void setRechercheEtEntreAgencesEtCommerciaux(Boolean rechercheEtEntreAgencesEtCommerciaux) {
        this.rechercheEtEntreAgencesEtCommerciaux = rechercheEtEntreAgencesEtCommerciaux;
    }

    /**
     * Récupération de desactiverFiltreAffichage.
     * @return the desactiverFiltreAffichage
     */
    public Boolean getDesactiverFiltreAffichage() {
        return desactiverFiltreAffichage;
    }

    /**
     * Définition de desactiverFiltreAffichage.
     * @param desactiverFiltreAffichage the desactiverFiltreAffichage to set
     */
    public void setDesactiverFiltreAffichage(Boolean desactiverFiltreAffichage) {
        this.desactiverFiltreAffichage = desactiverFiltreAffichage;
    }

    /**
     * Getter function.
     * @return the idsActions
     */
    public Set<Long> getIdsActions() {
        return idsActions;
    }

    /**
     * Setter function.
     * @param idsActions the idsActions to set
     */
    public void setIdsActions(Set<Long> idsActions) {
        this.idsActions = idsActions;
    }

    /**
     * Setter function.
     * @param notifier the notifier to set
     */
    public void setNotifier(Boolean notifier) {
        this.notifier = notifier;
    }

    /**
     * Getter function.
     * @return the notifier
     */
    public Boolean getNotifier() {
        return notifier;
    }

    /**
     * Setter function.
     * @param dateNotification the dateNotification to set
     */
    public void setDateNotification(Calendar dateNotification) {
        this.dateNotification = dateNotification;
    }

    /**
     * Getter function.
     * @return the dateNotification
     */
    public Calendar getDateNotification() {
        return dateNotification;
    }

    /**
     * Getter function.
     * @return the idsAgencesExclues
     */
    public Set<Long> getIdsAgencesExclues() {
        return idsAgencesExclues;
    }

    /**
     * Setter function.
     * @param idsAgencesExclues the idsAgencesExclues to set
     */
    public void setIdsAgencesExclues(Set<Long> idsAgencesExclues) {
        this.idsAgencesExclues = idsAgencesExclues;
    }

}

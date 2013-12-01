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
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Objet contenant les critères de recherche des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionCritereRechercheModel implements IsSerializable {
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
    private String dateDebutAction;

    /**
     * La date de fin de l'action.
     */
    private String dateFinAction;

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

    /** Flag indiquant que l'on fait une recherche de type "ET" entre agences et commerciaux. */
    private Boolean rechercheEtEntreAgencesEtCommerciaux = Boolean.TRUE;

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
     * Retourne la valeur de dateDebutAction.
     * @return the dateDebutAction
     */
    public String getDateDebutAction() {
        return dateDebutAction;
    }

    /**
     * Modifie la valeur de dateDebutAction.
     * @param dateDebutAction the dateDebutAction to set
     */
    public void setDateDebutAction(String dateDebutAction) {
        this.dateDebutAction = dateDebutAction;
    }

    /**
     * Retourne la valeur de dateFinAction.
     * @return the dateFinAction
     */
    public String getDateFinAction() {
        return dateFinAction;
    }

    /**
     * Modifie la valeur de dateFinAction.
     * @param dateFinAction the dateFinAction to set
     */
    public void setDateFinAction(String dateFinAction) {
        this.dateFinAction = dateFinAction;
    }

    /**
     * Retourne la valeur de reclamation.
     * @return the reclamation
     */
    public Boolean getReclamation() {
        return reclamation;
    }

    /**
     * Modifie la valeur de reclamation.
     * @param reclamation the reclamation to set
     */
    public void setReclamation(Boolean reclamation) {
        this.reclamation = reclamation;
    }

    /**
     * Renvoi la valeur de listeStatuts.
     * @return listeStatuts
     */
    public List<Long> getListeStatuts() {
        return listeStatuts;
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
     * Modifie listeStatuts.
     * @param listeStatuts la nouvelle valeur de listeStatuts
     */
    public void setListeStatuts(List<Long> listeStatuts) {
        this.listeStatuts = listeStatuts;
    }

    /**
     * Getter function.
     * @return the idCreateurs
     */
    public List<Long> getIdCreateurs() {
        return idCreateurs;
    }

    /**
     * Getter function.
     * @return the idCommerciaux
     */
    public List<Long> getIdCommerciaux() {
        return idCommerciaux;
    }

    /**
     * Getter function.
     * @return the idAgences
     */
    public List<Long> getIdAgences() {
        return idAgences;
    }

    /**
     * Setter function.
     * @param idCreateurs the idCreateurs to set
     */
    public void setIdCreateurs(List<Long> idCreateurs) {
        this.idCreateurs = idCreateurs;
    }

    /**
     * Setter function.
     * @param idCommerciaux the idCommerciaux to set
     */
    public void setIdCommerciaux(List<Long> idCommerciaux) {
        this.idCommerciaux = idCommerciaux;
    }

    /**
     * Setter function.
     * @param idAgences the idAgences to set
     */
    public void setIdAgences(List<Long> idAgences) {
        this.idAgences = idAgences;
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

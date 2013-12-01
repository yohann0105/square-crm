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

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model contenant les informations pour l'affichage d'une action dans l'agenda.
 * @author cblanchard - SCUB
 */
public class ActionAgendaModel implements IsSerializable {

    /** Identifiant de l'action. */
    private Long idAction;

    /** Identifiant de la personne. */
    private Long idPersonne;

    /** Nom de la personne. */
    private String nomPersonne;

    /** Prénom de la personne. */
    private String prenomPersonne;

    /** Raison sociale. */
    private String raisonSociale;

    /** Nature de l'action. */
    private String nature;

    /** Type de l'action. */
    private String type;

    /** Objet de l'action. */
    private String objet;

    /** Sous objet de l'action. */
    private String sousObjet;

    /** Le statut de l'action. */
    private Long idStatut;

    /** La date d'action. */
    private Date dateAction;

    /** Titre, correspond au nom de la personne. */
    private String titre;

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
     * Renvoi la valeur de nomPersonne.
     * @return nomPersonne
     */
    public String getNomPersonne() {
        return nomPersonne;
    }

    /**
     * Modifie nomPersonne.
     * @param nomPersonne la nouvelle valeur de nomPersonne
     */
    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    /**
     * Renvoi la valeur de prenomPersonne.
     * @return prenomPersonne
     */
    public String getPrenomPersonne() {
        return prenomPersonne;
    }

    /**
     * Modifie prenomPersonne.
     * @param prenomPersonne la nouvelle valeur de prenomPersonne
     */
    public void setPrenomPersonne(String prenomPersonne) {
        this.prenomPersonne = prenomPersonne;
    }

    /**
     * Renvoi la valeur de raisonSociale.
     * @return raisonSociale
     */
    public String getRaisonSociale() {
        return raisonSociale;
    }

    /**
     * Modifie raisonSociale.
     * @param raisonSociale la nouvelle valeur de raisonSociale
     */
    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    /**
     * Renvoi la valeur de nature.
     * @return nature
     */
    public String getNature() {
        return nature;
    }

    /**
     * Modifie nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(String nature) {
        this.nature = nature;
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
     * Renvoi la valeur de titre.
     * @return titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Modifie titre.
     * @param titre la nouvelle valeur de titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Renvoi la valeur de dateAction.
     * @return dateAction
     */
    public Date getDateAction() {
        return dateAction;
    }

    /**
     * Modifie dateAction.
     * @param dateAction la nouvelle valeur de dateAction
     */
    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

}

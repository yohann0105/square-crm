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
package com.square.client.gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.square.client.gwt.client.controller.AppControllerConstants;

/**
 * Evenement d'ouverture de personne pour le bus.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class OpenPersonEvent extends GwtEvent<OpenPersonEventHandler> {

    /** Nom de l'évènement. */
    public static final String EVENT_NAME = "OpenPersonEvent";

    /** Paramètre url filtreEidOpportunite. */
    public static final String PARAM_FILTRE_EID_OPPORTUNITE = "filtreEidOpportunite";

    /** Paramètre url filtreidOpportunite. */
    public static final String PARAM_FILTRE_ID_OPPORTUNITE = "filtreidOpportunite";

    /** Paramètre url idOpportunite. */
    public static final String PARAM_ID_OPPORTUNITE = "idOpportunite";

    /** Paramètre url idAction. */
    public static final String PARAM_ID_ACTION = "idAction";

    /** Paramètre url id. */
    public static final String PARAM_ID = "id";

    /** Type de l'evenement. */
    public static final Type<OpenPersonEventHandler> TYPE = new Type<OpenPersonEventHandler>();

    /** Identifiant. */
    private Long id;

    /** Numéro de la personne. */
    private String num;

    /** Nom. */
    private String nom;

    /** Prenom. */
    private String prenom;

    /** Identifiant d'Action permet de deamnder l'affichage d'une action. */
    private Long idAction;

    /** Identifiant de l'opportunité utilisée pour filtrer les actions affichées. */
    private Long filtreidOpportunite;

    /** Identifiant externe de l'opportunité utilisée pour filtrer les actions affichées. */
    private String filtreEidOpportunite;

    /** Identifiant de l'opportunité permet de demander l'affichage d'une opportunité. */
    private Long idOpportunite;

    /** Identifiant de la nature de la personne. */
    private Long idNature;

    /**
     * Constructeur.
     * @param id identifiant de la personne
     * @param num numéro de la personne
     * @param nom nom de la personne
     * @param prenom prénom de la personne
     * @param idNature l'identifiant de la nature
     */
    public OpenPersonEvent(Long id, String num, String nom, String prenom, Long idNature) {
        super();
        this.id = id;
        this.num = num;
        this.nom = nom;
        this.prenom = prenom;
        this.idNature = idNature;
    }

    /**
     * Constructeur.
     * @param id l'identifiant
     * @param num le numéro client
     * @param nom le nom
     * @param prenom le prenom
     * @param idNature l'identifiant de la nature
     * @param idAction l'identifiant de l'action
     * @param idOpportunite l'identifiant de l'opportunité
     */
    public OpenPersonEvent(Long id, String num, String nom, String prenom, Long idNature, Long idAction, Long idOpportunite) {
        this(id, num, nom, prenom, idNature);
        this.idAction = idAction;
        this.idOpportunite = idOpportunite;
    }

    /**
     * Constructeur.
     * @param id l'identifiant de la personne
     * @param num le numéro client
     * @param nom le nom de la personne
     * @param prenom le prenom de la personne
     * @param idNature l'identifiant de la nature
     * @param idAction l'identifiant de l'action à ouvrir
     * @param filtreidOpportunite Identifiant de l'opportunité utilisée pour filtrer les actions
     * @param filtreEidOpportunite Identifiant externe de l'opportunité utilisée pour filtrer les actions
     */
    public OpenPersonEvent(Long id, String num, String nom, String prenom, Long idNature, Long idAction, Long filtreidOpportunite,
        String filtreEidOpportunite) {
        this(id, num, nom, prenom, idNature);
        this.idAction = idAction;
        this.filtreidOpportunite = filtreidOpportunite;
        this.filtreEidOpportunite = filtreEidOpportunite;
    }

    @Override
    public Type<OpenPersonEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(OpenPersonEventHandler handler) {
        handler.openPerson(this);
    }

    /**
     * Get the id value.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the nom value.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Get the prenom value.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Get the param value.
     * @return the idAction
     */
    public Long getIdAction() {
        return idAction;
    }

    /**
     * Set the param value.
     * @param idAction the idAction to set
     */
    public void setIdAction(Long idAction) {
        this.idAction = idAction;
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
     * Getter function.
     * @return the num
     */
    public String getNum() {
        return num;
    }

    /**
     * Getter function.
     * @return the filtreidOpportunite
     */
    public Long getFiltreidOpportunite() {
        return filtreidOpportunite;
    }

    /**
     * Getter function.
     * @return the filtreEidOpportunite
     */
    public String getFiltreEidOpportunite() {
        return filtreEidOpportunite;
    }

    /**
     * Transforme l'évènement en url.
     * @return l'url générée pour l'évènement.
     */
    public String toUrl() {
        final StringBuffer url = new StringBuffer(EVENT_NAME);
        url.append(AppControllerConstants.PARAM_SEPARATOR + PARAM_ID + AppControllerConstants.PARAM_ASSIGNMENT + this.id);
        if (idAction != null) {
            url.append(AppControllerConstants.PARAM_SEPARATOR + PARAM_ID_ACTION + AppControllerConstants.PARAM_ASSIGNMENT + this.idAction);
        }
        if (idOpportunite != null) {
            url.append(AppControllerConstants.PARAM_SEPARATOR + PARAM_ID_OPPORTUNITE + AppControllerConstants.PARAM_ASSIGNMENT + this.idOpportunite);
        }
        if (filtreidOpportunite != null) {
            url.append(AppControllerConstants.PARAM_SEPARATOR + PARAM_FILTRE_ID_OPPORTUNITE
                + AppControllerConstants.PARAM_ASSIGNMENT + this.filtreidOpportunite);
        }
        if (filtreEidOpportunite != null && !"".equals(filtreEidOpportunite.trim())) {
            url.append(AppControllerConstants.PARAM_SEPARATOR + PARAM_FILTRE_EID_OPPORTUNITE
                + AppControllerConstants.PARAM_ASSIGNMENT + this.filtreEidOpportunite);
        }
        return url.toString();
    }

    /**
     * Setter function.
     * @param num the num to set
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * Setter function.
     * @param filtreidOpportunite the filtreidOpportunite to set
     */
    public void setFiltreidOpportunite(Long filtreidOpportunite) {
        this.filtreidOpportunite = filtreidOpportunite;
    }

    /**
     * Setter function.
     * @param filtreEidOpportunite the filtreEidOpportunite to set
     */
    public void setFiltreEidOpportunite(String filtreEidOpportunite) {
        this.filtreEidOpportunite = filtreEidOpportunite;
    }

    /**
     * Récupère la valeur de idNature.
     * @return la valeur de idNature
     */
    public Long getIdNature() {
        return idNature;
    }

    /**
     * Définit la valeur de idNature.
     * @param idNature la nouvelle valeur de idNature
     */
    public void setIdNature(Long idNature) {
        this.idNature = idNature;
    }

}

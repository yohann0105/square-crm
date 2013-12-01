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

/**
 * Evenement d'ouverture de personne morale pour le bus.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class OpenPersonneMoraleEvent extends GwtEvent<OpenPersonneMoraleEventHandler> {

    /** Nom de l'évènement. */
    public static final String EVENT_NAME = "OpenPersonneMoraleEvent";

    /** Paramètre url id. */
    public static final String PARAM_ID = "id";

    /** Paramètre url idAction. */
    public static final String PARAM_ID_ACTION = "idAction";

    /** Type de l'evenement. */
    public static final Type<OpenPersonneMoraleEventHandler> TYPE = new Type<OpenPersonneMoraleEventHandler>();

    /**
     * L'identifiant de la société.
     */
    private Long id;

    /**
     * Raison sociale de la société.
     */
    private String raisonSociale;

    /** Identifiant d'Action permet de demander l'affichage d'une action. */
    private Long idAction;

    /** Identifiant de la nature de la personne morale. */
    private Long idNaturePersonne;

    /**
     * Constructeur de .
     * @param id L'identtifiant.
     * @param idNaturePersonne l'identifiant de la nature de la personne.
     * @param raisonSociale raison sociale.
     */
    public OpenPersonneMoraleEvent(Long id, Long idNaturePersonne, String raisonSociale) {
        super();
        this.id = id;
        this.idNaturePersonne = idNaturePersonne;
        this.raisonSociale = raisonSociale;
    }

    /**
     * Constructeur.
     * @param id l'identifiant
     * @param idNaturePersonne l'identifiant de la nature de la personne.
     * @param raisonSociale raison sociale
     * @param idAction identifiant de l'action à ouvrir
     */
    public OpenPersonneMoraleEvent(Long id, Long idNaturePersonne, String raisonSociale, Long idAction) {
        this(id, idNaturePersonne, raisonSociale);
        this.idAction = idAction;
    }

    @Override
    protected void dispatch(OpenPersonneMoraleEventHandler handler) {
        handler.openPersonMorale(this);
    }

    @Override
    public Type<OpenPersonneMoraleEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Retourne la valeur de id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Retourne la valeur de raisonSociale.
     * @return the raisonSociale
     */
    public String getRaisonSociale() {
        return raisonSociale;
    }

    /**
     * Get the param value.
     * @return the idAction
     */
    public Long getIdAction() {
        return idAction;
    }

    /**
     * Récupère la valeur de idNaturePersonne.
     * @return la valeur de idNaturePersonne
     */
    public Long getIdNaturePersonne() {
        return idNaturePersonne;
    }
}

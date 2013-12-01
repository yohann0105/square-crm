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
 * Evenement de l'affichage de ressource pour le bus.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class OpenRessourceEvent extends GwtEvent<OpenRessourceEventHandler> {

    /** Nom de l'évènement. */
    public static final String EVENT_NAME = "OpenRessourceEvent";

    /** Paramètre url id. */
    public static final String PARAM_ID = "id";

    /** Type de l'evenement. */
    public static final Type<OpenRessourceEventHandler> TYPE = new Type<OpenRessourceEventHandler>();

    /** Identifiant. */
    private Long id;

    /** nom. */
    private String nom;

    /** Prénom. */
    private String prenom;

    /**
     * Constructeur.
     * @param id l'identifiant de la ressource
     * @param nom le nom de la ressource
     * @param prenom le prénom de la ressource
     */
    public OpenRessourceEvent(Long id, String nom, String prenom) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    protected void dispatch(OpenRessourceEventHandler handler) {
        handler.openRessource(this);
    }

    @Override
    public Type<OpenRessourceEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Renvoi la valeur de id.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Renvoi la valeur de libelle.
     * @return libelle
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoi la valeur de prenom.
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

}

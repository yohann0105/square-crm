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
 * Evenement de creation de campagne pour le bus.
 * @author cblanchard - SCUB
 */
public class CreateCampagneEvent extends GwtEvent<CreateCampagneEventHandler> {

    /** Type de l'evenement. */
    public static final Type<CreateCampagneEventHandler> TYPE = new Type<CreateCampagneEventHandler>();

    /** Identifiant. */
    private Long id;

    /** Libelle. */
    private String libelle;

    /**
     * Constructeur.
     * @param id l'identifiant de la campagne
     * @param libelle le libelle de la campagne
     */
    public CreateCampagneEvent(Long id, String libelle) {
        super();
        this.id = id;
        this.libelle = libelle;
    }

    @Override
    protected void dispatch(CreateCampagneEventHandler handler) {
        handler.createCampagne(this);
    }

    @Override
    public Type<CreateCampagneEventHandler> getAssociatedType() {
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
    public String getLibelle() {
        return libelle;
    }

}

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
import com.square.client.gwt.client.model.OngletModel;

/**
 * Evenement de mise a jour d'un onglet.
 * @author jgoncalves - SCUB
 */
public class UpdateTabEvent extends GwtEvent<UpdateTabEventHandler> {
    /** Type de l'evenement. */
    public static final Type<UpdateTabEventHandler> TYPE = new Type<UpdateTabEventHandler>();

    /**
     * Onglet.
     */
    private OngletModel onglet;

    /**
     * Constructeur.
     * @param onglet .
     */
    public UpdateTabEvent(final OngletModel onglet) {
        super();
        this.onglet = onglet;
    }

    @Override
    public Type<UpdateTabEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UpdateTabEventHandler handler) {
        handler.updateTab(this);
    }

    /**
     * Récupération de l'onglet.
     * @return the onglet
     */
    public OngletModel getOnglet() {
        return onglet;
    }
}

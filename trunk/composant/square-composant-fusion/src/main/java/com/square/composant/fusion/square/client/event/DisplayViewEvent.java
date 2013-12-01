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
package com.square.composant.fusion.square.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Handler sur la demande de rafraichissement.
 * @author Goumard Stephane (Scub) - SCUB
 */
public class DisplayViewEvent extends GwtEvent<DisplayViewEventHandler> {
    /** Type de l'evenement. */
    public static final Type<DisplayViewEventHandler> TYPE = new Type<DisplayViewEventHandler>();

    /** Page à rafraichir. */
    private String token;

    /**
     * Le nom de la page à rafraichir.
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Constructeur.
     * @param token .
     */
    public DisplayViewEvent(String token) {
        super();
        this.token = token;
    }

    @Override
    public Type<DisplayViewEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DisplayViewEventHandler handler) {
        handler.displayView(this);
    }
}

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
package com.square.client.gwt.client.event.zoom;

import com.google.gwt.event.shared.GwtEvent;
import com.square.client.gwt.client.model.actions.ActionZoomModel;

/**
 * Evenement pour la sortie de survol d'un item d'action pour le zoom.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ActionZoomOutEvent extends GwtEvent<ActionZoomOutEventHandler> {

    /** Type de l'evenement. */
    public static final Type<ActionZoomOutEventHandler> TYPE = new Type<ActionZoomOutEventHandler>();

    /** Item action survolé. */
    private ActionZoomModel actionZoomOut;

    /** Flag isOpen. */
    private boolean isOpen;

    /**
     * Constructeur.
     */
    @Deprecated
    public ActionZoomOutEvent() {
        super();
    }

    /**
     * Constructeur.
     * @param actionZoomOut l'item survolé
     * @param isOpen flag isOpen
     */
    public ActionZoomOutEvent(ActionZoomModel actionZoomOut, boolean isOpen) {
        super();
        this.actionZoomOut = actionZoomOut;
        this.isOpen = isOpen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispatch(ActionZoomOutEventHandler handler) {
        handler.onMouseOut(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type<ActionZoomOutEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Get the value of actionZoomOut.
     * @return the actionZoomOut
     */
    public ActionZoomModel getActionZoomOut() {
        return actionZoomOut;
    }

    /**
     * Get the value of isOpen.
     * @return the isOpen
     */
    public boolean isOpen() {
        return isOpen;
    }

}

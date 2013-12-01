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
package com.square.composants.graphiques.lib.client.event.popups.minimizable;

import com.google.gwt.event.shared.GwtEvent;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskItem;

/**
 * Evènement pour maximiser une popup.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class MaximizePopupEvent extends GwtEvent<MaximizePopupEventHandler> {

    /** Type de l'evenement. */
    public static final Type<MaximizePopupEventHandler> TYPE = new Type<MaximizePopupEventHandler>();

    /** Item. */
    private DeskItem item;

    /**
     * Constructeur.
     * @param item l'item
     */
    public MaximizePopupEvent(DeskItem item) {
        super();
        this.item = item;
    }

    @Override
    protected void dispatch(MaximizePopupEventHandler handler) {
        handler.maximizePopup(this);
    }

    @Override
    public Type<MaximizePopupEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Get the item value.
     * @return the item
     */
    public DeskItem getItem() {
        return item;
    }

}

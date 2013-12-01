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
package com.square.composant.aide.gwt.client;

import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.HasContextMenuHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Image;

/**
 * Composant image d'une aide.
 * @author Ksouri Mohamed Ali - SCUB
 */
public class AideImage extends Image implements HasContextMenuHandlers {
    /**
     * constructeur.
     * @param image ressource image
     */
    public AideImage(ImageResource image) {
        super();
        sinkEvents(Event.ONDBLCLICK);
        sinkEvents(Event.ONCONTEXTMENU);
        setResource(image);
    }

    /**
     * constructeur.
     */
    public AideImage() {
        super();
        sinkEvents(Event.ONCONTEXTMENU);
        sinkEvents(Event.ONDBLCLICK);
    }

    @Override
    public HandlerRegistration addContextMenuHandler(ContextMenuHandler handler) {
        return addHandler(handler, ContextMenuEvent.getType());
    }

    @Override
    public void onBrowserEvent(Event event) {
        // We have to clear the unhandled event before firing handlers because the
        // handlers could trigger onLoad, which would refire the event.

        super.onBrowserEvent(event);
        if (event.getTypeInt() == Event.ONCONTEXTMENU) {
            event.stopPropagation();
            event.preventDefault();
        }
    }

}

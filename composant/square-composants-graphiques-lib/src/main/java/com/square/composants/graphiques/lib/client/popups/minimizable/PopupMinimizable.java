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
package com.square.composants.graphiques.lib.client.popups.minimizable;

import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEvent;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.MaximizePopupEvent;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.MinimizePopupEvent;

/**
 * Popup pouvant être minimisé dans la barre des taches.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class PopupMinimizable implements IsMinimizable {

    private DeskItem deskItem;

    private HandlerManager eventBus;

    private Popup popup;

    /**
     * Constructeur.
     * @param popup la popup qui peut etre réduite
     * @param title le titre à afficher dans l'item
     * @param minimizeWidget widget qui permet de minimiser la popup
     */
    public PopupMinimizable(final Popup popup, String title, HasClickHandlers minimizeWidget) {
        this.popup = popup;
        this.deskItem = new DeskItem(title, getMaximizeHandler());

        // on ajoute le handler au widget
        minimizeWidget.addClickHandler(getMinimizeHandler());
    }

    @Override
    public void minimize() {
        popup.hide();
        eventBus.fireEvent(new MinimizePopupEvent(deskItem));
    }

    @Override
    public void maximize() {
        popup.show();
        enableMinimizeWidget(true);
        eventBus.fireEvent(new MaximizePopupEvent(deskItem));
    }

    /**
     * Get the minimizeHandler value.
     * @return the minimizeHandler
     */
    private ClickHandler getMinimizeHandler() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                minimize();
            }
        };
    }

    /**
     * Get the maximizeHandler value.
     * @return the maximizeHandler
     */
    private ClickHandler getMaximizeHandler() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                maximize();
            }
        };
    }

    @Override
    public void setTitle(String title) {
        deskItem.setHTML(title);
    }

    @Override
    public void setEventBus(HandlerManager eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void enableMinimizeWidget(boolean enabled) {
        eventBus.fireEvent(new EnableMinimizeWidgetEvent(enabled));
    }

}

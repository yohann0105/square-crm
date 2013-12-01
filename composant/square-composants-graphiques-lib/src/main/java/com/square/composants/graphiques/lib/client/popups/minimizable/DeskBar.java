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

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.square.composants.graphiques.lib.client.bundle.SquareLibResources;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.MaximizePopupEvent;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.MaximizePopupEventHandler;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.MinimizePopupEvent;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.MinimizePopupEventHandler;

/**
 * DeskBar permettant de regrouper des popups minimisées.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class DeskBar extends Composite {

    private FlowPanel panel;

    private HandlerManager eventBus;

    private Integer maxItem;

    /**
     * Constructeur.
     * @param eventBus bus d'evenement
     */
    public DeskBar(HandlerManager eventBus) {
        this(null, eventBus);
    }

    /**
     * Constructeur.
     * @param maxItem nb d'item max autorisé
     * @param eventBus bus d'evenement
     */
    public DeskBar(final Integer maxItem, final HandlerManager eventBus) {
        this.eventBus = eventBus;
        this.maxItem = maxItem;

        eventBus.addHandler(MinimizePopupEvent.TYPE, new MinimizePopupEventHandler() {
            @Override
            public void minimizePopup(MinimizePopupEvent event) {
                if (maxItem == null || panel.getWidgetCount() < maxItem.intValue()) {
                    addItem(event.getItem());
                }
            }
        });
        eventBus.addHandler(MaximizePopupEvent.TYPE, new MaximizePopupEventHandler() {
            @Override
            public void maximizePopup(MaximizePopupEvent event) {
                removeItem(event.getItem());
            }
        });

        panel = new FlowPanel();

        final HorizontalPanel conteneur = new HorizontalPanel();
        conteneur.setStylePrimaryName(SquareLibResources.INSTANCE.css().deskBar());
        conteneur.add(panel);
        conteneur.setCellHorizontalAlignment(panel, HasHorizontalAlignment.ALIGN_RIGHT);

        initWidget(conteneur);
    }

    /**
     * Ajoute un objet qui peut être minimisé dans la deskbar.
     * @param minimizableWidget l'objet
     */
    public void addPopup(IsMinimizable minimizableWidget) {
        minimizableWidget.setEventBus(eventBus);
        // on désactive le bouton de minimisation si on a atteint la limite
        if (maxItem != null && panel.getWidgetCount() == maxItem.intValue()) {
            minimizableWidget.enableMinimizeWidget(false);
        }
    }

    /**
     * Ajoute un item dans la barre.
     * @param item l'item à ajouter
     */
    private void addItem(DeskItem item) {
        panel.add(item);
    }

    /**
     * Supprime un item dans la barre.
     * @param item l'item à supprimer
     */
    private void removeItem(DeskItem item) {
        panel.remove(item);
    }

    /**
     * Get the eventBus value.
     * @return the eventBus
     */
    public HandlerManager getEventBus() {
        return eventBus;
    }

}

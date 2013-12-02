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
package com.square.composant.cotisations.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composants.graphiques.lib.client.bundle.SquareLibResources;

/**
 * Point d'entrée pour le module cotisations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ComposantCotisationsModule implements EntryPoint {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onModuleLoad() {
        firefox3compatibility();

        StyleInjector.inject(SquareLibResources.INSTANCE.css().getText());

        final HandlerManager eventBus = new HandlerManager(this);
        final Long uidPersonne = 517064L;

        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setWidth("944px");
        final ComposantCotisations composantCotisations = new ComposantCotisations(eventBus, new ComposantCotisationsViewImpl(), uidPersonne);
        composantCotisations.showPresenter(conteneur);
        RootPanel.get().add(conteneur);
    }

    private static native void firefox3compatibility()
    /*-{
       if (!$doc.getBoxObjectFor) { $doc.getBoxObjectFor = function (element) {
       var box = element.getBoundingClientRect();
       return { "x" : box.left, "y" : box.top, "width" : box.width, "height" : box.height, "screenX": box.left, "screenY":box.top }; } }
       }-*/;
}

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
package com.square.composant.flux.opportunite.client;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composant.flux.opportunite.client.presenter.PanelQuotasPresenter;
import com.square.composant.flux.opportunite.client.presenter.PopupModifQuotasPresenter;
import com.square.composant.flux.opportunite.client.presenter.PanelQuotasPresenter.PanelQuotasView;
import com.square.composant.flux.opportunite.client.presenter.PopupModifQuotasPresenter.PopupModifQuotasView;
import com.square.composant.flux.opportunite.client.view.PanelQuotasViewImpl;
import com.square.composant.flux.opportunite.client.view.PopupModifQuotasViewImpl;
import com.square.composants.graphiques.lib.client.bundle.SquareLibResources;

/**
 * Point d'entré pour le composant des flux d'opportunités.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public final class ComposantFluxOpportuniteModule implements EntryPoint {
    /**
     * {@inheritDoc}
     */
    public void onModuleLoad() {
        firefox3compatibility();

        StyleInjector.inject(SquareLibResources.INSTANCE.css().getText());

        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setWidth("944px");
        conteneur.setSpacing(10);
        final Long uidRessource = 3L;
        final HandlerManager eventBus = new HandlerManager(null);

        // Panel des quotas
        final PanelQuotasView panelQuotaView = new PanelQuotasViewImpl();
        final PanelQuotasPresenter panelQuotasPresenter = new PanelQuotasPresenter(eventBus, panelQuotaView, uidRessource);
        panelQuotasPresenter.showPresenter(conteneur);

        // Bouton pour ouvrir la popup de modification des quotas
        final DecoratedButton btnPopup = new DecoratedButton("Modifier quotas");
        btnPopup.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final PopupModifQuotasView popupView = new PopupModifQuotasViewImpl();
                final List<Long> listeIdsRessources = new ArrayList<Long>();
                listeIdsRessources.add(3L);
                final PopupModifQuotasPresenter popupPresenter = new PopupModifQuotasPresenter(eventBus, popupView, listeIdsRessources);
                popupPresenter.showPresenter(null);
            }
        });
        conteneur.add(btnPopup);

        RootPanel.get().add(conteneur);
    }

    private static native void firefox3compatibility() /*-{
    if (!$doc.getBoxObjectFor) { $doc.getBoxObjectFor = function (element) {
        var box = element.getBoundingClientRect();
        return { "x" : box.left, "y" : box.top, "width" : box.width, "height" : box.height, "screenX": box.left, "screenY":box.top }; } }
    }-*/;
}
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
package com.square.composant.ged.square.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composant.ged.square.client.presenter.ListeCompacteDocumentsPresenter;
import com.square.composant.ged.square.client.service.DocumentsServiceGwt;
import com.square.composant.ged.square.client.service.DocumentsServiceGwtAsync;
import com.square.composant.ged.square.client.view.listecompacte.ListeCompacteDocumentsViewImpl;
import com.square.composants.graphiques.lib.client.bundle.SquareLibResources;

/**
 * Point d'entré de l'application.
 * @author Goumard Stéphane (stephane.goumard@scub.net) - SCUB
 */
public final class ClientEntryPointGwt implements EntryPoint {

	private static final Long NUM_CLIENT = 95189490L;
    /**
     * {@inheritDoc}
     */
    public void onModuleLoad() {
        StyleInjector.inject(SquareLibResources.INSTANCE.css().getText());
        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setWidth("944px");

        final DocumentsServiceGwtAsync documentRpcService = (DocumentsServiceGwtAsync) GWT.create(DocumentsServiceGwt.class);

        final HandlerManager eventBus = new HandlerManager(null);
        // new AjoutDocumentPresenter(eventBus, "2239978", 6L, new AjoutDocumentViewImpl(), documentRpcService).showPresenter(conteneur);
        // new ListeDetailleeDocumentsPresenter(eventBus, "1015036", new ListeDetailleeDocumentsViewImpl(), documentRpcService).showPresenter(conteneur);
        new ListeCompacteDocumentsPresenter(eventBus, 6L, "2239978", new ListeCompacteDocumentsViewImpl(), documentRpcService).showPresenter(conteneur);

        //new AjoutDocumentPresenter(eventBus, "2239978", NUM_CLIENT, new AjoutDocumentViewImpl(), documentRpcService).showPresenter(conteneur);
        RootPanel.get().add(conteneur);
    }
}

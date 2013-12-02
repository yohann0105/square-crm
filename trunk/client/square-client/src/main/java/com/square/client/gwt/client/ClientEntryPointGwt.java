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
package com.square.client.gwt.client;

import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppController;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.controller.AppControllerMessages;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.service.ConstantesServiceGwt;
import com.square.client.gwt.client.service.ConstantesServiceGwtAsync;
import com.square.client.gwt.client.service.RessourceServiceGwt;
import com.square.client.gwt.client.service.RessourceServiceGwtAsync;
import com.square.composants.graphiques.lib.client.bundle.SquareLibResources;

/**
 * Point d'entré de l'application.
 * @author Goumard Stéphane (stephane.goumard@scub.net) - SCUB
 */
public final class ClientEntryPointGwt implements EntryPoint {

    private RessourceServiceGwtAsync ressourceService;

    private ConstantesServiceGwtAsync constantesServiceGwtAsync;

    private final AppControllerMessages appControllerMessages = (AppControllerMessages) GWT.create(AppControllerMessages.class);

    /**
     * {@inheritDoc}
     */
    public void onModuleLoad() {
        StyleInjector.inject(SquareResources.INSTANCE.css().getText());
        StyleInjector.inject(SquareLibResources.INSTANCE.css().getText());

        firefox3compatibility();
        ressourceService = GWT.create(RessourceServiceGwt.class);
        this.constantesServiceGwtAsync = GWT.create(ConstantesServiceGwt.class);
        LoadingPopup.afficher(new LoadingPopupConfiguration(appControllerMessages.chargementApplication()));
        ressourceService.getRessourceConnectee(new AsyncCallback<DimensionRessourceModel>() {
            @Override
            public void onSuccess(final DimensionRessourceModel ressource) {
                constantesServiceGwtAsync.getConstantes(new AsyncCallback<ConstantesModel>() {
                    @Override
                    public void onFailure(Throwable caught) {
                    	LoadingPopup.stopAll();
                        ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
                    }

                    @Override
                    public void onSuccess(ConstantesModel constantesGwt) {
                        final AppControllerConstants appControllerConstants = (AppControllerConstants) GWT.create(AppControllerConstants.class);
                        new AppController(new HandlerManager(null), appControllerConstants, appControllerMessages, ressource, constantesGwt)
                        .showPresenter(RootPanel.get("slotPrincipal"));
                        LoadingPopup.stop();
                    }
                });
            }

            @Override
            public void onFailure(Throwable caught) {
                ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
            }
        });
    }

    private static native void firefox3compatibility()
    /*-{
        if (!$doc.getBoxObjectFor) { $doc.getBoxObjectFor = function (element) {
        var box = element.getBoundingClientRect();
        return { "x" : box.left, "y" : box.top, "width" : box.width, "height" : box.height, "screenX": box.left, "screenY":box.top }; } }
     }-*/;
}

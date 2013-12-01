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
package com.square.composant.prestations.square.client;

import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.prestations.square.client.ComposantPrestations.ComposantPrestationsView;
import com.square.composant.prestations.square.client.content.i18n.ComposantPrestationsConstants;

/**
 * Vue du composant prestations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ComposantPrestationsViewImpl extends Composite implements ComposantPrestationsView {

    private VerticalPanel conteneur;

    /**
     * Constructeur.
     */
    public ComposantPrestationsViewImpl() {
        conteneur = new VerticalPanel();
        conteneur.setWidth(ComposantPrestationsConstants.POURCENT_100);
        conteneur.addStyleName(ComposantPrestations.RESOURCES.css().composantPrestations());

        this.initWidget(conteneur);
        this.setWidth(ComposantPrestationsConstants.POURCENT_100);
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(config);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasWidgets getConteneur() {
        return conteneur;
    }

}

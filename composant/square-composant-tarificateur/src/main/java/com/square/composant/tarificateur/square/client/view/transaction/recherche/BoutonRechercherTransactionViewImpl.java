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
package com.square.composant.tarificateur.square.client.view.transaction.recherche;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.tarificateur.square.client.presenter.transaction.recherche.BoutonRechercherTransactionPresenter.BoutonRechercherTransactionView;

/**
 * Vue permettant l'accès à la recherche d'opportunité par numéro de transaction.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class BoutonRechercherTransactionViewImpl extends Composite implements BoutonRechercherTransactionView {

    private BoutonRechercherTransactionViewImplConstants viewConstants;

    private DecoratedButton button;

    /**
     * Constructeur.
     */
    public BoutonRechercherTransactionViewImpl() {
        viewConstants = GWT.create(BoutonRechercherTransactionViewImplConstants.class);
        button = new DecoratedButton();
        button.setText(viewConstants.textBtnRechercherTransaction());
        this.initWidget(button);
        // On cache le widget par défaut
        this.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget asWidget() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afficher(boolean visible) {
        this.setVisible(visible);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onRpcServiceFailure(Throwable exception) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(new ErrorPopupConfiguration(exception));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasClickHandlers getBtnClickHandler() {
        return button;
    }

}

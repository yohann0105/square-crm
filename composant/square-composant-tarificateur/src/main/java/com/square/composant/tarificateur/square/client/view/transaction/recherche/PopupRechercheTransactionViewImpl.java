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
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.presenter.transaction.recherche.PopupRechercheTransactionPresenter.PopupRechercheTransactionView;

/**
 * Vue permettant de rechercher une opportunité à partir d'un numéro de transaction.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class PopupRechercheTransactionViewImpl extends Popup implements PopupRechercheTransactionView {

    private PopupRechercheTransactionViewImplConstants viewConstants = GWT.create(PopupRechercheTransactionViewImplConstants.class);

    private VerticalPanel container;

    private DecoratedTextBox tbNumTransaction;

    private DecoratedButton btnRechercherOpp;

    private DecoratedButton btnAnnuler;

    private FocusPanel focusPanel;

    /**
     * Constructeur.
     */
    public PopupRechercheTransactionViewImpl() {
        super(ComposantTarificateur.CONSTANTS.titrePopupRechercheTransaction(), false, false, true);

        // Label / Champ de recherche numéro transaction
        final Label lNumTransaction = new Label(viewConstants.libelleNumTransaction());
        tbNumTransaction = new DecoratedTextBox();

        final HorizontalPanel hpNumTransaction = new HorizontalPanel();
        hpNumTransaction.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        hpNumTransaction.setSpacing(5);
        hpNumTransaction.add(lNumTransaction);
        hpNumTransaction.add(tbNumTransaction);

        // Bouton pour lancer la recherche
        btnRechercherOpp = new DecoratedButton(viewConstants.libelleBtnRechercherOpp());
        btnAnnuler = new DecoratedButton(viewConstants.libelleAnnuler());

        final HorizontalPanel hpBoutons = new HorizontalPanel();
        hpBoutons.setWidth(ComposantTarificateurConstants.POURCENT_100);
        hpBoutons.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        hpBoutons.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        hpBoutons.setSpacing(5);
        hpBoutons.add(btnRechercherOpp);
        hpBoutons.add(btnAnnuler);

        container = new VerticalPanel();
        container.add(hpNumTransaction);
        container.add(hpBoutons);
        focusPanel = new FocusPanel(container);
        this.setWidget(focusPanel);
        this.addStyleName(ComposantTarificateur.RESOURCES.css().popupRechercheOppTransaction());
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
        if (visible) {
            this.show();

            DeferredCommand.addCommand(new Command() {
                @Override
                public void execute() {
                    // On met le focus sur le champ de recherche
                    tbNumTransaction.setFocus(true);
                }
            });
        } else {
            this.hide();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasClickHandlers getBtnRechercheClickHandler() {
        return btnRechercherOpp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<String> getNumTransactionValue() {
        return tbNumTransaction;
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
    public HasClickHandlers getBtnAnnulerClickHandler() {
        return btnAnnuler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stopAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PopupRechercheTransactionViewImplConstants getViewConstants() {
        return viewConstants;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasAllKeyHandlers getFocusPanelAllKeyHandlers() {
        return focusPanel;
    }

}

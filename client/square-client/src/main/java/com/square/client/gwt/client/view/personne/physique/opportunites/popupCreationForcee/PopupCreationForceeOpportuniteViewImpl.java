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
package com.square.client.gwt.client.view.personne.physique.opportunites.popupCreationForcee;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.presenter.personne.physique.opportunites.PopupCreationForceeOpportunitePresenter.PopupCreationForceeOpportuniteView;

/**
 * Implémentation de la vue de la popup de création forcée d'une opportunité.
 * @author cblanchard - SCUB
 */
public class PopupCreationForceeOpportuniteViewImpl extends Popup implements PopupCreationForceeOpportuniteView {

    /** View constants. */
    private static PopupCreationForceeOpportuniteViewImplConstants viewConstants =
        (PopupCreationForceeOpportuniteViewImplConstants) GWT.create(PopupCreationForceeOpportuniteViewImplConstants.class);

    /** View constants. */
    private static PopupCreationForceeOpportuniteViewImplDebugIdConstants viewDebugIdConstants =
        (PopupCreationForceeOpportuniteViewImplDebugIdConstants) GWT.create(PopupCreationForceeOpportuniteViewImplDebugIdConstants.class);

    /** Le conteneur global. */
    private VerticalPanel conteneur;

    /** Le bouton oui. */
    private DecoratedButton btnValider;

    /** Le bouton non. */
    private DecoratedButton btnRefuser;

    /**
     * Constructeur.
     */
    public PopupCreationForceeOpportuniteViewImpl() {
        super(viewConstants.titrePopup(), false, true, true);
        conteneur = new VerticalPanel();
        conteneur.setSpacing(10);
        construirePopup();

        this.setWidget(conteneur, 0);
        this.addStyleName(SquareResources.INSTANCE.css().popupCreationForceeOpp());
    }

    private void construirePopup() {
        // Construction de la zone de text
        final VerticalPanel vpText = new VerticalPanel();
        final Label ltextLigne1 = new Label(viewConstants.textLigne1());
        final Label ltextLigne2 = new Label(viewConstants.textLigne2());
        vpText.add(ltextLigne1);
        vpText.add(ltextLigne2);
        vpText.setCellHorizontalAlignment(ltextLigne1, HasAlignment.ALIGN_CENTER);
        vpText.setCellHorizontalAlignment(ltextLigne2, HasAlignment.ALIGN_CENTER);
        conteneur.add(vpText);
        // construction de la zone de bouton
        final HorizontalPanel hpBoutons = new HorizontalPanel();
        btnValider = new DecoratedButton(viewConstants.libelleOui());
        btnValider.ensureDebugId(viewDebugIdConstants.debugIdBtnValider());
        btnRefuser = new DecoratedButton(viewConstants.libelleNon());
        btnRefuser.ensureDebugId(viewDebugIdConstants.debugIdBtnRefuser());
        hpBoutons.add(btnValider);
        hpBoutons.add(btnRefuser);
        hpBoutons.setSpacing(5);
        conteneur.add(hpBoutons);
        conteneur.setCellHorizontalAlignment(hpBoutons, HasAlignment.ALIGN_CENTER);
    }

    @Override
    public Widget asWidget() {
        return null;
    }

    @Override
    public void showPopup() {
        this.show();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                btnValider.setFocus(true);
            }
        });
    }

    @Override
    public HasClickHandlers getBtnValider() {
        return btnValider;
    }

    @Override
    public HasClickHandlers getBtnRefuser() {
        return btnRefuser;
    }

    @Override
    public void hidePopup() {
        this.hide();
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

}

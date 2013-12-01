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
package com.square.client.gwt.client.view.personne.physique.creation;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.presenter.personne.physique.PopupModificationPersonneDoublonPresenter.PopupModificationPersonneDoublonView;

/**
 * Implémentation de la vue de la popup de confirmation de modification d'une personne suite à la détection de doublons.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class PopupModificationPersonneDoublonViewImpl extends Popup implements PopupModificationPersonneDoublonView {

    private static PopupModificationPersonneDoublonViewImplConstants viewConstants = GWT.create(PopupModificationPersonneDoublonViewImplConstants.class);

    private FocusPanel focusPanel;

    private DecoratedButton btnEnregistrer;

    private DecoratedButton btnEnregistrerPuisFusionner;

    private DecoratedButton btnAnnuler;

    /**
     * Constructeur par défaut.
     */
    public PopupModificationPersonneDoublonViewImpl() {
        super(viewConstants.titrePopup(), false, false, true);

        focusPanel = new FocusPanel();
        focusPanel.setWidth(AppControllerConstants.POURCENT_100);

        // Ajout des boutons
        final VerticalPanel contenu = new VerticalPanel();
        contenu.setSpacing(10);
        contenu.setWidth(AppControllerConstants.POURCENT_100);

        final Label lAvertissement = new Label(viewConstants.msgAvertissement());

        final HorizontalPanel pBoutons = new HorizontalPanel();
        pBoutons.setSpacing(10);
        pBoutons.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        pBoutons.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

        btnEnregistrer = new DecoratedButton(viewConstants.labelBtnEnregistrer());
        btnEnregistrerPuisFusionner = new DecoratedButton(viewConstants.labelBtnEnregistrerPuisFusionner());
        btnAnnuler = new DecoratedButton(viewConstants.labelBtnAnnuler());

        pBoutons.add(btnEnregistrer);
        pBoutons.add(btnEnregistrerPuisFusionner);
        pBoutons.add(btnAnnuler);

        contenu.add(lAvertissement);
        contenu.add(pBoutons);
        contenu.setCellHorizontalAlignment(lAvertissement, HasHorizontalAlignment.ALIGN_LEFT);
        contenu.setCellHorizontalAlignment(pBoutons, HasHorizontalAlignment.ALIGN_CENTER);

        focusPanel.setWidget(contenu);
        this.setWidget(focusPanel, 0);
        this.setWidth(PopupModificationPersonneDoublonViewImplConstants.LARGEUR_POPUP);
        this.addStyleName(SquareResources.INSTANCE.css().popupModificationPersonneDoublon());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget asWidget() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afficherPopup() {
        this.show();
        DeferredCommand.addCommand(new Command() {

            @Override
            public void execute() {
                btnAnnuler.setFocus(true);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cacherPopup() {
        this.hide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasClickHandlers getClickHandlerBtnAnnuler() {
        return btnAnnuler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasClickHandlers getClickHandlerBtnEnregistrer() {
        return btnEnregistrer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasClickHandlers getClickHandlerBtnEnregistrerPuisFusionner() {
        return btnEnregistrerPuisFusionner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasAllKeyHandlers getAllKeyHandlersFocusPanel() {
        return focusPanel;
    }

}

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
package com.square.client.gwt.client.view.personne.coordonnees;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.presenter.personne.PopupCoordonneesAdressePresenter.PopupCoordonneesAdresseView;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.popups.minimizable.IsMinimizable;
import com.square.composants.graphiques.lib.client.popups.minimizable.PopupMinimizable;

/**
 * Vue de la popup des coordonnées d'une adresse.
 */
public class PopupCoordonneesAdresseViewImpl extends Composite implements PopupCoordonneesAdresseView {

    /** Constantes de la vue. */
    private PopupCoordonneesAdresseViewImplConstants viewConstants;

    /** Bloc adresse. */
    private AdresseCreationViewImpl blocAdresse;

    /** Popup affichage Selection Multiple. **/
    private Popup popup;

    /** Bouton annuler. **/
    private DecoratedButton bAnnuler;

    /** Bouton selectionner. **/
    private DecoratedButton bEnregistrer;

    private DecoratedButton btnReduire;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    private FocusPanel focusPopupPanel;

    private PopupMinimizable minimizablePopup;

    /**
     * Constructeur.
     * @param constantes les constantes.
     */
    public PopupCoordonneesAdresseViewImpl(ConstantesModel constantes) {
        this.viewConstants = GWT.create(PopupCoordonneesAdresseViewImplConstants.class);
        this.iconeErreurChampManager = new IconeErreurChampManager();

        bAnnuler = new DecoratedButton(this.viewConstants.annuler());
        bEnregistrer = new DecoratedButton(this.viewConstants.enregistrer());
        btnReduire = new DecoratedButton(this.viewConstants.reduire());
        popup = new Popup(viewConstants.titrePopupAjoutAdresse(), false, true, true);
        blocAdresse = new AdresseCreationViewImpl(this.iconeErreurChampManager, 0, constantes.isHasRoleAdmin());
        blocAdresse.afficheBlocCoordonneesFrance(true);

        final HorizontalPanel popupButtonsPanel = new HorizontalPanel();
        popupButtonsPanel.setSpacing(5);
        popupButtonsPanel.add(bEnregistrer);
        popupButtonsPanel.add(btnReduire);
        popupButtonsPanel.add(bAnnuler);

        // on en fait une popup minimisable
        minimizablePopup = new PopupMinimizable(popup, viewConstants.titrePopupAjoutAdresse(), btnReduire);

        final VerticalPanel panelPopup = new VerticalPanel();
        panelPopup.setWidth(PopupCoordonneesAdresseViewImplConstants.LARGEUR_POPUP);
        panelPopup.add(blocAdresse);
        panelPopup.add(popupButtonsPanel);
        panelPopup.setCellHorizontalAlignment(popupButtonsPanel, HasHorizontalAlignment.ALIGN_CENTER);

        focusPopupPanel = new FocusPanel();
        focusPopupPanel.setWidth(AppControllerConstants.POURCENT_100);
        focusPopupPanel.add(panelPopup);

        popup.setWidget(focusPopupPanel, 0);
        popup.addStyleName(SquareResources.INSTANCE.css().popupAjoutAdresse());
    }

    @Override
    public AdresseCreationViewImpl getBlocAdresse() {
        return blocAdresse;
    }

    @Override
    public HasClickHandlers getBtnEnregistrer() {
        return bEnregistrer;
    }

    @Override
    public HasClickHandlers getBtnAnnuler() {
        return bAnnuler;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void afficherPopup() {
        popup.show();
    }

    @Override
    public void cacherPopup() {
        popup.hide();
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stop();
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(config);
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasAllKeyHandlers getAllKeyHandlersFocusPanel() {
        return focusPopupPanel;
    }

    @Override
    public void activerBoutonReduire(boolean enabled) {
        btnReduire.setEnabled(enabled);
    }

    @Override
    public IsMinimizable getMinimizablePopup() {
        return minimizablePopup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PopupCoordonneesAdresseViewImplConstants getViewConstants() {
        return viewConstants;
    }
}

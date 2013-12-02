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
/**
 * 
 */
package com.square.client.gwt.client.view.personne.relations;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.presenter.personne.PersonneRelationsModePresenter.PersonneRelationsModeView;

/**
 * Implementation de la vue gestion des Modes de representation des vues.
 * @author sgoumard (scub) - SCUB
 */
public class PersonneRelationsModeViewImpl extends Composite implements PersonneRelationsModeView {

    /** Boutton pour ajouter une relation. */
    private DecoratedButton btAjouterRelationGenerale;

    /** Boutton pour enregistrer une relation. */
    private DecoratedButton btEnregistrerRelationGenerale;

    /** Bouton Mode Graphique. **/
    private DecoratedButton btChangementDeMode;

    /** Conteneur pour les representations. **/
    private SimplePanel conteneurModeRelation;

    /** Constantes de la vue. **/
    private PersonneRelationsModeViewImplConstants constants;

    /** Constantes de la vue de debug. **/
    private PersonneRelationsModeViewImplDebugIdConstants viewDebugIdConstants;

    /**
     * Constructeur.
     */
    public PersonneRelationsModeViewImpl() {
        constants = GWT.create(PersonneRelationsModeViewImplConstants.class);
        viewDebugIdConstants = GWT.create(PersonneRelationsModeViewImplDebugIdConstants.class);

        btAjouterRelationGenerale = new DecoratedButton(constants.btAjouterRelation());
        btEnregistrerRelationGenerale = new DecoratedButton(constants.btEnregistrerRelation());
        btChangementDeMode = new DecoratedButton();
        btChangementDeMode.ensureDebugId(viewDebugIdConstants.debugIdBtChangementDeMode());

        final HorizontalPanel panelButtons = new HorizontalPanel();
        panelButtons.setSpacing(10);
        panelButtons.add(btEnregistrerRelationGenerale);
        panelButtons.add(btAjouterRelationGenerale);
        panelButtons.add(btChangementDeMode);

        conteneurModeRelation = new SimplePanel();
        conteneurModeRelation.setWidth(AppControllerConstants.POURCENT_100);

        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.add(panelButtons);
        conteneur.setCellHorizontalAlignment(panelButtons, HasAlignment.ALIGN_RIGHT);
        conteneur.add(conteneurModeRelation);
        initWidget(conteneur);
    }

    @Override
    public HasClickHandlers btChangementDeMode() {
        return btChangementDeMode;
    }

    @Override
    public HasWidgets getConteneurModeRelation() {
        return conteneurModeRelation;
    }

    @Override
    public void passerEnModeEdition() {
        btChangementDeMode.setText(constants.btChangementDeModeGraphique());
    }

    @Override
    public void passerEnModeGraphique() {
        btChangementDeMode.setText(constants.btChangementDeModeEdition());
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasClickHandlers getBtAjouterRelationGenerale() {
        return btAjouterRelationGenerale;
    }

    @Override
    public void afficherChangementMode(boolean afficher) {
        btChangementDeMode.setVisible(afficher);
    }

    @Override
    public HasClickHandlers getBtEnregistrerRelationGenerale() {
        return btEnregistrerRelationGenerale;
    }

    @Override
    public void afficherPopupErreur(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

}

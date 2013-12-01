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
package com.square.client.gwt.client.view.personne.physique.opportunites.gestion;

import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.presenter.personne.physique.opportunites.PersonnePhysiqueOpportunitePresenter.OpportuniteBlocView;
import com.square.client.gwt.client.presenter.personne.physique.opportunites.PersonnePhysiqueOpportunitePresenter.PersonnePhysiqueOpportunitesView;

/**
 * Vue pour la gestion des opportunités.
 * @author cblanchard - SCUB
 */
public class PersonnePhysiqueOpportunitesViewImpl extends Composite implements PersonnePhysiqueOpportunitesView {

    /** View constants. */
    private static PersonnePhysiqueOpportunitesViewImplConstants viewConstants =
        (PersonnePhysiqueOpportunitesViewImplConstants) GWT.create(PersonnePhysiqueOpportunitesViewImplConstants.class);

    /** Le conteneur des opportunités. */
    private VerticalPanel conteneurPrincipal;

    /**
     * mode de connexion.
     */
    private boolean isAdmin;

    /** Constructeur. */
    public PersonnePhysiqueOpportunitesViewImpl(boolean isAdmin) {
        this.isAdmin = isAdmin;
        conteneurPrincipal = new VerticalPanel();
        conteneurPrincipal.setWidth(AppControllerConstants.POURCENT_100);
        this.initWidget(conteneurPrincipal);

    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration) {
        LoadingPopup.afficher(loadingPopupConfiguration);
    }

    @Override
    public void onRpcServiceSucess() {
        LoadingPopup.stopAll();
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    @Override
    public PersonnePhysiqueOpportunitesViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public void clear() {
        conteneurPrincipal.clear();
    }

    @Override
    public OpportuniteBlocView creerBloc() {
        final OpportuniteBlocViewImpl bloc = new OpportuniteBlocViewImpl(this.isAdmin);
        conteneurPrincipal.add(bloc);
        return bloc;
    }

}

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
package com.square.client.gwt.client.view.personne.relations;

import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.presenter.personne.PersonneRelationsPresenter.PersonneRelationsView;

/**
 * Vue relation familiale.
 * @author cblanchard - SCUB
 */
public class PersonneRelationsViewImpl extends Composite implements PersonneRelationsView {

    /** Les constantes de la page. */
    private PersonneRelationsViewImplConstants constants;

    /** Le conteneur des relations. */
    private VerticalPanel conteneurRelation;

    /**
     * Constructeur.
     */
    public PersonneRelationsViewImpl() {
        constants = (PersonneRelationsViewImplConstants) GWT.create(PersonneRelationsViewImplConstants.class);

        conteneurRelation = new VerticalPanel();
        conteneurRelation.setSpacing(10);
        conteneurRelation.setWidth(AppControllerConstants.POURCENT_100);
        conteneurRelation.setStylePrimaryName(SquareResources.INSTANCE.css().personneRelations());

        this.initWidget(conteneurRelation);
        this.setWidth(AppControllerConstants.POURCENT_100);
    }

    @Override
    public void afficherLoadingPopup() {
        LoadingPopup.afficher(new LoadingPopupConfiguration(constants.chergementRelationEnCours()));
    }

    @Override
    public void stopAllLoadingPopup() {
        LoadingPopup.stopAll();
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stop();
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void ajouterBlocRelation(PersonneRelationsBlocViewImpl bloc) {
        conteneurRelation.add(bloc);
    }

    @Override
    public PersonneRelationsViewImplConstants getViewConstants() {
        return constants;
    }

    @Override
    public void supprimerBlocs() {
        conteneurRelation.clear();
    }

}

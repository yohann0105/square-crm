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
package com.square.client.gwt.client.view.personne.action;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.presenter.personne.PersonneActionsPresenter.PersonneBlocActionView;
import com.square.client.gwt.client.presenter.personne.PersonneActionsPresenter.PersonneInfosActionView;

/**
 * Vue pour les blocs des actions.
 * @author cblanchard - SCUB
 */
public class PersonneBlocActionViewImpl extends Composite implements PersonneBlocActionView {

    /** Conteneur des actions. */
    private VerticalPanel conteneur;
    /**
     * mode de connexion admin/normal.
     */
    private boolean isAdmin;

    /** Constructeur. */
    public PersonneBlocActionViewImpl(boolean isAdmin) {
        this.isAdmin = isAdmin;
        conteneur = new VerticalPanel();
        conteneur.setStylePrimaryName(SquareResources.INSTANCE.css().personneBlocAction());
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.setSpacing(10);
        initWidget(conteneur);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    /**
     * Ajout une action au conteneur.
     * @param isActionTerminer si l'action a le statut terminer
     * @return l'interface de la vue
     */
    @Override
    public PersonneInfosActionView ajouterAction() {
        final PersonneInfosActionViewImpl action = new PersonneInfosActionViewImpl(isAdmin);
        conteneur.add(action);
        return action;
    }

}

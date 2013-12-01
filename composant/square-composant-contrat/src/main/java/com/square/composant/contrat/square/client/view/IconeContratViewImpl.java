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
package com.square.composant.contrat.square.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.contrat.square.client.bundle.ComposantContratResources;
import com.square.composant.contrat.square.client.presenter.IconeContratPresenter.IconeContratView;

/**
 * Implémentation de la vue de l'icone des contrats.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class IconeContratViewImpl extends Composite implements IconeContratView {

    private SimplePanel conteneur;

    private ComposantContratResources resources;

    private IconeContratViewImplConstants viewConstantes;

    /** Constructeur. */
    public IconeContratViewImpl() {
        resources = GWT.create(ComposantContratResources.class);
        viewConstantes = GWT.create(IconeContratViewImplConstants.class);
        conteneur = new SimplePanel();
        initWidget(conteneur);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIconeContratsActifs() {
        final Image image = new Image(resources.imageIconeContratActif());
        image.setTitle(viewConstantes.contratActif());
        conteneur.setWidget(image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIconeContratsResilies() {
        final Image image = new Image(resources.imageIconeContratResilie());
        image.setTitle(viewConstantes.contratResilie());
        conteneur.setWidget(image);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

}

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
package com.square.composant.espace.client.square.client.view.espace.client;

import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.espace.client.square.client.content.i18n.ComposantEspaceClientConstants;
import com.square.composant.espace.client.square.client.presenter.espace.client.EspaceClientPresenter;
import com.square.composant.espace.client.square.client.presenter.espace.client.EspaceClientPresenter.BlocIdentifiantsAdherentView;
import com.square.composant.espace.client.square.client.presenter.espace.client.EspaceClientPresenter.BlocOptionsAdherentView;
import com.square.composant.espace.client.square.client.presenter.espace.client.EspaceClientPresenter.BlocSyntheseEspaceAdherentView;
import com.square.composant.espace.client.square.client.presenter.espace.client.EspaceClientPresenter.EspaceClientView;
import com.square.composant.espace.client.square.client.view.espaceadherent.BlocIdentifiantsAdherentViewImpl;
import com.square.composant.espace.client.square.client.view.espaceadherent.BlocOptionsAdherentViewImpl;
import com.square.composant.espace.client.square.client.view.espaceadherent.BlocSyntheseEspaceAdherentViewImpl;

/**
 * Implémentation de la vue modélisant le contenu de l'onglet espace client.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class EspaceClientViewImpl extends Composite implements EspaceClientView {

    private BlocSyntheseEspaceAdherentView blocSynthese;

    private BlocIdentifiantsAdherentView blocIdentifiants;

    private BlocOptionsAdherentView blocOptions;

    private VerticalPanel vpEspaceAdherent;

    /**
     * Constructeur.
     */
    public EspaceClientViewImpl() {
        super();
        vpEspaceAdherent = new VerticalPanel();
        vpEspaceAdherent.setSpacing(10);
        vpEspaceAdherent.setWidth(ComposantEspaceClientConstants.POURCENT_100);
        blocSynthese = new BlocSyntheseEspaceAdherentViewImpl();
        blocIdentifiants = new BlocIdentifiantsAdherentViewImpl();
        vpEspaceAdherent.add(blocSynthese.asWidget());
        vpEspaceAdherent.add(blocIdentifiants.asWidget());
        blocOptions = new BlocOptionsAdherentViewImpl();
        vpEspaceAdherent.add(blocOptions.asWidget());
        blocOptions.setBlocOptionVisible(false);
        initWidget(vpEspaceAdherent);
        this.setStylePrimaryName(EspaceClientPresenter.RESOURCES.css().composantEspaceClient());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlocIdentifiantsAdherentView getBlocIdentifiantsView() {
        return blocIdentifiants;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlocOptionsAdherentView getBlocOptionsView() {
        return blocOptions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlocSyntheseEspaceAdherentView getBlocSyntheseView() {
        return blocSynthese;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget asWidget() {
        return this;
    }

}

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
package com.square.composant.cotisations.client;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.cotisations.client.bundle.ComposantCotisationsResources;
import com.square.composant.cotisations.client.presenter.moteur.recherche.MoteurRechercheCotisationsPresenter;
import com.square.composant.cotisations.client.service.CotisationsServiceGwt;
import com.square.composant.cotisations.client.service.CotisationsServiceGwtAsync;
import com.square.composant.cotisations.client.view.moteur.recherche.MoteurRechercheCotisationsViewImpl;

/**
 * Presenter pour l'espace personnel de l'adhérent.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ComposantCotisations extends Presenter {

    /** Instance des ressources. */
    public static final ComposantCotisationsResources RESOURCES = GWT.create(ComposantCotisationsResources.class);

    private ComposantCotisationsView view;

    private CotisationsServiceGwtAsync cotisationsRpcService;
    private MoteurRechercheCotisationsPresenter moteurRecherchePresenter;

    /**
     * Constructeur.
     * @param eventBus le bus qui transmet les évènements.
     * @param view la vue
     * @param uidPersonne identifiant unique Square de l'adhérent.
     */
    public ComposantCotisations(HandlerManager eventBus, ComposantCotisationsView view, Long uidPersonne) {
        super(eventBus);

        StyleInjector.inject(RESOURCES.css().getText());
        this.view = view;
        cotisationsRpcService = GWT.create(CotisationsServiceGwt.class);
        moteurRecherchePresenter = addChildPresenter(
            new MoteurRechercheCotisationsPresenter(eventBus, new MoteurRechercheCotisationsViewImpl(), uidPersonne, cotisationsRpcService));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View asView() {
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBind() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onShow(HasWidgets container) {
        moteurRecherchePresenter.showPresenter(view.getConteneur());
        container.add(view.asWidget());
    }

    /**
     * Interface de la vue.
     */
    public interface ComposantCotisationsView extends View {
        /**
         * Methode appelé lorsque un servie Rpc ne s'est pas deroulé correctement.
         * @param config infos sur l'erreur.
         */
        void onRpcServiceFailure(final ErrorPopupConfiguration config);

        /**
         * Retourne le conteneur.
         * @return le conteneur
         */
        HasWidgets getConteneur();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }

}

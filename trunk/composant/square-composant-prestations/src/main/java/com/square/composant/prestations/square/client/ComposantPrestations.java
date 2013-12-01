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
package com.square.composant.prestations.square.client;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.prestations.square.client.bundle.ComposantPrestationsResources;
import com.square.composant.prestations.square.client.model.ConstantesPrestationModel;
import com.square.composant.prestations.square.client.presenter.moteur.recherche.MoteurRecherchePrestationsPresenter;
import com.square.composant.prestations.square.client.service.ConstantesPrestationServiceGwt;
import com.square.composant.prestations.square.client.service.ConstantesPrestationServiceGwtAsync;
import com.square.composant.prestations.square.client.service.PrestationServiceGwt;
import com.square.composant.prestations.square.client.service.PrestationServiceGwtAsync;
import com.square.composant.prestations.square.client.view.moteur.recherche.MoteurRecherchePrestationsViewImpl;

/**
 * Presenter pour l'espace personnel de l'adhérent.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ComposantPrestations extends Presenter {

    /** Instance des ressources. */
    public static final ComposantPrestationsResources RESOURCES = GWT.create(ComposantPrestationsResources.class);

    private ComposantPrestationsView view;

    /** Service asynchrone. */
    private PrestationServiceGwtAsync prestationRpcService;

    /** Service des constantes. */
    private ConstantesPrestationServiceGwtAsync constantesPrestationService;

    private Long uidPersonne;
    private MoteurRecherchePrestationsPresenter moteurRecherchePrestationsPresenter;

    /**
     * Constructeur.
     * @param eventBus le bus qui transmet les évènements.
     * @param view la vue
     * @param uidPersonne identifiant unique Square de l'adhérent.
     */
    public ComposantPrestations(HandlerManager eventBus, ComposantPrestationsView view, Long uidPersonne) {
        super(eventBus);
        this.uidPersonne = uidPersonne;

        StyleInjector.inject(RESOURCES.css().getText());
        this.view = view;

        prestationRpcService = GWT.create(PrestationServiceGwt.class);
        constantesPrestationService = GWT.create(ConstantesPrestationServiceGwt.class);

    }

    /**
     * Charge les contanstes des prestations.
     * @param uidPersonne identifiant unique Square de l'adhérent.
     */
    private void chargerConstantes() {
        final AsyncCallback<ConstantesPrestationModel> asyncCallback = new AsyncCallback<ConstantesPrestationModel>() {
            @Override
            public void onSuccess(ConstantesPrestationModel result) {
                if (moteurRecherchePrestationsPresenter == null) {
                    moteurRecherchePrestationsPresenter = addChildPresenter(
                            new MoteurRecherchePrestationsPresenter(eventBus,
                                new MoteurRecherchePrestationsViewImpl(), uidPersonne, prestationRpcService, result));
                    moteurRecherchePrestationsPresenter.showPresenter(view.getConteneur());
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        };
        constantesPrestationService.chargerConstantesPrestations(asyncCallback);
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
        chargerConstantes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onShow(HasWidgets container) {
        container.add(view.asWidget());
    }

    /**
     * Interface de la vue.
     */
    public interface ComposantPrestationsView extends View {
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

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
package com.square.composant.tarificateur.square.client.presenter.transaction.recherche;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.tarificateur.square.client.event.OpenOpportuniteEvent;
import com.square.composant.tarificateur.square.client.event.OpenOpportuniteEventHandler;
import com.square.composant.tarificateur.square.client.service.TarificateurServiceGwtAsync;
import com.square.composant.tarificateur.square.client.view.transaction.recherche.PopupRechercheTransactionViewImpl;

/**
 * Presenter qui gère le bouton permettant d'accéder à la recherche d'opportunité par numéro de transaction.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class BoutonRechercherTransactionPresenter extends Presenter {

    private BoutonRechercherTransactionView view;

    private TarificateurServiceGwtAsync tarificateurServiceGwtAsync;
    private PopupRechercheTransactionPresenter popupRechercheTransactionPresenter;

    /**
     * Constructeur.
     * @param eventBus bus gestionnaire d'évènements
     * @param view vue associée au presenter
     * @param tarificateurServiceGwtAsync service Tarificateur asynchrone
     */
    public BoutonRechercherTransactionPresenter(HandlerManager eventBus, BoutonRechercherTransactionView view,
        TarificateurServiceGwtAsync tarificateurServiceGwtAsync) {
        super(eventBus);
        this.view = view;
        this.tarificateurServiceGwtAsync = tarificateurServiceGwtAsync;
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
        view.getBtnClickHandler().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (popupRechercheTransactionPresenter == null) {
                    popupRechercheTransactionPresenter = addChildPresenter(new PopupRechercheTransactionPresenter(eventBus,
                    		new PopupRechercheTransactionViewImpl(), tarificateurServiceGwtAsync));
                    popupRechercheTransactionPresenter.showPresenter(null);
                    popupRechercheTransactionPresenter.addEventHandlerToLocalBus(OpenOpportuniteEvent.TYPE, new OpenOpportuniteEventHandler() {
                        @Override
                        public void openOpportunite(OpenOpportuniteEvent event) {
                            // Propagation
                            fireEventLocalBus(event);
                        }
                    });
                } else {
                    popupRechercheTransactionPresenter.onShow(null);
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onShow(HasWidgets container) {
        container.add(view.asWidget());
        tarificateurServiceGwtAsync.peutRechercherOppParTransaction(new AsyncCallback<Boolean>() {

            @Override
            public void onSuccess(Boolean result) {
                view.afficher(result);
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(caught);
            }
        });
    }

    /**
     * Interface de la vue.
     * 
     * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
     *
     */
    public interface BoutonRechercherTransactionView extends View {

        /**
         * Affiche / masque la vue.
         * @param visible true pour afficher, false pour masquer
         */
        void afficher(boolean visible);

        /**
         * Accesseur au handler de clic sur le bouton.
         * @return le handler
         */
        HasClickHandlers getBtnClickHandler();

        /**
         * Popup d'erreur.
         * @param exception l'exception
         */
        void onRpcServiceFailure(Throwable exception);
    }

    @Override
    public void onDetach() {
        GWT.log("OnDetach " + this);
    }
}

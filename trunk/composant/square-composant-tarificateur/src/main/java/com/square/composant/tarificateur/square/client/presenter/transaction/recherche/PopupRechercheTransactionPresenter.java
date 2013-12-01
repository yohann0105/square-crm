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
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.tarificateur.square.client.event.OpenOpportuniteEvent;
import com.square.composant.tarificateur.square.client.model.opportunite.OpportuniteModel;
import com.square.composant.tarificateur.square.client.service.TarificateurServiceGwtAsync;
import com.square.composant.tarificateur.square.client.view.transaction.recherche.PopupRechercheTransactionViewImplConstants;

/**
 * Presenter qui gère la popup permettant de rechercher une opportunité par numéro de transaction.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class PopupRechercheTransactionPresenter extends Presenter {

    private PopupRechercheTransactionView view;

    private TarificateurServiceGwtAsync tarificateurServiceGwtAsync;

    /**
     * Constructeur.
     * @param eventBus bus d'évènements
     * @param view vue associée au presenter
     * @param tarificateurServiceGwtAsync service Tarificateur asynchrone
     */
    public PopupRechercheTransactionPresenter(HandlerManager eventBus, PopupRechercheTransactionView view,
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
        view.getBtnRechercheClickHandler().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                getOpportuniteByNumTransaction();
            }

        });

        view.getBtnAnnulerClickHandler().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                view.afficher(false);
            }
        });
        view.getFocusPanelAllKeyHandlers().addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                final int keyCode = event.getNativeKeyCode();
                if (keyCode == KeyCodes.KEY_ENTER) {
                    // On lance la recherche lorsque la touche entrée est pressée
                    getOpportuniteByNumTransaction();
                } else if (keyCode == KeyCodes.KEY_ESCAPE) {
                    // On cache la popup lorsque la touche echap est pressée
                    view.afficher(false);
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onShow(HasWidgets container) {
        view.afficher(true);
    }

    /**
     * Rechercher l'opportunité correspondant au numéro de transaction saisi.
     * Si l'opportunité est trouvée, envoie un évènement pour ouvrir l'opportunité dans Square.
     */
    private void getOpportuniteByNumTransaction() {
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().msgRechercheOppEnCours()));
        final String numTransaction = view.getNumTransactionValue().getValue();
        tarificateurServiceGwtAsync.getOpportuniteByNumTransaction(numTransaction, new AsyncCallback<OpportuniteModel>() {

            @Override
            public void onSuccess(OpportuniteModel result) {
                // On cache la popup
                view.afficher(false);
                view.onRpcServiceSuccess();
                // On ouvre la fiche de la personne et l'opportunité trouvée
                fireEventLocalBus(new OpenOpportuniteEvent(result.getEidOpportunite(), result.getEidPersonnePrincipale()));
            }

            @Override
            public void onFailure(Throwable caught) {
                // On cache la popup
                view.afficher(false);
                // On affiche le message d'erreur
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
    public interface PopupRechercheTransactionView extends View {

        /**
         * Affiche / masque la vue.
         * @param visible true pour afficher la vue, false pour la masquer
         */
        void afficher(boolean visible);

        /**
         * Accesseur handler clic bouton de recherche.
         * @return le handler
         */
        HasClickHandlers getBtnRechercheClickHandler();

        /**
         * Accesseur handler clic bouton d'annulation.
         * @return le handler
         */
        HasClickHandlers getBtnAnnulerClickHandler();

        /**
         * Accesseur AllKeyHandlers pour le focus panel.
         * @return le handler
         */
        HasAllKeyHandlers getFocusPanelAllKeyHandlers();

        /**
         * Accesseur valeur numéro de transaction saisi.
         * @return la valeur
         */
        HasValue<String> getNumTransactionValue();

        /**
         * Affiche une popup de chargement.
         * @param config configuration
         */
        void afficherLoadingPopup(LoadingPopupConfiguration config);

        /**
         * Cache la popup de chargement après un appel asynchrone.
         */
        void onRpcServiceSuccess();

        /**
         * Popup d'erreur.
         * @param exception l'exception
         */
        void onRpcServiceFailure(Throwable exception);

        /**
         * Accesseur constantes de la vue.
         * @return les constantes
         */
        PopupRechercheTransactionViewImplConstants getViewConstants();
    }

    @Override
    public void onDetach() {
        // TODO Auto-generated method stub
        
    }
}

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
package com.square.composant.flux.opportunite.client.presenter;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.flux.opportunite.client.bundle.ComposantFluxOpportuniteResources;
import com.square.composant.flux.opportunite.client.content.i18n.ComposantFluxOpportuniteConstants;
import com.square.composant.flux.opportunite.client.event.RafraichirRessourceEvent;
import com.square.composant.flux.opportunite.client.model.QuotaModel;
import com.square.composant.flux.opportunite.client.service.FluxOpportuniteServiceGwt;
import com.square.composant.flux.opportunite.client.service.FluxOpportuniteServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;

/**
 * Popup permettant de modifier les quotas de resosurces.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupModifQuotasPresenter extends Presenter {

    /** Vue associée au presenter. */
    private PopupModifQuotasView view;

    /** Liste des identifiants des ressources dont on veut modifier les quotas. */
    private List<Long> listeIdsRessources;

    /** Service du flux d'opportunités. */
    private FluxOpportuniteServiceGwtAsync fluxOpportuniteServiceRpc;

    /** Instance des ressources. */
    public static final ComposantFluxOpportuniteResources RESOURCES = GWT.create(ComposantFluxOpportuniteResources.class);

    /** Constantes du presenter. */
    private PopupModifQuotasPresenterConstants presenterConstants;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènement.
     * @param view la vue associée au presenter.
     * @param listeIdsRessources la liste des identifiants des ressources dont on veut modifier les quotas.
     */
    public PopupModifQuotasPresenter(HandlerManager eventBus, PopupModifQuotasView view, List<Long> listeIdsRessources) {
        super(eventBus);
        this.view = view;
        this.listeIdsRessources = listeIdsRessources;
        fluxOpportuniteServiceRpc = GWT.create(FluxOpportuniteServiceGwt.class);
        presenterConstants = GWT.create(PopupModifQuotasPresenterConstants.class);
        StyleInjector.inject(PopupModifQuotasPresenter.RESOURCES.css().getText());
    }

    @Override
    public View asView() {
        return view;
    }

    public void rafraichir(List<Long> listeIdsRessources) {
        this.listeIdsRessources = listeIdsRessources;
        chargerJours();
    }

    @Override
    public void onBind() {
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.hidePopup();
            }
        });
        view.getBtnEnregistrer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                modifierQuotas();
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        chargerJours();
    }

    private void chargerJours() {
        final AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback = new AsyncCallback<List<IdentifiantLibelleGwt>>() {
            @Override
            public void onSuccess(List<IdentifiantLibelleGwt> result) {
                view.onRpcServiceSuccess();
                view.showPopup(result);
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        };
        fluxOpportuniteServiceRpc.getListeJours(asyncCallback);
    }

    /**
     * Modifie les quotas.
     */
    private void modifierQuotas() {
        view.afficherLoadingPopup(new LoadingPopupConfiguration(presenterConstants.modificationQuotasEnCours()));

        // Récupération des quotas saisie
        final List<QuotaModel> listeQuotas = view.getListeQuotas();

        // Modification des ressources
        final AsyncCallback<Void> asyncCallback = new AsyncCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                view.onRpcServiceSuccess();
                view.hidePopup();
                // Publication d'un évènement pour rafraichir les informations des resosurces déjà ouvertes
                fireEventLocalBus(new RafraichirRessourceEvent(listeIdsRessources));
                final PopupInfoConfiguration config = new PopupInfoConfiguration(presenterConstants.notificationQuotasModifies(),
                    ComposantFluxOpportuniteConstants.NOTIFICATION_TIME_OUT);
                new DecoratedInfoPopup(config).show();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        };
        fluxOpportuniteServiceRpc.modifierQuotas(listeIdsRessources, listeQuotas, asyncCallback);
    }

    /**
     * Interface de la vue de la popup de modification des quotas.
     * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
     */
    public interface PopupModifQuotasView extends View  {

        /**
         * Affiche la popup de modification des quotas.
         * @param listeJours la liste des jours à afficher.
         */
        void showPopup(List<IdentifiantLibelleGwt> listeJours);

        /** Cache la popup de modification des quotas. */
        void hidePopup();

        /** Fermer la popup de chargement. */
        void onRpcServiceSuccess();

        /**
         * Popup d'erreur.
         * @param errorPopupConfiguration la configuration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Accesseur sur le ClickHandler du bouton d'enregistrement.
         * @return le ClickHandler.
         */
        HasClickHandlers getBtnEnregistrer();

        /**
         * Accesseur sur le ClickHandler du bouton d'annulation.
         * @return le ClickHandler.
         */
        HasClickHandlers getBtnAnnuler();

        /**
         * Récupère la liste des quotas.
         * @return la liste des quotas.
         */
        List<QuotaModel> getListeQuotas();

        /**
         * Affiche un message d'opération en cours.
         * @param config loadingPopupConfiguration la config
         */
        void afficherLoadingPopup(LoadingPopupConfiguration config);
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}

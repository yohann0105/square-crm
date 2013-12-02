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

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.flux.opportunite.client.bundle.ComposantFluxOpportuniteResources;
import com.square.composant.flux.opportunite.client.event.RafraichirRessourceEvent;
import com.square.composant.flux.opportunite.client.event.RafraichirRessourceEventHandler;
import com.square.composant.flux.opportunite.client.model.QuotaModel;
import com.square.composant.flux.opportunite.client.service.FluxOpportuniteServiceGwt;
import com.square.composant.flux.opportunite.client.service.FluxOpportuniteServiceGwtAsync;

/**
 * Presenter du panel des quotas.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PanelQuotasPresenter extends Presenter {

    /** Vue associée au presenter. */
    private PanelQuotasView view;

    /** Identifiant de la ressource dont on veut afficher les ressources. */
    private Long uidRessource;

    /** Service du flux d'opportunités. */
    private FluxOpportuniteServiceGwtAsync fluxOpportuniteServiceRpc;

    /** Instance des ressources. */
    public static final ComposantFluxOpportuniteResources RESOURCES = GWT.create(ComposantFluxOpportuniteResources.class);

    /**
     * Constructeur.
     * @param eventBus le bus d'évènements.
     * @param view la vue associée au presnter.
     * @param uidRessource l'identifiant de la ressource dont on veut afficher les quotas.
     */
    public PanelQuotasPresenter(HandlerManager eventBus, PanelQuotasView view, Long uidRessource) {
        super(eventBus);
        this.view = view;
        this.uidRessource = uidRessource;
        fluxOpportuniteServiceRpc = GWT.create(FluxOpportuniteServiceGwt.class);
        StyleInjector.inject(PanelQuotasPresenter.RESOURCES.css().getText());
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        addEventHandlerToGlobalBus(RafraichirRessourceEvent.TYPE, new RafraichirRessourceEventHandler() {
            @Override
            public void rafraichirRessource(RafraichirRessourceEvent event) {
                if (event.getListeIdsRessources().contains(uidRessource)) {
                    chargerQuotas();
                }
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        // Chargement des quotas de la ressource
        chargerQuotas();
        container.add(view.asWidget());
    }

    public void rafraichir(Long uidRessource) {
        this.uidRessource = uidRessource;
        chargerQuotas();
    }

    /** Charge les quotas de la ressource. */
    private void chargerQuotas() {
        final AsyncCallback<List<QuotaModel>> asyncCallback = new AsyncCallback<List<QuotaModel>>() {
            @Override
            public void onSuccess(List<QuotaModel> result) {
                view.onRpcServiceSuccess();
                boolean hasValQuota = false;
                for (QuotaModel quota : result) {
                    if (quota.getValue() != null && quota.getValue() > 0) {
                        hasValQuota = true;
                    }
                }
                if (hasValQuota) {
                    view.chargerQuotas(result);
                }
                else {
                    view.cacherPanelQuotas();
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        };
        fluxOpportuniteServiceRpc.getListeQuotasRessource(uidRessource, asyncCallback);
    }

    /**
     * Vue du panel des quotas.
     */
    public interface PanelQuotasView extends View {

        /** Fermer la popup de chargement. */
        void onRpcServiceSuccess();

        /**
         * Popup d'erreur.
         * @param errorPopupConfiguration la configuration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Charge la liste des quotas.
         * @param listeQuotas la liste des quotas à charger.
         */
        void chargerQuotas(List<QuotaModel> listeQuotas);

        /**
         * Cache le panel des quotas.
         */
        void cacherPanelQuotas();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }

}

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

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.flux.opportunite.client.content.i18n.ComposantFluxOpportuniteConstants;
import com.square.composant.flux.opportunite.client.event.LancerRechercheActionEvent;
import com.square.composant.flux.opportunite.client.model.AccesPotCommunModel;
import com.square.composant.flux.opportunite.client.service.FluxOpportuniteServiceGwt;
import com.square.composant.flux.opportunite.client.service.FluxOpportuniteServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;
import com.square.composants.graphiques.lib.client.popups.DecoratedWarningPopup;

/**
 * Presenter pour déterminer l'accès au pot commun.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class AccesPotCommunPresenter extends Presenter {

    /** Service du flux d'opportunités. */
    private FluxOpportuniteServiceGwtAsync fluxOpportuniteServiceRpc;

    /** Constantes du presenter. */
    private AccesPotCommunPresenterConstants presenterConstants;

    /** Identifiant de la ressource connectée. */
    private Long uidRessource;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènement.
     * @param uidRessource l'identifiant de la ressource connectée.
     */
    public AccesPotCommunPresenter(HandlerManager eventBus, Long uidRessource) {
        super(eventBus);
        this.uidRessource = uidRessource;
        fluxOpportuniteServiceRpc = GWT.create(FluxOpportuniteServiceGwt.class);
        presenterConstants = GWT.create(AccesPotCommunPresenterConstants.class);
    }

    @Override
    public View asView() {
        return null;
    }

    @Override
    public void onBind() {
    }

    @Override
    public void onShow(HasWidgets container) {
    }

    /**
     * Détermine si la ressource connectée a accès au pot commun.
     * @param afficherWarningPopup indique si il faut afficher la popup d'avertissement
     */
    public void hasAccesPotCommun(final boolean afficherWarningPopup) {
        final AsyncCallback<AccesPotCommunModel> asyncCallback = new AsyncCallback<AccesPotCommunModel>() {
            @Override
            public void onSuccess(AccesPotCommunModel result) {
                if (afficherWarningPopup && !result.isHasAccesPotCommun()) {
                    new DecoratedWarningPopup(presenterConstants.pasAccesPotCommun()).show();
                } else {
                    fireEventLocalBus(new LancerRechercheActionEvent(result.isHasAccesPotCommun()));
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                LoadingPopup.stopAll();
                ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
            }
        };
        fluxOpportuniteServiceRpc.hasAccesPotCommun(uidRessource, asyncCallback);
    }

    /**
     * Détermine si la ressource connectée a accès au pot commun, qu'elle a atteint son quota et que toutes les actions de son quota sont terminées.
     */
    public void afficherAccesPotCommun() {
        final AsyncCallback<AccesPotCommunModel> asyncCallback = new AsyncCallback<AccesPotCommunModel>() {
            @Override
            public void onSuccess(AccesPotCommunModel result) {
                if (result.isHasAccesPotCommun() && result.isAllActionsQuotaTerminees()) {
                    final PopupInfoConfiguration config = new PopupInfoConfiguration(presenterConstants.accesPotCommun(),
                        ComposantFluxOpportuniteConstants.NOTIFICATION_TIME_OUT);
                    new DecoratedInfoPopup(config).show();
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                LoadingPopup.stopAll();
                ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
            }
        };
        fluxOpportuniteServiceRpc.hasAccesPotCommun(uidRessource, asyncCallback);
    }

    @Override
    public void onDetach() {
       GWT.log("onDetach " + this);
    }
}

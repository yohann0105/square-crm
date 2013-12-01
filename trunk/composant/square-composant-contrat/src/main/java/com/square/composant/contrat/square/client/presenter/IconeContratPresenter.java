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
package com.square.composant.contrat.square.client.presenter;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.contrat.square.client.model.InfosContratsBeneficiaireModel;
import com.square.composant.contrat.square.client.service.ContratServiceGwt;
import com.square.composant.contrat.square.client.service.ContratServiceGwtAsync;

/**
 * Presenter pour afficher ou non l'icone signalant l'existence de contrat entre 2 personnes.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class IconeContratPresenter extends Presenter {

    /** Vue associée au presenter. */
    private IconeContratView view;

    /** Identifiant de l'assuré du contrat. */
    private Long idAssure;

    /** Identifiant du bénéficiaire du contrat. */
    private Long idBeneficiaire;

    /** Service contrat. */
    private ContratServiceGwtAsync contratServiceRpc;

    /** Constantes du presenter. */
    private IconeContratPresenterConstants constantesPresenter;

    /**
     * Constructeur.
     * @param eventBus le bus d'évènements.
     * @param view la vue associé au présenter.
     * @param idAssure l'identifiant de l'assuré.
     * @param idBeneficiaire l'identifiant du bénéficiaire.
     */
    public IconeContratPresenter(HandlerManager eventBus, IconeContratView view, Long idAssure, Long idBeneficiaire) {
        super(eventBus);
        this.view = view;
        this.idAssure = idAssure;
        this.idBeneficiaire = idBeneficiaire;
        this.contratServiceRpc = GWT.create(ContratServiceGwt.class);
        this.constantesPresenter = GWT.create(IconeContratPresenterConstants.class);
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
    }

    @Override
    public void onShow(HasWidgets container) {
        chercherContrats(container);
    }

    /**
     * Recherche s'il existe un contrat entre l'assure et le bénéficiaire.
     * @param container le conteneur où la vue sera affichée.
     */
    private void chercherContrats(final HasWidgets container) {
        final AsyncCallback<InfosContratsBeneficiaireModel> asyncCallback = new AsyncCallback<InfosContratsBeneficiaireModel>() {
            @Override
            public void onSuccess(InfosContratsBeneficiaireModel result) {
                    if (result.isHasContratsActifs()) {
                        view.setIconeContratsActifs();
                        container.add(view.asWidget());
                    } else if (result.isHasContrats()) {
                        view.setIconeContratsResilies();
                        container.add(view.asWidget());
                    }
            }

            @Override
            public void onFailure(Throwable caught) {
                LoadingPopup.stopAll();
                ErrorPopup.afficher(new ErrorPopupConfiguration(constantesPresenter.erreurRechercheContrat()));
            }
        };
        contratServiceRpc.hasContratAssureBeneficiaire(idAssure, idBeneficiaire, asyncCallback);
    }

    /**
     * Vue pour l'icone d'un contrat.
     */
    public interface IconeContratView extends View {
        /**
         * Image contrats actifs.
         */
        void setIconeContratsActifs();

        /**
         * Image contrats résiliés.
         */
        void setIconeContratsResilies();
    }

    @Override
    public void onDetach() {
    }
}

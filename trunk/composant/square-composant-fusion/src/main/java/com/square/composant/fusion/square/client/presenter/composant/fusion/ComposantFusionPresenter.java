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
package com.square.composant.fusion.square.client.presenter.composant.fusion;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.fusion.square.client.bundle.ComposantFusionResources;
import com.square.composant.fusion.square.client.event.AfficherPreparationFusionEvent;
import com.square.composant.fusion.square.client.event.AfficherPreparationFusionEventHandler;
import com.square.composant.fusion.square.client.event.FinFusionPersonnesEvent;
import com.square.composant.fusion.square.client.event.FinFusionPersonnesEventHandler;
import com.square.composant.fusion.square.client.event.MajSelectionDoublonsEvent;
import com.square.composant.fusion.square.client.event.MajSelectionDoublonsEventHandler;
import com.square.composant.fusion.square.client.model.ConstantesModel;
import com.square.composant.fusion.square.client.model.RechercheDoublonCritereModel;
import com.square.composant.fusion.square.client.presenter.fusion.FusionPresenter;
import com.square.composant.fusion.square.client.presenter.fusion.FusionPresenter.FusionView;
import com.square.composant.fusion.square.client.presenter.selection.doublon.SelectionDoublonPresenter;
import com.square.composant.fusion.square.client.service.ConstantesMappingServiceGwt;
import com.square.composant.fusion.square.client.service.ConstantesMappingServiceGwtAsync;
import com.square.composant.fusion.square.client.service.FusionServiceGwt;
import com.square.composant.fusion.square.client.service.FusionServiceGwtAsync;
import com.square.composant.fusion.square.client.view.composant.fusion.ComposantFusionViewImpl;
import com.square.composant.fusion.square.client.view.fusion.FusionViewImpl;
import com.square.composant.fusion.square.client.view.selection.doublon.SelectionDoublonViewImpl;

/**
 * Contrôleur de l'application.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ComposantFusionPresenter extends Presenter {

    /** Constantes du composant. */
    public static final ComposantFusionPresenterConstants CONSTANTS = GWT.create(ComposantFusionPresenterConstants.class);

    /** Instance des ressources. */
    public static final ComposantFusionResources RESOURCES = GWT.create(ComposantFusionResources.class);

    /** Services asynchrones. */
    private FusionServiceGwtAsync fusionRpcService;

    private ConstantesMappingServiceGwtAsync constantesServiceGwtAsync;

    /** Vue du moteur de recherche. */
    private ComposantFusionView composantFusionView;

    /** Constantes. */
    private ConstantesModel constantes;

    /** Vue pour la préparation de la fusion. */
    private FusionView fusionView;

    /** Critères de recherche de doublons. */
    private RechercheDoublonCritereModel criteres;

    private Long idPersonneSelectionnee;

    private FusionPresenter fusionPresenter;
    private SelectionDoublonPresenter selectionDoublonPresenter;

    /**
     * Constructeur par défaut.
     * @param eventBus bus des évenements.
     * @param criteres les critères de recherche de doublons.
     * @param idPersonneSelectionnee identifiant de la personne pré-selectionnée
     */
    public ComposantFusionPresenter(final HandlerManager eventBus, RechercheDoublonCritereModel criteres, Long idPersonneSelectionnee) {
        super(eventBus);
        this.constantesServiceGwtAsync = GWT.create(ConstantesMappingServiceGwt.class);
        this.fusionRpcService = GWT.create(FusionServiceGwt.class);
        this.criteres = criteres;
        this.idPersonneSelectionnee = idPersonneSelectionnee;

        StyleInjector.inject(RESOURCES.css().getText());

        // Initialisation du conteneur principal
        composantFusionView = new ComposantFusionViewImpl();
    }

    /** Charge les constntes de mapping de l'application. */
    private void chargerConstantesMappingApplication(final HasWidgets container) {
        final AsyncCallback<ConstantesModel> asyncCallback = new AsyncCallback<ConstantesModel>() {
            @Override
            public void onFailure(Throwable caught) {
                composantFusionView.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(ConstantesModel constantesGwt) {
                constantes = constantesGwt;
                container.add(composantFusionView.asWidget());
                composantFusionView.selectOnglet(CONSTANTS.ongletSelectionDoublons());
            }
        };
        constantesServiceGwtAsync.getConstantes(asyncCallback);
    }

    @Override
    public View asView() {
        return composantFusionView;
    }

    @Override
    public void onBind() {
        // GESTION DE LA SELECTION SUR LA TABLAYOUT DU CONTAINER
        composantFusionView.getOngletSelectionHandler().addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> event) {
                if (composantFusionView.getOngletContainerName(event.getSelectedItem()).equals(CONSTANTS.ongletSelectionDoublons())) {
                    if (selectionDoublonPresenter == null) {
                        selectionDoublonPresenter = addChildPresenter(
                            new SelectionDoublonPresenter(eventBus, new SelectionDoublonViewImpl(constantes), criteres, fusionRpcService, idPersonneSelectionnee));
                        selectionDoublonPresenter.showPresenter(composantFusionView.getOngletContainer(CONSTANTS.ongletSelectionDoublons()));
                        selectionDoublonPresenter.addEventHandlerToLocalBus(AfficherPreparationFusionEvent.TYPE, new AfficherPreparationFusionEventHandler() {
                                @Override
                                public void preparerFusion(final AfficherPreparationFusionEvent event) {
                                    fusionView = new FusionViewImpl(constantes);
                                    if (fusionPresenter == null) {
                                        fusionPresenter = addChildPresenter(new FusionPresenter(eventBus, fusionRpcService, fusionView));
                                        fusionPresenter.showPresenter(composantFusionView.getOngletContainer(CONSTANTS.ongletFusionner()));
                                        fusionPresenter.addEventHandlerToLocalBus(MajSelectionDoublonsEvent.TYPE, new MajSelectionDoublonsEventHandler() {
                                                @Override
                                                public void mettreAJourSelectionDoublons(MajSelectionDoublonsEvent event) {
                                                    selectionDoublonPresenter.rechercher(idPersonneSelectionnee, criteres);
                                                    composantFusionView.selectOnglet(CONSTANTS.ongletSelectionDoublons());
                                                }
                                            });
                                        fusionPresenter.addEventHandlerToLocalBus(FinFusionPersonnesEvent.TYPE, new FinFusionPersonnesEventHandler() {
                                            @Override
                                            public void onFinFusionPersonnes(FinFusionPersonnesEvent event) {
                                                // propagation
                                                fireEventLocalBus(event);
                                            }
                                        });
                                    }
                                    fusionPresenter.chargerIdsPersonnes(event.getIdPersonne1(), event.getIdPersonne2());
                                    composantFusionView.selectOnglet(CONSTANTS.ongletFusionner());
                                }
                            });
                    }
                } else if (composantFusionView.getOngletContainerName(event.getSelectedItem()).equals(CONSTANTS.ongletFusionner())) {
                    if (fusionPresenter == null) {
                        fusionPresenter = addChildPresenter(new FusionPresenter(eventBus, fusionRpcService, new FusionViewImpl(constantes)));
                        fusionPresenter.showPresenter(composantFusionView.getOngletContainer(CONSTANTS.ongletFusionner()));
                    }
                }
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        chargerConstantesMappingApplication(container);
    }

    public void demanderFusion(RechercheDoublonCritereModel criteres, Long idPersonneSelectionnee) {
        this.criteres = criteres;
        this.idPersonneSelectionnee = idPersonneSelectionnee;
        composantFusionView.selectOnglet(CONSTANTS.ongletSelectionDoublons());
        selectionDoublonPresenter.rechercher(idPersonneSelectionnee, criteres);
        if (fusionPresenter != null) {
            fusionPresenter.nettoyer();
        }
    }

    /**
     * Interface pour la vue de l'application.
     */
    public interface ComposantFusionView extends View {

        /**
         * Permet de récupérer le Widget associé à un onglet.
         * @param onglet le nom de l'onglet.
         * @return le widget de l'onglet.
         */
        HasWidgets getOngletContainer(final String onglet);

        /**
         * Permet de sélectionner un onglet.
         */
        void selectOnglet(final String onglet);

        /**
         * Permet de recuperer le Handler de selection du tab panel.
         * @return le handler de selection.
         */
        HasSelectionHandlers<Integer> getOngletSelectionHandler();

        /**
         * Methode appelée lorsqu'un servie Rpc ne s'est pas deroulé correctement.
         * @param config infos sur l'erreur.
         */
        void onRpcServiceFailure(final ErrorPopupConfiguration config);

        /**
         * Permet de recuperer le Widget associé à un onglet.
         * @param index du container.
         * @return le nom de l'onglet.
         */
        String getOngletContainerName(final Integer index);
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}

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
package com.square.client.gwt.client.presenter.ressource;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.square.client.gwt.client.model.RessourceModel;
import com.square.client.gwt.client.service.RessourceServiceGwtAsync;
import com.square.client.gwt.client.view.ressource.gestion.GestionRessourceViewImplConstants;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Presenter pour la page de gestion d'une campagne.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class GestionRessourcePresenter extends Presenter {

    /** Le service des campagnes. */
    private RessourceServiceGwtAsync ressourceServiceRpc;

    /** La vue. */
    private GestionRessourceView view;

    /** Id de la ressource. */
    private Long idRessource;

    private AideServiceGwtAsync aideService;

    /**
     * Constructeur.
     * @param eventBus le bus
     * @param ressourceServiceRpc le service des ressources
     * @param ressourceGestionView vue associée au presenter
     * @param idRessource l'identifiant de la ressource
     * @param aideService service d'aide.
     */
    public GestionRessourcePresenter(HandlerManager eventBus, RessourceServiceGwtAsync ressourceServiceRpc, GestionRessourceView ressourceGestionView,
        Long idRessource, AideServiceGwtAsync aideService) {
        super(eventBus);
        this.ressourceServiceRpc = ressourceServiceRpc;
        this.view = ressourceGestionView;
        this.idRessource = idRessource;
        this.aideService = aideService;
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        // les évenements relatifs à l'aide en ligne pour les composants d'aide.

        for (HasUpdateAideEventHandler handler : view.getListeUpdateEventHandler()) {
            handler.addUpdateAideEventHandler(new UpdateAideEventHandler() {

                @Override
                public void onUpdateAide(UpdateAideEvent event) {
                    aideService.creerOuModifierAide(event.getAide(), event.getCallBack());

                }
            });

        }
        for (HasDemandeAideEventHandler handler : view.getListeDemandeEventHandler()) {
            handler.addDemandeAideEventHandler(new DemandeAideEventHandler() {

                @Override
                public void onDemandeAide(DemandeAideEvent event) {

                    aideService.rechercherAide(event.getIdComposant(), event.getCallBack());

                }
            });

        }

        // Action sur le bouton annuler
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                initRessource();
            }
        });
        // Action sur le bouton enregistrer
        view.getBtnEnregistrer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                enregistrementModificationsRessource();
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        // Construction de la liste des identifiants des composants d'aide de la vue associée à ce presenter
        final List<Long> listeIdsComposantsAides = new ArrayList<Long>();
        for (final AideComposant composant : view.getListAideCompsant()) {
            listeIdsComposantsAides.add(composant.getId());
        }

        // Recherche des composants d'aide existant pour les rendre visible
        aideService.rechercherIdsComposantsAides(listeIdsComposantsAides, new AsyncCallback<List<Long>>() {
            @Override
            public void onSuccess(List<Long> result) {
                if (result != null) {
                    for (final AideComposant composant : view.getListAideCompsant()) {
                        composant.setVisible(result.contains(composant.getId()));
                    }
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });

        view.masquerLoadingPopup();
        initRessource();
        container.add(view.asWidget());
    }

    private void initRessource() {
        ressourceServiceRpc.rechercherRessourceParId(idRessource, new AsyncCallback<RessourceModel>() {
            @Override
            public void onSuccess(RessourceModel ressource) {
                view.getlNom().setText(ressource.getNom());
                view.getlPrenom().setText(ressource.getPrenom());
                view.getlEtat().setText(ressource.getEtat().getLibelle());
                view.getlFonction().setText(ressource.getFonction().getLibelle());
                view.getlService().setText(ressource.getService().getLibelle());
                view.getlAgence().setText(ressource.getAgence().getLibelle());
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    private void enregistrementModificationsRessource() {
        // TODO
    }

    /**
     * Interface de la vue de la gestion des ressources.
     */
    public interface GestionRessourceView extends View {

        /**
         * Renvoi la valeur de lNom.
         * @return lNom
         */
        Label getlNom();

        /**
         * Renvoi la valeur de lPrenom.
         * @return lPrenom
         */
        Label getlPrenom();

        /**
         * Renvoi la valeur de lFonction.
         * @return lFonction
         */
        Label getlFonction();

        /**
         * Renvoi la valeur de lService.
         * @return lService
         */
        Label getlService();

        /**
         * Renvoi la valeur de lEtat.
         * @return lEtat
         */
        Label getlEtat();

        /**
         * Renvoi la valeur de lEtat.
         * @return lEtat
         */
        Label getlAgence();

        /**
         * Récupère le manager d'icone.
         * @return le manager d'icone
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Renvoi la valeur de btnEnregistrer.
         * @return btnEnregistrer
         */
        HasClickHandlers getBtnEnregistrer();

        /**
         * Renvoi la valeur de btnAnnuler.
         * @return btnAnnuler
         */
        HasClickHandlers getBtnAnnuler();

        /**
         * Gestion de la popup en cas d'erreur.
         * @param config la configuration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration config);

        /**
         * Gestion de la popup pour le chargement.
         * @param loadingPopupConfiguration la configuration
         */
        void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration);

        /**
         * Récupère les constantes de la vue.
         * @return les constantes
         */
        GestionRessourceViewImplConstants getViewConstants();

        /** Masque la popup de chargement. */
        void masquerLoadingPopup();

        /**
         * Accesseur sur le conteneur des quotas.
         * @return le conteneur.
         */
        HasWidgets getConteneurQuotas();

          /**
         * Récupére la listes des composants d'aides.
         * @return List<AideComposant>
         */
        List<AideComposant> getListAideCompsant();

        /**
         * Récupére la liste des composants d'aides avec demandeEventHandler.
         * @return List<HasDemandeAideEventHandler>
         */
        List<HasDemandeAideEventHandler> getListeDemandeEventHandler();

        /**
         * Récupére la liste des composants d'aides avec demandeEventHandler.
         * @return List<HasUpdateAideEventHandler>
         */
        List<HasUpdateAideEventHandler> getListeUpdateEventHandler();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}

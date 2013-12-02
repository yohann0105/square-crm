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
package com.square.client.gwt.client.presenter.action;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.RafraichirRechercheActionEvent;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.client.gwt.client.model.ActionCritereRechercheModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRessourceModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.service.ActionServiceGwtAsync;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.view.action.transfert.ActionTransfertViewImplConstants;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Présenter de la popup de transfert d'action.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ActionTransfertPresenter extends Presenter {

    private ActionTransfertView view;

    private ActionServiceGwtAsync actionRpcService;

    private DimensionServiceGwtAsync dimensionServiceRpcService;

    private ActionCritereRechercheModel criteresActions;

    private DimensionRessourceModel ressourceConnectee;

    private AideServiceGwtAsync aideService;

    /**
     * Constructeur du présenter.
     * @param eventBus le bus.
     * @param actionTransfertView la vue.
     * @param actionRpcService le service d'action.
     * @param dimensionServiceRpcService le service de dimension.
     * @param criteres criteres de actions
     * @param ressourceConnectee la ressource connectée
     * @param aideService service d'aide.
     */
    public ActionTransfertPresenter(HandlerManager eventBus, ActionTransfertView actionTransfertView, ActionServiceGwtAsync actionRpcService,
        DimensionServiceGwtAsync dimensionServiceRpcService, final ActionCritereRechercheModel criteres, DimensionRessourceModel ressourceConnectee,
        AideServiceGwtAsync aideService) {
        super(eventBus);
        this.view = actionTransfertView;
        this.actionRpcService = actionRpcService;
        this.dimensionServiceRpcService = dimensionServiceRpcService;
        this.criteresActions = criteres;
        this.ressourceConnectee = ressourceConnectee;
        this.aideService = aideService;
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {

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
        view.getSuggestAgence().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final String libelleAgenceRecherchee = event.getSuggest();
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(libelleAgenceRecherchee);
                if (libelleAgenceRecherchee == null || "".equals(libelleAgenceRecherchee.trim())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceRpcService.rechercherAgenceParCriteres(criteres, event.getCallBack());
            }
        });
        view.getSuggestCommercial().addSuggestHandler(new SuggestListBoxSuggestEventHandler<DimensionRessourceModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<DimensionRessourceModel> event) {
                final String libelleRessourceRecherchee = event.getSuggest();
                final DimensionCriteresRechercheRessourceModel criteres = new DimensionCriteresRechercheRessourceModel();
                criteres.setNom(libelleRessourceRecherchee);
                criteres.setPrenom(libelleRessourceRecherchee);
                final IdentifiantLibelleGwt agenceSelectionnee = view.getAgence().getValue();
                if (agenceSelectionnee != null) {
                    criteres.setIdAgence(agenceSelectionnee.getIdentifiant());
                }
                if (libelleRessourceRecherchee == null || "".equals(libelleRessourceRecherchee.trim())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceRpcService.rechercherRessourceParCriteres(criteres, event.getCallBack());
            }
        });
        view.getChangeAgence().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                // On déselectionne le commercial
                view.getCommercial().setValue(null);
            }
        });
        // Click sur le bouton annuler
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.hidePopup();
            }
        });
        // Click sur le bouton creer
        view.getBtnTransferer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().transfertActions()));

                // on recupere l'agence et la ressource
                final Long idAgenceCible = view.getAgence().getValue() != null ? view.getAgence().getValue().getIdentifiant() : null;
                final Long idRessourceCible = view.getCommercial().getValue() != null ? view.getCommercial().getValue().getIdentifiant() : null;
                actionRpcService.transfererActionsParCritere(criteresActions, idAgenceCible, idRessourceCible, new AsyncCallback<Object>() {
                    @Override
                    public void onSuccess(Object result) {
                        view.hidePopup();
                        view.masquerLoadingPopup();
                        // on met à jour le moteur de recherche
                        fireEventLocalBus(new RafraichirRechercheActionEvent());
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        if (caught instanceof ControleIntegriteExceptionGwt) {
                            final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                            // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                            view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                            view.masquerLoadingPopup();
                        } else {
                            view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                        }
                    }
                });
            }
        });
    }

    /**
     * Met à jour les ritères et affiche la popup.
     * @param criteres les critères à mettre à jour.
     */
    public void afficher(ActionCritereRechercheModel criteres) {
        this.criteresActions = criteres;
        view.showPopup();
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

        // On pré-remplit l'agence et la ressource à partir des informations de la ressource connectée.
        view.getAgence().setValue(ressourceConnectee.getAgence());
        view.getCommercial().setValue(ressourceConnectee);

        view.showPopup();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                view.activerFocusAgence(true);
            }
        });
    }

    /**
     * Interface de la vue.
     */
    public interface ActionTransfertView extends View {

        /**
         * Affiche la popup.
         */
        void showPopup();

        /**
         * Masque la popup de chargement.
         */
        void masquerLoadingPopup();

        /**
         * Affiche la popup d'erreur.
         * @param errorPopupConfiguration la configuration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Masque la popup.
         */
        void hidePopup();

        /**
         * Afficher un message de chargement.
         * @param config la config
         */
        void afficherLoadingPopup(LoadingPopupConfiguration config);

        /**
         * Renvoi la valeur de btnAnnuler.
         * @return btnAnnuler
         */
        HasClickHandlers getBtnAnnuler();

        /**
         * Renvoi la valeur de btnCreer.
         * @return btnCreer
         */
        HasClickHandlers getBtnTransferer();

        /**
         * Renvoi la valeur de iconeErreurChampManager.
         * @return iconeErreurChampManager
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasAllKeyHandlers getAllKeyHandlersFocusPanel();

        /**
         * Retourne la valeur de l'agence selectionnée.
         * @return valeur de l'agence selectionnée.
         */
        HasValue<IdentifiantLibelleGwt> getAgence();

        /**
         * Active le focus sur le champ Agence.
         * @param actif flag
         */
        void activerFocusAgence(boolean actif);

        /**
         * Handler de suggestion pour la recherche d'agences.
         * @return le handler.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestAgence();

        /**
         * Handler pour les changements de valeur de l'agence selectionnée.
         * @return le handler.
         */
        HasValueChangeHandlers<IdentifiantLibelleGwt> getChangeAgence();

        /**
         * Handler de suggestion pour la recherche de commerciaux.
         * @return le handler.
         */
        HasSuggestListBoxHandler<DimensionRessourceModel> getSuggestCommercial();

        /**
         * Récupère le commercial selectionné.
         * @return le commercial selectionné.
         */
        HasValue<DimensionRessourceModel> getCommercial();

        /**
         * Renvoi la valeur de viewConstants.
         * @return viewConstants
         */
        ActionTransfertViewImplConstants getViewConstants();

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

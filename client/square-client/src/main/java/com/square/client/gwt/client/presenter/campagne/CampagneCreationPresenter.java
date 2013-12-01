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
package com.square.client.gwt.client.presenter.campagne;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.CreateCampagneEvent;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.client.gwt.client.model.CampagneModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.service.CampagneServiceGwtAsync;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.view.campagne.creation.CampagneCreationViewImplConstants;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEvent;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEventHandler;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;
import com.square.composants.graphiques.lib.client.popups.minimizable.IsMinimizable;

/**
 * Presenter pour la page de la création des campagnes.
 * @author cblanchard - SCUB
 */
public class CampagneCreationPresenter extends Presenter {

    /** Le service des campagnes. */
    private CampagneServiceGwtAsync campagneRpcService;

    /** Le services de dimensions. */
    private DimensionServiceGwtAsync dimensionRpcService;

    /** La vue de la page de création. */
    private CampagneCreationView view;

    /** Constantes du presenter. */
    private CampagneConstants presenterConstants;

    private DeskBar deskBar;

    /** Le service de gestion d'aides. */
    private AideServiceGwtAsync aideService;

    private HandlerRegistration deskBarRegistration;

    /**
     * Constructeur par defaut.
     * @param eventBus le bus des évenements
     * @param view la vue associé au presenter
     * @param campagneRpcService le service
     * @param dimensionRpcService le service des dimensions
     * @param appControllerConstants les constantes d'application
     * @param constantes les constantes
     * @param deskBar deskBar
     * @param aideService service d'aide.
     */
    public CampagneCreationPresenter(HandlerManager eventBus, CampagneServiceGwtAsync campagneRpcService, DimensionServiceGwtAsync dimensionRpcService,
        CampagneCreationView view, AppControllerConstants appControllerConstants, ConstantesModel constantes, DeskBar deskBar,
        AideServiceGwtAsync aideService) {
        super(eventBus);
        this.dimensionRpcService = dimensionRpcService;
        this.campagneRpcService = campagneRpcService;
        this.aideService = aideService;
        this.view = view;
        this.presenterConstants = GWT.create(CampagneConstants.class);
        this.deskBar = deskBar;
    }

    @Override
    public View asView() {
        return this.view;
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
        // Gestion des touches de la popup
        view.getAllKeyHandlersFocusPanel().addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                final int keyCode = event.getNativeKeyCode();
                if (keyCode == KeyCodes.KEY_ENTER) {
                    if (isDatesValides()) {
                        creerCampagne();
                    }
                } else if (keyCode == KeyCodes.KEY_ESCAPE) {
                    view.hidePopup();
                }
            };
        });
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.hidePopup();
                view.clearPopup();
            }
        });
        view.getSlbType().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                dimensionRpcService.rechercherTypesCampagnes(criteres, event.getCallBack());
            }
        });
        view.getBtnEnregistrer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (isDatesValides()) {
                    creerCampagne();
                }
            }
        });

        deskBarRegistration = deskBar.getEventBus().addHandler(EnableMinimizeWidgetEvent.TYPE, new EnableMinimizeWidgetEventHandler() {
            @Override
            public void enableMinimizeWidget(EnableMinimizeWidgetEvent event) {
                view.activerBoutonReduire(event.isEnabled());
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

        afficher();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                // on ajoute la popup à la deskBar
                deskBar.addPopup(view.getMinimizablePopup());
            }
        });
    }

    /**
     * .
     */
    public void afficher() {
        view.clearPopup();
        view.getCdbDateDebut().setValue(new Date());
        view.showPopup();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                view.getTbLibelle().setFocus(true);
            }
        });
    }

    private void creerCampagne() {
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().creationCampagne()));
        // Récupération des différentes valeurs
        final CampagneModel campagneModel = new CampagneModel();
        campagneModel.setLibelle(view.getTbLibelle().getValue());
        campagneModel.setType(view.getSlbType().getValue());
        if (view.getCdbDateDebut().getValue() != null) {
            campagneModel.setDateDebut(DateUtil.getString(view.getCdbDateDebut().getValue()));
        }
        if (view.getCdbDateFin().getValue() != null) {
            campagneModel.setDateFin(DateUtil.getString(view.getCdbDateFin().getValue()));
        }

        // Appel au service
        campagneRpcService.creerCampagne(campagneModel, new AsyncCallback<CampagneModel>() {
            @Override
            public void onSuccess(final CampagneModel campagneCreee) {
                view.hidePopup();
                final PopupInfoConfiguration config =
                    new PopupInfoConfiguration(presenterConstants.notificationCampagneCreee(), AppControllerConstants.NOTIFICATION_TIME_OUT);
                config.setCallback(new PopupCallback() {
                    @Override
                    public void onResult(boolean result) {
                        // on envoie un evenement de creation dans le bus
                        fireEventLocalBus(new CreateCampagneEvent(campagneCreee.getId(), campagneCreee.getLibelle()));
                    }
                });
                new DecoratedInfoPopup(config).show();
            }

            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                    // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                    view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                    view.masquerLoadingPopup();
                    view.getTbLibelle().setFocus(true);
                } else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }
        });
    }

    /**
     * Teste la validité des dates.
     * @return true si les dates sont valides, false sinon
     */
    private boolean isDatesValides() {
        boolean datesValides = true;
        final RapportModel rapport = new RapportModel();
        if (!view.getCdbDateDebut().isDateValide()) {
            rapport.ajoutRapport(presenterConstants.champDateDebut(), presenterConstants.erreurDateDebutInvalide(), true);
            datesValides = false;
        }
        if (!view.getCdbDateFin().isDateValide()) {
            rapport.ajoutRapport(presenterConstants.champDateFin(), presenterConstants.erreurDateFinInvalide(), true);
            datesValides = false;
        }
        view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
        if (!datesValides) {
            view.masquerLoadingPopup();
        }
        return datesValides;
    }

    /**
     * Interface pour la vue de la création de campagne.
     */
    public interface CampagneCreationView extends View {
        /**
         * Methode pour afficher la popup.
         */
        void showPopup();

        /** Masque la popUp de chargement. */
        void masquerLoadingPopup();

        /**
         * Affiche la popup de chargement.
         * @param loadingPopupConfiguration la configuration
         */
        void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration);

        /**
         * Renvoi les constantes de la vue.
         * @return les constantes de la vue
         */
        CampagneCreationViewImplConstants getViewConstants();

        /**
         * Renvoi le gestionnaire d'icone d'erreurs.
         * @return le gestionnaire d'iconde d'erreurs
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /** Méthode pour nettoyer la popup. */
        void clearPopup();

        /**
         * Methode appelé lorsque un servie Rpc ne s'est pas deroulé correctement.
         * @param config infos sur l'erreur.
         */
        void onRpcServiceFailure(final ErrorPopupConfiguration config);

        /**
         * Methode pour masquer la popup.
         */
        void hidePopup();

        /**
         * Renvoi la valeur de tbLibelle.
         * @return tbLibelle
         */
        DecoratedTextBox getTbLibelle();

        /**
         * Renvoi la valeur de slbType.
         * @return slbType
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbType();

        /**
         * Renvoi la valeur de cdbDateDebut.
         * @return cdbDateDebut
         */
        DecoratedCalendrierDateBox getCdbDateDebut();

        /**
         * Renvoi la valeur de cdbDateFin.
         * @return cdbDateFin
         */
        DecoratedCalendrierDateBox getCdbDateFin();

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
         * Recupere le handler.
         * @return le handler
         */
        HasAllKeyHandlers getAllKeyHandlersFocusPanel();

        /**
         * Active/Désactive le bouton réduire.
         * @param enabled flag
         */
        void activerBoutonReduire(boolean enabled);

        /**
         * Récupère la popup minimisable.
         * @return la popup minimisable
         */
        IsMinimizable getMinimizablePopup();

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
        deskBarRegistration.removeHandler();
    }
}

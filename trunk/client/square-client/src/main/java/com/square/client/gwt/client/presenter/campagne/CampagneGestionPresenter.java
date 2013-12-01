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
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.composants.richtextarea.RichTextToolbar;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.UpdateTabEvent;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.client.gwt.client.model.CampagneModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.OngletModel;
import com.square.client.gwt.client.service.CampagneServiceGwtAsync;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.view.campagne.gestion.CampagneGestionViewImpl;
import com.square.client.gwt.client.view.campagne.gestion.CampagneGestionViewImplConstants;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;

/**
 * Presenter pour la page de gestion d'une campagne.
 * @author cblanchard - SCUB
 */
public class CampagneGestionPresenter extends Presenter {

    /** Le service des dimensions. */
    private DimensionServiceGwtAsync dimensionServiceRpc;

    /** Le service des campagnes. */
    private CampagneServiceGwtAsync campagneServiceRpc;

    /** La vue. */
    private CampagneGestionView view;

    /** Id de la campagne. */
    private Long idCampagne;

    /** Type de campagne. */
    private IdentifiantLibelleGwt typeCampagne;

    private AppControllerConstants appControllerConstants;

    private ConstantesModel constantes;

    /** Constantes du presenter. */
    private CampagneConstants presenterConstants;

    /** Le service de gestion d'aides. */
    private AideServiceGwtAsync aideService;

    /**
     * Constructeur.
     * @param eventBus le bus
     * @param dimensionServiceRpc le service de dimension
     * @param campagneServiceRpc le service des campagnes
     * @param campagneGestionViewImpl la vue
     * @param idCampagne l'identifiant de la campagne
     * @param appControllerConstants les constantes de l'application
     * @param constantes les constantes du model
     * @param aideService service d'aide.
     */
    public CampagneGestionPresenter(HandlerManager eventBus, DimensionServiceGwtAsync dimensionServiceRpc, CampagneServiceGwtAsync campagneServiceRpc,
        CampagneGestionViewImpl campagneGestionViewImpl, Long idCampagne, AppControllerConstants appControllerConstants, ConstantesModel constantes,
        AideServiceGwtAsync aideService) {
        super(eventBus);
        this.dimensionServiceRpc = dimensionServiceRpc;
        this.campagneServiceRpc = campagneServiceRpc;
        this.view = campagneGestionViewImpl;
        this.idCampagne = idCampagne;
        this.aideService = aideService;
        this.appControllerConstants = appControllerConstants;
        this.constantes = constantes;
        this.presenterConstants = GWT.create(CampagneConstants.class);
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

        // Si l'utilisateur a le rôle campagne
        if (constantes.isHasRoleCampagne()) {
            // Alimentation de la suggestListBox des statuts
            view.getSlbStatut().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
                @Override
                public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                    final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                    criteres.setLibelle(event.getSuggest());
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                    dimensionServiceRpc.rechercherStatutsCampagnes(criteres, event.getCallBack());
                }
            });
            // Action sur le bouton annuler
            view.getBtnAnnuler().addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    initCampagne();
                }
            });
            // Action sur le bouton enregistrer
            view.getBtnEnregistrer().addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    if (isDatesValides()) {
                        enregistrementModificationsCampagne();
                    }
                }
            });
            // On autorise l'édition de la campagne
            view.setEnabled(true);
        } else {
            // On autorise seulement la consultation de la fiche de la campagne
            view.setEnabled(false);
        }
    }

    @Override
    public void onShow(HasWidgets container) {
        view.masquerLoadingPopup();
        initCampagne();

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

        container.add(view.asWidget());
    }

    private void initCampagne() {
        campagneServiceRpc.rechercherCampagnesParId(idCampagne, new AsyncCallback<CampagneModel>() {
            @Override
            public void onSuccess(CampagneModel campagne) {
                view.getlCode().setText(campagne.getCode());
                view.getTbLibelle().setValue(campagne.getLibelle());
                final IdentifiantLibelleGwt aucun = new IdentifiantLibelleGwt(-1L);
                view.getlType().setText(campagne.getType().getLibelle());
                typeCampagne = campagne.getType();
                view.getStatut().setValue(campagne.getStatut() != null ? campagne.getStatut() : aucun);
                view.getCreateur().setText(campagne.getCreateur().getLibelle());
                view.getCdbDateDebut().setValue(DateUtil.getDate(campagne.getDateDebut()));
                view.getCdbDateFin().setValue(DateUtil.getDate(campagne.getDateFin()));
                view.getRtaDescriptif().setHTML(campagne.getDescriptif() != null ? campagne.getDescriptif() : "");
                view.getTaDescriptif().setHTML(campagne.getDescriptif() != null ? campagne.getDescriptif() : "");
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    private void enregistrementModificationsCampagne() {
        // Récupération des données
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().enregistrementDonnees()));
        final CampagneModel campagneModel = new CampagneModel();
        campagneModel.setCode(view.getlCode().getText());
        campagneModel.setLibelle(view.getTbLibelle().getValue());
        campagneModel.setStatut(view.getStatut().getValue());
        if (view.getCdbDateDebut().getValue() != null) {
            campagneModel.setDateDebut(DateUtil.getString(view.getCdbDateDebut().getValue()));
        }
        if (view.getCdbDateFin().getValue() != null) {
            campagneModel.setDateFin(DateUtil.getString(view.getCdbDateFin().getValue()));
        }
        campagneModel.setDescriptif(view.getRtaDescriptif().getHTML());
        campagneModel.setId(idCampagne);
        campagneModel.setType(typeCampagne);
        // Appel au service de modification
        campagneServiceRpc.modifierCampagne(campagneModel, new AsyncCallback<CampagneModel>() {

            @Override
            public void onSuccess(final CampagneModel campagneMaj) {
                view.masquerLoadingPopup();
                // Réaffichage des informations
                initCampagne();
                final PopupInfoConfiguration config = new PopupInfoConfiguration(presenterConstants.notificationCampagneMaj(),
                    AppControllerConstants.NOTIFICATION_TIME_OUT);
                config.setCallback(new PopupCallback() {
                    @Override
                    public void onResult(boolean result) {
                        // on envoie un evenement de creation dans le bus
                        fireEventLocalBus(new UpdateTabEvent(new OngletModel(campagneMaj.getId().toString(), campagneMaj.getLibelle())));
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
                }
                else {
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
     * Interface de la vue de la gestion des campagnes.
     */
    public interface CampagneGestionView extends View {

        /**
         * Renvoi la valeur de lCode.
         * @return lCode
         */
        Label getlCode();

        /**
         * Accesseur pour le widget de la zone d'affichage du descriptif de la campagne.
         * @return le widget qui affiche le descriptif en mode lecture seule.
         */
        HasHTML getTaDescriptif();

        /**
         * Active / désactive les widgets encapsulés par la vue.
         * @param enabled true pour activer les widget / false pour les désactiver
         */
        void setEnabled(boolean enabled);

        /**
         * Accesseur champ créateur.
         * @return le widget
         */
        HasText getCreateur();

        /**
         * Récupère le manager d'icone.
         * @return le manager d'icone
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Renvoi la valeur de tbLibelle.
         * @return tbLibelle
         */
        HasValue<String> getTbLibelle();

        /**
         * Renvoi la valeur de slbStatut.
         * @return slbStatut
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbStatut();

        /**
         * Récupère le statut sélectionné.
         * @return le statut sélectionné.
         */
        HasValue<IdentifiantLibelleGwt> getStatut();

        /**
         * Renvoi la valeur de lType.
         * @return l²Type
         */
        Label getlType();

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
         * Renvoi la valeur de rtaDescriptif.
         * @return rtaDescriptif
         */
        RichTextArea getRtaDescriptif();

        /**
         * Renvoi la valeur de rttToolbar.
         * @return rttToolbar
         */
        RichTextToolbar getRttToolbar();

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
        CampagneGestionViewImplConstants getViewConstants();

        /** Masque la popup de chargement. */
        void masquerLoadingPopup();
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

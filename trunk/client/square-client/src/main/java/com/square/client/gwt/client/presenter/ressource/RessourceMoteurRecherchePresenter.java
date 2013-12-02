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
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetCellClickedEvent;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetCellClickedEventHandler;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEvent;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEventHandler;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.grid.remote.paging.RemotePagingTable;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.servlet.ParamServlet;
import org.scub.foundation.framework.gwt.module.client.util.servlet.ServletUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.controller.AppControllerViewImpl.ContainerTabPanel;
import com.square.client.gwt.client.event.OpenRessourceEvent;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheRessourceFonctionModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.RessourceCriteresRechercheModel;
import com.square.client.gwt.client.model.RessourceModel;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.RessourceServiceGwtAsync;
import com.square.client.gwt.client.service.UtilServiceGwtAsync;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;

/**
 * Presenter pour la page moteur de recherche de ressources.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class RessourceMoteurRecherchePresenter extends Presenter {

    /** Service asynchrone. */
    private RessourceServiceGwtAsync ressourceRpcService;

    /** Service asynchrone des dimensions. */
    private DimensionServiceGwtAsync dimensionServiceGwtAsync;

    /** Vue associé au presenter. */
    private RessourceMoteurRechercheView view;

    private UtilServiceGwtAsync utilServiceGwtAsync;

    private RemotePagingCriteriasGwt<RessourceCriteresRechercheModel> criteresRechercheEnCours;

    private ConstantesModel constantes;

    /** Constantes du presenter. */
    private RessourceMoteurRecherchePresenterConstants presenterConstants;

    private AideServiceGwtAsync aideService;

    /**
     * Constructeur par defaut.
     * @param eventBus le bus des évenements
     * @param ressourceRpcService service rpc pour les personnes physique
     * @param dimensionServiceGwtAsync service rpc pour les dimensions
     * @param view la vue associé au presenter
     * @param appControllerConstants les constantes d'application
     * @param utilServiceGwtAsync service rpc pour l'utilitaire
     * @param constantes les constantes
     * @param aideService service d'aide.
     */
    public RessourceMoteurRecherchePresenter(HandlerManager eventBus, RessourceServiceGwtAsync ressourceRpcService,
        DimensionServiceGwtAsync dimensionServiceGwtAsync, UtilServiceGwtAsync utilServiceGwtAsync, RessourceMoteurRechercheView view,
        AppControllerConstants appControllerConstants, ConstantesModel constantes, AideServiceGwtAsync aideService) {
        super(eventBus);
        this.ressourceRpcService = ressourceRpcService;
        this.dimensionServiceGwtAsync = dimensionServiceGwtAsync;
        this.view = view;
        this.utilServiceGwtAsync = utilServiceGwtAsync;
        this.constantes = constantes;
        this.presenterConstants = GWT.create(RessourceMoteurRecherchePresenterConstants.class);
        this.aideService = aideService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View asView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
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
        view.getBtnRechercher().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                rechercherRessource();
            }
        });
        view.getGestionToucheEntreHandler().addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    rechercherRessource();
                }
            }
        });
        view.getBtnEffacerRecherche().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.effacerRecherche();
                view.setFonctionEnabled(false);
            }
        });
        view.getRemotePagingHandlerManager().addHandler(SetDataProviderEvent.TYPE,
            new SetDataProviderEventHandler<RessourceCriteresRechercheModel, RessourceModel>() {
                @Override
                public void setDataProvider(SetDataProviderEvent<RessourceCriteresRechercheModel, RessourceModel> event) {
                    // on recupère les criteres
                    final RessourceCriteresRechercheModel criterias = new RessourceCriteresRechercheModel();

                    criterias.setNom(view.getTbNom().getValue().trim());
                    criterias.setPrenom(view.getTbPrenom().getValue().trim());

                    final List<Long> listeFonctions = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getSlbFonctionValue().getValue()) {
                        if (idLibelle != null) {
                            listeFonctions.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setIdFonctions(listeFonctions);

                    final List<Long> listeAgences = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getSlbAgenceValue().getValue()) {
                        if (idLibelle != null) {
                            listeAgences.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setIdAgences(listeAgences);

                    final List<Long> listeServices = new ArrayList<Long>();
                    if (view.getSlbServiceValue() != null && view.getSlbServiceValue().getValue() != null
                            && view.getSlbServiceValue().getValue().getIdentifiant() != null) {
                        listeServices.add(view.getSlbServiceValue().getValue().getIdentifiant());
                    }
                    criterias.setIdServices(listeServices);

                    final List<Long> listeEtats = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getSlbEtatValue().getValue()) {
                        if (idLibelle != null) {
                            listeEtats.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setIdEtats(listeEtats);

                    event.getParams().setCriterias(criterias);

                    // on affiche le lien pour l'export excel
                    if (criteresRechercheEnCours == null) {
                        view.afficherExportExcel();
                    }
                    // on stocke les parametres pour l'export excel
                    criteresRechercheEnCours = event.getParams();

                    // appel du service
                    ressourceRpcService.rechercherRessourceFullTextParCriteres(event.getParams(), event.getCallback());
                }
            });
        view.getRemotePagingHandlerManager().addHandler(SetCellClickedEvent.TYPE, new SetCellClickedEventHandler<RessourceModel>() {
            @Override
            public void setCellClicked(SetCellClickedEvent<RessourceModel> event) {
                // on envoie un evenement de demande d'ouverture dans le bus
                final OpenRessourceEvent openRessourceEvent =
                    new OpenRessourceEvent(event.getModele().getId(), event.getModele().getNom(), event.getModele().getPrenom());
                fireEventGlobalBus(openRessourceEvent);
            }
        });

        view.getSuggestionService().addSuggestHandler(
            new org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {

                @Override
                public void suggest(
                    org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                    final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                    final String libelleServiceRecherche = event.getSuggest();
                    criteres.setLibelle(libelleServiceRecherche);
                    criteres.setVisible(true);
                    if (libelleServiceRecherche == null || "".equals(libelleServiceRecherche.trim())) {
                        criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                    }
                    dimensionServiceGwtAsync.rechercherRessourceServiceParCriteres(criteres, event.getCallBack());
                }
            });

        view.getSlbServiceValue().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                view.nettoyerFonction();
                view.setFonctionEnabled(event.getValue() != null && event.getValue().getIdentifiant() != null);
            }
        });

        view.getSuggestionFonction().addSuggestHandler(
            new org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {

                @Override
                public void suggest(
                    org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                    final DimensionCritereRechercheRessourceFonctionModel criteres = new DimensionCritereRechercheRessourceFonctionModel();
                    final String libelleFonctionRecherchee = event.getSuggest();
                    criteres.setLibelle(libelleFonctionRecherchee);
                    criteres.setVisible(true);
                    final List<Long> listeIdsServices = new ArrayList<Long>();
                    if (view.getSlbServiceValue() != null && view.getSlbServiceValue().getValue() != null
                            && view.getSlbServiceValue().getValue().getIdentifiant() != null) {
                        listeIdsServices.add(view.getSlbServiceValue().getValue().getIdentifiant());
                    }
                    criteres.setListeIdsServices(listeIdsServices);
                    if (libelleFonctionRecherchee == null || "".equals(libelleFonctionRecherchee.trim())) {
                        criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                    }
                    dimensionServiceGwtAsync.rechercherRessourceFonctionParCriteres(criteres, event.getCallBack());
                }
            });

        view.getSuggestionEtat().addSuggestHandler(
            new org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {

                @Override
                public void suggest(
                    org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                    final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                    final String libelleEtatRecherche = event.getSuggest();
                    criteres.setLibelle(libelleEtatRecherche);
                    criteres.setVisible(true);
                    if (libelleEtatRecherche == null || "".equals(libelleEtatRecherche.trim())) {
                        criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                    }
                    dimensionServiceGwtAsync.rechercherRessourceEtatParCriteres(criteres, event.getCallBack());
                }
            });

        view.getSuggestionAgence().addSuggestHandler(
            new org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {

                @Override
                public void suggest(
                    org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                    final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                    final String libelleAgenceRecherchee = event.getSuggest();
                    criteres.setLibelle(libelleAgenceRecherchee);
                    criteres.setVisible(true);
                    if (libelleAgenceRecherchee == null || "".equals(libelleAgenceRecherchee.trim())) {
                        criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                    }
                    dimensionServiceGwtAsync.rechercherAgenceParCriteres(criteres, event.getCallBack());
                }
            });

        final ClickHandler exportExcel = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                utilServiceGwtAsync.mapperCriteresRecherche(criteresRechercheEnCours, new AsyncCallback<Map<String, String>>() {
                    @Override
                    public void onSuccess(Map<String, String> result) {
                        // on ajoute le type du service à appeler et le type de l'objet
                        result.put(constantes.getParamService(), constantes.getValueServiceRechercheRessource());
                        // on transforme la map en liste de params
                        final List<ParamServlet> listeParams = new ArrayList<ParamServlet>();
                        for (String key : result.keySet()) {
                            listeParams.add(new ParamServlet(key, result.get(key)));
                        }
                        // on cree l'url complete
                        final String urlServleComplete = ServletUtil.formatUrl(constantes.getUrlServletExporterRecherche(), listeParams);
                        // on appelle la servlet
                        Window.open(urlServleComplete, "_blank", "resizable=yes,menubar=no,location=no");
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                    }
                });
            }
        };
        view.getImageExportExcel().addClickHandler(exportExcel);
        view.getLabelExportExcel().addClickHandler(exportExcel);

        if (constantes.isHasRoleGestionnaireOpportunites()) {
            view.getBtnModifierQuotas().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    if (view.getRemotePagingTable().getCurrentList() != null && view.getRemotePagingTable().getCurrentList().size() > 0) {
                        // Récupération de l'ensemble des ressources du moteur de recherche
                        getListeRessourcesRecherche();
                    }
                    else {
                        final ErrorPopupConfiguration config = new ErrorPopupConfiguration(presenterConstants.aucuneRessourceRecherche());
                        ErrorPopup.afficher(config);
                    }
                }
            });
        }
    }

    /**
     * Récupère la liste des ressources de la recherche.
     */
    private void getListeRessourcesRecherche() {
        final AsyncCallback<List<Long>> asyncCallback = new AsyncCallback<List<Long>>() {
            @Override
            public void onSuccess(List<Long> result) {
            		// FIXME : Toujours Utile ??
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        };
        ressourceRpcService.rechercherIdsRessourcesFullTextParCriteres(criteresRechercheEnCours.getCriterias(), asyncCallback);
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

        container.add(view.asWidget());
        // On initialise la listBox de l'état à "Actif"
        final List<IdentifiantLibelleGwt> etats = new ArrayList<IdentifiantLibelleGwt>();
        etats.add(constantes.getIdLibelleEtatActifRessource());
        view.getSlbEtatValue().setValue(etats);
        // Fonction non éditable
        view.setFonctionEnabled(false);
        if (constantes.isHasRoleGestionnaireOpportunites()) {
            ((ContainerTabPanel) container).addContextMenu(view.asContextMenuWidget());
        }
    }

    /**
     * Recherche une ressource à partir des criteres.
     */
    private void rechercherRessource() {
        view.getRemotePagingTable().rechercher();
    }

    /**
     * Interface pour la vue PersonnePhysiqueMoteurRechercheView.
     */
    public interface RessourceMoteurRechercheView extends View {
        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbNom();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbPrenom();

        /**
         * Retourne le bouton.
         * @return le bouton
         */
        HasClickHandlers getBtnRechercher();

        /**
         * Retourne le bouton.
         * @return le bouton
         */
        HasClickHandlers getBtnEffacerRecherche();

        /**
         * Affiche un message de chargement.
         * @param config la config
         */
        void afficherLoadingPopup(LoadingPopupConfiguration config);

        /**
         * Affiche un message d'avertissement.
         * @param config la config
         */
        void afficherInfoPopup(PopupInfoConfiguration config);

        /**
         * Methode appelé lorsque un service Rpc s'est deroulé correctement.
         */
        void onRpcServiceSuccess();

        /**
         * Methode appelé lorsque un servie Rpc ne s'est pas deroulé correctement.
         * @param config infos sur l'erreur.
         */
        void onRpcServiceFailure(final ErrorPopupConfiguration config);

        /**
         * Retourne le tableau d'affichage des ressources.
         * @return le tableau.
         */
        RemotePagingTable<RessourceModel, RessourceCriteresRechercheModel> getRemotePagingTable();

        /**
         * Retourne le gestionnaire d'evenement du tableau.
         * @return le gestionnaire d'evenement.
         */
        HandlerManager getRemotePagingHandlerManager();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbFonction();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbFonctionValue();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbAgence();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbAgenceValue();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbService();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<IdentifiantLibelleGwt> getSlbServiceValue();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbEtat();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbEtatValue();

        /**
         * Handler pour la suggestion de recherche d'agences.
         * @return le handler.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestionAgence();

        /**
         * Handler pour la suggestion de recherche de fonctions.
         * @return le handler.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestionFonction();

        /**
         * Handler pour la suggestion de recherche de services.
         * @return le handler.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestionService();

        /**
         * Handler pour la suggestion de recherche d'etats.
         * @return le handler.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestionEtat();

        /**
         * Initialiser le moteur de recherche.
         */
        void effacerRecherche();

        /**
         * Gerer la touche entré.
         * @return le handler sur la gestion de la touche entré.
         */
        HasKeyPressHandlers getGestionToucheEntreHandler();

        /**
         * Handler sur le clic de l'export excel.
         * @return handler
         */
        HasClickHandlers getImageExportExcel();

        /**
         * Handler sur le clic de l'export excel.
         * @return handler
         */
        HasClickHandlers getLabelExportExcel();

        /**
         * Affiche le bloc d'export excel.
         */
        void afficherExportExcel();

        /**
         * Rend éditable ou pas la liste des fonctions.
         * @param enabled flag indiquant si la liste est éditable (true : éditable, false : non éditable)
         */
        void setFonctionEnabled(boolean enabled);

        /** Efface les fonctions sélectionnées. */
        void nettoyerFonction();

        /**
         * Récupère le menu contextuel.
         * @return le widget.
         */
        Widget asContextMenuWidget();

        /**
         * Accesseur sur le ClickHandler du bouton de modification des quotas.
         * @return le handler.
         */
        HasClickHandlers getBtnModifierQuotas();

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

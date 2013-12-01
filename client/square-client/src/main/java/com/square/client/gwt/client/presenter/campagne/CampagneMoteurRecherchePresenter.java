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
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetCellClickedEvent;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetCellClickedEventHandler;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEvent;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEventHandler;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.composants.grid.remote.paging.RemotePagingTable;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
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
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.OpenCampagneEvent;
import com.square.client.gwt.client.model.CampagneCriteresRechercheModel;
import com.square.client.gwt.client.model.CampagneRechercheModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRessourceModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.service.CampagneServiceGwtAsync;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.UtilServiceGwtAsync;
import com.square.client.gwt.client.view.campagne.moteur.recherche.CampagneMoteurRechercheViewImplConstants;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;

/**
 * Présenteur pour le moteur de recherche des campagnes.
 * @author cblanchard - SCUB
 */
public class CampagneMoteurRecherchePresenter extends Presenter {

    /** Le service des campagnes. */
    private CampagneServiceGwtAsync campagneRpcService;

    /** Le service des dimensions. */
    private DimensionServiceGwtAsync dimensionServiceRpcService;

    /** La vue. */
    private CampagneMoteurRechercheView view;

    private UtilServiceGwtAsync utilServiceGwtAsync;

    private RemotePagingCriteriasGwt<CampagneCriteresRechercheModel> criteresRechercheEnCours;

    private ConstantesModel constantes;

    /** Service de gestion des aides . */
    private AideServiceGwtAsync aideService;

    /**
     * Constructeur.
     * @param eventBus le bus
     * @param campagneRpcService le service des campagnes
     * @param dimensionServiceRpcService le services des dimensions
     * @param utilServiceGwtAsync service rpc pour l'utilitaire
     * @param constantes les constantes
     * @param campagneMoteurRechercheView la vue
     * @param appControllerConstants les constantes de l'applications
     */
    public CampagneMoteurRecherchePresenter(HandlerManager eventBus, CampagneServiceGwtAsync campagneRpcService,
        DimensionServiceGwtAsync dimensionServiceRpcService, UtilServiceGwtAsync utilServiceGwtAsync, ConstantesModel constantes,
        CampagneMoteurRechercheView campagneMoteurRechercheView, AppControllerConstants appControllerConstants, AideServiceGwtAsync aideService) {
        super(eventBus);
        this.campagneRpcService = campagneRpcService;
        this.dimensionServiceRpcService = dimensionServiceRpcService;
        this.view = campagneMoteurRechercheView;
        this.utilServiceGwtAsync = utilServiceGwtAsync;
        this.constantes = constantes;
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
        // Alimentation de la sugggestListBox du type
        view.getSlbType().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if ("".equals(event.getSuggest().trim())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceRpcService.rechercherTypesCampagnes(criteres, event.getCallBack());

            }
        });

        // Alimentation de la suggestListBox des statuts
        view.getSlbStatut().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if ("".equals(event.getSuggest().trim())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceRpcService.rechercherStatutsCampagnes(criteres, event.getCallBack());
            }
        });
        // Alimentation de la suggestListBox du créateur
        view.getSlbCreateur().addSuggestHandler(new SuggestListBoxSuggestEventHandler<DimensionRessourceModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<DimensionRessourceModel> event) {
                final DimensionCriteresRechercheRessourceModel criteres = new DimensionCriteresRechercheRessourceModel();
                final String libelleRessourceRecherchee = event.getSuggest();
                criteres.setNom(libelleRessourceRecherchee);
                criteres.setPrenom(libelleRessourceRecherchee);
                if ("".equals(libelleRessourceRecherchee.trim())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceRpcService.rechercherRessourceParCriteres(criteres, event.getCallBack());
            }
        });

        // Bouton rechercher
        view.getBtnRechercher().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.getRemotePagingTableCampagne().rechercher();
            }
        });
        view.getGestionToucheEntreHandler().addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    view.getRemotePagingTableCampagne().rechercher();
                }
            }
        });
        view.getBtnEffacerRecherche().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.effacerRecherche();
            }
        });

        // Alimentation du tableau
        view.getRemotePagingHandlerManager().addHandler(SetDataProviderEvent.TYPE,
            new SetDataProviderEventHandler<CampagneCriteresRechercheModel, CampagneRechercheModel>() {
                @Override
                public void setDataProvider(SetDataProviderEvent<CampagneCriteresRechercheModel, CampagneRechercheModel> event) {
                    // on recupère les criteres
                    final CampagneCriteresRechercheModel criterias = new CampagneCriteresRechercheModel();
                    // Le code
                    if (!"".equals(view.getTbCodeValue().getValue().trim())) {
                        final Long codeCampagne = Long.parseLong(view.getTbCodeValue().getValue());
                        if (codeCampagne <= Integer.MAX_VALUE) {
                            criterias.setCode(view.getTbCodeValue().getValue());
                        }
                    }
                    // Le libelle
                    criterias.setLibelle(view.getTbLibelleValue().getValue());
                    // Les types
                    final List<Long> listeTypes = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getSlbTypeValue().getValue()) {
                        if (idLibelle != null) {
                            listeTypes.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeTypes(listeTypes);
                    // Les status
                    final List<Long> listeStatuts = new ArrayList<Long>();
                    for (IdentifiantLibelleGwt idLibelle : view.getSlbStatutValue().getValue()) {
                        if (idLibelle != null) {
                            listeStatuts.add(idLibelle.getIdentifiant());
                        }
                    }
                    criterias.setListeStatuts(listeStatuts);

                    if (view.getCdbDateInfDateDebut().getValue() != null) {
                        criterias.setDateDebutBorneInf(DateUtil.getString(view.getCdbDateInfDateDebut().getValue()));
                    }
                    if (view.getCdbDateSupDateDebut().getValue() != null) {
                        criterias.setDateDebutBorneSup(DateUtil.getString(view.getCdbDateSupDateDebut().getValue()));
                    }
                    if (view.getCdbDateInfDateFin().getValue() != null) {
                        criterias.setDateFinBorneInf(DateUtil.getString(view.getCdbDateInfDateFin().getValue()));
                    }
                    if (view.getCdbDateSupDateFin().getValue() != null) {
                        criterias.setDateFinBorneSup(DateUtil.getString(view.getCdbDateSupDateFin().getValue()));
                    }
                    // Le créateur
                    final DimensionRessourceModel createur = view.getSlbCreateurValue().getValue();
                    if (createur != null) {
                        criterias.setIdCreateur(createur.getIdentifiant());
                    }
                    event.getParams().setCriterias(criterias);

                    // on affiche le lien pour l'export excel
                    if (criteresRechercheEnCours == null) {
                        view.afficherExportExcel();
                    }
                    // on stocke les parametres pour l'export excel
                    criteresRechercheEnCours = event.getParams();

                    // appel du service
                    campagneRpcService.rechercherCampagnesParCriteres(event.getParams(), event.getCallback());
                }
            });
        // Evenement d'un clique sur une ligne
        view.getRemotePagingHandlerManager().addHandler(SetCellClickedEvent.TYPE, new SetCellClickedEventHandler<CampagneRechercheModel>() {
            @Override
            public void setCellClicked(SetCellClickedEvent<CampagneRechercheModel> event) {
                // Demande d'ouverture de la fiche de la campagne
                view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().chargementCampagne()));
                fireEventGlobalBus(new OpenCampagneEvent(event.getModele().getId(), event.getModele().getLibelle()));
            }
        });
        final ClickHandler exportExcel = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                utilServiceGwtAsync.mapperCriteresRecherche(criteresRechercheEnCours, new AsyncCallback<Map<String, String>>() {
                    @Override
                    public void onSuccess(Map<String, String> result) {
                        // on ajoute le type du service à appeler et le type de l'objet
                        result.put(constantes.getParamService(), constantes.getValueServiceRechercheCampagne());
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
    }

    /**
     * Interface de la vue du moteur de recherche.
     */
    public interface CampagneMoteurRechercheView extends View {

        /**
         * Renvoi la valeur de tbCode.
         * @return tbCode
         */
        HasValue<String> getTbCodeValue();

        /**
         * Renvoi la valeur de tbCode.
         * @return tbCode
         */
        HasKeyPressHandlers getTbCode();

        /**
         * Renvoi la valeur de tbLibelle.
         * @return tbLibelle
         */
        HasValue<String> getTbLibelleValue();

        /**
         * Renvoi la valeur de tbLibelle.
         * @return tbLibelle
         */
        HasKeyPressHandlers getTbLibelle();

        /**
         * Renvoi la valeur de slbType.
         * @return slbType
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbTypeValue();

        /**
         * Renvoi la valeur de slbType.
         * @return slbType
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbType();

        /**
         * Renvoi la valeur de slbStatut.
         * @return slbStatut
         */
        HasValue<List<IdentifiantLibelleGwt>> getSlbStatutValue();

        /**
         * Renvoi la valeur de slbStatut.
         * @return slbStatut
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbStatut();

        /**
         * Renvoi la valeur de cdbDateInfDateDebut.
         * @return cdbDateInfDateDebut
         */
        DecoratedCalendrierDateBox getCdbDateInfDateDebut();

        /**
         * Renvoi la valeur de cdbDateSupDateDebut.
         * @return cdbDateSupDateDebut
         */
        DecoratedCalendrierDateBox getCdbDateSupDateDebut();

        /**
         * Renvoi la valeur de cdbDateInfDateFin.
         * @return cdbDateInfDateFin
         */
        DecoratedCalendrierDateBox getCdbDateInfDateFin();

        /**
         * Renvoi la valeur de cdbDateSupDateFin.
         * @return cdbDateSupDateFin
         */
        DecoratedCalendrierDateBox getCdbDateSupDateFin();

        /**
         * renvoi la valeur de slbCreateur.
         * @return slbCreateur
         */
        HasValue<DimensionRessourceModel> getSlbCreateurValue();

        /**
         * renvoi la valeur de slbCreateur.
         * @return slbCreateur
         */
        HasSuggestListBoxHandler<DimensionRessourceModel> getSlbCreateur();

        /**
         * Renvoi la valeur de btnRechercher.
         * @return btnRechercher
         */
        HasClickHandlers getBtnRechercher();

        /**
         * Retourne le bouton.
         * @return le bouton
         */
        HasClickHandlers getBtnEffacerRecherche();

        /**
         * Renvoi la valeur de remotePagingTableCampagne.
         * @return remotePagingTableCampagne
         */
        RemotePagingTable<CampagneRechercheModel, CampagneCriteresRechercheModel> getRemotePagingTableCampagne();

        /**
         * Renvoi la valeur de remotePagingHandlerManager.
         * @return remotePagingHandlerManager
         */
        HandlerManager getRemotePagingHandlerManager();

        /**
         * Affiche la popup de chargement.
         * @param loadingPopupConfiguration la configuration
         */
        void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration);

        /**
         * Masque la fenêtre de chargement.
         */
        void masquerLoadingPopup();

        /**
         * Récupère les constantes de la vue.
         * @return les constantes
         */
        CampagneMoteurRechercheViewImplConstants getViewConstants();

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
         * Methode appelé lorsque un servie Rpc ne s'est pas deroulé correctement.
         * @param config infos sur l'erreur.
         */
        void onRpcServiceFailure(final ErrorPopupConfiguration config);

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

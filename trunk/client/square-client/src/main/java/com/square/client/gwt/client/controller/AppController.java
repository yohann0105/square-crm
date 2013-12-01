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
package com.square.client.gwt.client.controller;

import static com.square.client.gwt.client.controller.AppControllerConstants.SAUT_LIGNE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.composant.popup.PopupFusionPresenter;
import com.square.client.gwt.client.event.AppellerPersonneEvent;
import com.square.client.gwt.client.event.AppellerPersonneEventHandler;
import com.square.client.gwt.client.event.CloseTabEvent;
import com.square.client.gwt.client.event.CloseTabEventHandler;
import com.square.client.gwt.client.event.CreateCampagneEvent;
import com.square.client.gwt.client.event.CreateCampagneEventHandler;
import com.square.client.gwt.client.event.FinSuppressionPersonnesEvent;
import com.square.client.gwt.client.event.FinSuppressionPersonnesEventHandler;
import com.square.client.gwt.client.event.FusionnerPersonnesEvent;
import com.square.client.gwt.client.event.FusionnerPersonnesEventHandler;
import com.square.client.gwt.client.event.OpenCampagneEvent;
import com.square.client.gwt.client.event.OpenCampagneEventHandler;
import com.square.client.gwt.client.event.OpenPersonEvent;
import com.square.client.gwt.client.event.OpenPersonEventHandler;
import com.square.client.gwt.client.event.OpenPersonneMoraleEvent;
import com.square.client.gwt.client.event.OpenPersonneMoraleEventHandler;
import com.square.client.gwt.client.event.OpenRessourceEvent;
import com.square.client.gwt.client.event.OpenRessourceEventHandler;
import com.square.client.gwt.client.event.RafraichirRecherchePersonneEvent;
import com.square.client.gwt.client.event.RafraichirRecherchePersonneEventHandler;
import com.square.client.gwt.client.event.UpdateTabEvent;
import com.square.client.gwt.client.event.UpdateTabEventHandler;
import com.square.client.gwt.client.event.actions.AjoutActionEvent;
import com.square.client.gwt.client.event.actions.AjoutActionEventHandler;
import com.square.client.gwt.client.event.actions.AjoutCampagneEvent;
import com.square.client.gwt.client.event.actions.AjoutCampagneEventHandler;
import com.square.client.gwt.client.event.actions.AjoutPersonneEvent;
import com.square.client.gwt.client.event.actions.AjoutPersonneEventHandler;
import com.square.client.gwt.client.model.CampagneModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.OngletModel;
import com.square.client.gwt.client.model.PersonneCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneMoraleSimpleModel;
import com.square.client.gwt.client.model.PersonnePermissionOngletModel;
import com.square.client.gwt.client.model.PersonneSimpleModel;
import com.square.client.gwt.client.model.RessourceModel;
import com.square.client.gwt.client.presenter.action.ActionCreationPresenter;
import com.square.client.gwt.client.presenter.action.ActionMoteurRecherchePresenter;
import com.square.client.gwt.client.presenter.actions.ActionsPresenter;
import com.square.client.gwt.client.presenter.actions.ActionsPresenter.ActionsView;
import com.square.client.gwt.client.presenter.agenda.AgendaPresenter;
import com.square.client.gwt.client.presenter.campagne.CampagneCreationPresenter;
import com.square.client.gwt.client.presenter.campagne.CampagneGestionPresenter;
import com.square.client.gwt.client.presenter.campagne.CampagneMoteurRecherchePresenter;
import com.square.client.gwt.client.presenter.personne.morale.GestionPersonneMoralePresenter;
import com.square.client.gwt.client.presenter.personne.morale.PersonneMoraleMoteurRecherchePresenter;
import com.square.client.gwt.client.presenter.personne.physique.GestionPersonnePhysiquePresenter;
import com.square.client.gwt.client.presenter.personne.physique.PersonnePhysiqueCreationPresenter;
import com.square.client.gwt.client.presenter.personne.physique.PersonnePhysiqueMoteurRecherchePresenter;
import com.square.client.gwt.client.presenter.ressource.GestionRessourcePresenter;
import com.square.client.gwt.client.presenter.ressource.RessourceMoteurRecherchePresenter;
import com.square.client.gwt.client.presenter.ressource.GestionRessourcePresenter.GestionRessourceView;
import com.square.client.gwt.client.service.ActionServiceGwt;
import com.square.client.gwt.client.service.ActionServiceGwtAsync;
import com.square.client.gwt.client.service.AgendaServiceGwt;
import com.square.client.gwt.client.service.AgendaServiceGwtAsync;
import com.square.client.gwt.client.service.CampagneServiceGwt;
import com.square.client.gwt.client.service.CampagneServiceGwtAsync;
import com.square.client.gwt.client.service.ConstantesServiceGwt;
import com.square.client.gwt.client.service.ConstantesServiceGwtAsync;
import com.square.client.gwt.client.service.DimensionServiceGwt;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.OpportuniteServiceGwt;
import com.square.client.gwt.client.service.OpportuniteServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneMoraleServiceGwt;
import com.square.client.gwt.client.service.PersonneMoraleServiceGwtAsync;
import com.square.client.gwt.client.service.PersonnePhysiqueServiceGwt;
import com.square.client.gwt.client.service.PersonnePhysiqueServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwt;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.service.RessourceServiceGwt;
import com.square.client.gwt.client.service.RessourceServiceGwtAsync;
import com.square.client.gwt.client.service.UtilServiceGwt;
import com.square.client.gwt.client.service.UtilServiceGwtAsync;
import com.square.client.gwt.client.view.action.creation.ActionCreationViewImpl;
import com.square.client.gwt.client.view.action.moteur.recherche.ActionMoteurRechercheViewImpl;
import com.square.client.gwt.client.view.actions.ActionsViewImpl;
import com.square.client.gwt.client.view.agenda.AgendaViewImpl;
import com.square.client.gwt.client.view.campagne.creation.CampagneCreationViewImpl;
import com.square.client.gwt.client.view.campagne.gestion.CampagneGestionViewImpl;
import com.square.client.gwt.client.view.campagne.moteur.recherche.CampagneMoteurRechercheViewImpl;
import com.square.client.gwt.client.view.personne.morale.gestion.GestionPersonneMoraleViewImpl;
import com.square.client.gwt.client.view.personne.morale.moteur.recherche.PersonneMoraleMoteurRechercheViewImpl;
import com.square.client.gwt.client.view.personne.physique.creation.PersonnePhysiqueCreationViewImpl;
import com.square.client.gwt.client.view.personne.physique.gestion.GestionPersonnePhysiqueViewImpl;
import com.square.client.gwt.client.view.personne.physique.moteur.recherche.PersonnePhysiqueMoteurRechercheViewImpl;
import com.square.client.gwt.client.view.ressource.gestion.GestionRessourceViewImpl;
import com.square.client.gwt.client.view.ressource.moteur.recherche.RessourceMoteurRechercheViewImpl;
import com.square.composant.aide.gwt.client.service.AideServiceGwt;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composant.fusion.square.client.event.FinFusionPersonnesEvent;
import com.square.composant.fusion.square.client.event.FinFusionPersonnesEventHandler;
import com.square.composant.ged.square.client.service.DocumentsServiceGwt;
import com.square.composant.ged.square.client.service.DocumentsServiceGwtAsync;
import com.square.composant.tarificateur.square.client.event.OpenOpportuniteEvent;
import com.square.composant.tarificateur.square.client.event.OpenOpportuniteEventHandler;
import com.square.composant.tarificateur.square.client.presenter.transaction.recherche.BoutonRechercherTransactionPresenter;
import com.square.composant.tarificateur.square.client.service.TarificateurServiceGwt;
import com.square.composant.tarificateur.square.client.service.TarificateurServiceGwtAsync;
import com.square.composant.tarificateur.square.client.view.transaction.recherche.BoutonRechercherTransactionViewImpl;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;

/**
 * Controller de l'application.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class AppController extends Presenter implements ValueChangeHandler<String> {

    /** Service asynchrone. */
    private PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService;

    private DimensionServiceGwtAsync dimensionServiceRpcService;

    private UtilServiceGwtAsync utilRpcService;

    private ConstantesServiceGwtAsync constantesRpcService;

    /** Service de gestion des aides . */
    private AideServiceGwtAsync aideService;

    private PersonneServiceGwtAsync personneRpcService;

    private PersonneMoraleServiceGwtAsync personneMoraleRpcService;

    private CampagneServiceGwtAsync campagneRpcService;

    private ActionServiceGwtAsync actionRpcService;

    private AgendaServiceGwtAsync agendaRpcService;

    private OpportuniteServiceGwtAsync opportuniteRpcService;

    private RessourceServiceGwtAsync ressourceRpcService;

//    private AiaServiceGwtAsync aiaServiceGwtAsync;

    private DocumentsServiceGwtAsync documentsService;

    private TarificateurServiceGwtAsync tarificateurServiceGwtAsync;

    /** Constantes du controller d'application. */
    protected AppControllerConstants appControllerConstants;

    /** Messages du controller d'application. */
    protected AppControllerMessages appControllerMessages;

    /** LES VUES STATIQUES. **/
    private AppControllerView appControllerView;

    private ActionsView actionContexteView;

    // private AgendaView agendaView;
    private List<OngletModel> listeOnglets;

    /** LES PRESENTER STATIQUE. **/
//    private ZoneTelephoniePresenter zoneTelephoniePresenter;

    private ConstantesModel constantes;

    private DimensionRessourceModel ressourceConnectee;

    /** Booléen pour savoir si l'utilisateur Square est connecté à la téléphonie. */
    private boolean isConnecteTelephonie = false;

    /** Booléen pour savoir si l'utilisateur Square a la téléphonie. */
    private boolean hasTelephonie = false;

    // LISTE DES PRESENTERS
    private Map<String, Presenter> ongletPresenterDynamique;

    private PersonnePhysiqueMoteurRecherchePresenter personnePhysiqueMoteurRecherchePresenter;

    private PersonneMoraleMoteurRecherchePresenter personneMoraleMoteurRecherchePresenter;

    private CampagneMoteurRecherchePresenter campagneMoteurRecherchePresenter;

    private ActionMoteurRecherchePresenter actionMoteurRecherchePresenter;

    private RessourceMoteurRecherchePresenter ressourceMoteurRecherchePresenter;

    private AgendaPresenter agendaPresenter;

//    private DashBoard dashBoardPresenter;

    private PopupFusionPresenter popupFusion;

    private CampagneCreationPresenter campagneCreationPresenter;

    private CampagneGestionPresenter campagneGestionPresenter;

    private PersonnePhysiqueCreationPresenter personnePhysiqueCreationPresenter;

    private ActionsPresenter actionsPresenter;

    private BoutonRechercherTransactionPresenter boutonRechercherTransactionPresenter;

    private ActionCreationPresenter actionCreationPresenter;

    /**
     * Constructeur par défaut.
     * @param eventBus bus des évenements.
     * @param appControllerConstants gestion des constantes de l'application.
     * @param appControllerMessages gestion des messages de l'application.
     * @param ressourceConnectee la ressource qui est authentifiée.
     * @param constantes constantes
     */
    public AppController(final HandlerManager eventBus, final AppControllerConstants appControllerConstants, AppControllerMessages appControllerMessages,
        DimensionRessourceModel ressourceConnectee, final ConstantesModel constantes) {
        super(eventBus);
        History.addValueChangeHandler(this);
        this.appControllerConstants = appControllerConstants;
        this.appControllerMessages = appControllerMessages;
        this.constantesRpcService = GWT.create(ConstantesServiceGwt.class);
        this.personnePhysiqueRpcService = GWT.create(PersonnePhysiqueServiceGwt.class);
        this.dimensionServiceRpcService = GWT.create(DimensionServiceGwt.class);
        this.utilRpcService = GWT.create(UtilServiceGwt.class);
        this.personneRpcService = GWT.create(PersonneServiceGwt.class);
        this.personneMoraleRpcService = GWT.create(PersonneMoraleServiceGwt.class);
        this.campagneRpcService = GWT.create(CampagneServiceGwt.class);
        this.actionRpcService = GWT.create(ActionServiceGwt.class);
        this.agendaRpcService = GWT.create(AgendaServiceGwt.class);
        this.opportuniteRpcService = GWT.create(OpportuniteServiceGwt.class);
        this.ressourceRpcService = GWT.create(RessourceServiceGwt.class);
//        this.aiaServiceGwtAsync = GWT.create(AiaServiceGwt.class);
        this.tarificateurServiceGwtAsync = GWT.create(TarificateurServiceGwt.class);
        this.aideService = GWT.create(AideServiceGwt.class);
        this.documentsService = GWT.create(DocumentsServiceGwt.class);

        this.ressourceConnectee = ressourceConnectee;

        this.constantes = constantes;
        this.ongletPresenterDynamique = new HashMap<String, Presenter>();

        // on construit la liste des onglets fixes
        this.listeOnglets = new ArrayList<OngletModel>();
        listeOnglets.add(new OngletModel(appControllerConstants.libelleTabPersonnes(), appControllerConstants.libelleTabPersonnes(), SquareResources.INSTANCE
                .iconeOngletPersonnePhysique()));
        listeOnglets.add(new OngletModel(appControllerConstants.libelleTabSocietes(), appControllerConstants.libelleTabSocietes(), SquareResources.INSTANCE
                .iconeOngletPersonneMorale()));
        listeOnglets.add(new OngletModel(appControllerConstants.libelleTabCampagnes(), appControllerConstants.libelleTabCampagnes(), SquareResources.INSTANCE
                .iconeOngletCampagnesMarketing()));
        listeOnglets.add(new OngletModel(appControllerConstants.libelleTabActions(), appControllerConstants.libelleTabActions(), SquareResources.INSTANCE
                .iconeOngletActionsRelances()));
        if (constantes.isHasRoleAdmin() || constantes.isHasRoleAnimateur()
            || (ressourceConnectee.getFonction() != null && ressourceConnectee.getFonction().getIdentifiant().equals(constantes.getIdFonctionCC()))) {
            listeOnglets.add(new OngletModel(appControllerConstants.libelleTabAgenda(), appControllerConstants.libelleTabAgenda(), SquareResources.INSTANCE
                .iconeOngletAgenda()));
        }
        listeOnglets.add(new OngletModel(appControllerConstants.libelleTabRessources(), appControllerConstants.libelleTabRessources(), SquareResources.INSTANCE
                .iconeOngletRessources()));

        // INITIALISATON DE LA VUE PRINCIPAL
        this.appControllerView = new AppControllerViewImpl(listeOnglets, localEventBus, appControllerConstants, eventBus);
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
    }

    @Override
    public void onBind() {
        addEventHandlerToGlobalBus(FusionnerPersonnesEvent.TYPE, new FusionnerPersonnesEventHandler() {
            @Override
            public void demanderFusion(FusionnerPersonnesEvent event) {
                if (popupFusion == null) {
                    popupFusion =
                        addChildPresenter(new PopupFusionPresenter(eventBus, event.getNom(), event.getPrenom(), event.getDateNaissance(), event
                                .getIdPersonneSelectionnee(), appControllerView.getDeskBar()));
                    popupFusion.showPresenter(null);
                    // Gestion de la fin de la fusion de deux personnes
                    popupFusion.addEventHandlerToLocalBus(FinFusionPersonnesEvent.TYPE, new FinFusionPersonnesEventHandler() {
                        @Override
                        public void onFinFusionPersonnes(FinFusionPersonnesEvent event) {
                            // Fermeture de la fiche de la personne source si ouverte
                            if (event.getIdPersonneSource() != null) {
                                appControllerView.fermerOnglet(event.getIdPersonneSource().toString());
                            }
                            if (event.getIdPersonneCible() != null) {
                                // Si la fiche de la personne cible est ouverte
                                if (appControllerView.isOngletContainerExistant(new OngletModel(event.getIdPersonneCible().toString()))) {
                                    // Réouverture de la fiche de la personne fusionnée à jour
                                    personnePhysiqueRpcService.rechercherPersonneSimpleParIdentifiant(event.getIdPersonneCible(),
                                        new AsyncCallback<PersonneSimpleModel>() {

                                            @Override
                                            public void onFailure(Throwable caught) {
                                                ErrorPopup.afficher(new ErrorPopupConfiguration(appControllerConstants
                                                        .messageErreurChargementPersonnePhysique()));
                                            }

                                            @Override
                                            public void onSuccess(PersonneSimpleModel result) {
                                                fireEventGlobalBus(new OpenPersonEvent(result.getId(), result.getNumeroClient(),
                                                    result.getNom() != null ? result.getNom() : "", result.getPrenom() != null ? result.getPrenom() : "",
                                                    result.getNature() != null ? result.getNature().getIdentifiant() : null));
                                            }
                                        });
                                }
                            }
                        }
                    });
                    popupFusion.addEventHandlerToLocalBus(RafraichirRecherchePersonneEvent.TYPE, new RafraichirRecherchePersonneEventHandler() {
                        @Override
                        public void rafraichirRecherchePersonne(RafraichirRecherchePersonneEvent event) {
                            if (personnePhysiqueMoteurRecherchePresenter != null) {
                                personnePhysiqueMoteurRecherchePresenter.rechercherPersonnesPhysique();
                            }
                        }
                    });
                }
                else {
                    popupFusion.rafraichir(event.getNom(), event.getPrenom(), event.getDateNaissance(), event.getIdPersonneSelectionnee());
                }
            }
        });

        addEventHandlerToGlobalBus(OpenPersonEvent.TYPE, new OpenPersonEventHandler() {
            @Override
            public void openPerson(final OpenPersonEvent event) {

                // CONTROLE ONGLET DEJA OUVERT ON LE FERME PROPREMENT
                if (ongletPresenterDynamique.containsKey(event.getId().toString())) {
                    appControllerView.fermerOnglet(event.getId().toString());
                    ongletPresenterDynamique.get(event.getId().toString()).detachPresenter();
                    ongletPresenterDynamique.remove(event.getId().toString());
                }

                // gestion du nombre de caractères du nom prénom
                final String nom = event.getNom();
                String nomLimite = nom;
                if (nom != null && nom.length() > appControllerConstants.nbMaxCaracteresNom()) { // limite si superieur a la constante
                    nomLimite = nomLimite.substring(0, appControllerConstants.nbMaxCaracteresNom()) + "...";
                }
                final String prenom = event.getPrenom();
                String prenomLimite = prenom;
                if (prenom != null && prenom.length() > appControllerConstants.nbMaxCaracteresPrenom()) { // limite si superieur a la constante
                    prenomLimite = prenomLimite.substring(0, appControllerConstants.nbMaxCaracteresPrenom()) + "...";
                }
                final OngletModel onglet = new OngletModel(event.getId().toString(), nomLimite + SAUT_LIGNE + prenomLimite);

                if (controlerNbOngletsOuverts(onglet)) {
                    final AsyncCallback<PersonnePermissionOngletModel> asyncCallback = new AsyncCallback<PersonnePermissionOngletModel>() {
                        @Override
                        public void onSuccess(PersonnePermissionOngletModel result) {
                            final GestionPersonnePhysiquePresenter presenter =
                                new GestionPersonnePhysiquePresenter(dimensionServiceRpcService, personnePhysiqueRpcService, personneRpcService,
                                    personneMoraleRpcService, actionRpcService, opportuniteRpcService, constantesRpcService, eventBus,
                                    new GestionPersonnePhysiqueViewImpl(result, constantes.isHasRoleAdmin()), event.getId(), event.getNum(), event
                                            .getIdNature(), appControllerConstants, constantes, event.getIdAction(), event.getIdOpportunite(), event
                                            .getFiltreidOpportunite(), event.getFiltreEidOpportunite(), ressourceConnectee, hasTelephonie,
                                    isConnecteTelephonie, appControllerView.getDeskBar(), aideService, utilRpcService, documentsService, campagneRpcService);
                            // LES ONGLETS DYNAMQUE SONT GERE HORS ARCHITECTURE CHILD PRESENTER
                            ongletPresenterDynamique.put(event.getId().toString(), presenter);
                            presenter.showPresenter(appControllerView.getOngletContainer(onglet, appControllerConstants.libelleTabActions()));
                            presenter.addEventHandlerToLocalBus(AppellerPersonneEvent.TYPE, new AppellerPersonneEventHandler() {
                                @Override
                                public void appellerPersonne(AppellerPersonneEvent event) {
                                    // Propagation
                                    fireEventLocalBus(event);
                                }
                            });
                            presenter.addEventHandlerToLocalBus(UpdateTabEvent.TYPE, new UpdateTabEventHandler() {
                                @Override
                                public void updateTab(UpdateTabEvent event) {
                                    appControllerView.updateOnglet(event.getOnglet());
                                }
                            });
                            presenter.addEventHandlerToLocalBus(FinSuppressionPersonnesEvent.TYPE, new FinSuppressionPersonnesEventHandler() {
					            @Override
					            public void fermerOngletPersonnesSupprimes(FinSuppressionPersonnesEvent event) {
					            	// fermeture des onglets des personnes supprimées si ouverts
					            	for (Long idPersonneSupprimee : event.getListeIdPersonne()) {
						                appControllerView.fermerOnglet(idPersonneSupprimee.toString());
						                // SUPPRIME TOUTE REFERENCE AU PRESENTER DE L'ONGLET DYNAMIQUE
						                if (ongletPresenterDynamique.get(idPersonneSupprimee.toString()) != null) {
							                ongletPresenterDynamique.get(idPersonneSupprimee.toString()).detachPresenter();
							                ongletPresenterDynamique.remove(idPersonneSupprimee.toString());
						                }
					            	}
					            }
					        });
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            LoadingPopup.stopAll();
                            ErrorPopup.afficher(new ErrorPopupConfiguration(appControllerConstants.messageErreurCodeSplitting()));
                        }
                    };
                    personnePhysiqueRpcService.chargerPermissionOngletPersonne(event.getId(), asyncCallback);
                }
            }
        });

        // GESTON DE LA FERMETURE DE L'ONGLET
        appControllerView.getBusLocal().addHandler(CloseTabEvent.TYPE, new CloseTabEventHandler() {
            @Override
            public void onCloseTab(final CloseTabEvent event) {
                appControllerView.fermerOnglet(event.getOnglet().toString());
                // SUPPRIME TOUTE REFERENCE AU PRESENTER DE L'ONGLET DYNAMIQUE
                ongletPresenterDynamique.get(event.getOnglet()).detachPresenter();
                ongletPresenterDynamique.remove(event.getOnglet());
            }
        });

        // GESTON DE LA SELECTION D'UN ONGLET (STATIQUE UNIQUEMENT)
        appControllerView.getOngletSelectionHandler().addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> event) {
                appControllerView.initContextMenu(event.getSelectedItem());
                final String token = appControllerView.getOngletContainerName(event.getSelectedItem());

                if ((token.equals(appControllerConstants.libelleTabPersonnes())) && personnePhysiqueMoteurRecherchePresenter == null) {

                    personnePhysiqueMoteurRecherchePresenter =
                        addChildPresenter(new PersonnePhysiqueMoteurRecherchePresenter(eventBus, personnePhysiqueRpcService, dimensionServiceRpcService,
                            utilRpcService, new PersonnePhysiqueMoteurRechercheViewImpl(constantes, appControllerConstants), appControllerConstants,
                            constantes, appControllerView.getDeskBar(), aideService));
                    personnePhysiqueMoteurRecherchePresenter.showPresenter(appControllerView.getOngletContainer(new OngletModel(appControllerConstants
                            .libelleTabPersonnes())));
                }
                else if (token.equals(appControllerConstants.libelleTabSocietes()) && personneMoraleMoteurRecherchePresenter == null) {
                    personneMoraleMoteurRecherchePresenter =
                        addChildPresenter(new PersonneMoraleMoteurRecherchePresenter(eventBus, personneMoraleRpcService, dimensionServiceRpcService,
                            utilRpcService, new PersonneMoraleMoteurRechercheViewImpl(appControllerConstants, constantes.isHasRoleAdmin()),
                            appControllerConstants, constantes, aideService));
                    personneMoraleMoteurRecherchePresenter.showPresenter(appControllerView.getOngletContainer(new OngletModel(appControllerConstants
                            .libelleTabSocietes())));
                }
                else if (token.equals(appControllerConstants.libelleTabCampagnes()) && campagneMoteurRecherchePresenter == null) {
                    campagneMoteurRecherchePresenter =
                        addChildPresenter(new CampagneMoteurRecherchePresenter(eventBus, campagneRpcService, dimensionServiceRpcService, utilRpcService,
                            constantes, new CampagneMoteurRechercheViewImpl(appControllerConstants, constantes.isHasRoleAdmin()), appControllerConstants,
                            aideService));
                    campagneMoteurRecherchePresenter.showPresenter(appControllerView.getOngletContainer(new OngletModel(appControllerConstants
                            .libelleTabCampagnes())));

                }
                else if (token.equals(appControllerConstants.libelleTabActions())) {
                    if (actionMoteurRecherchePresenter == null) {
                        actionMoteurRecherchePresenter =
                            addChildPresenter(new ActionMoteurRecherchePresenter(eventBus, actionRpcService, dimensionServiceRpcService, campagneRpcService,
                                personneRpcService, utilRpcService, new ActionMoteurRechercheViewImpl(constantes, appControllerConstants),
                                appControllerConstants, ressourceConnectee, constantes, ressourceConnectee.getIdentifiant(), aideService));
                        actionMoteurRecherchePresenter.showPresenter(appControllerView.getOngletContainer(new OngletModel(appControllerConstants
                                .libelleTabActions())));
                    }
                }
                else if (token.equals(appControllerConstants.libelleTabAgenda())) {
                    if (agendaPresenter == null) {
                        agendaPresenter =
                            addChildPresenter(new AgendaPresenter(eventBus, agendaRpcService, personneRpcService, new AgendaViewImpl(), constantes,
                                ressourceConnectee));
                        agendaPresenter.showPresenter(appControllerView.getOngletContainer(new OngletModel(appControllerConstants.libelleTabAgenda())));
                    } else {
                        agendaPresenter.refresh();
                    }
                }
                else if (token.equals(appControllerConstants.libelleTabRessources()) && ressourceMoteurRecherchePresenter == null) {
                    ressourceMoteurRecherchePresenter =
                        addChildPresenter(new RessourceMoteurRecherchePresenter(eventBus, ressourceRpcService, dimensionServiceRpcService, utilRpcService,
                            new RessourceMoteurRechercheViewImpl(appControllerConstants, constantes.isHasRoleAdmin()), appControllerConstants, constantes,
                            aideService));
                    ressourceMoteurRecherchePresenter.showPresenter(appControllerView.getOngletContainer(new OngletModel(appControllerConstants
                            .libelleTabRessources())));
                }
            }
        });
        addEventHandlerToGlobalBus(OpenPersonneMoraleEvent.TYPE, new OpenPersonneMoraleEventHandler() {
            @Override
            public void openPersonMorale(final OpenPersonneMoraleEvent event) {
                final OngletModel onglet = new OngletModel(event.getId().toString(), event.getRaisonSociale());
                if (controlerNbOngletsOuverts(onglet)) {

                    // CONTROLE ONGLET DEJA OUVERT ON LE FERME PROPREMENT
                    if (ongletPresenterDynamique.get(event.getId().toString()) != null) {
                        appControllerView.fermerOnglet(event.getId().toString());
                        ongletPresenterDynamique.get(event.getId().toString()).detachPresenter();
                        ongletPresenterDynamique.remove(event.getId().toString());
                    }

                    final GestionPersonneMoralePresenter presenter =
                        new GestionPersonneMoralePresenter(dimensionServiceRpcService, personnePhysiqueRpcService, personneMoraleRpcService,
                            personneRpcService, actionRpcService, constantesRpcService, eventBus,
                            new GestionPersonneMoraleViewImpl(constantes.isHasRoleAdmin()), event.getId(), event.getIdNaturePersonne(), appControllerConstants,
                            constantes, event.getIdAction(), appControllerView.getDeskBar(), ressourceConnectee, aideService);
                    presenter.showPresenter(appControllerView.getOngletContainer(onglet, appControllerConstants.libelleTabSocietes()));
                    // LES ONGLETS DYNAMQUE SONT GERE HORS ARCHITECTURE CHILD PRESENTER
                    ongletPresenterDynamique.put(event.getId().toString(), presenter);
                }
            }
        });
        addEventHandlerToGlobalBus(OpenRessourceEvent.TYPE, new OpenRessourceEventHandler() {
            @Override
            public void openRessource(final OpenRessourceEvent openRessourceEvent) {

                // CONTROLE ONGLET DEJA OUVERT ON LE FERME PROPREMENT
                if (ongletPresenterDynamique.get(openRessourceEvent.getId().toString()) != null) {
                    appControllerView.fermerOnglet(openRessourceEvent.getId().toString());
                    ongletPresenterDynamique.get(openRessourceEvent.getId().toString()).detachPresenter();
                    ongletPresenterDynamique.remove(openRessourceEvent.getId().toString());
                }

                final OngletModel onglet =
                    new OngletModel(openRessourceEvent.getId().toString(), openRessourceEvent.getNom() + SAUT_LIGNE + openRessourceEvent.getPrenom());
                if (controlerNbOngletsOuverts(onglet)) {

                    final Long uidRessource = openRessourceEvent.getId();
                    final GestionRessourceView gestionRessourceView = new GestionRessourceViewImpl(constantes.isHasRoleAdmin());

                    final GestionRessourcePresenter presenter =
                        new GestionRessourcePresenter(eventBus, ressourceRpcService, gestionRessourceView, uidRessource, aideService);
                    presenter.showPresenter(appControllerView.getOngletContainer(onglet, appControllerConstants.libelleTabRessources()));

                    // LES ONGLETS DYNAMQUE SONT GERE HORS ARCHITECTURE CHILD PRESENTER
                    ongletPresenterDynamique.put(openRessourceEvent.getId().toString(), presenter);
                }
            }
        });
        addEventHandlerToGlobalBus(OpenCampagneEvent.TYPE, new OpenCampagneEventHandler() {
            @Override
            public void openCampagne(final OpenCampagneEvent openCampagneEvent) {

                // CONTROLE ONGLET DEJA OUVERT ON LE FERME PROPREMENT
                if (ongletPresenterDynamique.get(openCampagneEvent.getId().toString()) != null) {
                    appControllerView.fermerOnglet(openCampagneEvent.getId().toString());
                    ongletPresenterDynamique.get(openCampagneEvent.getId().toString()).detachPresenter();
                    ongletPresenterDynamique.remove(openCampagneEvent.getId().toString());
                }

                final CampagneGestionPresenter presenter =
                    new CampagneGestionPresenter(eventBus, dimensionServiceRpcService, campagneRpcService, new CampagneGestionViewImpl(constantes
                            .isHasRoleAdmin()), openCampagneEvent.getId(), appControllerConstants, constantes, aideService);
                presenter.showPresenter(appControllerView.getOngletContainer(new OngletModel(openCampagneEvent.getId().toString(), openCampagneEvent
                        .getLibelle()), appControllerConstants.libelleTabCampagnes()));
                presenter.addEventHandlerToLocalBus(UpdateTabEvent.TYPE, new UpdateTabEventHandler() {
                    @Override
                    public void updateTab(UpdateTabEvent event) {
                        appControllerView.updateOnglet(event.getOnglet());
                    }
                });
                // LES ONGLETS DYNAMQUE SONT GERE HORS ARCHITECTURE CHILD PRESENTER
                ongletPresenterDynamique.put(openCampagneEvent.getId().toString(), presenter);
            }
        });
        addEventHandlerToGlobalBus(AjoutActionEvent.TYPE, new AjoutActionEventHandler() {
            @Override
            public void ajouterAction(AjoutActionEvent event) {
                if (actionCreationPresenter == null) {
                    actionCreationPresenter =
                        addChildPresenter(new ActionCreationPresenter(eventBus, new ActionCreationViewImpl(constantes.isHasRoleAdmin()),
                            dimensionServiceRpcService, actionRpcService, personneRpcService, campagneRpcService, constantesRpcService, aideService, event
                                    .getIdPersonne(), event.getIdActionSource(), event.getFiltreidOpportunite(), event.getFiltreEidOpportunite(),
                            ressourceConnectee, event.getActionSourceAEnregistrer(), event.getDemanderFermerSource(), constantes, appControllerView
                                    .getDeskBar()));
                    actionCreationPresenter.showPresenter(null);
                }
                else {
                    actionCreationPresenter.nouvelleAction(event.getIdPersonne(), event.getIdActionSource(), event.getFiltreidOpportunite(), event
                            .getFiltreEidOpportunite(), event.getActionSourceAEnregistrer(), event.getDemanderFermerSource());
                }
            }
        });
//        addEventHandlerToGlobalBus(AppellerPersonneEvent.TYPE, new AppellerPersonneEventHandler() {
//            @Override
//            public void appellerPersonne(AppellerPersonneEvent event) {
//                zoneTelephoniePresenter.telephonerPersonne(event.getNumeroTelephone());
//            }
//        });
    }

    @Override
    public View asView() {
        return appControllerView;
    }

    @Override
    public void onShow(HasWidgets container) {
        if ("".equals(History.getToken())) {
            // INITIALISATION
            container.add(appControllerView.asWidget());

            if (actionsPresenter == null) {
                this.actionContexteView = new ActionsViewImpl();
                actionsPresenter = addChildPresenter(new ActionsPresenter(eventBus, actionContexteView, appControllerConstants, constantes));
                actionsPresenter.showPresenter(appControllerView.getActionsContainer());
                actionsPresenter.addEventHandlerToLocalBus(AjoutPersonneEvent.TYPE, new AjoutPersonneEventHandler() {
                    @Override
                    public void ajouterPersonne(AjoutPersonneEvent event) {
                        if (personnePhysiqueCreationPresenter == null) {
                            personnePhysiqueCreationPresenter =
                                addChildPresenter(new PersonnePhysiqueCreationPresenter(eventBus, personnePhysiqueRpcService, personneRpcService,
                                    dimensionServiceRpcService, new PersonnePhysiqueCreationViewImpl(constantes.isHasRoleAdmin()), appControllerConstants,
                                    constantes, appControllerView.getDeskBar(), aideService, ressourceConnectee));
                            personnePhysiqueCreationPresenter.showPresenter(null);
                        }
                        else {
                            personnePhysiqueCreationPresenter.nettoyer();
                        }
                    }
                });
                actionsPresenter.addEventHandlerToLocalBus(AjoutCampagneEvent.TYPE, new AjoutCampagneEventHandler() {
                    @Override
                    public void ajouterCampagne(AjoutCampagneEvent event) {
                        if (campagneCreationPresenter == null) {
                            campagneCreationPresenter =
                                addChildPresenter(new CampagneCreationPresenter(eventBus, campagneRpcService, dimensionServiceRpcService,
                                    new CampagneCreationViewImpl(constantes.isHasRoleAdmin()), appControllerConstants, constantes, appControllerView
                                            .getDeskBar(), aideService));
                            campagneCreationPresenter.showPresenter(null);
                            campagneCreationPresenter.addEventHandlerToLocalBus(CreateCampagneEvent.TYPE, new CreateCampagneEventHandler() {
                                @Override
                                public void createCampagne(final CreateCampagneEvent event) {
                                    // Ouverture de la campagne
                                    fireEventGlobalBus(new OpenCampagneEvent(event.getId(), event.getLibelle()));
                                }
                            });
                        }
                        else {
                            campagneCreationPresenter.afficher();
                        }
                    }
                });
            }

            // PLUGIN RECHERCE DE TRANSACTION
            if (boutonRechercherTransactionPresenter == null) {
                boutonRechercherTransactionPresenter =
                    addChildPresenter(new BoutonRechercherTransactionPresenter(eventBus, new BoutonRechercherTransactionViewImpl(),
                        tarificateurServiceGwtAsync));
                boutonRechercherTransactionPresenter.showPresenter(actionContexteView.getNewContextMenuSlot());
                boutonRechercherTransactionPresenter.addEventHandlerToLocalBus(OpenOpportuniteEvent.TYPE, new OpenOpportuniteEventHandler() {
                    @Override
                    public void openOpportunite(OpenOpportuniteEvent event) {
                        final Long idOpportunite = event.getEidOpportunite();
                        personnePhysiqueRpcService.rechercherPersonneSimpleParIdentifiant(event.getEidPersonnePrincipale(),
                            new AsyncCallback<PersonneSimpleModel>() {
                                @Override
                                public void onFailure(Throwable caught) {
                                    ErrorPopup.afficher(new ErrorPopupConfiguration(appControllerConstants.messageErreurChargementPersonnePhysique()));
                                }

                                @Override
                                public void onSuccess(PersonneSimpleModel result) {
                                    fireEventGlobalBus(new OpenPersonEvent(result.getId(), result.getNumeroClient(), result.getNom() != null ? result.getNom()
                                        : "", result.getPrenom() != null ? result.getPrenom() : "", result.getNature() != null ? result.getNature()
                                            .getIdentifiant() : null, null, idOpportunite));
                                }
                            });
                    }
                });
            }

            // INITIALISATION TELEPHONIE
//            final AsyncCallback<Boolean> asyncCallback = new AsyncCallback<Boolean>() {
//                @Override
//                public void onSuccess(Boolean hasAgentConnecteRoleTelephonie) {
//                    if (hasAgentConnecteRoleTelephonie != null && hasAgentConnecteRoleTelephonie) {
//                        // ON DEMARRE LA TELPHONIE
//                        final Timer timer = new Timer() {
//                            @Override
//                            public void run() {
//                                if (zoneTelephoniePresenter == null) {
//                                    zoneTelephoniePresenter =
//                                        addChildPresenter(new ZoneTelephoniePresenter(eventBus, new ZoneTelephonieViewImpl(MODE.COMPACT)));
//                                    zoneTelephoniePresenter.showPresenter(appControllerView.getTelephonieContainer());
//                                    zoneTelephoniePresenter.addEventHandlerToLocalBus(AppelEntrantEvent.TYPE, new AppelEntrantEventHandler() {
//                                        @Override
//                                        public void nouvelAppelEntrant(AppelEntrantEvent event) {
//                                            recherchePersonneAppel(null, null, event.getNumeroTelephone());
//                                        }
//                                    });
//                                    zoneTelephoniePresenter.addEventHandlerToLocalBus(ConnecteAcpEvent.TYPE, new ConnecteAcpEventHandler() {
//                                        @Override
//                                        public void connecteAcp(ConnecteAcpEvent event) {
//                                            isConnecteTelephonie = true;
//                                            hasTelephonie = true;
//                                        }
//                                    });
//                                    zoneTelephoniePresenter.addEventHandlerToLocalBus(DeconnecteAcpEvent.TYPE, new DeconnecteAcpEventHandler() {
//                                        @Override
//                                        public void deconnecteAcp(DeconnecteAcpEvent event) {
//                                            isConnecteTelephonie = false;
//                                            // D'autres seront peut être interessés :
//                                            fireEventGlobalBus(event);
//                                        }
//                                    });
//                                    zoneTelephoniePresenter.addEventHandlerToLocalBus(AppelPossibleEvent.TYPE, new AppelPossibleEventHandler() {
//                                        @Override
//                                        public void appellerPossible(AppelPossibleEvent event) {
//                                            // D'autres seront peut être interessés :
//                                            fireEventGlobalBus(event);
//                                        }
//                                    });
//                                    zoneTelephoniePresenter.addEventHandlerToLocalBus(RechercherPersonneCallBackEvent.TYPE,
//                                        new RechercherPersonneCallBackEventHandler() {
//                                            @Override
//                                            public void lancerRecherchePersonneCallBack(RechercherPersonneCallBackEvent event) {
//                                                recherchePersonneAppel(event.getNumAdherent(), event.getNom(), event.getNumTel());
//                                            }
//                                        });
//                                    zoneTelephoniePresenter.addEventHandlerToGlobalBus(DesactiverAppelSquareEvent.TYPE,
//                                        new DesactiverAppelSquareEventHandler() {
//                                            @Override
//                                            public void desactiverAppel(DesactiverAppelSquareEvent event) {
//                                                // D'autres seront peut être interessés :
//                                                fireEventGlobalBus(event);
//                                            }
//                                        });
//                                }
//                            }
//                        };
//                        timer.schedule(AppControllerConstants.DELAI_TIMER_TELEPHONIE);
//                    }
//                }
//
//                @Override
//                public void onFailure(Throwable caught) {
//                    LoadingPopup.stopAll();
//                    ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
//                }
//            };
//            final UtilisateurServiceRpcAsync utilisateurTelephonie = GWT.create(UtilisateurServiceRpc.class);
//            utilisateurTelephonie.hasAgentConnecteRoleTelephonieCTI(asyncCallback);

            if (Window.Location.getParameter(AppControllerConstants.EVENT) != null) {
                gestionLienDirect();
            }
            else {
                appControllerView.selectionOnglet(appControllerConstants.viewDefaultToken());
            }
        }
        else {
            History.fireCurrentHistoryState();
        }
    }

    /**
     * Gestion des liens dirececte.
     * @return
     */
    private void gestionLienDirect() {

        final String paramEvent = Window.Location.getParameter(AppControllerConstants.EVENT);
        if (paramEvent != null && !paramEvent.isEmpty()) {
            if (paramEvent.equals(OpenPersonEvent.EVENT_NAME)) {
                final Long id = Long.valueOf(Window.Location.getParameter(OpenPersonEvent.PARAM_ID));
                personnePhysiqueRpcService.rechercherPersonneSimpleParIdentifiant(id, new AsyncCallback<PersonneSimpleModel>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        History.back();
                    }

                    @Override
                    public void onSuccess(PersonneSimpleModel personne) {
                        final OpenPersonEvent openPersonEvent =
                            new OpenPersonEvent(personne.getId(), personne.getNumeroClient(), personne.getNom() != null ? personne.getNom() : "", personne
                                    .getPrenom() != null ? personne.getPrenom() : "", (personne.getNature() != null) ? personne.getNature().getIdentifiant()
                                : null);
                        final String idAction = Window.Location.getParameter(OpenPersonEvent.PARAM_ID_ACTION);
                        if (idAction != null && !"".equals(idAction.trim())) {
                            openPersonEvent.setIdAction(Long.valueOf(idAction));
                        }
                        final String idOpportunite = Window.Location.getParameter(OpenPersonEvent.PARAM_ID_OPPORTUNITE);
                        if (idOpportunite != null && !"".equals(idOpportunite)) {
                            openPersonEvent.setIdOpportunite(Long.valueOf(idOpportunite));
                        }
                        final String filtreidOpportunite = Window.Location.getParameter(OpenPersonEvent.PARAM_FILTRE_ID_OPPORTUNITE);
                        if (filtreidOpportunite != null && !"".equals(filtreidOpportunite.trim())) {
                            openPersonEvent.setFiltreidOpportunite(Long.valueOf(idOpportunite));
                        }
                        openPersonEvent.setFiltreEidOpportunite(Window.Location.getParameter(OpenPersonEvent.PARAM_FILTRE_EID_OPPORTUNITE));
                        fireEventGlobalBus(openPersonEvent);
                    }
                });

            }
            else if (paramEvent.equals(OpenPersonneMoraleEvent.EVENT_NAME)) {
                final Long id = Long.valueOf(Window.Location.getParameter(OpenPersonneMoraleEvent.PARAM_ID));
                personneMoraleRpcService.recherchepersonneMoraleSimpleParId(id, new AsyncCallback<PersonneMoraleSimpleModel>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        History.back();
                    }

                    @Override
                    public void onSuccess(PersonneMoraleSimpleModel personneMorale) {
                        final String valueIdAction = Window.Location.getParameter(OpenPersonneMoraleEvent.PARAM_ID_ACTION);
                        Long idAction = null;
                        if (valueIdAction != null && !"".equals(valueIdAction.trim())) {
                            idAction = Long.valueOf(valueIdAction);
                        }
                        final OpenPersonneMoraleEvent openPersonneMoraleEvent =
                            new OpenPersonneMoraleEvent(id, personneMorale.getNature() != null ? personneMorale.getNature().getIdentifiant() : null,
                                personneMorale.getRaisonSociale(), idAction);
                        fireEventGlobalBus(openPersonneMoraleEvent);
                    }
                });
            }
            else if (paramEvent.equals(OpenRessourceEvent.EVENT_NAME)) {
                final Long id = Long.valueOf(Window.Location.getParameter(OpenRessourceEvent.PARAM_ID));
                ressourceRpcService.rechercherRessourceParId(id, new AsyncCallback<RessourceModel>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        History.back();
                    }

                    @Override
                    public void onSuccess(RessourceModel ressource) {
                        final OpenRessourceEvent openRessourceEvent = new OpenRessourceEvent(ressource.getId(), ressource.getNom(), ressource.getPrenom());
                        fireEventGlobalBus(openRessourceEvent);
                    }
                });
            }
            else if (paramEvent.equals(OpenCampagneEvent.EVENT_NAME)) {
                final Long id = Long.valueOf(Window.Location.getParameter(OpenCampagneEvent.PARAM_ID));
                campagneRpcService.rechercherCampagnesParId(id, new AsyncCallback<CampagneModel>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        History.back();
                    }

                    @Override
                    public void onSuccess(CampagneModel campagne) {
                        final OpenCampagneEvent openCampagneEvent = new OpenCampagneEvent(campagne.getId(), campagne.getLibelle());
                        fireEventGlobalBus(openCampagneEvent);
                    }
                });
            }
        }
    }

    /**
     * Verifie si on peut ouvrir un onglet suivant le nb max autorisé.
     */
    private boolean controlerNbOngletsOuverts(final OngletModel onglet) {
        final boolean isOngletContainerExistant = appControllerView.isOngletContainerExistant(onglet);
        if (!isOngletContainerExistant && appControllerView.getNbreDynamicsTabsOpened() == AppControllerConstants.NB_MAX_ONGLETS_OUVERTS) {
            appControllerView.onRpcServiceFailure(new ErrorPopupConfiguration(appControllerMessages
                    .messageTropOngletsOuverts(AppControllerConstants.NB_MAX_ONGLETS_OUVERTS)));
            return false;
        }
        return true;
    }

    /**
     * Recherche la personne appelant.
     * @param numAdherent le numéro de l'adhérent.
     * @param nom le nom de la personne de l'appel.
     * @param numeroTelephone le numéro de téléphone de la personne de l'appel.
     */
    private void recherchePersonneAppel(String numAdherent, String nom, String numeroTelephone) {
        // Nettoyage du numéro de téléphone
        String numTel = numeroTelephone;
        if (numTel != null && !"".equals(numTel)) {
            if (numTel.startsWith("00")) {
                numTel = numeroTelephone.replaceFirst("00", "0");
            }
        }

        if ((numAdherent == null || "".equals(numAdherent)) && (nom == null || "".equals(nom)) && (numTel == null || "".equals(numTel))) {
            // Si aucun critère de recherche, alors on n'affiche le moteur de recherche
            // Si aucun critère de recherche, alors on n'affiche le moteur de recherche
            appControllerView.selectionOnglet(appControllerConstants.libelleTabPersonnes());
        }
        else {
            // Création du critère de recherche
            final PersonneCriteresRechercheModel criterias = new PersonneCriteresRechercheModel();
            criterias.setNumeroClient(numAdherent);
            criterias.setNom(nom);
            criterias.setTelephone(numTel);
            criterias.setRechercheStricte(true);
            final RemotePagingCriteriasGwt<PersonneCriteresRechercheModel> critere =
                new RemotePagingCriteriasGwt<PersonneCriteresRechercheModel>(criterias, 0, 1);

            // Lancement du calcul du nombre de résultat de la recherche
            final AsyncCallback<RemotePagingResultsGwt<PersonneSimpleModel>> asyncallback = new AsyncCallback<RemotePagingResultsGwt<PersonneSimpleModel>>() {
                @Override
                public void onFailure(Throwable caught) {
                    ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
                }

                @Override
                public void onSuccess(RemotePagingResultsGwt<PersonneSimpleModel> result) {
                    if (result.getTotalResults() != 1) {
                        // Si aucun critère de recherche, alors on n'affiche le moteur de recherche
                        appControllerView.selectionOnglet(appControllerConstants.libelleTabPersonnes());
                        personnePhysiqueMoteurRecherchePresenter.lancerRechercheAppelEntrant(critere.getCriterias().getNumeroClient(), critere.getCriterias()
                                .getNom(), critere.getCriterias().getTelephone());
                    }
                    else {
                        final PersonneSimpleModel personneAppelant = result.getListResults().get(0);
                        // Ouverture de la fiche de la personne
                        final AsyncCallback<PersonnePermissionOngletModel> asyncCallback = new AsyncCallback<PersonnePermissionOngletModel>() {
                            @Override
                            public void onSuccess(PersonnePermissionOngletModel result) {
                                // Ouverture de la fiche de la personne
                                fireEventLocalBus(new OpenPersonEvent(personneAppelant.getId(), personneAppelant.getNumeroClient(),
                                    personneAppelant.getNom() != null ? personneAppelant.getNom() : "", personneAppelant.getPrenom() != null ? personneAppelant
                                            .getPrenom() : "", personneAppelant.getNature() != null ? personneAppelant.getNature().getIdentifiant() : null));
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                ErrorPopup.afficher(new ErrorPopupConfiguration(appControllerConstants.messageErreurCodeSplitting()));
                            }
                        };
                        personnePhysiqueRpcService.chargerPermissionOngletPersonne(personneAppelant.getId(), asyncCallback);
                    }
                }
            };
            personnePhysiqueRpcService.rechercherPersonneFullTextParCriteres(critere, asyncallback);
        }
    }

    /**
     * Interface pour la vue PersonnePhysiqueMoteurRechercheView.
     */
    public interface AppControllerView extends View {
        /**
         * Verifie si l'onglet est déjà affiché.
         * @param onglet le nom de l'onglet.
         * @return true ou false
         */
        boolean isOngletContainerExistant(final OngletModel onglet);

        /**
         * Permet de recuperer le Widget associé à un onglet.
         * @param onglet le nom de l'onglet.
         * @param idOngletParent le nom de l'onglet parent.
         * @return le widget dans l'onglet.
         */
        HasWidgets getOngletContainer(final OngletModel onglet, final String idOngletParent);

        /**
         * Permet de recuperer le Widget associé à un onglet.
         * @param onglet l'onglet
         * @return le widget dans l'onglet.
         */
        HasWidgets getOngletContainer(final OngletModel onglet);

        /**
         * Permet de recuperer le Widget associé à un onglet décoré d'un ContenuOnglet.
         * @param onglet l'onglet
         * @return le widget dans l'onglet.
         */
        HasWidgets getOngletContenuOnglet(final OngletModel onglet);

        /**
         * Permet de demander la fermeture d'un onglet.
         * @param idOnglet l'id de l'onglet à fermer.
         */
        void fermerOnglet(String idOnglet);

        /**
         * Permet de mettre a jour un onglet.
         * @param onglet onglet
         */
        void updateOnglet(OngletModel onglet);

        /**
         * Affichage des Actions.
         * @return actionsContainer
         */
        HasWidgets getActionsContainer();

        /**
         * Affichage des Actions.
         * @return actionsContainer
         */
        HasWidgets getActionsContextContainer();

        /**
         * Methode appelé lorsque un servie Rpc ne s'est pas deroulé correctement.
         * @param config infos sur l'erreur.
         */
        void onRpcServiceFailure(final ErrorPopupConfiguration config);

        /**
         * Permet de recuperer le Handler de selection du tab panel.
         * @return le handler de selection.
         */
        HasSelectionHandlers<Integer> getOngletSelectionHandler();

        /**
         * Permet de recuperer le Widget associé à un onglet.
         * @param index du container.
         * @return le nom de l'onglet.
         */
        String getOngletContainerName(final Integer index);

        /**
         * Permet de re-initialiser le contexte.
         * @param onglet .
         */
        void initContextMenu(final Integer onglet);

        /**
         * Affichage de la téléphonie.
         * @return telephonieContainer.
         */
        HasWidgets getTelephonieContainer();

        /**
         * Recupere le nombre d'onglets dynamiques déjà ouverts.
         * @return le nb d'onglets
         */
        int getNbreDynamicsTabsOpened();

        /**
         * Recupere la deskBar.
         * @return la deskBar
         */
        DeskBar getDeskBar();

        /**
         * .
         * @param tabName .
         */
        void selectionOnglet(String tabName);

        /**
         * Un Bus local pour ce vue special.
         * @return .
         */
        HandlerManager getBusLocal();
    }

}

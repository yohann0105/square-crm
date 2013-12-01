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
package com.square.client.gwt.client.presenter.personne.morale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.HasBeforeSelectionHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.controller.AppControllerViewImpl.ContainerTabPanel;
import com.square.client.gwt.client.event.RafraichirPersonneEvent;
import com.square.client.gwt.client.event.RafraichirPersonneEventHandler;
import com.square.client.gwt.client.event.UpdateTabNameEvent;
import com.square.client.gwt.client.event.UpdateTabNameEventHandler;
import com.square.client.gwt.client.event.actions.AjoutActionEvent;
import com.square.client.gwt.client.model.CompteursModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRessourceModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.PersonneMoraleModel;
import com.square.client.gwt.client.model.PersonneMoraleSimpleModel;
import com.square.client.gwt.client.model.CompteursModel.Compteur;
import com.square.client.gwt.client.presenter.personne.PersonneActionsPresenter;
import com.square.client.gwt.client.presenter.personne.PersonneCoordonneesPresenter;
import com.square.client.gwt.client.presenter.personne.PersonneRelationsModePresenter;
import com.square.client.gwt.client.service.ActionServiceGwtAsync;
import com.square.client.gwt.client.service.ConstantesServiceGwtAsync;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneMoraleServiceGwtAsync;
import com.square.client.gwt.client.service.PersonnePhysiqueServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.view.personne.action.PersonneActionsViewImpl;
import com.square.client.gwt.client.view.personne.coordonnees.PersonneCoordonneesViewImpl;
import com.square.client.gwt.client.view.personne.morale.gestion.GestionPersonneMoraleViewImplConstants;
import com.square.client.gwt.client.view.personne.relations.PersonneRelationsModeViewImpl;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composant.contrat.personne.morale.square.client.event.ContratsPersonneMoraleChargesEvent;
import com.square.composant.contrat.personne.morale.square.client.event.ContratsPersonneMoraleChargesEventHandler;
import com.square.composant.contrat.personne.morale.square.client.presenter.ContratsPersonneMoralePresenter;
import com.square.composant.contrat.personne.morale.square.client.view.ContratsPersonneMoraleViewImpl;
import com.square.composant.ged.square.client.presenter.ListeDetailleeDocumentsPresenter;
import com.square.composant.ged.square.client.service.DocumentsServiceGwt;
import com.square.composant.ged.square.client.service.DocumentsServiceGwtAsync;
import com.square.composant.ged.square.client.view.listedetaillee.ListeDetailleeDocumentsViewImpl;
import com.square.composants.graphiques.lib.client.composants.BlocSyntheseDepliant;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEvent;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEventHandler;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;

/**
 * Presenter sur la gestion des personnes morales.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class GestionPersonneMoralePresenter extends Presenter {
    /**
     * Services asynchrones.
     */
    private PersonneMoraleServiceGwtAsync personneMoraleRpcService;

    private DimensionServiceGwtAsync dimensionServiceRpcService;

    private AppControllerConstants appControllerConstants;

    private PersonneServiceGwtAsync personneRpcService;

    private PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService;

    private ActionServiceGwtAsync actionServiceRpc;

    private PersonneActionsPresenter personneActionsPresenter;

    private ConstantesServiceGwtAsync constantesRpcService;

    /**
     * Constantes.
     */
    private ConstantesModel constantes;

    /**
     * Identifiant de la société.
     */
    private Long idPersonneMorale;

    /**
     * Identifiant de la nature de la société.
     */
    private Long idNaturePersonneMorale;

    /**
     * Numéro de client.
     */
    private String numClient;

    /**
     * La ressource connectee.
     */
    private DimensionRessourceModel ressourceConnectee;

    /**
     * Demande d'ouverture d'action initiale.
     */
    private Long idActionOuvrir;

    /**
     * Booleen qui indique si la société est intitialisée ou pas.
     */
    private boolean initPersonneMorale;

    /**
     * La Vue.
     */
    private GestionPersonneMoraleView view;

    private PersonneRelationsModePresenter presenterRelations;

    private DeskBar deskBar;

    /** Service de gestion des aides . */
    private AideServiceGwtAsync aideService;

    private PersonneCoordonneesPresenter personneCoordonneesPresenter;
    private ListeDetailleeDocumentsPresenter listeDetailleeDocumentsPresenter;
    private ContratsPersonneMoralePresenter contratsPersonneMoralePresenter;
    private Map<Compteur, String> mapCompteursOnglets;


    /**
     * Constructeur de .
     * @param dimensionServiceRpcService le service sur les dimensions.
     * @param personnePhysiqueRpcService le service sur les personnes physiques.
     * @param personneMoraleRpcService le service sur les personnes morales.
     * @param personneRpcService le service sur les personnes.
     * @param actionRpcService le service des actions
     * @param constantesRpcService le service des constantes
     * @param eventBus le bus d'evenement.
     * @param view la vue associée au presenter.
     * @param idPersonneMorale l'identifiant de la personne morale.
     * @param idNaturePersonneMorale identifiant de la nature de la personne morale.
     * @param ressourceConnectee la ressource connectée
     * @param appControllerConstants les constantes de l'application.
     * @param constantes les constantes.
     * @param idActionOuvrir demande d'ouverture d'action initiale.
     * @param deskBar deskBar
     * @param aideService service d'aide.
     */
    public GestionPersonneMoralePresenter(DimensionServiceGwtAsync dimensionServiceRpcService, PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService,
        PersonneMoraleServiceGwtAsync personneMoraleRpcService, PersonneServiceGwtAsync personneRpcService, ActionServiceGwtAsync actionRpcService,
        ConstantesServiceGwtAsync constantesRpcService, HandlerManager eventBus, GestionPersonneMoraleView view, final Long idPersonneMorale,
        Long idNaturePersonneMorale, AppControllerConstants appControllerConstants, ConstantesModel constantes, Long idActionOuvrir, DeskBar deskBar,
        DimensionRessourceModel ressourceConnectee, AideServiceGwtAsync aideService) {
        super(eventBus);
        this.dimensionServiceRpcService = dimensionServiceRpcService;
        this.personnePhysiqueRpcService = personnePhysiqueRpcService;
        this.personneMoraleRpcService = personneMoraleRpcService;
        this.personneRpcService = personneRpcService;
        this.view = view;
        this.idPersonneMorale = idPersonneMorale;
        this.idNaturePersonneMorale = idNaturePersonneMorale;
        this.ressourceConnectee = ressourceConnectee;
        this.appControllerConstants = appControllerConstants;
        this.constantes = constantes;
        this.actionServiceRpc = actionRpcService;
        this.constantesRpcService = constantesRpcService;
        this.idActionOuvrir = idActionOuvrir;
        this.deskBar = deskBar;
        this.aideService = aideService;
    }

    @Override
    public View asView() {
        return view;
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

        view.getSbNature().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                dimensionServiceRpcService.rechercherNaturePersonneMoraleParCriteres(criteres, event.getCallBack());
            }
        });
        view.getSbFormeJuridique().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                dimensionServiceRpcService.rechercherFormesJuridiques(criteres, event.getCallBack());
            }
        });
        view.getBlRpersonneMorale().addOpenEventHandler(new OpenBlocSyntheseEventHandler() {
            @Override
            public void onOpen(OpenBlocSyntheseEvent event) {
                if (!initPersonneMorale) {
                    initPersonneMorale();
                }
            }
        });

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

        view.getTabPanelBeforeSelectionHandlers().addBeforeSelectionHandler(new BeforeSelectionHandler<Integer>() {
            @Override
            public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {

                final String nomOnglet = view.getContainerTabInfos(event.getItem());
                // L'event n'est plus levé..
//                eventBus.addHandler(RafraichirRelationEvent.TYPE, new RafraichirRelationEventHandler() {
//                    @Override
//                    public void rafraichirRelation(RafraichirRelationEvent event) {
//                        if (event.getFiltrePasDansGroupements() != null) {
//                            for (Long idGroupement : event.getFiltrePasDansGroupements()) {
//                                if (constantes.getIdGroupementProfessionnel().equals(idGroupement)) {
//                                    viewRelations = new PersonneRelationsModeViewImpl();
//                                    final List<Long> filtrePasDansGroupements = new ArrayList<Long>();
//                                    filtrePasDansGroupements.add(constantes.getIdGroupementFamiliale());
//                                    filtrePasDansGroupements.add(constantes.getIdGroupementProfessionnel());
//                                    if (presenterRelations == null) {
//                                        presenterRelations = new PersonneRelationsModePresenter(eventBus, viewRelations, personneRpcService,
//                                            personnePhysiqueRpcService, personneMoraleRpcService, dimensionServiceRpcService, constantes, null,
//                                            idPersonneMorale, null, idNaturePersonneMorale, filtrePasDansGroupements, 1L,
//                                            AppControllerConstants.MODE_RELATION_EDITION,aideService, deskBar);
//                                    }
//                                    presenterRelations.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabRelations()));
//                                }
//                            }
//                        }
//                    }
//                });
                if (nomOnglet.equals(view.getViewConstants().libelleTabRelations())) {
                    final List<Long> filtrePasDansGroupements = new ArrayList<Long>();
                    filtrePasDansGroupements.add(constantes.getIdGroupementFamiliale());
                    filtrePasDansGroupements.add(constantes.getIdGroupementProfessionnel());
                    if (presenterRelations == null) {
                        presenterRelations = addChildPresenter(new PersonneRelationsModePresenter(eventBus, new PersonneRelationsModeViewImpl(),
                            personneRpcService, personnePhysiqueRpcService, personneMoraleRpcService, dimensionServiceRpcService, constantes, null,
                            idPersonneMorale, null, idNaturePersonneMorale, filtrePasDansGroupements, 1L, AppControllerConstants.MODE_RELATION_EDITION,
                            aideService, deskBar));
                        presenterRelations.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabRelations()));
                    } else {
                        presenterRelations.switchModeEdition(AppControllerConstants.MODE_RELATION_EDITION);
                    }
                } else if (nomOnglet.equals(view.getViewConstants().libelleTabActions())) {
                    if (personneActionsPresenter == null) {
                        personneActionsPresenter =
                            addChildPresenter(new PersonneActionsPresenter(eventBus, actionServiceRpc, dimensionServiceRpcService, constantesRpcService,
                                personneRpcService, new PersonneActionsViewImpl(constantes), idPersonneMorale, constantes, idActionOuvrir, null, null,
                                numClient, ressourceConnectee, aideService));
                        personneActionsPresenter.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabActions()));
                        personneActionsPresenter.addEventHandlerToLocalBus(UpdateTabNameEvent.TYPE, new UpdateTabNameEventHandler() {
                            @Override
                            public void updateTabName(UpdateTabNameEvent event) {
                                    view.updateTabName(event.getNbItems(), event.getTabName());
                            }
                        });
                    } else {
                        personneActionsPresenter.initListeActions(idActionOuvrir, null, null);
                    }
                } else if (nomOnglet.equals(view.getViewConstants().libelleTabCoordonnees()) && personneCoordonneesPresenter == null) {
                    personneCoordonneesPresenter = addChildPresenter(new PersonneCoordonneesPresenter(
                        eventBus, personneRpcService, dimensionServiceRpcService, new PersonneCoordonneesViewImpl(constantes, true, false, false),
                        appControllerConstants, constantes, idPersonneMorale, deskBar, aideService));
                    personneCoordonneesPresenter.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabCoordonnees()));
                    personneCoordonneesPresenter.addEventHandlerToLocalBus(RafraichirPersonneEvent.TYPE, new RafraichirPersonneEventHandler() {
                        @Override
                        public void rafraichirPersonne(RafraichirPersonneEvent event) {
                            if (idPersonneMorale.equals(event.getUidPersonne())) {
                                initPersonneMorale();
                            }
                        }
                    });
                } else if (nomOnglet.equals(view.getViewConstants().libelleTabDocuments())) {
                    if (listeDetailleeDocumentsPresenter == null) {
                        // FIXME : Déplacer l'instanciation du service ailleurs
                        final DocumentsServiceGwtAsync documentService = GWT.create(DocumentsServiceGwt.class);
                        listeDetailleeDocumentsPresenter = addChildPresenter(new ListeDetailleeDocumentsPresenter(eventBus, numClient,
                            new ListeDetailleeDocumentsViewImpl(), documentService));
                        listeDetailleeDocumentsPresenter.showPresenter(view
                                .getContainerTab(view.getViewConstants().libelleTabDocuments()));
                    } else {
                        listeDetailleeDocumentsPresenter.rafraichir();
                    }
                } else if (nomOnglet.equals(view.getViewConstants().libelleTabContratsPM()) && contratsPersonneMoralePresenter == null) {
                    contratsPersonneMoralePresenter = addChildPresenter(new ContratsPersonneMoralePresenter(eventBus, new ContratsPersonneMoraleViewImpl(),
                        idPersonneMorale));
                    contratsPersonneMoralePresenter.showPresenter(view.getContainerTab(view.getViewConstants().libelleTabContratsPM()));
                    contratsPersonneMoralePresenter.addEventHandlerToLocalBus(ContratsPersonneMoraleChargesEvent.TYPE,
                        new ContratsPersonneMoraleChargesEventHandler() {
                        @Override
                        public void updateContratsPersonneMoraleCharges(ContratsPersonneMoraleChargesEvent event) {
                            if (event.getNbContratsCharges() > 0) {
                                view.updateTabName(event.getNbContratsCharges(), view.getViewConstants().libelleTabContratsPM());
                            }
                        }
                    });
                }
            }
        });

        view.getBtActionsContextAddActions().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fireEventGlobalBus(new AjoutActionEvent(idPersonneMorale));
            }
        });

    }

    /**
     * Permet d'initialiser la fiche personne morale à partir du click sur l'evenement.
     * @param personne
     */
    private void initPersonneMorale() {
        view.afficherLoadingPopup();
        personneMoraleRpcService.recherchePersonneMoraleParId(idPersonneMorale, new AsyncCallback<PersonneMoraleModel>() {
            @Override
            public void onSuccess(PersonneMoraleModel result) {
                // MAPPING PERSONNE MORALE
                numClient = result.getNumEntreprise();
                final String emptyString = "";
                view.getTbRaisonSociale().setValue(result.getRaisonSociale() == null ? emptyString : result.getRaisonSociale());
                view.getTbNumEntreprise().setValue(result.getNumEntreprise() == null ? emptyString : result.getNumEntreprise());
                view.getTbNumSiret().setValue(result.getNumSiret() == null ? emptyString : result.getNumSiret());
                view.getTbEnseigneCommerciale().setValue(result.getEnseigneCommercial() == null ? emptyString : result.getEnseigneCommercial());
                view.getTbNAF().setValue(result.getNaf() == null ? emptyString : result.getNaf());

                final IdentifiantLibelleGwt aucun = new IdentifiantLibelleGwt(-1L);
                view.getNature().setValue(result.getNature() != null ? result.getNature() : aucun);
                view.getFormeJuridique().setValue(result.getFormeJuridique() != null ? result.getFormeJuridique() : aucun);

                final IdentifiantLibelleGwt agence = result.getAgence();
                if (agence != null) {
                    view.getAgence().setValue(agence);
                }
                final DimensionRessourceModel commercial = result.getCommercial();
                if (commercial != null) {
                    view.getCommercial().setValue(commercial);
                }
                view.getCreateur().setText(result.getCreateur().getNom() + " " + result.getCreateur().getPrenom());
                view.getDateCreation().setText(result.getDateCreation());

                initPersonneMorale = true;

                view.onRpcServiceSuccess();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    /**
     * Permet d'inifialiser la synthèse de la personne morale.
     */
    private void initPersonneMoraleSimple() {
        view.afficherLoadingPopup();
        rafraichirCompteurs(new CompteursModel(Compteur.values()));
        personneMoraleRpcService.recherchepersonneMoraleSimpleParId(idPersonneMorale, new AsyncCallback<PersonneMoraleSimpleModel>() {
            @Override
            public void onSuccess(PersonneMoraleSimpleModel result) {
                numClient = result.getNumeroEntreprise();
                view.setDataResumePersonneMorale(result.getNumeroEntreprise(), result.getRaisonSociale(), result.getNature() != null ? result.getNature()
                        .getLibelle() : "");

                // AFFICHAGE MENU CONTEXTUEL
                view.getLbContextMenu().setText(view.getViewConstants().actionsSur() + " " + result.getRaisonSociale());

                if (idActionOuvrir != null) {
                    view.selectTab(view.getViewConstants().libelleTabActions());
                } else {
                    view.selectTab(view.getViewConstants().libelleTabCoordonnees());
                }

                view.onRpcServiceSuccess();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
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

        initPersonneMoraleSimple();
        container.add(view.asWidget());
        ((ContainerTabPanel) container).addContextMenu(view.asContextMenuWidget());
    }

    /**
     * Interface de la vue.
     */
    public interface GestionPersonneMoraleView extends View {
        /**
         * Getter sur composant.
         * @return le composant.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSbNature();

        /**
         * Récupère le handler de selection des onglets de la vue.
         * @return le handler.
         */
        HasSelectionHandlers<Integer> getTabPanelSelectionHandlers();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSbFormeJuridique();

        /**
         * Récupère la nature sélectionnée.
         * @return la nature selectionnée.
         */
        HasValue<IdentifiantLibelleGwt> getNature();

        /**
         * Récupère la forme juridique sélectionnée.
         * @return la forme juridique selectionnée.
         */
        HasValue<IdentifiantLibelleGwt> getFormeJuridique();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        HasValue<String> getTbRaisonSociale();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        HasValue<String> getTbNumSiret();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        HasValue<String> getTbNumEntreprise();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        HasValue<String> getTbNAF();

        /**
         * Getter sur composant.
         * @return le composant.
         */
        HasValue<String> getTbEnseigneCommerciale();

        /**
         * Fixe les valeurs de la synthese.
         * @param numEntreprise .
         * @param raisonSociale .
         * @param nature .
         */
        void setDataResumePersonneMorale(final String numEntreprise, final String raisonSociale, final String nature);

        /**
         * Recuperer le gestionnaire des icones d'erreurs.
         * @return iconeErreurChampManager
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Affiche un message de chargement.
         */
        void afficherLoadingPopup();

        /**
         * Retourne les constantes.
         * @return les constantes.
         */
        GestionPersonneMoraleViewImplConstants getViewConstants();

        /**
         * Methode appelé lorsqu'un service Rpc s'est deroulé correctement.
         */
        void onRpcServiceSuccess();

        /**
         * Methode appelé lorsque un servie Rpc ne s'est pas deroulé correctement.
         * @param config infos sur l'erreur.
         */
        void onRpcServiceFailure(final ErrorPopupConfiguration config);

        /**
         * Masque un message de chargement.
         */
        void masquerLoadingPopup();

        /**
         * Gestions du click sur la synthese.
         * @return le composant
         */
        BlocSyntheseDepliant getBlRpersonneMorale();

        /**
         * Recupere le container associé à l'onglet et le remonte selectionne le container.
         * @param nomOnglet le nom de l'onglet
         * @return le container.
         */
        HasWidgets getContainerTab(String nomOnglet);

        /**
         * Recuperer le nom d'un onglet par son index.
         * @param index .
         * @return .
         */
        String getContainerTabInfos(Integer index);

        /**
         * Recuperer le tab panel pour gerer ces evenements.
         * @return la gestion de l'onglet.
         */
        HasBeforeSelectionHandlers<Integer> getTabPanelBeforeSelectionHandlers();

        /**
         * Récupère l'agence sélectionnée.
         * @return l'agence selectionnée.
         */
        HasValue<IdentifiantLibelleGwt> getAgence();

        /**
         * Récupère le commercial selectionné.
         * @return le commercial selectionné.
         */
        HasValue<DimensionRessourceModel> getCommercial();

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
         * Spécifie le créateur.
         * @return le créateur.
         */
        HasText getCreateur();

        /**
         * Spécifie la date de création.
         * @return la date de création.
         */
        HasText getDateCreation();

        /**
         * Bouton ajouter une action à la personne.
         * @return le gestionnaire on click.
         */
        HasClickHandlers getBtActionsContextAddActions();

        /**
         * Recuperer le menu context.
         * @return .
         */
        Widget asContextMenuWidget();

        /**
         * Recuperer la label du menu Context.
         * @return .
         */
        HasText getLbContextMenu();

        /**
         * Recuperer l'index de l'onglet selectionné.
         * @return l'index
         */
        int getTabPanelSelectedIndex();

        /**
         * Sélectionne un onglet par son index.
         * @param index index
         */
        void setTabPanelSelectedIndex(int index);

        /**
         * Sélectionne l'onglet ayant le nom passé en paramètre.
         * @param tabName le nom de l'onglet à sélectionner.
         */
        void selectTab(String tabName);

        /**
         * Modifie le libellé de l'onglet.
         * @param nbItems le nombre à afficher dans l'onglet.
         * @param tabName le libellé de l'onglet.
         */
        void updateTabName(int nbItems, String tabName);

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

    /** Rafraichir un ou plusieurs compteurs. */
    public void rafraichirCompteurs(CompteursModel params) {
        if (mapCompteursOnglets == null) {
            // Remplissage de la map Compteur / onglet :
            mapCompteursOnglets = new HashMap<Compteur, String>();
            mapCompteursOnglets.put(Compteur.ACTIONS, view.getViewConstants().libelleTabActions());
            mapCompteursOnglets.put(Compteur.CONTRATS, view.getViewConstants().libelleTabContratsPM());
            mapCompteursOnglets.put(Compteur.COORDONNEES, view.getViewConstants().libelleTabCoordonnees());
            mapCompteursOnglets.put(Compteur.DOCUMENTS, view.getViewConstants().libelleTabDocuments());
            mapCompteursOnglets.put(Compteur.RELATIONS, view.getViewConstants().libelleTabRelations());
        }

        personneMoraleRpcService.getCompteursParId(idPersonneMorale, params, new AsyncCallback<CompteursModel>() {

            @Override
            public void onSuccess(CompteursModel result) {
                for (Entry<Compteur, Integer> entry : result.getCompteursObtenus().entrySet()) {
                    if (mapCompteursOnglets.containsKey(entry.getKey()) && entry.getValue() != null) {
                        view.updateTabName(entry.getValue(), mapCompteursOnglets.get(entry.getKey()));
                    }
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                // rien
            }
        });
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }

}

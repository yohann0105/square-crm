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
package com.square.client.gwt.client.presenter.personne;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.ListBoxUtil;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.OpenOpportuniteEvent;
import com.square.client.gwt.client.event.UpdateTabNameEvent;
import com.square.client.gwt.client.event.actions.AjoutActionEvent;
import com.square.client.gwt.client.event.opportunites.AjoutOpportuniteEvent;
import com.square.client.gwt.client.event.zoom.ActionZoomOutEvent;
import com.square.client.gwt.client.event.zoom.ActionZoomOutEventHandler;
import com.square.client.gwt.client.event.zoom.ActionZoomOverEvent;
import com.square.client.gwt.client.event.zoom.ActionZoomOverEventHandler;
import com.square.client.gwt.client.exception.ConfirmationCreationOpportuniteExceptionGwt;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.client.gwt.client.model.ActionModel;
import com.square.client.gwt.client.model.ActionModificationModel;
import com.square.client.gwt.client.model.ActionResultatModel;
import com.square.client.gwt.client.model.ActionSyntheseModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.CritereActionSyntheseModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.EnregistrementAction;
import com.square.client.gwt.client.model.HistoriqueCommentaireModel;
import com.square.client.gwt.client.model.StackModel;
import com.square.client.gwt.client.model.actions.ActionZoomModel;
import com.square.client.gwt.client.presenter.action.ActionPopupAnnulerPresenter;
import com.square.client.gwt.client.presenter.personne.PersonneActionContenuPresenter.PersonneActionContenuView;
import com.square.client.gwt.client.service.ActionServiceGwtAsync;
import com.square.client.gwt.client.service.ConstantesServiceGwtAsync;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.view.action.annulation.ActionPopupAnnulerViewImpl;
import com.square.client.gwt.client.view.personne.action.PersonneActionsViewImplConstants;
import com.square.client.gwt.client.view.personne.action.PersonneInfosActionViewImplConstants;
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
import com.square.composants.graphiques.lib.client.event.CloseBlocSyntheseEvent;
import com.square.composants.graphiques.lib.client.event.CloseBlocSyntheseEventHandler;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.HasCloseBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.HasOpenBlocSyntheseEventHandlers;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEvent;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEventHandler;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;

/**
 * Presenter pour les actions d'une personne.
 * @author cblanchard - SCUB
 */
public class PersonneActionsPresenter extends Presenter {

    /** La vue. */
    private PersonneActionsView view;

    /** L'identifiant de la personne. */
    private Long idPersonne;

    /** Le numéro de client. */
    private String numClient;

    /** La ressource connectee. */
    private DimensionRessourceModel ressourceConnectee;

    /** Service des actions. */
    private ActionServiceGwtAsync actionServiceRpc;

    /** Le service de dimension. */
    private DimensionServiceGwtAsync dimensionServiceRpc;

    /** Le service des constantes. */
    private ConstantesServiceGwtAsync constantesServiceRpc;

    /** Les constantes. */
    private ConstantesModel constantes;

    /** Le service des personnes physique. */
    private PersonneServiceGwtAsync personneServiceRPC;

    /** Constantes du presenter. */
    private PersonneActionsPresenterConstants presenterConstants;

    private Map<Integer, List<ActionZoomModel>> mapActionsZoom;

    /** Service de gestion des aides . */
    private AideServiceGwtAsync aideService;

    private Map<Long, Timer> mapTimersAffichageInfoBulle;

    /** Filtre. */
    private Long idActionAOuvir;

    private Long idFiltreIdOpportunite;

    private String idFiltreEidOpportunite;

    private Map<Long, PersonneActionContenuPresenter> mapPresentersActions;

    private ActionPopupAnnulerPresenter actionPopupAnnulerPresenter;

    /**
     * Constructeur.
     * @param eventBus le bus
     * @param actionServiceRpc le service des actions
     * @param dimensionServiceRpcService le service des dimensions
     * @param constantesRpcService le service des constantes
     * @param personneRpcService le service des personnes
     * @param viewActions la vue
     * @param idPersonne l'identifiant de la personne
     * @param constantes les constantes de mapping
     * @param idActionOuvrir l'identifiant de l'action à ouvrir
     * @param filtreIdOpportunite l'identifiant de l'opportunité utilisée pour filtrer les actions
     * @param filtreEidOpportunite l'identifiant externe de l'opportunité utilisée pour filtrer les actions
     * @param numClient le numéro de client.
     * @param ressourceConnectee la ressource connectée
     * @param aideService aideService
     */
    public PersonneActionsPresenter(HandlerManager eventBus, ActionServiceGwtAsync actionServiceRpc, DimensionServiceGwtAsync dimensionServiceRpcService,
        ConstantesServiceGwtAsync constantesRpcService, PersonneServiceGwtAsync personneRpcService, PersonneActionsView viewActions, final Long idPersonne,
        ConstantesModel constantes, Long idActionOuvrir, Long filtreIdOpportunite, String filtreEidOpportunite, final String numClient,
        DimensionRessourceModel ressourceConnectee, AideServiceGwtAsync aideService) {
        super(eventBus);
        this.view = viewActions;
        this.idPersonne = idPersonne;
        this.numClient = numClient;
        this.ressourceConnectee = ressourceConnectee;
        this.actionServiceRpc = actionServiceRpc;
        this.dimensionServiceRpc = dimensionServiceRpcService;
        this.constantesServiceRpc = constantesRpcService;
        this.constantes = constantes;
        this.personneServiceRPC = personneRpcService;
        this.presenterConstants = GWT.create(PersonneActionsPresenterConstants.class);
        this.mapActionsZoom = new HashMap<Integer, List<ActionZoomModel>>();
        this.aideService = aideService;
        mapTimersAffichageInfoBulle = new HashMap<Long, Timer>();
        mapPresentersActions = new HashMap<Long, PersonneActionContenuPresenter>();

        this.idActionAOuvir = idActionOuvrir;
        this.idFiltreEidOpportunite = filtreEidOpportunite;
        this.idFiltreIdOpportunite = filtreIdOpportunite;
    }

    @Override
    public View asView() {
        return view;
    }

    @Override
    public void onBind() {
        view.getBtnAfficherActions().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Suppression du filtre de recherche d'actions à partir d'une opportunité
                view.masquerFiltre();
                initListeActions(null, null, null);
            }
        });
        addEventHandlerToLocalBus(ActionZoomOverEvent.TYPE, new ActionZoomOverEventHandler() {
            @Override
            public void onMouseOver(final ActionZoomOverEvent event) {
                if (!event.isOpen()) {
                    // on lance le timer pour afficher l'info bulle si bloc annulé ou terminé
                    if (event.getActionZoomOver().getViewAction().isAnnuleOuTermine()) {
                        final Timer timerAffichageInfoBulle = new Timer() {
                            @Override
                            public void run() {
                                actionServiceRpc.rechercherActionParIdentifiant(event.getActionZoomOver().getIdAction(), new AsyncCallback<ActionModel>() {
                                    @Override
                                    public void onFailure(Throwable caught) {
                                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                                    }

                                    @Override
                                    public void onSuccess(final ActionModel result) {
                                        view.afficherInfoBulleAction(result, event.getActionZoomOver().getViewAction());
                                    }
                                });
                            }
                        };
                        timerAffichageInfoBulle.schedule(PersonneActionsPresenterConstants.DELAI_AFFICHAGE_INFOBULLE);
                        mapTimersAffichageInfoBulle.put(event.getActionZoomOver().getIdAction(), timerAffichageInfoBulle);
                    }
                }
                for (ActionZoomModel actionZoom : mapActionsZoom.get(event.getActionZoomOver().getIndexBloc())) {
                    if (!event.getActionZoomOver().getIdAction().equals(actionZoom.getIdAction())) {
                        // on verifie si l'action parcourue est la mere ou un enfant de l'action survolée
                        final boolean actionFils = event.getActionZoomOver().getIdAction().equals(actionZoom.getIdActionMere());
                        final boolean actionMere =
                            event.getActionZoomOver().getIdActionMere() != null && event.getActionZoomOver().getIdActionMere().equals(actionZoom.getIdAction());
                        if (!actionFils && !actionMere) {
                            // si ce n'est ni l'un ni l'autre, on ajoute le style pastel
                            actionZoom.getViewAction().appliquerStylePastel(true);
                        }
                    }
                }
            }
        });
        addEventHandlerToLocalBus(ActionZoomOutEvent.TYPE, new ActionZoomOutEventHandler() {
            @Override
            public void onMouseOut(ActionZoomOutEvent event) {
                if (!event.isOpen()) {
                    // On masque l'info bulle du bloc annulé ou terminé, ou on désactive le timer
                    if (event.getActionZoomOut().getViewAction().isAnnuleOuTermine()) {
                        if (mapTimersAffichageInfoBulle.get(event.getActionZoomOut().getIdAction()) != null) {
                            final Timer timerAffichageInfoBulle = mapTimersAffichageInfoBulle.get(event.getActionZoomOut().getIdAction());
                            timerAffichageInfoBulle.cancel();
                            mapTimersAffichageInfoBulle.remove(timerAffichageInfoBulle);
                        }
                        view.masquerInfoBulleAction();
                    }
                }
                for (ActionZoomModel actionZoom : mapActionsZoom.get(event.getActionZoomOut().getIndexBloc())) {
                    // on informe les vues d'enlever le style pastel
                    actionZoom.getViewAction().appliquerStylePastel(false);
                }
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        initListeActions(idActionAOuvir, idFiltreIdOpportunite, idFiltreEidOpportunite);
        container.add(view.asWidget());
    }

    private void nettoyerActions() {
        // Nettoyage des presenters
        for (PersonneActionContenuPresenter presenter : mapPresentersActions.values()) {
            presenter.detachPresenter();
        }
        mapPresentersActions.clear();
        mapActionsZoom.clear();
        view.effacerListeActions();
    }

    /**
     * Permet de re-initlialiser la vue des actions.
     * @param idActionAOuvrir l'identifiant de l'action à ouvrir par defaut.
     * @param filtreIdOpportunite un filtre pour filtrer sur une opporunite.
     * @param filtreEidOpportunite un filtre sur l'eid d'une opportunite.
     */
    public void initListeActions(final Long idActionAOuvrir, final Long filtreIdOpportunite, final String filtreEidOpportunite) {
        this.idActionAOuvir = idActionAOuvrir;
        this.idFiltreIdOpportunite = filtreIdOpportunite;
        this.idFiltreEidOpportunite = filtreEidOpportunite;
        nettoyerActions();

        final CritereActionSyntheseModel critere = new CritereActionSyntheseModel();
        critere.setIdPersonne(idPersonne);
        critere.setFiltrerDateCreation(true);
        if (filtreIdOpportunite != null) {
            critere.setIdOpportunite(filtreIdOpportunite);
        }
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().messageChargement()));
        actionServiceRpc.recupererActionsSynthese(critere, new AsyncCallback<List<StackModel<ActionSyntheseModel>>>() {
            @Override
            public void onSuccess(List<StackModel<ActionSyntheseModel>> result) {
                int nbActionsTotalEnCours = 0;
                // Traitement
                if (result != null) {
                    if (filtreIdOpportunite != null) {
                        view.afficherFiltre(filtreEidOpportunite);
                    }
                    int indexBloc = 0;
                    for (StackModel<ActionSyntheseModel> pile : result) {
                        nbActionsTotalEnCours += pile.size();
                        // on cree un nouveau bloc
                        final PersonneBlocActionView bloc = view.ajouterBlocAction();
                        // on cree la liste des items du bloc
                        mapActionsZoom.put(indexBloc, new ArrayList<ActionZoomModel>());
                        while (!pile.empty()) {
                            final ActionSyntheseModel actionSynthese = pile.pop();
                            // on ajoute l'action au bloc
                            final PersonneInfosActionView actionView = formerAction(actionSynthese, idActionAOuvrir, bloc);
                            // on cree l'item pour le systeme de zoom
                            final ActionZoomModel item = new ActionZoomModel(actionSynthese.getId(), actionSynthese.getIdActionSource(), actionView, indexBloc);
                            mapActionsZoom.get(indexBloc).add(item);
                            // on s'abonne a l'evenement de mouse over
                            actionView.getEnteteAllMouseHandler().addMouseOverHandler(new MouseOverHandler() {
                                @Override
                                public void onMouseOver(MouseOverEvent event) {
                                    fireEventLocalBus(new ActionZoomOverEvent(item, actionView.isOpen()));
                                }
                            });
                            // on s'abonne a l'evenement de mouse out
                            actionView.getEnteteAllMouseHandler().addMouseOutHandler(new MouseOutHandler() {
                                @Override
                                public void onMouseOut(MouseOutEvent event) {
                                    fireEventLocalBus(new ActionZoomOutEvent(item, actionView.isOpen()));
                                }
                            });
                        }
                        indexBloc++;
                    }
                    fireEventLocalBus(new UpdateTabNameEvent(idPersonne, nbActionsTotalEnCours, "Actions"));
                }
                // Fin de chargement
                view.onRpcServiceSuccess();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    private PersonneInfosActionView formerAction(final ActionSyntheseModel actionSynthese, final Long idActionAOuvrir, final PersonneBlocActionView bloc) {
        final PersonneInfosActionView viewActionInfos = bloc.ajouterAction();

        // Construction de la liste des identifiants des composants d'aide de la vue associée à ce presenter
        final List<Long> listeIdsComposantsAides = new ArrayList<Long>();
        for (final AideComposant composant : viewActionInfos.getListAideCompsant()) {
            listeIdsComposantsAides.add(composant.getId());
        }

        // Recherche des composants d'aide existant pour les rendre visible
        aideService.rechercherIdsComposantsAides(listeIdsComposantsAides, new AsyncCallback<List<Long>>() {
            @Override
            public void onSuccess(List<Long> result) {
                if (result != null) {
                    for (final AideComposant composant : viewActionInfos.getListAideCompsant()) {
                        composant.setVisible(result.contains(composant.getId()));
                    }
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });

        for (HasUpdateAideEventHandler handler : viewActionInfos.getListeUpdateEventHandler()) {
            handler.addUpdateAideEventHandler(new UpdateAideEventHandler() {
                @Override
                public void onUpdateAide(UpdateAideEvent event) {
                    aideService.creerOuModifierAide(event.getAide(), event.getCallBack());
                }
            });
        }
        for (HasDemandeAideEventHandler handler : viewActionInfos.getListeDemandeEventHandler()) {
            handler.addDemandeAideEventHandler(new DemandeAideEventHandler() {
                @Override
                public void onDemandeAide(DemandeAideEvent event) {
                    aideService.rechercherAide(event.getIdComposant(), event.getCallBack());
                }
            });
        }

        // Alimentation de la liste des priorités
        viewActionInfos.getSlbPriorite().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                dimensionServiceRpc.rechercherPrioriteActions(criteres, event.getCallBack());
            }
        });
        if (actionSynthese.getType() != null) {
            viewActionInfos.getlType().setText(actionSynthese.getType().getLibelle());
        }
        viewActionInfos.getlObjet().setText(actionSynthese.getObjet());
        viewActionInfos.getlSousObjet().setText(actionSynthese.getSousObjet() == null ? "-" : actionSynthese.getSousObjet());
        if (actionSynthese.getNiveau() > 0) {
            viewActionInfos.getLibelleNiveau().setHTML(viewActionInfos.getViewConstants().libelleActionNiveau());
            viewActionInfos.getlNiveau().setText(String.valueOf(actionSynthese.getNiveau()));
        }
        // Date A Faire
        if (actionSynthese.getDateAction() != null) {
            viewActionInfos.getcdbDate().setDate(DateUtil.getDate(actionSynthese.getDateAction()));
        }
        // On n'affiche pas les heures créées par défaut (00:00)
        if (!PersonneActionsPresenterConstants.HEURE_VIDE.equals(actionSynthese.getHeureAction())) {
            viewActionInfos.getlHeureDate().setValue(actionSynthese.getHeureAction());
        }
        // Date terminée
        if (actionSynthese.getDateActionTerminee() != null && !"".equals(actionSynthese.getDateActionTerminee())) {
            viewActionInfos.getLDateTerminee().setText(actionSynthese.getDateActionTerminee());
            if (actionSynthese.getHeureActionTerminee() != null && !"".equals(actionSynthese.getHeureActionTerminee())
                && !PersonneActionsPresenterConstants.HEURE_VIDE.equals(actionSynthese.getHeureActionTerminee())) {
                viewActionInfos.getLHeureTerminee().setText(" " + presenterConstants.compA() + " " + actionSynthese.getHeureActionTerminee());
            }
        }
        if (actionSynthese.getDateActionEditable() == null || !actionSynthese.getDateActionEditable()) {
            viewActionInfos.desactiverDate();
        }

        viewActionInfos.getlRessource().setText(actionSynthese.getAttribueA());
        if (actionSynthese.getReclamation()) {
            viewActionInfos.getlReclamation().setText(presenterConstants.reclamation());
        }
        viewActionInfos.getSlbPrioriteValue().setValue(actionSynthese.getPriorite());
        viewActionInfos.getSlbDureeValue().setValue(actionSynthese.getDuree());
        // La priorité est modifiable seulement si c'est une action de type relance qui n'a jamais été modifiée (date de modification null)
        if (actionSynthese.getType() != null && constantes.getIdTypeActionRelance().equals(actionSynthese.getType().getIdentifiant())
            && actionSynthese.getDateModification() == null) {
            viewActionInfos.activerPriorite(true);
        } else {
            viewActionInfos.activerPriorite(false);
        }
        viewActionInfos.getLNatureContact().setText(actionSynthese.getNatureContact());
        viewActionInfos.getLCampagne().setText(actionSynthese.getCampagne());
        viewActionInfos.getBlocSyntheseDepliantOpenHandler().addOpenEventHandler(new OpenBlocSyntheseEventHandler() {
            @Override
            public void onOpen(OpenBlocSyntheseEvent event) {
                final EnregistrementAction enregistrementAction = new EnregistrementAction();
                enregistrementAction.setIdAction(actionSynthese.getId());
                enregistrementAction.setIdStatut(actionSynthese.getStatut().getIdentifiant());
                // Création de la vue du contenu de l'action
                final PersonneActionContenuView vueContenu = viewActionInfos.creerVueContenu();
                // Création du présenter

                if (!mapPresentersActions.containsKey(actionSynthese.getId())) {
                    final PersonneActionContenuPresenter actionPresenter =
                        new PersonneActionContenuPresenter(eventBus, vueContenu, actionSynthese.getId(), actionServiceRpc, constantesServiceRpc,
                            dimensionServiceRpc, constantes, enregistrementAction, idPersonne, numClient, personneServiceRPC, aideService, ressourceConnectee);
                    mapPresentersActions.put(actionSynthese.getId(), actionPresenter);
                    actionPresenter.showPresenter(viewActionInfos.getContenu());
                } else {
                    mapPresentersActions.get(actionSynthese.getId()).initialiserContenu();
                }
                viewActionInfos.activerBtnEnregistrer(actionSynthese.getStatut().getIdentifiant() != null
                    && actionSynthese.getStatut().getIdentifiant().equals(constantes.getIdStatutAFaire()));

                if (actionSynthese.getStatut().getIdentifiant().equals(constantes.getIdStatutTerminer())
                    || actionSynthese.getStatut().getIdentifiant().equals(constantes.getIdStatutAnnuler())) {
                    viewActionInfos.restaurerBloc();
                    viewActionInfos.getBlocSyntheseDepliantCloseHandler().addCloseEventHandler(new CloseBlocSyntheseEventHandler() {
                        @Override
                        public void onClose(CloseBlocSyntheseEvent event) {
                            viewActionInfos.reduireBlocEnUneLigne();
                        }
                    });
                }
                viewActionInfos.getBtnEnregistrer().addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        if (isDatesValides(viewActionInfos)) {
                            enregistrementAction(viewActionInfos, vueContenu, enregistrementAction);
                        }
                    }
                });

                // Alimentation de la liste des durées
                viewActionInfos.getSlbDuree().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
                    @Override
                    public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                        if (event.getSuggest() == null || "".equals(event.getSuggest())) {
                            vueContenu.activerAjoutAgenda(false);
                            vueContenu.getCbAjoutAgenda().setValue(false);
                        }
                        final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                        criteres.setLibelle(event.getSuggest());
                        dimensionServiceRpc.rechercherDureeActionParCriteres(criteres, event.getCallBack());
                    }
                });

                // on s'abonne aux évènement de modification de la date, l'heure et la durée de l'action
                viewActionInfos.getValueChangeHandlerDateAction().addValueChangeHandler(new ValueChangeHandler<Date>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<Date> event) {
                        if (hasValuePourAjoutAgenda(viewActionInfos) && isDatesValides(viewActionInfos)) {
                            vueContenu.activerAjoutAgenda(true);
                        } else {
                            vueContenu.activerAjoutAgenda(false);
                            vueContenu.getCbAjoutAgenda().setValue(false);
                        }
                    }
                });
                viewActionInfos.getValueChangeHandlerHeureAction().addValueChangeHandler(new ValueChangeHandler<String>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<String> event) {
                        if (hasValuePourAjoutAgenda(viewActionInfos) && isDatesValides(viewActionInfos)) {
                            vueContenu.activerAjoutAgenda(true);
                        } else {
                            vueContenu.activerAjoutAgenda(false);
                            vueContenu.getCbAjoutAgenda().setValue(false);
                        }
                    }
                });
                viewActionInfos.getValueChangeHandlerDuree().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                        if (hasValuePourAjoutAgenda(viewActionInfos) && isDatesValides(viewActionInfos)) {
                            vueContenu.activerAjoutAgenda(true);
                        } else {
                            vueContenu.activerAjoutAgenda(false);
                            vueContenu.getCbAjoutAgenda().setValue(false);
                        }
                    }
                });
                viewActionInfos.getKeyUpHandlerDateAction().addKeyUpHandler(new KeyUpHandler() {
                    @Override
                    public void onKeyUp(KeyUpEvent event) {
                        final int keyCode = event.getNativeKeyCode();
                        if (keyCode == KeyCodes.KEY_DELETE || keyCode == KeyCodes.KEY_BACKSPACE) {
                            if (hasValuePourAjoutAgenda(viewActionInfos) && isDatesValides(viewActionInfos)) {
                                vueContenu.activerAjoutAgenda(true);
                            } else {
                                vueContenu.activerAjoutAgenda(false);
                                vueContenu.getCbAjoutAgenda().setValue(false);
                            }
                        }
                    }
                });
            }
        });

        viewActionInfos.getBtnAjoutActionLiee().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fireEventGlobalBus(new AjoutActionEvent(idPersonne, actionSynthese.getId(), idFiltreIdOpportunite, idFiltreEidOpportunite, true));
            }
        });

        // Gestion du statut terminer.
        if (actionSynthese.getStatut() != null && actionSynthese.getStatut().getIdentifiant() != null) {
            if (actionSynthese.getStatut().getIdentifiant().equals(constantes.getIdStatutTerminer())) {
                viewActionInfos.modifierStyleStatutTerminer();
                viewActionInfos.desactiverInfos();
                viewActionInfos.activerPriorite(false);
                viewActionInfos.reduireBlocEnUneLigne();
                viewActionInfos.setAnnuleOuTermine(true);
            } else if (actionSynthese.getStatut().getIdentifiant().equals(constantes.getIdStatutAnnuler())) {
                viewActionInfos.modifierStyleStatutAnnuler();
                viewActionInfos.desactiverInfos();
                viewActionInfos.activerPriorite(false);
                viewActionInfos.reduireBlocEnUneLigne();
                viewActionInfos.setAnnuleOuTermine(true);
            } else {
                viewActionInfos.modifierStyleStatutEnCours();
                viewActionInfos.setAnnuleOuTermine(false);
            }
        }

        // on ouvre le bloc si il s'agit de l'action à ouvrir
        if (actionSynthese.getId().equals(idActionAOuvrir)) {
            viewActionInfos.openBlocSyntheseDepliant(true);
        }
        return viewActionInfos;
    }

    /**
     * Méthode privée qui récupère les éléments des deux vues pour les enregistrer.
     * @param actionInfos la vue du bandeau de synthèse
     * @param vueContenu la vue du contenu du bloc
     * @param idAction l'identifiant de l'action
     */
    private void enregistrementAction(final PersonneInfosActionView actionInfos, final PersonneActionContenuView vueContenu,
        final EnregistrementAction enregistrementAction) {
        // Récupération des champs à modifier.
        final ActionModificationModel actionModificationModel = new ActionModificationModel();
        // Identifiant de l'action
        actionModificationModel.setIdAction(enregistrementAction.getIdAction());
        // Agence
        if (vueContenu.getAttributionAgence() != null) {
            actionModificationModel.setAgence(vueContenu.getAttributionAgence());
        }
        // Ressource
        if (vueContenu.getRessourceAgence() != null) {
            actionModificationModel.setRessource(vueContenu.getRessourceAgence());
        }
        // Nature contact
        actionModificationModel.setNatureAction(vueContenu.getSlbNatureContactValue().getValue());
        // Statut
        actionModificationModel.setStatut(vueContenu.getSlbStatut().getValue());
        // Résultat
        actionModificationModel.setResultat(vueContenu.getSlbsResultatAction().getValue());
        // Résultat de la nature
        if (vueContenu.getSlbNatureResultat() != null) {
            actionModificationModel.setNatureResultat(vueContenu.getSlbNatureResultatValue().getValue());
        }
        // Commentaire
        final HistoriqueCommentaireModel commentaire = new HistoriqueCommentaireModel();
        commentaire.setDescriptif(vueContenu.getRtaDescriptif().getHTML());
        commentaire.setRessource(ressourceConnectee);
        actionModificationModel.setCommentaire(commentaire);
        // La date d'action
        if (actionInfos.getcdbDate().getValue() != null) {
            actionModificationModel.setDateAction(DateUtil.getString(actionInfos.getcdbDate().getValue()));
        }
        // Durée
        if (actionInfos.getSlbDureeValue() != null) {
            actionModificationModel.setDuree(actionInfos.getSlbDureeValue().getValue());
        }
        actionModificationModel.setHeureAction(actionInfos.getlHeureDate().getValue());
        // Notification
        actionModificationModel.setRappel(vueContenu.getCbRappel().getValue());
        if (vueContenu.getLbNotification().getSelectedIndex() != -1) {
            final Long idNotification = Long.valueOf(ListBoxUtil.getValueSelectListe(vueContenu.getLbNotification()));
            actionModificationModel.setIdNotification(idNotification);
        }
        // Rappel mail
        actionModificationModel.setRappelMail(vueContenu.getCbRappelMail().getValue());
        // Visible dans l'agenda
        actionModificationModel.setVisibleAgenda(vueContenu.getCbAjoutAgenda().getValue());
        // Priorité
        if (actionInfos.getSlbPrioriteValue() != null) {
            actionModificationModel.setPriorite(actionInfos.getSlbPrioriteValue().getValue());
        }

        // Si le résultat est passée à relance, on demande la création d'une action liée, l'enregistrement de l'action source est différée.
        if (actionModificationModel.getResultat() != null && actionModificationModel.getResultat().getIdentifiant().equals(constantes.getIdResultatRelance())) {
            actionModificationModel.setVerifierRegleGestionOpportuniteEnCours(Boolean.FALSE);
            GWT.log(actionModificationModel.getNatureAction() + " : " + actionModificationModel.getStatut());
            fireEventGlobalBus(new AjoutActionEvent(idPersonne, actionModificationModel.getIdAction(), idFiltreIdOpportunite, idFiltreEidOpportunite,
                actionModificationModel, false));
        } else {
            // Appel au service de modification
            actionServiceRpc.modifierAction(actionModificationModel, new AsyncCallback<ActionResultatModel>() {
                @Override
                public void onSuccess(final ActionResultatModel actionResultat) {
                    view.effacerListeActions();
                    // Si l'action est passée à terminer ou annuler on la réduit.
                    if (actionModificationModel.getStatut().getIdentifiant().equals(constantes.getIdStatutTerminer())
                        || actionModificationModel.getStatut().getIdentifiant().equals(constantes.getIdStatutAnnuler())) {
                        initListeActions(null, null, null);
                    } else {
                        initListeActions(actionModificationModel.getIdAction(), null, null);
                    }
                    view.onRpcServiceSuccess();
                    final PopupInfoConfiguration config =
                        new PopupInfoConfiguration(presenterConstants.notificationActionMaj(), AppControllerConstants.NOTIFICATION_TIME_OUT);
                    config.setCallback(new PopupCallback() {

                        @Override
                        public void onResult(boolean result) {
                            // Si l'action est passée à annuler on demande la création d'une action liée.
                            if (actionModificationModel.getStatut().getIdentifiant().equals(constantes.getIdStatutAnnuler())
                                && !enregistrementAction.getIdStatut().equals(constantes.getIdStatutAnnuler())) {
                                if (actionPopupAnnulerPresenter == null) {
                                    actionPopupAnnulerPresenter =
                                        addChildPresenter(new ActionPopupAnnulerPresenter(eventBus, new ActionPopupAnnulerViewImpl(), actionModificationModel
                                                .getIdAction(), idPersonne, idFiltreIdOpportunite, idFiltreEidOpportunite));
                                    actionPopupAnnulerPresenter.showPresenter(null);
                                } else {
                                    actionPopupAnnulerPresenter.afficher(actionModificationModel.getIdAction(), idPersonne, idFiltreIdOpportunite,
                                        idFiltreEidOpportunite);
                                }
                            }

                            // Si le résultat est à opportunité, création de l'opportunite
                            if (actionModificationModel.getResultat() != null
                                && actionModificationModel.getResultat().getIdentifiant().equals(constantes.getIdResultatOpportunite())) {
                                fireEventLocalBus(new OpenOpportuniteEvent(actionResultat.getIdOpportunite(), true));
                            }
                        }
                    });
                    new DecoratedInfoPopup(config).show();
                }

                @Override
                public void onFailure(Throwable caught) {
                    if (caught instanceof ControleIntegriteExceptionGwt) {
                        final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                        // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                        actionInfos.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                        vueContenu.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                    } else if (caught instanceof ConfirmationCreationOpportuniteExceptionGwt) {
                        // Si finalité opportunité & opportunité en cours, cette exception est levée, il faut tout de même forcer la création d'une opp
                        fireEventLocalBus(new AjoutOpportuniteEvent(idPersonne));
                    } else {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                    }
                }
            });
        }
    }

    /**
     * Teste la validité des dates.
     * @return true si les dates sont valides, false sinon
     */
    private boolean isDatesValides(PersonneInfosActionView viewActionInfos) {
        boolean datesValides = true;
        final RapportModel rapport = new RapportModel();
        if (!viewActionInfos.getcdbDate().isDateValide()) {
            rapport.ajoutRapport(presenterConstants.champDateAction(), presenterConstants.erreurDateActionInvalide(), true);
            datesValides = false;
        }
        viewActionInfos.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
        return datesValides;
    }

    /**
     * Détermine si on peut ajouter la possibiliter d'ajouter l'action dans l'agenda.
     * @return true si on peut ajouter l'action dans l'agenda, false si non.
     */
    private boolean hasValuePourAjoutAgenda(PersonneInfosActionView vue) {
        final IdentifiantLibelleGwt duree = vue.getSlbDureeValue().getValue();
        final Date dateAction = vue.getcdbDate().getValue();
        final String heureAction = vue.getlHeureDate().getValue();
        return duree != null && duree.getIdentifiant() != null && dateAction != null && heureAction != null && !"".equals(heureAction);
    }

    /**
     * Interface de la vue des actions.
     */
    public interface PersonneActionsView extends View {
        /**
         * Efface les actions.
         */
        void effacerListeActions();

        /**
         * Masque l'entête indiquant le filtre.
         */
        void masquerFiltre();

        /**
         * Affiche l'entête du filtre.
         * @param eidOpportunite l'eid de l'opportunité.
         */
        void afficherFiltre(String eidOpportunite);

        /**
         * Fermer la popup de chargement.
         */
        void onRpcServiceSuccess();

        /**
         * Popup d'erreur.
         * @param errorPopupConfiguration la configuration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Affiche une popup de chargement.
         * @param loadingPopupConfiguration la configuration
         */
        void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration);

        /**
         * Ajoute un bloc action.
         * @return l'interface de la vue
         */
        PersonneBlocActionView ajouterBlocAction();

        /**
         * Renvoi la valeur de viewConstants.
         * @return viewConstants
         */
        PersonneActionsViewImplConstants getViewConstants();

        /**
         * Renvoi la valeur de btnAfficherActions.
         * @return btnAfficherActions
         */
        HasClickHandlers getBtnAfficherActions();

        /**
         * Affiche l'info bulle de l'action.
         * @param action action
         * @param personneInfosActionView bloc survolé
         */
        void afficherInfoBulleAction(ActionModel action, PersonneInfosActionView personneInfosActionView);

        /**
         * Masque l'info bulle de l'action.
         */
        void masquerInfoBulleAction();
    }

    /**
     * Interface de la vue des blocs.
     */
    public interface PersonneBlocActionView extends View {
        /**
         * Creer une vue et l'ajouter a la page.
         * @return l'interface de la vue créee
         */
        PersonneInfosActionView ajouterAction();
    }

    /**
     * Interface de la vue sur le détail des actions.
     */
    public interface PersonneInfosActionView extends View {
        /**
         * Renvoi la valeur de btnAjoutActionLiee.
         * @return btnAjoutActionLiee
         */
        HasClickHandlers getBtnAjoutActionLiee();

        /** Modife le style du statut en cours. */
        void modifierStyleStatutEnCours();

        /** Modife le style du statut annuler. */
        void modifierStyleStatutAnnuler();

        /** Modife le style du statut terminer. */
        void modifierStyleStatutTerminer();

        /**
         * Permet de réduire le bloc en une seule ligne.
         */
        void reduireBlocEnUneLigne();

        /**
         * Permet de restaurer la taille original du bloc.
         */
        void restaurerBloc();

        /**
         * Créer la vue du contenu d'une action.
         * @return l'interface de la vue créee
         */
        PersonneActionContenuView creerVueContenu();

        /**
         * Désactive les champs de la date d'action.
         */
        void desactiverDate();

        /**
         * Renvoi la valeur de btnEnregistrer.
         * @return btnEnregistrer
         */
        HasClickHandlers getBtnEnregistrer();

        /**
         * Renvoi la valeur de lType.
         * @return lType
         */
        HasText getlType();

        /**
         * Renvoi la valeur de lObjet.
         * @return lObjet
         */
        HasText getlObjet();

        /**
         * Renvoi la valeur de lSousObjet.
         * @return lSousObjet
         */
        HasText getlSousObjet();

        /**
         * Renvoi la valeur de lNiveau.
         * @return lNiveau
         */
        HasText getlNiveau();

        /**
         * Accesseur widget qui affiche le libellé pour le niveau d'une action.
         * @return widget qui affiche le libellé du niveau de l'action.
         */
        HasHTML getLibelleNiveau();

        /**
         * Renvoi la valeur de lRessource.
         * @return lRessource
         */
        HasText getlRessource();

        /**
         * Renvoi la valeur de lReclamation.
         * @return lReclamation
         */
        HasText getlReclamation();

        /**
         * Renvoi la valeur de slbPriorite.
         * @return slbPriorite
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbPriorite();

        /**
         * Renvoi la valeur de slbPriorite.
         * @return slbPriorite
         */
        HasValue<IdentifiantLibelleGwt> getSlbPrioriteValue();

        /**
         * Renvoi la valeur de constants.
         * @return constants
         */
        PersonneInfosActionViewImplConstants getConstants();

        /**
         * Renvoi la valeur de blocSyntheseDepliant.
         * @return blocSyntheseDepliant
         */
        HasOpenBlocSyntheseEventHandlers getBlocSyntheseDepliantOpenHandler();

        /**
         * Renvoi la valeur de blocSyntheseDepliant.
         * @return blocSyntheseDepliant
         */
        HasCloseBlocSyntheseEventHandlers getBlocSyntheseDepliantCloseHandler();

        /**
         * Renvoi la valeur de contenu.
         * @return contenu
         */
        VerticalPanel getContenu();

        /**
         * Renvoi la valeur de lHeureDate.
         * @return lHeureDate
         */
        HasValue<String> getlHeureDate();

        /**
         * Renvoi la valeur de iconeErreurChampManager.
         * @return iconeErreurChampManager
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Désactive les infos.
         */
        void desactiverInfos();

        /**
         * Renvoi la valeur de lDate.
         * @return lDate
         */
        DecoratedCalendrierDateBox getcdbDate();

        /**
         * Active / Désactive le bouton d'enregistrement des actions.
         * @param isEnable true actif, false inactif.
         */
        void activerBtnEnregistrer(boolean isEnable);

        /**
         * Récupère les constantes de la vue.
         * @return contantes de la vue.
         */
        PersonneInfosActionViewImplConstants getViewConstants();

        /**
         * Recupere les handlers de mouse sur l'entete.
         * @return les handlers
         */
        HasAllMouseHandlers getEnteteAllMouseHandler();

        /**
         * Ouvre/Ferme le bloc de synthese.
         * @param open true ouvre, false ferme.
         */
        void openBlocSyntheseDepliant(boolean open);

        /**
         * Applique ou enleve le style pastel.
         * @param set true applique, false enleve.
         */
        void appliquerStylePastel(boolean set);

        /**
         * Récupère la nature du contact.
         * @return la nature de contact.
         */
        HasText getLNatureContact();

        /**
         * Récupère le libellé de la campagne.
         * @return le libellé de la campagne.
         */
        HasText getLCampagne();

        /**
         * Active / Désactive la liste des priorités.
         * @param isEnable true actif, false inactif.
         */
        void activerPriorite(boolean isEnable);

        /**
         * Récupère le libellé de la date Terminée.
         * @return le libellé de la date Terminée.
         */
        HasText getLDateTerminee();

        /**
         * Récupère le libellé de l'heure Terminée.
         * @return le libellé de l'heure Terminée.
         */
        HasText getLHeureTerminee();

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

        /**
         * Renvoie la valeur de isAnnuleOuTermine.
         * @return isAnnuleOuTermine
         */
        boolean isAnnuleOuTermine();

        /**
         * Modifie isAnnuleOuTermine.
         * @param isAnnuleOuTermine la nouvelle valeur de isAnnuleOuTermine
         */
        void setAnnuleOuTermine(boolean isAnnuleOuTermine);

        /**
         * Retourne si le bloc est ouvert ou pas.
         * @return flag
         */
        boolean isOpen();

        /**
         * Renvoi la valeur de slbDuree.
         * @return slbPriorite
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbDuree();

        /**
         * Renvoi la valeur de slbDuree.
         * @return slbPriorite
         */
        HasValue<IdentifiantLibelleGwt> getSlbDureeValue();

        /**
         * Récupère le handler de changement de la date de l'action.
         * @return le handler.
         */
        HasValueChangeHandlers<Date> getValueChangeHandlerDateAction();

        /**
         * Récupère le handler de changement de l'heure de l'action.
         * @return le handler.
         */
        HasValueChangeHandlers<String> getValueChangeHandlerHeureAction();

        /**
         * Récupère le handler de changement de la valeur de la durée.
         * @return le handler.
         */
        HasValueChangeHandlers<IdentifiantLibelleGwt> getValueChangeHandlerDuree();

        /**
         * Récupère le handler de changement de la date de l'action.
         * @return le handler.
         */
        HasKeyUpHandlers getKeyUpHandlerDateAction();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}

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
import java.util.Date;
import java.util.List;

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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.OpenPersonEvent;
import com.square.client.gwt.client.event.OpenPersonneMoraleEvent;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.client.gwt.client.model.ActionCreationModel;
import com.square.client.gwt.client.model.ActionModel;
import com.square.client.gwt.client.model.ActionModificationModel;
import com.square.client.gwt.client.model.ActionNotificationInfosModel;
import com.square.client.gwt.client.model.ActionResultatModel;
import com.square.client.gwt.client.model.CampagneCriteresRechercheLibelleModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheObjetModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheSousObjetModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheRessourceModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.PersonneBaseModel;
import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.model.PersonneMoraleModel;
import com.square.client.gwt.client.service.ActionServiceGwtAsync;
import com.square.client.gwt.client.service.CampagneServiceGwtAsync;
import com.square.client.gwt.client.service.ConstantesServiceGwtAsync;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.view.action.creation.ActionCreationViewImplConstants;
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
import com.square.composants.graphiques.lib.client.composants.model.SousRapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEvent;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEventHandler;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;
import com.square.composants.graphiques.lib.client.popups.minimizable.IsMinimizable;

/**
 * Présenter de la popup de création d'action.
 * @author cblanchard - SCUB
 */
public class ActionCreationPresenter extends Presenter {

    /** La vue. */
    ActionCreationView view;

    /** Le service des actions. */
    private ActionServiceGwtAsync actionRpcService;

    /** Le service des dimensions. */
    private DimensionServiceGwtAsync dimensionRpcService;

    /** Le service des personnes. */
    private PersonneServiceGwtAsync personneRpcService;

    /** Le service des campagnes. */
    private CampagneServiceGwtAsync campagneRpcService;

    /** Le service de constantes. */
    private ConstantesServiceGwtAsync constantesRpcService;

    /** Le service de gestion d'aides. */
    private AideServiceGwtAsync aideService;

    /** L'identifiant de la personne. */
    private Long idPersonne;

    /** L'identifiant de l'action source. */
    private Long idActionSource;

    /** La ressource connectee. */
    private DimensionRessourceModel ressourceConnectee;

    /** La ressource connectee. */
    private DimensionRessourceModel ressourceSelectionnee;

    /** l'identifiant de l'opportunité utilisée pour filtrer les actions. */
    private Long filtreIdOpportunite;

    /** l'identifiant externe de l'opportunité utilisée pour filtrer les actions. */
    private String filtreEidOpportunite;

    /** L'action source à enregistrer avant l'enregistrement d'action liée. */
    private ActionModificationModel actionSourceAEnregistrer;

    /** Constantes du presenter. */
    private ActionCreationPresenterConstants presenterConstants;

    /** Indique si l'on affiche dans la popup la possibilité de fermer l'action source. */
    private Boolean demanderFermerSource;

    /** Les constantes. */
    private ConstantesModel constantesModel;

    private DeskBar deskBar;

    private HandlerRegistration deskBarRegistration;

    /**
     * Constructeur du présenter.
     * @param eventBus le bus.
     * @param actionCreationView la vue.
     * @param dimensionRpcService le service de dimension.
     * @param actionRpcService le service d'action.
     * @param personneRpcService le service des personnes.
     * @param campagneRpcService le service des campagnes.
     * @param constantesRpcService le service de constantes.
     * @param aideServiceGwt aideServiceGwt
     * @param idPersonne l'identifiant de la personne.
     * @param idActionSource l'identifiant de l'action source.
     * @param filtreIdOpportunite l'identifiant de l'opportunité utilisée pour filtrer les actions
     * @param filtreEidOpportunite l'identifiant externe de l'opportunité utilisée pour filtrer les actions
     * @param ressourceConnectee la ressource connectée
     * @param actionSourcePourRelance l'action source à enregistrer avant l'enregistrement de la relance
     * @param demanderFermerSource Indique si l'on affiche dans la popup la possibilité de fermer l'action source
     * @param constantesModel les constantesModel.
     * @param deskBar deskBar
     */
    public ActionCreationPresenter(HandlerManager eventBus, ActionCreationView actionCreationView, DimensionServiceGwtAsync dimensionRpcService,
        ActionServiceGwtAsync actionRpcService, PersonneServiceGwtAsync personneRpcService, CampagneServiceGwtAsync campagneRpcService,
        ConstantesServiceGwtAsync constantesRpcService, AideServiceGwtAsync aideServiceGwt, Long idPersonne, Long idActionSource, Long filtreIdOpportunite,
        String filtreEidOpportunite, DimensionRessourceModel ressourceConnectee, ActionModificationModel actionSourcePourRelance, Boolean demanderFermerSource,
        ConstantesModel constantesModel, DeskBar deskBar) {
        super(eventBus);
        this.view = actionCreationView;
        this.actionRpcService = actionRpcService;
        this.dimensionRpcService = dimensionRpcService;
        this.personneRpcService = personneRpcService;
        this.campagneRpcService = campagneRpcService;
        this.constantesRpcService = constantesRpcService;
        this.aideService = aideServiceGwt;
        this.idPersonne = idPersonne;
        this.idActionSource = idActionSource;
        this.filtreIdOpportunite = filtreIdOpportunite;
        this.filtreEidOpportunite = filtreEidOpportunite;
        this.ressourceConnectee = ressourceConnectee;
        this.actionSourceAEnregistrer = actionSourcePourRelance;
        this.presenterConstants = GWT.create(ActionCreationPresenterConstants.class);
        this.demanderFermerSource = demanderFermerSource;
        this.constantesModel = constantesModel;
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
        view.getAllKeyHandlersFocusPanel().addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                final int keyCode = event.getNativeKeyCode();
                if (keyCode == KeyCodes.KEY_ENTER) {
                    if (isDatesValides()) {
                        modifierActionSourcePourRelance();
                    }
                }
                else if (keyCode == KeyCodes.KEY_ESCAPE) {
                    view.hidePopup();
                }
            }
        });
        // Click sur le bouton annuler
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.hidePopup();
                view.clearPopup();
            }
        });
        // Click sur le bouton creer
        view.getBtnCreer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Modification de l'action source s'il y en a une
                if (isDatesValides()) {
                    view.activerBtnCreer(false);
                    modifierActionSourcePourRelance();
                }
            }
        });
        view.getSlbPriorite().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                dimensionRpcService.rechercherPrioriteActions(criteres, event.getCallBack());
            }
        });
        view.getSlbNature().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                dimensionRpcService.rechercherNatureActions(criteres, event.getCallBack());
            }
        });
        view.getSlbType().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                dimensionRpcService.rechercherTypesActions(criteres, event.getCallBack());
            }
        });
        view.getSlbType().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                view.getSlbObjet().clean();
                view.getSlbSousObjet().clean();
                view.activerObjet(event.getValue() != null);
                view.activerSousObjet(false);
            }
        });
        // Alimentation de l'objet
        view.getSlbObjet().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCritereRechercheObjetModel criteres = new DimensionCritereRechercheObjetModel();
                final DimensionCriteresRechercheModel dimensionCriteres = new DimensionCriteresRechercheModel();
                dimensionCriteres.setLibelle(event.getSuggest());
                if (view.getSlbType().getValue() != null) {
                    criteres.setIdType(view.getSlbType().getValue().getIdentifiant());
                }
                criteres.setDimensionCriteres(dimensionCriteres);
                dimensionRpcService.rechercherObjetActions(criteres, event.getCallBack());
            }
        });
        view.getSlbObjet().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                view.getSlbSousObjet().clean();
                view.activerSousObjet(event.getValue() != null);
            }
        });
        // Alimentation du sous objet
        view.getSlbSousObjet().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCritereRechercheSousObjetModel criteres = new DimensionCritereRechercheSousObjetModel();
                final DimensionCriteresRechercheModel dimensionCriteres = new DimensionCriteresRechercheModel();
                dimensionCriteres.setLibelle(event.getSuggest());
                if (view.getSlbObjet().getValue() != null) {
                    criteres.setIdObjet(view.getSlbObjet().getValue().getIdentifiant());
                }
                criteres.setDimensionCriteres(dimensionCriteres);
                dimensionRpcService.rechercherSousObjetActions(criteres, event.getCallBack());
            }
        });
        // Alimentation de la campagne
        view.getSlbCampagne().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final CampagneCriteresRechercheLibelleModel criteres = new CampagneCriteresRechercheLibelleModel();
                criteres.setLibelle(event.getSuggest());
                campagneRpcService.rechercherCampagnesParLibelle(criteres, event.getCallBack());
            }
        });
        view.getSuggestAgence().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                final String libelleAgence = event.getSuggest();
                criteres.setLibelle(libelleAgence);
                criteres.setVisible(true);
                ressourceSelectionnee = null;
                if (libelleAgence == null || "".equals(libelleAgence.trim())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionRpcService.rechercherAgenceParCriteres(criteres, event.getCallBack());
            }
        });
        view.getSuggestRessourceAgence().addSuggestHandler(new SuggestListBoxSuggestEventHandler<DimensionRessourceModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<DimensionRessourceModel> event) {
                final DimensionCriteresRechercheRessourceModel criteres = new DimensionCriteresRechercheRessourceModel();
                final String libelleRessourceRecherchee = event.getSuggest();
                if (view.getAgence().getValue() != null) {
                    criteres.setIdAgence(view.getAgence().getValue().getIdentifiant());
                }
                criteres.setPrenom(libelleRessourceRecherchee);
                criteres.setNom(libelleRessourceRecherchee);
                // Recherche des ressources actives
                final List<Long> listeIdsEtats = new ArrayList<Long>();
                listeIdsEtats.add(constantesModel.getIdEtatActifRessource());
                criteres.setIdEtats(listeIdsEtats);
                if (libelleRessourceRecherchee == null || "".equals(libelleRessourceRecherchee.trim())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionRpcService.rechercherRessourceParCriteres(criteres, event.getCallBack());
            }
        });
        view.getValueChangedHandlerAgence().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                // On déselectionne la ressource
                view.getRessourceAgence().setValue(ressourceSelectionnee);
            }
        });
        view.getDecoratedSlbsRessourceAgence().addValueChangeHandler(new ValueChangeHandler<DimensionRessourceModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<DimensionRessourceModel> event) {
                final Long idRessource = (event.getValue() == null) ? null : event.getValue().getIdentifiant();
                ressourceSelectionnee = event.getValue();
                if (idRessource != null) {
                    dimensionRpcService.rechercherAgenceParIdRessource(idRessource, new AsyncCallback<IdentifiantLibelleGwt>() {
                        @Override
                        public void onSuccess(IdentifiantLibelleGwt result) {
                            view.getAgence().setValue(result);
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                        }
                    });
                }
            }
        });
        view.getSuggestHandlersDuree().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                if (event.getSuggest() == null || "".equals(event.getSuggest())) {
                    view.activerAjoutAgenda(false);
                    view.getCbAjouterAgenda().setValue(false);
                }
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                dimensionRpcService.rechercherDureeActionParCriteres(criteres, event.getCallBack());
            }
        });
        view.getValueChangeHandlerDateAction().addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                if (hasValuePourAjoutAgenda() && isDatesValides()) {
                    view.activerAjoutAgenda(true);
                }
                else {
                    view.activerAjoutAgenda(false);
                    view.getCbAjouterAgenda().setValue(false);
                }
            }
        });
        view.getValueChangeHandlerHeureAction().addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                if (hasValuePourAjoutAgenda() && isDatesValides()) {
                    view.activerAjoutAgenda(true);
                }
                else {
                    view.activerAjoutAgenda(false);
                    view.getCbAjouterAgenda().setValue(false);
                }
            }
        });
        view.getValueChangeHandlerDuree().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                if (hasValuePourAjoutAgenda() && isDatesValides()) {
                    view.activerAjoutAgenda(true);
                }
                else {
                    view.activerAjoutAgenda(false);
                    view.getCbAjouterAgenda().setValue(false);
                }
            }
        });
        view.getKeyUpHandlerDateAction().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                final int keyCode = event.getNativeKeyCode();
                if (keyCode == KeyCodes.KEY_DELETE || keyCode == KeyCodes.KEY_BACKSPACE) {
                    if (hasValuePourAjoutAgenda() && isDatesValides()) {
                        view.activerAjoutAgenda(true);
                    }
                    else {
                        view.activerAjoutAgenda(false);
                        view.getCbAjouterAgenda().setValue(false);
                    }
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

    /** Modification de l'action source. */
    private void modifierActionSourcePourRelance() {
        if (actionSourceAEnregistrer != null) {
            if (view.getCbFermerPrecedente().getValue()) {
                actionSourceAEnregistrer.setStatut(new IdentifiantLibelleGwt(constantesModel.getIdStatutTerminer()));
            }
            // Appel au service de modification
            actionRpcService.modifierAction(actionSourceAEnregistrer, new AsyncCallback<ActionResultatModel>() {
                @Override
                public void onFailure(Throwable caught) {
                    if (caught instanceof ControleIntegriteExceptionGwt) {
                        final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                        final StringBuffer erreur = new StringBuffer(presenterConstants.erreurActionSource());
                        for (SousRapportModel ssRapport : rapport.getRapports().values()) {
                            if (ssRapport.getMessage() != null) {
                                erreur.append(ssRapport.getMessage() + "<br>");
                            }
                        }
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(erreur.toString()));
                    }
                    else {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught.getMessage() == null ? presenterConstants.erreurSurvenue() : caught.getMessage()));
                    }
                    view.activerBtnCreer(true);
                }

                @Override
                public void onSuccess(ActionResultatModel result) {
                    // Création de l'action liée
                    creerAction();
                }
            });
        }
        else {
            creerAction();
        }
    }

    private void creerAction() {
        // Récupération des valeurs pour la création.
        final ActionCreationModel action = new ActionCreationModel();
        action.setIdPersonne(idPersonne);
        action.setIdActionSource(idActionSource);
        if (view.getCdbDateAction().getValue() != null) {
            action.setDateAction(DateUtil.getString(view.getCdbDateAction().getValue()));
        }
        action.setHeuresAction(view.getTbfHeureAction().getValue());
        if (view.getSlbPriorite().getValue() != null) {
            action.setIdPriorite(view.getSlbPriorite().getValue().getIdentifiant());
        }
        if (view.getSlbNature().getValue() != null) {
            action.setIdNatureAction(view.getSlbNature().getValue().getIdentifiant());
        }
        if (view.getSlbType().getValue() != null) {
            action.setIdType(view.getSlbType().getValue().getIdentifiant());
        }
        if (view.getSlbObjet().getValue() != null) {
            action.setIdObjet(view.getSlbObjet().getValue().getIdentifiant());
        }
        if (view.getSlbSousObjet().getValue() != null) {
            action.setIdSousObjet(view.getSlbSousObjet().getValue().getIdentifiant());
        }
        action.setReclamation(view.getCbReclamation().getValue());
        if (view.getSlbCampagne().getValue() != null) {
            action.setIdCampagne(view.getSlbCampagne().getValue().getIdentifiant());
        }
        action.setRappel(view.getCbRappelNotification().getValue());

        if (view.getLbNotification().getSelectedIndex() != -1) {
            final Long id = Long.valueOf(ListBoxUtil.getValueSelectListe(view.getLbNotification()));
            action.setIdNotificationList(id);
        }
        action.setMePrevenirParMail(view.getCbRappelMail().getValue());

        final IdentifiantLibelleGwt agence = view.getAgence().getValue();
        if (agence != null) {
            action.setIdAgence(agence.getIdentifiant());
        }

        final DimensionRessourceModel commercial = view.getRessourceAgence().getValue();
        if (commercial != null) {
            action.setIdCommercial(commercial.getIdentifiant());
        }
        final IdentifiantLibelleGwt duree = view.getSlbDuree().getValue();
        if (duree != null && duree.getIdentifiant() != null) {
            action.setIdDuree(duree.getIdentifiant());
        }
        action.setVisibleAgenda(view.getCbAjouterAgenda().getValue());

        // Appel du service
        actionRpcService.creerAction(action, new AsyncCallback<ActionCreationModel>() {
            @Override
            public void onSuccess(final ActionCreationModel action) {
                view.hidePopup();
                final PopupInfoConfiguration config =
                    new PopupInfoConfiguration(view.getViewConstants().nofificationActionCreee(), AppControllerConstants.NOTIFICATION_TIME_OUT);
                config.setCallback(new PopupCallback() {

                    @Override
                    public void onResult(boolean result) {
                        // ON AFFICHE LA NOUVELLE ACTION CREE
                        if (action.getIdPersonne() != null) {
                            personneRpcService.rechercherPersonneParId(action.getIdPersonne(), new AsyncCallback<PersonneBaseModel>() {
                                @Override
                                public void onSuccess(PersonneBaseModel result) {
                                    if (result instanceof PersonneModel) {
                                        final PersonneModel personne = (PersonneModel) result;
                                        fireEventGlobalBus(new OpenPersonEvent(action.getIdPersonne(), personne.getNumClient(),
                                            personne.getNom() != null ? personne.getNom() : "", personne.getPrenom() != null ? personne.getPrenom() : "",
                                            (personne.getNaturePersonne() != null) ? personne.getNaturePersonne().getIdentifiant() : null, action
                                                    .getIdentifiant(), filtreIdOpportunite, filtreEidOpportunite));
                                    }
                                    else {
                                        final PersonneMoraleModel personneMorale = (PersonneMoraleModel) result;
                                        fireEventGlobalBus(new OpenPersonneMoraleEvent(action.getIdPersonne(),
                                            (personneMorale.getNature() != null) ? personneMorale.getNature().getIdentifiant() : null, personneMorale
                                                    .getRaisonSociale(), action.getIdentifiant()));
                                    }
                                }

                                @Override
                                public void onFailure(Throwable caught) {
                                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught.getMessage()));
                                }
                            });
                        }
                    }
                });
                new DecoratedInfoPopup(config).show();
                view.activerBtnCreer(true);
                view.clearPopup();
            }

            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                    // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                    view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                    view.masquerLoadingPopup();
                    view.getCdbDateAction().setFocus(true);
                }
                else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
                view.activerBtnCreer(true);
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

        nouvelleAction();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                // on ajoute la popup à la deskBar
                deskBar.addPopup(view.getMinimizablePopup());
            }
        });
    }

    /** Nouvelle fenetre de création. */
    public void nouvelleAction(Long idPersonne, Long idActionSource, Long filtreIdOpportunite, String filtreEidOpportunite,
        ActionModificationModel actionSourcePourRelance, Boolean demanderFermerSource) {
        this.idPersonne = idPersonne;
        this.idActionSource = idActionSource;
        this.filtreIdOpportunite = filtreIdOpportunite;
        this.filtreEidOpportunite = filtreEidOpportunite;
        this.actionSourceAEnregistrer = actionSourcePourRelance;
        this.demanderFermerSource = demanderFermerSource;
        nouvelleAction();
    }

    /** Nouvelle fenetre de création. */
    private void nouvelleAction() {

        // Choix par defaut de la valeur de la priorite
        // Alimentation du label de la personne

        personneRpcService.rechercherPersonneParId(idPersonne, new AsyncCallback<PersonneBaseModel>() {
            @Override
            public void onSuccess(PersonneBaseModel result) {
                if (result instanceof PersonneModel) {
                    final PersonneModel personneModel = (PersonneModel) result;
                    view.getlPersonneAffecte().setText(personneModel.getNom() + " " + personneModel.getPrenom());
                }
                else if (result instanceof PersonneMoraleModel) {
                    final PersonneMoraleModel personneMoraleModel = (PersonneMoraleModel) result;
                    view.getlPersonneAffecte().setText(personneMoraleModel.getRaisonSociale());
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
        // On pré-remplit l'agence et la ressource à partir des informations de la ressource connectée.
        view.getAgence().setValue(ressourceConnectee.getAgence());
        view.getRessourceAgence().setValue(ressourceConnectee);

        view.getCdbDateAction().setValue(new Date());
        view.getCbFermerPrecedente().setValue(false);
        view.getCbRappelMail().setValue(false);
        view.getCbRappelNotification().setValue(false);
        view.getCbReclamation().setValue(false);
        view.getSlbCampagne().setValue(new IdentifiantLibelleGwt());
        view.getSlbNature().setValue(new IdentifiantLibelleGwt());
        view.getSlbObjet().setValue(new IdentifiantLibelleGwt());
        view.getSlbPriorite().setValue(new IdentifiantLibelleGwt());
        view.getSlbSousObjet().setValue(new IdentifiantLibelleGwt());
        view.getSlbType().setValue(new IdentifiantLibelleGwt());
        view.getTbfHeureAction().setValue("");
        view.activerBtnCreer(true);

        // Initialisation de la liste des notifications
        initListesNotification();

        if (idActionSource != null) {
            actionRpcService.rechercherActionParIdentifiant(idActionSource, new AsyncCallback<ActionModel>() {
                @Override
                public void onFailure(Throwable caught) {
                    final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                    // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                    view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                    view.masquerLoadingPopup();
                }

                @Override
                public void onSuccess(ActionModel result) {
                    // CAS PAR EXEMPLE DE L'ANNULATION DU ACTION AVEC OUI AU NIVEAU CREATION D'UNE NOUVELLE
                    if (actionSourceAEnregistrer == null) {
                        actionSourceAEnregistrer = new ActionModificationModel();
                        actionSourceAEnregistrer.setIdAction(result.getIdentifiant());
                        actionSourceAEnregistrer.setDateAction(result.getDateAction());
                        actionSourceAEnregistrer.setHeureAction(result.getHeureAction());
                        actionSourceAEnregistrer.setPriorite(result.getPriorite());
                        actionSourceAEnregistrer.setAgence(result.getAgence());
                        actionSourceAEnregistrer.setRessource(result.getRessource());
                        actionSourceAEnregistrer.setNatureAction(result.getNatureAction());
                        actionSourceAEnregistrer.setType(result.getType());
                        actionSourceAEnregistrer.setObjet(result.getObjet());
                        actionSourceAEnregistrer.setSousObjet(result.getSousObjet());
                        actionSourceAEnregistrer.setCampagne(result.getCampagne());
                        actionSourceAEnregistrer.setIdNotification(result.getIdNotificationList());
                        actionSourceAEnregistrer.setReclamation(result.getReclamation());
                        actionSourceAEnregistrer.setRappel(result.getRappel());
                        actionSourceAEnregistrer.setRappelMail(result.getMePrevenirParMail());
                        actionSourceAEnregistrer.setNatureResultat(result.getNatureResultat());
                        actionSourceAEnregistrer.setStatut(result.getStatut());
                        actionSourceAEnregistrer.setResultat(result.getResultat());
                        chargerActionSource();
                    }
                    else {
                        actionSourceAEnregistrer.setType(result.getType());
                        actionSourceAEnregistrer.setObjet(result.getObjet());
                        chargerActionSource();
                    }
                }
            });
        }

        view.showPopup();

        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                view.getCdbDateAction().setFocus(true);
            }
        });
    }

    /**
     * Permet d'intialiser la liste de notification.
     */
    private void initListesNotification() {
        constantesRpcService.getListeActionNotifications(new AsyncCallback<List<ActionNotificationInfosModel>>() {
            @Override
            public void onSuccess(List<ActionNotificationInfosModel> result) {
                final List<IdentifiantLibelleGwt> resultGwt = new ArrayList<IdentifiantLibelleGwt>();
                for (ActionNotificationInfosModel notification : result) {
                    final IdentifiantLibelleGwt notificationGwt = new IdentifiantLibelleGwt();
                    notificationGwt.setIdentifiant(notification.getId());
                    notificationGwt.setLibelle(notification.getLibelle());
                    resultGwt.add(notificationGwt);
                }
                ListBoxUtil.remplirListe(view.getLbNotification(), resultGwt, true);
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
     * Charge les informations de l'action source dans la popup.
     */
    private void chargerActionSource() {
        view.getCdbDateAction().setValue(DateUtil.getDate(actionSourceAEnregistrer.getDateAction()));
        view.getTbfHeureAction().setValue(actionSourceAEnregistrer.getHeureAction());
        view.getSlbPriorite().setValue(actionSourceAEnregistrer.getPriorite());
        view.getAgence().setValue(actionSourceAEnregistrer.getAgence());
        view.getRessourceAgence().setValue(actionSourceAEnregistrer.getRessource());
        final IdentifiantLibelleGwt natureAction = actionSourceAEnregistrer.getNatureAction();
        if (natureAction != null) {
            view.getSlbNature().setValue(natureAction);
        }
        final IdentifiantLibelleGwt type = actionSourceAEnregistrer.getType();
        if (type != null) {
            view.getSlbType().setValue(type);
        }
        final IdentifiantLibelleGwt objet = actionSourceAEnregistrer.getObjet();
        if (objet != null) {
            view.getSlbObjet().setValue(objet);
        }
        final IdentifiantLibelleGwt ssObjet = actionSourceAEnregistrer.getSousObjet();
        if (ssObjet != null) {
            view.getSlbSousObjet().setValue(actionSourceAEnregistrer.getSousObjet());
        }
        final IdentifiantLibelleGwt campagne = actionSourceAEnregistrer.getCampagne();
        if (campagne != null) {
            view.getSlbCampagne().setValue(campagne);
            view.getSlbCampagne().setEnabled(false);
        }
        final Long idNotification = actionSourceAEnregistrer.getIdNotification();
        if (idNotification != null) {
            ListBoxUtil.setValueSelected(view.getLbNotification(), idNotification.toString());
        }
        if (actionSourceAEnregistrer.getReclamation() != null) {
            view.getCbReclamation().setValue(actionSourceAEnregistrer.getReclamation());
        }
        if (actionSourceAEnregistrer.getRappel() != null) {
            view.getCbRappelNotification().setValue(actionSourceAEnregistrer.getRappel());
        }
        if (actionSourceAEnregistrer.getRappelMail() != null) {
            view.getCbRappelMail().setValue(actionSourceAEnregistrer.getRappelMail());
        }
        // si on doit afficher la chackbox de demande de fermeture de l'action source
        if (demanderFermerSource) {
            view.getCbFermerPrecedente().setVisible(true);
        }
        final IdentifiantLibelleGwt duree = actionSourceAEnregistrer.getDuree();
        if (duree != null) {
            view.getSlbDuree().setValue(duree);
        }
        view.getCbAjouterAgenda().setValue(actionSourceAEnregistrer.getVisibleAgenda());
        if (duree != null && duree.getIdentifiant() != null && actionSourceAEnregistrer.getDateAction() != null
            && !"".equals(actionSourceAEnregistrer.getDateAction()) && actionSourceAEnregistrer.getHeureAction() != null
            && !"".equals(actionSourceAEnregistrer.getHeureAction())) {
            view.activerAjoutAgenda(true);
        } else {
            view.activerAjoutAgenda(false);
        }
        // ON SURCHARGE LES VALUE CHANGE CAR ORDRE INCERTAIN AU NIVEAU DIFFUSION EVENEMENT
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                view.activerObjet(actionSourceAEnregistrer.getType() != null && !"".equals(actionSourceAEnregistrer.getType()));
                view.activerSousObjet(actionSourceAEnregistrer.getObjet() != null && !"".equals(actionSourceAEnregistrer.getObjet()));
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
        if (!view.getCdbDateAction().isDateValide()) {
            rapport.ajoutRapport(presenterConstants.champDateAction(), presenterConstants.erreurDateActionInvalide(), true);
            datesValides = false;
        }
        view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
        if (!datesValides) {
            view.masquerLoadingPopup();
        }
        return datesValides;
    }

    /**
     * Détermine si on peut ajouter la possibiliter d'ajouter l'action dans l'agenda.
     * @return true si on peut ajouter l'action dans l'agenda, false si non.
     */
    private boolean hasValuePourAjoutAgenda() {
        final IdentifiantLibelleGwt duree = view.getSlbDuree().getValue();
        final Date dateAction = view.getCdbDateAction().getValue();
        final String heureAction = view.getTbfHeureAction().getValue();
        return duree != null && duree.getIdentifiant() != null && dateAction != null && heureAction != null && !"".equals(heureAction);
    }

    /**
     * Interface de la vue.
     */
    public interface ActionCreationView extends View {

        /** Affiche la popup. */
        void showPopup();

        /** Masque la popup de chargement. */
        void masquerLoadingPopup();

        /**
         * Affiche la popup d'erreur.
         * @param errorPopupConfiguration la configuration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /** Nettoie la popup. */
        void clearPopup();

        /** Masque la popup. */
        void hidePopup();

        /**
         * Renvoi la valeur de viewConstants.
         * @return viewConstants
         */
        ActionCreationViewImplConstants getViewConstants();

        /**
         * Renvoi la valeur de cdbDateAction.
         * @return cdbDateAction
         */
        DecoratedCalendrierDateBox getCdbDateAction();

        /**
         * Renvoi la valeur de tbfHeureAction.
         * @return tbfHeureAction
         */
        HasValue<String> getTbfHeureAction();

        /**
         * Renvoi la valeur de btnAnnuler.
         * @return btnAnnuler
         */
        HasClickHandlers getBtnAnnuler();

        /**
         * Renvoi la valeur de btnCreer.
         * @return btnCreer
         */
        HasClickHandlers getBtnCreer();

        /**
         * Active le btnCreer.
         * @param activer flag
         */
        void activerBtnCreer(boolean activer);

        /**
         * Renvoi la valeur de iconeErreurChampManager.
         * @return iconeErreurChampManager
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Renvoi la valeur de lPersonneAffecte.
         * @return lPersonneAffecte
         */
        Label getlPersonneAffecte();

        /**
         * Renvoi la valeur de slbNature.
         * @return slbNature
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbNature();

        /**
         * Renvoi la valeur de slbType.
         * @return slbType
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbType();

        /**
         * Renvoi la valeur de slbObjet.
         * @return slbObjet
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbObjet();

        /**
         * Renvoi la valeur de slbSousObjet.
         * @return slbSousObjet
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbSousObjet();

        /**
         * Renvoi la valeur de slbPriorite.
         * @return slbPriorite
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbPriorite();

        /**
         * Renvoi la valeur de cbReclamation.
         * @return cbReclamation
         */
        CheckBox getCbReclamation();

        /**
         * Renvoi la valeur de cbFermerPrecedente.
         * @return cbFermerPrecedente
         */
        CheckBox getCbFermerPrecedente();

        /**
         * Renvoi la valeur de slbCampagne.
         * @return slbCampagne
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbCampagne();

        /**
         * Renvoi la valeur de cbRappelNotification.
         * @return cbRappelNotification
         */
        CheckBox getCbRappelNotification();

        /**
         * Renvoi la valeur de lbNotification.
         * @return lbNotification
         */
        ListBox getLbNotification();

        /**
         * Renvoi la valeur de cbRappelMail.
         * @return cbRappelMail
         */
        CheckBox getCbRappelMail();

        /**
         * Retourne le handler pour la suggestion d'agences.
         * @return handler de suggestion.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestAgence();

        /**
         * Retourne le handler qui gère les changements de valeur d'agence.
         * @return le handler.
         */
        HasValueChangeHandlers<IdentifiantLibelleGwt> getValueChangedHandlerAgence();

        /**
         * Retourne le handler pour la suggestion de ressources.
         * @return handler de suggestion.
         */
        HasSuggestListBoxHandler<DimensionRessourceModel> getSuggestRessourceAgence();

        /**
         * Retourne la valeur de l'agence selectionnée.
         * @return valeur de l'agence selectionnée.
         */
        HasValue<IdentifiantLibelleGwt> getAgence();

        /**
         * Retourne la valeur de la ressource selectionnée.
         * @return valeur de la ressource selectionnée.
         */
        HasValue<DimensionRessourceModel> getRessourceAgence();

        /**
         * Retroune le handler pour la suggestion de ressrouces.
         * @return le handler pour la suggestion de ressrouces
         */
        DecoratedSuggestListBoxSingle<DimensionRessourceModel> getDecoratedSlbsRessourceAgence();

        /**
         * Active / Désactive la sélection d'objet.
         * @param actif true pour activer, false si non.
         */
        void activerObjet(Boolean actif);

        /**
         * Active / Désactive la sélection de sous objet.
         * @param actif true pour activer, false si non.
         */
        void activerSousObjet(Boolean actif);

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

        /**
         * Récupère la valeur sélectionnée pour la durée.
         * @return la valeur sélectionnée de la durée.
         */
        HasValue<IdentifiantLibelleGwt> getSlbDuree();

        /**
         * Récupère la CheckBox pour l'ajout ou non de l'action dans l'agenda.
         * @return la CheckBox.
         */
        HasValue<Boolean> getCbAjouterAgenda();

        /**
         * Récupère le handler de suggestion pour les durées.
         * @return le handler.
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestHandlersDuree();

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
         * Active / désactive l'ajout de l'action dans l'agenda.
         * @param enabled true pour activer, false pour désactiver.
         */
        void activerAjoutAgenda(boolean enabled);

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
        deskBarRegistration.removeHandler();
    }
}

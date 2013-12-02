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
package com.square.composant.tarificateur.square.client.presenter.adhesion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.event.SelectionReferentRoEvent;
import com.square.composant.tarificateur.square.client.event.SelectionReferentRoEventHandler;
import com.square.composant.tarificateur.square.client.event.SuccesMajInfosAdhesionEvent;
import com.square.composant.tarificateur.square.client.exception.ControleIntegriteExceptionGwt;
import com.square.composant.tarificateur.square.client.model.CaisseSimpleModel;
import com.square.composant.tarificateur.square.client.model.ConstantesModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresLienFamilialRechercheModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresRechercheCaisseModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresRechercheModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresRechercheRegimeModel;
import com.square.composant.tarificateur.square.client.model.IdentifiantEidLibelleModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosAdhesionModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosGlobalesAdhesionModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosPaiementModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosPersonneModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosPersonneSyncModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosRibModel;
import com.square.composant.tarificateur.square.client.model.adhesion.RelationAssureSocialModel;
import com.square.composant.tarificateur.square.client.model.personne.AssureSocialModel;
import com.square.composant.tarificateur.square.client.model.personne.InfoSanteModel;
import com.square.composant.tarificateur.square.client.presenter.popup.ajout.assure.PopupAjoutAssurePresenter;
import com.square.composant.tarificateur.square.client.service.DimensionServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurPersonneServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurTransformationAiaServiceGwtAsync;
import com.square.composant.tarificateur.square.client.view.adhesion.InfosAdhesionViewImplConstants;
import com.square.composant.tarificateur.square.client.view.popup.ajout.assure.PopupAjoutAssureViewImpl;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEvent;
import com.square.composants.graphiques.lib.client.event.popups.minimizable.EnableMinimizeWidgetEventHandler;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;
import com.square.composants.graphiques.lib.client.popups.minimizable.IsMinimizable;

/**
 * Presenter pour la vue de saisie des informations supplémentaires nécessaires à l'adhésion.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class InfosAdhesionPresenter extends Presenter {

    /** Vue. */
    private InfosAdhesionView view;

    /** Vue de la personne principale. */
    private BlocInfoSanteComplete blocPersonnePrincipaleView;

    /** Vue du conjoint. */
    private BlocInfoSanteComplete blocConjointView;

    /** Vues associées aux enfants. */
    private Map<Long, BlocInfoSanteSimple> mapBlocsEnfantsViews;

    /** Services Tarificateur. */
    private TarificateurServiceGwtAsync tarificateurService;

    /** Services Tarificateur. */
    private TarificateurTransformationAiaServiceGwtAsync tarificateurTransformationAiaService;

    /** Flag qui indique si la transformation vers Aia doit etre lancée. */
    private boolean lancerTransformationAia;

    /** Informations d'adhésion. */
    private InfosAdhesionModel infosAdhesion;

    /** Informations de la personne principale. */
    private InfosPersonneModel infosPersonnePrincipale;

    /** Informations du conjoint. */
    private InfosPersonneModel infosConjoint;

    /** Liste des assures sociaux. */
    private List<InfosPersonneModel> listeEnfants;

    /** Liste des assures sociaux. */
    private List<AssureSocialModel> listeAssuresSociaux;

    /** Constantes de l'application. */
    private ConstantesModel constantesApp;

    private DimensionServiceGwtAsync dimensionService;

    private TarificateurPersonneServiceGwtAsync tarificateurPersonneService;

    private SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt> regimeSuggestEventHandler;

    /** Indique si les informations d'adhésion sont éditables ou non. */
    private boolean isInfoAdhesionEditable;

    private Long idDepartement;

    private Long idDevis;

    /** Rapport d'erreurs pour les infos d'adhésion. */
    private RapportModel rapportErreurs;

    /** Constantes du presenter. */
    private InfosAdhesionPresenterConstants presenterConstants;

    private DeskBar deskBar;

    private HandlerRegistration registerDeskbar;

    private PopupAjoutAssurePresenter popupAjoutAssurePresenter;

    /**
     * Constructeur.
     * @param eventBus Bus de transport des évènements.
     * @param view vue associée au presenter.
     * @param tarificateurService services tarificateur square.
     * @param tarificateurTransformationAiaService services tarificateur square transformation aia.
     * @param constantesApp constantes de l'application.
     * @param dimensionService dimension service
     * @param tarificateurPersonneService tarificateurPersonneService
     * @param deskBar deskBar
     */
    public InfosAdhesionPresenter(HandlerManager eventBus, InfosAdhesionView view, TarificateurServiceGwtAsync tarificateurService,
        TarificateurTransformationAiaServiceGwtAsync tarificateurTransformationAiaService, ConstantesModel constantesApp,
        DimensionServiceGwtAsync dimensionService, TarificateurPersonneServiceGwtAsync tarificateurPersonneService, DeskBar deskBar, Long idDevis,
        boolean isInfoAdhesionEditable, Long idDepartement, boolean lancerTransformationAia, RapportModel rapportErreurs) {
        super(eventBus);
        this.infosAdhesion = new InfosAdhesionModel();
        this.infosAdhesion.setIdDevis(idDevis);
        this.lancerTransformationAia = lancerTransformationAia;
        this.isInfoAdhesionEditable = isInfoAdhesionEditable;
        this.idDepartement = idDepartement;
        this.idDevis = idDevis;
        this.tarificateurService = tarificateurService;
        this.tarificateurTransformationAiaService = tarificateurTransformationAiaService;
        this.constantesApp = constantesApp;
        this.view = view;
        this.mapBlocsEnfantsViews = new HashMap<Long, BlocInfoSanteSimple>();
        this.dimensionService = dimensionService;
        this.tarificateurPersonneService = tarificateurPersonneService;
        this.deskBar = deskBar;
        this.rapportErreurs = rapportErreurs;
        this.presenterConstants = GWT.create(InfosAdhesionPresenterConstants.class);
        this.listeEnfants = new ArrayList<InfosPersonneModel>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View asView() {
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBind() {
        // Gestion des touches de la popup
        view.getAllKeyHandlersFocusPanel().addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    updateInfosAdhesion();
                } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    view.hide();
                }
            }
        });
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.hide();
            }
        });
        view.getBtnEnregistrer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                updateInfosAdhesion();
            }
        });

        regimeSuggestEventHandler = new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheRegimeModel criteres = new DimensionCriteresRechercheRegimeModel();
                final DimensionCriteresRechercheModel dimensionCriteres = new DimensionCriteresRechercheModel();
                dimensionCriteres.setMaxResults(ComposantTarificateurConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                dimensionCriteres.setLibelle(event.getSuggest());
                dimensionCriteres.setVisible(true);
                criteres.setDimensionCriteres(dimensionCriteres);
                dimensionService.rechercherRegimeParCriteres(criteres, event.getCallBack());
            }
        };

        view.getModesPaimentSuggestHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final IdentifiantLibelleGwt criteres = new IdentifiantLibelleGwt();
                criteres.setLibelle(event.getSuggest());
                tarificateurService.rechercherModesPaiementParCriteres(criteres, event.getCallBack());
            }
        });
        view.getPeriodicitesPaiementSuggestHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final IdentifiantLibelleGwt criteres = new IdentifiantLibelleGwt();
                criteres.setLibelle(event.getSuggest());
                tarificateurService.rechercherPeriodicitesPaiementParCriteres(criteres, event.getCallBack());
            }
        });
        view.getJoursPaiementSuggestHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final IdentifiantLibelleGwt criteres = new IdentifiantLibelleGwt();
                criteres.setLibelle(event.getSuggest());
                tarificateurService.rechercherJoursPaiementParCriteres(criteres, event.getCallBack());
            }
        });

        registerDeskbar = deskBar.getEventBus().addHandler(EnableMinimizeWidgetEvent.TYPE, new EnableMinimizeWidgetEventHandler() {
            @Override
            public void enableMinimizeWidget(EnableMinimizeWidgetEvent event) {
                view.activerBoutonReduire(event.isEnabled());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onShow(HasWidgets container) {
        recupererInfosAdhesion();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                // on ajoute la popup à la deskBar
                deskBar.addPopup(view.getMinimizablePopup());
            }
        });
    }

    public void afficherAdhesion(boolean lancerTransfoAiaEnsuite, boolean isInfoAdhesionEditable, Long idDepartement, RapportModel rapportErreurs,
        boolean rechargerInfosAdhesion) {
        this.lancerTransformationAia = lancerTransfoAiaEnsuite;
        this.isInfoAdhesionEditable = isInfoAdhesionEditable;
        this.idDepartement = idDepartement;
        this.rapportErreurs = rapportErreurs;
        if (rechargerInfosAdhesion) {
            view.viderPersonnes(infosAdhesion.getInfosPersonnes().size());
            view.viderAssuresSociaux(listeAssuresSociaux.size());
            recupererInfosAdhesion();
        } else {
            view.show();
            // On affiche les erreurs s'il y en a
            if (rapportErreurs != null) {
                // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapportErreurs, true));
            }
        }
    }

    public void fermerAdhesion() {
        view.hide();
    }

    /**
     * Charge les infos d'adhésion.
     */
    private void recupererInfosAdhesion() {
        view.onRpcCall(new LoadingPopupConfiguration(ComposantTarificateur.CONSTANTS.chargementInfosAdhesionEnCours()));
        final AsyncCallback<InfosGlobalesAdhesionModel> callback = new AsyncCallback<InfosGlobalesAdhesionModel>() {
            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(InfosGlobalesAdhesionModel result) {
                infosAdhesion = result.getInfosAdhesionModel();
                listeAssuresSociaux = result.getListeAssuresSociaux();

                chargerInfosAdhesion();
                view.onRpcSuccess();

                if (blocPersonnePrincipaleView != null) {
                    DeferredCommand.addCommand(new Command() {
                        @Override
                        public void execute() {
                            blocPersonnePrincipaleView.initFocus();
                        }
                    });
                }
            }
        };
        // suivant le processus on appelle le service correspondant
        if (lancerTransformationAia) {
            tarificateurTransformationAiaService.getInfosAdhesion(infosAdhesion.getIdDevis(), callback);
        } else {
            tarificateurService.getInfosAdhesion(infosAdhesion.getIdDevis(), callback);
        }
    }

    /**
     * Charge les informations d'adhésion dans la vue.
     */
    private void chargerInfosAdhesion() {
        view.chargerInfosRib(infosAdhesion.getInfosRib());

        final View[] tabVuesOrdonnees = new View[infosAdhesion.getInfosPersonnes().size()];
        int position = 0;
        // on charge d'abord les personnes qui sont leurs propres referents
        for (final InfosPersonneModel infosPersonneParcourue : infosAdhesion.getInfosPersonnes()) {
            if (infosPersonneParcourue.getInfoSante() == null
                || (infosPersonneParcourue.getInfoSante() != null && (infosPersonneParcourue.getInfoSante().getIdReferent() == null || (infosPersonneParcourue
                        .getInfoSante().getIdReferent() != null && infosPersonneParcourue.getInfoSante().getIdReferent().equals(infosPersonneParcourue.getId()))))) {
                final View vuePersonne = chargerPersonne(infosPersonneParcourue);
                tabVuesOrdonnees[position] = vuePersonne;
            }
            position++;
        }

        // on charge ensuite ceux qui ont des referents
        position = 0;
        for (final InfosPersonneModel infosPersonneParcourue : infosAdhesion.getInfosPersonnes()) {
            if (infosPersonneParcourue.getInfoSante() != null && infosPersonneParcourue.getInfoSante().getIdReferent() != null
                && !infosPersonneParcourue.getInfoSante().getIdReferent().equals(infosPersonneParcourue.getId())) {
                final View vuePersonne = chargerPersonne(infosPersonneParcourue);
                tabVuesOrdonnees[position] = vuePersonne;
            }
            position++;
        }

        // on affiche les blocs
        position = 0;
        for (View vue : tabVuesOrdonnees) {
            if (vue != null) {
                view.ajouterBlocInfoSante(vue, position);
                position++;
            }
        }

        // on charge les infos des assurés sociaux
        view.afficherAssuresSociaux(listeAssuresSociaux);

        // on charge les autres infos
        view.chargerInfosPaiement(infosAdhesion.getInfosPaiement());
        if (infosAdhesion.getDateClicBA() != null && infosAdhesion.getDateTelechargementBA() != null) {
            view.chargerInfosSignatureNumerique(infosAdhesion.getDateClicBA(), infosAdhesion.getDateTelechargementBA());
        }
        view.getTeletransmission().setValue(infosAdhesion.getTeletransmission() != null ? infosAdhesion.getTeletransmission().booleanValue() : Boolean.FALSE);
        if (!isInfoAdhesionEditable) {
            view.setInfosAdhesionEditables(isInfoAdhesionEditable);
        }

        // On affiche les erreurs s'il y en a
        if (rapportErreurs != null) {
            // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
            view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapportErreurs, true));
        }

        view.show();
    }

    private View chargerPersonne(InfosPersonneModel infosPersonneParcourue) {
        final Long idLienFamilial = infosPersonneParcourue.getLienFamilial() != null ? infosPersonneParcourue.getLienFamilial().getIdentifiant() : null;
        if (idLienFamilial == null) {
            // toujours en 1ere position
            blocPersonnePrincipaleView = view.creerBlocInfoSanteComplete("");
            infosPersonnePrincipale = infosPersonneParcourue;
            final String captionText =
                infosPersonneParcourue.getPrenom() + InfosAdhesionViewImplConstants.ESPACE + infosPersonneParcourue.getNom()
                    + InfosAdhesionViewImplConstants.ESPACE + infosPersonneParcourue.getNumClient();
            initialiserBlocInfoSanteSimple(blocPersonnePrincipaleView, infosPersonneParcourue, captionText);
            return blocPersonnePrincipaleView;
        } else if (idLienFamilial.equals(constantesApp.getIdLienFamilialConjoint())) {
            // 1ere position si pas encore personne principale, sinon 2eme
            blocConjointView = view.creerBlocInfoSanteComplete("-" + presenterConstants.conjoint());
            infosConjoint = infosPersonneParcourue;
            final String captionText =
                infosPersonneParcourue.getPrenom() + InfosAdhesionViewImplConstants.ESPACE + infosPersonneParcourue.getNom()
                    + InfosAdhesionViewImplConstants.ESPACE + infosPersonneParcourue.getNumClient() + InfosAdhesionViewImplConstants.ESPACE
                    + view.getViewConstants().labelConjoint();
            initialiserBlocInfoSanteSimple(blocConjointView, infosPersonneParcourue, captionText);
            return blocConjointView;
        } else if (idLienFamilial != null && idLienFamilial.equals(constantesApp.getIdLienFamilialEnfant())) {
            final BlocInfoSanteSimple viewEnfant = view.creerBlocInfoSanteSimple("-" + presenterConstants.enfant() + "" + infosPersonneParcourue.getId());
            mapBlocsEnfantsViews.put(infosPersonneParcourue.getId(), viewEnfant);
            listeEnfants.add(infosPersonneParcourue);
            final String captionText =
                infosPersonneParcourue.getPrenom() + InfosAdhesionViewImplConstants.ESPACE + infosPersonneParcourue.getNom()
                    + InfosAdhesionViewImplConstants.ESPACE + infosPersonneParcourue.getNumClient() + InfosAdhesionViewImplConstants.ESPACE
                    + view.getViewConstants().labelEnfant();
            initialiserBlocInfoSanteSimple(viewEnfant, infosPersonneParcourue, captionText);
            return viewEnfant;
        }
        return null;
    }

    private void initialiserBlocInfoSanteSimple(final BlocInfoSanteSimple bloc, final InfosPersonneModel infosPersonne, String captionText) {
        // on recupere la reference
        final IdentifiantEidLibelleModel referenceSS = recupererReferent(infosPersonne);

        bloc.chargerInfos(infosPersonne, referenceSS, captionText);

        // on selectionne la reference et on met a jour les champs de saisie
        if (referenceSS != null && referenceSS.getIdentifiant() != null) {
            updateInfosSs(bloc, referenceSS != null ? referenceSS.getIdentifiant() : null);
        }

        if (!isInfoAdhesionEditable) {
            bloc.setInfosAdhesionEditables(isInfoAdhesionEditable);
        }

        final ValueChangeHandler<String> repercuteReferenceSsHandler = genererRepercuteReferenceSsHandler(bloc, infosPersonne.getId());
        bloc.getNumSsValueChangeHandler().addValueChangeHandler(repercuteReferenceSsHandler);
        bloc.getCleSsValueChangeHandler().addValueChangeHandler(repercuteReferenceSsHandler);

        final KeyPressHandler restrictionTouchesHandler = genererRestrictionTouchesHandler(bloc);
        bloc.getNumSsKeyPressHandler().addKeyPressHandler(restrictionTouchesHandler);
        bloc.getCleSsKeyPressHandler().addKeyPressHandler(restrictionTouchesHandler);

        bloc.getRegimeSuggestHandler().addSuggestHandler(regimeSuggestEventHandler);
        bloc.getCaisseSuggestHandler().addSuggestHandler(getCaisseSuggestEventHandler(bloc.getRegime()));

        bloc.getRegimeValueChangeHandler().addValueChangeHandler(genererRegimeValueChangeHandler(bloc, infosPersonne));
        bloc.getCaisseValueChangeHandler().addValueChangeHandler(genererCaisseValueChangeHandler(bloc, infosPersonne.getId()));

        // handler pour proposer les numeros de sécu disponibles
        bloc.getReferentSuggestHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantEidLibelleModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantEidLibelleModel> event) {
                event.getCallBack().onSuccess(construireListeReferencesSs(infosPersonne.getId()));
            }
        });
        bloc.getRelationSuggestHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresLienFamilialRechercheModel criteres = new DimensionCriteresLienFamilialRechercheModel();
                final List<Long> listeIds = new ArrayList<Long>();
                listeIds.add(constantesApp.getIdLienFamilialConjoint());
                listeIds.add(constantesApp.getIdLienFamilialEnfant());
                criteres.setListeIds(listeIds);
                criteres.setLibelle(event.getSuggest());
                dimensionService.rechercherLiensFamiliauxParCriteres(criteres, event.getCallBack());
            }
        });

        // handler pour gerer le numero et clé de sécu suivant le choix dans la listbox et la relation avec l'assure social
        // on déclenche l'évenement artificiellement si un assure social est defini
        final IdentifiantEidLibelleModel referent = bloc.getReferent().getValue();
        if (referenceSS != null && referenceSS.getIdentifiant() != null) {
            bloc.getReferent().setValue(null);
        }
        bloc.getReferentValueChangeHandler().addValueChangeHandler(genererReferentValueChangeHandler(bloc, infosPersonne));
        if (referenceSS != null && referenceSS.getIdentifiant() != null) {
            bloc.getReferent().setValue(referent);
        }
    }

    /**
     * Recupere la reference suivant la personne parcourue.
     * @param infoSante la personne parcourue
     * @return la reference
     */
    private IdentifiantEidLibelleModel recupererReferent(InfosPersonneModel infosPersonne) {
        final InfoSanteModel infoSante = infosPersonne != null ? infosPersonne.getInfoSante() : null;
        // si pas d'infos santé
        if (infoSante == null || infoSante.getIdReferent() == null) {
            if (infosPersonne != null && infosPersonnePrincipale != null && !infosPersonne.getId().equals(infosPersonnePrincipale.getId())) {
                return construireReferent(infosPersonnePrincipale);
            } else {
                return new IdentifiantEidLibelleModel(null, view.getViewConstants().labelSaisieInfosSecuriteSociale());
            }
        }
        // si la personne est son propre réferent
        else if (infoSante.getIdReferent() != null && infoSante.getIdReferent().equals(infosPersonne.getId())) {
            return new IdentifiantEidLibelleModel(null, view.getViewConstants().labelSaisieInfosSecuriteSociale());
        }
        // si la personne a un referent
        else if (infoSante.getIdReferent() != null && !infoSante.getIdReferent().equals(infosPersonne.getId())) {
            if (infosPersonnePrincipale.getId().equals(infoSante.getIdReferent()) && infosPersonnePrincipale.getInfoSante() != null) {
                return construireReferent(infosPersonnePrincipale);
            }
            if (infosConjoint != null && infosConjoint.getId().equals(infoSante.getIdReferent()) && infosConjoint.getInfoSante() != null) {
                return construireReferent(infosConjoint);
            }
            // on verifie dans les enfants
            if (listeEnfants != null && listeEnfants.size() > 0) {
                for (InfosPersonneModel enfant : listeEnfants) {
                    if (enfant.getId().equals(infoSante.getIdReferent())) {
                        return construireReferent(enfant);
                    }
                }
            }
            // on verifie dans les assures sociaux
            if (listeAssuresSociaux != null && listeAssuresSociaux.size() > 0) {
                for (AssureSocialModel assureSocial : listeAssuresSociaux) {
                    if (assureSocial.getId().equals(infoSante.getIdReferent())) {
                        return construireReferenceSS(assureSocial);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Met à jour les informations de sécurité sociale.
     * @param bloc la vue à mettre à jour.
     */
    private void updateInfosSs(final BlocInfoSanteSimple bloc, final Long identifiant) {
        if (identifiant == null) {
            bloc.activerSaisieInfos(true);
            bloc.getNumeroSecuriteSociale().setValue("");
            bloc.getCleSecuriteSociale().setValue("");
            bloc.getCaisse().setValue(null);
            bloc.getRegime().setValue(null);
        }
        // personne principale sélectionnée
        else if (infosPersonnePrincipale.getId().equals(identifiant)) {
            bloc.activerSaisieInfos(false);
            bloc.getNumeroSecuriteSociale().setValue(blocPersonnePrincipaleView.getNumeroSecuriteSociale().getValue());
            bloc.getCleSecuriteSociale().setValue(blocPersonnePrincipaleView.getCleSecuriteSociale().getValue());
            bloc.getRegime().setValue(blocPersonnePrincipaleView.getRegime().getValue());
            bloc.getCaisse().setValue(blocPersonnePrincipaleView.getCaisse().getValue());
        }
        // conjoint sélectionné
        else if (infosConjoint != null && infosConjoint.getId().equals(identifiant)) {
            bloc.activerSaisieInfos(false);
            bloc.getNumeroSecuriteSociale().setValue(blocConjointView.getNumeroSecuriteSociale().getValue());
            bloc.getCleSecuriteSociale().setValue(blocConjointView.getCleSecuriteSociale().getValue());
            bloc.getRegime().setValue(blocConjointView.getRegime().getValue());
            bloc.getCaisse().setValue(blocConjointView.getCaisse().getValue());
        }
        // on verifie dans les enfant
        else if (listeEnfants != null && listeEnfants.size() > 0 && mapBlocsEnfantsViews.containsKey(identifiant)) {
            final BlocInfoSanteSimple blocEnfant = mapBlocsEnfantsViews.get(identifiant);
            bloc.activerSaisieInfos(false);
            bloc.getNumeroSecuriteSociale().setValue(blocEnfant.getNumeroSecuriteSociale().getValue());
            bloc.getCleSecuriteSociale().setValue(blocEnfant.getCleSecuriteSociale().getValue());
            bloc.getRegime().setValue(blocEnfant.getRegime().getValue());
            bloc.getCaisse().setValue(blocEnfant.getCaisse().getValue());
        }
        // on verifie dans les assures sociaux
        else if (listeAssuresSociaux != null && listeAssuresSociaux.size() > 0) {
            for (AssureSocialModel assureSocial : listeAssuresSociaux) {
                if (assureSocial.getId().equals(identifiant)) {
                    bloc.activerSaisieInfos(false);
                    if (assureSocial.getInfoSante() != null) {
                        bloc.getNumeroSecuriteSociale().setValue(assureSocial.getInfoSante().getNumSecuriteSocial());
                        bloc.getCleSecuriteSociale().setValue(assureSocial.getInfoSante().getCleSecuriteSocial());
                        bloc.getRegime().setValue(assureSocial.getInfoSante().getRegime());
                        bloc.getCaisse().setValue(assureSocial.getInfoSante().getCaisse());
                    }
                    break;
                }
            }
        }
    }

    /**
     * Met à jour les infos d'adhésion.
     */
    private void updateInfosAdhesion() {
        // On vérifie que les dates sont valides
        if (isDatesValides()) {
            // On récupère les informations saisies pour mettre à jour le modèle
            // On met à jour les infos de paiement
            infosAdhesion.getInfosPaiement().setJourPaiement(view.getJourPaiement().getValue());
            infosAdhesion.getInfosPaiement().setMoyenPaiement(view.getMoyenPaiement().getValue());
            infosAdhesion.getInfosPaiement().setPeriodicitePaiement(view.getPeriodicitePaiement().getValue());
            infosAdhesion.getInfosPaiement().setDateSignature(view.getDateSignature());

            final InfosRibModel infosRib = new InfosRibModel();
            infosRib.setCodeBanque(view.getRibCodeBanque().getValue());
            infosRib.setCodeGuichet(view.getRibCodeGuichet().getValue());
            infosRib.setCodeCompte(view.getRibCodeCompte().getValue());
            infosRib.setCle(view.getRibCle().getValue());
            if (infosRib.getCodeBanque().equals("") && infosRib.getCodeGuichet().equals("") && infosRib.getCodeCompte().equals("")
                && infosRib.getCle().equals("")) {
                infosAdhesion.setInfosRib(null);
            } else {
                infosAdhesion.setInfosRib(infosRib);
            }

            // On met à jour les infos des personnes
            for (final InfosPersonneModel infosPersonneParcourue : infosAdhesion.getInfosPersonnes()) {
                if (infosPersonneParcourue.getInfoSante() == null) {
                    infosPersonneParcourue.setInfoSante(new InfoSanteModel());
                }
                if (infosPersonneParcourue.getLienFamilial() == null) {
                    // On récupère les informations de la personne principale
                    remplirInfosPersonneSimple(infosPersonneParcourue, blocPersonnePrincipaleView);
                    remplirInfosPersonneComplete(infosPersonneParcourue, blocPersonnePrincipaleView);
                } else if (infosPersonneParcourue.getLienFamilial().getIdentifiant().equals(constantesApp.getIdLienFamilialConjoint())) {
                    // On récupère les informations du conjoint de la personne principale
                    remplirInfosPersonneSimple(infosPersonneParcourue, blocConjointView);
                    remplirInfosPersonneComplete(infosPersonneParcourue, blocConjointView);
                } else if (infosPersonneParcourue.getLienFamilial().getIdentifiant().equals(constantesApp.getIdLienFamilialEnfant())) {
                    // On récupère les informations de l'enfant de la personne principale
                    final BlocInfoSanteSimple viewEnfant = mapBlocsEnfantsViews.get(infosPersonneParcourue.getId());
                    remplirInfosPersonneSimple(infosPersonneParcourue, viewEnfant);
                }
            }
            infosAdhesion.setTeletransmission(view.getTeletransmission().getValue());

            // Suppression du rapport d'erreurs
            rapportErreurs = null;

            view.onRpcCall(new LoadingPopupConfiguration(ComposantTarificateur.CONSTANTS.updateInfosAdhesionEnCours()));
            tarificateurService.updateInfosAdhesion(infosAdhesion, new AsyncCallback<Void>() {
                @Override
                public void onFailure(Throwable caught) {
                    if (caught instanceof ControleIntegriteExceptionGwt) {
                        final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                        // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                        view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport, true));
                        view.onRpcServiceFailure(null);
                        blocPersonnePrincipaleView.initFocus();
                    } else {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                    }
                }

                @Override
                public void onSuccess(Void result) {
                    // On envoie un event qui notifie que les infos des personnes ont été mises à jour
                    final List<InfosPersonneSyncModel> listePersonnesSync = new ArrayList<InfosPersonneSyncModel>();
                    for (InfosPersonneModel personneParcourue : infosAdhesion.getInfosPersonnes()) {
                        // on ne synchronise que les infos santé pointant sur des personnes square (pas d'assuré social)
                        if (personneParcourue.getInfoSante().getEidReferent() != null) {
                            final InfosPersonneSyncModel personneSync = new InfosPersonneSyncModel();
                            personneSync.setEidPersonne(personneParcourue.getEidPersonne());
                            personneSync.setInfoSante(personneParcourue.getInfoSante());
                            listePersonnesSync.add(personneSync);
                        }
                    }
                    view.onRpcSuccess();
                    fireEventLocalBus(new SuccesMajInfosAdhesionEvent(listePersonnesSync, infosAdhesion.getInfosPaiement(), lancerTransformationAia,
                        infosAdhesion.getIdDevis()));
                    if (!lancerTransformationAia) {
                        view.hide();
                    }
                }
            });
        }
    }

    private void remplirInfosPersonneSimple(InfosPersonneModel infosPersonne, BlocInfoSanteSimple bloc) {
        infosPersonne.getInfoSante().setNumSecuriteSocial(bloc.getNumeroSecuriteSociale().getValue());
        infosPersonne.getInfoSante().setCleSecuriteSocial(bloc.getCleSecuriteSociale().getValue());
        infosPersonne.getInfoSante().setRegime(bloc.getRegime().getValue());
        infosPersonne.getInfoSante().setCaisse(bloc.getCaisse().getValue());
        if (bloc.getReferent().getValue() != null) {
            // si on est sur une saisie manuelle
            if (bloc.getReferent().getValue().getIdentifiant() == null) {
                infosPersonne.getInfoSante().setIdReferent(infosPersonne.getId());
                infosPersonne.getInfoSante().setEidReferent(infosPersonne.getEidPersonne());
            } else {
                // mettre l'id de la personne referente
                infosPersonne.getInfoSante().setIdReferent(bloc.getReferent().getValue().getIdentifiant());
                infosPersonne.getInfoSante().setEidReferent(bloc.getReferent().getValue().getIdentifiantExterieur());
            }
        }
        // si on est pas sur une saisie libre et que la relation est renseignée
        if (bloc.getRelation().getValue() != null) {
            infosPersonne.setTypeRelationAssureSocial(bloc.getRelation().getValue());
        }
    }

    private void remplirInfosPersonneComplete(InfosPersonneModel infosPersonne, BlocInfoSanteComplete bloc) {
        infosPersonne.setTravailleurNonSalarie(bloc.getTns().getValue());
        infosPersonne.setLoiMadelin(bloc.getLoiMadelin().getValue());
        infosPersonne.setActuellementCouvert(bloc.getCouvertActuellement().getValue());
        infosPersonne.setCouvertSixDerniersMois(bloc.getCouvert6DerniersMois().getValue());
    }

    /**
     * Construit la liste avec les informations de securité sociale des parents.
     * @return la liste construite.
     */
    private List<IdentifiantEidLibelleModel> construireListeReferencesSs(Long idPersonne) {
        final List<IdentifiantEidLibelleModel> listeReferencesSs = new ArrayList<IdentifiantEidLibelleModel>();
        listeReferencesSs.add(new IdentifiantEidLibelleModel(null, view.getViewConstants().labelSaisieInfosSecuriteSociale()));
        // on ajoute si il s'agit pas de la même personne et que la personne est son propre référent
        if (infosPersonnePrincipale != null && !infosPersonnePrincipale.getId().equals(idPersonne)
            && (blocPersonnePrincipaleView.getReferent().getValue() == null || blocPersonnePrincipaleView.getReferent().getValue().getIdentifiant() == null)) {
            listeReferencesSs.add(construireReferent(infosPersonnePrincipale));
        }
        if (infosConjoint != null && !infosConjoint.getId().equals(idPersonne)
            && (blocConjointView.getReferent().getValue() == null || blocConjointView.getReferent().getValue().getIdentifiant() == null)) {
            listeReferencesSs.add(construireReferent(infosConjoint));
        }
        for (InfosPersonneModel enfant : listeEnfants) {
            if (!idPersonne.equals(enfant.getId())
                && (mapBlocsEnfantsViews.get(enfant.getId()).getReferent().getValue() == null || mapBlocsEnfantsViews.get(enfant.getId()).getReferent()
                        .getValue().getIdentifiant() == null)) {
                listeReferencesSs.add(construireReferent(enfant));
            }
        }
        for (AssureSocialModel assureSocial : listeAssuresSociaux) {
            listeReferencesSs.add(construireReferenceSS(assureSocial));
        }
        listeReferencesSs.add(new IdentifiantEidLibelleModel(InfosAdhesionPresenterConstants.ID_AJOUT_ASSURE_SOCIAL, view.getViewConstants()
                .labelAjoutAssureSocial()));
        return listeReferencesSs;
    }

    /**
     * Construit une reference SS.
     * @return la reference
     */
    private IdentifiantEidLibelleModel construireReferent(InfosPersonneModel infosPersonne) {
        String numeroSecu = "";
        String cleSecu = "";
        // on recupere le numero de secu dans les champs de saisie
        if (infosPersonne.getId().equals(infosPersonnePrincipale.getId())) {
            numeroSecu = blocPersonnePrincipaleView.getNumeroSecuriteSociale().getValue();
            cleSecu = blocPersonnePrincipaleView.getCleSecuriteSociale().getValue();
        } else if (infosConjoint != null && infosPersonne.getId().equals(infosConjoint.getId())) {
            numeroSecu = blocConjointView.getNumeroSecuriteSociale().getValue();
            cleSecu = blocConjointView.getCleSecuriteSociale().getValue();
        } else if (mapBlocsEnfantsViews.containsKey(infosPersonne.getId())) {
            numeroSecu = mapBlocsEnfantsViews.get(infosPersonne.getId()).getNumeroSecuriteSociale().getValue();
            cleSecu = mapBlocsEnfantsViews.get(infosPersonne.getId()).getCleSecuriteSociale().getValue();
        } else {
            return null;
        }

        final IdentifiantEidLibelleModel refSS = new IdentifiantEidLibelleModel();
        refSS.setIdentifiant(infosPersonne.getId());
        refSS.setIdentifiantExterieur(infosPersonne.getEidPersonne());
        refSS.setLibelle(infosPersonne.getPrenom() + " (" + numeroSecu + " " + cleSecu + ")");
        return refSS;
    }

    /**
     * Construit une reference SS.
     * @return la reference
     */
    private IdentifiantEidLibelleModel construireReferenceSS(AssureSocialModel assureSocial) {
        final IdentifiantEidLibelleModel refSS = new IdentifiantEidLibelleModel();
        refSS.setIdentifiant(assureSocial.getId());
        refSS.setIdentifiantExterieur(assureSocial.getEidPersonne());
        final String numSecu = assureSocial.getInfoSante() != null ? assureSocial.getInfoSante().getNumSecuriteSocial() : "";
        final String cleSecu = assureSocial.getInfoSante() != null ? assureSocial.getInfoSante().getCleSecuriteSocial() : "";
        refSS.setLibelle(assureSocial.getPrenom() + " (" + numSecu + " " + cleSecu + ")");
        return refSS;
    }

    private void chargerAssuresSociaux(final Command command) {
        view.onRpcCall(new LoadingPopupConfiguration(view.getViewConstants().recuperationAssuresSociaux()));
        if (listeAssuresSociaux != null) {
            view.viderAssuresSociaux(listeAssuresSociaux.size());
        }
        tarificateurPersonneService.getListeAssuresSociaux(idDevis, new AsyncCallback<List<AssureSocialModel>>() {
            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(List<AssureSocialModel> result) {
                view.onRpcSuccess();
                listeAssuresSociaux = result;
                view.afficherAssuresSociaux(listeAssuresSociaux);

                if (command != null) {
                    command.execute();
                }
            }
        });
    }

    private SuggestListBoxSuggestEventHandler<CaisseSimpleModel> getCaisseSuggestEventHandler(final HasValue<IdentifiantLibelleGwt> regimeAssocie) {
        return new SuggestListBoxSuggestEventHandler<CaisseSimpleModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<CaisseSimpleModel> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setMaxResults(ComposantTarificateurConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                criteres.setLibelle(event.getSuggest());
                final DimensionCriteresRechercheCaisseModel criteresCaisse = new DimensionCriteresRechercheCaisseModel();
                criteresCaisse.setDimensionCriteres(criteres);
                criteresCaisse.setIdRegime(regimeAssocie.getValue() != null ? regimeAssocie.getValue().getIdentifiant() : null);
                dimensionService.rechercherCaisseParCriteres(criteresCaisse, event.getCallBack());
            }
        };
    }

    /**
     * Teste la validité des dates.
     * @return true si les dates sont valides, false sinon
     */
    private boolean isDatesValides() {
        boolean datesValides = true;
        final RapportModel rapport = new RapportModel();
        if (!view.getCdbDateSignature().isDateValide()) {
            rapport.ajoutRapport(presenterConstants.champDateSignature(), presenterConstants.erreurDateSignatureInvalide(), true);
            datesValides = false;
        }
        view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
        if (!datesValides) {
            view.masquerLoadingPopup();
        }
        return datesValides;
    }

    /**
     * Détermine s'il s'agit d'un A ou d'un B spécifique à la Corse.
     * @param keyCode le caractère.
     * @return true si le caractère de la Corse, false si non.
     */
    private boolean isCharCorse(char keyCode, BlocInfoSanteSimple bloc) {
        final int charPos = bloc.getNumeroSecuriteSociale().getValue().length();
        return charPos == 6 && (keyCode == 'A' || keyCode == 'B' || keyCode == 'a' || keyCode == 'b');
    }

    private ValueChangeHandler<IdentifiantLibelleGwt> genererRegimeValueChangeHandler(final BlocInfoSanteSimple bloc, final InfosPersonneModel infosPersonne) {
        return new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                // met à jour la caisse suivant le regime, si champ actif
                if (bloc.isChampRegimeActif()) {
                    updateCaisseByRegime(bloc, event.getValue(), infosPersonne);
                }

                // on impacte que si on est son propre référent
                if (bloc.getReferent().getValue() == null || bloc.getReferent().getValue().getIdentifiant() == null) {
                    impacterInfosSs(blocPersonnePrincipaleView, infosPersonne.getId());
                    if (blocConjointView != null) {
                        impacterInfosSs(blocConjointView, infosPersonne.getId());
                    }
                    // On verifie si la reference pointe vers un parent
                    for (BlocInfoSanteSimple blocEnfant : mapBlocsEnfantsViews.values()) {
                        impacterInfosSs(blocEnfant, infosPersonne.getId());
                    }
                }
            }
        };
    }

    private void updateCaisseByRegime(final BlocInfoSanteSimple bloc, IdentifiantLibelleGwt regime, InfosPersonneModel infoPersonneOrigine) {
        if (regime != null) {
            // on recupere le 1er element de la liste des caisses
            final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
            criteres.setMaxResults(1);
            criteres.setLibelle("");
            final DimensionCriteresRechercheCaisseModel criteresCaisse = new DimensionCriteresRechercheCaisseModel();
            criteresCaisse.setDimensionCriteres(criteres);
            criteresCaisse.setIdRegime(regime.getIdentifiant());
            criteresCaisse.setIdDepartement(idDepartement);
            dimensionService.rechercherCaisseParCriteres(criteresCaisse, new AsyncCallback<List<CaisseSimpleModel>>() {
                @Override
                public void onFailure(Throwable caught) {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }

                @Override
                public void onSuccess(List<CaisseSimpleModel> result) {
                    view.onRpcSuccess();
                    if (result != null && result.size() == 1) {
                        bloc.getCaisse().setValue(result.get(0));
                    } else {
                        bloc.getCaisse().setValue(null);
                    }
                }
            });
        } else {
            bloc.getCaisse().setValue(null);
        }
    }

    private ValueChangeHandler<CaisseSimpleModel> genererCaisseValueChangeHandler(final BlocInfoSanteSimple bloc, final Long idPersonne) {
        return new ValueChangeHandler<CaisseSimpleModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<CaisseSimpleModel> event) {
                // on impacte que si on est son propre référent
                if (bloc.getReferent().getValue() == null || bloc.getReferent().getValue().getIdentifiant() == null) {
                    impacterInfosSs(blocPersonnePrincipaleView, idPersonne);
                    if (blocConjointView != null) {
                        impacterInfosSs(blocConjointView, idPersonne);
                    }
                    // On verifie si la reference pointe vers un parent
                    for (BlocInfoSanteSimple blocEnfant : mapBlocsEnfantsViews.values()) {
                        impacterInfosSs(blocEnfant, idPersonne);
                    }
                }
            }
        };
    }

    private void impacterInfosSs(BlocInfoSanteSimple bloc, final Long idPersonne) {
        if (bloc.getReferent().getValue() != null) {
            final Long idReferent = bloc.getReferent().getValue().getIdentifiant();
            // si le referent est la personne modifiée
            if (idReferent != null && idReferent.equals(idPersonne)) {
                updateInfosSs(bloc, idPersonne);
            }
        }
    }

    private ValueChangeHandler<String> genererRepercuteReferenceSsHandler(final BlocInfoSanteSimple bloc, final Long idPersonne) {
        return new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                // on impacte que si on est son propre référent
                if (bloc.getReferent().getValue() == null || bloc.getReferent().getValue().getIdentifiant() == null) {
                    impacterLibelleReferent(blocPersonnePrincipaleView, idPersonne);
                    if (blocConjointView != null) {
                        impacterLibelleReferent(blocConjointView, idPersonne);
                    }
                    // On verifie si la reference pointe vers un parent
                    for (BlocInfoSanteSimple blocEnfant : mapBlocsEnfantsViews.values()) {
                        impacterLibelleReferent(blocEnfant, idPersonne);
                    }
                }
            }
        };
    }

    private void impacterLibelleReferent(BlocInfoSanteSimple bloc, final Long idPersonne) {
        if (bloc.getReferent().getValue() != null) {
            final Long idReferent = bloc.getReferent().getValue().getIdentifiant();
            // si le referent est la personne modifiée
            if (idReferent != null && idReferent.equals(idPersonne)) {
                if (idPersonne.equals(infosPersonnePrincipale.getId())) {
                    bloc.getReferent().setValue(construireReferent(infosPersonnePrincipale));
                } else if (infosConjoint != null && idPersonne.equals(infosConjoint.getId())) {
                    bloc.getReferent().setValue(construireReferent(infosConjoint));
                } else {
                    for (InfosPersonneModel enfant : listeEnfants) {
                        if (idPersonne.equals(enfant.getId())) {
                            bloc.getReferent().setValue(construireReferent(enfant));
                            break;
                        }
                    }
                }
            }
        }
    }

    private ValueChangeHandler<IdentifiantEidLibelleModel> genererReferentValueChangeHandler(final BlocInfoSanteSimple bloc, final InfosPersonneModel personne) {
        return new ValueChangeHandler<IdentifiantEidLibelleModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantEidLibelleModel> event) {
                if (event.getValue() != null && event.getValue().getIdentifiant() != null
                    && event.getValue().getIdentifiant().equals(InfosAdhesionPresenterConstants.ID_AJOUT_ASSURE_SOCIAL)) {
                    if (popupAjoutAssurePresenter == null) {
                        // on ouvre la popup de création d'assuré social
                        bloc.getRelation().setValue(new IdentifiantLibelleGwt());
                        final PopupAjoutAssurePresenter popupAjoutAssurePresenter =
                            addChildPresenter(new PopupAjoutAssurePresenter(eventBus, new PopupAjoutAssureViewImpl(), dimensionService,
                                tarificateurPersonneService, idDepartement, idDevis, bloc));
                        popupAjoutAssurePresenter.addEventHandlerToLocalBus(SelectionReferentRoEvent.TYPE, new SelectionReferentRoEventHandler() {
                            @Override
                            public void onSelect(final SelectionReferentRoEvent event) {
                                // on charge la reference
                                if (event.getReferent() != null) {
                                    final Command command = new Command() {
                                        @Override
                                        public void execute() {
                                            // on selectionne l'item
                                            event.getView().getReferent().setValue(construireReferenceSS(event.getReferent()));
                                            updateInfosSs(event.getView(), event.getReferent().getId());
                                        }
                                    };
                                    // on met à jour la liste des assurés sociaux
                                    chargerAssuresSociaux(command);
                                }
                                // on selectionne la personne principale
                                else {
                                    event.getView().getReferent().setValue(recupererReferent(null));
                                }
                            }
                        });
                        popupAjoutAssurePresenter.showPresenter(null);
                    } else {
                        popupAjoutAssurePresenter.afficher(idDepartement, idDevis, bloc);
                    }
                } else {
                    final Long idPersonne = personne.getId();
                    updateInfosSs(bloc, event.getValue() != null ? event.getValue().getIdentifiant() : null);

                    // si d'autres personnes pointe sur cette personne, on change aussi leur référent
                    impacterReferent(blocPersonnePrincipaleView, idPersonne, event.getValue());
                    if (blocConjointView != null) {
                        impacterReferent(blocConjointView, idPersonne, event.getValue());
                    }
                    // On verifie si la reference pointe vers un parent
                    for (BlocInfoSanteSimple blocEnfant : mapBlocsEnfantsViews.values()) {
                        impacterReferent(blocEnfant, idPersonne, event.getValue());
                    }

                    if (event.getValue() != null && event.getValue().getIdentifiant() != null && !event.getValue().getIdentifiant().equals(personne.getId())) {
                        // mise a jour de la relation
                        tarificateurPersonneService.rechercherRelationAssureSocial(personne.getId(), event.getValue().getIdentifiant(), personne
                                .getEidPersonne(), event.getValue().getIdentifiantExterieur(), new AsyncCallback<RelationAssureSocialModel>() {
                            @Override
                            public void onSuccess(RelationAssureSocialModel result) {
                                // s'il existe un lien familial dans square on l'utilise et on le masque
                                bloc.setRelationVisible(result.getLienFamilialSquare() == null);
                                if (result.getLienFamilialSquare() != null) {
                                    bloc.getRelation().setValue(result.getLienFamilialSquare());
                                } else if (result.getLienFamilialTarificateur() == null) {
                                    bloc.getRelation().setValue(null);
                                } else {
                                    bloc.getRelation().setValue(result.getLienFamilialTarificateur());
                                }
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                            }
                        });
                    } else {
                        bloc.getRelation().setValue(new IdentifiantLibelleGwt());
                        bloc.setRelationVisible(false);
                    }
                }
            }
        };
    }

    private void impacterReferent(BlocInfoSanteSimple bloc, final Long idPersonne, final IdentifiantEidLibelleModel nouveauReferent) {
        if (bloc != null && bloc.getReferent() != null && bloc.getReferent().getValue() != null) {
            final Long idReferent = bloc.getReferent().getValue().getIdentifiant();
            // si le referent est la personne modifiée
            if (idReferent != null && idReferent.equals(idPersonne)) {
                bloc.getReferent().setValue(nouveauReferent);
            }
        }
    }

    private KeyPressHandler genererRestrictionTouchesHandler(final BlocInfoSanteSimple bloc) {
        return new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                final char keyCode = event.getCharCode();
                // On ne tient pas compte des caractères non numériques saisis
                if ((!event.isControlKeyDown() && !Character.isDigit(keyCode)) && !isCharCorse(keyCode, bloc) && (keyCode != (char) KeyCodes.KEY_TAB)
                    && (keyCode != (char) KeyCodes.KEY_BACKSPACE) && (keyCode != (char) KeyCodes.KEY_ESCAPE) && (keyCode != (char) KeyCodes.KEY_DELETE)
                    && (keyCode != (char) KeyCodes.KEY_ENTER) && (keyCode != (char) KeyCodes.KEY_HOME) && (keyCode != (char) KeyCodes.KEY_END)
                    && (keyCode != (char) KeyCodes.KEY_LEFT) && (keyCode != (char) KeyCodes.KEY_RIGHT) && (keyCode != (char) KeyCodes.KEY_UP)) {
                    event.preventDefault();
                    event.stopPropagation();
                }
            }

        };
    }

    /**
     * Interface de la vue.
     */
    public interface InfosAdhesionView extends View {
        /**
         * Récupère la date de signature.
         * @return la date de signature.
         */
        String getDateSignature();

        /**
         * Récupère le composant date de signature.
         * @return le composant
         */
        DecoratedCalendrierDateBox getCdbDateSignature();

        /**
         * Charge les informations de paiement.
         * @param infosPaiement informations de paiement.
         */
        void chargerInfosPaiement(InfosPaiementModel infosPaiement);

        /**
         * Charge les informations de signature numérique.
         * @param dateClicBA date de clic BA
         * @param dateTelechargementBA date de telechargement BA
         */
        void chargerInfosSignatureNumerique(String dateClicBA, String dateTelechargementBA);

        /**
         * Charge les informations du RIB.
         * @param infosRib infos de RIB
         */
        void chargerInfosRib(InfosRibModel infosRib);

        /**
         * Récupère le bouton qui permet d'enregistrer les infos d'adhésion.
         * @return handler du bouton.
         */
        HasClickHandlers getBtnEnregistrer();

        /**
         * Récupère le bouton qui permet d'annuler la saisie d'infos d'adhésion.
         * @return handler du bouton.
         */
        HasClickHandlers getBtnAnnuler();

        /**
         * Accesseur option Télétransmission.
         * @return la valeur de l'option
         */
        HasValue<Boolean> getTeletransmission();

        /**
         * Récupère le jour de paiement selectionné.
         * @return le jour de paiement selectionné.
         */
        HasValue<IdentifiantLibelleGwt> getJourPaiement();

        /**
         * Récupère le moyen de paiement selectionné.
         * @return le moyen de paiement selectionné.
         */
        HasValue<IdentifiantLibelleGwt> getMoyenPaiement();

        /**
         * Récupère la periodicité de paiement selectionnée.
         * @return la periodicité de paiement selectionnée.
         */
        HasValue<IdentifiantLibelleGwt> getPeriodicitePaiement();

        /**
         * Affiche la vue.
         */
        void show();

        /**
         * Cache la vue.
         */
        void hide();

        /**
         * Cree un bloc simple.
         * @param suffix suffix des champs erreur
         * @return le bloc simple
         */
        BlocInfoSanteSimple creerBlocInfoSanteSimple(String suffix);

        /**
         * Cree un bloc complet.
         * @param suffix suffix des champs erreur
         * @return le bloc complet
         */
        BlocInfoSanteComplete creerBlocInfoSanteComplete(String suffix);

        /**
         * Integre un bloc info sante.
         * @param vue vue
         * @param position position
         */
        void ajouterBlocInfoSante(View vue, int position);

        /**
         * Handler pour l'affichage d'icone associés aux champs de la vue.
         * @return le handler.
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Récupère le code banque du rib.
         * @return valeur du champ.
         */
        HasValue<String> getRibCodeBanque();

        /**
         * Récupère le code guichet du rib.
         * @return valeur du champ.
         */
        HasValue<String> getRibCodeGuichet();

        /**
         * Récupère le code compte du rib.
         * @return valeur du champ.
         */
        HasValue<String> getRibCodeCompte();

        /**
         * Récupère la clé du rib.
         * @return valeur du champ.
         */
        HasValue<String> getRibCle();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getModesPaimentSuggestHandler();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getPeriodicitesPaiementSuggestHandler();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getJoursPaiementSuggestHandler();

        /**
         * Retourne les constantes.
         * @return les constantes
         */
        InfosAdhesionViewImplConstants getViewConstants();

        /**
         * Lancement d'un appel rpc.
         * @param config la config
         */
        void onRpcCall(LoadingPopupConfiguration config);

        /**
         * Echec d'un appel rpc.
         * @param errorPopupConfiguration la config
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Succes d'un appel rpc.
         */
        void onRpcSuccess();

        /**
         * Rend les infos d'adhésion éditable ou non.
         * @param isEditable true pour les rendre éditables, false si non.
         */
        void setInfosAdhesionEditables(boolean isEditable);

        /**
         * Accesseur AllKeyHandlers pour le focus panel.
         * @return le handler
         */
        HasAllKeyHandlers getAllKeyHandlersFocusPanel();

        /**
         * Masque la popup de loading.
         */
        void masquerLoadingPopup();

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
         * Affiche la liste des assures sociaux.
         * @param liste la liste
         */
        void afficherAssuresSociaux(List<AssureSocialModel> liste);

        /**
         * Vide la liste des assures sociaux.
         * @param nbAssuresSociaux nb d'assures
         */
        void viderAssuresSociaux(int nbAssuresSociaux);

        /**
         * Vide un certain nombre de personne.
         * @param nbPersonnes nombre de personnes
         */
        void viderPersonnes(int nbPersonnes);
    }

    /**
     * Interface de la vue.
     */
    public interface BlocInfoSanteComplete extends BlocInfoSanteSimple {
        /**
         * Récupère l'option cochée pour la personne principale.
         * @return valeur du flag.
         */
        HasValue<Boolean> getLoiMadelin();

        /**
         * Récupère l'option cochée pour la personne principale.
         * @return valeur du flag.
         */
        HasValue<Boolean> getTns();

        /**
         * Récupère l'option cochée pour la personne principale.
         * @return valeur du flag.
         */
        HasValue<Boolean> getCouvertActuellement();

        /**
         * Récupère l'option cochée pour la personne principale.
         * @return valeur du flag.
         */
        HasValue<Boolean> getCouvert6DerniersMois();
    }

    /**
     * Interface de la vue.
     */
    public interface BlocInfoSanteSimple extends View {

        /**
         * Initialise le focus.
         */
        void initFocus();

        /**
         * Récupère le numéro de sécurité sociale associé.
         * @return valeur du champ.
         */
        HasValue<String> getNumeroSecuriteSociale();

        /**
         * Permet d'activer/désactiver la saisie d'informations de Sécurité Sociale.
         * @param activerSaisie true pour activer, false pour désactiver.
         */
        void activerSaisieInfos(boolean activerSaisie);

        /**
         * Récupère la clé de sécurité sociale associé à l'enfant.
         * @return valeur du champ.
         */
        HasValue<String> getCleSecuriteSociale();

        /**
         * Récupère la valeur selectionnée pour la liste associée.
         * @return valeur selectionnée.
         */
        HasValue<IdentifiantEidLibelleModel> getReferent();

        /**
         * Récupère la valeur selectionnée pour la liste associée.
         * @return valeur selectionnée.
         */
        HasValue<IdentifiantLibelleGwt> getRegime();

        /**
         * Récupère la valeur selectionnée pour la liste associée.
         * @return valeur selectionnée.
         */
        HasValue<CaisseSimpleModel> getCaisse();

        /**
         * Handler pour les events associés aux touches sur le champ numéro de Sécurité Sociale.
         * @return le handler.
         */
        HasKeyPressHandlers getNumSsKeyPressHandler();

        /**
         * Handler pour les events associés aux touches sur le champ clé de Sécurité Sociale.
         * @return le handler.
         */
        HasKeyPressHandlers getCleSsKeyPressHandler();

        /**
         * Handler pour la modification de la caisse.
         * @return le handler.
         */
        HasValueChangeHandlers<CaisseSimpleModel> getCaisseValueChangeHandler();

        /**
         * Handler pour la modification de la caisse.
         * @return le handler.
         */
        HasValueChangeHandlers<IdentifiantLibelleGwt> getRegimeValueChangeHandler();

        /**
         * Handler pour la modification du numéro de sécurité sociale.
         * @return le handler.
         */
        HasValueChangeHandlers<String> getNumSsValueChangeHandler();

        /**
         * Handler pour la modification de la clé de sécurité sociale.
         * @return le handler.
         */
        HasValueChangeHandlers<String> getCleSsValueChangeHandler();

        /**
         * Handler pour la selection d'infos de sécurité sociale d'un referent.
         * @return le handler.
         */
        HasValueChangeHandlers<IdentifiantEidLibelleModel> getReferentValueChangeHandler();

        /**
         * Charge les informations.
         * @param infos informations.
         * @param referent referent
         * @param captionText captionText
         */
        void chargerInfos(InfosPersonneModel infos, IdentifiantEidLibelleModel referent, String captionText);

        /**
         * Handler pour l'affichage d'icone associés aux champs de la vue.
         * @return le handler.
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantEidLibelleModel> getReferentSuggestHandler();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getRegimeSuggestHandler();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<CaisseSimpleModel> getCaisseSuggestHandler();

        /**
         * Rend les infos d'adhésion éditable ou non.
         * @param isEditable true pour les rendre éditables, false si non.
         */
        void setInfosAdhesionEditables(boolean isEditable);

        /**
         * Retourne si le champ régime est actif.
         * @return true ou false
         */
        boolean isChampRegimeActif();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getRelationSuggestHandler();

        /**
         * Recupere la valeur.
         * @return la valeur
         */
        HasValue<IdentifiantLibelleGwt> getRelation();

        /**
         * Affiche/Masque la relation.
         * @param visible flag
         */
        void setRelationVisible(boolean visible);

    }

    @Override
    public void onDetach() {
        registerDeskbar.removeHandler();
    }
}

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
package com.square.composant.tarificateur.square.client.presenter.popup.ajout.assure;

import java.util.Date;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
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
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.event.CreerAssureSocialEvent;
import com.square.composant.tarificateur.square.client.event.CreerAssureSocialEventHandler;
import com.square.composant.tarificateur.square.client.event.SelectionReferentRoEvent;
import com.square.composant.tarificateur.square.client.exception.ControleIntegriteExceptionGwt;
import com.square.composant.tarificateur.square.client.model.CaisseSimpleModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresRechercheCaisseModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresRechercheModel;
import com.square.composant.tarificateur.square.client.model.DimensionCriteresRechercheRegimeModel;
import com.square.composant.tarificateur.square.client.model.doublons.PersonneDoublonModel;
import com.square.composant.tarificateur.square.client.model.doublons.RechercheDoublonCritereModel;
import com.square.composant.tarificateur.square.client.model.personne.AssureSocialModel;
import com.square.composant.tarificateur.square.client.model.personne.InfoSanteModel;
import com.square.composant.tarificateur.square.client.presenter.adhesion.InfosAdhesionPresenter.BlocInfoSanteSimple;
import com.square.composant.tarificateur.square.client.presenter.popup.doublon.PopupCreationAssureSocialDoublonPresenter;
import com.square.composant.tarificateur.square.client.service.DimensionServiceGwtAsync;
import com.square.composant.tarificateur.square.client.service.TarificateurPersonneServiceGwtAsync;
import com.square.composant.tarificateur.square.client.view.popup.ajout.assure.PopupAjoutAssureViewImplConstants;
import com.square.composant.tarificateur.square.client.view.popup.doublon.PopupCreationAssureSocialDoublonViewImpl;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Presenter de la popup d'ajout d'assure social.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class PopupAjoutAssurePresenter extends Presenter {

    private PopupAjoutAssureView view;

    private DimensionServiceGwtAsync dimensionService;

    private TarificateurPersonneServiceGwtAsync tarificateurPersonneService;

    private Long idDepartement;

    private Long idDevis;

    private BlocInfoSanteSimple viewEnfantSource;

    private AssureSocialModel assureSocial;

    private PopupCreationAssureSocialDoublonPresenter popupCreationAssureSocialDoublonPresenter;

    /**
     * Constructeur du présenter.
     * @param eventBus eventBus
     * @param vue vue
     * @param dimensionService dimension service
     * @param tarificateurPersonneService tarificateurPersonneService
     * @param idDepartement idDepartement
     * @param idDevis idDevis
     * @param viewEnfantSource vue enfant source
     */
    public PopupAjoutAssurePresenter(HandlerManager eventBus, PopupAjoutAssureView vue, DimensionServiceGwtAsync dimensionService,
        TarificateurPersonneServiceGwtAsync tarificateurPersonneService, Long idDepartement, Long idDevis, final BlocInfoSanteSimple viewEnfantSource) {
        super(eventBus);
        this.view = vue;
        this.dimensionService = dimensionService;
        this.tarificateurPersonneService = tarificateurPersonneService;
        this.idDepartement = idDepartement;
        this.idDevis = idDevis;
        this.viewEnfantSource = viewEnfantSource;
        assureSocial = new AssureSocialModel();
    }

    public void afficher(Long idDepartement, Long idDevis, final BlocInfoSanteSimple viewEnfantSource) {
        this.idDepartement = idDepartement;
        this.idDevis = idDevis;
        this.viewEnfantSource = viewEnfantSource;
        view.getCaisse().setValue(null);
        view.getCivilite().setValue(null);
        view.getCleSS().setValue(null);
        view.getDateNaissance().setValue(null);
        view.getNom().setValue(null);
        view.getNumSS().setValue(null);
        view.getPrenom().setValue(null);
        view.getRegime().setValue(null);
        view.showPopup();
    }

    @Override
    public View asView() {
        return null;
    }

    @Override
    public void onBind() {
        // Gestion des touches de la popup
        view.getAllKeyHandlersFocusPanel().addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    rechercherDoublon();
                }
                else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    fermerPopup();
                }
            }
        });
        view.getBtnValider().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                rechercherDoublon();
            }
        });
        view.getBtnAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // selectionner le nouvel item dans la liste des references
                fireEventLocalBus(new SelectionReferentRoEvent(null, viewEnfantSource));
                fermerPopup();
            }
        });

        view.getRegimeSuggestHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
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
        });
        view.getCaisseSuggestHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<CaisseSimpleModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<CaisseSimpleModel> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setMaxResults(ComposantTarificateurConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                criteres.setLibelle(event.getSuggest());
                final DimensionCriteresRechercheCaisseModel criteresCaisse = new DimensionCriteresRechercheCaisseModel();
                criteresCaisse.setDimensionCriteres(criteres);
                criteresCaisse.setIdRegime(view.getRegime().getValue() != null ? view.getRegime().getValue().getIdentifiant() : null);
                dimensionService.rechercherCaisseParCriteres(criteresCaisse, event.getCallBack());
            }
        });
        view.getCiviliteSuggestHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(ComposantTarificateurConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionService.rechercherCiviliteParCriteres(criteres, event.getCallBack());
            }
        });

        view.getRegimeValueChangeHandler().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                if (event.getValue() != null) {
                    // on recupere le 1er element de la liste des caisses
                    final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                    criteres.setMaxResults(1);
                    criteres.setLibelle("");
                    final DimensionCriteresRechercheCaisseModel criteresCaisse = new DimensionCriteresRechercheCaisseModel();
                    criteresCaisse.setDimensionCriteres(criteres);
                    criteresCaisse.setIdRegime(event.getValue().getIdentifiant());
                    criteresCaisse.setIdDepartement(idDepartement);
                    dimensionService.rechercherCaisseParCriteres(criteresCaisse, new AsyncCallback<List<CaisseSimpleModel>>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                        }

                        @Override
                        public void onSuccess(List<CaisseSimpleModel> result) {
                            if (result != null && result.size() == 1) {
                                view.getCaisse().setValue(result.get(0));
                            }
                            else {
                                view.getCaisse().setValue(null);
                            }
                        }
                    });
                }
                else {
                    view.getCaisse().setValue(null);
                }
            }
        });
        final ValueChangeHandler<String> doublonValueChangeHandler = new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                rechercherDoublonsWarning();
            }
        };
        view.getNom().addValueChangeHandler(doublonValueChangeHandler);
        view.getPrenom().addValueChangeHandler(doublonValueChangeHandler);
        view.getDateNaissance().addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                rechercherDoublonsWarning();
            }
        });

    }

    private void fermerPopup() {
        view.hidePopup();
    }

    private void recupererAssureSocial() {
        if (view.getDateNaissance().getValue() != null) {
            assureSocial.setDateNaissance(DateUtil.getString(view.getDateNaissance().getValue()));
        }
        assureSocial.setCivilite(view.getCivilite().getValue());
        assureSocial.setNom(view.getNom().getValue());
        assureSocial.setPrenom(view.getPrenom().getValue());
        final InfoSanteModel infoSante = new InfoSanteModel();
        infoSante.setCaisse(view.getCaisse().getValue());
        infoSante.setCleSecuriteSocial(view.getCleSS().getValue());
        infoSante.setNumSecuriteSocial(view.getNumSS().getValue());
        infoSante.setRegime(view.getRegime().getValue());
        assureSocial.setInfoSante(infoSante);
    }

    /**
     * Crée un assurer social.
     */
    private void creerAssureSocial() {
        view.onRpcCall(new LoadingPopupConfiguration(view.getConstants().creationAssureSocial()));
        if (assureSocial.getInfoSante() != null && assureSocial.getInfoSante().getRegime() != null) {
        	GWT.log("" + assureSocial.getInfoSante().getRegime().getIdentifiant() + " : " + assureSocial.getInfoSante().getRegime().getLibelle());
        }
        tarificateurPersonneService.createAssureSocial(assureSocial, idDevis, false, new AsyncCallback<AssureSocialModel>() {
            @Override
            public void onSuccess(AssureSocialModel result) {
                view.onRpcSuccess();
                view.hidePopup();

                // selectionner le nouvel item dans la liste des references
                fireEventLocalBus(new SelectionReferentRoEvent(result, viewEnfantSource));
            }

            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                    // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                    view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                    view.onRpcServiceFailure(null);
                }
                else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }
        });
    }

    private RechercheDoublonCritereModel getCriteresRechercheDoublon() {
        // Création des critères de recherche
        final RechercheDoublonCritereModel criteres = new RechercheDoublonCritereModel();
        if (view.getDateNaissance().getValue() != null) {
            criteres.setDateNaissance(DateUtil.getString(view.getDateNaissance().getValue()));
        }
        criteres.setNom(view.getNom().getValue());
        criteres.setPrenom(view.getPrenom().getValue());
        return criteres;
    }

    /**
     * Recherche si une personne a des doublons.
     * @param personne la personne.
     * @param isBeneficiaire indique si la personne est un bénéficiaire.
     */
    private void rechercherDoublon() {
        recupererAssureSocial();
        final RechercheDoublonCritereModel criteres = getCriteresRechercheDoublon();
        if (!criteres.isComplete() || assureSocial.getEidPersonne() != null) {
            creerAssureSocial();
        }
        else {
            final AsyncCallback<List<PersonneDoublonModel>> asyncCallback = new AsyncCallback<List<PersonneDoublonModel>>() {
                @Override
                public void onSuccess(List<PersonneDoublonModel> listeDoublons) {
                    if (listeDoublons.size() > 0) {
                        if (popupCreationAssureSocialDoublonPresenter == null) {
                            final PopupCreationAssureSocialDoublonViewImpl popupView =
                                new PopupCreationAssureSocialDoublonViewImpl(assureSocial.getNom(), assureSocial.getPrenom(), listeDoublons);
                            final PopupCreationAssureSocialDoublonPresenter popupCreationAssureSocialDoublonPresenter =
//                            	popupCreationAssureSocialDoublonPresenter =
                                addChildPresenter(new PopupCreationAssureSocialDoublonPresenter(eventBus, popupView, assureSocial, listeDoublons));
                            popupCreationAssureSocialDoublonPresenter.addEventHandlerToLocalBus(CreerAssureSocialEvent.TYPE,
                                new CreerAssureSocialEventHandler() {
                                    @Override
                                    public void creerPersonne(CreerAssureSocialEvent event) {
                                        view.getCivilite().setValue(event.getPersonne().getCivilite());
                                        // on renseigne les champs de la popup avec les infos récupérées
                                        view.getNumSS().setValue(
                                            event.getPersonne().getInfoSante() != null ? event.getPersonne().getInfoSante().getNumSecuriteSocial() : null);
                                        view.getCleSS().setValue(
                                            event.getPersonne().getInfoSante() != null ? event.getPersonne().getInfoSante().getCleSecuriteSocial() : null);
                                        view.getCaisse().setValue(
                                            event.getPersonne().getInfoSante() != null ? event.getPersonne().getInfoSante().getCaisse() : null);
                                        view.getRegime().setValue(
                                            event.getPersonne().getInfoSante() != null ? event.getPersonne().getInfoSante().getRegime() : null);
                                        assureSocial = event.getPersonne();
                                        // 0003506: 0009958: mauvais assuré social rataché.
                                        creerAssureSocial();
                                    }
                                });
                            popupCreationAssureSocialDoublonPresenter.showPresenter(null);
                        }
                        else {
                            popupCreationAssureSocialDoublonPresenter.afficher(assureSocial, listeDoublons);
                        }
                    }
                    else {
                        creerAssureSocial();
                    }
                }

                @Override
                public void onFailure(Throwable caught) {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            };
            tarificateurPersonneService.rechercherDoublonsPersonneParCriteres(criteres, asyncCallback);
        }
    }

    /**
     * Recherche si une personne a des doublons pour prévenit l'utilisateur lors de la saisie.
     * @param vue la vue où afficher le warning.
     * @param nom le nom de la personne.
     * @param prenom le prénom de la personne.
     * @param date la date de naissance de la personne.
     */
    private void rechercherDoublonsWarning() {
        // on reinitialise l'eid personne pour rechercher à nouveau les doublons
        assureSocial.setId(null);
        assureSocial.setEidPersonne(null);
        final RechercheDoublonCritereModel criteres = getCriteresRechercheDoublon();
        if (criteres.isComplete()) {
            final AsyncCallback<List<PersonneDoublonModel>> asyncCallback = new AsyncCallback<List<PersonneDoublonModel>>() {
                @Override
                public void onSuccess(List<PersonneDoublonModel> listeDoublons) {
                    view.afficherWarningDoublons(listeDoublons.size() > 0);
                }

                @Override
                public void onFailure(Throwable caught) {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            };
            tarificateurPersonneService.rechercherDoublonsPersonneParCriteres(criteres, asyncCallback);
        }
    }

    @Override
    public void onShow(HasWidgets container) {
        view.showPopup();
    }

    /**
     * Interface de la vue.
     */
    public interface PopupAjoutAssureView extends View {
        /**
         * Affiche la popup d'erreur.
         * @param errorPopupConfiguration la configuration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Récupère le handler du bouton de validation.
         * @return le handler.
         */
        HasClickHandlers getBtnValider();

        /**
         * Récupère le handler du bouton d'annulation.
         * @return le handler.
         */
        HasClickHandlers getBtnAnnuler();

        /**
         * Affiche la popup.
         */
        void showPopup();

        /**
         * Cache la popup.
         */
        void hidePopup();

        /**
         * Accesseur AllKeyHandlers du focus panel.
         * @return le handler
         */
        HasAllKeyHandlers getAllKeyHandlersFocusPanel();

        /**
         * Initialise le focus.
         */
        void initFocus();

        /**
         * Retourne les constantes de vue.
         * @return les constantes
         */
        PopupAjoutAssureViewImplConstants getConstants();

        /**
         * Recupere la valeur.
         * @return la valeur
         */
        HasValue<IdentifiantLibelleGwt> getCivilite();

        /**
         * Recupere la valeur.
         * @return la valeur
         */
        HasValue<CaisseSimpleModel> getCaisse();

        /**
         * Recupere la valeur.
         * @return la valeur
         */
        HasValue<IdentifiantLibelleGwt> getRegime();

        /**
         * Recupere la valeur.
         * @return la valeur
         */
        HasValue<String> getNom();

        /**
         * Recupere la valeur.
         * @return la valeur
         */
        HasValue<String> getPrenom();

        /**
         * Recupere la valeur.
         * @return la valeur
         */
        HasValue<String> getNumSS();

        /**
         * Recupere la valeur.
         * @return la valeur
         */
        HasValue<String> getCleSS();

        /**
         * Recupere la valeur.
         * @return la valeur
         */
        HasValue<Date> getDateNaissance();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getCiviliteSuggestHandler();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<CaisseSimpleModel> getCaisseSuggestHandler();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getRegimeSuggestHandler();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasValueChangeHandlers<IdentifiantLibelleGwt> getRegimeValueChangeHandler();

        /**
         * Handler pour l'affichage d'icone associés aux champs de la vue.
         * @return le handler.
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Succes d'un appel rpc.
         */
        void onRpcSuccess();

        /**
         * Lancement d'un appel rpc.
         * @param config la config
         */
        void onRpcCall(LoadingPopupConfiguration config);

        /**
         * Affiche ou pas le warning.
         * @param visible flag
         */
        void afficherWarningDoublons(boolean visible);

        /**
         * Désactive les boxs.
         */
        void desactiverBoxs();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}

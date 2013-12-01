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
package com.square.client.gwt.client.presenter.personne.physique;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
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
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.CreerPersonneEvent;
import com.square.client.gwt.client.event.CreerPersonneEventHandler;
import com.square.client.gwt.client.event.ModifierPaysTelephoneEvent;
import com.square.client.gwt.client.event.ModifierPaysTelephoneEventHandler;
import com.square.client.gwt.client.event.OpenPersonEvent;
import com.square.client.gwt.client.event.SimpleValueChangeEvent;
import com.square.client.gwt.client.event.SimpleValueChangeEventHandler;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.client.gwt.client.model.AdresseModel;
import com.square.client.gwt.client.model.BeneficiaireModel;
import com.square.client.gwt.client.model.CodePostalCommuneModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.CoordonneesModel;
import com.square.client.gwt.client.model.DimensionCritereCodePostalCommuneModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.EmailModel;
import com.square.client.gwt.client.model.IdentifiantLibelleBooleanModel;
import com.square.client.gwt.client.model.PaysSimpleModel;
import com.square.client.gwt.client.model.PersonneBaseModel;
import com.square.client.gwt.client.model.PersonneDoublonModel;
import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.model.TelephoneModel;
import com.square.client.gwt.client.presenter.personne.PopupSelectionPaysPresenter;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.PersonnePhysiqueServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.view.personne.coordonnees.PopupSelectionPaysViewImpl;
import com.square.client.gwt.client.view.personne.coordonnees.PopupSelectionPaysViewImpl.ListBoxPaysProperties;
import com.square.client.gwt.client.view.personne.physique.creation.BlocCreationBeneficiaireViewImpl;
import com.square.client.gwt.client.view.personne.physique.creation.PersonnePhysiqueCreationViewImplConstants;
import com.square.client.gwt.client.view.personne.physique.creation.PopupCreationPersonneDoublonViewImpl;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composant.fusion.square.client.model.RechercheDoublonCritereModel;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.DecoratedTextBoxFormat;
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
 * Presenter pour la page de création de personne physique.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class PersonnePhysiqueCreationPresenter extends Presenter {

    /** Service asynchrone. */
    private PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService;

    private DimensionServiceGwtAsync dimensionServiceGwtAsync;

    /** Vue associé au presenter. */
    private PersonnePhysiqueCreationView view;

    private ConstantesModel constantes;

    private AppControllerConstants appControllerConstants;

    /** Nombre de personnes à créer. */
    private int nbPersonnesPourCreation;

    /** Nombre de personnes recherchées (doublon) pour une création. */
    private int nbPersonnesRechercheesPourCreation;

    /** Personne en cours à créer. */
    private PersonneModel personneACreerEnCours;

    /** Personne en cours à créer. */
    private BeneficiaireModel conjointACreerEnCours;

    /** Liste des enfant en cours à créer. */
    private Map<Integer, BeneficiaireModel> mapEnfantsACreerEnCours;

    /** Liste des bénéficiaires en cours à créer. */
    private List<BeneficiaireModel> listeBeneficiaireACreerEnCours;

    /** Popup de sélection du pays du téléphone 1. */
    private PopupSelectionPaysPresenter popupSelectionPaysTel1;

    /** Popup de sélection du pays du téléphone 2. */
    private PopupSelectionPaysPresenter popupSelectionPaysTel2;

    /** Constantes du presenter. */
    private PersonnePhysiqueCreationPresenterConstants presenterConstants;

    private DeskBar deskBar;

    /** service de gestion d'aide. */
    private AideServiceGwtAsync aideService;

    /** ressource connectée. */
    private DimensionRessourceModel ressourceConnectee;

    private PopupCreationPersonneDoublonPresenter popupCreationPersonneDoublonPresenter;

    private Stack<PersonneBaseModel> personnesATester;

    private HandlerRegistration deskBarRegistration;

    /**
     * Constructeur par defaut.
     * @param eventBus le bus des évenements
     * @param personnePhysiqueRpcService service rpc pour les personnes physique
     * @param dimensionServiceGwtAsync service rpc pour les dimensions
     * @param view la vue associé au presenter
     * @param appControllerConstants les constantes d'application
     * @param personneRpcService service rpc pour personnes physique
     * @param constantes les constantes.
     * @param deskBar deskBar
     * @param aideService le service d'aide
     * @param ressourceConnectee la personne connectée
     */
    public PersonnePhysiqueCreationPresenter(HandlerManager eventBus, PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService,
        PersonneServiceGwtAsync personneRpcService, DimensionServiceGwtAsync dimensionServiceGwtAsync, PersonnePhysiqueCreationView view,
        AppControllerConstants appControllerConstants, ConstantesModel constantes, DeskBar deskBar, AideServiceGwtAsync aideService,
        DimensionRessourceModel ressourceConnectee) {
        super(eventBus);
        this.personnePhysiqueRpcService = personnePhysiqueRpcService;
        this.dimensionServiceGwtAsync = dimensionServiceGwtAsync;
        this.appControllerConstants = appControllerConstants;
        this.constantes = constantes;
        this.view = view;
        listeBeneficiaireACreerEnCours = new ArrayList<BeneficiaireModel>();
        this.presenterConstants = GWT.create(PersonnePhysiqueCreationPresenterConstants.class);
        this.deskBar = deskBar;
        this.aideService = aideService;
        this.ressourceConnectee = ressourceConnectee;
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
        // Gestion des touches de la popup
        view.getAllKeyHandlersFocusPanel().addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                final int keyCode = event.getNativeKeyCode();
                if (keyCode == KeyCodes.KEY_ENTER) {
                    lancerCreationPersonnePhysique();
                } else if (keyCode == KeyCodes.KEY_ESCAPE) {
                    view.hidePopup();
                }
            }
        });
        view.getBtnCreer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                lancerCreationPersonnePhysique();
            }
        });
        view.getLienAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.hidePopup();
            }
        });
        view.getSlbCivilite().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                rechercherCiviliteParCriteres(event.getSuggest(), event.getCallBack());
            }
        });
        view.getSlbNatureVoie().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceGwtAsync.rechercherNatureVoieParCriteres(criteres, event.getCallBack());
            }
        });
        view.getSlbCodePostal().addSuggestHandler(new SuggestListBoxSuggestEventHandler<CodePostalCommuneModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<CodePostalCommuneModel> event) {
                DimensionCritereCodePostalCommuneModel criteres = null;
                final String libelleRecherche = event.getSuggest();
                if (libelleRecherche == null || "".equals(libelleRecherche.trim())) {
                    criteres = new DimensionCritereCodePostalCommuneModel();
                    criteres.setMaxResults(200);
                    dimensionServiceGwtAsync.rechercherCodesPostauxCommunes(criteres, event.getCallBack());
                } else {
                    criteres = new DimensionCritereCodePostalCommuneModel();
                    criteres.setLibelleCodePostal(libelleRecherche);
                    criteres.setMaxResults(200);
                    dimensionServiceGwtAsync.rechercherCodesPostauxCommunes(criteres, event.getCallBack());
                }
            }
        });
        view.getSlbCodePostalValue().addValueChangeHandler(new ValueChangeHandler<CodePostalCommuneModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<CodePostalCommuneModel> event) {
                if (event.getValue() != null) {
                    view.getLValueVille().setText(event.getValue().getLibelleAcheminement());
                }
                else {
                    view.getLValueVille().setText("");
                }
            }
        });
        view.getSlbNatureTelephone().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceGwtAsync.rechercherNatureTelephoneParCriteres(criteres, event.getCallBack());
            }
        });
        view.getSlbNatureTelephonePortable().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceGwtAsync.rechercherNatureTelephoneParCriteres(criteres, event.getCallBack());
            }
        });
        view.getSlbProfession().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleBooleanModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleBooleanModel> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceGwtAsync.rechercherProfessionParCriteres(criteres, event.getCallBack());
            }
        });
        view.getConjointView().getSlbCivilite().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                rechercherCiviliteParCriteres(event.getSuggest(), event.getCallBack());
            }
        });

        view.getSlbPays().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleBooleanModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleBooleanModel> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceGwtAsync.rechercherPaysParCriteres(criteres, event.getCallBack());
            }
        });
        view.getSlbPaysValue().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleBooleanModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleBooleanModel> event) {
                afficheMasqueBlocCommune();
            }
        });
        view.getLbNbEnfantsValue().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {

            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                // on cree les blocs
                if (view.getLbNbEnfantsValue() != null && view.getLbNbEnfantsValue().getValue() != null) {
                    final List<BlocCreationBeneficiaireView> listeNouveauxBlocs =
                        view.afficherBlocsEnfants(Integer.valueOf(view.getLbNbEnfantsValue().getValue().getIdentifiant().toString()));
                    // on ajoute un handler sur les civilites
                    for (BlocCreationBeneficiaireView blocEnfant : listeNouveauxBlocs) {
                        blocEnfant.getSlbCivilite().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
                            @Override
                            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                                rechercherCiviliteParCriteres(event.getSuggest(), event.getCallBack());
                            }
                        });

                        // On recopie le nom de l'adhérent principal si nécessaire
                        if (blocEnfant.getTbNom().getValue() == null || "".equals(blocEnfant.getTbNom().getValue().trim())) {
                            blocEnfant.getTbNom().setValue(view.getTbNom().getValue().trim());
                        }
                    }
                }
            }
        });
        view.getLbConjointValue().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {

            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                if (event.getValue() != null) {
                    view.afficherBlocConjoint(event.getValue().getIdentifiant() > 0);
                    // si le bloc conjoint est ouvert, on rend visible la checkbox de rattachement aux parents pour les enfants
                    if (event.getValue().getIdentifiant() > 0) {
                        for (final BlocCreationBeneficiaireView blocEnfant : view.getListeEnfantsView()) {
                            blocEnfant.getCbRattacherParents().setVisible(true);
                            blocEnfant.getCbRattacherParents().setValue(true);
                        }
                    }
                    else {
                        for (final BlocCreationBeneficiaireView blocEnfant : view.getListeEnfantsView()) {
                            blocEnfant.getCbRattacherParents().setVisible(false);
                            blocEnfant.getCbRattacherParents().setValue(false);
                        }
                    }
                    // On recopie le nom de l'adhérent principal si nécessaire
                    if (event.getValue().getIdentifiant() > 0
                        && (view.getConjointView().getTbNom().getValue() == null || "".equals(view.getConjointView().getTbNom().getValue().trim()))) {
                        view.getConjointView().getTbNom().setValue(view.getTbNom().getValue().trim());
                    }
                }
            }
        });
        view.getTbTelephone().addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                final TelephoneModel telephoneModel = new TelephoneModel();
                telephoneModel.setNumero(event.getValue());
                final IdentifiantLibelleGwt natureTelephone = new IdentifiantLibelleGwt(constantes.getIdNatureTelephoneFixe());
                telephoneModel.setNature(natureTelephone);
                final PaysSimpleModel paysTelephone = new PaysSimpleModel();
                if (popupSelectionPaysTel1 != null) {
                    paysTelephone.setId(popupSelectionPaysTel1.getIdPaysSelectionne());
                }
                else {
                    paysTelephone.setId(view.getIdPaysTelephone());
                }
                telephoneModel.setPays(paysTelephone);
                personnePhysiqueRpcService.controlerTelephone(telephoneModel, new AsyncCallback<CoordonneesModel>() {
                    @Override
                    public void onSuccess(CoordonneesModel result) {
                        view.onRpcServiceSuccess();
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
        });
        view.getTbTelephonePortable().addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                final TelephoneModel telephoneModel = new TelephoneModel();
                telephoneModel.setNumero(event.getValue());
                final IdentifiantLibelleGwt natureTelephone = new IdentifiantLibelleGwt(constantes.getIdNatureMobilePrive());
                telephoneModel.setNature(natureTelephone);
                final PaysSimpleModel paysTelephone = new PaysSimpleModel();
                if (popupSelectionPaysTel2 != null) {
                    paysTelephone.setId(popupSelectionPaysTel2.getIdPaysSelectionne());
                }
                else {
                    paysTelephone.setId(view.getIdPaysTelephone());
                }
                telephoneModel.setPays(paysTelephone);
                personnePhysiqueRpcService.controlerTelephone(telephoneModel, new AsyncCallback<CoordonneesModel>() {
                    @Override
                    public void onSuccess(CoordonneesModel result) {
                        view.onRpcServiceSuccess();
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
        });

        final ListBoxPaysProperties paysProperties = new ListBoxPaysProperties() {
            @Override
            public String[] getResultRowsRenderer(PaysSimpleModel row) {
                if (row == null) {
                    return null;
                }
                else {
                    final String[] result = new String[4];
                    result[0] = row.getLibelle();
                    result[1] = String.valueOf(row.getIndicatifTelephone());
                    result[2] = row.getFormatTelephone();
                    result[3] = String.valueOf(row.getId());
                    return result;
                }
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {presenterConstants.titreColonnePays(), presenterConstants.titreColonneIndicatifTelephonique(),
                    presenterConstants.titreColonneFormatTelephonique(), presenterConstants.titreColonneDrapeau()};
            }
        };
        final ModifierPaysTelephoneEventHandler modifierPaysTelephoneEventHandler = new ModifierPaysTelephoneEventHandler() {
            @Override
            public void modifierPaysTelephone(ModifierPaysTelephoneEvent event) {
                if (event.getIsTelephonePrincipal()) {
                    view.chargerImagePaysTelephone(event.getUrlImagePays(), event.getTitleImagePays());
                    view.initTbTelephone(event.getPaysTelephone().getFormatTelephone());
                }
                else {
                    view.chargerImagePaysTelephonePortable(event.getUrlImagePays(), event.getTitleImagePays());
                    view.initTbTelephonePortable(event.getPaysTelephone().getFormatTelephone());
                }
            }
        };
        view.getImgFlagPaysTelephone().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (popupSelectionPaysTel1 == null) {
                    popupSelectionPaysTel1 = addChildPresenter(new PopupSelectionPaysPresenter(eventBus, dimensionServiceGwtAsync,
                        new PopupSelectionPaysViewImpl(paysProperties), true));
                    popupSelectionPaysTel1.showPresenter(null);
                    popupSelectionPaysTel1.addEventHandlerToLocalBus(ModifierPaysTelephoneEvent.TYPE, modifierPaysTelephoneEventHandler);
                } else {
                    popupSelectionPaysTel1.showPopup();
                }
            }
        });
        view.getImgFlagPaysTelephone2().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (popupSelectionPaysTel2 == null) {
                    popupSelectionPaysTel2 = addChildPresenter(new PopupSelectionPaysPresenter(eventBus, dimensionServiceGwtAsync,
                        new PopupSelectionPaysViewImpl(paysProperties), false));
                    popupSelectionPaysTel2.showPresenter(null);
                    popupSelectionPaysTel2.addEventHandlerToLocalBus(ModifierPaysTelephoneEvent.TYPE, modifierPaysTelephoneEventHandler);
                } else {
                    popupSelectionPaysTel2.showPopup();
                }
            }
        });

        view.getTbNom().addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                rechercherDoublonsWarning(view, view.getTbNom().getValue().trim(), view.getTbPrenom().getValue().trim(), view.getCdbDateNaissance().getValue());
            }
        });
        view.getTbPrenom().addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                rechercherDoublonsWarning(view, view.getTbNom().getValue().trim(), view.getTbPrenom().getValue().trim(), view.getCdbDateNaissance().getValue());
            }
        });
        view.getCdbDateNaissance().addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                rechercherDoublonsWarning(view, view.getTbNom().getValue().trim(), view.getTbPrenom().getValue().trim(), view.getCdbDateNaissance().getValue());
            }
        });
        if (view.getConjointView() != null) {
            view.getConjointView().getTbNom().addValueChangeHandler(new ValueChangeHandler<String>() {
                @Override
                public void onValueChange(ValueChangeEvent<String> event) {
                    rechercherDoublonsWarning(view.getConjointView(), view.getConjointView().getTbNom().getValue().trim(),
                        view.getConjointView().getTbPrenom().getValue().trim(), view.getConjointView().getCdbDateNaissance().getValue());
                }
            });
            view.getConjointView().getTbPrenom().addValueChangeHandler(new ValueChangeHandler<String>() {
                @Override
                public void onValueChange(ValueChangeEvent<String> event) {
                    rechercherDoublonsWarning(view.getConjointView(), view.getConjointView().getTbNom().getValue().trim(),
                        view.getConjointView().getTbPrenom().getValue().trim(), view.getConjointView().getCdbDateNaissance().getValue());
                }
            });
            view.getConjointView().getCdbDateNaissance().addValueChangeHandler(new ValueChangeHandler<Date>() {
                @Override
                public void onValueChange(ValueChangeEvent<Date> event) {
                    rechercherDoublonsWarning(view.getConjointView(), view.getConjointView().getTbNom().getValue().trim(),
                        view.getConjointView().getTbPrenom().getValue().trim(), view.getConjointView().getCdbDateNaissance().getValue());
                }
            });
        }
        view.getLbNbEnfantsValue().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                if (view.getListeEnfantsView() != null && view.getListeEnfantsView().size() > 0) {
                    for (final BlocCreationBeneficiaireView blocEnfant : view.getListeEnfantsView()) {
                        // si aucun conjoint n'est renseigné, on cache la checkbox de demande de rattachements aux deux parents
                        if (view.getLbConjointValue().getValue().getLibelle().equals(appControllerConstants.libAucunConjoint())) {
                            blocEnfant.getCbRattacherParents().setVisible(false);
                            blocEnfant.getCbRattacherParents().setValue(false);
                        }
                        else {
                            blocEnfant.getCbRattacherParents().setVisible(true);
                            blocEnfant.getCbRattacherParents().setValue(true);
                        }
                        blocEnfant.getTbNom().addValueChangeHandler(new ValueChangeHandler<String>() {
                            @Override
                            public void onValueChange(ValueChangeEvent<String> event) {
                                rechercherDoublonsWarning(blocEnfant, blocEnfant.getTbNom().getValue().trim(), blocEnfant.getTbPrenom().getValue().trim(),
                                    blocEnfant.getCdbDateNaissance().getValue());
                            }
                        });
                        blocEnfant.getTbPrenom().addValueChangeHandler(new ValueChangeHandler<String>() {
                            @Override
                            public void onValueChange(ValueChangeEvent<String> event) {
                                rechercherDoublonsWarning(blocEnfant, blocEnfant.getTbNom().getValue().trim(), blocEnfant.getTbPrenom().getValue().trim(),
                                    blocEnfant.getCdbDateNaissance().getValue());
                            }
                        });
                        blocEnfant.getCdbDateNaissance().addValueChangeHandler(new ValueChangeHandler<Date>() {
                            @Override
                            public void onValueChange(ValueChangeEvent<Date> event) {
                                rechercherDoublonsWarning(blocEnfant, blocEnfant.getTbNom().getValue().trim(), blocEnfant.getTbPrenom().getValue().trim(),
                                    blocEnfant.getCdbDateNaissance().getValue());
                            }
                        });
                    }
                }
            }
        });
        view.getLbNbEnfants().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                dimensionServiceGwtAsync.rechercherListeNombreEnfants(AppControllerConstants.NB_ENFANTS_MAX, appControllerConstants.libNoEnfants(),
                    appControllerConstants.libEnfant(), appControllerConstants.libEnfants(), event.getSuggest(), event.getCallBack());
            }
        });
        view.getLbConjoint().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {

            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                dimensionServiceGwtAsync.rechercherListeConjoint(appControllerConstants.libAucunConjoint(), appControllerConstants.libUnConjoint(), event
                        .getSuggest(), event.getCallBack());
            }
        });

        deskBarRegistration = deskBar.getEventBus().addHandler(EnableMinimizeWidgetEvent.TYPE, new EnableMinimizeWidgetEventHandler() {
            @Override
            public void enableMinimizeWidget(EnableMinimizeWidgetEvent event) {
                view.activerBoutonReduire(event.isEnabled());
            }
        });
    }

    /** Lance la création d'une personne physique avec vérification des doublons. */
    private void lancerCreationPersonnePhysique() {
        if (isDatesValides()) {
            recupererPersonnes();
            rechercherDoublons();
        }
    }

    /**
     * Recherche les civilites.
     * @param libelle le libelle
     * @param callback le callback
     */
    private void rechercherCiviliteParCriteres(String libelle, AsyncCallback<List<IdentifiantLibelleGwt>> callback) {
        final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
        criteres.setLibelle(libelle);
        if (!"".equals(libelle)) {
            criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
        }
        dimensionServiceGwtAsync.rechercherCiviliteParCriteres(criteres, callback);
    }

    /**
     * Désactive le bloc du conjoint.
     */
    private void afficheMasqueBlocCommune() {
        boolean activer = false;
        if (view.getSlbPaysValue().getValue() != null && view.getSlbPaysValue().getValue().getIdentifiant() != null) {
            activer = view.getSlbPaysValue().getValue().getIdentifiant().equals(constantes.getIdPaysFrance());
        }
        if (activer) {
            view.getTbCodePostalEtranger().setValue("");
            view.getTbCommuneEtranger().setValue("");
            view.afficheBlocCoordonneesFrance(true);
        }
        else {
            view.nettoyerCodePostal();
            view.getLValueVille().setText("");
            view.afficheBlocCoordonneesFrance(false);
        }
    }

    /**
     * Nettoie.
     */
    public void nettoyer() {
        final Long idCodePaysDefaut = new Long(constantes.getIdPaysParDefaut());

        // Pays par défaut
        final DimensionCriteresRechercheModel critPays = new DimensionCriteresRechercheModel(constantes.getIdPaysParDefaut());
        dimensionServiceGwtAsync.rechercherPaysParCriteres(critPays, new AsyncCallback<List<IdentifiantLibelleBooleanModel>>() {

            @Override
            public void onSuccess(List<IdentifiantLibelleBooleanModel> result) {
                view.getSlbPaysValue().setValue(result.get(0));
            }

            @Override
            public void onFailure(Throwable caught) {
                view.getSlbPaysValue().setValue(new IdentifiantLibelleBooleanModel());

            }
        });
        // Telephone
        DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel(constantes.getIdNatureTelephoneFixe());
        criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
        dimensionServiceGwtAsync.rechercherNatureTelephoneParCriteres(criteres, new AsyncCallback<List<IdentifiantLibelleGwt>>() {
            @Override
            public void onSuccess(List<IdentifiantLibelleGwt> result) {
                view.getSlbNatureTelephoneValue().setValue(result.get(0));
            }

            @Override
            public void onFailure(Throwable caught) {
                view.getSlbNatureTelephoneValue().setValue(new IdentifiantLibelleGwt());
            }
        });

        criteres = new DimensionCriteresRechercheModel(constantes.getIdNatureMobilePrive());
        criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
        dimensionServiceGwtAsync.rechercherNatureTelephoneParCriteres(criteres, new AsyncCallback<List<IdentifiantLibelleGwt>>() {
            @Override
            public void onSuccess(List<IdentifiantLibelleGwt> result) {
                view.getSlbNatureTelephonePortableValue().setValue(result.get(0));
            }

            @Override
            public void onFailure(Throwable caught) {
                view.getSlbNatureTelephonePortableValue().setValue(new IdentifiantLibelleGwt());
            }
        });

        view.setIdPaysTelephone(idCodePaysDefaut);
        view.setIdPaysTelephone2(idCodePaysDefaut);

        view.getLbNbEnfantsValue().setValue(new IdentifiantLibelleGwt(0L, appControllerConstants.libNoEnfants()));
        view.getLbConjointValue().setValue(new IdentifiantLibelleGwt(0L, appControllerConstants.libAucunConjoint()));

        view.getCdbDateNaissance().clean();
        view.getConjointView().getCbRattacherParents().setEnabled(false);
        view.getConjointView().getSlbCivilite().clean();
        view.getConjointView().getTbNom().setValue("");
        view.getConjointView().getTbPrenom().setValue("");
        view.getListeEnfantsView().clear();
        view.getLValueVille().setText("");
        view.getSlbCiviliteValue().setValue(new IdentifiantLibelleGwt());
        view.getSlbCodePostalValue().setValue(new CodePostalCommuneModel());
        view.getSlbNatureVoieValue().setValue(new IdentifiantLibelleGwt());
        view.getSlbProfessionValue().setValue(new IdentifiantLibelleBooleanModel());
        view.getTbAdresse().setValue("");
        view.getTbAutresComplements().setValue("");
        view.getTbCodePostalEtranger().setValue("");
        view.getTbCommuneEtranger().setValue("");
        view.getTbComplementAdresse().setValue("");
        view.getTbComplementNom().setValue("");
        view.getTbEmail().setValue("");
        view.getTbNom().setValue("");
        view.getTbNumeroVoie().setValue("");
        view.getTbPrenom().setValue("");
        view.getTbTelephone().setValue("");
        view.getTbTelephonePortable().setValue("");

        view.showPopup();

        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                view.setFocusCivilite();
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

        nettoyer();

        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                // on ajoute la popup à la deskBar
                deskBar.addPopup(view.getMinimizablePopup());
            }
        });
    }

    /**
     * Recupere les personnes à creer.
     */
    private void recupererPersonnes() {
        nbPersonnesPourCreation = 0;
        nbPersonnesRechercheesPourCreation = 0;
        listeBeneficiaireACreerEnCours = new ArrayList<BeneficiaireModel>();
        mapEnfantsACreerEnCours = new HashMap<Integer, BeneficiaireModel>();
        conjointACreerEnCours = null;

        // Personne principale
        personneACreerEnCours = new PersonneModel();
        personneACreerEnCours.setCivilite(view.getSlbCiviliteValue().getValue());
        personneACreerEnCours.setNom(view.getTbNom().getValue().trim());
        personneACreerEnCours.setPrenom(view.getTbPrenom().getValue().trim());
        if (view.getCdbDateNaissance().getValue() != null) {
            personneACreerEnCours.setDateNaissance(DateUtil.getString(view.getCdbDateNaissance().getValue()));
        }
        personneACreerEnCours.setProfession(view.getSlbProfessionValue().getValue());
        if (!view.getSlbPaysValue().getValue().getIdentifiant().equals(constantes.getIdPaysFrance())
                && !view.getSlbPaysValue().getValue().getIdentifiant().equals(constantes.getIdPaysFranceMetropolitaine())) {
            personneACreerEnCours.setCommercial(ressourceConnectee);
        }
        nbPersonnesPourCreation++;

        // Bénéficiaires
        // Conjoint
        if (view.getLbConjointValue().getValue() != null && view.getLbConjointValue().getValue().getIdentifiant() > 0) {
            conjointACreerEnCours = new BeneficiaireModel();
            conjointACreerEnCours.setCivilite(view.getConjointView().getSlbCivilite().getValue());
            conjointACreerEnCours.setNom(view.getConjointView().getTbNom().getValue().trim());
            conjointACreerEnCours.setPrenom(view.getConjointView().getTbPrenom().getValue().trim());
            if (view.getConjointView().getCdbDateNaissance().getValue() != null) {
                conjointACreerEnCours.setDateNaissance(DateUtil.getString(view.getConjointView().getCdbDateNaissance().getValue()));
            }
            conjointACreerEnCours.setTypeRelation(new IdentifiantLibelleGwt(constantes.getIdTypeRelationConjoint()));
            conjointACreerEnCours.setIndex(0);
            listeBeneficiaireACreerEnCours.add(conjointACreerEnCours);
            nbPersonnesPourCreation++;
        }
        // Enfants
        if (view.getListeEnfantsView() != null && view.getListeEnfantsView().size() > 0) {
            for (BlocCreationBeneficiaireView blocEnfant : view.getListeEnfantsView()) {
                final BeneficiaireModel enfant = new BeneficiaireModel();
                enfant.setCivilite(blocEnfant.getSlbCivilite().getValue());
                enfant.setNom(blocEnfant.getTbNom().getValue().trim());
                enfant.setPrenom(blocEnfant.getTbPrenom().getValue().trim());
                if (blocEnfant.getCdbDateNaissance().getValue() != null) {
                    enfant.setDateNaissance(DateUtil.getString(blocEnfant.getCdbDateNaissance().getValue()));
                }
                enfant.setTypeRelation(new IdentifiantLibelleGwt(constantes.getIdTypeRelationEnfant()));
                enfant.setIndex(blocEnfant.getIndex());
                enfant.setRattacherAuxParents(blocEnfant.getCbRattacherParents().getValue());
                mapEnfantsACreerEnCours.put(blocEnfant.getIndex(), enfant);
                listeBeneficiaireACreerEnCours.add(enfant);
                nbPersonnesPourCreation++;
            }
        }
    }

    /**
     * Recherche s'il existe des doublons avant la création.
     */
    private void rechercherDoublons() {
        listeBeneficiaireACreerEnCours.clear();
        // Préparation de la liste pour afficher les différentes popups
        personnesATester = new Stack<PersonneBaseModel>();
        for (BeneficiaireModel enfant : mapEnfantsACreerEnCours.values()) {
            personnesATester.push(enfant);
        }
        if (conjointACreerEnCours != null) {
            personnesATester.push(conjointACreerEnCours);
        }
        personnesATester.push(personneACreerEnCours);

        rechercherDoublonsPersonne();
    }

    /**
     * Recherche si une personne a des doublons.
     * @param personne la personne.
     * @param isBeneficiaire indique si la personne est un bénéficiaire.
     */
    private void rechercherDoublonsPersonne() {
        //final PersonneBaseModel personne, final boolean isBeneficiaire, final int indexBeneficiaire) {
        // Création des critères de recherche
        final PersonneBaseModel personne = personnesATester.pop();
        final boolean isBeneficiaire = (conjointACreerEnCours != null && conjointACreerEnCours.equals(personne))
                || (mapEnfantsACreerEnCours != null && mapEnfantsACreerEnCours.values().contains(personne));
        int idxBeneficiaire = 0;
        if (isBeneficiaire) {
            if (mapEnfantsACreerEnCours.values().contains(personne)) {
                for (Entry<Integer, BeneficiaireModel> entry : mapEnfantsACreerEnCours.entrySet()) {
                    if (entry.getValue().equals(personne)) {
                        idxBeneficiaire = entry.getKey();
                    }
                }
            }
        }
        final int indexBeneficiaire = idxBeneficiaire;

        final RechercheDoublonCritereModel criteres = new RechercheDoublonCritereModel();
        String nom = "";
        String prenom = "";
        if (personne instanceof PersonneModel) {
            final PersonneModel personneModel = (PersonneModel) personne;
            nom = personneModel.getNom();
            criteres.setNom(nom);
            prenom = personneModel.getPrenom();
            criteres.setPrenom(prenom);
            criteres.setDateNaissance(personneModel.getDateNaissance());
        }
        else if (personne instanceof BeneficiaireModel) {
            final BeneficiaireModel beneficiaire = (BeneficiaireModel) personne;
            nom = beneficiaire.getNom();
            criteres.setNom(nom);
            prenom = beneficiaire.getPrenom();
            criteres.setPrenom(prenom);
            criteres.setDateNaissance(beneficiaire.getDateNaissance());
        }
        final String nomPersonne = nom;
        final String prenomPersonne = prenom;

        // appel du service
        final AsyncCallback<List<PersonneDoublonModel>> asyncCallback = new AsyncCallback<List<PersonneDoublonModel>>() {
            @Override
            public void onSuccess(List<PersonneDoublonModel> listeDoublons) {
                if (listeDoublons.size() > 0) {
                    if (popupCreationPersonneDoublonPresenter == null) {
                        popupCreationPersonneDoublonPresenter =
                            addChildPresenter(new PopupCreationPersonneDoublonPresenter(eventBus,
                                new PopupCreationPersonneDoublonViewImpl(nomPersonne, prenomPersonne,
                            !isBeneficiaire, listeDoublons), personne,
                            isBeneficiaire, false));
                        popupCreationPersonneDoublonPresenter.showPresenter(null);
                        popupCreationPersonneDoublonPresenter.addEventHandlerToLocalBus(CreerPersonneEvent.TYPE, new CreerPersonneEventHandler() {
                            @Override
                            public void creerPersonne(CreerPersonneEvent event) {
                                if (event.isBeneficiaire()) {
                                    final BeneficiaireModel beneficiaire = (BeneficiaireModel) event.getPersonne();
                                    listeBeneficiaireACreerEnCours.add(beneficiaire);
                                }
                                else {
                                    personneACreerEnCours = (PersonneModel) event.getPersonne();
                                }
                                nbPersonnesRechercheesPourCreation++;
                                // On lance la création de la personne et de ses bénéficiaires que lorsque l'on a tout récupéré.
                                if (nbPersonnesRechercheesPourCreation == nbPersonnesPourCreation) {
                                    creerPersonnePhysique();
                                }
                            }
                        });
                        popupCreationPersonneDoublonPresenter.addEventHandlerToLocalBus(
                            SimpleValueChangeEvent.TYPE, new SimpleValueChangeEventHandler<Object>() {
                                @Override
                                public void onValueChange(SimpleValueChangeEvent event) {
                                    if (!personnesATester.isEmpty()) {
                                        rechercherDoublonsPersonne();
                                    }
                                }
                        });
                    } else {
                        popupCreationPersonneDoublonPresenter.showPopup(nomPersonne, prenomPersonne, !isBeneficiaire, listeDoublons);
                    }
                }
                else {
                    if (isBeneficiaire) {
                        final BeneficiaireModel beneficiaire = (BeneficiaireModel) personne;
                        beneficiaire.setIndex(indexBeneficiaire);
                        listeBeneficiaireACreerEnCours.add(beneficiaire);
                    }
                    else {
                        personneACreerEnCours = (PersonneModel) personne;
                    }
                    nbPersonnesRechercheesPourCreation++;

                    // On lance la création de la personne et de ses bénéficiaires que lorsque l'on a tout récupéré.
                    if (nbPersonnesRechercheesPourCreation == nbPersonnesPourCreation) {
                        creerPersonnePhysique();
                    } else {
                        rechercherDoublonsPersonne();
                    }
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        };
        personnePhysiqueRpcService.rechercherDoublonsPersonneParCriteres(criteres, asyncCallback);
    }

    /**
     * Recherche si une personne a des doublons pour prévenit l'utilisateur lors de la saisie.
     * @param vue la vue où afficher le warning.
     * @param nom le nom de la personne.
     * @param prenom le prénom de la personne.
     * @param date la date de naissance de la personne.
     */
    private void rechercherDoublonsWarning(final View vue, String nom, String prenom, Date date) {
        if (nom != null && !"".equals(nom)) {
            if (prenom != null && !"".equals(prenom)) {
                if (date != null) {
                    final String dateNaissance = DateUtil.getString(date);
                    if (dateNaissance != null && !"".equals(dateNaissance)) {
                        final RechercheDoublonCritereModel criteres = new RechercheDoublonCritereModel();
                        criteres.setNom(nom);
                        criteres.setPrenom(prenom);
                        criteres.setDateNaissance(dateNaissance);
                        final AsyncCallback<List<PersonneDoublonModel>> asyncCallback = new AsyncCallback<List<PersonneDoublonModel>>() {
                            @Override
                            public void onSuccess(List<PersonneDoublonModel> listeDoublons) {
                                if (listeDoublons.size() > 0) {
                                    if (vue instanceof PersonnePhysiqueCreationView) {
                                        final PersonnePhysiqueCreationView vuePersonne = (PersonnePhysiqueCreationView) vue;
                                        vuePersonne.afficherWarningDoublons();
                                    }
                                    else if (vue instanceof BlocCreationBeneficiaireView) {
                                        final BlocCreationBeneficiaireView vueBenef = (BlocCreationBeneficiaireView) vue;
                                        vueBenef.afficherWarningDoublons();
                                    }
                                    else {
                                        view.onRpcServiceFailure(new ErrorPopupConfiguration(presenterConstants.erreurRechercheDoublons()));
                                    }
                                }
                                else {
                                    if (vue instanceof PersonnePhysiqueCreationView) {
                                        final PersonnePhysiqueCreationView vuePersonne = (PersonnePhysiqueCreationView) vue;
                                        vuePersonne.masquerWarningDoublons();
                                    }
                                    else if (vue instanceof BlocCreationBeneficiaireView) {
                                        final BlocCreationBeneficiaireView vueBenef = (BlocCreationBeneficiaireView) vue;
                                        vueBenef.masquerWarningDoublons();
                                    }
                                    else {
                                        view.onRpcServiceFailure(new ErrorPopupConfiguration(presenterConstants.erreurRechercheDoublons()));
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                            }
                        };
                        personnePhysiqueRpcService.rechercherDoublonsPersonneParCriteres(criteres, asyncCallback);
                    }
                    else {
                        if (vue instanceof PersonnePhysiqueCreationView) {
                            final PersonnePhysiqueCreationView vuePersonne = (PersonnePhysiqueCreationView) vue;
                            vuePersonne.masquerWarningDoublons();
                        }
                        else if (vue instanceof BlocCreationBeneficiaireView) {
                            final BlocCreationBeneficiaireView vueBenef = (BlocCreationBeneficiaireView) vue;
                            vueBenef.masquerWarningDoublons();
                        }
                        else {
                            view.onRpcServiceFailure(new ErrorPopupConfiguration(presenterConstants.erreurRechercheDoublons()));
                        }
                    }
                }
                else {
                    if (vue instanceof PersonnePhysiqueCreationView) {
                        final PersonnePhysiqueCreationView vuePersonne = (PersonnePhysiqueCreationView) vue;
                        vuePersonne.masquerWarningDoublons();
                    }
                    else if (vue instanceof BlocCreationBeneficiaireView) {
                        final BlocCreationBeneficiaireView vueBenef = (BlocCreationBeneficiaireView) vue;
                        vueBenef.masquerWarningDoublons();
                    }
                    else {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(presenterConstants.erreurRechercheDoublons()));
                    }
                }
            }
            else {
                if (vue instanceof PersonnePhysiqueCreationView) {
                    final PersonnePhysiqueCreationView vuePersonne = (PersonnePhysiqueCreationView) vue;
                    vuePersonne.masquerWarningDoublons();
                }
                else if (vue instanceof BlocCreationBeneficiaireView) {
                    final BlocCreationBeneficiaireView vueBenef = (BlocCreationBeneficiaireView) vue;
                    vueBenef.masquerWarningDoublons();
                }
                else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(presenterConstants.erreurRechercheDoublons()));
                }
            }
        }
        else {
            if (vue instanceof PersonnePhysiqueCreationView) {
                final PersonnePhysiqueCreationView vuePersonne = (PersonnePhysiqueCreationView) vue;
                vuePersonne.masquerWarningDoublons();
            }
            else if (vue instanceof BlocCreationBeneficiaireView) {
                final BlocCreationBeneficiaireView vueBenef = (BlocCreationBeneficiaireView) vue;
                vueBenef.masquerWarningDoublons();
            }
            else {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(presenterConstants.erreurRechercheDoublons()));
            }
        }
    }

    /**
     * Crée une personne.
     */
    private void creerPersonnePhysique() {
        final AdresseModel adresse = new AdresseModel();
        adresse.setNumVoie(view.getTbNumeroVoie().getValue());
        adresse.setTypeVoie(view.getSlbNatureVoieValue().getValue());
        adresse.setComplementNom(view.getTbComplementNom().getValue());
        adresse.setNomVoie(view.getTbAdresse().getValue());
        adresse.setComplementAdresse(view.getTbComplementAdresse().getValue());
        adresse.setAutresComplements(view.getTbAutresComplements().getValue());
        adresse.setCodePostalEtranger(view.getTbCodePostalEtranger().getValue());
        adresse.setCommuneEtranger(view.getTbCommuneEtranger().getValue());
        final CodePostalCommuneModel codePostalCommune = view.getSlbCodePostalValue().getValue();
        if (codePostalCommune != null) {
            adresse.setCodePostalCommune(codePostalCommune);
        }
        adresse.setPays(view.getSlbPaysValue().getValue());

        final EmailModel email = new EmailModel();
        email.setAdresse(view.getTbEmail().getValue());
        // Premier telephone
        final TelephoneModel telephone = new TelephoneModel();
        telephone.setNature(view.getSlbNatureTelephoneValue().getValue());
        telephone.setNumero(view.getTbTelephone().getValue());
        final PaysSimpleModel paysTel1 = new PaysSimpleModel();
        if (popupSelectionPaysTel1 != null) {
            paysTel1.setId(popupSelectionPaysTel1.getIdPaysSelectionne());
        }
        else {
            paysTel1.setId(view.getIdPaysTelephone());
        }
        telephone.setPays(paysTel1);
        // Deuxieme telephone
        final TelephoneModel telephonePortable = new TelephoneModel();
        telephonePortable.setNature(view.getSlbNatureTelephonePortableValue().getValue());
        telephonePortable.setNumero(view.getTbTelephonePortable().getValue());
        final PaysSimpleModel paysTel2 = new PaysSimpleModel();
        if (popupSelectionPaysTel2 != null) {
            paysTel2.setId(popupSelectionPaysTel2.getIdPaysSelectionne());
        }
        else {
            paysTel2.setId(view.getIdPaysTelephone2());
        }
        telephonePortable.setPays(paysTel2);

        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().creationPersonne()));
        final CoordonneesModel cordonneesPersonne = new CoordonneesModel();
        cordonneesPersonne.getAdresses().add(adresse);
        cordonneesPersonne.getEmails().add(email);
        cordonneesPersonne.getTelephones().add(telephone);
        cordonneesPersonne.getTelephones().add(telephonePortable);

        // On affecte le statut prospect à la personne principale
        personneACreerEnCours.setNaturePersonne(new IdentifiantLibelleGwt(constantes.getIdNaturePersonneProspect()));

        trierListeBeneficiaires();
        personnePhysiqueRpcService.creerPersonnePhysique(personneACreerEnCours, listeBeneficiaireACreerEnCours, cordonneesPersonne,
            new AsyncCallback<PersonneModel>() {
            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                    // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                    view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                    view.masquerLoadingPopup();
                    view.setFocusCivilite();
                }
                else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }

            @Override
            public void onSuccess(final PersonneModel personne) {
                view.onRpcServiceSuccess();
                view.hidePopup();
                final PopupInfoConfiguration config = new PopupInfoConfiguration(presenterConstants.notificationPersonneCreee(),
                    AppControllerConstants.NOTIFICATION_TIME_OUT);
                config.setCallback(new PopupCallback() {

                    @Override
                    public void onResult(boolean result) {
                        fireEventGlobalBus(new OpenPersonEvent(personne.getIdentifiant(), personne.getNumClient(),
                        personne.getNom() != null ? personne.getNom() : "", personne.getPrenom() != null ? personne.getPrenom() : "",
                        (personne.getNaturePersonne() != null) ? personne.getNaturePersonne().getIdentifiant() : null));
                    }
                });
                new DecoratedInfoPopup(config).show();
            }
        });
    }

    /**
     * Permet de trier la liste des beneficiaires de façon à mettre le conjoint en tête de la liste.
     */
    private void trierListeBeneficiaires() {
        for (int i = 0; i < listeBeneficiaireACreerEnCours.size(); i++) {
            final BeneficiaireModel beneficiaire = listeBeneficiaireACreerEnCours.get(i);
            // si on tombe sur le conjoint et qu'il n'est pas déjà en début de liste, en le met en premiere position
            if (beneficiaire.getTypeRelation().getIdentifiant().equals(constantes.getIdTypeRelationConjoint())) {
                if (i > 0) {
                    listeBeneficiaireACreerEnCours.remove(i);
                    listeBeneficiaireACreerEnCours.add(0, beneficiaire);
                }
                break;
            }
        }
    }

    /**
     * Teste la validité des dates.
     * @return true si les dates sont valides, false sinon
     */
    private boolean isDatesValides() {
        boolean datesValides = true;
        final RapportModel rapport = new RapportModel();
        if (!view.getCdbDateNaissance().isDateValide()) {
            rapport.ajoutRapport(presenterConstants.champDateNaissancePersonne(), presenterConstants.erreurDateNaissancePersonneInvalide(), true);
            datesValides = false;
        }
        // Bénéficiaires
        int idxBeneficiaires = 0;
        // Conjoint
        if (view.getLbConjointValue().getValue() != null && view.getLbConjointValue().getValue().getIdentifiant() > 0) {
            if (!view.getConjointView().getCdbDateNaissance().isDateValide()) {
                rapport.ajoutRapport(presenterConstants.champDateNaissanceBeneficiaire() + idxBeneficiaires,
                    presenterConstants.erreurDateNaissanceConjointInvalide(), true);
                datesValides = false;
            }
            idxBeneficiaires++;
        }
        // Enfants
        if (view.getListeEnfantsView() != null && view.getListeEnfantsView().size() > 0) {
            for (BlocCreationBeneficiaireView blocEnfant : view.getListeEnfantsView()) {
                if (!blocEnfant.getCdbDateNaissance().isDateValide()) {
                    rapport.ajoutRapport(presenterConstants.champDateNaissanceBeneficiaire() + idxBeneficiaires,
                        presenterConstants.erreurDateNaissanceEnfantInvalide(), true);
                    datesValides = false;
                }
                idxBeneficiaires++;
            }
        }
        view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
        if (!datesValides) {
            view.masquerLoadingPopup();
        }
        return datesValides;
    }

    /**
     * Interface pour la vue PersonnePhysiqueCreationView.
     */
    public interface PersonnePhysiqueCreationView extends View {
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
         * Retourne le widget.
         * @return le widget
         */
        DecoratedCalendrierDateBox getCdbDateNaissance();

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedTextBoxFormat getTbTelephone();

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedTextBox getTbEmail();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbConjoint();

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedTextBox getTbNumeroVoie();

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedTextBox getTbComplementNom();

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedTextBox getTbAdresse();

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedTextBox getTbComplementAdresse();

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedTextBox getTbAutresComplements();

        /**
         * Retourne le bouton.
         * @return le bouton
         */
        HasClickHandlers getBtnCreer();

        /**
         * Retourne le lien.
         * @return le lien
         */
        HasClickHandlers getLienAnnuler();

        /**
         * Affiche un message de chargement.
         * @param config loadingPopupConfiguration la config
         */
        void afficherLoadingPopup(LoadingPopupConfiguration config);

        /**
         * Masque un message de chargement.
         */
        void masquerLoadingPopup();

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
         * Methode pour afficher la popup.
         */
        void showPopup();

        /**
         * Methode pour masquer la popup.
         */
        void hidePopup();

        /**
         * Valeur du suggestHandler civilite.
         * @return .
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbCivilite();

        /**
         * Valeur du suggestHandler civilite.
         * @return .
         */
        HasValue<IdentifiantLibelleGwt> getSlbCiviliteValue();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNatureVoie();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<IdentifiantLibelleGwt> getSlbNatureVoieValue();

        /**
         * Retourne le widget.
         * @return le widget
         */
        Label getLValueVille();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNatureTelephone();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<IdentifiantLibelleGwt> getSlbNatureTelephoneValue();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleBooleanModel> getSlbProfession();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<IdentifiantLibelleBooleanModel> getSlbProfessionValue();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleBooleanModel> getSlbPays();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<IdentifiantLibelleBooleanModel> getSlbPaysValue();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<IdentifiantLibelleGwt> getLbNbEnfantsValue();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbNbEnfants();

        /**
         * Retourne l'id.
         * @return l'id
         */
        Long getIdPaysTelephone();

        /**
         * Définit l'id.
         * @param id l'id
         */
        void setIdPaysTelephone(Long id);

        /**
         * Retourne l'id.
         * @return l'id
         */
        Long getIdPaysTelephone2();

        /**
         * Définit l'id.
         * @param id l'id
         */
        void setIdPaysTelephone2(Long id);

        /**
         * Retourne la vue du conjoint.
         * @return la vue
         */
        BlocCreationBeneficiaireView getConjointView();

        /**
         * Retourne les vues des enfants.
         * @return les vues
         */
        List<BlocCreationBeneficiaireViewImpl> getListeEnfantsView();

        /**
         * Affiche le nombre de blocs enfants correspondants.
         * @param nbEnfants le nb de bloc enfants
         * @return les blocs de création des bénéficiaires
         */
        List<BlocCreationBeneficiaireView> afficherBlocsEnfants(Integer nbEnfants);

        /**
         * Affiche / Masque le bloc conjoint.
         * @param affiche vrai ou faux selon ce que l'on souhaite
         */
        void afficherBlocConjoint(Boolean affiche);

        /**
         * Retourne les constantes.
         * @return les constantes.
         */
        PersonnePhysiqueCreationViewImplConstants getViewConstants();

        /**
         * Retourne le widget.
         * @return le widget
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Retourne la commune pour l'étranger.
         * @return le composant
         */
        HasValue<String> getTbCommuneEtranger();

        /**
         * Retourne la code postal pour l'étranger.
         * @return le composant
         */
        HasValue<String> getTbCodePostalEtranger();

        /**
         * Affiche Masque le bloc coordonnées France.
         * @param affiche visible ou pas
         */
        void afficheBlocCoordonneesFrance(Boolean affiche);

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNatureTelephonePortable();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<IdentifiantLibelleGwt> getSlbNatureTelephonePortableValue();

        /**
         * Rtroune le widget.
         * @return le widget
         */
        DecoratedTextBoxFormat getTbTelephonePortable();

        /**
         * Rtroune le widget.
         * @return le widget
         */
        HasValue<IdentifiantLibelleGwt> getLbConjointValue();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<CodePostalCommuneModel> getSlbCodePostal();

        /**
         * Retourne la valeur.
         * @return la valeur
         */
        HasValue<CodePostalCommuneModel> getSlbCodePostalValue();

        /**
         * Nettoie le code postal.
         */
        void nettoyerCodePostal();

        /**
         * Accesseur sur l'image du pays du téléphone 1.
         * @return le widget.
         */
        HasClickHandlers getImgFlagPaysTelephone();

        /**
         * Accesseur sur l'image du pays du téléphone 2.
         * @return le widget.
         */
        HasClickHandlers getImgFlagPaysTelephone2();

        /**
         * Charge l'image du pays du téléphone.
         * @param url l'url de l'image.
         * @param title le title de l'image.
         */
        void chargerImagePaysTelephone(String url, String title);

        /**
         * Charge l'image du pays du téléphone portable.
         * @param url l'url de l'image.
         * @param title le title de l'image.
         */
        void chargerImagePaysTelephonePortable(String url, String title);

        /**
         * Initialise la TextBox du téléphone.
         * @param format le nouveau format.
         */
        void initTbTelephone(String format);

        /**
         * Initialise la TextBox du téléphone.
         * @param format le nouveau format.
         */
        void initTbTelephonePortable(String format);

        /**
         * Définit le focus sur le champ civilite.
         */
        void setFocusCivilite();

        /**
         * Affiche un message d'alerte pour prévenir de la présence d'un doublon.
         */
        void afficherWarningDoublons();

        /**
         * Masque le message d'alerte pour prévenir de la présence d'un doublon.
         */
        void masquerWarningDoublons();

        /**
         * Accesseur AllKeyHandlers.
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

    /**
     * Interface pour la vue BlocCreationBeneficiaireView.
     */
    public interface BlocCreationBeneficiaireView extends View {

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbCivilite();

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
         * Retourne le widget.
         * @return le widget
         */
        DecoratedCalendrierDateBox getCdbDateNaissance();

        /**
         * Affiche un message d'alerte pour prévenir de la présence d'un doublon.
         */
        void afficherWarningDoublons();

        /**
         * Masque le message d'alerte pour prévenir de la présence d'un doublon.
         */
        void masquerWarningDoublons();

        /**
         * Retourne l'index du bloc.
         * @return l'index du bloc.
         */
        int getIndex();

        /**
         * Renvoi la valeur de cbRattacherParents.
         * @return cbRattacherParents
         */
        CheckBox getCbRattacherParents();

    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
        deskBarRegistration.removeHandler();
    }
}

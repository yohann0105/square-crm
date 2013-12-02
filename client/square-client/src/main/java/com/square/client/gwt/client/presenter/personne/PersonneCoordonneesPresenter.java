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
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.evenement.FormulaireChangeHandlerUtil;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;
import org.scub.foundation.framework.gwt.module.client.util.popup.alert.AlertPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.alert.AlertPopup.AlertPopupType;
import org.scub.foundation.framework.gwt.module.client.util.popup.confirm.ConfirmPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.PopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.AppellerPersonneEvent;
import com.square.client.gwt.client.event.ModifierPaysTelephoneEvent;
import com.square.client.gwt.client.event.ModifierPaysTelephoneEventHandler;
import com.square.client.gwt.client.event.RafraichirCoordonneesEvent;
import com.square.client.gwt.client.event.RafraichirCoordonneesEventHandler;
import com.square.client.gwt.client.event.RafraichirPersonneEvent;
import com.square.client.gwt.client.exception.ConfirmationDesactivationEserviceExceptionGwt;
import com.square.client.gwt.client.exception.ConfirmationImpacterFamilleExceptionGwt;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.client.gwt.client.model.AdresseModel;
import com.square.client.gwt.client.model.CodePostalCommuneModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.CoordonneesModel;
import com.square.client.gwt.client.model.DimensionCritereCodePostalCommuneModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheDepartementModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.EmailModel;
import com.square.client.gwt.client.model.IdentifiantLibelleBooleanModel;
import com.square.client.gwt.client.model.IdentifiantLibelleDepartementCodeModel;
import com.square.client.gwt.client.model.PaysSimpleModel;
import com.square.client.gwt.client.model.TelephoneModel;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.view.personne.coordonnees.BlocCoordonneesAdresseViewImpl;
import com.square.client.gwt.client.view.personne.coordonnees.PersonneCoordonneesViewImplConstants;
import com.square.client.gwt.client.view.personne.coordonnees.PopupCoordonneesAdresseViewImpl;
import com.square.client.gwt.client.view.personne.coordonnees.PopupSelectionPaysViewImpl;
import com.square.client.gwt.client.view.personne.coordonnees.PopupSelectionPaysViewImpl.ListBoxPaysProperties;
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
import com.square.composants.graphiques.lib.client.composants.DecoratedTextBoxFormat;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;
import com.square.composants.graphiques.lib.client.popups.minimizable.DeskBar;

/**
 * Presenter pour la page coordonnées de personne .
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class PersonneCoordonneesPresenter extends Presenter {

    /** Service asynchrone. */
    private PersonneServiceGwtAsync personneRpcService;

    private DimensionServiceGwtAsync dimensionServiceGwtAsync;

    /** Vue associé au presenter. */
    private PersonneCoordonneesView view;

    /** Constantes du presenter. */
    private PersonneCoordonneesPresenterConstants presenterConstants;

    /** L'id de la personne en cours. */
    private Long idPersonne;

    private int indexAdresses;

    private SuggestListBoxSuggestEventHandler<IdentifiantLibelleBooleanModel> requestHandlerPays;

    private ConstantesModel constantes;

    /** Modele Coordonnee en cours. */
    private CoordonneesModel coordonneesModel;

    /** Popup de sélection du pays du téléphone 1. */
    private PopupSelectionPaysPresenter popupSelectionPaysTel1;

    /** Popup de sélection du pays du téléphone 2. */
    private PopupSelectionPaysPresenter popupSelectionPaysTel2;

    /** Liste des natures de téléphones visibles. */
    private List<Long> listeIdsNaturesTelephoneVisibles;

    private DeskBar deskBar;

    /** Service de gestion des aides . */
    private AideServiceGwtAsync aideService;

    private Boolean impacterFamille;

    private Boolean forcerDesactivationEservice;

    //LISTE DES PRESENTER
    private PopupCoordonneesAdressePresenter popupCoordonneesPresenter;


    /**
     * Constructeur par defaut.
     * @param eventBus le bus des évenements
     * @param personneRpcService service rpc pour les personnes
     * @param dimensionServiceGwtAsync service rpc pour les dimensions
     * @param view la vue associé au presenter
     * @param appControllerConstants les constantes d'application
     * @param constantes les constantes
     * @param idPersonne l'identifiant de la personne
     * @param deskBar deskBar
     * @param aideService aideService
     */
    public PersonneCoordonneesPresenter(HandlerManager eventBus, PersonneServiceGwtAsync personneRpcService, DimensionServiceGwtAsync dimensionServiceGwtAsync,
        PersonneCoordonneesView view, AppControllerConstants appControllerConstants, ConstantesModel constantes, Long idPersonne, DeskBar deskBar,
        AideServiceGwtAsync aideService) {
        super(eventBus);
        this.idPersonne = idPersonne;
        this.personneRpcService = personneRpcService;
        this.dimensionServiceGwtAsync = dimensionServiceGwtAsync;
        this.constantes = constantes;

        this.view = view;
        this.deskBar = deskBar;
        this.aideService = aideService;
        this.impacterFamille = null;
        this.forcerDesactivationEservice = null;
        this.presenterConstants = (PersonneCoordonneesPresenterConstants) GWT.create(PersonneCoordonneesPresenterConstants.class);
        listeIdsNaturesTelephoneVisibles = new ArrayList<Long>();
        recupererNaturesTelephoneVisibles();
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
        view.getBtnAjouter().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // On détermine si la personne possède déjà une adresse principale
                boolean hasAdressePrincipale = false;
                if (coordonneesModel != null && coordonneesModel.getAdresses() != null && coordonneesModel.getAdresses().size() > 0) {
                    for (AdresseModel adresse : coordonneesModel.getAdresses()) {
                        if (constantes.getIdNatureAdressePrincipale().equals(adresse.getNature().getIdentifiant())) {
                            hasAdressePrincipale = true;
                            break;
                        }
                    }
                }

                //CONSTRUCTION ET AFFICHAGE
                if (popupCoordonneesPresenter == null)
                {
                    popupCoordonneesPresenter =
                        addChildPresenter(new PopupCoordonneesAdressePresenter(eventBus, personneRpcService, dimensionServiceGwtAsync,
                            new PopupCoordonneesAdresseViewImpl(constantes), constantes, idPersonne, hasAdressePrincipale, deskBar, aideService));
                    popupCoordonneesPresenter.addEventHandlerToLocalBus(RafraichirCoordonneesEvent.TYPE, new RafraichirCoordonneesEventHandler()
                    {
                        @Override
                        public void rafraichirCoordonnees(RafraichirCoordonneesEvent event) {
                            // on vide le bloc des adresses
                            view.supprimerBlocsAdresses();
                            // on récupère les adresses de la personne
                            recupererCoordonnees();
                        }
                    });
                    popupCoordonneesPresenter.showPresenter(null);
                } else {
                    popupCoordonneesPresenter.showPopup(hasAdressePrincipale);
                }
            }
        });

        view.getBtnEnregistrer().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (isDatesValides()) {
                        enregistrerCoordonnees();
                }
            }
        });

        view.getImageTelephoneActif().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final AppellerPersonneEvent eventTelephone = new AppellerPersonneEvent(nettoyerFormatNumTel(view.getNumeroTelephone()));
                fireEventLocalBus(eventTelephone);
            }
        });
        view.getImageTelephone2Actif().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final AppellerPersonneEvent eventTelephone = new AppellerPersonneEvent(nettoyerFormatNumTel(view.getNumeroTelephone2()));
                fireEventLocalBus(eventTelephone);
            }
        });
        SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt> natureTelRequestSuggestionsEventHandler =
            new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
                @Override
                public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                    final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                    criteres.setLibelle(event.getSuggest());
                    if (!"".equals(event.getSuggest())) {
                        criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                    }
                    dimensionServiceGwtAsync.rechercherNatureTelephoneParCriteres(criteres, event.getCallBack());

                }
            };

        view.getSlbNatureTelephone().addSuggestHandler(natureTelRequestSuggestionsEventHandler);
        view.getSlbNatureTelephone2().addSuggestHandler(natureTelRequestSuggestionsEventHandler);

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
        view.getImgFlagPaysTelephone().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event)
            {
                if (popupSelectionPaysTel1 == null)
                {
                    popupSelectionPaysTel1 =
                        addChildPresenter(new PopupSelectionPaysPresenter(eventBus, dimensionServiceGwtAsync,
                            new PopupSelectionPaysViewImpl(paysProperties), true));
                    popupSelectionPaysTel1.addEventHandlerToLocalBus(ModifierPaysTelephoneEvent.TYPE, new ModifierPaysTelephoneEventHandler() {
                        @Override
                        public void modifierPaysTelephone(ModifierPaysTelephoneEvent event) {
                            view.chargerImagePaysTelephone(event.getUrlImagePays(), event.getTitleImagePays());
                            view.initTbTelephone(event.getPaysTelephone(), "");
                        }
                    });
                    popupSelectionPaysTel1.showPresenter(null);
                }
                else
                {
                    popupSelectionPaysTel1.showPopup();
                }
            }
        });
        view.getImgFlagPaysTelephone2().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event)
            {
                if (popupSelectionPaysTel2 == null)
                {
                    popupSelectionPaysTel2 =
                        addChildPresenter(new PopupSelectionPaysPresenter(eventBus, dimensionServiceGwtAsync,
                            new PopupSelectionPaysViewImpl(paysProperties), true));
                    popupSelectionPaysTel2.addEventHandlerToLocalBus(ModifierPaysTelephoneEvent.TYPE, new ModifierPaysTelephoneEventHandler() {
                        @Override
                        public void modifierPaysTelephone(ModifierPaysTelephoneEvent event) {
                            view.chargerImagePaysTelephone2(event.getUrlImagePays(), event.getTitleImagePays());
                            view.initTbTelephone2(event.getPaysTelephone(), "");
                        }
                    });
                    popupSelectionPaysTel2.showPresenter(null);
                }
                else
                {
                    popupSelectionPaysTel2.showPopup();
                }
            }
        });

        requestHandlerPays = new SuggestListBoxSuggestEventHandler<IdentifiantLibelleBooleanModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleBooleanModel> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                dimensionServiceGwtAsync.rechercherPaysParCriteres(criteres, event.getCallBack());
            }
        };

//        // Abonnements au bus principal
//        addEventHandlerToGlobalBus(ConnecteAcpEvent.TYPE, new ConnecteAcpEventHandler() {
//            @Override
//            public void connecteAcp(ConnecteAcpEvent event) {
//                view.activerBoutonsTelephonie();
//            }
//        });
//        addEventHandlerToGlobalBus(DeconnecteAcpEvent.TYPE, new DeconnecteAcpEventHandler() {
//            @Override
//            public void deconnecteAcp(DeconnecteAcpEvent event) {
//                view.desactiverBoutonsTelephonie();
//            }
//        });
//        addEventHandlerToGlobalBus(AppelPossibleEvent.TYPE, new AppelPossibleEventHandler() {
//            @Override
//            public void appellerPossible(AppelPossibleEvent event) {
//                view.activerBoutonsTelephonie();
//            }
//        });
//        addEventHandlerToGlobalBus(DesactiverAppelSquareEvent.TYPE, new DesactiverAppelSquareEventHandler() {
//            @Override
//            public void desactiverAppel(DesactiverAppelSquareEvent event) {
//                view.desactiverBoutonsTelephonie();
//            }
//        });

        // on ajoute les handler sur les blocs adresses
        for (final BlocCoordonneesAdresseView blocAdresse : view.getListeAdressesView()) {
            ajouterEvenementBlocAdresse(blocAdresse);
        }
        FormulaireChangeHandlerUtil.registerFormulaireChangeHandler(view.asWidget(), (DecoratedButton) view.getBtnEnregistrer());
    }

    @Override
    public void onDetach()
    {
        GWT.log("On Detach PersonneCoordonnesPresenter");
    }

    /**
     * Ajoute les evenements sur un bloc adresse.
     */
    private void ajouterEvenementBlocAdresse(final BlocCoordonneesAdresseView blocAdresse) {
        // Construction de la liste des identifiants des composants d'aide de la vue associée à ce presenter
        final List<Long> listeIdsComposantsAides = new ArrayList<Long>();
        for (final AideComposant composant : blocAdresse.getListAideCompsant()) {
            listeIdsComposantsAides.add(composant.getId());
        }

        // Recherche des composants d'aide existant pour les rendre visible
        aideService.rechercherIdsComposantsAides(listeIdsComposantsAides, new AsyncCallback<List<Long>>() {
            @Override
            public void onSuccess(List<Long> result) {
                if (result != null) {
                    for (final AideComposant composant : blocAdresse.getListAideCompsant()) {
                        composant.setVisible(result.contains(composant.getId()));
                    }
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });

        for (HasUpdateAideEventHandler handler : blocAdresse.getListeUpdateEventHandler()) {
            handler.addUpdateAideEventHandler(new UpdateAideEventHandler() {
                @Override
                public void onUpdateAide(UpdateAideEvent event) {
                    aideService.creerOuModifierAide(event.getAide(), event.getCallBack());
                }
            });
        }

        for (HasDemandeAideEventHandler handler : blocAdresse.getListeDemandeEventHandler()) {
            handler.addDemandeAideEventHandler(new DemandeAideEventHandler() {
                @Override
                public void onDemandeAide(DemandeAideEvent event) {
                    aideService.rechercherAide(event.getIdComposant(), event.getCallBack());
                }
            });
        }

        blocAdresse.getSlbType().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionServiceGwtAsync.rechercherTypeAdresseParCriteres(criteres, event.getCallBack());
            }
        });

        blocAdresse.getSlbNatureVoie().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
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
        blocAdresse.getSlbCodePostal().addSuggestHandler(new SuggestListBoxSuggestEventHandler<CodePostalCommuneModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<CodePostalCommuneModel> event) {
                DimensionCritereCodePostalCommuneModel criteres = null;
                final String libelleRecherche = event.getSuggest();
                if (libelleRecherche == null || "".equals(libelleRecherche.trim())) {
                    criteres = new DimensionCritereCodePostalCommuneModel();
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                    dimensionServiceGwtAsync.rechercherCodesPostauxCommunes(criteres, event.getCallBack());
                }
                else {
                    criteres = new DimensionCritereCodePostalCommuneModel();
                    criteres.setLibelleCodePostal(libelleRecherche);
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX_CP);
                    dimensionServiceGwtAsync.rechercherCodesPostauxCommunes(criteres, event.getCallBack());
                }
            }
        });
        blocAdresse.getSlbCodePostalValue().addValueChangeHandler(new ValueChangeHandler<CodePostalCommuneModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<CodePostalCommuneModel> event) {
                if (event.getValue() != null) {
                    blocAdresse.getLValueVille().setText(event.getValue().getLibelleAcheminement());
                    recupererDepartement(blocAdresse, event.getValue().getCommune().getIdentifiant());
                }
                else {
                    blocAdresse.getLValueVille().setText("");
                    blocAdresse.getLValueDepartement().setText("");
                }
            }
        });

        blocAdresse.getSlbPays().addSuggestHandler(requestHandlerPays);
        blocAdresse.getSlbPays().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleBooleanModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleBooleanModel> event) {
                desactiverCodePostal(blocAdresse);
            }
        });

        FormulaireChangeHandlerUtil.registerFormulaireChangeHandler(blocAdresse.asWidget(), (DecoratedButton) view.getBtnEnregistrer());
    }

    /** Récupère la liste des natures de téléphone visibles. */
    private void recupererNaturesTelephoneVisibles() {
        listeIdsNaturesTelephoneVisibles = new ArrayList<Long>();
        final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
        criteres.setVisible(Boolean.TRUE);
        dimensionServiceGwtAsync.rechercherNatureTelephoneParCriteres(criteres, new AsyncCallback<List<IdentifiantLibelleGwt>>() {
            @Override
            public void onSuccess(List<IdentifiantLibelleGwt> result) {
                if (result != null && !result.isEmpty()) {
                    for (IdentifiantLibelleGwt natureTelephone : result) {
                        if (natureTelephone.getIdentifiant() != null) {
                            listeIdsNaturesTelephoneVisibles.add(natureTelephone.getIdentifiant());
                        }
                    }
                }
                // Récupération des coordonnées
                recupererCoordonnees();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    /**
     * Recupere les coordonnees de la personne.
     */
    private void recupererCoordonnees() {
        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().recuperationCoordonnees()));
        personneRpcService.rechercherCoordonneesParIdPersonne(idPersonne, new AsyncCallback<CoordonneesModel>() {
            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(CoordonneesModel coordonnees) {
                if (coordonnees != null) {
                    chargerCoordonnees(coordonnees);
                }
                view.onRpcServiceSuccess();
            }
        });
    }

    /**
     * Charge les coordonnees dans la vue.
     */
    private void chargerCoordonnees(CoordonneesModel coordonnees) {
        this.coordonneesModel = coordonnees;
        // On compte le nombre d'adresses principales / secondaires actives de la personne
        int nbAdressesPrincipalesActives = 0;
        int nbAdressesSecondairesActives = 0;
        final Date dateDuJour = new Date();
        for (AdresseModel adresse : coordonnees.getAdresses()) {
            // Si l'adresse n'a pas de date de fin ou que la date de fin est postérieure à la date du jour
            if (adresse.getDateFin() == null || (adresse.getDateFin() != null && DateUtil.getDate(adresse.getDateFin()).after(dateDuJour))) {
                if (constantes.getIdNatureAdressePrincipale().equals(adresse.getNature().getIdentifiant())) {
                    nbAdressesPrincipalesActives++;
                }
                else if (constantes.getIdNatureAdresseSecondaire().equals(adresse.getNature().getIdentifiant())) {
                    nbAdressesSecondairesActives++;
                }
            }
        }
        // On active le bouton permettant d'ajouter une adresse si la personne possède moins
        final boolean enableBtnAjoutAdresse =
            (nbAdressesPrincipalesActives <= 1 && nbAdressesSecondairesActives == 0)
                || (nbAdressesPrincipalesActives == 0 && nbAdressesSecondairesActives <= 1);
        view.enableBtnAjoutAdresse(enableBtnAjoutAdresse);

        final PaysSimpleModel paysDefaut = constantes.getPaysFrance();
        popupSelectionPaysTel1 = null;
        popupSelectionPaysTel2 = null;

        // CHARGEMENT DES TELEPHONES
        TelephoneModel telephone1 = null;
        TelephoneModel telephone2 = null;
        // On ne récupère que les téléphones dont la nature est visible
        if (coordonneesModel.getTelephones() != null && !coordonneesModel.getTelephones().isEmpty()) {
            for (TelephoneModel telephone : coordonneesModel.getTelephones()) {
                if ((telephone1 == null || telephone2 == null) && telephone.getNature() != null
                    && listeIdsNaturesTelephoneVisibles.contains(telephone.getNature().getIdentifiant())) {
                    if (telephone1 == null) {
                        telephone1 = telephone;
                    }
                    else {
                        telephone2 = telephone;
                    }
                }
            }
        }
        if (telephone1 != null) {
            if (telephone1.getNature() != null) {
                view.getSlbNatureTelephone().setValue(telephone1.getNature());
            }
            if (telephone1.getPays() != null) {
                view.setPaysTelephone(telephone1.getPays());
                view.initTbTelephone(telephone1.getPays(), telephone1.getNumero());
            }
            else {
                // on charge le pays de téléphone par défaut
                view.setPaysTelephone(paysDefaut);
                view.initTbTelephone(paysDefaut, telephone1.getNumero());
            }
            view.nettoyerTelephone2(paysDefaut);
        }
        else {
            // on charge le pays de téléphone par défaut
            view.nettoyerTelephone(paysDefaut);
        }
        if (telephone2 != null) {
            if (telephone2.getNature() != null) {
                view.getSlbNatureTelephone2().setValue(telephone2.getNature());
            }
            if (telephone2.getPays() != null) {
                view.setPaysTelephone2(telephone2.getPays());
                view.initTbTelephone2(telephone2.getPays(), telephone2.getNumero());
            }
            else {
                // on charge les pays de téléphone par défaut
                view.setPaysTelephone2(paysDefaut);
                view.initTbTelephone2(paysDefaut, telephone2.getNumero());
            }
        }
        else {
            // on charge le pays de téléphone par défaut
            view.nettoyerTelephone2(paysDefaut);
        }
        // CHARGEMENT DE L'EMAIL
        if (coordonneesModel.getEmails() != null && coordonneesModel.getEmails().size() >= 1) {
            final EmailModel email = coordonneesModel.getEmails().get(0);
            view.getTbEmail().setValue(email.getAdresse());
        }

        // CHARGEMENT DES ADRESSES
        if (coordonneesModel.getAdresses() != null) {
            indexAdresses = 0;
            for (AdresseModel adresse : coordonneesModel.getAdresses()) {
                final BlocCoordonneesAdresseView blocAdresse =
                    view.ajouterBlocAdresse(false, indexAdresses, adresse.getNature().getIdentifiant().equals(constantes.getIdNatureAdressePrincipale()));
                // on ajoute la gestion des evenements
                ajouterEvenementBlocAdresse(blocAdresse);

                blocAdresse.setIdAdresse(adresse.getIdentifiant());
                if (adresse.getNature() != null) {
                    blocAdresse.getSlbType().setValue(adresse.getNature());
                }
                blocAdresse.getCbNPAI().setValue(adresse.getNpai(), true);
                if (adresse.getDateDebut() != null) {
                    blocAdresse.getCdbDateDebut().setValue(DateUtil.getDate(adresse.getDateDebut()), true);
                }
                if (adresse.getDateFin() != null) {
                    blocAdresse.getCdbDateFin().setValue(DateUtil.getDate(adresse.getDateFin()), true);
                }
                blocAdresse.getTbNumeroVoie().setValue(adresse.getNumVoie());
                if (adresse.getTypeVoie() != null) {
                    blocAdresse.getSlbNatureVoie().setValue(adresse.getTypeVoie());
                }
                blocAdresse.getTbComplementNom().setValue(adresse.getComplementNom());
                blocAdresse.getTbAdresse().setValue(adresse.getNomVoie());
                blocAdresse.getTbComplementAdresse().setValue(adresse.getComplementAdresse());
                blocAdresse.getTbAutresComplements().setValue(adresse.getAutresComplements());
                if (adresse.getPays() != null) {
                    blocAdresse.getSlbPays().setValue(adresse.getPays());
                }
                if (adresse.getCodePostalCommune() != null) {
                    blocAdresse.getSlbCodePostalValue().setValue(adresse.getCodePostalCommune());
                    blocAdresse.getLValueVille().setText(adresse.getCodePostalCommune().getLibelleAcheminement());
                }
                if (adresse.getDepartement() != null) {
                    blocAdresse.setIdDepartement(adresse.getDepartement().getIdentifiant());
                    blocAdresse.getLValueDepartement().setText(adresse.getDepartement().getLibelle());
                }
                blocAdresse.getTbCommuneEtranger().setValue(adresse.getCommuneEtranger());
                blocAdresse.getTbCodePostalEtranger().setValue(adresse.getCodePostalEtranger());
                blocAdresse.getTbCommuneEtranger().setValue(adresse.getCommuneEtranger());
                indexAdresses++;
            }
        }
        view.disableBoutonEnregistrer();
    }

    /**
     * Recupere le departement de la commune.
     */
    private void recupererDepartement(final BlocCoordonneesAdresseView blocAdresse, Long idCommune) {
        final DimensionCritereRechercheDepartementModel criteres = new DimensionCritereRechercheDepartementModel();
        criteres.setIdCommune(idCommune);
        dimensionServiceGwtAsync.rechercherDepartementParCriteres(criteres, new AsyncCallback<List<IdentifiantLibelleDepartementCodeModel>>() {
            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }

            @Override
            public void onSuccess(List<IdentifiantLibelleDepartementCodeModel> result) {
                if (result != null) {
                    final IdentifiantLibelleDepartementCodeModel departement = result.get(0);
                    blocAdresse.setIdDepartement(departement.getId());
                    blocAdresse.getLValueDepartement().setText(departement.getLibelle());
                }
                view.onRpcServiceSuccess();
            }
        });
    }

    /**
     * Désactive le bloc du conjoint.
     */
    private void desactiverCodePostal(BlocCoordonneesAdresseView blocAdresse) {
        boolean activer = false;
        if (blocAdresse.getSlbPays().getValue() != null && blocAdresse.getSlbPays().getValue().getIdentifiant() != null) {
            activer = blocAdresse.getSlbPays().getValue().getIdentifiant().equals(constantes.getIdPaysFrance());
        }
        if (!activer) {
            blocAdresse.nettoyerCodePostal();
            blocAdresse.getLValueVille().setText("");
            blocAdresse.getLValueDepartement().setText("");
        }
        else {
            blocAdresse.getTbCodePostalEtranger().setValue("");
            blocAdresse.getTbCommuneEtranger().setValue("");
        }
        blocAdresse.afficheBlocCoordonneesFrance(activer);
    }

    /** Enregistre les coordonnées. */
    private void enregistrerCoordonnees() {
        // On fait une copie des coordonnées avant l'enregistrement au cas où une erreur soit remontée et ainsi réinitialiser coordonneesModel
        final CoordonneesModel oldCoordonnees = new CoordonneesModel();
        oldCoordonnees.setIdPersonne(coordonneesModel.getIdPersonne());
        oldCoordonnees.setAdresses(coordonneesModel.getAdresses());
        oldCoordonnees.setEmails(coordonneesModel.getEmails());
        oldCoordonnees.setTelephones(coordonneesModel.getTelephones());

        EmailModel email = null;
        if (coordonneesModel.getEmails() == null || coordonneesModel.getEmails().size() == 0) {
            email = new EmailModel();
            final List<EmailModel> emails = new ArrayList<EmailModel>();
            emails.add(email);
            coordonneesModel.setEmails(emails);
        }
        else {
            email = coordonneesModel.getEmails().get(0);
        }
        email.setAdresse(view.getTbEmail().getValue());

        TelephoneModel telephone1 = null;
        TelephoneModel telephone2 = null;
        // On ne récupère que les téléphones dont la nature est visible
        if (coordonneesModel.getTelephones() != null && !coordonneesModel.getTelephones().isEmpty()) {
            for (TelephoneModel telephone : coordonneesModel.getTelephones()) {
                if (telephone1 == null || telephone2 == null) {
                    if (telephone1 == null) {
                        telephone1 = telephone;
                    }
                    else {
                        telephone2 = telephone;
                    }
                }
            }
        }

        // Téléphone 1
        if (telephone1 == null) {
            telephone1 = new TelephoneModel();
            coordonneesModel.getTelephones().add(telephone1);
        }
        if (telephone1 != null) {
            telephone1.setNature(view.getSlbNatureTelephone().getValue());
            telephone1.setNumero(nettoyerFormatNumTel(view.getTbTelephone().getValue()));
            final PaysSimpleModel paysTel1 = new PaysSimpleModel();
            if (popupSelectionPaysTel1 != null) {
                paysTel1.setId(popupSelectionPaysTel1.getIdPaysSelectionne());
            }
            else {
                paysTel1.setId(view.getIdPaysTelephone());
            }
            telephone1.setPays(paysTel1);
        }

        // telephone 2
        if (telephone2 == null) {
            telephone2 = new TelephoneModel();
            coordonneesModel.getTelephones().add(telephone2);
        }
        if (telephone2 != null) {
            telephone2.setNature(view.getSlbNatureTelephone2().getValue());
            telephone2.setNumero(nettoyerFormatNumTel(view.getTbTelephone2().getValue()));
            final PaysSimpleModel paysTel2 = new PaysSimpleModel();
            if (popupSelectionPaysTel2 != null) {
                paysTel2.setId(popupSelectionPaysTel2.getIdPaysSelectionne());
            }
            else {
                paysTel2.setId(view.getIdPaysTelephone2());
            }
            telephone2.setPays(paysTel2);
        }

        // AJOUT DES ADRESSES
        final List<AdresseModel> listeAdresses = new ArrayList<AdresseModel>();
        for (int index = 0; index < view.getListeAdressesView().size(); index++) {
            final BlocCoordonneesAdresseView blocAdresse = view.getListeAdressesView().get(index);
            final AdresseModel adresse = coordonneesModel.getAdresses().get(index);
            adresse.setIdentifiant(blocAdresse.getIdAdresse());
            adresse.setNature(blocAdresse.getSlbType().getValue());
            adresse.setNpai(blocAdresse.getCbNPAI().getValue());
            if (blocAdresse.getCdbDateDebut().getValue() != null) {
                adresse.setDateDebut(DateUtil.getString(blocAdresse.getCdbDateDebut().getValue()));
            }
            else {
                adresse.setDateDebut(null);
            }
            if (blocAdresse.getCdbDateFin().getValue() != null) {
                adresse.setDateFin(DateUtil.getString(blocAdresse.getCdbDateFin().getValue()));
            }
            else {
                adresse.setDateFin(null);
            }
            adresse.setNumVoie(blocAdresse.getTbNumeroVoie().getValue());
            adresse.setTypeVoie(blocAdresse.getSlbNatureVoie().getValue());
            adresse.setComplementNom(blocAdresse.getTbComplementNom().getValue());
            adresse.setNomVoie(blocAdresse.getTbAdresse().getValue());
            adresse.setComplementAdresse(blocAdresse.getTbComplementAdresse().getValue());
            adresse.setAutresComplements(blocAdresse.getTbAutresComplements().getValue());
            final CodePostalCommuneModel codePostalCommune = blocAdresse.getSlbCodePostalValue().getValue();
            if (codePostalCommune != null) {
                adresse.setCodePostalCommune(codePostalCommune);
            }
            if (blocAdresse.getIdDepartement() != null) {
                adresse.setDepartement(new IdentifiantLibelleGwt(blocAdresse.getIdDepartement(), blocAdresse.getLValueDepartement().getText()));
            }
            adresse.setPays(blocAdresse.getSlbPays().getValue());
            adresse.setCodePostalEtranger(blocAdresse.getTbCodePostalEtranger().getValue());
            adresse.setCommuneEtranger(blocAdresse.getTbCommuneEtranger().getValue());
            listeAdresses.add(adresse);
        }
        coordonneesModel.setAdresses(listeAdresses);

        view.afficherLoadingPopup(new LoadingPopupConfiguration(view.getViewConstants().majCoordonnees()));
        personneRpcService.creerOuMettreAJourCoordonnees(coordonneesModel, impacterFamille, forcerDesactivationEservice, new AsyncCallback<CoordonneesModel>() {
            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    coordonneesModel = oldCoordonnees;
                    // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                    view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(((ControleIntegriteExceptionGwt) caught).getRapport()));
                    view.masquerLoadingPopup();
                }
                else if (caught instanceof ConfirmationImpacterFamilleExceptionGwt) {
                    view.masquerLoadingPopup();
                    final PopupConfiguration config = new PopupConfiguration(caught.getMessage());
                    config.setHtmlCaptionBtnOk(view.getViewConstants().libelleOui());
                    config.setHtmlCaptionBtnCancel(view.getViewConstants().libelleNon());
                    config.setCallback(new PopupCallback() {
                        @Override
                        public void onResult(boolean result) {
                            impacterFamille = result;
                            // On relance l'enregistrement des coordonnées
                            enregistrerCoordonnees();
                        }
                    });
                    ConfirmPopup.afficher(config);
                }
                // Récupération de l'exception de desactivation d'eservice
                else if (caught instanceof ConfirmationDesactivationEserviceExceptionGwt) {
                    view.masquerLoadingPopup();
                    final PopupConfiguration config = new PopupConfiguration(caught.getMessage());
                    config.setHtmlCaptionBtnOk(view.getViewConstants().libelleOui());
                    config.setHtmlCaptionBtnCancel(view.getViewConstants().libelleNon());
                    config.setForcerBtnCancel(true);
                    config.setCallback(new PopupCallback() {
                        @Override
                        public void onResult(boolean result) {
                            if (result) {
                                forcerDesactivationEservice = result;
                                // On relance l'enregistrement des coordonnées
                                enregistrerCoordonnees();
                            } else {
                                forcerDesactivationEservice = null;
                            }
                        }
                    });
                    AlertPopup.afficher(AlertPopupType.WARNING, config);
                }
                else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }

            @Override
            public void onSuccess(CoordonneesModel coordonnees) {
                // On réinitialise le flag
                impacterFamille = null;
                forcerDesactivationEservice = null;
                view.supprimerBlocsAdresses();
                chargerCoordonnees(coordonnees);
                fireEventLocalBus(new RafraichirPersonneEvent(idPersonne));
                view.onRpcServiceSuccess();
                String messageNotification = presenterConstants.notificationCoordonneesMaj();
                if (coordonnees.getHasNaturePersonneChanged() != null && coordonnees.getHasNaturePersonneChanged()) {
                    messageNotification = messageNotification + AppControllerConstants.SAUT_LIGNE
                    + presenterConstants.debutNotificiationNaturePersonneModifiee() + " " + coordonnees.getAncienneNaturePersonne() + " "
                    + presenterConstants.finNotificiationNaturePersonneModifiee() + " " + coordonnees.getNouvelleNaturePersonne();
                }
                final PopupInfoConfiguration config =
                    new PopupInfoConfiguration(messageNotification, AppControllerConstants.NOTIFICATION_TIME_OUT);
                new DecoratedInfoPopup(config).show();
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

        container.add(view.asWidget());
    }

    /**
     * Nettoye un numéro de téléphone de son formatage.
     * @param numTel le numéro à nettoyer.
     * @return le numéro nettoyer.
     */
    private String nettoyerFormatNumTel(String numTel) {
        String numTelNettoye = numTel.replaceAll(" ", "");
        numTelNettoye = numTelNettoye.replaceAll("\\.", "");
        numTelNettoye = numTelNettoye.replaceAll("-", "");
        numTelNettoye = numTelNettoye.replaceAll("-", "");
        numTelNettoye = numTelNettoye.replaceAll("/", "");
        return numTelNettoye;
    }

    /**
     * Teste la validité des dates.
     * @return true si les dates sont valides, false sinon
     */
    private boolean isDatesValides() {
        boolean datesValides = true;
        final RapportModel rapport = new RapportModel();
        for (int index = 0; index < view.getListeAdressesView().size(); index++) {
            if (!view.getListeAdressesView().get(index).getCdbDateDebut().isDateValide()) {
                rapport.ajoutRapport(presenterConstants.champDateDebut() + index, presenterConstants.erreurDateDebutInvalide(), true);
                datesValides = false;
            }
            if (!view.getListeAdressesView().get(index).getCdbDateFin().isDateValide()) {
                rapport.ajoutRapport(presenterConstants.champDateFin() + index, presenterConstants.erreurDateFinInvalide(), true);
                datesValides = false;
            }
        }
        view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
        if (!datesValides) {
            view.masquerLoadingPopup();
        }
        return datesValides;
    }

    private boolean isTelephoneMobileRenseigne(DecoratedTextBoxFormat telephone, DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> nature) {
        return telephone.getValue() != null
            && !telephone.getValue().isEmpty()
            && nature.getValue() != null
            && (nature.getValue().getIdentifiant().equals(constantes.getIdNatureMobilePrive()) || nature.getValue().getIdentifiant().equals(
                constantes.getIdNatureMobileTravail()));
    }

    /**
     * Interface pour la vue PersonneCreationView.
     */
    public interface PersonneCoordonneesView extends View {

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbNatureTelephone();

        /**
         * Retourne l'id.
         * @return l'id
         */
        Long getIdPaysTelephone();

        /**
         * Définit le pays du téléphone 1.
         * @param pays le pays
         */
        void setPaysTelephone(PaysSimpleModel pays);

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbNatureTelephone2();

        /**
         * Retourne l'id.
         * @return l'id
         */
        Long getIdPaysTelephone2();

        /**
         * Définit le pays du téléphone 2.
         * @param pays le pays
         */
        void setPaysTelephone2(PaysSimpleModel pays);

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbEmail();

        /**
         * Retourne les vues des adresses.
         * @return les vues
         */
        List<BlocCoordonneesAdresseViewImpl> getListeAdressesView();

        /**
         * Retourne le bouton.
         * @return le bouton
         */
        HasClickHandlers getBtnEnregistrer();

        /**
         * Retourne le bouton.
         * @return le bouton
         */
        HasClickHandlers getBtnAjouter();

        /**
         * Ajoute un bloc adresse.
         * @param addOnTop flag pour ajouter le bloc en haut
         * @param index l'index
         * @param isOpen demande si ouverture du bloc ou pas.
         * @return le bloc nouvellement ajouté
         */
        BlocCoordonneesAdresseView ajouterBlocAdresse(boolean addOnTop, int index, boolean isOpen);

        /**
         * Supprime les blocs adresses.
         */
        void supprimerBlocsAdresses();

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
         * Retourne les constantes.
         * @return les constantes.
         */
        PersonneCoordonneesViewImplConstants getViewConstants();

        /**
         * Affiche un message de chargement.
         * @param config la config
         */
        void afficherLoadingPopup(LoadingPopupConfiguration config);

        /**
         * Masque un message de chargement.
         */
        void masquerLoadingPopup();

        /**
         * Retourne le widget.
         * @return le widget
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Affiche / Masque les options pour la téléphonie.
         * @param affiche true si visible, false si non.
         */
        void afficherTelephonie(Boolean affiche);

        /**
         * Accesseur sur l'image du téléphone actif.
         * @return le widget.
         */
        HasClickHandlers getImageTelephoneActif();

        /**
         * Accesseur sur l'image du téléphone 2 actif.
         * @return le widget.
         */
        HasClickHandlers getImageTelephone2Actif();

        /** Active les boutons de la téléphonie. */
        void activerBoutonsTelephonie();

        /** Désactive les boutons de la téléphonie. */
        void desactiverBoutonsTelephonie();

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
         * Accesseur sur ta TextBox du téléphone.
         * @return le TextBox.
         */
        DecoratedTextBoxFormat getTbTelephone();

        /**
         * Accesseur sur ta TextBox du téléphone 2.
         * @return le TextBox.
         */
        DecoratedTextBoxFormat getTbTelephone2();

        /**
         * Charge l'image du téléphone.
         * @param url l'url de l'image.
         * @param title le title de l'image.
         */
        void chargerImagePaysTelephone(String url, String title);

        /**
         * Charge l'image du téléphone 2.
         * @param url l'url de l'image.
         * @param title le title de l'image.
         */
        void chargerImagePaysTelephone2(String url, String title);

        /**
         * Initialise la TextBox du téléphone.
         * @param pays le pays du téléphone.
         * @param value la valeur de la TextBox.
         */
        void initTbTelephone(PaysSimpleModel pays, String value);

        /**
         * Initialise la TextBox du téléphone 2.
         * @param pays le pays du téléphone.
         * @param value la valeur de la TextBox.
         */
        void initTbTelephone2(PaysSimpleModel pays, String value);

        /**
         * Récupère le numéro de téléphone.
         * @return le numéro de téléphone.
         */
        String getNumeroTelephone();

        /**
         * Récupère le numéro de téléphone 2.
         * @return le numéro de téléphone 2.
         */
        String getNumeroTelephone2();

        /**
         * Nettoye le téléphone 1.
         * @param paysDefautTel le pays par défaut.
         */
        void nettoyerTelephone(PaysSimpleModel paysDefautTel);

        /**
         * Nettoye le téléphone 2.
         * @param paysDefautTel le pays par défaut.
         */
        void nettoyerTelephone2(PaysSimpleModel paysDefautTel);

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
         * Active / désactive le bouton d'ajout d'adresse.
         * @param enabled true pour activer, false pour désactiver
         */
        void enableBtnAjoutAdresse(boolean enabled);

        /**
         * Grise le bouton enregistrer.
         */
        void disableBoutonEnregistrer();
    }

    /**
     * Interface pour la vue BlocCoordonneesAdresseView.
     */
    public interface BlocCoordonneesAdresseView extends View {

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbType();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<Boolean> getCbNPAI();

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedCalendrierDateBox getCdbDateDebut();

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedCalendrierDateBox getCdbDateFin();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbNumeroVoie();

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbNatureVoie();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbComplementNom();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbAdresse();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbComplementAdresse();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbAutresComplements();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasSuggestListBoxHandler<CodePostalCommuneModel> getSlbCodePostal();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasText getLValueVille();

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasText getLValueDepartement();

        /**
         * Retourne le widget.
         * @return le widget
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel> getSlbPays();

        /**
         * Retourne la valeur.
         * @return la valeur
         */
        Long getIdDepartement();

        /**
         * Retourne la valeur.
         * @return la valeur
         */
        Long getIdAdresse();

        /**
         * Définit la valeur.
         * @param id l'id
         */
        void setIdDepartement(Long id);

        /**
         * Définit la valeur.
         * @param id l'id
         */
        void setIdAdresse(Long id);

        /**
         * Récupération de la commune (si pays étranger).
         * @return le widget
         */
        HasValue<String> getTbCommuneEtranger();

        /**
         * Récupération du code postal (si pays étranger).
         * @return le widget
         */
        HasValue<String> getTbCodePostalEtranger();

        /**
         * Affiche Masque le bloc coordonnées France.
         * @param affiche visible ou pas
         */
        void afficheBlocCoordonneesFrance(Boolean affiche);

        /**
         * Renvoie le choix.
         * @return choixAction
         */
        Boolean getChoixPasserEnSecondaire();

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
}

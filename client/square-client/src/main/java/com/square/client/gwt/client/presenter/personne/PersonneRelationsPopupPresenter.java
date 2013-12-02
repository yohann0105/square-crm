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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetCellClickedEvent;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetCellClickedEventHandler;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEvent;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEventHandler;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
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
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.CreerPersonneRelationEvent;
import com.square.client.gwt.client.event.CreerPersonneRelationEventHandler;
import com.square.client.gwt.client.event.ModifierPaysTelephoneEvent;
import com.square.client.gwt.client.event.ModifierPaysTelephoneEventHandler;
import com.square.client.gwt.client.event.SimpleValueChangeEvent;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.CoordonneesModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheTypeRelationModel;
import com.square.client.gwt.client.model.DimensionCriteresRechercheModel;
import com.square.client.gwt.client.model.ItemValueModel;
import com.square.client.gwt.client.model.PaysSimpleModel;
import com.square.client.gwt.client.model.PersonneDoublonModel;
import com.square.client.gwt.client.model.PersonneModel;
import com.square.client.gwt.client.model.PersonneMoralCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneMoraleRechercheModel;
import com.square.client.gwt.client.model.PersonnePhysiqueCopieModel;
import com.square.client.gwt.client.model.PersonnePhysiqueRelationModel;
import com.square.client.gwt.client.model.RelationModel;
import com.square.client.gwt.client.model.TelephoneModel;
import com.square.client.gwt.client.presenter.personne.physique.PopupCreationPersonneDoublonPresenter;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneMoraleServiceGwtAsync;
import com.square.client.gwt.client.service.PersonnePhysiqueServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.view.personne.coordonnees.PopupSelectionPaysViewImpl;
import com.square.client.gwt.client.view.personne.coordonnees.PopupSelectionPaysViewImpl.ListBoxPaysProperties;
import com.square.client.gwt.client.view.personne.physique.creation.PopupCreationPersonneDoublonViewImpl;
import com.square.client.gwt.client.view.personne.relations.PersonneRelationAjouterEnfantConjointViewImpl;
import com.square.client.gwt.client.view.personne.relations.PersonneRelationsViewImplConstants;
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
 * Presenter pour la popup d'ajout d'une relation.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonneRelationsPopupPresenter extends Presenter {

    /** Le service sur les personnes. */
    private PersonneServiceGwtAsync personneRpcService;

    /** Le service sur les dimensions. */
    private DimensionServiceGwtAsync dimensionRpcService;

    /** Le service de recherche des personnes morales. */
    private PersonneMoraleServiceGwtAsync personneMoraleRpcService;

    /** Vue associé au presenter. */
    private PersonneRelationPopupView view;

    /** Constantes. */
    private ConstantesModel constantes;

    /** Identifiant de la personne principale. */
    private Long idPersonne;

    /** Nom de la personne principale. */
    private String nomPersonne;

    /** Identifiant du département de la personne principale. */
    private Long idDepartement;

    /** Identifiant de la personne pour creation. */
    private Long idPersonneCible;

    /** identifiant limitation pour les groupement. */
    private List<Long> filtreGroupements;

    /** identifiant limitation pour les groupement. */
    private List<Long> filtrePasDansGroupements;

    /** Le service sur les personne physique. */
    private PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService;

    /** Type PP. */
    private Long typePP = 0L;

    /** Type PM. **/
    private Long typePM = 1L;

    /** Type de la personne source. */
    private Long typePersonneSource;

    private PersonneRelationsPopupPresenterConstants presenterConstants;

    private DeskBar deskBar;
    private  HandlerRegistration deskBarRegistration;

    /** Le service de gestion d'aides. */
    private AideServiceGwtAsync aideService;

    /** Map des relations des personnes physiques. */
    private Map<Long, PersonnePhysiqueRelationModel> mapRelationsPP;

    /** Personne principale en cours. */
    private PersonneModel personneEnCours;

    /** LISTE DES PRESENTER. **/
    private PopupSelectionPaysPresenter popupSelectionPaysTel1;
    private PersonneRelationAjouterEnfantConjointPresenter personneRelationAjouterEnfantConjoint;
    private PopupCreationPersonneDoublonPresenter popupCreationPersonneDoublonPresenter;


    /**
     * Constructeur par defaut.
     * @param eventBus le bus des évenements
     * @param personneRpcService service rpc pour les personnes
     * @param personnePhysiqueRpcService le service rpc pour les personnes physiques
     * @param personneMoraleRpcService le service rpc pour la gestion des personnes morales.
     * @param dimensionRpcService service rpc pour les dimensions
     * @param view la vue associé au presenter
     * @param constantes les constantes (service de mapping)
     * @param idPersonne l'identifiant de la personne principale.
     * @param nomPersonne nom de la personne principale.
     * @param filtreGroupements permet de limiter l'affichage des relations à un groupement.
     * @param filtrePasDansGroupements permet de limiter l'affichage des relations à un groupement.
     * @param typePersonneSource le type de la personne source
     * @param deskBar deskBar
     * @param aideService service d'aide
     */
    public PersonneRelationsPopupPresenter(HandlerManager eventBus, PersonneServiceGwtAsync personneRpcService,
        PersonnePhysiqueServiceGwtAsync personnePhysiqueRpcService, PersonneMoraleServiceGwtAsync personneMoraleRpcService,
        DimensionServiceGwtAsync dimensionRpcService, PersonneRelationPopupView view, ConstantesModel constantes, Long idPersonne, String nomPersonne,
        List<Long> filtreGroupements, List<Long> filtrePasDansGroupements, Long typePersonneSource, DeskBar deskBar,
        AideServiceGwtAsync aideService) {
        super(eventBus);
        this.personneRpcService = personneRpcService;
        this.dimensionRpcService = dimensionRpcService;
        this.personneMoraleRpcService = personneMoraleRpcService;
        this.constantes = constantes;
        this.view = view;
        this.idPersonne = idPersonne;
        this.nomPersonne = nomPersonne;
        this.personnePhysiqueRpcService = personnePhysiqueRpcService;
        this.filtreGroupements = filtreGroupements;
        this.filtrePasDansGroupements = filtrePasDansGroupements;
        this.presenterConstants = GWT.create(PersonneRelationsPopupPresenterConstants.class);
        this.typePersonneSource = typePersonneSource;
        this.deskBar = deskBar;
        this.aideService = aideService;
        this.mapRelationsPP = new HashMap<Long, PersonnePhysiqueRelationModel> ();

        initTextPopupCreation();
        view.getTbNom().setValue(nomPersonne == null ? "" : nomPersonne);
    }

    @Override
    public View asView() {
        return this.view;
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

        idPersonneCible = null;

        initTelephone();
        // Annulation 5449
//        initDepartement();
//        if (typePP.equals(typePersonneSource)) {
//            initReferents();
//        }
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                view.initFocus();
                // on ajoute la popup à la deskBar
                deskBar.addPopup(view.getMinimizablePopup());
            }
        });
    }

    /** Affiche la popup d'ajout de relation. */
    public void afficherPopupAjoutRelation() {
        view.afficherPopupAjoutRelation();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                idPersonneCible = null;
                view.initFocus();
            }
        });
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

        view.getSlbCivilite().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionRpcService.rechercherCiviliteParCriteres(criteres, event.getCallBack());
            }
        });
        // Traitement de la création d'une relation.
        view.getBtEnregistrerCreation().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (isDatesValides()) {
                    rechercherDoublonsPersonne();
                }
            }
        });

        // Annulation de la création de relation
        view.getBtAnnuler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                idPersonneCible = null;
                fermerPopup();
            }
        });
        view.getFocusPanelAllKeyHandlers().addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                final int keyCode = event.getNativeKeyCode();
                if (keyCode == KeyCodes.KEY_ENTER) {
                    rechercherDoublonsPersonne();
                }
                else if (keyCode == KeyCodes.KEY_ESCAPE) {
                    idPersonneCible = null;
                    fermerPopup();
                }
            }
        });
        view.getRemotePagingHandlerManagerPm().addHandler(SetDataProviderEvent.TYPE,
            new SetDataProviderEventHandler<PersonneMoralCriteresRechercheModel, PersonneMoraleRechercheModel>() {
                public void setDataProvider(SetDataProviderEvent<PersonneMoralCriteresRechercheModel, PersonneMoraleRechercheModel> event) {
                    // On récupère les critères.
                    final PersonneMoralCriteresRechercheModel criterias = new PersonneMoralCriteresRechercheModel();
                    criterias.setRaisonSociale(view.getTbRaisonSocialePm().getValue());
                    criterias.setNumeroEntreprise(view.getTbNumEntreprisePm().getValue());
                    event.getParams().setCriterias(criterias);
                    // Appel du service
                    personneMoraleRpcService.rechercherPersonneMoraleParCriteres(event.getParams(), event.getCallback());
                }
            });
        view.getRemotePagingHandlerManagerPm().addHandler(SetCellClickedEvent.TYPE, new SetCellClickedEventHandler<PersonneMoraleRechercheModel>() {
            @Override
            public void setCellClicked(SetCellClickedEvent<PersonneMoraleRechercheModel> event) {
                idPersonneCible = event.getModele().getId();
                ajouterRelation(false);
            }
        });
        view.getBtnRechercherPm().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.lancerLaRecherchePm();
            }
        });
        view.getTypePersonneChangeHandler().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
            @Override
            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
                if (event.getValue() != null && event.getValue().getIdentifiant().equals(typePM)) {
                    view.switchPanelPopupPersonnePM();
                    view.centrerPopup(PersonneRelationPopupView.LARGEUR_PM);
                }
                else {
                    // Si la personne source est une personne morale : affichage du panneau PP avec numéro de téléphone
                    if (typePM.equals(typePersonneSource)) {
                        view.switchPanelPopupPersonnePPNumeroTelephone();
                        view.centrerPopup(PersonneRelationPopupView.LARGEUR_PP_TELEPHONE);
                    }
                    else {
                        view.switchPanelPopupPersonnePP();
                        view.centrerPopup(PersonneRelationPopupView.LARGEUR_PP);
                    }
                }
            }
        });
        view.getSlbTypeRelation().addSuggestHandler(new SuggestListBoxSuggestEventHandler<ItemValueModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<ItemValueModel> event) {
                final DimensionCritereRechercheTypeRelationModel criteres = new DimensionCritereRechercheTypeRelationModel();
                criteres.setGroupements(filtreGroupements);
                criteres.setPasDansGroupements(filtrePasDansGroupements);
                criteres.setVisible(true);
                criteres.setInverse(true);
                criteres.setLibelle(event.getSuggest());
                dimensionRpcService.chargerListeRelation(criteres, event.getCallBack());
            }
        });

        // GESTION DU TYPE DE PERSONNE
        final List<IdentifiantLibelleGwt> listeTypesPersonne = new ArrayList<IdentifiantLibelleGwt>();
        listeTypesPersonne.add(new IdentifiantLibelleGwt(typePP, view.getViewConstants().typePersonnePP()));
        final Long idGroupementRelationFamille = constantes.getIdGroupementFamiliale();
        if (filtreGroupements == null || filtreGroupements.size() == 0 || filtreGroupements.size() > 1
            || !filtreGroupements.get(0).equals(idGroupementRelationFamille)) {
            listeTypesPersonne.add(new IdentifiantLibelleGwt(typePM, view.getViewConstants().typePersonnePM()));
        }
        view.getSlbTypePersonne().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final List<IdentifiantLibelleGwt> liste = new ArrayList<IdentifiantLibelleGwt>();
                for (IdentifiantLibelleGwt type : listeTypesPersonne) {
                    if (event.getSuggest() != null && type.getLibelle().startsWith(event.getSuggest())) {
                        liste.add(type);
                    }
                }
                event.getCallBack().onSuccess(liste);
            }
        });
        // si un seul type on enleve la liste de type
        if (listeTypesPersonne.size() <= 1) {
            view.masquerTypePersonneVisible();
        }

        view.getSlbNatureTelephone().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
                criteres.setLibelle(event.getSuggest());
                if (!"".equals(event.getSuggest())) {
                    criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
                }
                dimensionRpcService.rechercherNatureTelephoneParCriteres(criteres, event.getCallBack());
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
                            view.stopAllLoadingPopup();
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
        view.getImgFlagPaysTelephone().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event)
            {
                if (popupSelectionPaysTel1 == null)
                {
                    popupSelectionPaysTel1 = addChildPresenter(new PopupSelectionPaysPresenter(eventBus, dimensionRpcService,
                        new PopupSelectionPaysViewImpl(paysProperties), true));
                    popupSelectionPaysTel1.addEventHandlerToLocalBus(ModifierPaysTelephoneEvent.TYPE, new ModifierPaysTelephoneEventHandler() {
                        @Override
                        public void modifierPaysTelephone(ModifierPaysTelephoneEvent event) {
                            if (event.getIsTelephonePrincipal()) {
                                view.chargerImagePaysTelephone(event.getUrlImagePays(), event.getTitleImagePays());
                                view.initTbTelephone(event.getPaysTelephone().getFormatTelephone());
                            }
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
        // Annulation 5449
//        view.getRegimeSuggestHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantLibelleGwt>() {
//            @Override
//            public void suggest(SuggestListBoxSuggestEvent<IdentifiantLibelleGwt> event) {
//                final DimensionCriteresRechercheRegimeModel criteres = new DimensionCriteresRechercheRegimeModel();
//                final DimensionCriteresRechercheModel dimensionCriteres = new DimensionCriteresRechercheModel();
//                dimensionCriteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
//                dimensionCriteres.setLibelle(event.getSuggest());
//                dimensionCriteres.setVisible(true);
//                criteres.setDimensionCriteres(dimensionCriteres);
//                dimensionRpcService.rechercherRegimeParCriteres(criteres, event.getCallBack());
//            }
//        });
//        view.getCaisseSuggestHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<CaisseSimpleModel>() {
//            @Override
//            public void suggest(SuggestListBoxSuggestEvent<CaisseSimpleModel> event) {
//                final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
//                criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
//                criteres.setLibelle(event.getSuggest());
//                final DimensionCriteresRechercheCaisseModel criteresCaisse = new DimensionCriteresRechercheCaisseModel();
//                criteresCaisse.setDimensionCriteres(criteres);
//                criteresCaisse.setIdRegime(view.getRegime().getValue() != null ? view.getRegime().getValue().getIdentifiant() : null);
//                dimensionRpcService.rechercherCaisseParCriteres(criteresCaisse, event.getCallBack());
//            }
//        });
//        view.getRegimeValueChangeHandler().addValueChangeHandler(new ValueChangeHandler<IdentifiantLibelleGwt>() {
//            @Override
//            public void onValueChange(ValueChangeEvent<IdentifiantLibelleGwt> event) {
//                if (view.getReferent() == null || view.getReferent().getValue() == null || view.getReferent().getValue().getIdentifiant() == null) {
//                    if (event.getValue() != null) {
//                        // on recupere le 1er element de la liste des caisses
//                        final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel();
//                        criteres.setMaxResults(1);
//                        criteres.setLibelle("");
//                        final DimensionCriteresRechercheCaisseModel criteresCaisse = new DimensionCriteresRechercheCaisseModel();
//                        criteresCaisse.setDimensionCriteres(criteres);
//                        criteresCaisse.setIdRegime(event.getValue().getIdentifiant());
//                        criteresCaisse.setIdDepartement(idDepartement);
//                        dimensionRpcService.rechercherCaisseParCriteres(criteresCaisse, new AsyncCallback<List<CaisseSimpleModel>>() {
//                            @Override
//                            public void onFailure(Throwable caught) {
//                                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
//                            }
//
//                            @Override
//                            public void onSuccess(List<CaisseSimpleModel> result) {
//                                if (result != null && result.size() == 1) {
//                                    view.getCaisse().setValue(result.get(0));
//                                } else {
//                                    view.getCaisse().setValue(null);
//                                }
//                            }
//                        });
//                    } else {
//                        view.getCaisse().setValue(null);
//                    }
//                    view.enableInfoSante(true);
//                }
//                else {
//                    view.enableInfoSante(false);
//                }
//            }
//        });
//        view.getReferentSuggestHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<IdentifiantEidLibelleModel>() {
//            @Override
//            public void suggest(SuggestListBoxSuggestEvent<IdentifiantEidLibelleModel> event) {
//                event.getCallBack().onSuccess(construireListeReferencesSs());
//            }
//        });
//        view.getReferentValueChangeHandler().addValueChangeHandler(new ValueChangeHandler<IdentifiantEidLibelleModel>() {
//            @Override
//            public void onValueChange(ValueChangeEvent<IdentifiantEidLibelleModel> event) {
//                if (event.getValue() != null && event.getValue().getIdentifiant() != null) {
//                    final PersonnePhysiqueRelationModel relation = mapRelationsPP.get(event.getValue().getIdentifiant());
//                    view.getNumeroSecuriteSociale().setValue(relation.getNumSS());
//                    view.getCleSecuriteSociale().setValue(relation.getCleNumSS());
//                    view.getRegime().setValue(relation.getRegime());
//                    view.getCaisse().setValue(relation.getCaisse());
//                }
//                else {
//                    view.getNumeroSecuriteSociale().setValue("");
//                    view.getCleSecuriteSociale().setValue("");
//                    view.getRegime().setValue(null);
//                    view.getCaisse().setValue(null);
//                }
//            }
//        });

        // Si la personne source est une personne morale : init le type de personne
        if (typePM.equals(typePersonneSource)) {
            view.getSlbTypePersonneValue().setValue(new IdentifiantLibelleGwt(typePP, view.getViewConstants().typePersonnePP()));
        }

        deskBarRegistration = deskBar.getEventBus().addHandler(EnableMinimizeWidgetEvent.TYPE, new EnableMinimizeWidgetEventHandler() {
            @Override
            public void enableMinimizeWidget(EnableMinimizeWidgetEvent event) {
                view.activerBoutonReduire(event.isEnabled());
            }
        });

    }

    /**
     * Recherche si une personne a des doublons.
     * @param personne la personne.
     * @param isBeneficiaire indique si la personne est un bénéficiaire.
     */
    private void rechercherDoublonsPersonne() {
        // On affiche une popup de chargement durant le déroulement du processus de création de la relation
        view.afficherLoadingPopup(new LoadingPopupConfiguration(presenterConstants.recherchePersonneEnCours()));
        // Création des critères de recherche
        final String nom = view.getTbNom().getValue();
        final String prenom = view.getTbPrenom().getValue();
        final RechercheDoublonCritereModel criteres = new RechercheDoublonCritereModel();
        criteres.setNom(nom);
        criteres.setPrenom(prenom);
        if (view.getCdbDateNaissance().getValue() != null) {
            criteres.setDateNaissance(DateUtil.getString(view.getCdbDateNaissance().getValue()));
        }

        // appel du service
        final AsyncCallback<List<PersonneDoublonModel>> asyncCallback = new AsyncCallback<List<PersonneDoublonModel>>() {
            @Override
            public void onSuccess(List<PersonneDoublonModel> listeDoublons) {
                view.stopAllLoadingPopup();
                if (listeDoublons.size() > 0)
                {
                    if (popupCreationPersonneDoublonPresenter == null)
                    {
                        popupCreationPersonneDoublonPresenter = addChildPresenter(
                            new PopupCreationPersonneDoublonPresenter(eventBus, new PopupCreationPersonneDoublonViewImpl(nom, prenom, false, listeDoublons),
                                null, false, true));
                        popupCreationPersonneDoublonPresenter.showPresenter(null);
                        popupCreationPersonneDoublonPresenter.addEventHandlerToLocalBus(CreerPersonneRelationEvent.TYPE,
                            new CreerPersonneRelationEventHandler() {
                            @Override
                            public void creerPersonneRelation(CreerPersonneRelationEvent event) {
                                idPersonneCible = event.getIdPersonneSelectionnee();
                                ajouterRelationAvecPersonne();
                            }
                        });
                    }
                    else
                    {
                        popupCreationPersonneDoublonPresenter.showPopup(nom, prenom, false, listeDoublons);
                    }
                }
                else
                {
                    ajouterRelationAvecPersonne();
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
     * Ajouter une relation avec creation de personne.
     */
    private void ajouterRelationAvecPersonne() {
        // NECESSAIRE POUR LA CREATION EN DEUX TEMPS D'UNE RELATION
        if (idPersonneCible == null) {
            // Récupération du type de relation à créer pour déterminer la nature de la personne à créer
            Long idTypeRelation = null;
            if (view.getSlbTypeRelationValue().getValue() != null) {
                final String[] tokenType = view.getSlbTypeRelationValue().getValue().getItem().split("#");
                idTypeRelation = Long.valueOf(tokenType[0]);
            }
            // Vérification si une relation conjoint existe déjà
            if (constantes.getIdTypeRelationConjoint().equals(idTypeRelation)) {
                final AsyncCallback<Boolean> asyncCallback = new AsyncCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            view.onRpcServiceFailure(new ErrorPopupConfiguration(presenterConstants.errorRelationCointExisteDeja()));
                        }
                        else {
                            creerPersonnePourRelation();
                        }
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                    }
                };
                personneRpcService.hasConjoint(idPersonne, asyncCallback);
            }
            else {
                creerPersonnePourRelation();
            }
        }
        else {
            ajouterRelation(false);
        }
    }

    /**
     * Méthode permettant d'ajouter un enfant au conjoint d'une personne.
     * @param idConjoint l'identifiant du conjoint.
     */
    public void ajouterEnfantConjoint(Long idConjoint) {
        if (idConjoint != null && idPersonneCible != null) {
            final Long tmp = idPersonne;
            idPersonne = idConjoint;
            ajouterRelation(true);
            idPersonne = tmp;
        }
        else {
            actualiserRelations();
        }
    }

    /** Crée la personne pour la relation. */
    private void creerPersonnePourRelation() {
        // On affiche une popup de chargement durant le déroulement du processus de création de la relation
        view.afficherLoadingPopup(new LoadingPopupConfiguration(presenterConstants.creationRelationEnCours()));
        // Récupération des valeurs
        final PersonnePhysiqueCopieModel personneInfosCopie = new PersonnePhysiqueCopieModel();
        if (view.getSlbCivilite().getValue() != null) {
            personneInfosCopie.setCivilite(view.getSlbCivilite().getValue().getIdentifiant());
        }
        else {
            personneInfosCopie.setCivilite(null);
        }
        personneInfosCopie.setNom(view.getTbNom().getValue());
        personneInfosCopie.setPrenom(view.getTbPrenom().getValue());
        if (view.getCdbDateNaissance().getValue() != null) {
            personneInfosCopie.setDateNaissance(DateUtil.getString(view.getCdbDateNaissance().getValue()));
        }
        personneInfosCopie.setIdPersonneSource(idPersonne);
        // Si le type de la relation est conjoint ou enfant : nature Bénéficiaire Prospect
        Long idTypeRelation = null;
        if (view.getSlbTypeRelationValue().getValue() != null) {
            final String[] tokenType = view.getSlbTypeRelationValue().getValue().getItem().split("#");
            idTypeRelation = Long.valueOf(tokenType[0]);
        }
        if (constantes.getIdTypeRelationConjoint().equals(idTypeRelation) || constantes.getIdTypeRelationEnfant().equals(idTypeRelation)) {
            personneInfosCopie.setDupliquerCoordonnees(false);
            personneInfosCopie.setIdNaturePersonne(constantes.getIdNaturePersonneBeneficiaireProspect());
//                     // Annulation 5449
//            if (constantes.getIdNaturePersonneAdherent().equals(personneEnCours.getNaturePersonne().getIdentifiant())) {
//                final InfoSanteModel infosSante = new InfoSanteModel();
//                if (view.getReferent() != null && view.getReferent().getValue() != null) {
//                    infosSante.setIdReferent(view.getReferent().getValue().getIdentifiant());
//                }
//                infosSante.setNro(view.getNumeroSecuriteSociale().getValue() + view.getCleSecuriteSociale().getValue());
//                if (view.getCaisse().getValue() != null && !PersonneRelationsViewImplConstants.IDENTIFIANT_NC.equals(view.getCaisse().getValue().getId())) {
//                    infosSante.setCaisse(new CaisseSimpleModel(view.getCaisse().getValue().getId()));
//                }
//                if (view.getRegime().getValue() != null
//                        && !PersonneRelationsViewImplConstants.IDENTIFIANT_NC.equals(view.getRegime().getValue().getIdentifiant())) {
//                    infosSante.setCaisseRegime(view.getRegime().getValue());
//                }
//                personneInfosCopie.setInfosSante(infosSante);
//            }
        }
        else {
            personneInfosCopie.setDupliquerCoordonnees(true);
        }
        // Ajout du téléphone si la personne source est une PM
        if (typePM.equals(typePersonneSource)) {
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
            personneInfosCopie.setTelephone(telephone);
        }
        // Appel de la copie
        personnePhysiqueRpcService.creerUneCopiePersonne(personneInfosCopie, new AsyncCallback<PersonneModel>() {
            @Override
            public void onSuccess(PersonneModel result) {
                idPersonneCible = result.getIdentifiant();
                ajouterRelation(false);
            }

            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                    // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                    view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                    // Mantis Smatis id 0007142
                    // On filte les erreurs relatives à la personne source de la relation
                    final RapportModel rapportPersonneSource = ((ControleIntegriteExceptionGwt) caught).getRapport();
                    // Liste les messages d'erreurs présents dans les sous rapports
                    final Set<String> listeMessagesErreurs = new HashSet<String>();
                    final Map<String, SousRapportModel> rapportsFiltres = new HashMap<String, SousRapportModel>();
                    for (final Entry<String, SousRapportModel> entry : rapport.getRapports().entrySet()) {
                        final String key = entry.getKey();
                        final SousRapportModel value = entry.getValue();
                        // On filtre les sous rapports pour n'afficher que les messages spécifiques à la personne source de la relation
                        // On filtre aussi les messages afin d'éviter les doublons
                        if (!key.equals("PersonneDto.civilite") && !key.equals("PersonneDto.nom") && !key.equals("PersonneDto.prenom")
                            && !key.equals("PersonneDto.dateNaissance") && !listeMessagesErreurs.contains(value.getMessage())) {
                            // On stocke le message d'erreur
                            listeMessagesErreurs.add(value.getMessage());
                            // On ajoute le sous rapport
                            rapportsFiltres.put(key, value);
                        }
                    }
                    rapportPersonneSource.setMessageGenerale(view.getViewConstants().titreErreursPersonneSource());
                    rapportPersonneSource.setRapports(rapportsFiltres);
                    view.getErreursPersonneSourceManager().fireEvent(new ControleIntegriteExceptionEvent(rapportPersonneSource));
                    view.stopAllLoadingPopup();
                    view.initFocus();
                }
                else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }
        });
    }

    /**
     * Permet d'ajouter une nouvelle relation.
     * @param ajouterRelationConjointEnfant booléen pour savoir si l'ajout de la relation concerne un ajout
     * supplémentaire pour une relation entre le conjoint et l'enfant.
     */
    private void ajouterRelation(final boolean ajouterRelationConjointEnfant) {
        // Création de la relation.
        final RelationModel relation = new RelationModel();
        relation.setIdPersonnePrincipale(idPersonne);
        relation.setIdPersonne(idPersonneCible);
        if (view.getSlbTypeRelationValue().getValue() != null) {
            final String[] tokenType = view.getSlbTypeRelationValue().getValue().getItem().split("#");
            relation.setType(new IdentifiantLibelleGwt(Long.valueOf(tokenType[0]), tokenType[1]));
        }
        relation.setDateDebut(DateUtil.getString(new Date()));
        personneRpcService.creerRelation(relation, new AsyncCallback<RelationModel>() {
            @Override
            public void onSuccess(RelationModel result) {
                    ajouterRelationConjointEnfant(ajouterRelationConjointEnfant);
            }

            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    final RapportModel rapport = ((ControleIntegriteExceptionGwt) caught).getRapport();
                    // PASSAGE DE L'EXCEPTION VERS LES COMPOSANTS
                    view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
                    view.stopAllLoadingPopup();
                    view.initFocus();
                }
                else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }
        });
    }

    /**
     * Demande si possible d'ajouter la relation entre le conjoint et l'enfant créé.
     * @param ajouterRelationConjointEnfant booléen pour savoir si l'ajout de la relation concerne un ajout
     * supplémentaire pour une relation entre le conjoint et l'enfant.
     * */
    private void ajouterRelationConjointEnfant(final boolean ajouterRelationConjointEnfant) {
        // On vérifie si la relation était un lien parent-enfant.
        final AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!ajouterRelationConjointEnfant) {
                    String libTypeRelation = null;
                    if (view.getSlbTypeRelationValue().getValue() != null) {
                        final String[] tokenType = view.getSlbTypeRelationValue().getValue().getItem().split("#");
                        libTypeRelation = tokenType[1];
                    }
                    // Si c'est le cas, on regarde si on a un conjoint et on propose de lui ajouter l'enfant
                    if (result.equals(libTypeRelation)) {
                        final AsyncCallback<Long> asyncCallback = new AsyncCallback<Long>() {
                            @Override
                            public void onSuccess(final Long result) {
                                // Si la personne a un conjoint, on propose de rajouter l'enfant à celui-ci également
                                if (result != null)
                                {
                                    if (personneRelationAjouterEnfantConjoint == null)
                                    {
                                        personneRelationAjouterEnfantConjoint = addChildPresenter(new PersonneRelationAjouterEnfantConjointPresenter(
                                            eventBus, new PersonneRelationAjouterEnfantConjointViewImpl(), result));
                                        personneRelationAjouterEnfantConjoint.getBtnNon().addClickHandler(new ClickHandler() {
                                            @Override
                                            public void onClick(ClickEvent event) {
                                                actualiserRelations();
                                            }
                                        });
                                        personneRelationAjouterEnfantConjoint.getBtnOui().addClickHandler(new ClickHandler() {
                                            @Override
                                            public void onClick(ClickEvent event) {
                                                ajouterEnfantConjoint(result);
                                            }
                                        });
                                        personneRelationAjouterEnfantConjoint.showPresenter(null);
                                    }
                                    else
                                    {
                                        personneRelationAjouterEnfantConjoint.setIdConjoint(result);
                                        personneRelationAjouterEnfantConjoint.onShow(null);
                                    }
                                } else {
                                    actualiserRelations();
                                }
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                            }
                        };
                        personneRpcService.getIdConjoint(idPersonne, asyncCallback);
                    }
                    else {
                        GWT.log("2");
                        actualiserRelations();
                    }
                }
                else {
                    GWT.log("1");
                    actualiserRelations();
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        };
        personneRpcService.getLibelleAPourEnfant(asyncCallback);
    }

        /**
     * Inilialise le nom de la popup de creation en fonction des filtres sur le groupement.
     */
    private void initTextPopupCreation() {
        boolean famille = false;
        if (filtreGroupements != null) {
            for (Long groupement : filtreGroupements) {
                if (groupement.equals(constantes.getIdGroupementFamiliale())) {
                    famille = true;
                    break;
                }
            }
        }
        view.setTextPopupCreation(famille);
    }

    /** Initialisation du téléphone (pays). */
    private void initTelephone() {
        final Long idCodePaysDefaut = new Long(constantes.getIdPaysParDefaut());
        // Telephone
        final DimensionCriteresRechercheModel criteres = new DimensionCriteresRechercheModel(constantes.getIdNatureTelephoneFixe());
        criteres.setMaxResults(AppControllerConstants.SUGGESTLISBBOX_NB_RESULTATS_MAX);
        dimensionRpcService.rechercherNatureTelephoneParCriteres(criteres, new AsyncCallback<List<IdentifiantLibelleGwt>>() {
            @Override
            public void onSuccess(List<IdentifiantLibelleGwt> result) {
                view.getSlbNatureTelephoneValue().setValue(result.get(0));
            }

            @Override
            public void onFailure(Throwable caught) {
                view.getSlbNatureTelephoneValue().setValue(new IdentifiantLibelleGwt());
            }
        });
        view.setIdPaysTelephone(idCodePaysDefaut);
        // Si source est une PM : affichage du panel PP avec numéro de téléphone
        if (typePM.equals(typePersonneSource)) {
            view.centrerPopup(PersonneRelationPopupView.LARGEUR_PP_TELEPHONE);
        } else {
            view.switchPanelPopupPersonnePP();
            view.centrerPopup(PersonneRelationPopupView.LARGEUR_PP);
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
        view.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
        return datesValides;
    }

    /** Ferme la popup. */
    private void fermerPopup() {
        view.fermerPopupCreation();
    }
 // Annulation 5449
//    /** Initialisation du département de la personne principale. */
//    private void initDepartement() {
//        final AsyncCallback<CoordonneesModel> asyncCallback =  new AsyncCallback<CoordonneesModel>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
//            }
//
//            @Override
//            public void onSuccess(CoordonneesModel result) {
//                if (result.getAdresses() != null && result.getAdresses().size() > 0) {
//                    for (AdresseModel adresse : result.getAdresses()) {
//                        if (adresse.getNature() != null && adresse.getNature().getIdentifiant() != null
//                                && constantes.getIdNatureAdressePrincipale().equals(adresse.getNature().getIdentifiant())) {
//                            if (adresse.getDepartement() != null) {
//                                idDepartement = adresse.getDepartement().getIdentifiant();
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        };
//        personneRpcService.rechercherCoordonneesParIdPersonne(idPersonne, asyncCallback);
//    }
//
//    /** Initialise la liste des référents. */
//    private void initReferents() {
//        view.switchPanelPopupPersonnePP();
//
//        // Ajout de la personne principale
//        personnePhysiqueRpcService.rechercherPersonneParIdentifiant(idPersonne, new AsyncCallback<PersonneModel>() {
//            @Override
//            public void onSuccess(PersonneModel result) {
//                personneEnCours = result;
//                // Affichage des infos santé pour les personnes physiques adhérentes avec des relations de type famille.
//                if (typePP.equals(typePersonneSource) && constantes.getIdNaturePersonneAdherent().equals(result.getNaturePersonne().getIdentifiant())
//                        && filtreGroupements != null) {
//                    view.afficherInfosSante(true);
//
//                    String numSS = "";
//                    String cleSS = "";
//                    if (result.getInfoSante() != null && result.getInfoSante().getNro() != null) {
//                        numSS = result.getInfoSante().getNro().substring(0, 13);
//                        cleSS = result.getInfoSante().getNro().substring(13, 15);
//                    }
//                    final PersonnePhysiqueRelationModel relationPersonnePhysique = new PersonnePhysiqueRelationModel();
//                    relationPersonnePhysique.setId(result.getIdentifiant());
//                    relationPersonnePhysique.setAge(result.getAge());
//                    relationPersonnePhysique.setCivilite(result.getCivilite());
//                    relationPersonnePhysique.setCleNumSS(cleSS);
//                    relationPersonnePhysique.setDateDeces(result.getDateDeces());
//                    relationPersonnePhysique.setDateNaissance(result.getDateNaissance());
//                    relationPersonnePhysique.setDeces(result.isDecede());
//                    relationPersonnePhysique.setNaturePersonne(result.getNaturePersonne());
//                    relationPersonnePhysique.setNom(result.getNom());
//                    relationPersonnePhysique.setNum(result.getNumClient());
//                    relationPersonnePhysique.setNumSS(numSS);
//                    relationPersonnePhysique.setPrenom(result.getPrenom());
//                    if (result.getInfoSante() != null) {
//                        relationPersonnePhysique.setCaisse(result.getInfoSante().getCaisse());
//                        relationPersonnePhysique.setRegime(result.getInfoSante().getCaisseRegime());
//                    }
//                    mapRelationsPP.put(result.getIdentifiant(), relationPersonnePhysique);
//                    view.getReferent().setValue(construireReferent(relationPersonnePhysique));
//
//                    // Ajout des autres relations
//                    chargerReferentsFamille();
//
//                    view.centrerPopup(PersonneRelationPopupView.LARGEUR_PP_INFOS_SANTE);
//                }
//                else {
//                    view.afficherInfosSante(false);
//                    view.centrerPopup(PersonneRelationPopupView.LARGEUR_PP);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable caught) {
//                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
//            }
//        });
//    }
//
//    /** Charge les référents de la famille autre que la personne principale. */
//    private void chargerReferentsFamille() {
//        final RelationCriteresRechercheModel criteres = new RelationCriteresRechercheModel();
//        criteres.setIdPersonne(idPersonne);
//        criteres.setGroupements(filtreGroupements);
//        criteres.setPasDansGroupements(filtrePasDansGroupements);
//        personneRpcService.rechercherRelationsParCritreres(criteres, new AsyncCallback<List<RelationInfosModel<? extends PersonneRelationModel>>>() {
//            @Override
//            public void onSuccess(List<RelationInfosModel<? extends PersonneRelationModel>> result) {
//                if (result != null) {
//                    // Ajout des autres relations
//                    for (final RelationInfosModel<? extends PersonneRelationModel> relation : result) {
//                        if (relation.getPersonne() instanceof PersonnePhysiqueRelationModel) {
//                            final PersonnePhysiqueRelationModel relationPersonnePhysique = (PersonnePhysiqueRelationModel) relation.getPersonne();
//                            if (relationPersonnePhysique.getNumSS() != null && !"".equals(relationPersonnePhysique.getNumSS())
//                                    && relationPersonnePhysique.getCleNumSS() != null && !"".equals(relationPersonnePhysique.getCleNumSS())) {
//                                mapRelationsPP.put(relationPersonnePhysique.getId(), relationPersonnePhysique);
//                            }
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable caught) {
//                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
//            }
//        });
//    }
//
//    /**
//     * Construit la liste avec les informations de securité sociale des parents.
//     * @return la liste construite.
//     */
//    private List<IdentifiantEidLibelleModel> construireListeReferencesSs() {
//        final List<IdentifiantEidLibelleModel> listeReferencesSs = new ArrayList<IdentifiantEidLibelleModel>();
//        listeReferencesSs.add(new IdentifiantEidLibelleModel(null, view.getViewConstants().labelSaisieInfosSecuriteSociale()));
//
//        if (typePP.equals(typePersonneSource)) {
//            for (Long idPersonneRelation : mapRelationsPP.keySet()) {
//                listeReferencesSs.add(construireReferent(mapRelationsPP.get(idPersonneRelation)));
//            }
//        }
//
//        return listeReferencesSs;
//    }
//
//    /**
//     * Construit une reference SS.
//     * @return la reference
//     */
//    private IdentifiantEidLibelleModel construireReferent(PersonnePhysiqueRelationModel infosPersonne) {
//        final IdentifiantEidLibelleModel refSS = new IdentifiantEidLibelleModel();
//        refSS.setIdentifiant(infosPersonne.getId());
//        refSS.setLibelle(infosPersonne.getPrenom() + " (" + infosPersonne.getNumSS() + " " + infosPersonne.getCleNumSS() + ")");
//        return refSS;
//    }

    /** Actualise les relations et ferme la popup. */
    public void actualiserRelations() {
        view.stopAllLoadingPopup();
        fermerPopup();
        final PopupInfoConfiguration config =
            new PopupInfoConfiguration(presenterConstants.notificationRelationCree(), AppControllerConstants.NOTIFICATION_TIME_OUT);
        config.setCallback(new PopupCallback() {
            @Override
            public void onResult(boolean result) {
                fireEventLocalBus(new SimpleValueChangeEvent<String>(null));
            }
        });
        new DecoratedInfoPopup(config).show();
    }

    /**
     * Interface de la vue de la popup des relations de personne.
     * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
     */
    public interface PersonneRelationPopupView extends View {

        /** Largeur de la popup PP. */
        String LARGEUR_PP = "420px";

        /** Largeur de la popup PP avec infos santé. */
        String LARGEUR_PP_INFOS_SANTE = "770px";

        /** Largeur de la popup PP avec téléphone. */
        String LARGEUR_PP_TELEPHONE = "700px";

        /** Largeur de la popup PM. */
        String LARGEUR_PM = "550px";

        /**
         * Accesseur gestionnaire des touches sur le focus panel.
         * @return le handler sur le focus panel
         */
        HasAllKeyHandlers getFocusPanelAllKeyHandlers();

        /**
         * Affiche la popup de création.
         */
        void afficherPopupAjoutRelation();

        /**
         * Ferme la popup d'ajout.
         */
        void fermerPopupCreation();

        /**
         * Valeur du suggestHandler civilite.
         * @return .
         */
        DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbCivilite();

        /**
         * Renvoi la texteBox du nom.
         * @return tbNom
         */
        HasValue<String> getTbNom();

        /**
         * Renvoi la texteBox du prénom.
         * @return tbPrenom
         */
        HasValue<String> getTbPrenom();

        /**
         * Renvoi la date de naissance.
         * @return cdbDateNaissance
         */
        DecoratedCalendrierDateBox getCdbDateNaissance();

        /**
         * Renvoi la listebox relation.
         * @return lbRelation
         */
        HasSuggestListBoxHandler<ItemValueModel> getSlbTypeRelation();

        /**
         * Renvoi la listebox personne.
         * @return lbRelation
         */
        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbTypePersonne();

        /**
         * Renvoi la valeur de slbTypeRelation.
         * @return slbTypeRelation
         */
        HasValue<ItemValueModel> getSlbTypeRelationValue();

        /**
         * Boutton de création d'une relation.
         * @return btEnregistrerCreation
         */
        HasClickHandlers getBtEnregistrerCreation();

        /**
         * Affiche une popUp d'erreur.
         * @param errorPopupConfiguration la configuration de la popup d'erreur
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Retourne le lien.
         * @return le lien
         */
        HasClickHandlers getBtAnnuler();

        /**
         * Manager des icones de la vue.
         * @return .
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Accesseur pour le gestionnaire d'erreurs relatives à la personne source de la relation.
         * @return le gestionnaire.
         */
        HandlerManager getErreursPersonneSourceManager();

        /**
         * Les constantes de la vue.
         * @return .
         */
        PersonneRelationsViewImplConstants getViewConstants();

        /**
         * Afficher la popup de chargement.
         * @param config configuration de la popup de chargement.
         */
        void afficherLoadingPopup(LoadingPopupConfiguration config);

        /**
         * Notification a la vue reussite d'un appel RPc.
         */
        void onRpcServiceSuccess();

        /**
         * Permet d'arreter les popups de chargements.
         */
        void stopAllLoadingPopup();

        /**
         * Le Handler du tableau pagine.
         * @return le handler.
         */
        HandlerManager getRemotePagingHandlerManagerPm();

        /**
         * Permet de lancer une recherche sur le tableau.
         */
        void lancerLaRecherchePm();

        /**
         * Text Box raison sociale.
         * @return .
         */
        HasValue<String> getTbRaisonSocialePm();

        /**
         * Text Box numero d'entreprise.
         * @return .
         */
        HasValue<String> getTbNumEntreprisePm();

        /**
         * Bouton rechercher du moteur de recherche sur les PM.
         * @return .
         */
        HasClickHandlers getBtnRechercherPm();

        /**
         * Selection des types de personnes pour l'ajout de relation.
         * @return le listBox.
         */
        HasValueChangeHandlers<IdentifiantLibelleGwt> getTypePersonneChangeHandler();

        /**
         * Masquer le panel type personne.
         */
        void masquerTypePersonneVisible();

        /**
         * Permet de switcher de la selection de PM vers la selection de PP.
         */
        void switchPanelPopupPersonnePP();

        /**
         * Permet de switcher vers la selection de PP avec saisie du numéro de téléphone.
         */
        void switchPanelPopupPersonnePPNumeroTelephone();

        /**
         * Permet de switcher de la selection de PP vers la selection de PM.
         */
        void switchPanelPopupPersonnePM();

        /**
         * Changer le libelle de la popup de creation.
         * @param famille indique si la popup sert à creer des relation de types familliales.
         */
        void setTextPopupCreation(boolean famille);

        /**
         * Centre la popup.
         * @param width largeur de la popup
         */
        void centrerPopup(String width);

        /**
         * Retourne le widget.
         * @return le widget
         */
        HasValue<String> getTbTelephone();

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
         * Accesseur sur l'image du pays du téléphone 1.
         * @return le widget.
         */
        HasClickHandlers getImgFlagPaysTelephone();

        /**
         * Charge l'image du pays du téléphone.
         * @param url l'url de l'image.
         * @param title le title de l'image.
         */
        void chargerImagePaysTelephone(String url, String title);

        /**
         * Initialise la TextBox du téléphone.
         * @param format le nouveau format.
         */
        void initTbTelephone(String format);

        /**
         * Masque un message de chargement.
         */
        void masquerLoadingPopup();

        /**
         * Initialise le focus.
         */
        void initFocus();

        /**
         * Retourne la valeur.
         * @return la valeur.
         */
        HasValue<IdentifiantLibelleGwt> getSlbTypePersonneValue();

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
// Annulation 5449
//        /**
//         * Récupère le régime.
//         * @return le régime.
//         */
//        HasValue<IdentifiantLibelleGwt> getRegime();
//
//        /**
//         * Récupère la caisse.
//         * @return la caisse.
//         */
//        HasValue<CaisseSimpleModel> getCaisse();
//
//        /**
//         * Récupère le numéro de sécurité sociale.
//         * @return le numéro de sécurité sociale.
//         */
//        HasValue<String> getNumeroSecuriteSociale();
//
//        /**
//         * Récupère la clé de sécurité sociale.
//         * @return la clé de sécurité sociale.
//         */
//        HasValue<String> getCleSecuriteSociale();
//
//        /**
//         * Récupère le référent des infos santé.
//         * @return le référent des infos santé.
//         */
//        HasValue<IdentifiantEidLibelleModel> getReferent();
//
//        /**
//         * Recupere le handler.
//         * @return le handler
//         */
//        HasSuggestListBoxHandler<CaisseSimpleModel> getCaisseSuggestHandler();
//
//        /**
//         * Recupere le handler.
//         * @return le handler
//         */
//        HasSuggestListBoxHandler<IdentifiantLibelleGwt> getRegimeSuggestHandler();
//
//        /**
//         * Recupere le handler.
//         * @return le handler
//         */
//        HasValueChangeHandlers<IdentifiantLibelleGwt> getRegimeValueChangeHandler();
//
//        /**
//         * Recupere le handler.
//         * @return le handler
//         */
//        HasSuggestListBoxHandler<IdentifiantEidLibelleModel> getReferentSuggestHandler();
//
//        /**
//         * Handler pour la selection d'infos de sécurité sociale d'un référent.
//         * @return le handler.
//         */
//        HasValueChangeHandlers<IdentifiantEidLibelleModel> getReferentValueChangeHandler();
//
//        /**
//         * Active/Désactive la saisie des infos santé.
//         * @param enabled true pour activer, false pour désactiver.
//         */
//        void enableInfoSante(boolean enabled);
//
//        /**
//         * Affiche ou non les infos santé.
//         * @param affiche true pour afficher, false pour masquer.
//         */
//        void afficherInfosSante(boolean affiche);

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasValueChangeHandlers<ItemValueModel> getSlbTypeRelationValueChangeHandler();
    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
        deskBarRegistration.removeHandler();
    }
}

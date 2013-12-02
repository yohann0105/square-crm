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
package com.square.client.gwt.client.view.action.moteur.recherche;

import java.util.ArrayList;
import java.util.List;

import org.gwtwidgets.client.ui.pagination.Column;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetCellClickedEvent;
import org.scub.foundation.framework.gwt.module.client.mvp.event.util.SetDataProviderEvent;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingSortGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.grid.remote.paging.RemotePagingTable;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxComposite.SuggestListBoxCompositeProperties;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.composant.bloc.ContenuOnglet;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.ActionCritereRechercheModel;
import com.square.client.gwt.client.model.ActionRechercheModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.presenter.action.ActionMoteurRecherchePresenter.ActionMoteurRechercheView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxComposite;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;

/**
 * Implémentation de la vue du moteur de recherche des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionMoteurRechercheViewImpl extends Composite implements ActionMoteurRechercheView {

    private static final String ESPACE = " ";

    private FlexTable conteneur;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbType;

    private AideComposant aideslbType;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbStatut;

    private AideComposant aideslbStatut;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbPriorite;

    private AideComposant aideslbPriorite;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbNature;

    private AideComposant aideslbNature;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbResultat;

    private AideComposant aideslbResultat;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbNatureResultat;

    private AideComposant aideslbNatureResultat;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbTypeCampagne;

    private AideComposant aideslbTypeCampagne;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbObjet;

    private AideComposant aideslbObjet;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbSousObjet;

    private AideComposant aideslbSousObjet;

    private DecoratedButton btnRechercher;

    private DecoratedButton btnEffacerRecherche;

    private DecoratedCalendrierDateBox clBdateDebut;

    private AideComposant aideclBdateDebut;

    private DecoratedCalendrierDateBox clBdateFin;

    private AideComposant aideclBdateFin;

    private CheckBox cbReclamation;

    private DecoratedSuggestListBoxComposite<DimensionRessourceModel> slbCreateur;

    private AideComposant aideslbCreateur;

    private DecoratedSuggestListBoxComposite<DimensionRessourceModel> slbAttribueA;

    private AideComposant aideslbAttribueA;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbAgence;

    private AideComposant aideslbAgence;

    private CheckBox cbRechercheEtEntreAgencesEtCommerciaux;

    /** Propriétés pour les listes. */
    private SuggestListBoxCompositeProperties<IdentifiantLibelleGwt> slbIdLibelleProperties;

    private FocusPanel focusPanel;

    private ConstantesModel constantes;

    private HorizontalPanel panelExport;

    private Image imageExportExcel;

    private Label labelExportExcel;

    private AppControllerConstants appConstantes;

    /** View constants. */
    static ActionsMoteurRechercheViewImplConstants viewConstants =
        (ActionsMoteurRechercheViewImplConstants) GWT.create(ActionsMoteurRechercheViewImplConstants.class);

    /** View constants. */
    static ActionsMoteurRechercheViewImplDebugIdConstants viewDebugIdConstants =
        (ActionsMoteurRechercheViewImplDebugIdConstants) GWT.create(ActionsMoteurRechercheViewImplDebugIdConstants.class);

    private RemotePagingTable<ActionRechercheModel, ActionCritereRechercheModel> remotePagingTableAction;

    /** Pour recuperer les evenements sur le tableau dans le Presenter. */
    private HandlerManager remotePagingHandlerManager;

    private VerticalPanel contextMenu;

    private Label lbContextMenu;

    /** Boutton transfert action. */
    private DecoratedButton boutonTransfererActions;

    private boolean isAdmin;

    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    /**
     * Constructeur.
     * @param constantes constantes.
     * @param appConstantes les appConstantes.
     */
    public ActionMoteurRechercheViewImpl(ConstantesModel constantes, AppControllerConstants appConstantes) {
        this.constantes = constantes;
        this.appConstantes = appConstantes;
        focusPanel = new FocusPanel();
        this.isAdmin = constantes.isHasRoleAdmin();
        final VerticalPanel conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setSpacing(10);
        final AideComposant aideView = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_GLOBAL, isAdmin);
        ajouterAideComposant(aideView);
        conteneurGlobal.add(aideView);
        conteneurGlobal.setCellHorizontalAlignment(aideView, HasAlignment.ALIGN_RIGHT);
        conteneurGlobal.setWidth(AppControllerConstants.POURCENT_100);

        conteneur = new FlexTable();
        conteneur.setCellSpacing(10);
        conteneur.setWidth(AppControllerConstants.POURCENT_100);

        final Widget identificationWidget = construireBlocIdentification();
        final Widget creationWidget = construireBlocCreation();
        final Widget planificationWidget = construireBlocPlanification();
        final Widget informationWidget = construireBlocInformation();

        conteneur.setWidget(0, 0, identificationWidget);
        conteneur.setWidget(0, 1, creationWidget);
        conteneur.setWidget(0, 2, planificationWidget);
        conteneur.getColumnFormatter().setWidth(0, "39%");
        conteneur.getColumnFormatter().setWidth(1, "39%");
        conteneur.getColumnFormatter().setWidth(2, "22%");
        conteneur.setWidget(1, 0, informationWidget);
        conteneur.getFlexCellFormatter().setColSpan(1, 0, 3);

        btnRechercher = new DecoratedButton(viewConstants.rechercher());
        btnRechercher.ensureDebugId(viewDebugIdConstants.debugIdBtnRechercher());
        btnEffacerRecherche = new DecoratedButton(viewConstants.effacer());
        btnEffacerRecherche.ensureDebugId(viewDebugIdConstants.debugIdBtnEffacerRecherche());

        final FlexTable conteneurButtons = new FlexTable();
        conteneurButtons.setCellSpacing(5);
        conteneurButtons.setWidget(0, 0, btnRechercher);
        conteneurButtons.setWidget(0, 1, btnEffacerRecherche);
        conteneur.setWidget(2, 0, conteneurButtons);
        conteneur.getCellFormatter().setHorizontalAlignment(2, 0, HasAlignment.ALIGN_CENTER);
        conteneur.getFlexCellFormatter().setColSpan(2, 0, 3);

        createRemotePagingTable();
        conteneur.setWidget(3, 0, remotePagingTableAction);
        conteneur.getCellFormatter().setHorizontalAlignment(3, 0, HasAlignment.ALIGN_CENTER);
        conteneur.getFlexCellFormatter().setColSpan(3, 0, 3);

        creerExportExcel();

        // FORME LE MENU CONTEXT
        contruireActionsContextPanel();
        conteneurGlobal.add(conteneur);
        focusPanel.add(new ContenuOnglet(conteneurGlobal));
        // conteneurGlobal.add(focusPanel);
        this.initWidget(focusPanel);
        this.setWidth(AppControllerConstants.POURCENT_100);
        this.addStyleName(SquareResources.INSTANCE.css().actionsMoteurRecherche());
    }

    private Widget construireBlocIdentification() {
        final SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdentifiantLibelleProperties =
            new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {

                @Override
                public String getSelectSuggestRenderer(IdentifiantLibelleGwt row) {
                    return row == null ? "" : row.getLibelle();
                }

                @Override
                public String[] getResultColumnsRenderer() {
                    return new String[] {};
                }

                @Override
                public String[] getResultRowsRenderer(IdentifiantLibelleGwt row) {
                    return new String[] {row == null ? "" : row.getLibelle()};
                }
            };
        slbType = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbType.ensureDebugId(viewDebugIdConstants.debugIdSlbType());
        aideslbType = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_TYPE, isAdmin);
        final HorizontalPanel panelslbType = new HorizontalPanel();
        panelslbType.setSpacing(5);
        panelslbType.add(slbType);
        panelslbType.add(aideslbType);
        ajouterAideComposant(aideslbType);

        slbObjet = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbObjet.ensureDebugId(viewDebugIdConstants.debugIdSlbObjet());
        aideslbObjet = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_OBJET, isAdmin);
        final HorizontalPanel panelslbObjet = new HorizontalPanel();
        panelslbObjet.setSpacing(5);
        panelslbObjet.add(slbObjet);
        panelslbObjet.add(aideslbObjet);
        ajouterAideComposant(aideslbObjet);

        slbSousObjet = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbSousObjet.ensureDebugId(viewDebugIdConstants.debugIdSlbSousObjet());
        aideslbSousObjet = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_SOUS_OBJET, isAdmin);
        final HorizontalPanel panelslbSousObjet = new HorizontalPanel();
        panelslbSousObjet.setSpacing(5);
        panelslbSousObjet.add(slbSousObjet);
        panelslbSousObjet.add(aideslbSousObjet);
        ajouterAideComposant(aideslbSousObjet);

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.identificaton());
        fieldSetPanel.setHeight(ActionsMoteurRechercheViewImplConstants.HAUTEUR_BLOC);

        final FlexTable fpIdentification = new FlexTable();
        fpIdentification.setCellSpacing(5);
        final Label type = creerLibelle(viewConstants.type());
        final Label objet = creerLibelle(viewConstants.objet());
        final Label sousObjet = creerLibelle(viewConstants.sousObjet());

        fpIdentification.setWidget(0, 0, type);
        fpIdentification.setWidget(0, 1, panelslbType);
        fpIdentification.setWidget(1, 0, objet);
        fpIdentification.setWidget(1, 1, panelslbObjet);
        fpIdentification.setWidget(2, 0, sousObjet);
        fpIdentification.setWidget(2, 1, panelslbSousObjet);

        fpIdentification.getColumnFormatter().setWidth(0, "25%");
        fieldSetPanel.add(fpIdentification);
        fieldSetPanel.setHeight("200px");
        return fieldSetPanel;
    }

    /**
     * Construit le bloc "création".
     * @return le bloc généré.
     */
    private Widget construireBlocCreation() {
        slbCreateur = new DecoratedSuggestListBoxComposite<DimensionRessourceModel>(new SuggestListBoxCompositeProperties<DimensionRessourceModel>() {
            @Override
            public String getSelectSuggestRenderer(DimensionRessourceModel row) {
                return construireLabelRessource(row, false);
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(DimensionRessourceModel row) {
                return new String[] {construireLabelRessource(row, true)};
            }

            @Override
            public String getSuggestListBoxMultiplePopupTitle() {
                return viewConstants.titrePopUpSelection();
            }

            @Override
            public Integer getLeftPosition() {
                return LEFT_POSITION_CENTER;
            }

            @Override
            public Integer getTopPosition() {
                return TOP_POSITION_CENTER;
            }
        });
        slbCreateur.ensureDebugId(viewDebugIdConstants.debugIdSlbCreateur());
        slbCreateur.setScrollPanelSuggestMultipleHeight(ActionsMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbCreateur = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_CREATEUR, isAdmin);
        final HorizontalPanel panelslbCreateur = new HorizontalPanel();
        panelslbCreateur.setSpacing(3);
        panelslbCreateur.add(slbCreateur);
        panelslbCreateur.add(aideslbCreateur);
        ajouterAideComposant(aideslbCreateur);

        slbAttribueA = new DecoratedSuggestListBoxComposite<DimensionRessourceModel>(new SuggestListBoxCompositeProperties<DimensionRessourceModel>() {
            @Override
            public String getSelectSuggestRenderer(DimensionRessourceModel row) {
                return construireLabelRessource(row, false);
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(DimensionRessourceModel row) {
                return new String[] {construireLabelRessource(row, true)};
            }

            @Override
            public String getSuggestListBoxMultiplePopupTitle() {
                return viewConstants.titrePopUpSelection();
            }

            @Override
            public Integer getLeftPosition() {
                return LEFT_POSITION_CENTER;
            }

            @Override
            public Integer getTopPosition() {
                return TOP_POSITION_CENTER;
            }
        });
        slbAttribueA.ensureDebugId(viewDebugIdConstants.debugIdSlbAttribueA());
        slbAttribueA.setScrollPanelSuggestMultipleHeight(ActionsMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbAttribueA = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_ATTRIBUE_A, isAdmin);
        final HorizontalPanel panelslbAttribueA = new HorizontalPanel();
        panelslbAttribueA.setSpacing(3);
        panelslbAttribueA.add(slbAttribueA);
        panelslbAttribueA.add(aideslbAttribueA);
        ajouterAideComposant(aideslbAttribueA);

        slbAgence = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(new SuggestListBoxCompositeProperties<IdentifiantLibelleGwt>() {
            @Override
            public String getSelectSuggestRenderer(IdentifiantLibelleGwt row) {
                return row == null ? "" : row.getLibelle();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(IdentifiantLibelleGwt row) {
                return new String[] {row == null ? "" : row.getLibelle()};
            }

            @Override
            public String getSuggestListBoxMultiplePopupTitle() {
                return viewConstants.titrePopUpSelection();
            }

            @Override
            public Integer getLeftPosition() {
                return LEFT_POSITION_CENTER;
            }

            @Override
            public Integer getTopPosition() {
                return TOP_POSITION_CENTER;
            }
        });
        slbAgence.ensureDebugId(viewDebugIdConstants.debugIdSlbAgence());
        slbAgence.setScrollPanelSuggestMultipleHeight(ActionsMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbAgence = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_AGENCE, isAdmin);
        final HorizontalPanel panelslbAgence = new HorizontalPanel();
        panelslbAgence.setSpacing(3);
        panelslbAgence.add(slbAgence);
        panelslbAgence.add(aideslbAgence);
        ajouterAideComposant(aideslbAgence);

        cbRechercheEtEntreAgencesEtCommerciaux = new CheckBox();
        cbRechercheEtEntreAgencesEtCommerciaux.ensureDebugId(viewDebugIdConstants.debugIdCbRechercheEtEntreAgencesEtCommerciaux());

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.libeleCreation());
        fieldSetPanel.setHeight(ActionsMoteurRechercheViewImplConstants.HAUTEUR_BLOC);
        final FlexTable fpCreation = new FlexTable();
        fpCreation.setCellSpacing(5);
        final Label createur = creerLibelle(viewConstants.createur());
        final Label agence = creerLibelle(viewConstants.agence());
        final Label rechercheEt = creerLibelle(viewConstants.rechercheEt());
        final Label attribueA = creerLibelle(viewConstants.attribueA());

        fpCreation.setWidget(0, 0, createur);
        fpCreation.setWidget(0, 1, panelslbCreateur);
        fpCreation.setWidget(1, 0, agence);
        fpCreation.setWidget(1, 1, panelslbAgence);
        fpCreation.setWidget(2, 0, rechercheEt);
        fpCreation.setWidget(2, 1, cbRechercheEtEntreAgencesEtCommerciaux);
        fpCreation.setWidget(3, 0, attribueA);
        fpCreation.setWidget(3, 1, panelslbAttribueA);

        fpCreation.getColumnFormatter().setWidth(0, "25%");
        fieldSetPanel.add(fpCreation);
        fieldSetPanel.setHeight("200px");
        return fieldSetPanel;
    }

    private String construireLabelRessource(DimensionRessourceModel ressource, boolean ajouteDel) {
        final StringBuffer label = new StringBuffer();
        if (ressource != null) {
            label.append(ressource.getNom() + ESPACE + ressource.getPrenom());
            if (constantes.getIdEtatInactifRessource().equals(ressource.getEtat().getIdentifiant()) && ajouteDel) {
                label.insert(0, "<del>");
                label.append("<del/>");
            }
        }
        return label.toString();
    }

    private Widget construireBlocPlanification() {
        clBdateDebut = new DecoratedCalendrierDateBox();
        clBdateDebut.ensureDebugId(viewDebugIdConstants.debugIdClBdateDebut());
        aideclBdateDebut = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_DATE_DEBUT, isAdmin);
        final HorizontalPanel panelclBdateDebut = new HorizontalPanel();
        panelclBdateDebut.setSpacing(1);
        panelclBdateDebut.add(clBdateDebut);
        panelclBdateDebut.add(aideclBdateDebut);
        ajouterAideComposant(aideclBdateDebut);

        clBdateFin = new DecoratedCalendrierDateBox();
        clBdateFin.ensureDebugId(viewDebugIdConstants.debugIdClBdateFin());
        aideclBdateFin = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_DATE_FIN, isAdmin);
        final HorizontalPanel panelclBdateFin = new HorizontalPanel();
        panelclBdateFin.setSpacing(1);
        panelclBdateFin.add(clBdateFin);
        panelclBdateFin.add(aideclBdateFin);
        ajouterAideComposant(aideclBdateFin);

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.planification());
        fieldSetPanel.setHeight(ActionsMoteurRechercheViewImplConstants.HAUTEUR_BLOC);
        final FlexTable fpPlanification = new FlexTable();
        fpPlanification.setCellSpacing(1);
        final Label dateDebut = creerLibelle(viewConstants.dateDebut());
        final Label dateFin = creerLibelle(viewConstants.dateFin());

        fpPlanification.setWidget(0, 0, dateDebut);
        fpPlanification.setWidget(0, 1, panelclBdateDebut);
        fpPlanification.setWidget(1, 0, dateFin);
        fpPlanification.setWidget(1, 1, panelclBdateFin);
        panelclBdateDebut.setWidth("105%");
        panelclBdateFin.setWidth("105%");
        fieldSetPanel.add(fpPlanification);
        fieldSetPanel.setHeight("200px");
        return fieldSetPanel;
    }

    private Widget construireBlocInformation() {
        slbIdLibelleProperties = new SuggestListBoxCompositeProperties<IdentifiantLibelleGwt>() {
            @Override
            public String getSuggestListBoxMultiplePopupTitle() {
                return viewConstants.titrePopUpSelection();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(IdentifiantLibelleGwt row) {
                return new String[] {row == null ? "" : row.getLibelle()};
            }

            @Override
            public String getSelectSuggestRenderer(IdentifiantLibelleGwt row) {
                return row == null ? "" : row.getLibelle();
            }

            @Override
            public Integer getLeftPosition() {
                return LEFT_POSITION_CENTER;
            }

            @Override
            public Integer getTopPosition() {
                return TOP_POSITION_CENTER;
            }
        };
        slbStatut = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbStatut.ensureDebugId(viewDebugIdConstants.debugIdSlbStatut());
        slbStatut.setScrollPanelSuggestMultipleHeight(ActionsMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbStatut = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_STATUT, isAdmin);
        final HorizontalPanel panelslbStatut = new HorizontalPanel();
        panelslbStatut.setSpacing(5);
        panelslbStatut.add(slbStatut);
        panelslbStatut.add(aideslbStatut);
        ajouterAideComposant(aideslbStatut);

        slbNature = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbNature.ensureDebugId(viewDebugIdConstants.debugIdSlbNature());
        slbNature.setScrollPanelSuggestMultipleHeight(ActionsMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbNature = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_NATURE, isAdmin);
        final HorizontalPanel panelslbNature = new HorizontalPanel();
        panelslbNature.setSpacing(5);
        panelslbNature.add(slbNature);
        panelslbNature.add(aideslbNature);
        ajouterAideComposant(aideslbNature);

        slbResultat = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbResultat.ensureDebugId(viewDebugIdConstants.debugIdSlbResultat());
        slbResultat.setScrollPanelSuggestMultipleHeight(ActionsMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbResultat = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_RESULTAT, isAdmin);
        final HorizontalPanel panelslbResultat = new HorizontalPanel();
        panelslbResultat.setSpacing(5);
        panelslbResultat.add(slbResultat);
        panelslbResultat.add(aideslbResultat);
        ajouterAideComposant(aideslbResultat);

        slbPriorite = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbPriorite.ensureDebugId(viewDebugIdConstants.debugIdSlbPriorite());
        slbPriorite.setScrollPanelSuggestMultipleHeight(ActionsMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);

        aideslbPriorite = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_PRIORITE, isAdmin);
        final HorizontalPanel panelslbPriorite = new HorizontalPanel();
        panelslbPriorite.setSpacing(5);
        panelslbPriorite.add(slbPriorite);
        panelslbPriorite.add(aideslbPriorite);
        ajouterAideComposant(aideslbPriorite);

        slbNatureResultat = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbNatureResultat.ensureDebugId(viewDebugIdConstants.debugIdSlbNatureResultat());
        slbNatureResultat.setScrollPanelSuggestMultipleHeight(ActionsMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbNatureResultat = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_NATURE_RESULTAT, isAdmin);
        final HorizontalPanel panelslbNatureResultat = new HorizontalPanel();
        panelslbNatureResultat.setSpacing(5);
        panelslbNatureResultat.add(slbNatureResultat);
        panelslbNatureResultat.add(aideslbNatureResultat);
        ajouterAideComposant(aideslbNatureResultat);

        slbTypeCampagne = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbTypeCampagne.ensureDebugId(viewDebugIdConstants.debugIdSlbTypeCampagne());
        slbTypeCampagne.setScrollPanelSuggestMultipleHeight(ActionsMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbTypeCampagne = new AideComposant(AideComposantConstants.AIDE_ACTION_RECHERCHE_TYPE_CAMPAGNE, isAdmin);
        final HorizontalPanel panelslbTypeCampagne = new HorizontalPanel();
        panelslbTypeCampagne.setSpacing(5);
        panelslbTypeCampagne.add(slbTypeCampagne);
        panelslbTypeCampagne.add(aideslbTypeCampagne);
        ajouterAideComposant(aideslbTypeCampagne);

        cbReclamation = new CheckBox();
        cbReclamation.ensureDebugId(viewDebugIdConstants.debugIdCbReclamation());

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.information());

        final FlexTable fpInformation = new FlexTable();
        fpInformation.setCellSpacing(5);
        final Label statut = creerLibelle(viewConstants.statut());
        final Label libelleCampagne = creerLibelle(viewConstants.libelleCampagne());
        final Label nature = creerLibelle(viewConstants.nature());
        final Label natureResultat = creerLibelle(viewConstants.natureResultat());
        final Label resultat = creerLibelle(viewConstants.resultat());
        final Label priorite = creerLibelle(viewConstants.priorite());
        final Label reclamation = creerLibelle(viewConstants.reclamation());

        final FlexTable fpanel = new FlexTable();
        fpanel.setWidth(AppControllerConstants.POURCENT_100);
        fpanel.setCellSpacing(5);
        fpanel.setWidget(0, 0, statut);
        fpanel.setWidget(0, 1, panelslbStatut);
        fpanel.setWidget(0, 2, libelleCampagne);
        fpanel.setWidget(0, 3, panelslbTypeCampagne);
        fpanel.setWidget(1, 0, nature);
        fpanel.setWidget(1, 1, panelslbNature);
        fpanel.setWidget(1, 2, natureResultat);
        fpanel.setWidget(1, 3, panelslbNatureResultat);
        fpanel.setWidget(2, 0, resultat);
        fpanel.setWidget(2, 1, panelslbResultat);
        fpanel.setWidget(2, 2, priorite);
        fpanel.setWidget(2, 3, panelslbPriorite);
        fpanel.setWidget(3, 0, reclamation);
        fpanel.setWidget(3, 1, cbReclamation);
        slbStatut.setWidth("110px");
        slbNature.setWidth("110px");
        slbResultat.setWidth("110px");

        fpanel.getColumnFormatter().setWidth(0, "3%");
        fpanel.getColumnFormatter().setWidth(1, "25%");
        fpanel.getColumnFormatter().setWidth(2, "16%");
        fieldSetPanel.add(fpanel);
        return fieldSetPanel;
    }

    private Label creerLibelle(final String libelle) {
        final Label label = new Label(libelle, false);
        label.setHorizontalAlignment(HasAlignment.ALIGN_RIGHT);
        label.addStyleName(SquareResources.INSTANCE.css().libelleMoteurRecherche());
        return label;
    }

    private void createRemotePagingTable() {
        remotePagingTableAction =
            new RemotePagingTable<ActionRechercheModel, ActionCritereRechercheModel>(ActionsMoteurRechercheViewImplConstants.NB_ELEMENT_PAR_PAGE, true) {
                @Override
                public int setDefaultSortAsc() {
                    return RemotePagingSortGwt.REMOTE_PAGING_SORT_DESC;
                }

                public String setDefaultSortField() {
                    return constantes.getOrderByActionDateCreation();
                }

                @Override
                public Column[] setHeader() {
                    return new Column[] {new Column(viewConstants.headerPriorite(), constantes.getOrderByActionPriorite()),
                        new Column(viewConstants.headerDateAction(), constantes.getOrderByActionDateAction()), new Column(viewConstants.headerHeureAction()),
                        new Column(viewConstants.headerDateCreation(), constantes.getOrderByActionDateCreation()),
                        new Column(viewConstants.headerType(), constantes.getOrderByActionType()),
                        new Column(viewConstants.headerObjet(), constantes.getOrderByActionObjet()),
                        new Column(viewConstants.headerSousObjet(), constantes.getOrderByActionSsObjet()),
                        new Column(viewConstants.headerStatut(), constantes.getOrderByActionStatut()),
                        new Column(viewConstants.headerAgence(), constantes.getOrderByActionAttributionAgence()),
                        new Column(viewConstants.headerCommerciale(), constantes.getOrderByActionAttributionRessource()),
                        new Column(viewConstants.headerDateTerminee(), constantes.getOrderByActionDateTerminee()),
                    };
                }

                @Override
                public void setRow(int row, ActionRechercheModel action) {
                    int col = 0;
                    setWidget(row, col++, new Label(action.getPriorite() != null ? action.getPriorite() : "", false));
                    setWidget(row, col++, new Label(action.getDateAction(), false));
                    setWidget(row, col++, new Label(action.getHeureAction() != null ? action.getHeureAction() : "", false));
                    setWidget(row, col++, new Label(action.getDateCreation() != null ? action.getDateCreation() : "", false));
                    setWidget(row, col++, new Label(action.getType() != null ? action.getType() : ""));
                    setWidget(row, col++, new Label(action.getObjet() != null ? action.getObjet() : ""));
                    setWidget(row, col++, new Label(action.getSousObjet() != null ? action.getSousObjet() : ""));
                    setWidget(row, col++, new Label(action.getStatut() != null ? action.getStatut() : "", false));
                    setWidget(row, col++, new Label(action.getAgence() != null ? action.getAgence().getLibelle() : ""));
                    setWidget(row, col++, new Label(action.getCommercial() != null ? action.getCommercial().getNom() + ESPACE
                        + action.getCommercial().getPrenom() : ""));
                    setWidget(row, col++, new Label(action.getDateTerminee() != null ? action.getDateTerminee() : "", false));
                }

                @Override
                public void setDataProvider(RemotePagingCriteriasGwt<ActionCritereRechercheModel> params,
                    AsyncCallback<RemotePagingResultsGwt<ActionRechercheModel>> callback) {
                    remotePagingHandlerManager.fireEvent(new SetDataProviderEvent<ActionCritereRechercheModel, ActionRechercheModel>(params, callback));
                }

                @Override
                public void setCellClicked(ActionRechercheModel objet) {
                    remotePagingHandlerManager.fireEvent(new SetCellClickedEvent<ActionRechercheModel>(objet));

                }

            };

        remotePagingTableAction.ensureDebugId(viewDebugIdConstants.debugIdRemotePagingTableAction());
        remotePagingHandlerManager = new HandlerManager(remotePagingTableAction);
        remotePagingTableAction.setWidth(AppControllerConstants.POURCENT_100);
    }

    private void creerExportExcel() {
        panelExport = new HorizontalPanel();
        panelExport.setSpacing(2);
        labelExportExcel = new Label(appConstantes.labelExportExcel());
        labelExportExcel.setStylePrimaryName(SquareResources.INSTANCE.css().labelExportExcel());
        imageExportExcel = new Image(SquareResources.INSTANCE.iconeExcel());
        imageExportExcel.setTitle(appConstantes.titreExportExcel());
        imageExportExcel.setStylePrimaryName(SquareResources.INSTANCE.css().imageExportExcel());
        panelExport.add(labelExportExcel);
        panelExport.add(imageExportExcel);
        panelExport.setCellVerticalAlignment(labelExportExcel, HasAlignment.ALIGN_MIDDLE);
    }

    /** ajoute les composants d'aide. */
    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);

    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
    }

    @Override
    public HasClickHandlers getBtnRechercher() {
        return btnRechercher;
    }

    @Override
    public CheckBox getCbreclamation() {
        return cbReclamation;
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateDebut() {
        return clBdateDebut;
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateFin() {
        return clBdateFin;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbLibCampagne() {
        return slbTypeCampagne;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbNature() {
        return slbNature;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbNatureResultat() {
        return slbNatureResultat;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbPriorite() {
        return slbPriorite;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbResultat() {
        return slbResultat;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbStatut() {
        return slbStatut;
    }

    @Override
    public HandlerManager getRemotePagingHandlerManager() {
        return remotePagingHandlerManager;
    }

    @Override
    public RemotePagingTable<ActionRechercheModel, ActionCritereRechercheModel> getRemotePagingTable() {
        return remotePagingTableAction;
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(config);
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stop();
    }

    @Override
    public void afficherInfoPopup(PopupInfoConfiguration config) {
        new DecoratedInfoPopup(config).show();
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public ActionsMoteurRechercheViewImplConstants getViewConstants() {
        return viewConstants;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasSuggestListBoxHandler<DimensionRessourceModel> getSlbCommercial() {
        return slbAttribueA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DecoratedSuggestListBoxComposite<DimensionRessourceModel> getSlbCreateur() {
        return slbCreateur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DecoratedSuggestListBoxComposite<DimensionRessourceModel> getDecoratedSblCommercial() {
        return slbAttribueA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<List<DimensionRessourceModel>> getSlbCommercialValue() {
        return slbAttribueA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<List<DimensionRessourceModel>> getSlbCreateurValue() {
        return slbCreateur;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getLbStatutValue() {
        return slbStatut;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getLbLibCampagneValue() {
        return slbTypeCampagne;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getLbNatureValue() {
        return slbNature;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getLbPrioriteValue() {
        return slbPriorite;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getLbResultatValue() {
        return slbResultat;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getLbNatureResultatValue() {
        return slbNatureResultat;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbAgence() {
        return slbAgence;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getSlbAgenceValue() {
        return slbAgence;
    }

    @Override
    public HasClickHandlers getBtnEffaceRecherche() {
        return btnEffacerRecherche;
    }

    @Override
    public void effacerRecherche() {
        clBdateDebut.clean();
        clBdateFin.clean();
        slbObjet.clean();
        slbSousObjet.clean();
        slbType.clean();
        slbCreateur.clean();
        slbNature.clean();
        slbPriorite.clean();
        slbResultat.clean();
        slbTypeCampagne.clean();
        slbNatureResultat.clean();
        slbAgence.clean();
        slbAttribueA.clean();
        slbStatut.clean();
        cbReclamation.setValue(false);
        cbRechercheEtEntreAgencesEtCommerciaux.setValue(true);
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbObjet() {
        return slbObjet;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getLbObjetValue() {
        return slbObjet;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbSousObjet() {
        return slbSousObjet;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getLbSousObjetValue() {
        return slbSousObjet;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLbType() {
        return slbType;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getLbTypeValue() {
        return slbType;
    }

    @Override
    public void nettoyerObjet() {
        slbObjet.clean();
    }

    @Override
    public void nettoyerSousObjet() {
        slbSousObjet.clean();
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getSlbStatutValue() {
        return slbStatut;
    }

    @Override
    public HasKeyPressHandlers focusPanelKeyPressHandler() {
        return focusPanel;
    }

    @Override
    public HasClickHandlers getImageExportExcel() {
        return imageExportExcel;
    }

    @Override
    public HasClickHandlers getLabelExportExcel() {
        return labelExportExcel;
    }

    @Override
    public void afficherExportExcel() {
        conteneur.setWidget(4, 0, panelExport);
        conteneur.getCellFormatter().setHorizontalAlignment(4, 0, HasAlignment.ALIGN_RIGHT);
        conteneur.getFlexCellFormatter().setColSpan(4, 0, 3);
    }

    @Override
    public HasValue<Boolean> getCbRechercheEtEntreAgencesEtCommerciaux() {
        return cbRechercheEtEntreAgencesEtCommerciaux;
    }

    @Override
    public HasKeyPressHandlers getCbRechercheEtEntreAgencesEtCommerciauxKeyPress() {
        return cbRechercheEtEntreAgencesEtCommerciaux;
    }

    @Override
    public Widget asContextMenuWidget() {
        return contextMenu;
    }

    /**
     * Construction du menu contextuel.
     */
    private void contruireActionsContextPanel() {
        lbContextMenu = new Label(viewConstants.libelleMenuContextuel());
        lbContextMenu.ensureDebugId(viewDebugIdConstants.debugIdLbContextMenu());
        lbContextMenu.addStyleName(SquareResources.INSTANCE.css().titrePanelDroite());
        boutonTransfererActions = new DecoratedButton(viewConstants.libelleBoutonTransfererActions());
        boutonTransfererActions.ensureDebugId(viewDebugIdConstants.debugIdBtnTransfererActions());

        contextMenu = new VerticalPanel();
        contextMenu.add(lbContextMenu);
        contextMenu.add(boutonTransfererActions);

        contextMenu.addStyleName(SquareResources.INSTANCE.css().blocPanelDroite());
        contextMenu.setWidth(AppControllerConstants.POURCENT_100);
        contextMenu.setSpacing(10);
        contextMenu.setCellHorizontalAlignment(boutonTransfererActions, HasAlignment.ALIGN_CENTER);
    }

    @Override
    public HasClickHandlers getTransfererActionsHandler() {
        return boutonTransfererActions;
    }

    @Override
    public List<AideComposant> getListAideCompsant() {

        return listComposantAide;
    }

    @Override
    public List<HasDemandeAideEventHandler> getListeDemandeEventHandler() {
        return listDemandeAideEventHandler;
    }

    @Override
    public List<HasUpdateAideEventHandler> getListeUpdateEventHandler() {
        return listUpdateAideEventHandler;
    }

    @Override
    public void changeStyleRessourceBarre(Long type) {
        if (constantes.getIdEtatInactifRessource().equals(type)) {
        	slbAttribueA.addStyleName(SquareResources.INSTANCE.css().suggestListBoxRaye());
        }
        else {
        	slbAttribueA.removeStyleName(SquareResources.INSTANCE.css().suggestListBoxRaye());
        }
    }

    @Override
    public void changeStyleCreateurBarre(Long type) {
        if (constantes.getIdEtatInactifRessource().equals(type)) {
        	slbCreateur.addStyleName(SquareResources.INSTANCE.css().suggestListBoxRaye());
        }
        else {
        	slbCreateur.removeStyleName(SquareResources.INSTANCE.css().suggestListBoxRaye());
        }
    }
}

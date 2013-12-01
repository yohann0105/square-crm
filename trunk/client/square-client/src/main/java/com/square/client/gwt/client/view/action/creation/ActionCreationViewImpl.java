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
package com.square.client.gwt.client.view.action.creation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.presenter.action.ActionCreationPresenter.ActionCreationView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.DecoratedTextBoxFormat;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.popups.minimizable.IsMinimizable;
import com.square.composants.graphiques.lib.client.popups.minimizable.PopupMinimizable;

/**
 * Vue de la popup de création d'action.
 * @author cblanchard - SCUB
 */
public class ActionCreationViewImpl extends Popup implements ActionCreationView {

    /** View constants. */
    private static ActionCreationViewImplConstants viewConstants = (ActionCreationViewImplConstants) GWT.create(ActionCreationViewImplConstants.class);

    /*
     * (non-Javadoc)
     * @see com.square.client.gwt.client.presenter.action.ActionCreationPresenter.ActionCreationView#getListAideCompsant()
     */

    /** View constants. */
    private static ActionCreationViewImplDebugIdConstants viewDebugIdConstants =
        (ActionCreationViewImplDebugIdConstants) GWT.create(ActionCreationViewImplDebugIdConstants.class);

    /** Icone manager. */
    private IconeErreurChampManager iconeErreurChampManager;

    /** Le conteneur principale. */
    private VerticalPanel conteneurGlobal;

    /** La date d'action. */
    private DecoratedCalendrierDateBox cdbDateAction;

    /** aide pour TextBox pour La date d'action. */
    private AideComposant aidecdbDateAction;

    /** TextBox pour les heures/minutes. */
    private DecoratedTextBoxFormat tbfHeureAction;

    /** aide pour TextBox pour les heures/minutes. */
    private AideComposant aideTbfHeureAction;

    /** Label de la personne affectée. */
    private Label lPersonneAffecte;

    /** SuggestLisBox pour la nature du contact. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbNature;

    /** aide pour la nature du contact. */
    private AideComposant aideSlbNature;

    /** SuggestListBox pour le type d'action. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbType;

    /** aide pour pour le type d'action. */
    private AideComposant aideSlbType;

    /** SuggestListBox de l'objet. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbObjet;

    /** aide pour pour SuggestListBox de l'objet. */
    private AideComposant aideSlbObjet;

    /** SuggesListBox du sous objet. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbSousObjet;

    /** aide pour pour SuggesListBox du sous objet. */
    private AideComposant aideSlbSousObjet;

    /** SuggestListBox de la priorite. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbPriorite;

    /** aide pour pour SuggestListBox de la priorite. */
    private AideComposant aideSlbPriorite;

    /** CheckBox reclamation. */
    private CheckBox cbReclamation;

    /** CheckBox fermant l'action précédente. */
    private CheckBox cbFermerPrecedente;

    /** SuggestListBox des campagnes. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbCampagne;

    /** aide pour pour SuggestListBox des campagnes. */
    private AideComposant aideSlbCampagne;

    /** SuggestListBox qui indique l'attribution de l'action à une agence. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbAttributionAgence;

    /** aide pour pour SuggestListBox des agences. */
    private AideComposant aideSlbAttributionAgence;

    /** SuggestListBox qui indique l'attribution de l'action à une ressource de l'agence selectionnée. */
    private DecoratedSuggestListBoxSingle<DimensionRessourceModel> slbAttributionRessourceAgence;

    /** aide pour pour SuggestListBox des ressource. */
    private AideComposant aideSlbAttributionRessourceAgence;

    /** CheckBox du rappel notification. */
    private CheckBox cbRappelNotification;

    private AideComposant aidecbRappelNotification;

    /** Liste de notification. */
    private ListBox lbNotification;

    /** CheckBox du rappel par mail. */
    private CheckBox cbRappelMail;

    private AideComposant aidecbRappelMail;

    /** Bouton annuler. */
    private DecoratedButton btnAnnuler;

    /** Bouton Creer. */
    private DecoratedButton btnCreer;

    private DecoratedButton btnReduire;

    private FocusPanel focusPopupPanel;

    private PopupMinimizable minimizablePopup;

    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    /** listes des evenements relatifs à l'aide en ligne . */

    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    /** boolean pour Mode admin */
    private boolean isAdmin;

    /** SuggestListBox qui indique la durée d'une action. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbDuree;

    /** Aide pour la SuggestListBox des durées. */
    private AideComposant aideSlbDuree;

    /** CheckBox pour ajouter l'action dans l'agenda. */
    private CheckBox cbAjouterAgenda;

    /** Aide pour la CheckBox d'ajout de l'action à l'agenda. */
    private AideComposant aideCbAjouterAgenda;

    /**
     * Constructeur.
     */
    public ActionCreationViewImpl(boolean isAdmin) {
        super(viewConstants.popupTitle(), false, false, true);
        this.isAdmin = isAdmin;
        iconeErreurChampManager = new IconeErreurChampManager();
        conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setSpacing(10);
        AideComposant aideView = new AideComposant(AideComposantConstants.AIDE_ACTION_CREATION_CONTENEUR_GLOBAL, isAdmin);
        ajouterAideComposant(aideView);
        conteneurGlobal.add(aideView);
        conteneurGlobal.setCellHorizontalAlignment(aideView, HasAlignment.ALIGN_RIGHT);
        construireBlocPlanification();
        construireBlocAffectation();
        construireBlocAction();
        construireBlocCampagne();
        construireBlocNotification();
        conteneurGlobal.setWidth(ActionCreationViewImplConstants.LARGEUR_POPUP);

        focusPopupPanel = new FocusPanel();
        focusPopupPanel.setWidth(AppControllerConstants.POURCENT_100);
        focusPopupPanel.add(conteneurGlobal);

        final HorizontalPanel conteneurBoutons = construireBlocBouton();
        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.add(focusPopupPanel);
        conteneur.add(conteneurBoutons);
        conteneur.setCellHorizontalAlignment(conteneurBoutons, HasHorizontalAlignment.ALIGN_CENTER);

        // on en fait une popup minimisable
        minimizablePopup = new PopupMinimizable(this, viewConstants.popupTitle(), btnReduire);

        this.setWidget(conteneur, 0);
        this.addStyleName(SquareResources.INSTANCE.css().popupCreationAction());
    }

    /** Construction du bloc de planification. */
    private void construireBlocPlanification() {
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

        final Label labelAFaire = new Label(viewConstants.labelAFaire(), false);
        cdbDateAction = new DecoratedCalendrierDateBox(true);
        cdbDateAction.ensureDebugId(viewDebugIdConstants.debugIdCdbDateAction());
        aidecdbDateAction = new AideComposant(AideComposantConstants.AIDE_ACTION_CREATION_DATE, isAdmin);
        // listComposantAide.add(aidecdbDateAction);
        ajouterAideComposant(aidecdbDateAction);
        final Label labelDebut = new Label(viewConstants.labelDebut(), false);
        tbfHeureAction = new DecoratedTextBoxFormat("NN:NN");
        tbfHeureAction.ensureDebugId(viewDebugIdConstants.debugIdTbfHeureAction());
        tbfHeureAction.addStyleName(SquareResources.INSTANCE.css().heureDate());
        aideTbfHeureAction = new AideComposant(AideComposantConstants.AIDE_ACTION_CREATION_HEURE, isAdmin);
        HorizontalPanel panelHeure = new HorizontalPanel();
        panelHeure.setSpacing(5);
        panelHeure.add(tbfHeureAction);
        panelHeure.add(aideTbfHeureAction);
        panelHeure.setCellVerticalAlignment(aideTbfHeureAction, HasAlignment.ALIGN_MIDDLE);
        ajouterAideComposant(aideTbfHeureAction);
        final Label labelDuree = new Label(viewConstants.labelDuree(), false);
        slbDuree = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbDuree.ensureDebugId(viewDebugIdConstants.debugIdSlbDuree());
        slbDuree.setWidth(ActionCreationViewImplConstants.LARGEUR_SLB_DUREE);
        aideSlbDuree = new AideComposant(100014L, isAdmin);
        ajouterAideComposant(aideSlbDuree);

        // Constuction de la priorite
        final Label lpriorite = new Label(viewConstants.priorite());
        slbPriorite = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbPriorite.ensureDebugId(viewDebugIdConstants.debugIdSlbPriorite());
        aideSlbPriorite = new AideComposant(AideComposantConstants.AIDE_ACTION_CREATION_PRIORITE, isAdmin);
        HorizontalPanel panelPriorite = new HorizontalPanel();
        panelPriorite.setSpacing(5);
        panelPriorite.add(slbPriorite);
        panelPriorite.add(aideSlbPriorite);
        ajouterAideComposant(aideSlbPriorite);

        final CaptionPanel captionPanel = new CaptionPanel(viewConstants.libellePanelPlanification());
        final FlexTable flexTable = new FlexTable();
        flexTable.setWidth(AppControllerConstants.POURCENT_100);
        flexTable.setCellSpacing(3);
        flexTable.setWidget(0, 0, labelAFaire);
        flexTable.setWidget(0, 1, construireBlocIcone(cdbDateAction, "ActionCreationDto.dateAction", aidecdbDateAction));
        flexTable.setWidget(0, 2, labelDebut);
        flexTable.setWidget(0, 3, panelHeure);
        flexTable.setWidget(0, 4, labelDuree);
        flexTable.setWidget(0, 5, construireBlocIcone(slbDuree, "ActionCreationDto.idDuree", aideSlbDuree));
        flexTable.setWidget(1, 0, lpriorite);
        flexTable.setWidget(1, 1, panelPriorite);
        flexTable.getFlexCellFormatter().setColSpan(1, 1, 5);
        flexTable.getRowFormatter().setVerticalAlign(0, HasAlignment.ALIGN_MIDDLE);
        flexTable.getRowFormatter().setVerticalAlign(1, HasAlignment.ALIGN_MIDDLE);
        flexTable.getColumnFormatter().setWidth(0, ActionCreationViewImplConstants.LARGEUR_COL_LIBELLE);

        captionPanel.add(flexTable);
        conteneurGlobal.add(captionPanel);
    }

    /** Construction du bloc affectation. */
    private void construireBlocAffectation() {
        final Label lPersonne = new Label(viewConstants.libellePersonne(), false);
        final Label lAttributionAgence = new Label(viewConstants.labelAttributionAgence(), false);
        final Label lAttributionRessourceAgence = new Label(viewConstants.labelAttributionRessourceAgence(), false);
        lPersonneAffecte = new Label();
        slbAttributionAgence = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
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
        });
        aideSlbAttributionAgence = new AideComposant(AideComposantConstants.AIDE_ACTION_CREATION_ATTRIBUTION_AGENCE, isAdmin);
        ajouterAideComposant(aideSlbAttributionAgence);

        slbAttributionAgence.ensureDebugId(viewDebugIdConstants.debugIdSlbAttributionAgence());
        slbAttributionRessourceAgence =
            new DecoratedSuggestListBoxSingle<DimensionRessourceModel>(new SuggestListBoxSingleProperties<DimensionRessourceModel>() {
                @Override
                public String getSelectSuggestRenderer(DimensionRessourceModel row) {
                    return row == null ? "" : row.getNom() + " " + row.getPrenom();
                }

                @Override
                public String[] getResultColumnsRenderer() {
                    return new String[] {};
                }

                @Override
                public String[] getResultRowsRenderer(DimensionRessourceModel row) {
                    return new String[] {row == null ? "" : row.getNom(), row.getPrenom()};
                }

            });
        slbAttributionRessourceAgence.ensureDebugId(viewDebugIdConstants.debugIdSlbAttributionRessourceAgence());
        aideSlbAttributionRessourceAgence = new AideComposant(AideComposantConstants.AIDE_ACTION_CREATION_ATTRIBUTION_RESSOURCE, isAdmin);
        ajouterAideComposant(aideSlbAttributionRessourceAgence);

        final CaptionPanel captionPanel = new CaptionPanel(viewConstants.libellePanelAffectation());
        final FlexTable ftAffectation = new FlexTable();
        ftAffectation.setWidth(AppControllerConstants.POURCENT_100);
        ftAffectation.setCellSpacing(3);
        ftAffectation.setWidget(0, 0, lAttributionAgence);
        ftAffectation.setWidget(0, 1, construireBlocIcone(slbAttributionAgence, "ActionCreationDto.idAgence", aideSlbAttributionAgence));
        ftAffectation.setWidget(1, 0, lAttributionRessourceAgence);
        ftAffectation.setWidget(1, 1, construireBlocIcone(slbAttributionRessourceAgence, "ActionCreationDto.idCommercial", aideSlbAttributionRessourceAgence));
        ftAffectation.setWidget(2, 0, lPersonne);
        ftAffectation.setWidget(2, 1, lPersonneAffecte);
        ftAffectation.getColumnFormatter().setWidth(0, ActionCreationViewImplConstants.LARGEUR_COL_LIBELLE);
        captionPanel.add(ftAffectation);
        conteneurGlobal.add(captionPanel);
    }

    /** Construction du bloc Action. */
    private void construireBlocAction() {
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
        slbNature = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbNature.ensureDebugId(viewDebugIdConstants.debugIdSlbNature());
        aideSlbNature = new AideComposant(AideComposantConstants.AIDE_ACTION_CREATION_NATURE, isAdmin);
        ajouterAideComposant(aideSlbNature);
        slbObjet = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbObjet.ensureDebugId(viewDebugIdConstants.debugIdSlbObjet());
        aideSlbObjet = new AideComposant(AideComposantConstants.AIDE_ACTION_CREATION_OBJET, isAdmin);
        ajouterAideComposant(aideSlbObjet);
        slbSousObjet = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbSousObjet.ensureDebugId(viewDebugIdConstants.debugIdSlbSousObjet());
        aideSlbSousObjet = new AideComposant(AideComposantConstants.AIDE_ACTION_CREATION_SOUS_OBJET, isAdmin);
        HorizontalPanel panelSlbSousObjet = new HorizontalPanel();
        panelSlbSousObjet.setSpacing(5);
        panelSlbSousObjet.add(slbSousObjet);
        panelSlbSousObjet.add(aideSlbSousObjet);
        ajouterAideComposant(aideSlbSousObjet);
        slbType = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbType.ensureDebugId(viewDebugIdConstants.debugIdSlbType());
        aideSlbType = new AideComposant(AideComposantConstants.AIDE_ACTION_CREATION_TYPE, isAdmin);
        ajouterAideComposant(aideSlbType);
        cbReclamation = new CheckBox(viewConstants.reclamation());
        cbReclamation.ensureDebugId(viewDebugIdConstants.debugIdCbReclamation());
        cbFermerPrecedente = new CheckBox(viewConstants.fermerActionPrecedente());
        cbFermerPrecedente.ensureDebugId(viewDebugIdConstants.debugIdCbFermerPrecedente());
        cbFermerPrecedente.setVisible(false);
        cbFermerPrecedente.setValue(false);
        final Label lnature = new Label(viewConstants.nature(), false);
        final Label ltype = new Label(viewConstants.type(), false);
        final Label lObjet = new Label(viewConstants.objet(), false);
        final Label lSousObjet = new Label(viewConstants.sousObjet(), false);

        final CaptionPanel captionPanel = new CaptionPanel(viewConstants.libellePanelAction());
        final FlexTable flexTable = new FlexTable();
        flexTable.setWidth(AppControllerConstants.POURCENT_100);
        flexTable.setCellSpacing(3);
        flexTable.setWidget(0, 0, lnature);
        flexTable.setWidget(0, 1, construireBlocIcone(slbNature, "ActionCreationDto.idNatureAction", aideSlbNature));
        flexTable.setWidget(1, 0, ltype);
        flexTable.setWidget(1, 1, construireBlocIcone(slbType, "ActionCreationDto.idType", aideSlbType));
        flexTable.setWidget(2, 0, lObjet);
        flexTable.setWidget(2, 1, construireBlocIcone(slbObjet, "ActionCreationDto.idObjet", aideSlbObjet));
        flexTable.setWidget(3, 0, lSousObjet);
        flexTable.setWidget(3, 1, panelSlbSousObjet);
        flexTable.setWidget(4, 1, cbReclamation);
        flexTable.setWidget(5, 1, cbFermerPrecedente);
        flexTable.getColumnFormatter().setWidth(0, ActionCreationViewImplConstants.LARGEUR_COL_LIBELLE);

        captionPanel.add(flexTable);
        conteneurGlobal.add(captionPanel);
    }

    /** Construction du bloc campagne. */
    private void construireBlocCampagne() {
        final Label lcampagne = new Label(viewConstants.campagne(), false);
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
        slbCampagne = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbCampagne.ensureDebugId(viewDebugIdConstants.debugIdSlbCampagne());
        aideSlbCampagne = new AideComposant(AideComposantConstants.AIDE_ACTION_CREATION_CAMPAGNE, isAdmin);
        HorizontalPanel panelSlbCampagne = new HorizontalPanel();
        panelSlbCampagne.setSpacing(5);
        panelSlbCampagne.add(slbCampagne);
        panelSlbCampagne.add(aideSlbCampagne);
        ajouterAideComposant(aideSlbCampagne);
        final CaptionPanel captionPanel = new CaptionPanel(viewConstants.libellePanelCampagne());
        final FlexTable flexTable = new FlexTable();
        flexTable.setWidth(AppControllerConstants.POURCENT_100);
        flexTable.setCellSpacing(3);
        flexTable.setWidget(0, 0, lcampagne);
        flexTable.setWidget(0, 1, panelSlbCampagne);
        flexTable.getColumnFormatter().setWidth(0, ActionCreationViewImplConstants.LARGEUR_COL_LIBELLE);

        captionPanel.add(flexTable);
        conteneurGlobal.add(captionPanel);
    }

    /** Construction du bloc de notification. */
    private void construireBlocNotification() {
        // Constuction de la ligne de rappel
        cbRappelNotification = new CheckBox();
        aidecbRappelNotification =new AideComposant(AideComposantConstants.AIDE_ACTION_CREATION_RAPPEL_NOTIFICATION, isAdmin);
        ajouterAideComposant(aidecbRappelNotification);

        cbRappelNotification.ensureDebugId(viewDebugIdConstants.debugIdCbRappelNotification());
        final Label lrappeler = new Label(viewConstants.rappeler());
        lbNotification = new ListBox();
        lbNotification.ensureDebugId(viewDebugIdConstants.debugIdLbNotification());
        final Label lavant = new Label(viewConstants.avant());
        final HorizontalPanel horizontalPanelLigne1 = new HorizontalPanel();
        horizontalPanelLigne1.add(aidecbRappelNotification);
        horizontalPanelLigne1.add(cbRappelNotification);
        horizontalPanelLigne1.add(lrappeler);
        horizontalPanelLigne1.add(lbNotification);
        horizontalPanelLigne1.add(lavant);
        horizontalPanelLigne1.setCellVerticalAlignment(lrappeler, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelLigne1.setCellVerticalAlignment(lavant, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelLigne1.setCellVerticalAlignment(aidecbRappelNotification, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelLigne1.setSpacing(2);
        // Construction de la ligne rappel par mail
        final HorizontalPanel horizontalPanelLigne2 = new HorizontalPanel();
        cbRappelMail = new CheckBox();
        aidecbRappelMail = new AideComposant(AideComposantConstants.AIDE_ACTION_CREATION_RAPPEL_MAIL, isAdmin);
        ajouterAideComposant(aidecbRappelMail);
        horizontalPanelLigne2.add(aidecbRappelMail);
        horizontalPanelLigne2.add(cbRappelMail);
        cbRappelMail.ensureDebugId(viewDebugIdConstants.debugIdCbRappelMail());
        final Label libelleRappelMail = new Label(viewConstants.rappelMail());
        horizontalPanelLigne2.add(libelleRappelMail);
        horizontalPanelLigne2.setCellVerticalAlignment(libelleRappelMail, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelLigne2.setCellVerticalAlignment(aidecbRappelMail, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelLigne2.setSpacing(2);

        // Construction de la ligne d'ajout à l'agenda
        final HorizontalPanel horizontalPanelLigne3 = new HorizontalPanel();
        cbAjouterAgenda = new CheckBox();
        cbAjouterAgenda.setEnabled(false);
        aideCbAjouterAgenda = new AideComposant(100014L, isAdmin);
        ajouterAideComposant(aideCbAjouterAgenda);
        horizontalPanelLigne3.add(aideCbAjouterAgenda);
        horizontalPanelLigne3.add(cbAjouterAgenda);
        cbAjouterAgenda.ensureDebugId(viewDebugIdConstants.debugIdCbAjouterAgenda());
        final Label libelleAjouterAgenda = new Label(viewConstants.ajouterAgenda());
        horizontalPanelLigne3.add(libelleAjouterAgenda);
        horizontalPanelLigne3.setCellVerticalAlignment(libelleRappelMail, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelLigne3.setCellVerticalAlignment(aidecbRappelMail, HasAlignment.ALIGN_MIDDLE);
        horizontalPanelLigne3.setSpacing(2);

        final CaptionPanel captionPanel = new CaptionPanel(viewConstants.libellePanelNotification());
        final VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(horizontalPanelLigne1);
        verticalPanel.add(horizontalPanelLigne2);
        verticalPanel.add(horizontalPanelLigne3);

        captionPanel.add(verticalPanel);
        conteneurGlobal.add(captionPanel);
    }

    /** Construction du bloc de boutons. */
    private HorizontalPanel construireBlocBouton() {
        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        btnCreer = new DecoratedButton(viewConstants.creer());
        btnCreer.ensureDebugId(viewDebugIdConstants.debugIdBtnCreer());
        btnReduire = new DecoratedButton(viewConstants.reduire());
        btnReduire.ensureDebugId(viewDebugIdConstants.debugIdBtnReduire());
        btnAnnuler = new DecoratedButton(viewConstants.annuler());
        btnAnnuler.ensureDebugId(viewDebugIdConstants.debugIdBtnAnnuler());
        horizontalPanel.add(btnCreer);
        horizontalPanel.add(btnReduire);
        horizontalPanel.add(btnAnnuler);
        horizontalPanel.setSpacing(5);
        return horizontalPanel;
    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp, AideComposant aideComposant) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        final HorizontalPanel panelIcone = new HorizontalPanel();
        panelIcone.add(icone);
        panelIcone.add(aideComposant);
        panel.add(panelIcone);
        panel.setCellVerticalAlignment(panelIcone, HasVerticalAlignment.ALIGN_MIDDLE);
        return panel;
    }

    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);
    }

    @Override
    public void clearPopup() {
        cbRappelMail.setValue(false);
        cbRappelNotification.setValue(false);
        cbReclamation.setValue(false);
        cbFermerPrecedente.setValue(false);
        lbNotification.clear();
        cdbDateAction.clean();
        slbCampagne.clean();
        slbNature.clean();
        slbObjet.clean();
        slbPriorite.clean();
        slbSousObjet.clean();
        slbType.clean();
        slbDuree.clean();
        tbfHeureAction.setValue("");
        cbAjouterAgenda.setValue(false);
        cbAjouterAgenda.setEnabled(false);
    }

    @Override
    public void hidePopup() {
        this.hide();
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stop();
    }

    @Override
    public Widget asWidget() {
        return null;
    }

    @Override
    public void showPopup() {
        this.show();
        slbObjet.setEnabled(false);
        slbSousObjet.setEnabled(false);
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    /**
     * Renvoi la valeur de viewConstants.
     * @return viewConstants
     */
    @Override
    public ActionCreationViewImplConstants getViewConstants() {
        return viewConstants;
    }

    /**
     * Renvoi la valeur de cdbDateAction.
     * @return cdbDateAction
     */
    @Override
    public DecoratedCalendrierDateBox getCdbDateAction() {
        return cdbDateAction;
    }

    /**
     * Renvoi la valeur de tbfHeureAction.
     * @return tbfHeureAction
     */
    @Override
    public HasValue<String> getTbfHeureAction() {
        return tbfHeureAction;
    }

    /**
     * Renvoi la valeur de btnAnnuler.
     * @return btnAnnuler
     */
    @Override
    public DecoratedButton getBtnAnnuler() {
        return btnAnnuler;
    }

    /**
     * Renvoi la valeur de btnCreer.
     * @return btnCreer
     */
    @Override
    public DecoratedButton getBtnCreer() {
        return btnCreer;
    }

    /**
     * Renvoi la valeur de iconeErreurChampManager.
     * @return iconeErreurChampManager
     */
    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    /**
     * Renvoi la valeur de lPersonneAffecte.
     * @return lPersonneAffecte
     */
    @Override
    public Label getlPersonneAffecte() {
        return lPersonneAffecte;
    }

    /**
     * Renvoi la valeur de slbNature.
     * @return slbNature
     */
    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbNature() {
        return slbNature;
    }

    /**
     * Renvoi la valeur de slbType.
     * @return slbType
     */
    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbType() {
        return slbType;
    }

    /**
     * Renvoi la valeur de slbObjet.
     * @return slbObjet
     */
    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbObjet() {
        return slbObjet;
    }

    /**
     * Renvoi la valeur de slbSousObjet.
     * @return slbSousObjet
     */
    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbSousObjet() {
        return slbSousObjet;
    }

    /**
     * Renvoi la valeur de slbPriorite.
     * @return slbPriorite
     */
    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbPriorite() {
        return slbPriorite;
    }

    /**
     * Renvoi la valeur de cbReclamation.
     * @return cbReclamation
     */
    @Override
    public CheckBox getCbReclamation() {
        return cbReclamation;
    }

    /**
     * Renvoi la valeur de cbFermerPrecedente.
     * @return cbFermerPrecedente
     */
    @Override
    public CheckBox getCbFermerPrecedente() {
        return cbFermerPrecedente;
    }

    /**
     * Renvoi la valeur de slbCampagne.
     * @return slbCampagne
     */
    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbCampagne() {
        return slbCampagne;
    }

    /**
     * Renvoi la valeur de cbRappelNotification.
     * @return cbRappelNotification
     */
    @Override
    public CheckBox getCbRappelNotification() {
        return cbRappelNotification;
    }

    /**
     * Renvoi la valeur de lbNotification.
     * @return lbNotification
     */
    @Override
    public ListBox getLbNotification() {
        return lbNotification;
    }

    /**
     * Renvoi la valeur de cbRappelMail.
     * @return cbRappelMail
     */
    @Override
    public CheckBox getCbRappelMail() {
        return cbRappelMail;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<IdentifiantLibelleGwt> getAgence() {
        return slbAttributionAgence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<DimensionRessourceModel> getRessourceAgence() {
        return slbAttributionRessourceAgence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestAgence() {
        return slbAttributionAgence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasSuggestListBoxHandler<DimensionRessourceModel> getSuggestRessourceAgence() {
        return slbAttributionRessourceAgence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValueChangeHandlers<IdentifiantLibelleGwt> getValueChangedHandlerAgence() {
        return slbAttributionAgence;
    }

    @Override
    public DecoratedSuggestListBoxSingle<DimensionRessourceModel> getDecoratedSlbsRessourceAgence() {
        return slbAttributionRessourceAgence;
    }

    @Override
    public void activerObjet(Boolean actif) {
        slbObjet.setEnabled(actif);
    }

    @Override
    public void activerSousObjet(Boolean actif) {
        slbSousObjet.setEnabled(actif);
    }

    @Override
    public HasAllKeyHandlers getAllKeyHandlersFocusPanel() {
        return focusPopupPanel;
    }

    @Override
    public void activerBtnCreer(boolean activer) {
        btnCreer.setEnabled(activer);
    }

    @Override
    public void activerBoutonReduire(boolean enabled) {
        btnReduire.setEnabled(enabled);
    }

    @Override
    public IsMinimizable getMinimizablePopup() {
        return minimizablePopup;
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
    public HasValue<IdentifiantLibelleGwt> getSlbDuree() {
        return slbDuree;
    }

    @Override
    public HasValue<Boolean> getCbAjouterAgenda() {
        return cbAjouterAgenda;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestHandlersDuree() {
        return slbDuree;
    }

    @Override
    public HasValueChangeHandlers<Date> getValueChangeHandlerDateAction() {
        return cdbDateAction;
    }

    @Override
    public HasKeyUpHandlers getKeyUpHandlerDateAction() {
        return cdbDateAction;
    }

    @Override
    public HasValueChangeHandlers<IdentifiantLibelleGwt> getValueChangeHandlerDuree() {
        return slbDuree;
    }

    @Override
    public HasValueChangeHandlers<String> getValueChangeHandlerHeureAction() {
        return tbfHeureAction;
    }

    @Override
    public void activerAjoutAgenda(boolean enabled) {
        cbAjouterAgenda.setEnabled(enabled);
    }
}

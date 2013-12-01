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
package com.square.client.gwt.client.view.personne.physique.gestion;

import static com.square.client.gwt.client.view.personne.physique.gestion.GestionPersonnePhysiqueViewImplConstants.LARGEUR_COL_CHAMP_1;
import static com.square.client.gwt.client.view.personne.physique.gestion.GestionPersonnePhysiqueViewImplConstants.LARGEUR_COL_CHAMP_3;
import static com.square.client.gwt.client.view.personne.physique.gestion.GestionPersonnePhysiqueViewImplConstants.LARGEUR_COL_LIBELLE_0;
import static com.square.client.gwt.client.view.personne.physique.gestion.GestionPersonnePhysiqueViewImplConstants.LARGEUR_COL_LIBELLE_2;
import static com.square.client.gwt.client.view.personne.physique.gestion.GestionPersonnePhysiqueViewImplConstants.LIGNE_NOM_JEUNE_FILLE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.ExplorableComposite;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasBeforeSelectionHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.composant.bloc.BlocArrondiBleu;
import com.square.client.gwt.client.composant.onglet.scroll.SimpleTabPanelScroll;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.CaisseSimpleModel;
import com.square.client.gwt.client.model.IdentifiantLibelleBooleanModel;
import com.square.client.gwt.client.model.IdentifiantLibelleOuiNonModel;
import com.square.client.gwt.client.model.PersonnePermissionOngletModel;
import com.square.client.gwt.client.presenter.personne.physique.GestionPersonnePhysiquePresenter.GestionPersonnePhysiqueView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.BlocSyntheseDepliant;
import com.square.composants.graphiques.lib.client.composants.ChampSynthese;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.DecoratedTextBoxFormat;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Vue Gestion d'une personne physique.
 * @author Goumard Stephane (Scub). - SCUB
 */
public class GestionPersonnePhysiqueViewImpl extends ExplorableComposite implements GestionPersonnePhysiqueView {

    /** Label sur le resume d'une personne. */
    private Label lbRnumeroClient;

    private BlocSyntheseDepliant blRpersonne;

    /** Composant pour affichage gestion personne. */
    private DecoratedTextBoxFormat tbGnomJeuneFille;

    private AideComposant aidetbGnomJeuneFille;

    private DecoratedCalendrierDateBox cbGdateNaissance;

    private AideComposant aidecbGdateNaissance;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> libGcivilite;

    private AideComposant aidelibGcivilite;

    private DecoratedTextBoxFormat tbGnom;

    private AideComposant aidetbGnom;

    private DecoratedTextBoxFormat tbGprenom;

    private AideComposant aidetbGprenom;

    private Label lbGstatut;

    private Label lbGnumeroRo;

    private Label libDecede;

    private Label lbSituationFamiliale;

    private Label lbAge;

    private Label lCreateur;

    private Label lDateCreation;

    private DecoratedSuggestListBoxSingle<CaisseSimpleModel> libGcaisse;

    private AideComposant aidelibGcaisse;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> libGregime;

    private AideComposant aidelibGregime;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel> libGprofession;

    private AideComposant aidelibGprofession;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> libGsegment;

    private AideComposant aidelibGsegment;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> libGreseauVente;

    private AideComposant aidelibGreseauVente;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> libGcsp;

    private AideComposant aidelibGcsp;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> libGsituationMarie;

    private AideComposant aidelibGsituationMarie;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbsLibSitiationEnfants;

    private AideComposant aideslbsLibSitiationEnfants;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> libGnature;

    private AideComposant aidelibGnature;

    private DecoratedButton btGenregistrer;

    private DecoratedButton btGAnnuler;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbAgence;

    private AideComposant aideslbAgence;

    private VerticalPanel contextMenu;

    private DecoratedButton btActionContextAddAction;

    private DecoratedButton btnOpportuniteContextAddOpportunite;

    private Label lbContextMenu;

    private ChampSynthese csDateNaissance;

    private ChampSynthese csAge;

    /** TabLayoutPanel. */
    private SimpleTabPanelScroll tabPanel;

    private Map<Integer, String> gestionOnglets;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    /** Constantes de la vue. */
    private GestionPersonnePhysiqueViewImplConstants constants;

    /** Constantes debugId de la vue. */
    private GestionPersonnePhysiqueViewImplDebugIdConstants constantsDebugId;

    private FlexTable flexTableInformations;

    /**
     * liste de composants d'aide de la vue.
     */
    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    /**
     * liste des events handlers des composants d'aides.
     */

    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    /**
     * mode de connexion.
     */
    private boolean isAdmin;

    /**
     * Constructeur.
     * @param permissionsOnglets les permissions sur les onglets.
     * @param isAdmin flag
     */
    public GestionPersonnePhysiqueViewImpl(PersonnePermissionOngletModel permissionsOnglets, boolean isAdmin) {
        this.isAdmin = isAdmin;
        iconeErreurChampManager = new IconeErreurChampManager();
        constants = (GestionPersonnePhysiqueViewImplConstants) GWT.create(GestionPersonnePhysiqueViewImplConstants.class);
        constantsDebugId = (GestionPersonnePhysiqueViewImplDebugIdConstants) GWT.create(GestionPersonnePhysiqueViewImplDebugIdConstants.class);
        final VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSize("950px", AppControllerConstants.POURCENT_100);

        // Initialisation des champs du résumé
        lbRnumeroClient = new Label();
        lbRnumeroClient.ensureDebugId(constantsDebugId.debugIdLbRnumeroClient());
        lbSituationFamiliale = new Label();
        lbSituationFamiliale.ensureDebugId(constantsDebugId.debugIdLbSituationFamiliale());
        lbAge = new Label();
        lbAge.addStyleName(SquareResources.INSTANCE.css().tripletPersonne());
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
        libGcivilite = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        libGcivilite.ensureDebugId(constantsDebugId.debugIdLibGcivilite());
        libGcivilite.setWidth("166px");
        aidelibGcivilite = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_GESTION_CIVILITE, isAdmin);
        ajouterAideComposant(aidelibGcivilite);
        tbGnom = new DecoratedTextBoxFormat("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        tbGnom.ensureDebugId(constantsDebugId.debugIdTbGnom());
        tbGprenom = new DecoratedTextBoxFormat("AAAAAAAAAAAAAAAAAAAAAAAAA");
        tbGprenom.ensureDebugId(constantsDebugId.debugIdTbGprenom());
        cbGdateNaissance = new DecoratedCalendrierDateBox(true);
        cbGdateNaissance.ensureDebugId(constantsDebugId.debugIdCbGdateNaissance());
        libGnature = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        libGnature.setWidth("120px");
        libGnature.setEnabled(false);
        libGnature.ensureDebugId(constantsDebugId.debugIdLibGnature());

        // final Label lbPersonne = new Label(constants.lbRpersonne());
        final Label lbNature = new Label(constants.lbRnature());
        final HTML lbNumClient = new HTML(constants.lbRnumeroClient());

        final List<ChampSynthese> listeChampsSynthese = new ArrayList<ChampSynthese>();
        final ChampSynthese csCivilite =
            new ChampSynthese(libGcivilite, "", iconeErreurChampManager.createInstance("PersonneDto.civilite", libGcivilite), false);
        final ChampSynthese csNom =
            new ChampSynthese(tbGnom, "", iconeErreurChampManager.createInstance("PersonneDto.nom", tbGnom), false, constants.maxCaracteresNom());
        final ChampSynthese csPrenom =
            new ChampSynthese(tbGprenom, "", iconeErreurChampManager.createInstance("PersonneDto.prenom", tbGprenom), false, constants.maxCaracteresPrenom());
        csDateNaissance =
            new ChampSynthese(cbGdateNaissance, "née le ", iconeErreurChampManager.createInstance("PersonneDto.dateNaissance", cbGdateNaissance), false);
        csAge = new ChampSynthese(lbAge, "", true);
        csCivilite.addStyleName(SquareResources.INSTANCE.css().titreFichePersonne());
        csPrenom.addStyleName(SquareResources.INSTANCE.css().titreFichePersonne());
        csNom.addStyleName(SquareResources.INSTANCE.css().titreFichePersonne());
        listeChampsSynthese.add(csCivilite);
        listeChampsSynthese.add(csNom);
        listeChampsSynthese.add(csPrenom);
        listeChampsSynthese.add(csDateNaissance);
        listeChampsSynthese.add(csAge);

        listeChampsSynthese
                .add(new ChampSynthese(libGnature, lbNature, iconeErreurChampManager.createInstance("PersonneDto.naturePersonne", libGnature), true));
        listeChampsSynthese.add(new ChampSynthese(lbRnumeroClient, lbNumClient, true));
        listeChampsSynthese.add(new ChampSynthese(lbSituationFamiliale, "", true));
        final AideComposant aideView = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_GESTION_GLOBAL, isAdmin);
        ajouterAideComposant(aideView);
        final VerticalPanel aideIconePanel = new VerticalPanel();
        aideIconePanel.add(aideView);
        aideIconePanel.setCellHorizontalAlignment(aideView, HasAlignment.ALIGN_RIGHT);

        blRpersonne = new BlocSyntheseDepliant(listeChampsSynthese, aideIconePanel, construireGestionPanel(), 5);
        blRpersonne.setWidth(AppControllerConstants.POURCENT_100);
        blRpersonne.addStyleName(SquareResources.INSTANCE.css().personnePhysiqueGestionSynthese());

        // AJOUT DE PANEL
        this.tabPanel = new SimpleTabPanelScroll(944);
        tabPanel.addStyleName(SquareResources.INSTANCE.css().personnePhysiqueGestionTabPanel());
        tabPanel.setHeight(AppControllerConstants.POURCENT_100);
        majTabPanel(permissionsOnglets);

        verticalPanel.setCellHorizontalAlignment(aideView, HasAlignment.ALIGN_RIGHT);
        verticalPanel.add(blRpersonne);
        verticalPanel.add(tabPanel);
        verticalPanel.setCellHeight(blRpersonne, "1%");
        verticalPanel.setCellVerticalAlignment(tabPanel, HasVerticalAlignment.ALIGN_TOP);

        // FORME LE MENU CONTEXT
        contruireActionsContextPanel();

        initWidget(verticalPanel);
        this.addStyleName(SquareResources.INSTANCE.css().personnePhysiqueGestion());
    }

    /**
     * Construction du menu contextuel.
     */
    private void contruireActionsContextPanel() {
        lbContextMenu = new Label();
        lbContextMenu.ensureDebugId(constantsDebugId.debugIdLbContextMenu());
        lbContextMenu.addStyleName(SquareResources.INSTANCE.css().titrePanelDroite());
        btActionContextAddAction = new DecoratedButton(constants.nouvelleAction());
        btActionContextAddAction.ensureDebugId(constantsDebugId.debugIdBtActionContextAddAction());
        btnOpportuniteContextAddOpportunite = new DecoratedButton(constants.nouvelleOpportunite());
        btnOpportuniteContextAddOpportunite.ensureDebugId(constantsDebugId.debugIdBtnOpportuniteContextAddOpportunite());

        contextMenu = new VerticalPanel();
        contextMenu.add(lbContextMenu);
        contextMenu.add(btnOpportuniteContextAddOpportunite);
        contextMenu.add(btActionContextAddAction);

        contextMenu.addStyleName(SquareResources.INSTANCE.css().blocPanelDroite());
        contextMenu.setWidth(AppControllerConstants.POURCENT_100);
        contextMenu.setSpacing(10);
        contextMenu.setCellHorizontalAlignment(btnOpportuniteContextAddOpportunite, HasAlignment.ALIGN_CENTER);
        contextMenu.setCellHorizontalAlignment(btActionContextAddAction, HasAlignment.ALIGN_CENTER);
    }

    /**
     * Construit un affichage pour le resume.
     * @return l'affichage correspondant au resume.
     */
    private Widget construireGestionPanel() {
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
            final SuggestListBoxSingleProperties<IdentifiantLibelleBooleanModel> slbIdentifiantLibelleBooleanProperties =
                new SuggestListBoxSingleProperties<IdentifiantLibelleBooleanModel>() {
                    @Override
                    public String getSelectSuggestRenderer(IdentifiantLibelleBooleanModel row) {
                        return row == null ? "" : row.getLibelle();
                    }

                    @Override
                    public String[] getResultColumnsRenderer() {
                        return new String[] {};
                    }

                    @Override
                    public String[] getResultRowsRenderer(IdentifiantLibelleBooleanModel row) {
                        return new String[] {row == null ? "" : row.getLibelle()};
                    }
                };
            final SuggestListBoxSingleProperties<IdentifiantLibelleOuiNonModel> slbIdentifiantLibelleOuiNonProperties =
                new SuggestListBoxSingleProperties<IdentifiantLibelleOuiNonModel>() {
                    @Override
                    public String getSelectSuggestRenderer(IdentifiantLibelleOuiNonModel row) {
                        return row == null ? "" : row.getLibelle();
                    }

                    @Override
                    public String[] getResultColumnsRenderer() {
                        return new String[] {};
                    }

                    @Override
                    public String[] getResultRowsRenderer(IdentifiantLibelleOuiNonModel row) {
                        return new String[] {row == null ? "" : row.getLibelle()};
                    }
                };

        btGenregistrer = new DecoratedButton(constants.lbGenregistrer());
        btGenregistrer.ensureDebugId(constantsDebugId.debugIdBtGenregistrer());
        // disableElement(btGenregistrer.getElement());
        btGAnnuler = new DecoratedButton(constants.lbGannuler());
        btGAnnuler.ensureDebugId(constantsDebugId.debugIdBtGAnnuler());

        final HorizontalPanel conteneurBoutons = new HorizontalPanel();
        conteneurBoutons.setSpacing(5);
        conteneurBoutons.add(btGenregistrer);
        conteneurBoutons.add(btGAnnuler);
        conteneurBoutons.setCellVerticalAlignment(btGAnnuler, HasVerticalAlignment.ALIGN_MIDDLE);

        final VerticalPanel panel = new VerticalPanel();
        panel.setWidth(AppControllerConstants.POURCENT_100);
        panel.setSpacing(10);
        panel.add(construireBlocInformations(slbIdentifiantLibelleProperties));
        panel.add(construireBlocInfosSup(slbIdentifiantLibelleProperties, slbIdentifiantLibelleBooleanProperties));
        panel.add(construireBlocCreation());
        panel.add(conteneurBoutons);
        panel.setCellHorizontalAlignment(conteneurBoutons, HasHorizontalAlignment.ALIGN_RIGHT);
        return panel;
    }

    private Widget construireBlocInfosSup(SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdentifiantLibelleProperties, SuggestListBoxSingleProperties<IdentifiantLibelleBooleanModel> slbIdentifiantLibelleBooleanProperties) {
        libDecede = new Label("", false);
        libDecede.ensureDebugId(constantsDebugId.debugIdLbGDecede());
        lbGnumeroRo = new Label("", false);
        lbGnumeroRo.ensureDebugId(constantsDebugId.debugIdLbGnumeroRo());
        lbGstatut = new Label("", false);
        lbGstatut.ensureDebugId(constantsDebugId.debugIdLbGstatut());
        libGregime = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        aidelibGregime = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_GESTION_REGIME, isAdmin);
        ajouterAideComposant(aidelibGregime);
        libGregime.ensureDebugId(constantsDebugId.debugIdLibGregime());
        libGsegment = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        libGsegment.ensureDebugId(constantsDebugId.debugIdLibGsegment());
        libGsegment.setEnabled(false);
        aidelibGsegment = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_GESTION_SEGMENT, isAdmin);
        ajouterAideComposant(aidelibGsegment);
        final SuggestListBoxSingleProperties<CaisseSimpleModel> slbCaisseProperties = new SuggestListBoxSingleProperties<CaisseSimpleModel>() {

            @Override
            public String getSelectSuggestRenderer(CaisseSimpleModel row) {
                return row == null || row.getId().equals(constants.IDENTIFIANT_NC) ? "" : row.getCode() + "." + row.getCentre() + "." + row.getNom();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(CaisseSimpleModel row) {
                return new String[] {row == null || row.getId().equals(constants.IDENTIFIANT_NC) ? "" : row.getCode() + "." + row.getCentre() + "."
                    + row.getNom()};
            }
        };
        libGcaisse = new DecoratedSuggestListBoxSingle<CaisseSimpleModel>(slbCaisseProperties);
        libGcaisse.ensureDebugId(constantsDebugId.debugIdLibGcaisse());
        aidelibGcaisse = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_GESTION_CAISSE, isAdmin);
        ajouterAideComposant(aidelibGcaisse);
        libGprofession = new DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel>(slbIdentifiantLibelleBooleanProperties);
        libGprofession.ensureDebugId(constantsDebugId.debugIdLibGprofession());
        aidelibGprofession = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_GESTION_PROFESSION, isAdmin);
        ajouterAideComposant(aidelibGprofession);

        final FlexTable flexTable = new FlexTable();
        flexTable.setWidth(AppControllerConstants.POURCENT_100);
        flexTable.setCellSpacing(5);
        flexTable.setWidget(0, 0, libDecede);
        flexTable.setWidget(1, 0, new Label(constants.lbGnumeroRo(), false));
        flexTable.setWidget(1, 1, lbGnumeroRo);
        flexTable.setWidget(1, 2, new Label(constants.lbGstatut(), false));
        flexTable.setWidget(1, 3, lbGstatut);
        flexTable.setWidget(2, 0, new Label(constants.lbGregime(), false));
        flexTable.setWidget(2, 1, construireBlocIcone(libGregime, "PersonneDto.infoSante.caisseRegime", aidelibGregime));
        flexTable.setWidget(2, 2, new Label(constants.lbGsegment(), false));
        flexTable.setWidget(2, 3, construireBlocIcone(libGsegment, "PersonneDto.segment", aidelibGsegment));
        flexTable.setWidget(3, 0, new Label(constants.lbGcaisse(), false));
        flexTable.setWidget(3, 1, construireBlocIcone(libGcaisse, "PersonneDto.infoSante.caisse", aidelibGcaisse));
        flexTable.setWidget(3, 2, new Label(constants.lbGprofession(), false));
        flexTable.setWidget(3, 3, construireBlocIcone(libGprofession, "PersonneDto.profession", aidelibGprofession));
        flexTable.getFlexCellFormatter().setColSpan(0, 0, 2);
        flexTable.getRowFormatter().setVerticalAlign(2, HasVerticalAlignment.ALIGN_MIDDLE);
        flexTable.getColumnFormatter().setWidth(0, LARGEUR_COL_LIBELLE_0);
        flexTable.getColumnFormatter().setWidth(1, LARGEUR_COL_CHAMP_1);
        flexTable.getColumnFormatter().setWidth(2, LARGEUR_COL_LIBELLE_2);
        flexTable.getColumnFormatter().setWidth(3, LARGEUR_COL_CHAMP_3);

        final CaptionPanel captionPanel = new CaptionPanel(constants.lbGinformationSup());
        captionPanel.add(flexTable);
        return captionPanel;
    }


    private Widget construireBlocInformations(SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdentifiantLibelleProperties) {
        final SuggestListBoxSingleProperties<IdentifiantLibelleGwt> propertiesLbEnfant = new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
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

        libGreseauVente = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        libGreseauVente.setEnabled(false);
        libGreseauVente.ensureDebugId(constantsDebugId.debugIdLibGreseauVente());
        aidelibGreseauVente = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_GESTION_RESEAU_VENTE, isAdmin);
        ajouterAideComposant(aidelibGreseauVente);

        libGcsp = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        libGcsp.ensureDebugId(constantsDebugId.debugIdLibGcsp());
        aidelibGcsp = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_GESTION_CSP, isAdmin);
        ajouterAideComposant(aidelibGcsp);

        tbGnomJeuneFille = new DecoratedTextBoxFormat("AAAAAAAAAAAAAAAAA");
        tbGnomJeuneFille.ensureDebugId(constantsDebugId.debugIdTbGnomJeuneFille());
        aidetbGnomJeuneFille = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_GESTION_NOM_JEUNE_FILLE, isAdmin);
        ajouterAideComposant(aidetbGnomJeuneFille);

        slbAgence = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbAgence.setEnabled(false);
        slbAgence.ensureDebugId(constantsDebugId.debugIdSlbAgence());
        aideslbAgence = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_GESTION_AGENCE, isAdmin);
        ajouterAideComposant(aideslbAgence);

        libGsituationMarie = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        libGsituationMarie.ensureDebugId(constantsDebugId.debugIdLibGsituationMarie());
        aidelibGsituationMarie = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_GESTION_SITAUTION_MARITALE, isAdmin);
        ajouterAideComposant(aidelibGsituationMarie);

        slbsLibSitiationEnfants = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(propertiesLbEnfant);
        aideslbsLibSitiationEnfants = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_GESTION_SITUATION_PARENTALE, isAdmin);
        ajouterAideComposant(aideslbsLibSitiationEnfants);

        flexTableInformations = new FlexTable();
        flexTableInformations.setWidth(AppControllerConstants.POURCENT_100);
        flexTableInformations.setCellSpacing(5);
        flexTableInformations.setWidget(0, 0, new Label(constants.lbGreseauVente(), false));
        flexTableInformations.setWidget(0, 1, construireBlocIcone(libGreseauVente, "PersonneDto.reseauVente", aidelibGreseauVente));
        flexTableInformations.setWidget(0, 2, new Label(constants.lbGcsp(), false));
        flexTableInformations.setWidget(0, 3, construireBlocIcone(libGcsp, "PersonneDto.csp", aidelibGcsp));
        flexTableInformations.setWidget(LIGNE_NOM_JEUNE_FILLE, 0, new Label(constants.lbGnomJeuneFille(), false));
        flexTableInformations.setWidget(LIGNE_NOM_JEUNE_FILLE, 1, construireBlocIcone(tbGnomJeuneFille, "PersonneDto.nomJeuneFille", aidetbGnomJeuneFille));
        flexTableInformations.setWidget(2, 0, new Label(constants.libelleAgence(), false));
        flexTableInformations.setWidget(2, 1, construireBlocIcone(slbAgence, "PersonneDto.agence", aideslbAgence));
        flexTableInformations.setWidget(3, 0, new Label(constants.lbGsituationDeclare(), false));
        flexTableInformations.setWidget(3, 1, construireBlocIcone(libGsituationMarie, "PersonneDto.sitFam", aidelibGsituationMarie));
        flexTableInformations.setWidget(3, 2, new Label(constants.lbGsituationEnfants(), false));
        flexTableInformations.setWidget(3, 3, construireBlocIcone(slbsLibSitiationEnfants, "PersonneDto.nbEnfant", aideslbsLibSitiationEnfants));
        flexTableInformations.getColumnFormatter().setWidth(0, LARGEUR_COL_LIBELLE_0);
        flexTableInformations.getColumnFormatter().setWidth(1, LARGEUR_COL_CHAMP_1);
        flexTableInformations.getColumnFormatter().setWidth(2, LARGEUR_COL_LIBELLE_2);
        flexTableInformations.getColumnFormatter().setWidth(3, LARGEUR_COL_CHAMP_3);

        final CaptionPanel captionPanel = new CaptionPanel(constants.lbGinformation());
        captionPanel.add(flexTableInformations);
        return captionPanel;
    }

    private Widget construireBlocCreation() {
        lCreateur = new Label("", false);
        lCreateur.ensureDebugId(constantsDebugId.debugIdLCreateur());
        lDateCreation = new Label("", false);
        lDateCreation.ensureDebugId(constantsDebugId.debugIdLDateCreation());
        final HorizontalPanel hpLibelle = new HorizontalPanel();
        hpLibelle.add(lCreateur);
        hpLibelle.add(new HTML("&nbsp;"));
        hpLibelle.add(new Label(constants.libelleLe()));
        hpLibelle.add(new HTML("&nbsp;"));
        hpLibelle.add(lDateCreation);

        final FlexTable flexTable = new FlexTable();
        flexTable.setWidth(AppControllerConstants.POURCENT_100);
        flexTable.setCellSpacing(5);
        flexTable.setWidget(0, 0, new Label(constants.libelleCreePar(), false));
        flexTable.setWidget(0, 1, lCreateur);
        flexTable.setWidget(0, 2, new Label(constants.libelleLe(), false));
        flexTable.setWidget(0, 3, lDateCreation);
        flexTable.getColumnFormatter().setWidth(0, LARGEUR_COL_LIBELLE_0);
        flexTable.getColumnFormatter().setWidth(1, LARGEUR_COL_CHAMP_1);
        flexTable.getColumnFormatter().setWidth(2, LARGEUR_COL_LIBELLE_2);
        flexTable.getColumnFormatter().setWidth(3, LARGEUR_COL_CHAMP_3);

        final CaptionPanel captionPanel = new CaptionPanel(constants.libelleCreation());
        captionPanel.add(flexTable);
        return captionPanel;
    }

    @Override
    public void majTabPanel(PersonnePermissionOngletModel permissionsOnglets) {
        this.gestionOnglets = new HashMap<Integer, String>();

        while (tabPanel.getWidgetCount() > 0) {
            tabPanel.remove(tabPanel.getWidgetCount() - 1);
        }

        int i = 0;
        gestionOnglets.put(i++, constants.libelleTabCoordonnees());
        gestionOnglets.put(i++, constants.libelleTabRelationFamiliales());
        gestionOnglets.put(i++, constants.libelleTabRelations());
        gestionOnglets.put(i++, constants.libelleTabActions());
        gestionOnglets.put(i++, constants.libelleTabOpportunite());
        // Ajout des onglets des contrats, cotisations et prestations que si la personne possède un contrat
        if (permissionsOnglets != null && permissionsOnglets.getHasContrats()) {
            gestionOnglets.put(i++, constants.libelleTabContrats());
            if (permissionsOnglets.getIsAfficherCotisation()) {
                gestionOnglets.put(i++, constants.libelleTabCotisations());
            }
            gestionOnglets.put(i++, constants.libelleTabPrestation());
        }
        gestionOnglets.put(i++, constants.libelleTabEmails());
        gestionOnglets.put(i++, constants.libelleTabDocuments());

        // Ajout de l'onglet de l'espace client que si la personne en possède un
        if (permissionsOnglets != null && permissionsOnglets.getHasEspaceClient()) {
            gestionOnglets.put(i++, constants.libelleTabEspaceClient());
        }

        // CONSTRUCTION PANEL
        for (Integer key : gestionOnglets.keySet()) {
            final BlocArrondiBleu panel = new BlocArrondiBleu();
            this.tabPanel.add(panel, new HTML(gestionOnglets.get(key), false));
        }
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                tabPanel.refreshScrollButtons();
            }
        });
    }

    @Override
    public HasWidgets getContainerTab(String nomOnglet) {
        for (Integer index : gestionOnglets.keySet()) {
            if (gestionOnglets.get(index).equals(nomOnglet)) {
                final HasWidgets conteneur = (HasWidgets) tabPanel.getWidget(index);
                return conteneur;
            }
        }
        return null;
    }

    @Override
    public String getContainerTabInfos(Integer index) {
        return gestionOnglets.get(index);
    }

    @Override
    public HasBeforeSelectionHandlers<Integer> getTabPanelBeforeSelectionHandlers() {
        return tabPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasSelectionHandlers<Integer> getTabPanelSelectionHandlers() {
        return tabPanel;
    }

    /**
     * Associe au widget une icone qui apparait en cas d'erreur.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        panel.add(icone);
        panel.setCellVerticalAlignment(icone, HasVerticalAlignment.ALIGN_MIDDLE);
        return panel;
    }

    private HorizontalPanel construireBlocAide(final Widget composant, AideComposant aide) {
        final HorizontalPanel panel = new HorizontalPanel();
        panel.setSpacing(0);
        panel.add(composant);
        panel.add(aide);
        panel.setCellVerticalAlignment(aide, HasVerticalAlignment.ALIGN_MIDDLE);
        panel.setCellHorizontalAlignment(aide, HasHorizontalAlignment.ALIGN_CENTER);
        panel.setCellWidth(aide, "40");
        return panel;
    }

    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);

    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage et un champ d'aide.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp, AideComposant aideComposant) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        HorizontalPanel panelIcone = new HorizontalPanel();
        // panelIcone.setSpacing(5);
        panelIcone.add(icone);
        panelIcone.add(aideComposant);
        panel.add(panelIcone);
        // panelIcone.setCellVerticalAlignment(aideComposant, HasVerticalAlignment.ALIGN_MIDDLE);
        panel.setCellVerticalAlignment(panelIcone, HasVerticalAlignment.ALIGN_MIDDLE);
        return panel;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    /**
     * Récupération de la listbox nature.
     * @return the libGnature
     */
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGnature() {
        return libGnature;
    }

    /**
     * Récupération de la listbox caisse.
     * @return the libGcaisse
     */
    public DecoratedSuggestListBoxSingle<CaisseSimpleModel> getLibGcaisse() {
        return libGcaisse;
    }

    /**
     * Récupération de la listbox régime.
     * @return the libGregime
     */
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGregime() {
        return libGregime;
    }

    /**
     * Récupération de la listbox profession.
     * @return the libGprofession
     */
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel> getLibGprofession() {
        return libGprofession;
    }

    /**
     * Récupération de la listbox segment.
     * @return the libGsegment
     */
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGsegment() {
        return libGsegment;
    }

    /**
     * Récupération de la listbox réseau de vente.
     * @return the libGreseauVente
     */
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGreseauVente() {
        return libGreseauVente;
    }

    /**
     * Récupération de la listbox csp.
     * @return the libGcsp
     */
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGcsp() {
        return libGcsp;
    }

    /**
     * Récupération de la listbox Sittuation famiiliale.
     * @return the libGsituationMarie
     */
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGsituationMarie() {
        return libGsituationMarie;
    }

    /**
     * Récupération de la listbox nombre d'enfants.
     * @return the libGsitionationEnfants
     */
    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getLibGsituationEnfants() {
        return slbsLibSitiationEnfants;
    }

    /**
     * Récupération de la valeur de la listebox du nombre d'enfant.
     * @return libGsituationEnfants
     */
    @Override
    public HasValue<IdentifiantLibelleGwt> getLibGsituationEnfantsValue() {
        return slbsLibSitiationEnfants;
    }

    /**
     * Récupération de la date de naissance.
     * @return the cbGdateNaissance
     */
    public DecoratedCalendrierDateBox getCbGdateNaissance() {
        return cbGdateNaissance;
    }

    /**
     * Récupération de la textbox nom.
     * @return the tbGnom
     */
    public HasValue<String> getTbGnom() {
        return tbGnom;
    }

    /**
     * Récupération de la textbox prénom.
     * @return the tbGprenom
     */
    public HasValue<String> getTbGprenom() {
        return tbGprenom;
    }

    /**
     * Récupération du bloc synthèse.
     * @return the blRpersonne
     */
    public BlocSyntheseDepliant getBlRpersonne() {
        return blRpersonne;
    }

    /**
     * Récupération de la textbox nom de jeune fille.
     * @return the tbGnomJeuneFille
     */
    public HasValue<String> getTbGnomJeuneFille() {
        return tbGnomJeuneFille;
    }

    /**
     * Récupération de la listbox statut.
     * @return the lbGstatut
     */
    public HasText getLbGstatut() {
        return lbGstatut;
    }

    /**
     * Récupération de la listbox numéro RO.
     * @return the lbGnumeroRo
     */
    public HasText getLbGnumeroRo() {
        return lbGnumeroRo;
    }

    /**
     * Récupération de la checkbox décédé.
     * @return the libDecede
     */
    public HasText getLbGDecede() {
        return libDecede;
    }

    @Override
    public HasClickHandlers getBtGenregistrer() {
        return btGenregistrer;
    }

    @Override
    public HasClickHandlers getLbGannuler() {
        return btGAnnuler;
    }

    /**
     * Récupération du gestionnaire d'erreur.
     * @return the iconeErreurChampManager
     */
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
    }

    @Override
    public GestionPersonnePhysiqueViewImplConstants getViewConstants() {
        return constants;
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
    public void masquerLoadingPopup() {
        LoadingPopup.stop();
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLibGcivilite() {
        return libGcivilite;
    }

    @Override
    public Label getLbRnumeroClient() {
        return lbRnumeroClient;
    }

    /**
     * Renvoi la valeur de lbSituationFamiliale.
     * @return lbSituationFamiliale
     */
    @Override
    public Label getLbSituationFamiliale() {
        return lbSituationFamiliale;
    }

    /**
     * Renvoi la valeur de lbAge.
     * @return lbAge
     */
    @Override
    public Label getLbAge() {
        return lbAge;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<IdentifiantLibelleGwt> getAgence() {
        return slbAgence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestAgence() {
        return slbAgence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValueChangeHandlers<IdentifiantLibelleGwt> getChangeAgence() {
        return slbAgence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getCreateur() {
        return lCreateur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasText getDateCreation() {
        return lDateCreation;
    }

    /**
     * Afficher ou masquer le champ nom de jeune fille.
     * @param visible visible
     */
    @Override
    public void afficherNomJeuneFille(boolean visible) {
        if (visible && !tbGnomJeuneFille.isAttached()) {
            flexTableInformations.insertRow(LIGNE_NOM_JEUNE_FILLE);
            flexTableInformations.setWidget(LIGNE_NOM_JEUNE_FILLE, 0, new Label(constants.lbGnomJeuneFille(), false));
            flexTableInformations.setWidget(LIGNE_NOM_JEUNE_FILLE, 1, construireBlocIcone(tbGnomJeuneFille, "PersonneDto.nomJeuneFille"));
        } else if (!visible && tbGnomJeuneFille.isAttached()) {
            flexTableInformations.removeRow(LIGNE_NOM_JEUNE_FILLE);
        }
    }

    @Override
    public Widget asContextMenuWidget() {
        return contextMenu;
    }

    @Override
    public HasWidgets getNewContextMenuSlot() {
        final SimplePanel slot = new SimplePanel();
        contextMenu.add(slot);
        return slot;
    }

    @Override
    public HasText getLbContextMenu() {
        return lbContextMenu;
    }

    @Override
    public HasClickHandlers getBtActionsContextAddActions() {
        return btActionContextAddAction;
    }

    @Override
    public int getTabPanelSelectedIndex() {
        return tabPanel.getTabBar().getSelectedTab();
    }

    @Override
    public void setTabPanelSelectedIndex(int index) {
        tabPanel.getTabBar().selectTab(index);
    }

    @Override
    public HasClickHandlers getBtnOpportuniteContextAddOpportunite() {
        return btnOpportuniteContextAddOpportunite;
    }

    @Override
    public void updateTabName(int nbItems, String tabName) {
        for (int i = 0; i < gestionOnglets.size(); i++) {
            if (getContainerTabInfos(i).equals(tabName)) {
                tabPanel.getTabBar().setTabText(i, tabName + "(" + nbItems + ")");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectTab(String tabName) {
        // On recherche parmis les onglets l'index de l'onglet à selectionner
        for (Entry<Integer, String> entry : gestionOnglets.entrySet()) {
            if (entry.getValue().equals(tabName)) {
                tabPanel.getTabBar().selectTab(entry.getKey());
                break;
            }
        }
    }

    @Override
    public void afficherChampSyntheseDateNaissance(boolean visible) {
        // csDateNaissance.setVisible(visible);
        csAge.setVisible(visible);
    }

    @Override
    public void activerInfoSante(boolean actif) {
        libGcaisse.setEnabled(actif);
        libGregime.setEnabled(actif);
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
    public void setLbContextMenuTitle(String title) {
        this.lbContextMenu.setTitle(title);
    }

    @Override
    public void disableBoutonEnregistrer() {
        btGenregistrer.setEnabled(false);
    }

	@Override
	public void afficherPanelAutres(boolean bool) {
	}
}

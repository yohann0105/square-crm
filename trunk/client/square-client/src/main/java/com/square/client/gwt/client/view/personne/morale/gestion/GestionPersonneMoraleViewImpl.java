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
package com.square.client.gwt.client.view.personne.morale.gestion;

import static com.square.client.gwt.client.view.personne.morale.gestion.GestionPersonneMoraleViewImplConstants.LARGEUR_COL_CHAMP_1;
import static com.square.client.gwt.client.view.personne.morale.gestion.GestionPersonneMoraleViewImplConstants.LARGEUR_COL_CHAMP_3;
import static com.square.client.gwt.client.view.personne.morale.gestion.GestionPersonneMoraleViewImplConstants.LARGEUR_COL_LIBELLE_0;
import static com.square.client.gwt.client.view.personne.morale.gestion.GestionPersonneMoraleViewImplConstants.LARGEUR_COL_LIBELLE_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
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
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.composant.bloc.BlocArrondiBleu;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.presenter.personne.morale.GestionPersonneMoralePresenter.GestionPersonneMoraleView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.BlocSyntheseDepliant;
import com.square.composants.graphiques.lib.client.composants.ChampSynthese;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * La vue pour la gestion d'une personne morale.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class GestionPersonneMoraleViewImpl extends Composite implements GestionPersonneMoraleView {
    private Label lNumEntreprise;

    private Label lRaisonSociale;

    private Label lNature;

    private BlocSyntheseDepliant bsPersonneMorale;

    private DecoratedTextBox tbRaisonSociale;

    private AideComposant aidetbRaisonSociale;

    private DecoratedTextBox tbNumeroEntreprise;

    private AideComposant aidetbNumeroEntreprise;

    private DecoratedTextBox tbNumSiret;

    private AideComposant aidetbNumSiret;

    private DecoratedTextBox tbEnseigneCommercial;

    private AideComposant aidetbEnseigneCommercial;

    private DecoratedTextBox tbNAF;

    private AideComposant aidetbNAF;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> sbNature;

    private AideComposant aidesbNature;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> sbFormeJuridique;

    private AideComposant aidesbFormeJuridique;

    private Label lDateCreation;

    private Label lCreateur;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbAgence;

    private AideComposant aideslbAgence;

    private DecoratedSuggestListBoxSingle<DimensionRessourceModel> slbCommercial;

    private AideComposant aideslbCommercial;

    private VerticalPanel contextMenu;

    private DecoratedButton btActionContextAddAction;

    private Label lbContextMenu;

    private DecoratedTabPanel tabLayoutPanel;

    private Map<Integer, String> gestionOnglets;

    private IconeErreurChampManager iconeErreurChampManager;

    private GestionPersonneMoraleViewImplConstants constants;

    private GestionPersonneMoraleViewImplMessages messages;

    private GestionPersonneMoraleViewImplDebugIdConstants constantsDebugId;

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
     */
    public GestionPersonneMoraleViewImpl(boolean isAdmin) {
        iconeErreurChampManager = new IconeErreurChampManager();
        constants = (GestionPersonneMoraleViewImplConstants) GWT.create(GestionPersonneMoraleViewImplConstants.class);
        constantsDebugId = (GestionPersonneMoraleViewImplDebugIdConstants) GWT.create(GestionPersonneMoraleViewImplDebugIdConstants.class);
        messages = (GestionPersonneMoraleViewImplMessages) GWT.create(GestionPersonneMoraleViewImplMessages.class);
        this.isAdmin = isAdmin;
        final VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSize(AppControllerConstants.POURCENT_100, AppControllerConstants.POURCENT_100);

        lNumEntreprise = new Label();
        lNumEntreprise.ensureDebugId(constantsDebugId.debugIdLNumEntreprise());
        lRaisonSociale = new Label();
        lRaisonSociale.ensureDebugId(constantsDebugId.debugIdLRaisonSociale());
        lNature = new Label();
        lNature.ensureDebugId(constantsDebugId.debugIdLNature());

        final ChampSynthese numEntreprise = new ChampSynthese(lNumEntreprise, "", true);
        numEntreprise.addStyleName(SquareResources.INSTANCE.css().titreFichePersonne());
        final ChampSynthese csRaisonSociale = new ChampSynthese(lRaisonSociale, "", true);
        csRaisonSociale.addStyleName(SquareResources.INSTANCE.css().titreFichePersonne());
        final List<ChampSynthese> listeChampsSynthese = new ArrayList<ChampSynthese>();
        listeChampsSynthese.add(numEntreprise);
        listeChampsSynthese.add(csRaisonSociale);
        listeChampsSynthese.add(new ChampSynthese(lNature, constants.lNature(), true));

        AideComposant aideView = new AideComposant(AideComposantConstants.AIDE_PERSONNE_MORALE_GESTION_GLOBAL, isAdmin);
        ajouterAideComposant(aideView);
        VerticalPanel aideIconePanel = new VerticalPanel();
        aideIconePanel.add(aideView);
        aideIconePanel.setCellHorizontalAlignment(aideView, HasAlignment.ALIGN_RIGHT);

        this.bsPersonneMorale = new BlocSyntheseDepliant(listeChampsSynthese, aideIconePanel, construireGestionPanel());
        bsPersonneMorale.addStyleName(SquareResources.INSTANCE.css().personneMoraleGestionSynthese());

        construireTabPanel();

        verticalPanel.add(bsPersonneMorale);
        verticalPanel.add(tabLayoutPanel);
        verticalPanel.setCellHeight(bsPersonneMorale, "1%");
        verticalPanel.setCellVerticalAlignment(tabLayoutPanel, HasVerticalAlignment.ALIGN_TOP);

        initWidget(verticalPanel);
        this.addStyleName(SquareResources.INSTANCE.css().personneMoraleGestion());

        contruireActionsContextPanel();
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

        contextMenu = new VerticalPanel();
        contextMenu.add(lbContextMenu);
        contextMenu.add(btActionContextAddAction);

        contextMenu.addStyleName(SquareResources.INSTANCE.css().blocPanelDroite());
        contextMenu.setWidth(AppControllerConstants.POURCENT_100);
        contextMenu.setSpacing(10);
        contextMenu.setCellHorizontalAlignment(btActionContextAddAction, HasAlignment.ALIGN_CENTER);
    }

    /**
     * Construit un affichage pour la gestion de personne morale.
     * @return l'affichage correspondant au bloc de gestion.
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

        final VerticalPanel panel = new VerticalPanel();
        panel.setWidth(AppControllerConstants.POURCENT_100);
        panel.setSpacing(10);
        panel.add(construireBlocDescription(slbIdentifiantLibelleProperties));
        panel.add(construireBlocInformations(slbIdentifiantLibelleProperties));
        panel.add(construireBlocCreation());
        return panel;
    }

    private Widget construireBlocDescription(SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdentifiantLibelleProperties) {
        tbRaisonSociale = new DecoratedTextBox();
        tbRaisonSociale.ensureDebugId(constantsDebugId.debugIdTbRaisonSociale());
        tbRaisonSociale.setEnabled(false);
        aidetbRaisonSociale = new AideComposant(AideComposantConstants.AIDE_PERSONNE_MORALE_GESTION_RAISON_SOCIALE, isAdmin);
        final HorizontalPanel paneltbRaisonSociale = new HorizontalPanel();
        paneltbRaisonSociale.setSpacing(5);
        paneltbRaisonSociale.add(tbRaisonSociale);
        paneltbRaisonSociale.add(aidetbRaisonSociale);
        ajouterAideComposant(aidetbRaisonSociale);

        sbNature = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        sbNature.ensureDebugId(constantsDebugId.debugIdSbNature());
        sbNature.setEnabled(false);
        aidesbNature = new AideComposant(AideComposantConstants.AIDE_PERSONNE_MORALE_GESTION_NATURE, isAdmin);
        final HorizontalPanel panelsbNature = new HorizontalPanel();
        panelsbNature.setSpacing(5);
        panelsbNature.add(sbNature);
        panelsbNature.add(aidesbNature);
        ajouterAideComposant(aidesbNature);

        tbNumSiret = new DecoratedTextBox();
        sbNature.ensureDebugId(constantsDebugId.debugIdTbNumSiret());
        tbNumSiret.setEnabled(false);
        aidetbNumSiret = new AideComposant(AideComposantConstants.AIDE_PERSONNE_MORALE_GESTION_NUM_SIRET, isAdmin);
        final HorizontalPanel paneltbNumSiret = new HorizontalPanel();
        paneltbNumSiret.setSpacing(5);
        paneltbNumSiret.add(tbNumSiret);
        paneltbNumSiret.add(aidetbNumSiret);
        ajouterAideComposant(aidetbNumSiret);

        tbNumeroEntreprise = new DecoratedTextBox();
        tbNumeroEntreprise.ensureDebugId(constantsDebugId.debugIdTbNumeroEntreprise());
        tbNumeroEntreprise.setEnabled(false);
        aidetbNumeroEntreprise = new AideComposant(AideComposantConstants.AIDE_PERSONNE_MORALE_GESTION_NUMERO_ENTREPRISE, isAdmin);
        final HorizontalPanel paneltbNumeroEntreprise = new HorizontalPanel();
        paneltbNumeroEntreprise.setSpacing(5);
        paneltbNumeroEntreprise.add(tbNumeroEntreprise);
        paneltbNumeroEntreprise.add(aidetbNumeroEntreprise);
        ajouterAideComposant(aidetbNumeroEntreprise);

        sbFormeJuridique = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        sbFormeJuridique.ensureDebugId(constantsDebugId.debugIdSbFormeJuridique());
        sbFormeJuridique.setEnabled(false);
        aidesbFormeJuridique = new AideComposant(AideComposantConstants.AIDE_PERSONNE_MORALE_GESTION_FORME_JURIDIQUE, isAdmin);
        final HorizontalPanel panelsbFormeJuridique = new HorizontalPanel();
        panelsbFormeJuridique.setSpacing(5);
        panelsbFormeJuridique.add(sbFormeJuridique);
        panelsbFormeJuridique.add(aidesbFormeJuridique);
        ajouterAideComposant(aidesbFormeJuridique);

        tbNAF = new DecoratedTextBox();
        tbNAF.ensureDebugId(constantsDebugId.debugIdTbNAF());
        tbNAF.setEnabled(false);
        aidetbNAF = new AideComposant(AideComposantConstants.AIDE_PERSONNE_MORALE_GESTION_NAF, isAdmin);
        final HorizontalPanel paneltbNAF = new HorizontalPanel();
        paneltbNAF.setSpacing(5);
        paneltbNAF.add(tbNAF);
        paneltbNAF.add(aidetbNAF);
        ajouterAideComposant(aidetbNAF);

        tbEnseigneCommercial = new DecoratedTextBox();
        tbEnseigneCommercial.ensureDebugId(constantsDebugId.debugIdTbEnseigneCommercial());
        tbEnseigneCommercial.setEnabled(false);
        aidetbEnseigneCommercial = new AideComposant(AideComposantConstants.AIDE_PERSONNE_MORALE_GESTION_ENSEIGNE_COMMERCIALE, isAdmin);
        final HorizontalPanel paneltbEnseigneCommercial = new HorizontalPanel();
        paneltbEnseigneCommercial.setSpacing(5);
        paneltbEnseigneCommercial.add(tbEnseigneCommercial);
        paneltbEnseigneCommercial.add(aidetbEnseigneCommercial);
        ajouterAideComposant(aidetbEnseigneCommercial);

        final FlexTable flexTable = new FlexTable();
        flexTable.setWidth(AppControllerConstants.POURCENT_100);
        flexTable.setCellSpacing(5);
        flexTable.setWidget(0, 0, new Label(constants.tbRaisonSociale(), false));
        flexTable.setWidget(0, 1, tbRaisonSociale);
        flexTable.setWidget(0, 2, new Label(constants.sbNature(), false));
        flexTable.setWidget(0, 3, panelsbNature);
        flexTable.setWidget(1, 0, new Label(constants.tbNumeroEntreprise()));
        flexTable.setWidget(1, 1, paneltbNumeroEntreprise);
        flexTable.setWidget(1, 2, new Label(constants.tbNumSiret(), false));
        flexTable.setWidget(1, 3, paneltbNumSiret);
        flexTable.setWidget(2, 0, new Label(constants.tbEnseigneCommercial(), false));
        flexTable.setWidget(2, 1, paneltbEnseigneCommercial);
        flexTable.setWidget(2, 2, new Label(constants.sbFormeJuridique(), false));
        flexTable.setWidget(2, 3, panelsbFormeJuridique);
        flexTable.setWidget(3, 0, new Label(constants.tbNAF(), false));
        flexTable.setWidget(3, 1, paneltbNAF);
        flexTable.getColumnFormatter().setWidth(0, LARGEUR_COL_LIBELLE_0);
        flexTable.getColumnFormatter().setWidth(1, LARGEUR_COL_CHAMP_1);
        flexTable.getColumnFormatter().setWidth(2, LARGEUR_COL_LIBELLE_2);
        flexTable.getColumnFormatter().setWidth(3, LARGEUR_COL_CHAMP_3);

        final CaptionPanel captionPanel = new CaptionPanel(constants.captionDescription());
        captionPanel.add(flexTable);
        return captionPanel;
    }

    private Widget construireBlocInformations(SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdentifiantLibelleProperties) {
        slbAgence = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbAgence.ensureDebugId(constantsDebugId.debugIdSlbAgence());
        slbAgence.setEnabled(false);
        aideslbAgence = new AideComposant(AideComposantConstants.AIDE_PERSONNE_MORALE_GESTION_AGENCE, isAdmin);
        ajouterAideComposant(aideslbAgence);

        slbCommercial = new DecoratedSuggestListBoxSingle<DimensionRessourceModel>(new SuggestListBoxSingleProperties<DimensionRessourceModel>() {
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
                return new String[] {row == null ? "" : row.getNom() + " " + row.getPrenom()};
            }
        });
        slbCommercial.ensureDebugId(constantsDebugId.debugIdSlbCommercial());
        slbCommercial.setEnabled(false);
        aideslbCommercial = new AideComposant(AideComposantConstants.AIDE_PERSONNE_MORALE_GESTION_COMMERCIAL, isAdmin);
        ajouterAideComposant(aideslbCommercial);

        final FlexTable flexTableInformations = new FlexTable();
        flexTableInformations.setWidth(AppControllerConstants.POURCENT_100);
        flexTableInformations.setCellSpacing(5);
        flexTableInformations.setWidget(0, 0, new Label(constants.libelleAgence(), false));
        flexTableInformations.setWidget(0, 1, construireBlocIcone(slbAgence, "PersonneDto.agence", aideslbAgence));
        flexTableInformations.setWidget(0, 2, new Label(constants.libelleCommercial(), false));
        flexTableInformations.setWidget(0, 3, construireBlocIcone(slbCommercial, "PersonneDto.commercial", aideslbCommercial));
        flexTableInformations.getColumnFormatter().setWidth(0, LARGEUR_COL_LIBELLE_0);
        flexTableInformations.getColumnFormatter().setWidth(1, LARGEUR_COL_CHAMP_1);
        flexTableInformations.getColumnFormatter().setWidth(2, LARGEUR_COL_LIBELLE_2);
        flexTableInformations.getColumnFormatter().setWidth(3, LARGEUR_COL_CHAMP_3);

        final CaptionPanel captionPanel = new CaptionPanel(constants.lbGinformation());
        captionPanel.add(flexTableInformations);
        return captionPanel;
    }

    private Widget construireBlocCreation() {
        lCreateur = new Label();
        lCreateur.ensureDebugId(constantsDebugId.debugIdLCreateur());
        lDateCreation = new Label();
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
        flexTable.setWidget(0, 2, new Label(constants.libelleLe()));
        flexTable.setWidget(0, 3, lDateCreation);
        flexTable.getColumnFormatter().setWidth(0, LARGEUR_COL_LIBELLE_0);
        flexTable.getColumnFormatter().setWidth(1, LARGEUR_COL_CHAMP_1);
        flexTable.getColumnFormatter().setWidth(2, LARGEUR_COL_LIBELLE_2);
        flexTable.getColumnFormatter().setWidth(3, LARGEUR_COL_CHAMP_3);

        final CaptionPanel captionPanel = new CaptionPanel(constants.libelleCreation());
        captionPanel.add(flexTable);
        return captionPanel;
    }

    /**
     * Construction du Panel.
     */
    private void construireTabPanel() {
        this.gestionOnglets = new HashMap<Integer, String>();
        this.tabLayoutPanel = new DecoratedTabPanel();
        tabLayoutPanel.addStyleName(SquareResources.INSTANCE.css().personneMoraleGestionTabPanel());
        tabLayoutPanel.setSize(AppControllerConstants.POURCENT_100, AppControllerConstants.POURCENT_100);

        // AJOUT DE PANEL
        gestionOnglets.put(GestionPersonneMoraleViewImplConstants.INDEX_TAB_COORDONNEES, constants.libelleTabCoordonnees());
        gestionOnglets.put(GestionPersonneMoraleViewImplConstants.INDEX_TAB_RELATIONS, constants.libelleTabRelations());
        gestionOnglets.put(GestionPersonneMoraleViewImplConstants.INDEX_TAB_ACTIONS, constants.libelleTabActions());
        gestionOnglets.put(GestionPersonneMoraleViewImplConstants.INDEX_TAB_CONTRATS, constants.libelleTabContratsPM());
        gestionOnglets.put(GestionPersonneMoraleViewImplConstants.INDEX_TAB_DOCUMENTS, constants.libelleTabDocuments());

        // CONSTRUCTION PANEL
        for (Integer key : gestionOnglets.keySet()) {
            final BlocArrondiBleu panel = new BlocArrondiBleu();
            this.tabLayoutPanel.add(panel, new HTML(gestionOnglets.get(key)));
        }
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

    /** construire les listes des aides */
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
    public void setDataResumePersonneMorale(String numEntreprise, String raisonSociale, String nature) {
        lNumEntreprise.setText(numEntreprise);
        lRaisonSociale.setText(raisonSociale);
        lNature.setText(nature);
    }

    @Override
    public HasWidgets getContainerTab(String nomOnglet) {
        for (Integer index : gestionOnglets.keySet()) {
            if (gestionOnglets.get(index).equals(nomOnglet)) {
                final HasWidgets conteneur = (HasWidgets) tabLayoutPanel.getWidget(index);
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
        return tabLayoutPanel;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSbNature() {
        return sbNature;
    }

    @Override
    public void afficherLoadingPopup() {
        LoadingPopup.afficher(new LoadingPopupConfiguration(messages.chargementPersonneMorale()));
    }

    @Override
    public BlocSyntheseDepliant getBlRpersonneMorale() {
        return bsPersonneMorale;
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSbFormeJuridique() {
        return sbFormeJuridique;
    }

    @Override
    public HasValue<String> getTbEnseigneCommerciale() {
        return tbEnseigneCommercial;
    }

    @Override
    public HasValue<String> getTbNAF() {
        return tbNAF;
    }

    @Override
    public HasValue<String> getTbNumEntreprise() {
        return tbNumeroEntreprise;
    }

    @Override
    public HasValue<String> getTbNumSiret() {
        return tbNumSiret;
    }

    @Override
    public HasValue<String> getTbRaisonSociale() {
        return tbRaisonSociale;
    }

    @Override
    public GestionPersonneMoraleViewImplConstants getViewConstants() {
        return constants;
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stop();
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        ErrorPopup.afficher(config);

    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stop();
    }

    @Override
    public Widget asWidget() {
        return this;
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
    public HasValue<DimensionRessourceModel> getCommercial() {
        return slbCommercial;
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
    public HasSuggestListBoxHandler<DimensionRessourceModel> getSuggestCommercial() {
        return slbCommercial;
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

    @Override
    public Widget asContextMenuWidget() {
        return contextMenu;
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
        return tabLayoutPanel.getTabBar().getSelectedTab();
    }

    @Override
    public void setTabPanelSelectedIndex(int index) {
        tabLayoutPanel.getTabBar().selectTab(index);
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getFormeJuridique() {
        return sbFormeJuridique;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getNature() {
        return sbNature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasSelectionHandlers<Integer> getTabPanelSelectionHandlers() {
        return tabLayoutPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectTab(String tabName) {
        // On recherche parmis les onglets l'index de l'onglet à selectionner
        for (Entry<Integer, String> entry : gestionOnglets.entrySet()) {
            if (entry.getValue().equals(tabName)) {
                tabLayoutPanel.getTabBar().selectTab(entry.getKey());
                break;
            }
        }
    }

    @Override
    public void updateTabName(int nbItems, String tabName) {
        for (int i = 0; i < gestionOnglets.size(); i++) {
            if (getContainerTabInfos(i).equals(tabName)) {
                tabLayoutPanel.getTabBar().setTabText(i, tabName + "(" + nbItems + ")");
            }
        }
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
}

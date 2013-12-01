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
package com.square.client.gwt.client.view.personne.morale.moteur.recherche;

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
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.grid.remote.paging.RemotePagingTable;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxComposite.SuggestListBoxCompositeProperties;
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
import com.square.client.gwt.client.model.CodePostalCommuneModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.model.IdentifiantLibelleDepartementCodeModel;
import com.square.client.gwt.client.model.PersonneMoralCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneMoraleRechercheModel;
import com.square.client.gwt.client.presenter.personne.morale.PersonneMoraleMoteurRecherchePresenter;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxComposite;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;

/**
 * Vue du moteur de recherche de personne morale.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonneMoraleMoteurRechercheViewImpl extends Composite implements PersonneMoraleMoteurRecherchePresenter.PersonneMoraleMoteurRechercheView {

    /** View constants. */
    private static PersonneMoraleMoteurRechercheViewImplConstants viewConstants =
        (PersonneMoraleMoteurRechercheViewImplConstants) GWT.create(PersonneMoraleMoteurRechercheViewImplConstants.class);

    /** View debugId constants. */
    private static PersonneMoraleMoteurRechercheViewImplDebugIdConstants viewDebugIdConstants =
        (PersonneMoraleMoteurRechercheViewImplDebugIdConstants) GWT.create(PersonneMoraleMoteurRechercheViewImplDebugIdConstants.class);

    private VerticalPanel conteneur;

    private DecoratedTextBox tbRaisonSociale;

    private AideComposant aidetbRaisonSociale;

    private DecoratedTextBox tbNumEntreprise;

    private AideComposant aidetbNumEntreprise;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleDepartementCodeModel> slbDepartement;

    private AideComposant aideslbDepartement;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbFormeJuridique;

    private AideComposant aideslbFormeJuridique;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbNature;

    private AideComposant aideslbNature;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbCodepostal;

    private AideComposant aideslbCodepostal;

    private DecoratedSuggestListBoxComposite<CodePostalCommuneModel> slbVille;

    private AideComposant aideslbVille;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbAgence;

    private AideComposant aideslbAgence;

    private DecoratedSuggestListBoxComposite<DimensionRessourceModel> slbCommercial;

    private AideComposant aideslbCommercial;

    private SuggestListBoxCompositeProperties<IdentifiantLibelleGwt> slbIdLibelleProperties;

    private SuggestListBoxCompositeProperties<IdentifiantLibelleGwt> slbCodePostalProperties;

    private SuggestListBoxCompositeProperties<CodePostalCommuneModel> slbCommuneProperties;

    private SuggestListBoxCompositeProperties<IdentifiantLibelleDepartementCodeModel> slbDepartementProperties;

    private SuggestListBoxCompositeProperties<IdentifiantLibelleGwt> slbAgenceProperties;

    private SuggestListBoxCompositeProperties<DimensionRessourceModel> slbCommercialProperties;

    private DecoratedButton btnRechercher;

    private DecoratedButton btnEffacerRecherche;

    private RemotePagingTable<PersonneMoraleRechercheModel, PersonneMoralCriteresRechercheModel> remotePagingTablePersonnesMorale;

    /** Pour recuperer les evenements sur le tableau dans le Presenter. */
    private HandlerManager remotePagingHandlerManager;

    private FocusPanel focusPanel;

    private HorizontalPanel panelExport;

    private Image imageExportExcel;

    private Label labelExportExcel;

    private AppControllerConstants appConstantes;

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
     * @param appConstantes les appConstantes.
     */
    public PersonneMoraleMoteurRechercheViewImpl(AppControllerConstants appConstantes, boolean isAdmin) {
        this.focusPanel = new FocusPanel();
        this.appConstantes = appConstantes;
        this.isAdmin = isAdmin;
        conteneur = new VerticalPanel();
        conteneur.setSpacing(10);
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        AideComposant aideView = new AideComposant(AideComposantConstants.AIDE_VIDE, isAdmin);
        ajouterAideComposant(aideView);
        conteneur.add(aideView);
        conteneur.setCellHorizontalAlignment(aideView, HasAlignment.ALIGN_RIGHT);
        construireBlocIdentification();

        construireBlocInformation();

        btnRechercher = new DecoratedButton(viewConstants.rechercher());
        btnRechercher.ensureDebugId(viewDebugIdConstants.debugIdBtnRechercher());
        btnEffacerRecherche = new DecoratedButton(viewConstants.effacer());
        btnEffacerRecherche.ensureDebugId(viewDebugIdConstants.debugIdBtnEffacerRecherche());

        final FlexTable conteneurButtons = new FlexTable();
        conteneurButtons.setCellSpacing(5);
        conteneurButtons.setWidget(0, 0, btnRechercher);
        conteneurButtons.setWidget(0, 1, btnEffacerRecherche);
        conteneur.add(conteneurButtons);
        conteneur.setCellHorizontalAlignment(conteneurButtons, HasAlignment.ALIGN_CENTER);

        createRemotePagingTable();

        creerExportExcel();

        focusPanel.add(new ContenuOnglet(conteneur));

        this.initWidget(focusPanel);
        this.setWidth(AppControllerConstants.POURCENT_100);
        this.addStyleName(SquareResources.INSTANCE.css().personneMoraleMoteurRecherche());
    }

    /**
     * construction des listes des composants d'aides.
     * @param aideComposant composant d'aide à ajouter aux listes.
     */
    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);

    }

    /**
     * Construire le premier bloc des critères de recherche.
     */
    private void construireBlocIdentification() {
        slbIdLibelleProperties = new SuggestListBoxCompositeProperties<IdentifiantLibelleGwt>() {
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
                return viewConstants.titrePopupSelection();
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
        // Proprietés code postal
        slbCodePostalProperties = new SuggestListBoxCompositeProperties<IdentifiantLibelleGwt>() {
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
                return viewConstants.titrePopupSelection();
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
        // Proprieté commune
        slbCommuneProperties = new SuggestListBoxCompositeProperties<CodePostalCommuneModel>() {
            @Override
            public String getSuggestListBoxMultiplePopupTitle() {
                return viewConstants.titrePopupSelection();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(CodePostalCommuneModel row) {
            	if (row == null) {
                    return new String[] {"", ""};
                } else {
                	// Affichage du libellé de la commune si différent du libellé d'acheminement
                	String libelleCommune = row.getLibelleAcheminement();
                	if (!row.getLibelleAcheminement().equals(row.getCommune().getLibelle())) {
                		libelleCommune += " (" + row.getCommune().getLibelle() + ")";
                	}
                    return new String[] {row.getCodePostal().getLibelle(), libelleCommune};
                }
            }

            @Override
            public String getSelectSuggestRenderer(CodePostalCommuneModel row) {
                return row == null ? "" : row.getLibelleAcheminement();
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
        // Départements
        slbDepartementProperties = new SuggestListBoxCompositeProperties<IdentifiantLibelleDepartementCodeModel>() {
            @Override
            public String getSuggestListBoxMultiplePopupTitle() {
                return viewConstants.titrePopupSelection();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(IdentifiantLibelleDepartementCodeModel row) {
                String libelle;
                String codeDepartement;
                if (row == null) {
                    libelle = "";
                    codeDepartement = "";
                }
                else {
                    libelle = row.getLibelle();
                    codeDepartement = row.getCodeDepartement();
                }
                return new String[] {libelle, codeDepartement};
            }

            @Override
            public String getSelectSuggestRenderer(IdentifiantLibelleDepartementCodeModel row) {
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

        tbRaisonSociale = new DecoratedTextBox();
        tbRaisonSociale.ensureDebugId(viewDebugIdConstants.debugIdTbRaisonSociale());
        aidetbRaisonSociale = new AideComposant(AideComposantConstants.AIDE_VIDE, isAdmin);
        HorizontalPanel paneltbRaisonSociale = new HorizontalPanel();
        paneltbRaisonSociale.setSpacing(5);
        paneltbRaisonSociale.add(tbRaisonSociale);
        paneltbRaisonSociale.add(aidetbRaisonSociale);
        ajouterAideComposant(aidetbRaisonSociale);

        tbNumEntreprise = new DecoratedTextBox();
        tbNumEntreprise.ensureDebugId(viewDebugIdConstants.debugIdTbNumEntreprise());
        aidetbNumEntreprise = new AideComposant(AideComposantConstants.AIDE_VIDE, isAdmin);
        HorizontalPanel paneltbNumEntreprise = new HorizontalPanel();
        paneltbNumEntreprise.setSpacing(5);
        paneltbNumEntreprise.add(tbNumEntreprise);
        paneltbNumEntreprise.add(aidetbNumEntreprise);
        ajouterAideComposant(aidetbNumEntreprise);

        slbDepartement = new DecoratedSuggestListBoxComposite<IdentifiantLibelleDepartementCodeModel>(slbDepartementProperties);
        slbDepartement.ensureDebugId(viewDebugIdConstants.debugIdSlbDepartement());
        slbDepartement.setScrollPanelSuggestMultipleHeight(PersonneMoraleMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbDepartement = new AideComposant(AideComposantConstants.AIDE_VIDE, isAdmin);
        HorizontalPanel panelslbDepartement = new HorizontalPanel();
        panelslbDepartement.setSpacing(5);
        panelslbDepartement.add(slbDepartement);
        panelslbDepartement.add(aideslbDepartement);
        ajouterAideComposant(aideslbDepartement);

        slbFormeJuridique = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbFormeJuridique.ensureDebugId(viewDebugIdConstants.debugIdSlbFormeJuridique());
        slbFormeJuridique.setScrollPanelSuggestMultipleHeight(PersonneMoraleMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbFormeJuridique = new AideComposant(AideComposantConstants.AIDE_VIDE, isAdmin);
        HorizontalPanel panelslbFormeJuridique = new HorizontalPanel();
        panelslbFormeJuridique.setSpacing(5);
        panelslbFormeJuridique.add(slbFormeJuridique);
        panelslbFormeJuridique.add(aideslbFormeJuridique);
        ajouterAideComposant(aideslbFormeJuridique);

        slbCodepostal = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbCodePostalProperties);
        slbCodepostal.ensureDebugId(viewDebugIdConstants.debugIdSlbCodepostal());
        slbCodepostal.setScrollPanelSuggestMultipleHeight(PersonneMoraleMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbCodepostal = new AideComposant(AideComposantConstants.AIDE_VIDE, isAdmin);
        HorizontalPanel panelslbCodepostal = new HorizontalPanel();
        panelslbCodepostal.setSpacing(5);
        panelslbCodepostal.add(slbCodepostal);
        panelslbCodepostal.add(aideslbCodepostal);
        ajouterAideComposant(aideslbCodepostal);

        slbVille = new DecoratedSuggestListBoxComposite<CodePostalCommuneModel>(slbCommuneProperties);
        slbVille.ensureDebugId(viewDebugIdConstants.debugIdSlbVille());
        slbVille.setScrollPanelSuggestMultipleHeight(PersonneMoraleMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbVille = new AideComposant(AideComposantConstants.AIDE_VIDE, isAdmin);
        HorizontalPanel panelslbVille = new HorizontalPanel();
        panelslbVille.setSpacing(5);
        panelslbVille.add(slbVille);
        panelslbVille.add(aideslbVille);
        ajouterAideComposant(aideslbVille);

        slbNature = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbNature.ensureDebugId(viewDebugIdConstants.debugIdSlbNature());
        slbNature.setScrollPanelSuggestMultipleHeight(PersonneMoraleMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbNature = new AideComposant(AideComposantConstants.AIDE_VIDE, isAdmin);
        HorizontalPanel panelslbNature = new HorizontalPanel();
        panelslbNature.setSpacing(5);
        panelslbNature.add(slbNature);
        panelslbNature.add(aideslbNature);
        ajouterAideComposant(aideslbNature);

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.identification());

        final FlexTable fpIdentification = new FlexTable();
        fpIdentification.setWidth(AppControllerConstants.POURCENT_100);
        fpIdentification.setCellSpacing(5);
        final Label raisonSociale = creerLibelle(viewConstants.raisonSociale());
        final Label formeJuridique = creerLibelle(viewConstants.formeJuridique());
        final Label numEntreprise = creerLibelle(viewConstants.numEntreprise());
        final Label departement = creerLibelle(viewConstants.departement());
        final Label codePostal = creerLibelle(viewConstants.codePostal());
        final Label ville = creerLibelle(viewConstants.ville());
        final Label nature = creerLibelle(viewConstants.nature());

        fpIdentification.setWidget(0, 0, raisonSociale);
        fpIdentification.setWidget(0, 1, paneltbRaisonSociale);
        fpIdentification.setWidget(0, 2, numEntreprise);
        fpIdentification.setWidget(0, 3, paneltbNumEntreprise);
        fpIdentification.setWidget(0, 4, departement);
        fpIdentification.setWidget(0, 5, panelslbDepartement);
        fpIdentification.setWidget(1, 0, formeJuridique);
        fpIdentification.setWidget(1, 1, panelslbFormeJuridique);
        fpIdentification.setWidget(1, 2, codePostal);
        fpIdentification.setWidget(1, 3, panelslbCodepostal);
        fpIdentification.setWidget(1, 4, ville);
        fpIdentification.setWidget(1, 5, panelslbVille);
        fpIdentification.setWidget(2, 0, nature);
        fpIdentification.setWidget(2, 1, panelslbNature);

        fpIdentification.getColumnFormatter().setWidth(0, PersonneMoraleMoteurRechercheViewImplConstants.LARGEUR_COL1_LIBELLE);
        fpIdentification.getColumnFormatter().setWidth(1, PersonneMoraleMoteurRechercheViewImplConstants.LARGEUR_COL_CHAMP);
        fpIdentification.getColumnFormatter().setWidth(2, PersonneMoraleMoteurRechercheViewImplConstants.LARGEUR_COL2_LIBELLE);
        fpIdentification.getColumnFormatter().setWidth(3, PersonneMoraleMoteurRechercheViewImplConstants.LARGEUR_COL_CHAMP);
        fpIdentification.getColumnFormatter().setWidth(4, PersonneMoraleMoteurRechercheViewImplConstants.LARGEUR_COL3_LIBELLE);
        fpIdentification.getColumnFormatter().setWidth(5, PersonneMoraleMoteurRechercheViewImplConstants.LARGEUR_COL_CHAMP);
        fieldSetPanel.add(fpIdentification);
        conteneur.add(fieldSetPanel);

    }

    /**
     * Construire le deuxième bloc des critères de recherche.
     */
    private void construireBlocInformation() {
        // Agence
        slbAgenceProperties = new SuggestListBoxCompositeProperties<IdentifiantLibelleGwt>() {
            @Override
            public String getSuggestListBoxMultiplePopupTitle() {
                return viewConstants.agenceSlbProperties();
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
        // Commercial
        slbCommercialProperties = new SuggestListBoxCompositeProperties<DimensionRessourceModel>() {
            @Override
            public String getSuggestListBoxMultiplePopupTitle() {
                return viewConstants.responsable();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(DimensionRessourceModel row) {
                return new String[] {row.getNom() + " " + row.getPrenom()};
            }

            @Override
            public String getSelectSuggestRenderer(DimensionRessourceModel row) {
                return row == null ? "" : (row.getNom() + " " + row.getPrenom());
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

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.information());
        slbAgence = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbAgenceProperties);
        slbAgence.ensureDebugId(viewDebugIdConstants.debugIdSlbAgence());
        slbAgence.setScrollPanelSuggestMultipleHeight(PersonneMoraleMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbAgence = new AideComposant(AideComposantConstants.AIDE_VIDE, isAdmin);
        HorizontalPanel panelslbAgence = new HorizontalPanel();
        panelslbAgence.setSpacing(5);
        panelslbAgence.add(slbAgence);
        panelslbAgence.add(aideslbAgence);
        ajouterAideComposant(aideslbAgence);

        slbCommercial = new DecoratedSuggestListBoxComposite<DimensionRessourceModel>(slbCommercialProperties);
        slbCommercial.ensureDebugId(viewDebugIdConstants.debugIdSlbCommercial());
        slbCommercial.setScrollPanelSuggestMultipleHeight(PersonneMoraleMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);

        aideslbCommercial = new AideComposant(AideComposantConstants.AIDE_VIDE, isAdmin);
        HorizontalPanel panelslbCommercial = new HorizontalPanel();
        panelslbCommercial.setSpacing(5);
        panelslbCommercial.add(slbCommercial);
        panelslbCommercial.add(aideslbCommercial);
        ajouterAideComposant(aideslbCommercial);

        final FlexTable fpInformation = new FlexTable();
        fpInformation.setWidth(AppControllerConstants.POURCENT_100);
        fpInformation.setCellPadding(5);
        final Label agence = creerLibelle(viewConstants.agence());
        final Label commercial = creerLibelle(viewConstants.commercial());

        fpInformation.setWidget(0, 0, agence);
        fpInformation.setWidget(0, 1, panelslbAgence);
        fpInformation.setWidget(0, 2, commercial);
        fpInformation.setWidget(0, 3, panelslbCommercial);

        fpInformation.getColumnFormatter().setWidth(0, PersonneMoraleMoteurRechercheViewImplConstants.LARGEUR_COL1_LIBELLE);
        fpInformation.getColumnFormatter().setWidth(1, PersonneMoraleMoteurRechercheViewImplConstants.LARGEUR_COL_CHAMP);
        fpInformation.getColumnFormatter().setWidth(2, PersonneMoraleMoteurRechercheViewImplConstants.LARGEUR_COL2_LIBELLE);
        fpInformation.getColumnFormatter().setWidth(3, "55%");
        fieldSetPanel.add(fpInformation);
        conteneur.add(fieldSetPanel);
    }

    private Label creerLibelle(final String libelle) {
        final Label label = new Label(libelle, false);
        label.setHorizontalAlignment(HasAlignment.ALIGN_RIGHT);
        label.addStyleName(SquareResources.INSTANCE.css().libelleMoteurRecherche());
        return label;
    }

    private void createRemotePagingTable() {
        remotePagingTablePersonnesMorale =
            new RemotePagingTable<PersonneMoraleRechercheModel, PersonneMoralCriteresRechercheModel>(
                    PersonneMoraleMoteurRechercheViewImplConstants.NB_ELEMENT_PAR_PAGE, true) {

                @Override
                public void setDataProvider(RemotePagingCriteriasGwt<PersonneMoralCriteresRechercheModel> params,
                    AsyncCallback<RemotePagingResultsGwt<PersonneMoraleRechercheModel>> callback) {
                    remotePagingHandlerManager.fireEvent(new SetDataProviderEvent<PersonneMoralCriteresRechercheModel, PersonneMoraleRechercheModel>(params,
                        callback));
                }

                @Override
                public int setDefaultSortAsc() {
                    return RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                }

                @Override
                public String setDefaultSortField() {
                    return "numEntreprise";
                }

                @Override
                public Column[] setHeader() {
                    return new Column[] {new Column(viewConstants.headerNumeroEntreprise(), viewConstants.fieldNumeroEntreprise()),
                        new Column(viewConstants.headerRaisonSociale(), viewConstants.fieldRaisonSociale()),
                        new Column(viewConstants.headerNature(), viewConstants.fieldNature()),
                        new Column(viewConstants.headerCodePostal(), viewConstants.fieldCodePostal()),
                        new Column(viewConstants.headerVille(), viewConstants.fieldVille())};
                }

                @Override
                public void setRow(int row, PersonneMoraleRechercheModel personneMorale) {
                    setWidget(row, 0, new Label(personneMorale.getNumeroEntreprise()));
                    setWidget(row, 1, new Label(personneMorale.getRaisonSociale()));
                    setWidget(row, 2, new Label(personneMorale.getNature() != null ? personneMorale.getNature().getLibelle() : ""));
                    IdentifiantLibelleGwt codePostal = null;
                    String commune = null;
                    if (personneMorale.getCodePostalCommune() != null) {
                    	if (personneMorale.getCodePostalCommune().getCodePostal() != null) {
                    		codePostal = personneMorale.getCodePostalCommune().getCodePostal();
                    	}
                    	if (personneMorale.getCodePostalCommune().getLibelleAcheminement() != null) {
                    		commune = personneMorale.getCodePostalCommune().getLibelleAcheminement();
                    	}
                    }
                    setWidget(row, 3, new Label(codePostal != null ? codePostal.getLibelle() : ""));
                    setWidget(row, 4, new Label(commune != null ? commune : ""));
                }

                @Override
                public void setCellClicked(PersonneMoraleRechercheModel objet) {
                    remotePagingHandlerManager.fireEvent(new SetCellClickedEvent<PersonneMoraleRechercheModel>(objet));
                }
            };
        remotePagingHandlerManager = new HandlerManager(remotePagingTablePersonnesMorale);
        remotePagingTablePersonnesMorale.setWidth(AppControllerConstants.POURCENT_100);
        remotePagingTablePersonnesMorale.ensureDebugId(viewDebugIdConstants.debugIdRemotePagingTablePersonnesMorale());
        conteneur.add(remotePagingTablePersonnesMorale);
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

    @Override
    public void afficherInfoPopup(PopupInfoConfiguration config) {
        new DecoratedInfoPopup(config).show();
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
    public HandlerManager getRemotePagingHandlerManager() {
        return remotePagingHandlerManager;
    }

    @Override
    public RemotePagingTable<PersonneMoraleRechercheModel, PersonneMoralCriteresRechercheModel> getRemotePagingTable() {
        return remotePagingTablePersonnesMorale;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbCodePostal() {
        return slbCodepostal;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleDepartementCodeModel> getSlbDepartement() {
        return slbDepartement;
    }

    @Override
    public HasSuggestListBoxHandler<CodePostalCommuneModel> getSlbVille() {
        return slbVille;
    }

    @Override
    public HasValue<String> getTbRaisonSocialeValue() {
        return tbRaisonSociale;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbFormeJuridique() {
        return slbFormeJuridique;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNature() {
        return slbNature;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbAgence() {
        return slbAgence;
    }

    @Override
    public HasSuggestListBoxHandler<DimensionRessourceModel> getSlbCommercial() {
        return slbCommercial;
    }

    @Override
    public HasKeyPressHandlers getTbNumEntreprise() {
        return tbNumEntreprise;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getSlbAgenceValue() {
        return slbAgence;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getSlbCodePostalValue() {
        return slbCodepostal;
    }

    @Override
    public HasValue<List<DimensionRessourceModel>> getSlbCommercialValue() {
        return slbCommercial;
    }

    @Override
    public HasValue<List<IdentifiantLibelleDepartementCodeModel>> getSlbDepartementValue() {
        return slbDepartement;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getSlbFormeJuridiqueValue() {
        return slbFormeJuridique;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getSlbNatureValue() {
        return slbNature;
    }

    @Override
    public HasValue<List<CodePostalCommuneModel>> getSlbVilleValue() {
        return slbVille;
    }

    @Override
    public HasValue<String> getTbNumEntrepriseValue() {
        return tbNumEntreprise;
    }

    @Override
    public HasKeyPressHandlers getTbRaisonSociale() {
        return tbRaisonSociale;
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
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasClickHandlers getBtnEffacerRecherche() {
        return btnEffacerRecherche;
    }

    @Override
    public void effacerRecherche() {
        tbRaisonSociale.setValue("");
        tbNumEntreprise.setValue("");
        slbCodepostal.clean();
        slbAgence.clean();
        slbCommercial.clean();
        slbDepartement.clean();
        slbFormeJuridique.clean();
        slbVille.clean();
    }

    @Override
    public HasKeyPressHandlers getGestionToucheEntreHandler() {
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
        conteneur.add(panelExport);
        conteneur.setCellHorizontalAlignment(panelExport, HasAlignment.ALIGN_RIGHT);
    }

    @Override
    public void setNbMinCaracteresRechercheCodePostal(int nbMinCaracteres) {
        slbCodepostal.setNbMinCaracteresFireEventSuggestMultiple(nbMinCaracteres);
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

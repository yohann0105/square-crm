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
package com.square.client.gwt.client.view.personne.physique.moteur.recherche;

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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.composant.bloc.ContenuOnglet;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.ChercherDoublonEvent;
import com.square.client.gwt.client.model.CodePostalCommuneModel;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.PersonneCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneSimpleModel;
import com.square.client.gwt.client.presenter.personne.physique.PersonnePhysiqueMoteurRecherchePresenter.PersonnePhysiqueMoteurRechercheView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxComposite;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;

/**
 * Vue du moteur de recherche de personne physique.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class PersonnePhysiqueMoteurRechercheViewImpl extends Composite implements PersonnePhysiqueMoteurRechercheView {

    /** View constants. */
    private static PersonnePhysiqueMoteurRechercheViewImplConstants viewConstants =
        (PersonnePhysiqueMoteurRechercheViewImplConstants) GWT.create(PersonnePhysiqueMoteurRechercheViewImplConstants.class);

    /** View constants debugId. */
    private static PersonnePhysiqueMoteurRechercheViewImplDebugIdConstants viewDebugIdConstants =
        (PersonnePhysiqueMoteurRechercheViewImplDebugIdConstants) GWT.create(PersonnePhysiqueMoteurRechercheViewImplDebugIdConstants.class);

    private FocusPanel focusPanel;

    private VerticalPanel conteneur;

    private DecoratedTextBox tbNumeroClient;

    private AideComposant aidetbNumeroClient;

    private DecoratedTextBox tbNom;

    private AideComposant aidetbNom;

    private DecoratedTextBox tbNomJF;

    private AideComposant aidetbNomJF;

    private DecoratedTextBox tbPrenom;

    private AideComposant aidetbPrenom;

    private DecoratedTextBox tbNumeroRO;

    private AideComposant aidetbNumeroRO;

    private DecoratedTextBox tbTelephone;

    private AideComposant aidetbTelephone;

    private DecoratedTextBox tbEmail;

    private AideComposant aidetbEmail;

    private DecoratedTextBox tbAdresse;

    private AideComposant aidetbAdresse;

    private DecoratedTextBox tbNumeroVoie;

    private AideComposant aidetbNumeroVoie;

    private DecoratedCalendrierDateBox cdbDateNaissance;

    private AideComposant aidecdbDateNaissance;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbNature;

    private AideComposant aideslbNature;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbNatureVoie;

    private AideComposant aideslbNatureVoie;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbCodePostal;

    private AideComposant aideslbCodePostal;

    private DecoratedSuggestListBoxComposite<CodePostalCommuneModel> slbVille;

    private AideComposant aideslbVille;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbcAgences;

    private AideComposant aideslbcAgences;

    private SuggestListBoxCompositeProperties<IdentifiantLibelleGwt> slbIdLibelleProperties;

    private DecoratedButton btnRechercher;

    private DecoratedButton btnEffacerRecherche;

    private SuggestListBoxCompositeProperties<IdentifiantLibelleGwt> slbCodePostalProperties;

    private SuggestListBoxCompositeProperties<CodePostalCommuneModel> slbCommuneProperties;

    private RemotePagingTable<PersonneSimpleModel, PersonneCriteresRechercheModel> remotePagingTablePersonnes;

    /** Pour recuperer les evenements sur le tableau dans le Presenter. */
    private HandlerManager remotePagingHandlerManager;

    /** Constantes. */
    private ConstantesModel constantes;

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
     * @param constantes les constantes.
     * @param appConstantes les appConstantes.
     */
    public PersonnePhysiqueMoteurRechercheViewImpl(ConstantesModel constantes, AppControllerConstants appConstantes) {
        this.focusPanel = new FocusPanel();
        this.constantes = constantes;
        this.appConstantes = appConstantes;
        this.isAdmin = constantes.isHasRoleAdmin();

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
        conteneur = new VerticalPanel();
        conteneur.setSpacing(10);
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        final AideComposant aideView = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_GLOBAL, isAdmin);
        ajouterAideComposant(aideView);
        conteneur.add(aideView);
        conteneur.setCellHorizontalAlignment(aideView, HasAlignment.ALIGN_RIGHT);
        construireBlocIdentification();
        construireBlocComplement();
        construiteBlocInformations();

        btnRechercher = new DecoratedButton(viewConstants.rechercher());
        btnRechercher.ensureDebugId(viewDebugIdConstants.debugIdBtnRecherche());
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
        this.addStyleName(SquareResources.INSTANCE.css().personnePhysiqueMoteurRecherche());
        this.setWidth(AppControllerConstants.POURCENT_100);
    }

    /**
     * Construit le bloc des criteres de recherche.
     */
    private void construireBlocIdentification() {
        tbNumeroClient = new DecoratedTextBox();
        tbNumeroClient.ensureDebugId(viewDebugIdConstants.debugIdTbNumClient());
        aidetbNumeroClient = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_NUMERO_CLIENT, isAdmin);
        final HorizontalPanel paneltbNumeroClient = new HorizontalPanel();
        paneltbNumeroClient.setSpacing(5);
        paneltbNumeroClient.add(tbNumeroClient);
        paneltbNumeroClient.add(aidetbNumeroClient);
        ajouterAideComposant(aidetbNumeroClient);

        tbNom = new DecoratedTextBox();
        tbNom.ensureDebugId(viewDebugIdConstants.debugIdTbNom());
        aidetbNom = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_NOM, isAdmin);
        final HorizontalPanel paneltbNom = new HorizontalPanel();
        paneltbNom.setSpacing(5);
        paneltbNom.add(tbNom);
        paneltbNom.add(aidetbNom);
        ajouterAideComposant(aidetbNom);

        tbNomJF = new DecoratedTextBox();
        tbNomJF.ensureDebugId(viewDebugIdConstants.debugIdTbNomJF());
        aidetbNomJF = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_NOM_JEUNE_FILLE, isAdmin);
        final HorizontalPanel paneltbNomJF = new HorizontalPanel();
        paneltbNomJF.setSpacing(5);
        paneltbNomJF.add(tbNomJF);
        paneltbNomJF.add(aidetbNomJF);
        ajouterAideComposant(aidetbNomJF);

        tbPrenom = new DecoratedTextBox();
        tbPrenom.ensureDebugId(viewDebugIdConstants.debugIdTbPrenom());
        aidetbPrenom = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_PRENOM, isAdmin);
        final HorizontalPanel paneltbPrenom = new HorizontalPanel();
        paneltbPrenom.setSpacing(5);
        paneltbPrenom.add(tbPrenom);
        paneltbPrenom.add(aidetbPrenom);
        ajouterAideComposant(aidetbPrenom);

        tbNumeroRO = new DecoratedTextBox();
        tbNumeroRO.ensureDebugId(viewDebugIdConstants.debugIdTbNumRO());
        aidetbNumeroRO = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_NUMERO_RO, isAdmin);
        final HorizontalPanel paneltbNumeroRO = new HorizontalPanel();
        paneltbNumeroRO.setSpacing(5);
        paneltbNumeroRO.add(tbNumeroRO);
        paneltbNumeroRO.add(aidetbNumeroRO);
        ajouterAideComposant(aidetbNumeroRO);

        cdbDateNaissance = new DecoratedCalendrierDateBox(true);
        cdbDateNaissance.ensureDebugId(viewDebugIdConstants.debugIdCdbDateNaissance());
        aidecdbDateNaissance = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_DATE_NAISSANCE, isAdmin);
        final HorizontalPanel panelcdbDateNaissance = new HorizontalPanel();
        panelcdbDateNaissance.setSpacing(5);
        panelcdbDateNaissance.add(cdbDateNaissance);
        panelcdbDateNaissance.add(aidecdbDateNaissance);
        ajouterAideComposant(aidecdbDateNaissance);

        slbNature = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbNature.ensureDebugId(viewDebugIdConstants.debugIdSlbNature());
        slbNature.setScrollPanelSuggestMultipleHeight(PersonnePhysiqueMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbNature = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_NATURE, isAdmin);
        final HorizontalPanel panelslbNature = new HorizontalPanel();
        panelslbNature.setSpacing(5);
        panelslbNature.add(slbNature);
        panelslbNature.add(aideslbNature);
        ajouterAideComposant(aideslbNature);
        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.identification());

        final Label numeroClient = creerLibelle(viewConstants.numeroClient());
        final Label nom = creerLibelle(viewConstants.nom());
        final Label nomJF = creerLibelle(viewConstants.nomJF());
        final Label prenom = creerLibelle(viewConstants.prenom());
        final Label numeroRO = creerLibelle(viewConstants.numeroRO());
        final Label dateNaissance = creerLibelle(viewConstants.dateNaissance());
        final Label nature = creerLibelle(viewConstants.nature());

        final FlexTable fpIdentification = new FlexTable();
        fpIdentification.setWidth(AppControllerConstants.POURCENT_100);
        fpIdentification.setCellSpacing(5);
        fpIdentification.setWidget(0, 0, numeroClient);
        fpIdentification.setWidget(0, 1, paneltbNumeroClient);
        fpIdentification.setWidget(0, 2, nom);
        fpIdentification.setWidget(0, 3, paneltbNom);
        fpIdentification.setWidget(0, 4, prenom);
        fpIdentification.setWidget(0, 5, paneltbPrenom);
        fpIdentification.setWidget(1, 0, numeroRO);
        fpIdentification.setWidget(1, 1, paneltbNumeroRO);
        fpIdentification.setWidget(1, 2, nomJF);
        fpIdentification.setWidget(1, 3, paneltbNomJF);
        fpIdentification.setWidget(1, 4, dateNaissance);
        fpIdentification.setWidget(1, 5, panelcdbDateNaissance);
        fpIdentification.setWidget(2, 0, nature);
        fpIdentification.setWidget(2, 1, panelslbNature);

        fpIdentification.getColumnFormatter().setWidth(0, PersonnePhysiqueMoteurRechercheViewImplConstants.LARGEUR_COL1_LIBELLE);
        fpIdentification.getColumnFormatter().setWidth(1, PersonnePhysiqueMoteurRechercheViewImplConstants.LARGEUR_COL_CHAMP);
        fpIdentification.getColumnFormatter().setWidth(2, PersonnePhysiqueMoteurRechercheViewImplConstants.LARGEUR_COL2_LIBELLE);
        fpIdentification.getColumnFormatter().setWidth(3, PersonnePhysiqueMoteurRechercheViewImplConstants.LARGEUR_COL_CHAMP);
        fpIdentification.getColumnFormatter().setWidth(4, PersonnePhysiqueMoteurRechercheViewImplConstants.LARGEUR_COL3_LIBELLE);
        fpIdentification.getColumnFormatter().setWidth(5, PersonnePhysiqueMoteurRechercheViewImplConstants.LARGEUR_COL_CHAMP);

        fieldSetPanel.add(fpIdentification);        conteneur.add(fieldSetPanel);
    }

    /**
     * Construit le bloc des criteres de recherche.
     */
    private void construireBlocComplement() {
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

        tbTelephone = new DecoratedTextBox();
        tbTelephone.ensureDebugId(viewDebugIdConstants.debugIdTbTelephone());
        aidetbTelephone = new AideComposant(AideComposantConstants.AIDE_RECHERCHE_PERSONNE_NUMERO_TEL, isAdmin);
        final HorizontalPanel paneltbTelephone = new HorizontalPanel();
        paneltbTelephone.setSpacing(5);
        paneltbTelephone.add(tbTelephone);
        paneltbTelephone.add(aidetbTelephone);
        ajouterAideComposant(aidetbTelephone);

        tbEmail = new DecoratedTextBox();
        tbEmail.ensureDebugId(viewDebugIdConstants.debugIdTbEmail());
        aidetbEmail = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_EMAIL, isAdmin);
        final HorizontalPanel paneltbEmail = new HorizontalPanel();
        paneltbEmail.setSpacing(5);
        paneltbEmail.add(tbEmail);
        paneltbEmail.add(aidetbEmail);
        ajouterAideComposant(aidetbEmail);

        tbNumeroVoie = new DecoratedTextBox();
        tbNumeroVoie.ensureDebugId(viewDebugIdConstants.debugIdTbNumeroVoie());
        aidetbNumeroVoie = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_NUMERO_VOIE, isAdmin);
        final HorizontalPanel paneltbNumeroVoie = new HorizontalPanel();
        paneltbNumeroVoie.setSpacing(5);
        paneltbNumeroVoie.add(tbNumeroVoie);
        paneltbNumeroVoie.add(aidetbNumeroVoie);
        ajouterAideComposant(aidetbNumeroVoie);

        tbAdresse = new DecoratedTextBox();
        tbAdresse.ensureDebugId(viewDebugIdConstants.debugIdTbAdresse());
        aidetbAdresse = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_ADRESSE, isAdmin);
        final HorizontalPanel paneltbAdresse = new HorizontalPanel();
        paneltbAdresse.setSpacing(5);
        paneltbAdresse.add(tbAdresse);
        paneltbAdresse.add(aidetbAdresse);
        ajouterAideComposant(aidetbAdresse);

        slbCodePostal = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbCodePostalProperties);
        slbCodePostal.ensureDebugId(viewDebugIdConstants.debugIdSlbCodePostal());
        slbCodePostal.setScrollPanelSuggestMultipleHeight(PersonnePhysiqueMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbCodePostal = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_CODE_POSTAL, isAdmin);
        final HorizontalPanel panelslbCodePostal = new HorizontalPanel();
        panelslbCodePostal.setSpacing(5);
        panelslbCodePostal.add(slbCodePostal);
        panelslbCodePostal.add(aideslbCodePostal);
        ajouterAideComposant(aideslbCodePostal);

        slbNatureVoie = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbNatureVoie.ensureDebugId(viewDebugIdConstants.debugIdSlbNatureVoie());
        slbNatureVoie.setScrollPanelSuggestMultipleHeight(PersonnePhysiqueMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbNatureVoie = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_NATURE_VOIE, isAdmin);
        final HorizontalPanel panelslbNatureVoie = new HorizontalPanel();
        panelslbNatureVoie.setSpacing(5);
        panelslbNatureVoie.add(slbNatureVoie);
        panelslbNatureVoie.add(aideslbNatureVoie);
        ajouterAideComposant(aideslbNatureVoie);

        slbVille = new DecoratedSuggestListBoxComposite<CodePostalCommuneModel>(slbCommuneProperties);
        slbVille.ensureDebugId(viewDebugIdConstants.debugIdSlbVille());
        slbVille.setScrollPanelSuggestMultipleHeight(PersonnePhysiqueMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbVille = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_VILLE, isAdmin);
        final HorizontalPanel panelslbVille = new HorizontalPanel();
        panelslbVille.setSpacing(5);
        panelslbVille.add(slbVille);
        panelslbVille.add(aideslbVille);
        ajouterAideComposant(aideslbVille);

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.complement());

        final FlexTable fpComplement = new FlexTable();
        fpComplement.setWidth(AppControllerConstants.POURCENT_100);
        fpComplement.setCellSpacing(5);
        final Label telephone = creerLibelle(viewConstants.telephone());
        final Label email = creerLibelle(viewConstants.email());
        final Label numeroVoie = creerLibelle(viewConstants.numeroVoie());
        final Label natureVoie = creerLibelle(viewConstants.natureVoie());
        final Label adresse = creerLibelle(viewConstants.adresse());
        final Label codePostal = creerLibelle(viewConstants.codePostal());
        final Label ville = creerLibelle(viewConstants.ville());

        fpComplement.setWidget(0, 0, telephone);
        fpComplement.setWidget(0, 1, paneltbTelephone);
        fpComplement.setWidget(0, 2, email);
        fpComplement.setWidget(0, 3, paneltbEmail);
        fpComplement.setWidget(1, 0, numeroVoie);
        fpComplement.setWidget(1, 1, paneltbNumeroVoie);
        fpComplement.setWidget(1, 2, natureVoie);
        fpComplement.setWidget(1, 3, panelslbNatureVoie);
        fpComplement.setWidget(1, 4, adresse);
        fpComplement.setWidget(1, 5, paneltbAdresse);
        fpComplement.setWidget(2, 0, codePostal);
        fpComplement.setWidget(2, 1, panelslbCodePostal);
        fpComplement.setWidget(2, 2, ville);
        fpComplement.setWidget(2, 3, panelslbVille);

        fpComplement.getColumnFormatter().setWidth(0, PersonnePhysiqueMoteurRechercheViewImplConstants.LARGEUR_COL1_LIBELLE);
        fpComplement.getColumnFormatter().setWidth(1, PersonnePhysiqueMoteurRechercheViewImplConstants.LARGEUR_COL_CHAMP);
        fpComplement.getColumnFormatter().setWidth(2, PersonnePhysiqueMoteurRechercheViewImplConstants.LARGEUR_COL2_LIBELLE);
        fpComplement.getColumnFormatter().setWidth(3, PersonnePhysiqueMoteurRechercheViewImplConstants.LARGEUR_COL_CHAMP);
        fpComplement.getColumnFormatter().setWidth(4, PersonnePhysiqueMoteurRechercheViewImplConstants.LARGEUR_COL3_LIBELLE);
        fpComplement.getColumnFormatter().setWidth(5, PersonnePhysiqueMoteurRechercheViewImplConstants.LARGEUR_COL_CHAMP);
        fieldSetPanel.add(fpComplement);
        conteneur.add(fieldSetPanel);
    }

    /**
     * Construit le bloc relatif aux informations.
     */
    private void construiteBlocInformations() {
        slbcAgences = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(new SuggestListBoxCompositeProperties<IdentifiantLibelleGwt>() {
            @Override
            public String getSuggestListBoxMultiplePopupTitle() {
                return viewConstants.SlbPropertiesAgence();
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
        });
        slbcAgences.ensureDebugId(viewDebugIdConstants.debugIdSlbcAgences());
        slbcAgences.setScrollPanelSuggestMultipleHeight(PersonnePhysiqueMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbcAgences = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_RECHERCHE_AGENCES, isAdmin);
        final HorizontalPanel panelslbcAgences = new HorizontalPanel();
        panelslbcAgences.setSpacing(5);
        panelslbcAgences.add(slbcAgences);
        panelslbcAgences.add(aideslbcAgences);
        ajouterAideComposant(aideslbcAgences);
        final CaptionPanel captionPanel = new CaptionPanel(viewConstants.informations());

        final FlexTable fpInformation = new FlexTable();
        fpInformation.setCellSpacing(5);
        fpInformation.setWidth(AppControllerConstants.POURCENT_100);
        final Label libelleAgences = creerLibelle(viewConstants.libelleAgences());

        fpInformation.setWidget(0, 0, libelleAgences);
        fpInformation.setWidget(0, 1, panelslbcAgences);

        fpInformation.getColumnFormatter().setWidth(0, PersonnePhysiqueMoteurRechercheViewImplConstants.LARGEUR_COL1_LIBELLE);
        fpInformation.getColumnFormatter().setWidth(1, "89%");

        captionPanel.add(fpInformation);
        conteneur.add(captionPanel);
    }

    private Label creerLibelle(final String libelle) {
        final Label label = new Label(libelle, false);
        label.setHorizontalAlignment(HasAlignment.ALIGN_RIGHT);
        label.addStyleName(SquareResources.INSTANCE.css().libelleMoteurRecherche());
        return label;
    }

    /**
     * Crée la table paginée.
     */
    private void createRemotePagingTable() {
        remotePagingTablePersonnes =
            new RemotePagingTable<PersonneSimpleModel, PersonneCriteresRechercheModel>(PersonnePhysiqueMoteurRechercheViewImplConstants.NB_ELEMENT_PAR_PAGE,
                    true) {
                @Override
                public void setDataProvider(RemotePagingCriteriasGwt<PersonneCriteresRechercheModel> params,
                    AsyncCallback<RemotePagingResultsGwt<PersonneSimpleModel>> callback) {
                    remotePagingHandlerManager.fireEvent(new SetDataProviderEvent<PersonneCriteresRechercheModel, PersonneSimpleModel>(params, callback));
                }

                @Override
                public int setDefaultSortAsc() {
                    return RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                }

                @Override
                public String setDefaultSortField() {
                    return constantes.getProprietePersonneNom();
                }

                @Override
                public Column[] setHeader() {
                    return new Column[] {new Column(viewConstants.headerNumeroClient(), constantes.getProprietePersonneNumeroClient()),
                        new Column(viewConstants.headerNom(), constantes.getProprietePersonneNom()),
                        new Column(viewConstants.headerPrenom(), constantes.getProprietePersonnePrenom()),
                        new Column(viewConstants.headerDateNaissance(), constantes.getProprietePersonneDateNaissance()),
                        new Column(viewConstants.headerNature(), constantes.getProprietePersonneNature()),
                        new Column(viewConstants.headerSegment(), constantes.getProprietePersonneSegment()),
                        new Column(viewConstants.headerAgence(), constantes.getProprietePersonneAgence()),
                        new Column(viewConstants.headerCodePostal(), constantes.getProprietePersonneCodePostal()),
                        new Column(viewConstants.headerVille(), constantes.getProprietePersonneCommune()), new Column(viewConstants.headerDoublon())};
                }

                @Override
                public void setRow(int row, final PersonneSimpleModel personne) {
                    setWidget(row, 0, new Label(personne.getNumeroClient()));
                    	//limite le nombre de caractères pour le nom
	                    final String nom = personne.getNom();
	                    String nomLimite = nom;
	                    if (nom != null && nom.length() > viewConstants.maxCaracteresNom()) { //limite si superieur a la constante
	                    	nomLimite = nomLimite.substring(0, viewConstants.maxCaracteresNom()) + "...";
	                    }
	                    final Label labelNom = new Label(nomLimite);
	                    labelNom.setTitle(nom); //affiche le nom complet

	                  //limite le nombre de caractères pour le prénom
	                    final String prenom = personne.getPrenom();
	                    String prenomLimite = prenom;
	                    if (prenom != null && prenom.length() > viewConstants.maxCaracteresPrenom()) { //limite si superieur a la constante
	                    	prenomLimite = prenomLimite.substring(0, viewConstants.maxCaracteresPrenom()) + "...";
	                    }
	                    final Label labelPrenom = new Label(prenomLimite);
	                    labelPrenom.setTitle(prenom); //affiche le prenom complet

                    setWidget(row, 1, labelNom);
                    setWidget(row, 2, labelPrenom);
                    setWidget(row, 3, new Label(personne.getDateNaissance()));
                    setWidget(row, 4, new Label(personne.getNature() != null ? personne.getNature().getLibelle() : ""));
                    setWidget(row, 5, new Label(personne.getSegment() != null ? personne.getSegment().getLibelle() : ""));
                    setWidget(row, 6, new Label(personne.getAgence() != null ? personne.getAgence().getLibelle() : ""));
                    IdentifiantLibelleGwt codePostal = null;
                    String commune = null;
                    if (personne.getCodePostalCommune() != null) {
                        if (personne.getCodePostalCommune().getCodePostal() != null) {
                            codePostal = personne.getCodePostalCommune().getCodePostal();
                        }
                        if (personne.getCodePostalCommune().getLibelleAcheminement() != null) {
                            commune = personne.getCodePostalCommune().getLibelleAcheminement();
                        }
                    }
                    setWidget(row, 7, new Label(codePostal != null ? codePostal.getLibelle() : ""));
                    setWidget(row, 8, new Label(commune != null ? commune : ""));
                    if (personne.isDoublon()) {
                        setWidget(row, 9, new Label(viewConstants.lDoublon()));
                    } else {
                        setWidget(row, 9, new Label(""));
                    }
                }

                @Override
                public void setCellClicked(PersonneSimpleModel objet) {
                    remotePagingHandlerManager.fireEvent(new SetCellClickedEvent<PersonneSimpleModel>(objet));
                }

                /**
                 * {@inheritDoc}
                 */
                public void onClick(ClickEvent event) {
                    // si ligne cliquée différente de entête et qu'il y a des résultats
                    final FlexTable ftResultats = getFtResultats();
                    if (event.getSource().equals(ftResultats)) {
                        final Cell cell = ftResultats.getCellForEvent(event);
                        final List<PersonneSimpleModel> currentList = getCurrentList();
                        if (cell.getRowIndex() > 0 && currentList.size() > 0) {
                            // on recupere l'objet
                            final PersonneSimpleModel objetClique = currentList.get(cell.getRowIndex() - 1);
                            if (cell.getCellIndex() == 9 && objetClique.isDoublon()) {
                                remotePagingHandlerManager.fireEvent(new ChercherDoublonEvent(objetClique.getNom(), objetClique.getPrenom(),
                                    objetClique.getDateNaissance()));
                            }
                            else {
                                // on appel la methode de l'action à faire
                                setCellClicked(objetClique);
                            }
                        }
                    }
                }
            };
        remotePagingTablePersonnes.ensureDebugId(viewDebugIdConstants.debugIdTablePersonnes());
        remotePagingHandlerManager = new HandlerManager(remotePagingTablePersonnes);
        remotePagingTablePersonnes.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.add(remotePagingTablePersonnes);
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

    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);

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
    public HasValue<List<IdentifiantLibelleGwt>> getAgences() {
        return slbcAgences;
    }

    @Override
    public HasClickHandlers getBtnRechercher() {
        return btnRechercher;
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateNaissance() {
        return cdbDateNaissance;
    }

    @Override
    public HandlerManager getRemotePagingHandlerManager() {
        return remotePagingHandlerManager;
    }

    @Override
    public void rechercher() {
        remotePagingTablePersonnes.rechercher();
    }

    // TODO à remettre éventuellement pour le bug 7339
//    @Override
//    public void rechercherPageEnCours() {
//        remotePagingTablePersonnes.rechercherPageEnCours();
//    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbCodePostal() {
        return slbCodePostal;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getSlbCodePostalValue() {
        return slbCodePostal;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNature() {
        return slbNature;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getSlbNatureValue() {
        return slbNature;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNatureVoie() {
        return slbNatureVoie;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getSlbNatureVoieValue() {
        return slbNatureVoie;
    }

    @Override
    public HasSuggestListBoxHandler<CodePostalCommuneModel> getSlbVille() {
        return slbVille;
    }

    @Override
    public HasValue<List<CodePostalCommuneModel>> getSlbVilleValue() {
        return slbVille;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestionAgence() {
        return slbcAgences;
    }

    @Override
    public HasValue<String> getTbAdresse() {
        return tbAdresse;
    }

    @Override
    public HasValue<String> getTbEmail() {
        return tbEmail;
    }

    @Override
    public HasValue<String> getTbNom() {
        return tbNom;
    }

    @Override
    public HasValue<String> getTbNomJF() {
        return tbNomJF;
    }

    @Override
    public HasValue<String> getTbNumeroClient() {
        return tbNumeroClient;
    }

    @Override
    public HasValue<String> getTbNumeroRO() {
        return tbNumeroRO;
    }

    @Override
    public HasValue<String> getTbNumeroVoie() {
        return tbNumeroVoie;
    }

    @Override
    public HasValue<String> getTbPrenom() {
        return tbPrenom;
    }

    @Override
    public HasValue<String> getTbTelephone() {
        return tbTelephone;
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

    @Override
    public HasClickHandlers getBtnEffacerRecherche() {
        return btnEffacerRecherche;
    }

    @Override
    public void effacerRecherche() {
        tbNumeroClient.setValue("");
        tbNumeroRO.setValue("");
        tbNom.setValue("");
        tbPrenom.setValue("");
        cdbDateNaissance.clean();
        tbNomJF.setValue("");
        tbTelephone.setValue("");
        tbEmail.setValue("");
        tbNumeroVoie.setValue("");
        tbAdresse.setValue("");
        slbcAgences.clean();
        slbCodePostal.clean();
        slbNature.clean();
        slbNatureVoie.clean();
        slbVille.clean();
    }

    @Override
    public HasKeyPressHandlers getGestionToucheEntreHandler() {
        return focusPanel;
    }

    @Override
    public Focusable getTbNumeroClientFocus() {
        return tbNumeroClient;
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
        slbCodePostal.setNbMinCaracteresFireEventSuggestMultiple(nbMinCaracteres);
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

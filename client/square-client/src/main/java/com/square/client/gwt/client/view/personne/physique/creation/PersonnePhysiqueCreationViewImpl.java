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
package com.square.client.gwt.client.view.personne.physique.creation;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.bundle.theme.smatis.SmatisResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.CodePostalCommuneModel;
import com.square.client.gwt.client.model.IdentifiantLibelleBooleanModel;
import com.square.client.gwt.client.presenter.personne.physique.PersonnePhysiqueCreationPresenter.BlocCreationBeneficiaireView;
import com.square.client.gwt.client.presenter.personne.physique.PersonnePhysiqueCreationPresenter.PersonnePhysiqueCreationView;
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
 * Vue de la page de création de personne physique.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class PersonnePhysiqueCreationViewImpl extends Popup implements PersonnePhysiqueCreationView {

    /** View constants. */
    private static PersonnePhysiqueCreationViewImplConstants viewConstants =
        (PersonnePhysiqueCreationViewImplConstants) GWT.create(PersonnePhysiqueCreationViewImplConstants.class);

    /** View constants debugId. */
    private static PersonnePhysiqueCreationViewImplDebugIdConstants viewDebugIdConstants =
        (PersonnePhysiqueCreationViewImplDebugIdConstants) GWT.create(PersonnePhysiqueCreationViewImplDebugIdConstants.class);

    private VerticalPanel conteneur;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbCivilite;

    private AideComposant aideslbCivilite;

    private SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdentifiantLibelleProperties;
    
    private SuggestListBoxSingleProperties<IdentifiantLibelleBooleanModel> slbIdentifiantLibelleBooleanProperties;

    private AideComposant aideslbIdentifiantLibelleProperties;

    private DecoratedTextBoxFormat tbNom;

    private AideComposant aidetbNom;

    private DecoratedTextBoxFormat tbPrenom;

    private AideComposant aidetbPrenom;

    private DecoratedCalendrierDateBox cdbDateNaissance;

    private AideComposant aidecdbDateNaissance;

    private DecoratedTextBoxFormat tbTelephone;

    private AideComposant aidetbTelephone;

    private DecoratedTextBoxFormat tbTelephonePortable;

    private AideComposant aidetbTelephonePortable;

    private DecoratedTextBox tbEmail;

    private AideComposant aidetbEmail;

    private DecoratedTextBox tbAdresse;

    private AideComposant aidetbAdresse;

    private DecoratedTextBox tbComplementNom;

    private AideComposant aidetbComplementNom;

    private DecoratedTextBox tbNumeroVoie;

    private AideComposant aidetbNumeroVoie;

    private DecoratedTextBox tbComplementAdresse;

    private AideComposant aidetbComplementAdresse;

    private DecoratedTextBox tbAutresComplements;

    private AideComposant aidetbAutresComplements;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbNatureVoie;

    private AideComposant aideslbNatureVoie;

    private DecoratedSuggestListBoxSingle<CodePostalCommuneModel> slbCodePostal;

    private AideComposant aideslbCodePostal;

    private Label lValueVille;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbNatureTelephone;

    private AideComposant aideslbNatureTelephone;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbNatureTelephonePortable;

    private AideComposant aideslbNatureTelephonePortable;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel> slbProfession;

    private AideComposant aideslbProfession;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> lbConjoint;

    private AideComposant aidelbConjoint;

    private DecoratedButton btnCreer;

    private DecoratedButton btnReduire;

    private DecoratedButton btnAnnuler;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel> slbPays;

    private AideComposant aideslbPays;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> lbNbEnfants;

    private AideComposant aidelbNbEnfants;

    private Image imgFlagPaysTelephone;

    private Image imgFlagPaysTelephonePortable;

    private Long idPaysTelephone;

    private Long idPaysTelephone2;

    private DecoratedTextBox tbCommuneEtranger;

    private AideComposant aidetbCommuneEtranger;

    private DecoratedTextBox tbCodePostalEtranger;

    private AideComposant aidetbCodePostalEtranger;

    private Label lVille;

    private BlocCreationBeneficiaireViewImpl conjointView;

    private List<BlocCreationBeneficiaireViewImpl> listeEnfantsView;

    private VerticalPanel conteneurBlocsEnfant;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    private HorizontalPanel ligneVille;

    private HorizontalPanel ligneVilleEtranger;

    private HorizontalPanel ligneCodePostal;

    private HorizontalPanel ligneCodePostalEtranger;

    /** Panel pour prévenir de la présence d'un doublon. */
    private HorizontalPanel pWarningDoublon;

    private FocusPanel focusPopupPanel;

    private PopupMinimizable minimizablePopup;

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
     * @param isAdmin .
     */
    public PersonnePhysiqueCreationViewImpl(boolean isAdmin) {
        super(viewConstants.popupTitle(), false, false, true);
        iconeErreurChampManager = new IconeErreurChampManager();
        this.isAdmin = isAdmin;
        conteneur = new VerticalPanel();
        conteneur.setSpacing(10);
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        final AideComposant aideView = new AideComposant(
            AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_GLOBAL, isAdmin);
        ajouterAideComposant(aideView);
        conteneur.add(aideView);
        conteneur.setCellHorizontalAlignment(aideView, HasAlignment.ALIGN_RIGHT);
        construireBlocChefFamille();
        construireBlocConjoint();
        construireBlocEnfants();
        construireBlocAdresse();

        final Widget conteneurBoutons = construitreBlocBoutons();

        // on en fait une popup minimisable
        minimizablePopup = new PopupMinimizable(this, viewConstants.popupTitle(), btnReduire);

        focusPopupPanel = new FocusPanel();
        focusPopupPanel.setWidth(AppControllerConstants.POURCENT_100);
        focusPopupPanel.add(conteneur);

        final VerticalPanel conteneurGlobal = new VerticalPanel();
        conteneurGlobal.add(focusPopupPanel);
        conteneurGlobal.add(conteneurBoutons);
        conteneurGlobal.setCellHorizontalAlignment(conteneurBoutons, HasAlignment.ALIGN_CENTER);

        this.setWidget(conteneurGlobal, 0);
        this.addStyleName(SquareResources.INSTANCE.css().popupCreationPersonnePhysique());
    }

    /**
     * Construit le bloc des criteres de recherche.
     */
    private Widget construitreBlocBoutons() {
        btnCreer = new DecoratedButton(viewConstants.creer());
        btnCreer.ensureDebugId(viewDebugIdConstants.debugIdBtnCreer());
        btnReduire = new DecoratedButton(viewConstants.reduire());
        btnReduire.ensureDebugId(viewDebugIdConstants.debugIdBtnReduire());
        btnAnnuler = new DecoratedButton(viewConstants.annuler());
        btnAnnuler.ensureDebugId(viewDebugIdConstants.debugIdBtnAnnuler());

        final HorizontalPanel conteneurBoutons = new HorizontalPanel();
        conteneurBoutons.setSpacing(5);
        conteneurBoutons.add(btnCreer);
        conteneurBoutons.add(btnReduire);
        conteneurBoutons.add(btnAnnuler);
        conteneurBoutons.setCellVerticalAlignment(btnAnnuler, HasAlignment.ALIGN_MIDDLE);
        return conteneurBoutons;
    }

    /**
     * Construit le bloc des criteres de recherche.
     */
    private void construireBlocChefFamille() {
        final Label lCivilite = new Label(viewConstants.civilite(), false);
        final Label lNom = new Label(viewConstants.nom(), false);
        final Label lDateNaissance = new Label(viewConstants.dateNaissance(), false);
        final Label lEmail = new Label(viewConstants.email(), false);
        final Label lTelephone = new Label(viewConstants.telephone(), false);
        final Label lProfession = new Label(viewConstants.profession(), false);

        slbIdentifiantLibelleProperties = new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
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

        slbIdentifiantLibelleBooleanProperties = new SuggestListBoxSingleProperties<IdentifiantLibelleBooleanModel>() {
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

        slbCivilite = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbCivilite.ensureDebugId(viewDebugIdConstants.debugIdSlbCivilite());
        aideslbCivilite = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_CIVILITE, isAdmin);
        ajouterAideComposant(aideslbCivilite);

        tbNom = new DecoratedTextBoxFormat("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        tbNom.ensureDebugId(viewDebugIdConstants.debugIdTbNom());
        aidetbNom = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_NOM, isAdmin);
        ajouterAideComposant(aidetbNom);

        tbPrenom = new DecoratedTextBoxFormat("AAAAAAAAAAAAAAAAAAAAAAAAA");
        tbPrenom.ensureDebugId(viewDebugIdConstants.debugIdTbPrenom());
        aidetbPrenom = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_PRENOM, isAdmin);
        ajouterAideComposant(aidetbPrenom);

        cdbDateNaissance = new DecoratedCalendrierDateBox(true);
        cdbDateNaissance.ensureDebugId(viewDebugIdConstants.debugIdCdbDateNaissance());
        aidecdbDateNaissance = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_DATE_NAISSANCE, isAdmin);
        ajouterAideComposant(aidecdbDateNaissance);
        tbEmail = new DecoratedTextBox();
        tbEmail.setMaxLength(PersonnePhysiqueCreationViewImplConstants.MAX_LENGTH_TB_EMAIL);
        tbEmail.ensureDebugId(viewDebugIdConstants.debugIdTbEmail());
        aidetbEmail = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_EMAIL, isAdmin);
        ajouterAideComposant(aidetbEmail);

        tbTelephone = new DecoratedTextBoxFormat(PersonnePhysiqueCreationViewImplConstants.FORMAT_TELEPHONE_DEFAUT);
        tbTelephone.ensureDebugId(viewDebugIdConstants.debugIdTbTelephone());
        aidetbTelephone = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_TELEPHONE, isAdmin);
        ajouterAideComposant(aidetbTelephone);

        tbTelephonePortable = new DecoratedTextBoxFormat(PersonnePhysiqueCreationViewImplConstants.FORMAT_TELEPHONE_DEFAUT);
        tbTelephonePortable.ensureDebugId(viewDebugIdConstants.debugIdTbTelephonePortable());
        aidetbTelephonePortable = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_TELEPHONE_PORTABLE, isAdmin);
        ajouterAideComposant(aidetbTelephonePortable);

        slbNatureTelephone = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbNatureTelephone.ensureDebugId(viewDebugIdConstants.debugIdSlbNatureTelephone());
        aideslbNatureTelephone = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_NATURE_TELEPHONE, isAdmin);
        ajouterAideComposant(aideslbNatureTelephone);

        slbNatureTelephonePortable = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbNatureTelephonePortable.ensureDebugId(viewDebugIdConstants.debugIdSlbNatureTelephonePortable());
        aideslbNatureTelephonePortable = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_NATURE_TELEPHONE_PORTABLE, isAdmin);
        ajouterAideComposant(aideslbNatureTelephonePortable);

        slbProfession = new DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel>(slbIdentifiantLibelleBooleanProperties);
        slbProfession.ensureDebugId(viewDebugIdConstants.debugIdSlbProfession());
        aideslbProfession = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_PROFESSION, isAdmin);
        ajouterAideComposant(aideslbProfession);

        imgFlagPaysTelephone = new Image(SquareResources.INSTANCE.flagFr());
        imgFlagPaysTelephone.ensureDebugId(viewDebugIdConstants.debugIdImgFlagPaysTelephone());
        imgFlagPaysTelephone.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());
        imgFlagPaysTelephonePortable = new Image(SquareResources.INSTANCE.flagFr());
        imgFlagPaysTelephonePortable.ensureDebugId(viewDebugIdConstants.debugIdImgFlagPaysTelephonePortable());
        imgFlagPaysTelephonePortable.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.titreChefDeFamille());

        final FlexTable flexTable = new FlexTable();
        flexTable.setWidth(AppControllerConstants.POURCENT_100);
        int row = 0;
        final int spacing = 3;

        final HorizontalPanel ligneCivilite = new HorizontalPanel();
        ligneCivilite.setSpacing(spacing);
        ligneCivilite.add(construireBlocIcone(slbCivilite, "PersonneDto.civilite", aideslbCivilite));
        flexTable.setWidget(row, 0, lCivilite);
        flexTable.setWidget(row++, 1, ligneCivilite);

        final HorizontalPanel ligneNom = new HorizontalPanel();
        ligneNom.setSpacing(spacing);
        ligneNom.add(construireBlocIcone(tbNom, "PersonneDto.nom", aidetbNom));
        ligneNom.add(construireBlocIcone(tbPrenom, "PersonneDto.prenom", aidetbPrenom));
        flexTable.setWidget(row, 0, lNom);
        flexTable.setWidget(row++, 1, ligneNom);

        final HorizontalPanel ligneDateNaissance = new HorizontalPanel();
        ligneDateNaissance.setSpacing(spacing);
        ligneDateNaissance.add(construireBlocIcone(cdbDateNaissance, "PersonneDto.dateNaissance", aidecdbDateNaissance));
        flexTable.setWidget(row, 0, lDateNaissance);
        flexTable.setWidget(row++, 1, ligneDateNaissance);

        pWarningDoublon = new HorizontalPanel();
        pWarningDoublon.setVisible(false);
        pWarningDoublon.setSpacing(2);
        final Image imgWarning = new Image(SmatisResources.INSTANCE.imgWarning());
        pWarningDoublon.add(imgWarning);
        final Label lWarningDoublon = new Label(viewConstants.warningDoublons());
        lWarningDoublon.ensureDebugId(viewDebugIdConstants.debugIdLWarningDoublon());
        lWarningDoublon.addStyleName(SquareResources.INSTANCE.css().labelReclamation());
        pWarningDoublon.add(lWarningDoublon);
        flexTable.setWidget(row, 0, pWarningDoublon);
        flexTable.getFlexCellFormatter().setColSpan(row++, 0, 2);

        final HorizontalPanel ligneEmail = new HorizontalPanel();
        ligneEmail.setSpacing(spacing);
        ligneEmail.add(construireBlocIcone(tbEmail, "EmailDto.adresse", aidetbEmail));
        flexTable.setWidget(row, 0, lEmail);
        flexTable.setWidget(row++, 1, ligneEmail);

        final HorizontalPanel ligneTelephone = new HorizontalPanel();
        ligneTelephone.setSpacing(spacing);
        ligneTelephone.add(construireBlocIcone(tbTelephone, "TelephoneDto.numero.0", aidetbTelephone));
        ligneTelephone.add(construireBlocIcone(slbNatureTelephone, "TelephoneDto.nature.0", aideslbNatureTelephone));
        ligneTelephone.add(imgFlagPaysTelephone);
        flexTable.setWidget(row, 0, lTelephone);
        flexTable.setWidget(row++, 1, ligneTelephone);

        final HorizontalPanel ligneTelephonePortable = new HorizontalPanel();
        ligneTelephonePortable.setSpacing(spacing);
        ligneTelephonePortable.add(construireBlocIcone(tbTelephonePortable, "TelephoneDto.numero.1", aidetbTelephonePortable));
        ligneTelephonePortable.add(construireBlocIcone(slbNatureTelephonePortable, "TelephoneDto.nature.1", aideslbNatureTelephonePortable));
        ligneTelephonePortable.add(imgFlagPaysTelephonePortable);
        flexTable.setWidget(row, 0, new Label(""));
        flexTable.setWidget(row++, 1, ligneTelephonePortable);

        final HorizontalPanel ligneProfession = new HorizontalPanel();
        ligneProfession.setSpacing(spacing);
        ligneProfession.add(construireBlocIcone(slbProfession, "PersonneDto.profession", aideslbProfession));
        flexTable.setWidget(row, 0, lProfession);
        flexTable.setWidget(row++, 1, ligneProfession);

        fieldSetPanel.add(flexTable);
        conteneur.add(fieldSetPanel);
    }

    /**
     * Construit le bloc du conjoint.
     */
    private void construireBlocConjoint() {
        final SuggestListBoxSingleProperties<IdentifiantLibelleGwt> propertiesConjoint = new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
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
                return new String[] {row.getLibelle()};
            }
        };
        lbConjoint = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(propertiesConjoint);
        lbConjoint.ensureDebugId(viewDebugIdConstants.debugIdLbConjoint());
        aidelbConjoint = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_CONJOINT, isAdmin);
        ajouterAideComposant(aidelbConjoint);
        HorizontalPanel panellbConjoint = new HorizontalPanel();
        panellbConjoint.setSpacing(5);
        panellbConjoint.add(lbConjoint);
        panellbConjoint.add(aidelbConjoint);

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.titreConjoint());
        final VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing(5);
        verticalPanel.setWidth(AppControllerConstants.POURCENT_100);

        verticalPanel.add(panellbConjoint);

        conjointView = new BlocCreationBeneficiaireViewImpl(iconeErreurChampManager, viewConstants, 0);
        conjointView.ensureDebugId(viewDebugIdConstants.debugIdConjointView());
        conjointView.setVisible(false);
        verticalPanel.add(conjointView);
        fieldSetPanel.add(verticalPanel);
        conteneur.add(fieldSetPanel);
    }

    /**
     * Construit le bloc des enfants.
     */
    private void construireBlocEnfants() {
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
                return new String[] {row.getLibelle()};
            }
        };
        lbNbEnfants = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(propertiesLbEnfant);
        lbNbEnfants.ensureDebugId(viewDebugIdConstants.debugIdLbNbEnfants());
        aidelbNbEnfants = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_NB_ENFANTS, isAdmin);
        ajouterAideComposant(aidelbNbEnfants);
        HorizontalPanel panellbNbEnfants = new HorizontalPanel();
        panellbNbEnfants.setSpacing(5);
        panellbNbEnfants.add(lbNbEnfants);
        panellbNbEnfants.add(aidelbNbEnfants);

        listeEnfantsView = new ArrayList<BlocCreationBeneficiaireViewImpl>();

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.titreEnfants());
        conteneurBlocsEnfant = new VerticalPanel();
        conteneurBlocsEnfant.setSpacing(5);
        conteneurBlocsEnfant.setWidth(AppControllerConstants.POURCENT_100);

        conteneurBlocsEnfant.add(panellbNbEnfants);

        fieldSetPanel.add(conteneurBlocsEnfant);
        conteneur.add(fieldSetPanel);
    }

    /**
     * Ajoute un bloc enfant.
     */
    private BlocCreationBeneficiaireViewImpl ajouterBlocEnfant() {
        final BlocCreationBeneficiaireViewImpl blocEnfants =
            new BlocCreationBeneficiaireViewImpl(iconeErreurChampManager, viewConstants, listeEnfantsView.size() + 1);
        blocEnfants.ensureDebugId(viewDebugIdConstants.debugIdBlocEnfants());
        listeEnfantsView.add(blocEnfants);
        blocEnfants.addStyleName(SquareResources.INSTANCE.css().blocCreationEnfant());
        conteneurBlocsEnfant.add(blocEnfants);
        return blocEnfants;
    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage.
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
     * Supprime un bloc enfant.
     */
    private void supprimeBlocEnfant() {
        final BlocCreationBeneficiaireViewImpl bloc = listeEnfantsView.get(listeEnfantsView.size() - 1);
        conteneurBlocsEnfant.remove(bloc);
        listeEnfantsView.remove(bloc);
    }

    /**
     * Construit le bloc de l'adresse.
     */
    private void construireBlocAdresse() {
        final Label lNumeroVoie = new Label(viewConstants.numeroVoie(), false);
        final Label lNatureVoie = new Label(viewConstants.natureVoie(), false);
        final Label lComplementNom = new Label(viewConstants.complementNom(), false);
        final Label lAdresse = new Label(viewConstants.adresse(), false);
        final Label lComplementAdresse = new Label(viewConstants.complementAdresse(), false);
        final Label lAutresComplements = new Label(viewConstants.autresComplements(), false);
        final Label lCodePostal = new Label(viewConstants.codePostal(), false);
        lVille = new Label(viewConstants.ville(), false);
        final Label lPays = new Label(viewConstants.pays(), false);

        tbNumeroVoie = new DecoratedTextBox();
        tbNumeroVoie.setMaxLength(5);
        tbNumeroVoie.ensureDebugId(viewDebugIdConstants.debugIdTbNumeroVoie());
        aidetbNumeroVoie = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_NUMERO_VOIE, isAdmin);
        ajouterAideComposant(aidetbNumeroVoie);

        slbNatureVoie = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbNatureVoie.ensureDebugId(viewDebugIdConstants.debugIdSlbNatureVoie());
        aideslbNatureVoie = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_NATURE_VOIE, isAdmin);
        ajouterAideComposant(aideslbNatureVoie);

        tbComplementNom = new DecoratedTextBox();
        tbComplementNom.setMaxLength(38);
        tbComplementNom.ensureDebugId(viewDebugIdConstants.debugIdTbComplementNom());
        aidetbComplementNom = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_COMPLEMENT_NOM, isAdmin);
        ajouterAideComposant(aidetbComplementNom);

        tbAdresse = new DecoratedTextBox();
        tbAdresse.setMaxLength(38);
        tbAdresse.ensureDebugId(viewDebugIdConstants.debugIdTbAdresse());
        aidetbAdresse = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_ADRESSE, isAdmin);
        ajouterAideComposant(aidetbAdresse);

        tbComplementAdresse = new DecoratedTextBox();
        tbComplementAdresse.setMaxLength(38);
        tbComplementAdresse.ensureDebugId(viewDebugIdConstants.debugIdTbComplementAdresse());
        aidetbComplementAdresse = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_COMPLEMENT_ADRESSE, isAdmin);
        ajouterAideComposant(aidetbComplementAdresse);

        tbAutresComplements = new DecoratedTextBox();
        tbAutresComplements.setMaxLength(38);
        tbAutresComplements.ensureDebugId(viewDebugIdConstants.debugIdTbAutresComplements());
        aidetbAutresComplements = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_AUTRES_COMPLEMENTS, isAdmin);
        ajouterAideComposant(aidetbAutresComplements);

        final SuggestListBoxSingleProperties<CodePostalCommuneModel> properties = new SuggestListBoxSingleProperties<CodePostalCommuneModel>() {
            @Override
            public String getSelectSuggestRenderer(CodePostalCommuneModel row) {
                return row == null || row.getCodePostal() == null ? "" : row.getCodePostal().getLibelle();
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
        };
        slbCodePostal = new DecoratedSuggestListBoxSingle<CodePostalCommuneModel>(properties);
        slbCodePostal.setMaxLenght(5);
        slbCodePostal.ensureDebugId(viewDebugIdConstants.debugIdSlbCodePostal());
        aideslbCodePostal = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_CODE_POSTAL, isAdmin);
        ajouterAideComposant(aideslbCodePostal);

        slbPays = new DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel>(slbIdentifiantLibelleBooleanProperties);
        slbPays.ensureDebugId(viewDebugIdConstants.debugIdSlbPays());
        aideslbPays = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_PAYS, isAdmin);
        ajouterAideComposant(aideslbPays);

        lValueVille = new Label();
        lValueVille.ensureDebugId(viewDebugIdConstants.debugIdLValueVille());
        tbCodePostalEtranger = new DecoratedTextBox();
        tbCodePostalEtranger.setMaxLength(10);
        tbCodePostalEtranger.ensureDebugId(viewDebugIdConstants.debugIdTbCodePostalEtranger());
        aidetbCodePostalEtranger = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_CODE_POSTAL_ETRANGER, isAdmin);
        ajouterAideComposant(aidetbCodePostalEtranger);

        tbCommuneEtranger = new DecoratedTextBox();
        tbCommuneEtranger.setMaxLength(38);
        tbCommuneEtranger.ensureDebugId(viewDebugIdConstants.debugIdTbCommuneEtranger());
        aidetbCommuneEtranger = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_CREATION_COMMUNE_ETRANGER, isAdmin);
        ajouterAideComposant(aidetbCommuneEtranger);

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.titreAdresse());
        final VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing(5);
        verticalPanel.setWidth(AppControllerConstants.POURCENT_100);

        final FlexTable flexTable = new FlexTable();
        int row = 0;
        final int spacing = 3;

        final HorizontalPanel ligneComplementNom = new HorizontalPanel();
        ligneComplementNom.setSpacing(spacing);
        ligneComplementNom.add(construireBlocIcone(tbComplementNom, "AdresseDto.complementNom", aidetbComplementNom));
        flexTable.setWidget(row, 0, lComplementNom);
        flexTable.setWidget(row++, 1, ligneComplementNom);

        final HorizontalPanel ligneNumeroVoie = new HorizontalPanel();
        ligneNumeroVoie.setSpacing(spacing);
        ligneNumeroVoie.add(construireBlocIcone(tbNumeroVoie, "AdresseDto.numVoie", aidetbNumeroVoie));
        ligneNumeroVoie.add(lNatureVoie);
        ligneNumeroVoie.add(construireBlocIcone(slbNatureVoie, "AdresseDto.typeVoie", aideslbNatureVoie));
        ligneNumeroVoie.setCellVerticalAlignment(lNatureVoie, HasAlignment.ALIGN_MIDDLE);
        flexTable.setWidget(row, 0, lNumeroVoie);
        flexTable.setWidget(row++, 1, ligneNumeroVoie);

        final HorizontalPanel ligneAdresse = new HorizontalPanel();
        ligneAdresse.setSpacing(spacing);
        ligneAdresse.add(construireBlocIcone(tbAdresse, "AdresseDto.nomVoie", aidetbAdresse));
        flexTable.setWidget(row, 0, lAdresse);
        flexTable.setWidget(row++, 1, ligneAdresse);

        final HorizontalPanel ligneComplementAdresse = new HorizontalPanel();
        ligneComplementAdresse.setSpacing(spacing);
        ligneComplementAdresse.add(construireBlocIcone(tbComplementAdresse, "AdresseDto.complementAdresse", aidetbComplementAdresse));
        flexTable.setWidget(row, 0, lComplementAdresse);
        flexTable.setWidget(row++, 1, ligneComplementAdresse);

        final HorizontalPanel ligneAutresComplements = new HorizontalPanel();
        ligneAutresComplements.setSpacing(spacing);
        ligneAutresComplements.add(construireBlocIcone(tbAutresComplements, "AdresseDto.autresComplements", aidetbAutresComplements));
        flexTable.setWidget(row, 0, lAutresComplements);
        flexTable.setWidget(row++, 1, ligneAutresComplements);

        final HorizontalPanel lignePays = new HorizontalPanel();
        lignePays.setSpacing(spacing);
        lignePays.add(construireBlocIcone(slbPays, "AdresseDto.pays", aideslbPays));
        flexTable.setWidget(row, 0, lPays);
        flexTable.setWidget(row++, 1, lignePays);

        final VerticalPanel blocCodePostal = new VerticalPanel();

        ligneCodePostal = new HorizontalPanel();
        ligneCodePostal.setSpacing(spacing);
        ligneCodePostal.add(construireBlocIcone(slbCodePostal, "AdresseDto.codePostal", aideslbCodePostal));
        blocCodePostal.add(ligneCodePostal);

        ligneCodePostalEtranger = new HorizontalPanel();
        ligneCodePostalEtranger.setSpacing(spacing);
        ligneCodePostalEtranger.add(construireBlocIcone(tbCodePostalEtranger, "AdresseDto.codePostalEtranger", aidetbCodePostalEtranger));
        blocCodePostal.add(ligneCodePostalEtranger);

        flexTable.setWidget(row, 0, lCodePostal);
        flexTable.setWidget(row++, 1, blocCodePostal);

        final VerticalPanel blocVille = new VerticalPanel();

        ligneVille = new HorizontalPanel();
        ligneVille.setSpacing(spacing);
        ligneVille.add(lValueVille);
        blocVille.add(ligneVille);

        ligneVilleEtranger = new HorizontalPanel();
        ligneVilleEtranger.setSpacing(spacing);
        ligneVilleEtranger.add(construireBlocIcone(tbCommuneEtranger, "AdresseDto.communeEtranger", aidetbCommuneEtranger));
        blocVille.add(ligneVilleEtranger);

        flexTable.setWidget(row, 0, lVille);
        flexTable.setWidget(row++, 1, blocVille);

        verticalPanel.add(flexTable);
        fieldSetPanel.add(verticalPanel);
        conteneur.add(fieldSetPanel);
    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        panel.add(icone);
        panel.setCellVerticalAlignment(icone, HasAlignment.ALIGN_MIDDLE);
        return panel;
    }

    @Override
    public Widget asWidget() {
        return null;
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateNaissance() {
        return cdbDateNaissance;
    }

    @Override
    public HasValue<String> getTbNom() {
        return tbNom;
    }

    @Override
    public HasValue<String> getTbPrenom() {
        return tbPrenom;
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
    }

    @Override
    public DecoratedTextBox getTbAdresse() {
        return tbAdresse;
    }

    @Override
    public DecoratedTextBox getTbEmail() {
        return tbEmail;
    }

    @Override
    public DecoratedTextBox getTbNumeroVoie() {
        return tbNumeroVoie;
    }

    @Override
    public DecoratedTextBoxFormat getTbTelephone() {
        return tbTelephone;
    }

    @Override
    public DecoratedTextBoxFormat getTbTelephonePortable() {
        return tbTelephonePortable;
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
    public HasClickHandlers getBtnCreer() {
        return btnCreer;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLbNbEnfants() {
        return lbNbEnfants;
    }

    @Override
    public HasClickHandlers getLienAnnuler() {
        return btnAnnuler;
    }

    @Override
    public DecoratedTextBox getTbAutresComplements() {
        return tbAutresComplements;
    }

    @Override
    public DecoratedTextBox getTbComplementAdresse() {
        return tbComplementAdresse;
    }

    @Override
    public DecoratedTextBox getTbComplementNom() {
        return tbComplementNom;
    }

    @Override
    public void hidePopup() {
        this.hide();
    }

    @Override
    public void showPopup() {
        this.show();
        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                slbCivilite.setFocus(true);
            }
        });
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbCivilite() {
        return slbCivilite;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNatureVoie() {
        return slbNatureVoie;
    }

    @Override
    public Label getLValueVille() {
        return lValueVille;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNatureTelephone() {
        return slbNatureTelephone;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNatureTelephonePortable() {
        return slbNatureTelephonePortable;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleBooleanModel> getSlbProfession() {
        return slbProfession;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleBooleanModel> getSlbPays() {
        return slbPays;
    }

    @Override
    public BlocCreationBeneficiaireViewImpl getConjointView() {
        return conjointView;
    }

    @Override
    public List<BlocCreationBeneficiaireViewImpl> getListeEnfantsView() {
        return listeEnfantsView;
    }

    @Override
    public List<BlocCreationBeneficiaireView> afficherBlocsEnfants(Integer nbEnfants) {
        final List<BlocCreationBeneficiaireView> listeNouveauxBlocs = new ArrayList<BlocCreationBeneficiaireView>();
        if (listeEnfantsView.size() > nbEnfants) {
            // on retire des blocs
            while (listeEnfantsView.size() != nbEnfants) {
                supprimeBlocEnfant();
            }
        } else {
            // on ajoute des blocs
            while (listeEnfantsView.size() != nbEnfants) {
                listeNouveauxBlocs.add(ajouterBlocEnfant());
            }
        }
        return listeNouveauxBlocs;
    }

    @Override
    public PersonnePhysiqueCreationViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stop();
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getLbConjoint() {
        return lbConjoint;
    }

    @Override
    public void afficherBlocConjoint(Boolean affiche) {
        conjointView.setVisible(affiche);
    }

    @Override
    public HasValue<String> getTbCodePostalEtranger() {
        return tbCodePostalEtranger;
    }

    @Override
    public HasValue<String> getTbCommuneEtranger() {
        return tbCommuneEtranger;
    }

    @Override
    public void afficheBlocCoordonneesFrance(Boolean affiche) {
        ligneVille.setVisible(affiche);
        ligneCodePostal.setVisible(affiche);
        ligneVilleEtranger.setVisible(!affiche);
        ligneCodePostalEtranger.setVisible(!affiche);
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getLbConjointValue() {
        return lbConjoint;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getLbNbEnfantsValue() {
        return lbNbEnfants;
    }

    @Override
    public HasSuggestListBoxHandler<CodePostalCommuneModel> getSlbCodePostal() {
        return slbCodePostal;
    }

    @Override
    public HasValue<CodePostalCommuneModel> getSlbCodePostalValue() {
        return slbCodePostal;
    }

    @Override
    public void nettoyerCodePostal() {
        slbCodePostal.clean();
    }

    @Override
    public HasValue<IdentifiantLibelleBooleanModel> getSlbPaysValue() {
        return slbPays;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbCiviliteValue() {
        return slbCivilite;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbNatureTelephoneValue() {
        return slbNatureTelephone;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbNatureVoieValue() {
        return slbNatureVoie;
    }

    @Override
    public HasValue<IdentifiantLibelleBooleanModel> getSlbProfessionValue() {
        return slbProfession;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbNatureTelephonePortableValue() {
        return slbNatureTelephonePortable;
    }

    @Override
    public Long getIdPaysTelephone() {
        return idPaysTelephone;
    }

    @Override
    public Long getIdPaysTelephone2() {
        return idPaysTelephone2;
    }

    @Override
    public void setIdPaysTelephone(Long id) {
        this.idPaysTelephone = id;
    }

    @Override
    public void setIdPaysTelephone2(Long id) {
        this.idPaysTelephone2 = id;
    }

    @Override
    public HasClickHandlers getImgFlagPaysTelephone() {
        return imgFlagPaysTelephone;
    }

    @Override
    public HasClickHandlers getImgFlagPaysTelephone2() {
        return imgFlagPaysTelephonePortable;
    }

    @Override
    public void chargerImagePaysTelephone(String url, String title) {
        imgFlagPaysTelephone.setUrl(url);
        imgFlagPaysTelephone.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());
        imgFlagPaysTelephone.setTitle(title);
    }

    @Override
    public void chargerImagePaysTelephonePortable(String url, String title) {
        imgFlagPaysTelephonePortable.setUrl(url);
        imgFlagPaysTelephonePortable.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());
        imgFlagPaysTelephonePortable.setTitle(title);
    }

    @Override
    public void initTbTelephone(String format) {
        tbTelephone.setFormat(format);
        tbTelephone.setValue("");
    }

    @Override
    public void initTbTelephonePortable(String format) {
        tbTelephonePortable.setFormat(format);
        tbTelephonePortable.setValue("");
    }

    @Override
    public void setFocusCivilite() {
        slbCivilite.setFocus(true);
    }

    @Override
    public void afficherWarningDoublons() {
        pWarningDoublon.setVisible(true);
    }

    @Override
    public void masquerWarningDoublons() {
        pWarningDoublon.setVisible(false);
    }

    @Override
    public HasAllKeyHandlers getAllKeyHandlersFocusPanel() {
        return focusPopupPanel;
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

}

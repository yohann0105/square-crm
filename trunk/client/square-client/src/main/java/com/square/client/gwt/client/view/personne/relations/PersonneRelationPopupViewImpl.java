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
package com.square.client.gwt.client.view.personne.relations;

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
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.composant.PanelErreursIntegrite;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.CaisseSimpleModel;
import com.square.client.gwt.client.model.IdentifiantEidLibelleModel;
import com.square.client.gwt.client.model.ItemValueModel;
import com.square.client.gwt.client.model.PersonneMoralCriteresRechercheModel;
import com.square.client.gwt.client.model.PersonneMoraleRechercheModel;
import com.square.client.gwt.client.presenter.personne.PersonneRelationsPopupPresenter.PersonneRelationPopupView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.DecoratedTextBoxFormat;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.popups.minimizable.IsMinimizable;
import com.square.composants.graphiques.lib.client.popups.minimizable.PopupMinimizable;

/**
 * Implémentation de la vue de la popup de création d'une relation.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class PersonneRelationPopupViewImpl extends Popup implements PersonneRelationPopupView {

    private static final String LARGEUR_COL_LIBELLE = "120px";

    /** Date de Naissance de la personne cible. */
    private DecoratedCalendrierDateBox cdbDateNaissance;

    private AideComposant aidecdbDateNaissance;

    /** Types de relations. */
    private DecoratedSuggestListBoxSingle<ItemValueModel> slbTypeRelation;

    private AideComposant aideslbTypeRelation;

    /** Civilité de la personne cible. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbCivilite;

    private AideComposant aideslbCivilite;

    /** Nom de la personne cible. */
    private DecoratedTextBoxFormat tbNom;

    private AideComposant aidetbNom;

    /** Prénom de la personne cible. */
    private DecoratedTextBoxFormat tbPrenom;

    private AideComposant aidetbPrenom;

    /** Label du téléphone. */
    private Label lTelephone;

    /** Téléphone de la cible. */
    private DecoratedTextBoxFormat tbTelephone;

    private AideComposant aidetbTelephone;

    /** Nature du téléphone. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbNatureTelephone;

    private AideComposant aideslbNatureTelephone;

    private Image imgFlagPaysTelephone;

    private Long idPaysTelephone;

    /** Bouton enregistrer pour la création d'une relation. */
    private DecoratedButton btEnregistrerCreation;

    /** Popup de création de relation. */
    private Popup popupCreation;

    /** Panel global. */
    private VerticalPanel panelGlobal;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    private HandlerManager erreursPersonneSourceManager;

    /** Les constantes de la page. */
    private PersonneRelationsViewImplConstants constants;

    /** Les constantes de debugId de la page. */
    private PersonneRelationsViewImplDebugIdConstants viewDebugIdConstants;

    /** Le lien annuler. */
    private DecoratedButton btAnnuler;

    /** Raison sociale. */
    private DecoratedTextBox tbRaisonSocialePm;

    private AideComposant aidetbRaisonSocialePm;

    /** numero entreprise. */
    private DecoratedTextBox tbNumEntreprisePm;

    private AideComposant aidetbNumEntreprisePm;

    /** recherche de pm. */
    private DecoratedButton btnRechercherPm;

    /** tableau pagine recherche de PM. */
    private RemotePagingTable<PersonneMoraleRechercheModel, PersonneMoralCriteresRechercheModel> remotePagingTablePm;

    /** handler sur le tableau de recherche de PM. */
    private HandlerManager remotePagingHandlerManagerPm;

    /** Type de personne. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbTypePersonne;

    private AideComposant aideslbTypePersonne;

    /** personne physique. **/
    private CaptionPanel captionPersonnePhysique;

    /** personne morale. **/
    private CaptionPanel captionPersonneMorale;

    /** Caption type personne. **/
    private CaptionPanel captionTypePersonne;

    private FocusPanel focusPanel;

    private HorizontalPanel ligneTelephone;

    private DecoratedButton btnReduire;

    private PopupMinimizable minimizablePopup;

    /** Mode de connexion. */
    private boolean isAdmin;

    /** Liste de composants d'aide de la vue. */
    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    /** Liste des events handlers des composants d'aides. */
    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();
    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    /** Panel pour les infos santé. */
    //private CaptionPanel captionInfosSante;

    /** Liste déroulante qui contient les références de Sécurité Sociale des referents. */
    //private DecoratedSuggestListBoxSingle<IdentifiantEidLibelleModel> lbSsReferents;

    /** DecoratedTextBox pour le numéro de sécurité sociale. */
    //private DecoratedTextBox tbNumSs;

    /** DecoratedTextBox pour le clé de sécurité sociale. */
    //private DecoratedTextBox tbCleSs;

    /** ListBox des régimes. */
    //private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> lbRegime;

    /** ListBox des caisses. */
    //private DecoratedSuggestListBoxSingle<CaisseSimpleModel> lbCaisse;

    /** Aide pour le n° RO. */
    //private AideComposant aidetbNRO;

    /** Aide pour le régime. */
    //private AideComposant aidelbRegime;

    /** Aide pour la caisse. */
    //private AideComposant aidelbCaisse;

    /**
     * Constructeur.
     * @param eventBus eventBus
     * @param isAdmin .
     */
    public PersonneRelationPopupViewImpl(boolean isAdmin) {
        super(null, false, false, true);
        iconeErreurChampManager = new IconeErreurChampManager();
        erreursPersonneSourceManager = new HandlerManager(null);
        constants = (PersonneRelationsViewImplConstants) GWT.create(PersonneRelationsViewImplConstants.class);
        viewDebugIdConstants = (PersonneRelationsViewImplDebugIdConstants) GWT.create(PersonneRelationsViewImplDebugIdConstants.class);
        this.isAdmin = isAdmin;
        construirePopup();
    }

    /**
     * Construction de la popup ajout sur personne morale.
     */
    private void construirePopup() {
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

        // TYPE DE PERSONNE
        slbTypePersonne = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbTypePersonne.ensureDebugId(viewDebugIdConstants.debugIdLbTypePersonne());
        aideslbTypePersonne = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_TYPE_PERSONNE, isAdmin);
        ajouterAideComposant(aideslbTypePersonne);
        final HorizontalPanel panelslbTypePersonne = new HorizontalPanel();
        panelslbTypePersonne.add(slbTypePersonne);
        panelslbTypePersonne.add(aideslbTypePersonne);
        panelslbTypePersonne.setSpacing(5);
        final FlexTable ftTypePersonne = new FlexTable();
        ftTypePersonne.setWidth(AppControllerConstants.POURCENT_100);
        ftTypePersonne.setCellSpacing(5);
        ftTypePersonne.setWidget(0, 0, new Label(constants.lbtypePersonne()));
        ftTypePersonne.setWidget(0, 1, panelslbTypePersonne);
        ftTypePersonne.getColumnFormatter().setWidth(0, LARGEUR_COL_LIBELLE);
        captionTypePersonne = new CaptionPanel(constants.lbCaptionTypePersonne());
        captionTypePersonne.add(ftTypePersonne);

        final CaptionPanel captionType = new CaptionPanel(constants.lbCaptionReseau());
        final VerticalPanel panelType = new VerticalPanel();
        panelType.setSpacing(3);

        // TYPE DE RELATION
        final SuggestListBoxSingleProperties<ItemValueModel> properties = new SuggestListBoxSingleProperties<ItemValueModel>() {
            @Override
            public String getSelectSuggestRenderer(ItemValueModel row) {
                return row == null ? "" : row.getValue();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(ItemValueModel row) {
                return new String[] {row == null ? "" : row.getValue()};
            }
        };

        slbTypeRelation = new DecoratedSuggestListBoxSingle<ItemValueModel>(properties);
        slbTypeRelation.ensureDebugId(viewDebugIdConstants.debugIdSlbTypeRelation());
        aideslbTypeRelation = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_TYPE_RELATION, isAdmin);
        ajouterAideComposant(aideslbTypeRelation);

        final FlexTable ftTypeRelation = new FlexTable();
        ftTypeRelation.setWidth(AppControllerConstants.POURCENT_100);
        ftTypeRelation.setCellSpacing(5);
        ftTypeRelation.setWidget(0, 0, new Label(constants.typeRelation()));
        ftTypeRelation.setWidget(0, 1, construireBlocIcone(slbTypeRelation, "RelationDto.type.libelle", aideslbTypeRelation));
        ftTypeRelation.getColumnFormatter().setWidth(0, LARGEUR_COL_LIBELLE);
        captionType.add(ftTypeRelation);

        // PANEL PERSONNE MORALE
        captionPersonneMorale = new CaptionPanel(constants.lbCaptionPersonneMorale());
        tbRaisonSocialePm = new DecoratedTextBox();
        tbRaisonSocialePm.ensureDebugId(viewDebugIdConstants.debugIdTbNumEntreprisePm());
        aidetbRaisonSocialePm = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_RAISON_SOCIALE, isAdmin);
        ajouterAideComposant(aidetbRaisonSocialePm);
        final HorizontalPanel paneltbRaisonSocialePm = new HorizontalPanel();
        paneltbRaisonSocialePm.add(tbRaisonSocialePm);
        paneltbRaisonSocialePm.add(aidetbRaisonSocialePm);
        paneltbRaisonSocialePm.setSpacing(5);

        tbNumEntreprisePm = new DecoratedTextBox();
        tbNumEntreprisePm.ensureDebugId(viewDebugIdConstants.debugIdTbNumEntreprisePm());
        aidetbNumEntreprisePm = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_NUM_ENTREPRISE, isAdmin);
        ajouterAideComposant(aidetbNumEntreprisePm);
        final HorizontalPanel paneltbNumEntreprisePm = new HorizontalPanel();
        paneltbNumEntreprisePm.add(tbNumEntreprisePm);
        paneltbNumEntreprisePm.add(aidetbNumEntreprisePm);
        paneltbNumEntreprisePm.setSpacing(5);

        btnRechercherPm = new DecoratedButton(constants.rechercher());
        btnRechercherPm.ensureDebugId(viewDebugIdConstants.debugIdBtnRechercherPm());

        remotePagingTablePm = new RemotePagingTable<PersonneMoraleRechercheModel, PersonneMoralCriteresRechercheModel>(10, true) {
            @Override
            public void setDataProvider(RemotePagingCriteriasGwt<PersonneMoralCriteresRechercheModel> params,
                AsyncCallback<RemotePagingResultsGwt<PersonneMoraleRechercheModel>> callback) {
                remotePagingHandlerManagerPm.fireEvent(new SetDataProviderEvent<PersonneMoralCriteresRechercheModel, PersonneMoraleRechercheModel>(params,
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
                return new Column[] {new Column(constants.headerNumeroEntreprise(), constants.fieldNumeroEntreprise()),
                    new Column(constants.headerRaisonSociale(), constants.fieldRaisonSociale()), new Column(constants.headerNature(), constants.fieldNature())};
            }

            @Override
            public void setRow(int row, PersonneMoraleRechercheModel personneMorale) {
                setWidget(row, 0, new Label(personneMorale.getNumeroEntreprise()));
                setWidget(row, 1, new Label(personneMorale.getRaisonSociale()));
                setWidget(row, 2, new Label(personneMorale.getNature() != null ? personneMorale.getNature().getLibelle() : ""));
            }

            @Override
            public void setCellClicked(PersonneMoraleRechercheModel objet) {
                remotePagingHandlerManagerPm.fireEvent(new SetCellClickedEvent<PersonneMoraleRechercheModel>(objet));
            }

        };
        remotePagingTablePm.ensureDebugId(viewDebugIdConstants.debugIdRemotePagingTablePm());
        remotePagingHandlerManagerPm = new HandlerManager(remotePagingTablePm);
        remotePagingTablePm.setWidth(AppControllerConstants.POURCENT_100);

        final FlexTable ftRecherchePM = new FlexTable();
        ftRecherchePM.setWidth(AppControllerConstants.POURCENT_100);
        ftRecherchePM.setCellSpacing(5);
        ftRecherchePM.setWidget(0, 0, new Label(constants.raisonSociale()));
        ftRecherchePM.setWidget(0, 1, paneltbRaisonSocialePm);
        ftRecherchePM.setWidget(1, 0, new Label(constants.numEntreprise()));
        ftRecherchePM.setWidget(1, 1, paneltbNumEntreprisePm);
        ftRecherchePM.getColumnFormatter().setWidth(0, LARGEUR_COL_LIBELLE);
        ftRecherchePM.setWidget(2, 0, btnRechercherPm);
        ftRecherchePM.getFlexCellFormatter().setColSpan(2, 0, 2);
        ftRecherchePM.getCellFormatter().setHorizontalAlignment(2, 0, HasAlignment.ALIGN_CENTER);
        ftRecherchePM.setWidget(3, 0, remotePagingTablePm);
        ftRecherchePM.getFlexCellFormatter().setColSpan(3, 0, 2);
        captionPersonneMorale.add(ftRecherchePM);

        // PERSONNE PHYSIQUE
        final Label lCivilite = new Label(constants.civilite());
        final Label lNom = new Label(constants.nom());
        final Label lPrenom = new Label(constants.prenom());
        final Label lDate = new Label(constants.dateNaissance());
        lTelephone = new Label(constants.telephone());
        captionPersonnePhysique = new CaptionPanel(constants.lbCaptionPersonnePhysique());
        slbCivilite = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbCivilite.ensureDebugId(viewDebugIdConstants.debugIdSlbCivilite());
        aideslbCivilite = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_CIVILITE, isAdmin);
        ajouterAideComposant(aideslbCivilite);

        tbNom = new DecoratedTextBoxFormat("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        tbNom.ensureDebugId(viewDebugIdConstants.debugIdTbNom());
        aidetbNom = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_NOM, isAdmin);
        ajouterAideComposant(aidetbNom);

        tbPrenom = new DecoratedTextBoxFormat("AAAAAAAAAAAAAAAAAAAAAAAAA");
        tbPrenom.ensureDebugId(viewDebugIdConstants.debugIdTbPrenom());
        aidetbPrenom = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_PRENOM, isAdmin);
        ajouterAideComposant(aidetbPrenom);

        cdbDateNaissance = new DecoratedCalendrierDateBox(true);
        cdbDateNaissance.ensureDebugId(viewDebugIdConstants.debugIdCdbDateNaissance());
        aidecdbDateNaissance = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_DATE_NAISSANCE, isAdmin);
        ajouterAideComposant(aidecdbDateNaissance);

        tbTelephone = new DecoratedTextBoxFormat("NN NN NN NN NN");
        tbTelephone.ensureDebugId(viewDebugIdConstants.debugIdTbTelephone());
        aidetbTelephone = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_TELEHONE, isAdmin);
        ajouterAideComposant(aidetbTelephone);

        slbNatureTelephone = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbNatureTelephone.ensureDebugId(viewDebugIdConstants.debugIdSlbNatureTelephone());
        aideslbNatureTelephone = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_NATURE_TELEPHONE, isAdmin);
        ajouterAideComposant(aideslbNatureTelephone);

        imgFlagPaysTelephone = new Image(SquareResources.INSTANCE.flagFr());
        imgFlagPaysTelephone.ensureDebugId(viewDebugIdConstants.debugIdImgFlagPaysTelephone());
        imgFlagPaysTelephone.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());
        ligneTelephone = new HorizontalPanel();
        ligneTelephone.setSpacing(3);
        ligneTelephone.add(construireBlocIcone(tbTelephone, "TelephoneDto.numero.0", aidetbTelephone));
        ligneTelephone.add(construireBlocIcone(slbNatureTelephone, "TelephoneDto.nature.0", aideslbNatureTelephone));
        ligneTelephone.add(imgFlagPaysTelephone);

        final FlexTable ftPersonnePhysique = new FlexTable();
        ftPersonnePhysique.setWidth(AppControllerConstants.POURCENT_100);
        ftPersonnePhysique.setCellSpacing(5);
        ftPersonnePhysique.setWidget(0, 0, lCivilite);
        ftPersonnePhysique.setWidget(0, 1, construireBlocIcone(slbCivilite, "PersonnePhysiqueCopieDto.civilite", aideslbCivilite));
        ftPersonnePhysique.setWidget(1, 0, lNom);
        ftPersonnePhysique.setWidget(1, 1, construireBlocIcone(tbNom, "PersonnePhysiqueCopieDto.nom", aidetbNom));
        ftPersonnePhysique.setWidget(2, 0, lPrenom);
        ftPersonnePhysique.setWidget(2, 1, construireBlocIcone(tbPrenom, "PersonnePhysiqueCopieDto.prenom", aidetbPrenom));
        ftPersonnePhysique.setWidget(3, 0, lDate);
        ftPersonnePhysique.setWidget(3, 1, construireBlocIcone(cdbDateNaissance, "PersonnePhysiqueCopieDto.dateNaissance", aidecdbDateNaissance));
        ftPersonnePhysique.setWidget(4, 0, lTelephone);
        ftPersonnePhysique.setWidget(4, 1, ligneTelephone);
        ftPersonnePhysique.setWidget(5, 0, construirePanelErreursPersonneSource());
        ftPersonnePhysique.getFlexCellFormatter().setColSpan(5, 0, 2);
        ftPersonnePhysique.getColumnFormatter().setWidth(0, LARGEUR_COL_LIBELLE);
        captionPersonnePhysique.add(ftPersonnePhysique);

        // Infos santé
        // Annulation 5449
        //construireBlocInfosSante();

        // CONTENEUR PERSONNE MORALE ET PHYSIQUE
        final VerticalPanel panelCible = new VerticalPanel();
        panelCible.setWidth(AppControllerConstants.POURCENT_100);
        panelCible.add(captionPersonneMorale);
        panelCible.add(captionPersonnePhysique);
        //panelCible.add(captionInfosSante);

        // CONTENEUR BOUTONS
        final HorizontalPanel conteneurBoutons = new HorizontalPanel();
        btEnregistrerCreation = new DecoratedButton(constants.enregistrer());
        btEnregistrerCreation.ensureDebugId(viewDebugIdConstants.debugIdBtEnregistrerCreation());
        btnReduire = new DecoratedButton(constants.reduire());
        btnReduire.ensureDebugId(viewDebugIdConstants.debugIdBtnReduire());
        btAnnuler = new DecoratedButton(constants.annuler());
        btAnnuler.ensureDebugId(viewDebugIdConstants.debugIdBtnAnnuler());
        conteneurBoutons.add(btEnregistrerCreation);
        conteneurBoutons.add(btnReduire);
        conteneurBoutons.add(btAnnuler);
        conteneurBoutons.setSpacing(5);

        // CONSTRUCTION DU CONTENEUR PRINCIPAL
        panelGlobal = new VerticalPanel();
        panelGlobal.setSpacing(10);
        panelGlobal.setWidth(AppControllerConstants.POURCENT_100);
        panelGlobal.add(captionTypePersonne);
        panelGlobal.add(captionType);
        panelGlobal.add(panelCible);

        // FOCUS PANEL POUR GERER LES KEY HANDLERS
        focusPanel = new FocusPanel();
        focusPanel.setWidth(AppControllerConstants.POURCENT_100);
        focusPanel.add(panelGlobal);

        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.add(focusPanel);
        conteneur.add(conteneurBoutons);
        conteneur.setCellHorizontalAlignment(conteneurBoutons, HasHorizontalAlignment.ALIGN_CENTER);

        // CREATION DE LA POPUP
        popupCreation = new Popup(constants.textPopupCreationGenerique(), false, true, true);
        popupCreation.setWidget(conteneur, 0);
        popupCreation.addStyleName(SquareResources.INSTANCE.css().popupAjoutRelation());

        // on en fait une popup minimisable
        minimizablePopup = new PopupMinimizable(popupCreation, constants.textPopupCreationGenerique(), btnReduire);
    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage et un champ d'aide.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp, AideComposant aideComposant) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        final HorizontalPanel panelIcone = new HorizontalPanel();
        panelIcone.add(icone);
        panelIcone.add(aideComposant);
        panel.add(panelIcone);

        // panelIcone.setCellVerticalAlignment(aideComposant, HasVerticalAlignment.ALIGN_MIDDLE);
        panel.setCellVerticalAlignment(panelIcone, HasVerticalAlignment.ALIGN_MIDDLE);
        return panel;
    }

    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);

    }

    /**
     * Construit un widget permettant d'afficher les erreurs de la personne source.
     * @return le widget construit
     */
    private Widget construirePanelErreursPersonneSource() {
        final PanelErreursIntegrite panelErreur = new PanelErreursIntegrite();
        erreursPersonneSourceManager.addHandler(ControleIntegriteExceptionEvent.TYPE, panelErreur);
        return panelErreur;
    }

    /** Construit le bloc des infos santé. */
    // Annulation 5449
//    private void construireBlocInfosSante() {
//        captionInfosSante = new CaptionPanel(constants.lbCaptionInfosSante());
//        final Label lNumSs = new Label(constants.lNumSs());
//        final Label lRegime = new Label(constants.lRegime());
//        final Label lCaisse = new Label(constants.lCaisse());
//        final SuggestListBoxSingleProperties<IdentifiantEidLibelleModel> slbIdentifiantEidLibelleProperties =
//            new SuggestListBoxSingleProperties<IdentifiantEidLibelleModel>() {
//            @Override
//            public String getSelectSuggestRenderer(IdentifiantEidLibelleModel row) {
//                return row == null ? "" : row.getLibelle();
//            }
//
//            @Override
//            public String[] getResultColumnsRenderer() {
//                return new String[] {};
//            }
//
//            @Override
//            public String[] getResultRowsRenderer(IdentifiantEidLibelleModel row) {
//                return new String[] {row == null ? "" : row.getLibelle()};
//            }
//        };
//        final SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdentifiantLibelleProperties =
//            new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
//            @Override
//            public String getSelectSuggestRenderer(IdentifiantLibelleGwt row) {
//                return row == null ? "" : row.getLibelle();
//            }
//
//            @Override
//            public String[] getResultColumnsRenderer() {
//                return new String[] {};
//            }
//
//            @Override
//            public String[] getResultRowsRenderer(IdentifiantLibelleGwt row) {
//                return new String[] {row == null ? "" : row.getLibelle()};
//            }
//        };
//        final SuggestListBoxSingleProperties<CaisseSimpleModel> slbCaisseProperties = new SuggestListBoxSingleProperties<CaisseSimpleModel>() {
//            @Override
//            public String getSelectSuggestRenderer(CaisseSimpleModel row) {
//                return row == null ? "" : row.getCode() + "." + row.getCentre() + "." + row.getNom();
//            }
//
//            @Override
//            public String[] getResultColumnsRenderer() {
//                return new String[] {};
//            }
//
//            @Override
//            public String[] getResultRowsRenderer(CaisseSimpleModel row) {
//                return new String[] {row == null ? "" : row.getCode() + "." + row.getCentre() + "." + row.getNom()};
//            }
//        };
//        lbSsReferents = new DecoratedSuggestListBoxSingle<IdentifiantEidLibelleModel>(slbIdentifiantEidLibelleProperties);
//        tbNumSs = new DecoratedTextBox();
//        tbNumSs.setMaxLength(PersonneRelationsViewImplConstants.LONGUEUR_NUM_SECURITE_SOCIALE);
//        tbNumSs.setWidth("150px");
//        tbCleSs = new DecoratedTextBox();
//        tbCleSs.setMaxLength(PersonneRelationsViewImplConstants.LONGUEUR_CLE_SECURITE_SOCIALE);
//        tbCleSs.setWidth(PersonneRelationsViewImplConstants.LARGEUR_TB_CLE_SS);
//        lbRegime = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
//        lbCaisse = new DecoratedSuggestListBoxSingle<CaisseSimpleModel>(slbCaisseProperties);
//
//        aidetbNRO = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_NUMERO_RO, isAdmin);
//        aidelbRegime = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_REGIME, isAdmin);
//        aidelbCaisse = new AideComposant(AideComposantConstants.AIDE_PERSONNE_RELATIONS_CAISSE, isAdmin);
//        ajouterAideComposant(aidetbNRO);
//        ajouterAideComposant(aidelbRegime);
//        ajouterAideComposant(aidelbCaisse);
//
//        final HorizontalPanel hpSecuSociale = new HorizontalPanel();
//        hpSecuSociale.setWidth(AppControllerConstants.POURCENT_100);
//        hpSecuSociale.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
//        hpSecuSociale.setSpacing(3);
//        hpSecuSociale.add(lbSsReferents);
//        hpSecuSociale.add(new HTML("&nbsp;"));
//        hpSecuSociale.add(tbNumSs);
//        hpSecuSociale.add(new HTML("&nbsp;"));
//        hpSecuSociale.add(tbCleSs);
//
//        final FlexTable ftInfosSante = new FlexTable();
//        ftInfosSante.setWidth(AppControllerConstants.POURCENT_100);
//        ftInfosSante.setCellSpacing(5);
//        ftInfosSante.setWidget(0, 0, lNumSs);
//        ftInfosSante.setWidget(0, 1, construireBlocIcone(hpSecuSociale, "PersonnePhysiqueCopieDto.infoSante.nro", aidetbNRO));
//        ftInfosSante.setWidget(1, 0, lRegime);
//        ftInfosSante.setWidget(1, 1, construireBlocIcone(lbRegime, "PersonnePhysiqueCopieDto.infoSante.regime", aidelbRegime));
//        ftInfosSante.setWidget(1, 2, lCaisse);
//        ftInfosSante.setWidget(1, 3, construireBlocIcone(lbCaisse, "PersonnePhysiqueCopieDto.infoSante.caisse", aidelbCaisse));
//        ftInfosSante.getFlexCellFormatter().setColSpan(0, 1, 3);
//        ftInfosSante.getColumnFormatter().setWidth(0, PersonneRelationsViewImplConstants.LARGEUR_COL_LIBELLE_0);
//        ftInfosSante.getColumnFormatter().setWidth(1, PersonneRelationsViewImplConstants.LARGEUR_COL_CHAMP_1);
//        ftInfosSante.getColumnFormatter().setWidth(2, PersonneRelationsViewImplConstants.LARGEUR_COL_LIBELLE_2);
//        ftInfosSante.getColumnFormatter().setWidth(3, PersonneRelationsViewImplConstants.LARGEUR_COL_CHAMP_3);
//        captionInfosSante.add(ftInfosSante);
//        captionInfosSante.setVisible(false);
//    }

    @Override
    public void afficherPopupAjoutRelation() {
        fermerPopupCreation();
        popupCreation.show();
    }

    @Override
    public HandlerManager getRemotePagingHandlerManagerPm() {
        return remotePagingHandlerManagerPm;
    }

    @Override
    public void lancerLaRecherchePm() {
        remotePagingTablePm.rechercher();
    }

    @Override
    public HasValue<String> getTbRaisonSocialePm() {
        return tbRaisonSocialePm;
    }

    @Override
    public HasValue<String> getTbNumEntreprisePm() {
        return tbNumEntreprisePm;
    }

    @Override
    public HasClickHandlers getBtnRechercherPm() {
        return btnRechercherPm;
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
    }

    @Override
    public void stopAllLoadingPopup() {
        LoadingPopup.stopAll();
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stop();
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    @Override
    public void fermerPopupCreation() {
        clean();
        popupCreation.hide();
        popupCreation.hide();
    }

    private void clean() {
        slbTypeRelation.clean();
        slbTypeRelation.clean();
        getTbNom().setValue("");
        getTbPrenom().setValue("");
        getCdbDateNaissance().clean();
        getSlbCivilite().clean();
        tbTelephone.setValue("");
        slbNatureTelephone.clean();
        tbNumEntreprisePm.setValue("");
        tbRaisonSocialePm.setValue("");
//        lbSsReferents.clean();
//        tbNumSs.setValue("");
//        tbCleSs.setValue("");
//        lbRegime.clean();
//        lbCaisse.clean();
        remotePagingTablePm.init();
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
    public HasSuggestListBoxHandler<ItemValueModel> getSlbTypeRelation() {
        return slbTypeRelation;
    }

    @Override
    public HasValue<ItemValueModel> getSlbTypeRelationValue() {
        return slbTypeRelation;
    }

    @Override
    public HasValueChangeHandlers<ItemValueModel> getSlbTypeRelationValueChangeHandler() {
        return slbTypeRelation;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbCivilite() {
        return slbCivilite;
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
    public HasClickHandlers getBtEnregistrerCreation() {
        return btEnregistrerCreation;
    }

    @Override
    public HasClickHandlers getBtAnnuler() {
        return btAnnuler;
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public PersonneRelationsViewImplConstants getViewConstants() {
        return constants;
    }

    @Override
    public void switchPanelPopupPersonnePM() {
        lTelephone.setVisible(false);
        ligneTelephone.setVisible(false);
        captionPersonnePhysique.setVisible(false);
        captionPersonneMorale.setVisible(true);
    }

    @Override
    public void switchPanelPopupPersonnePP() {
        lTelephone.setVisible(false);
        ligneTelephone.setVisible(false);
        captionPersonnePhysique.setVisible(true);
        captionPersonneMorale.setVisible(false);
    }

    @Override
    public void switchPanelPopupPersonnePPNumeroTelephone() {
        lTelephone.setVisible(true);
        ligneTelephone.setVisible(true);
        captionPersonnePhysique.setVisible(true);
        captionPersonneMorale.setVisible(false);
    }

    @Override
    public void masquerTypePersonneVisible() {
        panelGlobal.remove(captionTypePersonne);
    }

    @Override
    public void setTextPopupCreation(boolean famille) {
        popupCreation.setText(famille ? constants.textPopupCreationFamille() : constants.textPopupCreationGenerique());
        minimizablePopup.setTitle(famille ? constants.textPopupCreationFamille() : constants.textPopupCreationGenerique());
    }

    @Override
    public void centrerPopup(String width) {
        focusPanel.setWidth(width);
        popupCreation.show();
    }

    @Override
    public HasValue<String> getTbTelephone() {
        return tbTelephone;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbNatureTelephone() {
        return slbNatureTelephone;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbNatureTelephoneValue() {
        return slbNatureTelephone;
    }

    @Override
    public Long getIdPaysTelephone() {
        return idPaysTelephone;
    }

    @Override
    public void setIdPaysTelephone(Long id) {
        this.idPaysTelephone = id;
    }

    @Override
    public HasClickHandlers getImgFlagPaysTelephone() {
        return imgFlagPaysTelephone;
    }

    @Override
    public void chargerImagePaysTelephone(String url, String title) {
        imgFlagPaysTelephone.setUrl(url);
        imgFlagPaysTelephone.addStyleName(SquareResources.INSTANCE.css().imgDrapeau());
        imgFlagPaysTelephone.setTitle(title);
    }

    @Override
    public void initTbTelephone(String format) {
        tbTelephone.setFormat(format);
        tbTelephone.setValue("");
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stop();
    }

    @Override
    public void initFocus() {
        if (slbTypePersonne.isAttached() && slbTypePersonne.isVisible()) {
            slbTypePersonne.setFocus(true);
        } else {
            slbTypeRelation.setFocus(true);
        }
    }

    @Override
    public HasValueChangeHandlers<IdentifiantLibelleGwt> getTypePersonneChangeHandler() {
        return slbTypePersonne;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbTypePersonne() {
        return slbTypePersonne;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbTypePersonneValue() {
        return slbTypePersonne;
    }

    @Override
    public HandlerManager getErreursPersonneSourceManager() {
        return erreursPersonneSourceManager;
    }

    @Override
    public HasAllKeyHandlers getFocusPanelAllKeyHandlers() {
        return focusPanel;
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
    // Annulation 5449
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public HasValue<IdentifiantLibelleGwt> getRegime() {
//        return lbRegime;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public HasValue<com.square.client.gwt.client.model.CaisseSimpleModel> getCaisse() {
//        return lbCaisse;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public HasValue<String> getNumeroSecuriteSociale() {
//        return tbNumSs;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public HasValue<String> getCleSecuriteSociale() {
//        return tbCleSs;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public HasValue<IdentifiantEidLibelleModel> getReferent() {
//        return lbSsReferents;
//    }
//
//    @Override
//    public HasSuggestListBoxHandler<CaisseSimpleModel> getCaisseSuggestHandler() {
//        return lbCaisse;
//    }
//
//    @Override
//    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getRegimeSuggestHandler() {
//        return lbRegime;
//    }
//
//    @Override
//    public HasValueChangeHandlers<IdentifiantLibelleGwt> getRegimeValueChangeHandler() {
//        return lbRegime;
//    }
//
//    @Override
//    public HasSuggestListBoxHandler<IdentifiantEidLibelleModel> getReferentSuggestHandler() {
//        return lbSsReferents;
//    }
//
//    @Override
//    public HasValueChangeHandlers<IdentifiantEidLibelleModel> getReferentValueChangeHandler() {
//        return lbSsReferents;
//    }
//
//    @Override
//    public void enableInfoSante(boolean enabled) {
//        tbNumSs.setEnabled(enabled);
//        tbCleSs.setEnabled(enabled);
//        lbRegime.setEnabled(enabled);
//        lbCaisse.setEnabled(enabled);
//    }
//
//    @Override
//    public void afficherInfosSante(boolean affiche) {
//        captionInfosSante.setVisible(affiche);
//    }
}

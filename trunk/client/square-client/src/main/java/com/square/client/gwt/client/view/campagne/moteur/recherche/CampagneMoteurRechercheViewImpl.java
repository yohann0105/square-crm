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
package com.square.client.gwt.client.view.campagne.moteur.recherche;

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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
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
import com.square.client.gwt.client.model.CampagneCriteresRechercheModel;
import com.square.client.gwt.client.model.CampagneRechercheModel;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.presenter.campagne.CampagneMoteurRecherchePresenter.CampagneMoteurRechercheView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxComposite;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.DecoratedTextBoxFormat;

/**
 * Implementation de la vue du moteur de recherche des campagnes.
 * @author cblanchard - SCUB
 */
public class CampagneMoteurRechercheViewImpl extends Composite implements CampagneMoteurRechercheView {

    /** View constants. */
    private static CampagneMoteurRechercheViewImplConstants viewConstants =
        (CampagneMoteurRechercheViewImplConstants) GWT.create(CampagneMoteurRechercheViewImplConstants.class);

    /** View constants. */
    private static CampagneMoteurRechercheViewImplDebugIdConstants viewDebugIdConstants =
        (CampagneMoteurRechercheViewImplDebugIdConstants) GWT.create(CampagneMoteurRechercheViewImplDebugIdConstants.class);

    /** TextBox pour le code. */
    private DecoratedTextBoxFormat tbCode;

    private AideComposant aidetbCode;

    /** TextBox pour le libelle. */
    private DecoratedTextBox tbLibelle;

    private AideComposant aidetbLibelle;

    /** SuggestListBox pour le type. */
    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbType;

    private AideComposant aideslbType;

    /** SuggestListBox pour le statut. */
    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbStatut;

    private AideComposant aideslbStatut;

    /** SuggestListBox pour le créateur. */
    private DecoratedSuggestListBoxSingle<DimensionRessourceModel> slbCreateur;

    private AideComposant aideslbCreateur;

    /** Date inférieur pour la date de début. */
    private DecoratedCalendrierDateBox cdbDateInfDateDebut;

    private AideComposant aidecdbDateInfDateDebut;

    /** Date supérieur pour la date de début. */
    private DecoratedCalendrierDateBox cdbDateSupDateDebut;

    private AideComposant aidecdbDateSupDateDebut;

    /** Date inférieur pour la date de fin. */
    private DecoratedCalendrierDateBox cdbDateInfDateFin;

    private AideComposant aidecdbDateInfDateFin;

    /** Date supérieur pour la date de fin. */
    private DecoratedCalendrierDateBox cdbDateSupDateFin;

    private AideComposant aidecdbDateSupDateFin;

    /** Bouton rechercher. */
    private DecoratedButton btnRechercher;

    /** Bouton rechercher. */
    private DecoratedButton btnEffacerRecherche;

    /** Conteneur principal de la page. */
    private VerticalPanel conteneur;

    /** La table de résultat. */
    private RemotePagingTable<CampagneRechercheModel, CampagneCriteresRechercheModel> remotePagingTableCampagne;

    /** Pour recuperer les evenements sur le tableau dans le Presenter. */
    private HandlerManager remotePagingHandlerManager;

    /** Propriétés pour les listes. */
    private SuggestListBoxCompositeProperties<IdentifiantLibelleGwt> slbIdLibelleProperties;

    private FocusPanel focusPanel;

    private HorizontalPanel panelExport;

    private Image imageExportExcel;

    private Label labelExportExcel;

    private AppControllerConstants appConstantes;

    private boolean isAdmin;

    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    private List<AideComposant> listComposantHavingAide = new ArrayList<AideComposant>();

    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    /**
     * Constructeur.
     * @param appConstantes les appConstantes.
     */
    public CampagneMoteurRechercheViewImpl(AppControllerConstants appConstantes, boolean isAdmin) {
        this.focusPanel = new FocusPanel();
        this.appConstantes = appConstantes;
        this.isAdmin = isAdmin;
        conteneur = new VerticalPanel();
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.setSpacing(10);
        AideComposant aideView = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_RECHERCHE_GLOBAL, isAdmin);
        ajouterAideComposant(aideView);
        conteneur.add(aideView);
        conteneur.setCellHorizontalAlignment(aideView, HasAlignment.ALIGN_RIGHT);

        construireBlocRecherche();

        // Construction de la ligne avec le bouton
        btnRechercher = new DecoratedButton(viewConstants.libelleBtnRechercher());
        btnRechercher.ensureDebugId(viewDebugIdConstants.debugIdBtnRechercher());
        btnEffacerRecherche = new DecoratedButton(viewConstants.effacer());
        btnEffacerRecherche.ensureDebugId(viewDebugIdConstants.debugIdBtnEffacerRecherche());

        final FlexTable conteneurButtons = new FlexTable();
        conteneurButtons.setCellSpacing(5);
        conteneurButtons.setWidget(0, 0, btnRechercher);
        conteneurButtons.setWidget(0, 1, btnEffacerRecherche);
        conteneur.add(conteneurButtons);
        conteneur.setCellHorizontalAlignment(conteneurButtons, HasAlignment.ALIGN_CENTER);

        construireBlocResultat();
        creerExportExcel();

        focusPanel.add(new ContenuOnglet(conteneur));

        this.initWidget(focusPanel);
        this.setWidth(AppControllerConstants.POURCENT_100);
        this.addStyleName(SquareResources.INSTANCE.css().campagneMoteurRecherche());
    }

    private void construireBlocRecherche() {
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

        tbCode = new DecoratedTextBoxFormat("NNNNNNNNNN");
        tbCode.ensureDebugId(viewDebugIdConstants.debugIdTbCode());
        aidetbCode = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_RECHERCHE_CODE, isAdmin);
        HorizontalPanel paneltbCode = new HorizontalPanel();
        paneltbCode.setSpacing(5);
        paneltbCode.add(tbCode);
        paneltbCode.add(aidetbCode);
        ajouterAideComposant(aidetbCode);
        tbLibelle = new DecoratedTextBox();
        tbLibelle.ensureDebugId(viewDebugIdConstants.debugIdTbLibelle());

        aidetbLibelle = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_RECHERCHE_LIBELLE, isAdmin);
        HorizontalPanel paneltbLibelle = new HorizontalPanel();
        paneltbLibelle.setSpacing(5);
        paneltbLibelle.add(tbLibelle);
        paneltbLibelle.add(aidetbLibelle);
        ajouterAideComposant(aidetbLibelle);
        slbType = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbType.ensureDebugId(viewDebugIdConstants.debugIdSlbType());
        slbType.setScrollPanelSuggestMultipleHeight(CampagneMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);

        aideslbType = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_RECHERCHE_TYPE, isAdmin);
        HorizontalPanel panelslbType = new HorizontalPanel();
        panelslbType.setSpacing(5);
        panelslbType.add(slbType);
        panelslbType.add(aideslbType);
        ajouterAideComposant(aideslbType);
        slbStatut = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbStatut.ensureDebugId(viewDebugIdConstants.debugIdSlbStatut());
        slbStatut.setScrollPanelSuggestMultipleHeight(CampagneMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbStatut = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_RECHERCHE_STATUT, isAdmin);
        HorizontalPanel panelslbStatut = new HorizontalPanel();
        panelslbStatut.setSpacing(5);
        panelslbStatut.add(slbStatut);
        panelslbStatut.add(aideslbStatut);
        ajouterAideComposant(aideslbStatut);

        cdbDateInfDateDebut = new DecoratedCalendrierDateBox();
        cdbDateInfDateDebut.ensureDebugId(viewDebugIdConstants.debugIdCdbDateInfDateDebut());
        aidecdbDateInfDateDebut = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_RECHERCHE_DATE_INF_DEBUT, isAdmin);
        HorizontalPanel panelcdbDateInfDateDebut = new HorizontalPanel();
        panelcdbDateInfDateDebut.setSpacing(5);
        panelcdbDateInfDateDebut.add(cdbDateInfDateDebut);
        panelcdbDateInfDateDebut.add(aidecdbDateInfDateDebut);
        ajouterAideComposant(aidecdbDateInfDateDebut);

        cdbDateSupDateDebut = new DecoratedCalendrierDateBox();
        cdbDateSupDateDebut.ensureDebugId(viewDebugIdConstants.debugIdCdbDateSupDateDebut());
        aidecdbDateSupDateDebut = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_RECHERCHE_DATE_SUP_DEBUT, isAdmin);
        HorizontalPanel panelcdbDateSupDateDebut = new HorizontalPanel();
        panelcdbDateSupDateDebut.setSpacing(5);
        panelcdbDateSupDateDebut.add(cdbDateSupDateDebut);
        panelcdbDateSupDateDebut.add(aidecdbDateSupDateDebut);
        ajouterAideComposant(aidecdbDateSupDateDebut);

        cdbDateInfDateFin = new DecoratedCalendrierDateBox();
        cdbDateInfDateFin.ensureDebugId(viewDebugIdConstants.debugIdCdbDateInfDateFin());
        aidecdbDateInfDateFin = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_RECHERCHE_DATE_INF_FIN, isAdmin);
        HorizontalPanel panelcdbDateInfDateFin = new HorizontalPanel();
        panelcdbDateInfDateFin.setSpacing(5);
        panelcdbDateInfDateFin.add(cdbDateInfDateFin);
        panelcdbDateInfDateFin.add(aidecdbDateInfDateFin);
        ajouterAideComposant(aidecdbDateInfDateFin);

        cdbDateSupDateFin = new DecoratedCalendrierDateBox();
        cdbDateSupDateFin.ensureDebugId(viewDebugIdConstants.debugIdCdbDateSupDateFin());

        aidecdbDateInfDateFin = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_RECHERCHE_DATE_SUP_FIN, isAdmin);
        HorizontalPanel panelcdbDateSupDateFin = new HorizontalPanel();
        panelcdbDateSupDateFin.setSpacing(5);
        panelcdbDateSupDateFin.add(cdbDateSupDateFin);
        panelcdbDateSupDateFin.add(aidecdbDateInfDateFin);
        ajouterAideComposant(aidecdbDateInfDateFin);

        final SuggestListBoxSingleProperties<DimensionRessourceModel> properties = new SuggestListBoxSingleProperties<DimensionRessourceModel>() {
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
        };
        slbCreateur = new DecoratedSuggestListBoxSingle<DimensionRessourceModel>(properties);
        slbCreateur.ensureDebugId(viewDebugIdConstants.debugIdSlbCreateur());
        aideslbCreateur = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_RECHERCHE_CREATEUR, isAdmin);
        HorizontalPanel panelslbCreateur = new HorizontalPanel();
        panelslbCreateur.setSpacing(5);
        panelslbCreateur.add(slbCreateur);
        panelslbCreateur.add(aideslbCreateur);
        ajouterAideComposant(aideslbCreateur);

        final CaptionPanel captionPanel = new CaptionPanel(viewConstants.recherche());
        final FlexTable fpRechercher = new FlexTable();
        fpRechercher.setWidth(AppControllerConstants.POURCENT_100);
        fpRechercher.setCellSpacing(5);

        final Label code = creerLibelle(viewConstants.code());
        final Label libelle = creerLibelle(viewConstants.libelle());
        final Label type = creerLibelle(viewConstants.type());
        final Label statut = creerLibelle(viewConstants.statut());
        final Label libelleDateDebut = creerLibelle(viewConstants.libelleDateDebut());
        final Label libelleDateFin = creerLibelle(viewConstants.libelleDateFin());
        final Label createur = creerLibelle(viewConstants.createur());
        final Label auDateDebut = creerLibelle(viewConstants.au());
        final Label auDateFin = creerLibelle(viewConstants.au());

        final HorizontalPanel conteneurDateDebut = new HorizontalPanel();
        conteneurDateDebut.setVerticalAlignment(HasAlignment.ALIGN_MIDDLE);
        conteneurDateDebut.add(panelcdbDateInfDateDebut);
        conteneurDateDebut.add(new HTML("&nbsp;"));
        conteneurDateDebut.add(auDateDebut);
        conteneurDateDebut.add(new HTML("&nbsp;"));
        conteneurDateDebut.add(panelcdbDateSupDateDebut);
        final HorizontalPanel conteneurDateFin = new HorizontalPanel();
        conteneurDateFin.setVerticalAlignment(HasAlignment.ALIGN_MIDDLE);
        conteneurDateFin.add(panelcdbDateInfDateFin);
        conteneurDateFin.add(new HTML("&nbsp;"));
        conteneurDateFin.add(auDateFin);
        conteneurDateFin.add(new HTML("&nbsp;"));
        conteneurDateFin.add(panelcdbDateSupDateFin);

        fpRechercher.setWidget(0, 0, code);
        fpRechercher.setWidget(0, 1, paneltbCode);
        fpRechercher.setWidget(0, 2, libelle);
        fpRechercher.setWidget(0, 3, paneltbLibelle);

        fpRechercher.setWidget(1, 0, type);
        fpRechercher.setWidget(1, 1, panelslbType);
        fpRechercher.setWidget(1, 2, statut);
        fpRechercher.setWidget(1, 3, panelslbStatut);

        fpRechercher.setWidget(2, 0, libelleDateDebut);
        fpRechercher.setWidget(2, 1, conteneurDateDebut);
        fpRechercher.setWidget(2, 2, libelleDateFin);
        fpRechercher.setWidget(2, 3, conteneurDateFin);

        fpRechercher.setWidget(3, 0, createur);
        fpRechercher.setWidget(3, 1, panelslbCreateur);

        fpRechercher.getColumnFormatter().setWidth(0, CampagneMoteurRechercheViewImplConstants.LARGEUR_COL_LIBELLE);
        fpRechercher.getColumnFormatter().setWidth(1, CampagneMoteurRechercheViewImplConstants.LARGEUR_COL_CHAMP);
        fpRechercher.getColumnFormatter().setWidth(2, CampagneMoteurRechercheViewImplConstants.LARGEUR_COL_LIBELLE);
        fpRechercher.getColumnFormatter().setWidth(3, CampagneMoteurRechercheViewImplConstants.LARGEUR_COL_CHAMP);

        captionPanel.add(fpRechercher);
        conteneur.add(captionPanel);
    }

    private void construireBlocResultat() {
        remotePagingTableCampagne =
            new RemotePagingTable<CampagneRechercheModel, CampagneCriteresRechercheModel>(CampagneMoteurRechercheViewImplConstants.NB_ELEMENT_PAR_PAGE, true) {
                @Override
                public void setRow(int row, CampagneRechercheModel campagne) {
                    setWidget(row, 0, new Label(campagne.getCode()));
                    setWidget(row, 1, new Label(campagne.getLibelle()));
                    setWidget(row, 2, new Label(campagne.getStatut() != null ? campagne.getStatut().getLibelle() : ""));
                    setWidget(row, 3, new Label(campagne.getDateDebut()));
                    setWidget(row, 4, new Label(campagne.getRessource() != null ? campagne.getRessource().getNom() + " " + campagne.getRessource().getPrenom()
                        : ""));
                }

                @Override
                public Column[] setHeader() {
                    return new Column[] {new Column(viewConstants.headerCode(), viewConstants.fieldCodeCampagne()),
                        new Column(viewConstants.headerLibelle(), viewConstants.fieldLibelleCampagne()),
                        new Column(viewConstants.headerStatut(), viewConstants.fieldStatut()),
                        new Column(viewConstants.headerDateDebut(), viewConstants.fieldDateDebut()),
                        new Column(viewConstants.headerCreateur(), viewConstants.fieldCreateur())};
                }

                @Override
                public String setDefaultSortField() {
                    return viewConstants.fieldCodeCampagne();
                }

                @Override
                public int setDefaultSortAsc() {
                    return RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                }

                @Override
                public void setDataProvider(RemotePagingCriteriasGwt<CampagneCriteresRechercheModel> params,
                    AsyncCallback<RemotePagingResultsGwt<CampagneRechercheModel>> callback) {
                    remotePagingHandlerManager.fireEvent(new SetDataProviderEvent<CampagneCriteresRechercheModel, CampagneRechercheModel>(params, callback));
                }

                @Override
                public void setCellClicked(CampagneRechercheModel objet) {
                    remotePagingHandlerManager.fireEvent(new SetCellClickedEvent<CampagneRechercheModel>(objet));
                }
            };
        remotePagingTableCampagne.ensureDebugId(viewDebugIdConstants.debugIdRemotePagingTableCampagne());
        remotePagingHandlerManager = new HandlerManager(remotePagingTableCampagne);
        remotePagingTableCampagne.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.add(remotePagingTableCampagne);
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

    private Label creerLibelle(final String libelle) {
        final Label label = new Label(libelle, false);
        label.setHorizontalAlignment(HasAlignment.ALIGN_RIGHT);
        label.addStyleName(SquareResources.INSTANCE.css().libelleMoteurRecherche());
        return label;
    }

    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);

    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasValue<String> getTbCodeValue() {
        return tbCode;
    }

    @Override
    public HasKeyPressHandlers getTbCode() {
        return tbCode;
    }

    @Override
    public RemotePagingTable<CampagneRechercheModel, CampagneCriteresRechercheModel> getRemotePagingTableCampagne() {
        return remotePagingTableCampagne;
    }

    @Override
    public HandlerManager getRemotePagingHandlerManager() {
        return remotePagingHandlerManager;
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration) {
        LoadingPopup.afficher(loadingPopupConfiguration);
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stop();
    }

    @Override
    public CampagneMoteurRechercheViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public HasValue<DimensionRessourceModel> getSlbCreateurValue() {
        return slbCreateur;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getSlbStatutValue() {
        return slbStatut;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getSlbTypeValue() {
        return slbType;
    }

    @Override
    public HasValue<String> getTbLibelleValue() {
        return tbLibelle;
    }

    @Override
    public HasClickHandlers getBtnRechercher() {
        return btnRechercher;
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateInfDateDebut() {
        return cdbDateInfDateDebut;
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateInfDateFin() {
        return cdbDateInfDateFin;
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateSupDateDebut() {
        return cdbDateSupDateDebut;
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateSupDateFin() {
        return cdbDateSupDateFin;
    }

    @Override
    public HasSuggestListBoxHandler<DimensionRessourceModel> getSlbCreateur() {
        return slbCreateur;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbStatut() {
        return slbStatut;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbType() {
        return slbType;
    }

    @Override
    public HasKeyPressHandlers getTbLibelle() {
        return tbLibelle;
    }

    @Override
    public HasClickHandlers getBtnEffacerRecherche() {
        return btnEffacerRecherche;
    }

    @Override
    public void effacerRecherche() {
        tbCode.setValue("");
        tbLibelle.setValue("");
        cdbDateInfDateDebut.clean();
        cdbDateSupDateDebut.clean();
        cdbDateInfDateFin.clean();
        cdbDateSupDateFin.clean();
        slbCreateur.clean();
        slbStatut.clean();
        slbType.clean();
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
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        ErrorPopup.afficher(config);
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

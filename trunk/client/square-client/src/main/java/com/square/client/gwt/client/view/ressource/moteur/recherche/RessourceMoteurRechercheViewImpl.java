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
package com.square.client.gwt.client.view.ressource.moteur.recherche;

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
import com.square.client.gwt.client.model.RessourceCriteresRechercheModel;
import com.square.client.gwt.client.model.RessourceModel;
import com.square.client.gwt.client.presenter.ressource.RessourceMoteurRecherchePresenter;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxComposite;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;

/**
 * Vue du moteur de recherche de ressource.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public class RessourceMoteurRechercheViewImpl extends Composite implements RessourceMoteurRecherchePresenter.RessourceMoteurRechercheView {

    /** View constants. */
    private static RessourceMoteurRechercheViewImplConstants viewConstants =
        (RessourceMoteurRechercheViewImplConstants) GWT.create(RessourceMoteurRechercheViewImplConstants.class);

    /** View constants debugId. */
    private static RessourceMoteurRechercheViewImplDebugIdConstants viewDebugIdConstants =
        (RessourceMoteurRechercheViewImplDebugIdConstants) GWT.create(RessourceMoteurRechercheViewImplDebugIdConstants.class);

    private VerticalPanel conteneur;

    private DecoratedTextBox tbNom;

    private AideComposant aidetbNom;

    private DecoratedTextBox tbPrenom;

    private AideComposant aidetbPrenom;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbFonction;

    private AideComposant aideslbFonction;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbService;

    private AideComposant aideslbService;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbEtat;

    private AideComposant aideslbEtat;

    private DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt> slbAgence;

    private AideComposant aideslbAgence;

    private SuggestListBoxCompositeProperties<IdentifiantLibelleGwt> slbIdLibelleProperties;

    private AideComposant aideslbIdLibelleProperties;

    private SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdLibelleServiceProperties;

    private AideComposant aideslbIdLibelleServiceProperties;

    private DecoratedButton btnRechercher;

    private DecoratedButton btnEffacerRecherche;

    private RemotePagingTable<RessourceModel, RessourceCriteresRechercheModel> remotePagingTableRessources;

    /** Pour recuperer les evenements sur le tableau dans le Presenter. */
    private HandlerManager remotePagingHandlerManager;

    private FocusPanel focusPanel;

    private HorizontalPanel panelExport;

    private Image imageExportExcel;

    private Label labelExportExcel;

    private AppControllerConstants appConstantes;

    /** Menu contextuel. */
    private VerticalPanel contextMenu;

    /** Bouton pour modifier les quotas. */
    private DecoratedButton btnMofifierQuotas;

    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    private boolean isAdmin;

    /**
     * Constructeur.
     * @param appConstantes les appConstantes.
     */
    public RessourceMoteurRechercheViewImpl(AppControllerConstants appConstantes, boolean isAdmin) {
        this.appConstantes = appConstantes;
        this.isAdmin = isAdmin;
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
        slbIdLibelleServiceProperties = new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {

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
        this.focusPanel = new FocusPanel();
        conteneur = new VerticalPanel();
        conteneur.setSpacing(10);
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        AideComposant aideView = new AideComposant(AideComposantConstants.AIDE_RESSOURCES_RECHERCHE_GLOBAL, isAdmin);
        ajouterAideComposant(aideView);
        conteneur.add(aideView);
        conteneur.setCellHorizontalAlignment(aideView, HasAlignment.ALIGN_RIGHT);
        construireBlocRecherche();

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

        // Construction du menu contextuel
        contruireMenuContextuel();

        this.initWidget(focusPanel);
        this.addStyleName(SquareResources.INSTANCE.css().personneMoraleMoteurRecherche());
        this.setWidth(AppControllerConstants.POURCENT_100);
    }

    /**
     * Construction du menu contextuel.
     */
    private void contruireMenuContextuel() {
        final Label lTitreMenuContextuel = new Label(viewConstants.titreMenuContextuel());
        lTitreMenuContextuel.addStyleName(SquareResources.INSTANCE.css().titrePanelDroite());
        btnMofifierQuotas = new DecoratedButton(viewConstants.modifierQuotas());
        btnMofifierQuotas.ensureDebugId(viewDebugIdConstants.debugIdBtnModifierQuotas());

        contextMenu = new VerticalPanel();
        contextMenu.add(lTitreMenuContextuel);
        contextMenu.add(btnMofifierQuotas);

        contextMenu.addStyleName(SquareResources.INSTANCE.css().blocPanelDroite());
        contextMenu.setWidth(AppControllerConstants.POURCENT_100);
        contextMenu.setSpacing(10);
        contextMenu.setCellHorizontalAlignment(btnMofifierQuotas, HasAlignment.ALIGN_CENTER);
    }

    /**
     * Construit le bloc des criteres de recherche.
     */
    private void construireBlocRecherche() {
        tbNom = new DecoratedTextBox();
        tbNom.ensureDebugId(viewDebugIdConstants.debugIdTbNom());
        aidetbNom = new AideComposant(AideComposantConstants.AIDE_RESSOURCES_RECHERCHE_NOM, isAdmin);
        HorizontalPanel paneltbNom = new HorizontalPanel();
        paneltbNom.setSpacing(5);
        paneltbNom.add(tbNom);
        paneltbNom.add(aidetbNom);
        ajouterAideComposant(aidetbNom);

        tbPrenom = new DecoratedTextBox();
        tbPrenom.ensureDebugId(viewDebugIdConstants.debugIdTbPrenom());
        aidetbPrenom = new AideComposant(AideComposantConstants.AIDE_RESSOURCES_RECHERCHE_PRENOM, isAdmin);
        HorizontalPanel paneltbPrenom = new HorizontalPanel();
        paneltbPrenom.setSpacing(5);
        paneltbPrenom.add(tbPrenom);
        paneltbPrenom.add(aidetbPrenom);
        ajouterAideComposant(aidetbPrenom);

        slbAgence = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbAgence.setScrollPanelSuggestMultipleHeight(RessourceMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbAgence = new AideComposant(AideComposantConstants.AIDE_RESSOURCES_RECHERCHE_AGENCE, isAdmin);
        HorizontalPanel panelslbAgence = new HorizontalPanel();
        panelslbAgence.setSpacing(5);
        panelslbAgence.add(slbAgence);
        panelslbAgence.add(aideslbAgence);
        ajouterAideComposant(aideslbAgence);

        slbService = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdLibelleServiceProperties);
        aideslbService = new AideComposant(AideComposantConstants.AIDE_RESSOURCES_RECHERCHE_SERVICE, isAdmin);
        HorizontalPanel panelslbService = new HorizontalPanel();
        panelslbService.setSpacing(5);
        panelslbService.add(slbService);
        panelslbService.add(aideslbService);
        ajouterAideComposant(aideslbService);

        slbFonction = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbFonction.setScrollPanelSuggestMultipleHeight(RessourceMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbFonction = new AideComposant(AideComposantConstants.AIDE_RESSOURCES_RECHERCHE_FONCTION, isAdmin);
        HorizontalPanel panelslbFonction = new HorizontalPanel();
        panelslbFonction.setSpacing(5);
        panelslbFonction.add(slbFonction);
        panelslbFonction.add(aideslbFonction);
        ajouterAideComposant(aideslbFonction);

        slbEtat = new DecoratedSuggestListBoxComposite<IdentifiantLibelleGwt>(slbIdLibelleProperties);
        slbEtat.setScrollPanelSuggestMultipleHeight(RessourceMoteurRechercheViewImplConstants.HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE);
        aideslbEtat = new AideComposant(AideComposantConstants.AIDE_RESSOURCES_RECHERCHE_ETAT, isAdmin);
        HorizontalPanel panelslbEtat = new HorizontalPanel();
        panelslbEtat.setSpacing(5);
        panelslbEtat.add(slbEtat);
        panelslbEtat.add(aideslbEtat);
        ajouterAideComposant(aideslbEtat);

        slbAgence.ensureDebugId(viewDebugIdConstants.debugIdSlbAgence());
        slbService.ensureDebugId(viewDebugIdConstants.debugIdSlbService());
        slbFonction.ensureDebugId(viewDebugIdConstants.debugIdSlbFonction());
        slbEtat.ensureDebugId(viewDebugIdConstants.debugIdSlbEtat());

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.recherche());

        final Label nom = creerLibelle(viewConstants.nom());
        final Label prenom = creerLibelle(viewConstants.prenom());
        final Label agence = creerLibelle(viewConstants.agence());
        final Label service = creerLibelle(viewConstants.service());
        final Label fonction = creerLibelle(viewConstants.fonction());
        final Label etat = creerLibelle(viewConstants.etat());

        final FlexTable fpRecherche = new FlexTable();
        fpRecherche.setCellSpacing(5);
        fpRecherche.setWidget(0, 0, nom);
        fpRecherche.setWidget(0, 1, paneltbNom);
        fpRecherche.setWidget(0, 2, prenom);
        fpRecherche.setWidget(0, 3, paneltbPrenom);
        fpRecherche.setWidget(0, 4, agence);
        fpRecherche.setWidget(0, 5, panelslbAgence);

        fpRecherche.setWidget(1, 0, service);
        fpRecherche.setWidget(1, 1, panelslbService);
        fpRecherche.setWidget(1, 2, fonction);
        fpRecherche.setWidget(1, 3, panelslbFonction);
        fpRecherche.setWidget(1, 4, etat);
        fpRecherche.setWidget(1, 5, panelslbEtat);

        fpRecherche.getColumnFormatter().setWidth(0, "9%");
        fpRecherche.getColumnFormatter().setWidth(1, "23%");
        fpRecherche.getColumnFormatter().setWidth(2, "11%");
        fpRecherche.getColumnFormatter().setWidth(3, "23%");
        fpRecherche.getColumnFormatter().setWidth(4, "9%");
        fpRecherche.getColumnFormatter().setWidth(5, "24%");

        fieldSetPanel.add(fpRecherche);
        conteneur.add(fieldSetPanel);
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
        remotePagingTableRessources =
            new RemotePagingTable<RessourceModel, RessourceCriteresRechercheModel>(RessourceMoteurRechercheViewImplConstants.NB_ELEMENT_PAR_PAGE, true) {

                @Override
                public void setDataProvider(RemotePagingCriteriasGwt<RessourceCriteresRechercheModel> params,
                    AsyncCallback<RemotePagingResultsGwt<RessourceModel>> callback) {
                    remotePagingHandlerManager.fireEvent(new SetDataProviderEvent<RessourceCriteresRechercheModel, RessourceModel>(params, callback));
                }

                @Override
                public int setDefaultSortAsc() {
                    return RemotePagingSortGwt.REMOTE_PAGING_SORT_ASC;
                }

                @Override
                public String setDefaultSortField() {
                    return "triNom";
                }

                @Override
                public Column[] setHeader() {
                    return new Column[] {new Column(viewConstants.headerNom(), viewConstants.fieldNom()),
                        new Column(viewConstants.headerPrenom(), viewConstants.fieldPrenom()),
                        new Column(viewConstants.headerFonction(), viewConstants.fieldFonction()),
                        new Column(viewConstants.headerAgence(), viewConstants.fieldAgence()),
                        new Column(viewConstants.headerService(), viewConstants.fieldService()),
                        new Column(viewConstants.headerEtat(), viewConstants.fieldEtat())};
                }

                @Override
                public void setRow(int row, RessourceModel ressource) {
                    setWidget(row, 0, new Label(ressource.getNom()));
                    setWidget(row, 1, new Label(ressource.getPrenom()));
                    setWidget(row, 2, new Label(ressource.getFonction() != null ? ressource.getFonction().getLibelle() : ""));
                    setWidget(row, 3, new Label(ressource.getAgence() != null ? ressource.getAgence().getLibelle() : ""));
                    setWidget(row, 4, new Label(ressource.getService() != null ? ressource.getService().getLibelle() : ""));
                    setWidget(row, 5, new Label(ressource.getEtat() != null ? ressource.getEtat().getLibelle() : ""));
                }

                @Override
                public void setCellClicked(RessourceModel objet) {
                    remotePagingHandlerManager.fireEvent(new SetCellClickedEvent<RessourceModel>(objet));
                }

            };
        remotePagingTableRessources.ensureDebugId(viewDebugIdConstants.debugIdTableRessources());
        remotePagingHandlerManager = new HandlerManager(remotePagingTableRessources);
        remotePagingTableRessources.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.add(remotePagingTableRessources);
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
    public HasClickHandlers getBtnRechercher() {
        return btnRechercher;
    }

    @Override
    public HandlerManager getRemotePagingHandlerManager() {
        return remotePagingHandlerManager;
    }

    @Override
    public RemotePagingTable<RessourceModel, RessourceCriteresRechercheModel> getRemotePagingTable() {
        return remotePagingTableRessources;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbFonction() {
        return slbFonction;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getSlbFonctionValue() {
        return slbFonction;
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
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbService() {
        return slbService;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbServiceValue() {
        return slbService;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbEtat() {
        return slbEtat;
    }

    @Override
    public HasValue<List<IdentifiantLibelleGwt>> getSlbEtatValue() {
        return slbEtat;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestionAgence() {
        return slbAgence;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestionFonction() {
        return slbFonction;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestionService() {
        return slbService;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestionEtat() {
        return slbEtat;
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
        tbNom.setValue("");
        tbPrenom.setValue("");
        slbAgence.clean();
        slbService.clean();
        slbFonction.clean();
        slbEtat.clean();
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
    public void nettoyerFonction() {
        slbFonction.clean();
    }

    @Override
    public void setFonctionEnabled(boolean enabled) {
        slbFonction.setEnabled(enabled);
    }

    @Override
    public Widget asContextMenuWidget() {
        return contextMenu;
    }

    @Override
    public HasClickHandlers getBtnModifierQuotas() {
        return btnMofifierQuotas;
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

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
package com.square.client.gwt.client.view.personne.physique.opportunites.creation;

import java.util.ArrayList;
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
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.presenter.personne.physique.opportunites.OpportuniteCreationPresenter.OpportuniteCreationView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.popups.minimizable.IsMinimizable;
import com.square.composants.graphiques.lib.client.popups.minimizable.PopupMinimizable;

/**
 * Implementation de la vue de la popup de création d'opportunité.
 * @author cblanchard - SCUB
 */
public class OpportuniteCreationViewImpl extends Popup implements OpportuniteCreationView {

    private static final int PANEL_COMPOSANT_SPACING = 15;

	/** View constants. */
    private static OpportuniteCreationViewImplConstants viewConstants =
        (OpportuniteCreationViewImplConstants) GWT.create(OpportuniteCreationViewImplConstants.class);

    /** View constants. */
    private static OpportuniteCreationViewImplDebugIdConstants viewDebugIdConstants =
        (OpportuniteCreationViewImplDebugIdConstants) GWT.create(OpportuniteCreationViewImplDebugIdConstants.class);

    /** Conteneur principal. */
    private FocusPanel panelPrincipal;

    private VerticalPanel conteneur;

    private IconeErreurChampManager iconeErreurChampManager;

    private Label lPersonne;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbsNature;

    private AideComposant aideslbsNature;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbsCampagne;

    private AideComposant aideslbsCampagne;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbsAgence;

    private AideComposant aideslbsAgence;

    private DecoratedSuggestListBoxSingle<DimensionRessourceModel> slbsRessource;

    private AideComposant aideslbsRessource;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbsType;

    private AideComposant aideslbsType;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbsObjet;

    private AideComposant aideslbsObjet;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbsSousObjet;

    private AideComposant aideslbsSousObjet;

    private DecoratedButton btnAnnuler;

    private DecoratedButton btnEnregistrer;

    private DecoratedButton btnReduire;

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
     * @param isAdmin L'utilisateur connécté posséde-t-il le role d'admin.
     */
    public OpportuniteCreationViewImpl(boolean isAdmin) {
        super(viewConstants.titrePopup(), false, false, true);
        this.isAdmin = isAdmin;
        iconeErreurChampManager = new IconeErreurChampManager();
        conteneur = new VerticalPanel();
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.setSpacing(5);

        final SuggestListBoxSingleProperties<IdentifiantLibelleGwt> propertiesIdentifiantLibelleGwt =
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

        construireBlocActionOrigine(propertiesIdentifiantLibelleGwt);
        construireBlocOpportunite(propertiesIdentifiantLibelleGwt);

        panelPrincipal = new FocusPanel();
        panelPrincipal.add(conteneur);
        panelPrincipal.setWidth(AppControllerConstants.POURCENT_100);

        final HorizontalPanel blocBoutons = construireBlocBoutons();

        // on en fait une popup minimisable
        minimizablePopup = new PopupMinimizable(this, viewConstants.titrePopup(), btnReduire);

        final VerticalPanel conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setWidth(OpportuniteCreationViewImplConstants.LARGEUR_POPUP);
        final AideComposant aideView = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_OPPORTUNITES_CREATION_GLOBAL, isAdmin);
        ajouterAideComposant(aideView);
        conteneurGlobal.add(aideView);
        conteneurGlobal.setCellHorizontalAlignment(aideView, HasAlignment.ALIGN_RIGHT);
        conteneurGlobal.add(panelPrincipal);
        conteneurGlobal.add(blocBoutons);
        conteneurGlobal.setCellHorizontalAlignment(blocBoutons, HasHorizontalAlignment.ALIGN_CENTER);

        this.setWidget(conteneurGlobal);
        this.addStyleName(SquareResources.INSTANCE.css().popupCreationOpportunite());
    }

    private void construireBlocActionOrigine(SuggestListBoxSingleProperties<IdentifiantLibelleGwt> propertiesIdentifiantLibelleGwt) {
        // Création des composants
        final Label ltitreNature = new Label(viewConstants.nature(), false);
        final Label ltitreCampagne = new Label(viewConstants.campagne(), false);
        slbsNature = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(propertiesIdentifiantLibelleGwt);
        slbsNature.ensureDebugId(viewDebugIdConstants.debugIdSlbsNature());
        aideslbsNature = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_OPPORTUNITES_CREATION_NATURE, isAdmin);
        ajouterAideComposant(aideslbsNature);

        slbsCampagne = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(propertiesIdentifiantLibelleGwt);
        slbsCampagne.ensureDebugId(viewDebugIdConstants.debugIdSlbsCampagne());
        aideslbsCampagne = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_OPPORTUNITES_CREATION_CAMPAGNE, isAdmin);
        final HorizontalPanel panelslbsCampagne = new HorizontalPanel();
        slbsCampagne.getElement().getStyle().setMarginRight(PANEL_COMPOSANT_SPACING, Unit.PX);
        panelslbsCampagne.add(slbsCampagne);
        panelslbsCampagne.add(aideslbsCampagne);
        ajouterAideComposant(aideslbsCampagne);
        final HorizontalPanel panelSlbNature = new HorizontalPanel();
        panelSlbNature.add(construireBlocIcone(slbsNature, "OpportuniteDto.idNature"));
        panelSlbNature.add(aideslbsNature);
        final FlexTable flexTable = new FlexTable();
        flexTable.setCellSpacing(3);
        flexTable.setWidth(AppControllerConstants.POURCENT_100);
        flexTable.setWidget(0, 0, ltitreNature);
        flexTable.setWidget(0, 1, panelSlbNature);
        flexTable.setWidget(1, 0, ltitreCampagne);
        flexTable.setWidget(1, 1, panelslbsCampagne);
        flexTable.getColumnFormatter().setWidth(0, OpportuniteCreationViewImplConstants.WIDTH_COL1);
        flexTable.getColumnFormatter().setWidth(1, OpportuniteCreationViewImplConstants.WIDTH_COL2);

        final CaptionPanel captionPanel = new CaptionPanel(viewConstants.titreActionOrigine());
        captionPanel.add(flexTable);
        conteneur.add(captionPanel);
    }

    private void construireBlocOpportunite(SuggestListBoxSingleProperties<IdentifiantLibelleGwt> propertiesIdentifiantLibelleGwt) {
        final Label ltitreRessource = new Label(viewConstants.titreRessource());
        final Label ltitreAgence = new Label(viewConstants.titreAgence());
        final Label ltitrePersonne = new Label(viewConstants.titrePersonne());
        final Label ltitreType = new Label(viewConstants.titreType());
        final Label ltitreObjet = new Label(viewConstants.titreObjet());
        final Label ltitreSousObjet = new Label(viewConstants.titreSousObjet());
        final SuggestListBoxSingleProperties<DimensionRessourceModel> propertiesRessources = new SuggestListBoxSingleProperties<DimensionRessourceModel>() {
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
        slbsAgence = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(propertiesIdentifiantLibelleGwt);
        slbsAgence.ensureDebugId(viewDebugIdConstants.debugIdSlbsAgence());
        aideslbsAgence = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_OPPORTUNITES_CREATION_AGENCE, isAdmin);
        ajouterAideComposant(aideslbsAgence);

        slbsRessource = new DecoratedSuggestListBoxSingle<DimensionRessourceModel>(propertiesRessources);
        slbsRessource.ensureDebugId(viewDebugIdConstants.debugIdSlbsRessource());
        aideslbsRessource = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_OPPORTUNITES_CREATION_RESSOURCE, isAdmin);
        ajouterAideComposant(aideslbsRessource);

        slbsType = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(propertiesIdentifiantLibelleGwt);
        slbsType.ensureDebugId(viewDebugIdConstants.debugIdSlbsType());
        aideslbsType = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_OPPORTUNITES_CREATION_TYPE, isAdmin);
        ajouterAideComposant(aideslbsType);

        slbsObjet = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(propertiesIdentifiantLibelleGwt);
        slbsObjet.ensureDebugId(viewDebugIdConstants.debugIdSlbsObjet());
        aideslbsObjet = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_OPPORTUNITES_CREATION_OBJET, isAdmin);
        ajouterAideComposant(aideslbsObjet);

        slbsSousObjet = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(propertiesIdentifiantLibelleGwt);
        slbsSousObjet.ensureDebugId(viewDebugIdConstants.debugIdSlbsSousObjet());
        aideslbsSousObjet = new AideComposant(AideComposantConstants.AIDE_PERSONNE_PHYSIQUE_OPPORTUNITES_CREATION_SOUS_OBJET, isAdmin);
        ajouterAideComposant(aideslbsSousObjet);
        final HorizontalPanel panelslbsSousObjet = new HorizontalPanel();
        slbsSousObjet.getElement().getStyle().setMarginRight(PANEL_COMPOSANT_SPACING, Unit.PX);
        panelslbsSousObjet.add(slbsSousObjet);
        panelslbsSousObjet.add(aideslbsSousObjet);

        final HorizontalPanel panelSlbsAgence = new HorizontalPanel();
        panelSlbsAgence.add(construireBlocIcone(slbsAgence, "OpportuniteDto.idAgence"));
        panelSlbsAgence.add(aideslbsAgence);

        final HorizontalPanel panelSlbsRessource = new HorizontalPanel();
        panelSlbsRessource.add(construireBlocIcone(slbsRessource, "OpportuniteDto.idRessource"));
        panelSlbsRessource.add(aideslbsRessource);

        final HorizontalPanel panelSlbsType = new HorizontalPanel();
        panelSlbsType.add(construireBlocIcone(slbsType, "OpportuniteDto.idType"));
        panelSlbsType.add(aideslbsType);

        final HorizontalPanel panelSlbsObjet = new HorizontalPanel();
        panelSlbsObjet.add(construireBlocIcone(slbsObjet, "OpportuniteDto.idObjet"));
        panelSlbsObjet.add(aideslbsObjet);

        lPersonne = new Label();

        final FlexTable flexTable = new FlexTable();
        flexTable.setCellSpacing(3);
        flexTable.setWidth(AppControllerConstants.POURCENT_100);
        flexTable.setWidget(0, 0, ltitreAgence);
        flexTable.setWidget(0, 1, panelSlbsAgence);
        flexTable.setWidget(1, 0, ltitreRessource);
        flexTable.setWidget(1, 1, panelSlbsRessource);
        flexTable.setWidget(2, 0, ltitrePersonne);
        flexTable.setWidget(2, 1, lPersonne);
        flexTable.setWidget(3, 0, ltitreType);
        flexTable.setWidget(3, 1, panelSlbsType);
        flexTable.setWidget(4, 0, ltitreObjet);
        flexTable.setWidget(4, 1, panelSlbsObjet);
        flexTable.setWidget(5, 0, ltitreSousObjet);
        flexTable.setWidget(5, 1, panelslbsSousObjet);
        flexTable.getColumnFormatter().setWidth(0, OpportuniteCreationViewImplConstants.WIDTH_COL1);
        flexTable.getColumnFormatter().setWidth(1, OpportuniteCreationViewImplConstants.WIDTH_COL2);

        final CaptionPanel captionPanel = new CaptionPanel(viewConstants.titreOpportunite());
        captionPanel.add(flexTable);
        conteneur.add(captionPanel);
    }

    private HorizontalPanel construireBlocBoutons() {
        final HorizontalPanel horizontalPanelBoutons = new HorizontalPanel();
        btnAnnuler = new DecoratedButton(viewConstants.libelleAnnuler());
        btnAnnuler.ensureDebugId(viewDebugIdConstants.debugIdBtnAnnuler());
        btnEnregistrer = new DecoratedButton(viewConstants.libelleEnregistrer());
        btnEnregistrer.ensureDebugId(viewDebugIdConstants.debugIdBtnEnregistrer());
        btnReduire = new DecoratedButton(viewConstants.reduire());
        btnReduire.ensureDebugId(viewDebugIdConstants.debugIdBtnReduire());
        horizontalPanelBoutons.add(btnEnregistrer);
        horizontalPanelBoutons.add(btnReduire);
        horizontalPanelBoutons.add(btnAnnuler);
        horizontalPanelBoutons.setSpacing(5);
        return horizontalPanelBoutons;
    }

    /**
     * Construit un horizontal panel contenant le widget et l'icone d'erreur.
     * @param composant le composant
     * @param nomChamp le nom du champ pour la gestion des erreurs
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        panel.add(icone);
        panel.setCellVerticalAlignment(icone, HasAlignment.ALIGN_MIDDLE);
        return panel;
    }

    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);

    }

    @Override
    public Widget asWidget() {
        return null;
    }

    @Override
    public void showPopup() {
        this.show();
    }

    @Override
    public void hidePopup() {
        this.hide();
        clearPopup();
    }

    private void clearPopup() {
        lPersonne.setText("");
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stopAll();
    }

    @Override
    public HasText getlPersonne() {
        return lPersonne;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbsNatureValue() {
        return slbsNature;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbsCampagneValue() {
        return slbsCampagne;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbsAgenceValue() {
        return slbsAgence;
    }

    @Override
    public HasValue<DimensionRessourceModel> getSlbsRessourceValue() {
        return slbsRessource;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbsTypeValue() {
        return slbsType;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbsObjetValue() {
        return slbsObjet;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbsSousObjetValue() {
        return slbsSousObjet;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsNature() {
        return slbsNature;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsCampagne() {
        return slbsCampagne;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsAgence() {
        return slbsAgence;
    }

    @Override
    public DecoratedSuggestListBoxSingle<DimensionRessourceModel> getSlbsCommercial() {
        return slbsRessource;
    }

    @Override
    public HasSuggestListBoxHandler<DimensionRessourceModel> getSlbsRessource() {
        return slbsRessource;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsType() {
        return slbsType;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsObjet() {
        return slbsObjet;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbsSousObjet() {
        return slbsSousObjet;
    }

    @Override
    public HasClickHandlers getBtnAnnuler() {
        return btnAnnuler;
    }

    @Override
    public HasClickHandlers getBtnEnregistrer() {
        return btnEnregistrer;
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public void criserBoutonEnregister() {
        btnEnregistrer.setEnabled(false);
    }

    @Override
    public void activerBoutonEnregister() {
        btnEnregistrer.setEnabled(true);
    }

    @Override
    public void setFocus() {
        slbsNature.setFocus(true);
    }

    @Override
    public HasAllKeyHandlers getAllKeyHandlersFocusPanel() {
        return panelPrincipal;
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

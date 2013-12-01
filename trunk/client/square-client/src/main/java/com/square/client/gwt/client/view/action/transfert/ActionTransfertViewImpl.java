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
package com.square.client.gwt.client.view.action.transfert;

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
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.model.DimensionRessourceModel;
import com.square.client.gwt.client.presenter.action.ActionTransfertPresenter.ActionTransfertView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Vue de la popup de transfert d'action.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ActionTransfertViewImpl extends Popup implements ActionTransfertView {

    /** View constants. */
    private static ActionTransfertViewImplConstants viewConstants = (ActionTransfertViewImplConstants) GWT.create(ActionTransfertViewImplConstants.class);

    /** View debug id constants. */
    private static ActionTransfertViewImplDebugIdConstants viewDebugIdConstants =
        (ActionTransfertViewImplDebugIdConstants) GWT.create(ActionTransfertViewImplDebugIdConstants.class);

    /** Icon manager. */
    private IconeErreurChampManager iconeErreurChampManager;

    /** Le conteneur principale. */
    private VerticalPanel conteneurGlobal;

    /** Bouton annuler. */
    private DecoratedButton btnAnnuler;

    /** Bouton Transferer. */
    private DecoratedButton btnTransferer;

    private FocusPanel focusPopupPanel;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbAgence;
    
    private AideComposant aideslbAgence;

    private DecoratedSuggestListBoxSingle<DimensionRessourceModel> slbCommercial;
    
    private AideComposant aideslbCommercial;
    
    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    private List<AideComposant> listComposantHavingAide = new ArrayList<AideComposant>();

    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    private boolean isAdmin;

    /**
     * Constructeur.
     */
    public ActionTransfertViewImpl(boolean isAdmin) {
        super(viewConstants.popupTitle(), false, false, true);
        this.isAdmin = isAdmin;
        iconeErreurChampManager = new IconeErreurChampManager();
        conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setSpacing(10);
        conteneurGlobal.setWidth(ActionTransfertViewImplConstants.LARGEUR_POPUP);
        construireBlocListes();

        focusPopupPanel = new FocusPanel();
        focusPopupPanel.setWidth(AppControllerConstants.POURCENT_100);
        focusPopupPanel.add(conteneurGlobal);

        final HorizontalPanel conteneurBoutons = construireBlocBouton();
        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.add(focusPopupPanel);
        conteneur.add(conteneurBoutons);
        conteneur.setCellHorizontalAlignment(conteneurBoutons, HasHorizontalAlignment.ALIGN_CENTER);

        this.setWidget(conteneur, 0);
        this.addStyleName(SquareResources.INSTANCE.css().popupCreationAction());
    }

    /** Construction du bloc de boutons. */
    private void construireBlocListes() {
        // Agence
        final SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbAgenceProperties = new SuggestListBoxSingleProperties<IdentifiantLibelleGwt>() {
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
        };
        // Commercial
        final SuggestListBoxSingleProperties<DimensionRessourceModel> slbCommercialProperties = new SuggestListBoxSingleProperties<DimensionRessourceModel>() {
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
        };

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.cibleTransfert());
        slbAgence = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbAgenceProperties);
        slbAgence.ensureDebugId(viewDebugIdConstants.debugIdSlbAgence());
        aideslbAgence = new AideComposant(AideComposantConstants.AIDE_ACTION_TRANSFERT_AGENCE, isAdmin);
        ajouterAideComposant(aideslbAgence);

        slbCommercial = new DecoratedSuggestListBoxSingle<DimensionRessourceModel>(slbCommercialProperties);
        slbCommercial.ensureDebugId(viewDebugIdConstants.debugIdSlbCommercial());
        aideslbCommercial = new AideComposant(AideComposantConstants.AIDE_ACTION_TRANSFERT_COMMERCIAL, isAdmin);
        ajouterAideComposant(aideslbCommercial);
        final  HorizontalPanel panelslbCommercial = new HorizontalPanel();
        panelslbCommercial.setSpacing(5);
        panelslbCommercial.add(slbCommercial);
        panelslbCommercial.add(aideslbCommercial);
       

        final FlexTable fpInformation = new FlexTable();
        fpInformation.setWidth(AppControllerConstants.POURCENT_100);
        fpInformation.setCellPadding(5);
        final Label agence = creerLibelle(viewConstants.agence());
        final Label commercial = creerLibelle(viewConstants.commercial());

        fpInformation.setWidget(0, 0, agence);
        fpInformation.setWidget(0, 1, construireBlocIcone(slbAgence, "ActionRechercheDto.agence.id",aideslbAgence));
        fpInformation.setWidget(1, 0, commercial);
        fpInformation.setWidget(1, 1, panelslbCommercial);

        fpInformation.getColumnFormatter().setWidth(0, ActionTransfertViewImplConstants.LARGEUR_COL_LIBELLE);

        fieldSetPanel.add(fpInformation);
        conteneurGlobal.add(fieldSetPanel);
    }

    private Label creerLibelle(final String libelle) {
        final Label label = new Label(libelle, false);
        label.setHorizontalAlignment(HasAlignment.ALIGN_RIGHT);
        label.addStyleName(SquareResources.INSTANCE.css().libelleMoteurRecherche());
        return label;
    }

    /** Construction du bloc de boutons. */
    private HorizontalPanel construireBlocBouton() {
        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        btnTransferer = new DecoratedButton(viewConstants.transferer());
        btnTransferer.ensureDebugId(viewDebugIdConstants.debugIdBtnTransferer());
        btnAnnuler = new DecoratedButton(viewConstants.annuler());
        btnAnnuler.ensureDebugId(viewDebugIdConstants.debugIdBtnAnnuler());
        horizontalPanel.add(btnTransferer);
        horizontalPanel.add(btnAnnuler);
        horizontalPanel.setSpacing(5);
        return horizontalPanel;
    }

    /**
     * Construit un bloc avec un label et un champ pour l'affichage.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        panel.add(icone);
        panel.setCellVerticalAlignment(icone, HasVerticalAlignment.ALIGN_MIDDLE);
        return panel;
    }
    /** construire les listes des aides. */
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
    public void hidePopup() {
        this.hide();
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stop();
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
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
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    @Override
    public HasAllKeyHandlers getAllKeyHandlersFocusPanel() {
        return focusPopupPanel;
    }

    @Override
    public HasClickHandlers getBtnAnnuler() {
        return btnAnnuler;
    }

    @Override
    public HasClickHandlers getBtnTransferer() {
        return btnTransferer;
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public void activerFocusAgence(boolean actif) {
        slbAgence.setFocus(actif);
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getAgence() {
        return slbAgence;
    }

    @Override
    public HasValueChangeHandlers<IdentifiantLibelleGwt> getChangeAgence() {
        return slbAgence;
    }

    @Override
    public HasValue<DimensionRessourceModel> getCommercial() {
        return slbCommercial;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSuggestAgence() {
        return slbAgence;
    }

    @Override
    public HasSuggestListBoxHandler<DimensionRessourceModel> getSuggestCommercial() {
        return slbCommercial;
    }

    @Override
    public ActionTransfertViewImplConstants getViewConstants() {
        return viewConstants;
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

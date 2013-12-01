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
package com.square.client.gwt.client.view.campagne.creation;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.presenter.campagne.CampagneCreationPresenter.CampagneCreationView;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.popups.minimizable.IsMinimizable;
import com.square.composants.graphiques.lib.client.popups.minimizable.PopupMinimizable;

/**
 * Vue de la page de création de campagne.
 * @author cblanchard - SCUB
 */
public class CampagneCreationViewImpl extends Popup implements CampagneCreationView {

    /** View constants. */
    private static CampagneCreationViewImplConstants viewConstants = (CampagneCreationViewImplConstants) GWT.create(CampagneCreationViewImplConstants.class);

    /** View constants pour debugId. */
    private static CampagneCreationViewImplDebugIdConstants viewDebugIdConstants =
        (CampagneCreationViewImplDebugIdConstants) GWT.create(CampagneCreationViewImplDebugIdConstants.class);

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    /** Textbox pour saisir le libelle. */
    private DecoratedTextBox tbLibelle;

    /** aide Textbox pour saisir le libelle. */
    private AideComposant aideTbLibelle;

    /** SuggestListBox des types. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbType;

    /** aide pour pour SuggestListBox des types. */
    private AideComposant aideSlbType;

    /** Calendrier pour la date de début. */
    private DecoratedCalendrierDateBox cdbDateDebut;

    /** aide pour Calendrier pour la date de début. */
    private AideComposant aideCdbDateDebut;

    /** Calendrier pour la date de fin. */
    private DecoratedCalendrierDateBox cdbDateFin;

    /** aide pour Calendrier pour la date de fin. */
    private AideComposant aideCdbDateFin;

    /** Boutton enregistrer. */
    DecoratedButton btnEnregistrer;

    private DecoratedButton btnReduire;

    /** Boutton annuler. */
    DecoratedButton btnAnnuler;

    /** Vertical panel général. */
    private VerticalPanel conteneurGlobal;

    /** Vertical panel pour les infos de créations. */
    private VerticalPanel conteneurInfos;

    /** Horizontal panel pour les boutons. */
    private HorizontalPanel conteneurBoutons;

    private FocusPanel focusPopupPanel;

    private PopupMinimizable minimizablePopup;

    /** listes des Events relatifs à l'aide en ligne. */
    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    private List<AideComposant> listComposantHavingAide = new ArrayList<AideComposant>();

    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    /** mode de connexion admin/normal . */
    private boolean isAdmin;

    /**
     * Constructeur.
     */
    public CampagneCreationViewImpl(boolean isAdmin) {
        super(viewConstants.popupTitle(), false, false, true);
        iconeErreurChampManager = new IconeErreurChampManager();
        this.isAdmin = isAdmin;
        conteneurGlobal = new VerticalPanel();

        construireBlocCreation();
        construireBlocBouton();

        // on en fait une popup minimisable
        minimizablePopup = new PopupMinimizable(this, viewConstants.popupTitle(), btnReduire);

        focusPopupPanel = new FocusPanel();
        focusPopupPanel.setWidth(AppControllerConstants.POURCENT_100);
        focusPopupPanel.add(conteneurInfos);
        conteneurInfos.setWidth(AppControllerConstants.POURCENT_100);

        conteneurGlobal.add(focusPopupPanel);
        conteneurGlobal.add(conteneurBoutons);
        conteneurGlobal.setCellHorizontalAlignment(conteneurBoutons, HasAlignment.ALIGN_CENTER);

        conteneurGlobal.setWidth(AppControllerConstants.POURCENT_100);
        this.setWidget(conteneurGlobal);
        this.addStyleName(SquareResources.INSTANCE.css().popupCreationCampagne());
    }

    /** Construit le panel avec les informations de création. */
    private void construireBlocCreation() {
        conteneurInfos = new VerticalPanel();
        conteneurInfos.setSpacing(10);
        tbLibelle = new DecoratedTextBox();
        aideTbLibelle = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_CREATION_LIBELLE, isAdmin);
        ajouterAideComposant(aideTbLibelle);
        tbLibelle.ensureDebugId(viewDebugIdConstants.debugIdTbLibelle());

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
        slbType = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbType.ensureDebugId(viewDebugIdConstants.debugIdSlbType());
        aideSlbType = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_CREATION_TYPE, isAdmin);
        ajouterAideComposant(aideSlbType);
        cdbDateDebut = new DecoratedCalendrierDateBox(true);
        cdbDateDebut.ensureDebugId(viewDebugIdConstants.debugIdCdbDateDebut());
        aideCdbDateDebut = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_CREATION_DATE_DEBUT, isAdmin);
        ajouterAideComposant(aideCdbDateDebut);
        cdbDateFin = new DecoratedCalendrierDateBox(true);
        cdbDateFin.ensureDebugId(viewDebugIdConstants.debugIdCdbDateFin());
        aideCdbDateFin = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_CREATION_DATE_FIN, isAdmin);
        ajouterAideComposant(aideCdbDateFin);
        final CaptionPanel captionPanel = new CaptionPanel(viewConstants.infoBloc());
        final FlexTable flexTable = new FlexTable();
        flexTable.setWidget(0, 0, new Label(viewConstants.libelle()));
        flexTable.setWidget(0, 1, construireBlocIcone(tbLibelle, "CampagneDto.libelle", aideTbLibelle));
        flexTable.setWidget(1, 0, new Label(viewConstants.type()));
        flexTable.setWidget(1, 1, construireBlocIcone(slbType, "CampagneDto.type", aideSlbType));
        flexTable.setWidget(2, 0, new Label(viewConstants.dateDebut()));
        flexTable.setWidget(2, 1, construireBlocIcone(cdbDateDebut, "CampagneDto.dateDebut", aideCdbDateDebut));
        flexTable.setWidget(3, 0, new Label(viewConstants.dateFin()));
        flexTable.setWidget(3, 1, construireBlocIcone(cdbDateFin, "CampagneDto.dateFin", aideCdbDateFin));
        flexTable.setCellSpacing(5);
        flexTable.setWidth(AppControllerConstants.POURCENT_100);
        captionPanel.add(flexTable);
        conteneurInfos.add(captionPanel);
    }

    /** Construit le panel avec les boutons. */
    private void construireBlocBouton() {
        conteneurBoutons = new HorizontalPanel();
        btnEnregistrer = new DecoratedButton(viewConstants.creationCampagne());
        btnEnregistrer.ensureDebugId(viewDebugIdConstants.debugIdBtnEnregistrer());
        btnReduire = new DecoratedButton(viewConstants.reduire());
        btnReduire.ensureDebugId(viewDebugIdConstants.debugIdBtnReduire());
        btnAnnuler = new DecoratedButton(viewConstants.annuler());
        btnAnnuler.ensureDebugId(viewDebugIdConstants.debugIdBtnAnnuler());
        conteneurBoutons.add(btnEnregistrer);
        conteneurBoutons.add(btnReduire);
        conteneurBoutons.add(btnAnnuler);
        conteneurBoutons.setSpacing(5);
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

    private void ajouterAideComposant(AideComposant aideComposant) {
        listComposantAide.add(aideComposant);
        listDemandeAideEventHandler.add(aideComposant);
        listUpdateAideEventHandler.add(aideComposant);

    }

    @Override
    public void showPopup() {
        this.show();
    }

    @Override
    public void clearPopup() {
        tbLibelle.setValue("");
        slbType.clean();
        cdbDateDebut.clean();
        cdbDateFin.clean();
    }

    @Override
    public Widget asWidget() {
        return null;
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(config);
    }

    @Override
    public void hidePopup() {
        this.hide();
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateDebut() {
        return cdbDateDebut;
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateFin() {
        return cdbDateFin;
    }

    @Override
    public DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> getSlbType() {
        return slbType;
    }

    @Override
    public DecoratedTextBox getTbLibelle() {
        return tbLibelle;
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
    public void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration) {
        LoadingPopup.afficher(loadingPopupConfiguration);
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public CampagneCreationViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stop();
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

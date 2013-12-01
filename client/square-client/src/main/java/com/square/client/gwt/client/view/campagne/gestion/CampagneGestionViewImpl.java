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
package com.square.client.gwt.client.view.campagne.gestion;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.richtextarea.RichTextPanel;
import org.scub.foundation.framework.gwt.module.client.util.composants.richtextarea.RichTextToolbar;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;
import com.square.client.gwt.client.composant.bloc.ContenuOnglet;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.presenter.campagne.CampagneGestionPresenter;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.util.AideComposantConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Vue pour la gestion d'une campagne.
 * @author cblanchard - SCUB
 */
public class CampagneGestionViewImpl extends Composite implements CampagneGestionPresenter.CampagneGestionView {

    /** View constants. */
    private static CampagneGestionViewImplConstants viewConstants = (CampagneGestionViewImplConstants) GWT.create(CampagneGestionViewImplConstants.class);

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    /** Conteneur principal. */
    private VerticalPanel conteneur;

    /** Label du code. */
    private Label lCode;

    /** Label du créateur. */
    private Label lCreateur;

    /** Label du libelle. */
    private DecoratedTextBox tbLibelle;

    /** aide Textbox pour saisir le libelle. */
    private AideComposant aideTbLibelle;

    /** SuggestListBox du statut. */
    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbStatut;

    /** aide pour SuggestListBox du statut. */
    private AideComposant aideSlbStatut;

    /** Label du type. */
    private Label lType;

    /** Date de début. */
    private DecoratedCalendrierDateBox cdbDateDebut;

    /** aide pour Date de début. */
    private AideComposant aideCdbDateDebut;

    /** Date de fin. */
    private DecoratedCalendrierDateBox cdbDateFin;

    /** aide pour Date de fin. */
    private AideComposant aideCdbDateFin;

    /** Descriptif zone de text. */
    private RichTextArea rtaDescriptif;

    /** aide pour Descriptif zone de text. */
    private AideComposant aidertaDescriptif;

    /** Descriptif de la campagne en mode read only. */
 
    private HTML taDescriptif;

    /** aide pour Descriptif zone de text. */
    private AideComposant aidetaDescriptif;

    /** Descriptif toolbar. */
    private RichTextToolbar rttToolbar;

    /** aide pour Descriptif zone de text. */
    private AideComposant aiderttToolbar;

    /** Zone de texte pour l'action. */
    private RichTextPanel rtpCommmentaire;

    /** aide pour Zone de texte pour l'action. */
    private AideComposant aidertpCommmentaire;

    /** Bouton Enregistrer. */
    private DecoratedButton btnEnregistrer;

    /** Bouton Annuler. */
    private DecoratedButton btnAnnuler;

    /**
     * Listes des Events des composants d'aides.
     */
    private List<AideComposant> listComposantAide = new ArrayList<AideComposant>();

    private List<AideComposant> listComposantHavingAide = new ArrayList<AideComposant>();

    private List<HasUpdateAideEventHandler> listUpdateAideEventHandler = new ArrayList<HasUpdateAideEventHandler>();

    private List<HasDemandeAideEventHandler> listDemandeAideEventHandler = new ArrayList<HasDemandeAideEventHandler>();

    /**
     * mode connexion :admin/normal.
     */
    private boolean isAdmin;

    /** Constructeur. */
    public CampagneGestionViewImpl(boolean isAdmin) {
        iconeErreurChampManager = new IconeErreurChampManager();
        this.isAdmin = isAdmin;
        conteneur = new VerticalPanel();
        conteneur.setWidth(AppControllerConstants.POURCENT_100);
        conteneur.setSpacing(10);
        construirePage();
        this.initWidget(new ContenuOnglet(conteneur));
        this.addStyleName(SquareResources.INSTANCE.css().campagneGestion());
    }

    private void construirePage() {
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

        lCode = new Label();

        lCreateur = new Label();
        tbLibelle = new DecoratedTextBox();
        aideTbLibelle = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_GESTION_LIBELLE, isAdmin);
        HorizontalPanel paneltbLibelle = new HorizontalPanel();
        paneltbLibelle.setSpacing(5);
        paneltbLibelle.add(tbLibelle);
        paneltbLibelle.add(aideTbLibelle);
        ajouterAideComposant(aideTbLibelle);
        slbStatut = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        aideSlbStatut = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_GESTION_STATUT, isAdmin);
        ajouterAideComposant(aideSlbStatut);
        lType = new Label();
        cdbDateDebut = new DecoratedCalendrierDateBox(true);
        aideCdbDateDebut = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_GESTION_DATE_DEBUT, isAdmin);
        ajouterAideComposant(aideCdbDateDebut);
        cdbDateFin = new DecoratedCalendrierDateBox(true);
        aideCdbDateFin = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_GESTION_DATE_FIN, isAdmin);
        ajouterAideComposant(aideCdbDateFin);
        taDescriptif = new HTML();
        taDescriptif.addStyleName(SquareResources.INSTANCE.css().descriptifReadOnly());
        taDescriptif.setVisible(false);
        aidetaDescriptif = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_GESTION_DESCRIPTIF, isAdmin);
        ajouterAideComposant(aidetaDescriptif);
        rtaDescriptif = new RichTextArea();
        rtaDescriptif.setWidth(AppControllerConstants.POURCENT_100);
        aidertaDescriptif = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_GESTION_DECRIPTIF_BIS, isAdmin);
        ajouterAideComposant(aidertaDescriptif);
        rttToolbar = new RichTextToolbar(rtaDescriptif, RichTextToolbar.MODE_EXTENDED);
        rttToolbar.setWidth(AppControllerConstants.POURCENT_100);
        aiderttToolbar = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_GESTION_TOOLBAR, isAdmin);
        ajouterAideComposant(aiderttToolbar);
        rtpCommmentaire = new RichTextPanel(rttToolbar, rtaDescriptif);
        rtpCommmentaire.setWidth(AppControllerConstants.POURCENT_100);
        aidertpCommmentaire = new AideComposant(AideComposantConstants.AIDE_CAMPAGNE_GESTION_COMMENTAIRE, isAdmin);
        btnAnnuler = new DecoratedButton(viewConstants.annuler());
        btnEnregistrer = new DecoratedButton(viewConstants.enregistrer());

        final CaptionPanel captionPanelInformation = new CaptionPanel(viewConstants.informations());
        final FlexTable conteneurInformations = new FlexTable();
        conteneurInformations.setWidth(AppControllerConstants.POURCENT_100);
        conteneurInformations.setWidget(0, 0, new Label(viewConstants.code(), false));
        conteneurInformations.setWidget(0, 1, lCode);
        conteneurInformations.setWidget(0, 2, new Label(viewConstants.libelle(), false));
        conteneurInformations.setWidget(0, 3, paneltbLibelle);
        conteneurInformations.setWidget(1, 0, new Label(viewConstants.statut(), false));
        conteneurInformations.setWidget(1, 1, construireBlocIcone(slbStatut, "CampagneDto.statut", aideSlbStatut));
        conteneurInformations.setWidget(1, 2, new Label(viewConstants.type(), false));
        conteneurInformations.setWidget(1, 3, lType);
        conteneurInformations.setWidget(1, 4, new Label(viewConstants.createur(), false));
        conteneurInformations.setWidget(1, 5, lCreateur);
        conteneurInformations.setWidget(2, 0, new Label(viewConstants.dateDebut(), false));
        conteneurInformations.setWidget(2, 1, construireBlocIcone(cdbDateDebut, "CampagneDto.dateDebut", aideCdbDateDebut));
        conteneurInformations.setWidget(2, 2, new Label(viewConstants.dateFin(), false));
        conteneurInformations.setWidget(2, 3, construireBlocIcone(cdbDateFin, "CampagneDto.dateFin", aideCdbDateFin));
        conteneurInformations.getColumnFormatter().setWidth(0, "10%");
        conteneurInformations.getColumnFormatter().setWidth(1, "27%");
        conteneurInformations.getColumnFormatter().setWidth(2, "8%");
        conteneurInformations.getColumnFormatter().setWidth(3, "27%");
        conteneurInformations.getColumnFormatter().setWidth(4, "8%");
        conteneurInformations.getColumnFormatter().setWidth(5, "20%");
        captionPanelInformation.add(conteneurInformations);

        // Contruction de la zone de text
        final CaptionPanel captionPanelDescription = new CaptionPanel(viewConstants.description());
        final Grid grid = new Grid(3, 1);
        grid.setWidget(0, 0, rtpCommmentaire);
        grid.setWidget(2, 0, taDescriptif);
        grid.setWidth(AppControllerConstants.POURCENT_100);
        captionPanelDescription.add(grid);

        // Construction de la bare de bouton
        final HorizontalPanel horizontalPanelBoutons = new HorizontalPanel();
        horizontalPanelBoutons.add(btnEnregistrer);
        horizontalPanelBoutons.add(btnAnnuler);
        horizontalPanelBoutons.setSpacing(5);

        conteneur.add(captionPanelInformation);
        conteneur.add(captionPanelDescription);
        conteneur.add(horizontalPanelBoutons);
        conteneur.setCellHorizontalAlignment(horizontalPanelBoutons, HasAlignment.ALIGN_RIGHT);
    }

    /**
     * Construit un bloc avec un champ pour l'affichage.
     */
    private HorizontalPanel construireBlocIcone(final Widget composant, final String nomChamp) {
        final IconeErreurChamp icone = iconeErreurChampManager.createInstance(nomChamp, composant);
        final HorizontalPanel panel = new HorizontalPanel();
        panel.add(composant);
        panel.add(icone);
        panel.setCellVerticalAlignment(icone, HasAlignment.ALIGN_MIDDLE);
        panel.setSpacing(5);
        return panel;
    }

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
    public void onRpcServiceFailure(ErrorPopupConfiguration config) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(config);
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration) {
        LoadingPopup.afficher(loadingPopupConfiguration);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    /**
     * Renvoi la valeur de lCode.
     * @return lCode
     */
    @Override
    public Label getlCode() {
        return lCode;
    }

    /**
     * Renvoi la valeur de lLibelle.
     * @return lLibelle
     */
    @Override
    public HasValue<String> getTbLibelle() {
        return tbLibelle;
    }

    /**
     * Renvoi la valeur de slbStatut.
     * @return slbStatut
     */
    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbStatut() {
        return slbStatut;
    }

    /**
     * Renvoi la valeur de slbType.
     * @return slbType
     */
    @Override
    public Label getlType() {
        return lType;
    }

    /**
     * Renvoi la valeur de cdbDateDebut.
     * @return cdbDateDebut
     */
    @Override
    public DecoratedCalendrierDateBox getCdbDateDebut() {
        return cdbDateDebut;
    }

    /**
     * Renvoi la valeur de cdbDateFin.
     * @return cdbDateFin
     */
    @Override
    public DecoratedCalendrierDateBox getCdbDateFin() {
        return cdbDateFin;
    }

    /**
     * Renvoi la valeur de rtaDescriptif.
     * @return rtaDescriptif
     */
    @Override
    public RichTextArea getRtaDescriptif() {
        return rtaDescriptif;
    }

    /**
     * Renvoi la valeur de rttToolbar.
     * @return rttToolbar
     */
    @Override
    public RichTextToolbar getRttToolbar() {
        return rttToolbar;
    }

    /**
     * Renvoi la valeur de btnEnregistrer.
     * @return btnEnregistrer
     */
    @Override
    public DecoratedButton getBtnEnregistrer() {
        return btnEnregistrer;
    }

    /**
     * Renvoi la valeur de btnAnnuler.
     * @return btnAnnuler
     */
    @Override
    public DecoratedButton getBtnAnnuler() {
        return btnAnnuler;
    }

    /**
     * Renvoi les constantes de la vue.
     * @return viewConstants
     */
    @Override
    public CampagneGestionViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stopAll();
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getStatut() {
        return slbStatut;
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
    public HasHTML getTaDescriptif() {
        return taDescriptif;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnabled(boolean enabled) {
        tbLibelle.setEnabled(enabled);
        cdbDateDebut.setEnabled(enabled);
        cdbDateFin.setEnabled(enabled);
        rttToolbar.setVisible(enabled);
        rttToolbar.setEnabled(enabled);
        rtaDescriptif.setVisible(enabled);
        rtaDescriptif.setEnabled(enabled);
        taDescriptif.setVisible(!enabled);
        slbStatut.setEnabled(enabled);
        btnAnnuler.setEnabled(enabled);
        btnEnregistrer.setEnabled(enabled);
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

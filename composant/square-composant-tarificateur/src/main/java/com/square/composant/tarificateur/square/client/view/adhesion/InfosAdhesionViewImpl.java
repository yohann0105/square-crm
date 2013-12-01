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
package com.square.composant.tarificateur.square.client.view.adhesion;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
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
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosPaiementModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosRibModel;
import com.square.composant.tarificateur.square.client.model.personne.AssureSocialModel;
import com.square.composant.tarificateur.square.client.presenter.adhesion.InfosAdhesionPresenter.BlocInfoSanteComplete;
import com.square.composant.tarificateur.square.client.presenter.adhesion.InfosAdhesionPresenter.BlocInfoSanteSimple;
import com.square.composant.tarificateur.square.client.presenter.adhesion.InfosAdhesionPresenter.InfosAdhesionView;
import com.square.composant.tarificateur.square.client.view.ligne.devis.LigneDevisViewImplConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.popups.minimizable.IsMinimizable;
import com.square.composants.graphiques.lib.client.popups.minimizable.PopupMinimizable;

/**
 * Popup qui permet la saisie d'informations supplémentaires nécessaires à une adhésion.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class InfosAdhesionViewImpl extends Popup implements InfosAdhesionView {

    /** Constantes associées à la vue. */
    private InfosAdhesionViewImplConstants viewConstants;
    
    /** Constantes autres. */
    private LigneDevisViewImplConstants lignesDevisConstants;

    /** Panel conteneur de l'ensemble de la vue. */
    private VerticalPanel container;

    /** Gestion des icones d'erreur. */
    private IconeErreurChampManager iconeErreurChampManager;

    /** Bouton pour annuler les informations saisies. */
    private DecoratedButton btnAnnuler;

    /** Bouton pour enregistrer les informations saisies. */
    private DecoratedButton btnEnregistrer;

    private DecoratedButton btnReduire;

    private Label lValeurMontantPaiement;

    private Label lValeurNumeroTransaction;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> lbModesPaiement;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> lbPeriodicitesPaiement;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> lbJoursPaiement;

    private DecoratedCalendrierDateBox calDateSignature;

    private CheckBox cbTeletransmission;

    private DecoratedTextBox tbRibCodeBanque;

    private DecoratedTextBox tbRibCodeGuichet;

    private DecoratedTextBox tbRibCodeCompte;

    private DecoratedTextBox tbRibCle;

    private Label lDateClicBA;

    private Label lDateTelechargementBA;

    private SuggestListBoxSingleProperties<IdentifiantLibelleGwt> slbIdentifiantLibelleProperties;

    private FocusPanel focusPanel;

    private PopupMinimizable minimizablePopup;

    /** Compteur du nombre de panels supplémentaires affichés dans la popup d'info d'adhésion. */
    private int nbPanelSupplementaires;

    private NumberFormat numberFormat = NumberFormat.getFormat("#,##0.00");

    /**
     * Constructeur.
     * @param autoHide précise si on souhaite cacher la popup en cliquant en dehors de celle ci.
     * @param modal modal.
     * @param hasGlassPanel ajoute un glassPanel.
     */
    public InfosAdhesionViewImpl(boolean autoHide, boolean modal, boolean hasGlassPanel) {
        super(ComposantTarificateur.CONSTANTS.titrePopupAdhesion(), autoHide, modal, hasGlassPanel);
        viewConstants = (InfosAdhesionViewImplConstants) GWT.create(InfosAdhesionViewImplConstants.class);
        lignesDevisConstants = GWT.create(LigneDevisViewImplConstants.class);
        iconeErreurChampManager = new IconeErreurChampManager();
        nbPanelSupplementaires = 0;
        container = new VerticalPanel();
        container.setSpacing(10);
        container.setWidth(ComposantTarificateurConstants.POURCENT_100);

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

        construirePanelInfosPaiement();
        construirePanelInfosSignatureNumerique();
        construirePanelOptions();

        focusPanel = new FocusPanel(container);
        focusPanel.setWidth(ComposantTarificateurConstants.POURCENT_100);

        final VerticalPanel conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setWidth(InfosAdhesionViewImplConstants.LARGEUR_POPUP);

        conteneurGlobal.add(focusPanel);
        construirePanelBoutons(conteneurGlobal);

        // on en fait une popup minimisable
        minimizablePopup = new PopupMinimizable(this, ComposantTarificateur.CONSTANTS.titrePopupAdhesion(), btnReduire);

        this.setWidget(conteneurGlobal, 0);
        this.addStyleName(ComposantTarificateur.RESOURCES.css().popupInfosAdhesion());
    }

    /**
     * Construit le panel contenant les informations de paiement.
     */
    private void construirePanelInfosPaiement() {
        final Label lDateSignature = new Label(viewConstants.labelDateSignature(), false);
        final Label lMontantPaiement = new Label(viewConstants.labelMontantPaiement(), false);
        final Label lNumeroTransaction = new Label(viewConstants.labelNumeroTransaction(), false);
        final Label lModePaiement = new Label(viewConstants.labelModePaiement(), false);
        final Label lPeriodicitePaiement = new Label(viewConstants.labelPeriodicitePaiement(), false);
        final Label lJourPaiement = new Label(viewConstants.labelJourPaiement(), false);
        final Label lRIB = new Label(viewConstants.lRib(), false);

        tbRibCodeBanque = new DecoratedTextBox();
        tbRibCodeGuichet = new DecoratedTextBox();
        tbRibCodeCompte = new DecoratedTextBox();
        tbRibCle = new DecoratedTextBox();

        lValeurMontantPaiement = new Label();
        lValeurNumeroTransaction = new Label();
        lbModesPaiement = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        lbPeriodicitesPaiement = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        lbJoursPaiement = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        calDateSignature = new DecoratedCalendrierDateBox(true);

        tbRibCodeBanque.setMaxLength(5);
        tbRibCodeGuichet.setMaxLength(5);
        tbRibCodeCompte.setMaxLength(11);
        tbRibCle.setMaxLength(2);
        tbRibCodeBanque.setWidth("45px");
        tbRibCodeGuichet.setWidth("45px");
        tbRibCodeCompte.setWidth("85px");
        tbRibCle.setWidth("20px");

        final HorizontalPanel conteneurRib = new HorizontalPanel();
        conteneurRib.add(tbRibCodeBanque);
        conteneurRib.add(new HTML("&nbsp;"));
        conteneurRib.add(tbRibCodeGuichet);
        conteneurRib.add(new HTML("&nbsp;"));
        conteneurRib.add(tbRibCodeCompte);
        conteneurRib.add(new HTML("&nbsp;"));
        conteneurRib.add(tbRibCle);

        final FlexTable ftInfosPaiement = new FlexTable();
        ftInfosPaiement.setWidth(ComposantTarificateurConstants.POURCENT_100);
        ftInfosPaiement.setCellSpacing(3);
        ftInfosPaiement.setWidget(0, 0, lDateSignature);
        ftInfosPaiement.setWidget(0, 1, construireBlocIcone(calDateSignature, "dateSignature"));
        ftInfosPaiement.setWidget(0, 2, lModePaiement);
        ftInfosPaiement.setWidget(0, 3, construireBlocIcone(lbModesPaiement, "idMoyenPaiement"));
        ftInfosPaiement.setWidget(1, 0, lMontantPaiement);
        ftInfosPaiement.setWidget(1, 1, lValeurMontantPaiement);
        ftInfosPaiement.setWidget(1, 2, lPeriodicitePaiement);
        ftInfosPaiement.setWidget(1, 3, construireBlocIcone(lbPeriodicitesPaiement, "idPeriodicitePaiement"));
        ftInfosPaiement.setWidget(2, 0, lNumeroTransaction);
        ftInfosPaiement.setWidget(2, 1, lValeurNumeroTransaction);
        ftInfosPaiement.setWidget(2, 2, lJourPaiement);
        ftInfosPaiement.setWidget(2, 3, construireBlocIcone(lbJoursPaiement, "idJourPaiement"));
        ftInfosPaiement.setWidget(3, 0, lRIB);
        ftInfosPaiement.setWidget(3, 1, construireBlocIcone(conteneurRib, "infosRib"));
        ftInfosPaiement.getFlexCellFormatter().setColSpan(3, 1, 3);
        ftInfosPaiement.getColumnFormatter().setWidth(0, InfosAdhesionViewImplConstants.LARGEUR_COL_LIBELLE_0);
        ftInfosPaiement.getColumnFormatter().setWidth(1, InfosAdhesionViewImplConstants.LARGEUR_COL_CHAMP_1);
        ftInfosPaiement.getColumnFormatter().setWidth(2, InfosAdhesionViewImplConstants.LARGEUR_COL_LIBELLE_2);
        ftInfosPaiement.getColumnFormatter().setWidth(3, InfosAdhesionViewImplConstants.LARGEUR_COL_CHAMP_3);

        final CaptionPanel panelinfosPaiement = new CaptionPanel(viewConstants.captionPanelInfosPaiement());
        panelinfosPaiement.add(ftInfosPaiement);
        container.add(panelinfosPaiement);
        // On incrémente le nombre de panel supplémentaires
        nbPanelSupplementaires++;
    }

    /**
     * Construit le panel contenant les informations de signature numerique.
     */
    private void construirePanelInfosSignatureNumerique() {
        final Label lbDateClicBA = new Label(viewConstants.lDateClicBA(), false);
        lbDateClicBA.setTitle(viewConstants.lTitreDateClicBA());
        final Label lbDateTelechargementBA = new Label(viewConstants.lDateTelechargementBA(), false);
        lbDateTelechargementBA.setTitle(viewConstants.lTitreDateTelechargementBA());
        lDateClicBA = new Label();
        lDateTelechargementBA = new Label();

        final FlexTable ftInfosSN = new FlexTable();
        ftInfosSN.setWidth(ComposantTarificateurConstants.POURCENT_100);
        ftInfosSN.setCellSpacing(3);
        ftInfosSN.setWidget(0, 0, lbDateClicBA);
        ftInfosSN.setWidget(0, 1, lDateClicBA);
        ftInfosSN.setWidget(0, 2, lbDateTelechargementBA);
        ftInfosSN.setWidget(0, 3, lDateTelechargementBA);
        ftInfosSN.getColumnFormatter().setWidth(0, InfosAdhesionViewImplConstants.LARGEUR_COL_LIBELLE_0);
        ftInfosSN.getColumnFormatter().setWidth(1, InfosAdhesionViewImplConstants.LARGEUR_COL_CHAMP_1);
        ftInfosSN.getColumnFormatter().setWidth(2, InfosAdhesionViewImplConstants.LARGEUR_COL_LIBELLE_2);
        ftInfosSN.getColumnFormatter().setWidth(3, InfosAdhesionViewImplConstants.LARGEUR_COL_CHAMP_3);

        final CaptionPanel panelInfosSN = new CaptionPanel(viewConstants.captionPanelInfosSignatureNumerique());
        panelInfosSN.add(ftInfosSN);
        container.add(panelInfosSN);
        // On incrémente le nombre de panel supplémentaires
        nbPanelSupplementaires++;
    }

    private void construirePanelOptions() {
        final Label lTeletransmission = new Label(viewConstants.lTeletransmission(), false);
        cbTeletransmission = new CheckBox();
        cbTeletransmission.setTitle(viewConstants.lTitreTeletransmission());

        final FlexTable ftOptions = new FlexTable();
        ftOptions.setWidth(ComposantTarificateurConstants.POURCENT_100);
        ftOptions.setCellSpacing(3);
        ftOptions.setWidget(0, 0, lTeletransmission);
        ftOptions.setWidget(0, 1, cbTeletransmission);
        ftOptions.getColumnFormatter().setWidth(0, InfosAdhesionViewImplConstants.LARGEUR_COL_LIBELLE_0);
        ftOptions.getColumnFormatter().setWidth(1, InfosAdhesionViewImplConstants.LARGEUR_COL_CHAMP_1);
        ftOptions.getColumnFormatter().setWidth(2, InfosAdhesionViewImplConstants.LARGEUR_COL_LIBELLE_2);
        ftOptions.getColumnFormatter().setWidth(3, InfosAdhesionViewImplConstants.LARGEUR_COL_CHAMP_3);

        final CaptionPanel panelOptions = new CaptionPanel(viewConstants.captionPanelOptions());
        panelOptions.add(ftOptions);
        container.add(panelOptions);
        // On incrémente le nombre de panel supplémentaires
        nbPanelSupplementaires++;
    }

    /**
     * Construit le panel contenant la barre de boutons de la vue.
     */
    private void construirePanelBoutons(final VerticalPanel conteneur) {
        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        horizontalPanel.setSpacing(5);

        btnAnnuler = new DecoratedButton(viewConstants.lAnnuler());
        btnEnregistrer = new DecoratedButton(viewConstants.lEnregistrer());
        btnReduire = new DecoratedButton(viewConstants.reduire());

        horizontalPanel.add(btnEnregistrer);
        horizontalPanel.add(btnReduire);
        horizontalPanel.add(btnAnnuler);
        conteneur.add(horizontalPanel);
        conteneur.setCellHorizontalAlignment(horizontalPanel, HasHorizontalAlignment.ALIGN_CENTER);
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
    public HasClickHandlers getBtnAnnuler() {
        return btnAnnuler;
    }

    @Override
    public HasClickHandlers getBtnEnregistrer() {
        return btnEnregistrer;
    }

    @Override
    public BlocInfoSanteComplete creerBlocInfoSanteComplete(String suffix) {
    	 return new BlocInfoSanteCompleteViewImpl(viewConstants, suffix, iconeErreurChampManager);
    }

    @Override
    public BlocInfoSanteSimple creerBlocInfoSanteSimple(String suffix) {
    	 return new BlocInfoSanteSimpleViewImpl(viewConstants, suffix, iconeErreurChampManager);
    }

    @Override
    public void ajouterBlocInfoSante(View vue, int position) {
        container.insert(vue.asWidget(), position);
    }

    @Override
    public void chargerInfosPaiement(InfosPaiementModel infosPaiement) {
        if (infosPaiement != null) {
            final String dateSignature = infosPaiement.getDateSignature();
            if (dateSignature != null && !"".equals(dateSignature.trim())) {
                calDateSignature.setValue(DateUtil.getDate(dateSignature));
            }
            final Float montantPaiement = infosPaiement.getMontantPaiement();
            if (montantPaiement != null) {
                lValeurMontantPaiement.setText(numberFormat.format((double) infosPaiement.getMontantPaiement()) + " " + lignesDevisConstants.symboleMonnaie());
            }

            lValeurNumeroTransaction.setText(infosPaiement.getNumeroTransaction());
            if (infosPaiement.getMoyenPaiement() != null) {
                lbModesPaiement.setValue(infosPaiement.getMoyenPaiement());
            }
            if (infosPaiement.getPeriodicitePaiement() != null) {
                lbPeriodicitesPaiement.setValue(infosPaiement.getPeriodicitePaiement());
            }
            if (infosPaiement.getJourPaiement() != null) {
                lbJoursPaiement.setValue(infosPaiement.getJourPaiement());
            }
        }
    }

    @Override
    public void chargerInfosSignatureNumerique(String dateClicBA, String dateTelechargementBA) {
        lDateClicBA.setText(dateClicBA);
        lDateTelechargementBA.setText(dateTelechargementBA);
    }

    @Override
    public void chargerInfosRib(InfosRibModel infosRib) {
        if (infosRib != null) {
            tbRibCodeBanque.setValue(infosRib.getCodeBanque());
            tbRibCodeGuichet.setValue(infosRib.getCodeGuichet());
            tbRibCodeCompte.setValue(infosRib.getCodeCompte());
            tbRibCle.setValue(infosRib.getCle());
        }
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getJourPaiement() {
        return lbJoursPaiement;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getMoyenPaiement() {
        return lbModesPaiement;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getPeriodicitePaiement() {
        return lbPeriodicitesPaiement;
    }

    @Override
    public String getDateSignature() {
        return calDateSignature.getValue() != null ? DateUtil.getString(calDateSignature.getValue()) : null;
    }

    @Override
    public IconeErreurChampManager getIconeErreurChampManager() {
        return iconeErreurChampManager;
    }

    @Override
    public HasValue<String> getRibCle() {
        return tbRibCle;
    }

    @Override
    public HasValue<String> getRibCodeBanque() {
        return tbRibCodeBanque;
    }

    @Override
    public HasValue<String> getRibCodeCompte() {
        return tbRibCodeCompte;
    }

    @Override
    public HasValue<String> getRibCodeGuichet() {
        return tbRibCodeGuichet;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getJoursPaiementSuggestHandler() {
        return lbJoursPaiement;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getModesPaimentSuggestHandler() {
        return lbModesPaiement;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getPeriodicitesPaiementSuggestHandler() {
        return lbPeriodicitesPaiement;
    }

    @Override
    public InfosAdhesionViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public void onRpcCall(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        if (errorPopupConfiguration != null) {
            ErrorPopup.afficher(errorPopupConfiguration);
        }
    }

    @Override
    public void onRpcSuccess() {
        LoadingPopup.stopAll();
    }

    @Override
    public void setInfosAdhesionEditables(boolean isEditable) {
        btnEnregistrer.setEnabled(isEditable);
        lbModesPaiement.setEnabled(isEditable);
        lbPeriodicitesPaiement.setEnabled(isEditable);
        lbJoursPaiement.setEnabled(isEditable);
        calDateSignature.setEnabled(isEditable);
        tbRibCodeBanque.setEnabled(isEditable);
        tbRibCodeGuichet.setEnabled(isEditable);
        tbRibCodeCompte.setEnabled(isEditable);
        tbRibCle.setEnabled(isEditable);
    }

    @Override
    public DecoratedCalendrierDateBox getCdbDateSignature() {
        return calDateSignature;
    }

    @Override
    public void masquerLoadingPopup() {
        LoadingPopup.stop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasAllKeyHandlers getAllKeyHandlersFocusPanel() {
        return focusPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HasValue<Boolean> getTeletransmission() {
        return cbTeletransmission;
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
    public void viderAssuresSociaux(int nbAssuresSociaux) {
        for (int i = 0; i < nbAssuresSociaux; i++) {
            container.remove(container.getWidgetCount() - nbPanelSupplementaires - 1);
        }
    }

    @Override
    public void afficherAssuresSociaux(List<AssureSocialModel> liste) {
        for (AssureSocialModel assureSocialModel : liste) {
            final Label lNumSs = new Label(viewConstants.lNumSs(), false);
            final Label lCleSs = new Label(viewConstants.lCleSs(), false);
            final Label lRegime = new Label(viewConstants.lRegime(), false);
            final Label lCaisse = new Label(viewConstants.lCaisse(), false);
            final Label lValueNumSs = new Label(assureSocialModel.getInfoSante() != null ? assureSocialModel.getInfoSante().getNumSecuriteSocial() : "", false);
            final Label lValueCleSs = new Label(assureSocialModel.getInfoSante() != null ? assureSocialModel.getInfoSante().getCleSecuriteSocial() : "", false);
            final Label lValueRegime =
                new Label(assureSocialModel.getInfoSante() != null && assureSocialModel.getInfoSante().getRegime() != null ? assureSocialModel.getInfoSante()
                        .getRegime().getLibelle() : "", false);
            final Label lValueCaisse =
                new Label(assureSocialModel.getInfoSante() != null && assureSocialModel.getInfoSante().getCaisse() != null ? assureSocialModel.getInfoSante()
                        .getCaisse().getNom() : "", false);

            final FlexTable ftAssureSocial = new FlexTable();
            ftAssureSocial.setWidth(ComposantTarificateurConstants.POURCENT_100);
            ftAssureSocial.setCellSpacing(3);
            ftAssureSocial.setWidget(0, 0, lNumSs);
            ftAssureSocial.setWidget(0, 1, lValueNumSs);
            ftAssureSocial.setWidget(0, 2, lCleSs);
            ftAssureSocial.setWidget(0, 3, lValueCleSs);
            ftAssureSocial.setWidget(1, 0, lRegime);
            ftAssureSocial.setWidget(1, 1, lValueRegime);
            ftAssureSocial.setWidget(1, 2, lCaisse);
            ftAssureSocial.setWidget(1, 3, lValueCaisse);
            ftAssureSocial.getColumnFormatter().setWidth(0, InfosAdhesionViewImplConstants.LARGEUR_COL_LIBELLE_0);
            ftAssureSocial.getColumnFormatter().setWidth(1, InfosAdhesionViewImplConstants.LARGEUR_COL_CHAMP_1);
            ftAssureSocial.getColumnFormatter().setWidth(2, InfosAdhesionViewImplConstants.LARGEUR_COL_LIBELLE_2);
            ftAssureSocial.getColumnFormatter().setWidth(3, InfosAdhesionViewImplConstants.LARGEUR_COL_CHAMP_3);

            final CaptionPanel panelAssureSocial = new CaptionPanel(assureSocialModel.getPrenom() + " " + assureSocialModel.getNom()
            		+ " " + assureSocialModel.getNumClient());
            panelAssureSocial.add(ftAssureSocial);

            container.insert(panelAssureSocial, container.getWidgetCount() - nbPanelSupplementaires);
        }
    }

    @Override
    public void viderPersonnes(int nbPersonnes) {
    	for (int i = 0; i < nbPersonnes; i++) {
    		container.remove(0);
    	}
    }
}

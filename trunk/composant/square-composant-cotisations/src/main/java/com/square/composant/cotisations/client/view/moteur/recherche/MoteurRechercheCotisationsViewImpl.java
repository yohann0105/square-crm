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
package com.square.composant.cotisations.client.view.moteur.recherche;

import java.util.Date;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedTextBox;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.cotisations.client.ComposantCotisations;
import com.square.composant.cotisations.client.content.i18n.ComposantCotisationsConstants;
import com.square.composant.cotisations.client.model.CodeAiaLibelleModel;
import com.square.composant.cotisations.client.presenter.moteur.recherche.MoteurRechercheCotisationsPresenter.MoteurRechercheCotisationsView;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;

/**
 * Vue du moteur de recherche de cotisations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class MoteurRechercheCotisationsViewImpl extends Composite implements MoteurRechercheCotisationsView {

    /** View constants. */
    private static MoteurRechercheCotisationsViewImplConstants viewConstants =
        (MoteurRechercheCotisationsViewImplConstants) GWT.create(MoteurRechercheCotisationsViewImplConstants.class);

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbContrats;

    private DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt> slbModesPaiement;

    private DecoratedSuggestListBoxSingle<CodeAiaLibelleModel> slbSituations;

    private DecoratedCalendrierDateBox cdbDateDebutPeriode;

    private DecoratedCalendrierDateBox cdbDateFinPeriode;

    private Label lValueSoldeGlobalActuel;

    private DecoratedTextBox tbMontantMin;

    private DecoratedTextBox tbMontantMax;

    private DecoratedButton btnRechercher;

    private DecoratedButton btnSimuler;

    private DecoratedButton btnInitRecherche;

    private FocusPanel focusPanel;

    private VerticalPanel conteneurEntetes;

    private HorizontalPanel conteneurPagination;

    private Label lTitreSimulation;

    /**
     * Constructeur.
     */
    public MoteurRechercheCotisationsViewImpl() {
        final Widget conteneurBarreBoutons = construireBarreBoutons();
        conteneurPagination = new HorizontalPanel();
        conteneurPagination.setSpacing(3);
        conteneurEntetes = new VerticalPanel();
        conteneurEntetes.setWidth(ComposantCotisationsConstants.POURCENT_100);
        conteneurEntetes.setSpacing(5);

        final VerticalPanel conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setSpacing(10);
        conteneurGlobal.setWidth(ComposantCotisationsConstants.POURCENT_100);
        conteneurGlobal.add(construireBlocRecherche());
        conteneurGlobal.add(conteneurBarreBoutons);
        conteneurGlobal.add(conteneurPagination);
        conteneurGlobal.add(conteneurEntetes);
        conteneurGlobal.setCellHorizontalAlignment(conteneurBarreBoutons, HasAlignment.ALIGN_CENTER);
        conteneurGlobal.setCellHorizontalAlignment(conteneurPagination, HasAlignment.ALIGN_RIGHT);

        lTitreSimulation = new Label(viewConstants.titreSimulation());
        lTitreSimulation.addStyleName(ComposantCotisations.RESOURCES.css().titreSimulation());

        this.focusPanel = new FocusPanel();
        focusPanel.setWidth(ComposantCotisationsConstants.POURCENT_100);
        focusPanel.add(conteneurGlobal);

        this.initWidget(focusPanel);
        this.setWidth(ComposantCotisationsConstants.POURCENT_100);
        this.addStyleName(ComposantCotisations.RESOURCES.css().moteurRecherche());
    }

    private Widget construireBlocRecherche() {
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
        final SuggestListBoxSingleProperties<CodeAiaLibelleModel> slbCodeAiaLibelleProperties = new SuggestListBoxSingleProperties<CodeAiaLibelleModel>() {
            @Override
            public String getSelectSuggestRenderer(CodeAiaLibelleModel row) {
                return row == null ? "" : row.getLibelle();
            }

            @Override
            public String[] getResultColumnsRenderer() {
                return new String[] {};
            }

            @Override
            public String[] getResultRowsRenderer(CodeAiaLibelleModel row) {
                return new String[] {row == null ? "" : row.getLibelle()};
            }
        };

        slbContrats = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbModesPaiement = new DecoratedSuggestListBoxSingle<IdentifiantLibelleGwt>(slbIdentifiantLibelleProperties);
        slbSituations = new DecoratedSuggestListBoxSingle<CodeAiaLibelleModel>(slbCodeAiaLibelleProperties);
        cdbDateDebutPeriode = new DecoratedCalendrierDateBox();
        cdbDateFinPeriode = new DecoratedCalendrierDateBox();
        tbMontantMax = new DecoratedTextBox();
        tbMontantMax.setWidth(MoteurRechercheCotisationsViewImplConstants.LARGEUR_TB_MONTANT);
        tbMontantMin = new DecoratedTextBox();
        tbMontantMin.setWidth(MoteurRechercheCotisationsViewImplConstants.LARGEUR_TB_MONTANT);
        lValueSoldeGlobalActuel = new Label();

        final Label lContrats = creerLibelle(viewConstants.contrats());
        final Label lDatePeriode = creerLibelle(viewConstants.datePeriode());
        final Label lPeriodeSeparateur = creerLibelle(viewConstants.separateur());
        final Label lModesPaiement = creerLibelle(viewConstants.modesPaiement());
        final Label lMontant = creerLibelle(viewConstants.montant());
        final Label lMontantSeparateur = creerLibelle(viewConstants.separateur());
        final Label lSituation = creerLibelle(viewConstants.situation());
        final Label lSoldeGlobalActuel = creerLibelle(viewConstants.soldeGlobalActuel());

        final CaptionPanel fieldSetPanel = new CaptionPanel(viewConstants.blocRechercher());
        final FlexTable fpRecherche = new FlexTable();
        fpRecherche.setWidth(ComposantCotisationsConstants.POURCENT_100);
        fpRecherche.setCellSpacing(5);
        fpRecherche.setWidget(0, 0, lContrats);
        fpRecherche.setWidget(0, 1, slbContrats);
        fpRecherche.setWidget(0, 2, lDatePeriode);
        fpRecherche.setWidget(0, 3, cdbDateDebutPeriode);
        fpRecherche.setWidget(0, 4, lPeriodeSeparateur);
        fpRecherche.setWidget(0, 5, cdbDateFinPeriode);
        fpRecherche.setWidget(1, 0, lModesPaiement);
        fpRecherche.setWidget(1, 1, slbModesPaiement);
        fpRecherche.setWidget(1, 2, lMontant);
        fpRecherche.setWidget(1, 3, tbMontantMin);
        fpRecherche.setWidget(1, 4, lMontantSeparateur);
        fpRecherche.setWidget(1, 5, tbMontantMax);
        fpRecherche.setWidget(2, 0, lSituation);
        fpRecherche.setWidget(2, 1, slbSituations);
        fpRecherche.setWidget(2, 2, lSoldeGlobalActuel);
        fpRecherche.setWidget(2, 3, lValueSoldeGlobalActuel);
        fpRecherche.getFlexCellFormatter().setColSpan(2, 3, 3);

        fieldSetPanel.add(fpRecherche);
        return fieldSetPanel;
    }

    private Widget construireBarreBoutons() {
        final HorizontalPanel pBoutons = new HorizontalPanel();
        pBoutons.setSpacing(5);
        btnRechercher = new DecoratedButton(viewConstants.rechercher());
        btnInitRecherche = new DecoratedButton(viewConstants.initRecherche());
        btnSimuler = new DecoratedButton(viewConstants.simuler());
        pBoutons.add(btnRechercher);
        pBoutons.add(btnInitRecherche);
        pBoutons.add(btnSimuler);
        return pBoutons;
    }

    private Label creerLibelle(final String libelle) {
        final Label label = new Label(libelle, false);
        label.setHorizontalAlignment(HasAlignment.ALIGN_RIGHT);
        label.addStyleName(ComposantCotisations.RESOURCES.css().libelleMoteurRecherche());
        return label;
    }

    @Override
    public Widget asWidget() {
        return this;
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
    public void afficherLoadingPopup(LoadingPopupConfiguration config) {
        LoadingPopup.afficher(config);
    }

    @Override
    public HasClickHandlers getBtnInitRecherche() {
        return btnInitRecherche;
    }

    @Override
    public HasClickHandlers getBtnRechercher() {
        return btnRechercher;
    }

    @Override
    public HasClickHandlers getBtnSimuler() {
        return btnSimuler;
    }

    @Override
    public HasValue<Date> getCdbDateDebutPeriode() {
        return cdbDateDebutPeriode;
    }

    @Override
    public HasValue<Date> getCdbDateFinPeriode() {
        return cdbDateFinPeriode;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbContrats() {
        return slbContrats;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbContratsHandler() {
        return slbContrats;
    }

    @Override
    public HasValue<IdentifiantLibelleGwt> getSlbModesPaiement() {
        return slbModesPaiement;
    }

    @Override
    public HasSuggestListBoxHandler<IdentifiantLibelleGwt> getSlbModesPaiementHandler() {
        return slbModesPaiement;
    }

    @Override
    public HasValue<CodeAiaLibelleModel> getSlbSituations() {
        return slbSituations;
    }

    @Override
    public HasSuggestListBoxHandler<CodeAiaLibelleModel> getSlbSituationsHandler() {
        return slbSituations;
    }

    @Override
    public HasValue<String> getTbMontantMax() {
        return tbMontantMax;
    }

    @Override
    public HasValue<String> getTbMontantMin() {
        return tbMontantMin;
    }

    @Override
    public void effacerRecherche() {
        slbContrats.clean();
        slbModesPaiement.clean();
        slbSituations.clean();
        cdbDateDebutPeriode.clean();
        cdbDateFinPeriode.clean();
        tbMontantMax.setValue(null);
        tbMontantMin.setValue(null);
    }

    @Override
    public HasKeyPressHandlers getGestionToucheEntreHandler() {
        return focusPanel;
    }

    @Override
    public HasWidgets ajouterConteneurEntete() {
        final SimplePanel conteneurEntete = new SimplePanel();
        conteneurEntete.setWidth(ComposantCotisationsConstants.POURCENT_100);
        conteneurEntetes.add(conteneurEntete);
        return conteneurEntete;
    }

    @Override
    public void viderConteneurEntetes() {
        conteneurEntetes.clear();
    }

    @Override
    public HasClickHandlers ajouterLienPage(int numPage, boolean selected) {
        final Label labelPage = new Label(String.valueOf(numPage));
        if (selected) {
            labelPage.addStyleName(ComposantCotisations.RESOURCES.css().lienPaginationSelected());
        } else {
            labelPage.addStyleName(ComposantCotisations.RESOURCES.css().lienPagination());
        }
        conteneurPagination.add(labelPage);
        return labelPage;
    }

    @Override
    public void viderPagination() {
        conteneurPagination.clear();
    }

    @Override
    public MoteurRechercheCotisationsViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public HasText getLValueSoldeGlobalActuel() {
        return lValueSoldeGlobalActuel;
    }

    @Override
    public void afficherModeSimulation(boolean isSimulation) {
        if (isSimulation) {
            conteneurEntetes.add(lTitreSimulation);
            conteneurEntetes.setCellHorizontalAlignment(lTitreSimulation, HasAlignment.ALIGN_CENTER);
            conteneurEntetes.addStyleName(ComposantCotisations.RESOURCES.css().conteneurEnteteSimulation());
        } else {
            conteneurEntetes.removeStyleName(ComposantCotisations.RESOURCES.css().conteneurEnteteSimulation());
        }
    }

    @Override
    public void afficherConteneurEntete(boolean afficher) {
        conteneurEntetes.setVisible(afficher);
    }

}

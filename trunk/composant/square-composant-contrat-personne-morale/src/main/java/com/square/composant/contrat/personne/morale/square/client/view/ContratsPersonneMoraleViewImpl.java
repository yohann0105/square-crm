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
package com.square.composant.contrat.personne.morale.square.client.view;

import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;
import com.square.composant.contrat.personne.morale.square.client.content.i18n.ComposantContratPersonneMoraleConstants;
import com.square.composant.contrat.personne.morale.square.client.model.ConstantesModel;
import com.square.composant.contrat.personne.morale.square.client.presenter.ContratsPersonneMoralePresenter;
import com.square.composant.contrat.personne.morale.square.client.presenter.ContratsPersonneMoralePresenter.ContratPersonneMoraleInfosView;
import com.square.composant.contrat.personne.morale.square.client.presenter.ContratsPersonneMoralePresenter.ContratsPersonneMoraleView;

/**
 * Implémentation de la vue ContratsPersonneMoraleView.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ContratsPersonneMoraleViewImpl extends Composite implements ContratsPersonneMoraleView {

    /** View constants. */
    private ContratsPersonneMoraleViewImplConstants viewConstants;

    /** Conteneur global. */
    private VerticalPanel conteneurGlobal;

    /** Label du statut. */
    private Label labelStatut;

    /** Label de lpremière mutualisation. */
    private Label labelPremiereMutualisation;

    /** Label de la fidélité. */
    private Label labelFidelite;

    /** Label de la gestion du contrat. */
    private Label labelGestionContrat;

    /** Label du gestionnaire. */
    private Label labelGestionnaire;

    /** Panel Dernière radiation. */
    private CaptionPanel panelDerniereRadiation;

    /** Label de le date de dernière radiation. */
    private Label labelDateDerniereRadiation;

    /** Label du motif de dernière radiation. */
    private Label labelMotifDerniereRadiation;

    /** Table des populations. */
    private FlexTable tablePopulation;

    /** Panel contenant le PieChart des populations. */
    private VerticalPanel panelPieChartPopulation;

    /** Panel de la liste des contrats. */
    private VerticalPanel panelListeContrats;

    /**
     * Constructeur.
     */
    public ContratsPersonneMoraleViewImpl() {
        this.viewConstants = (ContratsPersonneMoraleViewImplConstants) GWT.create(ContratsPersonneMoraleViewImplConstants.class);
        conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setWidth(ComposantContratPersonneMoraleConstants.POURCENT_100);
        conteneurGlobal.setSpacing(10);
        panelListeContrats = new VerticalPanel();
        panelListeContrats.setWidth(ComposantContratPersonneMoraleConstants.POURCENT_100);

        construireBlocSynthese();
        conteneurGlobal.add(panelListeContrats);

        initWidget(conteneurGlobal);
        this.setStylePrimaryName(ContratsPersonneMoralePresenter.RESSOURCES.css().composantContrat());
    }

    /** Construction du bloc synthèse. */
    private void construireBlocSynthese() {
        final Label lStatut = new Label(viewConstants.libelleStatut(), false);
        final Label lPremiereMutualisation = new Label(viewConstants.libellePremiereMutualisation(), false);
        final Label lFidelite = new Label(viewConstants.libelleFidelite(), false);
        final Label lGestionContrat = new Label(viewConstants.libelleGestionContrat(), false);
        final Label lGestionnaire = new Label(viewConstants.libelleGestionnaire(), false);
        labelStatut = new Label();
        labelPremiereMutualisation = new Label();
        labelFidelite = new Label();
        labelGestionContrat = new Label();
        labelGestionnaire = new Label();

        // Panel des infos
        final FlexTable panelInfos = new FlexTable();
        panelInfos.setWidth(ComposantContratPersonneMoraleConstants.POURCENT_100);
        panelInfos.setCellSpacing(5);
        panelInfos.setWidget(0, 0, lStatut);
        panelInfos.setWidget(0, 1, labelStatut);
        panelInfos.setWidget(0, 2, lPremiereMutualisation);
        panelInfos.setWidget(0, 3, labelPremiereMutualisation);
        panelInfos.setWidget(0, 4, lFidelite);
        panelInfos.setWidget(0, 5, labelFidelite);
        panelInfos.setWidget(1, 0, lGestionnaire);
        panelInfos.setWidget(1, 1, labelGestionnaire);
        panelInfos.setWidget(2, 0, lGestionContrat);
        panelInfos.setWidget(2, 1, labelGestionContrat);
        panelInfos.getColumnFormatter().setWidth(0, "13%");
        panelInfos.getColumnFormatter().setWidth(1, "22%");
        panelInfos.getColumnFormatter().setWidth(2, "15%");
        panelInfos.getColumnFormatter().setWidth(3, "20%");
        panelInfos.getColumnFormatter().setWidth(4, "6%");
        panelInfos.getColumnFormatter().setWidth(5, "24%");

        // Bloc dernière radiation
        contruireBlocDerniereRadiation();

        // Bloc de la population
        final HorizontalPanel panelPopulation = construireBlocPopulation();

        final VerticalPanel panelBlocSynthese = new VerticalPanel();
        panelBlocSynthese.setWidth(ComposantContratPersonneMoraleConstants.POURCENT_100);
        panelBlocSynthese.setSpacing(5);
        panelBlocSynthese.add(panelInfos);
        panelBlocSynthese.add(panelDerniereRadiation);
        panelBlocSynthese.add(panelPopulation);

        final CaptionPanel captionPanelBlocSynthese = new CaptionPanel(viewConstants.titreBlocSynthese());
        captionPanelBlocSynthese.add(panelBlocSynthese);
        conteneurGlobal.add(captionPanelBlocSynthese);
    }

    /** Construit le bloc de dernière radiation. */
    private void contruireBlocDerniereRadiation() {
        final Label lDateDerniereRadiation = new Label(viewConstants.libelleDateDerniereRadiation());
        final Label lMotifDerniereRadiation = new Label(viewConstants.libelleMotifDerniereRadiation());
        labelDateDerniereRadiation = new Label();
        labelMotifDerniereRadiation = new Label();

        final FlexTable panelPrincipalBlocDerniereRadiation = new FlexTable();
        panelPrincipalBlocDerniereRadiation.setWidth(ComposantContratPersonneMoraleConstants.POURCENT_100);
        panelPrincipalBlocDerniereRadiation.setWidget(0, 0, lDateDerniereRadiation);
        panelPrincipalBlocDerniereRadiation.setWidget(0, 1, labelDateDerniereRadiation);
        panelPrincipalBlocDerniereRadiation.setWidget(0, 2, lMotifDerniereRadiation);
        panelPrincipalBlocDerniereRadiation.setWidget(0, 3, labelMotifDerniereRadiation);
        panelPrincipalBlocDerniereRadiation.getColumnFormatter().setWidth(0, "8%");
        panelPrincipalBlocDerniereRadiation.getColumnFormatter().setWidth(1, "42%");
        panelPrincipalBlocDerniereRadiation.getColumnFormatter().setWidth(2, "8%");
        panelPrincipalBlocDerniereRadiation.getColumnFormatter().setWidth(3, "42%");

        panelDerniereRadiation = new CaptionPanel(viewConstants.titreBlocDerniereRadiation());
        panelDerniereRadiation.add(panelPrincipalBlocDerniereRadiation);
    }

    /** Construit le bloc des populations. */
    private HorizontalPanel construireBlocPopulation() {
        final HorizontalPanel panelBlocPopulation = new HorizontalPanel();
        panelBlocPopulation.setSpacing(10);

        // Tableau des populations
        tablePopulation = new FlexTable();
        tablePopulation.setCellPadding(6);
        tablePopulation.setStylePrimaryName(ContratsPersonneMoralePresenter.RESSOURCES.css().tableau());
        tablePopulation.getRowFormatter().setStylePrimaryName(0, ContratsPersonneMoralePresenter.RESSOURCES.css().ligneEnteteColonne());

        final Label lEntetePopulation = new Label(viewConstants.libelleEntetePopulation(), false);
        final Label lEnteteEffectif = new Label(viewConstants.libelleEnteteEffectif(), false);
        tablePopulation.setWidget(0, 0, lEntetePopulation);
        tablePopulation.setWidget(0, 1, lEnteteEffectif);
        tablePopulation.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
        tablePopulation.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);

        // PieChart des populations
        panelPieChartPopulation = new VerticalPanel();
        panelPieChartPopulation.setVisible(false);

        panelBlocPopulation.add(tablePopulation);
        panelBlocPopulation.add(panelPieChartPopulation);

        return panelBlocPopulation;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void afficherCamembertPopulation(final DataTable donneesPopulation) {
        final Runnable onLoadCallback = new Runnable() {
            public void run() {
                final Options options = Options.create();
                options.setSize(ContratsPersonneMoraleViewImplConstants.LARGEUR_CHART, ContratsPersonneMoraleViewImplConstants.HAUTEUR_CHART);
                options.setLegend(LegendPosition.NONE);
                options.setBackgroundColor(ContratsPersonneMoraleViewImplConstants.COULEUR_FOND_STATISTIQUE);
                options.setTitle(viewConstants.titrePopulation());
                options.setTitleFontSize(12);
                final PieChart pieChartPopulation = new PieChart(donneesPopulation, options);
                panelPieChartPopulation.add(pieChartPopulation);
                panelPieChartPopulation.setCellVerticalAlignment(pieChartPopulation, HasAlignment.ALIGN_TOP);
                panelPieChartPopulation.setVisible(true);
            }
        };
        VisualizationUtils.loadVisualizationApi(onLoadCallback, "corechart");
        panelPieChartPopulation.setVisible(true);
    }

    @Override
    public void afficherDerniereRadiation(boolean visible) {
        panelDerniereRadiation.setVisible(visible);
    }

    @Override
    public ContratPersonneMoraleInfosView ajouterContrat(ConstantesModel constantesApp, boolean afficherDateResiliation) {
        final ContratPersonneMoraleInfosViewImpl bloc =
            new ContratPersonneMoraleInfosViewImpl(constantesApp, ContratsPersonneMoralePresenter.RESSOURCES, afficherDateResiliation);
        panelListeContrats.add(bloc);
        return bloc;
    }

    @Override
    public void ajouterLignePopulation(String libellePopulation, Integer effectif) {
        final int ligne = tablePopulation.getRowCount();
        final Label lPopulation = new Label(libellePopulation);
        final Label lEffectif = new Label(effectif.toString());
        tablePopulation.setWidget(ligne, 0, lPopulation);
        tablePopulation.setWidget(ligne, 1, lEffectif);
    }

    @Override
    public HasText getLabelDateDerniereRadiation() {
        return labelDateDerniereRadiation;
    }

    @Override
    public HasText getLabelGestionContrat() {
        return labelGestionContrat;
    }

    @Override
    public HasText getLabelGestionnaire() {
        return labelGestionnaire;
    }

    @Override
    public HasText getLabelMotifDerniereRadiation() {
        return labelMotifDerniereRadiation;
    }

    @Override
    public HasText getLabelPremiereMutualisation() {
        return labelPremiereMutualisation;
    }

    @Override
    public HasText getLabelStatut() {
        return labelStatut;
    }

    @Override
    public void initTableauPopulation() {
        while (tablePopulation.getRowCount() > 1) {
            tablePopulation.removeRow(1);
        }
    }

    @Override
    public void masquerCamembertPopulation() {
        panelPieChartPopulation.setVisible(false);
    }

    @Override
    public void modifierCouleurStatut(String couleurStatut) {
        if (couleurStatut != null) {
            labelStatut.setStyleName(couleurStatut);
        }
    }

    @Override
    public void setLabelFidelite(Integer annees, Integer mois) {
        int nbAnnees = 0;
        int nbMois = 0;
        if (annees != null) {
            nbAnnees = annees;
        }
        if (mois != null) {
            nbMois = mois;
        }
        final StringBuffer libelleFidelite = new StringBuffer("");
        if (nbAnnees > 0) {
            libelleFidelite.append(nbAnnees).append(ContratsPersonneMoraleViewImplConstants.ESPACE);
            if (nbAnnees == 1) {
                libelleFidelite.append(viewConstants.libelleFideliteAnnee());
            }
            else {
                libelleFidelite.append(viewConstants.libelleFideliteAnnees());
            }
            if (nbMois > 0) {
                libelleFidelite.append(ContratsPersonneMoraleViewImplConstants.ESPACE);
                libelleFidelite.append(viewConstants.libelleFideliteEt());
                libelleFidelite.append(ContratsPersonneMoraleViewImplConstants.ESPACE);
            }
        }
        if (nbMois > 0) {
            libelleFidelite.append(nbMois).append(ContratsPersonneMoraleViewImplConstants.ESPACE).append(viewConstants.libelleFideliteMois());
        }
        labelFidelite.setText(libelleFidelite.toString());
    }

    @Override
    public void afficherLoadingPopup(LoadingPopupConfiguration loadingPopupConfiguration) {
        LoadingPopup.afficher(loadingPopupConfiguration);
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    @Override
    public void onRpcServiceSuccess() {
        LoadingPopup.stop();
    }
}

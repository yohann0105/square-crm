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
package com.square.composant.contrat.square.client.view;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.corechart.LineChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart;
import com.square.composant.contrat.square.client.model.ConstantesModel;
import com.square.composant.contrat.square.client.model.InfosGarantieBeneficiaireModel;
import com.square.composant.contrat.square.client.presenter.ContratsPresenter;
import com.square.composant.contrat.square.client.presenter.ContratsPresenter.ContratInfosView;
import com.square.composant.contrat.square.client.presenter.ContratsPresenter.ContratsView;

/**
 * Implémentation de la vue des contrats.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ContratsViewImpl extends Composite implements ContratsView {

    /** View constants. */
    private ContratsViewImplConstants viewConstants;

    /** View messages. */
    private ContratsViewImplMessages viewMessages;

    /** Conteneur global. */
    private VerticalPanel conteneurGlobal;

    /** Labels modifiables. */
    private Label labelStatut;

    private Label labelPremiereMutualisation;

    private Label labelFidelite;

    private Label labelTeletransmission;

    private Label labelDateDerniereRadiation;

    private Label labelMotifDerniereRadiation;

    private Label lPopulation;

    private Label lGestionnaire;

    private Label labelPopulation;

    private Label labelGestionContrat;

    private Label labelGestionnaire;

    /** Panel Dernière radiation. */
    private CaptionPanel panelDerniereRadiation;

    /** Panel du récapitulatif des garanties. */
    private FlexTable panelRecapitulatifGaranties;

    /** Panel de la liste des contrats. */
    private VerticalPanel panelListeContrats;

    /** Panel des statistiques. */
    private HorizontalPanel panelStats;

    /** Panel contenant les camemberts des stats Jauge Banco. */
    private HorizontalPanel panelStatsJaugeBanco;

    /** Panel contenant les statistiques P/C. */
    private VerticalPanel panelStatsPrestationCotisation;

    /** Graphique présentant les statistiques P/C. */
    private LineChart lineChartPrestationCotisation;

    /**
     * Constructeur.
     */
    public ContratsViewImpl() {
        this.viewConstants = (ContratsViewImplConstants) GWT.create(ContratsViewImplConstants.class);
        this.viewMessages = (ContratsViewImplMessages) GWT.create(ContratsViewImplMessages.class);
        conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setWidth(ContratsViewImplConstants.POURCENT_100);
        conteneurGlobal.setSpacing(10);
        panelListeContrats = new VerticalPanel();
        panelListeContrats.setWidth(ContratsViewImplConstants.POURCENT_100);

        construireBlocSynthese();
        conteneurGlobal.add(panelListeContrats);

        initWidget(conteneurGlobal);
        this.setStylePrimaryName(ContratsPresenter.RESOURCES.css().composantContrat());
    }

    /** Construction du bloc synthèse. */
    private void construireBlocSynthese() {
        final Label lStatut = new Label(viewConstants.libelleStatut());
        final Label lPremiereMutualisation = new Label(viewConstants.libellePremiereMutualisation());
        final Label lFidelite = new Label(viewConstants.libelleFidelite());
        final Label lTeletransmission = new Label(viewConstants.libelleTeletransmission());
        lPopulation = new Label(viewConstants.libellePopulation());
        final Label lGestionContrat = new Label(viewConstants.libelleGestionContrat());
        lGestionnaire = new Label(viewConstants.libelleGestionnaire());
        labelStatut = new Label();
        labelPremiereMutualisation = new Label();
        labelFidelite = new Label();
        labelTeletransmission = new Label();
        labelPopulation = new Label();
        labelGestionContrat = new Label();
        labelGestionnaire = new Label();

        // Panel des infos
        final FlexTable panelInfos = new FlexTable();
        panelInfos.setWidth(ContratsViewImplConstants.POURCENT_100);
        panelInfos.setCellSpacing(5);
        panelInfos.setWidget(0, 0, lStatut);
        panelInfos.setWidget(0, 1, labelStatut);
        panelInfos.setWidget(0, 2, lPremiereMutualisation);
        panelInfos.setWidget(0, 3, labelPremiereMutualisation);
        panelInfos.setWidget(0, 4, lFidelite);
        panelInfos.setWidget(0, 5, labelFidelite);
        panelInfos.setWidget(1, 0, lGestionnaire);
        panelInfos.setWidget(1, 1, labelGestionnaire);
        panelInfos.setWidget(1, 2, lGestionContrat);
        panelInfos.setWidget(1, 3, labelGestionContrat);
        panelInfos.setWidget(1, 4, lTeletransmission);
        panelInfos.setWidget(1, 5, labelTeletransmission);
        panelInfos.setWidget(2, 0, lPopulation);
        panelInfos.setWidget(2, 1, labelPopulation);
        panelInfos.getColumnFormatter().setWidth(0, "11%");
        panelInfos.getColumnFormatter().setWidth(1, "22%");
        panelInfos.getColumnFormatter().setWidth(2, "14%");
        panelInfos.getColumnFormatter().setWidth(3, "19%");
        panelInfos.getColumnFormatter().setWidth(4, "13%");
        panelInfos.getColumnFormatter().setWidth(5, "20%");

        // Bloc dernière radiation
        contruireBlocDerniereRadiation();
        // Bloc du récapitulatif des garanties
        construireBlocRecapitulatifGaranties();
        // Statistiques
        construireBlocStatistiques();

        final ScrollPanel scrollPanelRecapitulatifGaranties = new ScrollPanel(panelRecapitulatifGaranties);
        scrollPanelRecapitulatifGaranties.setWidth(ContratsViewImplConstants.LARGEUR_SCROLL_PANEL_SYNTHESE);

        final VerticalPanel panelBlocSynthese = new VerticalPanel();
        panelBlocSynthese.setWidth(ContratsViewImplConstants.POURCENT_100);
        panelBlocSynthese.setSpacing(10);
        panelBlocSynthese.add(panelInfos);
        panelBlocSynthese.add(panelDerniereRadiation);
        panelBlocSynthese.add(scrollPanelRecapitulatifGaranties);
        panelBlocSynthese.add(panelStats);
        panelBlocSynthese.setCellHorizontalAlignment(scrollPanelRecapitulatifGaranties, HasAlignment.ALIGN_CENTER);
        panelBlocSynthese.setCellHorizontalAlignment(panelStats, HasAlignment.ALIGN_CENTER);

        afficherInfosCollectif(false);
        afficherDerniereRadiation(false);

        final CaptionPanel captionPanelBlocSynthese = new CaptionPanel(viewConstants.titreBlocSynthese());
        captionPanelBlocSynthese.add(panelBlocSynthese);
        conteneurGlobal.add(captionPanelBlocSynthese);
    }

    /** Construction du bloc Dernière radiation. */
    private void contruireBlocDerniereRadiation() {
        final Label lDateDerniereRadiation = new Label(viewConstants.libelleDateDerniereRadiation());
        final Label lMotifDerniereRadiation = new Label(viewConstants.libelleMotifDerniereRadiation());
        labelDateDerniereRadiation = new Label();
        labelMotifDerniereRadiation = new Label();

        final FlexTable panelPrincipalBlocDerniereRadiation = new FlexTable();
        panelPrincipalBlocDerniereRadiation.setWidth(ContratsViewImplConstants.POURCENT_100);
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

    /** Construction du bloc du récapitulatif des garanties. */
    private void construireBlocRecapitulatifGaranties() {
        panelRecapitulatifGaranties = new FlexTable();
        panelRecapitulatifGaranties.setCellPadding(5);
        panelRecapitulatifGaranties.setWidth(ContratsViewImplConstants.POURCENT_100);
        panelRecapitulatifGaranties.setStylePrimaryName(ContratsPresenter.RESOURCES.css().tableau());
        panelRecapitulatifGaranties.getRowFormatter().setStylePrimaryName(0, ContratsPresenter.RESOURCES.css().ligneEnteteColonne());
    }

    /** Construction du bloc des statistiques. */
    private void construireBlocStatistiques() {
        // Statistiques P/C
        panelStatsPrestationCotisation = new VerticalPanel();
        panelStatsPrestationCotisation.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panelStatsPrestationCotisation.setVisible(false);

        // Jauge Banco
        panelStatsJaugeBanco = new HorizontalPanel();
        panelStatsJaugeBanco.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panelStatsJaugeBanco.setVisible(false);

        panelStats = new HorizontalPanel();
        panelStats.setWidth(ContratsViewImplConstants.POURCENT_100);
        panelStats.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panelStats.add(panelStatsPrestationCotisation);
        panelStats.add(panelStatsJaugeBanco);
        panelStats.setCellWidth(panelStatsPrestationCotisation, "50%");
        panelStats.setCellWidth(panelStatsJaugeBanco, "50%");
    }

    @Override
    public void remplirBeneficiaireRoleRecapGaranties(List<String> listeBeneficiaires, List<String> listeRoles) {
        if (listeBeneficiaires != null) {
            // Remplissage de l'entête
            final Label entetePersonne = new Label(viewConstants.enteteTableauGarantiesColonnePersonne(), false);
            panelRecapitulatifGaranties.setWidget(0, 0, entetePersonne);
            panelRecapitulatifGaranties.getFlexCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
            panelRecapitulatifGaranties.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
            for (int idxBeneficiaire = 0; idxBeneficiaire < listeBeneficiaires.size(); idxBeneficiaire++) {
                final String nomPrenom = listeBeneficiaires.get(idxBeneficiaire);
                final String nomPrenomLimite = nomPrenom;
                final HTML personne = new HTML(nomPrenomLimite, false);
                personne.setTitle(nomPrenom.replace("<br/>", " ")); // affiche le nom complet

                panelRecapitulatifGaranties.setWidget(idxBeneficiaire + 1, 0, personne);
                panelRecapitulatifGaranties.getCellFormatter().setStylePrimaryName(idxBeneficiaire + 1, 0,
                    ContratsPresenter.RESOURCES.css().celluleEnteteLigne());
            }
            final Label enteteRole = new Label(viewConstants.enteteTableauGarantiesColonneRole(), false);
            panelRecapitulatifGaranties.setWidget(0, 1, enteteRole);
            panelRecapitulatifGaranties.getFlexCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
            panelRecapitulatifGaranties.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
            for (int idxRole = 0; idxRole < listeRoles.size(); idxRole++) {
                final Label role = new Label(listeRoles.get(idxRole), false);
                panelRecapitulatifGaranties.setWidget(idxRole + 1, 1, role);
                panelRecapitulatifGaranties.getFlexCellFormatter().setHorizontalAlignment(idxRole + 1, 1, HasHorizontalAlignment.ALIGN_CENTER);
                panelRecapitulatifGaranties.getCellFormatter().setStylePrimaryName(idxRole + 1, 1, ContratsPresenter.RESOURCES.css().celluleEnteteLigne());
            }
        }
    }

    @Override
    public HasClickHandlers ajouterGarantieEtValeursRecapGaranties(String libelleGarantie, boolean possedeGrillePresta,
        List<InfosGarantieBeneficiaireModel> listeValeurs, ConstantesModel constantes) {
        int idxColonneGarantieSuivante = panelRecapitulatifGaranties.getCellCount(1);

        // Remplissage de l'entête
        final Label enteteGarantie = new Label(libelleGarantie);
        // Ajout d'une image PDF si la garantie possède une grille de presta
        Image imagePdf = null;
        if (possedeGrillePresta) {
            imagePdf = new Image(ContratsPresenter.RESOURCES.imageIconePdf());
            imagePdf.setStylePrimaryName(ContratsPresenter.RESOURCES.css().imagePdfCliquable());
            imagePdf.setTitle(viewConstants.libelleVoirGrillePresta());
        }
        final VerticalPanel panelEnteteGarantie = new VerticalPanel();
        panelEnteteGarantie.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        panelEnteteGarantie.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panelEnteteGarantie.add(enteteGarantie);
        if (imagePdf != null) {
            panelEnteteGarantie.add(imagePdf);
        }
        panelRecapitulatifGaranties.setWidget(0, idxColonneGarantieSuivante, panelEnteteGarantie);
        panelRecapitulatifGaranties.getFlexCellFormatter().setVerticalAlignment(0, idxColonneGarantieSuivante, HasVerticalAlignment.ALIGN_TOP);
        panelRecapitulatifGaranties.getCellFormatter().setHorizontalAlignment(0, idxColonneGarantieSuivante, HasHorizontalAlignment.ALIGN_CENTER);
        if (listeValeurs != null) {
            for (int idxValeur = 0; idxValeur < listeValeurs.size(); idxValeur++) {
                final Label valeur = new Label();
                if (listeValeurs.get(idxValeur) != null && listeValeurs.get(idxValeur).getDateAdhesion() != null
                    && !"".equals(listeValeurs.get(idxValeur).getDateAdhesion())) {
                    valeur.setText(listeValeurs.get(idxValeur).getDateAdhesion());
                }
                panelRecapitulatifGaranties.setWidget(idxValeur + 1, idxColonneGarantieSuivante, valeur);
                panelRecapitulatifGaranties.getCellFormatter().setHorizontalAlignment(idxValeur + 1, idxColonneGarantieSuivante,
                    HasHorizontalAlignment.ALIGN_CENTER);
                // on change la couleur de case suivant le statut
                String css = ContratsPresenter.RESOURCES.css().celluleRecapitulatifGarantie();
                if (listeValeurs.get(idxValeur) != null && listeValeurs.get(idxValeur).getStatut() != null
                    && listeValeurs.get(idxValeur).getStatut().getIdentifiant() != null) {
                    final IdentifiantLibelleGwt statut = listeValeurs.get(idxValeur).getStatut();
                    if (constantes.getIdStatutGarantieEnCours().equals(statut.getIdentifiant())) {
                        css = ContratsPresenter.RESOURCES.css().couleurFondGarantieEnCours();
                    } else if (constantes.getIdStatutGarantieResiliee().equals(statut.getIdentifiant())) {
                        css = ContratsPresenter.RESOURCES.css().couleurFondGarantieResiliee();
                    } else if (constantes.getIdStatutGarantieFutur().equals(statut.getIdentifiant())) {
                        css = ContratsPresenter.RESOURCES.css().couleurFondGarantieFutur();
                    }
                }
                panelRecapitulatifGaranties.getCellFormatter().addStyleName(idxValeur + 1, idxColonneGarantieSuivante, css);
            }
        }
        // Incrémentation de l'index de colonne suivante
        idxColonneGarantieSuivante++;
        return imagePdf;
    }

    @Override
    public ContratInfosView ajouterContrat(ConstantesModel constantes, boolean afficherDateResiliation) {
        final ContratInfosViewImpl bloc = new ContratInfosViewImpl(ContratsPresenter.RESOURCES, constantes, afficherDateResiliation);
        panelListeContrats.add(bloc);
        return bloc;
    }

    @Override
    public void initJaugesBanco() {
        panelStatsJaugeBanco.clear();
    }

    @Override
    public void afficherJaugeBanco(final String dateDebut, final String dateFin, final DataTable donnees) {
        final Runnable onLoadCallback = new Runnable() {
            public void run() {
                final Options options = Options.create();
                options.setWidth(ContratsViewImplConstants.LARGEUR_CHART);
                options.setHeight(ContratsViewImplConstants.HAUTEUR_CHART);
                options.setLegend(LegendPosition.NONE);
                options.setColors(getListeCouleursJaugeBanco());
                options.setBackgroundColor(ContratsViewImplConstants.COULEUR_FOND_STATISTIQUE);
                options.setTitle(viewMessages.titreJaugeBanco(dateDebut, dateFin));
                options.setFontSize(12);
                final PieChart pieChartJaugeBanco = new PieChart(donnees, options);
                panelStatsJaugeBanco.add(pieChartJaugeBanco);
                panelStatsJaugeBanco.setVisible(true);
            }
        };
        VisualizationUtils.loadVisualizationApi(onLoadCallback, "corechart");
    }

    @Override
    public void afficherStatistiquePrestationCotisation(final DataTable dataLineChart) {
        final Runnable onLoadCallback = new Runnable() {
            public void run() {
                final Options options = Options.create();
                options.setWidth(ContratsViewImplConstants.LARGEUR_CHART);
                options.setHeight(ContratsViewImplConstants.HAUTEUR_CHART);
                options.setLegend(LegendPosition.NONE);
                options.setBackgroundColor(ContratsViewImplConstants.COULEUR_FOND_STATISTIQUE);
                options.setTitle(viewConstants.titreStatsPrestationCotisation());
                options.setFontSize(12);
                lineChartPrestationCotisation = new LineChart(dataLineChart, options);
                panelStatsPrestationCotisation.clear();
                panelStatsPrestationCotisation.add(lineChartPrestationCotisation);
                panelStatsPrestationCotisation.setVisible(true);
            }
        };
        VisualizationUtils.loadVisualizationApi(onLoadCallback, LineChart.PACKAGE);
    }

    @Override
    public void modifierCouleurStatut(String couleurStatut) {
        if (couleurStatut != null) {
            labelStatut.setStyleName(couleurStatut);
        }
    }

    /**
     * Définit les couleurs du camembert.
     * @return la liste des couleurs
     */
    private String[] getListeCouleursJaugeBanco() {
        final String[] listeCouleurs = new String[2];
        listeCouleurs[0] = ContratsViewImplConstants.COULEUR_RESERVE_BANCO_CONSOMMEE;
        listeCouleurs[1] = ContratsViewImplConstants.COULEUR_RESERVE_BANCO_RESTANTE;
        return listeCouleurs;
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
            libelleFidelite.append(nbAnnees).append(ContratsViewImplConstants.ESPACE);
            if (nbAnnees == 1) {
                libelleFidelite.append(viewConstants.libelleFideliteAnnee());
            } else {
                libelleFidelite.append(viewConstants.libelleFideliteAnnees());
            }
            if (nbMois > 0) {
                libelleFidelite.append(ContratsViewImplConstants.ESPACE).append(viewConstants.libelleFideliteEt()).append(ContratsViewImplConstants.ESPACE);
            }
        }
        if (nbMois > 0) {
            libelleFidelite.append(nbMois).append(ContratsViewImplConstants.ESPACE).append(viewConstants.libelleFideliteMois());
        }
        labelFidelite.setText(libelleFidelite.toString());
    }

    @Override
    public void setLabelTeletransmission(Boolean teletransmission) {
        if (teletransmission != null) {
            labelTeletransmission.setText(teletransmission.booleanValue() ? viewConstants.libelleTeletransmissionOui() : viewConstants
                    .libelleTeletransmissionNon());
        } else {
            labelTeletransmission.setText(viewConstants.libelleTeletransmissionNon());
        }
    }

    @Override
    public void afficherInfosCollectif(boolean visible) {
        lPopulation.setVisible(visible);
        labelPopulation.setVisible(visible);
    }

    @Override
    public void afficherDerniereRadiation(boolean visible) {
        panelDerniereRadiation.setVisible(visible);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasText getLabelStatut() {
        return labelStatut;
    }

    @Override
    public HasText getLabelPremiereMutualisation() {
        return labelPremiereMutualisation;
    }

    @Override
    public HasText getLabelDateDerniereRadiation() {
        return labelDateDerniereRadiation;
    }

    @Override
    public HasText getLabelMotifDerniereRadiation() {
        return labelMotifDerniereRadiation;
    }

    @Override
    public HasText getLabelPopulation() {
        return labelPopulation;
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

    @Override
    public void masquerJaugesBanco() {
        panelStatsJaugeBanco.setVisible(false);
    }

    @Override
    public void masquerStatistiquePrestationCotisation() {
        panelStatsPrestationCotisation.setVisible(false);
    }

    @Override
    public ContratsViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public void voirGrillePrestations(String url) {
        Window.open(url, "_blank", "resizable=yes,menubar=no,location=no");
    }

}

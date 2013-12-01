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
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.contrat.square.client.bundle.ComposantContratResources;
import com.square.composant.contrat.square.client.model.AjustementTarifModel;
import com.square.composant.contrat.square.client.model.ConstantesModel;
import com.square.composant.contrat.square.client.model.ContratModel;
import com.square.composant.contrat.square.client.model.GarantieModel;
import com.square.composant.contrat.square.client.model.InfosGarantieBeneficiaireModel;
import com.square.composant.contrat.square.client.model.InfosPaiementModel;
import com.square.composant.contrat.square.client.presenter.ContratsPresenter;
import com.square.composant.contrat.square.client.presenter.ContratContenuPresenter.ContratContenuView;

/**
 * Implémentation de la vue du contenu du contrat.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ContratContenuViewImpl extends Composite implements ContratContenuView {

    /** Ressources. */
    private ComposantContratResources resources;

    /** View constants. */
    private ContratContenuViewImplConstants viewConstants;

    /** Conteneur global. */
    private VerticalPanel conteneurGlobal;

    /** Conteneur garanties. */
    private VerticalPanel conteneurGaranties;

    /** Panel du récapitulatif des garanties. */
    private FlexTable panelRecapitulatifGaranties;

    /** Label Apporteur. */
    private Label labelApporteur;

    /** Label Apporteur. */
    private Label labelGestionnaire;

    /** Constantes. */
    private ConstantesModel constantes;

    private FlexTable panelContenuInfosPaiementPrestation;

    private FlexTable panelContenuInfosPaiementCotisation;

    /**
     * Constructeur.
     * @param resources les ressources
     * @param constantes les constantes
     */
    public ContratContenuViewImpl(ComposantContratResources resources, ConstantesModel constantes) {
        this.viewConstants = (ContratContenuViewImplConstants) GWT.create(ContratContenuViewImplConstants.class);
        this.resources = resources;
        this.constantes = constantes;
        conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setWidth(ContratsViewImplConstants.POURCENT_100);
        conteneurGlobal.setSpacing(10);
        construireBlocContenu();
        initWidget(conteneurGlobal);
    }

    /** Construction du bloc de contenu. */
    private void construireBlocContenu() {
        // Bloc du récapitulatif des garanties
        construireBlocRecapitulatifGaranties();
        // Bloc apporteur
        construireBlocApporteur();
        // Bloc des infos de paiement
        construireBlocInfosPaiement();
    }

    /** Construction du bloc du récapitulatif des garanties. */
    private void construireBlocRecapitulatifGaranties() {
        panelRecapitulatifGaranties = new FlexTable();
        panelRecapitulatifGaranties.setCellPadding(5);
        panelRecapitulatifGaranties.setWidth(ContratsViewImplConstants.POURCENT_100);
        panelRecapitulatifGaranties.setStylePrimaryName(resources.css().tableau());
        panelRecapitulatifGaranties.getRowFormatter().setStyleName(0, resources.css().ligneEnteteColonne());

        conteneurGlobal.add(panelRecapitulatifGaranties);
    }

    /** Construction du bloc apporteur. */
    private void construireBlocApporteur() {
        final Label lApporteur = new Label(viewConstants.libelleApporteur());
        final Label lGestionnaire = new Label(viewConstants.libelleGestionnaire());
        labelApporteur = new Label();
        labelGestionnaire = new Label();

        final FlexTable panelApporteur = new FlexTable();
        panelApporteur.setWidth(ContratsViewImplConstants.POURCENT_100);
        panelApporteur.setCellSpacing(5);
        panelApporteur.setWidget(0, 0, lApporteur);
        panelApporteur.setWidget(0, 1, labelApporteur);
        panelApporteur.setWidget(0, 2, lGestionnaire);
        panelApporteur.setWidget(0, 3, labelGestionnaire);
        panelApporteur.getColumnFormatter().setWidth(0, "10%");
        panelApporteur.getColumnFormatter().setWidth(1, "42%");
        panelApporteur.getColumnFormatter().setWidth(2, "10%");
        panelApporteur.getColumnFormatter().setWidth(3, "42%");

        conteneurGlobal.add(panelApporteur);
    }

    /** Construction des blocs des infos de paiement. */
    private void construireBlocInfosPaiement() {
        panelContenuInfosPaiementPrestation = new FlexTable();
        panelContenuInfosPaiementPrestation.setCellPadding(5);
        panelContenuInfosPaiementCotisation = new FlexTable();
        panelContenuInfosPaiementCotisation.setCellPadding(5);

        final CaptionPanel panelInfosPaiementPrestation = new CaptionPanel(viewConstants.titrePaiementPrestation());
        final CaptionPanel panelInfosPaiementCotisation = new CaptionPanel(viewConstants.titrePaiementCotisation());
        panelInfosPaiementPrestation.add(panelContenuInfosPaiementPrestation);
        panelInfosPaiementCotisation.add(panelContenuInfosPaiementCotisation);
        panelInfosPaiementPrestation.setHeight(ContratContenuViewImplConstants.HAUTEUR_CAPTIONPANEL_PAIEMENT);
        panelInfosPaiementCotisation.setHeight(ContratContenuViewImplConstants.HAUTEUR_CAPTIONPANEL_PAIEMENT);

        // Infos Paiement
        final HorizontalPanel panelInfosPaiement = new HorizontalPanel();
        panelInfosPaiement.setWidth(ContratsViewImplConstants.POURCENT_100);
        panelInfosPaiement.setSpacing(5);
        panelInfosPaiement.add(panelInfosPaiementPrestation);
        panelInfosPaiement.add(panelInfosPaiementCotisation);

        conteneurGlobal.add(panelInfosPaiement);
    }

    @Override
    public void chargerBlocInfosPaiement(ContratModel contrat, boolean segmentCollectif, boolean afficherJourPaiementCotis) {
        // Panel Infos de paiement prestation
        final InfosPaiementModel infosPaiementPrestation = contrat.getInfosPaiementPrestation();
        int row = 0;
        final Label lModePaiementPrestation = new Label(viewConstants.libelleModePaiement());
        String libelleMoyenPaiement = "";
        if (infosPaiementPrestation.getMoyenPaiement() != null) {
            libelleMoyenPaiement = infosPaiementPrestation.getMoyenPaiement().getLibelle();
        }
        final Label labelModePaiementPrestation = new Label(libelleMoyenPaiement);
        panelContenuInfosPaiementPrestation.setWidget(row, 0, lModePaiementPrestation);
        panelContenuInfosPaiementPrestation.setWidget(row++, 1, labelModePaiementPrestation);

        // Panel Infos de paiement cotisation
        final InfosPaiementModel infosPaiementCotisation = contrat.getInfosPaiementCotisation();
        row = 0;
        if (!segmentCollectif && infosPaiementCotisation.getMoyenPaiement() != null) {
            final Label lModePaiementCotisation = new Label(viewConstants.libelleModePaiement());
            final Label labelModePaiementCotisation = new Label(infosPaiementCotisation.getMoyenPaiement().getLibelle());
            panelContenuInfosPaiementCotisation.setWidget(row, 0, lModePaiementCotisation);
            panelContenuInfosPaiementCotisation.setWidget(row++, 1, labelModePaiementCotisation);
        }
        if (!segmentCollectif && infosPaiementCotisation.getFrequencePaiement() != null) {
            final Label lPeriodePaiementCotisation = new Label(viewConstants.libellePeriodePaiement());
            final Label labelPeriodePaiementCotisation = new Label(infosPaiementCotisation.getFrequencePaiement().getLibelle());
            panelContenuInfosPaiementCotisation.setWidget(row, 0, lPeriodePaiementCotisation);
            panelContenuInfosPaiementCotisation.setWidget(row++, 1, labelPeriodePaiementCotisation);
        }
        if (!segmentCollectif && afficherJourPaiementCotis && infosPaiementCotisation.getJourPaiement() != null) {
            final Label lJourPaiementCotisation = new Label(viewConstants.libelleJourPaiement());
            final Label labelJourPaiementCotisation = new Label(String.valueOf(infosPaiementCotisation.getJourPaiement()));
            panelContenuInfosPaiementCotisation.setWidget(row, 0, lJourPaiementCotisation);
            panelContenuInfosPaiementCotisation.setWidget(row++, 1, labelJourPaiementCotisation);
        }
        if (segmentCollectif && contrat.getSouscripteur() != null) {
            final Label lSouscripteurPayeurCotisation = new Label(viewConstants.libelleSouscripteurPayeur());
            final Label labelSouscripteurPayeurCotisation = new Label(contrat.getSouscripteur().getLibelle());
            panelContenuInfosPaiementCotisation.setWidget(row, 0, lSouscripteurPayeurCotisation);
            panelContenuInfosPaiementCotisation.setWidget(row++, 1, labelSouscripteurPayeurCotisation);
        }
    }

    @Override
    public void remplirBeneficiaireRoleRecapGaranties(List<String> listeBeneficiaires, List<String> listeRoles) {
        if (listeBeneficiaires != null) {
            // Remplissage de l'entête
            final Label entetePersonne = new Label(viewConstants.enteteTableauGarantiesColonnePersonne(), false);
            panelRecapitulatifGaranties.setWidget(0, 0, entetePersonne);
            panelRecapitulatifGaranties.getFlexCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
            panelRecapitulatifGaranties.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
            for (int idxBeneficiaire = 0; idxBeneficiaire < listeBeneficiaires.size(); idxBeneficiaire++) {
            	//limite le nombre de caractères
            	final String nomPrenom = listeBeneficiaires.get(idxBeneficiaire);
            	final String nomPrenomLimite = nomPrenom;
            	final HTML personne = new HTML(nomPrenomLimite, false);
            	personne.setTitle(nomPrenom.replace("<br/>", " ")); //affiche le nom complet

                panelRecapitulatifGaranties.setWidget(idxBeneficiaire + 1, 0, personne);
                panelRecapitulatifGaranties.getCellFormatter().setStylePrimaryName(idxBeneficiaire + 1, 0,
                    ContratsPresenter.RESOURCES.css().celluleEnteteLigne());
            }
            final Label enteteRole = new Label(viewConstants.enteteTableauGarantiesColonneRole(), false);
            panelRecapitulatifGaranties.setWidget(0, 1, enteteRole);
            panelRecapitulatifGaranties.getFlexCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
            panelRecapitulatifGaranties.getFlexCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
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
        List<InfosGarantieBeneficiaireModel> listeValeurs) {
        int idxColonneGarantieSuivante = panelRecapitulatifGaranties.getCellCount(0);
        // Remplissage de l'entête
        final VerticalPanel panelEnteteGarantie = new VerticalPanel();
        panelEnteteGarantie.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        panelEnteteGarantie.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        final Label enteteGarantie = new Label(libelleGarantie);

        panelEnteteGarantie.add(enteteGarantie);
        panelRecapitulatifGaranties.setWidget(0, idxColonneGarantieSuivante, panelEnteteGarantie);
        panelRecapitulatifGaranties.getFlexCellFormatter().setVerticalAlignment(0, idxColonneGarantieSuivante, HasVerticalAlignment.ALIGN_TOP);
        panelRecapitulatifGaranties.getFlexCellFormatter().setHorizontalAlignment(0, idxColonneGarantieSuivante, HasHorizontalAlignment.ALIGN_CENTER);
       if (listeValeurs != null) {
            for (int idxValeur = 0; idxValeur < listeValeurs.size(); idxValeur++) {
                final InfosGarantieBeneficiaireModel infosGarantieBenef = listeValeurs.get(idxValeur);
                if (infosGarantieBenef != null) {
                    final HTML infosGarantieBenefLabel = new HTML();
                    final String dateAdhesion = infosGarantieBenef.getDateAdhesion();
                    if (dateAdhesion != null && !"".equals(dateAdhesion)) {
                        final StringBuffer text = new StringBuffer(dateAdhesion);
                        final String dateResiliation = infosGarantieBenef.getDateResiliation();
                        if (dateResiliation != null && !"".equals(dateResiliation)) {
                            text.append("<br/>" + dateResiliation);
                        }
                        infosGarantieBenefLabel.setHTML(text.toString());
                        infosGarantieBenefLabel.setTitle(viewConstants.legendeInfosGarantieBeneficiaire());
                    }
                    panelRecapitulatifGaranties.setWidget(idxValeur + 1, idxColonneGarantieSuivante, infosGarantieBenefLabel);
                    panelRecapitulatifGaranties.getFlexCellFormatter().setHorizontalAlignment(idxValeur + 1, idxColonneGarantieSuivante,
                        HasHorizontalAlignment.ALIGN_CENTER);

                    // On applique un style css spécifique au statut de la garantie
                    final IdentifiantLibelleGwt statutGarantie = infosGarantieBenef.getStatut();
                    if (statutGarantie != null && statutGarantie.getIdentifiant() != null) {
                        if (constantes.getIdStatutGarantieEnCours().equals(statutGarantie.getIdentifiant())) {
                            panelRecapitulatifGaranties.getFlexCellFormatter().addStyleName(idxValeur + 1, idxColonneGarantieSuivante,
                                resources.css().couleurFondGarantieEnCours());
                        } else if (constantes.getIdStatutGarantieResiliee().equals(statutGarantie.getIdentifiant())) {
                            panelRecapitulatifGaranties.getFlexCellFormatter().addStyleName(idxValeur + 1, idxColonneGarantieSuivante,
                                resources.css().couleurFondGarantieResiliee());
                        } else if (constantes.getIdStatutGarantieFutur().equals(statutGarantie.getIdentifiant())) {
                            panelRecapitulatifGaranties.getFlexCellFormatter().addStyleName(idxValeur + 1, idxColonneGarantieSuivante,
                                resources.css().couleurFondGarantieFutur());
                        } else {
                            panelRecapitulatifGaranties.getFlexCellFormatter().addStyleName(idxValeur + 1, idxColonneGarantieSuivante,
                                resources.css().celluleRecapitulatifGarantie());
                        }
                    } else {
                        panelRecapitulatifGaranties.getFlexCellFormatter().addStyleName(idxValeur + 1, idxColonneGarantieSuivante,
                            resources.css().celluleRecapitulatifGarantie());
                    }
                }
            }
        }
        // Incrémentation de l'index de colonne suivante
        idxColonneGarantieSuivante++;
        // Ajout d'une image PDF si la garantie possède une grille de presta
        if (possedeGrillePresta) {
            final Image imagePdf = new Image(resources.imageIconePdf());
            imagePdf.setStyleName(resources.css().imagePdfCliquable());
            imagePdf.setTitle(viewConstants.libelleVoirGrillePresta());
            panelEnteteGarantie.add(imagePdf);
            return imagePdf;
        } else {
            return null;
        }
    }

    @Override
    public HasText getLabelApporteur() {
        return labelApporteur;
    }

    /**
     * {@inheritDoc}
     */
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
    public ContratContenuViewImplConstants getViewConstants() {
        return viewConstants;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void chargerGarantieAjustement(final List<AjustementTarifModel> listesAjustements) {
        // Tableau des ajustements
        final Label lReference = new Label(viewConstants.titreColonneAjustementReference());
        final Label lLibelle = new Label(viewConstants.titreColonneAjustementLibelle());
        final Label lTarif = new Label(viewConstants.titreColonneAjustementTarif());
        final Label lDateDebut = new Label(viewConstants.titreColonneAjustementDateDebut());
        final Label lDateFin = new Label(viewConstants.titreColonneAjustementDateFin());

        final FlexTable panelTableauAjustements = new FlexTable();
        panelTableauAjustements.setWidth(ContratsViewImplConstants.POURCENT_100);
        panelTableauAjustements.setStylePrimaryName(resources.css().tableau());
        panelTableauAjustements.setCellPadding(6);
        panelTableauAjustements.setWidget(0, 0, lReference);
        panelTableauAjustements.setWidget(0, 1, lLibelle);
        panelTableauAjustements.setWidget(0, 2, lTarif);
        panelTableauAjustements.setWidget(0, 3, lDateDebut);
        panelTableauAjustements.setWidget(0, 4, lDateFin);
        panelTableauAjustements.getRowFormatter().setStyleName(0, resources.css().ligneEnteteColonne());
        for (int idxCol = 0; idxCol < 5; idxCol++) {
            panelTableauAjustements.getFlexCellFormatter().setHorizontalAlignment(0, idxCol, HasHorizontalAlignment.ALIGN_CENTER);
        }

        for (AjustementTarifModel ajustement : listesAjustements) {
            final ContratGarantieAjustementViewImpl viewAjustement = new ContratGarantieAjustementViewImpl(ajustement);
            final int indexSuivant = panelTableauAjustements.getRowCount();
            panelTableauAjustements.setWidget(indexSuivant, 0, viewAjustement.getLabelReference());
            panelTableauAjustements.setWidget(indexSuivant, 1, viewAjustement.getLabelLibelle());
            panelTableauAjustements.setWidget(indexSuivant, 2, viewAjustement.getLabelTarif());
            panelTableauAjustements.setWidget(indexSuivant, 3, viewAjustement.getLabelDateDebut());
            panelTableauAjustements.setWidget(indexSuivant, 4, viewAjustement.getLabelDateFin());
            panelTableauAjustements.getFlexCellFormatter().setHorizontalAlignment(indexSuivant, 2, HasHorizontalAlignment.ALIGN_RIGHT);
            panelTableauAjustements.getFlexCellFormatter().setHorizontalAlignment(indexSuivant, 3, HasHorizontalAlignment.ALIGN_CENTER);
            panelTableauAjustements.getFlexCellFormatter().setHorizontalAlignment(indexSuivant, 4, HasHorizontalAlignment.ALIGN_CENTER);
        }

        // Ajustements
        final CaptionPanel captionPanelAjustements = new CaptionPanel(viewConstants.titreAjustements());
        captionPanelAjustements.add(panelTableauAjustements);
        conteneurGlobal.add(captionPanelAjustements);
    }

    @Override
    public void construireBlocGarantie() {
        conteneurGaranties = new VerticalPanel();
        conteneurGaranties.setWidth(ContratsViewImplConstants.POURCENT_100);
        conteneurGaranties.setSpacing(5);
        final CaptionPanel caption = new CaptionPanel(viewConstants.titreGaranties());
        caption.add(conteneurGaranties);
        conteneurGlobal.add(caption);
    }

    @Override
    public void ajouterGarantie(GarantieModel garantie, String style) {
        final ContratGarantieViewImpl contratGarantieView = new ContratGarantieViewImpl(resources, garantie, style);
        conteneurGaranties.add(contratGarantieView);
    }

    @Override
    public void voirGrillePrestations(String url) {
        Window.open(url, "_blank", "resizable=yes,menubar=no,location=no");
    }

    @Override
    public void nettoyer() {
        conteneurGlobal.clear();
        construireBlocContenu();
    }

}

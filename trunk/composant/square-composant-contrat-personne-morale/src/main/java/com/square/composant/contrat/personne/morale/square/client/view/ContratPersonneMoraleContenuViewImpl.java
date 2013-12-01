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
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.contrat.personne.morale.square.client.bundle.ComposantContratPersonneMoraleResources;
import com.square.composant.contrat.personne.morale.square.client.content.i18n.ComposantContratPersonneMoraleConstants;
import com.square.composant.contrat.personne.morale.square.client.model.ConstantesModel;
import com.square.composant.contrat.personne.morale.square.client.model.EffectifStatutModel;
import com.square.composant.contrat.personne.morale.square.client.model.GarantiePersonneMoraleModel;
import com.square.composant.contrat.personne.morale.square.client.model.RecapitulatifPopulationModel;
import com.square.composant.contrat.personne.morale.square.client.model.ValeursStatutsPopulationModel;
import com.square.composant.contrat.personne.morale.square.client.presenter.ContratPersonneMoraleContenuPresenter.ContratPersonneMoraleContenuView;

/**
 * Implémentation de la vue du contenu du contrat.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ContratPersonneMoraleContenuViewImpl extends Composite implements ContratPersonneMoraleContenuView {

    /** Ressources. */
    private ComposantContratPersonneMoraleResources ressources;

    /** View constants. */
    private ContratPersonneMoraleContenuViewImplConstants viewConstants;

    /** Constantes de l'application. */
    private ConstantesModel constantesApp;

    /** Conteneur global. */
    private VerticalPanel conteneurGlobal;

    /** Conteneur garanties. */
    private VerticalPanel conteneurGaranties;

    /** Table des populations. */
    private FlexTable tablePopulation;

    /** Table des informations. */
    private FlexTable tableInformations;

    /** Table des informations de paiement de cotisation. */
    private FlexTable tablePaiementCotisation;

    /** Panel des informations de paiement de cotisation. */
    private CaptionPanel panelInfosPaiementCotisation;

    /** Label du segment. */
    private Label libelleSegment;

    /** Label du type de gestion. */
    private Label libelleTypeGestion;

    /** Label du type de souscription. */
    private Label libelleTypeSouscription;

    /** Label du produit de gestion. */
    private Label libelleProduitGestion;

    /** Label de l'apporteur. */
    private Label libelleApporteur;

    /** Label du nombre d'adhérents. */
    private Label libelleNbAdherents;

    /** Label du nombre de bénéficiaires. */
    private Label libelleNbBeneficiaires;

    /** Label du mode de paiement. */
    private Label libelleModePaiement;

    /** Label de la période de paiement. */
    private Label libellePeriodePaiement;

    /** Label du jour de paiement. */
    private Label libelleJourPaiement;

    /** Label du type d'échéance. */
    private Label libelleTypeEcheance;

    /** Label du code de la banque. */
    private Label libelleCodeBanque;

    /** Label du code du guichet. */
    private Label libelleCodeGuichet;

    /** Label du code du compte. */
    private Label libelleCodeCompte;

    /** Label de la clé du RIB. */
    private Label libelleCleRIB;

    /**
     * Constructeur.
     * @param constantesApp constantes de l'application
     * @param ressources les ressources.
     */
    public ContratPersonneMoraleContenuViewImpl(ConstantesModel constantesApp, ComposantContratPersonneMoraleResources ressources) {
        this.viewConstants = (ContratPersonneMoraleContenuViewImplConstants) GWT.create(ContratPersonneMoraleContenuViewImplConstants.class);
        this.constantesApp = constantesApp;
        this.ressources = ressources;
        conteneurGlobal = new VerticalPanel();
        conteneurGlobal.setWidth(ComposantContratPersonneMoraleConstants.POURCENT_100);
        conteneurGlobal.setSpacing(10);
        construireContenu();
        initWidget(conteneurGlobal);
    }

    /** Construction du contenu. */
    private void construireContenu() {
        // Tableau des populations
        construireTableauPopulation();

        // Bloc des informations
        construireBlocInformations();
    }

    /** Construction du tableau des populations. */
    private void construireTableauPopulation() {
        tablePopulation = new FlexTable();
        tablePopulation.setCellPadding(6);
        tablePopulation.setStylePrimaryName(ressources.css().tableau());
        tablePopulation.getRowFormatter().setStyleName(0, ressources.css().ligneEnteteColonne());

        final ScrollPanel scrollPanelRecapitulatifGaranties = new ScrollPanel(tablePopulation);
        scrollPanelRecapitulatifGaranties.setWidth(ComposantContratPersonneMoraleConstants.LARGEUR_SCROLL_PANEL_CONTRAT);

        conteneurGlobal.add(scrollPanelRecapitulatifGaranties);
    }

    /** Construction du bloc des informations. */
    private void construireBlocInformations() {
        tableInformations = new FlexTable();
        tableInformations.setCellPadding(5);
        initTableInformations();
        final CaptionPanel panelInfos = new CaptionPanel(viewConstants.titrePanelInfos());
        panelInfos.add(tableInformations);

        tablePaiementCotisation = new FlexTable();
        tablePaiementCotisation.setCellPadding(5);
        initTablePaiementCotisation();
        panelInfosPaiementCotisation = new CaptionPanel(viewConstants.titrePanelInfosPaiementCotisation());
        panelInfosPaiementCotisation.setVisible(false);
        panelInfosPaiementCotisation.add(tablePaiementCotisation);

        final VerticalPanel panelInfosGlobales = new VerticalPanel();
        panelInfosGlobales.setWidth(ComposantContratPersonneMoraleConstants.POURCENT_100);
        panelInfosGlobales.setSpacing(5);
        panelInfosGlobales.add(panelInfos);
        panelInfosGlobales.add(panelInfosPaiementCotisation);

        conteneurGlobal.add(panelInfosGlobales);
    }

    /** Initialise le tableau des informations. */
    private void initTableInformations() {
        final Label labelSegment = new Label(viewConstants.labelSegment(), false);
        libelleSegment = new Label();
        final Label labelTypeGestion = new Label(viewConstants.labelTypeGestion(), false);
        libelleTypeGestion = new Label();
        final Label labelTypeSouscription = new Label(viewConstants.labelTypeSouscription(), false);
        libelleTypeSouscription = new Label();
        final Label labelProduitGestion = new Label(viewConstants.labelProduitGestion(), false);
        libelleProduitGestion = new Label();
        final Label labelApporteur = new Label(viewConstants.labelApporteur(), false);
        libelleApporteur = new Label();
        final Label labelNbAdherents = new Label(viewConstants.labelNbAdherents(), false);
        libelleNbAdherents = new Label();
        final Label labelNbBeneficiaires = new Label(viewConstants.labelNbBeneficiaires(), false);
        libelleNbBeneficiaires = new Label();

        tableInformations.setWidget(0, 0, labelSegment);
        tableInformations.setWidget(0, 1, libelleSegment);
        tableInformations.setWidget(0, 3, labelTypeGestion);
        tableInformations.setWidget(0, 4, libelleTypeGestion);
        tableInformations.setWidget(1, 0, labelTypeSouscription);
        tableInformations.setWidget(1, 1, libelleTypeSouscription);
        tableInformations.setWidget(2, 0, labelProduitGestion);
        tableInformations.setWidget(2, 1, libelleProduitGestion);
        tableInformations.setWidget(2, 3, labelApporteur);
        tableInformations.setWidget(2, 4, libelleApporteur);
        tableInformations.setWidget(3, 0, labelNbAdherents);
        tableInformations.setWidget(3, 1, libelleNbAdherents);
        tableInformations.setWidget(3, 3, labelNbBeneficiaires);
        tableInformations.setWidget(3, 4, libelleNbBeneficiaires);

        tableInformations.getColumnFormatter().setWidth(0, "15%");
        tableInformations.getColumnFormatter().setWidth(1, "40%");
        tableInformations.getColumnFormatter().setWidth(2, "5%");
        tableInformations.getColumnFormatter().setWidth(3, "15%");
        tableInformations.getColumnFormatter().setWidth(4, "25%");
        tableInformations.getCellFormatter().setVerticalAlignment(2, 0, HasAlignment.ALIGN_TOP);
        tableInformations.getCellFormatter().setVerticalAlignment(2, 1, HasAlignment.ALIGN_TOP);
        tableInformations.getCellFormatter().setVerticalAlignment(2, 3, HasAlignment.ALIGN_TOP);
        tableInformations.getCellFormatter().setVerticalAlignment(2, 4, HasAlignment.ALIGN_TOP);
    }

    /** Initialise le tableau des paiements de cotisation. */
    private void initTablePaiementCotisation() {
        final Label labelModePaiement = new Label(viewConstants.labelModePaiement(), false);
        libelleModePaiement = new Label();
        final Label labelPeriodePaiement = new Label(viewConstants.labelPeriodePaiement(), false);
        libellePeriodePaiement = new Label();
        final Label labelJourPaiement = new Label(viewConstants.labelJourPaiement(), false);
        libelleJourPaiement = new Label();
        final Label labelTypeEcheance = new Label(viewConstants.labelTypeEcheance(), false);
        libelleTypeEcheance = new Label();
        final Label labelRIB = new Label(viewConstants.labelRIB());
        libelleCodeBanque = new Label();
        libelleCodeGuichet = new Label();
        libelleCodeCompte = new Label();
        libelleCleRIB = new Label();
        final HorizontalPanel panelRIB = new HorizontalPanel();
        panelRIB.setSpacing(3);
        panelRIB.add(libelleCodeBanque);
        panelRIB.add(libelleCodeGuichet);
        panelRIB.add(libelleCodeCompte);
        panelRIB.add(libelleCleRIB);

        tablePaiementCotisation.setWidget(0, 0, labelModePaiement);
        tablePaiementCotisation.setWidget(0, 1, libelleModePaiement);
        tablePaiementCotisation.setWidget(1, 0, labelPeriodePaiement);
        tablePaiementCotisation.setWidget(1, 1, libellePeriodePaiement);
        tablePaiementCotisation.setWidget(2, 0, labelJourPaiement);
        tablePaiementCotisation.setWidget(2, 1, libelleJourPaiement);
        tablePaiementCotisation.setWidget(3, 0, labelTypeEcheance);
        tablePaiementCotisation.setWidget(3, 1, libelleTypeEcheance);
        tablePaiementCotisation.setWidget(4, 0, labelRIB);
        tablePaiementCotisation.setWidget(4, 1, panelRIB);
    }

    @Override
    public Widget asWidget() {
        return this;
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
    public void construireBlocGaranties() {
        conteneurGaranties = new VerticalPanel();
        conteneurGaranties.setWidth(ComposantContratPersonneMoraleConstants.POURCENT_100);
        conteneurGaranties.setSpacing(5);
        conteneurGlobal.add(conteneurGaranties);
    }

    @Override
    public HasClickHandlers ajouterGarantie(GarantiePersonneMoraleModel garantie) {
        final ContratGarantiePersonneMoraleViewImpl garantieView = new ContratGarantiePersonneMoraleViewImpl(constantesApp, ressources, garantie);
        conteneurGaranties.add(garantieView);

        // Ajout d'une image PDF si la garantie possède une grille de prestation
        if (garantie.getPossedeGrillePresta() != null && garantie.getPossedeGrillePresta()) {
            final Image imagePdf = new Image(ressources.imageIconePdf());
            imagePdf.setStyleName(ressources.css().imagePdfCliquable());
            imagePdf.setTitle(viewConstants.libelleVoirGrillePresta());
            garantieView.afficherIconePdf(imagePdf);
            return imagePdf;
        }
        else {
            return null;
        }
    }

    @Override
    public void chargerRecapitulatifPopulation(RecapitulatifPopulationModel recapitulatifPopulation) {
        if (recapitulatifPopulation.getListeStatuts() != null && recapitulatifPopulation.getListeStatuts().size() > 0) {
            tablePopulation.setWidget(0, 0, new Label(viewConstants.colonnePopulation()));
            int indexColonne = 1;
            for (IdentifiantLibelleGwt statut : recapitulatifPopulation.getListeStatuts()) {
                tablePopulation.setWidget(0, indexColonne, new Label(statut.getLibelle()));
                tablePopulation.getCellFormatter().setHorizontalAlignment(0, indexColonne, HasAlignment.ALIGN_CENTER);
                indexColonne++;
            }
            if (recapitulatifPopulation.getListeValeursPopulation() != null && recapitulatifPopulation.getListeValeursPopulation().size() > 0) {
                int indexLigne = 1;
                for (ValeursStatutsPopulationModel valeur : recapitulatifPopulation.getListeValeursPopulation()) {
                    tablePopulation.setWidget(indexLigne, 0, new Label(valeur.getLibellePopulation()));
                    int indexColonneValeurs = 1;
                    if (valeur.getListeEffectifsParStatut() != null && valeur.getListeEffectifsParStatut().size() > 0) {
                        for (EffectifStatutModel effectif : valeur.getListeEffectifsParStatut()) {
                            if (effectif.getEffectif() != null) {
                                tablePopulation.setWidget(indexLigne, indexColonneValeurs, new Label(effectif.getEffectif().toString()));
                            }
                            else {
                                tablePopulation.setWidget(indexLigne, indexColonneValeurs, new Label());
                            }
                            indexColonneValeurs++;
                        }
                    }
                    indexLigne++;
                }
            }
        }
    }

    @Override
    public HasText getLibelleApporteur() {
        return libelleApporteur;
    }

    @Override
    public HasText getLibelleCleRIB() {
        return libelleCleRIB;
    }

    @Override
    public HasText getLibelleCodeBanque() {
        return libelleCodeBanque;
    }

    @Override
    public HasText getLibelleCodeCompte() {
        return libelleCodeCompte;
    }

    @Override
    public HasText getLibelleCodeGuichet() {
        return libelleCodeGuichet;
    }

    @Override
    public HasText getLibelleJourPaiement() {
        return libelleJourPaiement;
    }

    @Override
    public HasText getLibelleModePaiement() {
        return libelleModePaiement;
    }

    @Override
    public HasText getLibelleNbAdherents() {
        return libelleNbAdherents;
    }

    @Override
    public HasText getLibelleNbBeneficiaires() {
        return libelleNbBeneficiaires;
    }

    @Override
    public HasText getLibellePeriodePaiement() {
        return libellePeriodePaiement;
    }

    @Override
    public HasText getLibelleProduitGestion() {
        return libelleProduitGestion;
    }

    @Override
    public HasText getLibelleSegment() {
        return libelleSegment;
    }

    @Override
    public HasText getLibelleTypeEcheance() {
        return libelleTypeEcheance;
    }

    @Override
    public HasText getLibelleTypeGestion() {
        return libelleTypeGestion;
    }

    @Override
    public HasText getLibelleTypeSouscription() {
        return libelleTypeSouscription;
    }

    @Override
    public void setPanelPaiementCotisationVisible(boolean isVisible) {
        panelInfosPaiementCotisation.setVisible(isVisible);
    }

    @Override
    public void voirGrillePrestations(String url) {
        Window.open(url, "_blank", "resizable=yes,menubar=no,location=no");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afficherErrorPopup(ErrorPopupConfiguration config) {
        ErrorPopup.afficher(config);
    }

}

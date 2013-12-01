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
package com.square.composant.fusion.square.client.composants;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composant.fusion.square.client.model.ConstantesModel;
import com.square.composant.fusion.square.client.model.EmailFusionModel;
import com.square.composant.fusion.square.client.model.PersonneCibleFusionModel;
import com.square.composant.fusion.square.client.model.PersonneSourceFusionModel;
import com.square.composant.fusion.square.client.model.ProprieteFusionnableEmailModel;
import com.square.composant.fusion.square.client.model.ProprieteFusionnableModel;
import com.square.composant.fusion.square.client.model.ProprieteFusionnableTelephoneModel;
import com.square.composant.fusion.square.client.model.TelephoneFusionModel;
import com.square.composant.fusion.square.client.presenter.composant.fusion.ComposantFusionPresenter;
import com.square.composant.fusion.square.client.presenter.composant.fusion.ComposantFusionPresenterConstants;

/**
 * Bloc permettant d'afficher les champs d'une personne pour la fusion.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class BlocChampsPersonneFusion extends VerticalPanel {

    /** Constantes du composant. */
    private BlocChampsPersonneFusionConstants composantConstants;

    /** Contenu du bloc. */
    private VerticalPanel pContenu;

    /** FlexTable contenant les propriétés de la personne à fusionner. */
    private FlexTable ftProprietes;

    /** Les informations de la personne source en cours pour la fusion. */
    private PersonneSourceFusionModel personneSourceEnCours;

    /** Les informations de la personne cible en cours pour la fusion. */
    private PersonneCibleFusionModel personneCibleEnCours;

    /** Booléen indiquant si la personne est source ou cible. */
    private boolean isSource;

    /** Label indiquant qu'il n'y a aucun champ à fusionner. */
    private Label lAucunChampsAFusionner;

    /** Bouton pour gérer la sélection. */
    private DecoratedButton btnSelection;

    /** Constantes. */
    private ConstantesModel constantes;

    /** Compteur pour savoir le nombre de champs sélectionnés pour la fusion. */
    private int nbChampsSelectionnesPourFusion = 0;

    /** Nombre total de champs fusionnables. */
    private int nbChampsFusionnables = 0;

    /**
     * Constructeur.
     * @param isSource booléen pour savoir si le bloc concerne une personne source ou une personne cible.
     * @param personneSourceFusion les informations de la personne source.
     * @param personneCibleFusion les informations de la personne cible.
     * @param constantes les constantes.
     */
    public BlocChampsPersonneFusion(boolean isSource, PersonneSourceFusionModel personneSourceFusion, PersonneCibleFusionModel personneCibleFusion,
        ConstantesModel constantes) {
        // Création des constantes du composant
        composantConstants = (BlocChampsPersonneFusionConstants) GWT.create(BlocChampsPersonneFusionConstants.class);

        this.personneSourceEnCours = personneSourceFusion;
        this.personneCibleEnCours = personneCibleFusion;
        this.isSource = isSource;
        this.constantes = constantes;

        this.setWidth(ComposantFusionPresenterConstants.POURCENT_100);

        lAucunChampsAFusionner = new Label(composantConstants.aucunChampAFusionner());
        btnSelection = new DecoratedButton(composantConstants.btnToutSelectionner());
        btnSelection.setVisible(this.isSource);
        btnSelection.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (composantConstants.btnToutSelectionner().equals(btnSelection.getText())) {
                    modifierSelectionFusion(true);
                } else {
                    modifierSelectionFusion(false);
                }
            }
        });

        String numClient = "";
        String dateCreation = "";
        if (isSource && personneSourceEnCours != null) {
            numClient = personneSourceEnCours.getNumeroClient();
            dateCreation = personneSourceEnCours.getDateCreation();
        } else if (!isSource && personneCibleEnCours != null) {
            numClient = personneCibleEnCours.getNumeroClient();
            dateCreation = personneCibleEnCours.getDateCreation();
        }
        final Label lNumClient = new Label(composantConstants.numeroClient() + " " + numClient);
        lNumClient.addStyleName(ComposantFusionPresenter.RESOURCES.css().numeroClient());
        final Label lDateCreation = new Label(composantConstants.dateCreation() + " " + dateCreation);
        lDateCreation.addStyleName(ComposantFusionPresenter.RESOURCES.css().dateCreation());

        // Ajout du numéro du client
        final VerticalPanel pInfosClient = new VerticalPanel();
        pInfosClient.add(lNumClient);
        pInfosClient.add(lDateCreation);
        pInfosClient.setSpacing(5);
        final HorizontalPanel pClient = new HorizontalPanel();
        pClient.setHeight(BlocChampsPersonneFusionConstants.HAUTEUR_NUM_CLIENT);
        pClient.setWidth(ComposantFusionPresenterConstants.POURCENT_100);
        pClient.add(pInfosClient);
        pClient.setCellVerticalAlignment(lNumClient, ALIGN_MIDDLE);
        pClient.add(btnSelection);
        pClient.setCellVerticalAlignment(btnSelection, ALIGN_MIDDLE);
        pClient.setCellHorizontalAlignment(btnSelection, ALIGN_RIGHT);

        pContenu = new VerticalPanel();
        pContenu.setWidth(ComposantFusionPresenterConstants.POURCENT_100);
        pContenu.addStyleName(ComposantFusionPresenter.RESOURCES.css().contenuBloc());
        pContenu.add(pClient);

        // Construction du tableau des champs de la personne
        construireTableauChamps();

        final CaptionPanel pBloc = new CaptionPanel();
        // Définition du style et du titre en fonction si le bloc est d'une personne source ou d'une personne cible
        if (this.isSource) {
            pBloc.addStyleName(ComposantFusionPresenter.RESOURCES.css().blocSource());
            pBloc.setCaptionText(composantConstants.titreClientSource());
        } else {
            pBloc.addStyleName(ComposantFusionPresenter.RESOURCES.css().blocCible());
            pBloc.setCaptionText(composantConstants.titreClientCible());
        }
        pBloc.add(pContenu);
        this.add(pBloc);
    }

    /**
     * Construit le tableau présentant les champs pour la fusion.
     */
    private void construireTableauChamps() {
        ftProprietes = new FlexTable();
        ftProprietes.setWidth(ComposantFusionPresenterConstants.POURCENT_100);
        ftProprietes.addStyleName(ComposantFusionPresenter.RESOURCES.css().tableau());

        // Construction des titres des colonnes du tableau
        final Label lTitreChamps = new Label(composantConstants.titreColonneChamp(), false);
        final Label lTitreValeur = new Label(composantConstants.titreColonneValeur());

        ftProprietes.setWidget(0, 0, lTitreChamps);
        ftProprietes.setWidget(0, 1, lTitreValeur);
        ftProprietes.getFlexCellFormatter().addStyleName(0, 0, ComposantFusionPresenter.RESOURCES.css().titreColonne());
        ftProprietes.getFlexCellFormatter().addStyleName(0, 1, ComposantFusionPresenter.RESOURCES.css().titreColonne());

        if (isSource) {
            // Ajout de la colonne de sélection pour la fusion
            final Label lTitreSelection = new Label(composantConstants.titreColonneFusionner());
            lTitreSelection.setTitle(composantConstants.titleColonneFusionner());
            ftProprietes.setWidget(0, 2, lTitreSelection);
            ftProprietes.getFlexCellFormatter().addStyleName(0, 2, ComposantFusionPresenter.RESOURCES.css().titreColonne());
        }

        if (personneSourceEnCours != null && personneCibleEnCours != null) {
            // On remplit les lignes du tableau avec les champs de la personne
            remplirChampsPersonne();
        }

        pContenu.add(ftProprietes);
    }

    /** Remplit le tableau avec les champs d'une personne. */
    private void remplirChampsPersonne() {
        int row = 1;
        String styleLigne = ComposantFusionPresenter.RESOURCES.css().cellClair();
        if (personneSourceEnCours.getNro().getDifferent() != null && personneSourceEnCours.getNro().getDifferent()) {
            final Label lNumClient = new Label(composantConstants.numRo());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getNro() != null && personneSourceEnCours.getNro().getValeur() != null) {
                    lValeur.setText(personneSourceEnCours.getNro().getValeur());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lNumClient, lValeur, personneSourceEnCours.getNro(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getNro() != null) {
                    lValeur.setText(personneCibleEnCours.getNro());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lNumClient, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getCivilite().getDifferent() != null && personneSourceEnCours.getCivilite().getDifferent()) {
            final Label lCivilite = new Label(composantConstants.civilite());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getCivilite().getValeur() != null && personneSourceEnCours.getCivilite().getValeur().getLibelle() != null) {
                    lValeur.setText(personneSourceEnCours.getCivilite().getValeur().getLibelle());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lCivilite, lValeur, personneSourceEnCours.getCivilite(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getCivilite() != null && personneCibleEnCours.getCivilite().getLibelle() != null) {
                    lValeur.setText(personneCibleEnCours.getCivilite().getLibelle());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lCivilite, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getNom().getDifferent() != null && personneSourceEnCours.getNom().getDifferent()) {
            final Label lNom = new Label(composantConstants.nom());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getNom().getValeur() != null) {
                    lValeur.setText(personneSourceEnCours.getNom().getValeur());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lNom, lValeur, personneSourceEnCours.getNom(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getNom() != null) {
                    lValeur.setText(personneCibleEnCours.getNom());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lNom, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getNomJeuneFille().getDifferent() != null && personneSourceEnCours.getNomJeuneFille().getDifferent()) {
            final Label lNom = new Label(composantConstants.nomJeuneFille());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getNomJeuneFille().getValeur() != null) {
                    lValeur.setText(personneSourceEnCours.getNomJeuneFille().getValeur());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lNom, lValeur, personneSourceEnCours.getNomJeuneFille(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getNomJeuneFille() != null) {
                    lValeur.setText(personneCibleEnCours.getNomJeuneFille());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lNom, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getPrenom().getDifferent() != null && personneSourceEnCours.getPrenom().getDifferent()) {
            final Label lPrenom = new Label(composantConstants.prenom());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getPrenom().getValeur() != null) {
                    lValeur.setText(personneSourceEnCours.getPrenom().getValeur());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lPrenom, lValeur, personneSourceEnCours.getPrenom(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getPrenom() != null) {
                    lValeur.setText(personneCibleEnCours.getPrenom());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lPrenom, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getDateNaissance().getDifferent() != null && personneSourceEnCours.getDateNaissance().getDifferent()) {
            final Label lDateNaissance = new Label(composantConstants.dateNaissance());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getDateNaissance().getValeur() != null) {
                    lValeur.setText(personneSourceEnCours.getDateNaissance().getValeur());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lDateNaissance, lValeur, personneSourceEnCours.getDateNaissance(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getDateNaissance() != null) {
                    lValeur.setText(personneCibleEnCours.getDateNaissance());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lDateNaissance, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getDateDeces().getDifferent() != null && personneSourceEnCours.getDateDeces().getDifferent()) {
            final Label lDateDeces = new Label(composantConstants.dateDeces());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getDateDeces().getValeur() != null) {
                    lValeur.setText(personneSourceEnCours.getDateDeces().getValeur());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lDateDeces, lValeur, personneSourceEnCours.getDateDeces(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getDateDeces() != null) {
                    lValeur.setText(personneCibleEnCours.getDateDeces());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lDateDeces, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getDecede().getDifferent() != null && personneSourceEnCours.getDecede().getDifferent()) {
            final Label lDecede = new Label(composantConstants.decede());
            final Label lValeur = new Label();
            if (isSource) {
                if (personneSourceEnCours.getDecede().getValeur() != null && personneSourceEnCours.getDecede().getValeur()) {
                    lValeur.setText(composantConstants.oui());
                } else {
                    lValeur.setText(composantConstants.non());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lDecede, lValeur, personneSourceEnCours.getDecede(), styleLigne);
            } else {
                if (personneCibleEnCours.getDecede() != null && personneCibleEnCours.getDecede()) {
                    lValeur.setText(composantConstants.oui());
                } else {
                    lValeur.setText(composantConstants.non());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lDecede, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getSitFam().getDifferent() != null && personneSourceEnCours.getSitFam().getDifferent()) {
            final Label lSituationFam = new Label(composantConstants.situationFam());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getSitFam().getValeur() != null && personneSourceEnCours.getSitFam().getValeur().getLibelle() != null) {
                    lValeur.setText(personneSourceEnCours.getSitFam().getValeur().getLibelle());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lSituationFam, lValeur, personneSourceEnCours.getSitFam(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getSitFam() != null && personneCibleEnCours.getSitFam().getLibelle() != null) {
                    lValeur.setText(personneCibleEnCours.getSitFam().getLibelle());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lSituationFam, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getProfession().getDifferent() != null && personneSourceEnCours.getProfession().getDifferent()) {
            final Label lProfession = new Label(composantConstants.profession());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getProfession().getValeur() != null && personneSourceEnCours.getProfession().getValeur().getLibelle() != null) {
                    lValeur.setText(personneSourceEnCours.getProfession().getValeur().getLibelle());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lProfession, lValeur, personneSourceEnCours.getProfession(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getProfession() != null && personneCibleEnCours.getProfession().getLibelle() != null) {
                    lValeur.setText(personneCibleEnCours.getProfession().getLibelle());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lProfession, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getNaturePersonne().getDifferent() != null && personneSourceEnCours.getNaturePersonne().getDifferent()) {
            final Label lNaturePersonne = new Label(composantConstants.naturePersonne());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getNaturePersonne().getValeur() != null && personneSourceEnCours.getNaturePersonne().getValeur().getLibelle() != null) {
                    lValeur.setText(personneSourceEnCours.getNaturePersonne().getValeur().getLibelle());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lNaturePersonne, lValeur, personneSourceEnCours.getNaturePersonne(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getNaturePersonne() != null && personneCibleEnCours.getNaturePersonne().getLibelle() != null) {
                    lValeur.setText(personneCibleEnCours.getNaturePersonne().getLibelle());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lNaturePersonne, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getStatut().getDifferent() != null && personneSourceEnCours.getStatut().getDifferent()) {
            final Label lStatut = new Label(composantConstants.statut());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getStatut().getValeur() != null && personneSourceEnCours.getStatut().getValeur().getLibelle() != null) {
                    lValeur.setText(personneSourceEnCours.getStatut().getValeur().getLibelle());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lStatut, lValeur, personneSourceEnCours.getStatut(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getStatut() != null && personneCibleEnCours.getStatut().getLibelle() != null) {
                    lValeur.setText(personneCibleEnCours.getStatut().getLibelle());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lStatut, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getCaisse().getDifferent() != null && personneSourceEnCours.getCaisse().getDifferent()) {
            final Label lCaisse = new Label(composantConstants.caisse());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getCaisse().getValeur() != null && personneSourceEnCours.getCaisse().getValeur().getLibelle() != null) {
                    lValeur.setText(personneSourceEnCours.getCaisse().getValeur().getLibelle());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lCaisse, lValeur, personneSourceEnCours.getCaisse(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getCaisse() != null && personneCibleEnCours.getCaisse().getLibelle() != null) {
                    lValeur.setText(personneCibleEnCours.getCaisse().getLibelle());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lCaisse, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getCaisseRegime().getDifferent() != null && personneSourceEnCours.getCaisseRegime().getDifferent()) {
            final Label lCaisseRegime = new Label(composantConstants.caisseRegime());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getCaisseRegime().getValeur() != null && personneSourceEnCours.getCaisseRegime().getValeur().getLibelle() != null) {
                    lValeur.setText(personneSourceEnCours.getCaisseRegime().getValeur().getLibelle());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lCaisseRegime, lValeur, personneSourceEnCours.getCaisseRegime(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getCaisseRegime() != null && personneCibleEnCours.getCaisseRegime().getLibelle() != null) {
                    lValeur.setText(personneCibleEnCours.getCaisseRegime().getLibelle());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lCaisseRegime, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getCsp().getDifferent() != null && personneSourceEnCours.getCsp().getDifferent()) {
            final Label lCsp = new Label(composantConstants.csp());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getCsp().getValeur() != null && personneSourceEnCours.getCsp().getValeur().getLibelle() != null) {
                    lValeur.setText(personneSourceEnCours.getCsp().getValeur().getLibelle());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lCsp, lValeur, personneSourceEnCours.getCsp(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getCsp() != null && personneCibleEnCours.getCsp().getLibelle() != null) {
                    lValeur.setText(personneCibleEnCours.getCsp().getLibelle());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lCsp, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getSegment().getDifferent() != null && personneSourceEnCours.getSegment().getDifferent()) {
            final Label lSegment = new Label(composantConstants.segment());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getSegment().getValeur() != null && personneSourceEnCours.getSegment().getValeur().getLibelle() != null) {
                    lValeur.setText(personneSourceEnCours.getSegment().getValeur().getLibelle());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lSegment, lValeur, personneSourceEnCours.getSegment(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getSegment() != null && personneCibleEnCours.getSegment().getLibelle() != null) {
                    lValeur.setText(personneCibleEnCours.getSegment().getLibelle());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lSegment, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getReseauVente().getDifferent() != null && personneSourceEnCours.getReseauVente().getDifferent()) {
            final Label lReseauVente = new Label(composantConstants.reseauVente());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getReseauVente().getValeur() != null && personneSourceEnCours.getReseauVente().getValeur().getLibelle() != null) {
                    lValeur.setText(personneSourceEnCours.getReseauVente().getValeur().getLibelle());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lReseauVente, lValeur, personneSourceEnCours.getReseauVente(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getReseauVente() != null && personneCibleEnCours.getReseauVente().getLibelle() != null) {
                    lValeur.setText(personneCibleEnCours.getReseauVente().getLibelle());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lReseauVente, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getCommercial().getDifferent() != null && personneSourceEnCours.getCommercial().getDifferent()) {
            final Label lCommercial = new Label(composantConstants.commercial());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getCommercial().getValeur() != null && personneSourceEnCours.getCommercial().getValeur().getLibelle() != null) {
                    lValeur.setText(personneSourceEnCours.getCommercial().getValeur().getLibelle());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lCommercial, lValeur, personneSourceEnCours.getCommercial(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getCommercial() != null && personneCibleEnCours.getCommercial().getLibelle() != null) {
                    lValeur.setText(personneCibleEnCours.getCommercial().getLibelle());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lCommercial, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getAgence().getDifferent() != null && personneSourceEnCours.getAgence().getDifferent()) {
            final Label lAgence = new Label(composantConstants.agence());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getAgence().getValeur() != null && personneSourceEnCours.getAgence().getValeur().getLibelle() != null) {
                    lValeur.setText(personneSourceEnCours.getAgence().getValeur().getLibelle());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lAgence, lValeur, personneSourceEnCours.getAgence(), styleLigne);
            } else {
                final Label lValeur = new Label();
                if (personneCibleEnCours.getAgence() != null && personneCibleEnCours.getAgence().getLibelle() != null) {
                    lValeur.setText("");
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lAgence, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getAdressePrincipale().getDifferent() != null && personneSourceEnCours.getAdressePrincipale().getDifferent()) {
            final Label lAdressePrincipale = new Label(composantConstants.adressePrincipale());
            if (isSource) {
                final Label lValeur = new Label("");
                if (personneSourceEnCours.getAdressePrincipale().getValeur() != null
                    && personneSourceEnCours.getAdressePrincipale().getValeur().getLibelleAdresse() != null) {
                    lValeur.setText(personneSourceEnCours.getAdressePrincipale().getValeur().getLibelleAdresse());
                }
                styleLigne = ajouterChampSourceBlocFusion(row++, lAdressePrincipale, lValeur, personneSourceEnCours.getAdressePrincipale(), styleLigne);
            } else {
                final Label lValeur = new Label("");
                if (personneCibleEnCours.getAdressePrincipale() != null && personneCibleEnCours.getAdressePrincipale().getLibelleAdresse() != null) {
                    lValeur.setText(personneCibleEnCours.getAdressePrincipale().getLibelleAdresse());
                }
                styleLigne = ajouterChampCibleBlocFusion(row++, lAdressePrincipale, lValeur, styleLigne);
            }
        }
        if (personneSourceEnCours.getListeTelephones() != null && personneSourceEnCours.getListeTelephones().size() > 0) {
        	List<ProprieteFusionnableTelephoneModel> listeTelephonesVides = new ArrayList<ProprieteFusionnableTelephoneModel>();
            for (int i = 0; i < personneSourceEnCours.getListeTelephones().size(); i++) {
                final ProprieteFusionnableTelephoneModel telephone = personneSourceEnCours.getListeTelephones().get(i);
                if (telephone.getDifferent() != null && telephone.getDifferent()) {
                    final String typeTel;
                    if (constantes.getIdNatureTelephoneFixe().equals(telephone.getValeur().getNature().getIdentifiant())) {
                    	typeTel = composantConstants.telFixe();
                    }
                    else {
                    	typeTel = composantConstants.telPortable();
                    }
                    if (isSource) {
                        final Label lValeur = new Label(telephone.getValeur().getNumero());
                        if (telephone.getValeur().getNumero() == null) {
                            lValeur.setText("");
                        }
                        styleLigne = ajouterChampSourceBlocFusion(row++, new Label(typeTel), lValeur, telephone, styleLigne);
                        if (i < personneCibleEnCours.getListeTelephones().size()) {
	                        final TelephoneFusionModel telephoneCible = personneCibleEnCours.getListeTelephones().get(i);
	                        if (telephoneCible.getNature().getIdentifiant().equals(telephone.getValeur().getNature().getIdentifiant())
	                        		&& telephoneCible.getIdentifiant() != null) {
		                        final ProprieteFusionnableTelephoneModel proprieteTelephoneVide = new ProprieteFusionnableTelephoneModel();
		                        proprieteTelephoneVide.setIsSelectionnable(true);
		                        final TelephoneFusionModel telephoneVide = new TelephoneFusionModel();
		                        telephoneVide.setNature(telephone.getValeur().getNature());
		                        telephoneVide.setIdentifiant(telephone.getValeur().getIdentifiant());
		                        proprieteTelephoneVide.setValeur(telephoneVide);
		                        proprieteTelephoneVide.setDifferent(true);
		                        listeTelephonesVides.add(proprieteTelephoneVide);
		                        styleLigne = ajouterChampSourceBlocFusion(row++, new Label(typeTel), new Label(""), proprieteTelephoneVide, styleLigne);
	                        }
	                    }
                    }
                    else {
                        if (i < personneCibleEnCours.getListeTelephones().size()) {
                            final TelephoneFusionModel telephoneCible = personneCibleEnCours.getListeTelephones().get(i);
                            final Label lValeur = new Label(telephoneCible.getNumero());

                            if (telephoneCible.getNumero() == null || telephoneCible.getIdentifiant() == null) {
                                lValeur.setText("");
                                styleLigne = ajouterChampCibleBlocFusion(row++, new Label(typeTel), lValeur, styleLigne);
                            }
                            else {
	                            styleLigne = ajouterChampCibleBlocFusion(row++, new Label(typeTel), new Label(""), styleLigne);
	                            styleLigne = ajouterChampCibleBlocFusion(row++, new Label(typeTel), lValeur, styleLigne);
                            }
                        }
                        else if (personneSourceEnCours.getListeTelephones().get(i).getValeur().getNumero() != null) {
                            final Label lValeur = new Label("");
                            styleLigne = ajouterChampCibleBlocFusion(row++, new Label(typeTel), lValeur, styleLigne);
                        }
                    }
                }
            }
            personneSourceEnCours.getListeTelephones().addAll(listeTelephonesVides);
        }
        if (personneSourceEnCours.getListeEmails() != null && personneSourceEnCours.getListeEmails().size() > 0) {
            for (int i = 0; i < personneSourceEnCours.getListeEmails().size(); i++) {
                final ProprieteFusionnableEmailModel email = personneSourceEnCours.getListeEmails().get(i);
                if (email.getDifferent() != null && email.getDifferent()) {
                    final Label lEmail = new Label(composantConstants.email());
                    if (isSource) {
                        final Label lValeur = new Label(email.getValeur().getAdresse());
                        if (email.getValeur().getAdresse() == null) {
                            lValeur.setText("");
                        }
                        styleLigne = ajouterChampSourceBlocFusion(row++, lEmail, lValeur, email, styleLigne);
                    } else {
                        if (i < personneCibleEnCours.getListeEmails().size()) {
                            final EmailFusionModel emailCible = personneCibleEnCours.getListeEmails().get(i);
                            final Label lValeur = new Label(emailCible.getAdresse());
                            if (emailCible.getAdresse() == null) {
                                lValeur.setText("");
                            }
                            styleLigne = ajouterChampCibleBlocFusion(row++, lEmail, lValeur, styleLigne);
                        } else {
                            final Label lValeur = new Label();
                            styleLigne = ajouterChampCibleBlocFusion(row++, lEmail, lValeur, styleLigne);
                        }
                    }
                }
            }
        }

        if (row == 1) {
            ftProprietes.setWidget(1, 0, lAucunChampsAFusionner);
            ftProprietes.getCellFormatter().setHorizontalAlignment(1, 0, HasAlignment.ALIGN_CENTER);
            ftProprietes.getCellFormatter().setVerticalAlignment(1, 0, HasAlignment.ALIGN_MIDDLE);
            ftProprietes.getFlexCellFormatter().setColSpan(1, 0, 3);
            ftProprietes.getFlexCellFormatter().addStyleName(1, 0, ComposantFusionPresenter.RESOURCES.css().cellClair());
            btnSelection.setEnabled(false);
        } else {
            btnSelection.setEnabled(true);
        }
    }

    /**
     * Ajoute un champ de la source dans le bloc des propriétés à fusionner.
     * @param row la ligne où ajouter le champ.
     * @param label le label du champ.
     * @param lChamp la valeur du champ.
     * @param champ le champ.
     * @param styleLigne le style de la ligne.
     * @return le style de la ligne
     */
    private String ajouterChampSourceBlocFusion(int row, Label label, Label lChamp, final ProprieteFusionnableModel champ, String styleLigne) {
        String style = styleLigne;
        ftProprietes.setWidget(row, 0, label);
        ftProprietes.setWidget(row, 1, lChamp);

        if (champ.getIsSelectionnable() != null && champ.getIsSelectionnable()) {
            final CheckBox cb = new CheckBox();
            cb.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                @Override
                public void onValueChange(ValueChangeEvent<Boolean> event) {
                    if (cb.getValue() != null && cb.getValue()) {
                        champ.setAFusionner(true);
                        nbChampsSelectionnesPourFusion++;
                        if (nbChampsSelectionnesPourFusion == nbChampsFusionnables) {
                            btnSelection.setText(composantConstants.btnRienSelectionner());
                        }
                    } else {
                        champ.setAFusionner(false);
                        nbChampsSelectionnesPourFusion--;
                        if (nbChampsSelectionnesPourFusion != nbChampsFusionnables) {
                            btnSelection.setText(composantConstants.btnToutSelectionner());
                        }
                    }
                }
            });
            ftProprietes.setWidget(row, 2, cb);
            ftProprietes.getFlexCellFormatter().setHorizontalAlignment(row, 2, ALIGN_CENTER);
        }

        // Définition du style pour alterner ligne sombre et ligne claire
        if (ComposantFusionPresenter.RESOURCES.css().cellClair().equals(style)) {
            style = ComposantFusionPresenter.RESOURCES.css().cellClair();
        } else {
            style = ComposantFusionPresenter.RESOURCES.css().cellSombre();
        }
        ftProprietes.getCellFormatter().addStyleName(row, 0, style);
        ftProprietes.getCellFormatter().addStyleName(row, 1, style);
        ftProprietes.getCellFormatter().addStyleName(row, 2, style);

        ftProprietes.getCellFormatter().setHeight(row, 0, BlocChampsPersonneFusionConstants.HAUTEUR_LIGNES_PROPRIETES);
        ftProprietes.getCellFormatter().setHeight(row, 1, BlocChampsPersonneFusionConstants.HAUTEUR_LIGNES_PROPRIETES);
        ftProprietes.getCellFormatter().setHeight(row, 2, BlocChampsPersonneFusionConstants.HAUTEUR_LIGNES_PROPRIETES);
        nbChampsFusionnables++;
        return style;
    }

    /**
     * Ajoute un champ cible dans le bloc des propriétés à fusionner.
     * @param row la ligne où ajouter le champ.
     * @param label le label du champ.
     * @param lChamp la valeur du champ.
     * @param styleLigne le style de la ligne.
     * @return le style de la ligne.
     */
    private String ajouterChampCibleBlocFusion(int row, Label label, Label lChamp, String styleLigne) {
        ftProprietes.setWidget(row, 0, label);
        ftProprietes.setWidget(row, 1, lChamp);

        // Définition du style pour alterner ligne sombre et ligne claire
        String style = styleLigne;
        if (ComposantFusionPresenter.RESOURCES.css().cellClair().equals(styleLigne)) {
            style = ComposantFusionPresenter.RESOURCES.css().cellSombre();
        } else {
            style = ComposantFusionPresenter.RESOURCES.css().cellClair();
        }
        ftProprietes.getCellFormatter().addStyleName(row, 0, style);
        ftProprietes.getCellFormatter().addStyleName(row, 1, style);

        ftProprietes.getCellFormatter().setHeight(row, 0, BlocChampsPersonneFusionConstants.HAUTEUR_LIGNES_PROPRIETES);
        ftProprietes.getCellFormatter().setHeight(row, 1, BlocChampsPersonneFusionConstants.HAUTEUR_LIGNES_PROPRIETES);
        return style;
    }

    /**
     * Change la sélection des champs pour la fusion (tous ou aucun).
     * @param isSelectionne true si tous les champs doivent être sélectionnés, false si aucun.
     */
    public void modifierSelectionFusion(boolean isSelectionne) {
        // Mise à jour champs sélectionnés
        if (personneSourceEnCours.getNro().getDifferent() != null && personneSourceEnCours.getNro().getDifferent()) {
            personneSourceEnCours.getNro().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getCivilite().getDifferent() != null && personneSourceEnCours.getCivilite().getDifferent()) {
            personneSourceEnCours.getCivilite().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getNom().getDifferent() != null && personneSourceEnCours.getNom().getDifferent()) {
            personneSourceEnCours.getNom().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getNomJeuneFille().getDifferent() != null && personneSourceEnCours.getNomJeuneFille().getDifferent()) {
            personneSourceEnCours.getNomJeuneFille().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getPrenom().getDifferent() != null && personneSourceEnCours.getPrenom().getDifferent()) {
            personneSourceEnCours.getPrenom().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getDateNaissance().getDifferent() != null && personneSourceEnCours.getDateNaissance().getDifferent()) {
            personneSourceEnCours.getDateNaissance().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getDateDeces().getDifferent() != null && personneSourceEnCours.getDateDeces().getDifferent()) {
            personneSourceEnCours.getDateDeces().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getDecede().getDifferent() != null && personneSourceEnCours.getDecede().getDifferent()) {
            personneSourceEnCours.getDecede().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getSitFam().getDifferent() != null && personneSourceEnCours.getSitFam().getDifferent()) {
            personneSourceEnCours.getSitFam().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getProfession().getDifferent() != null && personneSourceEnCours.getProfession().getDifferent()) {
            personneSourceEnCours.getProfession().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getNaturePersonne().getDifferent() != null && personneSourceEnCours.getNaturePersonne().getDifferent()
            && personneSourceEnCours.getNaturePersonne().getIsSelectionnable() != null && personneSourceEnCours.getNaturePersonne().getIsSelectionnable()) {
            personneSourceEnCours.getNaturePersonne().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getStatut().getDifferent() && personneSourceEnCours.getStatut().getDifferent()) {
            personneSourceEnCours.getStatut().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getCaisse().getDifferent() != null && personneSourceEnCours.getCaisse().getDifferent()) {
            personneSourceEnCours.getCaisse().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getCaisseRegime().getDifferent() != null && personneSourceEnCours.getCaisseRegime().getDifferent()) {
            personneSourceEnCours.getCaisseRegime().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getCsp().getDifferent() != null && personneSourceEnCours.getCsp().getDifferent()) {
            personneSourceEnCours.getCsp().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getSegment().getDifferent() != null && personneSourceEnCours.getSegment().getDifferent()) {
            personneSourceEnCours.getSegment().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getReseauVente().getDifferent() != null && personneSourceEnCours.getReseauVente().getDifferent()) {
            personneSourceEnCours.getReseauVente().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getCommercial().getDifferent() != null && personneSourceEnCours.getCommercial().getDifferent()) {
            personneSourceEnCours.getCommercial().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getAgence().getDifferent() != null && personneSourceEnCours.getAgence().getDifferent()) {
            personneSourceEnCours.getAgence().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getAdressePrincipale().getDifferent() != null && personneSourceEnCours.getAdressePrincipale().getDifferent()) {
            personneSourceEnCours.getAdressePrincipale().setAFusionner(isSelectionne);
        }
        if (personneSourceEnCours.getListeTelephones() != null && !"".equals(personneSourceEnCours.getListeTelephones().size() > 0)) {
            for (ProprieteFusionnableTelephoneModel telephone : personneSourceEnCours.getListeTelephones()) {
                if (telephone.getDifferent() != null && telephone.getDifferent()) {
                    telephone.setAFusionner(isSelectionne);
                }
            }
        }
        if (personneSourceEnCours.getListeEmails() != null && !"".equals(personneSourceEnCours.getListeEmails().size() > 0)) {
            for (ProprieteFusionnableEmailModel email : personneSourceEnCours.getListeEmails()) {
                if (email.getDifferent()) {
                    email.setAFusionner(isSelectionne);
                }
            }
        }

        // Mise à jour des CheckBox correspondantes
        for (int i = 1; i < ftProprietes.getRowCount(); i++) {
            if (ftProprietes.getWidget(i, 2) != null) {
                final CheckBox cb = (CheckBox) ftProprietes.getWidget(i, 2);
                cb.setValue(isSelectionne);
            }
        }

        // Mise à jour du texte sur le bouton de sélection
        if (isSelectionne) {
            btnSelection.setText(composantConstants.btnRienSelectionner());
        } else {
            btnSelection.setText(composantConstants.btnToutSelectionner());
        }
    }
}

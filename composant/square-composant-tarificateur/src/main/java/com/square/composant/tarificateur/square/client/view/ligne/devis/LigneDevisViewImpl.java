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
package com.square.composant.tarificateur.square.client.view.ligne.devis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.ConstantesModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.ligne.LigneDevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.ligne.ValeurCritereLigneDevisModel;
import com.square.composant.tarificateur.square.client.model.personne.BeneficiaireModel;
import com.square.composant.tarificateur.square.client.presenter.ligne.devis.LigneDevisPresenter.LigneDevisView;

/**
 * Vue des lignes de devis.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class LigneDevisViewImpl extends Composite implements LigneDevisView {

    /** View constants. */
    private static LigneDevisViewImplConstants viewConstants;

    private FlexTable tableauLignesDevis;

    private CheckBox cbTransfertLigne;

    private Label lDateCreation;

    private Label lCreateur;

    private Label lProvenance;

    private DecoratedButton btnVisuGrille;

    private DecoratedButton btnModifierLigne;

    private FlexTable ftEnteteDevis;

    private VerticalPanel conteneur;

    private Map<Long, HasValue<Boolean>> mapRbLigneAdhesion;

    private Map<Long, HasValue<Boolean>> mapRbLigneRefus;

    private Map<Long, HasValue<Boolean>> mapRbLigneEnAttente;

    private Map<Long, HasValue<Boolean>> mapRbLigneCorbeille;

    private Map<Long, HasValue<Boolean>> mapCbImprimerLigne;

    private ConstantesModel constantesApp;

    private NumberFormat numberFormat = NumberFormat.getFormat("#,##0.00");

    private float montantTotalLignesGroupees;

    private float montantTotalLignesGroupeesRemise;

    private List<BeneficiaireModel> listeBeneficiaires;

    private Label lMontantTotalLignesGroupees;

    private Label lMontantTotalLignesGroupeesRemise;

    private Label lMontantTotalApresRemise;

    /** Légende des produits sans tarif. */
    private FlexTable legende;

    /**
     * Constructeur.
     * @param constantesApp constantesApp
     */
    public LigneDevisViewImpl(ConstantesModel constantesApp) {
        viewConstants = GWT.create(LigneDevisViewImplConstants.class);
        this.constantesApp = constantesApp;

        conteneur = new VerticalPanel();
        conteneur.setWidth(ComposantTarificateurConstants.POURCENT_100);

        createEnteteLegendeSansTarif();
        creerEnteteLigneDevisPrincipale();
        creerTableauLignesDevis();

        conteneur.add(legende);
        conteneur.add(ftEnteteDevis);
        conteneur.add(tableauLignesDevis);

        this.initWidget(conteneur);
        this.setStylePrimaryName(ComposantTarificateur.RESOURCES.css().ligneDevis());
        this.setWidth(ComposantTarificateurConstants.POURCENT_100);
    }

    /**
     * Cree la fiche de creation de devis.
     */
    private void createEnteteLegendeSansTarif() {
        // Légende des lignes de devis associées à des produits non vendus
        legende = new FlexTable();
        legende.addStyleName(ComposantTarificateur.RESOURCES.css().legendeNonVendu());
        final HTML caseCouleurRelance = new HTML(ComposantTarificateurConstants.ESPACE_HTML);
        caseCouleurRelance.setStyleName(ComposantTarificateur.RESOURCES.css().caseCouleurNonVendu());
        legende.setWidget(0, 0, caseCouleurRelance);
        legende.setWidget(0, 1, new Label(viewConstants.produitNonVendu()));
        legende.setVisible(false);
    }

    /**
     * Crée l'en-tête d'une ligne de devis principale.
     */
    private void creerEnteteLigneDevisPrincipale() {
        ftEnteteDevis = new FlexTable();
        ftEnteteDevis.setWidth(ComposantTarificateurConstants.POURCENT_100);
        ftEnteteDevis.setStylePrimaryName(ComposantTarificateur.RESOURCES.css().enteteLignePrincipale());

        // Case à cocher pour le transfert de la proposition
        cbTransfertLigne = new CheckBox();
        cbTransfertLigne.setTitle(viewConstants.titreCheckboxTransfertSelection());

        lDateCreation = new Label();
        final HorizontalPanel pDateCreation = creerBlocLibelleValeur(viewConstants.labelDateCreation(), lDateCreation);
        lCreateur = new Label();
        final HorizontalPanel pCreateur = creerBlocLibelleValeur(viewConstants.labelCreateur(), lCreateur);
        lProvenance = new Label();
        final HorizontalPanel pProvenance = creerBlocLibelleValeur(viewConstants.labelProvenance(), lProvenance);

        btnVisuGrille = new DecoratedButton(viewConstants.libelleBoutonVisualiser());
        btnVisuGrille.setTitle(viewConstants.titreBoutonVisualiser());
        btnModifierLigne = new DecoratedButton(viewConstants.libelleBoutonModifier());
        btnModifierLigne.setTitle(viewConstants.titreBoutonModifier());
        final FlexTable ftBoutons = new FlexTable();
        ftBoutons.setCellSpacing(4);
        ftBoutons.setWidget(0, 0, btnVisuGrille);
        ftBoutons.setWidget(0, 1, btnModifierLigne);

        ftEnteteDevis.setWidget(0, 0, cbTransfertLigne);
        ftEnteteDevis.setWidget(0, 1, pDateCreation);
        ftEnteteDevis.setWidget(0, 2, pCreateur);
        ftEnteteDevis.setWidget(0, 3, pProvenance);
        ftEnteteDevis.setWidget(0, 4, ftBoutons);
        ftEnteteDevis.getCellFormatter().setHorizontalAlignment(0, 4, HasHorizontalAlignment.ALIGN_RIGHT);

        ftEnteteDevis.getColumnFormatter().setWidth(0, "2%");
        ftEnteteDevis.getColumnFormatter().setWidth(1, "24%");
        ftEnteteDevis.getColumnFormatter().setWidth(2, "32%");
        ftEnteteDevis.getColumnFormatter().setWidth(3, "32%");
        ftEnteteDevis.getColumnFormatter().setWidth(4, "10%");
    }

    /**
     * Cree l'entete du tableau.
     */
    private void creerTableauLignesDevis() {
        tableauLignesDevis = new FlexTable();
        tableauLignesDevis.addStyleName(ComposantTarificateur.RESOURCES.css().tableauLignesDevis());
        tableauLignesDevis.setWidth(ComposantTarificateurConstants.POURCENT_100);

        final Label lProduits = new Label(viewConstants.titreColonneProduits());
        final Label lNomPrenom = new Label(viewConstants.titreColonneNomPrenom());
        final Label lCriteres = new Label(viewConstants.titreColonneCriteres());
        final Label lDateEffet = new Label(viewConstants.titreColonneDateEffet());
        final Label lTarifs = new Label(viewConstants.titreColonneTarifs());
        final Label lAdhesion = new Label(viewConstants.labelColonneAdhesion());
        lAdhesion.setTitle(viewConstants.titleColonneAdhesion());
        final Label lRefus = new Label(viewConstants.labelColonneRefus());
        lRefus.setTitle(viewConstants.titleColonneRefus());
        final Label lCorbeille = new Label(viewConstants.labelColonneCorbeille());
        lCorbeille.setTitle(viewConstants.titleColonneCorbeille());
        final Label lEnCours = new Label(viewConstants.labelColonneEnCours());
        lEnCours.setTitle(viewConstants.titleColonneEnCours());
        final Label lSelectionImpression = new Label(viewConstants.titreColonneSelectionImpression());
        lSelectionImpression.setTitle(viewConstants.titreCheckboxSelection());

        lProduits.setWordWrap(false);
        lNomPrenom.setWordWrap(false);
        lCriteres.setWordWrap(false);
        lDateEffet.setWordWrap(false);
        lTarifs.setWordWrap(false);
        lAdhesion.setWordWrap(false);
        lRefus.setWordWrap(false);
        lCorbeille.setWordWrap(false);
        lEnCours.setWordWrap(false);
        lSelectionImpression.setWordWrap(false);

        tableauLignesDevis.setWidget(0, 0, lProduits);
        tableauLignesDevis.setWidget(0, 1, lNomPrenom);
        tableauLignesDevis.setWidget(0, 2, lCriteres);
        tableauLignesDevis.setWidget(0, 3, lDateEffet);
        tableauLignesDevis.setWidget(0, 4, lTarifs);
        tableauLignesDevis.setWidget(0, 5, lAdhesion);
        tableauLignesDevis.setWidget(0, 6, lRefus);
        tableauLignesDevis.setWidget(0, 7, lCorbeille);
        tableauLignesDevis.setWidget(0, 8, lEnCours);
        tableauLignesDevis.setWidget(0, 9, lSelectionImpression);

        tableauLignesDevis.getColumnFormatter().setWidth(0, "26%");
        tableauLignesDevis.getColumnFormatter().setWidth(1, "27%");
        tableauLignesDevis.getColumnFormatter().setWidth(2, "27%");
        tableauLignesDevis.getColumnFormatter().setWidth(3, "5%");
        tableauLignesDevis.getColumnFormatter().setWidth(4, "10%");
        tableauLignesDevis.getColumnFormatter().setWidth(5, ComposantTarificateurConstants.POURCENT_1);
        tableauLignesDevis.getColumnFormatter().setWidth(6, ComposantTarificateurConstants.POURCENT_1);
        tableauLignesDevis.getColumnFormatter().setWidth(7, ComposantTarificateurConstants.POURCENT_1);
        tableauLignesDevis.getColumnFormatter().setWidth(8, ComposantTarificateurConstants.POURCENT_1);
        tableauLignesDevis.getColumnFormatter().setWidth(9, ComposantTarificateurConstants.POURCENT_1);

        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(0, 4, HasHorizontalAlignment.ALIGN_CENTER);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(0, 5, HasHorizontalAlignment.ALIGN_CENTER);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(0, 6, HasHorizontalAlignment.ALIGN_CENTER);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(0, 7, HasHorizontalAlignment.ALIGN_CENTER);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(0, 8, HasHorizontalAlignment.ALIGN_CENTER);

        for (int i = 0; i < tableauLignesDevis.getCellCount(0); i++) {
            tableauLignesDevis.getCellFormatter().addStyleName(0, i, ComposantTarificateur.RESOURCES.css().tableauLignesDevisEntete());
        }
    }

    private HorizontalPanel creerBlocLibelleValeur(String libelle, Widget wValeur) {
        final Label lLibelle = new Label(libelle);
        lLibelle.setWordWrap(false);
        lLibelle.addStyleName(ComposantTarificateur.RESOURCES.css().important());
        if (wValeur instanceof HasText) {
            wValeur.addStyleName(ComposantTarificateur.RESOURCES.css().important());
        }
        final HorizontalPanel conteneurBloc = new HorizontalPanel();
        conteneurBloc.setSpacing(2);
        conteneurBloc.add(lLibelle);
        conteneurBloc.add(wValeur);
        conteneurBloc.setCellVerticalAlignment(lLibelle, HasVerticalAlignment.ALIGN_MIDDLE);
        conteneurBloc.setCellVerticalAlignment(wValeur, HasVerticalAlignment.ALIGN_MIDDLE);
        return conteneurBloc;
    }

    @Override
    public void chargerLigneDevisPrincipale(LigneDevisModel ligneDevis, boolean isLectureSeule) {
        int numLigne = 1;
        mapRbLigneAdhesion = new HashMap<Long, HasValue<Boolean>>();
        mapRbLigneRefus = new HashMap<Long, HasValue<Boolean>>();
        mapRbLigneCorbeille = new HashMap<Long, HasValue<Boolean>>();
        mapRbLigneEnAttente = new HashMap<Long, HasValue<Boolean>>();
        mapCbImprimerLigne = new HashMap<Long, HasValue<Boolean>>();

        // charge la ligne de devis principale
        afficherInfosLigneDevis(ligneDevis, false, isLectureSeule, numLigne);
        numLigne++;

        // On indique si le devis possède des produits non vendus
        if (ligneDevis.getNAAucunTarif() != null && ligneDevis.getNAAucunTarif().booleanValue()) {
            ErrorPopup.afficher(new ErrorPopupConfiguration(viewConstants.possedeProduitsNonVendu()));
            legende.setVisible(true);
        }
        else {
            legende.setVisible(false);
        }

        // charge les lignes liées
        for (LigneDevisModel ligneDevisLiee : ligneDevis.getListeLignesDevisLiees()) {
            afficherInfosLigneDevis(ligneDevisLiee, true, isLectureSeule, numLigne);
            numLigne++;
        }

        // on affiche le total
        afficherTotalLigneDevis(numLigne);
    }

    /**
     * Affiche le total de la ligne de devis.
     */
    private void afficherTotalLigneDevis(int numLigne) {
        final String libellePaiement = viewConstants.libelleModePaiementMensuel();

        Label libelle =
            new Label(viewConstants.libelleTotal() + ComposantTarificateurConstants.ESPACE + libellePaiement + ComposantTarificateurConstants.ESPACE
                + viewConstants.libelleTTC() + ComposantTarificateurConstants.DEUX_POINTS);
        lMontantTotalLignesGroupees =
            new Label(numberFormat.format((double) montantTotalLignesGroupees) + ComposantTarificateurConstants.ESPACE + viewConstants.symboleMonnaie());
        afficherLigneTotal(libelle, lMontantTotalLignesGroupees, numLigne, ComposantTarificateur.RESOURCES.css().total());

        // on affiche le montant de la remise si il est différent de 0
        if (montantTotalLignesGroupeesRemise != 0f) {
            libelle = new Label(viewConstants.libelleTotalRemise() + ComposantTarificateurConstants.DEUX_POINTS);
            lMontantTotalLignesGroupeesRemise =
                new Label(numberFormat.format((double) montantTotalLignesGroupeesRemise) + ComposantTarificateurConstants.ESPACE
                    + viewConstants.symboleMonnaie());
            afficherLigneTotal(libelle, lMontantTotalLignesGroupeesRemise, numLigne + 1, ComposantTarificateur.RESOURCES.css().total());

            libelle =
                new Label(viewConstants.libelleTotal() + ComposantTarificateurConstants.ESPACE + libellePaiement + ComposantTarificateurConstants.ESPACE
                    + viewConstants.libelleTTCApresRemise() + ComposantTarificateurConstants.DEUX_POINTS);
            lMontantTotalApresRemise =
                new Label(numberFormat.format((double) montantTotalLignesGroupees - montantTotalLignesGroupeesRemise)
                    + ComposantTarificateurConstants.ESPACE + viewConstants.symboleMonnaie());
            afficherLigneTotal(libelle, lMontantTotalApresRemise, numLigne + 2, ComposantTarificateur.RESOURCES.css().totalApresRemise());
        }
    }

    private void afficherLigneTotal(Label libelle, Label montant, int numLigne, String styleName) {
        final String sansBordure = ComposantTarificateur.RESOURCES.css().sansBordure();
        tableauLignesDevis.setWidget(numLigne, 2, libelle);
        tableauLignesDevis.setWidget(numLigne, 3, montant);
        tableauLignesDevis.getFlexCellFormatter().setColSpan(numLigne, 2, 2);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(numLigne, 2, HasAlignment.ALIGN_RIGHT);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(numLigne, 3, HasAlignment.ALIGN_RIGHT);
        tableauLignesDevis.getCellFormatter().addStyleName(numLigne, 0, sansBordure);
        tableauLignesDevis.getCellFormatter().addStyleName(numLigne, 1, sansBordure);
        tableauLignesDevis.getCellFormatter().addStyleName(numLigne, 2, styleName);
        tableauLignesDevis.getCellFormatter().addStyleName(numLigne, 3, styleName);
        tableauLignesDevis.getCellFormatter().addStyleName(numLigne, 4, sansBordure);
        tableauLignesDevis.getCellFormatter().addStyleName(numLigne, 5, sansBordure);
        tableauLignesDevis.getCellFormatter().addStyleName(numLigne, 6, sansBordure);
        tableauLignesDevis.getCellFormatter().addStyleName(numLigne, 7, sansBordure);
        tableauLignesDevis.getCellFormatter().addStyleName(numLigne, 8, sansBordure);
    }

    /**
     * Affiche les informations d'une ligne de devis.
     */
    private void afficherInfosLigneDevis(LigneDevisModel ligneDevis, final boolean ligneLiee, boolean isLectureSeule, int numLigne) {
    	// on récupère la finalité
        final Long idFinalite =
            ligneDevis.getFinalite().getIdentifiant() != null ? ligneDevis.getFinalite().getIdentifiant() : constantesApp.getIdFinaliteEnCours();

        // on récupère le critère optionnel ou pas du produit
        boolean isProduitOptionnel = true;
        if (ligneLiee && ligneDevis.getProduitOptionnel() != null) {
            isProduitOptionnel = ligneDevis.getProduitOptionnel().booleanValue();
        }

        // calcul du montant mensuel
        final float montantLigneMensuel = calculMontantMensuelLigne(ligneDevis.getMontantTtc().floatValue(), ligneDevis.getIdentifiantModePaiement());
        float montantLigneMensuelRemise = 0f;
        if (ligneDevis.getMontantRemise() != null) {
            montantLigneMensuelRemise = calculMontantMensuelLigne(ligneDevis.getMontantRemise().floatValue(), ligneDevis.getIdentifiantModePaiement());
        }

        // ajout du montant de la ligne au total sauf si ligne liée obligatoire
        if ((!ligneLiee || isProduitOptionnel) && montantLigneMensuelRemise != montantLigneMensuel) {
            montantTotalLignesGroupees += montantLigneMensuel;
            montantTotalLignesGroupeesRemise += montantLigneMensuelRemise;
        }

        final RadioButton rbLigneAdhesion =
            new RadioButton(ligneDevis.getIdentifiant() + "-" + numLigne);
        final RadioButton rbLigneRefus = new RadioButton(ligneDevis.getIdentifiant() + "-" + numLigne);
        final RadioButton rbLigneEnCours =
            new RadioButton(ligneDevis.getIdentifiant() + "-" + numLigne);
        final RadioButton rbLigneCorbeille =
            new RadioButton(ligneDevis.getIdentifiant() + "-" + numLigne);
        rbLigneAdhesion.setTitle(viewConstants.titreRadioAccepte());
        rbLigneRefus.setTitle(viewConstants.titreRadioRefuse());
        rbLigneEnCours.setTitle(viewConstants.titreRadioEnCours());
        rbLigneCorbeille.setTitle(viewConstants.titreRadioCorbeille());
        rbLigneAdhesion.setEnabled(!isLectureSeule);
        rbLigneRefus.setEnabled(!isLectureSeule);
        rbLigneEnCours.setEnabled(!isLectureSeule);
        rbLigneCorbeille.setEnabled(!isLectureSeule);

        // on coche la finalite suivant le cas
        if (idFinalite.equals(constantesApp.getIdFinaliteAcceptee())) {
            rbLigneAdhesion.setValue(Boolean.TRUE);
        }
        else if (idFinalite.equals(constantesApp.getIdFinaliteRefusee())) {
            rbLigneRefus.setValue(Boolean.TRUE);
        }
        else if (idFinalite.equals(constantesApp.getIdFinaliteEnCours())) {
            rbLigneEnCours.setValue(Boolean.TRUE);
        }
        else if (idFinalite.equals(constantesApp.getIdFinaliteCorbeille())) {
            rbLigneCorbeille.setValue(Boolean.TRUE);
        }

        final CheckBox cbImprimerLigne = new CheckBox();
        cbImprimerLigne.setTitle(viewConstants.titreCheckboxSelection());

        mapRbLigneAdhesion.put(ligneDevis.getIdentifiant(), rbLigneAdhesion);
        mapRbLigneRefus.put(ligneDevis.getIdentifiant(), rbLigneRefus);
        mapRbLigneEnAttente.put(ligneDevis.getIdentifiant(), rbLigneEnCours);
        mapRbLigneCorbeille.put(ligneDevis.getIdentifiant(), rbLigneCorbeille);
        mapCbImprimerLigne.put(ligneDevis.getIdentifiant(), cbImprimerLigne);

        if (ligneLiee && !isProduitOptionnel) {
            // produit obligatoire non coché et non décochable (non affiché de toute façon)
            cbImprimerLigne.setValue(Boolean.FALSE);
            cbImprimerLigne.setEnabled(Boolean.FALSE);
        }
        else if (ligneLiee && isProduitOptionnel) {
            // On sélectionne pour impression les produits bonus
            if (ligneDevis.getIdentifiantFamille().equals(constantesApp.getIdentifiantFamilleBonus1())
                || ligneDevis.getIdentifiantFamille().equals(constantesApp.getIdentifiantFamilleBonus2())) {
                cbImprimerLigne.setValue(Boolean.TRUE);
            }
            else {
                // produit optionnel décochable
                cbImprimerLigne.setValue(ligneDevis.getSelectionnePourImpression() != null ? ligneDevis.getSelectionnePourImpression() : Boolean.FALSE);
            }
            cbImprimerLigne.setEnabled(Boolean.TRUE);
        }
        else if (!ligneLiee) {
            // produit principal décochable
            cbImprimerLigne.setValue(ligneDevis.getSelectionnePourImpression() != null && ligneDevis.getSelectionnePourImpression().booleanValue());
            cbImprimerLigne.setEnabled(Boolean.TRUE);
        }

        String libelleMontant = "";
        if (montantLigneMensuelRemise != montantLigneMensuel) {
            libelleMontant = numberFormat.format((double) montantLigneMensuel) + ComposantTarificateurConstants.ESPACE + viewConstants.symboleMonnaie();
        } else if (montantLigneMensuel != 0f && (ligneDevis.getNAAucunTarif() == null || !ligneDevis.getNAAucunTarif())) {
            libelleMontant = viewConstants.libelleOffert();
        }

        final Label lProduit = new Label();
        final Label lPrenomNomBeneficiaire = new Label();
        final HTML lCriteresProduit = new HTML();
        final Label lDateEffet = new Label();
        final Label lMontant = new Label();
        lDateEffet.setWordWrap(false);
        lMontant.setWordWrap(false);

        final String libelleProduit = ligneDevis.getLibelleProduit();

        lProduit.setText(libelleProduit);

      //limite le nombre de caractères
        final String nomPrenom = ligneDevis.getPrenomNomBeneficiaire();
        String nomPrenomLimite = nomPrenom;
        if (nomPrenom.length() > viewConstants.nbMaxCaracteresNomPrenom()) { //limite si superieur a la constante
        	nomPrenomLimite = nomPrenomLimite.substring(0, viewConstants.nbMaxCaracteresNomPrenom()) + "...";
        }
        lPrenomNomBeneficiaire.setText(nomPrenomLimite);
        lPrenomNomBeneficiaire.setTitle(nomPrenom);

        lCriteresProduit.setHTML(getLibelleCriteresProduit(ligneDevis));
        lDateEffet.setText(ligneDevis.getDateEffet());
        lMontant.setText(libelleMontant);

        tableauLignesDevis.setWidget(numLigne, 0, lProduit);
        tableauLignesDevis.setWidget(numLigne, 1, lPrenomNomBeneficiaire);
        tableauLignesDevis.setWidget(numLigne, 2, lCriteresProduit);
        tableauLignesDevis.setWidget(numLigne, 3, lDateEffet);
        tableauLignesDevis.setWidget(numLigne, 4, lMontant);
        tableauLignesDevis.setWidget(numLigne, 5, rbLigneAdhesion);
        tableauLignesDevis.setWidget(numLigne, 6, rbLigneRefus);
        tableauLignesDevis.setWidget(numLigne, 7, rbLigneCorbeille);
        tableauLignesDevis.setWidget(numLigne, 8, rbLigneEnCours);
        tableauLignesDevis.setWidget(numLigne, 9, cbImprimerLigne);

        // mise en forme du tableau
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(numLigne, 0, HasAlignment.ALIGN_LEFT);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(numLigne, 1, HasAlignment.ALIGN_LEFT);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(numLigne, 2, HasAlignment.ALIGN_LEFT);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(numLigne, 3, HasAlignment.ALIGN_LEFT);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(numLigne, 4, HasAlignment.ALIGN_RIGHT);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(numLigne, 5, HasAlignment.ALIGN_CENTER);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(numLigne, 6, HasAlignment.ALIGN_CENTER);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(numLigne, 7, HasAlignment.ALIGN_CENTER);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(numLigne, 8, HasAlignment.ALIGN_CENTER);
        tableauLignesDevis.getCellFormatter().setHorizontalAlignment(numLigne, 9, HasAlignment.ALIGN_CENTER);
        tableauLignesDevis.getRowFormatter().setVerticalAlign(numLigne, HasAlignment.ALIGN_TOP);
        tableauLignesDevis.getCellFormatter().addStyleName(numLigne, 0, ComposantTarificateur.RESOURCES.css().important());
        tableauLignesDevis.getCellFormatter().addStyleName(numLigne, 1, ComposantTarificateur.RESOURCES.css().important());
        tableauLignesDevis.getCellFormatter().addStyleName(numLigne, 3, ComposantTarificateur.RESOURCES.css().important());
        tableauLignesDevis.getCellFormatter().addStyleName(numLigne, 4, ComposantTarificateur.RESOURCES.css().important());

        // Mise en forme spéciale pour les lignes associées à des produits sans tarifs
        if (ligneDevis.getNAAucunTarif() != null && ligneDevis.getNAAucunTarif().booleanValue()) {
            tableauLignesDevis.getRowFormatter().addStyleName(numLigne, ComposantTarificateur.RESOURCES.css().produitSansTarif());
        }

        // si c'est une ligne liee dont le produit est obligatoire, ou si il s'agit d'un produit bonus
        if ((ligneLiee && !isProduitOptionnel) || ligneDevis.getIdentifiantFamille().equals(constantesApp.getIdentifiantFamilleBonus1())
            || ligneDevis.getIdentifiantFamille().equals(constantesApp.getIdentifiantFamilleBonus2())) {
            rbLigneAdhesion.setEnabled(false);
            rbLigneRefus.setEnabled(false);
            rbLigneEnCours.setEnabled(false);
            rbLigneCorbeille.setEnabled(false);
            // on cache la ligne
            tableauLignesDevis.getRowFormatter().setVisible(numLigne, false);
        }
    }

    /**
     * Calcul le montant mensuel de la ligne.
     */
    private float calculMontantMensuelLigne(float montantLigne, Integer modePaiement) {
        float montantLigneDevisMensuel = 0f;
        // on recupere le montant mensuel ou l'annuel/12
        if (modePaiement.equals(constantesApp.getIdModePaiementMensuel())) {
            montantLigneDevisMensuel = montantLigne;
        }
        else if (modePaiement.equals(constantesApp.getIdModePaiementUnique())) {
            montantLigneDevisMensuel = montantLigne / 12f;
        }
        return montantLigneDevisMensuel;
    }

    /**
     * Cree le libelle des criteres du produit.
     */
    private String getLibelleCriteresProduit(LigneDevisModel ligne) {
        // le libelle des criteres du produit
        final StringBuffer libelleCriteres = new StringBuffer();

        // INFORMATION BENEFICIAIRE
        if (ligne.getIdentifiantBeneficiaire() != null && listeBeneficiaires != null) {
            for (BeneficiaireModel beneficiaire : listeBeneficiaires) {
                if (beneficiaire.getId().equals(ligne.getIdentifiantBeneficiaire())) {
                    libelleCriteres.append(viewConstants.libelleBeneficiaire() + ComposantTarificateurConstants.DEUX_POINTS + beneficiaire.getNom()
                        + ComposantTarificateurConstants.ESPACE + beneficiaire.getPrenom() + ComposantTarificateurConstants.SAUT_LIGNE);
                }
            }
        }
        if (ligne.getListeValeurCritereLigneDevis() != null) {
            // RECHERCHE DU CRITERE AGE_MIN ET MONTANT_MIN
            boolean critereAgeMinPresent = false;
            boolean critereMontantMinPresent = false;
            for (ValeurCritereLigneDevisModel valeurCritere : ligne.getListeValeurCritereLigneDevis()) {

                if (valeurCritere.getIdentifiantCritere().equals(constantesApp.getIdCritereAgeMin())) {
                    critereAgeMinPresent = true;
                }
                else if (valeurCritere.getIdentifiantCritere().equals(constantesApp.getIdCritereMontantMin())) {
                    critereMontantMinPresent = true;
                }
            }

            // Parcours pour l'affichage des valeurs de critères
            for (ValeurCritereLigneDevisModel valeurCritere : ligne.getListeValeurCritereLigneDevis()) {
                // NE PAS AFFICHER LE CRITERE AGE_MAX SI LE CRITERE AGE_MIN EST PRESENT
                // ET NE PAS AFFICHER LE CRITERE MONTANT_MAX SI LE CRITERE MONTANT_MIN EST PRESENT
                if (!(valeurCritere.getIdentifiantCritere().equals(constantesApp.getIdCritereAgeMax()) && critereAgeMinPresent)
                    && !(valeurCritere.getIdentifiantCritere().equals(constantesApp.getIdCritereMontantMax()) && critereMontantMinPresent)) {
                    libelleCriteres.append(valeurCritere.getLibelleCritere() + ComposantTarificateurConstants.DEUX_POINTS
                        + valeurCritere.getAffichageValeur() + ComposantTarificateurConstants.SAUT_LIGNE);
                }
            }
        }
        return libelleCriteres.toString();
    }

    @Override
    public Map<Long, HasValue<Boolean>> getMapRbLigneAdhesion() {
        return mapRbLigneAdhesion;
    }

    @Override
    public Map<Long, HasValue<Boolean>> getMapRbLigneCorbeille() {
        return mapRbLigneCorbeille;
    }

    @Override
    public Map<Long, HasValue<Boolean>> getMapRbLigneEnCours() {
        return mapRbLigneEnAttente;
    }

    @Override
    public Map<Long, HasValue<Boolean>> getMapRbLigneRefus() {
        return mapRbLigneRefus;
    }

    @Override
    public Map<Long, HasValue<Boolean>> getMapCbImprimerLigne() {
        return mapCbImprimerLigne;
    }

    @Override
    public void afficherPopupErreurSelectionImpression() {
        ErrorPopup.afficher(new ErrorPopupConfiguration(viewConstants.erreurSelectionLignesMax()));
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration) {
        LoadingPopup.stopAll();
        ErrorPopup.afficher(errorPopupConfiguration);
    }

    @Override
    public HasClickHandlers getBtnModifierLigne() {
        return btnModifierLigne;
    }

    @Override
    public HasClickHandlers getBtnVisuGrille() {
        return btnVisuGrille;
    }

    @Override
    public HasValue<Boolean> getCbTransfertLigne() {
        return cbTransfertLigne;
    }

    @Override
    public void setStylePrimaryNameFinaliteLigneDevis(String styleName) {
        for (int i = 0; i < ftEnteteDevis.getCellCount(0); i++) {
            ftEnteteDevis.getCellFormatter().setStylePrimaryName(0, i, styleName);
        }
    }

    @Override
    public void activerBoutonModifierLigne(boolean actif) {
        btnModifierLigne.setEnabled(actif);
    }

    @Override
    public HasText getLCreateur() {
        return lCreateur;
    }

    @Override
    public HasText getLDateCreation() {
        return lDateCreation;
    }

    @Override
    public HasText getLProvenance() {
        return lProvenance;
    }

    @Override
    public void stopperPopupChargement() {
        LoadingPopup.stop();
    }

    @Override
    public void voirGrillePrestations(String url) {
        Window.open(url, "_blank", "resizable=yes,menubar=no,location=no");
    }

    @Override
    public List<Long> getLignesDevisSelectionnees() {
        final List<Long> listeIdsLignesSelectionnes = new ArrayList<Long>();
        for (Long idLigneDevis : mapCbImprimerLigne.keySet()) {
            final CheckBox cb = (CheckBox) mapCbImprimerLigne.get(idLigneDevis);
            if (cb.getValue()) {
                listeIdsLignesSelectionnes.add(idLigneDevis);
            }
        }
        return listeIdsLignesSelectionnes;
    }

    @Override
    public void activerCbTransfertLigne(boolean actif) {
        cbTransfertLigne.setEnabled(actif);
    }
}

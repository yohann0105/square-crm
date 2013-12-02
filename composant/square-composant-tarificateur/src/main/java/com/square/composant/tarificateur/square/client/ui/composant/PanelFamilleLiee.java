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
package com.square.composant.tarificateur.square.client.ui.composant;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.exception.BusinessExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.exception.TechnicalExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSingle.SuggestListBoxSingleProperties;
import org.scub.foundation.framework.gwt.module.client.util.evenement.Evenement;
import org.scub.foundation.framework.gwt.module.client.util.evenement.ObservableGenerique;
import org.scub.foundation.framework.gwt.module.client.util.evenement.Observateur;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.composant.CalendarAssureSelecteur;
import com.square.composant.tarificateur.square.client.composant.CalendarSelecteur;
import com.square.composant.tarificateur.square.client.composant.CheckBoxAssureSelecteur;
import com.square.composant.tarificateur.square.client.composant.CheckBoxSelecteur;
import com.square.composant.tarificateur.square.client.composant.ListBoxItem;
import com.square.composant.tarificateur.square.client.composant.ListBoxSelecteur;
import com.square.composant.tarificateur.square.client.composant.TextBoxSelecteur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.ConstantesModel;
import com.square.composant.tarificateur.square.client.model.age.AgeModel;
import com.square.composant.tarificateur.square.client.model.age.DateCalculAgeModel;
import com.square.composant.tarificateur.square.client.model.selecteur.AssureSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.ContrainteVenteSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.CritereSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.FamilleLieeSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.ProduitSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.RapportErreurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.ValeurCritereAssureSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.ValeurCritereSelecteurModel;
import com.square.composant.tarificateur.square.client.service.SelecteurProduitServiceGwtAsync;
import com.square.composant.tarificateur.square.client.ui.panel.produits.PanelProduits;
import com.square.composant.tarificateur.square.client.ui.panel.produits.PanelProduits.PageConstants;
import com.square.composants.graphiques.lib.client.composants.DecoratedSuggestListBoxSingle;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Panneau pour la famille liée.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class PanelFamilleLiee extends VerticalPanel implements Observateur {

    private static final String SEPARATEUR_DATE = "/";

    /** Page constants. */
    private PanelProduits.PageConstants pageConstants;

    private FamilleLieeSelecteurModel currentFamilleLiee;

    private ProduitSelecteurModel currentProduitLie;

    private AssureSelecteurModel currentAssurePrincipal;

    /** Contrainte de vente du produit. */
    private ContrainteVenteSelecteurModel currentContrainteVente;

    /** Label d'infos sur la personne. */
    private HTML labelInfosPersonne;

    /** Label du libelle de l'age de la personne. */
    private HTML labelLibelleAgePersonne;

    /** Label de l'age de la personne. */
    private HTML labelAgePersonne;

    /** Label des erreurs. */
    private HTML lbErreurs;

    /** TextBox pour la date d'effet. */
    private CalendarAssureSelecteur tbDateEffet;

    private Integer idCritereAgeMin;

    private Integer idCritereGeneration;

    private Integer idCritereMois;

    private Integer idFamilleBonus1;

    private Integer idFamilleBonus2;

    private Integer nbAnneesBonus1;

    private Integer nbAnneesBonus2;

    private String constanteTypeHTMLSelect;

    private String constanteTypeCalendar;

    private FlexTable panelCriteres;

    private VerticalPanel panelBeneficiaires;

    private boolean hasSelectedProduit;

    private CheckBoxSelecteur[] listeCheckboxProduit;

    private List<CalendarAssureSelecteur> listeCalendarDateEffet;

    private VerticalPanel conteneurFamille;

    /** Service SelecteurProduitService. */
    private SelecteurProduitServiceGwtAsync selecteurProduitService;

    /** Panel Produit parent. */
    private PanelProduits parent;

    private IconeErreurChampManager iconeErreurChampManager;

    private ConstantesModel constantes;

    private ComposantTarificateurConstants constantComposantTarificateur = GWT.create(ComposantTarificateurConstants.class);

    /**
     * Constructeur avec paramètre.
     * @param familleLiee la famille principale
     * @param constantes constantes de mapping nécessaires au composant
     * @param pageConstants constantes de la page
     * @param selecteurProduitService selecteurProduitService
     * @param parent le panel Produits parent
     * @param iconeErreurChampManager manager des icones d'erreur
     */
    public PanelFamilleLiee(FamilleLieeSelecteurModel familleLiee, ConstantesModel constantes, PanelProduits.PageConstants pageConstants,
        SelecteurProduitServiceGwtAsync selecteurProduitService, PanelProduits parent, IconeErreurChampManager iconeErreurChampManager) {
        this.pageConstants = pageConstants;
        this.parent = parent;
        this.iconeErreurChampManager = iconeErreurChampManager;
        this.constantes = constantes;

        // Initialisation des services
        this.selecteurProduitService = selecteurProduitService;

        currentFamilleLiee = familleLiee;
        // l'assure principal est le meme sur tous les produits, on prend donc le 1er
        currentAssurePrincipal = ((ProduitSelecteurModel) currentFamilleLiee.getListeProduitsLies().get(0)).getAssurePrincipal();
        listeCalendarDateEffet = new ArrayList<CalendarAssureSelecteur>();

        // Initialisation des variables
        idCritereAgeMin = constantes.getIdCritereAgeMin();
        idCritereGeneration = constantes.getIdCritereGeneration();
        idCritereMois = constantes.getIdCritereMois();
        constanteTypeHTMLSelect = constantes.getConstanteTypeHTMLSelect();
        constanteTypeCalendar = constantes.getConstanteTypeCalendar();
        idFamilleBonus1 = constantes.getIdentifiantFamilleBonus1();
        idFamilleBonus2 = constantes.getIdentifiantFamilleBonus2();
        nbAnneesBonus1 = constantes.getNbAnneesBonus1();
        nbAnneesBonus2 = constantes.getNbAnneesBonus2();

        this.addStyleName(ComposantTarificateur.RESOURCES.css().panelFamilleLiee());
        initComposants();
    }

    /** Initialisation des composants. */
    private void initComposants() {
        setWidth(ComposantTarificateurConstants.POURCENT_100);

        // Libellé de la famille
        final FlexTable panelTitreFamille = new FlexTable();
        panelTitreFamille.setCellSpacing(PageConstants.SPACING_TITRE_FAMILLE);
        panelTitreFamille.addStyleName(ComposantTarificateur.RESOURCES.css().panelTitreFamille());
        panelTitreFamille.setWidth(ComposantTarificateurConstants.POURCENT_100);

        final HorizontalPanel conteneurGamme = new HorizontalPanel();
        conteneurGamme.setSpacing(PageConstants.SPACING_TITRE_FAMILLE);
        final HTML labelGamme = new HTML(pageConstants.labelGammeProduit() + ComposantTarificateurConstants.DEUX_POINTS);
        final HTML libelleGamme = new HTML(currentFamilleLiee.getLibelleGamme());
        libelleGamme.addStyleName(ComposantTarificateur.RESOURCES.css().libelleGras());
        conteneurGamme.add(labelGamme);
        conteneurGamme.add(libelleGamme);
        panelTitreFamille.setWidget(0, 0, conteneurGamme);
        final HorizontalPanel conteneurTitreFamille = new HorizontalPanel();
        conteneurTitreFamille.setSpacing(PageConstants.SPACING_TITRE_FAMILLE);
        final HTML labelFamille = new HTML(pageConstants.labelFamilleProduit() + ComposantTarificateurConstants.DEUX_POINTS);
        final HTML libelleFamille = new HTML(currentFamilleLiee.getLibelle());
        libelleFamille.addStyleName(ComposantTarificateur.RESOURCES.css().libelleGras());
        conteneurTitreFamille.add(labelFamille);
        conteneurTitreFamille.add(libelleFamille);
        panelTitreFamille.setWidget(0, 1, conteneurTitreFamille);
        final CheckBoxSelecteur ckFamille = new CheckBoxSelecteur(currentFamilleLiee);
        ckFamille.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                conteneurFamille.setVisible(((CheckBox) event.getSource()).getValue());
            }
        });
        ckFamille.setEnabled(currentFamilleLiee.getOptionnel() != null ? currentFamilleLiee.getOptionnel().booleanValue() : true);
        panelTitreFamille.setWidget(0, 2, ckFamille);
        panelTitreFamille.getCellFormatter().setHorizontalAlignment(0, 2, HasAlignment.ALIGN_RIGHT);
        add(panelTitreFamille);

        conteneurFamille = new VerticalPanel();
        conteneurFamille.setWidth(ComposantTarificateurConstants.POURCENT_100);
        conteneurFamille.addStyleName(ComposantTarificateur.RESOURCES.css().conteneurFamille());
        conteneurFamille.setVisible(currentFamilleLiee.getIsSelection() != null ? currentFamilleLiee.getIsSelection().booleanValue() : false);
        add(conteneurFamille);

        panelTitreFamille.getColumnFormatter().setWidth(0, ComposantTarificateurConstants.POURCENT_50);
        panelTitreFamille.getColumnFormatter().setWidth(1, ComposantTarificateurConstants.POURCENT_50);

        // Panneau Infos de la personne
        final VerticalPanel panelConteneurInfosPersonne = new VerticalPanel();
        panelConteneurInfosPersonne.setSpacing(PageConstants.SPACING_INFOS_PERSONNE);
        panelConteneurInfosPersonne.setWidth(ComposantTarificateurConstants.POURCENT_100);
        panelConteneurInfosPersonne.addStyleName(ComposantTarificateur.RESOURCES.css().panelInfosPersonne());
        final HorizontalPanel panelInfosPersonne = new HorizontalPanel();
        final StringBuffer libelleInfosPersonne =
            new StringBuffer(currentAssurePrincipal.getNom().toUpperCase()).append(PageConstants.SEPARATEUR).append(currentAssurePrincipal.getPrenom()).append(
                PageConstants.SEPARATEUR).append(currentAssurePrincipal.getDateNaissance());
        labelInfosPersonne = new HTML(libelleInfosPersonne.toString());
        labelLibelleAgePersonne = new HTML();
        labelAgePersonne = new HTML();
        panelInfosPersonne.add(labelInfosPersonne);
        panelInfosPersonne.add(labelLibelleAgePersonne);
        panelInfosPersonne.add(labelAgePersonne);
        panelConteneurInfosPersonne.add(panelInfosPersonne);
        // Erreur de contrainte de vente
        lbErreurs = new HTML();
        lbErreurs.addStyleName(ComposantTarificateur.RESOURCES.css().panelErreursContraintes());
        lbErreurs.setVisible(false);
        conteneurFamille.add(panelConteneurInfosPersonne);
        conteneurFamille.add(lbErreurs);

        // on charge la liste des produits
        chargerListeProduits();

        // on cree le panel des criteres
        panelCriteres = new FlexTable();
        panelCriteres.setCellSpacing(PageConstants.SPACING_CRITERES);
        panelCriteres.setWidth(ComposantTarificateurConstants.POURCENT_100);
        panelCriteres.addStyleName(ComposantTarificateur.RESOURCES.css().panelCriteres());
        conteneurFamille.add(panelCriteres);

        // on cree le panel des beneficiaires
        panelBeneficiaires = new VerticalPanel();
        panelBeneficiaires.setWidth(ComposantTarificateurConstants.POURCENT_100);
        conteneurFamille.add(panelBeneficiaires);

        // si aucun produit selectionné
        if (!hasSelectedProduit) {
            // on charge le 1er produit
            listeCheckboxProduit[0].setValue(Boolean.TRUE);
            chargerProduit((ProduitSelecteurModel) currentFamilleLiee.getListeProduitsLies().get(0));
        } else if (hasSelectedProduit && currentProduitLie != null) {
            chargerProduit(currentProduitLie);
        }
    }

    /** Met à jour le libelle de l'age. */
    private void setLibelleAgePersonne(HTML labelLibelle) {
        final StringBuffer libelleAgePersonne = new StringBuffer(PageConstants.SEPARATEUR);
        // Affichage Age ou Age Millésimé selon la contrainte de vente du produit
        if (currentContrainteVente != null && currentContrainteVente.getAgeMillesime() != null && currentContrainteVente.getAgeMillesime().booleanValue()) {
            libelleAgePersonne.append(pageConstants.labelAgeMillesime() + ComposantTarificateurConstants.DEUX_POINTS);
        } else {
            libelleAgePersonne.append(pageConstants.labelAge() + ComposantTarificateurConstants.DEUX_POINTS);
        }
        labelLibelle.setHTML(libelleAgePersonne.toString());
    }

    /**
     * Charge la liste des produits.
     */
    private void chargerListeProduits() {
        if (currentFamilleLiee.getListeProduitsLies() != null && currentFamilleLiee.getListeProduitsLies().size() > 0) {
            // Ajout des critères
            final FlexTable panelListeProduits = new FlexTable();
            panelListeProduits.setCellSpacing(PageConstants.SPACING_CRITERES);
            // on n'affiche que pour les produits non bonus
            panelListeProduits.setVisible(!currentFamilleLiee.getIdentifiant().equals(idFamilleBonus1)
                && !currentFamilleLiee.getIdentifiant().equals(idFamilleBonus2));
            panelListeProduits.setWidth(ComposantTarificateurConstants.POURCENT_100);
            panelListeProduits.addStyleName(ComposantTarificateur.RESOURCES.css().panelListeProduits());

            int indexProduit = 0;
            int indexLigne = 0;

            final HTML titreProduit = new HTML(pageConstants.titreProduits() + ComposantTarificateurConstants.DEUX_POINTS);
            panelListeProduits.addStyleName(ComposantTarificateur.RESOURCES.css().libelleGras());
            panelListeProduits.setWidget(indexLigne, 0, titreProduit);

            // on cree une liste des checkbox pour les abonner les unes aux autres
            listeCheckboxProduit = new CheckBoxSelecteur[currentFamilleLiee.getListeProduitsLies().size()];
            hasSelectedProduit = false;

            for (int i = 0; i < currentFamilleLiee.getListeProduitsLies().size(); i++) {
                final ProduitSelecteurModel produit = (ProduitSelecteurModel) currentFamilleLiee.getListeProduitsLies().get(i);
                final CheckBoxSelecteur checkBox = new CheckBoxSelecteur(produit);
                checkBox.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        if (((CheckBox) event.getSource()).getValue()) {
                            final Evenement evenementClic =
                                new Evenement(ComposantTarificateurConstants.EVENT_DEMANDE_DECOCHER_AUTRES_PRODUITS, constantComposantTarificateur
                                        .demandeDecochageAutresProduits(), null, checkBox);
                            checkBox.getObsGenerique().informerObservateur(evenementClic);
                            chargerProduit(produit);
                        } else {
                            ((CheckBox) event.getSource()).setValue(Boolean.TRUE);
                        }
                    }
                });
                if (produit.getIsSelection() != null && produit.getIsSelection().booleanValue()) {
                    hasSelectedProduit = true;
                    currentProduitLie = produit;
                }
                checkBox.setEnabled(currentFamilleLiee.getOptionnel() != null ? currentFamilleLiee.getOptionnel().booleanValue() : true);
                listeCheckboxProduit[i] = checkBox;

                final StringBuffer libelleTitreProduit = new StringBuffer(produit.getLibelle());
                if (produit.getIsActif() != null && !produit.getIsActif().booleanValue()) {
                    libelleTitreProduit.append(" (inactif)");
                }
                final HTML libelleProduit = new HTML(libelleTitreProduit.toString());

                final HorizontalPanel conteneurProduit = new HorizontalPanel();
                conteneurProduit.add(checkBox);
                conteneurProduit.add(libelleProduit);
                conteneurProduit.setCellVerticalAlignment(checkBox, HasAlignment.ALIGN_MIDDLE);
                conteneurProduit.setCellVerticalAlignment(libelleProduit, HasAlignment.ALIGN_MIDDLE);
                if (produit.getIsActif() != null && !produit.getIsActif().booleanValue()) {
                    conteneurProduit.addStyleName(ComposantTarificateur.RESOURCES.css().produitInactif());
                }

                // On ajoute les produits alternativement à gauche et à droite
                if (indexProduit % 2 == 0) {
                    // Colonnes à gauche
                    panelListeProduits.setWidget(indexLigne, 1, conteneurProduit);
                } else {
                    // Colonnes à droite
                    panelListeProduits.setWidget(indexLigne, 2, conteneurProduit);
                    indexLigne++;
                }
                indexProduit++;
            }

            if (currentFamilleLiee.getListeProduitsLies().size() == 1) {
                // creation de colonne fictives pour l'affichage
                panelListeProduits.setWidget(0, 2, new Label());
            }
            panelListeProduits.getColumnFormatter().setWidth(0, "10%");
            panelListeProduits.getColumnFormatter().setWidth(1, "45%");
            panelListeProduits.getColumnFormatter().setWidth(2, "45%");

            // on parcours les checkbox pour les abonner
            for (int i = 0; i < listeCheckboxProduit.length; i++) {
                for (int j = 0; j < listeCheckboxProduit.length; j++) {
                    // on n'abonne pas une checkbox à elle meme
                    if (i != j) {
                        listeCheckboxProduit[j].getObsGenerique().ajouterObservateur(listeCheckboxProduit[i]);
                    }
                }
            }

            conteneurFamille.add(panelListeProduits);
        }
    }

    /**
     * Charge un produit.
     */
    private void chargerProduit(ProduitSelecteurModel produit) {
        // on redefinit les valeurs courantes
        currentProduitLie = produit;
        currentAssurePrincipal = currentProduitLie.getAssurePrincipal();
        currentContrainteVente = currentProduitLie.getContraintesVente();

        if (currentProduitLie.getListeBeneficiaires() != null && currentProduitLie.getListeBeneficiaires().size() > 0) {
            // affiche l'assure principal et les beneficiaires
            panelCriteres.setVisible(false);
            panelBeneficiaires.clear();
            panelBeneficiaires.setVisible(true);

            // on verifie d'abord si un assure est selectionné
            boolean hasAssureSelected = currentAssurePrincipal.getIsSelection() != null ? currentAssurePrincipal.getIsSelection().booleanValue() : false;
            // on ne verifie les benefs que si assure principal pas selectionné
            if (!hasAssureSelected) {
                for (AssureSelecteurModel beneficiaire : currentProduitLie.getListeBeneficiaires()) {
                    if (beneficiaire.getIsSelection() != null && beneficiaire.getIsSelection().booleanValue()) {
                        hasAssureSelected = true;
                    }
                }
            }
            // sinon on selectionne l'assure principal par défaut
            if (!hasAssureSelected) {
                currentAssurePrincipal.setIsSelection(Boolean.TRUE);
            }

            afficherLigneAssure(currentAssurePrincipal, true);
            chargerBeneficiaires();
        } else {
            // affiche le conteneur des infos de l'assure principal
            panelBeneficiaires.setVisible(false);
            panelCriteres.clear();
            panelCriteres.setVisible(true);
            chargerCriteres();
            setLibelleAgePersonne(labelLibelleAgePersonne);
        }
    }

    /**
     * Charge les criteres.
     */
    private void chargerCriteres() {
        // on selectionne l'assure principal
        currentAssurePrincipal.setIsSelection(Boolean.TRUE);

        // Ajout de la textBox pour la date d'effet
        tbDateEffet = new CalendarAssureSelecteur(currentAssurePrincipal, labelAgePersonne, currentProduitLie, true, idCritereAgeMin);
        tbDateEffet.getObsGenerique().ajouterObservateur(this);
        listeCalendarDateEffet.add(tbDateEffet);
        panelCriteres.setWidget(0, 0, new Label(pageConstants.labelDateEffet() + ComposantTarificateurConstants.DEUX_POINTS));
        panelCriteres.setWidget(0, 1, tbDateEffet);
        panelCriteres.setWidget(0, 2, new Label());
        panelCriteres.setWidget(0, 3, new Label());
        panelCriteres.getColumnFormatter().setWidth(0, PageConstants.LARGEUR_LIBELLE_CRITERE);
        panelCriteres.getColumnFormatter().setWidth(1, PageConstants.LARGEUR_CHAMP_CRITERE);
        panelCriteres.getColumnFormatter().setWidth(2, PageConstants.LARGEUR_LIBELLE_CRITERE);
        panelCriteres.getColumnFormatter().setWidth(3, PageConstants.LARGEUR_CHAMP_CRITERE);

        if (currentProduitLie.getListeCriteres() != null && currentProduitLie.getListeCriteres().size() > 0) {
            int indexLigne = 1;
            int indexWidget = 0;

            // Parcours des critères pour initialisation des widgets
            for (CritereSelecteurModel critereGwt : currentProduitLie.getListeCriteres()) {
                CritereWidget critereWidget = null;

                // on recupere la valeur du critere pour l'assure
                for (int iValeur = 0; iValeur < currentAssurePrincipal.getListeValeursCriteres().size(); iValeur++) {
                    final ValeurCritereAssureSelecteurModel valeurAssure = currentAssurePrincipal.getListeValeursCriteres().get(iValeur);
                    if (valeurAssure.getIdentifiantCritere().equals(critereGwt.getIdentifiant())) {
                        // Création du widget
                        critereWidget = creerWidgetPourCritere(critereGwt, valeurAssure);
                    }
                }

                if (critereWidget != null) {
                    // SI LE CRITERE EST VISIBLE AJOUTER DANS LE TABLEAU
                    if (critereGwt.getVisible() != null && critereGwt.getVisible().booleanValue()) {
                        final HTML libelleCritere = new HTML();
                        // Cas particulier du libellé du critère pour le critere age
                        if (critereGwt.getIdentifiant().equals(idCritereAgeMin)) {
                            // Si la contrainte de vente du produit est par Age millésimé
                            if (currentContrainteVente != null && currentContrainteVente.getAgeMillesime() != null
                                && currentContrainteVente.getAgeMillesime().booleanValue()) {
                                libelleCritere.setHTML(pageConstants.labelAgeMillesime() + ComposantTarificateurConstants.DEUX_POINTS);
                            } else {
                                libelleCritere.setHTML(pageConstants.labelAge() + ComposantTarificateurConstants.DEUX_POINTS);
                            }
                        } else {
                            libelleCritere.setHTML(critereGwt.getLibelle() + ComposantTarificateurConstants.DEUX_POINTS);
                        }

                        final String libelleProp =
                            "familleLiee-" + currentFamilleLiee.getIdentifiant() + ".produitLie-" + currentProduitLie.getIdentifiant() + ".assure-"
                                + currentAssurePrincipal.getIdentifiant() + ".idCritere-" + critereGwt.getIdentifiant();

                        // On ajoute les criteres alternativement à gauche et à droite
                        panelCriteres.setWidget(indexLigne, indexWidget % 2 == 0 ? 0 : 2, libelleCritere);
                        panelCriteres.setWidget(indexLigne, indexWidget % 2 == 0 ? 1 : 3, construireBlocIcone(critereWidget.getWidget(), libelleProp));
                        if (indexWidget % 2 == 1) {
                            indexLigne++;
                        }
                        indexWidget++;
                    }
                }
            }
        }
        // Calcul de l'age du prospect en fonction du produit
        calculerAge(currentAssurePrincipal, labelAgePersonne, currentProduitLie, true, tbDateEffet.getObsGenerique());
    }

    /**
     * Charge les bénéficiaires.
     */
    private void chargerBeneficiaires() {
        if (currentProduitLie.getListeBeneficiaires() != null) {
            for (AssureSelecteurModel beneficiaire : currentProduitLie.getListeBeneficiaires()) {
                if (parent.isDevisProduitAdherent() || !parent.getIdentifiantsBeneficiairesInactifs().contains(beneficiaire.getEidPersonne())) {
                    afficherLigneAssure(beneficiaire, false);
                } else {
                    beneficiaire.setIsSelection(false);
                }
            }
            controlerErreurs();
        }
    }

    /**
     * Affiche la ligne pour un assuré.
     * @param assure l'assuré à afficher
     */
    private void afficherLigneAssure(final AssureSelecteurModel assure, final boolean assurePrincipal) {
        int indexWidget = 0;
        int indexLigne = 0;

        // Ajout de la checkBox pour la sélection du bénéficiaire
        final CheckBoxAssureSelecteur ckBeneficiaire = new CheckBoxAssureSelecteur(assure);
        ckBeneficiaire.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Contrôle des contraintes de vente
                controlerErreurs();
            }
        });
        final HorizontalPanel panelInfosBenef = new HorizontalPanel();
        final HTML labelInfosBenef = new HTML();
        final HTML labelLibelleAgeBenef = new HTML();
        final HTML labelAgeBenef = new HTML();
        if (!assurePrincipal) {
            final StringBuffer libelleInfosBenef =
                new StringBuffer(assure.getNom().toUpperCase()).append(PageConstants.SEPARATEUR).append(assure.getPrenom()).append(PageConstants.SEPARATEUR)
                        .append(assure.getDateNaissance());
            labelInfosBenef.setHTML(libelleInfosBenef.toString());
            // Clicklistener pour selectionner en cliquant aussi sur le libelle
            final ClickHandler selectionHandler = new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    ckBeneficiaire.setValue(!ckBeneficiaire.getValue());
                }
            };
            labelInfosBenef.addClickHandler(selectionHandler);
            labelLibelleAgeBenef.addClickHandler(selectionHandler);
            labelAgeBenef.addClickHandler(selectionHandler);
            panelInfosBenef.add(labelInfosBenef);
            panelInfosBenef.add(labelLibelleAgeBenef);
            panelInfosBenef.add(labelAgeBenef);
        }
        final HorizontalPanel conteneurInfosBenef = new HorizontalPanel();
        conteneurInfosBenef.add(ckBeneficiaire);
        conteneurInfosBenef.add(panelInfosBenef);
        conteneurInfosBenef.setCellVerticalAlignment(ckBeneficiaire, HasAlignment.ALIGN_MIDDLE);
        conteneurInfosBenef.setCellVerticalAlignment(panelInfosBenef, HasAlignment.ALIGN_MIDDLE);

        final FlexTable panelInfosBeneficiaires = new FlexTable();
        panelInfosBeneficiaires.setWidth(ComposantTarificateurConstants.POURCENT_100);
        panelInfosBeneficiaires.addStyleName(ComposantTarificateur.RESOURCES.css().panelInfosBeneficiaires());

        // Ajout de la textBox pour la date d'effet
        final CalendarAssureSelecteur tbDateEffetBenef;
        if (!assurePrincipal) {
            tbDateEffetBenef = new CalendarAssureSelecteur(assure, labelAgeBenef, currentProduitLie, assurePrincipal, idCritereAgeMin);
            tbDateEffetBenef.getObsGenerique().ajouterObservateur(this);
        } else {
            tbDateEffetBenef = new CalendarAssureSelecteur(assure, labelAgePersonne, currentProduitLie, assurePrincipal, idCritereAgeMin);
            tbDateEffetBenef.getObsGenerique().ajouterObservateur(this);
        }
        listeCalendarDateEffet.add(tbDateEffetBenef);

        panelInfosBeneficiaires.setWidget(0, 0, conteneurInfosBenef);
        panelInfosBeneficiaires.setWidget(0, 1, new Label(pageConstants.labelDateEffet() + ComposantTarificateurConstants.DEUX_POINTS));
        panelInfosBeneficiaires.setWidget(0, 2, tbDateEffetBenef);

        panelInfosBeneficiaires.getColumnFormatter().setWidth(0, ComposantTarificateurConstants.POURCENT_50);
        panelInfosBeneficiaires.getColumnFormatter().setWidth(1, PageConstants.LARGEUR_LIBELLE_CRITERE);
        panelInfosBeneficiaires.getColumnFormatter().setWidth(2, PageConstants.LARGEUR_CHAMP_CRITERE);

        if (!assurePrincipal) {
            setLibelleAgePersonne(labelLibelleAgeBenef);
        } else {
            setLibelleAgePersonne(labelLibelleAgePersonne);
        }

        final FlexTable panelCriteresBeneficiaires = new FlexTable();
        panelCriteresBeneficiaires.setWidth(ComposantTarificateurConstants.POURCENT_100);
        panelCriteresBeneficiaires.addStyleName(ComposantTarificateur.RESOURCES.css().panelCriteresBeneficiaires());

        // Parcours des critères pour initialisation des widgets
        for (CritereSelecteurModel critereGwt : currentProduitLie.getListeCriteres()) {
            CritereWidget critereWidget = null;

            // on recupere la valeur du critere pour l'assure
            for (ValeurCritereAssureSelecteurModel valeurAssure : assure.getListeValeursCriteres()) {
                if (valeurAssure.getIdentifiantCritere().equals(critereGwt.getIdentifiant())) {
                    // Création du widget
                    critereWidget = creerWidgetPourCritere(critereGwt, valeurAssure);
                }
            }

            if (critereWidget != null) {
                // SI LE CRITERE EST VISIBLE AJOUTER DANS LE TABLEAU
                if (critereGwt.getVisible() != null && critereGwt.getVisible().booleanValue()) {
                    final HTML libelleCritere = new HTML();
                    // Cas particulier du libellé du critère pour le critere age
                    if (critereGwt.getIdentifiant().equals(idCritereAgeMin)) {
                        // Si la contrainte de vente du produit est par Age millésimé
                        if (currentContrainteVente != null && currentContrainteVente.getAgeMillesime() != null
                            && currentContrainteVente.getAgeMillesime().booleanValue()) {
                            libelleCritere.setHTML(pageConstants.labelAgeMillesime() + ComposantTarificateurConstants.DEUX_POINTS);
                        } else {
                            libelleCritere.setHTML(pageConstants.labelAge() + ComposantTarificateurConstants.DEUX_POINTS);
                        }
                    } else {
                        libelleCritere.setHTML(critereGwt.getLibelle() + ComposantTarificateurConstants.DEUX_POINTS);
                    }

                    final String libelleProp =
                        "familleLiee-" + currentFamilleLiee.getIdentifiant() + ".produitLie-" + currentProduitLie.getIdentifiant() + ".assure-"
                            + assure.getIdentifiant() + ".idCritere-" + critereGwt.getIdentifiant();

                    // On ajoute les criteres alternativement à gauche et à droite
                    panelCriteresBeneficiaires.setWidget(indexLigne, indexWidget % 2 == 0 ? 0 : 2, libelleCritere);
                    panelCriteresBeneficiaires.setWidget(indexLigne, indexWidget % 2 == 0 ? 1 : 3, construireBlocIcone(critereWidget.getWidget(), libelleProp));
                    if (indexWidget % 2 == 1) {
                        indexLigne++;
                    }
                    indexWidget++;
                }
            }
        }

        if (currentProduitLie.getListeCriteres().size() == 1) {
            // creation de colonne fictives pour l'affichage
            panelCriteresBeneficiaires.setWidget(0, 0, new Label());
            panelCriteresBeneficiaires.setWidget(0, 1, new Label());
        }
        panelCriteresBeneficiaires.getColumnFormatter().setWidth(0, PageConstants.LARGEUR_LIBELLE_CRITERE);
        panelCriteresBeneficiaires.getColumnFormatter().setWidth(1, PageConstants.LARGEUR_CHAMP_CRITERE);
        panelCriteresBeneficiaires.getColumnFormatter().setWidth(2, PageConstants.LARGEUR_LIBELLE_CRITERE);
        panelCriteresBeneficiaires.getColumnFormatter().setWidth(3, PageConstants.LARGEUR_CHAMP_CRITERE);

        panelBeneficiaires.add(panelInfosBeneficiaires);
        panelBeneficiaires.add(panelCriteresBeneficiaires);

        if (!assurePrincipal) {
            // Calcul de l'age du bénéficiaire
            calculerAge(assure, labelAgeBenef, currentProduitLie, assurePrincipal, tbDateEffetBenef.getObsGenerique());
        } else {
            // Calcul de l'age du prospect
            calculerAge(assure, labelAgePersonne, currentProduitLie, assurePrincipal, tbDateEffetBenef.getObsGenerique());
        }
    }

    /** Crée le widget correspondant au critère. */
    private CritereWidget creerWidgetPourCritere(final CritereSelecteurModel critereGwt, ValeurCritereAssureSelecteurModel valeurCritereAssureGwt) {
        final CritereWidget critereWidget = new CritereWidget(idCritereGeneration, idCritereMois);

        critereWidget.setIdCritere(critereGwt.getIdentifiant());

        // Test du type d'affichage du critère
        // ListBox
        if (constanteTypeHTMLSelect.equals(critereGwt.getTypeHtml())) {
            // ListBox
            final boolean afficherCode = critereGwt.getAfficheCodeOuLib() != null ? critereGwt.getAfficheCodeOuLib().booleanValue() : true;
            final DecoratedSuggestListBoxSingle<ListBoxItem> listBox =
                new DecoratedSuggestListBoxSingle<ListBoxItem>(new SuggestListBoxSingleProperties<ListBoxItem>() {
                    @Override
                    public String getSelectSuggestRenderer(ListBoxItem row) {
                        // Affichage du code si spécifié
                        if (afficherCode) {
                            return row == null ? "" : row.getIdentifiant();
                        } else {
                            return row == null ? "" : row.getLibelle();
                        }
                    }

                    @Override
                    public String[] getResultColumnsRenderer() {
                        return new String[] {};
                    }

                    @Override
                    public String[] getResultRowsRenderer(ListBoxItem row) {
                        // Affichage du code si spécifié
                        if (afficherCode) {
                            return new String[] {row == null ? "" : row.getIdentifiant()};
                        } else {
                            return new String[] {row == null ? "" : row.getLibelle()};
                        }
                    }
                });
            // Remplissage de la liste box avec la liste des valeurs possibles
            listBox.addSuggestHandler(new SuggestListBoxSuggestEventHandler<ListBoxItem>() {
                @Override
                public void suggest(SuggestListBoxSuggestEvent<ListBoxItem> event) {
                    final List<ListBoxItem> liste = new ArrayList<ListBoxItem>();
                    for (ValeurCritereSelecteurModel valeurCritere : critereGwt.getListeValeursCriteres()) {
                        if (afficherCode && valeurCritere.getCodeCritere().startsWith(event.getSuggest())) {
                            liste.add(valeurCritere);
                        } else if (!afficherCode && valeurCritere.getLibelleCritere().startsWith(event.getSuggest())) {
                            liste.add(valeurCritere);
                        }
                    }
                    event.getCallBack().onSuccess(liste);
                }
            });

            final ListBoxSelecteur listBoxSelecteur = new ListBoxSelecteur(valeurCritereAssureGwt, listBox) {
                @Override
                public ListBoxItem getItemValue(String valeur) {
                    for (ValeurCritereSelecteurModel valeurCritere : critereGwt.getListeValeursCriteres()) {
                        if (valeurCritere.getCodeCritere().equals(valeur)) {
                            return valeurCritere;
                        }
                    }
                    return null;
                }
            };
            // On sélectionne la valeur par défaut dans la liste déroulante.
            final String valeurParDefaut = critereGwt.getValeurParDefautCod();
            if (valeurParDefaut != null && !"".equals(valeurParDefaut)) {
                listBoxSelecteur.setValue(valeurParDefaut);
            }
            critereWidget.setWidget(listBoxSelecteur);

        }
        // Gestion du type calendar
        else if (constanteTypeCalendar.equals(critereGwt.getTypeHtml())) {
            final CalendarSelecteur calendarSelecteur = new CalendarSelecteur(valeurCritereAssureGwt);
            critereWidget.setWidget(calendarSelecteur);
        }
        // Sinon type TextBox
        else {
            final TextBoxSelecteur textBoxSelecteur = new TextBoxSelecteur(valeurCritereAssureGwt);
            critereWidget.setWidget(textBoxSelecteur);
        }
        // PRISE EN CHARGE MODE VISIBLE/MODIFIABLE DU CRITERE
        critereWidget.setVisible(critereGwt.getVisible() != null ? critereGwt.getVisible().booleanValue() : true);
        critereWidget.setEnabled(critereGwt.getModifiable() != null ? critereGwt.getModifiable().booleanValue() : true);

        return critereWidget;

    }

    /**
     * Affecte la date d'effet.
     * @param dateEffet la date d'effet
     */
    public void setDateEffet(String dateEffet) {
        String dateEffetUtilisee = dateEffet;
        // si on est dans le cas d'une famille bonus, on ajoute le nombre d'années correspondantes
        if (currentFamilleLiee.getIdentifiant().equals(idFamilleBonus1)) {
            final String[] tabDate = dateEffetUtilisee.split(SEPARATEUR_DATE);
            tabDate[2] = String.valueOf(Integer.valueOf(tabDate[2]).intValue() + nbAnneesBonus1);
            dateEffetUtilisee = tabDate[0] + SEPARATEUR_DATE + tabDate[1] + SEPARATEUR_DATE + tabDate[2];
        }
        if (currentFamilleLiee.getIdentifiant().equals(idFamilleBonus2)) {
            final String[] tabDate = dateEffetUtilisee.split(SEPARATEUR_DATE);
            tabDate[2] = String.valueOf(Integer.valueOf(tabDate[2]).intValue() + nbAnneesBonus2);
            dateEffetUtilisee = tabDate[0] + SEPARATEUR_DATE + tabDate[1] + SEPARATEUR_DATE + tabDate[2];
        }
        for (CalendarAssureSelecteur calendar : listeCalendarDateEffet) {
            // on modifie la date automatiquement que si autorisé
            // ce flag est mis à true lorsque l'on recupere un bonus que l'adherent a deja
            if (calendar.getObjSelecteur().getAllowChangeWithAutoDateEffet()) {
                calendar.setDate(dateEffetUtilisee);
            }
        }
    }

    /**
     * Recupere et affiche les erreurs du produit.
     */
    private void controlerErreurs() {
        lbErreurs.setHTML("");
        final List<RapportErreurModel> listeRapports =
            currentProduitLie.controlerErreurs(idCritereGeneration, false, parent.getIdentifiantsBeneficiairesInactifs());

        if (listeRapports != null && listeRapports.size() > 0) {
            for (RapportErreurModel rapport : listeRapports) {
                if (rapport != null && rapport.getListeErreurs() != null) {
                    // Si erreur : affichage des erreurs dans le libellé
                    final StringBuffer libelleErreurs = new StringBuffer();
                    for (int i = 0; i < rapport.getListeErreurs().size(); i++) {
                        if (i != 0) {
                            libelleErreurs.append(ComposantTarificateurConstants.SAUT_LIGNE);
                        }
                        libelleErreurs.append(rapport.getListeErreurs().get(i));
                    }
                    if (lbErreurs.getHTML().equals("")) {
                        lbErreurs.setHTML(libelleErreurs.toString());
                    } else {
                        lbErreurs.setHTML(new StringBuffer(lbErreurs.getHTML()).append(ComposantTarificateurConstants.SAUT_LIGNE).append(
                            libelleErreurs.toString()).toString());
                    }
                    lbErreurs.setVisible(true);
                }
            }
        } else {
            lbErreurs.setText("");
            lbErreurs.setVisible(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void nouvelEvenement(Evenement evenement) {
        if (evenement.getNom().equals(ComposantTarificateurConstants.EVENT_DEMANDE_VERIF_CONTRAINTES_VENTE)) {
            controlerErreurs();
        } else if (evenement.getNom().equals(ComposantTarificateurConstants.EVENT_DEMANDE_CALCULER_AGE)) {
            // Evenement de recalcul de l'age
            if (evenement.getSender() instanceof CalendarAssureSelecteur) {
                final CalendarAssureSelecteur calendar = (CalendarAssureSelecteur) evenement.getSender();
                // Calcul de l'age
                calculerAge(calendar.getObjSelecteur(), calendar.getLabelAge(), calendar.getProduit(), calendar.isProspect(), calendar.getObsGenerique());
            }
        }
    }

    /**
     * Calcule l'age de l'assure pour le produit demandé et l'affiche dans le label.
     */
    private void calculerAge(final AssureSelecteurModel objSelecteur, final HTML labelAge, ProduitSelecteurModel produit, boolean isProspect,
        final ObservableGenerique observableGenerique) {

        // Création du DTO pour le calcul de l'âge
        final DateCalculAgeModel dateCalculAgeGwt = new DateCalculAgeModel();
        dateCalculAgeGwt.setDateCalcul(objSelecteur.getDateEffetTarification());
        dateCalculAgeGwt.setDateNaissance(objSelecteur.getDateNaissance());

        // Recherche de l'âge dans la map
        final AgeModel ageGwt = parent.getAgeDansMap(dateCalculAgeGwt);
        if (ageGwt != null) {
            // On termine la méthode de calcul d'âge
            finirCalculerAge(objSelecteur, labelAge, observableGenerique, dateCalculAgeGwt, ageGwt);
        } else {
            // Sinon appel du service de calcul de l'âge
            // Création du callback
            final AsyncCallback<AgeModel> callback = new AsyncCallback<AgeModel>() {
                public void onFailure(Throwable caught) {
                	if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_DATE_PARSING)) {
                		ErrorPopup.afficher(new ErrorPopupConfiguration(new TechnicalExceptionGwt(constantComposantTarificateur.errorDateParsing())));
                	} else if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_DATE_INVALID)) {
                		ErrorPopup.afficher(new ErrorPopupConfiguration(new BusinessExceptionGwt(constantComposantTarificateur.errorDateInvalid())));
                	} else if (caught.getMessage().equals(ComposantTarificateurConstants.ERROR_DATE_ABSENT)) {
                		ErrorPopup.afficher(new ErrorPopupConfiguration(new BusinessExceptionGwt(constantComposantTarificateur.errorDateAbsent())));
                	} else {
                		ErrorPopup.afficher(new ErrorPopupConfiguration(caught));
                	}
                }

                public void onSuccess(AgeModel ageGwt) {
                    // Ajout de l'âge à la map des âges
                    parent.getMapAges().put(dateCalculAgeGwt.getDateCalcul() + dateCalculAgeGwt.getDateNaissance(), ageGwt);

                    // On termine la méthode de calcul d'âge
                    finirCalculerAge(objSelecteur, labelAge, observableGenerique, dateCalculAgeGwt, ageGwt);
                }
            };

            // Appel du service de récupération de l'âge
            selecteurProduitService.getAgesCalculesPersonne(dateCalculAgeGwt, callback);
        }
    }

    /** Fin du calcul de l'âge. */
    private void finirCalculerAge(final AssureSelecteurModel objSelecteur, final HTML labelAge, final ObservableGenerique observableGenerique,
        DateCalculAgeModel dateCalculAgeGwt, AgeModel ageGwt) {

        Integer ageAssure = ageGwt.getAge();

        // Affichage Age ou Age Millésimé selon la contrainte de vente du produit
        if (currentContrainteVente != null && currentContrainteVente.getAgeMillesime() != null && currentContrainteVente.getAgeMillesime().booleanValue()) {
            ageAssure = ageGwt.getAgeMillesime();
        }
        // Affectation du libellé de l'age de la personne
        objSelecteur.setAge(ageAssure);
        labelAge.setHTML(ageAssure.toString());

        // on parcours les criteres de l'assure pour mettre a jour le critere age si il existe
        for (ValeurCritereAssureSelecteurModel valeur : objSelecteur.getListeValeursCriteres()) {
            if (valeur.getIdentifiantCritere().equals(idCritereAgeMin)) {
                valeur.setValeur(ageAssure.toString());
            }
        }

        // on parcours les criteres de l'assure pour mettre a jour le critere age si il existe
        for (int i = 0; i < objSelecteur.getListeValeursCriteres().size(); i++) {
            final ValeurCritereAssureSelecteurModel valeur = (ValeurCritereAssureSelecteurModel) objSelecteur.getListeValeursCriteres().get(i);
            if (valeur.getIdentifiantCritere().equals(idCritereAgeMin)) {
                valeur.setValeur(ageAssure.toString());
            }
        }

        // Contrôle des contraintes de vente
        controlerErreurs();
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

}

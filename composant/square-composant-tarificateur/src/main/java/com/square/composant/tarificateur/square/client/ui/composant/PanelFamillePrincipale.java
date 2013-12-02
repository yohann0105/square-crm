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
import org.scub.foundation.framework.gwt.module.client.util.DataCheckerUtil;
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
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
import com.square.composant.tarificateur.square.client.model.selecteur.FamillePrincipaleSelecteurModel;
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
 * Panneau pour la famille principale.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class PanelFamillePrincipale extends VerticalPanel implements Observateur {

    private PanelProduits.PageConstants pageConstants;

    private FamillePrincipaleSelecteurModel currentFamillePrincipale;

    private ProduitSelecteurModel currentProduitPrincipal;

    private AssureSelecteurModel currentAssurePrincipal;

    /** Contrainte de vente du produit. */
    private ContrainteVenteSelecteurModel currentContrainteVente;

    /** Label d'infos sur la personne. */
    private HTML labelInfosPersonne;

    /** Label de l'age de la personne. */
    private HTML labelAgePersonne;

    /** Label du libelle de l'age de la personne. */
    private HTML labelLibelleAgePersonne;

    /** Label des erreurs. */
    private HTML lbErreurs;

    /** TextBox pour la date d'effet. */
    private CalendarAssureSelecteur tbDateEffet;

    private Integer idCritereAgeMin;

    private Integer idCritereGeneration;

    private Integer idCritereMois;

    private String constanteTypeHTMLSelect;

    private String constanteTypeCalendar;

    private List<CalendarAssureSelecteur> listeCalendarDateEffet;

    /** Service SelecteurProduitService. */
    private SelecteurProduitServiceGwtAsync selecteurProduitService;

    /** Panel Produit parent. */
    private PanelProduits parent;

    private IconeErreurChampManager iconeErreurChampManager;

    private ComposantTarificateurConstants constantComposantTarificateur = GWT.create(ComposantTarificateurConstants.class);;

    /**
     * Constructeur avec paramètre.
     * @param famillePrincipale la famille principale
     * @param constantes constantes de mapping nécessaires au composant
     * @param pageConstants constantes de la page
     * @param selecteurProduitService selecteurProduitService
     * @param parent le panel Produits parent
     * @param iconeErreurChampManager manager des icones d'erreur
     */
    public PanelFamillePrincipale(FamillePrincipaleSelecteurModel famillePrincipale, ConstantesModel constantes, PanelProduits.PageConstants pageConstants,
        SelecteurProduitServiceGwtAsync selecteurProduitService, PanelProduits parent, IconeErreurChampManager iconeErreurChampManager) {
        this.pageConstants = pageConstants;
        this.parent = parent;
        this.iconeErreurChampManager = iconeErreurChampManager;
        // Initialisation des services
        this.selecteurProduitService = selecteurProduitService;

        currentFamillePrincipale = famillePrincipale;
        currentProduitPrincipal = famillePrincipale.getProduitPrincipal();
        currentAssurePrincipal = currentProduitPrincipal.getAssurePrincipal();
        currentContrainteVente = currentProduitPrincipal.getContraintesVente();
        listeCalendarDateEffet = new ArrayList<CalendarAssureSelecteur>();

        // Initialisation des variables
        idCritereAgeMin = constantes.getIdCritereAgeMin();
        idCritereGeneration = constantes.getIdCritereGeneration();
        idCritereMois = constantes.getIdCritereMois();
        constanteTypeHTMLSelect = constantes.getConstanteTypeHTMLSelect();
        constanteTypeCalendar = constantes.getConstanteTypeCalendar();

        this.addStyleName(ComposantTarificateur.RESOURCES.css().panelFamillePrincipale());
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
        final HTML libelleGamme = new HTML(currentFamillePrincipale.getLibelleGamme());
        libelleGamme.addStyleName(ComposantTarificateur.RESOURCES.css().libelleGras());
        conteneurGamme.add(labelGamme);
        conteneurGamme.add(libelleGamme);

        final HorizontalPanel conteneurProduit = new HorizontalPanel();
        conteneurProduit.setSpacing(PageConstants.SPACING_TITRE_FAMILLE);
        final HTML labelProduit = new HTML(pageConstants.labelLibelleProduit() + ComposantTarificateurConstants.DEUX_POINTS);
        final HTML libelleProduit = new HTML(currentProduitPrincipal.getLibelle());
        libelleProduit.addStyleName(ComposantTarificateur.RESOURCES.css().libelleGras());
        conteneurProduit.add(labelProduit);
        conteneurProduit.add(libelleProduit);
        panelTitreFamille.setWidget(0, 0, conteneurGamme);
        panelTitreFamille.setWidget(0, 1, conteneurProduit);
        add(panelTitreFamille);

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
        add(panelConteneurInfosPersonne);
        add(lbErreurs);

        chargerCriteres();

        chargerBeneficiaires();

        setLibelleAgePersonne(labelLibelleAgePersonne);
    }

    /** Affecte les infos de la personne. */
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
     * Charge les criteres.
     */
    private void chargerCriteres() {
        // Ajout des critères
        final FlexTable panelCriteres = new FlexTable();
        panelCriteres.setCellSpacing(PageConstants.SPACING_CRITERES);
        panelCriteres.setWidth(ComposantTarificateurConstants.POURCENT_100);
        panelCriteres.addStyleName(ComposantTarificateur.RESOURCES.css().panelCriteres());

        // Ajout de la textBox pour la date d'effet
        tbDateEffet = new CalendarAssureSelecteur(currentAssurePrincipal, labelAgePersonne, currentProduitPrincipal, true, idCritereAgeMin);
        tbDateEffet.getObsGenerique().ajouterObservateur(this);
        panelCriteres.setWidget(0, 0, new Label(pageConstants.labelDateEffet() + ComposantTarificateurConstants.DEUX_POINTS));
        panelCriteres.setWidget(0, 1, tbDateEffet);
        panelCriteres.setWidget(0, 2, new Label());
        panelCriteres.setWidget(0, 3, new Label());
        panelCriteres.getColumnFormatter().setWidth(0, PageConstants.LARGEUR_LIBELLE_CRITERE);
        panelCriteres.getColumnFormatter().setWidth(1, PageConstants.LARGEUR_CHAMP_CRITERE);
        panelCriteres.getColumnFormatter().setWidth(2, PageConstants.LARGEUR_LIBELLE_CRITERE);
        panelCriteres.getColumnFormatter().setWidth(3, PageConstants.LARGEUR_CHAMP_CRITERE);

        if (currentProduitPrincipal.getListeCriteres() != null && currentProduitPrincipal.getListeCriteres().size() > 0) {
            int indexLigne = 1;
            int indexWidget = 0;

            // Parcours des critères pour initialisation des widgets
            for (CritereSelecteurModel critereGwt : currentProduitPrincipal.getListeCriteres()) {
                CritereWidget critereWidget = null;

                // on recupere la valeur du critere pour l'assure
                for (ValeurCritereAssureSelecteurModel valeurAssure : currentAssurePrincipal.getListeValeursCriteres()) {
                    if (valeurAssure.getIdentifiantCritere().equals(critereGwt.getIdentifiant())) {
                        // Création du widget
                        critereWidget = creerWidgetPourCritere(critereGwt, valeurAssure, true);
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
                            "famillePrincipale.produitPrincipal.assure-" + currentAssurePrincipal.getIdentifiant() + ".idCritere-"
                                + critereGwt.getIdentifiant();

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

        add(panelCriteres);
        // Calcul de l'age du prospect
        calculerAge(currentAssurePrincipal, labelAgePersonne, currentProduitPrincipal, true, tbDateEffet.getObsGenerique());
    }

    /**
     * Charge les bénéficiaires.
     */
    private void chargerBeneficiaires() {
        if (currentProduitPrincipal.getListeBeneficiaires() != null) {

            int indexLigne = 0;

            for (final AssureSelecteurModel beneficiaire : currentProduitPrincipal.getListeBeneficiaires()) {
                if (parent.isDevisProduitAdherent() || !parent.getIdentifiantsBeneficiairesInactifs().contains(beneficiaire.getEidPersonne())) {
                    int indexWidget = 0;

                    // Ajout de la checkBox pour la sélection du bénéficiaire
                    final CheckBoxAssureSelecteur ckBeneficiaire = new CheckBoxAssureSelecteur(beneficiaire);
                    ckBeneficiaire.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent event) {
                            // Contrôle des contraintes de vente
                            controlerErreurs();
                        }
                    });
                    // Clicklistener pour selectionner en cliquant aussi sur le libelle
                    final ClickHandler selectionHandler = new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent event) {
                            ckBeneficiaire.setValue(!ckBeneficiaire.getValue());
                        }
                    };
                    final HTML labelAgeBenef = new HTML();
                    final HTML labelLibelleAgeBenef = new HTML();
                    final HorizontalPanel panelInfosBenef = new HorizontalPanel();
                    final StringBuffer libelleInfosBenef =
                        new StringBuffer(beneficiaire.getNom().toUpperCase()).append(PageConstants.SEPARATEUR).append(beneficiaire.getPrenom()).append(
                            PageConstants.SEPARATEUR).append(beneficiaire.getDateNaissance());
                    final HTML labelInfosBenef = new HTML(libelleInfosBenef.toString());
                    labelInfosBenef.addClickHandler(selectionHandler);
                    labelLibelleAgeBenef.addClickHandler(selectionHandler);
                    labelAgeBenef.addClickHandler(selectionHandler);
                    panelInfosBenef.add(labelInfosBenef);
                    panelInfosBenef.add(labelLibelleAgeBenef);
                    panelInfosBenef.add(labelAgeBenef);
                    final HorizontalPanel conteneurInfosBenef = new HorizontalPanel();
                    conteneurInfosBenef.add(ckBeneficiaire);
                    conteneurInfosBenef.add(panelInfosBenef);
                    conteneurInfosBenef.setCellVerticalAlignment(ckBeneficiaire, HasAlignment.ALIGN_MIDDLE);
                    conteneurInfosBenef.setCellVerticalAlignment(panelInfosBenef, HasAlignment.ALIGN_MIDDLE);

                    final FlexTable panelInfosBeneficiaires = new FlexTable();
                    panelInfosBeneficiaires.setWidth(ComposantTarificateurConstants.POURCENT_100);

                    // Ajout de la textBox pour la date d'effet
                    final CalendarAssureSelecteur tbDateEffetBenef =
                        new CalendarAssureSelecteur(beneficiaire, labelAgeBenef, currentProduitPrincipal, false, idCritereAgeMin);
                    tbDateEffetBenef.getObsGenerique().ajouterObservateur(this);
                    listeCalendarDateEffet.add(tbDateEffetBenef);

                    panelInfosBeneficiaires.setWidget(indexLigne, 0, conteneurInfosBenef);
                    panelInfosBeneficiaires.setWidget(indexLigne, 1, new Label(pageConstants.labelDateEffet() + ComposantTarificateurConstants.DEUX_POINTS));
                    panelInfosBeneficiaires.setWidget(indexLigne, 2, tbDateEffetBenef);

                    panelInfosBeneficiaires.getColumnFormatter().setWidth(0, ComposantTarificateurConstants.POURCENT_50);
                    panelInfosBeneficiaires.getColumnFormatter().setWidth(1, PageConstants.LARGEUR_LIBELLE_CRITERE);
                    panelInfosBeneficiaires.getColumnFormatter().setWidth(2, PageConstants.LARGEUR_CHAMP_CRITERE);

                    indexLigne++;

                    setLibelleAgePersonne(labelLibelleAgeBenef);

                    // Ajout des criteres des bénéficiaires
                    final FlexTable panelCriteresBeneficiaires = new FlexTable();
                    panelCriteresBeneficiaires.setWidth(ComposantTarificateurConstants.POURCENT_100);
                    panelCriteresBeneficiaires.addStyleName(ComposantTarificateur.RESOURCES.css().panelCriteresBeneficiaires());

                    // Parcours des critères pour initialisation des widgets
                    for (CritereSelecteurModel critereGwt : currentProduitPrincipal.getListeCriteres()) {
                        CritereWidget critereWidget = null;

                        // on recupere la valeur du critere pour l'assure
                        for (ValeurCritereAssureSelecteurModel valeurAssure : beneficiaire.getListeValeursCriteres()) {
                            if (valeurAssure.getIdentifiantCritere().equals(critereGwt.getIdentifiant())) {
                                // Création du widget
                                critereWidget = creerWidgetPourCritere(critereGwt, valeurAssure, false);
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
                                    "famillePrincipale.produitPrincipal.assure-" + beneficiaire.getIdentifiant() + ".idCritere-" + critereGwt.getIdentifiant();

                                // On ajoute les criteres alternativement à gauche et à droite
                                panelCriteresBeneficiaires.setWidget(indexLigne, indexWidget % 2 == 0 ? 0 : 2, libelleCritere);
                                panelCriteresBeneficiaires.setWidget(indexLigne, indexWidget % 2 == 0 ? 1 : 3, construireBlocIcone(critereWidget.getWidget(),
                                    libelleProp));
                                if (indexWidget % 2 == 1) {
                                    indexLigne++;
                                }
                                indexWidget++;
                            }
                        }

                    }

                    if (currentProduitPrincipal.getListeCriteres().size() == 1) {
                        // creation de colonne fictives pour l'affichage
                        panelCriteresBeneficiaires.setWidget(0, 0, new Label());
                        panelCriteresBeneficiaires.setWidget(0, 1, new Label());
                    }
                    panelCriteresBeneficiaires.getColumnFormatter().setWidth(0, PageConstants.LARGEUR_LIBELLE_CRITERE);
                    panelCriteresBeneficiaires.getColumnFormatter().setWidth(1, PageConstants.LARGEUR_CHAMP_CRITERE);
                    panelCriteresBeneficiaires.getColumnFormatter().setWidth(2, PageConstants.LARGEUR_LIBELLE_CRITERE);
                    panelCriteresBeneficiaires.getColumnFormatter().setWidth(3, PageConstants.LARGEUR_CHAMP_CRITERE);

                    add(panelInfosBeneficiaires);
                    add(panelCriteresBeneficiaires);

                    // Calcul de l'age du bénéficiaire
                    calculerAge(beneficiaire, labelAgeBenef, currentProduitPrincipal, false, tbDateEffetBenef.getObsGenerique());
                } else {
                    beneficiaire.setIsSelection(false);
                }
            }
            controlerErreurs();
        }
    }

    /** Crée le widget correspondant au critère. */
    private CritereWidget creerWidgetPourCritere(final CritereSelecteurModel critereGwt, final ValeurCritereAssureSelecteurModel valeurCritereAssureGwt,
        boolean isAdherentPrincipal) {

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
            listBoxSelecteur.addValueChangeHandler(new ValueChangeHandler<ListBoxItem>() {
                @Override
                public void onValueChange(ValueChangeEvent<ListBoxItem> event) {
                    if (valeurCritereAssureGwt.getIdentifiantCritere().equals(idCritereGeneration)) {
                        controlerErreurs();
                    }
                }
            });

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
        if (isAdherentPrincipal) {
            critereWidget.setEnabled(critereGwt.getModifiablePourAdherentPrincipal() != null ? critereGwt.getModifiablePourAdherentPrincipal().booleanValue()
                : true);
        }

        // si il s'agit du critere mois, le critere observe la page pour se mettre a jour suivant le chgt de date d'effet
        if (critereGwt.getIdentifiant().equals(idCritereMois)) {
            tbDateEffet.getObsGenerique().ajouterObservateur(critereWidget);
        }

        if (critereGwt.getIdentifiant().equals(idCritereGeneration) && parent.isDevisProduitAdherent()) {
            if (critereWidget != null) {
                critereWidget.setEnabled(false);
            }
        }
        return critereWidget;

    }

    /**
     * Récupère la date d'effet et l'affecte aux beneficiaires.
     * @return la date d'effet
     */
    public String getDateEffet() {
        final String dateEffet = tbDateEffet.getDate();

        if (DataCheckerUtil.isDate(dateEffet)) {
            // on met a jour les dates d'effet des beneficiaires
            for (CalendarAssureSelecteur calendar : listeCalendarDateEffet) {
                calendar.setDate(dateEffet);
            }
        }
        return dateEffet;
    }

    /**
     * Recupere et affiche les erreurs du produit.
     */
    private void controlerErreurs() {
        lbErreurs.setHTML("");
        final List<RapportErreurModel> listeRapports =
            currentProduitPrincipal.controlerErreurs(idCritereGeneration, false, parent.getIdentifiantsBeneficiairesInactifs());

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

    /**
     * Get the tbDateEffet value.
     * @return the tbDateEffet
     */
    public CalendarAssureSelecteur getTbDateEffet() {
        return tbDateEffet;
    }

}

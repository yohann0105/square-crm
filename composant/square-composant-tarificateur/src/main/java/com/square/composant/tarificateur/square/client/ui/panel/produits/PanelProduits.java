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
package com.square.composant.tarificateur.square.client.ui.panel.produits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.util.DataCheckerUtil;
import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.ConstantesModel;
import com.square.composant.tarificateur.square.client.model.age.AgeModel;
import com.square.composant.tarificateur.square.client.model.age.DateCalculAgeModel;
import com.square.composant.tarificateur.square.client.model.selecteur.FamilleLieeSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.FamillePrincipaleSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.ProduitSelecteurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.RapportErreurModel;
import com.square.composant.tarificateur.square.client.model.selecteur.SelecteurProduitModel;
import com.square.composant.tarificateur.square.client.service.SelecteurProduitServiceGwtAsync;
import com.square.composant.tarificateur.square.client.ui.composant.PanelFamilleLiee;
import com.square.composant.tarificateur.square.client.ui.composant.PanelFamillePrincipale;
import com.square.composant.tarificateur.square.client.ui.popup.selection.PopupSelectionProduits;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;

/**
 * Panel dans lequel se trouve l'ensemble des produits (principal et liés) par bénéficiaire.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class PanelProduits extends VerticalPanel {

    /**
     * The constants used in this page.
     */
    public interface PageConstants extends Constants {

        /** Spacing. */
        int SPACING_INFOS_PERSONNE = 5;

        /** Spacing. */
        int SPACING_CRITERES = 5;

        /** Spacing. */
        int SPACING_TITRE_FAMILLE = 2;

        /** LARGEUR_LIBELLE_CRITERE. */
        String LARGEUR_LIBELLE_CRITERE = "15%";

        /** LARGEUR_CHAMP_CRITERE. */
        String LARGEUR_CHAMP_CRITERE = "35%";

        /** PageConstants.SEPARATEUR. */
        String SEPARATEUR = "&nbsp;-&nbsp;";

        /**
         * Le texte.
         * @return le texte
         */
        String labelLibelleProduit();

        /**
         * Le texte.
         * @return le texte
         */
        String labelGammeProduit();

        /**
         * Le texte.
         * @return le texte
         */
        String labelAgeMillesime();

        /**
         * Le texte.
         * @return le texte
         */
        String labelAge();

        /**
         * Le texte.
         * @return le texte
         */
        String labelDateEffet();

        /**
         * Le texte.
         * @return le texte
         */
        String labelFamilleProduit();

        /**
         * Le texte.
         * @return le texte
         */
        String titreProduits();
    }

    /**
     * Page constants.
     */
    private PageConstants pageConstants;

    /** Panneau du produit principal. */
    private PanelFamillePrincipale panelFamillePrincipale;

    private ConstantesModel constantes;

    private SelecteurProduitServiceGwtAsync selecteurProduitService;

    private PopupSelectionProduits popup;

    /** Panneaux des produits liés. */
    private List<PanelFamilleLiee> listePanelsFamillesLiees;

    /** Selecteur produit. */
    private SelecteurProduitModel selecteurProduit;

    /** Map des âges. */
    private Map<String, AgeModel> mapAges;

    private Integer idCritereGeneration;

    /**
     * Constructeur.
     * @param constantes constantes de mapping nécessaires au composant
     * @param selecteurProduitService selecteurProduitService
     * @param popup popup de selection des produits
     */
    public PanelProduits(ConstantesModel constantes, SelecteurProduitServiceGwtAsync selecteurProduitService, PopupSelectionProduits popup) {
        // Create the page constants
        pageConstants = (PageConstants) GWT.create(PageConstants.class);

        this.constantes = constantes;
        this.selecteurProduitService = selecteurProduitService;
        this.popup = popup;
        this.addStyleName(ComposantTarificateur.RESOURCES.css().panelProduits());
        this.setWidth(ComposantTarificateurConstants.POURCENT_100);

        idCritereGeneration = constantes.getIdCritereGeneration();

        // Initialisation de la liste des panneaux de produits liés
        listePanelsFamillesLiees = new ArrayList<PanelFamilleLiee>();

        // Initialisation de la map des ages
        mapAges = new HashMap<String, AgeModel>();
    }

    /**
     * Charge le selecteur produit sur la fiche.
     * @param selecteurProduitGwt le selecteur produit contenant toutes les infos nécessaires
     * @param iconeErreurChampManager manager des icones d'erreur
     */
    public void chargerSelecteurProduit(SelecteurProduitModel selecteurProduitGwt, IconeErreurChampManager iconeErreurChampManager) {
        this.selecteurProduit = selecteurProduitGwt;

        // Récupération de la map des âges
        mapAges = selecteurProduitGwt.getMapAges();

        // Effacement des panels
        clear();

        // Initialisation de la liste des panneaux de produits liés
        if (panelFamillePrincipale != null) {
            remove(panelFamillePrincipale);
            for (int index = 0; index < listePanelsFamillesLiees.size(); index++) {
                remove((Widget) listePanelsFamillesLiees.get(index));
            }
            listePanelsFamillesLiees.clear();
        }

        // Création des panels du produit principal
        creerPanelFamillePrincipale(selecteurProduitGwt.getFamillePrincipale(), iconeErreurChampManager);
        creerPanelFamillesLiees(selecteurProduitGwt.getListeFamillesLiees(), iconeErreurChampManager);

        // Affichage de la popup de sélection des produits
        popup.show();

        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                panelFamillePrincipale.getTbDateEffet().setFocus(true);
            }
        });
    }

    /**
     * Uniformise les dates d'effet des produits liés avec celle du produit principal.
     */
    public void uniformiserDateEffet() {
        // Récupération de la date d'effet du produit principal et affecte cette date aux beneficiaires
        final String dateEffetProduitPrincipal = panelFamillePrincipale.getDateEffet();

        if (DataCheckerUtil.isDate(dateEffetProduitPrincipal)) {
            // Parcours des panneaux des produits liés
            for (PanelFamilleLiee panelFamilleLiee : listePanelsFamillesLiees) {
                // Modification de la date d'effet des produits liés
                panelFamilleLiee.setDateEffet(dateEffetProduitPrincipal);
            }
        }
    }

    /**
     * Contrôle les contraintes de vente du produit principal et des produits liés sélectionnés. Affiche une popup d'erreur s'il y a une erreur
     * @return return true s'il y a une erreur, false sinon
     */
    public boolean hasErreurs() {
        final StringBuffer messageComplet = new StringBuffer();
        boolean hasErreurs = false;

        // Récupération du rapport d'erreurs du produit principal
        final List<RapportErreurModel> listeRapportsProduitPrincipal = selecteurProduit.getFamillePrincipale().getProduitPrincipal().controlerErreurs(
                idCritereGeneration, true, getIdentifiantsBeneficiairesInactifs());
        // Si erreur
        if (listeRapportsProduitPrincipal != null && listeRapportsProduitPrincipal.size() > 0) {
            for (RapportErreurModel rapport : listeRapportsProduitPrincipal) {
                // ajoute le rapport au message complet
                messageComplet.append(rapport.toHTML()).append(ComposantTarificateurConstants.SAUT_LIGNE);
                hasErreurs = true;
            }
        }

        // Vérification des contraintes de ventes des produits liés sélectionnés
        for (FamilleLieeSelecteurModel familleLieeSelecteur : selecteurProduit.getListeFamillesLiees()) {
            if (familleLieeSelecteur.getIsSelection() != null && familleLieeSelecteur.getIsSelection().booleanValue()
                    && familleLieeSelecteur.getListeProduitsLies() != null) {
                for (ProduitSelecteurModel produitSelecteur : familleLieeSelecteur.getListeProduitsLies()) {
                    // Récupération du rapport d'erreurs de la famille liée
                    final List<RapportErreurModel> listeRapportsFamilleLiee = produitSelecteur.controlerErreurs(idCritereGeneration, true, getIdentifiantsBeneficiairesInactifs());
                    // Si erreur
                    if (listeRapportsFamilleLiee != null && listeRapportsFamilleLiee.size() > 0) {
                        for (RapportErreurModel rapport : listeRapportsFamilleLiee) {
                            // ajoute le rapport au message complet
                            messageComplet.append(rapport.toHTML()).append(ComposantTarificateurConstants.SAUT_LIGNE);
                            hasErreurs = true;
                        }
                    }
                }
            }
        }
        if (hasErreurs) {
            // Affichage de la popup d'erreurs
            ErrorPopup.afficher(new ErrorPopupConfiguration(messageComplet.toString()));
        }

        return hasErreurs;
    }

    /**
     * Ajoute les panneaux concernant la famille principale (pour prospect et bénéficiaires).
     */
    private void creerPanelFamillePrincipale(FamillePrincipaleSelecteurModel famillePrincipale, IconeErreurChampManager iconeErreurChampManager) {
        // Création du panneau de la famille principale
        panelFamillePrincipale = new PanelFamillePrincipale(famillePrincipale, constantes, pageConstants, selecteurProduitService, this,
            iconeErreurChampManager);
        add(panelFamillePrincipale);
    }

    /**
     * Ajoute les panneaux concernant les familles liées (pour prospect et bénéficiaires).
     */
    private void creerPanelFamillesLiees(List<FamilleLieeSelecteurModel> listeFamillesLiees, IconeErreurChampManager iconeErreurChampManager) {
        for (FamilleLieeSelecteurModel familleLieeSelecteur : listeFamillesLiees) {
            final PanelFamilleLiee panelFamilleLiee = new PanelFamilleLiee(familleLieeSelecteur, constantes, pageConstants, selecteurProduitService, this,
                iconeErreurChampManager);
            listePanelsFamillesLiees.add(panelFamilleLiee);
            add(panelFamilleLiee);
        }
    }

    /**
     * Récupère la liste des bénéficiaires inactifs, non sélectionnables.
     * @return les bénéficiaires inactifs
     */
    public List<Long> getIdentifiantsBeneficiairesInactifs() {
    	return popup.getIdentifiantsBeneficiairesInactifs();
    }

    /**
     * Recupere la valeur de selecteurProduit.
     * @return la valeur de selecteurProduit
     */
    public SelecteurProduitModel getSelecteurProduit() {
        return selecteurProduit;
    }

    /**
     * Récupère l'âge dans la map à partir de dates de calcul.
     * @param dateCalculAgeGwt les dates nécessaires au calcul
     * @return les âges calculés
     */
    public AgeModel getAgeDansMap(DateCalculAgeModel dateCalculAgeGwt) {
        return mapAges.get(dateCalculAgeGwt.getDateCalcul() + dateCalculAgeGwt.getDateNaissance());
    }

    /**
     * Retourne la valeur de mapAges.
     * @return la valeur de mapAges
     */
    public Map<String, AgeModel> getMapAges() {
        return mapAges;
    }

    /**
     * Retourne si on est dans le cadre d'une action "générer la proposition".
     * @return true si on est dans le cadre d'une action "générer la proposition".
     */
    public boolean isDevisProduitAdherent() {
    	return popup.isDevisProduitAdherent();
    }
}

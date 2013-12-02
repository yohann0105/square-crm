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
package com.square.client.gwt.client.view.personne.morale.gestion;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes pour la vue gestion de personne morale.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface GestionPersonneMoraleViewImplConstants extends Constants {

    /** Largeur. */
    String LARGEUR_COL_LIBELLE_0 = "16%";

    /** Largeur. */
    String LARGEUR_COL_CHAMP_1 = "34%";

    /** Largeur. */
    String LARGEUR_COL_LIBELLE_2 = "12%";

    /** Largeur. */
    String LARGEUR_COL_CHAMP_3 = "38%";

    /** Index de la table des actions. */
    int INDEX_TAB_ACTIONS = 2;

    /** Index de la table des relations. */
    int INDEX_TAB_RELATIONS = 1;

    /** Index de la table des coordonnées. */
    int INDEX_TAB_COORDONNEES = 0;

    /** Index de l'onglet des contrats. */
    int INDEX_TAB_CONTRATS = 3;

    /** Index de la table des coordonnées. */
    int INDEX_TAB_DOCUMENTS = 4;

    /**
     * Label constante.
     * @return la valeur.
     */
    String lNumEntreprise();

    /**
     * Label constante.
     * @return la valeur.
     */
    String lRaisonSociale();

    /**
     * Label constante.
     * @return la valeur.
     */
    String lNature();

    /**
     * Label constante.
     * @return la valeur.
     */
    String tbRaisonSociale();

    /**
     * Label constante.
     * @return la valeur.
     */
    String tbNumeroEntreprise();

    /**
     * Label constante.
     * @return la valeur.
     */
    String tbNumSiret();

    /**
     * Label constante.
     * @return la valeur.
     */
    String tbEnseigneCommercial();

    /**
     * Label constante.
     * @return la valeur.
     */
    String tbNAF();

    /**
     * Label constante.
     * @return la valeur.
     */
    String sbNature();

    /**
     * Label constante.
     * @return la valeur.
     */
    String libelleAgence();

    /**
     * Label constante.
     * @return la valeur.
     */
    String libelleCommercial();

    /**
     * Label constante.
     * @return la valeur.
     */
    String sbFormeJuridique();

    /**
     * Label constante.
     * @return la valeur.
     */
    String captionDescription();

    /**
     * Label constante.
     * @return la valeur.
     */
    String captionInformation();

    /**
     * Label constante.
     * @return la valeur.
     */
    String libelleTabCoordonnees();

    /**
     * Label constante.
     * @return la valeur.
     */
    String libelleTabRelations();

    /**
     * Label action.
     * @return le label action
     */
    String libelleTabActions();

    /**
     * Label contrat.
     * @return le label contrat.
     */
    String libelleTabContratsPM();

    /**
     * Label information.
     * @return le label information
     */
    String lbGinformation();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String captionCreation();

    /**
     * Label creation.
     * @return le label creation
     */
    String libelleCreation();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String libelleCreePar();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String libelleLe();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String actionsSur();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String nouvelleAction();
    /**
     * Libellé de l'onglet document.
     * @return le libellé
     */
    String libelleTabDocuments();

}

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
package com.square.client.gwt.client.controller;

import com.google.gwt.i18n.client.Constants;

/**
 * Constants used throughout the application.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface AppControllerConstants extends Constants {

    /** 100 pourcent. */
    String POURCENT_100 = "100%";

    /** 50 pourcent. */
    String POURCENT_50 = "50%";

    /** Nb d'enfants max. */
    int NB_ENFANTS_MAX = 10;

    /** Nombre de resultats max pour les suggestlisbox. */
    int SUGGESTLISBBOX_NB_RESULTATS_MAX = 15;

    /** Nombre de résultats max pour les suggestions de Communes / Codes Postaux. */
    int SUGGESTLISBBOX_NB_RESULTATS_MAX_CP = 50;

    /** Saut de ligne. */
    String SAUT_LIGNE = "<br/>";

    /** Nb d'onglets max ouverts. */
    int NB_MAX_ONGLETS_OUVERTS = 6;

    /** Timeout des messages de notification de l'application. */
    float NOTIFICATION_TIME_OUT = 1.5f;

    /** Paramètre dans l'url pour spécifier l'évènement. */
    String EVENT = "event";

    /** Séparateur des paramètres dans l'url. */
    String PARAM_SEPARATOR = "&";

    /** Séparateur entre le nom d'un paramètre de sa valeur dans l'url. */
    String PARAM_ASSIGNMENT = "=";

    /** Delai avant le chargement de la téléphonie. */
    int DELAI_TIMER_TELEPHONIE = 2000;

    /** Nombre de popup minimisable max. */
    int NB_MINIMIZE_POPUP_MAX = 1;

    /** Gestion des modes d'affichage des relations. */
    int MODE_RELATION_GRAPHIQUE = 0;

    /** Gestion des modes d'affichage des relations. */
    int MODE_RELATION_EDITION = 1;

    /** Largeur. */
    int LARGEUR_TABPANEL = 940;

    /** Largeur. */
    int LARGEUR_ONGLET = 63;

    /** Largeur. */
    String LARGEUR_TABPANEL_STRING = "950px";

    /**
     * Le nom de la page par défaut.
     * @return le nom de la page par défaut
     */
    String viewDefaultToken();

    /**
     * Liste des Onglet permanents.
     * @return la constante.
     */
    String libelleTabTableauDeBord();

    /**
     * Recupere la constante.
     * @return la constante.
     */
    String libelleTabActions();

    /**
     * Recupere la constante.
     * @return la constante.
     */
    String libelleTabPersonnes();

    /**
     * Recupere la constante.
     * @return la constante.
     */
    String libelleTabSocietes();

    /**
     * Recupere la constante.
     * @return la constante.
     */
    String libelleTabCampagnes();

    /**
     * Recupere la constante.
     * @return la constante.
     */
    String libelleTabAgenda();

    /**
     * Recupere la constante.
     * @return la constante.
     */
    String libelleTabRessources();

    /**
     * Message d'erreur sur le code splitting.
     * @return la constante.
     */
    String messageErreurCodeSplitting();

    /**
     * Gestion des ListBox d'enfants de l'application.
     * @return la constante.
     */
    int nbEnfantsMaxParFamille();

    /**
     * Recupere la constante.
     * @return la constante.
     */
    String libEnfant();

    /**
     * Recupere la constante.
     * @return la constante.
     */
    String libEnfants();

    /**
     * Recupere la constante.
     * @return la constante.
     */
    String libNoEnfants();

    /**
     * Recupere la constante.
     * @return la constante.
     */
    String libAucunConjoint();

    /**
     * Recupere la constante.
     * @return la constante.
     */
    String libUnConjoint();

    /**
     * Recupere la constante.
     * @return la constante.
     */
    String titrePartie1();

    /**
     * Recupere la constante.
     * @return la constante.
     */
    String titrePartie2();

    /**
     * Titre.
     * @return le label
     */
    String titreExportExcel();

    /**
     * Titre.
     * @return le label
     */
    String labelExportExcel();

    /**
     * Message d'erreur indiquant qu'il n'est pas possible de créer l'opportunité car la personne est décédée.
     * @return le message.
     */
    String erreurCreationOpportunitePersonneDecedee();

    /**
     * Message d'erreur indiquant une erreur lors du chargement de la personne physique avant d'ouvrir la fiche de cette personne.
     * @return le message d'erreur.
     */
    String messageErreurChargementPersonnePhysique();

    /**.
     * Récupère le nombre maximum de caractères à afficher pour le prénom
     * @return la valeur
     */
    int nbMaxCaracteresPrenom();

    /**.
     * Récupère le nombre maximum de caractères à afficher pour le nom
     * @return la valeur
     */
    int nbMaxCaracteresNom();

    /**
     * Message d'erreur indiquant une erreur car l'utilisateur essaye de créer une opportunité sur une personne dont un des membres
     * est de nature vivier ou bénéficiaire vivier.
     * @return le message d'erreur.
     */
    String erreurCreationOpportuniteFamilleVivier();
}

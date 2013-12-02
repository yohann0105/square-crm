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
/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.square.composant.tarificateur.square.client.content.i18n;

import com.google.gwt.i18n.client.Constants;

/**
 * Constants used throughout the application.
 */
public interface ComposantTarificateurConstants extends Constants {

    /** Cent pourcent. */
    String POURCENT_100 = "100%";

    /** 1 pourcent. */
    String POURCENT_1 = "1%";

    /** Cinquante pourcent. */
    String POURCENT_50 = "50%";

    /** Evenement de demande de décochage des autres produits. */
    String EVENT_DEMANDE_DECOCHER_AUTRES_PRODUITS = "DemandeDecocherAutresProduits";

    /** Evenement de fin de calcul de l'age d'un assure. */
    String EVENT_DEMANDE_VERIF_CONTRAINTES_VENTE = "DemandeVerifContraintesVente";

    /** Evenement de chargement de produit dans le selecteur. */
    String EVENT_CHARGER_PRODUIT_POUR_DEVIS = "chargerProduitPourDevis";

    /** Evenement de chargement de produit adherent dans le selecteur. */
    String EVENT_CHARGER_PRODUIT_ADHERENT_POUR_DEVIS = "chargerProduitAdherentPourDevis";

    /** Evenement de fin de calcul de l'age d'un assure. */
    String EVENT_OUVRIR_CHOIX_PRODUIT = "ouvrirChoixProduit";

    /** Evenement de lancement d'une transformation aia. */
    String EVENT_LANCER_TRANSFORMATION_AIA = "lancerTransformationAia";

    /** EVENT_OBJECT_ID_LIGNE. */
    String EVENT_OBJECT_ID_LIGNE = "idLigne";

    /** EVENT_OBJECT_TYPE_DEVIS. */
    String EVENT_OBJECT_TYPE_DEVIS = "typeDevis";

    /** EVENT_OBJECT_EXISTE_PROPOSITION. */
    String EVENT_OBJECT_EXISTE_PROPOSITION = "existeProposition";

    /** EVENT_OBJECT_ID_DEVIS. */
    String EVENT_OBJECT_ID_DEVIS = "idDevis";

    /** EVENT_OBJECT_PRODUIT_AIA. */
    String EVENT_OBJECT_PRODUIT_AIA = "produitAia";

    /** EVENT_OBJECT_GARANTIE_AIA. */
    String EVENT_OBJECT_GARANTIE_AIA = "garantieAia";

    /** OBJET_ID_PRODUIT. */
    String EVENT_OBJECT_ID_PRODUIT = "idProduit";

    /** Evenement de recalcul de l'age. */
    String EVENT_DEMANDE_CALCULER_AGE = "DemandeCalculerAge";

    /** Saut de ligne. */
    String SAUT_LIGNE = "<br />";

    /** Deux points. */
    String DEUX_POINTS = " : ";

    /** Espace. */
    String ESPACE = " ";

    /** Espace insécable HTML. */
    String ESPACE_HTML = "&nbsp;";

    /** Hauteur de la popup de selection. */
    int HAUTEUR_POPUP_SELECTION = 500;

    /** Largeur d'une error popup plus large. */
    int LARGEUR_LARGE_ERROR_POPUP = 500;

    /** Paramètre de l'identifiant du devis. */
    String PARAM_ID_PRODUIT = "idProduit";

    /** URL de la servlet de récupération de la grille de presta au format PDF. */
    String URL_SERVLET_GRILLE_PRESTA_PDF = "servlet/getGrillePrestaPDF";

    /** Séparateur d'identifiants. */
    String SEPARATOR_IDENTIFIANTS = ", ";

    /** URL d'impression de devis. */
    String URL_IMPRIMER_DEVIS = "servlet/imprimerDevisServlet";

    /** Paramètre pour l'impression de devis : l'identifiant de l'impression. */
    String PARAM_ID_IMPRESSION = "idImpression";

    /** Paramètre pour enregistrer les infos du devis avant l'impression. */
    String PARAM_ENREGISTREMENT_FINALITE_DEVIS = "enregistrementFinaliteDevis";

    /** Nombre de resultats max pour les suggestlisbox. */
    int SUGGESTLISBBOX_NB_RESULTATS_MAX = 15;

    /** Timeout des messages de notification de l'application. */
    float NOTIFICATION_TIME_OUT = 1.5f;

    /** Constante désignant un message à afficher.*/
    String ERROR_DATE_PARSING = "ERROR_DATE_PARSING";

    /** Constante désignant un message à afficher.*/
    String ERROR_DATE_INVALID = "ERROR_DATE_INVALID";

    /** Constante désignant un message à afficher.*/
    String ERROR_DATE_ABSENT = "ERROR_DATE_ABSENT";

    /** Constante désignant un message à afficher.*/
    String ERROR_USER_ABSENT = "ERROR_USER_ABSENT";

    /** Format de date.*/
    String DATE_FORMAT = "dd/MM/yyyy";

    /** Chaine utilisée dans les services.*/
    String MESSAGE_NPAI = "(NPAI)";

    /** Constante désignant un message à afficher.*/
	String ERROR_NOTIFICATION_LOGIN_ABSENT = "ERROR_NOTIFICATION_LOGIN_ABSENT";

    /** Constante désignant un message à afficher.*/
	String ERROR_NOTIFICATION_USER_ABSENT = "ERROR_NOTIFICATION_USER_ABSENT";

    /** Constante désignant un message à afficher.*/
	String ERROR_SAVE_ATTACHED_PIECE = "ERROR_SAVE_ATTACHED_PIECE";

    /** Constante désignant un message à afficher.*/
	String ERROR_GENERATION_FICHIER = "ERROR_GENERATION_FICHIER";

	 /** Constante désignant un message à afficher.*/
	String ERROR_LIGNE_DEVIS_PRINT_ABSENT = "ERROR_LIGNE_DEVIS_PRINT_ABSENT";

	 /** Constante désignant un message à afficher.*/
	String ERROR_MODELE_DEVIS_ABSENT = "ERROR_MODELE_DEVIS_ABSENT";

    /**
     * Titre de la popup de choix de produit.
     * @return le titre de la popup
     */
    String titrePopupChoixProduit();

    /**
     * Titre de la popup de selection de produit.
     * @return le titre de la popup
     */
    String titrePopupSelectionProduit();

    /**
     * Titre de la popup de modification des infos d'adhésion.
     * @return le titre de la popup
     */
    String titrePopupAdhesion();

    /**
     * Texte chargement.
     * @return le texte associé.
     */
    String chargementInfosAdhesionEnCours();

    /**
     * Message affiché durant la mise à jour des informations d'adhésion.
     * @return le texte associé.
     */
    String updateInfosAdhesionEnCours();

    /**
     * Texte chargement.
     * @return le texte associé.
     */
    String chargementCaissesEnCours();

    /**
     * Titre de la popup permettant de recherche d'opportunité par numéro de transaction.
     * @return le texte associé
     */
    String titrePopupRechercheTransaction();

    /**
     * Le texte.
     * @return le texte
     */
    String miseAJourHoportunite();

    /**
     * Le texte.
     * @return le texte
     */
    String succesImpression();

    /**
     * Le texte.
     * @return le texte
     */
    String succesMiseJourInfoAdhesion();

    /**
     * Le texte.
     * @return le texte
     */
    String succesTransformationAia();

    /**
     * Le texte.
     * @return le texte
     */
    String succesTransfert();

    /**
     * Le texte.
     * @return le texte
     */
    String affichageActions();

    /**
     * Le texte.
     * @return le texte
     */
    String demandeCalculAge();

    /**
     * Le texte.
     * @return le texte
     */
    String demandeVerificationContraintesVente();

    /**
     * Le texte.
     * @return le texte
     */
    String demandeDecochageAutresProduits();

    /**
     * Le texte.
     * @return le texte
     */
	String errorDateParsing();

    /**
     * Le texte.
     * @return le texte
     */
	String errorDateInvalid();

    /**
     * Le texte.
     * @return le texte
     */
	String errorDateAbsent();

    /**
     * Le texte.
     * @return le texte
     */
	String errorNotificationLoginAbsent();

    /**
     * Le texte.
     * @return le texte
     */
	String errorNotificationUserAbsent();

    /**
     * Le texte.
     * @return le texte
     */
	String errorSaveAttachedPiece();

    /**
     * Le texte.
     * @return le texte
     */
	String errorGenerationFichier();

    /**
     * Le texte.
     * @return le texte
     */
	String errorUserAbsent();

    /**
     * Le texte.
     * @return le texte
     */
	String errorModeleDevisAbsent();

    /**
     * Le texte.
     * @return le texte
     */
	String errorLigneDevisAbsent();
}

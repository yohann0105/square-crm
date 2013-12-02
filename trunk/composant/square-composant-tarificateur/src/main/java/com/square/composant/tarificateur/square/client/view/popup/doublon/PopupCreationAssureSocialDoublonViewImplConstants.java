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
package com.square.composant.tarificateur.square.client.view.popup.doublon;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes de la vue PopupCreationPersonneDoublonViewImpl.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface PopupCreationAssureSocialDoublonViewImplConstants extends Constants {

    /** Largeur de la popup. */
    String LARGEUR_POPUP = "900px";

    /**
     * Le titre de la popup.
     * @return le titre.
     */
    String titrePopup();

    /**
     * La fin de la phrase d'entête.
     * @return la phrase.
     */
    String finPhraseEnTete();

    /**
     * Le phrase de vérification.
     * @return la phrase.
     */
    String phraseVerifier();

    /**
     * Le titre de la colonne "Nom".
     * @return le titre.
     */
    String enTeteColonneNom();

    /**
     * Le titre de la colonne "Prénom".
     * @return le titre.
     */
    String enTeteColonnePrenom();

    /**
     * Le titre de la colonne "Date de naissance".
     * @return le titre.
     */
    String enTeteColonneDateNaissance();

    /**
     * Le titre de la colonne "Adresse".
     * @return le titre.
     */
    String enTeteColonneAdresse();

    /**
     * Le titre de la colonne "Code postal".
     * @return le titre.
     */
    String enTeteColonneCodePostal();

    /**
     * Le titre de la colonne "Ville".
     * @return le titre.
     */
    String enTeteColonneVille();

    /**
     * Le titre de la colonne "Composition familiale".
     * @return le titre.
     */
    String enTeteColonneCompoFamiliale();

    /**
     * Le titre de la colonne "Sélection".
     * @return le titre.
     */
    String enTeteSelection();

    /**
     * Le texte sur le bouton pour créer une nouvelle personne.
     * @return le texte.
     */
    String btnCreerNouvellePersonne();

    /**
     * Le texte sur le bouton pour rattacher la personne créée à la personne sélectionnée.
     * @return le texte.
     */
    String btnRattacherPersonneExistante();

    /**
     * Texte sur le bouton annuler.
     * @return le texte associé.
     */
    String btnAnnuler();
}

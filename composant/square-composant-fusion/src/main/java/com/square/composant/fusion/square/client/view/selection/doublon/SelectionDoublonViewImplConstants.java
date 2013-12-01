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
package com.square.composant.fusion.square.client.view.selection.doublon;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes de la vue de sélection de doublons.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface SelectionDoublonViewImplConstants extends Constants {

    /** Nombre d'elements par page. */
    int NB_ELEMENT_PAR_PAGE = 20;

    /**
     * Texte sur la bouton pour valider la sélection des 2 doublons.
     * @return le texte sur le bouton.
     */
    String btnValider();

    /**
     * Texte sur le bouton pour vider la sélection des doublons.
     * @return le texte sur le bouton.
     */
    String btnViderSelection();

    /**
     * Titre de la colonne pour le numéro de client.
     * @return le titre de la colonne.
     */
    String headerNumClient();

    /**
     * Titre de la colonne pour la date de création.
     * @return le titre de la colonne.
     */
    String headerDateCreation();

    /**
     * Titre de la colonne pour le nom.
     * @return le titre de la colonne.
     */
    String headerNom();

    /**
     * Titre de la colonne pour le prénom.
     * @return le titre de la colonne.
     */
    String headerPrenom();

    /**
     * Titre de la colonne pour la date de naissance.
     * @return le titre de la colonne.
     */
    String headerDateNaissance();

    /**
     * Titre de la colonne pour la nature.
     * @return le titre de la colonne.
     */
    String headerNature();

    /**
     * Titre de la colonne pour le code postal.
     * @return le titre de la colonne.
     */
    String headerCodePostal();

    /**
     * Titre de la colonne pour la commune.
     * @return le titre de la colonne.
     */
    String headerCommune();

    /**
     * Titre de la colonne pour la sélectionné.
     * @return le titre de la colonne.
     */
    String headerSelectionne();

    /**
     * Oui.
     * @return oui.
     */
    String oui();

    /**
     * Non.
     * @return non.
     */
    String non();
}

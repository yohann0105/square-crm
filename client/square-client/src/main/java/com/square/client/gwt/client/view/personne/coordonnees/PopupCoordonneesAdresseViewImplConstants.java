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
package com.square.client.gwt.client.view.personne.coordonnees;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes de la vue PopupCoordonneesAdresseViewImpl.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface PopupCoordonneesAdresseViewImplConstants extends Constants {

    /** Largeur. */
    String LARGEUR_POPUP = "720px";

    /**
     * Texte sur le bouton pour annuler.
     * @return le texte.
     */
    String annuler();

    /**
     * Texte sur le bouton pour enregistrer.
     * @return le texte.
     */
    String enregistrer();

    /**
     * Texte sur le bouton pour reduire.
     * @return le texte.
     */
    String reduire();

    /**
     * Titre de la popup d'ajout d'adresse.
     * @return le titre.
     */
    String titrePopupAjoutAdresse();

    /**
     * Libellé "oui".
     * @return le texte
     */
    String libelleOui();

    /**
     * Libellé "non".
     * @return le texte
     */
    String libelleNon();
}

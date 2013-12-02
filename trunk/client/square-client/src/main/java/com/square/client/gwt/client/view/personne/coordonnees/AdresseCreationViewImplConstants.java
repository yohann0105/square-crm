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
 * Interface des constantes de la vue AdresseCreationViewImpl.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface AdresseCreationViewImplConstants extends Constants {

    /** Largeur. */
    String LARGEUR_COL_LIBELLE_0 = "20%";

    /** Largeur. */
    String LARGEUR_COL_CHAMP_1 = "34%";

    /** Largeur. */
    String LARGEUR_COL_LIBELLE_2 = "12%";

    /** Largeur. */
    String LARGEUR_COL_CHAMP_3 = "34%";

    /**Longueur max de 38 pour les TextBox. */
    int MAX_LENGTH_38 = 38;

    /**Longueur max de 5 pour les TextBox. */
    int MAX_LENGTH_5 = 5;

    /**
     * Texte du label action.
     * @return le texte.
     */
    String lAction();

    /**
     * Texte du label pour passer l'adresse en secondaire.
     * @return le texte.
     */
    String passerSecondaire();

    /**
     * Texte du label pour renseigner la date de fin.
     * @return le texte.
     */
    String renseignerDateFin();

    /**
     * Texte.
     * @return le texte.
     */
    String typeAdresse();

    /**
     * Label.
     * @return le label
     */
    String type();

    /**
     * Label.
     * @return le label
     */
    String dateFin();

    /**
     * Label.
     * @return le label
     */
    String complementNom();

    /**
     * Label.
     * @return le label
     */
    String complementAdresse();

    /**
     * Label.
     * @return le label
     */
    String autresComplements();

    /**
     * Label.
     * @return le label
     */
    String pays();

    /**
     * Label.
     * @return le label
     */
    String codePostal();

    /**
     * Label.
     * @return le label
     */
    String natureVoie();

    /**
     * Label.
     * @return le label
     */
    String ville();

    /**
     * Label.
     * @return le label
     */
    String numeroVoie();

    /**
     * Label.
     * @return le label
     */
    String adresse();

    /**
     * Label.
     * @return le label
     */
    String departement();
}

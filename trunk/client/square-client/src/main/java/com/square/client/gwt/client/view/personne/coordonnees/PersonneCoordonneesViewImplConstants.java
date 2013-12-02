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
 * Interface des constants de la vue PersonnePhysiqueCoordonnees.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface PersonneCoordonneesViewImplConstants extends Constants {

    /** Format du téléphone par défaut. */
    String FORMAT_TELEPHONE_DEFAUT = "NN NN NN NN NN";

    /** Largeur col. */
    String LARGEUR_COL_CHAMP = "34%";

    /** Largeur col. */
    String LARGEUR_COL_LIBELLE = "16%";

    /** Caractère "espace". */
    String ESPACE = " ";

    /** Séparateur des différents champs de le title des images des drapeaux des pays. */
    String SEPARATEUR_TITLE_FLAG = " - ";

    /** Longueur max pour la TextBox des emails. */
    int MAX_LENGTH_TB_EMAIL = 80;

    /**
     * Label.
     * @return le label
     */
    String titreInformations();

    /**
     * Label.
     * @return le label
     */
    String titreAdresse();

    /**
     * Label.
     * @return le label
     */
    String telephone();

    /**
     * Label.
     * @return le label
     */
    String email();

    /**
     * Label.
     * @return le label
     */
    String enregistrer();

    /**
     * Label.
     * @return le label
     */
    String ajouter();

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

    /**
     * Label.
     * @return le label
     */
    String type();

    /**
     * Label.
     * @return le label
     */
    String NPAI();

    /**
     * Label.
     * @return le label
     */
    String NPAICheckbox();

    /**
     * Label.
     * @return le label
     */
    String dateDebut();

    /**
     * Label.
     * @return le label
     */
    String dateFin();

    /**
     * Label.
     * @return le label
     */
    String oui();

    /**
     * Label.
     * @return le label
     */
    String non();

    /**
     * Label.
     * @return le label
     */
    String nonRenseigne();

    /**
     * Label.
     * @return le label
     */
    String majCoordonnees();

    /**
     * Label.
     * @return le label
     */
    String recuperationCoordonnees();

    /**
     * Label.
     * @return le label
     */
    String annuler();

    /**
     * Partie du title du drapeau : le pays.
     * @return le texte.
     */
    String titlePays();

    /**
     * Partie du title du drapeau : l'indicatif téléphonique.
     * @return le texte.
     */
    String titleIndicatifTel();

    /**
     * Partie du title du drapeau : le format du numérode téléphone.
     * @return le texte.
     */
    String titleFormatNumTel();

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

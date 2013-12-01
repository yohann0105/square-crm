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
package com.square.client.gwt.client.view.personne.physique.moteur.recherche;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants de la vue PersonnePhysiqueMoteurRecherche.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface PersonnePhysiqueMoteurRechercheViewImplConstants extends Constants {

    /** Nombre d'elements par page. */
    int NB_ELEMENT_PAR_PAGE = 20;

    /** Largeur colonne. */
    String LARGEUR_COL_CHAMP = "23%";

    /** Largeur colonne. */
    String LARGEUR_COL1_LIBELLE = "11%";

    /** Largeur colonne. */
    String LARGEUR_COL2_LIBELLE = "9%";

    /** Largeur colonne. */
    String LARGEUR_COL3_LIBELLE = "11%";

    /** Hauteur scroll panel list box multiple. */
    String HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE = "300px";

    /**
     * Label.
     * @return le label
     */
    String numeroClient();

    /**
     * Label.
     * @return le label
     */
    String nom();

    /**
     * Label.
     * @return le label
     */
    String nomJF();

    /**
     * Label.
     * @return le label
     */
    String prenom();

    /**
     * Label.
     * @return le label
     */
    String numeroRO();

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
    String dateNaissance();

    /**
     * Label.
     * @return le label
     */
    String codePostal();

    /**
     * Label.
     * @return le label
     */
    String nature();

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
    String rechercher();

    /**
     * Label.
     * @return le label
     */
    String headerNumeroClient();

    /**
     * Label.
     * @return le label
     */
    String headerNom();

    /**
     * Label.
     * @return le label
     */
    String headerPrenom();

    /**
     * Label.
     * @return le label
     */
    String headerDateNaissance();

    /**
     * Label.
     * @return le label
     */
    String headerNature();

    /**
     * Label.
     * @return le label
     */
    String headerSegment();

    /**
     * Label.
     * @return le label
     */
    String headerCodePostal();

    /**
     * Label.
     * @return le label
     */
    String headerVille();

    /**
     * Label.
     * @return le label
     */
    String headerDoublon();

    /**
     * Label.
     * @return le label
     */
    String headerAgence();

    /**
     * Label.
     * @return le label
     */
    String headerCommerciale();

    /**
     * Label.
     * @return le label
     */
    String identification();

    /**
     * Label.
     * @return le label
     */
    String complement();

    /**
     * Titre de la popup de selection multiple.
     * @return le titre
     */
    String titrePopupSelection();

    /**
     * Libellé informations.
     * @return le libellé.
     */
    String informations();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String libelleAgences();

    /**
     * Accesseur libellé.
     * @return le libellé.
     */
    String libelleCommerciaux();

    /**
     * Label.
     * @return le label
     */
    String effacer();

    /**
     * Label pour un doublon.
     * @return le label
     */
    String lDoublon();

    /**.
     * Le nombre maximum de caractères affichés pour le nom
     * @return la valeur
     */
    int maxCaracteresNom();

    /**.
     * Le nombre maximum de caractères affichés pour le prenom
     * @return la valeur
     */
    int maxCaracteresPrenom();

    /**
     * Properties slb agence.
     * @return la chaine
     */
	String SlbPropertiesAgence();
}

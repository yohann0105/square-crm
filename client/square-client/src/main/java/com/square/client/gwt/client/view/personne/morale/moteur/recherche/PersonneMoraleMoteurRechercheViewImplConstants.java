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
package com.square.client.gwt.client.view.personne.morale.moteur.recherche;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants de la vue PersonneMoraleMoteurRechercheViewImplConstants.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface PersonneMoraleMoteurRechercheViewImplConstants extends Constants {

    /** Nombre d'elements par page. */
    int NB_ELEMENT_PAR_PAGE = 20;

    /** Largeur colonne. */
    String LARGEUR_COL1_LIBELLE = "12%";

    /** Largeur colonne. */
    String LARGEUR_COL2_LIBELLE = "10%";

    /** Largeur colonne. */
    String LARGEUR_COL3_LIBELLE = "9%";

    /** Largeur colonne. */
    String LARGEUR_COL_CHAMP = "23%";

    /** Hauteur scroll panel list box multiple. */
    String HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE = "300px";

    /**
     * Label .
     * @return le label.
     */
    String raisonSociale();

    /**
     * Label .
     * @return le label.
     */
    String numEntreprise();

    /**
     * Label .
     * @return le label.
     */
    String departement();

    /**
     * Label .
     * @return le label.
     */
    String formeJuridique();

    /**
     * Label .
     * @return le label.
     */
    String nature();

    /**
     * Label .
     * @return le label.
     */
    String codePostal();

    /**
     * Label .
     * @return le label.
     */
    String ville();

    /**
     * Label .
     * @return le label.
     */
    String agence();

    /**
     * Label .
     * @return le label.
     */
    String commercial();

    /**
     * Label.
     * @return le label
     */
    String rechercher();

    /**
     * Label.
     * @return le label
     */
    String headerNumeroEntreprise();

    /**
     * Label.
     * @return le label
     */
    String headerRaisonSociale();

    /**
     * Label.
     * @return le label
     */
    String headerNature();

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
    String identification();

    /**
     * Label.
     * @return le label
     */
    String information();

    /**
     * Champ numéro entreprise.
     * @return le nom du champ
     */
    String fieldNumeroEntreprise();

    /**
     * Champ raison sociale.
     * @return le nom du champ
     */
    String fieldRaisonSociale();

    /**
     * Champ nature.
     * @return le nom du champ
     */
    String fieldNature();

    /**
     * Champ code postal.
     * @return le nom du champ
     */
    String fieldCodePostal();

    /**
     * Champ ville.
     * @return le nom du champ
     */
    String fieldVille();

    /**
     * Titre de la popup de selection multiple.
     * @return le titre
     */
    String titrePopupSelection();

    /**
     * Bouton effacer.
     * @return le titre
     */
    String effacer();

    /**
     * Titre de la Pop-up.
     * @return le titre
     */
    String responsable();

    /**
     * Properties slb Agence.
     * @return la chaine "Agence"
     */
	String agenceSlbProperties();

}

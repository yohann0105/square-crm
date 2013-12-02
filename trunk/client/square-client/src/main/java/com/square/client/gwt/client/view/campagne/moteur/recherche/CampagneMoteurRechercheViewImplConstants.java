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
package com.square.client.gwt.client.view.campagne.moteur.recherche;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des messages pour le moteur de recherche des campagnes.
 * @author cblanchard - SCUB
 */
public interface CampagneMoteurRechercheViewImplConstants extends Constants {

    /** Nombre d'élement par page du tableau de résultat. */
    int NB_ELEMENT_PAR_PAGE = 20;

    /** Largeur colonne. */
    String LARGEUR_COL_CHAMP = "38%";

    /** Largeur colonne. */
    String LARGEUR_COL_LIBELLE = "12%";

    /** Hauteur scroll panel list box multiple. */
    String HAUTEUR_SCROLLPANEL_LISTBOX_MULTIPLE = "300px";

    /**
     * Renvoi le libelle du code.
     * @return le libelle du code
     */
    String code();

    /**
     * Renvoi le libelle du libelle.
     * @return le libelle du libelle
     */
    String libelle();

    /**
     * Renvoi le libelle du type.
     * @return le libelle du type
     */
    String type();

    /**
     * Renvoi le libelle du statut.
     * @return le libelle du statut
     */
    String statut();

    /**
     * Renvoi le libelle date début.
     * @return le libelle date début
     */
    String libelleDateDebut();

    /**
     * Renvoi le libelle date de fin.
     * @return le libelle date de fin
     */
    String libelleDateFin();

    /**
     * Renvoi le libelle créateur.
     * @return le libellé du créateur
     */
    String createur();

    /**
     * Renvoi le libelle recherche.
     * @return le libelle recherche
     */
    String recherche();

    /**
     * Renvoi l'entête Code.
     * @return Code.
     */
    String headerCode();

    /**
     * Renvoi l'entête Libellé.
     * @return Libellé
     */
    String headerLibelle();

    /**
     * Renvoi l'entête du statut.
     * @return Statut
     */
    String headerStatut();

    /**
     * Renvoi l'entête de la date de début.
     * @return Date de début
     */
    String headerDateDebut();

    /**
     * Renvoi l'entête du créateur.
     * @return créateur
     */
    String headerCreateur();

    /**
     * Champ code.
     * @return le nom du champ code
     */
    String fieldCodeCampagne();

    /**
     * Champ Libellé.
     * @return le nom du champ libelle
     */
    String fieldLibelleCampagne();

    /**
     * Champ Statut.
     * @return le nom du champ statut
     */
    String fieldStatut();

    /**
     * Champ date de début.
     * @return le nom du champ date de début
     */
    String fieldDateDebut();

    /**
     * Champ créateur.
     * @return créateur
     */
    String fieldCreateur();

    /**
     * Renvoi le libelle du bouton rechercher.
     * @return le libelle du bouton rechercher
     */
    String libelleBtnRechercher();

    /**
     * Message pour le chargement de la campagne.
     * @return le message pour le chargement de la campagne
     */
    String chargementCampagne();

    /**
     * Titre de la popup de selection multiple.
     * @return le titre de la selection multiple
     */
    String titrePopUpSelection();

    /**
     * Bouton effacer.
     * @return le titre
     */
    String effacer();

    /**
     * Libelle.
     * @return le libelle
     */
    String au();

}

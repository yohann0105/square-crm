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
package com.square.client.gwt.client.view.ressource.moteur.recherche;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants de la vue RessourceMoteurRecherche.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public interface RessourceMoteurRechercheViewImplConstants extends Constants {

    /** Nombre d'elements par page. */
    int NB_ELEMENT_PAR_PAGE = 20;

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
    String prenom();

    /**
     * Label.
     * @return le label
     */
    String fonction();

    /**
     * Label.
     * @return le label
     */
    String agence();

    /**
     * Label.
     * @return le label
     */
    String service();

    /**
     * Label.
     * @return le label
     */
    String etat();

    /**
     * Label.
     * @return le label
     */
    String rechercher();

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
    String headerFonction();

    /**
     * Label.
     * @return le label
     */
    String headerService();

    /**
     * Label.
     * @return le label
     */
    String headerEtat();

    /**
     * Label.
     * @return le label
     */
    String headerAgence();

    /**
     * Label.
     * @return le label
     */
    String recherche();

    /**
     * Champ nom.
     * @return le nom du champ
     */
    String fieldNom();

    /**
     * Champ prénom.
     * @return le nom du champ
     */
    String fieldPrenom();

    /**
     * Champ agence.
     * @return le champ
     */
    String fieldAgence();

    /**
     * Champ fonction.
     * @return le champ
     */
    String fieldFonction();

    /**
     * Champ service.
     * @return le champ
     */
    String fieldService();

    /**
     * Champ etat.
     * @return le champ
     */
    String fieldEtat();

    /**
     * Titre de la popup de selection multiple.
     * @return le titre
     */
    String titrePopupSelection();

    /**
     * Label.
     * @return le label
     */
    String effacer();

    /**
     * Titre du menu contextuel.
     * @return le titre.
     */
    String titreMenuContextuel();

    /**
     * Texte sur le bouton pour modifier les quotas.
     * @return le texte.
     */
    String modifierQuotas();
}

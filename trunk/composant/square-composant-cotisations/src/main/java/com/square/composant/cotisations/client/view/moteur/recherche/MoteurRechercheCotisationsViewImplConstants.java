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
package com.square.composant.cotisations.client.view.moteur.recherche;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants de la vue MoteurRecherche.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface MoteurRechercheCotisationsViewImplConstants extends Constants {

    /** Nombre d'elements par page. */
    int NB_ELEMENT_PAR_PAGE = 10;

    /** Largeur d'une textbox de montant. */
    String LARGEUR_TB_MONTANT = "65px";

    /**
     * Label.
     * @return le label
     */
    String simuler();

    /**
     * Label.
     * @return le label
     */
    String initRecherche();

    /**
     * Label.
     * @return le label
     */
    String rechercher();

    /**
     * Label.
     * @return le label
     */
    String rechercheEnCours();

    /**
     * Label.
     * @return le label
     */
    String blocRechercher();

    /**
     * Label.
     * @return le label
     */
    String contrats();

    /**
     * Label.
     * @return le label
     */
    String datePeriode();

    /**
     * Label.
     * @return le label
     */
    String modesPaiement();

    /**
     * Label.
     * @return le label
     */
    String montant();

    /**
     * Label.
     * @return le label
     */
    String separateur();

    /**
     * Label.
     * @return le label
     */
    String situation();

    /**
     * Label.
     * @return le label
     */
    String soldeGlobalActuel();

    /**
     * Label.
     * @return le label
     */
    String rechercherEnCours();

    /**
     * Label.
     * @return le label
     */
    String titreSimulation();
}

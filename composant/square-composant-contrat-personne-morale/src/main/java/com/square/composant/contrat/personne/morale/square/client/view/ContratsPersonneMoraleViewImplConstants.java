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
package com.square.composant.contrat.personne.morale.square.client.view;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes de la vue ContratsPersonneMoraleViewImpl.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface ContratsPersonneMoraleViewImplConstants extends Constants {

    /** Espace. */
    String ESPACE = " ";

    /** Largeur de la PieChart. */
    int LARGEUR_CHART = 170;

    /** Hauteur de la PieChart. */
    int HAUTEUR_CHART = 170;

    /** Couleur de fond des statistiques. */
    String COULEUR_FOND_STATISTIQUE = "#E3F3FC";

    /**
     * Titre du bloc synthèse.
     * @return le titre.
     */
    String titreBlocSynthese();

    /**
     * Libellé du statut.
     * @return le libellé.
     */
    String libelleStatut();

    /**
     * Libellé de la date de première mutualisation.
     * @return le libellé.
     */
    String libellePremiereMutualisation();

    /**
     * Libellé de la fidélité.
     * @return le libellé.
     */
    String libelleFidelite();

    /**
     * Libellé de la gestion du contrat.
     * @return le libellé.
     */
    String libelleGestionContrat();

    /**
     * Libellé du gestionnaire.
     * @return le libellé.
     */
    String libelleGestionnaire();

    /**
     * Titre du bloc de dernière radiation.
     * @return le titre.
     */
    String titreBlocDerniereRadiation();

    /**
     * Libellé de la date de dernière radiation.
     * @return le libellé.
     */
    String libelleDateDerniereRadiation();

    /**
     * Libellé du motif de dernière radiation.
     * @return le motif.
     */
    String libelleMotifDerniereRadiation();

    /**
     * Libellé de l'entête "Population" du tableau des populations.
     * @return le libellé.
     */
    String libelleEntetePopulation();

    /**
     * Libellé de l'entête "Effectif" du tableau des populations.
     * @return le libellé.
     */
    String libelleEnteteEffectif();

    /**
     * Libellé de la fidélité "année".
     * @return le libellé.
     */
    String libelleFideliteAnnee();

    /**
     * Libellé de la fidélité "années".
     * @return le libellé.
     */
    String libelleFideliteAnnees();

    /**
     * Libellé "et" pour la fidélié.
     * @return le libellé.
     */
    String libelleFideliteEt();

    /**
     * Libellé de la fidélité "mois".
     * @return le libellé.
     */
    String libelleFideliteMois();

    /**
     * Titre pour le camembert des populations.
     * @return le titre.
     */
    String titrePopulation();
}

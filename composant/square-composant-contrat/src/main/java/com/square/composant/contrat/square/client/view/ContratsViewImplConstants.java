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
package com.square.composant.contrat.square.client.view;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constantes de la vue des Contrats.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface ContratsViewImplConstants extends Constants {

    /** Largeur. */
    String LARGEUR_SCROLL_PANEL_SYNTHESE = "880px";

    /** Largeur. */
    String LARGEUR_SCROLL_PANEL_CONTRAT = "880px";

    /** Cent pourcent. */
    String POURCENT_100 = "100%";

    /** Largeur des Charts. */
    int LARGEUR_CHART = 170;

    /** Hauteur des Charts. */
    int HAUTEUR_CHART = 170;

    /** Couleur de la réserve banco consommée. */
    String COULEUR_RESERVE_BANCO_CONSOMMEE = "#abc200";

    /** Couleur de la réserve banco restante. */
    String COULEUR_RESERVE_BANCO_RESTANTE = "#0057d8";

    /** Couleur de fond des statistiques. */
    String COULEUR_FOND_STATISTIQUE = "#E3F3FC";

    /** URL de la servlet de récupération de la grille de presta au format PDF. */
    String URL_SERVLET_GRILLE_PRESTA_PDF = "servlet/getGrillePrestaPDFContrat";

    /** Paramètre de l'identifiant du produit pour la récupération de grille de prestation. */
    String PARAM_ID_PRODUIT = "idProduit";

    /** Paramètre s'il s'agit du collectif ou non pour la récupération de grille de prestation. */
    String PARAM_IS_COLLECTIF = "isCollectif";

    /** Espace. */
    String ESPACE = " ";

    /**
     * Libellé du titre du bloc Synthèse.
     * @return le libellé
     */
    String titreBlocSynthese();

    /**
     * Libellé du statut du contrat.
     * @return le libellé
     */
    String libelleStatut();

    /**
     * Libellé Première mutualisation.
     * @return le libellé
     */
    String libellePremiereMutualisation();

    /**
     * Libellé Fidelité.
     * @return le libellé
     */
    String libelleFidelite();

    /**
     * Libellé Télétransmission.
     * @return le libellé
     */
    String libelleTeletransmission();

    /**
     * Libellé Population.
     * @return le libellé
     */
    String libellePopulation();

    /**
     * Libellé Gestion du contrat.
     * @return le libellé
     */
    String libelleGestionContrat();

    /**
     * Libellé Gestionnaire.
     * @return le libellé
     */
    String libelleGestionnaire();

    /**
     * Titre du bloc "Dernière radiation".
     * @return le libellé
     */
    String titreBlocDerniereRadiation();

    /**
     * Libellé Date de dernière radiation.
     * @return le libellé
     */
    String libelleDateDerniereRadiation();

    /**
     * Libellé Motif de dernière radiation.
     * @return le libellé
     */
    String libelleMotifDerniereRadiation();

    /**
     * Entête de la colonne "Personne" du tableau des garanties.
     * @return le libellé
     */
    String enteteTableauGarantiesColonnePersonne();

    /**
     * Entête de la colonne "Rôle" du tableau des garanties.
     * @return le libellé
     */
    String enteteTableauGarantiesColonneRole();

    /**
     * Titre du bloc "Contrat".
     * @return le libellé
     */
    String titreBlocContrat();

    /**
     * Libellé du chargement de la liste des contrats.
     * @return le libellé
     */
    String chargementListeContrats();

    /**
     * Libellé Fidélité "Années".
     * @return le libellé
     */
    String libelleFideliteAnnees();

    /**
     * Libellé Fidélité "Année".
     * @return le libellé
     */
    String libelleFideliteAnnee();

    /**
     * Libellé Fidélité "Mois".
     * @return le libellé
     */
    String libelleFideliteMois();

    /**
     * Libellé Fidélité "Et".
     * @return le libellé
     */
    String libelleFideliteEt();

    /**
     * Libellé Télétransmission "Oui".
     * @return le libellé
     */
    String libelleTeletransmissionOui();

    /**
     * Libellé Télétransmission "Non".
     * @return le libellé
     */
    String libelleTeletransmissionNon();

    /**
     * Libellé de la légende "Description" de la jauge Banco.
     * @return le libellé
     */
    String libelleLegendeJaugeBancoDescription();

    /**
     * Libellé de la légende "Pourcentage" de la jauge Banco.
     * @return le libellé
     */
    String libelleLegendeJaugeBancoPourcentage();

    /**
     * Libellé de la légende "Consommé" de la jauge Banco.
     * @return le libellé
     */
    String libelleLegendeJaugeBancoConsomme();

    /**
     * Libellé de la légende "Restant" de la jauge Banco.
     * @return le libellé
     */
    String libelleLegendeJaugeBancoRestant();

    /**
     * Libellé du title pour voir la grille de prestation d'une garantie.
     * @return le libellé
     */
    String libelleVoirGrillePresta();

    /**
     * Libellé de la légende "Année" pour les stats P/C.
     * @return le libellé
     */
    String libelleLegendeStatPCAnnee();

    /**
     * Titre de la statistique P/C.
     * @return le libellé
     */
    String titreStatsPrestationCotisation();

    /**.
     * Limite le nombre de caractère affichés par le nom et le prénom
     * @return
     */
    int maxCaracteresNomPrenom();
}

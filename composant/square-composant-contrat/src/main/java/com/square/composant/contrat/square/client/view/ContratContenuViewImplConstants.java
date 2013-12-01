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
 * Interface des constantes de la vue du contenu d'un contrat.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface ContratContenuViewImplConstants extends Constants {

    /** Hauteur. */
    String HAUTEUR_CAPTIONPANEL_PAIEMENT = "95px";

    /**
     * Libellé Apporteur.
     * @return le libellé
     */
    String libelleApporteur();

    /**
     * Libellé Gestionnaire.
     * @return le libellé
     */
    String libelleGestionnaire();

    /**
     * Titre Paiement Prestation .
     * @return le libellé
     */
    String titrePaiementPrestation();

    /**
     * Titre Paiement Cotisation .
     * @return le libellé
     */
    String titrePaiementCotisation();

    /**
     * Libellé Mode paiement.
     * @return le libellé
     */
    String libelleModePaiement();

    /**
     * Libellé Période Paiement.
     * @return le libellé
     */
    String libellePeriodePaiement();

    /**
     * Libellé Jour Paiement.
     * @return le libellé
     */
    String libelleJourPaiement();

    /**
     * Libellé Souscripteur Payeur.
     * @return le libellé
     */
    String libelleSouscripteurPayeur();

    /**
     * Libellé du chargement d'un contrat.
     * @return le libellé
     */
    String chargementContrat();

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
     * Libellé du title pour voir la grille de prestation d'une garantie.
     * @return le libellé
     */
    String libelleVoirGrillePresta();

    /**
     * Titre des ajustements.
     * @return le libellé
     */
    String titreAjustements();

    /**
     * Titre des garanties.
     * @return le libellé
     */
    String titreGaranties();

    /**
     * Titre de la colonne "Référence" du tableau des ajustements.
     * @return le libellé
     */
    String titreColonneAjustementReference();

    /**
     * Titre de la colonne "Référence" du tableau des ajustements.
     * @return le libellé
     */
    String titreColonneAjustementLibelle();

    /**
     * Titre de la colonne "Référence" du tableau des ajustements.
     * @return le libellé
     */
    String titreColonneAjustementTarif();

    /**
     * Titre de la colonne "Référence" du tableau des ajustements.
     * @return le libellé
     */
    String titreColonneAjustementDateDebut();

    /**
     * Titre de la colonne "Référence" du tableau des ajustements.
     * @return le libellé
     */
    String titreColonneAjustementDateFin();

    /**
     * Légende pour la cellule contenant les infos sur la garantie d'un bénéficiaire.
     * @return la légende
     */
    String legendeInfosGarantieBeneficiaire();
    
    /**
     * Nombre maximum de caractères affichés pour le nom et prénom
     * @return la valeur
     */
    int maxCaracteresNomPrenom();
}

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
 * Constantes de la vue ContratPersonneMoraleContenuViewImpl.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface ContratPersonneMoraleContenuViewImplConstants extends Constants {

    /**
     * Titre du panel des informations.
     * @return le titre.
     */
    String titrePanelInfos();

    /**
     * Titre du panel des informations de paiement de cotisation.
     * @return le titre.
     */
    String titrePanelInfosPaiementCotisation();

    /**
     * Label du segment.
     * @return le label.
     */
    String labelSegment();

    /**
     * Label du type de gestion.
     * @return le label.
     */
    String labelTypeGestion();

    /**
     * Label du type de souscription.
     * @return le label.
     */
    String labelTypeSouscription();

    /**
     * Label du produit de gestion.
     * @return le label.
     */
    String labelProduitGestion();

    /**
     * Label de l'apporteur.
     * @return le label.
     */
    String labelApporteur();

    /**
     * Label du nombre d'adhérents.
     * @return le label.
     */
    String labelNbAdherents();

    /**
     * Label du nombre de bénéficiaires.
     * @return le label.
     */
    String labelNbBeneficiaires();

    /**
     * Label du mode de paiement.
     * @return le label.
     */
    String labelModePaiement();

    /**
     * Label de la période de paiement.
     * @return le label.
     */
    String labelPeriodePaiement();

    /**
     * Label du jour de paiement.
     * @return le label.
     */
    String labelJourPaiement();

    /**
     * Label du type d'échéance.
     * @return le label.
     */
    String labelTypeEcheance();

    /**
     * Label du RIB.
     * @return le label.
     */
    String labelRIB();

    /**
     * Libellé du title pour voir une grille de prestation.
     * @return le libellé.
     */
    String libelleVoirGrillePresta();

    /**
     * Libellé de la colonne population du tableau récapitulatif des populations.
     * @return le libellé.
     */
    String colonnePopulation();
}

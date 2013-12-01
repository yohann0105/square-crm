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
package com.square.composant.fusion.square.client.composants;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes du composant pour afficher le résumé de la fusion.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface ResumeFusionConstants extends Constants {

    /**
     * Titre du résumé de la fusion.
     * @return le titre.
     */
    String titreResumeFusion();

    /**
     * Titre de la colonne pour les champs.
     * @return le titre.
     */
    String titreColonneChamp();

    /**
     * Titre de la colonne pour la valeur.
     * @return le titre.
     */
    String titreColonneValeur();

    /**
     * Label indiquant qu'il n'y a aucun champ à fusionner.
     * @return le label.
     */
    String aucunChampAFusionner();
    /**
     * Label du numéro ro.
     * @return le label.
     */
    String numRo();

    /**
     * Label de la civilite.
     * @return le label.
     */
    String civilite();

    /**
     * Label du nom.
     * @return le label.
     */
    String nom();

    /**
     * Label du nom de jeune fille.
     * @return le label.
     */
    String nomJeuneFille();

    /**
     * Label du prénom.
     * @return le label.
     */
    String prenom();

    /**
     * Label de la date de naissance.
     * @return le label.
     */
    String dateNaissance();

    /**
     * Label de la date de décés.
     * @return le label.
     */
    String dateDeces();

    /**
     * Label du champ "décédé".
     * @return le label.
     */
    String decede();

    /**
     * Label de la nature de la personne.
     * @return le label.
     */
    String naturePersonne();

    /**
     * Label de la situation familiale.
     * @return le label.
     */
    String situationFam();

    /**
     * Label pour la profession.
     * @return le label.
     */
    String profession();

    /**
     * Label pour le statut.
     * @return le label.
     */
    String statut();

    /**
     * Label pour la caisse.
     * @return le label.
     */
    String caisse();

    /**
     * Label pour la caisse du régime.
     * @return le label.
     */
    String caisseRegime();

    /**
     * Label pour le csp.
     * @return le label.
     */
    String csp();

    /**
     * Label pour le segment.
     * @return le label.
     */
    String segment();

    /**
     * Label pour le réseau de vent.
     * @return le label.
     */
    String reseauVente();

    /**
     * Label pour le commercial.
     * @return le label.
     */
    String commercial();

    /**
     * Label pour l'agence.
     * @return le label.
     */
    String agence();

    /**
     * Label pour l'adresse principale.
     * @return le label.
     */
    String adressePrincipale();

    /**
     * Label pour le téléphone fixe.
     * @return le label.
     */
    String telFixe();

    /**
     * Label pour le téléphone portable.
     * @return le label.
     */
    String telPortable();

    /**
     * Label pour l'email.
     * @return le label.
     */
    String email();

    /**
     * Label pour "Oui".
     * @return le label.
     */
    String oui();

    /**
     * Label pour "Non".
     * @return le label.
     */
    String non();
}

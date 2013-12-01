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
package com.square.client.gwt.client.presenter.personne.physique;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes du presenter pour la création d'une personne physique.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface PersonnePhysiqueCreationPresenterConstants extends Constants {

    /**
     * Titre de la colonne des pays.
     * @return le titre.
     */
    String titreColonnePays();

    /**
     * Titre de la colonne de l'indicatif téléphonique.
     * @return le titre.
     */
    String titreColonneIndicatifTelephonique();

    /**
     * Titre de la colonne du format téléphonique.
     * @return le titre.
     */
    String titreColonneFormatTelephonique();

    /**
     * Titre de la colonne du drapeau.
     * @return le titre.
     */
    String titreColonneDrapeau();

    /**
     * Message d'erreur lors de la recherche de doublons à la saisie à la volée.
     * @return le message.
     */
    String erreurRechercheDoublons();

    /**
     * Message de notification lorsqu'une personne physique est créée avec succès.
     * @return le texte associé
     */
    String notificationPersonneCreee();

    /**
     * Chemin du champ de la date de naissance de la personne.
     * @return le chemin
     */
    String champDateNaissancePersonne();

    /**
     * Chemin du champ de la date de naissance d'un bénéficiaire.
     * @return le chemin
     */
    String champDateNaissanceBeneficiaire();

    /**
     * Message d'erreur sur la date de naissance de la personne.
     * @return le message
     */
    String erreurDateNaissancePersonneInvalide();

    /**
     * Message d'erreur sur la date de naissance du conjoint.
     * @return le message
     */
    String erreurDateNaissanceConjointInvalide();

    /**
     * Message d'erreur sur la date de naissance d'un enfant.
     * @return le message
     */
    String erreurDateNaissanceEnfantInvalide();
}

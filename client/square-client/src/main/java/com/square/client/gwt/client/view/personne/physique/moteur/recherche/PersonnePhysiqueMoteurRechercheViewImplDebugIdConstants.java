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
package com.square.client.gwt.client.view.personne.physique.moteur.recherche;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants de debugId de la vue PersonnePhysiqueMoteurRecherche.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface PersonnePhysiqueMoteurRechercheViewImplDebugIdConstants extends Constants {

    /**
     * DebugId pour le bouton de recherche.
     * @return le debugId.
     */
    String debugIdBtnRecherche();

    /**
     * DebugId pour la TextBox du numéro de client.
     * @return le debugId.
     */
    String debugIdTbNumClient();

    /**
     * DebugId pour la TextBox du nom.
     * @return le debugId.
     */
    String debugIdTbNom();

    /**
     * DebugId pour la TextBox du nom de jeune fille.
     * @return le debugId.
     */
    String debugIdTbNomJF();

    /**
     * DebugId pour la TextBox du prénom.
     * @return le debugId.
     */
    String debugIdTbPrenom();

    /**
     * DebugId pour la TextBox du numéro RO.
     * @return le debugId.
     */
    String debugIdTbNumRO();

    /**
     * DebugId pour la TextBox de la date de naissance.
     * @return le debugId.
     */
    String debugIdCdbDateNaissance();

    /**
     * DebugId pour la SuugestBox de la nature.
     * @return le debugId.
     */
    String debugIdSlbNature();

    /**
     * DebugId pour la TextBox du nom.
     * @return le debugId.
     */
    String debugIdTbTelephone();

    /**
     * DebugId pour la TextBox de l'email.
     * @return le debugId.
     */
    String debugIdTbEmail();

    /**
     * DebugId pour la TextBox du numéro de voie.
     * @return le debugId.
     */
    String debugIdTbNumeroVoie();

    /**
     * DebugId pour la TextBox de l'adresse.
     * @return le debugId.
     */
    String debugIdTbAdresse();

    /**
     * DebugId pour la SuggestBox du code postal.
     * @return le debugId.
     */
    String debugIdSlbCodePostal();

    /**
     * DebugId pour la SuggestBox de la nature de voie.
     * @return le debugId.
     */
    String debugIdSlbNatureVoie();

    /**
     * DebugId pour la SuggestBox de la ville.
     * @return le debugId.
     */
    String debugIdSlbVille();

    /**
     * DebugId pour la SuggestBox des agences.
     * @return le debugId.
     */
    String debugIdSlbcAgences();

    /**
     * DebugId pour la SuggestBox des commerciaux.
     * @return le debugId.
     */
    String debugIdSlbcCommerciaux();

    /**
     * DebugId pour la table des personnes résultant de la recherche.
     * @return le debugId.
     */
    String debugIdTablePersonnes();

    /**
     * DebugId pour le bouton effacer.
     * @return le debugId.
     */
    String debugIdBtnEffacerRecherche();
}

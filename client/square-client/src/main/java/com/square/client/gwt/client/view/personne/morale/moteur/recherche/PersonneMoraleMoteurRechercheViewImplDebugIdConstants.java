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
package com.square.client.gwt.client.view.personne.morale.moteur.recherche;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants de la vue PersonneMoraleMoteurRecherche.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface PersonneMoraleMoteurRechercheViewImplDebugIdConstants extends Constants {

    /**
     * DebugId pour le bouton de recherche.
     * @return le debugId.
     */
    String debugIdBtnRechercher();

    /**
     * DebugId pour le bouton effacer la recherche.
     * @return le debugId.
     */
    String debugIdBtnEffacerRecherche();

    /**
     * DebugId pour la TextBox de la raison sociale.
     * @return le debugId.
     */
    String debugIdTbRaisonSociale();

    /**
     * DebugId pour la TextBox du numéro d'entreprise.
     * @return le debugId.
     */
    String debugIdTbNumEntreprise();

    /**
     * DebugId pour la SuggestListBox du dépratement.
     * @return le debugId.
     */
    String debugIdSlbDepartement();

    /**
     * DebugId pour la SuggestListBox de la forme juridique.
     * @return le debugId.
     */
    String debugIdSlbFormeJuridique();

    /**
     * DebugId pour la SuggestListBox de la forme juridique.
     * @return le debugId.
     */
    String debugIdSlbNature();

    /**
     * DebugId pour la SuggestListBox du code postal.
     * @return le debugId.
     */
    String debugIdSlbCodepostal();

    /**
     * DebugId pour la SuggestListBox de la ville.
     * @return le debugId.
     */
    String debugIdSlbVille();

    /**
     * DebugId pour la SuggestListBox de l'agence.
     * @return le debugId.
     */
    String debugIdSlbAgence();

    /**
     * DebugId pour la SuggestListBox du commercial.
     * @return le debugId.
     */
    String debugIdSlbCommercial();

    /**
     * DebugId pour la table des résultats de la recherche des personnes morales.
     * @return le debugId.
     */
    String debugIdRemotePagingTablePersonnesMorale();

}

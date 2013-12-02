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
package com.square.client.gwt.client.view.personne.morale.gestion;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants debugId de la vue GestionPersonneMorale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface GestionPersonneMoraleViewImplDebugIdConstants extends Constants {

    /**
     * DebugId pour le label du numéro d'entreprise.
     * @return le debugId.
     */
    String debugIdLNumEntreprise();

    /**
     * DebugId pour le label de la raison sociale.
     * @return le debugId.
     */
    String debugIdLRaisonSociale();

    /**
     * DebugId pour le label de la nature
     * @return le debugId.
     */
    String debugIdLNature();

    /**
     * DebugId pour le label du menu contextuel.
     * @return le debugId.
     */
    String debugIdLbContextMenu();

    /**
     * DebugId pour le bouton d'ajout d'action.
     * @return le debugId.
     */
    String debugIdBtActionContextAddAction();

    /**
     * DebugId pour la TextBox de la raison sociale.
     * @return le debugId.
     */
    String debugIdTbRaisonSociale();

    /**
     * DebugId pour la SuggestBox de la nature.
     * @return le debugId.
     */
    String debugIdSbNature();

    /**
     * DebugId pour la TextBox du numéro siret.
     * @return le debugId.
     */
    String debugIdTbNumSiret();

    /**
     * DebugId pour la TextBox du numéro d'entreprise.
     * @return le debugId.
     */
    String debugIdTbNumeroEntreprise();

    /**
     * DebugId pour la SuggestBox de la forme juridique.
     * @return le debugId.
     */
    String debugIdSbFormeJuridique();

    /**
     * DebugId pour la TextBox NAF.
     * @return le debugId.
     */
    String debugIdTbNAF();

    /**
     * DebugId pour la TextBox de l'enseigne commerciale.
     * @return le debugId.
     */
    String debugIdTbEnseigneCommercial();

    /**
     * DebugId pour la SuggestBox de l'agence.
     * @return le debugId.
     */
    String debugIdSlbAgence();

    /**
     * DebugId pour la SuggestBox du commercial.
     * @return le debugId.
     */
    String debugIdSlbCommercial();

    /**
     * DebugId pour le label du créateur.
     * @return le debugId.
     */
    String debugIdLCreateur();

    /**
     * DebugId pour le label de la date de création.
     * @return le debugId.
     */
    String debugIdLDateCreation();
}

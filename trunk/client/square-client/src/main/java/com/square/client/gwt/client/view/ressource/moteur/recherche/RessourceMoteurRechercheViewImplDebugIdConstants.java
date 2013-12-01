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
package com.square.client.gwt.client.view.ressource.moteur.recherche;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants de debugId de la vue PersonnePhysiqueMoteurRecherche.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public interface RessourceMoteurRechercheViewImplDebugIdConstants extends Constants {

    /**
     * DebugId pour le bouton de recherche.
     * @return le debugId.
     */
    String debugIdBtnRecherche();

    /**
     * DebugId pour la TextBox du nom.
     * @return le debugId.
     */
    String debugIdTbNom();

    /**
     * DebugId pour la TextBox du prénom.
     * @return le debugId.
     */
    String debugIdTbPrenom();

    /**
     * DebugId pour la SuugestBox de la fonction.
     * @return le debugId.
     */
    String debugIdSlbFonction();

    /**
     * DebugId pour la SuugestBox de l'agence.
     * @return le debugId.
     */
    String debugIdSlbAgence();

    /**
     * DebugId pour la SuugestBox du service.
     * @return le debugId.
     */
    String debugIdSlbService();

    /**
     * DebugId pour la SuugestBox de l'etat.
     * @return le debugId.
     */
    String debugIdSlbEtat();

    /**
     * DebugId pour la table des ressources résultant de la recherche.
     * @return le debugId.
     */
    String debugIdTableRessources();

    /**
     * DebugId pour le bouton effacer.
     * @return le debugId.
     */
    String debugIdBtnEffacerRecherche();

    /**
     * DebugId pour le bouton de modification des quotas.
     * @return le debugId.
     */
    String debugIdBtnModifierQuotas();
}

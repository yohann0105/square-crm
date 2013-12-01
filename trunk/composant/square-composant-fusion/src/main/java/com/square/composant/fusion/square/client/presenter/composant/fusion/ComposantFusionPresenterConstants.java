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
package com.square.composant.fusion.square.client.presenter.composant.fusion;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes de l'application.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface ComposantFusionPresenterConstants extends Constants {

    /** 100 pourcent. */
    String POURCENT_100 = "100%";

    /** 75 pourcent. */
    String POURCENT_75 = "75%";

    /** Largeur. */
    String LARGEUR_CONTENEUR = "980px";

    /**
     * Onglet pour la sélection de doublons.
     * @return le titre.
     */
    String ongletSelectionDoublons();

    /**
     * Onglet pour la fusion.
     * @return le titre.
     */
    String ongletFusionner();

    /**
     * Titre de l'onglet pour la sélection de doublons.
     * @return le titre.
     */
    String libelleOngletSelectionDoublons();

    /**
     * Titre de l'onglet pour la fusion.
     * @return le titre.
     */
    String libelleOngletFusionner();

    /**
     * Message indiquant une erreur lors du chargement de la page.
     * @return le message.
     */
    String messageErreurChargementPage();
}

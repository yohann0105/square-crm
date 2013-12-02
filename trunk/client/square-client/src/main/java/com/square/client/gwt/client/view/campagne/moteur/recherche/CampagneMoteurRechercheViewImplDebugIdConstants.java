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
package com.square.client.gwt.client.view.campagne.moteur.recherche;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des messages de debug pour le moteur de recherche des campagnes.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface CampagneMoteurRechercheViewImplDebugIdConstants extends Constants {

    /**
     * DebugId pour le bouton de recherche.
     * @return le debugId.
     */
    String debugIdBtnRechercher();

    /**
     * DebugId pour le bouton de effacer la recherche.
     * @return le debugId.
     */
    String debugIdBtnEffacerRecherche();

    /**
     * DebugId .
     * @return le debugId.
     */
    String debugIdTbCode();

    /**
     * DebugId .
     * @return le debugId.
     */
    String debugIdTbLibelle();

    /**
     * DebugId .
     * @return le debugId.
     */
    String debugIdSlbType();

    /**
     * DebugId .
     * @return le debugId.
     */
    String debugIdSlbStatut();

    /**
     * DebugId .
     * @return le debugId.
     */
    String debugIdSlbCreateur();

    /**
     * DebugId .
     * @return le debugId.
     */
    String debugIdCdbDateInfDateDebut();

    /**
     * DebugId .
     * @return le debugId.
     */
    String debugIdCdbDateSupDateDebut();

    /**
     * DebugId .
     * @return le debugId.
     */
    String debugIdCdbDateInfDateFin();

    /**
     * DebugId .
     * @return le debugId.
     */
    String debugIdCdbDateSupDateFin();

    /**
     * DebugId pour la table des résultats de la recherche des personnes morales.
     * @return le debugId.
     */
    String debugIdRemotePagingTableCampagne();

}

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
package com.square.client.gwt.client.view.action.transfert;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constantes.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface ActionTransfertViewImplConstants extends Constants {

    /** Largeur. */
    String LARGEUR_POPUP = "450px";

    /** Largeur colonne. */
    String LARGEUR_COL_LIBELLE = "25%";

    /**
     * Titre de la popup.
     * @return le titre de la popup
     */
    String popupTitle();

    /**
     * Libelle du bouton transferer.
     * @return le libelle du bouton transferer
     */
    String transferer();

    /**
     * Libelle du bouton annuler.
     * @return le libelle du bouton annuler
     */
    String annuler();

    /**
     * Label .
     * @return le label.
     */
    String agence();

    /**
     * Label .
     * @return le label.
     */
    String commercial();

    /**
     * Label .
     * @return le label.
     */
    String cibleTransfert();

    /**
     * Label .
     * @return le label.
     */
    String transfertActions();
}

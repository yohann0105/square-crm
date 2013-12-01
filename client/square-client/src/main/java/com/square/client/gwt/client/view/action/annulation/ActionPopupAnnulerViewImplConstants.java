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
package com.square.client.gwt.client.view.action.annulation;

import com.google.gwt.i18n.client.Constants;

public interface ActionPopupAnnulerViewImplConstants extends Constants {

    /**
     * Renvoi le titre de la popup
     * @return le titre de la popup
     */
    public String popupTitle();

    /**
     * Renvoi le libelle de l'annonce.
     * @return le libelle de l'annonce
     */
    public String libelleAnnonce();

    /**
     * Renvoi le libelle du bouton oui.
     * @return le libelle oui.
     */
    public String oui();

    /**
     * Renvoi le libelle du bouton non.
     * @return le libelle non
     */
    public String non();

}

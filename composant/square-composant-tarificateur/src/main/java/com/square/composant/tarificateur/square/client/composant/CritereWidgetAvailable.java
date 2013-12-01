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
package com.square.composant.tarificateur.square.client.composant;

import com.google.gwt.user.client.ui.Widget;

/**
 * Interface pour qu'un widget soit disponible pour etre un critereWidget.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface CritereWidgetAvailable {

    /**
     * Definit la visibilité.
     * @param visible visible
     */
    void setVisible(boolean visible);

    /**
     * Recupere la visibilite.
     * @return la visibilite
     */
    boolean isVisible();

    /**
     * Definit l'activation.
     * @param enabled enabled
     */
    void setEnabled(boolean enabled);

    /**
     * Recupere l'activation.
     * @return l'activation
     */
    boolean isEnabled();

    /**
     * Recupere la valeur du widget sous forme de string.
     * @return la valeur
     */
    String getValeurCritereWidget();

    /**
     * Définit la valeur du widget sous forme de string.
     * @param valeur la valeur
     */
    void setValeurCritereWidget(String valeur);

    /**
     * Retourne le widget.
     * @return le widget
     */
    Widget getCritereWidget();
}

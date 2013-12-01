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
package com.square.composant.tarificateur.square.client.view.popup;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;

/**
 * Popup permettant d'afficher un title.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 *
 */
public class PopupTitle extends PopupPanel {

    /**
     * Constructeur.
     * @param message le message à afficher dans la popup.
     */
    public PopupTitle(String message) {
        super(true);

        final HTML lMessage = new HTML(message);
        lMessage.addStyleName(ComposantTarificateur.RESOURCES.css().messagePopupTitle());

        this.setWidget(lMessage);
        this.addStyleName(ComposantTarificateur.RESOURCES.css().popupTitle());
    }

    /**
     * Affiche la popup.
     * @param xPosition position horizontale.
     * @param yPosition position verticale.
     */
    public void show(int xPosition, int yPosition) {
        this.setPopupPosition(xPosition, yPosition);
        this.show();
    }
}

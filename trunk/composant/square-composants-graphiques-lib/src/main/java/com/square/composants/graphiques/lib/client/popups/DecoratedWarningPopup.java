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
package com.square.composants.graphiques.lib.client.popups;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;
import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composants.graphiques.lib.client.ComposantsGraphiquesConstants;
import com.square.composants.graphiques.lib.client.bundle.SquareLibResources;

/**
 * Popup gérant l'affichage de warning.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class DecoratedWarningPopup extends Popup {

	 private ComposantsGraphiquesConstants constants = GWT.create(ComposantsGraphiquesConstants.class);
    /**
     * Constructeur.
     * @param message le message.
     */
    public DecoratedWarningPopup(String message) {
        super("", false, true, true);

        // on cree le panel vertical qui contient tout le contenu
        final VerticalPanel contenu = new VerticalPanel();
        contenu.setWidth("100%");

        // on construit le panel du message
        final HorizontalPanel panelMessage = new HorizontalPanel();
        final Image icone = new Image(SquareLibResources.INSTANCE.warning());
        final HTML htmlMessage = new HTML(message);
        htmlMessage.addStyleName(SquareLibResources.INSTANCE.css().messageWarningPopup());
        panelMessage.add(icone);
        panelMessage.add(htmlMessage);

        final DecoratedButton buttonOK = new DecoratedButton(constants.validation());
        buttonOK.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hide();
            }
        });

        contenu.add(panelMessage);
        contenu.add(buttonOK);
        contenu.setCellHorizontalAlignment(buttonOK, HasAlignment.ALIGN_RIGHT);
        this.setWidget(contenu);
        this.setStyleName(SquareLibResources.INSTANCE.css().decoratedWarningPopup());
    }
}

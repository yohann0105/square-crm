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

import org.scub.foundation.framework.gwt.module.client.util.popup.Popup;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composants.graphiques.lib.client.bundle.SquareLibResources;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;

/**
 * Popup gérant l'affichage d'alertes (info).
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public final class DecoratedInfoPopup extends Popup {

    /** Configuration. */
    private PopupInfoConfiguration config;

    /**
     * Constructeur.
     * @param config configuration de la popup.
     */
    public DecoratedInfoPopup(final PopupInfoConfiguration config) {
        super("", false, true, config.isGlassPanel());
        this.config = config;

        // on cree le panel vertical qui contient tout le contenu
        final VerticalPanel panel = new VerticalPanel();
        panel.setWidth("100%");

        // on construit le panel du message
        final HTML htmlMessage = new HTML(this.config.getMessage());
        htmlMessage.addStyleName(SquareLibResources.INSTANCE.css().messageInfoPopup());
        panel.add(htmlMessage);
        panel.setCellVerticalAlignment(htmlMessage, HasAlignment.ALIGN_MIDDLE);

        this.setWidget(panel);
        this.setStyleName(SquareLibResources.INSTANCE.css().decoratedInfoPopup());
    }

    /** Affichage de la popup. */
    public void show() {
        super.show();
        final Timer timer = new Timer() {
            @Override
            public void run() {
                // On fait disparaitre la popup à la fin du timer.
                hide();
                if (config.getCallback() != null) {
                    config.getCallback().onResult(true);
                }
            }
        };
        // Lancement du timer
        timer.schedule((int) (config.getTimeOut() * PopupConstants.MILLISECONDS));
    }
}

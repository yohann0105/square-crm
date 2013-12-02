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
package com.square.composant.ged.square.client.composant.panel;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.square.composant.ged.square.client.bundle.ComposantGedBundle;

/**
 * Panel permettant d'afficher un message accompagné d'une icône adaptée au type de message.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class MessagePanel extends Composite {

    private Label lMessage;
    private Image image;

    /**
     * Les différents types de messages pris en charge par le panel.
     *
     */
    public enum MessageType {
        /** Message de type chargement. */
        LOADING,

        /** Message de type erreur. */
        ERROR,

        /** Message de type informatif. */
        INFO;
    }

    /**
     * Constructeur par défaut du panel de chargement.
     */
    public MessagePanel() {
        final HorizontalPanel container = new HorizontalPanel();
        container.setSpacing(5);
        container.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        container.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        container.setWidth("100%");

        image = new Image();
        lMessage = new Label();

        container.add(image);
        container.add(lMessage);

        this.initWidget(container);
        // Le widget n'est pas visible par défaut
        this.setVisible(false);
    }

    /**
     * Affiche le panel de chargement.
     * @param message le message de chargement affiché
     * @param messageType spécifie le type de message affiché pour déterminer l'image à afficher
     */
    public void showMessagePanel(String message, MessageType messageType) {
        // On détermine quelle image adaptée au type de message il faut afficher
        // et quel CSS il faut appliquer
        switch (messageType) {
        case LOADING:
            image.setResource(ComposantGedBundle.INSTANCE.loadingAnimation());
            this.setStyleName(ComposantGedBundle.INSTANCE.css().loadingMessagePanel());
            break;
        case ERROR:
            image.setResource(ComposantGedBundle.INSTANCE.errorIcon());
            this.setStyleName(ComposantGedBundle.INSTANCE.css().errorMessagePanel());
            break;
        case INFO:
            image.setResource(ComposantGedBundle.INSTANCE.infoIcon());
            this.setStyleName(ComposantGedBundle.INSTANCE.css().infoMessagePanel());
            break;
        default:
            image.setResource(ComposantGedBundle.INSTANCE.infoIcon());
            this.setStyleName(ComposantGedBundle.INSTANCE.css().infoMessagePanel());
            break;
        }
        lMessage.setText(message);
        this.setVisible(true);
    }

    /**
     * Cache le panel de chargement.
     */
    public void hideMessagePanel() {
        this.setVisible(false);
    }
}

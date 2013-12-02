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
package com.square.composant.ged.square.client.composant.popup;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Popup gérant la demande de confirmation. <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.confirmPopup</li>
 * </ul>
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ConfirmPopup extends Popup {
    private HTML message;

    private DecoratedButton buttonOK;

    private DecoratedButton buttonNOK;

    /** Résultat cancel. */
    public static final int RESULT_CANCEL = -1;

    /** Résultat ok. */
    public static final int RESULT_OK = 1;

    /** Résultat nok. */
    public static final int RESULT_NOK = 0;

    /**
     * PopupConstants.
     */
    public interface PopupConstants extends Constants {

        /** Longueur max du message pour redimensionner la popup. */
        int MESSAGE_LENGTH_MAX_RESIZE = 80;

        /** Style de la popup. */
        String CLASS_POPUP_CONFIRM = "confirmPopup";

        /** Largeur de l'image. */
        String LARGEUR_IMAGE = "32px";

        /**
         * Title of the pop up.
         * @return label
         */
        String titleLabel();

        /**
         * Default message.
         * @return message
         */
        String defaultMessage();

        /**
         * Label of Ok button.
         * @return label
         */
        String buttonOkLabel();

        /**
         * Label of NOk button.
         * @return label
         */
        String buttonNOkLabel();

        /**
         * Label of Cancel button.
         * @return label
         */
        String buttonCancelLabel();
    }

    /**
     * PopupImages.
     */
    public interface PopupImages extends ClientBundle {
        /**
         * Icone la popup de confirm.
         * @return l'image
         */
        @Source("com/square/composant/ged/square/public/themes/default/images/confirmPopup.png")
        ImageResource confirmPopup();
    }

    private static final PopupConstants POPUP_CONSTANTS = (PopupConstants) GWT.create(PopupConstants.class);

    private static final PopupImages POPUP_IMAGES = (PopupImages) GWT.create(PopupImages.class);

    /**
     * Constructeur de la popup.
     */
    public ConfirmPopup() {
        super(POPUP_CONSTANTS.titleLabel(), false, true, true);
        addStyleName(PopupConstants.CLASS_POPUP_CONFIRM);

        // on cree le panel vertical qui contient tout le contenu
        final VerticalPanel panel = new VerticalPanel();
        panel.setWidth(POURCENT_100);
        // on cree le panel horizontal qui contient le message et l'icone
        final HorizontalPanel panelMessage = new HorizontalPanel();
        panelMessage.setWidth(POURCENT_100);
        panelMessage.setSpacing(10);
        // on cree le panel horizontal des boutons
        final HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.setSpacing(5);

        final Image imagePopup = new Image(POPUP_IMAGES.confirmPopup());
        // on construit le panel du message
        message = new HTML();
        panelMessage.add(imagePopup);
        panelMessage.add(message);
        panelMessage.setCellWidth(imagePopup, PopupConstants.LARGEUR_IMAGE);
        panelMessage.setCellHorizontalAlignment(imagePopup, HasAlignment.ALIGN_LEFT);
        panelMessage.setCellVerticalAlignment(message, HasAlignment.ALIGN_MIDDLE);

        // on ajoute le bouton ok
        buttonOK = new DecoratedButton(POPUP_CONSTANTS.buttonOkLabel());
        buttonOK.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (getCallback() != null) {
                    getCallback().onResult(RESULT_OK);
                }
                hide();
            }
        });
        buttonPanel.add(buttonOK);
        buttonNOK = new DecoratedButton(POPUP_CONSTANTS.buttonNOkLabel());
        buttonNOK.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (getCallback() != null) {
                    getCallback().onResult(RESULT_NOK);
                }
                hide();
            }
        });
        buttonPanel.add(buttonNOK);
        final DecoratedButton buttonCancel = new DecoratedButton(POPUP_CONSTANTS.buttonCancelLabel());
        buttonCancel.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (getCallback() != null) {
                    getCallback().onResult(RESULT_CANCEL);
                }
                hide();
            }
        });
        buttonPanel.add(buttonCancel);
        // on ajoute les panels dans le panel principal
        panel.add(panelMessage);
        panel.add(buttonPanel);
        panel.setCellHorizontalAlignment(buttonPanel, HasAlignment.ALIGN_RIGHT);
        setWidget(panel, 0);
    }

    /**
     * Affiche la popup a partir d'un message.
     * @param newMmessage le message
     */
    public void afficher(String newMmessage) {
        if (newMmessage.length() > PopupConstants.MESSAGE_LENGTH_MAX_RESIZE) {
            setWidth(Popup.POPUP_WIDTH_LARGE + "px");
        }
        this.message.setHTML(newMmessage);
        this.show();
        DeferredCommand.addCommand(new Command() {
            public void execute() {
                buttonOK.setFocus(true);
            }
        });
    }

}

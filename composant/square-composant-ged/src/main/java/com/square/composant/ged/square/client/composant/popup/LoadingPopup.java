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

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Popup gérant l'affichage d'un message de chargement.
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.loadingPopup</li>
 * </ul>
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class LoadingPopup extends Popup {

    /**
     * PopupConstants.
     */
    public interface PopupConstants extends Constants {

        /** Longueur max du message pour redimensionner la popup. */
        int MESSAGE_LENGTH_MAX_RESIZE = 80;

        /** Source de l'image. */
        String SRC_IMAGE = "images/ajax-loader.gif";

        /** Class de la popup. */
        String CLASS_POPUP_LOADING = "loadingPopup";

        /** Class de la popup. */
        String CLASS_CONTENU_POPUP_LOADING = "loadingPopupContenu";

        /** Class du glass panel de la popup. */
        String CLASS_GLASS_PANEL_LOADING_POPUP = "glassPanelLoadingPopup";

        /** Millisecondes. */
        int MILLISECONDS = 1000;

        /** Taille du padding. */
        int SPACING = 5;

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
    }

    private static final PopupConstants POPUP_CONSTANTS = (PopupConstants) GWT.create(PopupConstants.class);

    /** Instance de la classe. */
    private static LoadingPopup instance;

    /** Message. */
    private SimplePanel contenu;

    /**
     * Compteur permettant de savoir le nombre de fois que l'info est affichée.<br>
     * Permet d'enlever la popup seulement une fois que tous les lancements ont été arrêtés
     */
    private static int compteurLancements;

    /**
     * The constants used in this Component.
     */
    public interface PageConstants extends Constants {
    }

    /**
     * Retourne la valeur de instance.
     * @return la valeur de instance
     */
    public static LoadingPopup getInstance() {
        if (instance == null) {
            instance = new LoadingPopup();
        }
        return instance;
    }

    /** Constructeur privé. */
    private LoadingPopup() {
        super(POPUP_CONSTANTS.titleLabel(), false, true, true);
        setGlassPanelStyleName(PopupConstants.CLASS_GLASS_PANEL_LOADING_POPUP);
        addStyleName(PopupConstants.CLASS_POPUP_LOADING);

        contenu = new SimplePanel();
        contenu.addStyleName(PopupConstants.CLASS_CONTENU_POPUP_LOADING);

        final HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setWidth(Popup.POURCENT_100);
        hPanel.setSpacing(PopupConstants.SPACING);
        hPanel.add(contenu);
        hPanel.setCellVerticalAlignment(contenu, HasVerticalAlignment.ALIGN_MIDDLE);
        hPanel.setCellHorizontalAlignment(contenu, HasHorizontalAlignment.ALIGN_CENTER);

        this.setWidget(hPanel);
    }

    /**
     * Affiche la popup d'info de chargement.
     * @param config la configuration de la popup
     */
    public static void afficher(LoadingPopupConfiguration config) {
        if (config.getMessage() == null) {
            config.setMessage(POPUP_CONSTANTS.defaultMessage());
        }
        // on tronque la largeur si message trop long
        if (config.getMessage().length() > PopupConstants.MESSAGE_LENGTH_MAX_RESIZE) {
            config.setWidth(Popup.POPUP_WIDTH_LARGE);
        }

        // On incrémente le compteur de lancements
        compteurLancements++;

        if (config.getExtraContent() != null) {
            final VerticalPanel panel = new VerticalPanel();
            panel.setWidth("100%");
            panel.add(new HTML(config.getMessage()));
            panel.add(config.getExtraContent());
            getInstance().setContenu(panel);
        } else {
            getInstance().setContenu(new HTML(config.getMessage()));
        }
        if (config.getWidth() != null) {
            getInstance().setWidth(config.getWidth() + "px");
        }

        getInstance().show();

        if (config.getSecondes() != null) {
            final Timer timer = new Timer() {
                public void run() {
                    // Arrêt de la popup
                    stop();
                }
            };
            // Lancement du timer
            timer.schedule(config.getSecondes() * PopupConstants.MILLISECONDS);
        }
    }

    /**
     * Stoppe l'affichage de la popup de chargement.
     */
    public static void stop() {
        // On stoppe seulement si la popup n'est pas déjà stoppée
        if (compteurLancements > 0) {
            // On décrémente le compteur
            compteurLancements--;

            // On stoppe l'affichage seulement s'il est nul
            if (compteurLancements == 0) {
                getInstance().hide();
            }
        }
    }

    /**
     * Stoppe complètement l'affichage de la popup de chargement.
     */
    public static void stopAll() {
        compteurLancements = 0;
        getInstance().hide();
    }

    private void setContenu(Widget contenu) {
        this.contenu.setWidget(contenu);
    }

}

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

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.Widget;

/**
 * Les parametres de contruction d'une popup de chargement.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class LoadingPopupConfiguration implements IsSerializable {

    /** Message. */
    private String message;

    /** Secondes du timer. */
    private Integer secondes;

    /** Width. */
    private Integer width;

    /** Flag pour afficher un glassPanel. */
    private boolean glassPanel = true;

    /** Widget complémentaire qui sera ajouté en dessous du message (ie progressbar, ...). */
    private Widget extraContent;

    /**
     * Constructeur avec un message.
     * @param message le message
     */
    public LoadingPopupConfiguration(String message) {
        this.message = message;
    }

    /**
     * Constructeur avec un message et un timer.
     * @param message le message
     * @param secondes secondes du timer
     */
    public LoadingPopupConfiguration(String message, int secondes) {
        this.message = message;
        this.secondes = secondes;
    }

    /**
     * Constructeur avec un message & widget.
     * @param message le message
     * @param widget le widget
     */
    public LoadingPopupConfiguration(String message, Widget widget) {
        this.message = message;
        this.extraContent = widget;
    }

    /**
     * Get the message value.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message value.
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get the secondes value.
     * @return the secondes
     */
    public Integer getSecondes() {
        return secondes;
    }

    /**
     * Get the glassPanel value.
     * @return the glassPanel
     */
    public boolean isGlassPanel() {
        return glassPanel;
    }

    /**
     * Set the glassPanel value.
     * @param glassPanel the glassPanel to set
     */
    public void setGlassPanel(boolean glassPanel) {
        this.glassPanel = glassPanel;
    }

    /**
     * Get the width value.
     * @return the width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Set the width value.
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = new Integer(width);
    }

    /**
     * Récupération de extraContent.
     * @return the extraContent
     */
    public Widget getExtraContent() {
        return extraContent;
    }

    /**
     * Définition de extraContent.
     * @param extraContent the extraContent to set
     */
    public void setExtraContent(Widget extraContent) {
        this.extraContent = extraContent;
    }
}

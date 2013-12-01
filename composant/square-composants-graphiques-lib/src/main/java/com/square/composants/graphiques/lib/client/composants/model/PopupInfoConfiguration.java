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
package com.square.composants.graphiques.lib.client.composants.model;

import org.scub.foundation.framework.gwt.module.client.util.popup.Popup.PopupCallback;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Les paramètres de configuration d'une popup d'info.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopupInfoConfiguration implements IsSerializable {

    /** Message. */
    private String message;

    /** Callback. */
    private PopupCallback callback;

    /** Width. */
    private Integer width;

    /** Flag pour afficher un glassPanel. */
    private boolean glassPanel = true;

    /** Durée (en secondes) pendant laquelle la popup s'affiche. */
    private float timeOut;

    /**
     * Construecteur.
     * @param message le message.
     * @param timeOut le time out.
     */
    public PopupInfoConfiguration(String message, float timeOut) {
        this.message = message;
        this.timeOut = timeOut;
    }

    /**
     * Récupère la valeur de message.
     * @return la valeur de message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Définit la valeur de message.
     * @param message la nouvelle valeur de message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Récupère la valeur de callback.
     * @return la valeur de callback
     */
    public PopupCallback getCallback() {
        return callback;
    }

    /**
     * Définit la valeur de callback.
     * @param callback la nouvelle valeur de callback
     */
    public void setCallback(PopupCallback callback) {
        this.callback = callback;
    }

    /**
     * Récupère la valeur de width.
     * @return la valeur de width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Définit la valeur de width.
     * @param width la nouvelle valeur de width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * Récupère la valeur de glassPanel.
     * @return la valeur de glassPanel
     */
    public boolean isGlassPanel() {
        return glassPanel;
    }

    /**
     * Définit la valeur de glassPanel.
     * @param glassPanel la nouvelle valeur de glassPanel
     */
    public void setGlassPanel(boolean glassPanel) {
        this.glassPanel = glassPanel;
    }

    /**
     * Récupère la valeur de timeOut.
     * @return la valeur de timeOut
     */
    public float getTimeOut() {
        return timeOut;
    }

    /**
     * Définit la valeur de timeOut.
     * @param timeOut la nouvelle valeur de timeOut
     */
    public void setTimeOut(float timeOut) {
        this.timeOut = timeOut;
    }

}

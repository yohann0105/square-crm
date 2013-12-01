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
package com.square.client.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle pour les notifications.
 * @author cblanchard - SCUB
 */
public class ActionNotificationInfosModel implements IsSerializable {
    /**
     * Identifiant de la notification.
     */
    private Long id;

    /**
     * Libelle de la notification.
     */
    private String libelle;

    /**
     * La Notification.
     */
    private String notification;

    /**
     * Renvoi l'id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie l'id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Renvoi le libelle.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Modifie le libelle.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Renvoi notification.
     * @return the notification
     */
    public String getNotification() {
        return notification;
    }

    /**
     * Modifie notification.
     * @param notification the notification to set
     */
    public void setNotification(String notification) {
        this.notification = notification;
    }
}

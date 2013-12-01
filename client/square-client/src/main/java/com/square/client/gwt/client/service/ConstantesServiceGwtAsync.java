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
package com.square.client.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.client.gwt.client.model.ActionNotificationInfosModel;
import com.square.client.gwt.client.model.ConstantesModel;

/**
 * Interface asynchrone des services GWT pour les constantes.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface ConstantesServiceGwtAsync {
    /**
     * Récuperes les constantes.
     * @param asyncCallback le callback
     */
    void getConstantes(AsyncCallback<ConstantesModel> asyncCallback);

    /**
     * Recuperer la liste des notifications.
     * @param asyncCallback la liste des notifications
     */
    void getListeActionNotifications(AsyncCallback<List<ActionNotificationInfosModel>> asyncCallback);

    /**
     * Recuperer la liste des notifications.
     * @param id l'identifiant de la nortification
     * @param asyncCallback la notification
     */
    void getActionNotificationParId(Long id, AsyncCallback<ActionNotificationInfosModel> asyncCallback);

    /**
     * Récupère l'identifiant correspondant à la différence entre deux dates.
     * @param dateAction la date d'action
     * @param dateNotification la date de notification
     * @param asyncCallback l'identifiant correspondant à la différence entre les deux dates
     */
    void getIdNotification(String dateAction, String dateNotification, AsyncCallback<Long> asyncCallback);
}

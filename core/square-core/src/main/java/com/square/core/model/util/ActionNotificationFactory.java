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
/**
 * 
 */
package com.square.core.model.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.FactoryBean;

import com.square.core.model.dto.ActionNotificationInfosDto;

/**
 * Classe qui permet d'instancier un ActionNotificationDto.
 * @author Goumard Stephane (scub) - SCUB
 */
public class ActionNotificationFactory implements FactoryBean {

    /**
     * Private Date.
     */
    private String date;

    /**
     * Libelle de la notification.
     */
    private String libelle;

    /**
     * identifiant.
     */
    private Long id;

    @Override
    public Object getObject() throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:SS");
        final ActionNotificationInfosDto notificationObjet = new ActionNotificationInfosDto();
        notificationObjet.setId(id);
        notificationObjet.setLibelle(libelle);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(date));
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        notificationObjet.setNotification(calendar);
        return notificationObjet;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getObjectType() {
        return ActionNotificationInfosDto.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    /**
     * Fixer la durée de la notification.
     * @param date la date.
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * Le libelle de la notification.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Fixer l'identifiant.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}

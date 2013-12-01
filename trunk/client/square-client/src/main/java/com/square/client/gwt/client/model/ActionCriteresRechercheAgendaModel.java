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
 * Model contenant les critères de recherche pour récupérer les actions dans l'agenda.
 * @author cblanchard - SCUB
 */
public class ActionCriteresRechercheAgendaModel implements IsSerializable {

    /** Date min. */
    private String dateMinDateDebut;

    /** Date max. */
    private String dateMaxDateDebut;

    /**
     * Renvoi la valeur de dateMinDateDebut.
     * @return dateMinDateDebut
     */
    public String getDateMinDateDebut() {
        return dateMinDateDebut;
    }

    /**
     * Modifie dateMinDateDebut.
     * @param dateMinDateDebut la nouvelle valeur de dateMinDateDebut
     */
    public void setDateMinDateDebut(String dateMinDateDebut) {
        this.dateMinDateDebut = dateMinDateDebut;
    }

    /**
     * Renvoi la valeur de dateMaxDateDebut.
     * @return dateMaxDateDebut
     */
    public String getDateMaxDateDebut() {
        return dateMaxDateDebut;
    }

    /**
     * Modifie dateMaxDateDebut.
     * @param dateMaxDateDebut la nouvelle valeur de dateMaxDateDebut
     */
    public void setDateMaxDateDebut(String dateMaxDateDebut) {
        this.dateMaxDateDebut = dateMaxDateDebut;
    }

}

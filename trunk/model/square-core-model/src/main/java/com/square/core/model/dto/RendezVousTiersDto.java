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
package com.square.core.model.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Dto contenant les informations pour l'affichage d'un rendez-vous dans l'agenda.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class RendezVousTiersDto implements Serializable {

    private static final long serialVersionUID = -3436046936603248144L;

    /** La date du rendez vous. */
    private Calendar date;

    /** Durée du rendez vous. */
    private Integer nbMinutesDuree;

    /** Titre. */
    private String titre;

    /**
     * Get the value of date.
     * @return the date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * Set the value of date.
     * @param date the date to set
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Get the value of titre.
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Set the value of titre.
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Get the value of nbMinutesDuree.
     * @return the nbMinutesDuree
     */
    public Integer getNbMinutesDuree() {
        return nbMinutesDuree;
    }

    /**
     * Set the value of nbMinutesDuree.
     * @param nbMinutesDuree the nbMinutesDuree to set
     */
    public void setNbMinutesDuree(Integer nbMinutesDuree) {
        this.nbMinutesDuree = nbMinutesDuree;
    }

}

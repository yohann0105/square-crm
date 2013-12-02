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
 * Dto contenant les criteres de recherche des rendez-vous de l'agenda tiers.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class RendezVousTiersCriteresDto implements Serializable {

    private static final long serialVersionUID = 1120443047436469376L;

    /** Id de l'utilisateur. */
    private Long idUtilisateurConnecte;

    /** Date min. */
    private Calendar dateMin;

    /** Date max. */
    private Calendar dateMax;

    /**
     * Get the value of dateMin.
     * @return the dateMin
     */
    public Calendar getDateMin() {
        return dateMin;
    }

    /**
     * Set the value of dateMin.
     * @param dateMin the dateMin to set
     */
    public void setDateMin(Calendar dateMin) {
        this.dateMin = dateMin;
    }

    /**
     * Get the value of dateMax.
     * @return the dateMax
     */
    public Calendar getDateMax() {
        return dateMax;
    }

    /**
     * Set the value of dateMax.
     * @param dateMax the dateMax to set
     */
    public void setDateMax(Calendar dateMax) {
        this.dateMax = dateMax;
    }

    /**
     * Get the value of idUtilisateurConnecte.
     * @return the idUtilisateurConnecte
     */
    public Long getIdUtilisateurConnecte() {
        return idUtilisateurConnecte;
    }

    /**
     * Set the value of idUtilisateurConnecte.
     * @param idUtilisateurConnecte the idUtilisateurConnecte to set
     */
    public void setIdUtilisateurConnecte(Long idUtilisateurConnecte) {
        this.idUtilisateurConnecte = idUtilisateurConnecte;
    }

}

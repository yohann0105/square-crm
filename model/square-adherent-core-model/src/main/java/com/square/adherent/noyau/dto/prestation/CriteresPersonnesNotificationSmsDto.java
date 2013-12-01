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
package com.square.adherent.noyau.dto.prestation;

import java.io.Serializable;
import java.util.Calendar;

/**
 * DTO représentant un décompte.
 * @author nnadeau - SCUB
 */
public class CriteresPersonnesNotificationSmsDto implements Serializable {

    /** Identifiant de sérialistaion. */
    private static final long serialVersionUID = -980907282141975786L;

    /** Date de règlement. */
    private Calendar dateReglement;

    private Float montantMinimal;

    /**
     * Get the dateReglement value.
     * @return the dateReglement
     */
    public Calendar getDateReglement() {
        return dateReglement;
    }

    /**
     * Set the dateReglement value.
     * @param dateReglement the dateReglement to set
     */
    public void setDateReglement(Calendar dateReglement) {
        this.dateReglement = dateReglement;
    }

    /**
     * Get the montantMinimal value.
     * @return the montantMinimal
     */
    public Float getMontantMinimal() {
        return montantMinimal;
    }

    /**
     * Set the montantMinimal value.
     * @param montantMinimal the montantMinimal to set
     */
    public void setMontantMinimal(Float montantMinimal) {
        this.montantMinimal = montantMinimal;
    }
}

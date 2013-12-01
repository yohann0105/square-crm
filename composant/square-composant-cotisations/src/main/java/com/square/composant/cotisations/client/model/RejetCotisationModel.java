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
package com.square.composant.cotisations.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model qui représente une rejet de cotisation.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class RejetCotisationModel implements IsSerializable {

    /** Montant du rejet. */
    private Float montant;

    /** Motif du rejet. */
    private String motif;

    /** Date du rejet. */
    private String date;

    /**
     * Get the montant value.
     * @return the montant
     */
    public Float getMontant() {
        return montant;
    }

    /**
     * Set the montant value.
     * @param montant the montant to set
     */
    public void setMontant(Float montant) {
        this.montant = montant;
    }

    /**
     * Get the motif value.
     * @return the motif
     */
    public String getMotif() {
        return motif;
    }

    /**
     * Set the motif value.
     * @param motif the motif to set
     */
    public void setMotif(String motif) {
        this.motif = motif;
    }

    /**
     * Get the date value.
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date value.
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

}

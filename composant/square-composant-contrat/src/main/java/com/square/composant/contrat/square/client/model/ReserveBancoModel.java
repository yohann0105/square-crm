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
package com.square.composant.contrat.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle encapsulant les informations de réserve pour les garanties Banco.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ReserveBancoModel implements IsSerializable {

    /** Montant de la réserve annuelle. */
    private double reserveAnnuelle;

    /** Montant de la réserve consommée. */
    private double reserveConsommee;

    /** Date debut reserve. */
    private String dateDebutReserve;

    /** Date fin reserve. */
    private String dateFinReserve;

    /**
     * Getter function.
     * @return the reserveAnnuelle
     */
    public double getReserveAnnuelle() {
        return reserveAnnuelle;
    }

    /**
     * Getter function.
     * @return the reserveConsommee
     */
    public double getReserveConsommee() {
        return reserveConsommee;
    }

    /**
     * Setter function.
     * @param reserveAnnuelle the reserveAnnuelle to set
     */
    public void setReserveAnnuelle(double reserveAnnuelle) {
        this.reserveAnnuelle = reserveAnnuelle;
    }

    /**
     * Setter function.
     * @param reserveConsommee the reserveConsommee to set
     */
    public void setReserveConsommee(double reserveConsommee) {
        this.reserveConsommee = reserveConsommee;
    }

    /**
     * Get the value of dateDebutReserve.
     * @return the dateDebutReserve
     */
    public String getDateDebutReserve() {
        return dateDebutReserve;
    }

    /**
     * Set the value of dateDebutReserve.
     * @param dateDebutReserve the dateDebutReserve to set
     */
    public void setDateDebutReserve(String dateDebutReserve) {
        this.dateDebutReserve = dateDebutReserve;
    }

    /**
     * Get the value of dateFinReserve.
     * @return the dateFinReserve
     */
    public String getDateFinReserve() {
        return dateFinReserve;
    }

    /**
     * Set the value of dateFinReserve.
     * @param dateFinReserve the dateFinReserve to set
     */
    public void setDateFinReserve(String dateFinReserve) {
        this.dateFinReserve = dateFinReserve;
    }

}

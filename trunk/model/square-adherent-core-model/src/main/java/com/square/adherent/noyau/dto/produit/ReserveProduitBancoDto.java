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
package com.square.adherent.noyau.dto.produit;

import java.io.Serializable;
import java.util.Calendar;

/**
 * DTO représentant les informations de la réserve d'un adhérent pour un produit Banco.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ReserveProduitBancoDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -183043828100443313L;

    /** Date anniversaire de la garantie. */
    private Calendar dateAnniversaire;

    /** Date debut reserve. */
    private Calendar dateDebutReserve;

    /** Date fin reserve. */
    private Calendar dateFinReserve;

    /** Réserve annuelle. */
    private double reserveAnnuelle;

    /** Réserve consommée. */
    private double reserveConsommee;

    /**
     * Get the dateAnniversaire value.
     * @return the dateAnniversaire
     */
    public Calendar getDateAnniversaire() {
        return dateAnniversaire;
    }

    /**
     * Set the dateAnniversaire value.
     * @param dateAnniversaire the dateAnniversaire to set
     */
    public void setDateAnniversaire(Calendar dateAnniversaire) {
        this.dateAnniversaire = dateAnniversaire;
    }

    /**
     * Get the reserveAnnuelle value.
     * @return the reserveAnnuelle
     */
    public double getReserveAnnuelle() {
        return reserveAnnuelle;
    }

    /**
     * Set the reserveAnnuelle value.
     * @param reserveAnnuelle the reserveAnnuelle to set
     */
    public void setReserveAnnuelle(double reserveAnnuelle) {
        this.reserveAnnuelle = reserveAnnuelle;
    }

    /**
     * Get the reserveConsommee value.
     * @return the reserveConsommee
     */
    public double getReserveConsommee() {
        return reserveConsommee;
    }

    /**
     * Set the reserveConsommee value.
     * @param reserveConsommee the reserveConsommee to set
     */
    public void setReserveConsommee(double reserveConsommee) {
        this.reserveConsommee = reserveConsommee;
    }

    /**
     * Get the value of dateDebutReserve.
     * @return the dateDebutReserve
     */
    public Calendar getDateDebutReserve() {
        return dateDebutReserve;
    }

    /**
     * Set the value of dateDebutReserve.
     * @param dateDebutReserve the dateDebutReserve to set
     */
    public void setDateDebutReserve(Calendar dateDebutReserve) {
        this.dateDebutReserve = dateDebutReserve;
    }

    /**
     * Get the value of dateFinReserve.
     * @return the dateFinReserve
     */
    public Calendar getDateFinReserve() {
        return dateFinReserve;
    }

    /**
     * Set the value of dateFinReserve.
     * @param dateFinReserve the dateFinReserve to set
     */
    public void setDateFinReserve(Calendar dateFinReserve) {
        this.dateFinReserve = dateFinReserve;
    }
}

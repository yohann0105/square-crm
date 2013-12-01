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
package com.square.tarificateur.noyau.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Représente les infos d'un ba sur la plateforme CNS.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class InfosBaDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6876836177613108131L;

    /** Id du fichier. */
    private String idFichier;

    /** Date de clic du mail. */
    private Calendar dateClicMail;

    /** Date de telechargement. */
    private Calendar dateTelechargement;

    /**
     * Get the idFichier value.
     * @return the idFichier
     */
    public String getIdFichier() {
        return idFichier;
    }

    /**
     * Set the idFichier value.
     * @param idFichier the idFichier to set
     */
    public void setIdFichier(String idFichier) {
        this.idFichier = idFichier;
    }

    /**
     * Get the dateClicMail value.
     * @return the dateClicMail
     */
    public Calendar getDateClicMail() {
        return dateClicMail;
    }

    /**
     * Set the dateClicMail value.
     * @param dateClicMail the dateClicMail to set
     */
    public void setDateClicMail(Calendar dateClicMail) {
        this.dateClicMail = dateClicMail;
    }

    /**
     * Get the dateTelechargement value.
     * @return the dateTelechargement
     */
    public Calendar getDateTelechargement() {
        return dateTelechargement;
    }

    /**
     * Set the dateTelechargement value.
     * @param dateTelechargement the dateTelechargement to set
     */
    public void setDateTelechargement(Calendar dateTelechargement) {
        this.dateTelechargement = dateTelechargement;
    }

}

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
package com.square.price.core.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Dto pour regrouper les criteres pour la selection de zonier.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public class ZonageCriteresDto implements Serializable {

    private static final long serialVersionUID = 5735589599309896323L;

    private Integer identifiantProduit;

    private String contrat;

    private String idZonier;

    private Calendar dateEffet;

    private String identifiantBareme;

    /**
     * Code Departement. Les deux premiers chiffre du code postal (sauf cas particulier)<br>
     * - B1 à B9 pour les departements belges.<br>
     * - 2A et 2B corse du Nord et Corse du Sud.<br>
     * - 99 pour l'etranger.<br>
     * - AM Regime alsace moselle.<br>
     */
    private String codeDepartement;

    /**
     * Get the value of codeDepartement.
     * @return the codeDepartement
     */
    public String getCodeDepartement() {
        return codeDepartement;
    }

    /**
     * Set the value of codeDepartement.
     * @param codeDepartement the codeDepartement to set
     */
    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    /**
     * Get the value of idZonier.
     * @return the idZonier
     */
    public String getIdZonier() {
        return idZonier;
    }

    /**
     * Set the value of idZonier.
     * @param idZonier the idZonier to set
     */
    public void setIdZonier(String idZonier) {
        this.idZonier = idZonier;
    }

    /**
     * Get the value of identifiantProduit.
     * @return the identifiantProduit
     */
    public Integer getIdentifiantProduit() {
        return identifiantProduit;
    }

    /**
     * Set the value of identifiantProduit.
     * @param identifiantProduit the identifiantProduit to set
     */
    public void setIdentifiantProduit(Integer identifiantProduit) {
        this.identifiantProduit = identifiantProduit;
    }

    /**
     * Get the value of contrat.
     * @return the contrat
     */
    public String getContrat() {
        return contrat;
    }

    /**
     * Set the value of contrat.
     * @param contrat the contrat to set
     */
    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    /**
     * Get the value of dateEffet.
     * @return the dateEffet
     */
    public Calendar getDateEffet() {
        return dateEffet;
    }

    /**
     * Set the value of dateEffet.
     * @param dateEffet the dateEffet to set
     */
    public void setDateEffet(Calendar dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Get the value of identifiantBareme.
     * @return the identifiantBareme
     */
    public String getIdentifiantBareme() {
        return identifiantBareme;
    }

    /**
     * Set the value of identifiantBareme.
     * @param identifiantBareme the identifiantBareme to set
     */
    public void setIdentifiantBareme(String identifiantBareme) {
        this.identifiantBareme = identifiantBareme;
    }
}

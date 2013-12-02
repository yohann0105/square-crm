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
package com.square.adherent.noyau.dto.adherent.contrat;

import java.io.Serializable;
import java.util.Calendar;

/**
 * DTO pour la recherche de produits collectifs adherents.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ProduitCollectifAdherentCriteresDto  implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6276487846313273717L;

    /** Date d'effet. */
    private Calendar dateEffet;

    /** Uid de l'entreprise. */
    private Long uidEntreprise;

    /** Contrat. */
    private String contrat;

    /** Population. */
    private String population;

    /**
     * Get the contrat value.
     * @return the contrat
     */
    public String getContrat() {
        return contrat;
    }

    /**
     * Set the contrat value.
     * @param contrat the contrat to set
     */
    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    /**
     * Get the population value.
     * @return the population
     */
    public String getPopulation() {
        return population;
    }

    /**
     * Set the population value.
     * @param population the population to set
     */
    public void setPopulation(String population) {
        this.population = population;
    }

    /**
     * Get the value of uidEntreprise.
     * @return the uidEntreprise
     */
    public Long getUidEntreprise() {
        return uidEntreprise;
    }

    /**
     * Set the value of uidEntreprise.
     * @param uidEntreprise the uidEntreprise to set
     */
    public void setUidEntreprise(Long uidEntreprise) {
        this.uidEntreprise = uidEntreprise;
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

}

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

/**
 * DTO de criteres de recherche d'un type de payeur.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class TypePayeurCriteresDto implements Serializable {

    private static final long serialVersionUID = 2810854585160740371L;

    private String garantieGestion;

    private String produitGestion;

    private String contrat;

    private String codeTarif;

    private Double montantSouscrit;

    private String libPopulation;

    /**
     * Get the value of garantieGestion.
     * @return the garantieGestion
     */
    public String getGarantieGestion() {
        return garantieGestion;
    }

    /**
     * Set the value of garantieGestion.
     * @param garantieGestion the garantieGestion to set
     */
    public void setGarantieGestion(String garantieGestion) {
        this.garantieGestion = garantieGestion;
    }

    /**
     * Get the value of produitGestion.
     * @return the produitGestion
     */
    public String getProduitGestion() {
        return produitGestion;
    }

    /**
     * Set the value of produitGestion.
     * @param produitGestion the produitGestion to set
     */
    public void setProduitGestion(String produitGestion) {
        this.produitGestion = produitGestion;
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
     * Get the value of codeTarif.
     * @return the codeTarif
     */
    public String getCodeTarif() {
        return codeTarif;
    }

    /**
     * Set the value of codeTarif.
     * @param codeTarif the codeTarif to set
     */
    public void setCodeTarif(String codeTarif) {
        this.codeTarif = codeTarif;
    }

    /**
     * Get the value of montantSouscrit.
     * @return the montantSouscrit
     */
    public Double getMontantSouscrit() {
        return montantSouscrit;
    }

    /**
     * Set the value of montantSouscrit.
     * @param montantSouscrit the montantSouscrit to set
     */
    public void setMontantSouscrit(Double montantSouscrit) {
        this.montantSouscrit = montantSouscrit;
    }

    /**
     * Get the value of libelle population.
     * @return libPopulation
     */
	public String getLibPopulation() {
		return libPopulation;
	}

	/**
	 * Set the value of libPopulation.
	 * @param libPopulation the libelle population
	 */
	public void setLibPopulation(String libPopulation) {
		this.libPopulation = libPopulation;
	}
}

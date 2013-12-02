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

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO représentant le ration prestation/cotisation pour une année et pour une personne.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class RatioPrestationCotisationDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -3093486213875539808L;

    /** Année. */
    private int annee;

    /** Personne. */
    private IdentifiantLibelleDto personne;

    /** Ratio Prestation/Cotisation. */
    private double ratioPrestationCotisation;

    /**
     * Récupère la valeur de annee.
     * @return la valeur de annee
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Définit la valeur de annee.
     * @param annee la nouvelle valeur de annee
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * Récupère la valeur de personne.
     * @return la valeur de personne
     */
    public IdentifiantLibelleDto getPersonne() {
        return personne;
    }

    /**
     * Définit la valeur de personne.
     * @param personne la nouvelle valeur de personne
     */
    public void setPersonne(IdentifiantLibelleDto personne) {
        this.personne = personne;
    }

    /**
     * Récupère la valeur de ratioPrestationCotisation.
     * @return la valeur de ratioPrestationCotisation
     */
    public double getRatioPrestationCotisation() {
        return ratioPrestationCotisation;
    }

    /**
     * Définit la valeur de ratioPrestationCotisation.
     * @param ratioPrestationCotisation la nouvelle valeur de ratioPrestationCotisation
     */
    public void setRatioPrestationCotisation(double ratioPrestationCotisation) {
        this.ratioPrestationCotisation = ratioPrestationCotisation;
    }

}

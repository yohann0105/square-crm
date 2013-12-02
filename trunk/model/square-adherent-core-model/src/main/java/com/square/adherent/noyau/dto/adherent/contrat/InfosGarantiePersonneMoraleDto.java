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
 * Dto regroupant les informations d'une ligne du tableau d'une garantie d'une personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class InfosGarantiePersonneMoraleDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -1369663681598687150L;

    /** Code du tarif. */
    private String codeTarif;

    /** Libellé de gestion de la garantie. */
    private String libelleGarantieGestion;

    /** Libellé de la population. */
    private String libellePopulation;

    /** Montant souscrit. */
    private Double montantSouscrit;

    /** Le statut de la garantie. */
    private IdentifiantLibelleDto statut;

    /**
     * Récupère la valeur de codeTarif.
     * @return la valeur de codeTarif
     */
    public String getCodeTarif() {
        return codeTarif;
    }

    /**
     * Définit la valeur de codeTarif.
     * @param codeTarif la nouvelle valeur de codeTarif
     */
    public void setCodeTarif(String codeTarif) {
        this.codeTarif = codeTarif;
    }

    /**
     * Récupère la valeur de libelleGarantieGestion.
     * @return la valeur de libelleGarantieGestion
     */
    public String getLibelleGarantieGestion() {
        return libelleGarantieGestion;
    }

    /**
     * Définit la valeur de libelleGarantieGestion.
     * @param libelleGarantieGestion la nouvelle valeur de libelleGarantieGestion
     */
    public void setLibelleGarantieGestion(String libelleGarantieGestion) {
        this.libelleGarantieGestion = libelleGarantieGestion;
    }

    /**
     * Récupère la valeur de libellePopulation.
     * @return la valeur de libellePopulation
     */
    public String getLibellePopulation() {
        return libellePopulation;
    }

    /**
     * Définit la valeur de libellePopulation.
     * @param libellePopulation la nouvelle valeur de libellePopulation
     */
    public void setLibellePopulation(String libellePopulation) {
        this.libellePopulation = libellePopulation;
    }

    /**
     * Récupère la valeur de montantSouscrit.
     * @return la valeur de montantSouscrit
     */
    public Double getMontantSouscrit() {
        return montantSouscrit;
    }

    /**
     * Définit la valeur de montantSouscrit.
     * @param montantSouscrit la nouvelle valeur de montantSouscrit
     */
    public void setMontantSouscrit(Double montantSouscrit) {
        this.montantSouscrit = montantSouscrit;
    }

    /**
     * Getter function.
     * @return the statut
     */
    public IdentifiantLibelleDto getStatut() {
        return statut;
    }

    /**
     * Setter function.
     * @param statut the statut to set
     */
    public void setStatut(IdentifiantLibelleDto statut) {
        this.statut = statut;
    }
}

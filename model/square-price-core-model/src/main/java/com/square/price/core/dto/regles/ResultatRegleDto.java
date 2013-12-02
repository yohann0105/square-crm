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
package com.square.price.core.dto.regles;

import java.io.Serializable;

/**
 * DTO résultat d'une recherche de règles.
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 *
 */
public class ResultatRegleDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -9166546073849679165L;

    private Integer idRegle;

    private String libelleRegle;

    private Integer idValeurRegle;

    private String valeurRegle;

    private String typeValeurRegle;

    private Boolean impacteTarif;

    /** Constructeur par défaut. */
    public ResultatRegleDto() { }

    /**
     * Constructeur paramétré.
     * TODO : Pas de constructeur paramétré, remplacer par un mapping dozer sur le noyau
     * @param idRegle l'identifiant de la règle
     * @param idValeurRegle l'identifiant de la valeur de règle
     * @param valeurRegle la valeur de la règle
     * @param typeValeurRegle le type de la valeur de la règle
     * @param impacteTarif la règle correspondante impacte-t-elle le tarif ?
     * @param libelleRegle le libellé de la règle
     */
    public ResultatRegleDto(Integer idRegle, Integer idValeurRegle, String valeurRegle, String typeValeurRegle, Boolean impacteTarif, String libelleRegle) {
        this.idRegle = idRegle;
        this.idValeurRegle = idValeurRegle;
        this.valeurRegle = valeurRegle;
        this.typeValeurRegle = typeValeurRegle;
        this.impacteTarif = impacteTarif;
        this.libelleRegle = libelleRegle;
    }

    /**
     * Get the idRegle value.
     * @return the idRegle
     */
    public Integer getIdRegle() {
        return idRegle;
    }

    /**
     * Set the idRegle value.
     * @param idRegle the idRegle to set
     */
    public void setIdRegle(Integer idRegle) {
        this.idRegle = idRegle;
    }

    /**
     * Get the valeurRegle value.
     * @return the valeurRegle
     */
    public String getValeurRegle() {
        return valeurRegle;
    }

    /**
     * Set the valeurRegle value.
     * @param valeurRegle the valeurRegle to set
     */
    public void setValeurRegle(String valeurRegle) {
        this.valeurRegle = valeurRegle;
    }

    /**
     * Get the typeValeurRegle value.
     * @return the typeValeurRegle
     */
    public String getTypeValeurRegle() {
        return typeValeurRegle;
    }

    /**
     * Set the typeValeurRegle value.
     * @param typeValeurRegle the typeValeurRegle to set
     */
    public void setTypeValeurRegle(String typeValeurRegle) {
        this.typeValeurRegle = typeValeurRegle;
    }

    /**
     * Get the impacteTarif value.
     * @return the impacteTarif
     */
    public Boolean getImpacteTarif() {
        return impacteTarif;
    }

    /**
     * Set the impacteTarif value.
     * @param impacteTarif the impacteTarif to set
     */
    public void setImpacteTarif(Boolean impacteTarif) {
        this.impacteTarif = impacteTarif;
    }

    /**
     * Get the libelleRegle value.
     * @return the libelleRegle
     */
    public String getLibelleRegle() {
        return libelleRegle;
    }

    /**
     * Set the libelleRegle value.
     * @param libelleRegle the libelleRegle to set
     */
    public void setLibelleRegle(String libelleRegle) {
        this.libelleRegle = libelleRegle;
    }

    /**
     * Get the idValeurRegle value.
     * @return the idValeurRegle
     */
    public Integer getIdValeurRegle() {
        return idValeurRegle;
    }

    /**
     * Set the idValeurRegle value.
     * @param idValeurRegle the idValeurRegle to set
     */
    public void setIdValeurRegle(Integer idValeurRegle) {
        this.idValeurRegle = idValeurRegle;
    }

}

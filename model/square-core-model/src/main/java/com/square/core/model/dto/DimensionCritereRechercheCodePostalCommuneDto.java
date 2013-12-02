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
package com.square.core.model.dto;

import java.io.Serializable;


/**
 * Critères de recherche pour la table de dimension CodePostalCommune.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class DimensionCritereRechercheCodePostalCommuneDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -8082020623170783769L;

    /** Identifiant du code postal - commune. */
    private Long idCodePostalCommune;

    /** Libellé d'acheminement. */
    private String libelleAcheminement;

    /** Identifiant de la commune. */
    private Long idCommune;

    /** Identifiant du code postal. */
    private Long idCodePostal;

    /** Libellé de la commune. */
    private String libelleCommune;

    /** Libellé du code postal. */
    private String libelleCodePostal;

    /**
     * Indique si on recherche les dimensions visibles.
     * true on recherche les éléments visible
     * false les éléments non visibles
     * null pas de recherche sur la visibilité.
     */
    private Boolean visible;

    /** Nombre de résultats max. */
    private Integer maxResults;

    /**
     * Retourne la valeur de idCommune.
     * @return the idCommune
     */
    public Long getIdCommune() {
        return idCommune;
    }

    /**
     * Modifie la valeur de idCommune.
     * @param idCommune the idCommune to set
     */
    public void setIdCommune(Long idCommune) {
        this.idCommune = idCommune;
    }

    /**
     * Recuperer la valeur.
     * @return the idCodePostal
     */
    public Long getIdCodePostal() {
        return idCodePostal;
    }

    /**
     * Fixer la valeur.
     * @param idCodePostal the idCodePostal to set
     */
    public void setIdCodePostal(Long idCodePostal) {
        this.idCodePostal = idCodePostal;
    }

    /**
     * Récupère la valeur libelleCommune.
     * @return the libelleCommune
     */
    public String getLibelleCommune() {
        return libelleCommune;
    }

    /**
     * Définit la valeur de libelleCommune.
     * @param libelleCommune the libelleCommune to set
     */
    public void setLibelleCommune(String libelleCommune) {
        this.libelleCommune = libelleCommune;
    }

    /**
     * Récupère la valeur libelleCodePostal.
     * @return the libelleCodePostal
     */
    public String getLibelleCodePostal() {
        return libelleCodePostal;
    }

    /**
     * Définit la valeur de libelleCodePostal.
     * @param libelleCodePostal the libelleCodePostal to set
     */
    public void setLibelleCodePostal(String libelleCodePostal) {
        this.libelleCodePostal = libelleCodePostal;
    }

    /**
     * Récupère la valeur idCodePostalCommune.
     * @return the idCodePostalCommune
     */
    public Long getIdCodePostalCommune() {
        return idCodePostalCommune;
    }

    /**
     * Définit la valeur de idCodePostalCommune.
     * @param idCodePostalCommune the idCodePostalCommune to set
     */
    public void setIdCodePostalCommune(Long idCodePostalCommune) {
        this.idCodePostalCommune = idCodePostalCommune;
    }

    /**
     * Récupère la valeur libelleAcheminement.
     * @return the libelleAcheminement
     */
    public String getLibelleAcheminement() {
        return libelleAcheminement;
    }

    /**
     * Définit la valeur de libelleAcheminement.
     * @param libelleAcheminement the libelleAcheminement to set
     */
    public void setLibelleAcheminement(String libelleAcheminement) {
        this.libelleAcheminement = libelleAcheminement;
    }

    /**
     * Récupère la valeur visible.
     * @return the visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * Définit la valeur de visible.
     * @param visible the visible to set
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * Récupère la valeur maxResults.
     * @return the maxResults
     */
    public Integer getMaxResults() {
        return maxResults;
    }

    /**
     * Définit la valeur de maxResults.
     * @param maxResults the maxResults to set
     */
    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }
}

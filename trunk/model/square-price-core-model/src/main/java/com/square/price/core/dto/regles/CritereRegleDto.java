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
 * DTO contenant les infos liées à un critère de règle.
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public class CritereRegleDto implements Serializable {
    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6906388654175068270L;

    private Integer identifiant;

    private String valeur;

    private String operateur;

    private Integer type;

    /** Constructeur par défaut. */
    public CritereRegleDto() { }

    /**
     * Constructeur paramétré.
     * @param identifiant l'identifiant
     * @param valeur la valeur
     */
    public CritereRegleDto(Integer identifiant, String valeur) {
        this.identifiant = identifiant;
        this.valeur = valeur;
    }

    /**
     * Get the identifiant value.
     * @return the identifiant
     */
    public Integer getIdentifiant() {
        return identifiant;
    }

    /**
     * Set the identifiant value.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Get the valeur value.
     * @return the valeur
     */
    public String getValeur() {
        return valeur;
    }

    /**
     * Set the valeur value.
     * @param valeur the valeur to set
     */
    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    /**
     * Get the operateur value.
     * @return the operateur
     */
    public String getOperateur() {
        return operateur;
    }

    /**
     * Set the operateur value.
     * @param operateur the operateur to set
     */
    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    /**
     * Get the type value.
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Set the type value.
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

}

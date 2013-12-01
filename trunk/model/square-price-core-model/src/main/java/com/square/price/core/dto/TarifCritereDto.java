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

/**
 * Dto pour le transfert d'information sur les critéres.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class TarifCritereDto implements Serializable {
    private static final long serialVersionUID = 4971876214651724756L;

    /** Identifiant du critere. */
    private Integer identifiant;

    /** Valeur du critere. */
    private String valeur;

    /**
     * Contructeur par defaut.
     * @param identifiant l'identifiant du bareme.
     * @param valeur la valeur du critere.
     */
    public TarifCritereDto(final Integer identifiant, final String valeur) {
        this.identifiant = identifiant;
        this.valeur = valeur;
    }

    /**
     * Contructeur par defaut.
     */
    public TarifCritereDto() {
    }

    /**
     * Recuperer la valeur du champ identifiant.
     * @return the identifiant
     */
    public Integer getIdentifiant() {
        return identifiant;
    }

    /**
     * Fixer la valeur du champ identifiant.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Recuperer la valeur du champ valeur.
     * @return the valeur
     */
    public String getValeur() {
        return valeur;
    }

    /**
     * Fixer la valeur du champ valeur.
     * @param valeur the valeur to set
     */
    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}

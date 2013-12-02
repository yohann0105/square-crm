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

/**
 * DTO permettant de récupérer les informations d'une caisse.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class RegimeDto extends IdentifiantEidLibelleDto {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -4569900831810489942L;

    /** Code de la caisse. */
    private String code;

    /**
     * Récupère la valeur de code.
     * @return la valeur de code
     */
    public String getCode() {
        return code;
    }

    /**
     * Définit la valeur de code.
     * @param code la nouvelle valeur de code
     */
    public void setCode(String code) {
        this.code = code;
    }

}

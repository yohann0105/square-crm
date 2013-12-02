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
 * Dto pour le numéro de régime obligatoire.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class NumeroRoDto implements Serializable {

    /**
     * Serial UID.
     */
    private static final long serialVersionUID = 3372011311388734592L;

    /**
     * Numéro de sécurité social.
     */
    private String numeroSS;

    /**
     * La clé.
     */
    private String cleSS;

    /**
     * Retourne la valeur de numeroSS.
     * @return the numeroSS
     */
    public String getNumeroSS() {
        return numeroSS;
    }

    /**
     * Modifie la valeur de numeroSS.
     * @param numeroSS the numeroSS to set
     */
    public void setNumeroSS(String numeroSS) {
        this.numeroSS = numeroSS;
    }

    /**
     * Retourne la valeur de cleSS.
     * @return the cleSS
     */
    public String getCleSS() {
        return cleSS;
    }

    /**
     * Modifie la valeur de cleSS.
     * @param cleSS the cleSS to set
     */
    public void setCleSS(String cleSS) {
        this.cleSS = cleSS;
    }

}

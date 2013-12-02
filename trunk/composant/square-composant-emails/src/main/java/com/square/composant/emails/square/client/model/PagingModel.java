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
package com.square.composant.emails.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO GWT représentant une pagination.
 * @author nnadeau - SCUB
 *
 */
public class PagingModel implements IsSerializable {

    /**
     * Debut pagination.
     */
    private Integer firstResult;

    /**
     * Max pagination.
     */
    private Integer maxResult;

    /**
     * Nombre de resultat de la requete hors pagination.
     */
    private Integer nombreTotalResultat;

    /**
     * Retourne la valeur de firstResult.
     * @return firstResult
     */
    public Integer getFirstResult() {
        return firstResult;
    }

    /**
     * Définit la valeur de firstResult.
     * @param firstResult la nouvelle valeur de firstResult
     */
    public void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    /**
     * Retourne la valeur de maxResult.
     * @return maxResult
     */
    public Integer getMaxResult() {
        return maxResult;
    }

    /**
     * Définit la valeur de maxResult.
     * @param maxResult la nouvelle valeur de maxResult
     */
    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

    /**
     * Retourne la valeur de nombreTotalResultat.
     * @return nombreTotalResultat
     */
    public Integer getNombreTotalResultat() {
        return nombreTotalResultat;
    }

    /**
     * Définit la valeur de nombreTotalResultat.
     * @param nombreTotalResultat la nouvelle valeur de nombreTotalResultat
     */
    public void setNombreTotalResultat(Integer nombreTotalResultat) {
        this.nombreTotalResultat = nombreTotalResultat;
    }
}

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
package com.square.client.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model contenant les critères pour rechercher les campagnes par libelle.
 * @author cblanchard - SCUB
 */
public class CampagneCriteresRechercheLibelleModel implements IsSerializable {

    /** Le libelle. */
    private String libelle;

    /** le nombre de résultat max. */
    private Integer maxResult;

    /**
     * Renvoi la valeur de libelle.
     * @return libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Modifie libelle.
     * @param libelle la nouvelle valeur de libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Renvoi la valeur de maxResult.
     * @return maxResult
     */
    public Integer getMaxResult() {
        return maxResult;
    }

    /**
     * Modifie maxResult.
     * @param maxResult la nouvelle valeur de maxResult
     */
    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

}

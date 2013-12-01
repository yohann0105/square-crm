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
package com.square.composant.flux.opportunite.client.model;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle représentant les informations d'un quota.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class QuotaModel implements IsSerializable {

    /** Identifiant du quota. */
    private Long idQuota;

    /** Le jour du quota. */
    private IdentifiantLibelleGwt jourQuota;

    /** La valeur du quota. */
    private Integer value;

    /**
     * Récupère la valeur de idQuota.
     * @return la valeur de idQuota
     */
    public Long getIdQuota() {
        return idQuota;
    }

    /**
     * Définit la valeur de idQuota.
     * @param idQuota la nouvelle valeur de idQuota
     */
    public void setIdQuota(Long idQuota) {
        this.idQuota = idQuota;
    }

    /**
     * Récupère la valeur de jourQuota.
     * @return la valeur de jourQuota
     */
    public IdentifiantLibelleGwt getJourQuota() {
        return jourQuota;
    }

    /**
     * Définit la valeur de jourQuota.
     * @param jourQuota la nouvelle valeur de jourQuota
     */
    public void setJourQuota(IdentifiantLibelleGwt jourQuota) {
        this.jourQuota = jourQuota;
    }

    /**
     * Récupère la valeur de value.
     * @return la valeur de value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Définit la valeur de value.
     * @param value la nouvelle valeur de value
     */
    public void setValue(Integer value) {
        this.value = value;
    }
}

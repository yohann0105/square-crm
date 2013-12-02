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
package com.square.tarificateur.noyau.dto.devis;

import java.io.Serializable;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO permettant la modification d'une opportunité.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class OpportuniteModificationDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -8569659456343821531L;

    /** Identifiant de l'opportunité à modifier (soit id ou eid doit être renseigné). */
    private Long idOpportunite;

    /** Identifiant externe de l'opportunité à modifier (soit id ou eid doit être renseigné). */
    private Long eidOpportunite;

    /** Finalité. */
    private IdentifiantLibelleDto finalite;

    /**
     * Récupère la valeur de idOpportunite.
     * @return la valeur de idOpportunite
     */
    public Long getIdOpportunite() {
        return idOpportunite;
    }

    /**
     * Définit la valeur de idOpportunite.
     * @param idOpportunite la nouvelle valeur de idOpportunite
     */
    public void setIdOpportunite(Long idOpportunite) {
        this.idOpportunite = idOpportunite;
    }

    /**
     * Récupère la valeur de finalite.
     * @return la valeur de finalite
     */
    public IdentifiantLibelleDto getFinalite() {
        return finalite;
    }

    /**
     * Définit la valeur de finalite.
     * @param finalite la nouvelle valeur de finalite
     */
    public void setFinalite(IdentifiantLibelleDto finalite) {
        this.finalite = finalite;
    }

    /**
     * Récupère la valeur de eidOpportunite.
     * @return la valeur de eidOpportunite
     */
    public Long getEidOpportunite() {
        return eidOpportunite;
    }

    /**
     * Définit la valeur de eidOpportunite.
     * @param eidOpportunite la nouvelle valeur de eidOpportunite
     */
    public void setEidOpportunite(Long eidOpportunite) {
        this.eidOpportunite = eidOpportunite;
    }

}

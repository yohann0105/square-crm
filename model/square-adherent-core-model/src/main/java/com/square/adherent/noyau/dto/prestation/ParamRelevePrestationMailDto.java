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
package com.square.adherent.noyau.dto.prestation;

import java.io.Serializable;

/**
 * Représente les paramètres d'envoi de relevé de prestations par mail.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ParamRelevePrestationMailDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 1124609055544542188L;

    /** Identifiant de la personne. */
    private Long idPersonne;

    private Long relevePrestationId;

    private boolean forceEnvoyeMail;

    /**
     * Retourne la valeur de relevePrestationId.
     * @return la valeur de relevePrestationId
     */
    public Long getRelevePrestationId() {
        return relevePrestationId;
    }

    /**
     * Définit la valeur de relevePrestationId.
     * @param relevePrestationId la nouvelle valeur de relevePrestationId
     */
    public void setRelevePrestationId(Long relevePrestationId) {
        this.relevePrestationId = relevePrestationId;
    }

    /**
     * Retourne la valeur de forceEnvoyeMail.
     * @return la valeur de forceEnvoyeMail
     */
    public boolean isForceEnvoyeMail() {
        return forceEnvoyeMail;
    }

    /**
     * Définit la valeur de forceEnvoyeMail.
     * @param forceEnvoyeMail la nouvelle valeur de forceEnvoyeMail
     */
    public void setForceEnvoyeMail(boolean forceEnvoyeMail) {
        this.forceEnvoyeMail = forceEnvoyeMail;
    }

    /**
     * Get the idPersonne value.
     * @return the idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Set the idPersonne value.
     * @param idPersonne the idPersonne to set
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }
}

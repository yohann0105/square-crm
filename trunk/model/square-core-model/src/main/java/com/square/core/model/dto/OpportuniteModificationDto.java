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

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto contenant les informations de modifications d'une opportunité.
 * @author cblanchard - SCUB
 */
public class OpportuniteModificationDto implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -1003229373541553096L;

    /** Identifiant de l'opportunité à modifier. */
    private Long idOpportunite;

    /** Ressource. */
    private DimensionRessourceDto ressource;

    /** Agence. */
    private IdentifiantLibelleDto agence;

    /**
     * Renvoi la valeur de ressource.
     * @return ressource
     */
    public DimensionRessourceDto getRessource() {
        return ressource;
    }

    /**
     * Modifie ressource.
     * @param ressource la nouvelle valeur de ressource
     */
    public void setRessource(DimensionRessourceDto ressource) {
        this.ressource = ressource;
    }

    /**
     * Renvoi la valeur de idOpportunite.
     * @return idOpportunite
     */
    public Long getIdOpportunite() {
        return idOpportunite;
    }

    /**
     * Modifie idOpportunite.
     * @param idOpportunite la nouvelle valeur de idOpportunite
     */
    public void setIdOpportunite(Long idOpportunite) {
        this.idOpportunite = idOpportunite;
    }

    /**
     * Renvoi la valeur de agence.
     * @return agence
     */
    public IdentifiantLibelleDto getAgence() {
        return agence;
    }

    /**
     * Modifie agence.
     * @param agence la nouvelle valeur de agence
     */
    public void setAgence(IdentifiantLibelleDto agence) {
        this.agence = agence;
    }

}

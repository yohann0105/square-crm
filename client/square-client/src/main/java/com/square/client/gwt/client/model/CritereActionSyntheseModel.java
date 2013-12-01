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
 * Model de critères de recherche pour la récupération d'actions synthèse.
 * @author cblanchard - SCUB
 */
public class CritereActionSyntheseModel implements IsSerializable {

    /** Identifiant de la personne pour recherche ses actions. */
    private Long idPersonne;

    /** Identifiant de l'opportunitée. */
    private Long idOpportunite;

    /** Booléen pour filtrer les actions sur leur date de création et ainsi ne pas récupérer les actions futur. */
    private Boolean filtrerDateCreation = false;

    /**
     * Renvoi la valeur de idPersonne.
     * @return idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Modifie idPersonne.
     * @param idPersonne la nouvelle valeur de idPersonne
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
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
     * Récupère la valeur de filtrerDateCreation.
     * @return la valeur de filtrerDateCreation
     */
    public Boolean getFiltrerDateCreation() {
        return filtrerDateCreation;
    }

    /**
     * Définit la valeur de filtrerDateCreation.
     * @param filtrerDateCreation la nouvelle valeur de filtrerDateCreation
     */
    public void setFiltrerDateCreation(Boolean filtrerDateCreation) {
        this.filtrerDateCreation = filtrerDateCreation;
    }

}

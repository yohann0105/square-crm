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
 * Modèle pour les permissions d'une personne sur certains onglets.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PersonnePermissionOngletModel implements IsSerializable {

    /** Booléen pour afficher ou pas l'onglet de l'espace client. */
    private Boolean hasEspaceClient;

    /** Booléen pour afficher ou pas l'onglet des contrats. */
    private Boolean hasContrats;

    /** Booléen pour afficher ou pas l'onglet des cotisations. */
    private Boolean isAfficherCotisation;

    /**
     * Récupère la valeur de hasContrats.
     * @return la valeur de hasContrats
     */
    public Boolean getHasContrats() {
        return hasContrats;
    }

    /**
     * Définit la valeur de hasContrats.
     * @param hasContrats la nouvelle valeur de hasContrats
     */
    public void setHasContrats(Boolean hasContrats) {
        this.hasContrats = hasContrats;
    }

    /**
     * Get the isAfficherCotisation value.
     * @return the isAfficherCotisation
     */
    public Boolean getIsAfficherCotisation() {
        return isAfficherCotisation;
    }

    /**
     * Set the isAfficherCotisation value.
     * @param isAfficherCotisation the isAfficherCotisation to set
     */
    public void setIsAfficherCotisation(Boolean isAfficherCotisation) {
        this.isAfficherCotisation = isAfficherCotisation;
    }

    /**
     * Get the hasEspaceClient value.
     * @return the hasEspaceClient
     */
    public Boolean getHasEspaceClient() {
        return hasEspaceClient;
    }

    /**
     * Set the hasEspaceClient value.
     * @param hasEspaceClient the hasEspaceClient to set
     */
    public void setHasEspaceClient(Boolean hasEspaceClient) {
        this.hasEspaceClient = hasEspaceClient;
    }
}

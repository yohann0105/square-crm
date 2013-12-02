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
 * DTO GWT représentant les infos de l'utilisateur connecté.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class InfosUtilisateurCourantModel implements IsSerializable {

    /** Identifiant. */
    private Long identifiant;

    /** Reseau. */
    private Long idReseau;

    /** Agence. */
    private Long idAgence;

    /** Service. */
    private Long idService;

    /** Indique si l'utilisateur appartient au role admin. */
    private Boolean roleAdmin;

    /**
     * Retourne la valeur de idAgence.
     * @return idAgence
     */
    public Long getIdAgence() {
        return idAgence;
    }

    /**
     * Définit la valeur de idAgence.
     * @param idAgence la nouvelle valeur de idAgence
     */
    public void setIdAgence(Long idAgence) {
        this.idAgence = idAgence;
    }

    /**
     * Retourne la valeur de identifiant.
     * @return identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Définit la valeur de identifiant.
     * @param identifiant la nouvelle valeur de identifiant
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Retourne la valeur de idReseau.
     * @return idReseau
     */
    public Long getIdReseau() {
        return idReseau;
    }

    /**
     * Définit la valeur de idReseau.
     * @param idReseau la nouvelle valeur de idReseau
     */
    public void setIdReseau(Long idReseau) {
        this.idReseau = idReseau;
    }

    /**
     * Get the roleAdmin value.
     * @return the roleAdmin
     */
    public Boolean getRoleAdmin() {
        return roleAdmin;
    }

    /**
     * Set the roleAdmin value.
     * @param roleAdmin the roleAdmin to set
     */
    public void setRoleAdmin(Boolean roleAdmin) {
        this.roleAdmin = roleAdmin;
    }

    /**
     * Get the idService value.
     * @return the idService
     */
    public Long getIdService() {
        return idService;
    }

    /**
     * Set the idService value.
     * @param idService the idService to set
     */
    public void setIdService(Long idService) {
        this.idService = idService;
    }

}

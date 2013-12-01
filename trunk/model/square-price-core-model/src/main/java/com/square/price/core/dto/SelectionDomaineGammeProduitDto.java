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
package com.square.price.core.dto;

import java.io.Serializable;

/**
 * Dto sur la selection des gammes produits par domaine.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class SelectionDomaineGammeProduitDto implements Serializable
{
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 3296633885872727230L;

    /**
     * Identifiant vetuste.
     */
    private Integer identifiantVetuste;

    /**
     * Identifiant categorie.
     */
    private Integer identifiantCategorie;

    /**
     * Identifiant reseau.
     */
    private Integer identifiantReseau;

    /**
     * Identifiant gestion.
     */
    private Integer identifiantGestion;

    /**
     * Get the identifiantCategorie value.
     * @return the identifiantCategorie
     */
    public Integer getIdentifiantCategorie() {
        return identifiantCategorie;
    }

    /**
     * Set the identifiantCategorie value.
     * @param identifiantCategorie the identifiantCategorie to set
     */
    public void setIdentifiantCategorie(Integer identifiantCategorie) {
        this.identifiantCategorie = identifiantCategorie;
    }

    /**
     * Get the identifiantGestion value.
     * @return the identifiantGestion
     */
    public Integer getIdentifiantGestion() {
        return identifiantGestion;
    }

    /**
     * Set the identifiantGestion value.
     * @param identifiantGestion the identifiantGestion to set
     */
    public void setIdentifiantGestion(Integer identifiantGestion) {
        this.identifiantGestion = identifiantGestion;
    }

    /**
     * Get the identifiantReseau value.
     * @return the identifiantReseau
     */
    public Integer getIdentifiantReseau() {
        return identifiantReseau;
    }

    /**
     * Set the identifiantReseau value.
     * @param identifiantReseau the identifiantReseau to set
     */
    public void setIdentifiantReseau(Integer identifiantReseau) {
        this.identifiantReseau = identifiantReseau;
    }

    /**
     * Get the identifiantVetuste value.
     * @return the identifiantVetuste
     */
    public Integer getIdentifiantVetuste() {
        return identifiantVetuste;
    }

    /**
     * Set the identifiantVetuste value.
     * @param identifiantVetuste the identifiantVetuste to set
     */
    public void setIdentifiantVetuste(Integer identifiantVetuste) {
        this.identifiantVetuste = identifiantVetuste;
    }
}

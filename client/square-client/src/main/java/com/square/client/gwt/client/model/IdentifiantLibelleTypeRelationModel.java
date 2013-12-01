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
/**
 * 
 */
package com.square.client.gwt.client.model;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

/**
 * Identifiant Libelle Dto specifique pour la gestion des types de relation.
 * @author Goumard Stephane (stephane.goumard@scub) - SCUB
 */
public class IdentifiantLibelleTypeRelationModel extends IdentifiantLibelleGwt {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -3400235732055168917L;

    /**
     * Libelle Inverse.
     */
    private String libelleInverse;

    /**
     * Retourne le libelle inverse.
     * @return the libelleInverse
     */
    public String getLibelleInverse() {
        return libelleInverse;
    }

    /**
     * Fixe le libelle inverse.
     * @param libelleInverse the libelleInverse to set
     */
    public void setLibelleInverse(String libelleInverse) {
        this.libelleInverse = libelleInverse;
    }
}

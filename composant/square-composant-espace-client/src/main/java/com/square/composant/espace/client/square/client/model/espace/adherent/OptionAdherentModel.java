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
package com.square.composant.espace.client.square.client.model.espace.adherent;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO encapsulant une option.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class OptionAdherentModel implements IsSerializable {

    /**
     * Identifiant de l'option.
     */
    private Long id;

    /**
     * Identifiant/libellé du type d'option.
     */
    private IdentifiantLibelleGwt type;

    /**
     * Indique si l'option est active ou non.
     */
    private boolean active;

    /**
     * Date de modification de l'option.
     */
    private String dateModification;

    /**
     * Getter function.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter function.
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Getter function.
     * @return the dateModification
     */
    public String getDateModification() {
        return dateModification;
    }

    /**
     * Setter function.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Setter function.
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Setter function.
     * @param dateModification the dateModification to set
     */
    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Getter function.
     * @return the type
     */
    public IdentifiantLibelleGwt getType() {
        return type;
    }

    /**
     * Setter function.
     * @param type the type to set
     */
    public void setType(IdentifiantLibelleGwt type) {
        this.type = type;
    }

}

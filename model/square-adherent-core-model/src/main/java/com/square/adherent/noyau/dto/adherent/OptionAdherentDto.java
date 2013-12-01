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
package com.square.adherent.noyau.dto.adherent;

import java.io.Serializable;
import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO encapsulant une option.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class OptionAdherentDto implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -5517063889174876528L;

    /**
     * Identifiant de l'option.
     */
    private Long id;

    /**
     * Identifiant/libellé du type d'option.
     */
    private IdentifiantLibelleDto type;

    /**
     * Indique si l'option est active ou non.
     */
    private boolean active;

    /**
     * Date de modification de l'option.
     */
    private Calendar dateModification;

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
    public Calendar getDateModification() {
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
    public void setDateModification(Calendar dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Getter function.
     * @return the type
     */
    public IdentifiantLibelleDto getType() {
        return type;
    }

    /**
     * Setter function.
     * @param type the type to set
     */
    public void setType(IdentifiantLibelleDto type) {
        this.type = type;
    }

}

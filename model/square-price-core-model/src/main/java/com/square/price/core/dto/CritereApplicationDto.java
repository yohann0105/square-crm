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
 * Information sur le critere par rapport à l'application appelante.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class CritereApplicationDto implements Serializable
{
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 9017693979143240804L;

    /**
     * Information modifiable.
     */
    private Boolean modifiable;

    /**
     * Information visible.
     */
    private Boolean visible;

    /**
     * Information obligatoire.
     */
    private Boolean obligatoire;

    /**
     * Get the modifiable value.
     * @return the modifiable
     */
    public Boolean getModifiable() {
        return modifiable;
    }

    /**
     * Set the modifiable value.
     * @param modifiable the modifiable to set
     */
    public void setModifiable(Boolean modifiable) {
        this.modifiable = modifiable;
    }

    /**
     * Get the obligatoire value.
     * @return the obligatoire
     */
    public Boolean getObligatoire() {
        return obligatoire;
    }

    /**
     * Set the obligatoire value.
     * @param obligatoire the obligatoire to set
     */
    public void setObligatoire(Boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

    /**
     * Get the visible value.
     * @return the visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * Set the visible value.
     * @param visible the visible to set
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}

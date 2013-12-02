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
package com.square.fusion.api.dto;

import java.io.Serializable;

/**
 * DTO pour des paramètres de la fusion.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ParametresFusionDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -8100094162701272662L;

    /** Personne source. */
    private PersonneSourceFusionDto personneSource;

    /** Personne cible. */
    private PersonneCibleFusionDto personneCible;

    /**
     * Récupère la valeur de personneSource.
     * @return la valeur de personneSource
     */
    public PersonneSourceFusionDto getPersonneSource() {
        return personneSource;
    }

    /**
     * Définit la valeur de personneSource.
     * @param personneSource la nouvelle valeur de personneSource
     */
    public void setPersonneSource(PersonneSourceFusionDto personneSource) {
        this.personneSource = personneSource;
    }

    /**
     * Récupère la valeur de personneCible.
     * @return la valeur de personneCible
     */
    public PersonneCibleFusionDto getPersonneCible() {
        return personneCible;
    }

    /**
     * Définit la valeur de personneCible.
     * @param personneCible la nouvelle valeur de personneCible
     */
    public void setPersonneCible(PersonneCibleFusionDto personneCible) {
        this.personneCible = personneCible;
    }

}

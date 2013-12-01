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
package com.square.core.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entité persistante de la civilité de la personne.
 * @author cblanchard - SCUB
 */
@Entity
@Table(name = "DIM_PERSONNE_CIVILITE")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "PERSONNE_CIVILITE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "PERSONNE_CIVILITE_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "PERSONNE_CIVILITE_EID", unique = true)),
    @AttributeOverride(name = "libelle", column = @Column(name = "PERSONNE_CIVILITE_LIB", nullable = false)),
    @AttributeOverride(name = "ordre", column = @Column(name = "PERSONNE_CIVILITE_ORDRE", nullable = false)),
    @AttributeOverride(name = "visible", column = @Column(name = "PERSONNE_CIVILITE_VISIBLE", nullable = false)) })
public class PersonneCivilite extends ModelDim {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -2572036880496717816L;

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PersonneCivilite)) {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

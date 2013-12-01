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
package com.square.tarificateur.noyau.model.opportunite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Modèle d'une source de ligne de devis (Demande de devis, ...).
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */

@Entity
@Table(name = "DIM_SOURCE_LIGNE_DEVIS")
public class SourceLigneDevis extends BaseModel {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -2102953272203247772L;

    /** Libellé. */
    @Column(name = "libelle")
    private String libelle;

    /**
     * Récupère la valeur de libelle.
     * @return la valeur de libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Définit la valeur de libelle.
     * @param libelle la nouvelle valeur de libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other)
    {
        if (other == null || !(other instanceof SourceLigneDevis))
        {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        return super.hashCode();
    }
}

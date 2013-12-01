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
package com.square.tarificateur.noyau.model.paiement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Modèle du jour de paiement.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table(name = "DIM_JOUR_PAIEMENT")
public class JourPaiement extends BaseModel {

    /** serialVersionUID. */
    private static final long serialVersionUID = -435693956007279335L;

    /** Libellé. */
    @Column(name = "LIBELLE")
    private String libelle;

    /** Ordre. */
    @Column(name = "ORDRE")
    private Long ordre;

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
     * Récupère la valeur de ordre.
     * @return la valeur de ordre
     */
    public Long getOrdre() {
        return ordre;
    }

    /**
     * Définit la valeur de ordre.
     * @param ordre la nouvelle valeur de ordre
     */
    public void setOrdre(Long ordre) {
        this.ordre = ordre;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof JourPaiement)) { return false; }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

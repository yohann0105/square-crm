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
package com.square.tarificateur.noyau.model.personne;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Modèle d'une adresse.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_ADRESSE")
public class Adresse extends BaseModel {

    /** serialVersionUID. */
    private static final long serialVersionUID = -1046131783327541567L;

    /** EID de l'adresse (Square). */
    @Column(name = "ADRESSE_EID")
    private Long eidAdresse;

    /** EID du code postal - commune (Square). */
    // TODO mettre en nullable à false après mise en prod du libellé acheminement
    @Column(name = "CODE_POSTAL_COMMUNE_EID")
    private Long eidCodePostalCommune;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Adresse)) {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Récupère la valeur de eidAdresse.
     * @return la valeur de eidAdresse
     */
    public Long getEidAdresse() {
        return eidAdresse;
    }

    /**
     * Définit la valeur de eidAdresse.
     * @param eidAdresse la nouvelle valeur de eidAdresse
     */
    public void setEidAdresse(Long eidAdresse) {
        this.eidAdresse = eidAdresse;
    }

    /**
     * Récupère la valeur eidCodePostalCommune.
     * @return the eidCodePostalCommune
     */
    public Long getEidCodePostalCommune() {
        return eidCodePostalCommune;
    }

    /**
     * Définit la valeur de eidCodePostalCommune.
     * @param eidCodePostalCommune the eidCodePostalCommune to set
     */
    public void setEidCodePostalCommune(Long eidCodePostalCommune) {
        this.eidCodePostalCommune = eidCodePostalCommune;
    }

}

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
package com.square.composant.tarificateur.square.client.model.age;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model permettant de calculer l'âge d'une personne à partir d'une date de calcul et d'une date de naissance.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class DateCalculAgeModel implements IsSerializable {

    /** Date de calcul. */
    private String dateCalcul;

    /** Date de naissance. */
    private String dateNaissance;

    /**
     * Retourne la valeur de dateCalcul.
     * @return la valeur de dateCalcul
     */
    public String getDateCalcul() {
        return dateCalcul;
    }

    /**
     * Définit la valeur de dateCalcul.
     * @param dateCalcul la nouvelle valeur de dateCalcul
     */
    public void setDateCalcul(String dateCalcul) {
        this.dateCalcul = dateCalcul;
    }

    /**
     * Retourne la valeur de dateNaissance.
     * @return la valeur de dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définit la valeur de dateNaissance.
     * @param dateNaissance la nouvelle valeur de dateNaissance
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DateCalculAgeModel) {
            final DateCalculAgeModel dateCalculAge = (DateCalculAgeModel) obj;
            return this.dateCalcul.equals(dateCalculAge.getDateCalcul()) && this.dateNaissance.equals(dateCalculAge.getDateNaissance());
        } else {
            return super.equals(obj);
        }
    }

    /** {@inheritDoc} */
    public int hashCode() {
        return super.hashCode();
    }

}

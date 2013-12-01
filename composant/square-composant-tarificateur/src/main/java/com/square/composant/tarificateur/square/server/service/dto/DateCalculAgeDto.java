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
package com.square.composant.tarificateur.square.server.service.dto;

import java.util.Calendar;

/**
 * DTO permettant de calculer l'âge d'une personne à partir d'une date de calcul et d'une date de naissance.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class DateCalculAgeDto {

    /** Date de calcul. */
    private Calendar dateCalcul;

    /** Date de naissance. */
    private Calendar dateNaissance;

    /**
     * Retourne la valeur de dateCalcul.
     * @return la valeur de dateCalcul
     */
    public Calendar getDateCalcul() {
        return dateCalcul;
    }

    /**
     * Définit la valeur de dateCalcul.
     * @param dateCalcul la nouvelle valeur de dateCalcul
     */
    public void setDateCalcul(Calendar dateCalcul) {
        this.dateCalcul = dateCalcul;
    }

    /**
     * Retourne la valeur de dateNaissance.
     * @return la valeur de dateNaissance
     */
    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définit la valeur de dateNaissance.
     * @param dateNaissance la nouvelle valeur de dateNaissance
     */
    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /** {@inheritDoc} */
    public boolean equals(Object obj) {
        if (obj instanceof DateCalculAgeDto) {
            final DateCalculAgeDto dateCalculAgeDto = (DateCalculAgeDto) obj;
            return this.dateCalcul.equals(dateCalculAgeDto.getDateCalcul()) && this.dateNaissance.equals(dateCalculAgeDto.getDateNaissance());
        }
        else {
            return super.equals(obj);
        }
    }

    /** {@inheritDoc} */
    public int hashCode() {
        return super.hashCode();
    }

}

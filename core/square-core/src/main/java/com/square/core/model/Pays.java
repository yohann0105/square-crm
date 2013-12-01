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
/**
 * 
 */
package com.square.core.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

/**
 * Entité persistante modélisant les pays.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
@Entity
@Table(name = "DIM_PAYS")
@Indexed
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "PAYS_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "PAYS_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "PAYS_EID", unique = true)),
    @AttributeOverride(name = "libelle", column = @Column(name = "PAYS_LIB", nullable = false)),
    @AttributeOverride(name = "ordre", column = @Column(name = "PAYS_ORDRE", nullable = false)),
    @AttributeOverride(name = "bool", column = @Column(name = "PAYS_AUTRES", nullable = false)),
    @AttributeOverride(name = "visible", column = @Column(name = "PAYS_VISIBLE", nullable = false)) })
public class Pays extends BooleanModelDim {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 6168216434288263027L;

    /**
     * Indicatif téléphonique du pays.
     */
    @Column(name = "PAYS_INDICATIF_TELEPHONE", nullable = false)
    private int indicatifTelephone;

    /**
     * Format téléphonique du pays.
     */
    @Column(name = "PAYS_FORMAT_TELEPHONE")
    private String formatTelephone;

    /**
     * Renvoi la valeur de indicatifTelephone.
     * @return indicatifTelephone
     */
    public int getIndicatifTelephone() {
        return indicatifTelephone;
    }

    /**
     * Modifie indicatifTelephone.
     * @param indicatifTelephone la nouvelle valeur de indicatifTelephone
     */
    public void setIndicatifTelephone(int indicatifTelephone) {
        this.indicatifTelephone = indicatifTelephone;
    }

    /**
     * Renvoie le format de téléphone.
     * @return valeur de formatTelephone
     */
    public String getFormatTelephone() {
        return formatTelephone;
    }

    /**
     * Modifie le format de téléphone.
     * @param formatTelephone nouvelle valeur dans formatTelephone
     */
    public void setFormatTelephone(String formatTelephone) {
        this.formatTelephone = formatTelephone;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Pays)) {
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

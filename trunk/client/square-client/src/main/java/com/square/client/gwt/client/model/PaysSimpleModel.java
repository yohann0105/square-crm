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
package com.square.client.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Objet models pour l'entité pays.
 * @author Farh ZARROUK (farh.zarrouk@gmail.com) - SCUB
 */

public class PaysSimpleModel implements IsSerializable {

    /** Identifiant. */
    private Long id;

    /** Libellé. */
    private String libelle;

    /** Indicatif du téléphone. */
    private int indicatifTelephone;

    /** Format du téléphone. */
    private String formatTelephone;

    /**
     * Renvoie l'identifiant du pays.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie l'identifiant du pays.
     * @param id dans id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Renvoie le libellé du pays.
     * @return libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Modifie le libellé du pays.
     * @param libelle dans libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Renvoie l'indicatif téléphonique du pays.
     * @return indicatifTelephone
     */
    public int getIndicatifTelephone() {
        return indicatifTelephone;
    }

    /**
     * Modifie l'indicatif téléphonique du pays.
     * @param indicatifTelephone dans indicatifTelephone
     */
    public void setIndicatifTelephone(int indicatifTelephone) {
        this.indicatifTelephone = indicatifTelephone;
    }

    /**
     * Renvoie le format téléphonique du pays.
     * @return indicatifTelephone
     */
    public String getFormatTelephone() {
        return formatTelephone;
    }

    /**
     * Modifie le format téléphonique du pays.
     * @param formatTelephone dans formatTelephone
     */
    public void setFormatTelephone(String formatTelephone) {
        this.formatTelephone = formatTelephone;
    }
}

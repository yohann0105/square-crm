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
package com.square.adherent.noyau.dto.magazine;

import java.io.Serializable;

/**
 * DTO représentant un magazine.
 * @author nnadeau - SCUB
 */
public class MagazineDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -7762829088159893462L;

    /** Identifiant Magazine. */
    private Long idMagazine;

    /** Flag d'envoi à tous les souscripteurs à l'envoi. */
    private Boolean envoye = Boolean.FALSE;

    /**
     * Constructeur.
     */
    public MagazineDto() { }

    /**
     * Constructeur.
     * @param idMagazine identifiant du magazine
     */
    public MagazineDto(Long idMagazine) {
        this.idMagazine = idMagazine;
    }

    /**
     * Retourne la valeur de idMagazine.
     * @return la valeur de idMagazine
     */
    public Long getIdMagazine() {
        return idMagazine;
    }

    /**
     * Définit la valeur de idMagazine.
     * @param idMagazine la nouvelle valeur de idMagazine
     */
    public void setIdMagazine(Long idMagazine) {
        this.idMagazine = idMagazine;
    }

    /**
     * Retourne la valeur de envoye.
     * @return la valeur de envoye
     */
    public Boolean getEnvoye() {
        return envoye;
    }

    /**
     * Définit la valeur de envoye.
     * @param envoye la nouvelle valeur de envoye
     */
    public void setEnvoye(Boolean envoye) {
        this.envoye = envoye;
    }

}

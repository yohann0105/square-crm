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
package com.square.core.model.dto;

import java.io.Serializable;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto pour gérer un email.
 * @author cblanchard - SCUB
 */
public class EmailDto implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -3437286906065149626L;

    /**
     * Identifiant de l'email.
     */
    private Long identifiant;

    /**
     * Identifiant extérieur de l'email.
     */
    private String idext;

    /**
     * Adresse email.
     */
    private String adresse;

    /**
     * Nature de l'email.
     */
    private IdentifiantLibelleDto natureEmail;

    /** Identifiant du porteur de l'email. */
    private Long porteurUid;

    /**
     * Renvoi la valeur de identifiant.
     * @return identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Modifie identifiant.
     * @param identifiant la nouvelle valeur de identifiant
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Renvoi la valeur de idext.
     * @return idext
     */
    public String getIdext() {
        return idext;
    }

    /**
     * Modifie idext.
     * @param idext la nouvelle valeur de idext
     */
    public void setIdext(String idext) {
        this.idext = idext;
    }

    /**
     * Renvoi la valeur de adresse.
     * @return adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Modifie adresse.
     * @param adresse la nouvelle valeur de adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Renvoi la valeur de natureEmail.
     * @return natureEmail
     */
    public IdentifiantLibelleDto getNatureEmail() {
        return natureEmail;
    }

    /**
     * Modifie natureEmail.
     * @param natureEmail la nouvelle valeur de natureEmail
     */
    public void setNatureEmail(IdentifiantLibelleDto natureEmail) {
        this.natureEmail = natureEmail;
    }

    /**
     * Récupère la valeur de porteurUid.
     * @return la valeur de porteurUid
     */
    public Long getPorteurUid() {
        return porteurUid;
    }

    /**
     * Définit la valeur de porteurUid.
     * @param porteurUid la nouvelle valeur de porteurUid
     */
    public void setPorteurUid(Long porteurUid) {
        this.porteurUid = porteurUid;
    }

}

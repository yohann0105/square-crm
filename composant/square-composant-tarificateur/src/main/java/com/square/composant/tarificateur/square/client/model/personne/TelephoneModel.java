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
package com.square.composant.tarificateur.square.client.model.personne;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model Gwt modélisant un numéro de téléphone.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class TelephoneModel implements IsSerializable {

    /** Identifiant du numéro de téléphone. */
    private Long idTelephone;

    /** EID du numéro de téléphone. */
    private Long eidTelephone;

    /** Code AIA du téléphone. */
    private String codeAia;

    /** Numéro de téléphone. */
    private String numTelephone;

    /**
     * Getter function.
     * @return the idTelephone
     */
    public Long getIdTelephone() {
        return idTelephone;
    }

    /**
     * Getter function.
     * @return the eidTelephone
     */
    public Long getEidTelephone() {
        return eidTelephone;
    }

    /**
     * Getter function.
     * @return the numTelephone
     */
    public String getNumTelephone() {
        return numTelephone;
    }

    /**
     * Setter function.
     * @param idTelephone the idTelephone to set
     */
    public void setIdTelephone(Long idTelephone) {
        this.idTelephone = idTelephone;
    }

    /**
     * Setter function.
     * @param eidTelephone the eidTelephone to set
     */
    public void setEidTelephone(Long eidTelephone) {
        this.eidTelephone = eidTelephone;
    }

    /**
     * Setter function.
     * @param numTelephone the numTelephone to set
     */
    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    /**
     * Récupère la valeur de codeAia.
     * @return la valeur de codeAia
     */
    public String getCodeAia() {
        return codeAia;
    }

    /**
     * Définit la valeur de codeAia.
     * @param codeAia la nouvelle valeur de codeAia
     */
    public void setCodeAia(String codeAia) {
        this.codeAia = codeAia;
    }

}

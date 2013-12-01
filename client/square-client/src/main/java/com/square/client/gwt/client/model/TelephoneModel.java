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

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Dto sur les téléphones.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class TelephoneModel implements IsSerializable {

    /** Identifiant du téléphone. */
    private Long id;

    /** Identifiant extérieur du téléphone. */
    private String idext;

    /** Numéro du téléphone. */
    private String numero;

    /** Nature du téléphone (fixe, portable). */
    private IdentifiantLibelleGwt nature;

    /** Pays d'origine du numéro. */
    private PaysSimpleModel pays;

    /** Identifiant du porteur du téléphone. */
    private Long porteurUid;

    /**
     * Renvoi la valeur de id.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Renvoi la valeur de numero.
     * @return numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Modifie numero.
     * @param numero la nouvelle valeur de numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Renvoi la valeur de nature.
     * @return nature
     */
    public IdentifiantLibelleGwt getNature() {
        return nature;
    }

    /**
     * Modifie nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(IdentifiantLibelleGwt nature) {
        this.nature = nature;
    }

    /**
     * Renvoi la valeur de pays.
     * @return pays
     */
    public PaysSimpleModel getPays() {
        return pays;
    }

    /**
     * Modifie pays.
     * @param pays la nouvelle valeur de pays
     */
    public void setPays(PaysSimpleModel pays) {
        this.pays = pays;
    }

    /**
     * Get the value of idext.
     * @return the idext
     */
    public String getIdext() {
        return idext;
    }

    /**
     * Set the value of idext.
     * @param idext the idext to set
     */
    public void setIdext(String idext) {
        this.idext = idext;
    }

    /**
     * Get the value of porteurUid.
     * @return the porteurUid
     */
    public Long getPorteurUid() {
        return porteurUid;
    }

    /**
     * Set the value of porteurUid.
     * @param porteurUid the porteurUid to set
     */
    public void setPorteurUid(Long porteurUid) {
        this.porteurUid = porteurUid;
    }
}

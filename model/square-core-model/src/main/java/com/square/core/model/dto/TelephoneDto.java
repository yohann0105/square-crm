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
 * Dto sur les téléphones.
 * @author cblanchard - SCUB
 */
public class TelephoneDto implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -6874004270962317312L;

    /**
     * Identifiant du téléphone.
     */
    private Long id;

    /**
     * Identifiant externe de l'adresse.
     */
    private String idext;

    /**
     * Numéro du téléphone.
     */
    private String numero;

    /**
     * Nature du téléphone (fixe, portable).
     */
    private IdentifiantLibelleDto nature;

    /**
     * Pays d'origine du numéro.
     */
    private PaysSimpleDto pays;

    /**
     * Indique si telephone doit être supprimé.
     */
    private Boolean supprime;

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
    public IdentifiantLibelleDto getNature() {
        return nature;
    }

    /**
     * Modifie nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(IdentifiantLibelleDto nature) {
        this.nature = nature;
    }

    /**
     * Renvoi la valeur de pays.
     * @return pays
     */
    public PaysSimpleDto getPays() {
        return pays;
    }

    /**
     * Modifie pays.
     * @param pays la nouvelle valeur de pays
     */
    public void setPays(PaysSimpleDto pays) {
        this.pays = pays;
    }

    /**
     * Récupère la valeur de idext.
     * @return la valeur de idext
     */
    public String getIdext() {
        return idext;
    }

    /**
     * Définit la valeur de idext.
     * @param idext la nouvelle valeur de idext
     */
    public void setIdext(String idext) {
        this.idext = idext;
    }

    /**
     * Récupère la valeur de supprime.
     * @return la valeur de supprime
     */
    public Boolean getSupprime() {
        return supprime;
    }

    /**
     * Définit la valeur de supprime.
     * @param supprime la nouvelle valeur de supprime
     */
    public void setSupprime(Boolean supprime) {
        this.supprime = supprime;
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

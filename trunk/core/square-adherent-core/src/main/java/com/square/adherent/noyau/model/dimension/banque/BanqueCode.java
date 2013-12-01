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
package com.square.adherent.noyau.model.dimension.banque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entité persistante modélisant un code de banque.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
@Entity
@Table(name = "DIM_BANQUE_CODE")
public class BanqueCode {

    /** Identifiant. */
    @Id
    @Column(name = "BANQUE_CODE_UID")
    private Long id;

    /** Identifiant externe. */
    @Column(name = "BANQUE_CODE_EID")
    private String eid;

    /** Libelle. */
    @Column(name = "BANQUE_CODE_LIB")
    private String libelle;

    /**
     * Récupère la valeur de id.
     * @return la valeur de id
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère la valeur de eid.
     * @return la valeur de eid
     */
    public String getEid() {
        return eid;
    }

    /**
     * Définit la valeur de eid.
     * @param eid la nouvelle valeur de eid
     */
    public void setEid(String eid) {
        this.eid = eid;
    }

    /**
     * Récupère la valeur de libelle.
     * @return la valeur de libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Définit la valeur de libelle.
     * @param libelle la nouvelle valeur de libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

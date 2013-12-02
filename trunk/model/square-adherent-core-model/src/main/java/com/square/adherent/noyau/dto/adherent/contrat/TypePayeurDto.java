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
package com.square.adherent.noyau.dto.adherent.contrat;

import java.io.Serializable;

/**
 * DTO d'un type de payeur.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class TypePayeurDto implements Serializable {

    private static final long serialVersionUID = 2810854585160740371L;

    /** Identifiant. */
    private Long id;

    /** Eid. */
    private String eid;

    /** Libelle. */
    private String libelle;

    /** Eid bareme 1. */
    private String bareme1Eid;

    /** Zone bareme 1. */
    private Integer bareme1Zone;

    /** Eid bareme 2. */
    private String bareme2Eid;

    /** Zone bareme 2. */
    private Integer bareme2Zone;

    /**
     * Constructeur paramétré.
     * @param id id
     * @param eid eid
     * @param libelle libelle
     * @param bareme1Eid bareme1Eid
     * @param bareme1Zone bareme1Zone
     * @param bareme2Eid bareme2Eid
     * @param bareme2Zone bareme2Zone
     */
    public TypePayeurDto(Long id, String eid, String libelle, String bareme1Eid, Integer bareme1Zone, String bareme2Eid, Integer bareme2Zone) {
        super();
        this.id = id;
        this.eid = eid;
        this.libelle = libelle;
        this.bareme1Eid = bareme1Eid;
        this.bareme1Zone = bareme1Zone;
        this.bareme2Eid = bareme2Eid;
        this.bareme2Zone = bareme2Zone;
    }

    /**
     * Get the value of id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of libelle.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Set the value of libelle.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Get the value of bareme1Eid.
     * @return the bareme1Eid
     */
    public String getBareme1Eid() {
        return bareme1Eid;
    }

    /**
     * Set the value of bareme1Eid.
     * @param bareme1Eid the bareme1Eid to set
     */
    public void setBareme1Eid(String bareme1Eid) {
        this.bareme1Eid = bareme1Eid;
    }

    /**
     * Get the value of bareme1Zone.
     * @return the bareme1Zone
     */
    public Integer getBareme1Zone() {
        return bareme1Zone;
    }

    /**
     * Set the value of bareme1Zone.
     * @param bareme1Zone the bareme1Zone to set
     */
    public void setBareme1Zone(Integer bareme1Zone) {
        this.bareme1Zone = bareme1Zone;
    }

    /**
     * Get the value of bareme2Eid.
     * @return the bareme2Eid
     */
    public String getBareme2Eid() {
        return bareme2Eid;
    }

    /**
     * Set the value of bareme2Eid.
     * @param bareme2Eid the bareme2Eid to set
     */
    public void setBareme2Eid(String bareme2Eid) {
        this.bareme2Eid = bareme2Eid;
    }

    /**
     * Get the value of bareme2Zone.
     * @return the bareme2Zone
     */
    public Integer getBareme2Zone() {
        return bareme2Zone;
    }

    /**
     * Set the value of bareme2Zone.
     * @param bareme2Zone the bareme2Zone to set
     */
    public void setBareme2Zone(Integer bareme2Zone) {
        this.bareme2Zone = bareme2Zone;
    }

    /**
     * Get the value of eid.
     * @return the eid
     */
    public String getEid() {
        return eid;
    }

    /**
     * Set the value of eid.
     * @param eid the eid to set
     */
    public void setEid(String eid) {
        this.eid = eid;
    }
}

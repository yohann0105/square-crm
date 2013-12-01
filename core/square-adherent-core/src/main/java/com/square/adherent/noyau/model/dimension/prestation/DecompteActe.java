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
package com.square.adherent.noyau.model.dimension.prestation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entité persistante modélisant un Acte du décompte.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
@Entity
@Table(name = "DIM_DECOMPTE_ACTE")
public class DecompteActe implements Serializable {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -4600976038595845787L;

    /** Identifiant. */
    @Id
    @Column(name = "DECOMPTE_ACTE_UID")
    private Long id;

    /** Identifiant. */
    @Column(name = "DECOMPTE_ACTE_EID")
    private String eid;

    /** Libelle de l'acte. */
    @Column(name = "DECOMPTE_ACTE_LIB")
    private String libelle;

    /** Ordre. */
    @Column(name = "DECOMPTE_ACTE_ORDRE")
    private Integer ordre;

    /** Ordre. */
    @Column(name = "DECOMPTE_ACTE_VISIBLE")
    private Boolean visible;

    /**
     * Recuperer la valeur.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Fixer la valeur.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Recuperer la valeur.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Fixer la valeur.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Recuperer la valeur.
     * @return the ordre
     */
    public Integer getOrdre() {
        return ordre;
    }

    /**
     * Fixer la valeur.
     * @param ordre the ordre to set
     */
    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    /**
     * Recuperer la valeur.
     * @return the visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * Fixer la valeur.
     * @param visible the visible to set
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * Get the eid value.
     * @return the eid
     */
    public String getEid() {
        return eid;
    }

    /**
     * Set the eid value.
     * @param eid the eid to set
     */
    public void setEid(String eid) {
        this.eid = eid;
    }
}

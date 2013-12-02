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
package com.square.adherent.noyau.model.dimension;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Super Classe abtraite pour les tables de dimension.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
@MappedSuperclass
public abstract class ModelDim implements Serializable {

    private static final long serialVersionUID = -3928943597981898040L;

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "LIBELLE")
    private String libelle;

    @Column(name = "ORDRE")
    private Integer ordre;

    @Column(name = "VISIBLE")
    private Boolean visible;

    /**
     * Getter function.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter function.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Getter function.
     * @return the ordre
     */
    public Integer getOrdre() {
        return ordre;
    }

    /**
     * Setter function.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Setter function.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Setter function.
     * @param ordre the ordre to set
     */
    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ModelDim)) {
            return false;
        }
        final ModelDim other = (ModelDim) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /**
     * Method utlilitaire pour aider au methodes equals des sous classes.
     * @param other l'ature objet.
     * @return true si egale false sinon.
     */
    protected boolean equalsUtil(Object other) {
        if (other == null || !(other instanceof ModelDim)) {
            return false;
        }
        final ModelDim otherObj = (ModelDim) other;
        return this.getId() == null && otherObj.getId() != null ? false : this.getId() != null && otherObj.getId() == null ? false : this.getId() == null
            && otherObj.getId() == null ? this == other : this.getId().equals(otherObj.getId());
    }

    /**
     * Récupère la valeur de visible.
     * @return la valeur de visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * Définit la valeur de visible.
     * @param visible la nouvelle valeur de visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}

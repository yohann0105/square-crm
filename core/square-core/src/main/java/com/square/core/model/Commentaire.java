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
package com.square.core.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;
import org.scub.foundation.framework.core.model.BaseModel;

import com.square.core.model.Ressources.Ressource;

/**
 * Commentaire dans action.
 * @author Clément Lavaud - SCUB
 */
@Entity
@Table(name = "DATA_COMMENTAIRE")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "COMMENTAIRE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "COMMENTAIRE_VERSION", unique = false)) })

public class Commentaire extends BaseModel {

	private static final long serialVersionUID = -2999611227432489728L;

    @Column(name = "COMMENTAIRE_DESCRIPTIF")
    @Type(type = "text")
    private String descriptif;

    /**
     * La ressource du commentaire.
     */
	@ManyToOne
    @JoinColumn(name = "RESSOURCE_UID")
    @ForeignKey(name = "FK_DATA_COMMENTAIRE_DATA_RESSOURCE_RESSOURCE_UID")
    private Ressource ressource;

    /**
     * Retourne la valeur de descriptif.
     * @return the descriptif
     */
	public String getDescriptif() {
		return descriptif;
	}

    /**
     * Modifie la valeur de descriptif.
     * @param descriptif the descriptif to set
     */
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

    /**
     * Retourne la valeur de ressource.
     * @return the ressource
     */
	public Ressource getRessource() {
		return ressource;
	}

    /**
     * Modifie la valeur de ressource.
     * @param ressource the ressource to set
     */
	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Commentaire)) {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

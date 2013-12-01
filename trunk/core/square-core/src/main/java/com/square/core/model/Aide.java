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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Entité persistante modélisant les aides du gestionnaire d'aide.
 * @author KsouriMohamed Ali - SCUB
 */
@Entity
@Table(name = "DATA_AIDE")
public class Aide extends BaseModel {
    /**
     * serial vesrion uid.
     */
    private static final long serialVersionUID = -5951128496545062497L;

    /**
     * id de composant relatif à l'aide.
     */
    @Column(name = "IDCOMPOSANT", nullable = false, unique = true)
    private Long idComposant;

    /**
     * texte de l'aide.
     */
    @Column(name = "TEXTE")
    @Type(type = "text")
    private String text;

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Aide)) {
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

    /**
     * Modifie le texte de l'aide.
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Renvoie le texte de l'aide.
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Modifie l'identifiant du composant.
     * @param idComposant the idComposant to set
     */
    public void setIdComposant(Long idComposant) {
        this.idComposant = idComposant;
    }

    /**
     * Renvoie l'identifiant du composant.
     * @return the idComposant
     */
    public Long getIdComposant() {
        return idComposant;
    }
}

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

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

/**
 * Entité persistante modélisant les codes postaux.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
@Entity
@Table(name = "DIM_CODE_POSTAL")
@Indexed
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "CODE_POSTAL_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "CODE_POSTAL_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "CODE_POSTAL_EID", unique = true)) })
public class CodePostal extends ModelEID {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 7030011450826092988L;

    /**
     * Le numéro du code postal.
     */
    @Column(name = "CODE_POSTAL_NUMERO", nullable = false, unique = true)
    @Field(index = Index.TOKENIZED)
    private String codePostal;

    /**
     * Ordre.
     */
    @Column(name = "CODE_POSTAL_ORDRE", nullable = false)
    private int ordre;

    /**
     * Visibilité.
     */
    @Column(name = "CODE_POSTAL_VISIBLE", nullable = false)
    private boolean visible;

    @OneToMany(mappedBy = "codePostal")
    private Set<CodePostalCommune> communes;

    /**
     * Retourne la valeur de codePostal.
     * @return the codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Modifie la valeur de codePostal.
     * @param codePostal the codePostal to set
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Renvoi la valeur de ordre.
     * @return ordre
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * Modifie ordre.
     * @param ordre la nouvelle valeur de ordre
     */
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    /**
     * Renvoi la valeur de visible.
     * @return visible
     */
    public boolean getVisible() {
        return visible;
    }

    /**
     * Modifie visible.
     * @param visible la nouvelle valeur de visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CodePostal)) {
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
     * Renvoi la valeur de communes.
     * @return communes
     */
    public Set<CodePostalCommune> getCommunes() {
        return communes;
    }

    /**
     * Modifie communes.
     * @param communes la nouvelle valeur de communes
     */
    public void setCommunes(Set<CodePostalCommune> communes) {
        this.communes = communes;
    }
}

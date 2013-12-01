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
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Entité persistante modélisant la table de jointure des codes postaux - communes.
 * @author cblanchard - SCUB
 */
@Entity
@Table(name = "DIM_CODE_POSTAL_COMMUNE")
@Indexed
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "CODE_POSTAL_COMMUNE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "CODE_POSTAL_COMMUNE_VERSION", unique = false)) })
public class CodePostalCommune extends BaseModel {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 4692396632110667350L;

    /**
     * Libellé d'acheminement.
     */
    @Column(name = "CODE_POSTAL_COMMUNE_LIBELLE_ACHEMINEMENT", nullable = true)
    private String libelleAcheminement;

    @ManyToOne
    @IndexedEmbedded
    @PrimaryKeyJoinColumn(name = "CODE_POSTAL_COMMUNE_CODE_POSTAL_UID", referencedColumnName = "CODE_POSTAL_UID")
    private CodePostal codePostal;

    @ManyToOne
    @IndexedEmbedded
    @PrimaryKeyJoinColumn(name = "CODE_POSTAL_COMMUNE_COMMUNE_UID", referencedColumnName = "COMMUNE_UID")
    private Commune commune;

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CodePostalCommune)) {
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
     * Renvoi la valeur de libelleAcheminement.
     * @return libelleAcheminement
     */
    public String getLibelleAcheminement() {
        return libelleAcheminement;
    }

    /**
     * Modifie libelleAcheminement.
     * @param libelleAcheminement la nouvelle valeur de libelleAcheminement
     */
    public void setLibelleAcheminement(String libelleAcheminement) {
        this.libelleAcheminement = libelleAcheminement;
    }

    /**
     * Renvoi la valeur de codePostal.
     * @return codePostal
     */
    public CodePostal getCodePostal() {
        return codePostal;
    }

    /**
     * Modifie codePostal.
     * @param codePostal la nouvelle valeur de codePostal
     */
    public void setCodePostal(CodePostal codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Renvoi la valeur de commune.
     * @return commune
     */
    public Commune getCommune() {
        return commune;
    }

    /**
     * Modifie commune.
     * @param commune la nouvelle valeur de commune
     */
    public void setCommune(Commune commune) {
        this.commune = commune;
    }
}

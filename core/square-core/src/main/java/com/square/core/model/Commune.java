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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * Entité persistante modélisant la commune.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
@Entity
@Table(name = "DIM_COMMUNE")
@Indexed
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "COMMUNE_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "COMMUNE_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "COMMUNE_EID", unique = true)),
    @AttributeOverride(name = "libelle", column = @Column(name = "COMMUNE_LIB", nullable = false)),
    @AttributeOverride(name = "ordre", column = @Column(name = "COMMUNE_ORDRE", nullable = false)),
    @AttributeOverride(name = "visible", column = @Column(name = "COMMUNE_VISIBLE", nullable = false)) })
public class Commune extends ModelDim {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 7239886109066774699L;

    /**
     * Code de la commune.
     */
    @Column(name = "COMMUNE_CODE_INSEE", nullable = false)
    private String codeCommune;

    /**
     * Clé étrangère vers la table DEPARTEMENT.
     */
    @ManyToOne
    @JoinColumn(name = "DEPARTEMENT_UID", nullable = false)
    @IndexedEmbedded
    @ForeignKey(name = "FK_DIM_COMMUNE_DIM_DEPARTEMENT_DEPARTEMENT_UID")
    private Departement departement;

    @OneToMany(mappedBy = "commune")
    private Set<CodePostalCommune> codesPostaux;

    /**
     * Retourne la valeur de codeCommune.
     * @return the codeCommune
     */
    public String getCodeCommune() {
        return codeCommune;
    }

    /**
     * Modifie la valeur de codeCommune.
     * @param codeCommune the codeCommune to set
     */
    public void setCodeCommune(String codeCommune) {
        this.codeCommune = codeCommune;
    }

    /**
     * Retourne la valeur de departement.
     * @return the departement
     */
    public Departement getDepartement() {
        return departement;
    }

    /**
     * Modifie la valeur de departement.
     * @param departement the departement to set
     */
    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Commune)) {
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
     * Renvoi la valeur de codesPostaux.
     * @return codesPostaux
     */
    public Set<CodePostalCommune> getCodesPostaux() {
        return codesPostaux;
    }

    /**
     * Modifie codesPostaux.
     * @param codesPostaux la nouvelle valeur de codesPostaux
     */
    public void setCodesPostaux(Set<CodePostalCommune> codesPostaux) {
        this.codesPostaux = codesPostaux;
    }
}

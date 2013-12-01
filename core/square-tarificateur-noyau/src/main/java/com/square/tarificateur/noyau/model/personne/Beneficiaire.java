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
package com.square.tarificateur.noyau.model.personne;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Modèle d'un bénéficiaire.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_BENEFICIAIRE")
public class Beneficiaire extends BaseModel {

    /** serialVersionUID. */
    private static final long serialVersionUID = 2522908810862418513L;

    /** Personne source. */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PERSONNE_SOURCE_UID")
    @ForeignKey(name = "FK_DATA_BENEFICIAIRE_DATA_PERSONNE_SOURCE")
    private Personne personneSource;

    /** Personne Cible. */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSONNE_CIBLE_UID")
    @ForeignKey(name = "FK_DATA_BENEFICIAIRE_DATA_PERSONNE_CIBLE")
    private Personne personneCible;

    /** Lien familial. */
    @ManyToOne
    @JoinColumn(name = "LIEN_FAMILIAL_UID")
    @ForeignKey(name = "FK_DATA_BENEFICIAIRE_DIM_LIEN_FAMILIAL_UID")
    private LienFamilial lienFamilial;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Beneficiaire)) { return false; }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Récupère la valeur de personneCible.
     * @return the personneCible
     */
    public Personne getPersonneCible() {
        return personneCible;
    }

    /**
     * Définit la valeur de personneCible.
     * @param personneCible the personneCible to set
     */
    public void setPersonneCible(Personne personneCible) {
        this.personneCible = personneCible;
    }

    /**
     * Récupère la valeur de lienFamilial.
     * @return the lienFamilial
     */
    public LienFamilial getLienFamilial() {
        return lienFamilial;
    }

    /**
     * Définit la valeur de lienFamilial.
     * @param lienFamilial the lienFamilial to set
     */
    public void setLienFamilial(LienFamilial lienFamilial) {
        this.lienFamilial = lienFamilial;
    }

    /**
     * Récupère la valeur de personneSource.
     * @return the personneSource
     */
    public Personne getPersonneSource() {
        return personneSource;
    }

    /**
     * Définit la valeur de personneSource.
     * @param personneSource the personneSource to set
     */
    public void setPersonneSource(Personne personneSource) {
        this.personneSource = personneSource;
    }
}

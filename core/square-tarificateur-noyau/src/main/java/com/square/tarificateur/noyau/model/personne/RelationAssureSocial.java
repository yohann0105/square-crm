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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Modèle d'une relation à un assuré social.
 * @author mgodbert - SCUB
 *
 */
@Entity
@Table(name = "DATA_RELATION_ASSURE_SOCIAL")
public class RelationAssureSocial extends BaseModel {

	private static final long serialVersionUID = -5121700971372386763L;

	/** Personne. */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PERSONNE_UID")
    @ForeignKey(name = "FK_DATA_RELATION_ASSURE_SOCIAL_PERSONNE")
    private Personne personne;

    /** Assuré social. */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ASSURE_SOCIAL_UID")
    @ForeignKey(name = "FK_DATA_RELATION_ASSURE_SOCIAL_ASSURE_SOCIAL")
    private Personne assureSocial;

    /** Lien familial. */
    @ManyToOne
    @JoinColumn(name = "LIEN_FAMILIAL_UID")
    @ForeignKey(name = "FK_DATA_ASSURE_SOCIAL_DIM_LIEN_FAMILIAL_UID")
    private LienFamilial lienFamilial;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof RelationAssureSocial)) { return false; }
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
	 * Récupère le personne.
	 * @return le personne
	 */
	public Personne getPersonne() {
		return personne;
	}

	/**
	 * Définit le personne.
	 * @param personne le nouveau personne
	 */
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	/**
	 * Récupère le assureSocial.
	 * @return le assureSocial
	 */
	public Personne getAssureSocial() {
		return assureSocial;
	}

	/**
	 * Définit le assureSocial.
	 * @param assureSocial le nouveau assureSocial
	 */
	public void setAssureSocial(Personne assureSocial) {
		this.assureSocial = assureSocial;
	}

	/**
	 * Récupère le lienFamilial.
	 * @return le lienFamilial
	 */
	public LienFamilial getLienFamilial() {
		return lienFamilial;
	}

	/**
	 * Définit le lienFamilial.
	 * @param lienFamilial le nouveau lienFamilial
	 */
	public void setLienFamilial(LienFamilial lienFamilial) {
		this.lienFamilial = lienFamilial;
	}
}

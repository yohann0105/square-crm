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
/**
 * 
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

/**
 * Représente un l'objet d'une action.
 * @author straumat - SCUB
 */
@Entity
@Table(name = "DIM_ACTION_SOUS_OBJET")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "ACTION_SOUS_OBJET_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "ACTION_SOUS_OBJET_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "ACTION_SOUS_OBJET_EID", unique = true)),
    @AttributeOverride(name = "libelle", column = @Column(name = "ACTION_SOUS_OBJET_LIB", nullable = false)),
    @AttributeOverride(name = "ordre", column = @Column(name = "ACTION_SOUS_OBJET_ORDRE", nullable = false)),
    @AttributeOverride(name = "visible", column = @Column(name = "ACTION_SOUS_OBJET_VISIBLE", nullable = false)) })
public class ActionSousObjet extends ModelDim {

    /**
     * Action Objet.
     */
    @ManyToOne
    @JoinColumn(name = "ACTION_OBJET_UID", nullable = false)
    @ForeignKey(name = "FK_DIM_ACTION_SOUS_OBJET_DIM_ACTION_OBJET_ACTION_SOUS_OBJET_UID")
    private ActionObjet actionObjet;

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 911153029678843449L;

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionSousObjet)) {
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
     * Renvoi la valeur de actionObjet.
     * @return actionObjet
     */
    public ActionObjet getActionObjet() {
        return actionObjet;
    }

    /**
     * Modifie actionObjet.
     * @param actionObjet la nouvelle valeur de actionObjet
     */
    public void setActionObjet(ActionObjet actionObjet) {
        this.actionObjet = actionObjet;
    }

}

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

import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * Entité persistante modélisant les relations.
 * @author cblanchard - SCUB
 */
@Entity
@Table(name = "DATA_RELATION")
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "RELATION_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "RELATION_VERSION", unique = false)),
    @AttributeOverride(name = "identifiantExterieur", column = @Column(name = "RELATION_EID", unique = true)),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "RELATION_DATE_CREATION", nullable = false)),
    @AttributeOverride(name = "dateModification", column = @Column(name = "RELATION_DATE_MODIFICATION")),
    @AttributeOverride(name = "dateSuppression", column = @Column(name = "RELATION_DATE_SUPPRESSION")),
    @AttributeOverride(name = "supprime", column = @Column(name = "RELATION_SUPPRIME", nullable = false)) })
public class Relation extends ModelData {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -1744269733600329348L;

    /**
     * Date de début de la relation.
     */
    @Column(name = "RELATION_DATE_DEBUT")
    private Calendar dateDebut;

    /**
     * Date de fin de la relation.
     */
    @Column(name = "RELATION_DATE_FIN")
    private Calendar dateFin;

    /**
     * En activité.
     */
    @Column(name = "RELATION_TOP_ACTIF")
    private boolean topActif;

    /**
     * Date de mise à jour.
     */
    @Column(name = "RELATION_DATE_MAJ")
    private Calendar dateMaj;

    /**
     * Type de la relation.
     */
    @ManyToOne
    @JoinColumn(name = "RELATION_TYPE_UID", nullable = false)
    @ForeignKey(name = "FK_DATA_RELATION_DIM_RELATION_TYPE_RELATION_TYPE_UID")
    private RelationType type;

    /**
     * Propriétaire de la relation.
     */
    @ManyToOne()
    @JoinColumn(name = "PERSONNE_UID_SOURCE", nullable = false)
    @ForeignKey(name = "FK_DATA_RELATION_DATA_PERSONNE_PERSONNE_UID_SOURCE")
    private Personne personneSource;

    /**
     * Propriétaire de la relation.
     */
    @ManyToOne()
    @JoinColumn(name = "PERSONNE_UID_CIBLE", nullable = false)
    @ForeignKey(name = "FK_DATA_RELATION_DATA_PERSONNE_PERSONNE_UID_CIBLE")
    private Personne personneCible;

    /**
     * Renvoi la valeur de dateDebut.
     * @return dateDebut
     */
    public Calendar getDateDebut() {
        return dateDebut;
    }

    /**
     * Modifie dateDebut.
     * @param dateDebut la nouvelle valeur de dateDebut
     */
    public void setDateDebut(Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Renvoi la valeur de dateFin.
     * @return dateFin
     */
    public Calendar getDateFin() {
        return dateFin;
    }

    /**
     * Modifie dateFin.
     * @param dateFin la nouvelle valeur de dateFin
     */
    public void setDateFin(Calendar dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Renvoi la valeur de topActif.
     * @return topActif
     */
    public boolean isTopActif() {
        return topActif;
    }

    /**
     * Modifie topActif.
     * @param topActif la nouvelle valeur de topActif
     */
    public void setTopActif(boolean topActif) {
        this.topActif = topActif;
    }

    /**
     * Renvoi la valeur de dateMaj.
     * @return dateMaj
     */
    public Calendar getDateMaj() {
        return dateMaj;
    }

    /**
     * Modifie dateMaj.
     * @param dateMaj la nouvelle valeur de dateMaj
     */
    public void setDateMaj(Calendar dateMaj) {
        this.dateMaj = dateMaj;
    }

    /**
     * Renvoi la valeur de type.
     * @return type
     */
    public RelationType getType() {
        return type;
    }

    /**
     * Modifie type.
     * @param type la nouvelle valeur de type
     */
    public void setType(RelationType type) {
        this.type = type;
    }

    /**
     * Renvoi la valeur de personneSource.
     * @return personneSource
     */
    public Personne getPersonneSource() {
        return personneSource;
    }

    /**
     * Modifie personneSource.
     * @param personneSource la nouvelle valeur de personneSource
     */
    public void setPersonneSource(Personne personneSource) {
        this.personneSource = personneSource;
    }

    /**
     * Renvoi la valeur de personneCible.
     * @return personneCible
     */
    public Personne getPersonneCible() {
        return personneCible;
    }

    /**
     * Modifie personneCible.
     * @param personneCible la nouvelle valeur de personneCible
     */
    public void setPersonneCible(Personne personneCible) {
        this.personneCible = personneCible;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Relation)) {
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

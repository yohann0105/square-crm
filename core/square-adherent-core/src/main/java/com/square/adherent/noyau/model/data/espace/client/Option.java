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
package com.square.adherent.noyau.model.data.espace.client;

import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.scub.foundation.framework.core.model.BaseModel;

import com.square.adherent.noyau.model.dimension.OptionType;

/**
 * Entité persistante modélisant une option d'un adhérent.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_OPTION")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "OPTION_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "OPTION_VERSION", unique = false))
})
public class Option extends BaseModel {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -5509710077743564340L;

    /**
     * Identifiant unique de l'adhérent.
     */
    @Column(name = "OPTION_PERSONNE_UID")
    @ForeignKey(name = "FK_OPTION_PERSONNE")
    private Long uidPersonne;

    /**
     * Indique si l'option est active ou non.
     */
    @Column(name = "OPTION_TOP_ACTIF")
    private boolean active;

    /**
     * Type de l'option.
     */
    @ManyToOne
    @JoinColumn(name = "OPTION_TYPE_UID")
    @ForeignKey(name = "FK_OPTION_TYPE")
    private OptionType type;

    /**
     * Date de création de l'option.
     */
    @Column(name = "OPTION_DATE_CREATION")
    private Calendar dateCreation;

    /**
     * Date de modification de l'option.
     */
    @Column(name = "OPTION_DATE_MODIFICATION")
    private Calendar dateModification;

    /**
     * Date à laquelle l'option débute.
     */
    @Column(name = "OPTION_DATE_DEBUT")
    private Calendar dateDebut;

    /**
     * Date à laquelle l'option prend fin.
     */
    @Column(name = "OPTION_DATE_FIN")
    private Calendar dateFin;

    /**
     * Getter function.
     * @return the uidPersonne
     */
    public Long getUidPersonne() {
        return uidPersonne;
    }

    /**
     * Getter function.
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Getter function.
     * @return the type
     */
    public OptionType getType() {
        return type;
    }

    /**
     * Getter function.
     * @return the dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Getter function.
     * @return the dateModification
     */
    public Calendar getDateModification() {
        return dateModification;
    }

    /**
     * Getter function.
     * @return the dateDebut
     */
    public Calendar getDateDebut() {
        return dateDebut;
    }

    /**
     * Getter function.
     * @return the dateFin
     */
    public Calendar getDateFin() {
        return dateFin;
    }

    /**
     * Setter function.
     * @param uidPersonne the uidPersonne to set
     */
    public void setUidPersonne(Long uidPersonne) {
        this.uidPersonne = uidPersonne;
    }

    /**
     * Setter function.
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Setter function.
     * @param type the type to set
     */
    public void setType(OptionType type) {
        this.type = type;
    }

    /**
     * Setter function.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Setter function.
     * @param dateModification the dateModification to set
     */
    public void setDateModification(Calendar dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Setter function.
     * @param dateDebut the dateDebut to set
     */
    public void setDateDebut(Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Setter function.
     * @param dateFin the dateFin to set
     */
    public void setDateFin(Calendar dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Option) {
            return super.equalsUtil(other);
        } else {
            return false;
        }
    }
}

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
package com.square.adherent.noyau.model.data.magazine;

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

/**
 * Lien entre les magazines envoyés et les adhérents.
 * @author nnadeau - SCUB
 */
@Entity
@Table(name = "DATA_ADHERENT_MAGAZINE")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "AHDMAG_UID", nullable = false, unique = true)),
    @AttributeOverride(name = "version", column = @Column(name = "ADHMAG_VERSION", unique = false))
})
public class AdherentMagazine extends BaseModel {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 3831742594169935279L;

    /** Identifiant externe. */
    @Column(name = "ADHMAG_EID")
    private String eidAdherentMagazine;

    /** Identifiant unique de l'adhérent. */
    @Column(name = "ADHMAG_PERSONNE_UID")
    @ForeignKey(name = "FK_PERSONNE_MAGAZINE")
    private Long uidPersonne;

    /** Magazine. */
    @ManyToOne
    @JoinColumn(name = "ADHMAG_MAGAZINE_UID")
    @ForeignKey(name = "FK_ADHERENT_MAGAZINE")
    private Magazine magazine;

    /** Date de création du magazine. */
    @Column(name = "MAGAZINE_DATE_CREATION")
    private Calendar dateCreation;

    /** Date de modification du magazine. */
    @Column(name = "MAGAZINE_DATE_MODIFICATION")
    private Calendar dateModification;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return false;
    }

    /**
     * Get the eidAdherentMagazine value.
     * @return the eidAdherentMagazine
     */
    public String getEidAdherentMagazine() {
        return eidAdherentMagazine;
    }

    /**
     * Set the eidAdherentMagazine value.
     * @param eidAdherentMagazine the eidAdherentMagazine to set
     */
    public void setEidAdherentMagazine(String eidAdherentMagazine) {
        this.eidAdherentMagazine = eidAdherentMagazine;
    }

    /**
     * Get the uidPersonne value.
     * @return the uidPersonne
     */
    public Long getUidPersonne() {
        return uidPersonne;
    }

    /**
     * Set the uidPersonne value.
     * @param uidPersonne the uidPersonne to set
     */
    public void setUidPersonne(Long uidPersonne) {
        this.uidPersonne = uidPersonne;
    }

    /**
     * Get the magazine value.
     * @return the magazine
     */
    public Magazine getMagazine() {
        return magazine;
    }

    /**
     * Set the magazine value.
     * @param magazine the magazine to set
     */
    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    /**
     * Get the dateCreation value.
     * @return the dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Set the dateCreation value.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Get the dateModification value.
     * @return the dateModification
     */
    public Calendar getDateModification() {
        return dateModification;
    }

    /**
     * Set the dateModification value.
     * @param dateModification the dateModification to set
     */
    public void setDateModification(Calendar dateModification) {
        this.dateModification = dateModification;
    }
}

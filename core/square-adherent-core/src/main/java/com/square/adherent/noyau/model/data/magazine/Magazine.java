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
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.search.annotations.DocumentId;
import org.scub.foundation.framework.core.model.SimpleBaseModel;

import com.square.adherent.noyau.model.hibernate.type.OuiNonType;

/**
 * Modèle d'un magazine.
 * @author nnadeau - SCUB
 */
@Entity
@Table(name = "DATA_MAGAZINE")
@AttributeOverrides({
    @AttributeOverride(name = "version", column = @Column(name = "MAGAZINE_VERSION", unique = false))
})
@TypeDef(name = "OuiNonType", typeClass = OuiNonType.class)
public class Magazine extends SimpleBaseModel {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -6578848690715193211L;

    /**
     * Identifiant Clé primaire par defaut.
     */
    @Id
    @Column(name = "MAGAZINE_UID", nullable = false, unique = true)
    @DocumentId
    private Long id;

    /** Identifiant externe du magazine. */
    @Column(name = "MAGAZINE_EID")
    private String eidMagazine;

    /** Flag d'envoi à tous les souscripteurs à l'envoi. */
    @Column(name = "MAGAZINE_ENVOYE")
    @Type(type = "OuiNonType")
    private Boolean envoye = Boolean.FALSE;

    /** Date de création du magazine. */
    @Column(name = "MAGAZINE_DATE_CREATION")
    private Calendar dateCreation;

    /** Date de modification du magazine. */
    @Column(name = "MAGAZINE_DATE_MODIFICATION")
    private Calendar dateModification;

    /**
     * Constructeur.
     */
    public Magazine() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return false;
    }

    /**
     * Get the envoye value.
     * @return the envoye
     */
    public Boolean getEnvoye() {
        return envoye;
    }

    /**
     * Set the envoye value.
     * @param envoye the envoye to set
     */
    public void setEnvoye(Boolean envoye) {
        this.envoye = envoye;
    }

    /**
     * Get the eidMagazine value.
     * @return the eidMagazine
     */
    public String getEidMagazine() {
        return eidMagazine;
    }

    /**
     * Set the eidMagazine value.
     * @param eidMagazine the eidMagazine to set
     */
    public void setEidMagazine(String eidMagazine) {
        this.eidMagazine = eidMagazine;
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

    /**
     * Get the id value.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id value.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}

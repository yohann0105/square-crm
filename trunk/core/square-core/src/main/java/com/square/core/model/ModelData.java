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

import javax.persistence.MappedSuperclass;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;

/**
 * Classe représentant la date de création, de suppression, de modification et la supression logique d'une table data.
 * @author cblanchard - SCUB
 */
@MappedSuperclass
public abstract class ModelData extends ModelEID {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -4158911626041135355L;

    /**
     * Date de création.
     */
    @Field(index = Index.UN_TOKENIZED)
    private Calendar dateCreation;

    /**
     * Date de suppression.
     */
    private Calendar dateSuppression;

    /**
     * Dernière date de modification.
     */
    private Calendar dateModification;

    /**
     * Indique si la donnée est supprimée.
     */
    @Field(index = Index.UN_TOKENIZED)
    private boolean supprime;

    /**
     * Renvoi la valeur de dateCreation.
     * @return dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Modifie dateCreation.
     * @param dateCreation la nouvelle valeur de dateCreation
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Renvoi la valeur de dateSuppression.
     * @return dateSuppression
     */
    public Calendar getDateSuppression() {
        return dateSuppression;
    }

    /**
     * Modifie dateSuppression.
     * @param dateSuppression la nouvelle valeur de dateSuppression
     */
    public void setDateSuppression(Calendar dateSuppression) {
        this.dateSuppression = dateSuppression;
    }

    /**
     * Renvoi la valeur de dateModification.
     * @return dateModification
     */
    public Calendar getDateModification() {
        return dateModification;
    }

    /**
     * Modifie dateModification.
     * @param dateModification la nouvelle valeur de dateModification
     */
    public void setDateModification(Calendar dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Renvoi la valeur de supprime.
     * @return supprime
     */
    public boolean isSupprime() {
        return supprime;
    }

    /**
     * Modifie supprime.
     * @param supprime la nouvelle valeur de supprime
     */
    public void setSupprime(boolean supprime) {
        this.supprime = supprime;
    }

}

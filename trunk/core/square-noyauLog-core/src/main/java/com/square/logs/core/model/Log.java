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
package com.square.logs.core.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;
import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Classe persistante de Log.
 * @author Ksouri MohamedAli - SCUB
 */
@Entity
@Table(name = "DATA_LOG")
public class Log extends BaseModel {

    /**
     * serial vesrion UID.
     */
    private static final long serialVersionUID = -8403579630871488576L;

	private static final int TAILLE_SIGNATURE = 2000;

    /**
     * l'application uid.
     */
    @Column(name = "APPLICATION")
    private String application;

    /**
     * la signature de la méthode .
     */
    @Column(name = "SIGNATURE", length = TAILLE_SIGNATURE)
    private String signature;

    /**
     * la date de l'appel.
     */
    @Column(name = "DATE")
    private Calendar date;

    /**
     * la liste des arguments de l'appel de la méthode.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "LOG_ID")
    @ForeignKey(name = "FK_LOG_ARGUMENT")
    private List<Argument> parametresFormates;

    /**
     * la trace d'exception java.
     */
    @Column(name = "TRACE_EXCEPTION")
    @Type(type = "text")
    private String traceException;

    /**
     * modifier l'application.
     * @param application the application to set
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * récupérer l'application.
     * @return the application
     */
    public String getApplication() {
        return application;
    }

    /**
     * modifier la date.
     * @param date the date to set
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * récupérer la date.
     * @return the date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * modifier la signature.
     * @param signature the signature to set
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * récupérer la signature.
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * modifier parametresFormates.
     * @param parametresFormates the parametresFormates to set
     */
    public void setParametresFormates(List<Argument> parametresFormates) {
        this.parametresFormates = parametresFormates;
    }

    /**
     * récupérer parametresFormates.
     * @return the parametresFormates
     */
    public List<Argument> getParametresFormates() {
        return parametresFormates;
    }

    /**
     * modifie la trace de l'exception.
     * @param traceException the traceException to set
     */
    public void setTraceException(String traceException) {
        this.traceException = traceException;
    }

    /**
     * récupére la trace de l'exception.
     * @return the traceException
     */
    public String getTraceException() {
        return traceException;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Log)) {
            return false;
        }
        return equalsUtil(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

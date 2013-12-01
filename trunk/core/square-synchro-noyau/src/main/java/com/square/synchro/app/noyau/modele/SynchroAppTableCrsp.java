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
package com.square.synchro.app.noyau.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.scub.foundation.framework.core.model.BaseModel;


/**
 * Permet de stocker des correspondances d'identifiant.
 * @author Goumard Stephane (stephane.goumard@scub.net) - SCUB
 */
@Entity
@Table(name = "SYNCHRO_APP_TABLE_CRSP")
public class SynchroAppTableCrsp extends BaseModel {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -5333301826663231725L;

    /**
     * identifant origine.
     */
    @Column(name = "IDENTIFIANT_OBJET")
    private String identifiantObjet;

    /**
     * Application.
     */
    @Column(name = "IDENTIFIANT_APP")
    private String identifiantApp;

    /**
     * identifant origine.
     */
    @Column(name = "IDENTIFIANT_OBJECT_CRSP")
    private String identifiantObjetCrsp;

    /**
     * Application.
     */
    @Column(name = "IDENTIFIANT_APP_CRSP")
    private String identifiantAppCrsp;

    /**
     * Application.
     */
    @Column(name = "IDENTIFIANT_TYPE_OBJECT")
    private String typeObjet;

    /**
     * Fixe la valeur.
     * @return the identifiantObjet
     */
    public String getIdentifiantObjet() {
        return identifiantObjet;
    }

    /**
     * fiexe la valeur.
     * @param identifiantObjet the identifiantObjet to set
     */
    public void setIdentifiantObjet(String identifiantObjet) {
        this.identifiantObjet = identifiantObjet;
    }

    /**
     * get la valeur.
     * @return the identifiantApp
     */
    public String getIdentifiantApp() {
        return identifiantApp;
    }

    /**
     * fixe la valeur.
     * @param identifiantApp the identifiantApp to set
     */
    public void setIdentifiantApp(String identifiantApp) {
        this.identifiantApp = identifiantApp;
    }

    /**
     * get la valeur.
     * @return the identifiantObjetCrsp
     */
    public String getIdentifiantObjetCrsp() {
        return identifiantObjetCrsp;
    }

    /**
     * fixe la valeur.
     * @param identifiantObjetCrsp the identifiantObjetCrsp to set
     */
    public void setIdentifiantObjetCrsp(String identifiantObjetCrsp) {
        this.identifiantObjetCrsp = identifiantObjetCrsp;
    }

    /**
     * get la valeur.
     * @return the identifiantAppCrsp
     */
    public String getIdentifiantAppCrsp() {
        return identifiantAppCrsp;
    }

    /**
     * fixe la valeur.
     * @param identifiantAppCrsp the identifiantAppCrsp to set
     */
    public void setIdentifiantAppCrsp(String identifiantAppCrsp) {
        this.identifiantAppCrsp = identifiantAppCrsp;
    }

    /**
     * get la valeur.
     * @return the typeObjet
     */
    public String getTypeObjet() {
        return typeObjet;
    }

    /**
     * fixe la valeur.
     * @param typeObjet the typeObjet to set
     */
    public void setTypeObjet(String typeObjet) {
        this.typeObjet = typeObjet;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SynchroAppTableCrsp)) {
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

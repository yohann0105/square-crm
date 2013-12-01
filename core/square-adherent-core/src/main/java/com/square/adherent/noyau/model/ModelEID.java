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
package com.square.adherent.noyau.model;

import javax.persistence.MappedSuperclass;

import org.scub.foundation.framework.core.model.BaseModel;

/**
 * Entité permettant de gérer les identifiants extérieurs.
 * @author cblanchard - SCUB
 */
@MappedSuperclass
public abstract class ModelEID extends BaseModel {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 6127166717018186307L;

    /**
     * Identifiant extérieur.
     */
    private String identifiantExterieur;

    /**
     * Renvoi la valeur de identifiantExterieur.
     * @return identifiantExterieur
     */
    public String getIdentifiantExterieur() {
        return identifiantExterieur;
    }

    /**
     * Modifie identifiantExterieur.
     * @param identifiantExterieur la nouvelle valeur de identifiantExterieur
     */
    public void setIdentifiantExterieur(String identifiantExterieur) {
        this.identifiantExterieur = identifiantExterieur;
    }
}

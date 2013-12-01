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
package com.square.logs.core.model.dto;

import java.io.Serializable;

/**
 * classe de Dto d'un argument de méthode.
 * @author KsouriMohamedAli - SCUB
 */
public class ArgumentDto implements Serializable {

    /**
     * serial version uid.
     */
    private static final long serialVersionUID = 8340844677917086323L;

    /**
     * nom de paramétre.
     */
    private String nom;

    /**
     * valeur de paramétre.
     */
    private String valeur;

    /**
     * modifier le nom.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * récupérer le nom.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * modifier la valeur.
     * @param valeur the valeur to set
     */
    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    /**
     * récupérer la valeur.
     * @return the valeur
     */
    public String getValeur() {
        return valeur;
    }

}

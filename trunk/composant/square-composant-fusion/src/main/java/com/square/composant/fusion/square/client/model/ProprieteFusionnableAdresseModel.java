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
package com.square.composant.fusion.square.client.model;

/**
 * Propriété fusionnable de type AdresseFusionDto.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ProprieteFusionnableAdresseModel extends ProprieteFusionnableModel {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 4397880020877946957L;

    /** Valeur. */
    private AdresseFusionModel valeur;

    /** Constructeur. */
    public ProprieteFusionnableAdresseModel() {
        super();
    }

    /**
     * Constructeur.
     * @param valeur la valeur
     * @param different le flag "Différent"
     */
    public ProprieteFusionnableAdresseModel(AdresseFusionModel valeur, Boolean different) {
        super(different);
        this.valeur = valeur;
    }

    /**
     * Récupère la valeur de valeur.
     * @return la valeur de valeur
     */
    public AdresseFusionModel getValeur() {
        return valeur;
    }

    /**
     * Définit la valeur de valeur.
     * @param valeur la nouvelle valeur de valeur
     */
    public void setValeur(AdresseFusionModel valeur) {
        this.valeur = valeur;
    }

}

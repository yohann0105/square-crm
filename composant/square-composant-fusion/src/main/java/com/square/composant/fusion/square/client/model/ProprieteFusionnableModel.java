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

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle représentant un champ fusionnable.
 * @author @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ProprieteFusionnableModel implements IsSerializable {

    /** Flag indiquant que le champ est différent par rapport à la cible. */
    private Boolean different;

    /** Flag indiquant que le champ doit être fusionné dans la cible. */
    private Boolean aFusionner;

    /** Flag indiquant si le champ est sélectionnable ou non pour la fusion. */
    private Boolean isSelectionnable;

    /** Constructeur. */
    public ProprieteFusionnableModel() {
    }

    /**
     * Constructeur avec paramètres.
     * @param different le flag de différence
     */
    public ProprieteFusionnableModel(Boolean different) {
        this.different = different;
    }

    /**
     * Récupère la valeur de different.
     * @return la valeur de different
     */
    public Boolean getDifferent() {
        return different;
    }

    /**
     * Définit la valeur de different.
     * @param different la nouvelle valeur de different
     */
    public void setDifferent(Boolean different) {
        this.different = different;
    }

    /**
     * Récupère la valeur de aFusionner.
     * @return la valeur de aFusionner
     */
    public Boolean getAFusionner() {
        return aFusionner;
    }

    /**
     * Définit la valeur de aFusionner.
     * @param aFusionner la nouvelle valeur de aFusionner
     */
    public void setAFusionner(Boolean aFusionner) {
        this.aFusionner = aFusionner;
    }

    /**
     * Récupère la valeur de isSelectionnable.
     * @return la valeur de isSelectionnable
     */
    public Boolean getIsSelectionnable() {
        return isSelectionnable;
    }

    /**
     * Définit la valeur de isSelectionnable.
     * @param isSelectionnable la nouvelle valeur de isSelectionnable
     */
    public void setIsSelectionnable(Boolean isSelectionnable) {
        this.isSelectionnable = isSelectionnable;
    }

}

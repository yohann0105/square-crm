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
package com.square.composant.tarificateur.square.client.model.selecteur;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.composant.tarificateur.square.client.composant.ListBoxItem;

/**
 * Model representant une valeur de criteres pour le selecteur de produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class ValeurCritereSelecteurModel implements IsSerializable, ListBoxItem {

    /**
     * Code du critère.
     */
    private String codeCritere;

    /**
     * Libelle du critère.
     */
    private String libelleCritere;

    /**
     * Constructeur.
     */
    public ValeurCritereSelecteurModel() {
    }

    /**
     * Constructeur.
     * @param codeCritere code du critere
     * @param libelleCritere libelle du critere
     */
    public ValeurCritereSelecteurModel(String codeCritere, String libelleCritere) {
        this.codeCritere = codeCritere;
        this.libelleCritere = libelleCritere;
    }

    /**
     * Get the codeCritere value.
     * @return the codeCritere
     */
    public String getCodeCritere() {
        return codeCritere;
    }

    /**
     * Set the codeCritere value.
     * @param codeCritere the codeCritere to set
     */
    public void setCodeCritere(String codeCritere) {
        this.codeCritere = codeCritere;
    }

    /**
     * Get the libelleCritere value.
     * @return the libelleCritere
     */
    public String getLibelleCritere() {
        return libelleCritere;
    }

    /**
     * Set the libelleCritere value.
     * @param libelleCritere the libelleCritere to set
     */
    public void setLibelleCritere(String libelleCritere) {
        this.libelleCritere = libelleCritere;
    }

    @Override
    public String getIdentifiant() {
        return codeCritere;
    }

    @Override
    public String getLibelle() {
        return libelleCritere;
    }

    @Override
    public void setIdentifiant(String identifiant) {
        codeCritere = identifiant;
    }

    @Override
    public void setLibelle(String libelle) {
        libelleCritere = libelle;
    }

}

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
package com.square.composant.tarificateur.square.client.model.opportunite.devis.ligne;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model d'une valeur d'un critère d'une ligne de devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ValeurCritereLigneDevisModel implements IsSerializable {

    /** Critère associé. */
    private Integer identifiantCritere;

    /** Libelle du critère associé. */
    private String libelleCritere;

    /** Valeur du critère. */
    private String valeur;

    /** Affichage de la valeur. */
    private String affichageValeur;

    /**
     * Retourne la valeur de affichageValeur.
     * @return affichageValeur
     */
    public String getAffichageValeur() {
        return affichageValeur;
    }

    /**
     * Définit la valeur de affichageValeur.
     * @param affichageValeur la nouvelle valeur de affichageValeur
     */
    public void setAffichageValeur(String affichageValeur) {
        this.affichageValeur = affichageValeur;
    }

    /**
     * Retourne la valeur de identifiantCritere.
     * @return identifiantCritere
     */
    public Integer getIdentifiantCritere() {
        return identifiantCritere;
    }

    /**
     * Définit la valeur de identifiantCritere.
     * @param identifiantCritere la nouvelle valeur de identifiantCritere
     */
    public void setIdentifiantCritere(Integer identifiantCritere) {
        this.identifiantCritere = identifiantCritere;
    }

    /**
     * Retourne la valeur de valeur.
     * @return valeur
     */
    public String getValeur() {
        return valeur;
    }

    /**
     * Définit la valeur de valeur.
     * @param valeur la nouvelle valeur de valeur
     */
    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    /**
     * Retourne la valeur de libelleCritere.
     * @return the libelleCritere
     */
    public String getLibelleCritere() {
        return libelleCritere;
    }

    /**
     * Modifie la valeur de libelleCritere.
     * @param libelleCritere the libelleCritere to set
     */
    public void setLibelleCritere(String libelleCritere) {
        this.libelleCritere = libelleCritere;
    }

}

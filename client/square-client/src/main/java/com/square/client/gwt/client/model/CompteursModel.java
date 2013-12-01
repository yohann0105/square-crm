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
package com.square.client.gwt.client.model;

import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Permet le transport des compteurs.
 * @author jgoncalves - SCUB
 */
public class CompteursModel implements IsSerializable {
    /**
     * Liste des compteurs gérés.
     * @author jgoncalves - SCUB
     */
    public static enum Compteur implements IsSerializable {
        /** . */
        COORDONNEES,
        /** . */
        FAMILLE,
        /** . */
        RELATIONS,
        /** . */
        ACTIONS,
        /** . */
        OPPORTUNITES,
        /** . */
        EMAILS,
        /** . */
        DOCUMENTS,
        /** . */
        COTISATIONS,
        /** . */
        PRESTATIONS,
        /** . */
        CONTRATS
    }

    private Compteur[] compteursDemandes;
    private Map<Compteur, Integer> compteursObtenus;

    /** Constructeur par défaut. */
    public CompteursModel() { }

    /**
     * Constructeur avec préselection.
     * @param compteursDemandes les compteurs demandes
     */
    public CompteursModel(Compteur... compteursDemandes) {
        this.compteursDemandes = compteursDemandes;
    }

    /**
     * Récupération de compteursDemandes.
     * @return the compteursDemandes
     */
    public Compteur[] getCompteursDemandes() {
        return compteursDemandes;
    }

    /**
     * Définition de compteursDemandes.
     * @param compteursDemandes the compteursDemandes to set
     */
    public void setCompteursDemandes(Compteur[] compteursDemandes) {
        this.compteursDemandes = compteursDemandes;
    }

    /**
     * Récupération de compteursObtenus.
     * @return the compteursObtenus
     */
    public Map<Compteur, Integer> getCompteursObtenus() {
        return compteursObtenus;
    }

    /**
     * Définition de compteursObtenus.
     * @param compteursObtenus the compteursObtenus to set
     */
    public void setCompteursObtenus(Map<Compteur, Integer> compteursObtenus) {
        this.compteursObtenus = compteursObtenus;
    }

}

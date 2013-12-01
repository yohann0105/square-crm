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

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model contenant le résultat d'une recherche de campagne.
 * @author cblanchard - SCUB
 */
public class CampagneRechercheModel implements IsSerializable {

    /** Identifiant de la campagne. */
    private Long id;

    /** Code de la campagne. */
    private String code;

    /** Libelle de la campagne. */
    private String libelle;

    /** Statut de la campagne. */
    private IdentifiantLibelleGwt statut;

    /** Date de début de la campagne. */
    private String dateDebut;

    /** createur de la campagne. */
    private DimensionRessourceModel ressource;

    /**
     * Renvoi la valeur de id.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Renvoi la valeur de code.
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Modifie code.
     * @param code la nouvelle valeur de code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Renvoi la valeur de libelle.
     * @return libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Modifie libelle.
     * @param libelle la nouvelle valeur de libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Renvoi la valeur de statut.
     * @return statut
     */
    public IdentifiantLibelleGwt getStatut() {
        return statut;
    }

    /**
     * Modifie statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(IdentifiantLibelleGwt statut) {
        this.statut = statut;
    }

    /**
     * Renvoi la valeur de dateDebut.
     * @return dateDebut
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Modifie dateDebut.
     * @param dateDebut la nouvelle valeur de dateDebut
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Retourne la valeur de ressource.
     * @return the ressource
     */
    public DimensionRessourceModel getRessource() {
        return ressource;
    }

    /**
     * Modifie la valeur de ressource.
     * @param ressource the ressource to set
     */
    public void setRessource(DimensionRessourceModel ressource) {
        this.ressource = ressource;
    }

}

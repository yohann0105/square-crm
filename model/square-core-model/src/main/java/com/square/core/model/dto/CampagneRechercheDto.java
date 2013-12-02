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
package com.square.core.model.dto;

import java.io.Serializable;
import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto permettant de récupérer le résultat de la recherche sur les campagnes.
 * @author cblanchard - SCUB
 */
public class CampagneRechercheDto implements Serializable {

    /** Serial verion UID. */
    private static final long serialVersionUID = -8308883149092753880L;

    /** Identifiant de la campagne. */
    private Long id;

    /** Code de la campagne. */
    private String code;

    /** Libelle de la campagne. */
    private String libelle;

    /** Statut de la campagne. */
    private IdentifiantLibelleDto statut;

    /** Date de début de la campagne. */
    private Calendar dateDebut;

    /** Date de fin de la campagne. */
    private Calendar dateFin;

    /**
     * Createur de la campagne.
     */
    private DimensionRessourceDto ressource;

    /**
     * Renvoi le createur de la campagne.
     * @return ressource
     */
    public DimensionRessourceDto getRessource() {
        return ressource;
    }

    /**
     * Modifie le createur de la campagne.
     * @param ressource dans ressource
     */
    public void setRessource(DimensionRessourceDto ressource) {
        this.ressource = ressource;
    }

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
    public IdentifiantLibelleDto getStatut() {
        return statut;
    }

    /**
     * Modifie statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(IdentifiantLibelleDto statut) {
        this.statut = statut;
    }

    /**
     * Renvoi la valeur de dateDebut.
     * @return dateDebut
     */
    public Calendar getDateDebut() {
        return dateDebut;
    }

    /**
     * Modifie dateDebut.
     * @param dateDebut la nouvelle valeur de dateDebut
     */
    public void setDateDebut(Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Getter function.
     * @return the dateFin
     */
    public Calendar getDateFin() {
        return dateFin;
    }

    /**
     * Setter function.
     * @param dateFin the dateFin to set
     */
    public void setDateFin(Calendar dateFin) {
        this.dateFin = dateFin;
    }

}

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
 * Dto pour les campagnes.
 * @author cblanchard - SCUB
 */
public class CampagneDto implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -5199220398807547966L;

    /** Identifiant de la campagne. */
    private Long id;

    /** Date de la campagne. */
    private String code;

    /** Libelle de la campagne. */
    private String libelle;

    /** Statut de la campagne. */
    private IdentifiantLibelleDto statut;

    /** Type de la campagne. */
    private IdentifiantLibelleDto type;

    /** La ressource ayant créé la campagne. */
    private IdentifiantLibelleDto createur;

    /** Date de création de la campagne. */
    private Calendar dateCreation;

    /** Date de début de la campagne. */
    private Calendar dateDebut;

    /** Date de fin de la campagne. */
    private Calendar dateFin;

    /** Descriptif de la campagne. */
    private String descriptif;

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
     * Renvoi la valeur de type.
     * @return type
     */
    public IdentifiantLibelleDto getType() {
        return type;
    }

    /**
     * Modifie type.
     * @param type la nouvelle valeur de type
     */
    public void setType(IdentifiantLibelleDto type) {
        this.type = type;
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
     * Renvoi la valeur de dateFin.
     * @return dateFin
     */
    public Calendar getDateFin() {
        return dateFin;
    }

    /**
     * Modifie dateFin.
     * @param dateFin la nouvelle valeur de dateFin
     */
    public void setDateFin(Calendar dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Renvoi la valeur de descriptif.
     * @return descriptif
     */
    public String getDescriptif() {
        return descriptif;
    }

    /**
     * Modifie descriptif.
     * @param descriptif la nouvelle valeur de descriptif
     */
    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
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
     * Getter function.
     * @return the createur
     */
    public IdentifiantLibelleDto getCreateur() {
        return createur;
    }

    /**
     * Setter function.
     * @param createur the createur to set
     */
    public void setCreateur(IdentifiantLibelleDto createur) {
        this.createur = createur;
    }

    /**
     * Getter function.
     * @return the dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Setter function.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

}

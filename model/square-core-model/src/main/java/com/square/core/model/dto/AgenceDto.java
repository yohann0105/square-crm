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

/**
 * DTO représentant une agence.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class AgenceDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -4921359692630512817L;

    /** Identifiant de l'agence. */
    private Long id;

    /** Identifiant externe de l'agence. */
    private String idExt;

    /** Libelle de l'agence. */
    private String libelle;

    /** Région de l'agence. */
    private IdentifiantEidLibelleDto region;

    /**
     * Récupère la valeur de id.
     * @return la valeur de id
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère la valeur de idExt.
     * @return la valeur de idExt
     */
    public String getIdExt() {
        return idExt;
    }

    /**
     * Définit la valeur de idExt.
     * @param idExt la nouvelle valeur de idExt
     */
    public void setIdExt(String idExt) {
        this.idExt = idExt;
    }

    /**
     * Récupère la valeur de libelle.
     * @return la valeur de libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Définit la valeur de libelle.
     * @param libelle la nouvelle valeur de libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Récupère la valeur de region.
     * @return la valeur de region
     */
    public IdentifiantEidLibelleDto getRegion() {
        return region;
    }

    /**
     * Définit la valeur de region.
     * @param region la nouvelle valeur de region
     */
    public void setRegion(IdentifiantEidLibelleDto region) {
        this.region = region;
    }
}

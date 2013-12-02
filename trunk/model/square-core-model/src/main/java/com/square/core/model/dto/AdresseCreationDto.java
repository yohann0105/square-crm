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
import java.util.List;

/**
 * DTO retour de la création d'une adresse, renvoie les adresses impactées par la création.
 * @author jgoncalves - SCUB
 */
public class AdresseCreationDto implements Serializable {
    private static final long serialVersionUID = 2871274149777172291L;

    /** Identifiant de la nouvelle adresse. */
    Long idAdresseCree;

    /** Identifiant des adresses impactées par la création de la nouvelle. */
    List<Long> idAdressesModifiees;

    /** Flag indiquant si la nature de la personne a changé. */
    private Boolean hasNaturePersonneChanged;

    /** Ancienne nature de la personne. */
    private String ancienneNaturePersonne;

    /** Nouvelle nature de la personne. */
    private String nouvelleNaturePersonne;

    /**
     * Récupération de idAdresseCree.
     * @return the idAdresseCree
     */
    public Long getIdAdresseCree() {
        return idAdresseCree;
    }

    /**
     * Définition de idAdresseCree.
     * @param idAdresseCree the idAdresseCree to set
     */
    public void setIdAdresseCree(Long idAdresseCree) {
        this.idAdresseCree = idAdresseCree;
    }

    /**
     * Récupération de idAdressesModifiees.
     * @return the idAdressesModifiees
     */
    public List<Long> getIdAdressesModifiees() {
        return idAdressesModifiees;
    }

    /**
     * Définition de idAdressesModifiees.
     * @param idAdressesModifiees the idAdressesModifiees to set
     */
    public void setIdAdressesModifiees(List<Long> idAdressesModifiees) {
        this.idAdressesModifiees = idAdressesModifiees;
    }

    /**
     * Récupère la valeur de hasNaturePersonneChanged.
     * @return la valeur de hasNaturePersonneChanged
     */
    public Boolean getHasNaturePersonneChanged() {
        return hasNaturePersonneChanged;
    }

    /**
     * Définit la valeur de hasNaturePersonneChanged.
     * @param hasNaturePersonneChanged la nouvelle valeur de hasNaturePersonneChanged
     */
    public void setHasNaturePersonneChanged(Boolean hasNaturePersonneChanged) {
        this.hasNaturePersonneChanged = hasNaturePersonneChanged;
    }

    /**
     * Récupère la valeur de ancienneNaturePersonne.
     * @return la valeur de ancienneNaturePersonne
     */
    public String getAncienneNaturePersonne() {
        return ancienneNaturePersonne;
    }

    /**
     * Définit la valeur de ancienneNaturePersonne.
     * @param ancienneNaturePersonne la nouvelle valeur de ancienneNaturePersonne
     */
    public void setAncienneNaturePersonne(String ancienneNaturePersonne) {
        this.ancienneNaturePersonne = ancienneNaturePersonne;
    }

    /**
     * Récupère la valeur de nouvelleNaturePersonne.
     * @return la valeur de nouvelleNaturePersonne
     */
    public String getNouvelleNaturePersonne() {
        return nouvelleNaturePersonne;
    }

    /**
     * Définit la valeur de nouvelleNaturePersonne.
     * @param nouvelleNaturePersonne la nouvelle valeur de nouvelleNaturePersonne
     */
    public void setNouvelleNaturePersonne(String nouvelleNaturePersonne) {
        this.nouvelleNaturePersonne = nouvelleNaturePersonne;
    }
}
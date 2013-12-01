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

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model contenant les critères de recherche sur les relations.
 * @author cblanchard - SCUB
 */
public class RelationCriteresRechercheModel implements IsSerializable {

    /** Recherche dans la personne cible ou dans la personne Source. */
    private Long idPersonne;

    /**
     * Identifiant du type de groupement.
     */
    private List<Long> groupements;

    /**
     * Identifiant du type de groupement.
     */
    private List<Long> pasDansGroupements;

    /**
     * Filtre sur l'identifiant de la personne source.
     */
    private Long idPersonneSource;
    
    /**
     * Flag permettant de rechercher les relations qui n'ont pas de contrat uniquement.
     */
    private Boolean relationsSansContrat;

    /**
     * Renvoi la valeur de idPersonne.
     * @return idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Modifie idPersonne.
     * @param idPersonne la nouvelle valeur de idPersonne
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    /**
     * Filtre groupements.
     * @return the groupements
     */
    public List<Long> getGroupements() {
        return groupements;
    }

    /**
     * Filtre groupements.
     * @param groupements the groupements to set
     */
    public void setGroupements(List<Long> groupements) {
        this.groupements = groupements;
    }

    /**
     * Filtre groupements.
     * @return the pasDansGroupements
     */
    public List<Long> getPasDansGroupements() {
        return pasDansGroupements;
    }

    /**
     * Filtre groupements.
     * @param pasDansGroupements the pasDansGroupements to set
     */
    public void setPasDansGroupements(List<Long> pasDansGroupements) {
        this.pasDansGroupements = pasDansGroupements;
    }

    /**
     * Récupère la valeur de idPersonneSource.
     * @return la valeur de idPersonneSource
     */
    public Long getIdPersonneSource() {
        return idPersonneSource;
    }

    /**
     * Définit la valeur de idPersonneSource.
     * @param idPersonneSource la nouvelle valeur de idPersonneSource
     */
    public void setIdPersonneSource(Long idPersonneSource) {
        this.idPersonneSource = idPersonneSource;
    }

    /**
     * Get the relationsSansContrat value.
     * @return the relationsSansContrat
     */
    public Boolean getRelationsSansContrat() {
        return relationsSansContrat;
    }

    /**
     * Set the relationsSansContrat value.
     * @param relationsSansContrat the relationsSansContrat to set
     */
    public void setRelationsSansContrat(Boolean relationsSansContrat) {
        this.relationsSansContrat = relationsSansContrat;
    }
}

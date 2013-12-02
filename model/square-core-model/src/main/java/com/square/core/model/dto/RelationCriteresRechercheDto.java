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
import java.util.List;

/**
 * Dto de critères de recherche pour les relations.
 * @author cblanchard - SCUB
 */
public class RelationCriteresRechercheDto implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = -4854726747918991389L;

    /** Recherche dans la personne cible ou dans la personne Source. */
    private Long idPersonne;

    /** Filtre sur l'identifiant de la personne source. */
    private Long idPersonneSource;

    /** Liste des ids de relations. */
    private List<Long> idsRelations;

    /** Liste des groupements. */
    private List<Long> groupements;

    /** Liste inversé des groupements. */
    private List<Long> pasDansGroupements;

    /** Liste des types. */
    private List<Long> types;

    /** Flag actif. */
    private Boolean actif;

    /** Flag supprime. */
    private Boolean supprime = Boolean.FALSE;

    /** Flag pour récupérer que les cibles actives. */
    private Boolean cibleActive;

    /** Personne cible de la relation. */
    private Long idPersonneCible;

    /** Pas dans la liste. */
    private List<Long> pasDansRelations;

    /** Début de la plage de la date de debut de relation. */
    private Calendar dateDebutRelationDebutPlage;

    /** Fin de la plage de la date de debut de relation. */
    private Calendar dateDebutRelationFinPlage;

    /** Date de fin max d'une relation. */
    private Calendar dateFinMax;

    /** Flag permettant de rechercher les relations qui n'ont pas de contrat uniquement. */
    private Boolean relationsSansContrat = Boolean.FALSE;

    /**
     * Get the idPersonne value.
     * @return the idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Set the idPersonne value.
     * @param idPersonne the idPersonne to set
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    /**
     * Get the idPersonneSource value.
     * @return the idPersonneSource
     */
    public Long getIdPersonneSource() {
        return idPersonneSource;
    }

    /**
     * Set the idPersonneSource value.
     * @param idPersonneSource the idPersonneSource to set
     */
    public void setIdPersonneSource(Long idPersonneSource) {
        this.idPersonneSource = idPersonneSource;
    }

    /**
     * Get the groupements value.
     * @return the groupements
     */
    public List<Long> getGroupements() {
        return groupements;
    }

    /**
     * Set the groupements value.
     * @param groupements the groupements to set
     */
    public void setGroupements(List<Long> groupements) {
        this.groupements = groupements;
    }

    /**
     * Get the pasDansGroupements value.
     * @return the pasDansGroupements
     */
    public List<Long> getPasDansGroupements() {
        return pasDansGroupements;
    }

    /**
     * Set the pasDansGroupements value.
     * @param pasDansGroupements the pasDansGroupements to set
     */
    public void setPasDansGroupements(List<Long> pasDansGroupements) {
        this.pasDansGroupements = pasDansGroupements;
    }

    /**
     * Get the types value.
     * @return the types
     */
    public List<Long> getTypes() {
        return types;
    }

    /**
     * Set the types value.
     * @param types the types to set
     */
    public void setTypes(List<Long> types) {
        this.types = types;
    }

    /**
     * Get the actif value.
     * @return the actif
     */
    public Boolean getActif() {
        return actif;
    }

    /**
     * Set the actif value.
     * @param actif the actif to set
     */
    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    /**
     * Set the supprime value.
	 * @return the supprime
	 */
	public Boolean getSupprime() {
		return supprime;
	}

	/**
     * Set the supprime value.
	 * @param supprime the supprime to set
	 */
	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}

	/**
     * Get the cibleActive value.
     * @return the cibleActive
     */
    public Boolean getCibleActive() {
        return cibleActive;
    }

    /**
     * Set the cibleActive value.
     * @param cibleActive the cibleActive to set
     */
    public void setCibleActive(Boolean cibleActive) {
        this.cibleActive = cibleActive;
    }

    /**
     * Get the idPersonneCible value.
     * @return the idPersonneCible
     */
    public Long getIdPersonneCible() {
        return idPersonneCible;
    }

    /**
     * Set the idPersonneCible value.
     * @param idPersonneCible the idPersonneCible to set
     */
    public void setIdPersonneCible(Long idPersonneCible) {
        this.idPersonneCible = idPersonneCible;
    }

    /**
     * Get the pasDansRelations value.
     * @return the pasDansRelations
     */
    public List<Long> getPasDansRelations() {
        return pasDansRelations;
    }

    /**
     * Set the pasDansRelations value.
     * @param pasDansRelations the pasDansRelations to set
     */
    public void setPasDansRelations(List<Long> pasDansRelations) {
        this.pasDansRelations = pasDansRelations;
    }

    /**
     * Get the dateDebutRelationDebutPlage value.
     * @return the dateDebutRelationDebutPlage
     */
    public Calendar getDateDebutRelationDebutPlage() {
        return dateDebutRelationDebutPlage;
    }

    /**
     * Set the dateDebutRelationDebutPlage value.
     * @param dateDebutRelationDebutPlage the dateDebutRelationDebutPlage to set
     */
    public void setDateDebutRelationDebutPlage(Calendar dateDebutRelationDebutPlage) {
        this.dateDebutRelationDebutPlage = dateDebutRelationDebutPlage;
    }

    /**
     * Get the dateDebutRelationFinPlage value.
     * @return the dateDebutRelationFinPlage
     */
    public Calendar getDateDebutRelationFinPlage() {
        return dateDebutRelationFinPlage;
    }

    /**
     * Set the dateDebutRelationFinPlage value.
     * @param dateDebutRelationFinPlage the dateDebutRelationFinPlage to set
     */
    public void setDateDebutRelationFinPlage(Calendar dateDebutRelationFinPlage) {
        this.dateDebutRelationFinPlage = dateDebutRelationFinPlage;
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

    /**
     * Get the value of dateFinMax.
     * @return the dateFinMax
     */
    public Calendar getDateFinMax() {
        return dateFinMax;
    }

    /**
     * Set the value of dateFinMax.
     * @param dateFinMax the dateFinMax to set
     */
    public void setDateFinMax(Calendar dateFinMax) {
        this.dateFinMax = dateFinMax;
    }

    /**
     * Get the value of idsRelations.
     * @return the idsRelations
     */
    public List<Long> getIdsRelations() {
        return idsRelations;
    }

    /**
     * Set the value of idsRelations.
     * @param idsRelations the idsRelations to set
     */
    public void setIdsRelations(List<Long> idsRelations) {
        this.idsRelations = idsRelations;
    }
}

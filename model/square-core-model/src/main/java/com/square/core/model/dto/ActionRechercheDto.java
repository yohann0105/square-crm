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
 * Objet contenant le résultat de recherche d'une action.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class ActionRechercheDto implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = 4188525397277533512L;

    /** identifiant. */
    private Long id;

    /** L'identifiant de la personne rattachée. */
    private Long idpersonne;

    /** Identifiant de l'opportunité rattachée. */
    private Long idOpportunite;

    /** Date de réalisation de l'action. */
    private Calendar dateAction;

    /** Date de création de l'action. */
    private Calendar dateCreation;

    /** Date de fin de l'action. */
    private Calendar dateTerminee;

    /** Le type d'action. */
    private String type;

    /** Objet de l'action. */
    private String objet;

    /** Sous objet de l'action. */
    private String sousObjet;

    /** La priorité de l'action. */
    private String priorite;

    /** Le statut de l'action. */
    private String statut;

    /** Le numero du client. */
    private String numeroClient;

    /** La ressource: responsable de la personne. */
    private DimensionRessourceDto commercial;

    /** La ressource: createur de l'action. */
    private DimensionRessourceDto createur;

    /** L'agence. */
    private IdentifiantLibelleDto agence;

    /** Nom de la personne.  */
    private String nomPersonne;

    /** Prenom de la personne.  */
    private String prenomPersonne;

    /** Raison sociale.  */
    private String raisonSociale;

    /**
     * Get the id value.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id value.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the idpersonne value.
     * @return the idpersonne
     */
    public Long getIdpersonne() {
        return idpersonne;
    }

    /**
     * Set the idpersonne value.
     * @param idpersonne the idpersonne to set
     */
    public void setIdpersonne(Long idpersonne) {
        this.idpersonne = idpersonne;
    }

    /**
     * Get the dateAction value.
     * @return the dateAction
     */
    public Calendar getDateAction() {
        return dateAction;
    }

    /**
     * Set the dateAction value.
     * @param dateAction the dateAction to set
     */
    public void setDateAction(Calendar dateAction) {
        this.dateAction = dateAction;
    }

    /**
     * Get the dateCreation value.
     * @return the dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Set the dateCreation value.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Get the dateTerminee value.
     * @return the dateTerminee
     */
    public Calendar getDateTerminee() {
        return dateTerminee;
    }

    /**
     * Set the dateTerminee value.
     * @param dateTerminee the dateTerminee to set
     */
    public void setDateTerminee(Calendar dateTerminee) {
        this.dateTerminee = dateTerminee;
    }

    /**
     * Get the type value.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type value.
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the objet value.
     * @return the objet
     */
    public String getObjet() {
        return objet;
    }

    /**
     * Set the objet value.
     * @param objet the objet to set
     */
    public void setObjet(String objet) {
        this.objet = objet;
    }

    /**
     * Get the sousObjet value.
     * @return the sousObjet
     */
    public String getSousObjet() {
        return sousObjet;
    }

    /**
     * Set the sousObjet value.
     * @param sousObjet the sousObjet to set
     */
    public void setSousObjet(String sousObjet) {
        this.sousObjet = sousObjet;
    }

    /**
     * Get the priorite value.
     * @return the priorite
     */
    public String getPriorite() {
        return priorite;
    }

    /**
     * Set the priorite value.
     * @param priorite the priorite to set
     */
    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    /**
     * Get the statut value.
     * @return the statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     * Set the statut value.
     * @param statut the statut to set
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * Get the commercial value.
     * @return the commercial
     */
    public DimensionRessourceDto getCommercial() {
        return commercial;
    }

    /**
     * Set the commercial value.
     * @param commercial the commercial to set
     */
    public void setCommercial(DimensionRessourceDto commercial) {
        this.commercial = commercial;
    }

    /**
     * Get the agence value.
     * @return the agence
     */
    public IdentifiantLibelleDto getAgence() {
        return agence;
    }

    /**
     * Set the agence value.
     * @param agence the agence to set
     */
    public void setAgence(IdentifiantLibelleDto agence) {
        this.agence = agence;
    }

    /**
     * Get the createur value.
     * @return the createur
     */
    public DimensionRessourceDto getCreateur() {
        return createur;
    }

    /**
     * Set the createur value.
     * @param createur the createur to set
     */
    public void setCreateur(DimensionRessourceDto createur) {
        this.createur = createur;
    }

    /**
     * Get the numeroClient value.
     * @return the numeroClient
     */
    public String getNumeroClient() {
        return numeroClient;
    }

    /**
     * Set the numeroClient value.
     * @param numeroClient the numeroClient to set
     */
    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
    }

	/**modifie le nom de la personne.
	 * @param nomPersonne the nomPersonne to set
	 */
	public void setNomPersonne(String nomPersonne) {
		this.nomPersonne = nomPersonne;
	}

	/**retourne le nom de la personne.
	 * @return the nomPersonne
	 */
	public String getNomPersonne() {
		return nomPersonne;
	}

	/**modifie le prenom de la personne.
	 * @param prenomPersonne the prenomPersonne to set.
	 */
	public void setPrenomPersonne(String prenomPersonne) {
		this.prenomPersonne = prenomPersonne;
	}

	/** retourne le prenom de la personne.
	 * @return the prenomPersonne
	 */
	public String getPrenomPersonne() {
		return prenomPersonne;
	}

    /**
     * Récupère la valeur de idOpportunite.
     * @return la valeur de idOpportunite
     */
    public Long getIdOpportunite() {
        return idOpportunite;
    }

    /**
     * Définit la valeur de idOpportunite.
     * @param idOpportunite la nouvelle valeur de idOpportunite
     */
    public void setIdOpportunite(Long idOpportunite) {
        this.idOpportunite = idOpportunite;
    }

    /**
     * Get the value of raisonSociale.
     * @return the raisonSociale
     */
    public String getRaisonSociale() {
        return raisonSociale;
    }

    /**
     * Set the value of raisonSociale.
     * @param raisonSociale the raisonSociale to set
     */
    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }
}

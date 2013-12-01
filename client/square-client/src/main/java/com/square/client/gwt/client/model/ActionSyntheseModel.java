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
 * Modèle de synthèse des actions.
 * @author cblanchard - SCUB
 */
public class ActionSyntheseModel implements IsSerializable {

    /** Identifiant de l'action. */
    private Long id;

    /** Identifiant de l'action source. */
    private Long idActionSource;

    /** Type de l'action. */
    private IdentifiantLibelleGwt type;

    /** Objet de l'action. */
    private String objet;

    /** Sous objet de l'action. */
    private String sousObjet;

    /** Statut de l'action. */
    private IdentifiantLibelleGwt statut;

    /** Date de l'action. */
    private String dateAction;

    /** Date terminée de l'action. */
    private String dateActionTerminee;

    /** Heure de l'action. */
    private String heureAction;

    /** Heure de l'action terminée. */
    private String heureActionTerminee;

	/** Agence. */
    private String agence;

    /** Attribution de l'action. */
    private String attribueA;

    /** Priorité de l'action. */
    private IdentifiantLibelleGwt priorite;

    /** Indique si l'action est une réclamation. */
    private Boolean reclamation;

    /** Indique le niveau de l'action. */
    private int niveau;

    /** Indique si la date et l'heure peuvent être éditables. */
    private Boolean dateActionEditable;

    /** Nature du contact. */
    private String natureContact;

    /** Libellé de la campagne. */
    private String campagne;

    /** Dernière date de modification. */
    private String dateModification;

    /** Durée de l'action. */
    private IdentifiantLibelleGwt duree;

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
     * Renvoi la valeur de type.
     * @return type
     */
    public IdentifiantLibelleGwt getType() {
        return type;
    }

    /**
     * Modifie type.
     * @param type la nouvelle valeur de type
     */
    public void setType(IdentifiantLibelleGwt type) {
        this.type = type;
    }

    /**
     * Renvoi la valeur de objet.
     * @return objet
     */
    public String getObjet() {
        return objet;
    }

    /**
     * Modifie objet.
     * @param objet la nouvelle valeur de objet
     */
    public void setObjet(String objet) {
        this.objet = objet;
    }

    /**
     * Renvoi la valeur de sousObjet.
     * @return sousObjet
     */
    public String getSousObjet() {
        return sousObjet;
    }

    /**
     * Modifie sousObjet.
     * @param sousObjet la nouvelle valeur de sousObjet
     */
    public void setSousObjet(String sousObjet) {
        this.sousObjet = sousObjet;
    }

    /**
     * Renvoi la valeur de dateAction.
     * @return dateAction
     */
    public String getDateAction() {
        return dateAction;
    }

    /**
     * Modifie dateAction.
     * @param dateAction la nouvelle valeur de dateAction
     */
    public void setDateAction(String dateAction) {
        this.dateAction = dateAction;
    }

    /**
     * Renvoi la valeur de attribueA.
     * @return attribueA
     */
    public String getAttribueA() {
        return attribueA;
    }

    /**
     * Modifie attribueA.
     * @param attribueA la nouvelle valeur de attribueA
     */
    public void setAttribueA(String attribueA) {
        this.attribueA = attribueA;
    }

    /**
     * Renvoi la valeur de reclamation.
     * @return reclamation
     */
    public Boolean getReclamation() {
        return reclamation;
    }

    /**
     * Modifie reclamation.
     * @param reclamation la nouvelle valeur de reclamation
     */
    public void setReclamation(Boolean reclamation) {
        this.reclamation = reclamation;
    }

    /**
     * Renvoi la valeur de niveau.
     * @return niveau
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * Modifie niveau.
     * @param niveau la nouvelle valeur de niveau
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    /**
     * Renvoi la valeur de heureAction.
     * @return heureAction
     */
    public String getHeureAction() {
        return heureAction;
    }

    /**
     * Modifie heureAction.
     * @param heureAction la nouvelle valeur de heureAction
     */
    public void setHeureAction(String heureAction) {
        this.heureAction = heureAction;
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
     * Renvoi la valeur de dateActionEditable.
     * @return dateActionEditable
     */
    public Boolean getDateActionEditable() {
        return dateActionEditable;
    }

    /**
     * Modifie dateActionEditable.
     * @param dateActionEditable la nouvelle valeur de dateActionEditable
     */
    public void setDateActionEditable(Boolean dateActionEditable) {
        this.dateActionEditable = dateActionEditable;
    }

    /**
     * Get the idActionSource value.
     * @return the idActionSource
     */
    public Long getIdActionSource() {
        return idActionSource;
    }

    /**
     * Set the idActionSource value.
     * @param idActionSource the idActionSource to set
     */
    public void setIdActionSource(Long idActionSource) {
        this.idActionSource = idActionSource;
    }

    /**
     * Récupère la valeur de agence.
     * @return la valeur de agence
     */
    public String getAgence() {
        return agence;
    }

    /**
     * Définit la valeur de agence.
     * @param agence la nouvelle valeur de agence
     */
    public void setAgence(String agence) {
        this.agence = agence;
    }

    /**
     * Récupère la valeur de natureContact.
     * @return la valeur de natureContact
     */
    public String getNatureContact() {
        return natureContact;
    }

    /**
     * Définit la valeur de natureContact.
     * @param natureContact la nouvelle valeur de natureContact
     */
    public void setNatureContact(String natureContact) {
        this.natureContact = natureContact;
    }

    /**
     * Récupère la valeur de campagne.
     * @return la valeur de campagne
     */
    public String getCampagne() {
        return campagne;
    }

    /**
     * Définit la valeur de campagne.
     * @param campagne la nouvelle valeur de campagne
     */
    public void setCampagne(String campagne) {
        this.campagne = campagne;
    }

    /**
     * Get the dateActionTerminee value.
     * @return the dateActionTerminee
     */
    public String getDateActionTerminee() {
        return dateActionTerminee;
    }

    /**
     * Set the dateActionTerminee value.
     * @param dateActionTerminee the dateActionTerminee to set
     */
    public void setDateActionTerminee(String dateActionTerminee) {
        this.dateActionTerminee = dateActionTerminee;
    }

    /**
     * Récupère la valeur de priorite.
     * @return la valeur de priorite
     */
    public IdentifiantLibelleGwt getPriorite() {
        return priorite;
    }

    /**
     * Définit la valeur de priorite.
     * @param priorite la nouvelle valeur de priorite
     */
    public void setPriorite(IdentifiantLibelleGwt priorite) {
        this.priorite = priorite;
    }

    /**
     * Récupère la valeur de dateModification.
     * @return la valeur de dateModification
     */
    public String getDateModification() {
        return dateModification;
    }

    /**
     * Définit la valeur de dateModification.
     * @param dateModification la nouvelle valeur de dateModification
     */
    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }
    
    /**
	 * @return the heureActionTerminee
	 */
	public String getHeureActionTerminee() {
		return heureActionTerminee;
	}

	/**
	 * @param heureActionTerminee the heureActionTerminee to set
	 */
	public void setHeureActionTerminee(String heureActionTerminee) {
		this.heureActionTerminee = heureActionTerminee;
	}

    /**
     * Récupère la valeur de duree.
     * @return la valeur de duree
     */
    public IdentifiantLibelleGwt getDuree() {
        return duree;
    }

    /**
     * Définit la valeur de duree.
     * @param duree la nouvelle valeur de duree
     */
    public void setDuree(IdentifiantLibelleGwt duree) {
        this.duree = duree;
    }

}

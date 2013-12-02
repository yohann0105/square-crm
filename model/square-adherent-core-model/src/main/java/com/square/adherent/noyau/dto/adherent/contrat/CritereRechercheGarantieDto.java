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
package com.square.adherent.noyau.dto.adherent.contrat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Critères de recherche pour les garanties.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class CritereRechercheGarantieDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -1265864040920433747L;

    /** L'identifiant du contrat possédant les garanties. */
    private List<Long> listeIdsContrat;

    /** Booléen indiquant si on ne veut récupérer que les garanties en cours. */
    private Boolean hasGarantieEnCours;

    /** L'identifiant du statut de garantie. */
    private Long idStatutGarantie;

    /** identifiant unique de l'assuré de la garantie. */
    private Long uidAssure;

    /** identifiant unique du bénéficiaire de la garantie. */
    private Long uidBeneficiaire;

    /** Eid de la garantie. */
    private String eid;

    /**
     * Récupère la valeur de hasGarantieEnCours.
     * @return la valeur de hasGarantieEnCours
     */
    public Boolean getHasGarantieEnCours() {
        return hasGarantieEnCours;
    }

    /**
     * Définit la valeur de hasGarantieEnCours.
     * @param hasGarantieEnCours la nouvelle valeur de hasGarantieEnCours
     */
    public void setHasGarantieEnCours(Boolean hasGarantieEnCours) {
        this.hasGarantieEnCours = hasGarantieEnCours;
    }

	/**
     * Récupère la valeur de listeIdsContrat.
     * @return la valeur de listeIdsContrat
     */
    public List<Long> getListeIdsContrat() {
        if (listeIdsContrat == null) {
            listeIdsContrat = new ArrayList<Long>();
        }
        return listeIdsContrat;
    }

    /**
     * Définit la valeur de listeIdsContrat.
     * @param listeIdsContrat la nouvelle valeur de listeIdsContrat
     */
    public void setListeIdsContrat(List<Long> listeIdsContrat) {
        this.listeIdsContrat = listeIdsContrat;
    }

    /**
     * Getter function.
     * @return the uidAssure
     */
    public Long getUidAssure() {
        return uidAssure;
    }

    /**
     * Getter function.
     * @return the uidBeneficiaire
     */
    public Long getUidBeneficiaire() {
        return uidBeneficiaire;
    }

    /**
     * Setter function.
     * @param uidAssure the uidAssure to set
     */
    public void setUidAssure(Long uidAssure) {
        this.uidAssure = uidAssure;
    }

    /**
     * Setter function.
     * @param uidBeneficiaire the uidBeneficiaire to set
     */
    public void setUidBeneficiaire(Long uidBeneficiaire) {
        this.uidBeneficiaire = uidBeneficiaire;
    }

    /**
     * Get the eid value.
     * @return the eid
     */
    public String getEid() {
        return eid;
    }

    /**
     * Set the eid value.
     * @param eid the eid to set
     */
    public void setEid(String eid) {
        this.eid = eid;
    }

	/**Get the idStatutGarantie.
	 * @return the idStatutGarantie
	 */
	public Long getIdStatutGarantie() {
		return idStatutGarantie;
	}

	/**Set the IdStatutGarantie.
	 * @param idStatutGarantie the idStatutGarantie to set
	 */
	public void setIdStatutGarantie(Long idStatutGarantie) {
		this.idStatutGarantie = idStatutGarantie;
	}
}

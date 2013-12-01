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
package com.square.tarificateur.noyau.dto.personne;

import java.io.Serializable;

/**
 * Dto des infos de santé.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class InfoSanteDto implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = -1066468563384547861L;

    /** Identifiant. */
    private Long id;

    /** Numéro de sécurité sociale. */
    private String numSecuriteSocial;

    /** Clé de sécurité sociale. */
    private String cleSecuriteSocial;

    /** EID du regime (Square). */
    private Long eidRegime;

    /** EID de la caisse (Square). */
    private Long eidCaisse;

    /** Identifiant du référent. */
    private Long idReferent;

    /** EID du referent (Square). */
    private Long eidReferent;

    /**
	 * Récupère le id.
	 * @return le id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Définit le id.
	 * @param id le nouveau id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
     * Get the numSecuriteSocial value.
     * @return the numSecuriteSocial
     */
    public String getNumSecuriteSocial() {
        return numSecuriteSocial;
    }

    /**
     * Set the numSecuriteSocial value.
     * @param numSecuriteSocial the numSecuriteSocial to set
     */
    public void setNumSecuriteSocial(String numSecuriteSocial) {
        this.numSecuriteSocial = numSecuriteSocial;
    }

    /**
     * Get the cleSecuriteSocial value.
     * @return the cleSecuriteSocial
     */
    public String getCleSecuriteSocial() {
        return cleSecuriteSocial;
    }

    /**
     * Set the cleSecuriteSocial value.
     * @param cleSecuriteSocial the cleSecuriteSocial to set
     */
    public void setCleSecuriteSocial(String cleSecuriteSocial) {
        this.cleSecuriteSocial = cleSecuriteSocial;
    }

    /**
     * Get the eidCaisse value.
     * @return the eidCaisse
     */
    public Long getEidCaisse() {
        return eidCaisse;
    }

    /**
     * Set the eidCaisse value.
     * @param eidCaisse the eidCaisse to set
     */
    public void setEidCaisse(Long eidCaisse) {
        this.eidCaisse = eidCaisse;
    }

    /**
     * Get the idReferent value.
     * @return the idReferent
     */
    public Long getIdReferent() {
        return idReferent;
    }

    /**
     * Set the idReferent value.
     * @param idReferent the idReferent to set
     */
    public void setIdReferent(Long idReferent) {
        this.idReferent = idReferent;
    }

    /**
     * Get the eidReferent value.
     * @return the eidReferent
     */
    public Long getEidReferent() {
        return eidReferent;
    }

    /**
     * Set the eidReferent value.
     * @param eidReferent the eidReferent to set
     */
    public void setEidReferent(Long eidReferent) {
        this.eidReferent = eidReferent;
    }

    /**
     * Get the eidRegime value.
     * @return the eidRegime
     */
    public Long getEidRegime() {
        return eidRegime;
    }

    /**
     * Set the eidRegime value.
     * @param eidRegime the eidRegime to set
     */
    public void setEidRegime(Long eidRegime) {
        this.eidRegime = eidRegime;
    }
}

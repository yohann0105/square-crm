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
 * Modèlise les informations d'une personne à synchroniser.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class InfosPersonneSyncDto implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = 3362059439971881873L;

    /**
     * Identifiant square de la personne.
     */
    private Long idPersonne;

    /**
     * Numéro de sécurité sociale de la personne.
     */
    private String numeroSecuriteSociale;

    /**
     * Clé de sécurité sociale.
     */
    private String cleSecuriteSociale;

    /**
     * Identifiant externe de la caisse de la personne.
     */
    private Long eidCaisse;

    /**
     * Identifiant du referent.
     */
    private Long idReferent;

    /**
     * Getter function.
     * @return the numeroSecuriteSociale
     */
    public String getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    /**
     * Getter function.
     * @return the cleSecuriteSociale
     */
    public String getCleSecuriteSociale() {
        return cleSecuriteSociale;
    }

    /**
     * Getter function.
     * @return the eidCaisse
     */
    public Long getEidCaisse() {
        return eidCaisse;
    }

    /**
     * Setter function.
     * @param numeroSecuriteSociale the numeroSecuriteSociale to set
     */
    public void setNumeroSecuriteSociale(String numeroSecuriteSociale) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    /**
     * Setter function.
     * @param cleSecuriteSociale the cleSecuriteSociale to set
     */
    public void setCleSecuriteSociale(String cleSecuriteSociale) {
        this.cleSecuriteSociale = cleSecuriteSociale;
    }

    /**
     * Setter function.
     * @param eidCaisse the eidCaisse to set
     */
    public void setEidCaisse(Long eidCaisse) {
        this.eidCaisse = eidCaisse;
    }

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

}

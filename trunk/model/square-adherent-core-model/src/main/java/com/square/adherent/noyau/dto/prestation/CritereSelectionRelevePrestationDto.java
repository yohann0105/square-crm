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
package com.square.adherent.noyau.dto.prestation;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Représente les critères pour une recherche de relevés de prestations.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class CritereSelectionRelevePrestationDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 8836430492436077636L;

    /** Identifiant de la personne. */
    private Long idPersonne;

    private Long relevePrestationId;

    private Boolean envoyeMail;

    private Boolean autorisationEnvoiMail;

    private Calendar dateMaxImpression;

    private Calendar dateMinImpression;

    private Integer nombreMax;

    /**
     * Retourne la valeur de relevePrestationId.
     * @return la valeur de relevePrestationId
     */
    public Long getRelevePrestationId() {
        return relevePrestationId;
    }

    /**
     * Définit la valeur de relevePrestationId.
     * @param relevePrestationId la nouvelle valeur de relevePrestationId
     */
    public void setRelevePrestationId(Long relevePrestationId) {
        this.relevePrestationId = relevePrestationId;
    }

    /**
     * Retourne la valeur de envoyeMail.
     * @return la valeur de envoyeMail
     */
    public Boolean getEnvoyeMail() {
        return envoyeMail;
    }

    /**
     * Définit la valeur de envoyeMail.
     * @param envoyeMail la nouvelle valeur de envoyeMail
     */
    public void setEnvoyeMail(Boolean envoyeMail) {
        this.envoyeMail = envoyeMail;
    }

    /**
     * Retourne la valeur de autorisationEnvoiMail.
     * @return la valeur de autorisationEnvoiMail
     */
    public Boolean getAutorisationEnvoiMail() {
        return autorisationEnvoiMail;
    }

    /**
     * Définit la valeur de autorisationEnvoiMail.
     * @param autorisationEnvoiMail la nouvelle valeur de autorisationEnvoiMail
     */
    public void setAutorisationEnvoiMail(Boolean autorisationEnvoiMail) {
        this.autorisationEnvoiMail = autorisationEnvoiMail;
    }

    /**
     * Retourne la valeur de dateMaxImpression.
     * @return la valeur de dateMaxImpression
     */
    public Calendar getDateMaxImpression() {
        return dateMaxImpression;
    }

    /**
     * Définit la valeur de dateMaxImpression.
     * @param dateMaxImpression la nouvelle valeur de dateMaxImpression
     */
    public void setDateMaxImpression(Calendar dateMaxImpression) {
        this.dateMaxImpression = dateMaxImpression;
    }

    /**
     * Get the dateMinImpression value.
     * @return the dateMinImpression
     */
    public Calendar getDateMinImpression() {
        return dateMinImpression;
    }

    /**
     * Set the dateMinImpression value.
     * @param dateMinImpression the dateMinImpression to set
     */
    public void setDateMinImpression(Calendar dateMinImpression) {
        this.dateMinImpression = dateMinImpression;
    }

    /**
     * Récupère la valeur de idPersonne.
     * @return la valeur de idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Définit la valeur de idPersonne.
     * @param idPersonne la nouvelle valeur de idPersonne
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    /**
     * Get the nombreMax value.
     * @return the nombreMax
     */
    public Integer getNombreMax() {
        return nombreMax;
    }

    /**
     * Set the nombreMax value.
     * @param nombreMax the nombreMax to set
     */
    public void setNombreMax(Integer nombreMax) {
        this.nombreMax = nombreMax;
    }
}

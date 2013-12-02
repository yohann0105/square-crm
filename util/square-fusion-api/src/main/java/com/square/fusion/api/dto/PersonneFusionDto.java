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
package com.square.fusion.api.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 * DTO représentant une personne pour la fusion.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class PersonneFusionDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 530732007149811054L;

    /**Identifiant de la personne. */
    private Long identifiant;

    /** Numéro du client. */
    private String numeroClient;

    /** Date de création. */
    private Calendar dateCreation;

    /** Flag indiquant que la personne possède un contrat. */
    protected Boolean possedeContrat;

    /** Flag indiquant que la personne possède ou non une opportunité transformée. */
    protected Boolean possedeOppTransformee;

    /**
     * Récupère la valeur de identifiant.
     * @return la valeur de identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Définit la valeur de identifiant.
     * @param identifiant la nouvelle valeur de identifiant
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Récupère la valeur de numeroClient.
     * @return la valeur de numeroClient
     */
    public String getNumeroClient() {
        return numeroClient;
    }

    /**
     * Définit la valeur de numeroClient.
     * @param numeroClient la nouvelle valeur de numeroClient
     */
    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
    }

    /**
     * Récupère la valeur de possedeContrat.
     * @return la valeur de possedeContrat
     */
    public Boolean getPossedeContrat() {
        return possedeContrat;
    }

    /**
     * Définit la valeur de possedeContrat.
     * @param possedeContrat la nouvelle valeur de possedeContrat
     */
    public void setPossedeContrat(Boolean possedeContrat) {
        this.possedeContrat = possedeContrat;
    }

    /**
     * Récupère la valeur de dateCreation.
     * @return la valeur de dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Définit la valeur de dateCreation.
     * @param dateCreation la nouvelle valeur de dateCreation
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Getter.
     * @return the possedeOppTransformee
     */
    public Boolean getPossedeOppTransformee() {
        return possedeOppTransformee;
    }

    /**
     * Setter.
     * @param possedeOppTransformee the possedeOppTransformee to set
     */
    public void setPossedeOppTransformee(Boolean possedeOppTransformee) {
        this.possedeOppTransformee = possedeOppTransformee;
    }

}

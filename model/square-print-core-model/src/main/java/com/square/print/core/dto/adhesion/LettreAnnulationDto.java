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
package com.square.print.core.dto.adhesion;

import java.io.Serializable;
import java.util.Calendar;

import com.square.print.core.dto.AgenceEditiqueDto;
import com.square.print.core.dto.ProspectDevisDto;

/**
 * DTO représentant l'ensemble des données nécessaires à la génération de la lettre d'annulation.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net)
 */
public class LettreAnnulationDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 7543377845951838946L;

    /** Numéro du dossier. */
    private String numeroDossier;

    /** Prospect associé à l'adhésion. */
    private ProspectDevisDto prospect;

    /** Agence du responsable. */
    private AgenceEditiqueDto agence;

    /** Date de creation de l'adhésion. */
    private Calendar dateCreationAdhesion;

    /** Date d'envoi du courrier. */
    private Calendar dateCourrier;

    /** Constructeur. */
    public LettreAnnulationDto() {
    }

    /**
     * Get the prospect value.
     * @return the prospect
     */
    public ProspectDevisDto getProspect() {
        return prospect;
    }

    /**
     * Set the prospect value.
     * @param prospect the prospect to set
     */
    public void setProspect(ProspectDevisDto prospect) {
        this.prospect = prospect;
    }

    /**
     * Get the agence value.
     * @return the agence
     */
    public AgenceEditiqueDto getAgence() {
        return agence;
    }

    /**
     * Set the agence value.
     * @param agence the agence to set
     */
    public void setAgence(AgenceEditiqueDto agence) {
        this.agence = agence;
    }

    /**
     * Get the dateCreationAdhesion value.
     * @return the dateCreationAdhesion
     */
    public Calendar getDateCreationAdhesion() {
        return dateCreationAdhesion;
    }

    /**
     * Set the dateCreationAdhesion value.
     * @param dateCreationAdhesion the dateCreationAdhesion to set
     */
    public void setDateCreationAdhesion(Calendar dateCreationAdhesion) {
        this.dateCreationAdhesion = dateCreationAdhesion;
    }

    /**
     * Get the dateCourrier value.
     * @return the dateCourrier
     */
    public Calendar getDateCourrier() {
        return dateCourrier;
    }

    /**
     * Set the dateCourrier value.
     * @param dateCourrier the dateCourrier to set
     */
    public void setDateCourrier(Calendar dateCourrier) {
        this.dateCourrier = dateCourrier;
    }

    /**
     * Get the numeroDossier value.
     * @return the numeroDossier
     */
    public String getNumeroDossier() {
        return numeroDossier;
    }

    /**
     * Set the numeroDossier value.
     * @param numeroDossier the numeroDossier to set
     */
    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

}

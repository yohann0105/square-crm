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
import java.util.HashSet;
import java.util.Set;

/**
 * Dto contenant les critères de recherche pour récupérer les rendez vous dans l'agenda.
 * @author cblanchard - SCUB
 */
public class RendezVousCriteresRechercheDto implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = 5703965957698867173L;

    /** Identifiant de la ressource. */
    private Long idRessource;

    /** Date min. */
    private Calendar dateMinDateDebut;

    /** Date max. */
    private Calendar dateMaxDateDebut;

    /** Identifiants des différents statuts des actions recherchées. */
    private Set<Long> idsStatut;

    /**
     * Renvoi la valeur de dateMinDateDebut.
     * @return dateMinDateDebut
     */
    public Calendar getDateMinDateDebut() {
        return dateMinDateDebut;
    }

    /**
     * Modifie dateMinDateDebut.
     * @param dateMinDateDebut la nouvelle valeur de dateMinDateDebut
     */
    public void setDateMinDateDebut(Calendar dateMinDateDebut) {
        this.dateMinDateDebut = dateMinDateDebut;
    }

    /**
     * Renvoi la valeur de dateMaxDateDebut.
     * @return dateMaxDateDebut
     */
    public Calendar getDateMaxDateDebut() {
        return dateMaxDateDebut;
    }

    /**
     * Modifie dateMaxDateDebut.
     * @param dateMaxDateDebut la nouvelle valeur de dateMaxDateDebut
     */
    public void setDateMaxDateDebut(Calendar dateMaxDateDebut) {
        this.dateMaxDateDebut = dateMaxDateDebut;
    }

    /**
     * Getter function.
     * @return the idsStatut
     */
    public Set<Long> getIdsStatut() {
        if (idsStatut == null) {
            idsStatut = new HashSet<Long>();
        }
        return idsStatut;
    }

    /**
     * Setter function.
     * @param idsStatut the idsStatut to set
     */
    public void setIdsStatut(Set<Long> idsStatut) {
        this.idsStatut = idsStatut;
    }

    /**
     * Get the value of idRessource.
     * @return the idRessource
     */
    public Long getIdRessource() {
        return idRessource;
    }

    /**
     * Set the value of idRessource.
     * @param idRessource the idRessource to set
     */
    public void setIdRessource(Long idRessource) {
        this.idRessource = idRessource;
    }

}

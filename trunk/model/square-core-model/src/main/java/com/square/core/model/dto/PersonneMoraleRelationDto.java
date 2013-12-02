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

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Représentation des personnes morales. TODO version temporaire
 */
public class PersonneMoraleRelationDto extends PersonneRelationDto implements Serializable {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 3144519846433052299L;

    /**
     * La raison sociale.
     */
    private String raisonSociale;

    /**
     * Le numéro d'entreprise.
     */
    private String numEntreprise;

    /**
     * Nature de la personne.
     */
    private IdentifiantLibelleDto naturePersonne;

    /**
     * Renvoie la raison sociale.
     * @return the raisonSociale
     */
    public String getRaisonSociale() {
        return raisonSociale;
    }

    /**
     * Modifie la raison sociale.
     * @param raisonSociale the raisonSociale to set
     */
    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    /**
     * Renvoie le numéro de l'entreprise.
     * @return the numEntreprise
     */
    public String getNumEntreprise() {
        return numEntreprise;
    }

    /**
     * modifie le numéro de l'entreprise.
     * @param numEntreprise the numEntreprise to set
     */
    public void setNumEntreprise(String numEntreprise) {
        this.numEntreprise = numEntreprise;
    }

    /**
     * Récupère la valeur de naturePersonne.
     * @return la valeur de naturePersonne
     */
    public IdentifiantLibelleDto getNaturePersonne() {
        return naturePersonne;
    }

    /**
     * Définit la valeur de naturePersonne.
     * @param naturePersonne la nouvelle valeur de naturePersonne
     */
    public void setNaturePersonne(IdentifiantLibelleDto naturePersonne) {
        this.naturePersonne = naturePersonne;
    }
}

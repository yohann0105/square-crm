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
package com.square.adherent.noyau.dto.banque;

import java.io.Serializable;

/**
 * DTO regroupant les informations d'une banque.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class BanqueDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -7825779447858758911L;

    /** Libelle de la banque. */
    private String libelleBanque;

    /** Libelle l'agence. */
    private String libelleAgence;

    /**
     * Récupère la valeur de libelleBanque.
     * @return la valeur de libelleBanque
     */
    public String getLibelleBanque() {
        return libelleBanque;
    }

    /**
     * Définit la valeur de libelleBanque.
     * @param libelleBanque la nouvelle valeur de libelleBanque
     */
    public void setLibelleBanque(String libelleBanque) {
        this.libelleBanque = libelleBanque;
    }

    /**
     * Récupère la valeur de libelleAgence.
     * @return la valeur de libelleAgence
     */
    public String getLibelleAgence() {
        return libelleAgence;
    }

    /**
     * Définit la valeur de libelleAgence.
     * @param libelleAgence la nouvelle valeur de libelleAgence
     */
    public void setLibelleAgence(String libelleAgence) {
        this.libelleAgence = libelleAgence;
    }
}

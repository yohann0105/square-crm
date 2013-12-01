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
package com.square.price.core.dto;

import java.io.Serializable;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO d'un produit d'un adhérent.
 * @author Nicolas Peltier (nicolas.peltier@scub.net)
 */
public class ProduitAdherentDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -6068658200336220408L;

    /** Identifiant du produit. */
    private Integer identifiant;

    /** Code Role. */
    private IdentifiantEidLibelleDto role;

    /** Uid Bénéficiaire. */
    private Long uidBeneficiaire;

    /** Liste des critères de tarifs. */
    private List<TarifCritereDto> listeCriteres;

    /** Mode tarification. */
    private IdentifiantLibelleDto modeTarification;

    /** Famille. */
    private IdentifiantEidLibelleDto famille;

    /**
     * Get the value of identifiant.
     * @return the identifiant
     */
    public Integer getIdentifiant() {
        return identifiant;
    }

    /**
     * Set the value of identifiant.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Get the value of uidBeneficiaire.
     * @return the uidBeneficiaire
     */
    public Long getUidBeneficiaire() {
        return uidBeneficiaire;
    }

    /**
     * Set the value of uidBeneficiaire.
     * @param uidBeneficiaire the uidBeneficiaire to set
     */
    public void setUidBeneficiaire(Long uidBeneficiaire) {
        this.uidBeneficiaire = uidBeneficiaire;
    }

    /**
     * Get the value of listeCriteres.
     * @return the listeCriteres
     */
    public List<TarifCritereDto> getListeCriteres() {
        return listeCriteres;
    }

    /**
     * Set the value of listeCriteres.
     * @param listeCriteres the listeCriteres to set
     */
    public void setListeCriteres(List<TarifCritereDto> listeCriteres) {
        this.listeCriteres = listeCriteres;
    }

    /**
     * Get the value of modeTarification.
     * @return the modeTarification
     */
    public IdentifiantLibelleDto getModeTarification() {
        return modeTarification;
    }

    /**
     * Set the value of modeTarification.
     * @param modeTarification the modeTarification to set
     */
    public void setModeTarification(IdentifiantLibelleDto modeTarification) {
        this.modeTarification = modeTarification;
    }

    /**
     * Récupère la valeur de role.
     * @return la valeur de role
     */
    public IdentifiantEidLibelleDto getRole() {
        return role;
    }

    /**
     * Définit la valeur de role.
     * @param role la nouvelle valeur de role
     */
    public void setRole(IdentifiantEidLibelleDto role) {
        this.role = role;
    }

    /**
     * Récupère la valeur de famille.
     * @return la valeur de famille
     */
    public IdentifiantEidLibelleDto getFamille() {
        return famille;
    }

    /**
     * Définit la valeur de famille.
     * @param famille la nouvelle valeur de famille
     */
    public void setFamille(IdentifiantEidLibelleDto famille) {
        this.famille = famille;
    }

}

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

/**
 * Dto encapsulant les informations pour la population d'un contrat d'une personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PopulationDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -7842468473974132970L;

    /** Libellé de la tranche de la population. */
    private String libelle;

    /** Effectif de la tranche de la population. */
    private Integer effectif;

    /** Ordre d'affichage. */
    private Integer ordre;

    /**
     * Récupère la valeur de libelle.
     * @return la valeur de libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Définit la valeur de libelle.
     * @param libelle la nouvelle valeur de libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Récupère la valeur de effectif.
     * @return la valeur de effectif
     */
    public Integer getEffectif() {
        return effectif;
    }

    /**
     * Définit la valeur de effectif.
     * @param effectif la nouvelle valeur de effectif
     */
    public void setEffectif(Integer effectif) {
        this.effectif = effectif;
    }

    /**
     * Récupère la valeur de ordre.
     * @return la valeur de ordre
     */
    public Integer getOrdre() {
        return ordre;
    }

    /**
     * Définit la valeur de ordre.
     * @param ordre la nouvelle valeur de ordre
     */
    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }
}

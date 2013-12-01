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
package com.square.composant.contrat.personne.morale.square.client.model;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle encapsulant les informations simplifiée du contrat d'une personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ContratSimplePersonneMoraleModel implements IsSerializable {

    /** Identifiant du contrat. */
    private Long identifiant;

    /** Numéro du contrat. */
    private String numeroContrat;

    /** Statut du contrat. */
    private IdentifiantLibelleGwt statut;

    /** Date de création du contrat. */
    private String dateCreation;

    /** Date d'effet du contrat.*/
    private String dateEffet;

    /** Date de résiliation du contrat. */
    private String dateResiliation;

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
     * Récupère la valeur de numeroContrat.
     * @return la valeur de numeroContrat
     */
    public String getNumeroContrat() {
        return numeroContrat;
    }

    /**
     * Définit la valeur de numeroContrat.
     * @param numeroContrat la nouvelle valeur de numeroContrat
     */
    public void setNumeroContrat(String numeroContrat) {
        this.numeroContrat = numeroContrat;
    }

    /**
     * Récupère la valeur de statut.
     * @return la valeur de statut
     */
    public IdentifiantLibelleGwt getStatut() {
        return statut;
    }

    /**
     * Définit la valeur de statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(IdentifiantLibelleGwt statut) {
        this.statut = statut;
    }

    /**
     * Récupère la valeur de dateEffet.
     * @return la valeur de dateEffet
     */
    public String getDateEffet() {
        return dateEffet;
    }

    /**
     * Définit la valeur de dateEffet.
     * @param dateEffet la nouvelle valeur de dateEffet
     */
    public void setDateEffet(String dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Récupère la valeur de dateResiliation.
     * @return la valeur de dateResiliation
     */
    public String getDateResiliation() {
        return dateResiliation;
    }

    /**
     * Définit la valeur de dateResiliation.
     * @param dateResiliation la nouvelle valeur de dateResiliation
     */
    public void setDateResiliation(String dateResiliation) {
        this.dateResiliation = dateResiliation;
    }

    /**
     * Récupère la valeur de dateCreation.
     * @return la valeur de dateCreation
     */
    public String getDateCreation() {
        return dateCreation;
    }

    /**
     * Définit la valeur de dateCreation.
     * @param dateCreation la nouvelle valeur de dateCreation
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }
}

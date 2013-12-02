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
package com.square.composant.contrat.square.client.model;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle encapsulant les informations d'un contrat sous forme de synthèse.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ContratSimpleModel implements IsSerializable {

    /** Identifiant du contrat. */
    private Long identifiant;

    /**
     * Le numéro du contrat.
     */
    private String numeroContrat;

    /**
     * Le statut du contrat.
     */
    private IdentifiantLibelleGwt statut;

    /**
     * Date de signature du contrat.
     */
    private String dateSignature;

    /**
     * Date d'effet du contrat.
     */
    private String dateEffet;

    /**
     * Date de résiliation du contrat.
     */
    private String dateResiliation;

    /**
     * Getter function.
     * @return the numeroContrat
     */
    public String getNumeroContrat() {
        return numeroContrat;
    }

    /**
     * Getter function.
     * @return the statut
     */
    public IdentifiantLibelleGwt getStatut() {
        return statut;
    }

    /**
     * Getter function.
     * @return the dateEffet
     */
    public String getDateEffet() {
        return dateEffet;
    }

    /**
     * Getter function.
     * @return the dateResiliation
     */
    public String getDateResiliation() {
        return dateResiliation;
    }

    /**
     * Setter function.
     * @param numeroContrat the numeroContrat to set
     */
    public void setNumeroContrat(String numeroContrat) {
        this.numeroContrat = numeroContrat;
    }

    /**
     * Setter function.
     * @param statut the statut to set
     */
    public void setStatut(IdentifiantLibelleGwt statut) {
        this.statut = statut;
    }

    /**
     * Setter function.
     * @param dateEffet the dateEffet to set
     */
    public void setDateEffet(String dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Setter function.
     * @param dateResiliation the dateResiliation to set
     */
    public void setDateResiliation(String dateResiliation) {
        this.dateResiliation = dateResiliation;
    }

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
     * Récupère la valeur de dateSignature.
     * @return la valeur de dateSignature
     */
    public String getDateSignature() {
        return dateSignature;
    }

    /**
     * Définit la valeur de dateSignature.
     * @param dateSignature la nouvelle valeur de dateSignature
     */
    public void setDateSignature(String dateSignature) {
        this.dateSignature = dateSignature;
    }
}

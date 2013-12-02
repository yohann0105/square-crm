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
 * Modèle encapsulant les informations d'un bénéficiaire pour une garantie.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class InfosGarantieBeneficiaireModel implements IsSerializable {

    /**
     * Identifiant du bénéficiaire.
     */
    private Long idBeneficiaire;

    /**
     * Date d'adhésion.
     */
    private String dateAdhesion;

    /**
     * Date à laquelle la garantie a été résiliée pour ce bénéficiaire.
     */
    private String dateResiliation;

    /** Le statut de la garantie. */
    private IdentifiantLibelleGwt statut;

    /**
     * Getter function.
     * @return the idBeneficiaire
     */
    public Long getIdBeneficiaire() {
        return idBeneficiaire;
    }

    /**
     * Getter function.
     * @return the dateAdhesion
     */
    public String getDateAdhesion() {
        return dateAdhesion;
    }

    /**
     * Setter function.
     * @param idBeneficiaire the idBeneficiaire to set
     */
    public void setIdBeneficiaire(Long idBeneficiaire) {
        this.idBeneficiaire = idBeneficiaire;
    }

    /**
     * Setter function.
     * @param dateAdhesion the dateAdhesion to set
     */
    public void setDateAdhesion(String dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
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
     * Accesseur pour l'attribut dateResiliation.
     * @return l'attribut dateResiliation
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

}

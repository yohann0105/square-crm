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
import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO encapsulant les informations d'un bénéficiaire pour une garantie.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class InfosGarantieBeneficiaireDto implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 4265781810702087407L;

    /**
     * Identifiant du bénéficiaire.
     */
    private Long idBeneficiaire;

    /**
     * Date d'adhésion.
     */
    private Calendar dateAdhesion;

    /**
     * Date à laquelle la garantie a été résiliée pour ce bénéficiaire.
     */
    private Calendar dateResiliation;

    /** Le statut de la garantie. */
    private IdentifiantLibelleDto statut;

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
    public Calendar getDateAdhesion() {
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
    public void setDateAdhesion(Calendar dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }

    /**
     * Récupère la valeur de statut.
     * @return la valeur de statut
     */
    public IdentifiantLibelleDto getStatut() {
        return statut;
    }

    /**
     * Définit la valeur de statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(IdentifiantLibelleDto statut) {
        this.statut = statut;
    }

    /**
     * Accesseur pour l'attribut dateResiliation.
     * @return l'attribut dateResiliation
     */
    public Calendar getDateResiliation() {
        return dateResiliation;
    }

    /**
     * Définit la valeur de dateResiliation.
     * @param dateResiliation la nouvelle valeur de dateResiliation
     */
    public void setDateResiliation(Calendar dateResiliation) {
        this.dateResiliation = dateResiliation;
    }

}

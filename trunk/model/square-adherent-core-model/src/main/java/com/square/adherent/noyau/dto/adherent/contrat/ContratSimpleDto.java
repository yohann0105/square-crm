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
 * DTO encapsulant les informations d'un contrat sous forme de synthèse.
 *
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class ContratSimpleDto implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6062204093277319727L;

    /** Identifiant du contrat. */
    private Long identifiant;

    /**
     * Le numéro du contrat.
     */
    private String numeroContrat;

    /**
     * Le statut du contrat.
     */
    private IdentifiantLibelleDto statut;

    /**
     * La nature du contrat.
     */
    private IdentifiantLibelleDto nature;

    /**
     * Date de création du contrat.
     */
    private Calendar dateCreation;

    /**
     * Date de signature du contrat.
     */
    private Calendar dateSignature;

    /**
     * Date d'effet du contrat.
     */
    private Calendar dateEffet;

    /**
     * Date de résiliation du contrat.
     */
    private Calendar dateResiliation;

    /** Code de l'apporteur. */
    private String codeApporteur;

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
    public IdentifiantLibelleDto getStatut() {
        return statut;
    }

    /**
     * Getter function.
     * @return the dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Getter function.
     * @return the dateEffet
     */
    public Calendar getDateEffet() {
        return dateEffet;
    }

    /**
     * Getter function.
     * @return the dateResiliation
     */
    public Calendar getDateResiliation() {
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
    public void setStatut(IdentifiantLibelleDto statut) {
        this.statut = statut;
    }

    /**
     * Setter function.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Setter function.
     * @param dateEffet the dateEffet to set
     */
    public void setDateEffet(Calendar dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Setter function.
     * @param dateResiliation the dateResiliation to set
     */
    public void setDateResiliation(Calendar dateResiliation) {
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
     * Récupère la valeur de codeApporteur.
     * @return la valeur de codeApporteur
     */
    public String getCodeApporteur() {
        return codeApporteur;
    }

    /**
     * Définit la valeur de codeApporteur.
     * @param codeApporteur la nouvelle valeur de codeApporteur
     */
    public void setCodeApporteur(String codeApporteur) {
        this.codeApporteur = codeApporteur;
    }

    /**
     * Récupère la valeur de dateSignature.
     * @return la valeur de dateSignature
     */
    public Calendar getDateSignature() {
        return dateSignature;
    }

    /**
     * Définit la valeur de dateSignature.
     * @param dateSignature la nouvelle valeur de dateSignature
     */
    public void setDateSignature(Calendar dateSignature) {
        this.dateSignature = dateSignature;
    }

    /**
     * Getter function.
     * @return the nature
     */
    public IdentifiantLibelleDto getNature() {
        return nature;
    }

    /**
     * Setter function.
     * @param nature the nature to set
     */
    public void setNature(IdentifiantLibelleDto nature) {
        this.nature = nature;
    }
}

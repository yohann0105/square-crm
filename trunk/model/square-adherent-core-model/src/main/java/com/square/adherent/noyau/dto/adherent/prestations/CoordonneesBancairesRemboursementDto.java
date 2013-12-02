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
package com.square.adherent.noyau.dto.adherent.prestations;

import java.io.Serializable;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

import com.square.adherent.noyau.dto.adherent.contrat.InfosBanqueDto;

/**
 * DTO qui encapsule les coordonnées bancaires spécifiques aux remboursements de prestations.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class CoordonneesBancairesRemboursementDto implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 8895231468659533215L;

    /**
     * Le numéro du contrat.
     */
    private String numeroContrat;

    /**
     * L'id et le nom/prénom de la personne bénéficiant des soins.
     */
    private IdentifiantLibelleDto beneficiaireSoins;

    /**
     * L'id et le nom/prénom du destinataire des remboursements des soins.
     */
    private IdentifiantLibelleDto destinataireRemboursements;

    /**
     * Le moyen de paiement des remboursements.
     */
    private IdentifiantLibelleDto moyenPaiement;

    /**
     * Les informations bancaires du compte où est reversé le paiement.
     */
    private InfosBanqueDto infosBancaires;

    /**
     * Getter function.
     * @return the numeroContrat
     */
    public String getNumeroContrat() {
        return numeroContrat;
    }

    /**
     * Getter function.
     * @return the beneficiaireSoins
     */
    public IdentifiantLibelleDto getBeneficiaireSoins() {
        return beneficiaireSoins;
    }

    /**
     * Getter function.
     * @return the destinataireRemboursements
     */
    public IdentifiantLibelleDto getDestinataireRemboursements() {
        return destinataireRemboursements;
    }

    /**
     * Getter function.
     * @return the moyenPaiement
     */
    public IdentifiantLibelleDto getMoyenPaiement() {
        return moyenPaiement;
    }

    /**
     * Getter function.
     * @return the infosBancaires
     */
    public InfosBanqueDto getInfosBancaires() {
        return infosBancaires;
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
     * @param beneficiaireSoins the beneficiaireSoins to set
     */
    public void setBeneficiaireSoins(IdentifiantLibelleDto beneficiaireSoins) {
        this.beneficiaireSoins = beneficiaireSoins;
    }

    /**
     * Setter function.
     * @param destinataireRemboursements the destinataireRemboursements to set
     */
    public void setDestinataireRemboursements(IdentifiantLibelleDto destinataireRemboursements) {
        this.destinataireRemboursements = destinataireRemboursements;
    }

    /**
     * Setter function.
     * @param moyenPaiement the moyenPaiement to set
     */
    public void setMoyenPaiement(IdentifiantLibelleDto moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    /**
     * Setter function.
     * @param infosBancaires the infosBancaires to set
     */
    public void setInfosBancaires(InfosBanqueDto infosBancaires) {
        this.infosBancaires = infosBancaires;
    }

}

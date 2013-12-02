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

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO encapsulant les informations de paiement.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class InfosPaiementDto implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -3344349834559549197L;

    /**
     * Moyen de paiement.
     */
    private IdentifiantLibelleDto moyenPaiement;

    /**
     * Fréquence de paiement.
     */
    private IdentifiantLibelleDto frequencePaiement;

    /**
     * Jour de paiement.
     */
    private Integer jourPaiement;

    /**
     * Getter function.
     * @return the moyenPaiement
     */
    public IdentifiantLibelleDto getMoyenPaiement() {
        return moyenPaiement;
    }

    /**
     * Getter function.
     * @return the frequencePaiement
     */
    public IdentifiantLibelleDto getFrequencePaiement() {
        return frequencePaiement;
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
     * @param frequencePaiement the frequencePaiement to set
     */
    public void setFrequencePaiement(IdentifiantLibelleDto frequencePaiement) {
        this.frequencePaiement = frequencePaiement;
    }

    /**
     * Récupère la valeur de jourPaiement.
     * @return la valeur de jourPaiement
     */
    public Integer getJourPaiement() {
        return jourPaiement;
    }

    /**
     * Définit la valeur de jourPaiement.
     * @param jourPaiement la nouvelle valeur de jourPaiement
     */
    public void setJourPaiement(Integer jourPaiement) {
        this.jourPaiement = jourPaiement;
    }

}

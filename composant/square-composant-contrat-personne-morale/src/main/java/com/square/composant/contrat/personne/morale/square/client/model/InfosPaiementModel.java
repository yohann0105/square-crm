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
 * Modèle encapsulant les informations de paiement d'un contrat d'une personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class InfosPaiementModel implements IsSerializable {

    /** Moyen de paiement de la cotisation. */
    private IdentifiantLibelleGwt moyenPaiementCotis;

    /** Fréquence de paiement de la cotisation. */
    private IdentifiantLibelleGwt frequencePaiementCotis;

    /** Jour de paiement de la cotisation. */
    private Integer jourPaiement;

    /**
     * Récupère la valeur de moyenPaiementCotis.
     * @return la valeur de moyenPaiementCotis
     */
    public IdentifiantLibelleGwt getMoyenPaiementCotis() {
        return moyenPaiementCotis;
    }

    /**
     * Définit la valeur de moyenPaiementCotis.
     * @param moyenPaiementCotis la nouvelle valeur de moyenPaiementCotis
     */
    public void setMoyenPaiementCotis(IdentifiantLibelleGwt moyenPaiementCotis) {
        this.moyenPaiementCotis = moyenPaiementCotis;
    }

    /**
     * Récupère la valeur de frequencePaiementCotis.
     * @return la valeur de frequencePaiementCotis
     */
    public IdentifiantLibelleGwt getFrequencePaiementCotis() {
        return frequencePaiementCotis;
    }

    /**
     * Définit la valeur de frequencePaiementCotis.
     * @param frequencePaiementCotis la nouvelle valeur de frequencePaiementCotis
     */
    public void setFrequencePaiementCotis(IdentifiantLibelleGwt frequencePaiementCotis) {
        this.frequencePaiementCotis = frequencePaiementCotis;
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

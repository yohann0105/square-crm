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
package com.square.adherent.noyau.model.data.contrat;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Modèle d'une synthèse de contrats.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table (name = "DATA_CONTRAT_SYNTHESE")
public class SyntheseContrat implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -4144036622877437454L;

    /** Identifiant. */
    @Id
    @Column(name = "CONTRAT_SYNTHESE_UID")
    private Long id;

    /** UID de la personne associée. */
    @Column(name = "CONTRAT_SYNTHESE_PERSONNE_UID")
    private Long uidPersonne;

    /** Date de première mutualisation. */
    @Column(name = "CONTRAT_SYNTHESE_DATE_MUTUALISATION")
    private Calendar datePremiereMutualisation;

    /** Date de dernière radiation. */
    @Column(name = "CONTRAT_SYNTHESE_DATE_RADIATION")
    private Calendar dateDerniereRadiation;

    //  TODO En attente des nouveaux champs   /**
    //     * Motif de dernière radiation.
    //     */
    //    private String motifDerniereRadiation;

    /** Nombre d'années de fidélité. */
    @Column(name = "CONTRAT_SYNTHESE_FIDELITE_ANNEE")
    private Integer nbAnneesFidelite;

    /** Nombre de mois de fidélité. */
    @Column(name = "CONTRAT_SYNTHESE_FIDELITE_MOIS")
    private Integer nbMoisFidelite;

    /** Flag télétransmission. */
    @Column(name = "CONTRAT_SYNTHESE_TELETRANSMISSION_FLAG")
    private Boolean teletransmission;

    /**
     * Récupère la valeur de id.
     * @return la valeur de id
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère la valeur de dateDerniereRadiation.
     * @return la valeur de dateDerniereRadiation
     */
    public Calendar getDateDerniereRadiation() {
        return dateDerniereRadiation;
    }

    /**
     * Définit la valeur de dateDerniereRadiation.
     * @param dateDerniereRadiation la nouvelle valeur de dateDerniereRadiation
     */
    public void setDateDerniereRadiation(Calendar dateDerniereRadiation) {
        this.dateDerniereRadiation = dateDerniereRadiation;
    }

    /**
     * Récupère la valeur de nbAnneesFidelite.
     * @return la valeur de nbAnneesFidelite
     */
    public Integer getNbAnneesFidelite() {
        return nbAnneesFidelite;
    }

    /**
     * Définit la valeur de nbAnneesFidelite.
     * @param nbAnneesFidelite la nouvelle valeur de nbAnneesFidelite
     */
    public void setNbAnneesFidelite(Integer nbAnneesFidelite) {
        this.nbAnneesFidelite = nbAnneesFidelite;
    }

    /**
     * Récupère la valeur de nbMoisFidelite.
     * @return la valeur de nbMoisFidelite
     */
    public Integer getNbMoisFidelite() {
        return nbMoisFidelite;
    }

    /**
     * Définit la valeur de nbMoisFidelite.
     * @param nbMoisFidelite la nouvelle valeur de nbMoisFidelite
     */
    public void setNbMoisFidelite(Integer nbMoisFidelite) {
        this.nbMoisFidelite = nbMoisFidelite;
    }

    /**
     * Récupère la valeur de teletransmission.
     * @return la valeur de teletransmission
     */
    public Boolean getTeletransmission() {
        return teletransmission;
    }

    /**
     * Définit la valeur de teletransmission.
     * @param teletransmission la nouvelle valeur de teletransmission
     */
    public void setTeletransmission(Boolean teletransmission) {
        this.teletransmission = teletransmission;
    }

    /**
     * Récupère la valeur de uidPersonne.
     * @return la valeur de uidPersonne
     */
    public Long getUidPersonne() {
        return uidPersonne;
    }

    /**
     * Définit la valeur de uidPersonne.
     * @param uidPersonne la nouvelle valeur de uidPersonne
     */
    public void setUidPersonne(Long uidPersonne) {
        this.uidPersonne = uidPersonne;
    }

    /**
     * Récupère la valeur de datePremiereMutualisation.
     * @return la valeur de datePremiereMutualisation
     */
    public Calendar getDatePremiereMutualisation() {
        return datePremiereMutualisation;
    }

    /**
     * Définit la valeur de datePremiereMutualisation.
     * @param datePremiereMutualisation la nouvelle valeur de datePremiereMutualisation
     */
    public void setDatePremiereMutualisation(Calendar datePremiereMutualisation) {
        this.datePremiereMutualisation = datePremiereMutualisation;
    }

}

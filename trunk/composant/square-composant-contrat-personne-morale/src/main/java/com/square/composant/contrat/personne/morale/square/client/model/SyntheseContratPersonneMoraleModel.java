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

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle encapsulant les informations de synthèse des contrats d'une personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class SyntheseContratPersonneMoraleModel implements IsSerializable {

    /** Statut. */
    private IdentifiantLibelleGwt statut;

    /** Date de première mutualisation. */
    private String datePremiereMutualisation;

    /** Date de dernière radiation. */
    private String dateDerniereRadiation;

    /** Motif de dernière radiation. */
    private String motifDerniereRadiation;

    /** Nombre d'années de fidélité. */
    private Integer nbAnneesFidelite;

    /** Nombre de mois de fidélité. */
    private Integer nbMoisFidelite;

    /** Gestion du contrat. */
    private String gestionDuContrat;

    /** Gestionnaire. */
    private String gestionnaire;

    /** Liste des populations. */
    private List<PopulationModel> population;

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
     * Récupère la valeur de datePremiereMutualisation.
     * @return la valeur de datePremiereMutualisation
     */
    public String getDatePremiereMutualisation() {
        return datePremiereMutualisation;
    }

    /**
     * Définit la valeur de datePremiereMutualisation.
     * @param datePremiereMutualisation la nouvelle valeur de datePremiereMutualisation
     */
    public void setDatePremiereMutualisation(String datePremiereMutualisation) {
        this.datePremiereMutualisation = datePremiereMutualisation;
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
     * Récupère la valeur de gestionDuContrat.
     * @return la valeur de gestionDuContrat
     */
    public String getGestionDuContrat() {
        return gestionDuContrat;
    }

    /**
     * Définit la valeur de gestionDuContrat.
     * @param gestionDuContrat la nouvelle valeur de gestionDuContrat
     */
    public void setGestionDuContrat(String gestionDuContrat) {
        this.gestionDuContrat = gestionDuContrat;
    }

    /**
     * Récupère la valeur de gestionnaire.
     * @return la valeur de gestionnaire
     */
    public String getGestionnaire() {
        return gestionnaire;
    }

    /**
     * Définit la valeur de gestionnaire.
     * @param gestionnaire la nouvelle valeur de gestionnaire
     */
    public void setGestionnaire(String gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    /**
     * Récupère la valeur de population.
     * @return la valeur de population
     */
    public List<PopulationModel> getPopulation() {
        if (population == null) {
            population = new ArrayList<PopulationModel>();
        }
        return population;
    }

    /**
     * Définit la valeur de population.
     * @param population la nouvelle valeur de population
     */
    public void setPopulation(List<PopulationModel> population) {
        this.population = population;
    }

    /**
     * Récupère la valeur de dateDerniereRadiation.
     * @return la valeur de dateDerniereRadiation
     */
    public String getDateDerniereRadiation() {
        return dateDerniereRadiation;
    }

    /**
     * Définit la valeur de dateDerniereRadiation.
     * @param dateDerniereRadiation la nouvelle valeur de dateDerniereRadiation
     */
    public void setDateDerniereRadiation(String dateDerniereRadiation) {
        this.dateDerniereRadiation = dateDerniereRadiation;
    }

    /**
     * Récupère la valeur de motifDerniereRadiation.
     * @return la valeur de motifDerniereRadiation
     */
    public String getMotifDerniereRadiation() {
        return motifDerniereRadiation;
    }

    /**
     * Définit la valeur de motifDerniereRadiation.
     * @param motifDerniereRadiation la nouvelle valeur de motifDerniereRadiation
     */
    public void setMotifDerniereRadiation(String motifDerniereRadiation) {
        this.motifDerniereRadiation = motifDerniereRadiation;
    }
}

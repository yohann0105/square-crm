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

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modèle encapsulant les informations de synthèse d'un contrat.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class InfosContratsModel implements IsSerializable {

    /**
     * Identifiant et libellé du segment des contrats.
     */
    private IdentifiantLibelleGwt segment;

    /**
     * Identifiant et libellé du statut des contrats.
     */
    private IdentifiantLibelleGwt statut;

    /**
     * Date de première mutualisation.
     */
    private String datePremiereMutualisation;

    /**
     * Date de dernière radiation.
     */
    private String dateDerniereRadiation;

    /**
     * Motif de dernière radiation.
     */
    private String motifDerniereRadiation;

    /**
     * Nombre d'années de fidélité.
     */
    private Integer nbAnneesFidelite;

    /**
     * Nombre de mois de fidélité.
     */
    private Integer nbMoisFidelite;

    /**
     * Flag télétransmission.
     */
    private Boolean teletransmission;

    /**
     * Population.
     */
    private String population;

    /**
     * Gestion du contrat.
     */
    private String gestionDuContrat;

    /**
     * Gestionnaire.
     */
    private String gestionnaire;

    /**
     * Réserve Banco.
     */
    private List<ReserveBancoModel> listeReservesBanco;

    /**
     * Récapitulatif garanties souscrites.
     */
    private RecapitulatifGarantiesContratModel listeGarantiesContrat;

    /**
     * Liste des ratios Prestations/Cotisations par personne et par année.
     */
    private List<RatioPrestationCotisationModel> listeRatiosPrestationCotisation;

    /**
     * Getter function.
     * @return the segment
     */
    public IdentifiantLibelleGwt getSegment() {
        return segment;
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
     * @return the datePremiereMutualisation
     */
    public String getDatePremiereMutualisation() {
        return datePremiereMutualisation;
    }

    /**
     * Getter function.
     * @return the dateDerniereRadiation
     */
    public String getDateDerniereRadiation() {
        return dateDerniereRadiation;
    }

    /**
     * Getter function.
     * @return the motifDerniereRadiation
     */
    public String getMotifDerniereRadiation() {
        return motifDerniereRadiation;
    }

    /**
     * Getter function.
     * @return the nbAnneesFidelite
     */
    public Integer getNbAnneesFidelite() {
        return nbAnneesFidelite;
    }

    /**
     * Getter function.
     * @return the nbMoisFidelite
     */
    public Integer getNbMoisFidelite() {
        return nbMoisFidelite;
    }

    /**
     * Getter function.
     * @return the teletransmission
     */
    public Boolean getTeletransmission() {
        return teletransmission;
    }

    /**
     * Getter function.
     * @return the population
     */
    public String getPopulation() {
        return population;
    }

    /**
     * Getter function.
     * @return the gestionDuContrat
     */
    public String getGestionDuContrat() {
        return gestionDuContrat;
    }

    /**
     * Getter function.
     * @return the gestionnaire
     */
    public String getGestionnaire() {
        return gestionnaire;
    }

    /**
     * Getter function.
     * @return the listeGarantiesContrat
     */
    public RecapitulatifGarantiesContratModel getListeGarantiesContrat() {
        return listeGarantiesContrat;
    }

    /**
     * Setter function.
     * @param segment the segment to set
     */
    public void setSegment(IdentifiantLibelleGwt segment) {
        this.segment = segment;
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
     * @param datePremiereMutualisation the datePremiereMutualisation to set
     */
    public void setDatePremiereMutualisation(String datePremiereMutualisation) {
        this.datePremiereMutualisation = datePremiereMutualisation;
    }

    /**
     * Setter function.
     * @param dateDerniereRadiation the dateDerniereRadiation to set
     */
    public void setDateDerniereRadiation(String dateDerniereRadiation) {
        this.dateDerniereRadiation = dateDerniereRadiation;
    }

    /**
     * Setter function.
     * @param motifDerniereRadiation the motifDerniereRadiation to set
     */
    public void setMotifDerniereRadiation(String motifDerniereRadiation) {
        this.motifDerniereRadiation = motifDerniereRadiation;
    }

    /**
     * Setter function.
     * @param nbAnneesFidelite the nbAnneesFidelite to set
     */
    public void setNbAnneesFidelite(Integer nbAnneesFidelite) {
        this.nbAnneesFidelite = nbAnneesFidelite;
    }

    /**
     * Setter function.
     * @param nbMoisFidelite the nbMoisFidelite to set
     */
    public void setNbMoisFidelite(Integer nbMoisFidelite) {
        this.nbMoisFidelite = nbMoisFidelite;
    }

    /**
     * Setter function.
     * @param teletransmission the teletransmission to set
     */
    public void setTeletransmission(Boolean teletransmission) {
        this.teletransmission = teletransmission;
    }

    /**
     * Setter function.
     * @param population the population to set
     */
    public void setPopulation(String population) {
        this.population = population;
    }

    /**
     * Setter function.
     * @param gestionDuContrat the gestionDuContrat to set
     */
    public void setGestionDuContrat(String gestionDuContrat) {
        this.gestionDuContrat = gestionDuContrat;
    }

    /**
     * Setter function.
     * @param gestionnaire the gestionnaire to set
     */
    public void setGestionnaire(String gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    /**
     * Setter function.
     * @param listeGarantiesContrat the listeGarantiesContrat to set
     */
    public void setListeGarantiesContrat(RecapitulatifGarantiesContratModel listeGarantiesContrat) {
        this.listeGarantiesContrat = listeGarantiesContrat;
    }

    /**
     * Récupère la valeur de listeRatiosPrestationCotisation.
     * @return la valeur de listeRatiosPrestationCotisation
     */
    public List<RatioPrestationCotisationModel> getListeRatiosPrestationCotisation() {
        if (listeRatiosPrestationCotisation == null) {
            listeRatiosPrestationCotisation = new ArrayList<RatioPrestationCotisationModel>();
        }
        return listeRatiosPrestationCotisation;
    }

    /**
     * Définit la valeur de listeRatiosPrestationCotisation.
     * @param listeRatiosPrestationCotisation la nouvelle valeur de listeRatiosPrestationCotisation
     */
    public void setListeRatiosPrestationCotisation(List<RatioPrestationCotisationModel> listeRatiosPrestationCotisation) {
        this.listeRatiosPrestationCotisation = listeRatiosPrestationCotisation;
    }

    /**
     * Get the value of listeReservesBanco.
     * @return the listeReservesBanco
     */
    public List<ReserveBancoModel> getListeReservesBanco() {
        return listeReservesBanco;
    }

    /**
     * Set the value of listeReservesBanco.
     * @param listeReservesBanco the listeReservesBanco to set
     */
    public void setListeReservesBanco(List<ReserveBancoModel> listeReservesBanco) {
        this.listeReservesBanco = listeReservesBanco;
    }

}

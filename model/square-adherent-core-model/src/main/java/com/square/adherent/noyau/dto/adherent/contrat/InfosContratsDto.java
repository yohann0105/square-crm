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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * DTO encapsulant les informations de synthèse d'un contrat.
 *
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class InfosContratsDto implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -4667232688621629158L;

    /**
     * Identifiant et libellé du segment des contrats.
     */
    private IdentifiantLibelleDto segment;

    /**
     * Identifiant et libellé du statut des contrats.
     */
    private IdentifiantLibelleDto statut;

    /**
     * Date de première mutualisation.
     */
    private Calendar datePremiereMutualisation;

    /**
     * Date de dernière radiation.
     */
    private Calendar dateDerniereRadiation;

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
    private List<ReserveBancoDto> listeReservesBanco;

    /**
     * Récapitulatif garanties souscrites.
     */
    private RecapitulatifGarantiesContratDto listeGarantiesContrat;

    /**
     * Liste des ratios Prestations/Cotisations par personne et par année.
     */
    private List<RatioPrestationCotisationDto> listeRatiosPrestationCotisation;

    /**
     * Getter function.
     * @return the segment
     */
    public IdentifiantLibelleDto getSegment() {
        return segment;
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
     * @return the datePremiereMutualisation
     */
    public Calendar getDatePremiereMutualisation() {
        return datePremiereMutualisation;
    }

    /**
     * Getter function.
     * @return the dateDerniereRadiation
     */
    public Calendar getDateDerniereRadiation() {
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
    public RecapitulatifGarantiesContratDto getListeGarantiesContrat() {
        return listeGarantiesContrat;
    }

    /**
     * Setter function.
     * @param segment the segment to set
     */
    public void setSegment(IdentifiantLibelleDto segment) {
        this.segment = segment;
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
     * @param datePremiereMutualisation the datePremiereMutualisation to set
     */
    public void setDatePremiereMutualisation(Calendar datePremiereMutualisation) {
        this.datePremiereMutualisation = datePremiereMutualisation;
    }

    /**
     * Setter function.
     * @param dateDerniereRadiation the dateDerniereRadiation to set
     */
    public void setDateDerniereRadiation(Calendar dateDerniereRadiation) {
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
    public void setListeGarantiesContrat(RecapitulatifGarantiesContratDto listeGarantiesContrat) {
        this.listeGarantiesContrat = listeGarantiesContrat;
    }

    /**
     * Récupère la valeur de listeRatiosPrestationCotisation.
     * @return la valeur de listeRatiosPrestationCotisation
     */
    public List<RatioPrestationCotisationDto> getListeRatiosPrestationCotisation() {
        if (listeRatiosPrestationCotisation == null) {
            listeRatiosPrestationCotisation = new ArrayList<RatioPrestationCotisationDto>();
        }
        return listeRatiosPrestationCotisation;
    }

    /**
     * Définit la valeur de listeRatiosPrestationCotisation.
     * @param listeRatiosPrestationCotisation la nouvelle valeur de listeRatiosPrestationCotisation
     */
    public void setListeRatiosPrestationCotisation(List<RatioPrestationCotisationDto> listeRatiosPrestationCotisation) {
        this.listeRatiosPrestationCotisation = listeRatiosPrestationCotisation;
    }

    /**
     * Get the value of listeReservesBanco.
     * @return the listeReservesBanco
     */
    public List<ReserveBancoDto> getListeReservesBanco() {
        return listeReservesBanco;
    }

    /**
     * Set the value of listeReservesBanco.
     * @param listeReservesBanco the listeReservesBanco to set
     */
    public void setListeReservesBanco(List<ReserveBancoDto> listeReservesBanco) {
        this.listeReservesBanco = listeReservesBanco;
    }

}

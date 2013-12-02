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
 * Modèle qui encapsule les informations d'un contrat.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ContratModel extends ContratSimpleModel implements IsSerializable {

    /**
     * Identifiant et libellé du segment du contrat.
     */
    private IdentifiantLibelleGwt segment;

    /**
     * Récapitulatif des garanties souscrites dans le contrat.
     */
    private RecapitulatifGarantiesContratModel recapitulatifGarantiesContrat;

    /**
     * L'apporteur du contrat.
     */
    private String apporteur;

    /**
     * Le gestionnaire du contrat.
     */
    private String gestionnaire;

    /**
     * Liste des garanties souscrites dans le contrat.
     */
    private List<GarantieModel> listeGaranties;

    /**
     * Informations de paiement pour les prestations associées au contrat.
     */
    private InfosPaiementModel infosPaiementPrestation;

    /**
     * Informations de paiement pour les cotisations associées au contrat.
     */
    private InfosPaiementModel infosPaiementCotisation;

    /** Souscripteur payeur. */
    private IdentifiantLibelleGwt souscripteur;

    /** Type payeur. */
    private IdentifiantLibelleGwt typePayeur;

    /**
     * Liste des ajustements appliqués à la garantie.
     */
    private List<AjustementTarifModel> listeAjustements;

    /**
     * Getter function.
     * @return the recapitulatifGarantiesContrat
     */
    public RecapitulatifGarantiesContratModel getRecapitulatifGarantiesContrat() {
        return recapitulatifGarantiesContrat;
    }

    /**
     * Getter function.
     * @return the apporteur
     */
    public String getApporteur() {
        return apporteur;
    }

    /**
     * Getter function.
     * @return the infosPaiementPrestation
     */
    public InfosPaiementModel getInfosPaiementPrestation() {
        return infosPaiementPrestation;
    }

    /**
     * Getter function.
     * @return the infosPaiementCotisation
     */
    public InfosPaiementModel getInfosPaiementCotisation() {
        return infosPaiementCotisation;
    }

    /**
     * Setter function.
     * @param recapitulatifGarantiesContrat the recapitulatifGarantiesContrat to set
     */
    public void setRecapitulatifGarantiesContrat(RecapitulatifGarantiesContratModel recapitulatifGarantiesContrat) {
        this.recapitulatifGarantiesContrat = recapitulatifGarantiesContrat;
    }

    /**
     * Setter function.
     * @param apporteur the apporteur to set
     */
    public void setApporteur(String apporteur) {
        this.apporteur = apporteur;
    }

    /**
     * Setter function.
     * @param infosPaiementPrestation the infosPaiementPrestation to set
     */
    public void setInfosPaiementPrestation(InfosPaiementModel infosPaiementPrestation) {
        this.infosPaiementPrestation = infosPaiementPrestation;
    }

    /**
     * Setter function.
     * @param infosPaiementCotisation the infosPaiementCotisation to set
     */
    public void setInfosPaiementCotisation(InfosPaiementModel infosPaiementCotisation) {
        this.infosPaiementCotisation = infosPaiementCotisation;
    }

    /**
     * Getter function.
     * @return the listeGaranties
     */
    public List<GarantieModel> getListeGaranties() {
        if (listeGaranties == null) {
            listeGaranties = new ArrayList<GarantieModel>();
        }
        return listeGaranties;
    }

    /**
     * Setter function.
     * @param listeGaranties the listeGaranties to set
     */
    public void setListeGaranties(List<GarantieModel> listeGaranties) {
        this.listeGaranties = listeGaranties;
    }

    /**
     * Récupère la valeur de segment.
     * @return la valeur de segment
     */
    public IdentifiantLibelleGwt getSegment() {
        return segment;
    }

    /**
     * Définit la valeur de segment.
     * @param segment la nouvelle valeur de segment
     */
    public void setSegment(IdentifiantLibelleGwt segment) {
        this.segment = segment;
    }

    /**
     * Récupère la valeur de souscripteur.
     * @return la valeur de souscripteur
     */
    public IdentifiantLibelleGwt getSouscripteur() {
        return souscripteur;
    }

    /**
     * Définit la valeur de souscripteur.
     * @param souscripteur la nouvelle valeur de souscripteur
     */
    public void setSouscripteur(IdentifiantLibelleGwt souscripteur) {
        this.souscripteur = souscripteur;
    }

    /**
     * Récupère la valeur de typePayeur.
     * @return la valeur de typePayeur
     */
    public IdentifiantLibelleGwt getTypePayeur() {
        return typePayeur;
    }

    /**
     * Définit la valeur de typePayeur.
     * @param typePayeur la nouvelle valeur de typePayeur
     */
    public void setTypePayeur(IdentifiantLibelleGwt typePayeur) {
        this.typePayeur = typePayeur;
    }

    /**
     * Getter function.
     * @return the listeAjustements
     */
    public List<AjustementTarifModel> getListeAjustements() {
        if (listeAjustements == null) {
            listeAjustements = new ArrayList<AjustementTarifModel>();
        }
        return listeAjustements;
    }

    /**
     * Setter function.
     * @param listeAjustements the listeAjustements to set
     */
    public void setListeAjustements(List<AjustementTarifModel> listeAjustements) {
        this.listeAjustements = listeAjustements;
    }

    /**
     * Getter function.
     * @return the gestionnaire
     */
    public String getGestionnaire() {
        return gestionnaire;
    }

    /**
     * Setter function.
     * @param gestionnaire the gestionnaire to set
     */
    public void setGestionnaire(String gestionnaire) {
        this.gestionnaire = gestionnaire;
    }
}

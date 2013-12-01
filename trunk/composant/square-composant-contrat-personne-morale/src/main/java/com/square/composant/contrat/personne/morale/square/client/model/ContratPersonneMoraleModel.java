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
 * Modèle encapsulant les informations d'un contrat d'une personne morale.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ContratPersonneMoraleModel extends ContratSimplePersonneMoraleModel implements IsSerializable {

    /** Récapitulatif des populations pour les différents statuts. */
    private RecapitulatifPopulationModel recapitulatifPopulation;

    /** Produit Gestion. */
    private String produitGestion;

    /** Identifiant et libellé du segment du contrat. */
    private IdentifiantLibelleGwt segment;

    /** Identifiant et libellé du type de gestion. */
    private IdentifiantLibelleGwt typeGestion;

    /** Type de souscription. */
    private String typeSouscription;

    /** Apporteur. */
    private String apporteur;

    /** Nombre d'adhérents. */
    private Integer nbAdherents;

    /** Nombre de bénéficiaires. */
    private Integer nbBeneficiaires;

    /** Informations de paiement de la cotisation. */
    private InfosPaiementPersonneMoraleModel infosPaiement;

    /** Liste des garanties souscrites dans le contrat. */
    private List<GarantiePersonneMoraleModel> listeGaranties;

    /** Liste des produits non trouvés associés aux garanties du contrat. */
    private List<String> listeProduitsNonTrouves;

    /**
     * Récupère la valeur de recapitulatifPopulation.
     * @return la valeur de recapitulatifPopulation
     */
    public RecapitulatifPopulationModel getRecapitulatifPopulation() {
        return recapitulatifPopulation;
    }

    /**
     * Définit la valeur de recapitulatifPopulation.
     * @param recapitulatifPopulation la nouvelle valeur de recapitulatifPopulation
     */
    public void setRecapitulatifPopulation(RecapitulatifPopulationModel recapitulatifPopulation) {
        this.recapitulatifPopulation = recapitulatifPopulation;
    }

    /**
     * Récupère la valeur de produitGestion.
     * @return la valeur de produitGestion
     */
    public String getProduitGestion() {
        return produitGestion;
    }

    /**
     * Définit la valeur de produitGestion.
     * @param produitGestion la nouvelle valeur de produitGestion
     */
    public void setProduitGestion(String produitGestion) {
        this.produitGestion = produitGestion;
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
     * Récupère la valeur de typeGestion.
     * @return la valeur de typeGestion
     */
    public IdentifiantLibelleGwt getTypeGestion() {
        return typeGestion;
    }

    /**
     * Définit la valeur de typeGestion.
     * @param typeGestion la nouvelle valeur de typeGestion
     */
    public void setTypeGestion(IdentifiantLibelleGwt typeGestion) {
        this.typeGestion = typeGestion;
    }

    /**
     * Récupère la valeur de typeSouscription.
     * @return la valeur de typeSouscription
     */
    public String getTypeSouscription() {
        return typeSouscription;
    }

    /**
     * Définit la valeur de typeSouscription.
     * @param typeSouscription la nouvelle valeur de typeSouscription
     */
    public void setTypeSouscription(String typeSouscription) {
        this.typeSouscription = typeSouscription;
    }

    /**
     * Récupère la valeur de apporteur.
     * @return la valeur de apporteur
     */
    public String getApporteur() {
        return apporteur;
    }

    /**
     * Définit la valeur de apporteur.
     * @param apporteur la nouvelle valeur de apporteur
     */
    public void setApporteur(String apporteur) {
        this.apporteur = apporteur;
    }

    /**
     * Récupère la valeur de nbAdherents.
     * @return la valeur de nbAdherents
     */
    public Integer getNbAdherents() {
        return nbAdherents;
    }

    /**
     * Définit la valeur de nbAdherents.
     * @param nbAdherents la nouvelle valeur de nbAdherents
     */
    public void setNbAdherents(Integer nbAdherents) {
        this.nbAdherents = nbAdherents;
    }

    /**
     * Récupère la valeur de nbBeneficiaires.
     * @return la valeur de nbBeneficiaires
     */
    public Integer getNbBeneficiaires() {
        return nbBeneficiaires;
    }

    /**
     * Définit la valeur de nbBeneficiaires.
     * @param nbBeneficiaires la nouvelle valeur de nbBeneficiaires
     */
    public void setNbBeneficiaires(Integer nbBeneficiaires) {
        this.nbBeneficiaires = nbBeneficiaires;
    }

    /**
     * Récupère la valeur de infosPaiement.
     * @return la valeur de infosPaiement
     */
    public InfosPaiementPersonneMoraleModel getInfosPaiement() {
        return infosPaiement;
    }

    /**
     * Définit la valeur de infosPaiement.
     * @param infosPaiement la nouvelle valeur de infosPaiement
     */
    public void setInfosPaiement(InfosPaiementPersonneMoraleModel infosPaiement) {
        this.infosPaiement = infosPaiement;
    }

    /**
     * Récupère la valeur de listeGaranties.
     * @return la valeur de listeGaranties
     */
    public List<GarantiePersonneMoraleModel> getListeGaranties() {
        if (listeGaranties == null) {
            listeGaranties = new ArrayList<GarantiePersonneMoraleModel>();
        }
        return listeGaranties;
    }

    /**
     * Définit la valeur de listeGaranties.
     * @param listeGaranties la nouvelle valeur de listeGaranties
     */
    public void setListeGaranties(List<GarantiePersonneMoraleModel> listeGaranties) {
        this.listeGaranties = listeGaranties;
    }

    /**
     * Getter function.
     * @return the listeProduitsNonTrouves
     */
    public List<String> getListeProduitsNonTrouves() {
        return listeProduitsNonTrouves;
    }

    /**
     * Setter function.
     * @param listeProduitsNonTrouves the listeProduitsNonTrouves to set
     */
    public void setListeProduitsNonTrouves(List<String> listeProduitsNonTrouves) {
        this.listeProduitsNonTrouves = listeProduitsNonTrouves;
    }
}

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
package com.square.composant.tarificateur.square.client.model.selecteur;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.DateUtil;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.composant.tarificateur.square.client.composant.SelecteurProduitEventMapper;

/**
 * Model representant un produit pour le selecteur de produit .
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class ProduitSelecteurModel implements IsSerializable, SelecteurProduitEventMapper<Boolean> {

    private static final String EST_INCORRECTE = " est incorrecte : ";

    private static final String L_AGE_DE = "L'âge de ";

    private static final int CENT_CINQUANTE = 150;

    /**
     * Identifiant.
     */
    private Integer identifiant;

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Ordre dans la famille.
     */
    private Integer ordreFamille;

    /**
     * Assure Principal.
     */
    private AssureSelecteurModel assurePrincipal;

    /**
     * Liste des bénéficiaires.
     */
    private List<AssureSelecteurModel> listeBeneficiaires;

    /**
     * Liste des criteres.
     */
    private List<CritereSelecteurModel> listeCriteres;

    /**
     * Contrainte de vente.
     */
    private ContrainteVenteSelecteurModel contraintesVente;

    /**
     * Flag de selection.
     */
    private Boolean isSelection;

    /**
     * Flag actif/non actif.
     */
    private Boolean isActif = true;

    /** Flag indiquant si le produit est ouvert à la vente ou non. */
    private Boolean isProduitOuvertVente;

    /**
     * Get the identifiant value.
     * @return the identifiant
     */
    public Integer getIdentifiant() {
        return identifiant;
    }

    /**
     * Set the identifiant value.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Get the libelle value.
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Set the libelle value.
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Get the ordreFamille value.
     * @return the ordreFamille
     */
    public Integer getOrdreFamille() {
        return ordreFamille;
    }

    /**
     * Set the ordreFamille value.
     * @param ordreFamille the ordreFamille to set
     */
    public void setOrdreFamille(Integer ordreFamille) {
        this.ordreFamille = ordreFamille;
    }

    /**
     * Get the assurePrincipal value.
     * @return the assurePrincipal
     */
    public AssureSelecteurModel getAssurePrincipal() {
        return assurePrincipal;
    }

    /**
     * Set the assurePrincipal value.
     * @param assurePrincipal the assurePrincipal to set
     */
    public void setAssurePrincipal(AssureSelecteurModel assurePrincipal) {
        this.assurePrincipal = assurePrincipal;
    }

    /**
     * Recupere la valeur de contraintesVente.
     * @return la valeur de contraintesVente
     */
    public ContrainteVenteSelecteurModel getContraintesVente() {
        return contraintesVente;
    }

    /**
     * Definit la valeur de contraintesVente.
     * @param contraintesVente la nouvelle valeur de contraintesVente
     */
    public void setContraintesVente(ContrainteVenteSelecteurModel contraintesVente) {
        this.contraintesVente = contraintesVente;
    }

    /**
     * Recupere la valeur de isSelection.
     * @return la valeur de isSelection
     */
    public Boolean getIsSelection() {
        return isSelection;
    }

    /**
     * Definit la valeur de isSelection.
     * @param isSelection la nouvelle valeur de isSelection
     */
    public void setIsSelection(Boolean isSelection) {
        this.isSelection = isSelection;
    }

    /**
     * Contrôle les erreurs en renvoyant un rapport d'erreurs si nécessaire.
     * @param idCritereGeneration idCritereGeneration
     * @param onlyCriteresBloquants flag pour récupérer que les erreurs bloquantes
     * @param identifiantsBeneficiairesInactifs liste des bénéficiaires non sélectionnables
     * @return une liste de rapports
     */
    public List<RapportErreurModel> controlerErreurs(Integer idCritereGeneration, boolean onlyCriteresBloquants, List<Long> identifiantsBeneficiairesInactifs) {
        final List<RapportErreurModel> listeRapports = new ArrayList<RapportErreurModel>();
        boolean hasAssureSelected = false;

        // on verifie les erreurs si le produit est selectionné
        if (isSelection.booleanValue()) {
            if (contraintesVente != null && isSelection != null) {
                final RapportErreurModel rapportAssurePrincipal = controlerContraintesVenteAssure(assurePrincipal);
                if (rapportAssurePrincipal != null) {
                    listeRapports.add(rapportAssurePrincipal);
                }
            }
            // on verifie si l'assure principal est selectionné
            if (assurePrincipal.getIsSelection() != null && assurePrincipal.getIsSelection().booleanValue()) {
                hasAssureSelected = true;
            }

            if (listeBeneficiaires != null) {
                for (AssureSelecteurModel beneficiaire : listeBeneficiaires) {
                    if (contraintesVente != null && isSelection != null) {
                        final RapportErreurModel rapportBeneficiaire = controlerContraintesVenteAssure(beneficiaire);
                        if (rapportBeneficiaire != null) {
                            listeRapports.add(rapportBeneficiaire);
                        }
                    }

                    // on verifie si le benef est selectionné
                    if (beneficiaire.getIsSelection() != null && beneficiaire.getIsSelection().booleanValue()) {
                        hasAssureSelected = true;
                    }

                    // si le bénéficiaire est sélectionné on vérifie qu'il est actif
                    if (beneficiaire.getIsSelection() != null && beneficiaire.getIsSelection().booleanValue()
                        && identifiantsBeneficiairesInactifs.contains(beneficiaire.getEidPersonne())) {
                        final RapportErreurModel rapportBeneficiaire = new RapportErreurModel();
                        rapportBeneficiaire.setNomPersonne(beneficiaire.getNom());
                        final List<String> listeErreurs = new ArrayList<String>();
                        listeErreurs.add(beneficiaire.getPrenom() + " n'est plus une relation active de l'assuré principal");
                        rapportBeneficiaire.setListeErreurs(listeErreurs);
                        listeRapports.add(rapportBeneficiaire);
                    }
                }
            }

            // si le produit est un produit bonus, la date d'effet doit être antérieure à la date du jour
            if (!hasAssureSelected) {
                final List<String> listeErreurs = new ArrayList<String>();
                final String erreur = "Aucun bénéficiaire n'a été sélectionné pour ce produit";
                listeErreurs.add(erreur);

                // Création du rapport d'erreurs
                final RapportErreurModel rapportErreurs = new RapportErreurModel();
                rapportErreurs.setNomProduit(null);
                rapportErreurs.setNomPersonne(null);
                rapportErreurs.setListeErreurs(listeErreurs);
                listeRapports.add(rapportErreurs);
            }
            if (!onlyCriteresBloquants) {
                // controle du changement de generation
                for (ValeurCritereAssureSelecteurModel valeur : assurePrincipal.getListeValeursCriteres()) {
                    if (valeur.getIdentifiantCritere().equals(idCritereGeneration)) {
                        // Détermination de la valeur de référence du code génération
                        String valeurReference = null;
                        if (isProduitOuvertVente != null && isProduitOuvertVente) {
                            valeurReference = "2012";
                        }
                        else if (assurePrincipal.getDateEffetTarification() != null) {
                            valeurReference = assurePrincipal.getDateEffetTarification().split("/")[2];
                        }

                        // Si le produit est ouvert à la vente, il faut "2012", si non on reste avec la date d'effet
                        if (valeurReference != null && !valeurReference.equals(valeur.getValeur())) {
                            final List<String> listeErreurs = new ArrayList<String>();
                            final String erreur = "La génération de ce produit a été modifiée";
                            listeErreurs.add(erreur);
                            // Création du rapport d'erreurs
                            final RapportErreurModel rapportErreurs = new RapportErreurModel();
                            rapportErreurs.setNomProduit(null);
                            rapportErreurs.setNomPersonne(null);
                            rapportErreurs.setListeErreurs(listeErreurs);
                            listeRapports.add(rapportErreurs);
                        }
                    }
                }
            }
        }
        return listeRapports;
    }

    /**
     * Contrôle les contraintes de vente en renvoyant un rapport d'erreurs si nécessaire.
     * @return le rapport d'erreurs s'il y en a, null sinon
     */
    private RapportErreurModel controlerContraintesVenteAssure(AssureSelecteurModel assure) {
        // on ne verifie les contraintes que si l'assure est selectionné
        if (assure.getIsSelection() != null && assure.getIsSelection().booleanValue()) {

            final String nomPersonne = new StringBuffer(assure.getNom().toUpperCase()).append(" ").append(assure.getPrenom()).toString();

            final List<String> listeErreurs = new ArrayList<String>();

            if (assure.getDateEffetTarification() != null) {
                // Criteres sur la date d'effet
                if (contraintesVente.getDateMinEffet() != null) {
                    // Erreur si date d'effet < dateMin
                    if (DateUtil.isDate1BeforeDate2(assure.getDateEffetTarification(), contraintesVente.getDateMinEffet())) {
                        final String erreur =
                            new StringBuffer("La date d'effet de ").append(nomPersonne).append(" est inférieure à la date minimale (").append(
                                contraintesVente.getDateMinEffet()).append(")").toString();
                        listeErreurs.add(erreur);
                    }
                }
                if (contraintesVente.getDateMaxEffet() != null) {
                    // Erreur si date d'effet > dateMax
                    if (DateUtil.isDate1AfterDate2(assure.getDateEffetTarification(), contraintesVente.getDateMaxEffet())) {
                        final String erreur =
                            new StringBuffer("La date d'effet de ").append(nomPersonne).append(" est supérieure à la date maximale (").append(
                                contraintesVente.getDateMaxEffet()).append(")").toString();
                        listeErreurs.add(erreur);
                    }
                }
            }
            else {
                final String erreur = new StringBuffer("La date d'effet de ").append(nomPersonne).append(" n'est pas renseignée").toString();
                listeErreurs.add(erreur);
            }

            // Critère sur l'age
            boolean erreurAge = false;
            if (assure.equals(assurePrincipal)) {
                // Erreur si l'age est inférieur à l'age minimal
                if (contraintesVente.getAgeMinProspect() != null) {
                    if (assure.getAge() != null && assure.getAge().intValue() < contraintesVente.getAgeMinProspect().intValue()) {
                        erreurAge = true;
                    }
                }
                // Erreur si l'age est supérieur à l'age maximal
                if (contraintesVente.getAgeMaxProspect() != null) {
                    if (assure.getAge() != null && assure.getAge().intValue() > contraintesVente.getAgeMaxProspect().intValue()) {
                        erreurAge = true;
                    }
                }

                if (erreurAge) {
                    if (contraintesVente.getAgeMinProspect() != null && contraintesVente.getAgeMaxProspect() != null) {
                        if (contraintesVente.getAgeMaxProspect().intValue() > CENT_CINQUANTE) {
                            final String erreur =
                                new StringBuffer(L_AGE_DE).append(nomPersonne).append(EST_INCORRECTE).append(assure.getAge()).append(" est inférieur à ")
                                        .append(contraintesVente.getAgeMinProspect()).toString();
                            listeErreurs.add(erreur);
                        }
                        else {
                            final String erreur =
                                new StringBuffer(L_AGE_DE).append(nomPersonne).append(EST_INCORRECTE).append(assure.getAge()).append(
                                    " n'est pas compris entre ").append(contraintesVente.getAgeMinProspect()).append(" et ").append(
                                    contraintesVente.getAgeMaxProspect()).toString();
                            listeErreurs.add(erreur);
                        }
                    }
                    else if (contraintesVente.getAgeMaxProspect() == null) {
                        final String erreur =
                            new StringBuffer(L_AGE_DE).append(nomPersonne).append(EST_INCORRECTE).append(assure.getAge()).append(" est inférieur à ").append(
                                contraintesVente.getAgeMinProspect()).toString();
                        listeErreurs.add(erreur);
                    }
                    else if (contraintesVente.getAgeMinProspect() == null) {
                        if (contraintesVente.getAgeMaxProspect().intValue() < CENT_CINQUANTE) {
                            final String erreur =
                                new StringBuffer(L_AGE_DE).append(nomPersonne).append(EST_INCORRECTE).append(assure.getAge()).append(" est supérieur à ")
                                        .append(contraintesVente.getAgeMaxProspect()).toString();
                            listeErreurs.add(erreur);
                        }
                    }
                }
            }
            else {
                // Erreur si l'age est inférieur à l'age minimal
                if (contraintesVente.getAgeMinBeneficiaire() != null) {
                    if (assure.getAge() != null && assure.getAge().intValue() < contraintesVente.getAgeMinBeneficiaire().intValue()) {
                        erreurAge = true;
                    }
                }
                // Erreur si l'age est supérieur à l'age maximal
                if (contraintesVente.getAgeMaxBeneficiaire() != null) {
                    if (assure.getAge() != null && assure.getAge().intValue() > contraintesVente.getAgeMaxBeneficiaire().intValue()) {
                        erreurAge = true;
                    }
                }

                if (erreurAge) {
                    if (contraintesVente.getAgeMinBeneficiaire() != null && contraintesVente.getAgeMaxBeneficiaire() != null) {
                        if (contraintesVente.getAgeMaxBeneficiaire().intValue() > CENT_CINQUANTE) {
                            final String erreur =
                                new StringBuffer(L_AGE_DE).append(nomPersonne).append(EST_INCORRECTE).append(assure.getAge()).append(" est inférieur à ")
                                        .append(contraintesVente.getAgeMinBeneficiaire()).toString();
                            listeErreurs.add(erreur);
                        }
                        else {
                            final String erreur =
                                new StringBuffer(L_AGE_DE).append(nomPersonne).append(EST_INCORRECTE).append(assure.getAge()).append(
                                    " n'est pas compris entre ").append(contraintesVente.getAgeMinBeneficiaire()).append(" et ").append(
                                    contraintesVente.getAgeMaxBeneficiaire()).toString();
                            listeErreurs.add(erreur);
                        }
                    }
                    else if (contraintesVente.getAgeMaxBeneficiaire() == null) {
                        final String erreur =
                            new StringBuffer(L_AGE_DE).append(nomPersonne).append(EST_INCORRECTE).append(assure.getAge()).append(" est inférieur à ").append(
                                contraintesVente.getAgeMinBeneficiaire()).toString();
                        listeErreurs.add(erreur);
                    }
                    else if (contraintesVente.getAgeMinBeneficiaire() == null) {
                        if (contraintesVente.getAgeMaxBeneficiaire().intValue() < CENT_CINQUANTE) {
                            final String erreur =
                                new StringBuffer(L_AGE_DE).append(nomPersonne).append(EST_INCORRECTE).append(assure.getAge()).append(" est supérieur à ")
                                        .append(contraintesVente.getAgeMaxBeneficiaire()).toString();
                            listeErreurs.add(erreur);
                        }
                    }
                }
            }

            // Si la liste d'erreurs n'est pas vide
            if (listeErreurs.size() != 0) {
                // Création du rapport d'erreurs
                final RapportErreurModel rapportErreurs = new RapportErreurModel();
                rapportErreurs.setNomProduit(libelle);
                rapportErreurs.setNomPersonne(nomPersonne);
                rapportErreurs.setListeErreurs(listeErreurs);

                return rapportErreurs;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean getValeurMapper() {
        return isSelection;
    }

    /**
     * {@inheritDoc}
     */
    public void setValeurMapper(Boolean valeurMapper) {
        isSelection = valeurMapper;
    }

    /**
     * Get the isActif value.
     * @return the isActif
     */
    public Boolean getIsActif() {
        return isActif;
    }

    /**
     * Set the isActif value.
     * @param isActif the isActif to set
     */
    public void setIsActif(Boolean isActif) {
        this.isActif = isActif;
    }

    /**
     * Get the listeBeneficiaires value.
     * @return the listeBeneficiaires
     */
    public List<AssureSelecteurModel> getListeBeneficiaires() {
        return listeBeneficiaires;
    }

    /**
     * Set the listeBeneficiaires value.
     * @param listeBeneficiaires the listeBeneficiaires to set
     */
    public void setListeBeneficiaires(List<AssureSelecteurModel> listeBeneficiaires) {
        this.listeBeneficiaires = listeBeneficiaires;
    }

    /**
     * Get the listeCriteres value.
     * @return the listeCriteres
     */
    public List<CritereSelecteurModel> getListeCriteres() {
        return listeCriteres;
    }

    /**
     * Set the listeCriteres value.
     * @param listeCriteres the listeCriteres to set
     */
    public void setListeCriteres(List<CritereSelecteurModel> listeCriteres) {
        this.listeCriteres = listeCriteres;
    }

    /**
     * Getter.
     * @return the isProduitOuvertVente
     */
    public Boolean getIsProduitOuvertVente() {
        return isProduitOuvertVente;
    }

    /**
     * Setter.
     * @param isProduitOuvertVente the isProduitOuvertVente to set
     */
    public void setIsProduitOuvertVente(Boolean isProduitOuvertVente) {
        this.isProduitOuvertVente = isProduitOuvertVente;
    }

}

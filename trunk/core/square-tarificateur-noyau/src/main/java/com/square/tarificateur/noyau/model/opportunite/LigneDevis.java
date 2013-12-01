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
package com.square.tarificateur.noyau.model.opportunite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.scub.foundation.framework.core.model.BaseModel;

import com.square.tarificateur.noyau.model.personne.Personne;

/**
 * Modèle d'une ligne de devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
@Entity
@Table(name = "DATA_LIGNE_DEVIS")
public class LigneDevis extends BaseModel {

    /** SerialVersionUID. */
    private static final long serialVersionUID = -5201166139190729417L;

    /** Le devis associé. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEVIS_UID")
    @ForeignKey(name = "FK_DATA_LIGNE_DEVIS_DATA_DEVIS")
    private Devis devis;

    /** Ligne de devis sur beneficiaire. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BENEFICIAIRE_UID")
    @ForeignKey(name = "FK_DATA_LIGNE_DEVIS_DATA_BENEFICIAIRE")
    private Personne beneficiaire;

    /** Montant de la ligne TTC. */
    @Column(name = "MONTANT_TTC")
    private Float montantTtc;

    /** Montant de la remise faite sur la ligne. */
    @Column(name = "MONTANT_REMISE")
    private Float montantRemise;

    /** Date effet de la ligne. */
    @Column(name = "DATE_EFFET")
    private Calendar dateEffet;

    /** Date de création de la ligne. */
    @Column(name = "DATE_CREATION")
    private Calendar dateCreation;

    /** L'identifiant du produit associé. */
    @Column(name = "PRODUIT_EID")
    private Integer eidProduit;

    /** Objet representant l'auteur (utilisateur) de cette ligne de devis. */
    @Column(name = "AUTEUR_EID", nullable = true)
    private Long eidAuteur;

    /** La finalité associée. */
    @ManyToOne
    @JoinColumn(name = "FINALITE_UID")
    @ForeignKey(name = "FK_DATA_LIGNE_DEVIS_DIM_FINALITE_UID")
    private Finalite finalite;

    /** Liste des valeurs de critères. */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "LIGNE_DEVIS_UID")
    private Set<ValeurCritereLigneDevis> listeValeurCritereLigneDevis = new HashSet<ValeurCritereLigneDevis>();

    /** Liste des Ligne Liee à la ligne. */
    @OneToMany(mappedBy = "ligneDevisSource", cascade = CascadeType.ALL)
    private Set<LigneDevisLiee> listeLignesDevisLiees = new HashSet<LigneDevisLiee>();

    /** Permet de spécifier si la ligne doit être imprimée. */
    @Column(name = "SELECTIONNE_POUR_IMPRESSION")
    private Boolean selectionnePourImpression = false;

    /** Flag indiquant que la ligne est sélectionnée pour l'adhésion. */
    @Column(name = "SELECTIONNE_POUR_ADHESION")
    private Boolean selectionnePourAdhesion = false;

    /** Liste des valeurs de règle. */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "LIGNE_DEVIS_UID")
    private List<RegleValeur> listeReglesValeurs;

    /** Source de la ligne de devis. */
    @ManyToOne
    @JoinColumn(name = "id_source_ligne_devis")
    private SourceLigneDevis sourceLigneDevis;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof LigneDevis)) {
            return false;
        }
        return equalsUtil(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Récupère la valeur de devis.
     * @return the devis
     */
    public Devis getDevis() {
        return devis;
    }

    /**
     * Définit la valeur de devis.
     * @param devis the devis to set
     */
    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    /**
     * Récupère la valeur de beneficiaire.
     * @return the beneficiaire
     */
    public Personne getBeneficiaire() {
        return beneficiaire;
    }

    /**
     * Définit la valeur de beneficiaire.
     * @param beneficiaire the beneficiaire to set
     */
    public void setBeneficiaire(Personne beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    /**
     * Récupère la valeur de montantTtc.
     * @return the montantTtc
     */
    public Float getMontantTtc() {
        return montantTtc;
    }

    /**
     * Définit la valeur de montantTtc.
     * @param montantTtc the montantTtc to set
     */
    public void setMontantTtc(Float montantTtc) {
        this.montantTtc = montantTtc;
    }

    /**
     * Récupère la valeur de montantRemise.
     * @return the montantRemise
     */
    public Float getMontantRemise() {
        return montantRemise;
    }

    /**
     * Définit la valeur de montantRemise.
     * @param montantRemise the montantRemise to set
     */
    public void setMontantRemise(Float montantRemise) {
        this.montantRemise = montantRemise;
    }

    /**
     * Récupère la valeur de dateEffet.
     * @return the dateEffet
     */
    public Calendar getDateEffet() {
        return dateEffet;
    }

    /**
     * Définit la valeur de dateEffet.
     * @param dateEffet the dateEffet to set
     */
    public void setDateEffet(Calendar dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Récupère la valeur de dateCreation.
     * @return the dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Définit la valeur de dateCreation.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Récupère la valeur de eidProduit.
     * @return the eidProduit
     */
    public Integer getEidProduit() {
        return eidProduit;
    }

    /**
     * Définit la valeur de eidProduit.
     * @param eidProduit the eidProduit to set
     */
    public void setEidProduit(Integer eidProduit) {
        this.eidProduit = eidProduit;
    }

    /**
     * Récupère la valeur de eidAuteur.
     * @return the eidAuteur
     */
    public Long getEidAuteur() {
        return eidAuteur;
    }

    /**
     * Définit la valeur de eidAuteur.
     * @param eidAuteur the eidAuteur to set
     */
    public void setEidAuteur(Long eidAuteur) {
        this.eidAuteur = eidAuteur;
    }

    /**
     * Récupère la valeur de finalite.
     * @return the finalite
     */
    public Finalite getFinalite() {
        return finalite;
    }

    /**
     * Définit la valeur de finalite.
     * @param finalite the finalite to set
     */
    public void setFinalite(Finalite finalite) {
        this.finalite = finalite;
    }

    /**
     * Récupère la valeur de listeValeurCritereLigneDevis.
     * @return the listeValeurCritereLigneDevis
     */
    public Set<ValeurCritereLigneDevis> getListeValeurCritereLigneDevis() {
        return listeValeurCritereLigneDevis;
    }

    /**
     * Définit la valeur de listeValeurCritereLigneDevis.
     * @param listeValeurCritereLigneDevis the listeValeurCritereLigneDevis to set
     */
    public void setListeValeurCritereLigneDevis(Set<ValeurCritereLigneDevis> listeValeurCritereLigneDevis) {
        this.listeValeurCritereLigneDevis = listeValeurCritereLigneDevis;
    }

    /**
     * Récupère la valeur de listeLignesDevisLiees.
     * @return the listeLignesDevisLiees
     */
    public Set<LigneDevisLiee> getListeLignesDevisLiees() {
        return listeLignesDevisLiees;
    }

    /**
     * Définit la valeur de listeLignesDevisLiees.
     * @param listeLignesDevisLiees the listeLignesDevisLiees to set
     */
    public void setListeLignesDevisLiees(Set<LigneDevisLiee> listeLignesDevisLiees) {
        this.listeLignesDevisLiees = listeLignesDevisLiees;
    }

    /**
     * Récupère la valeur de selectionnePourImpression.
     * @return the selectionnePourImpression
     */
    public Boolean getSelectionnePourImpression() {
        return selectionnePourImpression;
    }

    /**
     * Définit la valeur de selectionnePourImpression.
     * @param selectionnePourImpression the selectionnePourImpression to set
     */
    public void setSelectionnePourImpression(Boolean selectionnePourImpression) {
        this.selectionnePourImpression = selectionnePourImpression;
    }

    /**
     * Récupère la valeur de listeReglesValeurs.
     * @return the listeReglesValeurs
     */
    public List<RegleValeur> getListeReglesValeurs() {
        if (listeReglesValeurs == null) {
            listeReglesValeurs = new ArrayList<RegleValeur>();
        }
        return listeReglesValeurs;
    }

    /**
     * Définit la valeur de listeReglesValeurs.
     * @param listeReglesValeurs the listeReglesValeurs to set
     */
    public void setListeReglesValeurs(List<RegleValeur> listeReglesValeurs) {
        this.listeReglesValeurs = listeReglesValeurs;
    }

    /**
     * Recuperer la valeur.
     * @return the sourceLigneDevis
     */
    public SourceLigneDevis getSourceLigneDevis() {
        return sourceLigneDevis;
    }

    /**
     * Fixer la valeur.
     * @param sourceLigneDevis the sourceLigneDevis to set
     */
    public void setSourceLigneDevis(SourceLigneDevis sourceLigneDevis) {
        this.sourceLigneDevis = sourceLigneDevis;
    }

    /**
     * Recuperer la valeur.
     * @return the selectionnePourAdhesion
     */
    public Boolean getSelectionnePourAdhesion() {
        return selectionnePourAdhesion;
    }

    /**
     * Fixer la valeur.
     * @param selectionnePourAdhesion the selectionnePourAdhesion to set
     */
    public void setSelectionnePourAdhesion(Boolean selectionnePourAdhesion) {
        this.selectionnePourAdhesion = selectionnePourAdhesion;
    }
}

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
package com.square.composant.tarificateur.square.client.model.opportunite.devis.ligne;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model Gwt d'une ligne de devis.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class LigneDevisModel implements IsSerializable {

    /** Identifiant. */
    private Long identifiant;

    /** Montant de la ligne TTC. */
    private Float montantTtc;

    /** Date de la finalité. */
    private String dateFinalite;

    /** Date effet de la ligne. */
    private String dateEffet;

    /** Date de création de la ligne. */
    private String dateCreation;

    /** Le produit associé. */
    private Integer identifiantProduit;

    /** La finalité associée. */
    private IdentifiantLibelleGwt finalite;

    /** Liste des valeurs de critères. */
    private List<ValeurCritereLigneDevisModel> listeValeurCritereLigneDevis;

    /** Liste des Ligne Liee à la ligne. */
    private List<LigneDevisModel> listeLignesDevisLiees;

    /** Identifiant beneficiaire. */
    private Long identifiantBeneficiaire;

    /** Prénom et nom du bénéficiaire. */
    private String prenomNomBeneficiaire;

    /** Permet de spécifier si la ligne doit être imprimée. */
    private Boolean selectionnePourImpression;

    /** Indique si le produit n'a aucun tarif. */
    private Boolean nAAucunTarif;

    /** Booléen indiquant que la ligne de devis a été générer par contrat. */
    private Boolean genererParContrat;

    /** Montant de la remise faite sur la ligne. */
    private Float montantRemise;

    /** Le produit associé. */
    private String libelleProduit;

    /** Si le produit possede une grille de presta. */
    private Boolean produitAGrillePresta;

    /** L'identifiant du mode de paiement du produit. */
    private Integer identifiantModePaiement;

    /** Le mode de paiement du produit. */
    private String libelleModePaiement;

    /** Produit optionnel. */
    private Boolean produitOptionnel;

    /** Ordre d'affichage des produits. */
    private Integer ordreAffichage;

    /** Ordre d'affichage des produits dans la famille. */
    private Integer ordreFamille;

    /** Ordre d'affichage des produits dans la gamme. */
    private Integer ordreGamme;

    /** Ordre d'affichage par statut de prospects. */
    private Integer ordreStatutProspect;

    /** Ordre d'affichage par age de prospects. */
    private Integer ordreAgeProspect;

    /** Flag indiquant que la ligne est sélectionnée pour l'adhésion. */
    private Boolean selectionnePourAdhesion;

    /** Identifiant du devis. */
    private Long identifiantDevis;

    /** Identifiant du prospect associé au devis. */
    private Long identifiantProspect;

    /** Identifiant application externe. */
    private Long identifiantExterne;

    /** Flag indiquant que la ligne est sélectionnée pour le panier. */
    private Boolean selectionnePourPanier = false;

    /** Identifiant de la famille du produit. */
    private Integer identifiantFamille;

    /** Source de la ligne de devis. */
    private IdentifiantLibelleGwt sourceLigneDevis;

    /** Gestionnaire. */
    private IdentifiantLibelleGwt gestionnaire;

    /**
     * Get the dateEffet value.
     * @return the dateEffet
     */
    public String getDateEffet() {
        return dateEffet;
    }

    /**
     * Set the dateEffet value.
     * @param dateEffet the dateEffet to set
     */
    public void setDateEffet(String dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Get the dateFinalite value.
     * @return the dateFinalite
     */
    public String getDateFinalite() {
        return dateFinalite;
    }

    /**
     * Set the dateFinalite value.
     * @param dateFinalite the dateFinalite to set
     */
    public void setDateFinalite(String dateFinalite) {
        this.dateFinalite = dateFinalite;
    }

    /**
     * Get the finalite value.
     * @return the finalite
     */
    public IdentifiantLibelleGwt getFinalite() {
        return finalite;
    }

    /**
     * Set the finalite value.
     * @param finalite the finalite to set
     */
    public void setFinalite(IdentifiantLibelleGwt finalite) {
        this.finalite = finalite;
    }

    /**
     * Get the identifiant value.
     * @return the identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * Set the identifiant value.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Get the identifiantProduit value.
     * @return the identifiantProduit
     */
    public Integer getIdentifiantProduit() {
        return identifiantProduit;
    }

    /**
     * Set the identifiantProduit value.
     * @param identifiantProduit the identifiantProduit to set
     */
    public void setIdentifiantProduit(Integer identifiantProduit) {
        this.identifiantProduit = identifiantProduit;
    }

    /**
     * Get the listeLignesDevisLiees value.
     * @return the listeLignesDevisLiees
     */
    public List<LigneDevisModel> getListeLignesDevisLiees() {
        if (listeLignesDevisLiees == null) {
            listeLignesDevisLiees = new ArrayList<LigneDevisModel>();
        }
        return listeLignesDevisLiees;
    }

    /**
     * Set the listeLignesDevisLiees value.
     * @param listeLignesDevisLiees the listeLignesDevisLiees to set
     */
    public void setListeLignesDevisLiees(List<LigneDevisModel> listeLignesDevisLiees) {
        this.listeLignesDevisLiees = listeLignesDevisLiees;
    }

    /**
     * Get the listeValeurCritereLigneDevis value.
     * @return the listeValeurCritereLigneDevis
     */
    public List<ValeurCritereLigneDevisModel> getListeValeurCritereLigneDevis() {
        return listeValeurCritereLigneDevis;
    }

    /**
     * Set the listeValeurCritereLigneDevis value.
     * @param listeValeurCritereLigneDevis the listeValeurCritereLigneDevis to set
     */
    public void setListeValeurCritereLigneDevis(List<ValeurCritereLigneDevisModel> listeValeurCritereLigneDevis) {
        this.listeValeurCritereLigneDevis = listeValeurCritereLigneDevis;
    }

    /**
     * Get the montantTtc value.
     * @return the montantTtc
     */
    public Float getMontantTtc() {
        return montantTtc;
    }

    /**
     * Set the montantTtc value.
     * @param montantTtc the montantTtc to set
     */
    public void setMontantTtc(Float montantTtc) {
        this.montantTtc = montantTtc;
    }

    /**
     * Get the identifiantBeneficiaire value.
     * @return the identifiantBeneficiaire
     */
    public Long getIdentifiantBeneficiaire() {
        return identifiantBeneficiaire;
    }

    /**
     * Set the identifiantBeneficiaire value.
     * @param identifiantBeneficiaire the identifiantBeneficiaire to set
     */
    public void setIdentifiantBeneficiaire(Long identifiantBeneficiaire) {
        this.identifiantBeneficiaire = identifiantBeneficiaire;
    }

    /**
     * Recupere la valeur de selectionnePourImpression.
     * @return la valeur de selectionnePourImpression
     */
    public Boolean getSelectionnePourImpression() {
        return selectionnePourImpression;
    }

    /**
     * Definit la valeur de selectionnePourImpression.
     * @param selectionnePourImpression la nouvelle valeur de selectionnePourImpression
     */
    public void setSelectionnePourImpression(Boolean selectionnePourImpression) {
        this.selectionnePourImpression = selectionnePourImpression;
    }

    /**
     * Retourne la valeur de nAAucunTarif.
     * @return la valeur de nAAucunTarif
     */
    public Boolean getNAAucunTarif() {
        return nAAucunTarif;
    }

    /**
     * Définit la valeur de nAAucunTarif.
     * @param aucunTarif la nouvelle valeur de nAAucunTarif
     */
    public void setNAAucunTarif(Boolean aucunTarif) {
        nAAucunTarif = aucunTarif;
    }

    /**
     * Retourne la valeur de genererParContrat.
     * @return la valeur de genererParContrat
     */
    public Boolean getGenererParContrat() {
        return genererParContrat;
    }

    /**
     * Définit la valeur de genererParContrat.
     * @param genererParContrat la nouvelle valeur de genererParContrat
     */
    public void setGenererParContrat(Boolean genererParContrat) {
        this.genererParContrat = genererParContrat;
    }

    /**
     * Recupere la valeur de dateCreation.
     * @return la valeur de dateCreation
     */
    public String getDateCreation() {
        return dateCreation;
    }

    /**
     * Definit la valeur de dateCreation.
     * @param dateCreation la nouvelle valeur de dateCreation
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Définit la valeur de montantRemise.
     * @return la valeur de montantRemise
     */
    public Float getMontantRemise() {
        return montantRemise;
    }

    /**
     * Définit la valeur de montantRemise.
     * @param montantRemise la nouvelle valeur de montantRemise
     */
    public void setMontantRemise(Float montantRemise) {
        this.montantRemise = montantRemise;
    }

    /**
     * Get the libelleProduit value.
     * @return the libelleProduit
     */
    public String getLibelleProduit() {
        return libelleProduit;
    }

    /**
     * Set the libelleProduit value.
     * @param libelleProduit the libelleProduit to set
     */
    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

    /**
     * Get the produitAGrillePresta value.
     * @return the produitAGrillePresta
     */
    public Boolean getProduitAGrillePresta() {
        return produitAGrillePresta;
    }

    /**
     * Set the produitAGrillePresta value.
     * @param produitAGrillePresta the produitAGrillePresta to set
     */
    public void setProduitAGrillePresta(Boolean produitAGrillePresta) {
        this.produitAGrillePresta = produitAGrillePresta;
    }

    /**
     * Get the identifiantModePaiement value.
     * @return the identifiantModePaiement
     */
    public Integer getIdentifiantModePaiement() {
        return identifiantModePaiement;
    }

    /**
     * Set the identifiantModePaiement value.
     * @param identifiantModePaiement the identifiantModePaiement to set
     */
    public void setIdentifiantModePaiement(Integer identifiantModePaiement) {
        this.identifiantModePaiement = identifiantModePaiement;
    }

    /**
     * Get the libelleModePaiement value.
     * @return the libelleModePaiement
     */
    public String getLibelleModePaiement() {
        return libelleModePaiement;
    }

    /**
     * Set the libelleModePaiement value.
     * @param libelleModePaiement the libelleModePaiement to set
     */
    public void setLibelleModePaiement(String libelleModePaiement) {
        this.libelleModePaiement = libelleModePaiement;
    }

    /**
     * Get the produitOptionnel value.
     * @return the produitOptionnel
     */
    public Boolean getProduitOptionnel() {
        return produitOptionnel;
    }

    /**
     * Set the produitOptionnel value.
     * @param produitOptionnel the produitOptionnel to set
     */
    public void setProduitOptionnel(Boolean produitOptionnel) {
        this.produitOptionnel = produitOptionnel;
    }

    /**
     * Get the ordreAffichage value.
     * @return the ordreAffichage
     */
    public Integer getOrdreAffichage() {
        return ordreAffichage;
    }

    /**
     * Set the ordreAffichage value.
     * @param ordreAffichage the ordreAffichage to set
     */
    public void setOrdreAffichage(Integer ordreAffichage) {
        this.ordreAffichage = ordreAffichage;
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
     * Get the ordreGamme value.
     * @return the ordreGamme
     */
    public Integer getOrdreGamme() {
        return ordreGamme;
    }

    /**
     * Set the ordreGamme value.
     * @param ordreGamme the ordreGamme to set
     */
    public void setOrdreGamme(Integer ordreGamme) {
        this.ordreGamme = ordreGamme;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(LigneDevisModel obj) {
        return orderByGamme(obj);
    }

    private int orderByGamme(LigneDevisModel obj) {
        if (this.getOrdreGamme() == null && obj.getOrdreGamme() == null) {
            return 0;
        }
        else if (this.getOrdreGamme() != null && obj.getOrdreGamme() == null) {
            return 1;
        }
        else if (this.getOrdreGamme() == null && obj.getOrdreGamme() != null) {
            return -1;
        }
        if (this.getOrdreGamme().compareTo(obj.getOrdreGamme()) == 0) {
            return orderByFamille(obj);
        }
        return this.getOrdreGamme().compareTo(obj.getOrdreGamme());
    }

    private int orderByFamille(LigneDevisModel obj) {
        if (this.getOrdreFamille() == null && obj.getOrdreFamille() == null) {
            return 0;
        }
        else if (this.getOrdreFamille() != null && obj.getOrdreFamille() == null) {
            return 1;
        }
        else if (this.getOrdreFamille() == null && obj.getOrdreFamille() != null) {
            return -1;
        }
        if (this.getOrdreFamille().compareTo(obj.getOrdreFamille()) == 0) {
            return orderByAffichage(obj);
        }
        return this.getOrdreFamille().compareTo(obj.getOrdreFamille());
    }

    private int orderByAffichage(LigneDevisModel obj) {
        if (this.getOrdreAffichage() == null && obj.getOrdreAffichage() == null) {
            return 0;
        }
        else if (this.getOrdreAffichage() != null && obj.getOrdreAffichage() == null) {
            return 1;
        }
        else if (this.getOrdreAffichage() == null && obj.getOrdreAffichage() != null) {
            return -1;
        }
        if (this.getOrdreAffichage().compareTo(obj.getOrdreAffichage()) == 0) {
            return orderByStatutProspect(obj);
        }
        return this.getOrdreAffichage().compareTo(obj.getOrdreAffichage());
    }

    private int orderByStatutProspect(LigneDevisModel obj) {
        if (this.getOrdreStatutProspect() == null && obj.getOrdreStatutProspect() == null) {
            return 0;
        }
        else if (this.getOrdreStatutProspect() != null && obj.getOrdreStatutProspect() == null) {
            return 1;
        }
        else if (this.getOrdreStatutProspect() == null && obj.getOrdreStatutProspect() != null) {
            return -1;
        }
        if (this.getOrdreStatutProspect().compareTo(obj.getOrdreStatutProspect()) == 0) {
            return orderByNaissanceProspect(obj);
        }
        return this.getOrdreStatutProspect().compareTo(obj.getOrdreStatutProspect());
    }

    private int orderByNaissanceProspect(LigneDevisModel obj) {
        if (this.getOrdreAgeProspect() == null && obj.getOrdreAgeProspect() == null) {
            return 0;
        }
        else if (this.getOrdreAgeProspect() != null && obj.getOrdreAgeProspect() == null) {
            return 1;
        }
        else if (this.getOrdreAgeProspect() == null && obj.getOrdreAgeProspect() != null) {
            return -1;
        }
        // ordre décroissant
        return obj.getOrdreAgeProspect().compareTo(this.getOrdreAgeProspect());
    }

    /**
     * Get the ordreStatutProspect value.
     * @return the ordreStatutProspect
     */
    public Integer getOrdreStatutProspect() {
        return ordreStatutProspect;
    }

    /**
     * Set the ordreStatutProspect value.
     * @param ordreStatutProspect the ordreStatutProspect to set
     */
    public void setOrdreStatutProspect(Integer ordreStatutProspect) {
        this.ordreStatutProspect = ordreStatutProspect;
    }

    /**
     * Get the ordreAgeProspect value.
     * @return the ordreAgeProspect
     */
    public Integer getOrdreAgeProspect() {
        return ordreAgeProspect;
    }

    /**
     * Set the ordreAgeProspect value.
     * @param ordreAgeProspect the ordreAgeProspect to set
     */
    public void setOrdreAgeProspect(Integer ordreAgeProspect) {
        this.ordreAgeProspect = ordreAgeProspect;
    }

    /**
     * Retourne la valeur de selectionnePourAdhesion.
     * @return la valeur de selectionnePourAdhesion
     */
    public Boolean getSelectionnePourAdhesion() {
        return selectionnePourAdhesion;
    }

    /**
     * Définit la valeur de selectionnePourAdhesion.
     * @param selectionnePourAdhesion la nouvelle valeur de selectionnePourAdhesion
     */
    public void setSelectionnePourAdhesion(Boolean selectionnePourAdhesion) {
        this.selectionnePourAdhesion = selectionnePourAdhesion;
    }

    /**
     * Get the identifiantDevis value.
     * @return the identifiantDevis
     */
    public Long getIdentifiantDevis() {
        return identifiantDevis;
    }

    /**
     * Set the identifiantDevis value.
     * @param identifiantDevis the identifiantDevis to set
     */
    public void setIdentifiantDevis(Long identifiantDevis) {
        this.identifiantDevis = identifiantDevis;
    }

    /**
     * Retourne la valeur de identifiantProspect.
     * @return la valeur de identifiantProspect
     */
    public Long getIdentifiantProspect() {
        return identifiantProspect;
    }

    /**
     * Définit la valeur de identifiantProspect.
     * @param identifiantProspect la nouvelle valeur de identifiantProspect
     */
    public void setIdentifiantProspect(Long identifiantProspect) {
        this.identifiantProspect = identifiantProspect;
    }

    /**
     * Get the identifiantExterne value.
     * @return the identifiantExterne
     */
    public Long getIdentifiantExterne() {
        return identifiantExterne;
    }

    /**
     * Set the identifiantExterne value.
     * @param identifiantExterne the identifiantExterne to set
     */
    public void setIdentifiantExterne(Long identifiantExterne) {
        this.identifiantExterne = identifiantExterne;
    }

    /**
     * Retourne la valeur de selectionnePourPanier.
     * @return la valeur de selectionnePourPanier
     */
    public Boolean getSelectionnePourPanier() {
        return selectionnePourPanier;
    }

    /**
     * Définit la valeur de selectionnePourPanier.
     * @param selectionnePourPanier la nouvelle valeur de selectionnePourPanier
     */
    public void setSelectionnePourPanier(Boolean selectionnePourPanier) {
        this.selectionnePourPanier = selectionnePourPanier;
    }

    /**
     * Get the identifiantFamille value.
     * @return the identifiantFamille
     */
    public Integer getIdentifiantFamille() {
        return identifiantFamille;
    }

    /**
     * Set the identifiantFamille value.
     * @param identifiantFamille the identifiantFamille to set
     */
    public void setIdentifiantFamille(Integer identifiantFamille) {
        this.identifiantFamille = identifiantFamille;
    }

    /**
     * Récupère la valeur de sourceLigneDevis.
     * @return la valeur de sourceLigneDevis
     */
    public IdentifiantLibelleGwt getSourceLigneDevis() {
        return sourceLigneDevis;
    }

    /**
     * Définit la valeur de sourceLigneDevis.
     * @param sourceLigneDevis la nouvelle valeur de sourceLigneDevis
     */
    public void setSourceLigneDevis(IdentifiantLibelleGwt sourceLigneDevis) {
        this.sourceLigneDevis = sourceLigneDevis;
    }

    /**
     * Get the prenomNomBeneficiaire value.
     * @return the prenomNomBeneficiaire
     */
    public String getPrenomNomBeneficiaire() {
        return prenomNomBeneficiaire;
    }

    /**
     * Set the prenomNomBeneficiaire value.
     * @param prenomNomBeneficiaire the prenomNomBeneficiaire to set
     */
    public void setPrenomNomBeneficiaire(String prenomNomBeneficiaire) {
        this.prenomNomBeneficiaire = prenomNomBeneficiaire;
    }

    /**
     * Get the gestionnaire value.
     * @return the gestionnaire
     */
    public IdentifiantLibelleGwt getGestionnaire() {
        return gestionnaire;
    }

    /**
     * Set the gestionnaire value.
     * @param gestionnaire the gestionnaire to set
     */
    public void setGestionnaire(IdentifiantLibelleGwt gestionnaire) {
        this.gestionnaire = gestionnaire;
    }
}

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
package com.square.price.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Dto pour le transfert d'information pour les services de recherche des tarifs.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public final class TarifCriteresDto implements Serializable {

    private static final long serialVersionUID = 9080941517940889568L;

    /** Identifiant du produit obligatoire . */
    private Integer identifiantProduit;

    /** Identifiant bareme AIA. */
    private List<String> listeIdentifiantsBareme;

    private String idBareme1;

    private String idBareme2;

    /** Contrat . */
    private String contrat;

    /** Flag pour savoir si il s'agit d'un contrat collectif. */
    private Boolean contratCollectif = Boolean.FALSE;

    /** Population . */
    private String population;

    /** Date effet . */
    private Calendar dateEffet;

    /** Liste des criteres pour la recherche . */
    private List<TarifCritereDto> listeCriteres;

    /** Code Departement pour appliquer le critere zone tarifaire au besoin . */
    private String codeDepartement;

    /** Demander au service de controler les criteres obligatoire des produits valeur par defaut true . */
    private Boolean controleCriteres;

    /** Majoration à apporter sur les tarifs . */
    private Float montantMajoration;

    /** Minoration à apporteur sur les tarifs . */
    private Float montantMinoration;

    /** Identifiant de l'application . */
    private Integer identifiantApplication;

    /** Identifiant du produit principal. */
    private Integer idProduitPrincipal;

    /** Identifiant du courtier si récupération des tarifs pour un courtier<br>
     * TODO Lors migration Square : A remplir par le client. Si non rempli, recherche hors courtier
     */
    private Long idCourtier;

    /** Identifiant. */
    private List<String> listeIdsTypePayeur;

    /**
     * Constructeur par defaut.<br>
     * Place le paramétre controleCriteres a true par defaut controle sur les criteres.
     */
    public TarifCriteresDto() {
        this.controleCriteres = true;
    }

    /**
     * Recuperer la valeur du champ controleCriteres.
     * @return the controleCriteres
     */
    public Boolean getControleCriteres() {
        return controleCriteres;
    }

    /**
     * Fixer la valeur du champ controleCriteres.
     * @param controleCriteres the controleCriteres to set
     */
    public void setControleCriteres(Boolean controleCriteres) {
        this.controleCriteres = controleCriteres;
    }

    /**
     * Recuperer la valeur du champ dateEffet.
     * @return the dateEffet
     */
    public Calendar getDateEffet() {
        return dateEffet;
    }

    /**
     * Fixer la valeur du champ dateEffet.
     * @param dateEffet the dateEffet to set
     */
    public void setDateEffet(Calendar dateEffet) {
        this.dateEffet = dateEffet;
    }

    /**
     * Recuperer la valeur du champ identifiantProduit.
     * @return the identifiantProduit
     */
    public Integer getIdentifiantProduit() {
        return identifiantProduit;
    }

    /**
     * Fixer la valeur du champ identifiantProduit.
     * @param identifiantProduit the identifiantProduit to set
     */
    public void setIdentifiantProduit(Integer identifiantProduit) {
        this.identifiantProduit = identifiantProduit;
    }

    /**
     * Recuperer la valeur du champ listeCriteres.
     * @return the listeCriteres
     */
    public List<TarifCritereDto> getListeCriteres() {
        if (listeCriteres == null) {
            listeCriteres = new ArrayList<TarifCritereDto>();
        }
        return listeCriteres;
    }

    /**
     * Fixer la valeur du champ listeCriteres.
     * @param listeCriteres the listeCriteres to set
     */
    public void setListeCriteres(List<TarifCritereDto> listeCriteres) {
        this.listeCriteres = listeCriteres;
    }

    /**
     * Get the contrat value.
     * @return the contrat
     */
    public String getContrat() {
        return contrat;
    }

    /**
     * Set the contrat value.
     * @param contrat the contrat to set
     */
    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    /**
     * Get the codeDepartement value.
     * @return the codeDepartement
     */
    public String getCodeDepartement() {
        return codeDepartement;
    }

    /**
     * Set the codeDepartement value.
     * @param codeDepartement the codeDepartement to set
     */
    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    /**
     * Get the montantMajoration value.
     * @return the montantMajoration
     */
    public Float getMontantMajoration() {
        return montantMajoration;
    }

    /**
     * Set the montantMajoration value.
     * @param montantMajoration the montantMajoration to set
     */
    public void setMontantMajoration(Float montantMajoration) {
        this.montantMajoration = montantMajoration;
    }

    /**
     * Get the montantMinoration value.
     * @return the montantMinoration
     */
    public Float getMontantMinoration() {
        return montantMinoration;
    }

    /**
     * Set the montantMinoration value.
     * @param montantMinoration the montantMinoration to set
     */
    public void setMontantMinoration(Float montantMinoration) {
        this.montantMinoration = montantMinoration;
    }

    /**
     * Get the identifiantApplication value.
     * @return the identifiantApplication
     */
    public Integer getIdentifiantApplication() {
        return identifiantApplication;
    }

    /**
     * Set the identifiantApplication value.
     * @param identifiantApplication the identifiantApplication to set
     */
    public void setIdentifiantApplication(Integer identifiantApplication) {
        this.identifiantApplication = identifiantApplication;
    }

    /**
     * Get the population value.
     * @return the population
     */
    public String getPopulation() {
        return population;
    }

    /**
     * Set the population value.
     * @param population the population to set
     */
    public void setPopulation(String population) {
        this.population = population;
    }

    /**
     * Récupère la valeur de idProduitPrincipal.
     * @return la valeur de idProduitPrincipal
     */
    public Integer getIdProduitPrincipal() {
        return idProduitPrincipal;
    }

    /**
     * Définit la valeur de idProduitPrincipal.
     * @param idProduitPrincipal la nouvelle valeur de idProduitPrincipal
     */
    public void setIdProduitPrincipal(Integer idProduitPrincipal) {
        this.idProduitPrincipal = idProduitPrincipal;
    }

    /**
     * Récupère la valeur de idCourtier.
     * @return the idCourtier
     */
    public Long getIdCourtier() {
        return idCourtier;
    }

    /**
     * Définit la valeur de idCourtier.
     * @param idCourtier the idCourtier to set
     */
    public void setIdCourtier(Long idCourtier) {
        this.idCourtier = idCourtier;
    }

    /**
     * Get the value of contratCollectif.
     * @return the contratCollectif
     */
    public Boolean getContratCollectif() {
        return contratCollectif;
    }

    /**
     * Set the value of contratCollectif.
     * @param contratCollectif the contratCollectif to set
     */
    public void setContratCollectif(Boolean contratCollectif) {
        this.contratCollectif = contratCollectif;
    }

    /**
     * Ajoute un identifiant de bareme.
     * @param identifiantBareme identifiantBareme
     */
    public void addIdentifiantBareme(String identifiantBareme) {
        if (listeIdentifiantsBareme == null) {
            listeIdentifiantsBareme = new ArrayList<String>();
        }
        listeIdentifiantsBareme.add(identifiantBareme);
    }

    /**
     * Get the value of listeIdentifiantsBareme.
     * @return the listeIdentifiantsBareme
     */
    public List<String> getListeIdentifiantsBareme() {
        return listeIdentifiantsBareme;
    }

    /**
     * Set the value of listeIdentifiantsBareme.
     * @param listeIdentifiantsBareme the listeIdentifiantsBareme to set
     */
    public void setListeIdentifiantsBareme(List<String> listeIdentifiantsBareme) {
        this.listeIdentifiantsBareme = listeIdentifiantsBareme;
    }

    /**
     * Ajoute un identifiant de type payeur.
     * @param idTypePayeur idTypePayeur
     */
    public void addIdentifiantTypePayeur(String idTypePayeur) {
        if (listeIdsTypePayeur == null) {
            listeIdsTypePayeur = new ArrayList<String>();
        }
        listeIdsTypePayeur.add(idTypePayeur);
    }

    /**
     * Get the value of listeIdsTypePayeur.
     * @return the listeIdsTypePayeur
     */
    public List<String> getListeIdsTypePayeur() {
        return listeIdsTypePayeur;
    }

    /**
     * Set the value of listeIdsTypePayeur.
     * @param listeIdsTypePayeur the listeIdsTypePayeur to set
     */
    public void setListeIdsTypePayeur(List<String> listeIdsTypePayeur) {
        this.listeIdsTypePayeur = listeIdsTypePayeur;
    }

    /**
     * Get the value of idBareme1.
     * @return the idBareme1
     */
    public String getIdBareme1() {
        return idBareme1;
    }

    /**
     * Set the value of idBareme1.
     * @param idBareme1 the idBareme1 to set
     */
    public void setIdBareme1(String idBareme1) {
        this.idBareme1 = idBareme1;
    }

    /**
     * Get the value of idBareme2.
     * @return the idBareme2
     */
    public String getIdBareme2() {
        return idBareme2;
    }

    /**
     * Set the value of idBareme2.
     * @param idBareme2 the idBareme2 to set
     */
    public void setIdBareme2(String idBareme2) {
        this.idBareme2 = idBareme2;
    }
}

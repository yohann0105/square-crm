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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.composant.tarificateur.square.client.model.age.AgeModel;

/**
 * Model representant le selecteur de produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class SelecteurProduitModel implements IsSerializable {

    /**
     * Identifiant du devis.
     */
    private Long identifiantDevis;

    /**
     * Identifiant de la ligne de devis principale.
     */
    private Long identifiantLigneDevisPrincipale;

    /**
     * Famille principale du sélecteur.
     */
    private FamillePrincipaleSelecteurModel famillePrincipale;

    /**
     * Liste des familles liées du sélecteur.
     */
    private List<FamilleLieeSelecteurModel> listeFamillesLiees;

    /** Identifiant application externe, permet de transporter une information complémentaire pour les autres systèmes (ie Selligent). */
    private Long identifiantExterne;

    /** Map des âges utilisés pour ce sélecteur. */
    private Map<String, AgeModel> mapAges;

    /** Login de l'utilisateur connecté. */
    private String loginUtilisateurConnecte;

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
     * Get the famillePrincipale value.
     * @return the famillePrincipale
     */
    public FamillePrincipaleSelecteurModel getFamillePrincipale() {
        return famillePrincipale;
    }

    /**
     * Set the famillePrincipale value.
     * @param famillePrincipale the famillePrincipale to set
     */
    public void setFamillePrincipale(FamillePrincipaleSelecteurModel famillePrincipale) {
        this.famillePrincipale = famillePrincipale;
    }

    /**
     * Recupere la valeur de identifiantLigneDevisPrincipale.
     * @return la valeur de identifiantLigneDevisPrincipale
     */
    public Long getIdentifiantLigneDevisPrincipale() {
        return identifiantLigneDevisPrincipale;
    }

    /**
     * Definit la valeur de identifiantLigneDevisPrincipale.
     * @param identifiantLigneDevisPrincipale la nouvelle valeur de identifiantLigneDevisPrincipale
     */
    public void setIdentifiantLigneDevisPrincipale(Long identifiantLigneDevisPrincipale) {
        this.identifiantLigneDevisPrincipale = identifiantLigneDevisPrincipale;
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
     * Retourne la valeur de mapAges.
     * @return la valeur de mapAges
     */
    public Map<String, AgeModel> getMapAges() {
        if (mapAges == null) {
            mapAges = new HashMap<String, AgeModel>();
        }
        return mapAges;
    }

    /**
     * Définit la valeur de mapAges.
     * @param mapAges la nouvelle valeur de mapAges
     */
    public void setMapAges(Map<String, AgeModel> mapAges) {
        this.mapAges = mapAges;
    }

    /**
     * Get the listeFamillesLiees value.
     * @return the listeFamillesLiees
     */
    public List<FamilleLieeSelecteurModel> getListeFamillesLiees() {
        return listeFamillesLiees;
    }

    /**
     * Set the listeFamillesLiees value.
     * @param listeFamillesLiees the listeFamillesLiees to set
     */
    public void setListeFamillesLiees(List<FamilleLieeSelecteurModel> listeFamillesLiees) {
        this.listeFamillesLiees = listeFamillesLiees;
    }

    /**
     * Récupère la valeur de loginUtilisateurConnecte.
     * @return la valeur de loginUtilisateurConnecte
     */
    public String getLoginUtilisateurConnecte() {
        return loginUtilisateurConnecte;
    }

    /**
     * Définit la valeur de loginUtilisateurConnecte.
     * @param loginUtilisateurConnecte la nouvelle valeur de loginUtilisateurConnecte
     */
    public void setLoginUtilisateurConnecte(String loginUtilisateurConnecte) {
        this.loginUtilisateurConnecte = loginUtilisateurConnecte;
    }

}

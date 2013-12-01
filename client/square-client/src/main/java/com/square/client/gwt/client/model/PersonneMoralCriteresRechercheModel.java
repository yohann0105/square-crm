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
package com.square.client.gwt.client.model;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Un objet contenant les critères de recherche d'une société.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonneMoralCriteresRechercheModel implements IsSerializable {
    /**
     * Raison sociale de l'entreprise.
     */
    private String raisonSociale;

    /**
     * Numero de l'entreprise.
     */
    private String numeroEntreprise;

    /**
     * Département.
     */
    private List<Long> listeDepartements;

    /**
     * Ville.
     */
    private List<Long> listeVilles;

    /**
     * Code postal.
     */
    private List<Long> listeCodesPostaux;

    /**
     * Forme juridique.
     */
    private List<Long> listeFormesJuridiques;

    /**
     * Agence.
     */
    private List<Long> listeAgences;

    /**
     * Commercial.
     */
    private List<Long> listeCommerciaux;

    /**
     * Nature.
     */
    private List<Long> listeNatures;

    /**
     * Retourne la valeur de raisonSociale.
     * @return the raisonSociale
     */
    public String getRaisonSociale() {
        return raisonSociale;
    }

    /**
     * Modifie la valeur de raisonSociale.
     * @param raisonSociale the raisonSociale to set
     */
    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    /**
     * Retourne la valeur de numeroEntreprise.
     * @return the numeroEntreprise
     */
    public String getNumeroEntreprise() {
        return numeroEntreprise;
    }

    /**
     * Modifie la valeur de numeroEntreprise.
     * @param numeroEntreprise the numeroEntreprise to set
     */
    public void setNumeroEntreprise(String numeroEntreprise) {
        this.numeroEntreprise = numeroEntreprise;
    }

    /**
     * Récupération de la liste des départements.
     * @return the listeDepartements
     */
    public List<Long> getListeDepartements() {
        return listeDepartements;
    }

    /**
     * Définition de la liste des départements.
     * @param listeDepartements the listeDepartements to set
     */
    public void setListeDepartements(List<Long> listeDepartements) {
        this.listeDepartements = listeDepartements;
    }

    /**
     * Récupération de la liste des villes.
     * @return the listeVilles
     */
    public List<Long> getListeVilles() {
        return listeVilles;
    }

    /**
     * Définition de la liste des villes.
     * @param listeVilles the listeVilles to set
     */
    public void setListeVilles(List<Long> listeVilles) {
        this.listeVilles = listeVilles;
    }

    /**
     * Récupération de la liste des codes postaux.
     * @return the listeCodesPostaux
     */
    public List<Long> getListeCodesPostaux() {
        return listeCodesPostaux;
    }

    /**
     * Récupération de la liste des codes postaux.
     * @param listeCodesPostaux the listeCodesPostaux to set
     */
    public void setListeCodesPostaux(List<Long> listeCodesPostaux) {
        this.listeCodesPostaux = listeCodesPostaux;
    }

    /**
     * Récupération de la liste des formes juridiques.
     * @return the listeFormesJuridiques
     */
    public List<Long> getListeFormesJuridiques() {
        return listeFormesJuridiques;
    }

    /**
     * Définition de la liste des formes juridiques.
     * @param listeFormesJuridiques the listeFormesJuridiques to set
     */
    public void setListeFormesJuridiques(List<Long> listeFormesJuridiques) {
        this.listeFormesJuridiques = listeFormesJuridiques;
    }

    /**
     * Retourne la valeur de listeAgences.
     * @return the listeAgences
     */
    public List<Long> getListeAgences() {
        return listeAgences;
    }

    /**
     * Modifie la valeur de listeAgences.
     * @param listeAgences the listeAgences to set
     */
    public void setListeAgences(List<Long> listeAgences) {
        this.listeAgences = listeAgences;
    }

    /**
     * Retourne la valeur de listeCommerciaux.
     * @return the listeCommerciaux
     */
    public List<Long> getListeCommerciaux() {
        return listeCommerciaux;
    }

    /**
     * Modifie la valeur de listeCommerciaux.
     * @param listeCommerciaux the listeCommerciaux to set
     */
    public void setListeCommerciaux(List<Long> listeCommerciaux) {
        this.listeCommerciaux = listeCommerciaux;
    }

    /**
     * Récupération de la liste des natures.
     * @return the listeNatures
     */
    public List<Long> getListeNatures() {
        return listeNatures;
    }

    /**
     * Définition de la liste des natures.
     * @param listeNatures the listeNatures to set
     */
    public void setListeNatures(List<Long> listeNatures) {
        this.listeNatures = listeNatures;
    }

}

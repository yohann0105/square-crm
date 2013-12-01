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
 * Objet contenant les critères de recherche sur les personnes physique.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class PersonneCriteresRechercheModel implements IsSerializable {

    /** Numéro d'identifiation du client. */
    private String numeroClient;

    /** Nom du client. */
    private String nom;

    /** Nom de jeune fille du client. */
    private String nomJeuneFille;

    /** Prénom du client. */
    private String prenom;

    /** NRO du client. */
    private String nro;

    /** Date de naissance du client. */
    private String dateNaissance;

    /** Nature. */
    private List<Long> listeNatures;

    /** Téléphone du client. */
    private String telephone;

    /** Email du client. */
    private String email;

    /** Numéro de voie du client. */
    private String numVoie;

    /** Nature de la voie. */
    private List<Long> listeNaturesVoie;

    /** Adresse du client. */
    private String adresse;

    /** Code postal de l'adresse du client. */
    private List<Long> listeCodesPostaux;

    /** Ville de l'adresse du client. */
    private List<Long> listeVilles;

    /** Commercial qui traite le client. */
    private List<Long> idCommerciaux;

    /** Agence qui traite le client. */
    private List<Long> idAgences;

    /** Liste des identifiants de personnes à ignorer dans la recherche. */
    private List<Long> idPersonnesAIgnorer;

    /** Campagne. */
    private Long idCampagne;

    /** Booléen permettant de savoir si on fait une recherche stricte ou non ("%" à la fin desc hamps). */
    private boolean isRechercheStricte = false;

    /**
     * Renvoi la valeur de numeroClient.
     * @return numeroClient
     */
    public String getNumeroClient() {
        return numeroClient;
    }

    /**
     * Modifie numeroClient.
     * @param numeroClient la nouvelle valeur de numeroClient
     */
    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
    }

    /**
     * Renvoi la valeur de nom.
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie nom.
     * @param nom la nouvelle valeur de nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Renvoi la valeur de nomJeuneFille.
     * @return nomJeuneFille
     */
    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    /**
     * Modifie nomJeuneFille.
     * @param nomJeuneFille la nouvelle valeur de nomJeuneFille
     */
    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    /**
     * Renvoi la valeur de prenom.
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie prenom.
     * @param prenom la nouvelle valeur de prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Renvoi la valeur de nro.
     * @return nro
     */
    public String getNro() {
        return nro;
    }

    /**
     * Modifie nro.
     * @param nro la nouvelle valeur de nro
     */
    public void setNro(String nro) {
        this.nro = nro;
    }

    /**
     * Renvoi la valeur de dateNaissance.
     * @return dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Modifie dateNaissance.
     * @param dateNaissance la nouvelle valeur de dateNaissance
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Renvoi la valeur de telephone.
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Modifie telephone.
     * @param telephone la nouvelle valeur de telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Renvoi la valeur de email.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifie email.
     * @param email la nouvelle valeur de email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Renvoi la valeur de numVoie.
     * @return numVoie
     */
    public String getNumVoie() {
        return numVoie;
    }

    /**
     * Modifie numVoie.
     * @param numVoie la nouvelle valeur de numVoie
     */
    public void setNumVoie(String numVoie) {
        this.numVoie = numVoie;
    }

    /**
     * Renvoi la valeur de adresse.
     * @return adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Modifie adresse.
     * @param adresse la nouvelle valeur de adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Renvoi la valeur de idCampagne.
     * @return idCampagne
     */
    public Long getIdCampagne() {
        return idCampagne;
    }

    /**
     * Modifie idCampagne.
     * @param idCampagne la nouvelle valeur de idCampagne
     */
    public void setIdCampagne(Long idCampagne) {
        this.idCampagne = idCampagne;
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

    /**
     * Récupération de la liste des natures de voie.
     * @return the listeNaturesVoie
     */
    public List<Long> getListeNaturesVoie() {
        return listeNaturesVoie;
    }

    /**
     * Définition de la liste des natures de voie.
     * @param listeNaturesVoie the listeNaturesVoie to set
     */
    public void setListeNaturesVoie(List<Long> listeNaturesVoie) {
        this.listeNaturesVoie = listeNaturesVoie;
    }

    /**
     * Récupération de la liste des codes postaux.
     * @return the listeCodesPostaux
     */
    public List<Long> getListeCodesPostaux() {
        return listeCodesPostaux;
    }

    /**
     * Définition de la liste des codes postaux.
     * @param listeCodesPostaux the listeCodesPostaux to set
     */
    public void setListeCodesPostaux(List<Long> listeCodesPostaux) {
        this.listeCodesPostaux = listeCodesPostaux;
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
     * Getter function.
     * @return the idCommerciaux
     */
    public List<Long> getIdCommerciaux() {
        return idCommerciaux;
    }

    /**
     * Getter function.
     * @return the idAgences
     */
    public List<Long> getIdAgences() {
        return idAgences;
    }

    /**
     * Setter function.
     * @param idCommerciaux the idCommerciaux to set
     */
    public void setIdCommerciaux(List<Long> idCommerciaux) {
        this.idCommerciaux = idCommerciaux;
    }

    /**
     * Setter function.
     * @param idAgences the idAgences to set
     */
    public void setIdAgences(List<Long> idAgences) {
        this.idAgences = idAgences;
    }

    /**
     * Récupère la valeur de isRechercheStricte.
     * @return la valeur de isRechercheStricte
     */
    public boolean isRechercheStricte() {
        return isRechercheStricte;
    }

    /**
     * Définit la valeur de isRechercheStricte.
     * @param isRechercheStricte la nouvelle valeur de isRechercheStricte
     */
    public void setRechercheStricte(boolean isRechercheStricte) {
        this.isRechercheStricte = isRechercheStricte;
    }

    /**
     * Getter function.
     * @return the idPersonnesAIgnorer
     */
    public List<Long> getIdPersonnesAIgnorer() {
        return idPersonnesAIgnorer;
    }

    /**
     * Setter function.
     * @param idPersonnesAIgnorer the idPersonnesAIgnorer to set
     */
    public void setIdPersonnesAIgnorer(List<Long> idPersonnesAIgnorer) {
        this.idPersonnesAIgnorer = idPersonnesAIgnorer;
    }
}

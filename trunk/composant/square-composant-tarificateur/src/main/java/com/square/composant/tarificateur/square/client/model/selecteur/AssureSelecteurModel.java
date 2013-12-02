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

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model representant un assuré pour le selecteur de produit.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class AssureSelecteurModel implements IsSerializable {

    /** Identifiant. */
    private Long identifiant;

    /** Identifiant externe. */
    private Long eidPersonne;

    /** Nom. */
    private String nom;

    /** Prenom. */
    private String prenom;

    /** Date de naissance. */
    private String dateNaissance;

    /** Age. */
    private Integer age;

    /** Date d'effet de la tarification. */
    private String dateEffetTarification;

    /** Flag pour savoir si l'assure a été selectionné. */
    private Boolean isSelection;

    /** Flag pour savoir si on peut modifier la date d'effet de l'assure avec le bouton auto date effet. */
    private Boolean allowChangeWithAutoDateEffet = Boolean.TRUE;

    /** Liste des valeurs de criteres pour l'assuré. */
    private List<ValeurCritereAssureSelecteurModel> listeValeursCriteres;

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
     * Renvoi la valeur de eidPersonne.
     * @return eidPersonne
     */
    public Long getEidPersonne() {
        return eidPersonne;
    }

    /**
     * Modifie eidPersonne.
     * @param eidPersonne la nouvelle valeur de eidPersonne
     */
    public void setEidPersonne(Long eidPersonne) {
        this.eidPersonne = eidPersonne;
    }

    /**
     * Get the nom value.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the nom value.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get the prenom value.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Set the prenom value.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Get the dateNaissance value.
     * @return the dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Set the dateNaissance value.
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Get the dateEffetTarification value.
     * @return the dateEffetTarification
     */
    public String getDateEffetTarification() {
        return dateEffetTarification;
    }

    /**
     * Set the dateEffetTarification value.
     * @param dateEffetTarification the dateEffetTarification to set
     */
    public void setDateEffetTarification(String dateEffetTarification) {
        this.dateEffetTarification = dateEffetTarification;
    }

    /**
     * Get the isSelection value.
     * @return the isSelection
     */
    public Boolean getIsSelection() {
        return isSelection;
    }

    /**
     * Set the isSelection value.
     * @param isSelection the isSelection to set
     */
    public void setIsSelection(Boolean isSelection) {
        this.isSelection = isSelection;
    }

    /**
     * Recupere la valeur de age.
     * @return la valeur de age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Definit la valeur de age.
     * @param age la nouvelle valeur de age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Get the allowChangeWithAutoDateEffet value.
     * @return the allowChangeWithAutoDateEffet
     */
    public Boolean getAllowChangeWithAutoDateEffet() {
        return allowChangeWithAutoDateEffet;
    }

    /**
     * Set the allowChangeWithAutoDateEffet value.
     * @param allowChangeWithAutoDateEffet the allowChangeWithAutoDateEffet to set
     */
    public void setAllowChangeWithAutoDateEffet(Boolean allowChangeWithAutoDateEffet) {
        this.allowChangeWithAutoDateEffet = allowChangeWithAutoDateEffet;
    }

    /**
     * Get the listeValeursCriteres value.
     * @return the listeValeursCriteres
     */
    public List<ValeurCritereAssureSelecteurModel> getListeValeursCriteres() {
        return listeValeursCriteres;
    }

    /**
     * Set the listeValeursCriteres value.
     * @param listeValeursCriteres the listeValeursCriteres to set
     */
    public void setListeValeursCriteres(List<ValeurCritereAssureSelecteurModel> listeValeursCriteres) {
        this.listeValeursCriteres = listeValeursCriteres;
    }

}

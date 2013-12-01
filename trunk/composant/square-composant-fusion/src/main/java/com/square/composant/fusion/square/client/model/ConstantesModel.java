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
package com.square.composant.fusion.square.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Les constantes.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ConstantesModel implements IsSerializable {

    /** Propriété "Nom" du modèle Personne. */
    private String proprietePersonneNom;

    /** Propriété "Prénom" du modèle Personne. */
    private String proprietePersonnePrenom;

    /** Propriété "Date de naissance" du modèle Personne. */
    private String proprietePersonneDateNaissance;

    /** Propriété "Numéro Client" du modèle Personne. */
    private String proprietePersonneNumeroClient;

    /** Propriété "Segment" du modèle Personne. */
    private String proprietePersonneSegment;

    /** Propriété "Nature" du modèle Personne. */
    private String proprietePersonneNature;

    /** Propriété "Code postal" du modèle Personne. */
    private String proprietePersonneCodePostal;

    /** Propriété "Commune" du modèle Personne. */
    private String proprietePersonneCommune;

    /** Propriété "Commercial" du modèle Personne. */
    private String proprietePersonneCommercial;

    /** Propriété "Agence" du modèle Personne. */
    private String proprietePersonneAgence;

    /** Propriété "Date de création" du modèle Personne. */
    private String proprietePersonneDateCreation;

    /** Identifiant de la nature du téléphone fixe. */
    private Long idNatureTelephoneFixe;

    /**
     * Getter.
     * @return the proprietePersonneNom
     */
    public String getProprietePersonneNom() {
        return proprietePersonneNom;
    }

    /**
     * Setter.
     * @param proprietePersonneNom the proprietePersonneNom to set
     */
    public void setProprietePersonneNom(String proprietePersonneNom) {
        this.proprietePersonneNom = proprietePersonneNom;
    }

    /**
     * Getter.
     * @return the proprietePersonnePrenom
     */
    public String getProprietePersonnePrenom() {
        return proprietePersonnePrenom;
    }

    /**
     * Setter.
     * @param proprietePersonnePrenom the proprietePersonnePrenom to set
     */
    public void setProprietePersonnePrenom(String proprietePersonnePrenom) {
        this.proprietePersonnePrenom = proprietePersonnePrenom;
    }

    /**
     * Getter.
     * @return the proprietePersonneDateNaissance
     */
    public String getProprietePersonneDateNaissance() {
        return proprietePersonneDateNaissance;
    }

    /**
     * Setter.
     * @param proprietePersonneDateNaissance the proprietePersonneDateNaissance to set
     */
    public void setProprietePersonneDateNaissance(String proprietePersonneDateNaissance) {
        this.proprietePersonneDateNaissance = proprietePersonneDateNaissance;
    }

    /**
     * Getter.
     * @return the proprietePersonneSegment
     */
    public String getProprietePersonneSegment() {
        return proprietePersonneSegment;
    }

    /**
     * Setter.
     * @param proprietePersonneSegment the proprietePersonneSegment to set
     */
    public void setProprietePersonneSegment(String proprietePersonneSegment) {
        this.proprietePersonneSegment = proprietePersonneSegment;
    }

    /**
     * Getter.
     * @return the proprietePersonneNature
     */
    public String getProprietePersonneNature() {
        return proprietePersonneNature;
    }

    /**
     * Setter.
     * @param proprietePersonneNature the proprietePersonneNature to set
     */
    public void setProprietePersonneNature(String proprietePersonneNature) {
        this.proprietePersonneNature = proprietePersonneNature;
    }

    /**
     * Getter.
     * @return the proprietePersonneCodePostal
     */
    public String getProprietePersonneCodePostal() {
        return proprietePersonneCodePostal;
    }

    /**
     * Setter.
     * @param proprietePersonneCodePostal the proprietePersonneCodePostal to set
     */
    public void setProprietePersonneCodePostal(String proprietePersonneCodePostal) {
        this.proprietePersonneCodePostal = proprietePersonneCodePostal;
    }

    /**
     * Récupère la valeur de proprietePersonneNumeroClient.
     * @return la valeur de proprietePersonneNumeroClient
     */
    public String getProprietePersonneNumeroClient() {
        return proprietePersonneNumeroClient;
    }

    /**
     * Définit la valeur de proprietePersonneNumeroClient.
     * @param proprietePersonneNumeroClient la nouvelle valeur de proprietePersonneNumeroClient
     */
    public void setProprietePersonneNumeroClient(String proprietePersonneNumeroClient) {
        this.proprietePersonneNumeroClient = proprietePersonneNumeroClient;
    }

    /**
     * Récupère la valeur de proprietePersonneCommune.
     * @return la valeur de proprietePersonneCommune
     */
    public String getProprietePersonneCommune() {
        return proprietePersonneCommune;
    }

    /**
     * Définit la valeur de proprietePersonneCommune.
     * @param proprietePersonneCommune la nouvelle valeur de proprietePersonneCommune
     */
    public void setProprietePersonneCommune(String proprietePersonneCommune) {
        this.proprietePersonneCommune = proprietePersonneCommune;
    }

    /**
     * Récupère la valeur de proprietePersonneCommercial.
     * @return la valeur de proprietePersonneCommercial
     */
    public String getProprietePersonneCommercial() {
        return proprietePersonneCommercial;
    }

    /**
     * Définit la valeur de proprietePersonneCommercial.
     * @param proprietePersonneCommercial la nouvelle valeur de proprietePersonneCommercial
     */
    public void setProprietePersonneCommercial(String proprietePersonneCommercial) {
        this.proprietePersonneCommercial = proprietePersonneCommercial;
    }

    /**
     * Récupère la valeur de proprietePersonneAgence.
     * @return la valeur de proprietePersonneAgence
     */
    public String getProprietePersonneAgence() {
        return proprietePersonneAgence;
    }

    /**
     * Définit la valeur de proprietePersonneAgence.
     * @param proprietePersonneAgence la nouvelle valeur de proprietePersonneAgence
     */
    public void setProprietePersonneAgence(String proprietePersonneAgence) {
        this.proprietePersonneAgence = proprietePersonneAgence;
    }

    /**
     * Récupère la valeur de idNatureTelephoneFixe.
     * @return la valeur de idNatureTelephoneFixe
     */
    public Long getIdNatureTelephoneFixe() {
        return idNatureTelephoneFixe;
    }

    /**
     * Définit la valeur de idNatureTelephoneFixe.
     * @param idNatureTelephoneFixe la nouvelle valeur de idNatureTelephoneFixe
     */
    public void setIdNatureTelephoneFixe(Long idNatureTelephoneFixe) {
        this.idNatureTelephoneFixe = idNatureTelephoneFixe;
    }

    /**
     * Récupère la valeur de proprietePersonneDateCreation.
     * @return la valeur de proprietePersonneDateCreation
     */
    public String getProprietePersonneDateCreation() {
        return proprietePersonneDateCreation;
    }

    /**
     * Définit la valeur de proprietePersonneDateCreation.
     * @param proprietePersonneDateCreation la nouvelle valeur de proprietePersonneDateCreation
     */
    public void setProprietePersonneDateCreation(String proprietePersonneDateCreation) {
        this.proprietePersonneDateCreation = proprietePersonneDateCreation;
    }
}

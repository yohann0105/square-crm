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
package com.square.composant.tarificateur.square.client.model.opportunite;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.DevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.FinaliteModel;

/**
 * Model Gwt modélisant une opportunité.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class OpportuniteModel implements IsSerializable {

    /** Identifiant de l'opportunité. */
    private Long id;

    /** Identifiant externe de l'opportunité (Square). */
    private Long eidOpportunite;

    /** Identifiant externe de l'agence responsable de l'opportunité. */
    private Long eidAgence;

    /** Identifiant externe de la ressource responsable de l'opportunité. */
    private Long eidResponsable;

    /** Identifiant externe du créateur de l'opportunité. */
    private Long eidCreateur;

    /** Date de création de l'opportunité. */
    private String dateCreation;

    /** Date à laquelle l'opportunité a été cloturée. */
    private String dateCloture;

    /** Date de la dernière édition du Bulletin d'Adhésion. */
    private String dateEditionBA;

    /** Date de signature de l'adhésion. */
    private String dateSignature;

    /** Liste des devis liés à l'opportunité. */
    private List<DevisModel> listeDevis;

    /** Identifiant de la finalité du devis. */
    private FinaliteModel finalite;

    /** Nature de la personne principale ciblée par l'opportunité. */
    private String naturePersonnePrincipale;

    private Long eidPersonnePrincipale;

    /**
     * Getter function.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter function.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter function.
     * @return the eidOpportunite
     */
    public Long getEidOpportunite() {
        return eidOpportunite;
    }

    /**
     * Getter function.
     * @return the eidAgence
     */
    public Long getEidAgence() {
        return eidAgence;
    }

    /**
     * Getter function.
     * @return the eidResponsable
     */
    public Long getEidResponsable() {
        return eidResponsable;
    }

    /**
     * Getter function.
     * @return the eidCreateur
     */
    public Long getEidCreateur() {
        return eidCreateur;
    }

    /**
     * Getter function.
     * @return the dateCreation
     */
    public String getDateCreation() {
        return dateCreation;
    }

    /**
     * Getter function.
     * @return the dateCloture
     */
    public String getDateCloture() {
        return dateCloture;
    }

    /**
     * Getter function.
     * @return the dateEditionBA
     */
    public String getDateEditionBA() {
        return dateEditionBA;
    }

    /**
     * Setter function.
     * @param eidOpportunite the eidOpportunite to set
     */
    public void setEidOpportunite(Long eidOpportunite) {
        this.eidOpportunite = eidOpportunite;
    }

    /**
     * Setter function.
     * @param eidAgence the eidAgence to set
     */
    public void setEidAgence(Long eidAgence) {
        this.eidAgence = eidAgence;
    }

    /**
     * Setter function.
     * @param eidResponsable the eidResponsable to set
     */
    public void setEidResponsable(Long eidResponsable) {
        this.eidResponsable = eidResponsable;
    }

    /**
     * Setter function.
     * @param eidCreateur the eidCreateur to set
     */
    public void setEidCreateur(Long eidCreateur) {
        this.eidCreateur = eidCreateur;
    }

    /**
     * Setter function.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Setter function.
     * @param dateCloture the dateCloture to set
     */
    public void setDateCloture(String dateCloture) {
        this.dateCloture = dateCloture;
    }

    /**
     * Setter function.
     * @param dateEditionBA the dateEditionBA to set
     */
    public void setDateEditionBA(String dateEditionBA) {
        this.dateEditionBA = dateEditionBA;
    }

    /**
     * Get the finalite value.
     * @return the finalite
     */
    public FinaliteModel getFinalite() {
        return finalite;
    }

    /**
     * Set the finalite value.
     * @param finalite the finalite to set
     */
    public void setFinalite(FinaliteModel finalite) {
        this.finalite = finalite;
    }

    /**
     * Get the listeDevis value.
     * @return the listeDevis
     */
    public List<DevisModel> getListeDevis() {
        return listeDevis;
    }

    /**
     * Set the listeDevis value.
     * @param listeDevis the listeDevis to set
     */
    public void setListeDevis(List<DevisModel> listeDevis) {
        this.listeDevis = listeDevis;
    }

    /**
     * Getter function.
     * @return the dateSignature
     */
    public String getDateSignature() {
        return dateSignature;
    }

    /**
     * Setter function.
     * @param dateSignature the dateSignature to set
     */
    public void setDateSignature(String dateSignature) {
        this.dateSignature = dateSignature;
    }

    /**
     * Getter function.
     * @return the naturePersonnePrincipale
     */
    public String getNaturePersonnePrincipale() {
        return naturePersonnePrincipale;
    }

    /**
     * Setter function.
     * @param naturePersonnePrincipale the naturePersonnePrincipale to set
     */
    public void setNaturePersonnePrincipale(String naturePersonnePrincipale) {
        this.naturePersonnePrincipale = naturePersonnePrincipale;
    }

    /**
     * Getter function.
     * @return the eidPersonnePrincipale
     */
    public Long getEidPersonnePrincipale() {
        return eidPersonnePrincipale;
    }

    /**
     * Setter function.
     * @param eidPersonnePrincipale the eidPersonnePrincipale to set
     */
    public void setEidPersonnePrincipale(Long eidPersonnePrincipale) {
        this.eidPersonnePrincipale = eidPersonnePrincipale;
    }

}

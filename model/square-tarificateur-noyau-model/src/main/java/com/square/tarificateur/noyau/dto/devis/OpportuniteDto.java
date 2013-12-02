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
package com.square.tarificateur.noyau.dto.devis;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;


/**
 * DTO modélisant une opportunité.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class OpportuniteDto implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = -154324202080983460L;

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
    private Calendar dateCreation;

    /** Date à laquelle l'opportunité a été cloturée. */
    private Calendar dateCloture;

    /** Date de la dernière édition du Bulletin d'Adhésion. */
    private Calendar dateEditionBA;

    /** Date de signature de l'adhésion. */
    private Calendar dateSignature;

    /** Liste des devis liés à l'opportunité. */
    private List<DevisDto> listeDevis;

    /** Identifiant de la finalité du devis. */
    private FinaliteDto finalite;

    /** Nature de la personne principale ciblée par l'opportunité. */
    private String naturePersonnePrincipale;

    /** Identifiant externe (Square) de la personne principale. */
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
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Getter function.
     * @return the dateCloture
     */
    public Calendar getDateCloture() {
        return dateCloture;
    }

    /**
     * Getter function.
     * @return the dateEditionBA
     */
    public Calendar getDateEditionBA() {
        return dateEditionBA;
    }

    /**
     * Getter function.
     * @return the listeDevis
     */
    public List<DevisDto> getListeDevis() {
        return listeDevis;
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
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Setter function.
     * @param dateCloture the dateCloture to set
     */
    public void setDateCloture(Calendar dateCloture) {
        this.dateCloture = dateCloture;
    }

    /**
     * Setter function.
     * @param dateEditionBA the dateEditionBA to set
     */
    public void setDateEditionBA(Calendar dateEditionBA) {
        this.dateEditionBA = dateEditionBA;
    }

    /**
     * Setter function.
     * @param listeDevis the listeDevis to set
     */
    public void setListeDevis(List<DevisDto> listeDevis) {
        this.listeDevis = listeDevis;
    }

    /**
     * Récupère la valeur de finalite.
     * @return la valeur de finalite
     */
    public FinaliteDto getFinalite() {
        return finalite;
    }

    /**
     * Définit la valeur de finalite.
     * @param finalite la nouvelle valeur de finalite
     */
    public void setFinalite(FinaliteDto finalite) {
        this.finalite = finalite;
    }

    /**
     * Getter function.
     * @return the dateSignature
     */
    public Calendar getDateSignature() {
        return dateSignature;
    }

    /**
     * Setter function.
     * @param dateSignature the dateSignature to set
     */
    public void setDateSignature(Calendar dateSignature) {
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

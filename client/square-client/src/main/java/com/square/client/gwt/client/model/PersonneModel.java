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

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

/**
 * Un objet contenant une personne simple.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class PersonneModel extends PersonneBaseModel {

    /** SVUID. */
    private static final long serialVersionUID = -2558574879401662364L;

    /** Identifiant de la personne. */
    private Long identifiant;

    /** Numéro de client. */
    private String numClient;

    /** Info santé. */
    private InfoSanteModel infoSante;

    /** Civilité de la personne. */
    private IdentifiantLibelleGwt civilite;

    /** Prénom de la personne. */
    private String prenom;

    /** Nom de la personne. */
    private String nom;

    /** Nom de jeune fille. */
    private String nomJeuneFille;

    /** Date de naissance de la personne. */
    private String dateNaissance;

    /** Date de décès de la personne. */
    private String dateDeces;

    /** Date de création de la personne. */
    private String dateCreation;

    /** L'âge de la personne. */
    private int age;

    /** Nature de la personne. */
    private IdentifiantLibelleGwt naturePersonne;

    /** Indique si la personne est décédé. */
    private boolean decede;

    /** Le segment de la personne. */
    private IdentifiantLibelleGwt segment;

    /** Le réseau de vente. */
    private IdentifiantLibelleGwt reseauVente;

    /** Csp de la personne. */
    private IdentifiantLibelleGwt csp;

    /** Agence a laquelle la personne est rattachée. */
    private IdentifiantLibelleGwt agence;

    /** Commercial de la personne. */
    private DimensionRessourceModel commercial;

    /** Créateur de la personne. */
    private DimensionRessourceModel createur;

    /** Nombre d'enfant de la personne. */
    private int nbEnfants;

    /** Situation familiale. */
    private IdentifiantLibelleGwt sitFam;

    /** Indicatif du pays. */
    private Long indicatifTel;

    /** Profession de la personne. */
    private IdentifiantLibelleBooleanModel profession;

    /** La ressource responsable de la personne. */
    private IdentifiantLibelleGwt ressource;

    /** Statut de la personne. */
    private IdentifiantLibelleGwt statut;

    /** Flag indiquant si la nature de la personne a changé. */
    private Boolean hasNaturePersonneChanged;

    /** Ancienne nature de la personne. */
    private String ancienneNaturePersonne;

    /** Nouvelle nature de la personne. */
    private String nouvelleNaturePersonne;

    /**
     * Retourne la valeur de numClient.
     * @return the numClient
     */
    public String getNumClient() {
        return numClient;
    }

    /**
     * Modifie la valeur de numClient.
     * @param numClient the numClient to set
     */
    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    /**
     * Retourne la valeur de prenom.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie la valeur de prenom.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne la valeur de nom.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie la valeur de nom.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne la valeur de nomJeuneFille.
     * @return the nomJeuneFille
     */
    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    /**
     * Modifie la valeur de nomJeuneFille.
     * @param nomJeuneFille the nomJeuneFille to set
     */
    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    /**
     * Retourne la valeur de dateNaissance.
     * @return the dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Modifie la valeur de dateNaissance.
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Retourne la valeur de decede.
     * @return the decede
     */
    public boolean isDecede() {
        return decede;
    }

    /**
     * Modifie la valeur de decede.
     * @param decede the decede to set
     */
    public void setDecede(boolean decede) {
        this.decede = decede;
    }

    /**
     * Retourne la valeur de nbEnfant.
     * @return the nbEnfant
     */
    public int getNbEnfants() {
        return nbEnfants;
    }

    /**
     * Modifie la valeur de nbEnfant.
     * @param nbEnfants the nbEnfants to set
     */
    public void setNbEnfants(int nbEnfants) {
        this.nbEnfants = nbEnfants;
    }

    /**
     * Retourne la valeur de indicatifTel.
     * @return the indicatifTel
     */
    public Long getIndicatifTel() {
        return indicatifTel;
    }

    /**
     * Modifie la valeur de indicatifTel.
     * @param indicatifTel the indicatifTel to set
     */
    public void setIndicatifTel(Long indicatifTel) {
        this.indicatifTel = indicatifTel;
    }

    /**
     * Renvoi la valeur de naturePersonne.
     * @return naturePersonne
     */
    public IdentifiantLibelleGwt getNaturePersonne() {
        return naturePersonne;
    }

    /**
     * Modifie naturePersonne.
     * @param naturePersonne la nouvelle valeur de naturePersonne
     */
    public void setNaturePersonne(IdentifiantLibelleGwt naturePersonne) {
        this.naturePersonne = naturePersonne;
    }

    /**
     * Renvoi la valeur de segment.
     * @return segment
     */
    public IdentifiantLibelleGwt getSegment() {
        return segment;
    }

    /**
     * Modifie segment.
     * @param segment la nouvelle valeur de segment
     */
    public void setSegment(IdentifiantLibelleGwt segment) {
        this.segment = segment;
    }

    /**
     * Renvoi la valeur de reseauVente.
     * @return reseauVente
     */
    public IdentifiantLibelleGwt getReseauVente() {
        return reseauVente;
    }

    /**
     * Modifie reseauVente.
     * @param reseauVente la nouvelle valeur de reseauVente
     */
    public void setReseauVente(IdentifiantLibelleGwt reseauVente) {
        this.reseauVente = reseauVente;
    }

    /**
     * Renvoi la valeur de csp.
     * @return csp
     */
    public IdentifiantLibelleGwt getCsp() {
        return csp;
    }

    /**
     * Modifie csp.
     * @param csp la nouvelle valeur de csp
     */
    public void setCsp(IdentifiantLibelleGwt csp) {
        this.csp = csp;
    }

    /**
     * Renvoi la valeur de agence.
     * @return agence
     */
    public IdentifiantLibelleGwt getAgence() {
        return agence;
    }

    /**
     * Modifie agence.
     * @param agence la nouvelle valeur de agence
     */
    public void setAgence(IdentifiantLibelleGwt agence) {
        this.agence = agence;
    }

    /**
     * Renvoi la valeur de commercial.
     * @return commercial
     */
    public DimensionRessourceModel getCommercial() {
        return commercial;
    }

    /**
     * Modifie commercial.
     * @param commercial la nouvelle valeur de commercial
     */
    public void setCommercial(DimensionRessourceModel commercial) {
        this.commercial = commercial;
    }

    /**
     * Renvoi la valeur de sitFam.
     * @return sitFam
     */
    public IdentifiantLibelleGwt getSitFam() {
        return sitFam;
    }

    /**
     * Modifie sitFam.
     * @param sitFam la nouvelle valeur de sitFam
     */
    public void setSitFam(IdentifiantLibelleGwt sitFam) {
        this.sitFam = sitFam;
    }

    /**
     * Renvoi la valeur de profession.
     * @return profession
     */
    public IdentifiantLibelleBooleanModel getProfession() {
        return profession;
    }

    /**
     * Modifie profession.
     * @param profession la nouvelle valeur de profession
     */
    public void setProfession(IdentifiantLibelleBooleanModel profession) {
        this.profession = profession;
    }

    /**
     * Renvoi la valeur de ressource.
     * @return ressource
     */
    public IdentifiantLibelleGwt getRessource() {
        return ressource;
    }

    /**
     * Modifie ressource.
     * @param ressource la nouvelle valeur de ressource
     */
    public void setRessource(IdentifiantLibelleGwt ressource) {
        this.ressource = ressource;
    }

    /**
     * Renvoi la valeur de statut.
     * @return statut
     */
    public IdentifiantLibelleGwt getStatut() {
        return statut;
    }

    /**
     * Modifie statut.
     * @param statut la nouvelle valeur de statut
     */
    public void setStatut(IdentifiantLibelleGwt statut) {
        this.statut = statut;
    }

    /**
     * Renvoi la valeur de civilite.
     * @return civilite
     */
    public IdentifiantLibelleGwt getCivilite() {
        return civilite;
    }

    /**
     * Modifie civilite.
     * @param civilite la nouvelle valeur de civilite
     */
    public void setCivilite(IdentifiantLibelleGwt civilite) {
        this.civilite = civilite;
    }

    /**
     * Fixe la valeur de l'identifiant.
     * @return the identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * retourne la valeur de l'identifiant.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Getter function.
     * @return the createur
     */
    public DimensionRessourceModel getCreateur() {
        return createur;
    }

    /**
     * Setter function.
     * @param createur the createur to set
     */
    public void setCreateur(DimensionRessourceModel createur) {
        this.createur = createur;
    }

    /**
     * Getter function.
     * @return the dateCreation
     */
    public String getDateCreation() {
        return dateCreation;
    }

    /**
     * Setter function.
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Getter function.
     * @return the dateDeces
     */
    public String getDateDeces() {
        return dateDeces;
    }

    /**
     * Setter function.
     * @param dateDeces the dateDeces to set
     */
    public void setDateDeces(String dateDeces) {
        this.dateDeces = dateDeces;
    }

    /**
     * Getter function.
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter function.
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get the infoSante value.
     * @return the infoSante
     */
    public InfoSanteModel getInfoSante() {
        return infoSante;
    }

    /**
     * Set the infoSante value.
     * @param infoSante the infoSante to set
     */
    public void setInfoSante(InfoSanteModel infoSante) {
        this.infoSante = infoSante;
    }

    /**
     * Récupère la valeur de hasNaturePersonneChanged.
     * @return la valeur de hasNaturePersonneChanged
     */
    public Boolean getHasNaturePersonneChanged() {
        return hasNaturePersonneChanged;
    }

    /**
     * Définit la valeur de hasNaturePersonneChanged.
     * @param hasNaturePersonneChanged la nouvelle valeur de hasNaturePersonneChanged
     */
    public void setHasNaturePersonneChanged(Boolean hasNaturePersonneChanged) {
        this.hasNaturePersonneChanged = hasNaturePersonneChanged;
    }

    /**
     * Récupère la valeur de ancienneNaturePersonne.
     * @return la valeur de ancienneNaturePersonne
     */
    public String getAncienneNaturePersonne() {
        return ancienneNaturePersonne;
    }

    /**
     * Définit la valeur de ancienneNaturePersonne.
     * @param ancienneNaturePersonne la nouvelle valeur de ancienneNaturePersonne
     */
    public void setAncienneNaturePersonne(String ancienneNaturePersonne) {
        this.ancienneNaturePersonne = ancienneNaturePersonne;
    }

    /**
     * Récupère la valeur de nouvelleNaturePersonne.
     * @return la valeur de nouvelleNaturePersonne
     */
    public String getNouvelleNaturePersonne() {
        return nouvelleNaturePersonne;
    }

    /**
     * Définit la valeur de nouvelleNaturePersonne.
     * @param nouvelleNaturePersonne la nouvelle valeur de nouvelleNaturePersonne
     */
    public void setNouvelleNaturePersonne(String nouvelleNaturePersonne) {
        this.nouvelleNaturePersonne = nouvelleNaturePersonne;
    }
}

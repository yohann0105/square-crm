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
package com.square.core.model.dto;

import java.io.Serializable;
import java.util.Calendar;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

/**
 * Dto pour les résultats de la recherche.
 * @author cblanchard - SCUB
 */
public class PersonneSimpleDto implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 3666059723964723980L;

    /**
     * Identifiant du client.
     */
    private Long id;

    /**
     * Numéro du client.
     */
    private String numeroClient;

    /**
     * Civilité de la personne.
     */
    private IdentifiantLibelleDto civilite;

    /**
     * Nom du client.
     */
    private String nom;

    /**
     * Nom de jeune fille.
     */
    private String nomJeuneFille;

    /**
     * Prénom du client.
     */
    private String prenom;

    /**
     * Situation familiale.
     */
    private String situationFamiliale;

    /**
     * Date de naissance.
     */
    private Calendar dateNaissance;

    /**
     * Age de la personne.
     */
    private String age;

    /**
     * Code postal - Commune.
     */
    private CodePostalCommuneDto codePostalCommune;

    /**
     * Nature.
     */
    private IdentifiantLibelleDto nature;

    /**
     * Segment.
     */
    private IdentifiantLibelleDto segment;

    /**
     * La ressource: responsable de la personne.
     */
    private DimensionRessourceDto commercial;

    /**
     * L'agence.
     */
    private IdentifiantLibelleDto agence;

    /**
     * Indique si la personne est un doublon.
     */
    private boolean doublon;

    /**
     * Le numéro RO.
     */
    private String nro;

    /**
     * Créateur.
     */
    private DimensionRessourceDto createur;

    /** Date de création. */
    private Calendar dateCreation;

    /**
     * Renvoi la valeur de id.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * Renvoi la valeur de nature.
     * @return nature
     */
    public IdentifiantLibelleDto getNature() {
        return nature;
    }

    /**
     * Modifie nature.
     * @param nature la nouvelle valeur de nature
     */
    public void setNature(IdentifiantLibelleDto nature) {
        this.nature = nature;
    }

    /**
     * Renvoi la valeur de segment.
     * @return segment
     */
    public IdentifiantLibelleDto getSegment() {
        return segment;
    }

    /**
     * Modifie segment.
     * @param segment la nouvelle valeur de segment
     */
    public void setSegment(IdentifiantLibelleDto segment) {
        this.segment = segment;
    }

    /**
     * Renvoi la valeur de doublon.
     * @return doublon
     */
    public boolean isDoublon() {
        return doublon;
    }

    /**
     * Modifie doublon.
     * @param doublon la nouvelle valeur de doublon
     */
    public void setDoublon(boolean doublon) {
        this.doublon = doublon;
    }

    /**
     * Renvoi la valeur de dateNaissance.
     * @return dateNaissance
     */
    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Modifie dateNaissance.
     * @param dateNaissance la nouvelle valeur de dateNaissance
     */
    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Renvoi la valeur de civilite.
     * @return civilite
     */
    public IdentifiantLibelleDto getCivilite() {
        return civilite;
    }

    /**
     * Modifie civilite.
     * @param civilite la nouvelle valeur de civilite
     */
    public void setCivilite(IdentifiantLibelleDto civilite) {
        this.civilite = civilite;
    }

    /**
     * Renvoi la valeur de situationFamiliale.
     * @return situationFamiliale
     */
    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    /**
     * Modifie situationFamiliale.
     * @param situationFamiliale la nouvelle valeur de situationFamiliale
     */
    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    /**
     * Retourne la valeur de commerciale.
     * @return the commercial
     */
    public DimensionRessourceDto getCommercial() {
        return commercial;
    }

    /**
     * Modifie la valeur de commerciale.
     * @param commercial commercial to set
     */
    public void setCommercial(DimensionRessourceDto commercial) {
        this.commercial = commercial;
    }

    /**
     * Retourne la valeur de agence.
     * @return the agence
     */
    public IdentifiantLibelleDto getAgence() {
        return agence;
    }

    /**
     * Modifie la valeur de agence.
     * @param agence the agence to set
     */
    public void setAgence(IdentifiantLibelleDto agence) {
        this.agence = agence;
    }

    /**
     * Retourne la valeur de age.
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * Modifie la valeur de age.
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Récupération de nro.
     * @return the nro
     */
    public String getNro() {
        return nro;
    }

    /**
     * Définition de nro.
     * @param nro the nro to set
     */
    public void setNro(String nro) {
        this.nro = nro;
    }

    /**
     * Récupère la valeur de createur.
     * @return la valeur de createur
     */
    public DimensionRessourceDto getCreateur() {
        return createur;
    }

    /**
     * Définit la valeur de createur.
     * @param createur la nouvelle valeur de createur
     */
    public void setCreateur(DimensionRessourceDto createur) {
        this.createur = createur;
    }

    /**
     * Récupère la valeur de nomJeuneFille.
     * @return la valeur de nomJeuneFille
     */
    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    /**
     * Définit la valeur de nomJeuneFille.
     * @param nomJeuneFille la nouvelle valeur de nomJeuneFille
     */
    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    /**
     * Récupère la valeur de dateCreation.
     * @return la valeur de dateCreation
     */
    public Calendar getDateCreation() {
        return dateCreation;
    }

    /**
     * Définit la valeur de dateCreation.
     * @param dateCreation la nouvelle valeur de dateCreation
     */
    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Récupère la valeur codePostalCommune.
     * @return the codePostalCommune
     */
    public CodePostalCommuneDto getCodePostalCommune() {
        return codePostalCommune;
    }

    /**
     * Définit la valeur de codePostalCommune.
     * @param codePostalCommune the codePostalCommune to set
     */
    public void setCodePostalCommune(CodePostalCommuneDto codePostalCommune) {
        this.codePostalCommune = codePostalCommune;
    }

}

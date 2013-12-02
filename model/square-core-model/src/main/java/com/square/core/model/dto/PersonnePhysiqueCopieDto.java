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
/**
 * 
 */
package com.square.core.model.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Regrouppe les informations pour la copie d'une personne.
 * @author Goumard Stephane (scub) - SCUB
 */
public class PersonnePhysiqueCopieDto implements Serializable {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 3594606209708216832L;

    /**
     * La civilite de la personne.
     */
    private Long civilite;

    /**
     * Nom de la personne physique.
     */
    private String nom;

    /**
     * Prénom de la personne physique.
     */
    private String prenom;

    /**
     * Date de naissance de la personne physique.
     */
    private Calendar dateNaissance;

    /**
     * identifiant personne source.
     */
    private Long idPersonneSource;

    /** Identifiant de la nature de la personne. */
    private Long idNaturePersonne;

    /** Téléphone pour la création de relation de PP sur une PM. */
    private TelephoneDto telephone;

    /** Flag qui précise si il faut dupliquer les coordonnées de la personne source ou lier la personne copiée aux coordonnées de la personne source. */
    private boolean dupliquerCoordonnees;

    /** Infos santé de la personne. */
    private InfoSanteDto infosSante;

    /**
     * Constructeur sans argument.
     */
    public PersonnePhysiqueCopieDto() {
    }

    /**
     * Constructeur.
     * @param civilite la civilité de la nouvelle personne
     * @param nom le nom de la nouvelle personne
     * @param prenom le prénom de la nouvelle personne
     * @param dateNaissance la date de naissance de la nouvelle personne
     * @param idPersonneSource l'identifiant de la personne source
     */
    public PersonnePhysiqueCopieDto(Long civilite, String nom, String prenom, Calendar dateNaissance, Long idPersonneSource) {
        super();
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.idPersonneSource = idPersonneSource;
    }

    /**
     * Retourne la valeur de civilite.
     * @return the civilite
     */
    public Long getCivilite() {
        return civilite;
    }

    /**
     * Modifie la valeur de civilite.
     * @param civilite the civilite to set
     */
    public void setCivilite(Long civilite) {
        this.civilite = civilite;
    }

    /**
     * Renvoie le nom.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Renvoie le prénom.
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie le prénom.
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Renvoie la date de naissance.
     * @return the dateNaissance
     */
    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Modifie la date de naissance.
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Renvoie l'identifiant de la personne source.
     * @return the idPersonneSource
     */
    public Long getIdPersonneSource() {
        return idPersonneSource;
    }

    /**
     * Modifie l'identifiant de la personne source.
     * @param idPersonneSource the idPersonneSource to set
     */
    public void setIdPersonneSource(Long idPersonneSource) {
        this.idPersonneSource = idPersonneSource;
    }

    /**
     * Récupère la valeur de idNaturePersonne.
     * @return la valeur de idNaturePersonne
     */
    public Long getIdNaturePersonne() {
        return idNaturePersonne;
    }

    /**
     * Définit la valeur de idNaturePersonne.
     * @param idNaturePersonne la nouvelle valeur de idNaturePersonne
     */
    public void setIdNaturePersonne(Long idNaturePersonne) {
        this.idNaturePersonne = idNaturePersonne;
    }

    /**
     * Récupère la valeur de telephone.
     * @return la valeur de telephone
     */
    public TelephoneDto getTelephone() {
        return telephone;
    }

    /**
     * Définit la valeur de telephone.
     * @param telephone la nouvelle valeur de telephone
     */
    public void setTelephone(TelephoneDto telephone) {
        this.telephone = telephone;
    }

    /**
     * Getter function.
     * @return the dupliquerCoordonnees
     */
    public boolean isDupliquerCoordonnees() {
        return dupliquerCoordonnees;
    }

    /**
     * Setter function.
     * @param dupliquerCoordonnees the dupliquerCoordonnees to set
     */
    public void setDupliquerCoordonnees(boolean dupliquerCoordonnees) {
        this.dupliquerCoordonnees = dupliquerCoordonnees;
    }

    /**
     * Récupère la valeur de infosSante.
     * @return la valeur de infosSante
     */
    public InfoSanteDto getInfosSante() {
        return infosSante;
    }

    /**
     * Définit la valeur de infosSante.
     * @param infosSante la nouvelle valeur de infosSante
     */
    public void setInfosSante(InfoSanteDto infosSante) {
        this.infosSante = infosSante;
    }
}

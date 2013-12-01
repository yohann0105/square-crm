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
package com.square.print.core.dto;

import java.io.Serializable;

/**
 * DTO représentant un prospect pour les devis PDF.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net)
 */
public class ProspectDevisDto implements Serializable {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -5628590778809968733L;

    /** Identifiant. */
    private Long id;

    /** Genre. */
    private String genre;

    /** Nom. */
    private String nom;

    /** Prénom. */
    private String prenom;

    /** Adresse. */
    private AdresseDto adresse;

    /** Constructeur. */
    public ProspectDevisDto() { }

    /**
     * Constructeur.
     * @param id l'identifiant du prospect
     * @param genre le genre du prospect
     * @param nom le nom du prospect
     * @param prenom le prenom du prospect
     * @param adresse l'adresse du prospect
     */
    public ProspectDevisDto(Long id, String genre, String nom, String prenom, AdresseDto adresse) {
        this.id = id;
        this.genre = genre;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    /**
     * Définit la valeur de id.
     * @return la valeur de id
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de id.
     * @param id la nouvelle valeur de id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Définit la valeur de genre.
     * @return la valeur de genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Définit la valeur de genre.
     * @param genre la nouvelle valeur de genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Définit la valeur de nom.
     * @return la valeur de nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit la valeur de nom.
     * @param nom la nouvelle valeur de nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit la valeur de prenom.
     * @return la valeur de prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit la valeur de prenom.
     * @param prenom la nouvelle valeur de prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Get the adresse value.
     * @return the adresse
     */
    public AdresseDto getAdresse() {
        return adresse;
    }

    /**
     * Set the adresse value.
     * @param adresse the adresse to set
     */
    public void setAdresse(AdresseDto adresse) {
        this.adresse = adresse;
    }

}

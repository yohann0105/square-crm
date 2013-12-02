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
package com.square.document.core.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Transporte un document.
 * @author jgoncalves - SCUB - SCUB
 */
public class DocumentDto implements Serializable {
    private static final long serialVersionUID = 5247308446701457126L;
    private String identifiant;
    private String nom;
    private List<CodeLibelleDto> types;
    private List<String> tags;
    private String url;
    private Calendar dateNumerisation;
    private Calendar dateReception;
    private String typeMime;
    private String numeroClient;
    private byte[] contenu;
    private String sens;

    /**
     * Récupération de identifiant.
     * @return the identifiant
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Définition de identifiant.
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Récupération de nom.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définition de nom.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupération de url.
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Définition de url.
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Récupération de dateNumerisation.
     * @return the dateNumerisation
     */
    public Calendar getDateNumerisation() {
        return dateNumerisation;
    }

    /**
     * Définition de dateNumerisation.
     * @param dateNumerisation the dateNumerisation to set
     */
    public void setDateNumerisation(Calendar dateNumerisation) {
        this.dateNumerisation = dateNumerisation;
    }

    /**
     * Récupération de dateReception.
     * @return the dateReception
     */
    public Calendar getDateReception() {
        return dateReception;
    }

    /**
     * Définition de dateReception.
     * @param dateReception the dateReception to set
     */
    public void setDateReception(Calendar dateReception) {
        this.dateReception = dateReception;
    }

    /**
     * Récupération de typeMime.
     * @return the typeMime
     */
    public String getTypeMime() {
        return typeMime;
    }

    /**
     * Définition de typeMime.
     * @param typeMime the typeMime to set
     */
    public void setTypeMime(String typeMime) {
        this.typeMime = typeMime;
    }

    /**
     * Récupération de types.
     * @return the types
     */
    public List<CodeLibelleDto> getTypes() {
        return types;
    }

    /**
     * Définition de types.
     * @param types the types to set
     */
    public void setTypes(List<CodeLibelleDto> types) {
        this.types = types;
    }

    /**
     * Récupération de contenu.
     * @return the contenu
     */
    public byte[] getContenu() {
        return contenu;
    }

    /**
     * Définition de contenu.
     * @param contenu the contenu to set
     */
    public void setContenu(byte[] contenu) {
        this.contenu = contenu;
    }

    /**
     * Récupération de numeroClient.
     * @return the numeroClient
     */
    public String getNumeroClient() {
        return numeroClient;
    }

    /**
     * Définition de numeroClient.
     * @param numeroClient the numeroClient to set
     */
    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
    }

    /**
     * Récupération de sens.
     * @return the sens
     */
    public String getSens() {
        return sens;
    }

    /**
     * Définition de sens.
     * @param sens the sens to set
     */
    public void setSens(String sens) {
        this.sens = sens;
    }

    /**
     * Get the tags value.
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Set the tags value.
     * @param tags the tags to set
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}

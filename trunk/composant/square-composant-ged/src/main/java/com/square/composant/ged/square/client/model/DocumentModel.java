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
package com.square.composant.ged.square.client.model;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Modele document.
 * @author jgoncalves - SCUB
 */
public class DocumentModel implements IsSerializable {
    private String identifiant;
    private String nom;
    private List<CodeLibelleModel> types;
    private List<String> tags;
    private String url;
    private Date dateNumerisation;
    private Date dateReception;
    private String typeMime;
    private DossierDocumentModel parent;
    private String numeroClient;
    private Long idPersonne;
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
     * Récupération de types.
     * @return the types
     */
    public List<CodeLibelleModel> getTypes() {
        return types;
    }
    /**
     * Définition de types.
     * @param types the types to set
     */
    public void setTypes(List<CodeLibelleModel> types) {
        this.types = types;
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
    public Date getDateNumerisation() {
        return dateNumerisation;
    }
    /**
     * Définition de dateNumerisation.
     * @param dateNumerisation the dateNumerisation to set
     */
    public void setDateNumerisation(Date dateNumerisation) {
        this.dateNumerisation = dateNumerisation;
    }
    /**
     * Récupération de dateReception.
     * @return the dateReception
     */
    public Date getDateReception() {
        return dateReception;
    }
    /**
     * Définition de dateReception.
     * @param dateReception the dateReception to set
     */
    public void setDateReception(Date dateReception) {
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
     * Récupération de parent.
     * @return the parent
     */
    public DossierDocumentModel getParent() {
        return parent;
    }
    /**
     * Définition de parent.
     * @param parent the parent to set
     */
    public void setParent(DossierDocumentModel parent) {
        this.parent = parent;
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
     * Récupération de idPersonne.
     * @return the idPersonne
     */
    public Long getIdPersonne() {
        return idPersonne;
    }
    /**
     * Définition de idPersonne.
     * @param idPersonne the idPersonne to set
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
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

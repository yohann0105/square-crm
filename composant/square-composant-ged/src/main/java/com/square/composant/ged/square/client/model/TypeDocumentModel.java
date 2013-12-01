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
package com.square.composant.ged.square.client.model;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Type de document.
 * @author jgoncalves - SCUB
 */
public class TypeDocumentModel implements IsSerializable {
    /** Le code. */
    private String code;

    /** Le nom du type de documents. */
    private String nom;

    /** La description. */
    private String description;

    /** La valeur a été sélectionnée ou pas. */
    private Boolean selectionne;

    private int nombreDocs;

    /** Les sous types. */
    private List<TypeDocumentModel> listeTypesDocuments;

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
     * Récupération de selectionne.
     * @return the selectionne
     */
    public Boolean getSelectionne() {
        return selectionne;
    }

    /**
     * Définition de selectionne.
     * @param selectionne the selectionne to set
     */
    public void setSelectionne(Boolean selectionne) {
        this.selectionne = selectionne;
    }

    /**
     * Récupération de listeTypesDocuments.
     * @return the listeTypesDocuments
     */
    public List<TypeDocumentModel> getListeTypesDocuments() {
        return listeTypesDocuments;
    }

    /**
     * Définition de listeTypesDocuments.
     * @param listeTypesDocuments the listeTypesDocuments to set
     */
    public void setListeTypesDocuments(List<TypeDocumentModel> listeTypesDocuments) {
        this.listeTypesDocuments = listeTypesDocuments;
    }

    /**
     * Récupération de code.
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Définition de code.
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Récupération de description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définition de description.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Récupération de nombreDocs.
     * @return the nombreDocs
     */
    public int getNombreDocs() {
        return nombreDocs;
    }

    /**
     * Définition de nombreDocs.
     * @param nombreDocs the nombreDocs to set
     */
    public void setNombreDocs(int nombreDocs) {
        this.nombreDocs = nombreDocs;
    }
}

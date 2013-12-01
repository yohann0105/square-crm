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

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Représente un dossier contenant une liste de documents utilisateurs.
 * @author jgoncalves - SCUB
 */
public class DossierDocumentModel implements IsSerializable {
    private String identifiant;
    private String nom;
    private List<DocumentModel> documents;
    private List<DossierDocumentModel> dossiers;
    private DossierDocumentModel parent;
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
     * Récupération de documents.
     * @return the documents
     */
    public List<DocumentModel> getDocuments() {
        return documents;
    }
    /**
     * Définition de documents.
     * @param documents the documents to set
     */
    public void setDocuments(List<DocumentModel> documents) {
        this.documents = documents;
    }
    /**
     * Récupération de dossiers.
     * @return the dossiers
     */
    public List<DossierDocumentModel> getDossiers() {
        return dossiers;
    }
    /**
     * Définition de dossiers.
     * @param dossiers the dossiers to set
     */
    public void setDossiers(List<DossierDocumentModel> dossiers) {
        this.dossiers = dossiers;
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
}

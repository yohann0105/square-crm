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
package com.square.composant.ged.square.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.square.composant.ged.square.client.model.CriteresRechercheDocumentModel;
import com.square.composant.ged.square.client.model.DocumentModel;
import com.square.composant.ged.square.client.model.DossierDocumentModel;
import com.square.composant.ged.square.client.model.TypeDocumentModel;

/**
 * Interface asynchrone pour la gestion des documents.
 * @author jgoncalves - SCUB
 */
public interface DocumentsServiceGwtAsync {
    /**
     * Récupération d'une liste de documents a partir d'une liste d'identifiants.
     * @param listeIds la liste.
     * @param callback le callback.
     */
    void getListeDocumentsByListeIds(List<String> listeIds, AsyncCallback<List<DocumentModel>> callback);

    /**
     * Récupération des documents attachés a une personne rangés dans une arborescence.
     * @param criteres les critères.
     * @param callback le callback.
     */
    void getListeArborescenteDocumentsByCriteres(CriteresRechercheDocumentModel criteres, AsyncCallback<List<DossierDocumentModel>> callback);

    /**
     * Récupération des documents attachés a une personne.
     * @param criteres les critères.
     * @param callback le callback.
     */
    void getListeDocumentsByCriteres(CriteresRechercheDocumentModel criteres, AsyncCallback<List<DocumentModel>> callback);

    /**
     * Ajout d'un document dans la ged pour un utilisateur et association du document à l'action si renseignée.
     * @param document le document.
     * @param idAction l'identifiant de l'action à laquelle associer le document. L'association n'est pas faite si null.
     * @param callback le callback.
     */
    void ajouterDocumentEtAssocierAAction(DocumentModel document, Long idAction, AsyncCallback<Object> callback);

    /**
     * .
     * @param callback callback
     */
    void getListeTypesDocuments(AsyncCallback<List<TypeDocumentModel>> callback);

    /**
     * .
     * @param idAction .
     * @param listeIdsDocuments .
     * @param callback .
     */
    void associerDocumentsAAction(Long idAction, List<String> listeIdsDocuments, AsyncCallback<Object> callback);

    /**
     * .
     * @param idAction .
     * @param callback .
     */
    void getListeDocumentsAssociesAAction(Long idAction, AsyncCallback<List<String>> callback);
}

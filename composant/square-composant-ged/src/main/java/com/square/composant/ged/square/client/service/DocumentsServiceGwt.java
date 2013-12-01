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

import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.composant.ged.square.client.model.CriteresRechercheDocumentModel;
import com.square.composant.ged.square.client.model.DocumentModel;
import com.square.composant.ged.square.client.model.DossierDocumentModel;
import com.square.composant.ged.square.client.model.TypeDocumentModel;

/**
 * Service de gestion des documents.
 * @author jgoncalves - SCUB
 */
@RemoteServiceRelativePath(value = "handler/documentsService")
public interface DocumentsServiceGwt extends RemoteService {
    /**
     * Récupération d'une liste de documents a partir d'une liste d'identifiants.
     * @param listeIds la liste.
     * @return les documents.
     * @throws GwtRunTimeExceptionGwt .
     */
    List<DocumentModel> getListeDocumentsByListeIds(List<String> listeIds) throws GwtRunTimeExceptionGwt;

    /**
     * Récupération des documents attachés a une personne rangés dans une arborescence.
     * @param criteres les critères.
     * @return la liste arborescente de dossiers / documents
     * @throws GwtRunTimeExceptionGwt .
     */
    List<DossierDocumentModel> getListeArborescenteDocumentsByCriteres(CriteresRechercheDocumentModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Récupération des documents attachés a une personne.
     * @param criteres les critères.
     * @return la liste des documents
     * @throws GwtRunTimeExceptionGwt .
     */
    List<DocumentModel> getListeDocumentsByCriteres(CriteresRechercheDocumentModel criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Ajout d'un document dans la ged pour un utilisateur et association du document à l'action si renseignée.
     * @param document le document.
     * @param idAction l'identifiant de l'action à laquelle associer le document. L'association n'est pas faite si null.
     * @throws GwtRunTimeExceptionGwt .
     */
    void ajouterDocumentEtAssocierAAction(DocumentModel document, Long idAction) throws GwtRunTimeExceptionGwt;

    /**
     * .
     * @return .
     * @throws GwtRunTimeExceptionGwt GwtRunTimeExceptionGwt
     */
    List<TypeDocumentModel> getListeTypesDocuments() throws GwtRunTimeExceptionGwt;

    /**
     * .
     * @param idAction .
     * @param listeIdsDocuments .
     * @throws GwtRunTimeExceptionGwt .
     */
    void associerDocumentsAAction(Long idAction, List<String> listeIdsDocuments) throws GwtRunTimeExceptionGwt;

    /**
     * .
     * @param idAction .
     * @return .
     * @throws GwtRunTimeExceptionGwt .
     */
    List<String> getListeDocumentsAssociesAAction(Long idAction) throws GwtRunTimeExceptionGwt;
}

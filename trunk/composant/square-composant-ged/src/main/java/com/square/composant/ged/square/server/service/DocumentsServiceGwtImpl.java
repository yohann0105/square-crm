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
package com.square.composant.ged.square.server.service;

import gwtupload.server.UploadServlet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.fileupload.FileItem;
import org.gwtwidgets.server.spring.ServletUtils;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.gwt.module.client.exception.BusinessExceptionGwt;
import org.scub.foundation.framework.gwt.module.client.exception.TechnicalExceptionGwt;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.square.composant.ged.square.client.content.i18n.ComposantGedConstants;
import com.square.composant.ged.square.client.model.CriteresRechercheDocumentModel;
import com.square.composant.ged.square.client.model.DocumentModel;
import com.square.composant.ged.square.client.model.DossierDocumentModel;
import com.square.composant.ged.square.client.model.TypeDocumentModel;
import com.square.composant.ged.square.client.service.DocumentsServiceGwt;
import com.square.core.model.dto.ActionDto;
import com.square.core.service.interfaces.ActionService;
import com.square.document.core.dto.CodeLibelleDto;
import com.square.document.core.dto.CriteresRechercheDocumentDto;
import com.square.document.core.dto.DocumentDto;
import com.square.document.core.dto.RetourAjoutDocumentDto;
import com.square.document.core.dto.TypeDocumentDto;
import com.square.document.core.service.interfaces.DimensionService;
import com.square.document.core.service.interfaces.GedService;

/**
 * Implémentation des services de gestion des documents.
 * @author jgoncalves - SCUB
 */
public class DocumentsServiceGwtImpl extends RemoteServiceServlet implements DocumentsServiceGwt {
    private static final long serialVersionUID = 6482801915710163705L;

    private GedService gedService;

    private MapperDozerBean mapperDozerBean;

    private DimensionService gedDimensionService;

    private ActionService actionService;

    /***
     * Récupération du login (Acegi & Spring security supportés.
     * @return le login
     */
    private String getLoginUtilisateurCourant() {
        return SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null ? SecurityContextHolder
                .getContext().getAuthentication().getName() : org.springframework.security.context.SecurityContextHolder.getContext().getAuthentication()
                .getName();
    }

    @Override
    public List<DocumentModel> getListeDocumentsByListeIds(List<String> listeIds) {
        final CriteresRechercheDocumentDto criteres = new CriteresRechercheDocumentDto();
        criteres.setIds(listeIds);
        return mapperDozerBean.mapList(gedService.getListeDocumentsByCriteres(criteres, getLoginUtilisateurCourant()), DocumentModel.class);
    }

    @Override
    public List<DossierDocumentModel> getListeArborescenteDocumentsByCriteres(CriteresRechercheDocumentModel criteres) {
        // Récupération des documents de la personne :
        final List<DocumentDto> listeDocuments =
            gedService.getListeDocumentsByCriteres((CriteresRechercheDocumentDto) mapperDozerBean.map(criteres, CriteresRechercheDocumentDto.class),
                getLoginUtilisateurCourant());

        // Récupération de tous les tags des documents
        final Set<String> listeTags = new HashSet<String>();
        for (DocumentDto documentDto : listeDocuments) {
            if (documentDto.getTags() != null) {
                for (String tag : documentDto.getTags()) {
                    listeTags.add(tag);
                }
            }
        }

        // Récupération des types de documents de manière arborescente :
        final List<TypeDocumentDto> listeTypesDocuments = gedDimensionService.getListeTypesDocuments(getLoginUtilisateurCourant());
        final List<DossierDocumentModel> listeArborescente = new ArrayList<DossierDocumentModel>();
        for (TypeDocumentDto typeDocument : listeTypesDocuments) {
            listeArborescente.add(remplirDossier(typeDocument, listeDocuments, null));
        }
        for (String tag : listeTags) {
            listeArborescente.add(remplirDossier(tag, listeDocuments, null));
        }
        return listeArborescente;
    }

    @Override
    public List<DocumentModel> getListeDocumentsByCriteres(CriteresRechercheDocumentModel criteres) {
        // Récupération des documents de la personne :
        final List<DocumentDto> listeDocuments =
            gedService.getListeDocumentsByCriteres((CriteresRechercheDocumentDto) mapperDozerBean.map(criteres, CriteresRechercheDocumentDto.class),
                getLoginUtilisateurCourant());

        return mapperDozerBean.mapList(listeDocuments, DocumentModel.class);
    }

    /**
     * Parcours une arborescence de types de documents pour remplir une arborescence de dossiersDocumentModel.
     */
    private DossierDocumentModel remplirDossier(TypeDocumentDto typeDocument, List<DocumentDto> listeDocuments, DossierDocumentModel parent) {
        final DossierDocumentModel dossier = new DossierDocumentModel();
        dossier.setIdentifiant(typeDocument.getCode());
        dossier.setNom(typeDocument.getDescription() != null ? typeDocument.getDescription() : typeDocument.getNom());
        dossier.setDocuments(new ArrayList<DocumentModel>());
        dossier.setDossiers(new ArrayList<DossierDocumentModel>());
        dossier.setParent(parent);
        for (DocumentDto document : listeDocuments) {
            if (document.getTypes() != null) {
                for (CodeLibelleDto type : document.getTypes()) {
                    if (type.getCode().equals(typeDocument.getCode())) {
                        final DocumentModel documentModel = mapperDozerBean.map(document, DocumentModel.class);
                        documentModel.setParent(dossier);
                        dossier.getDocuments().add(documentModel);
                    }
                }
            }
        }
        if (typeDocument.getListeTypesDocuments() != null && !typeDocument.getListeTypesDocuments().isEmpty()) {
            for (TypeDocumentDto typeDoc : typeDocument.getListeTypesDocuments()) {
                dossier.getDossiers().add(remplirDossier(typeDoc, listeDocuments, dossier));
            }
        }
        return dossier;
    }

    /**
     * Parcours une arborescence de types de documents pour remplir une arborescence de dossiersDocumentModel.
     */
    private DossierDocumentModel remplirDossier(String tagDocument, List<DocumentDto> listeDocuments, DossierDocumentModel parent) {
        final DossierDocumentModel dossier = new DossierDocumentModel();
        dossier.setIdentifiant(tagDocument);
        dossier.setNom(tagDocument);
        dossier.setDocuments(new ArrayList<DocumentModel>());
        dossier.setDossiers(new ArrayList<DossierDocumentModel>());
        dossier.setParent(parent);
        for (DocumentDto document : listeDocuments) {
            if (document.getTags() != null) {
                for (String tag : document.getTags()) {
                    if (tag.equals(tagDocument)) {
                        final DocumentModel documentModel = mapperDozerBean.map(document, DocumentModel.class);
                        documentModel.setParent(dossier);
                        dossier.getDocuments().add(documentModel);
                    }
                }
            }
        }
        return dossier;
    }

    @Override
    public void ajouterDocumentEtAssocierAAction(DocumentModel document, Long idAction) {
        // Tentative de récupération du fichier uploadé dans la session :
        final List<FileItem> listeFichiers = UploadServlet.getSessionFileItems(ServletUtils.getRequest());
        final FileItem fichier = UploadServlet.findItemByFileName(listeFichiers, document.getNom());
        if (fichier == null) {
            throw new BusinessExceptionGwt(ComposantGedConstants.ERROR_FILE_RECOVER);
        }
        // Envoi au noyau
        final DocumentDto documentDto = mapperDozerBean.map(document, DocumentDto.class);
        List<CodeLibelleDto> typesListeDto = mapperDozerBean.mapList(document.getTypes(), CodeLibelleDto.class);
        documentDto.setTypes(typesListeDto);
        documentDto.setContenu(fichier.get());
        try {
            final RetourAjoutDocumentDto retourAjoutDocumentDto = gedService.ajouterDocument(documentDto, getLoginUtilisateurCourant());
            // Association du document à l'action si l'identifiant de l'action est renseigné
            if (idAction != null && retourAjoutDocumentDto != null && retourAjoutDocumentDto.getIdentifiantDocument() != null) {
                // Recherche de l'action pour récupérer la liste des documents déjà associés
                final ActionDto actionDto = actionService.rechercherActionParIdentifiant(idAction);
                List<com.square.core.model.dto.DocumentDto> listeDocs = new ArrayList<com.square.core.model.dto.DocumentDto>();
                if (actionDto.getDocuments() != null) {
                    listeDocs = actionDto.getDocuments();
                }
                listeDocs.add(new com.square.core.model.dto.DocumentDto(retourAjoutDocumentDto.getIdentifiantDocument()));
                actionService.attacherDocuments(idAction, listeDocs);
            }
        } catch (BusinessException e) {
            throw new BusinessExceptionGwt(e);
        } catch (TechnicalException e) {
            throw new TechnicalExceptionGwt(e);
        } finally {
            UploadServlet.removeSessionFileItems(ServletUtils.getRequest(), true);
        }
    }

    /**
     * Définition de gedService.
     * @param gedService the gedService to set
     */
    public void setGedService(GedService gedService) {
        this.gedService = gedService;
    }

    /**
     * Définition de mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définition de gedDimensionService.
     * @param gedDimensionService the gedDimensionService to set
     */
    public void setGedDimensionService(DimensionService gedDimensionService) {
        this.gedDimensionService = gedDimensionService;
    }

    @Override
    public void associerDocumentsAAction(Long idAction, List<String> listeIdsDocuments) {
        final List<com.square.core.model.dto.DocumentDto> listeDocs = new ArrayList<com.square.core.model.dto.DocumentDto>();
        for (String id : listeIdsDocuments) {
            listeDocs.add(new com.square.core.model.dto.DocumentDto(id));
        }
        actionService.attacherDocuments(idAction, listeDocs);
    }

    @Override
    public List<String> getListeDocumentsAssociesAAction(Long idAction) {
        final ActionDto action = actionService.rechercherActionParIdentifiant(idAction);
        final List<String> listeIdsDocuments = new ArrayList<String>();
        if (action.getDocuments() != null) {
            for (com.square.core.model.dto.DocumentDto document : action.getDocuments()) {
                listeIdsDocuments.add(document.getIdentifiantExterieur());
            }
        }
        return listeIdsDocuments;
    }

    @Override
    public List<TypeDocumentModel> getListeTypesDocuments() {
        return mapperDozerBean.mapList(gedDimensionService.getListeTypesDocuments(getLoginUtilisateurCourant()), TypeDocumentModel.class);
    }

    /**
     * Définition de actionService.
     * @param actionService the actionService to set
     */
    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }
}

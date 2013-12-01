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
package com.square.document.core.service.implementations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.IOUtils;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.square.document.core.dao.interfaces.DocumentDao;
import com.square.document.core.dao.interfaces.TagDao;
import com.square.document.core.dto.CodeLibelleDto;
import com.square.document.core.dto.CriteresRechercheDocumentDto;
import com.square.document.core.dto.DocumentDto;
import com.square.document.core.dto.RetourAjoutDocumentDto;
import com.square.document.core.model.Document;
import com.square.document.core.model.Tag;
import com.square.document.core.service.interfaces.GedMappingService;
import com.square.document.core.service.interfaces.GedService;
import com.square.document.core.util.GedKeyUtil;

/**
 * .
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
@Transactional(propagation = Propagation.REQUIRED)
public class GedServiceImpl implements GedService {

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    /** Dao pour les documents. */
    private DocumentDao documentDao;

    /** Dao pour les Tags. */
    private TagDao tagDao;

    /** Service de récupération de constantes. */
    private GedMappingService gedMappingService;

    /** Classe utilitaire pour accéder aux messages. */
    private MessageSourceUtil messageSourceUtil;

    private String backSlash = "/";

    /**
     * Edition de gedMappingService.
     * @param gedMappingService gedMappingService
     */
    public void setGedMappingService(GedMappingService gedMappingService) {
        this.gedMappingService = gedMappingService;
    }

    /**
     * Set the value of documentDao.
     * @param documentDao the documentDao to set
     */
    public void setDocumentDao(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    /**
     * Set the value of tagDao.
     * @param tagDao the tagDao to set
     */
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    /**
     * Set the value of mapperDozerBean.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Mutateur message.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }

    /** Existance d'un dossier. */
    private Boolean dossierExiste(String directoryPath) {
        final File directory = new File(directoryPath);
        return directory.exists();
    }

    /** Création d'un dossier. */
    private File creerDossier(String directoryPath) {
        if (!dossierExiste(directoryPath)) {
            new File(directoryPath).mkdir();
        }
        return new File(directoryPath);
    }

    @Override
    public RetourAjoutDocumentDto ajouterDocument(DocumentDto document, String utilisateur) {
        if (document.getNumeroClient() == null || document.getNumeroClient().trim().isEmpty()) {
            throw new BusinessException(messageSourceUtil.get(GedKeyUtil.MESSAGE_ERROR_FILE_NUMBER_USER_REQUIRED));
        }
        final String cheminDossierClient = gedMappingService.getCheminRepertoire() + "/" + document.getNumeroClient();

        creerDossier(cheminDossierClient);

        if (document.getNom() == null) {
            throw new BusinessException(messageSourceUtil.get(GedKeyUtil.MESSAGE_ERROR_FILE_NAME_REQUIRED));
        }

        final String name = Calendar.getInstance().getTimeInMillis() + "-" + document.getNom();
        final File file = new File(cheminDossierClient + "/" + name);

        try {
            file.createNewFile();
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            IOUtils.write(document.getContenu(), fileOutputStream);
            IOUtils.closeQuietly(fileOutputStream);
        } catch (IOException e) {
            throw new BusinessException(messageSourceUtil.get(GedKeyUtil.MESSAGE_ERROR_FILE_CONTENU));
        }

        final Set<Tag> listeTypes = new HashSet<Tag>();
        // TODO : A reprendre

        for (CodeLibelleDto typeDocument : document.getTypes()) {
            final Tag tag = tagDao.getTagByCriteria(typeDocument.getCode());
            if (tag == null){
                throw new BusinessException("tag null");
            }
            listeTypes.add(tag);
        }

        final Document docToSave = mapperDozerBean.map(document, Document.class);
        docToSave.setNom(name);
        docToSave.setTags(listeTypes);

        documentDao.ajoutDocument(docToSave);

        final RetourAjoutDocumentDto createdUpdatedFile = new RetourAjoutDocumentDto();
        createdUpdatedFile.setIdentifiantDocument(String.valueOf(docToSave.getId()));

        return createdUpdatedFile;
    }

    @Override
    public List<DocumentDto> getListeDocumentsByCriteres(CriteresRechercheDocumentDto criteres, String utilisateur) {
        final List<Document> documents = documentDao.rechercherDocumentParCritere(criteres);
        final List<DocumentDto> listDocumentDtos = new ArrayList<DocumentDto>();
        List<String> nomTags = null;
        DocumentDto documentDto = null;
        for (Document document : documents) {
            nomTags = new ArrayList<String>();
            documentDto = new DocumentDto();

            mapperDozerBean.map(document, documentDto);

            if (document.getTags() != null) {
                for (Tag tag : document.getTags()) {
                    nomTags.add(tag.getNom());
                }
            }
            documentDto.setTags(nomTags);

            listDocumentDtos.add(documentDto);
              }
              
        return listDocumentDtos;
    }

    @Override
    public DocumentDto getDocumentByCriteres(CriteresRechercheDocumentDto criteres, String utilisateur) {
        if (criteres.getNumeroClient() == null || criteres.getNumeroClient().trim().isEmpty()) {
            throw new BusinessException(messageSourceUtil.get(GedKeyUtil.MESSAGE_ERROR_FILE_NUMBER_USER_REQUIRED));
        }

        if (criteres.getIds() == null) {
            throw new BusinessException(messageSourceUtil.get(GedKeyUtil.MESSAGE_ERROR_FILE_NUMBER_USER_REQUIRED));
        }
        else{
            if (criteres.getIds().get(0) == null){
                throw new BusinessException(messageSourceUtil.get(GedKeyUtil.MESSAGE_ERROR_FILE_NUMBER_USER_REQUIRED));
            }
        }
        
        final Document document = documentDao.rechercherDocumentUniqueParCritere(criteres);
        final DocumentDto documentDto = mapperDozerBean.map(document, DocumentDto.class);
        final MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

        documentDto.setTypeMime(mimeTypesMap.getContentType(documentDto.getNom()));

        final String cheminFichier = gedMappingService.getCheminRepertoire() + "/" + document.getNumClient() + "/" + document.getNom();
        try {
            final byte[] contenuPieceJointe = IOUtils.toByteArray(new FileInputStream(cheminFichier));
            documentDto.setContenu(contenuPieceJointe);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return documentDto;
    }

    /** Enregistre un fichier dans un dossier. **/
    private void enregistrerFichier(File leFichier, String leRepertoire) {
        byte[] data = null;
        try {
            final File file = new File(leRepertoire + backSlash + leFichier.getName());
            final FileOutputStream fileOutputStream = new FileOutputStream(file);

            data = IOUtils.toByteArray(new FileInputStream(leFichier));
            file.createNewFile();
            IOUtils.write(data, fileOutputStream);
            IOUtils.closeQuietly(fileOutputStream);

        } catch (IOException e) {
            throw new BusinessException(messageSourceUtil.get(GedKeyUtil.ERROR_SAVE_FILE));
        }
    }

    /** Test l'existence d'un dossier adherent. */
    private Boolean dossierAdherentExiste(String numero) {
        return dossierExiste(gedMappingService.getCheminRepertoire() + backSlash + numero);
    }

    /** Suppression d'un dossier adherent. */
    private Boolean supprimerDossierAdherent(String numero) {
        return new File(gedMappingService.getCheminRepertoire() + backSlash + numero).delete();
    }

    /** Creation ou suppression d'un dossier adherent. **/
    private File creerDossierAdherent(String numero) {
        return creerDossier(gedMappingService.getCheminRepertoire() + backSlash + numero);
    }

    @Override
    public void transfererDocumentPersonne(String numAdherentSource, String numAdherentDestination, String utilisateur) {
        if (!dossierAdherentExiste(numAdherentSource)) {
            return;
        }

        // le dossier source est récupéré
        final File dossierSource = creerDossierAdherent(numAdherentSource);

        // recupère la liste des fichiers de l'adhérent source
        final CriteresRechercheDocumentDto criteresRechercheDocumentDto = new CriteresRechercheDocumentDto();
        criteresRechercheDocumentDto.setNumeroClient(numAdherentSource);
        final List<DocumentDto> listeDoc = getListeDocumentsByCriteres(criteresRechercheDocumentDto, utilisateur);

        // change le numero d'adhérent des documents source en l'hadérent de destination
        for (DocumentDto documentDto : listeDoc) {
            final Document document = documentDao.rechercherDocumentlParId(Long.parseLong(documentDto.getIdentifiant()));
            document.setNumClient(numAdherentDestination);
        }

        // création du dossier de destination
        final File dossierDestination = creerDossierAdherent(numAdherentDestination);

        if (!dossierAdherentExiste(numAdherentDestination)) {

            // si le dossier de destination n'existe pas le dossier source est renommé en ce dossier
            dossierSource.renameTo(dossierDestination);
        } else {

            // sinon les fichiers sont copiés puis supprimés
            for (File fichierSource : dossierSource.listFiles()) {
                enregistrerFichier(fichierSource, dossierDestination.getPath());
                fichierSource.delete();
            }
        }
        supprimerDossierAdherent(numAdherentSource);
    }

}

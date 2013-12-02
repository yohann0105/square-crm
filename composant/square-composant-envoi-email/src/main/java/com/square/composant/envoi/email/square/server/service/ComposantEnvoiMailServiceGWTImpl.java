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
package com.square.composant.envoi.email.square.server.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;

import com.square.composant.envoi.email.square.client.constantes.ComposantEnvoiEmailConstants;
import com.square.composant.envoi.email.square.client.exception.ControleIntegriteExceptionGwt;
import com.square.composant.envoi.email.square.client.model.DimensionCriteresRechercheModel;
import com.square.composant.envoi.email.square.client.model.EmailModel;
import com.square.composant.envoi.email.square.client.model.ModeleEmailModel;
import com.square.composant.envoi.email.square.client.model.PieceJointeModel;
import com.square.composant.envoi.email.square.client.service.ComposantEnvoiMailServiceGWT;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.core.model.dto.DimensionCriteresRechercheDto;
import com.square.core.model.dto.ModeleEmailDto;
import com.square.core.model.dto.RapportDto;
import com.square.core.service.interfaces.DimensionService;
import com.square.mail.core.dto.emails.MailDto;
import com.square.mail.core.dto.emails.PieceJointeFileDto;
import com.square.mail.core.service.interfaces.email.MailService;

/**
 * Implémentation de l'interface ComposantEnvoiMailServiceGWT.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ComposantEnvoiMailServiceGWTImpl implements ComposantEnvoiMailServiceGWT {

    /** Service d'envoi d'email. */
    private MailService mailService;

    /** Service d'envoi d'email. */
    private DimensionService dimensionService;

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    @Override
    public void envoyerEmail(EmailModel email) {
        // Création de l'email
        final List<PieceJointeFileDto> listePiecesJointes = new ArrayList<PieceJointeFileDto>();
        if (email.getListePiecesJointes() != null && email.getListePiecesJointes().size() > 0) {
            for (PieceJointeModel pieceJointeModel : email.getListePiecesJointes()) {
                final PieceJointeFileDto pieceJointeFileDto = mapperDozerBean.map(pieceJointeModel, PieceJointeFileDto.class);
                // Récupération du contenu du fichier par son path
                final byte[] contenuPieceJointe = getContenuPieceJointeByPath(pieceJointeModel.getPath());
                pieceJointeFileDto.setData(contenuPieceJointe);
                listePiecesJointes.add(pieceJointeFileDto);
            }
        }
        final MailDto mailDto = new MailDto(email.getExpediteur(), email.getListeDestinataires(), email.getTitre(), email.getTexte(),
            listePiecesJointes, email.getIsHtml());
        mailDto.setNomExpediteur(email.getNomExpediteur());

        // Vérification de l'email
        final RapportDto rapportErreur = new RapportDto();
        if (mailService.verifierEmail(mailDto, rapportErreur)) {
            throw new ControleIntegriteExceptionGwt((RapportModel) mapperDozerBean.map(rapportErreur, RapportModel.class));
        }
        // Envoi de l'email
        mailService.envoyerMail(mailDto);

        // Suppression des pièces jointes
        final List<String> listePathPiecesJointes = new ArrayList<String>();
        for (PieceJointeModel pieceJointe : email.getListePiecesJointes()) {
            listePathPiecesJointes.add(pieceJointe.getPath());
        }
        supprimerListePiecesJointesRepertoire(listePathPiecesJointes);
    }

    @Override
    public void supprimerListePiecesJointesRepertoire(List<String> listePath) {
        if (listePath != null && listePath.size() > 0) {
            for (String path : listePath) {
                final File fichierTemp = new File(path);
                fichierTemp.delete();
            }
        }
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherListeModelesEmails(DimensionCriteresRechercheModel criteres) {
        final List<IdentifiantLibelleDto> liste =
            dimensionService.rechercherListeModelesEmails((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, IdentifiantLibelleGwt.class);
    }

    @Override
    public List<ModeleEmailModel> rechercherModelesEmails(DimensionCriteresRechercheModel criteres) {
        final List<ModeleEmailDto> liste =
            dimensionService.rechercherModelesEmails((DimensionCriteresRechercheDto) mapperDozerBean.map(criteres, DimensionCriteresRechercheDto.class));
        return mapperDozerBean.mapList(liste, ModeleEmailModel.class);
    }

    /**
     * Récupère le contenu d'une pièce jointe par son path.
     * @param path le path de la pièce jointe.
     * @return le contenu de la pièce jointe.
     */
    private byte[] getContenuPieceJointeByPath(String path) {
        // Récupération du fichier temporaire
        final File tmp = new File(path);
        try {
            final FileInputStream in = new FileInputStream(tmp);
            final byte[] contenuPieceJointe = IOUtils.toByteArray(in);
            return contenuPieceJointe;
        } catch (FileNotFoundException e) {
            throw new TechnicalException(ComposantEnvoiEmailConstants.ERREUR_ABSCENCE_PIECE_JOINTE);
        } catch (IOException e) {
            throw new TechnicalException(ComposantEnvoiEmailConstants.ERREUR_RECUPERATION_PIECE_JOINTE);
        }
    }

    /**
     * Définit la valeur de mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définit la valeur de mailService.
     * @param mailService la nouvelle valeur de mailService
     */
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * Définit la valeur de dimensionService.
     * @param dimensionService la nouvelle valeur de dimensionService
     */
    public void setDimensionService(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

}

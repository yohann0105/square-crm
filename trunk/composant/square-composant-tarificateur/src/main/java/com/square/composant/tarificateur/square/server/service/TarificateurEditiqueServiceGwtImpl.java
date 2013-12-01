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
package com.square.composant.tarificateur.square.server.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.springframework.security.context.SecurityContextHolder;

import com.square.composant.envoi.email.square.client.model.PieceJointeModel;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.EditionDocumentModel;
import com.square.composant.tarificateur.square.client.service.TarificateurEditiqueServiceGwt;
import com.square.tarificateur.noyau.dto.editique.EditionDocumentDto;
import com.square.tarificateur.noyau.service.interfaces.TarificateurEditiqueService;
import com.square.user.core.dto.UtilisateurDto;
import com.square.user.core.service.interfaces.UtilisateurService;

/**
 * Implémentation de l'interface TarificateurEditiqueServiceGwt.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class TarificateurEditiqueServiceGwtImpl implements TarificateurEditiqueServiceGwt {

    /** Constante pour le type d'encodage. */
    private static final String TYPE_ENCODAGE = "MD5";

    /** Constante pour le décalage lors de la conversion en hexadécimal. */
    private static final int DECALAGE_HEXA = 0x0F;

    /** Service éditique du tarificateur. */
    private TarificateurEditiqueService tarificateurEditiqueService;

    /** Service Utilisateur. */
    private UtilisateurService utilisateurService;

    /** Répertoire de stockage des fichiers temporaires des pièces jointes. */
    private String repertoirePiecesJointes;

    /** Mapper Dozer. */
    private MapperDozerBean mapperDozerBean;

    @Override
    public List<PieceJointeModel> getPiecesJointes(EditionDocumentModel editionDocumentModel) {
        final EditionDocumentDto editionDocumentDto = mapperDozerBean.map(editionDocumentModel, EditionDocumentDto.class);
        // Renseignement de la date d'édition du BA
        editionDocumentDto.setDateEditionBa(Calendar.getInstance());
        final List<FichierDto> listeFichiersDto = tarificateurEditiqueService.genererDocumentsPdf(editionDocumentDto);
        final List<PieceJointeModel> listePiecesJointesModel = new ArrayList<PieceJointeModel>();
        if (listeFichiersDto != null && listeFichiersDto.size() > 0) {
            for (FichierDto fichierDto : listeFichiersDto) {
                final PieceJointeModel pieceJointeModel = mapperDozerBean.map(fichierDto, PieceJointeModel.class);
                pieceJointeModel.setPath(enregistrerPieceJointe(fichierDto.getData()));
                listePiecesJointesModel.add(pieceJointeModel);
            }
        }
        return listePiecesJointesModel;
    }

    @Override
    public void notifierEnvoiDocumentsPdfParMail(EditionDocumentModel editionDocumentModel) {
        final EditionDocumentDto editionDocumentDto = mapperDozerBean.map(editionDocumentModel, EditionDocumentDto.class);
        // Renseignement de la date d'édition du BA
        editionDocumentDto.setDateEditionBa(Calendar.getInstance());
        // Récupération de l'utilisateur connecté
        final String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if (login == null) {
            throw new TechnicalException(ComposantTarificateurConstants.ERROR_NOTIFICATION_LOGIN_ABSENT);
        }
        final UtilisateurDto utilisateurConnecte = utilisateurService.getUtilisateurByLogin(login);
        if (utilisateurConnecte == null) {
            throw new TechnicalException(ComposantTarificateurConstants.ERROR_NOTIFICATION_USER_ABSENT);
        }
        editionDocumentDto.setEidCreateur(utilisateurConnecte.getId());
        tarificateurEditiqueService.notifierEnvoiDocumentsPdfParMail(editionDocumentDto);
    }

    /**
     * Enregistre le contenu de la pièce jointe dans le fichier temporaire.
     * @param contenuPieceJointe le contenu de la pièce jointe.
     * @return le path de la pièce jointe
     */
    private String enregistrerPieceJointe(byte[] contenuPieceJointe) {
        final File repertoire = new File(repertoirePiecesJointes);
        if (!repertoire.exists()) {
            repertoire.mkdirs();
        }
        final String nomFichier = genererNomFichierTimeStamp();
        try {
            final File tmp = File.createTempFile(nomFichier, ".pdf", repertoire);
            final FileOutputStream out = new FileOutputStream(tmp);
            out.write(contenuPieceJointe);
            return tmp.getPath();
        }
        catch (IOException e) {
            throw new TechnicalException(ComposantTarificateurConstants.ERROR_SAVE_ATTACHED_PIECE);
        }
    }

    /**
     * Génère un nom pour un fichier à enregistrer à partir d'un TimeStamp et d'une clé MD5.
     * @return le nom généré
     */
    private String genererNomFichierTimeStamp() {
        try {
            // Initialisation du nom généré à partir d'un TimeStamp
            String nomFichierGenere = String.valueOf(System.currentTimeMillis());

            // Génération d'une clé MD5
            final MessageDigest md = MessageDigest.getInstance(TYPE_ENCODAGE);
            final Long init = new Long(System.currentTimeMillis());
            md.update(init.byteValue());
            // Récupération de la valeur et conversion en hexadécimal
            final String cle = convertToHexa(md.digest());

            // Ajout de la clé MD5 au nom généré
            nomFichierGenere += cle;

            return nomFichierGenere;
        }
        catch (NoSuchAlgorithmException e) {
            throw new TechnicalException(ComposantTarificateurConstants.ERROR_GENERATION_FICHIER);
        }
    }

    /**
     * Convertit un tableau de byte en hexadécimal.
     * @param data le tableau à convertir.
     * @return la chaînede caractère en hexadécimal.
     */
    private String convertToHexa(final byte[] data) {
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & DECALAGE_HEXA;
            int twoHalfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                }
                else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & DECALAGE_HEXA;
            }
            while (twoHalfs++ < 1);
        }
        return buf.toString();
    }

    /**
     * Définit la valeur de tarificateurEditiqueService.
     * @param tarificateurEditiqueService la nouvelle valeur de tarificateurEditiqueService
     */
    public void setTarificateurEditiqueService(TarificateurEditiqueService tarificateurEditiqueService) {
        this.tarificateurEditiqueService = tarificateurEditiqueService;
    }

    /**
     * Définit la valeur de mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définit la valeur de repertoirePiecesJointes.
     * @param repertoirePiecesJointes the repertoirePiecesJointes to set
     */
    public void setRepertoirePiecesJointes(String repertoirePiecesJointes) {
        this.repertoirePiecesJointes = repertoirePiecesJointes;
    }

    /**
     * Définit la valeur de utilisateurService.
     * @param utilisateurService the utilisateurService to set
     */
    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
}

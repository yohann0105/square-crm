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
package com.square.tarificateur.noyau.service.interfaces;

import java.util.List;

import org.scub.foundation.framework.base.dto.FichierDto;

import com.square.tarificateur.noyau.dto.editique.EditionDocumentDto;
import com.square.tarificateur.noyau.dto.editique.FichierModeleDto;

/**
 * Service d'édition des documents du tarificateur.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface TarificateurEditiqueService {

    /**
     * Imprime un document PDF lié au tarificateur (bulletin d'adhésion, avenant, lettre d'annulation).
     * @param editionDocumentDto le DTO contenant les infos pour l'impression
     * @return le(s) document(s) PDF
     */
    List<FichierDto> imprimerDocumentPdf(EditionDocumentDto editionDocumentDto);

    /**
     * Envoie un document par mail à la personne liée au devis.
     * @param editionDocumentDto le DTO contenant les infos pour l'impression
     */
    void envoyerParMailDocumentPdf(EditionDocumentDto editionDocumentDto);

    /**
     * Génère la liste des documents PDF.
     * @param editionDocumentDto le DTO contenant les infos d'édition
     * @return la liste des fichiers générés
     */
    List<FichierModeleDto> genererDocumentsModelePdf(EditionDocumentDto editionDocumentDto);

    /**
     * Génère la liste des documents PDF.
     * @param editionDocumentDto le DTO contenant les infos d'édition
     * @return la liste des fichiers générés
     */
    List<FichierDto> genererDocumentsPdf(EditionDocumentDto editionDocumentDto);

    /**
     * Notifie l'envoi de documents PDF par mails.
     * <br/>Crée l'action d'édition et associe les documents à cette action.
     * @param editionDocumentDto le DTO contenant les infos d'édition
     */
    void notifierEnvoiDocumentsPdfParMail(EditionDocumentDto editionDocumentDto);
}

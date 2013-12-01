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
package com.square.composant.tarificateur.square.server.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.gwt.module.client.exception.BusinessExceptionGwt;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.print.core.service.interfaces.EditiqueService;
import com.square.tarificateur.noyau.dto.editique.EditionDocumentDto;
import com.square.tarificateur.noyau.service.interfaces.TarificateurEditiqueService;

/**
 * Servlet d'impression de devis.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ImprimerDevisServlet extends HttpServlet {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 7697451032750978456L;

    private TarificateurEditiqueService tarificateurEditiqueService;

    private EditiqueService editiqueService;

    private static ImprimerDevisServlet instance;

    /** Le cache des fichiers à imprimer. */
    private static Map<Long, List<FichierDto>> cache;

    /** La séquence qui permet de gérer le cache des fichiers à imprimer. */
    private static long sequence = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() throws ServletException {
        super.init();
        tarificateurEditiqueService =
            (TarificateurEditiqueService) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("tarificateurEditiqueService");
        editiqueService = (EditiqueService) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("editiqueService");
        cache = new HashMap<Long, List<FichierDto>>();
        instance = this;
    }

    /**
     * Récupère une instance initialisée de la servlet.
     * @return une instance initialisée de la servlet
     */
    public static ImprimerDevisServlet getInstance() {
    	return instance;
    }

    /**
     * Construit les fichiers à imprimer.
     * @param idDevis l'identifiant du devis
     * @param lignesDevis les ids des lignes de devis à imprimer
     * @param modeledDevis les ids des modèles de devis à imprimer
     * @param beneficiaires les ids des bénéficiaires
     * @param referenceOpportunite la référence de l'opportunité
     * @return la liste des fichiers à imprimer
     */
    public Long construireFichiers(Long idDevis, String lignesDevis, String modeledDevis, String beneficiaires, String referenceOpportunite) {
        final List<Long> listeIdsLignesDevis = new ArrayList<Long>();
        final String[] tabIdsLignesDevis = lignesDevis.split(ComposantTarificateurConstants.SEPARATOR_IDENTIFIANTS);
        if (tabIdsLignesDevis.length <= 0) {
            throw new BusinessException(ComposantTarificateurConstants.ERROR_LIGNE_DEVIS_PRINT_ABSENT);
        }
        else {
            for (int i = 0; i < tabIdsLignesDevis.length; i++) {
                final String idLigneDevis = tabIdsLignesDevis[i];
                if (idLigneDevis != null && !"".equals(idLigneDevis)) {
                    listeIdsLignesDevis.add(new Long(tabIdsLignesDevis[i]));
                }
            }
        }

        final List<Long> listeIdsModelesDevis = new ArrayList<Long>();
        final String[] tabIdsModelesDevis = modeledDevis.split(ComposantTarificateurConstants.SEPARATOR_IDENTIFIANTS);
        if (tabIdsLignesDevis.length <= 0) {
            throw new BusinessException(ComposantTarificateurConstants.ERROR_MODELE_DEVIS_ABSENT);
        }
        else {
            for (int i = 0; i < tabIdsModelesDevis.length; i++) {
                final String idModeleDevis = tabIdsModelesDevis[i];
                if (idModeleDevis != null && !"".equals(idModeleDevis)) {
                    listeIdsModelesDevis.add(new Long(idModeleDevis));
                }
            }
        }

        final List<Long> listeIdsBenefsSelectionnes = new ArrayList<Long>();
        if (beneficiaires != null && !"".equals(beneficiaires)) {
            final String[] tabIdsBenefs = beneficiaires.split(ComposantTarificateurConstants.SEPARATOR_IDENTIFIANTS);
            if (tabIdsBenefs.length > 0) {
                for (int i = 0; i < tabIdsBenefs.length; i++) {
                    final String idBenef = tabIdsBenefs[i];
                    if (idBenef != null && !"".equals(idBenef)) {
                        listeIdsBenefsSelectionnes.add(new Long(idBenef));
                    }
                }
            }
        }

        final EditionDocumentDto editionDocumentDto = new EditionDocumentDto();
        editionDocumentDto.setIdentifiantDevis(idDevis);
        editionDocumentDto.setIdsLigneDevisSelection(listeIdsLignesDevis);
        editionDocumentDto.setIdsModelesDevisSelectionnes(listeIdsModelesDevis);
        editionDocumentDto.setReferenceOpportunite(referenceOpportunite);
        editionDocumentDto.setIdsBeneficiairesSelectionnes(listeIdsBenefsSelectionnes);
        // Renseignement de la date d'édition du BA
        editionDocumentDto.setDateEditionBa(Calendar.getInstance());
        // Récupération de l'utilisateur connecté
        final String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if (login == null) {
            throw new BusinessException(ComposantTarificateurConstants.ERROR_USER_ABSENT);
        }
        final long idImpression = getNextSequence();

        cache.put(idImpression, tarificateurEditiqueService.imprimerDocumentPdf(editionDocumentDto));

        return idImpression;
    }

    private static synchronized long getNextSequence() {
    	return sequence++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération des paramètres de la servlet
        final Long idImpression = new Long(request.getParameter(ComposantTarificateurConstants.PARAM_ID_IMPRESSION));

        try {
            final List<FichierDto> listeFichiers = cache.get(idImpression);
            if (listeFichiers != null && listeFichiers.size() > 0) {
                // Définition du type de la réponse
                response.setContentType(listeFichiers.get(0).getTypeMime());
                // Nom du fichier
                response.setHeader("Content-disposition", "inline; filename=" + listeFichiers.get(0).getNom());
                // Copie du contenu du fichier dans le flux de sortie
                final OutputStream out = response.getOutputStream();
                if (listeFichiers.size() > 1) {
                    out.write(editiqueService.fusionnerFichiersPdfs(listeFichiers));
                }
                else {
                    out.write(listeFichiers.get(0).getData());
                }
                out.close();
            }
        } catch (BusinessExceptionGwt e) {
            throw new ServletException(e.getMessage());
        } finally {
        	cache.remove(idImpression);
        }
    }
}

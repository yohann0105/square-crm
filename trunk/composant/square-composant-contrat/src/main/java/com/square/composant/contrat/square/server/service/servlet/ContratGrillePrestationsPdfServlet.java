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
package com.square.composant.contrat.square.server.service.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scub.foundation.framework.base.dto.FichierDto;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.square.composant.contrat.square.client.view.ContratsViewImplConstants;

/**
 * Servlet de génération du document PDF pour les grilles de prestations.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class ContratGrillePrestationsPdfServlet extends HttpServlet {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = -5200134304330061489L;

    private static final String NOM_FICHIER_GRILLE_PDF = "grillePresta.pdf";

    /**
     * {@inheritDoc}
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Définition du type de la réponse
//        response.setContentType(ServletConstants.TYPE_MIME_PDF);
//
//        // Nom du fichier
//        response.setHeader(ServletConstants.HEADER_CONTENT_DISPOSITION, ServletConstants.HEADER_CONTENT_DISPOSITION_INLINE + NOM_FICHIER_GRILLE_PDF);
//
//        // Récupération des service pour l'édition de grilles de prestations et de leur mapping.
//        final GrillePrestationEditiqueService grillePrestationEditiqueService =
//            (GrillePrestationEditiqueService) WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()).getBean(
//                "grillePrestationEditiqueService");
//        final GrillePrestationMappingService grillePrestationMappingService =
//            (GrillePrestationMappingService) WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()).getBean(
//                "grillePrestationMappingService");
//
//        // Récupération de l'ID du produit
//        final String identifiantProduit = request.getParameter(ContratsViewImplConstants.PARAM_ID_PRODUIT);
//        final String sIsCollectif = request.getParameter(ContratsViewImplConstants.PARAM_IS_COLLECTIF);
//        boolean isCollectif = false;
//        if (sIsCollectif != null && !"".equals(sIsCollectif)) {
//            isCollectif = Boolean.valueOf(sIsCollectif).booleanValue();
//        }
//
//        if (identifiantProduit != null && !"".equals(identifiantProduit)) {
//            final Integer idProduit = new Integer(identifiantProduit);
//            Long idSegment;
//            if (isCollectif) {
//                idSegment = grillePrestationMappingService.getIdSegmentCommercialCollectifObligatoire();
//            }
//            else {
//                idSegment = grillePrestationMappingService.getIdSegmentCommercialIndividuel();
//            }
//            final FichierDto fichier =
//                grillePrestationEditiqueService.getGrillePrestaPdfByIdProduit(idProduit, idSegment);
//
//            // Copie du contenu du fichier dans le flux de sortie
//            final OutputStream out = response.getOutputStream();
//            out.write(fichier.getContenuFichier());
//            out.close();
//        }
//        else {
//            throw new ServletException("Erreur lors de la génération de la grille de prestations : paramètres manquants");
//        }
    }
}

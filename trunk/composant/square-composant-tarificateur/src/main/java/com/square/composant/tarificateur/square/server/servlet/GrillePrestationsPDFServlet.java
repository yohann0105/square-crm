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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet de génération du document PDF pour les grilles de prestations.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class GrillePrestationsPDFServlet extends HttpServlet {

    /** Identificateur de sérialisation. */
    private static final long serialVersionUID = 4050203024846631733L;

    @SuppressWarnings("unused")
	private static final String NOM_FICHIER_GRILLE_PDF = "grillePresta.pdf";

    /**
     * {@inheritDoc}
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Définition du type de la réponse
//        response.setContentType(ServletConstants.TYPE_MIME_PDF);
//
//        // Nom du fichier
//        response.setHeader(ServletConstants.HEADER_CONTENT_DISPOSITION, ServletConstants.HEADER_CONTENT_DISPOSITION_INLINE
//                + NOM_FICHIER_GRILLE_PDF);
//
//        // Récupération des service pour l'édition de grilles de prestations et de leur mapping.
//        final GrillePrestationEditiqueService grillePrestationEditiqueService =
//            (GrillePrestationEditiqueService) WebApplicationContextUtils.getWebApplicationContext(
//                    request.getSession().getServletContext()).getBean("grillePrestationEditiqueService");
//        final GrillePrestationMappingService grillePrestationMappingService =
//            (GrillePrestationMappingService) WebApplicationContextUtils.getWebApplicationContext(
//                request.getSession().getServletContext()).getBean("grillePrestationMappingService");
//
//        // Récupération des paramètres de l'url
//        final String params = request.getQueryString();
//        final String[] decoupeParams = params.split("PRODUIT_AIA=");
//        String produitAIA = null;
//        String garantieAIA = null;
//        String identifiantProduit = null;
//        if (decoupeParams.length > 1) {
//            final String[] decoupeProduitAIA = decoupeParams[1].split("&GARANTIE_AIA=");
//            produitAIA = decoupeProduitAIA[0];
//            produitAIA = produitAIA.replaceAll("%20", " ");
//            if (decoupeProduitAIA.length > 1) {
//                final String[] decoupeGarantieAIA = decoupeProduitAIA[1].split("&NUMERO_GARANTIE_AIA=");
//                garantieAIA = decoupeGarantieAIA[0];
//                garantieAIA = garantieAIA.replaceAll("%20", " ");
//            }
//        }
//        else {
//            final String[] decoupeIdProuit = params.split(ComposantTarificateurConstants.PARAM_ID_PRODUIT + "=");
//            if (decoupeIdProuit.length > 1) {
//                identifiantProduit = decoupeIdProuit[1];
//            }
//        }
//
//        // Récupération du PDF de la grille de prestations en fonction du paramètre
//        if (produitAIA != null && !"".equals(produitAIA) && garantieAIA != null && !"".equals(garantieAIA)) {
//            final FichierDto fichier = grillePrestationEditiqueService.getGrillePrestaPdfByProduit(produitAIA, garantieAIA,
//                    grillePrestationMappingService.getIdSegmentCommercialIndividuel());
//
//            if (fichier == null || fichier.getContenuFichier() == null || fichier.getContenuFichier().length == 0) {
//                throw new ServletException("La grille demandée n'existe pas");
//            }
//
//            response.setHeader("Content-disposition", "attachment; filename=\"" + fichier.getNomFichier() + "\"");
//            response.setContentLength(fichier.getContenuFichier().length);
//            final OutputStream out = response.getOutputStream();
//            out.write(fichier.getContenuFichier());
//            out.close();
//        } else if (identifiantProduit != null && !"".equals(identifiantProduit)) {
//            final Integer idProduit = new Integer(identifiantProduit);
//            final FichierDto fichier = grillePrestationEditiqueService.getGrillePrestaPdfByIdProduit(
//                    idProduit, grillePrestationMappingService.getIdSegmentCommercialIndividuel());
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

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
package com.square.composant.envoi.email.square.server.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.square.composant.envoi.email.square.client.constantes.ComposantEnvoiEmailConstants;

/**
 * Servlet permettant l'upload de fichier joint.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class UploadFichierServlet extends HttpServlet {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 5881658754300420024L;

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        // Création d'un DiskFileItemFactory pour stocker les fichiers.
        if (isMultipart) {
            try {
                final FileItemFactory factory = new DiskFileItemFactory();
                final ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setHeaderEncoding(ComposantEnvoiEmailConstants.ENCODAGE_UTF_8);

                // Récupération des items contenus dans la requête
                final List<FileItem> listeItems = upload.parseRequest(request);

                // Parcours des items
                for (FileItem item : listeItems) {

                    // Fichier à uploader
                    if (!item.isFormField()) {
                        // Nom du fichier
                        String nomFichier = "";
                        // on verifie si il y a un backslash
                        if (item.getName().indexOf("\\") != -1) {
                            // [\\\\] = expression régulière pour découper suivant le backslash
                            final String[] tabNomFichier = item.getName().split("\\\\");
                            nomFichier = tabNomFichier[tabNomFichier.length - 1];
                        }
                        else {
                            nomFichier = item.getName();
                        }

                        // Type MIME
                        final String typeMime = item.getContentType();

                        // Création d'un fichier temporaire
                        String prefixe = nomFichier;
                        String suffixe = null;
                        if (nomFichier.indexOf(".") != -1) {
                            final int indexPoint = nomFichier.lastIndexOf(".");
                            prefixe = nomFichier.substring(0, indexPoint);
                            suffixe = nomFichier.substring(indexPoint);
                        }

                        if (prefixe.length() < 3) {
                            response.getOutputStream().print(ComposantEnvoiEmailConstants.ERREUR_UPLOAD_FICHIER_NOM_INCORRECT);
                        }
                        else {
                            final File fichierTemporaire = File.createTempFile(prefixe, suffixe);
                            item.write(fichierTemporaire);

                            // Renvoi des infos concernant le fichier
                            final StringBuffer infosFichier = new StringBuffer();
                            infosFichier.append(ComposantEnvoiEmailConstants.PARAM_NOM_FICHIER).append(ComposantEnvoiEmailConstants.EGAL).append(nomFichier);
                            infosFichier.append(ComposantEnvoiEmailConstants.ET);
                            infosFichier.append(ComposantEnvoiEmailConstants.PARAM_PATH_FICHIER_TEMP).append(ComposantEnvoiEmailConstants.EGAL).append(
                                fichierTemporaire.getCanonicalPath());
                            infosFichier.append(ComposantEnvoiEmailConstants.ET);
                            infosFichier.append(ComposantEnvoiEmailConstants.PARAM_TYPE_MIME).append(ComposantEnvoiEmailConstants.EGAL).append(typeMime);
                            response.getOutputStream().print(URLEncoder.encode(infosFichier.toString(), ComposantEnvoiEmailConstants.ENCODAGE_UTF_8));
                        }
                    }
                }

            }
            catch (FileUploadException e) {
                response.getOutputStream().print(ComposantEnvoiEmailConstants.ERREUR_UPLOAD_FICHIER);
            }
            catch (Exception e) {
                response.getOutputStream().print(ComposantEnvoiEmailConstants.ERREUR_UPLOAD_FICHIER);
            }
        }

    }
}

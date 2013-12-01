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
package com.square.composant.envoi.email.square.client.constantes;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes du composant.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface ComposantEnvoiEmailConstants extends Constants {

    /** Cent pourcent. */
    String CENT_POURCENT = "100%";

    /** Symbole "&". */
    String ET = "&";

    /** Symbole "=". */
    String EGAL = "=";

    /** URL de la servlet d'upload de fichier. */
    String URL_SERVLET_UPLOAD_FICHIER = "servlet/uploadFichierEnvoiEmail";

    /** Paramètre du nom du fichier uploadé. */
    String PARAM_NOM_FICHIER = "nomFichier";

    /** Paramètre du type MIME du fichier. */
    String PARAM_TYPE_MIME = "typeMime";

    /** Paramètre du path du fichier temporaire. */
    String PARAM_PATH_FICHIER_TEMP = "pathFichierTemp";

    /** Encodage UTF8. */
    String ENCODAGE_UTF_8 = "UTF-8";

    /** Timeout des messages de notification de l'application. */
    float NOTIFICATION_TIME_OUT = 1.5f;

    /** Nombre de resultats max pour les suggestlisbox. */
    int SUGGESTLISBBOX_NB_RESULTATS_MAX = 15;

    /**Constantes de selection d'un message d'erreur.*/
	String ERREUR_UPLOAD_FICHIER_NOM_INCORRECT = "ERREUR_UPLOAD_FICHIER_NOM_INCORRECT";

    /**Constantes de selection d'un message d'erreur.*/
	String ERREUR_UPLOAD_FICHIER = "ERREUR_UPLOAD_FICHIER";

    /**Constantes de selection d'un message d'erreur.*/
	String ERREUR_ABSCENCE_PIECE_JOINTE = "ERREUR_ABSCENCE_PIECE_JOINTE";

    /**Constantes de selection d'un message d'erreur.*/
	String ERREUR_RECUPERATION_PIECE_JOINTE = "ERREUR_RECUPERATION_PIECE_JOINTE";


}

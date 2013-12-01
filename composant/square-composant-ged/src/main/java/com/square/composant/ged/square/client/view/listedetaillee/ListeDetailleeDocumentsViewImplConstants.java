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
package com.square.composant.ged.square.client.view.listedetaillee;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes pour la vue.
 * @author jgoncalves - SCUB
 */
public interface ListeDetailleeDocumentsViewImplConstants extends Constants {

    /**
     * Libellé d'attente pour le chargement de documents.
     * @return le libellé
     */
    String chargementDesDocumentsEnCours();

    /**
     * Dossier stockage des images des types mime.
     * @return le dossier
     */
    String dossierImagesTypesMime();

    /**
     * Label documents duclient.
     * @return le texte
     */
    String titreDocumentsClient();

    /**
     * Label documents duclient.
     * @return le texte
     */
    String titreDetailDocument();

    /**
     * .
     * @return le texte
     */
    String emplacements();
    /**
     * .
     * @return le texte
     */
    String titre();
    /**
     * .
     * @return le texte
     */
    String dateReception();
    /**
     * .
     * @return le texte
     */
    String taille();
    /**
     * .
     * @return le texte
     */
    String actions();
    /**
     * .
     * @return le texte
     */
    String telecharger();

    /**
     * .
     * @return le texte
     */
    String titreColonneNomDocument();

    /**
     * .
     * @return le texte
     */
    String titreColonneDateReception();

    /**
     * .
     * @return le texte
     */
    String titreColonneSens();

    /**
     * .
     * @return le texte
     */
    String titreColonneTag();

    /**
     * .
     * @return le texte
     */
    String titreBlocMoinsUnMois();

    /**
     * .
     * @return le texte
     */
    String titreBlocMoinsTroisMois();

    /**
     * .
     * @return le texte
     */
    String titreBlocPlusTroisMois();

    /**
     * Message d'erreur générique pour un RPC vers la GED.
     * @return le message
     */
    String messageErreurGeneriqueGed();
}

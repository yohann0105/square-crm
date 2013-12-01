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
package com.square.composant.ged.square.client.view.ajoutdocument;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes.
 * @author jgoncalves - SCUB
 */
public interface AjoutDocumentViewImplConstants extends Constants {
    /**
     * Titre de la popup.
     * @return le texte
     */
    String titrePopup();

    /**
     * Label bouton annuler.
     * @return le texte
     */
    String boutonAnnuler();

    /**
     * Label bouton ajouter.
     * @return le texte
     */
    String boutonAjouter();

    /**
     * Informations générales (fichier / date de numérisation, ...).
     * @return le texte
     */
    String informationsFichier();

    /**
     * Date de réception.
     * @return le texte
     */
    String labelDateReception();

    /**
     * Fichier.
     * @return le texte
     */
    String labelFichier();

    /**
     * .
     * @return le texte
     */
    String chargementTypesDocuments();

    /**
     * .
     * @return le texte
     */
    String erreurChampFichier();

    /**
     * .
     * @return le texte
     */
    String erreurChampDate();

    /**
     * .
     * @return le texte
     */
    String erreurChampType();

    /**
     * .
     * @return le texte
     */
    String messageErreurChampFichier();

    /**
     * .
     * @return le texte
     */
    String messageErreurChampDate();

    /**
     * .
     * @return le texte
     */
    String messageErreurChampType();

    /**
     * .
     * @return le texte
     */
    String uploadEnCours();

    /**
     * .
     * @return le texte
     */
    String sauvegardeEnCours();
}

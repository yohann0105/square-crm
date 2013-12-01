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
package com.square.composant.ged.square.client.view.associationdocumentsaction;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes.
 * @author jgoncalves - SCUB
 */
public interface AssociationDocumentsActionViewImplConstants extends Constants {
    /**
     * Libellé d'attente pour le chargement de documents.
     * @return le libellé
     */
    String chargementDesDocumentsEnCours();

    /**
     * .
     * @return le libellé
     */
    String enregistrementEnCours();

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
     * Label documents duclient.
     * @return le texte
     */
    String titreDocumentsClient();

    /**
     * Label documents de l'action.
     * @return le texte
     */
    String titreDocumentsAction();

    /**
     * Label bouton ajouter.
     * @return le texte
     */
    String boutonAjouter();

    /**
     * Label bouton en.lever.
     * @return le texte
     */
    String boutonEnlever();

    /**
     * Label bouton ajouter document.
     * @return le texte
     */
    String boutonAjouterDocument();

    /**
     * Label bouton enregistrer.
     * @return le texte
     */
    String boutonEnregistrer();
}

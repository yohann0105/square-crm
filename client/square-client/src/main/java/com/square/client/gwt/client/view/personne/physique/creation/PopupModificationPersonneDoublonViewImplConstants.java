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
package com.square.client.gwt.client.view.personne.physique.creation;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes de la vue {@link PopupModificationPersonneDoublonViewImpl}.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public interface PopupModificationPersonneDoublonViewImplConstants extends Constants {

    /**
     * Largeur de la popup.
     */
    String LARGEUR_POPUP = "500px";

    /**
     * Titre de la popup.
     * @return le texte associé
     */
    String titrePopup();

    /**
     * Label bouton enregistrer.
     * @return le texte associé
     */
    String labelBtnEnregistrer();

    /**
     * Label bouton enregistrer puis fusionner.
     * @return le texte associé
     */
    String labelBtnEnregistrerPuisFusionner();

    /**
     * Label bouton annuler.
     * @return le texte associé
     */
    String labelBtnAnnuler();

    /**
     * Message d'avertissement de la popup.
     * @return le texte associé
     */
    String msgAvertissement();

}

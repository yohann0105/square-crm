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
package com.square.composant.tarificateur.square.client.view.popup.ajout.assure;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes de la popup d'ajout d'assure.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface PopupAjoutAssureViewImplConstants extends Constants {

    /** Largeur popup. */
    String LARGEUR_POPUP = "650px";

    /**
     * Titre de la popup.
     * @return le titre.
     */
    String titrePopup();

    /**
     * Texte sur le bouton de validation.
     * @return le texte.
     */
    String btnValider();

    /**
     * Texte sur le bouton d'annulation.
     * @return le texte.
     */
    String btnAnnuler();

    /**
     * Libelle.
     * @return le texte.
     */
    String civilite();

    /**
     * Libelle.
     * @return le texte.
     */
    String nom();

    /**
     * Libelle.
     * @return le texte.
     */
    String prenom();

    /**
     * Libelle.
     * @return le texte.
     */
    String dateNaissance();

    /**
     * Libelle.
     * @return le texte.
     */
    String numSS();

    /**
     * Libelle.
     * @return le texte.
     */
    String cleSS();

    /**
     * Libelle.
     * @return le texte.
     */
    String regime();

    /**
     * Libelle.
     * @return le texte.
     */
    String caisse();

    /**
     * Libelle.
     * @return le texte.
     */
    String creationAssureSocial();

    /**
     * Message indiquant la présence de doublons.
     * @return le message.
     */
    String warningDoublons();
}

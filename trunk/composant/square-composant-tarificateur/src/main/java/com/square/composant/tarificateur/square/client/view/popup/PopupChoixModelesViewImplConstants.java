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
package com.square.composant.tarificateur.square.client.view.popup;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes de la popup d'impression d'un devis.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface PopupChoixModelesViewImplConstants extends Constants {

    /** Largeur popup. */
    String LARGEUR_POPUP = "400px";

    /**
     * Titre de la popup de transfert.
     * @return le titre.
     */
    String titrePopupImpression();

    /**
     * Titre de la popup de transfert.
     * @return le titre.
     */
    String titrePopupEmail();

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
     * Label pour la sélection des modeles.
     * @return le label.
     */
    String selectionModeles();

    /**
     * Label pour la sélection des bénéficiaires.
     * @return le label.
     */
    String selectionBeneficiaires();
}

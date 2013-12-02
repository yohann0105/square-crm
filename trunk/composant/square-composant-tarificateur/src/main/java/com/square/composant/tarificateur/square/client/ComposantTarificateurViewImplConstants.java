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
package com.square.composant.tarificateur.square.client;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constants de la vue ComposantTarificateurViewImpl.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
interface ComposantTarificateurViewImplConstants extends Constants {
    /**
     * Libelle du bouton pour ajouter un devis.
     * @return le label
     */
    String ajouterDevis();

    /**
     * Libelle du bouton pour voir les actions.
     * @return le label
     */
    String voirActions();

    /**
     * Libellé du bouton pour supprimer l'opportunité.
     * @return le libellé.
     */
    String supprimerOpportunite();

    /**
     * Message.
     * @return le message
     */
    String chargementOpportunite();

    /**
     * Message.
     * @return le message
     */
    String aucunDevis();

    /**
     * Libellé.
     * @return le libellé.
     */
    String captionDates();

    /**
     * Libellé.
     * @return le libellé.
     */
    String captionInformations();

    /**
     * Libellé.
     * @return le libellé.
     */
    String labelDateTransformation();

    /**
     * Libellé.
     * @return le libellé.
     */
    String labelDateCloture();

    /**
     * Libellé.
     * @return le libellé.
     */
    String labelDateSignature();

    /**
     * Libellé.
     * @return le libellé.
     */
    String labelDateEditionBA();

    /**
     * Libellé.
     * @return le libellé.
     */
    String labelNature();

    /**
     * Message lors de la transformation en devis AIA.
     * @return le message
     */
    String transformationDevisAIA();

    /**
     * Le texte.
     * @return le texte
     */
    String titreListeErreursBloquantes();

    /**
     * Le texte.
     * @return le texte
     */
    String titreListeErreursNonBloquantes();

    /**
     * Le texte.
     * @return le texte
     */
    String messagePopupSuccesTransformation();

}

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
package com.square.composant.envoi.email.square.client.view;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes de l'implémentation de la vue du composant d'envoi d'email.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public interface ComposantEnvoiEmailViewImplConstants extends Constants {

    /** Largeur de la deuxieme colonne. */
    String LARGEUR_COLONNE_VIDE = "50px";

    /** Hauteur de la rich tect area. */
    String HAUTEUR_RICH_TEXT_AREA = "150px";

    /**
     * Titre du bloc du composant.
     * @return le titre.
     */
    String titreBlocComposant();

    /**
     * Texte sur le bouton pour envoyer un email.
     * @return le texte.
     */
    String btnEnvoyerEmail();

    /**
     * Texte sur le bouton pour joindre un fichier.
     * @return le texte.
     */
    String btnJoindreFichier();

    /**
     * Texte sur le bouton pour annuler.
     * @return le texte.
     */
    String btnAnnuler();

    /**
     * Label pour l'adresse mail de l'expéditeur.
     * @return le label.
     */
    String expediteur();

    /**
     * Label pour le nom de l'expéditeur.
     * @return le label.
     */
    String nomExpediteur();

    /**
     * Label pour le destinataire de l'email.
     * @return le label.
     */
    String destinataire();

    /**
     * Label pour l'objet de l'email.
     * @return le label.
     */
    String objet();

    /**
     * Label pour le modele de l'email.
     * @return le label.
     */
    String modeleEmail();

    /**
     * Label pour la pièce jointe.
     * @return le label.
     */
    String pieceJointe();

    /**
     * Label pour le message de l'email.
     * @return le message.
     */
    String message();

    /**
     * Label pour indiquer qu'il n'y a aucun fichier joint.
     * @return le label.
     */
    String aucunFichierJoint();

    /**
     * Texte sur le bouton pour supprimer une pièce jointe.
     * @return le texte.
     */
    String btnSupprimer();

    /**
     * Titre de la popup de confirmation de suppression d'une pièce jointe.
     * @return le titre.
     */
    String titrePopupConfirmationSuppressionPieceJointe();

    /**
     * Message indiquant qu'une erreur s'est produite lors de la suppression de la pièce jointe.
     * @return le message.
     */
    String erreurSuppresionPieceJointe();

    /**
     * Label.
     * @return le label
     */
    String reduire();
}

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
package com.square.client.gwt.client.view.action.creation;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des messages.
 * @author cblanchard - SCUB
 */
public interface ActionCreationViewImplConstants extends Constants {

    /** Largeur. */
    String LARGEUR_POPUP = "650px";

    /** Largeur colonne. */
    String LARGEUR_COL_LIBELLE = "20%";

    /** Largeur de la SuggestListBox de la durée. */
    String LARGEUR_SLB_DUREE = "40px";

    /**
     * Titre de la popup.
     * @return le titre de la popup
     */
    String popupTitle();

    /**
     * Titre du caption panel pour la planification.
     * @return planification
     */
    String libellePanelPlanification();

    /**
     * Titre du panel pour les affectations.
     * @return affectation
     */
    String libellePanelAffectation();

    /**
     * Titre du panel pour les actions.
     * @return titre du panel action
     */
    String libellePanelAction();

    /**
     * Titre du panel pour les campagnes.
     * @return titre du panel campagne
     */
    String libellePanelCampagne();

    /**
     * Titre du panel pour les notifications.
     * @return titre du panel notification
     */
    String libellePanelNotification();

    /**
     * Label A faire.
     * @return label a faire
     */
    String labelAFaire();

    /**
     * Label Début.
     * @return label début
     */
    String labelDebut();

    /**
     * Label pour la durée.
     * @return le label.
     */
    String labelDuree();

    /**
     * Libelle du bouton creer.
     * @return le libelle du bouton creer
     */
    String creer();

    /**
     * Libelle du bouton annuler.
     * @return le libelle du bouton annuler
     */
    String annuler();

    /**
     * Libelle personne.
     * @return le libelle personne
     */
    String libellePersonne();

    /**
     * Libelle reclamation.
     * @return le libelle reclamation
     */
    String reclamation();

    /**
     * Libelle pour la nature.
     * @return le libelle pour la nature
     */
    String nature();

    /**
     * Libelle pour le type.
     * @return le libelle pour le type
     */
    String type();

    /**
     * Libelle Objet.
     * @return le libelle pour l'objet
     */
    String objet();

    /**
     * Libelle priorite.
     * @return le libelle pour la priorite
     */
    String priorite();

    /**
     * Libelle sous objet.
     * @return le libelle sous objet
     */
    String sousObjet();

    /**
     * Libelle campagne.
     * @return le libelle campagne
     */
    String campagne();

    /**
     * Libelle du rappel.
     * @return le libelle du rappel
     */
    String rappeler();

    /**
     * Libelle avant.
     * @return le libelle avant
     */
    String avant();

    /**
     * Libelle du rappel mail.
     * @return le libelle du rappel mail
     */
    String rappelMail();

    /**
     * Accesseur Label.
     * @return le label.
     */
    String labelAttributionAgence();

    /**
     * Accesseur Label.
     * @return le label.
     */
    String labelAttributionRessourceAgence();

    /**
     * hauteur de panel.
     * @return l'hauteur
     */
    String panelHeight();

    /**
     * Message de notification pour confirmer que l'action a été créée.
     * @return le texte associé
     */
    String nofificationActionCreee();

    /**
     * Label.
     * @return le label
     */
    String reduire();

    /**
     * Libelle pour la case à cocher servant à fermer l'action d'origine.
     * @return le libelle
     */
    String fermerActionPrecedente();

    /**
     * Label pour ajouter une action dans l'agenda.
     * @return le label.
     */
    String ajouterAgenda();
}

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
package com.square.client.gwt.client.view.personne.action;

import com.google.gwt.i18n.client.Constants;

/**
 * Interfaces des constantes de la vue sur le contenu d'une action.
 * @author cblanchard - SCUB
 */
public interface PersonneActionContenuViewImplConstants extends Constants {

    /**
     * Renvoi le titre du caption notification.
     * @return le titre notification
     */
    String titreNotification();

    /**
     * Renvoi le titre du bloc Action.
     * @return le titre action
     */
    String titreAction();

    /**
     * Renvoi le titre du bloc GEd.
     * @return le titre action
     */
    String titreGed();

    /**
     * Renvoi le titre Affectation.
     * @return le titre Affectation
     */
    String titreCaptionAffectation();

    /**
     * Renvoi le titre Campagne.
     * @return le titre Campagne
     */
    String titreCaptionCampagne();

    /**
     * Renvoi le libelle du créateur.
     * @return le libelle créateur
     */
    String libelleCreateur();

    /**
     * Renvoi le libelle agence.
     * @return le libelle agence
     */
    String libelleAgence();

    /**
     * Renvoi le libelle de la date de création.
     * @return le libelle de la date de création
     */
    String libelleDateCreation();

    /**
     * Renvoi le libelle de la campagne.
     * @return le libelle de la campagne
     */
    String libelleCampagne();

    /**
     * Renvoi le libelle du rappel mail.
     * @return le libelle du rappel mail
     */
    String rappelMail();

    /**
     * Renvoi le libelle de rappel.
     * @return le libelle de rappel
     */
    String libelleRappel();

    /**
     * Renvoi le libelle de rappel avant.
     * @return le libelle de rappel avant
     */
    String libelleRappelAvant();

    /**
     * Renvoi le libelle de nature action.
     * @return le libelle de nature action
     */
    String libelleNatureAction();

    /**
     * Renvoi le libelle des résultats.
     * @return le libelle des résultats
     */
    String libelleResultat();

    /**
     * Renvoi le libelle des notes.
     * @return le libelle notes
     */
    String notes();

    /**
     * Renvoi le libelle de l'opportunité.
     * @return le libelle opportunité
     */
    String libelleOpportunite();

    /**
     * Renvoi le libelle du statut.
     * @return le libelle du statut
     */
    String libelleStatut();

    /**
     * Renvoi le libelle de l'état de l'appel.
     * @return le libelle de l'état de l'appel
     */
    String libelleEtatAppel();

    /**
     * Renvoi le libelle de l'attribution à une ressource.
     * @return le libelle de l'attribution à une ressource.
     */
    String libelleAttributionRessourceAgence();

    /**.
     * Renvoi le libelle de l'état de la visite
     * @return le libelle de l'état de la visite
     */
    String libelleEtatVisite();

    /**
     * Renvoi le libellé d'ajout à l'agenda.
     * @return le libellé d'ajout à l'agenda.
     */
    String ajoutAgenda();

}

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
package com.square.client.gwt.client.view.ressource.gestion;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface pour les messages de la vue de la gestion de campagne.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public interface GestionRessourceViewImplConstants extends Constants {

    /**
     * Renvoi le libelle nom.
     * @return le libelle nom
     */
    String nom();

    /**
     * Renvoi le libelle prenom.
     * @return le libelle prenom
     */
    String prenom();

    /**
     * Renvoi le libelle etat.
     * @return le libelle etat
     */
    String etat();

    /**
     * Renvoi le libelle fonction.
     * @return le libelle fonction
     */
    String fonction();

    /**
     * Renvoi le libelle service.
     * @return le libelle service
     */
    String service();

    /**
     * Renvoi le libelle agence.
     * @return le libelle agence
     */
    String agence();

    /**
     * Renvoi le libelle agence.
     * @return le libelle agence
     */
    String ressource();

    /**
     * Le libelle du bouton annuler.
     * @return le libelle du bouton annuler
     */
    String annuler();

    /**
     * Le libelle du bouton enregistrer.
     * @return le libelle du bouton enregistrer
     */
    String enregistrer();

    /**
     * Le libelle pour la popUp de chargement pdt l'enregistrement.
     * @return le libelle pour l'enregistrement
     */
    String enregistrementDonnees();

    /**
     * Le libelle pour le caption information.
     * @return le libelle pour le caption information
     */
    String informations();

}

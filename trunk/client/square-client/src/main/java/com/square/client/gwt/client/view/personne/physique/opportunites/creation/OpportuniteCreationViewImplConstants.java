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
package com.square.client.gwt.client.view.personne.physique.opportunites.creation;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constantes de la popup de création d'une opportunite.
 * @author cblanchard - SCUB
 */
public interface OpportuniteCreationViewImplConstants extends Constants {

    /** Largeur. */
    String LARGEUR_POPUP = "450px";

    /** Largeur. */
    String WIDTH_COL1 = "38%";

    /** Largeur. */
    String WIDTH_COL2 = "62%";

    /**
     * Renvoi le titre de la popup de création.
     * @return le titre de la popup de création
     */
    String titrePopup();

    /**
     * Renvoi le titre du bloc action origine.
     * @return Renvoi le titre du bloc action origine
     */
    String titreActionOrigine();

    /**
     * Renvoi le libelle nature.
     * @return le libelle nature
     */
    String nature();

    /**
     * Renvoi le libelle de la campagne.
     * @return le libelle de la campagne
     */
    String campagne();

    /**
     * Renvoi le titre opportunité.
     * @return le titre opportunité
     */
    String titreOpportunite();

    /**
     * Renvoi le libelle attribué à.
     * @return le libelle attribué à
     */
    String titreRessource();

    /**
     * Renvoi le libelle agenge.
     * @return le libelle agenge
     */
    String titreAgence();

    /**
     * Renvoi le libelle personne.
     * @return le libelle personne
     */
    String titrePersonne();

    /**
     * Renvoi le libelle type.
     * @return le libelle type
     */
    String titreType();

    /**
     * Renvoi le libelle objet.
     * @return le libelle objet
     */
    String titreObjet();

    /**
     * Renvoi le libelle sous objet.
     * @return le libelle sous objet
     */
    String titreSousObjet();

    /**
     * Renvoi le libelle annuler.
     * @return le libelle annuler
     */
    String libelleAnnuler();

    /**
     * Renvoi le libelle enregistrer.
     * @return le libelle enregistrer
     */
    String libelleEnregistrer();

    /**
     * Renvoi le libelle.
     * @return le libelle
     */
    String reduire();

}

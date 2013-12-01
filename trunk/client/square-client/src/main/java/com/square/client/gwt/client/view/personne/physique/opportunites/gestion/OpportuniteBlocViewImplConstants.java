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
package com.square.client.gwt.client.view.personne.physique.opportunites.gestion;

import com.google.gwt.i18n.client.Constants;

/**
 * Interface des constantes de la vue d'une opportunité.
 * @author cblanchard - SCUB
 */
public interface OpportuniteBlocViewImplConstants extends Constants {

    /**
     * Renvoi le libelle type.
     * @return le libelle type
     */
    String type();

    /**
     * Renvoi le libelle objet.
     * @return le libelle objet
     */
    String objet();

    /**
     * Renvoi le libelle du sous objet.
     * @return le libelle du sous objet
     */
    String sousObjet();

    /**
     * Renvoi le libelle du statut.
     * @return le libelle du statut
     */
    String statut();

    /**
     * Renvoi le libelle de la ressource.
     * @return le libelle de la ressource
     */
    String ressource();

    /**
     * Renvoi le libelle de la date de création.
     * @return le libelle de la date de création
     */
    String dateCreation();

    /**
     * Renvoi le libelle de l'agence.
     * @return le libelle de l'agence
     */
    String agence();

    /**
     * Renvoi le libelle du créateur.
     * @return le libelle du créateur
     */
    String createur();

    /**
     * Renvoi le libelle du titre de l'opportunité.
     * @return le libelle du titre opportunité
     */
    String titreOpportunite();

}

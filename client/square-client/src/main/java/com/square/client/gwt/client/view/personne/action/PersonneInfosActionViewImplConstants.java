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
 * Interface des constantes.
 * @author cblanchard - SCUB
 */
public interface PersonneInfosActionViewImplConstants extends Constants {

    /** Largeur de la SuggestListBox de la durée. */
    String LARGEUR_SLB_DUREE = "40px";

    /**
     * Renvoi le type.
     * @return le type
     */
    String libelleType();

    /**
     * Renvoi le libelle Objet.
     * @return le libelle Objet
     */
    String libelleObjet();

    /**
     * Renvoi le libelle sous objet.
     * @return le libelle sous objet
     */
    String libelleSousObjet();

    /**
     * Renvoi le libelle niveau.
     * @return le libelle niveau
     */
    String libelleActionNiveau();

    /**
     * Renvoi le libelle de la date.
     * @return le libelle de la date
     */
    String libelleDate();

    /**
     * Renvoi le libelle des ressources.
     * @return le libelle ressource
     */
    String libelleRessource();

    /**
     * Renvoi le libelle priorité.
     * @return le libelle priorite
     */
    String libellePriorite();

    /**
     * Renvoi le libelle A.
     * @return le libelle A
     */
    String libelleA();

    /**
     * Renvoi le libelle De.
     * @return le libelle De
     */
    String libelleDe();

    /**
     * Renvoi le label du bouton ajouter action liée.
     * @return le label du bouton ajouter action liée
     */
    String boutonAjouterActionLiee();

    /**
     * Renvoi le label du bouton enregistrer.
     * @return le label du bouton enregistrer
     */
    String boutonEnregistrer();

    /**
     * Accesseur libellé.
     * @return le texte associé
     */
    String libelleFaitLe();

    /**
     * Accesseur libellé.
     * @return le texte associé
     */
    String libelleAFaire();

    /**
     * Récupère le libellé de la nature du contact.
     * @return le libellé.
     */
    String libelleNatureContact();

    /**
     * Récupère le libellé de la campagne.
     * @return le libellé.
     */
    String libelleCampagne();

    /**
     * Récupère le libellé de la durée.
     * @return le libellé.
     */
    String libelleDuree();
}

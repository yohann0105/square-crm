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
package com.square.client.gwt.client.presenter.personne.physique;

import com.google.gwt.i18n.client.Constants;

/**
 * Constantes spécifiques à la gestion de personnes physiques.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public interface GestionPersonnePhysiquePresenterConstants extends Constants {

    /**
     * Message de notification affiché lorsqu'une personne est mise à jour.
     * @return le texte associé.
     */
    String notificationPersonneMaj();

    /**
     * Chemin du champ de la date de naissance de la personne.
     * @return le chemin
     */
    String champDateNaissancePersonne();

    /**
     * Message d'erreur sur la date de naissance de la personne.
     * @return le message
     */
    String erreurDateNaissancePersonneInvalide();

    /**
     * Libellé pour l'age.
     * @return le libellé.
     */
    String libelleAge();

    /**.
     * Retourne le nombre maximum de caractères pour l'affichage du nom
     * @return la valeur
     */
    int maxCaracteresNom();

    /**.
     * Retourne le nombre maximum de caractères pour l'affichage du preénom
     * @return la valleur
     */
    int maxCaracteresPrenom();

    /**
     * Debut du message indiquant que la nature de la personne a changé.
     * @return le message.
     */
    String debutNotificiationNaturePersonneModifiee();

    /**
     * Fin du message indiquant que la nature de la personne a changé.
     * @return le message.
     */
    String finNotificiationNaturePersonneModifiee();

    /**
     * i18n : chaine ans.
     * @return le message
     */
	String ans();
}

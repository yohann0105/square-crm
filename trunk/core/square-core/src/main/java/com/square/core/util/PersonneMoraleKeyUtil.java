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
package com.square.core.util;

/**
 * Fichier qui regrouppe les clefs des messages pour les personnes morale.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class PersonneMoraleKeyUtil {

    /**
     * Constructeur privé de la classe.
     */
    private PersonneMoraleKeyUtil() {
    }

    /**
     * Message d'erreur si une personne morale est nulle.
     */
    public static final String MESSAGE_ERREUR_PERSONNE_MORALE_NULL = "message_erreur_personne_morale_null";

    /**
     * Message d'erreur lorsqu'on tente de rechercher une personne morale en indiquant des critères de recherche invalide.
     */
    public static final String ERREUR_CRITERES_RECHERCHE_INVALIDE = "message_erreur_personne_morale_criteres_recherche_invalide";

    /** Message d'erreur. */
    public static final String MESSAGE_ERROR_SAVE_PERSONNE_MORALE_RAISON_SOCIALE_VIDE = "message_error_save_personne_morale_raison_sociale_vide";
}

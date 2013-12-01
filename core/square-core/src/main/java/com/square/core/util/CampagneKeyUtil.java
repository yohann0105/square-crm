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
 * Gestion des messages pour les campagnes.
 * @author cblanchard - SCUB
 */
public class CampagneKeyUtil {

    /** Message d'erreur lorsqu'un libelle est null. */
    public static final String MESSAGE_ERREUR_LIBELLE_CAMPAGNE_NULL = "message_erreur_libelle_campagne_null";

    /** Message d'erreur si le libelle de la campagne existe déjà. */
    public static final String MESSAGE_ERREUR_LIBELLE_CAMPAGNE_EXITANT = "message_erreur_libelle_campagne_exitant";

    /** Message d'erreur lorsque le statut est null. */
    public static final String MESSAGE_ERREUR_STATUT_CAMPAGNE_NULL = "message_erreur_statut_campagne_null";

    /** Message d'erreur lorsque le statut n'existe pas en base. */
    public static final String MESSAGE_ERREUR_STATUT_CAMPAGNE_INEXISTANT = "message_erreur_statut_campagne_inexistant";

    /** Message d'erreur pour le type de campagne est null. */
    public static final String MESSAGE_ERREUR_TYPE_CAMPAGNE_NULL = "message_erreur_type_campagne_null";

    /** Message d'erreur pour un type déjà existant. */
    public static final String MESSAGE_ERREUR_TYPE_CAMPAGNE_INEXISTANT = "message_erreur_type_campagne_inexistant";

    /** Message d'erreur pour une date de début nulle. */
    public static final String MESSAGE_ERREUR_DATE_DEBUT_CAMPAGNE_NULL = "message_erreur_date_debut_campagne_null";

    /** Message d'erreur pour une date de fin nulle. */
    public static final String MESSAGE_ERREUR_DATE_FIN_CAMPAGNE_NULL = "message_erreur_date_fin_campagne_null";

    /** Message d'erreur lorsque le dto de critères est null. */
    public static final String MESSAGE_ERROR_CAMPAGNE_SEARCH_DTO_NULL = "message_error_campagne_search_dto_null";

    /** Message d'erreur lorsque les critères sont null. */
    public static final String MESSAGE_ERREUR_CAMPAGNE_DTO_RECHERCHE_NULL = "message_erreur_campagne_dto_recherche_null";

    /** Message d'erreur lorsque l'identifiant de recherche est null. */
    public static final String MESSAGE_ERREUR_CAMPAGNE_ID_NULL = "message_erreur_campagne_id_null";

    /** Message d'erreur lorsque l'identifiant est inexistant. */
    public static final String MESSAGE_ERREUR_CAMPAGNE_ID_INEXISTANT = "message_erreur_campagne_id_inexistant";

    /** Message d'erreur lorsque la date de fin est inférieur à la date de début. */
    public static final String MESSAGE_ERREUR_CAMPAGNE_DATE_FIN_INFERIEURE_DATE_DEBUT = "message_erreur_campagne_date_fin_inferieure_date_debut";

    /** Message d'erreur lorsque la date de début est inférieure à la date du jour. */
    public static final String MESSAGE_ERREUR_CAMPAGNE_DATE_DEBUT_INFERIEURE_DATE_JOUR = "message_erreur_campagne_date_debut_inferieure_date_jour";

    /** Message d'erreur lorsque la date de fin est inférieur à la date du jour. */
    public static final String MESSAGE_ERREUR_CAMPAGNE_DATE_FIN_INFERIEURE_DATE_JOUR = "message_erreur_campagne_date_fin_inferieure_date_jour";

}

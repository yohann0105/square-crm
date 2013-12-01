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
 * Gestion des message pour le service de mapping.
 * @author cblanchard - SCUB
 */
public class SquareMappingKeyUtil {

    /** Message d'erreur lorsqu'une date est nulle. */
    public static final String MESSAGE_ERREUR_DATE_NULL = "message_erreur_date_null";

    /** Message d'erreur lorsque la date d'action est inférieur à la date de notification. */
    public static final String MESSAGE_ERREUR_DATE_ACTION_INF_DATE_NOTIFICATION = "message_erreur_date_action_inf_date_notification";

    /** Message d'erreur lors de la décomposition du numéro de sécurité sociale. */
    public static final String MESSAGE_ERREUR_DECOMPOSER_NUMERO_SECURITE_SOCIALE = "message.erreur.decomposer.numero.securite.sociale";

}

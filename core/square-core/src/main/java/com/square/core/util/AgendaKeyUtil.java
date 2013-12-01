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
 * Gestion des messages d'erreur pour les agendas.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class AgendaKeyUtil {

    /**
     * Constructeur par defaut.
     */
    private AgendaKeyUtil() {
    }

    /** Message d'erreur. */
    public static final String MESSAGE_ERREUR_AGENDA_CRITERES_NULL = "message_erreur_agenda_criteres_null";

    /** Message d'erreur. */
    public static final String MESSAGE_ERREUR_AGENDA_RESSOURCE_NULL = "message_erreur_agenda_ressource_null";

    /** Message d'erreur. */
    public static final String MESSAGE_ERREUR_AGENDA_RESSOURCE_INEXISTANTE = "message_erreur_agenda_ressource_inexistante";

    /** Message d'erreur. */
    public static final String MESSAGE_ERREUR_AGENDA_DATE_MIN_NULL = "message_erreur_agenda_date_min_null";

    /** Message d'erreur. */
    public static final String MESSAGE_ERREUR_AGENDA_DATE_MAX_NULL = "message_erreur_agenda_date_max_null";

    /** Message d'erreur. */
    public static final String MESSAGE_ERREUR_AGENDA_DATE_MIN_SUP_DATE_MAX = "message_erreur_agenda_date_min_sup_date_max";

    /** Message d'erreur. */
    public static final String MESSAGE_ERREUR_AGENDA_RECUPERATION_AGENDA_TIERS = "message_erreur_agenda_recuperation_agenda_tiers";

    /** Message d'erreur. */
    public static final String MESSAGE_ERREUR_AGENDA_ANIMATEUR_SANS_AGENCE = "message_erreur_agenda_animateur_sans_agence";
    
    /** Message d'erreur. */
    public static final String MESSAGE_ERREUR_AGENDA_AGENCE_SANS_REGION = "message_erreur_agenda_agence_sans_region";

    /** Message d'erreur. */
    public static final String MESSAGE_ERREUR_AGENDA_PAS_ANIMATEUR = "message_erreur_agenda_pas_animateur";
}

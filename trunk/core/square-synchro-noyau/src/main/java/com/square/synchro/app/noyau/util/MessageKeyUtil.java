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
package com.square.synchro.app.noyau.util;

/**
 * Constantes des messages.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public final class MessageKeyUtil {

    /** Constructeur privé. */
    private MessageKeyUtil() {
    }

    /** Message d'erreur pour un utilisateur inexistant. */
    public static final String UTILISATEUR_INEXISTANT = "error.utilisateur.inexistant";

    /** Message d'erreur pour un utilisateur inexistant. */
    public static final String ERREUR_ATTACHEMENT_DOCUMENT_UTILISATEUR_INEXISTANT = "erreur.attachement.document.utilisateur.inexistant";

    /** Message d'erreur pour un utilisateur inexistant. */
    public static final String ERREUR_NOTIFICATION_ERREUR_GED_ACTION_INEXISTANTE = "erreur.notification.erreur.ged.action.inexistante";

    /** Message d'erreur pour un utilisateur inexistant. */
    public static final String COMMENTAIRE_ACTION_ERREUR_ASSOCIATION_DOCUMENTS_GED = "commentaire.action.erreur.association.documents.ged";

    /** Message d'erreur pour un utilisateur inexistant. */
    public static final String ERREUR_EID_CREATEUR_ACTION_EDITION_NON_RENSEIGNE = "erreur.eid.createur.action.edition.non.renseigne";

    /** Message d'erreur pour un utilisateur inexistant. */
    public static final String ERREUR_RESSOURCE_CREATEUR_ACTION_EDITION_INEXISTANTE = "erreur.ressource.createur.action.edition.inexistante";

    /** Message d'erreur pour un utilisateur inexistant. */
    public static final String ERREUR_NOMBRE_DEVIS_DIFFERENT_DE_UN = "erreur.nombre.devis.different.de.un";

    /** Message.*/
	public static final String LOGGER_DEBUG_MESSAGE_TRAITEMENT_ENCOUR = "logger.debug.message.traitement.encour";

    /** Message.*/
	public static final String LOGGER_DEBUG_RECEPTION_MESSAGE = "logger.debug.reception.message";

    /** Message.*/
	public static final String LOGGER_DEBUG_ENVOIE_DMQ = "logger.debug.envoie.dmq";

    /** Message.*/
	public static final String LOGGER_RECHERCHE_CORRESPONDANCE_USER_HABILITATIONS = "logger.recherche.correspondance.user.habilitations";

    /** Message.*/
	public static final String LOGGER_ERROR_NOMBRE_DEVIS_RECUPERER_INCORRECT = "logger.error.nombre.devis.recuperer.incorrect";

    /** Message.*/
	public static final String LOGGER_DEBUG_CREATION_ACTION_EDITION_DOC = "logger.debug.creation.action.edition.doc";

    /** Message.*/
	public static final String LOGGER_DEBUG_CREATION_ACTION_ENVOIE_DOC = "logger.debug.creation.action.envoie.doc";

    /** Message.*/
	public static final String LOGGER_WARN_AUCUNE_ACTION_OPPORTUNITE = "logger.warn.aucune.action.opportunite";

    /** Message.*/
	public static final String LOGGER_DEBUG_ERREUR_AJOUT_DOCUMENT_ACTION = "logger.debug.erreur.ajout.document.action";

    /** Message.*/
	public static final String LOGGER_ERROR_ACTION_INEXISTANTE = "logger.error.action.inexistante";

    /** Message.*/
	public static final String LOGGER_ERROR_IDENTIFIANT_RESSOURCE_NULL = "logger.error.identifiant.ressource.null";

    /** Message.*/
	public static final String LOGGER_ERROR_RESSOURCE_CREATEUR_NULL = "logger.error.ressource.createur.null";

    /** Message.*/
	public static final String LOGGER_DEBUG_ENVOIE_MESSAGE = "logger.debug.envoie.message";

    /** Message.*/
	public static final String LOGGER_ERROR_INFO_MODE_GED_MOCK = "logger.error.info.mode.ged.mock";
}

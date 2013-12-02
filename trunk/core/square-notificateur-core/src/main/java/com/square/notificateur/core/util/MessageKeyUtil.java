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
package com.square.notificateur.core.util;

/**
 * Classe de gestion des messages.
 * */
public final class MessageKeyUtil {

	/**
	 * Constructeur.
	 * */
	private MessageKeyUtil() {

	}

	/**
	 * Message pour erreur lors de la sauvegarde de MessageService.
	 * */
	public static final String MESSAGE_ERROR_SAVE_MESSAGE_DTO_REQUIRED = "message.error.save.message.dto.required";

	/**
	 * Oubli du UserID de destinataire.
	 * */
	public static final String MESSAGE_ERROR_SAVE_MESSAGE_USERID_REQUIRED = "message.error.save.message.userid.required";

	/**
	 * Oubli du texte contenu de message.
	 * */
	public static final String MESSAGE_ERROR_SAVE_MESSAGE_TEXT_REQUIRED = "message.error.save.message.text.required";
	/**
	 * Pas de message trouvé.
	 * */
	public static final String MESSAGE_ERROR_GET_MESSAGE_NO_MESSAGE_FOUND = "message.error.save.message.no.message.found";

	/**
	 * Message à mettre à jour non trouvé.
	 * */
	public static final String MESSAGE_ERROR_SAVE_MESSAGE_MESSAGE_TO_UPDATE_NOT_FOUND = "message.error.save.message.message.to.update.not.found";
	/**
	 * Sauvegarde ok pour la création.
	 * */
	public static final String MESSAGE_INFO_SAVE_MESSAGE_CREATED = "message.info.save.message.created";

	/**
	 * Sauvegarde ok pour la mise à jour.
	 * */
	public static final String MESSAGE_INFO_SAVE_MESSAGE_UPDATED = "message.info.save.message.updated";
	/**
	 * Accusé en réception ok.
	 * */
	public static final String MESSAGE_INFO_RECEPTION_MESSAGE_ACQUITED = "message.info.reception.accuse";
	/**
	 * Oubli de userId en recherche.
	 * */
	public static final String MESSAGE_ERROR_SEARCH_MESSAGE_USERID_REQUIRED = "message.error.recherche.message.userid.required";
	/**
	 * Oubli de idMessage en accusé de recéption.
	 * */
	public static final String MESSAGE_ERROR_RECEPTION_MESSAGE_ID_REQUIRED = "message.error.reception.message.userid.required";
	/**
	 * faux id entré en recherche.
	 */
	public static final String MESSAGE_ERROR_RECEPTION_MESSAGE_MESSAGE_TO_ACQUIT_NOT_FOUND = "message.error.reception.message.message.to.acquit.not.found";
	/**
	 * oubli de critéres de recherches.
	 */
	public static final String MESSAGE_SEARCH_CRITERIA_REQUIRED = "message.error.recherche.message.criteria.required";
	/**
	 * Acquittement de are you alive message.
	 */
	public static final String MESSAGE_ERROR_RECEPTION_MESSAGE_MESSAGE_AREYOUALIVE_AQUIT = "message.error.reception.message.message.aya.acquit";
	/**
	 * DTO null lors de la recherches par criteres.
	 */
	public static final String MESSAGE_ERROR_SEARCH_MESSAGE_DTO_REQUIRED = "message.error.search.message.dto.required";
	/**
	 * Message are you alive.
	 */
	public static final String MESSAGE_ARE_YOU_ALIVE = "are.you.alive";
}
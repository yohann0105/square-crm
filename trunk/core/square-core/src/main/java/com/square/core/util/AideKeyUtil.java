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
 * classe de gestion de messages.
 * @author mohamedAli - SCUB
 *
 */
public final class AideKeyUtil {
	/**
	 * Constructeur.
	 * */
	private AideKeyUtil() {

	}
	/**
	 * Message pour erreur lors de la sauvegarde d'un aide avec dto null.
	 * */
	public static final String MESSAGE_ERROR_SAVE_AIDE_DTO_REQUIRED = "message.erreur.save.aide.dto.required";
	/**
	 * Oubli du texte contenu de l'aide.
	 * */
	public static final String MESSAGE_ERROR_SAVE_AIDE_IDCOMPOSANT_REQUIRED = "message.erreur.save.aide.idComposant.required";

	/**
	 * Oubli de IdComposant en recherche.
	 * */
	public static final String MESSAGE_ERROR_SEARCH_AIDE_IDCOMPOSANT_REQUIRED = "message.erreur.recherche.aide.idComposant.required";
	/**
     * Oubli de aideId en recherche.
     * */
    public static final String MESSAGE_ERROR_SEARCH_AIDE_ID_REQUIRED = "message.erreur.recherche.aide.id.required";
	/**
	 * Message à mettre à jour non trouvé.
	 * */
	public static final String MESSAGE_ERROR_SAVE_AIDE_TO_UPDATE_NOT_FOUND = "message.erreur.save.aide.to.update.not.found";
}

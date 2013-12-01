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
package com.square.notificateur.core.service.interfaces;


import java.util.List;

import com.square.notificateur.core.dto.MessageCriteresDto;
import com.square.notificateur.core.dto.MessageDto;

/**
 * Services permettant de publier les messages.
 * @author Ksouri Mohamed Ali - SCUB
 */
public interface MessageService {

	/**
	 * Sauvegarde un Message.
	 * @param message le message à sauvegarder
	 * @return le message sauvegardé
	 */
	MessageDto creerMessage(MessageDto message);
	/**
	 * Accuse en Réception le message correspondant à l'identifiant passé en paramètre.
	 * @param idMessage identifiant du message
	 * .
	 */
	void acquitterMessage(Long idMessage);
	/**
	 * Service de recherche de message par critères.
	 * @param criteres les critères pour la recherche du message
	 * @return la liste des messages qui correspondent aux critères passés en paramètre.
	 */
	List < MessageDto > rechercherMessagesParCriteres(MessageCriteresDto criteres);
}

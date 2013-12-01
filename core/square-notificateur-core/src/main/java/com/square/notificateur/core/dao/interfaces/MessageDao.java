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
package com.square.notificateur.core.dao.interfaces;

import java.util.List;

import com.square.notificateur.core.dto.MessageCriteresDto;
import com.square.notificateur.core.model.Message;

/**
 * Interface de MessageDao.
 * @author mohamed - SCUB
 *
 */
public interface MessageDao {
	/**
	 * Créé un Message.
	 * @param message le message à créer.
	 */
	void creerMessage(Message message);
	/**
	 * Récupère le message correspondant à l'identifiant unique spécifié.
	 * @param idMessage identifiant du message
	 * @return le message trouvé.
	 */
	Message getMessageById(Long idMessage);
	/**
	 * rechercher les messages par criteres.
	 * @param criteres criteres de recherche de messages
	 * @return liste de messages répondant aux criteres
	 */
	List<Message> rechercherMessageParCriteres(MessageCriteresDto criteres);
}

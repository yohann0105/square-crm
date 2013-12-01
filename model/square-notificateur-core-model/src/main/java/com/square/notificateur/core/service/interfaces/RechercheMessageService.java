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
 * Services permettant de récupérer des messages.
 * @author Ksouri Mohamed Ali - SCUB
 */
public interface RechercheMessageService {
	/**
	 * recupere les messages de l'utilisateur en mode push simulée.
	 * @param criteria les critéres de messages à rechercher
	 * @return liste de message
	 */
	List<MessageDto> recupererMessagePourUtilisateur(MessageCriteresDto criteria);

}

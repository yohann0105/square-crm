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
package com.square.core.model.plugin.Implemenatation;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.scub.foundation.framework.base.exception.TechnicalException;

import com.square.core.model.dto.MessagePublishDto;
import com.square.core.model.plugin.NotificateurSquarePlugin;
import com.square.notificateur.core.dto.MessageDto;
import com.square.notificateur.core.service.interfaces.MessageService;

/**
 * Implémentation du plugin de notification square.
 * @author abrochain 6 oct. 2012
 *
 */
public class NotificateurSquarePluginImpl implements NotificateurSquarePlugin {

	/**
	 * service de publication.
	 */
	private MessageService messageService;

	/**
	 * Publication d'un message.
	 * @param message le message à publier
	 */
	public void publierMessage(MessagePublishDto message) {
		try {
			final MessageDto messageRetour = new MessageDto();
			BeanUtils.copyProperties(messageRetour, message);
			messageService.creerMessage(messageRetour);
		} catch (IllegalAccessException e) {
			throw new TechnicalException(e);
		} catch (InvocationTargetException e) {
			throw new TechnicalException(e);
		}
	}
	/**
	 * Setter of MessageService.
	 * @param messageService the messageService to set
	 */
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
}

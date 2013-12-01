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
package com.square.notificateur.core.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.notificateur.core.dao.interfaces.MessageDao;
import com.square.notificateur.core.dto.MessageCriteresDto;
import com.square.notificateur.core.dto.MessageDto;
import com.square.notificateur.core.service.interfaces.MessageService;
import com.square.notificateur.core.service.interfaces.NotificateurMappingService;
import com.square.notificateur.core.service.interfaces.RechercheMessageService;
import com.square.notificateur.core.util.MessageKeyUtil;

/**
 * Implementaion de service de récupération des messages.
 * @author Ksouri Mohamed Ali - SCUB
 *
 */
public class RechercheMessageServiceImplementation implements RechercheMessageService {

	private static final int MILLE = 1000;

	/** Le logger. */
	private static Logger logger = Logger.getLogger(MessageServiceImplementation.class);

	/**
	 * Classe utilitaire pour accéder aux messages.
	 */
	private MessageSourceUtil messageSourceUtil;
	/**
	 * Service de message.
	 */
	private MessageService messageService;
	/**
	 * DAO pour les messages.
	 */
	private MessageDao messageDao;

	/**
	 * Mapper Dozer.
	 */
	private MapperDozerBean mapperDozerBean;
	/**
	 *notificateurMappingService.
	 */
	private NotificateurMappingService notificateurMappingService;
	/**
	 * nombre d'appel.
	 */
	private Integer nombreAppelModePush;
	/**
	 * Temps d'un appel.
	 */
	//todo RENOMER EN TIMEOUT
	private Integer timeout;
	@Override
	public  List<MessageDto> recupererMessagePourUtilisateur(
			MessageCriteresDto criteres) {
		int index = 0;
		 List < MessageDto > messages = new ArrayList< MessageDto >();
		while (index < nombreAppelModePush)
		{
			messages = messageService.rechercherMessagesParCriteres(criteres);
			if (messages.size() > 0)
			{
				return messages;
			}
			 int tempsAttente = timeout / nombreAppelModePush;
			if (tempsAttente < 1) {
				tempsAttente = 1;
			}
			try {
				synchronized (this) {
					this.wait(tempsAttente * MILLE);
				}

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			index++;
		}
		//sinon envoyer le message ARE YOU ALIVE
		final MessageDto messageAYA = new MessageDto();
//		messageAYA.setId(notificateurMappingService.getIdMessageAYA());
		messageAYA.setTexte(messageSourceUtil.get(MessageKeyUtil.MESSAGE_ARE_YOU_ALIVE));
		messages.add(messageAYA);
		return messages;
	}

	/**setter de logger.
	 * @param logger the logger to set
	 */
	public static void setLogger(Logger logger) {
		RechercheMessageServiceImplementation.logger = logger;
	}

	/**getter de logger.
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**setter de messageSourceUtil.
	 * @param messageSourceUtil the messageSourceUtil to set
	 */
	public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
		this.messageSourceUtil = messageSourceUtil;
	}

	/**getter de messageSourceUtil.
	 * @return the messageSourceUtil
	 */
	public MessageSourceUtil getMessageSourceUtil() {
		return messageSourceUtil;
	}

	/**setter de mapperDozerBean.
	 * @param mapperDozerBean the mapperDozerBean to set
	 */
	public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
		this.mapperDozerBean = mapperDozerBean;
	}

	/**getter de mapperDozerBean.
	 * @return the mapperDozerBean
	 */
	public MapperDozerBean getMapperDozerBean() {
		return mapperDozerBean;
	}

	/**setter de messageDao.
	 * @param messageDao the messageDao to set
	 */
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	/**getter de messageDao.
	 * @return the messageDao
	 */
	public MessageDao getMessageDao() {
		return messageDao;
	}


	/**setter de nombreAppelModePush.
	 * @param nombreAppelModePush the nombreAppelModePush to set
	 */
	public void setNombreAppelModePush(Integer nombreAppelModePush) {
		this.nombreAppelModePush = nombreAppelModePush;
	}


	/**setter de timeout.
	 * @param timeout the timeout to set
	 */
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	/**setter de messageService.
	 * @param messageService the messageService to set
	 */
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	/**getter de messageService.
	 * @return the messageService.
	 */
	public MessageService getMessageService() {
		return messageService;
	}
	/**setter de notificateurMappingService.
	 * @param notificateurMappingService the notificateurMappingService to set
	 */
	public void setNotificateurMappingService(NotificateurMappingService notificateurMappingService) {
		this.notificateurMappingService = notificateurMappingService;
	}
	/**getter de notificateurMappingService.
	 * @return the notificateurMappingService
	 */
	public NotificateurMappingService getNotificateurMappingService() {
		return notificateurMappingService;
	}


}

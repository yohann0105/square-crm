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

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.notificateur.core.dao.interfaces.MessageDao;
import com.square.notificateur.core.dto.MessageCriteresDto;
import com.square.notificateur.core.dto.MessageDto;
import com.square.notificateur.core.model.Message;
import com.square.notificateur.core.service.interfaces.MessageService;
import com.square.notificateur.core.service.interfaces.NotificateurMappingService;
import com.square.notificateur.core.util.MessageKeyUtil;

/**
 * Implementation de MessageService.
 * @author mohamed - SCUB
 *
 */
public class MessageServiceImplementation   implements MessageService {
	/** Le logger. */
	private static Logger logger = Logger.getLogger(MessageServiceImplementation.class);

	/**
	 * Classe utilitaire pour accéder aux messages.
	 */
	private MessageSourceUtil messageSourceUtil;


	/**
	 * DAO pour les messages.
	 */
	private MessageDao messageDao;

	/**
	 * Mapper Dozer.
	 */
	private MapperDozerBean mapperDozerBean;
	/**
	 * service de mapping.
	 */
	private NotificateurMappingService notificateurMappingService;

	@Override
	public MessageDto creerMessage(MessageDto message) {
		if (message == null) {
			throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_SAVE_MESSAGE_DTO_REQUIRED));
		}

		// On vérifie que le message à sauvegarder a un userID
		if (message.getIdUtilisateur() == null) {
			throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_SAVE_MESSAGE_USERID_REQUIRED));
		}
		if (message.getTitre() == null || message.getTitre() == "") {
			message.setTitre("Sans Titre");
		}

		// On vérifie que le message à sauvegarder a un texte
		if (message.getTexte() == null || message.getTexte().isEmpty()) {
			throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_SAVE_MESSAGE_TEXT_REQUIRED));
		}
		if (message.getDatePublication() == null) {
			message.setDatePublication(Calendar.getInstance());
		}

		final Message newMessage = mapperDozerBean.map(message, Message.class);
		// Sauvegarde du message dans la base
		messageDao.creerMessage(newMessage);
		logger.debug(messageSourceUtil.get(MessageKeyUtil.MESSAGE_INFO_SAVE_MESSAGE_CREATED));
		return mapperDozerBean.map(newMessage, MessageDto.class);


	}
	@Override
	public void acquitterMessage(Long idMessage) {
//		final Long idAYA = notificateurMappingService.getIdMessageAYA();
		//Le message ne doit pas être null
		if (idMessage == null) {
			throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_RECEPTION_MESSAGE_ID_REQUIRED));
		}
		//il ne faut pas acquitter le are you alive message
//		if (idMessage.equals(idAYA)) {
//			throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_RECEPTION_MESSAGE_MESSAGE_AREYOUALIVE_AQUIT));
//		}
		//Récupérer le Message à acquitter
		final Message message = messageDao.getMessageById(idMessage);
		final Calendar dateReception = Calendar.getInstance();
		//si le message n'existe pas
		if (message == null) {
			throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_RECEPTION_MESSAGE_MESSAGE_TO_ACQUIT_NOT_FOUND));
		} else {
			//si le message existe modifier sa date de reception
			message.setDateReception(dateReception);
			logger.debug(messageSourceUtil.get(MessageKeyUtil.MESSAGE_INFO_RECEPTION_MESSAGE_ACQUITED));

		}


	}
	@Override
	public List<MessageDto> rechercherMessagesParCriteres(MessageCriteresDto criteres) {
		if (criteres == null) {
			throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_SEARCH_MESSAGE_DTO_REQUIRED));
		}
		final Long idUtilisateur = criteres.getIdUtilisateur();
		Calendar datePublication = criteres.getDatePublication();
		if (idUtilisateur == null) {
			throw new BusinessException(messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_SEARCH_MESSAGE_USERID_REQUIRED));
		}
		if (datePublication == null) {
			datePublication = Calendar.getInstance();
		}
		final List <Message> messages = messageDao.rechercherMessageParCriteres(criteres);
		for (Message message : messages) {
			message.setDateLastPublication(Calendar.getInstance());
			final int nombreLivraison = message.getNombreLivraison();
			message.setNombreLivraison(nombreLivraison + 1);
		}
		final List <MessageDto> messagesToDeliver = mapperDozerBean.mapList(messages , MessageDto.class);
		return messagesToDeliver;
	}

	/**setter pour logger.
	 * @param logger the logger to set
	 */
	public static void setLogger(Logger logger) {
		MessageServiceImplementation.logger = logger;
	}
	/**setter pour messageSourceUtil.
	 * @param messageSourceUtil the messageSourceUtil to set
	 */
	public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
		this.messageSourceUtil = messageSourceUtil;
	}
	/**setter pour messageDao.
	 * @param messageDao the messageDao to set
	 */
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	/**setter pour mapperDozerBean.
	 * @param mapperDozerBean the mapperDozerBean to set
	 */
	public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
		this.mapperDozerBean = mapperDozerBean;
	}
	/**setter de notificateurMappingService.
	 * @param notificateurMappingService the notificateurMappingService to set.
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

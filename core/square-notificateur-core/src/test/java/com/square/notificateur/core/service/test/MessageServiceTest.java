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
package com.square.notificateur.core.service.test;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.notificateur.core.dto.MessageCriteresDto;
import com.square.notificateur.core.dto.MessageDto;
import com.square.notificateur.core.util.MessageKeyUtil;
import com.square.notificateur.core.service.interfaces.MessageService;
import com.square.notificateur.core.service.interfaces.NotificateurMappingService;

/**
 * Classe de test de MessageService.
 * @author mohamed - SCUB
 *
 */
public class MessageServiceTest extends DbunitBaseTestCase {
	/**
	 * Services liés aux messages.
	 */
	private MessageService messageService;

	/**
	 * Gestion des messages.
	 */
	private MessageSourceUtil messageSourceUtil;

	/**
	 * service de mapping de gestion des constantes.
	 */
	@SuppressWarnings("unused")
	private NotificateurMappingService notificateurMappingService;
	/**
	 * Méthode appellée avant chaque test unitaire.
	 */
	@Before
	public void setUp() {
		messageService = (MessageService) getBeanSpring("messageService");
		notificateurMappingService = (NotificateurMappingService) getBeanSpring("notificateurMappingService");
		messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
	}



	/** {@inheritDoc} */
	public String[] getFichierContextSpringSup() {
		return new String[] {"notificateurMappingContext.xml"};
	}
	/**
	 * Test global de créer Message.
	 */
	public void testGlobalCreerMessage() {
		//test de CreerMessage avec Dto Null
		try {
			messageService.creerMessage(null);
			fail(Messages.getString("MessageServiceTest.4"));
		} catch (BusinessException e) {
			assertEquals(Messages.getString("MessageServiceTest.5")
					, messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_SAVE_MESSAGE_DTO_REQUIRED)
					, e.getMessage());
		}
		//Test de Creer Message avec id Utilisateur null
		final MessageDto newMessage = new MessageDto();
		newMessage.setTexte("messageTexte");
		newMessage.setDateLastPublication(Calendar.getInstance());
		try {
			messageService.creerMessage(newMessage);
			fail(Messages.getString("MessageServiceTest.7"));
		} catch (BusinessException e) {
			assertEquals(Messages.getString("MessageServiceTest.8")
					, messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_SAVE_MESSAGE_USERID_REQUIRED)
					, e.getMessage());
		}
		//Test de Creer Message avec Texte null
		final Long userId = 9L;
		newMessage.setIdUtilisateur(userId);
		newMessage.setTexte(null);

		try {
			messageService.creerMessage(newMessage);
			fail(Messages.getString("MessageServiceTest.9"));
		} catch (BusinessException e) {
			assertEquals(Messages.getString("MessageServiceTest.10")
					, messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_SAVE_MESSAGE_TEXT_REQUIRED)
					, e.getMessage());
		}
		final Long idUser = 11L;
		newMessage.setIdUtilisateur(idUser);
		newMessage.setTexte("Message à diffuser");
		messageService.creerMessage(newMessage);
		final  MessageCriteresDto criteres = new MessageCriteresDto();
		criteres.setIdUtilisateur(idUser);
		criteres.setDatePublication(Calendar.getInstance());
		 List<MessageDto> messages = messageService.rechercherMessagesParCriteres(criteres);
		messages = messageService.rechercherMessagesParCriteres(criteres);
		assertEquals(Messages.getString("MessageServiceTest.12"), 1, messages.size());
		assertEquals(Messages.getString("MessageServiceTest.13"), idUser, messages.get(0).getIdUtilisateur());
	}
	/**
	 * Test unitaire de acquitterMessage avec un message inexistant dan la base.
	 */
	@Test
	public void acquitMessageTestWithNotexistantMessage() {
		final Long idMessage = 33L;
		try {
			messageService.acquitterMessage(idMessage);
			fail(Messages.getString("MessageServiceTest.14"));
		} catch (BusinessException e) {
			assertEquals(Messages.getString("MessageServiceTest.15")
					, messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_RECEPTION_MESSAGE_MESSAGE_TO_ACQUIT_NOT_FOUND)
					, e.getMessage());
		}
	}
	/**
	 * Test unitaire de AcquitMessage avec un id de message null.
	 */
	@Test
	public void acquitMessageTestWithNullId() {
		try {
			messageService.acquitterMessage(null);
			fail(Messages.getString("MessageServiceTest.16"));
		} catch (BusinessException e) {
			assertEquals(Messages.getString("MessageServiceTest.17")
					, messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_RECEPTION_MESSAGE_ID_REQUIRED)
					, e.getMessage());
		}

	}
//	/**
//	 * Test unitaire de AcquitMessage pour le message AREYOUALIVE.
//	 */
//	@Test
//	public void testAcuitterMessagePourAYAMessage() {
//		final Long idMessage = notificateurMappingService.getIdMessageAYA();
//		try {
//			messageService.acquitterMessage(idMessage);
//			fail("L'acquittement d'un message ARE YOU ALIVE aurait du échouer");
//		} catch (BusinessException e) {
//			assertEquals("L'exception rencontrée lors de l'acquittement d'un message ARE YOU ALIVE est différente de celle attendue"
//					, messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_RECEPTION_MESSAGE_MESSAGE_AREYOUALIVE_AQUIT)
//					, e.getMessage());
//		}
//	}

	/**
	 * Test unitaire de AcquitMessage.
	 */
	@Test
	public void acquitMessageTest() {

		final Long userId = 3L;
		final Calendar date = Calendar.getInstance();
		final MessageCriteresDto critere = new MessageCriteresDto();
		critere.setDatePublication(date);
		critere.setIdUtilisateur(userId);
		List < MessageDto > messages = messageService.rechercherMessagesParCriteres(critere);
		final MessageDto message1 = messages.get(0);
		assertEquals(Messages.getString("MessageServiceTest.18"), 1, messages.size());
		assertEquals(Messages.getString("MessageServiceTest.19"), 1, message1.getNombreLivraison());
		final Long idMessage = message1.getId();
		messageService.acquitterMessage(idMessage);
		critere.setInclureAquites(false);
		messages = messageService.rechercherMessagesParCriteres(critere);
		assertEquals(Messages.getString("MessageServiceTest.20"), 0, messages.size());
		critere.setInclureAquites(true);
		messages = messageService.rechercherMessagesParCriteres(critere);
		assertEquals(Messages.getString("MessageServiceTest.21"), 1, messages.size());

	}
	/**
	 * Test de recherche par critéres avec userId.
	 */
	@Test
	public void testRechercherMessagesWithDateNull() {
		final MessageCriteresDto critere = new MessageCriteresDto();
		final Long userId = null;
		critere.setDatePublication(null);
		critere.setIdUtilisateur(userId);
		try  {
			messageService.rechercherMessagesParCriteres(critere);
			fail(Messages.getString("MessageServiceTest.22"));
		} catch (BusinessException e) {
			assertEquals(Messages.getString("MessageServiceTest.23")
					, messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_SEARCH_MESSAGE_USERID_REQUIRED)
					, e.getMessage());
		}
	}
	/**
	 * Test de recherche par critéres avec DTO Criteres null.
	 */
	@Test
	public void testRechercherMessagesWithoutDto() {
		try  {
			messageService.rechercherMessagesParCriteres(null);
			fail(Messages.getString("MessageServiceTest.24"));
		} catch (BusinessException e) {
			assertEquals(Messages.getString("MessageServiceTest.25")
					, messageSourceUtil.get(MessageKeyUtil.MESSAGE_ERROR_SEARCH_MESSAGE_DTO_REQUIRED)
					, e.getMessage());
		}
	}

}

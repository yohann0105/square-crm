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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.notificateur.core.dto.MessageCriteresDto;
import com.square.notificateur.core.dto.MessageDto;
import com.square.notificateur.core.service.interfaces.MessageService;
import com.square.notificateur.core.service.interfaces.NotificateurMappingService;
import com.square.notificateur.core.service.interfaces.RechercheMessageService;

/**
 * Classe de test RechercheMessageService.
 * @author mohamed - SCUB
 *
 */
public class RechercheMessageServiceTest extends DbunitBaseTestCase {

	private static final String RECHERCHE_MESSAGE_SERVICE = "rechercheMessageService";

	private static final int VINGT_HUIT = 28;

	private static final int VINGT = 20;

	private static final long MILLE = 1000l;

	/** Le logger. */
	private static Logger logger = Logger.getLogger(RechercheMessageServiceTest.class);

	/**
	 * Services liés aux messages.
	 */
	private RechercheMessageService rechercheMessageService;
	private MessageService messageService;
	private RechercheMessageService rechercheMessageService1;
	private RechercheMessageService rechercheMessageService2;
	private RechercheMessageService rechercheMessageService3;
	private List<MessageDto> messages1;
	private List<MessageDto> messages2;
	private List<MessageDto> messages3;
	/**
	 * Gestion des messages.
	 */
	//private MessageSourceUtil messageSourceUtil;

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
		rechercheMessageService = (RechercheMessageService) getBeanSpring(RECHERCHE_MESSAGE_SERVICE);
		rechercheMessageService1 = (RechercheMessageService) getBeanSpring(RECHERCHE_MESSAGE_SERVICE);
		rechercheMessageService2 = (RechercheMessageService) getBeanSpring(RECHERCHE_MESSAGE_SERVICE);
		rechercheMessageService3 = (RechercheMessageService) getBeanSpring(RECHERCHE_MESSAGE_SERVICE);
		messageService = (MessageService) getBeanSpring("messageService");
		notificateurMappingService = (NotificateurMappingService) getBeanSpring("notificateurMappingService");
	}
	/** {@inheritDoc} */
	public String[] getFichierContextSpringSup() {
		return new String[] {"notificateurMappingContext.xml"};
	}
//	/**
//	 * test unitaire de recupererMessagePourUtilisateur avec pas de messages.
//	 */
//	@Test
//	public void testRechercheWithNoMessagesForUser() {
//		final MessageCriteresDto critere = new MessageCriteresDto();
//		critere.setDatePublication(Calendar.getInstance());
//		critere.setIdUtilisateur(9L);
//		final List <MessageDto>messages = rechercheMessageService.recupererMessagePourUtilisateur(critere);
//		final MessageDto message = messages.get(0);
//		//TODO utiliser constantes de mapping
//		final Long idMessage = notificateurMappingService.getIdMessageAYA();
//		assertEquals("nombre de messages trouvés" , 1 , messages.size());
//		assertEquals("id de Message Trouvé" , idMessage, message.getId());
//
//	}
	/**
	 * test unitaire de recupererMessagePourUtilisateur avec messages en réponse.
	 */
	@Test
	public void testRechercheWithExistantsMessages() {
		final MessageCriteresDto critere = new MessageCriteresDto();
		critere.setDatePublication(Calendar.getInstance());
		critere.setIdUtilisateur(5L);
		final List <MessageDto> messages = rechercheMessageService.recupererMessagePourUtilisateur(critere);
		final MessageDto message = messages.get(0);
		final Long idUser = 5L;
		assertEquals(Messages.getString("RechercheMessageServiceTest.7") , VINGT_HUIT , messages.size());
		assertEquals(Messages.getString("RechercheMessageServiceTest.8") , idUser, message.getIdUtilisateur());

	}
	/**
	 * test pour stresser le systéme pour des différents utilisateurs.
	 */
	@Test
	public void testStressForVariousUser() {
		final MessageCriteresDto critere = new MessageCriteresDto();
		critere.setIdUtilisateur(8L);
		critere.setDatePublication(Calendar.getInstance());
		final MessageCriteresDto critere2 = new MessageCriteresDto();
		critere2.setIdUtilisateur(5L);
		critere2.setDatePublication(Calendar.getInstance());
		final MessageCriteresDto critere3 = new MessageCriteresDto();
		critere3.setIdUtilisateur(9L);
		critere3.setDatePublication(Calendar.getInstance());
		// final List<MessageDto> messages1;
		final Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				logger.debug(Messages.getString("RechercheMessageServiceTest.0") + System.currentTimeMillis());

				messages1 = rechercheMessageService1.recupererMessagePourUtilisateur(critere);

				logger.debug(Messages.getString("RechercheMessageServiceTest.1") + System.currentTimeMillis());
			}

		});
		final Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				logger.debug(Messages.getString("RechercheMessageServiceTest.11") + System.currentTimeMillis());
				//RechercheMessageService	rechercheMessageService2 = (RechercheMessageService) getBeanSpring(RECHERCHE_MESSAGE_SERVICE);
				messages2 = rechercheMessageService2.recupererMessagePourUtilisateur(critere2);
				logger.debug(Messages.getString("RechercheMessageServiceTest.12") + System.currentTimeMillis());
			}

		});
		final Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				logger.debug(Messages.getString("RechercheMessageServiceTest.13") + System.currentTimeMillis());
				//RechercheMessageService	rechercheMessageService3 = (RechercheMessageService) getBeanSpring(RECHERCHE_MESSAGE_SERVICE);
				messages3 = rechercheMessageService3.recupererMessagePourUtilisateur(critere3);
				logger.debug(Messages.getString("RechercheMessageServiceTest.14") + System.currentTimeMillis());
			}

		});

		t1.start();
		//t4.start();
		t2.start();
		t3.start();
		while (t1.isAlive() || t2.isAlive() || t3.isAlive()) {
			//totto
		}
		logger.debug(rechercheMessageService1.toString());
		logger.debug(rechercheMessageService2.toString());
		logger.debug(rechercheMessageService3.toString());
		logger.debug(Messages.getString("RechercheMessageServiceTest.15") +  messages1.size());
		logger.debug(Messages.getString("RechercheMessageServiceTest.16") +  messages2.size());
		logger.debug(Messages.getString("RechercheMessageServiceTest.17") +  messages3.size());

	}
	/**
	 * test pour stresser le systéme avec plusieurs threads de recherche de messages
	 * pour le même utilisateur.
	 */
	@Test
	public void testStressForSameUser() {
		final Long idUser = 5l;
		final List<TestSearchMessageThread> threadList = new ArrayList<TestSearchMessageThread>();
		for (int i = 0; i < VINGT; i++) {
			final TestSearchMessageThread thread =	getThreadSearchForSystemTest(getCriteria(idUser));
			threadList.add(thread);
			thread.setName("Thread" + i);
			thread.start();
			try {
				Thread.sleep(2 * MILLE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Boolean terminated = true;

		while (terminated) {

			for (TestSearchMessageThread thread : threadList) {
				//TODO pk terminated = !thread.isAlive() donne une boucle infinie ?
				terminated = false || thread.isAlive();
			}
		}


	}
	/**
	 * Test de la synchronization.
	 */
	@Test
	public void testsynchronize() {
		final Long idUser = 6L;
		final TestSearchMessageThread searchThread =	getThreadSearchForSystemTest(getCriteria(idUser));
		searchThread.setName("searchthread");
		final Thread createThread = new Thread(new Runnable() {
			final Calendar date = Calendar.getInstance();
			@Override
			public void run() {
				logger.debug(Messages.getString("RechercheMessageServiceTest.3"));
				final MessageDto message = new MessageDto();
				message.setIdUtilisateur(idUser);
				message.setDatePublication(date);
				message.setTexte(Messages.getString("RechercheMessageServiceTest.21"));
				messageService.creerMessage(message);
				logger.debug(Messages.getString("RechercheMessageServiceTest.2"));
			}
		});
		searchThread.start();
//		try {
//			Thread.sleep(2*1000l);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		createThread.start();
		while(createThread.isAlive() ||searchThread.isAlive() ){
			
		}
		
	}

	/**
	 * classe de creation de thread de recherche.
	 * @param criteres criteres de recherches
	 * @return TestSearchMessageThread;
	 */
	public TestSearchMessageThread getThreadSearchForSystemTest(MessageCriteresDto criteres) {
		final RechercheMessageService rechercheMessageServiceForThread = (RechercheMessageService) getBeanSpring(RECHERCHE_MESSAGE_SERVICE);

		return new TestSearchMessageThread(rechercheMessageServiceForThread, criteres);
	}
	/**
	 * classe de creation de criteres de recherche.
	 * @param idUser id utilisateur
	 * @return MessageCriteresDto
	 */
	public MessageCriteresDto getCriteria(Long idUser) {
		final MessageCriteresDto critere = new MessageCriteresDto();
		critere.setIdUtilisateur(idUser);
		return critere;
	}
	
}
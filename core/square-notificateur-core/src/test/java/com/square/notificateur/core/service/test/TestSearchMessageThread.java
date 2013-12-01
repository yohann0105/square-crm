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

import java.util.List;

import org.apache.log4j.Logger;
import com.square.notificateur.core.dto.MessageCriteresDto;
import com.square.notificateur.core.dto.MessageDto;
import com.square.notificateur.core.service.interfaces.RechercheMessageService;
/**
 * classe de thread de recherche.
 * @author mohamed - SCUB
 *
 */
 class TestSearchMessageThread extends Thread {
	private RechercheMessageService rechercheMessageService;
	private MessageCriteresDto criteres;
	private List<MessageDto> messages;
	private static Logger logger = Logger.getLogger(RechercheMessageServiceTest.class);
	/**
	 * constructeur.
	 * @param rechercheMessageService service
	 * @param criteres criteres de recherches
	 */
	public TestSearchMessageThread(
			RechercheMessageService rechercheMessageService,
			MessageCriteresDto criteres) {
		super();
		this.rechercheMessageService = rechercheMessageService;
		this.criteres = criteres;

	}
	@Override
	public void run() {
		logger.debug(Messages.getString("TestSearchMessageThread.0") + this.getName() + Messages.getString("TestSearchMessageThread.1")
				+ System.currentTimeMillis());
		messages = rechercheMessageService.recupererMessagePourUtilisateur(criteres);
		logger.debug(Messages.getString("TestSearchMessageThread.2") + Messages.getString("TestSearchMessageThread.3")
				+ this.getName() + Messages.getString("TestSearchMessageThread.4") + System.currentTimeMillis()
				+ Messages.getString("TestSearchMessageThread.5") + messages.size() + Messages.getString("TestSearchMessageThread.6")
				+ messages.get(0).getNombreLivraison());
		logger.debug(Messages.getString("TestSearchMessageThread.7") + messages.get(0).getTexte());
	}



}

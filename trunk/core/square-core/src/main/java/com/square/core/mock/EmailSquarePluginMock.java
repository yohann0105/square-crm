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
package com.square.core.mock;

import org.apache.log4j.Logger;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.core.model.dto.MailDto;
import com.square.core.model.plugin.EmailSquarePlugin;
import com.square.core.service.implementations.ActionServiceImplementation;
import com.square.core.util.MockKeyUtil;

/**
 * Mock pour le plugin des emails de square.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class EmailSquarePluginMock implements EmailSquarePlugin {

	private MessageSourceUtil messageSourceutil;

    /** Le logger. */
    private static Logger logger = Logger.getLogger(ActionServiceImplementation.class);

    @Override
    public void envoyerEmail(MailDto emailDto) {
        logger.info(messageSourceutil.get(MockKeyUtil.MESSAGE_ENVOI_EMAIL_FICTIF));
    }

	/**Set messageSourceutil.
	 * @param messageSourceutil the messageSourceutil to set
	 */
	public void setMessageSourceutil(MessageSourceUtil messageSourceutil) {
		this.messageSourceutil = messageSourceutil;
	}

}

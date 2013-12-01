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
package com.square.synchro.app.noyau.services.implementations;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.square.synchro.app.noyau.dto.DefaultMessageSynchroAppDto;
import com.square.synchro.app.noyau.services.interfaces.SynchroAppJmsSender;
import com.square.synchro.app.noyau.util.MessageKeyUtil;

/**
 * Implementation service permettant d'envoyer des messages.
 * @author Goumard Stephane (stephane.goumard@scub.net). - SCUB
 */
public class SynchroAppJmsSenderImpl implements SynchroAppJmsSender {
    /**
     * Template sur la file topic.
     */
    private JmsTemplate topicSynchroAppSender;

    /** Gestion des messages. */
    private MessageSourceUtil messageSourceUtil;

    /**
     * Logger.
     */
    private Logger logger = RootLogger.getLogger(SynchroAppJmsSenderImpl.class);

    @Override
    public void envoyerMessageSynchro(final DefaultMessageSynchroAppDto message) {
        logger.debug(messageSourceUtil.get(messageSourceUtil.get(MessageKeyUtil.LOGGER_DEBUG_ENVOIE_MESSAGE,
        		 new String[] {String.valueOf(message.getMsgSynchroOrigineHeader())})));
        final MessageCreator creator = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                final ObjectMessage objectMessage = session.createObjectMessage(message);
                objectMessage.setStringProperty("MSG_HEADER_SYNCHRO_ORIGINE", message.getMsgSynchroOrigineHeader());
                return objectMessage;
            }
        };
        topicSynchroAppSender.send(creator);
    }

    /**
     * Fixe le template sur le topic.
     * @param topicSynchroAppSender the topicSynchroAppSender to set
     */
    public void setTopicSynchroAppSender(JmsTemplate topicSynchroAppSender) {
        this.topicSynchroAppSender = topicSynchroAppSender;
    }
}

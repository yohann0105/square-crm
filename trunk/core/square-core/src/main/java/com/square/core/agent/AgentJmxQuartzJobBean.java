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
package com.square.core.agent;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.square.core.util.AgentJmxKeyUtil;

/**
 * Agent Quartz pour la gestion des tâche automatisées.
 * @author Goumard Stephane (stephane.goumard@scub.net); - SCUB
 */
public class AgentJmxQuartzJobBean extends QuartzJobBean {
    /**
     * Agent JMX.
     */
    private IAgentJmxBaseMBean agentJmx;

    private MessageSourceUtil messageSourceUtil;

    /**
     * Logger.
     */
    private Logger logger = RootLogger.getLogger(AgentJmxQuartzJobBean.class);

    /**
     * . {@inheritDoc}
     */
    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        logger.info(messageSourceUtil.get(AgentJmxKeyUtil.MESSAGE_DEMARRAGE_CONNECTION_JMX) + " : " + agentJmx.getClass());
        agentJmx.start();
    }

    /**
     * Set the agentJmx value.
     * @param agentJmx the agentJmx to set
     */
    public void setAgentJmx(IAgentJmxBaseMBean agentJmx) {
        this.agentJmx = agentJmx;
    }

    /**
     * Set messageSourceUtil value.
     * @param messageSourceUtil the messageSourceUtil to set
     */
    public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
        this.messageSourceUtil = messageSourceUtil;
    }
}

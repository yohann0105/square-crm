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

import com.square.core.model.plugin.NumeroClientSquarePlugin;
import com.square.core.util.MockKeyUtil;

/**
 * Mock sur la generation de numero unique.
 * @author Goumard Stephane - Scub - SCUB
 */
public class NumeroUniqueSquarePluginMock implements NumeroClientSquarePlugin {

	/** Logger. */
    private Logger logger = Logger.getLogger(this.getClass());

    private MessageSourceUtil messageSourceUtil;

    /** Constante. */
    private static final Integer NUMBER_RANDOM_GENE_1 = 9999999;
    private static final Integer NUMBER_RANDOM_GENE_2 = 1000000;
    @Override
    public String getNumeroClient() {
    	// génère un int aléatoire dans la fourchette 9000000-10000000
    	final int value = NUMBER_RANDOM_GENE_1 - (int) (Math.random() * NUMBER_RANDOM_GENE_2);
        logger.debug(messageSourceUtil.get(MockKeyUtil.MESSAGE_CREATION_NUM_CLIENT, new String[] {String.valueOf(value)}));
        return Integer.toString(value);
    }

	/**Set messageSourceUtil.
	 * @param messageSourceUtil the messageSourceUtil to set
	 */
	public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
		this.messageSourceUtil = messageSourceUtil;
	}
}

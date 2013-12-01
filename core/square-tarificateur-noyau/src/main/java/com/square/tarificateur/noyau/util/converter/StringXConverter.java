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
package com.square.tarificateur.noyau.util.converter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.beans.factory.InitializingBean;

import com.square.tarificateur.noyau.util.MessageKeyUtil;

/**
 * Implémentation du converter.
 * @author nnadeau - SCUB
 */
public class StringXConverter implements Converter, InitializingBean {
    /**
     * Format expression.
     */
    private SimpleDateFormat simpleDateFormat;
    /**
     * Classe utilitaire contenant les chaines de caractéres.
     */
    private MessageSourceUtil messageSourceUtil;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Object convert(Class type, Object value) {
        if (value instanceof Calendar) {
            return simpleDateFormat.format(((Calendar) value).getTime());
        }
        else {
            return new StringConverter().convert(type, value);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void afterPropertiesSet() throws Exception {
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(messageSourceUtil.get(MessageKeyUtil.DATE_FORMAT_DDMMYYYY_SEPARATOR));
        }
    }

    /**
     * Set the simpleDateFormat value.
     * @param simpleDateFormat the simpleDateFormat to set
     */
    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

	/**Set messageSourceUtil.
	 * @param messageSourceUtil the messageSourceUtil to set
	 */
	public void setMessageSourceUtil(MessageSourceUtil messageSourceUtil) {
		this.messageSourceUtil = messageSourceUtil;
	}

}

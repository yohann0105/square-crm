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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.beanutils.Converter;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.beans.factory.InitializingBean;

import com.square.tarificateur.noyau.util.MessageKeyUtil;

/**
 * Convertisseur de date pour le plugin média.
 * @author nnadeau - SCUB
 */
public class CalendarConverter implements Converter, InitializingBean {
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
        if (value == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(String.valueOf(value)));
        } catch (ParseException e) {
            throw new TechnicalException(e);
        }
        return calendar;
    }

    /**
     * {@inheritDoc}
     */
    public void afterPropertiesSet() throws Exception {
        if (simpleDateFormat == null) {
        	final String sdfFormat = messageSourceUtil.get(MessageKeyUtil.DATE_FORMAT_DDMMYYYY_SEPARATOR);
            simpleDateFormat = new SimpleDateFormat(sdfFormat);
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

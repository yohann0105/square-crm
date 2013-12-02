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
package com.square.core.util.lucene;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.search.SearchException;
import org.hibernate.search.bridge.TwoWayStringBridge;
import org.hibernate.util.StringHelper;

/**
 * Bridge pour l'indexation de dates de naissance, sans heure ni considération de timezone.
 * @author mgodbert - SCUB
 */
public class DateNaissanceBridge implements TwoWayStringBridge {

	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	@Override
	public String objectToString(Object object) {
		if (object == null) {
			return null;
		}
		final Calendar calendar = (Calendar) object;
		return format.format(calendar.getTime());
	}

	@Override
	public Object stringToObject(String stringValue) {
		if (StringHelper.isEmpty(stringValue)) {
			return null;
		}
		try {
			final Date date = format.parse(stringValue);
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new SearchException("Unable to parse into calendar: " + stringValue, e);
		}
	}
}

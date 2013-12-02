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
package com.square.client.gwt.server.util;

import net.sf.dozer.util.mapping.converters.CustomConverter;

import com.square.client.gwt.client.model.IdentifiantLibelleOuiNonModel;

/**
 * Converter permettant de passer de Boolean à IdentilfiantLibelleOuiNonModel.
 * @author Benoit Thomas (benoit.thomas@scub.net) - SCUB
 */
public class CustomBooleanConverter implements CustomConverter {

	@Override
	public Object convert(Object destination, Object source,
			Class destinationClass, Class sourceClass) {
		IdentifiantLibelleOuiNonModel dest = null;
		if (sourceClass == Boolean.class) {
			// check to see if the object already exists
			if (destination == null) {
				final Boolean param = source == null ? null : (Boolean) source;
				dest = new IdentifiantLibelleOuiNonModel(param);
			} else {
				dest = (IdentifiantLibelleOuiNonModel) destination;
			}
			return dest;
		} else if (source instanceof IdentifiantLibelleOuiNonModel) {
			return ((IdentifiantLibelleOuiNonModel) source).getFlag();
		} else {
			return null;
		}
	}

}

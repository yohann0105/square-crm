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
package com.square.logs.core.util;

/**
 * Classe utilitaire qui regroupe l'ensemble des clés pour les messages.
 * @author abrochain 6 oct. 2012
 *
 */
public final class MessageKeyUtil {

	/** Constructeur. */
    private MessageKeyUtil() {
        super();
    }


	public static final String LOGGER_WARN_EXCEPTION_THREAD_BUT_ALIVE = "logger.warn.exception.thread.but.alive";
	public static final String ERROR_DTO_NULL = "error.dto.null";
	public static final String LOGGER_WARN_CLEAR_QUEUE_MAXIMUN_REACHED = "logger.warn.clear.queue.maximun.reached";
}

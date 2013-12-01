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
package com.square.synchro.app.noyau.jms.test;

/**
 * Exception pour les tests in container.
 * @author nnadeau - SCUB
 */
public class InContainerException extends Exception {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = -1992756715829158051L;

    /**
     * Constructeur avec message.
     * @param message message
     */
    public InContainerException(final String message) {
        super(message);
    }

    /**
     * Constructeur avec message & cause.
     * @param message message
     * @param cause cause
     */
    public InContainerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructeur avec cause.
     * @param cause cause
     */
    public InContainerException(final Throwable cause) {
        super(cause);
    }
}

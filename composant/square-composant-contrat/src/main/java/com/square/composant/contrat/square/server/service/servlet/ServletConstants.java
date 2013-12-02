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
package com.square.composant.contrat.square.server.service.servlet;

/**
 * Constantes des servlets.
 */
public final class ServletConstants {

    private ServletConstants() {
    }

    /** HEADER_CONTENT_DISPOSITION_ATTACHMENT. */
    public static final String HEADER_CONTENT_DISPOSITION_ATTACHMENT = "attachment; filename=";

    /** HEADER_CONTENT_DISPOSITION. */
    public static final String HEADER_CONTENT_DISPOSITION = "Content-disposition";

    /** TYPE_MIME_PDF. */
    public static final String TYPE_MIME_PDF = "application/pdf";

    /** HEADER_CONTENT_DISPOSITION_INLINE. */
    public static final String HEADER_CONTENT_DISPOSITION_INLINE = "inline; filename=";

    /** BEAN_GRILLE_PRESTATIONS_SERVICE. */
    public static final String BEAN_GRILLE_PRESTATIONS_SERVICE = "grillePrestationsService";

    /** ENCODING_UTF_8. */
    public static final String ENCODING_UTF_8 = "UTF-8";
}

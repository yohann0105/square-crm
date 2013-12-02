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
package com.square.composant.contrat.square.client.util;

import com.google.gwt.http.client.URL;

/**
 * Classe utilitaire pour les appels de servlets.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public final class FormatServletUtil
{
    /**
     * Constructeur.
     */
    private FormatServletUtil()
    {
    }

    /**
     * Entry variable de servlet name=valeur.
     * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
     */
    public static interface ParamServlet
    {
        /**
         * Nom du paramétre.
         * @return le nom du paramétre.
         */
        String getName();

        /**
         * Valeur du paramétre.
         * @return la valeur du paramétre.
         */
        String getValue();
    }

    /**
     * Retourne une instance de ParamServlet.
     * @param name le nom.
     * @param value la valeur.
     * @return l'instance.
     */
    public static ParamServlet getIntanceParamServlet(final String name, final String value)
    {
        return new ParamServlet()
                {
                    /**
                     * {@inheritDoc}
                     */
                    public String getName() {
                        return name;
                    }
                    /**
                     * {@inheritDoc}
                     */
                    public String getValue() {
                        return value;
                    }
                };
    }

    /**
     * Forme une url avec paramétre pour un appel de servlet.
     * @param queryEntries liste des paramétres pour l'appel.
     * @param url .
     * @return une chaine concaténation de l'url avec  ces paramétres.
     */
    public static String formatUrl(final String url, final ParamServlet[] queryEntries)
    {
        return new StringBuffer(url).append(formatUrlParams(queryEntries)).toString();
    }

    /**
     * Former une chaine de paramétres pour les appels de servlet.
     * @param queryEntries liste des paramétres (nom/valeur).
     * @return une chaine avec les paramétres.
     */
    public static String formatUrlParams(final ParamServlet[] queryEntries)
    {
        final StringBuffer sb = new StringBuffer();

        for (int i = 0; i < queryEntries.length; i++)
        {
            final ParamServlet queryEntry = queryEntries[i];
            sb.append(i > 0 ? "&" : "?");

            // encode the characters in the name
            final String encodedName = URL.encodeComponent(queryEntry.getName());
            sb.append(encodedName);

            sb.append("=");

            // encode the characters in the value
            final String encodedValue = URL.encodeComponent(queryEntry.getValue());
            sb.append(encodedValue);
        }
        return sb.toString();
    }
}

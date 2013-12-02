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

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

/**
 * Classe Permet le passage de l'analyser même en presence de wildcard.
 * @author sgoumard . - SCUB
 */
public class AnalysingCustomQueryParser extends org.apache.lucene.queryParser.analyzing.AnalyzingQueryParser {

    /**
     * Classe de caractères espace pour les expressions régulières.
     */
    private static final String WHITE_SPACE = "\\s";

    /**
     * Constructeur.
     * @param matchVersion .
     * @param field .
     * @param analyzer .
     */
    public AnalysingCustomQueryParser(Version matchVersion, String field, Analyzer analyzer) {
        super(matchVersion, field, analyzer);
    }

    @Override
    public Query parse(String termStr) throws ParseException {
        try {
            // Permet de rechercher sur les nom composes malgré la supression du tokeizer sur les espaces blanc
            if (termStr.indexOf(WHITE_SPACE + "TO" + WHITE_SPACE) == -1 && termStr.indexOf(WHITE_SPACE + "AND" + WHITE_SPACE) == -1
                && termStr.indexOf(WHITE_SPACE + "OR" + WHITE_SPACE) == -1 && termStr.indexOf(WHITE_SPACE + "NOT" + WHITE_SPACE) == -1) {
                // On remplace tous les espaces par le caractère ?
                return super.parse(termStr.replaceAll(WHITE_SPACE, "\\\\ "));
            }
            return super.parse(termStr);
        } catch (ParseException e) {
            final QueryParser parser = new QueryParser(Version.LUCENE_24, this.getField(), this.getAnalyzer());
            parser.setAllowLeadingWildcard(this.getAllowLeadingWildcard());
            return parser.parse(termStr.replaceFirst("\\-", "\\\\$0").replaceAll(WHITE_SPACE, "\\\\ "));
        } catch (IllegalArgumentException e) {
            final QueryParser parser = new QueryParser(Version.LUCENE_24, this.getField(), this.getAnalyzer());
            parser.setAllowLeadingWildcard(this.getAllowLeadingWildcard());
            return parser.parse(termStr.replaceAll(WHITE_SPACE, "\\\\ "));
        }
    }
}

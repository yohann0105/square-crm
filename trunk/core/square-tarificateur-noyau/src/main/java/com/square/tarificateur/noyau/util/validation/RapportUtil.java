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
package com.square.tarificateur.noyau.util.validation;

import org.apache.log4j.Logger;

import com.square.tarificateur.noyau.dto.RapportDto;


/**
 * Classe utilitaire pour les rapports.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public final class RapportUtil {

    /**
     * Constructeur privé pour empêcher d'instancier cette classe.
     */
    private RapportUtil() {

    }

    /**
     * Affiche un résumé du rapport d'erreur.
     * @param rapport le rapport
     * @param logger le logger
     */
    public static void logRapport(final RapportDto rapport, final Logger logger) {
        if (logger.isDebugEnabled()) {
            for (String cle : rapport.getRapports().keySet()) {
                if (rapport.getRapports().get(cle).getErreur()) {
                    logger.debug(cle + " : " + rapport.getRapports().get(cle).getMessage());
                }
            }
        }
    }

}

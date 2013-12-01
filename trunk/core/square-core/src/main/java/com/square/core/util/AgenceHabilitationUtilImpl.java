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
package com.square.core.util;

import org.apache.log4j.Logger;

import com.square.core.dao.interfaces.AgenceDao;
import com.square.core.model.Ressources.Agence;
import com.square.core.model.plugin.HabilitationSquarePlugin;

/**
 * Implémentation de l'interface AgenceHabilitationUtil.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class AgenceHabilitationUtilImpl implements AgenceHabilitationUtil {

    /** Logger. */
    private final Logger logger = Logger.getLogger(this.getClass());

    /** Dao sur les agences. */
    private AgenceDao agenceDao;

    /** Plugin des habilitations. */
    private HabilitationSquarePlugin pluginHabilitation;

    @Override
    public Agence getAgenceByCodePostal(String codePostal) {
        final String eidAgence = pluginHabilitation.getEidAgenceByCodePostal(codePostal);
        logger.debug("eid agence = " + eidAgence);

        return agenceDao.rechercheAgenceParEid(eidAgence);
    }

    /**
     * Setter.
     * @param agenceDao the agenceDao to set
     */
    public void setAgenceDao(AgenceDao agenceDao) {
        this.agenceDao = agenceDao;
    }

    /**
     * Setter.
     * @param pluginHabilitation the pluginHabilitation to set
     */
    public void setPluginHabilitation(HabilitationSquarePlugin pluginHabilitation) {
        this.pluginHabilitation = pluginHabilitation;
    }

}

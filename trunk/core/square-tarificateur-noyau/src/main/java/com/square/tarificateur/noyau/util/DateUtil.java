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
package com.square.tarificateur.noyau.util;

import java.util.Calendar;

/**
 * Classe utilitaire pour les dates.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public final class DateUtil {

    /** Constructeur privé. */
    private DateUtil() {
    }

    /**
     * Retourne la date du 1er du mois suivant la date d'aujourd'hui.
     * @return la date du 1er du mois suivant la date d'aujourd'hui
     */
    public static Calendar getDatePremierMoisSuivant() {
        final Calendar date = Calendar.getInstance();
        final int mois = date.get(Calendar.MONTH);
        final int annee = date.get(Calendar.YEAR);
        date.clear();
        final int moisDecembre = 11;
        if (mois != moisDecembre) {
            // Si ce n'est pas le mois de décembre : on retourne le mois suivant et même année
            date.set(annee, mois + 1, 1);
        }
        else {
            // Sinon : on retourne le 1er janvier de l'année suivante
            date.set(annee + 1, 0, 1);
        }
        return date;
    }

}

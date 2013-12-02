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
package com.square.composant.tarificateur.square.server.service.util;

import java.util.Calendar;

import com.square.composant.tarificateur.square.client.model.age.AgeModel;

/**
 * Classe utilitaire permetant le calcul d'age.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public final class CalculAgeUtil {

    /** Constructeur privé. */
    private CalculAgeUtil() { }

    /**
     * Calcul l'âge et l'âge millésimé d'une personne en fnction d'une date de calcul et de la date de naissance.
     * @param dateCalcul la date de calcul
     * @param dateNaissance la date de naissance
     * @return le DTO contenant les âges
     */
    public static AgeModel getAgesCalculesPersonne(Calendar dateCalcul, Calendar dateNaissance) {
     // Calcul de l'âge Millesimé
        final Calendar dateDeNaissance = (Calendar) dateNaissance.clone();
        final int ageMillesime = dateCalcul.get(Calendar.YEAR) - dateNaissance.get(Calendar.YEAR);

        // Calcul de l'âge réel
        int ageReel = ageMillesime;
        dateDeNaissance.add(Calendar.YEAR, ageMillesime);
        if (dateCalcul.before(dateDeNaissance)) {
            ageReel--;
        }

        // Création du DTO
        final AgeModel ageModel = new AgeModel();
        ageModel.setAge(ageReel);
        ageModel.setAgeMillesime(ageMillesime);
        return ageModel;
    }
}

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
/**
 * 
 */
package com.square.core.model.util;

import java.util.List;

/**
 * Classe permettant de Recuperer le resultat d'une recherche FullTextParPagination.
 * @author Goumard Stephane (Scub). - SCUB
 * @param <TypeResult> le type du resultat.
 */
public class ResultatPaginationFullText<TypeResult> {
    /**
     * La liste des resultats.
     */
    final List<TypeResult> listeResultats;

    /**
     * Le nombre total de resultat.
     */
    final Integer nombreTotalDeResultat;

    /**
     * Constructeur par defaut.
     * @param listeResultats la liste des resultats.
     * @param nombreTotalDeResultat le nombre total de resultat.
     */
    public ResultatPaginationFullText(List<TypeResult> listeResultats, Integer nombreTotalDeResultat) {

        this.listeResultats = listeResultats;
        this.nombreTotalDeResultat = nombreTotalDeResultat;
    }

    /**
     * Retourne la liste de resultat.
     * @return the listeResultats
     */
    public List<TypeResult> getListeResultats() {
        return listeResultats;
    }

    /**
     * Retourne le nombre de resultat.
     * @return the nombreTotalDeResultat
     */
    public Integer getNombreTotalDeResultat() {
        return nombreTotalDeResultat;
    }
}

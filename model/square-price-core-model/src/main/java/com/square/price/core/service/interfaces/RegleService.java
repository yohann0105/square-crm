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
package com.square.price.core.service.interfaces;

import com.square.price.core.dto.regles.ReglesRechercheDto;
import com.square.price.core.dto.regles.ReglesReponseDto;


/**
 * Service de récupération de règles applicable à un produit.
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public interface RegleService {
    /**
     * Recherche d'une liste de règles applicables à un produit.
     * @param reglesCriteresDto le DTO contenant les critères de recherche
     * @return un DTO contenant la liste des règles / valeurs applicables
     */
    ReglesReponseDto getListeReglesByProduit(ReglesRechercheDto reglesCriteresDto);
//
//    /**
//     * Recupere une regle par son identifiant.
//     * @param identifiant l'identifiant de la regle
//     * @return la regle
//     */
//    RegleDto getRegleByIdentifiant(Integer identifiant);
//
//    /**
//     * Recupere une valeur de regle par son identifiant.
//     * @param identifiant l'identifiant de la valeur de regle
//     * @return la valeur de regle
//     */
//    ValeurRegleDto getValeurRegleByIdentifiant(Integer identifiant);
}

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
package com.square.fusion.api.service.interfaces;

import com.square.fusion.api.dto.ParametresFusionDto;

/**
 * Interfaces des services liées à la fusion de personne.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface FusionPersonneService {

    /**
     * Prépare la fusion entre 2 personnes. Crée un DTO contenant les différentes informations et paramètres nécessaires à la fusion
     * (personne source, personne cible, différence entre les propriétés et les bénéficiaires, ...).
     * La personne cible sera, en priorité, celle qui possède un contrat. Si les deux personnes ont un contrat, une exception est renvoyée.
     * @param idPersonne1 l'identifiant Square de la première personne à fusionner
     * @param idPersonne2 l'identifiant Square de la seconde personne à fusionner
     * @return le DTO contenant les informations nécessaires à la fusion
     */
    ParametresFusionDto preparerFusion(Long idPersonne1, Long idPersonne2);

    /**
     * Valide une fusion entre deux personnes en fusionnant les propriétés demandées ainsi que les différents objets rattachés (opportunités, actions, ...).
     * @param parametresFusion les paramètres de la fusion
     * @param utilisateur login de l'utilisateur
     */
    void validerFusion(ParametresFusionDto parametresFusion, String utilisateur);

}

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
package com.square.tarificateur.noyau.dao.interfaces;

import java.util.List;

import com.square.tarificateur.noyau.model.personne.Personne;

/**
 * Interface d'accès aux données liées aux personnes.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public interface PersonneDao {

    /**
     * Récupère la personne demandée.
     * @param idPersonne l'identifiant de la personne
     * @return la personne
     */
    Personne getPersonne(Long idPersonne);

    /**
     * Récupère la personne demandée.
     * @param eidPersonne l'identifiant externe de la personne
     * @return la personne
     */
    Personne getPersonneByEid(Long eidPersonne);

    /**
     * Sauvegarde la personne passée en paramètre dans la base de données.
     * @param personne la personne à créer dans la base de données.
     */
    void savePersonne(Personne personne);

    /**
     * Récupère la liste des personnes associées à la personne Square ayant l'EID demandé.
     * @param eidPersonne l'EID (Square) de la personne
     * @return la liste des personnes
     */
    List<Personne> getListePersonnesByEid(Long eidPersonne);
}

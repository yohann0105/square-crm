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
package com.square.core.dao.interfaces;

import java.util.List;

import com.square.core.model.Ressources.Agence;
import com.square.core.model.dto.DimensionCriteresRechercheDto;

/**
 * Dao pour agence.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface AgenceDao {
    /**
     * recherche d'une agence par son identifiant.
     * @param identifiant l'identifiant.
     * @return l'agence de la personne.
     */
    Agence rechercheAgenceParId(Long identifiant);

    /**
     * Méthode de sauvegarde d'une agence.
     * @param agence l'agence à créer.
     */
    void creerAgence(Agence agence);

    /**
     * Recherche des agences par nom.
     * @param criteres critère de recherche de l'agence.
     * @return la liste des agences trouvées
     */
    List<Agence> rechercherAgenceParCriteres(DimensionCriteresRechercheDto criteres);

    /**
     * Recherche une agence par son identifiant extérieur.
     * @param eid l'identifiant extérieur.
     * @return l'agence trouvée.
     */
    Agence rechercheAgenceParEid(String eid);

    /**
     * Recherche des agences par leurs identifiants.
     * @param listeIds les identifiants des agences.
     * @return la liste des agences.
     */
    List<Agence> rechercheAgencesParIds(List<Long> listeIds);
}

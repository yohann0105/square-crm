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

import org.apache.lucene.queryParser.ParseException;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;

import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.DimensionCriteresRechercheRessourceDto;
import com.square.core.model.dto.RessourceCriteresRechercheDto;
import com.square.core.model.util.ResultatPaginationFullText;

/**
 * Dao pour la ressource.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface RessourceDao {
    /**
     * Recherche d'uen ressource par identifiant.
     * @param identifiant l'identifiant.
     * @return la ressource trouvée.
     */
    Ressource rechercherRessourceParId(Long identifiant);

    /**
     * Recherche ressource par identifiant extérieur.
     * @param eid l'identifiant extérieur
     * @return la ressource trouvée;
     */
    Ressource rechercherRessourceParEid(String eid);

    /**
     * Rechercher ressource par critères.
     * @param criteres les critères de recherche.
     * @return la ressource trouvée.
     */
    List<Ressource> rechercherRessourceParCriteres(DimensionCriteresRechercheRessourceDto criteres);

    /**
     * Permet une recherche full text sur les ressources.
     * @param criteres requete string lucene
     * @return la liste des Ressources et le nombre total de resultat non affectés par la pagination.
     * @throws ParseException probléme lors de l'analyse de la requete lucene.
     */
    ResultatPaginationFullText<Ressource> rechercherFullTextRessource(RemotePagingCriteriasDto<RessourceCriteresRechercheDto> criteres) throws ParseException;

    /**
     * Crée une nouvelle ressource.
     * @param ressource la ressource à créer.
     */
    void creerRessource(Ressource ressource);

    /**
     * Recherche les identifiants des ressources par critères.
     * @param criteres les critères de recherche.
     * @return la liste des identifiants des ressources correspondants.
     * @throws ParseException probléme lors de l'analyse de la requete lucene.
     */
    List<Long> rechercherFullTextIdsRessources(RessourceCriteresRechercheDto criteres) throws ParseException;
}

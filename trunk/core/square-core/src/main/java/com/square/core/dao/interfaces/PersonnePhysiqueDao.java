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

import com.square.core.model.PersonnePhysique;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonnePhysiqueIdCriteresRechercheDto;
import com.square.core.model.util.ResultatPaginationFullText;

/**
 * Dao pour les personnes physiques.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface PersonnePhysiqueDao {

    /**
     * Créer une personne physique.
     * @param personne personne à créer.
     */
    void creerPersonnePhysique(PersonnePhysique personne);

    /**
     * Recherche une liste de personne par une requête.
     * @param requete la requete.
     * @return la liste des Personnes Physiques.
     */
    List<PersonnePhysique> recherchePersonneParRequete(String requete);

    /**
     * Permet une recherche full text sur les personnes.
     * @param criteres requete string lucene
     * @return la liste des Personnes et le nombre total de resultat non affectés par la pagination.
     */
    ResultatPaginationFullText<PersonnePhysique> rechercheFullTextPersonne(RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres)
        throws ParseException;

    /**
     * Recherche une personne par son identifiant.
     * @param id l'identifiant de la personne à rechercher.
     * @return renvoi une personne contenant toutes les informations.
     */
    PersonnePhysique rechercherPersonneParId(Long id);

    /**
     * Recuperer une personne par son identifiant extérieur.
     * @param id l'identifiant de la personne à retourner.
     * @return la personne.
     */
    PersonnePhysique rechercherPersonneParIdExt(final String id);

    /**
     * Renvoie une liste d'id de personne par critère.
     * @param criteres les critères de recherche.
     * @return la liste des ids de personnes physiques remplissant ces critères.
     */
    List<Long> rechercherIdPersonneParCriteres(PersonnePhysiqueIdCriteresRechercheDto criteres);
}

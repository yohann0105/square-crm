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

import com.square.core.model.PersonneMorale;
import com.square.core.model.dto.PersonneMoralCriteresRechercheDto;
import com.square.core.model.dto.PersonneMoraleSimpleDto;
import com.square.core.model.util.ResultatPaginationFullText;

/**
 * Dao sur les personnes morales.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface PersonneMoraleDao {
    /**
     * Récupérer une personne morale par son identifiant.
     * @param id l'identifiant de la personne morale.
     * @return la personne trouvée.
     */
    PersonneMorale rechercherPersonneMoraleParId(Long id);

    /**
     * Créer une personneMoraleSimpleDto à partir de son identifiant.
     * @param id l'identifiant.
     * @return PersonneMoraleSimpleDto la personne morale simple.
     */
    PersonneMoraleSimpleDto rechercherPersonneMoraleSimpleParId(Long id);

    /**
     * Recherche une liste de personne morales par une requête.
     * @param requete la requete.
     * @return la liste des Personnes Morales.
     */
    List<PersonneMorale> recherchePersonnesMoralesParRequete(String requete);

    /**
     * Recherche des personnes morales par critères (Recherche full text).
     * @param criteres les critères de recherche
     * @return la liste des personnes morales correspondant.
     */
    ResultatPaginationFullText<PersonneMorale> rechercherPersonneMoraleParCriteres(RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto> criteres)
        throws ParseException;

    /**
     * Permet la création d'une personne Morale.
     * @param personne la personne à créer
     */
    void creerPersonneMorale(final PersonneMorale personne);
}

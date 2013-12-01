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
package com.square.core.service.interfaces;

import java.util.List;

import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;

import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.PersonneMoralCriteresRechercheDto;
import com.square.core.model.dto.PersonneMoraleDto;
import com.square.core.model.dto.PersonneMoraleRechercheDto;
import com.square.core.model.dto.PersonneMoraleSimpleDto;

/**
 * Interfaces des services pour une personne morale.
 * @author cblanchard - SCUB
 */
public interface PersonneMoraleService {

    /**
     * Recherche full text des personnes morales.
     * @param criteres les critères de recherches
     * @return la liste des personnes morales.
     */
    RemotePagingResultsDto<PersonneMoraleRechercheDto> rechercherPersonneMoraleParCriteres(
        RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto> criteres);

    /**
     * Retourne un fichier contenant les resultats de la recherche.
     * @param criteres le texte à rechercher.
     * @return le contenu du fichier
     */
    FichierDto exporterRecherchePersonneMoraleParCriteres(RemotePagingCriteriasDto<PersonneMoralCriteresRechercheDto> criteres);

    /**
     * Recherche d'une personne morale par son identifiant.
     * @param identifiant l'identifiant de la personne
     * @return la personne morale trouvée
     * @author mlamine - SCUB
     */
    PersonneMoraleDto recherchePersonneMoraleParId(Long identifiant);

    /**
     * Recherche d'une personne morale simple par son identifiant.
     * @param identifiant l'identifiant de la personne
     * @return la personne morale simple trouvée
     * @author mlamine - SCUB
     */
    PersonneMoraleSimpleDto recherchepersonneMoraleSimpleParId(Long identifiant);

    /**
     * Recherche une liste de personne par une requête.
     * @param requete la requete.
     * @return la liste des identifiants des personnes morales.
     */
    List<PersonneMoraleDto> rechercherPersonnesMoralesParRequete(String requete);

    /**
     * Crée une personne morale.
     * @param personne la personne morale a créer.
     * @param adresse l'adresse de la personne morale.
     * @return la personne morale créée.
     */
    PersonneMoraleDto creerPersonneMorale(PersonneMoraleDto personne, AdresseDto adresse);

    /**
     * Indexe une liste de personnes morales.
     * @param listeIdsPersonnesAIndexer liste des identifiants des personnes morales à indexer.
     */
    void indexerListePersonnesMorales(List<Long> listeIdsPersonnesAIndexer);
}

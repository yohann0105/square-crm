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

import com.square.core.model.dto.RessourceCriteresRechercheDto;
import com.square.core.model.dto.RessourceDto;
import com.square.core.model.dto.RessourceRechercheDto;

/**
 * Service de gestion des ressources.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public interface RessourceService {
    /**
     * Recherche full text sur les ressources.
     * @param criteres requete string lucene
     * @return la liste des Ressources et le nombre total de resultat non affectés par la pagination.
     */
    RemotePagingResultsDto<RessourceRechercheDto> rechercherRessourceFullTextParCriteres(RemotePagingCriteriasDto<RessourceCriteresRechercheDto> criteres);

    /**
     * Retourne un fichier contenant les resultats de la recherche.
     * @param criteres le texte à rechercher.
     * @return le contenu du fichier
     */
    FichierDto exporterRechercheRessourceFullTextParCriteres(RemotePagingCriteriasDto<RessourceCriteresRechercheDto> criteres);

    /**
     * Recherche une ressource par son identifiant et renvoi toutes les informations de cette ressource.
     * @param id l'identifiant de la ressource à retrouver
     * @return la ressource contenant tous les informations
     */
    RessourceDto rechercherRessourceParIdentifiant(Long id);

    /**
     * Recherche une ressource par son external id et renvoi toutes les informations de cette ressource.
     * @param eid l'identifiant externe de la ressource à retrouver
     * @return la ressource contenant tous les informations
     */
    RessourceDto rechercherRessourceParEid(String eid);

    /**
     * Crée une ressource.
     * @param ressourceDto la ressource à créer.
     * @return la ressource créée.
     */
    RessourceDto creerRessource(RessourceDto ressourceDto);

    /**
     * Modifie une ressource.
     * @param ressourceDto la ressource à modifier.
     * @return la ressource modifiée.
     */
    RessourceDto updateRessource(RessourceDto ressourceDto);

    /**
     * Active ou désactive une ressource.
     * @param idRessource l'identifiant de la ressource à activer.
     * @param isActif true pour activer, false pour désactiver.
     */
    void activerRessource(Long idRessource, boolean isActif);

    /**
     * Détermine si une ressource existe.
     * @param eidRessource l'identifiant externe d'une ressource.
     * @return la ressource existante.
     */
    RessourceDto isRessourceExiste(String eidRessource);

    /**
     * Recherche les identifiants des ressources correspondant aux critères de recherche.
     * @param criteres les critères de recherche.
     * @return la liste des identifiants des ressources correspondant aux critères.
     */
    List<Long> rechercherIdsRessourcesFullTextParCriteres(RessourceCriteresRechercheDto criteres);
}

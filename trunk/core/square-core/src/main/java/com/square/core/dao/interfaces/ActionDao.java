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

import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;

import com.square.core.model.Action;
import com.square.core.model.Ressources.Ressource;
import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.ActionRechercheDto;
import com.square.core.model.dto.CritereActionSyntheseDto;
import com.square.core.model.util.CritereRechercheAction;

/**
 * Dao pour les actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface ActionDao {

    /**
     * Recherche d'une action par critère.
     * @param criteres les critères de recherche
     * @return la liste des actions trouvées.
     */
    List<ActionRechercheDto> rechercheActionParCriteres(RemotePagingCriteriasDto<ActionCritereRechercheDto> criteres);

    /**
     * Calcul du nombre total de résultat.
     * @param criteres les critères de recherche
     * @return le nombre total d'actions trouvé.
     */
    int nombreTotalAction(RemotePagingCriteriasDto<ActionCritereRechercheDto> criteres);

    /**
     * Sauvegarde une action.
     * @param action l'action à sauvegarder
     */
    void creerAction(Action action);

    /**
     * Supprime une action.
     * @param action l'action à supprimer
     */
    void supprimerAction(Action action);

    /**
     * Supprime logiquement une action.
     * @param action l'action a supprimer.
     */
    void supprimerLogiquement(Action action);

    /**
     * Recherche toutes les actions sources correspondant à des critères de recherche.
     * @param critereActionSyntheseDto les critères de recherche
     * @return la liste des actions sources correspondant aux critères de recherche
     */
    List<Action> rechercherActionsSources(CritereActionSyntheseDto critereActionSyntheseDto);

    /**
     * Recherche des actions liées d'une action source.
     * @param idActionSource l'identifiant de l'action source
     * @param idOpportunite l'identifiant de l'opportunité
     * @param filtrerDateCreation bolléen pour indiquer si on filtre ou non sur la date de création
     * @return la liste des actions liées de l'action source
     */
    List<Action> rechercherActionsLiees(Long idActionSource, Long idOpportunite, Boolean filtrerDateCreation);

    /**
     * Recherche une action par son identifiant.
     * @param idAction l'identifiant de l'action
     * @return l'action correspondant à l'identifiant
     */
    Action rechercherActionParId(Long idAction);

    /**
     * Recherche une action par son identifiant ext.
     * @param idExt l'identifiant de l'action
     * @return l'action correspondant à l'identifiant
     */
    Action rechercherActionParIdExterieur(Long idExt);

    /**
     * Rechercher une action par opportunite.
     * @param idOpportunite l'identifiant de l'opportunite
     * @return la liste des actions ayant comme affectation l'opportunite
     */
    List<Action> rechercherActionsParOpportunite(Long idOpportunite);

    /**
     * Recherche les actions par criteres.
     * @param criteres les critères de recherche
     * @param ressource la ressource connectée
     * @return la liste des actions correspondant
     */
    List<Action> rechercherActionParCriteresEtRessource(CritereRechercheAction criteres, Ressource ressource);

    /**
     * Rechercher une action par opportunite.
     * @param idOpportunite l'identifiant de l'opportunite
     * @return la liste des actions ayant comme affectation l'opportunite
     */
    List<Action> rechercherActionsSourcesParOpportunite(Long idOpportunite);
}

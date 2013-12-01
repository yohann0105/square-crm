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
import java.util.Stack;

import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;

import com.square.core.model.dto.ActionCreationDto;
import com.square.core.model.dto.ActionCritereRechercheDto;
import com.square.core.model.dto.ActionDto;
import com.square.core.model.dto.ActionModificationDto;
import com.square.core.model.dto.ActionRechercheDto;
import com.square.core.model.dto.ActionResultatDto;
import com.square.core.model.dto.ActionSyntheseDto;
import com.square.core.model.dto.CritereActionSyntheseDto;
import com.square.core.model.dto.DocumentDto;
import com.square.core.model.dto.HistoriqueCommentaireDto;

/**
 * Service de gestion des actions.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface ActionService {

    /**
     * La création d'une action.
     * @param action l'action à enregistrer
     * @return l'action enregistrée
     */
    ActionCreationDto creerAction(ActionCreationDto action);

    /**
     * Supprime l'action correspondant à l'identifiant passé en paramètre.
     * @param idAction l'identifiant de l'action à supprimer
     */
    void supprimerAction(Long idAction);

    /**
     * Supprime les actions logiquement correspondant à l'identifiant d'une personne passé en paramètre.
     * @param idPersonne l'identifiant de la personne
     */
    void supprimerActionsPersonne(Long idPersonne);

    /**
     * Recherche d'une action par critère.
     * @param criteres le critère de recherche.
     * @return la liste des actions.
     */
    RemotePagingResultsDto<ActionRechercheDto> rechercherActionParCritere(RemotePagingCriteriasDto<ActionCritereRechercheDto> criteres);

    /**
     * Transfère des actions à une ressource.
     * @param criteres le critère de recherche.
     * @param idAgenceCible agence cible
     * @param idRessourceCible ressource cible
     */
    void transfererActionsParCritere(ActionCritereRechercheDto criteres, Long idAgenceCible, Long idRessourceCible);

    /**
     * Retourne un fichier contenant les resultats de la recherche.
     * @param criteres le texte à rechercher.
     * @return le contenu du fichier
     */
    FichierDto exporterRechercheActionsParCriteres(RemotePagingCriteriasDto<ActionCritereRechercheDto> criteres);

    /**
     * Recherche les actions synthèse d'une personne.
     * @param critere les critères de recherche
     * @return la liste des piles d'actions
     */
    List<Stack<ActionSyntheseDto>> recupererActionsSynthese(CritereActionSyntheseDto critere);

    /**
     * Recherche une action par son identifiant.
     * @param idAction l'identifiant de l'action à rechercher
     * @return action correspondant à l'identifiant
     */
    ActionDto rechercherActionParIdentifiant(Long idAction);

    /**
     * Recherche une action par son identifiant exterieur.
     * @param idExterieur l'identifiant exterieur de l'action à rechercher
     * @return action correspondant à l'identifiant
     */
    ActionDto rechercherActionParIdentifiantExterieur(String idExterieur);

    /**
     * Recherche les messages d'une action.
     * @param idAction l'identifiant de l'action
     * @return l'historique des notes à partir de cette action
     */
    List<HistoriqueCommentaireDto> rechercherNotesActions(Long idAction);

    /**
     * Modifie une action.
     * @param actionModificationDto les nouvelles informations de l'action
     * @return les résultats modifiés
     */
    ActionResultatDto modifierAction(ActionModificationDto actionModificationDto);

    /**
     * Modifie une action (pas de règle métier, utilisée pour les scripts internes & batchs...
     * @param actionDto les nouvelles informations de l'action
     * @return les résultats modifiés
     */
    ActionDto modifierAction(ActionDto actionDto);

    /**
     * Transfère les actions d'une personne source vers une personne cible.
     * @param idPersonneSource l'identifiant de la personne physique source
     * @param idPersonneCible l'identifiant de la personne physique cible
     */
    void transfererActionsPersonne(Long idPersonneSource, Long idPersonneCible);

    /**
     * Définit la liste de documents attachés à une action.
     * @param idAction l'id de l'action
     * @param documents les documents
     */
    void attacherDocuments(Long idAction, List<DocumentDto> documents);

    /**
     * service de notification d'une action.
     * @param idAction id de l'action à notifier
     */
    void notifier(Long idAction);

    /**
     * Compte un nombre d'actions en fonction de critères.
     * @param criteres les critères
     * @return le nombre d'actions en fonction de critères
     */
    int countActionsParCriteres(ActionCritereRechercheDto criteres);
}

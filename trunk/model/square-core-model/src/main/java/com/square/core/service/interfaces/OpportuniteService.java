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

import com.square.core.model.dto.OpportuniteCriteresRechercheDto;
import com.square.core.model.dto.OpportuniteDto;
import com.square.core.model.dto.OpportuniteMaJStatutDto;
import com.square.core.model.dto.OpportuniteModificationDto;
import com.square.core.model.dto.OpportuniteSimpleDto;

/**
 * Interface de sevives pour les opportunités.
 * @author cblanchard - SCUB
 */
public interface OpportuniteService {

    /**
     * Service de création d'opportunité.
     * @param opportuniteDto le dto contenant les informations de création
     * @return l'opportunité créée
     */
    OpportuniteDto creerOpportunite(OpportuniteDto opportuniteDto);

    /**
     * Service de récupération d'opportunité.
     * @param criteres les critères de recherche
     * @return la liste des opportunités correspondant
     */
    List<OpportuniteSimpleDto> rechercherOpportuniteParCriteres(OpportuniteCriteresRechercheDto criteres);

    /**
     * Service de comptage d'opportunités.
     * @param criteres les critères de recherche
     * @return la liste des opportunités correspondant
     */
    int countOpportuniteParCriteres(OpportuniteCriteresRechercheDto criteres);

    /**
     * Service de modification d'une opportunité.
     * @param opportuniteModificationDto les informations de modification
     */
    void modifierOpportunite(OpportuniteModificationDto opportuniteModificationDto);

    /**
     * Service de mise a jour du statut d'une opportunité.
     * @param opportuniteMaJStatutDto le infos du statut
     */
    void mettreAJourStatutOpportunite(OpportuniteMaJStatutDto opportuniteMaJStatutDto);

    /**
     * Détermine si une personne possède déjà ou non des opportunités.
     * @param idPersonne l'identifiant de la personne.
     * @return true si la personne possède des opportunités, false si non.
     */
    boolean hasPersonneOpportunite(Long idPersonne);

    /**
     * Transfère la liste des opportunités d'une personne source vers une personne cible.
     * @param idPersonneSource l'identifiant de la personne physique source
     * @param idPersonneCible l'identifiant de la personne physique cible
     */
    void transfererOpportunites(Long idPersonneSource, Long idPersonneCible);

    /**
     * Teste si la famille de la personne principale est éligible pour la création d'une opportunité.
     * <br/>Teste si la personne principale ou son conjoint possède une adresse principale.
     * @param idPersonnePrincipale l'identifiant de la personne principale
     * @return true si la famille est éléigible, false sinon
     */
    boolean isFamilleEligiblePourOpportunite(Long idPersonnePrincipale);

    /**
     * Service de suppression d'une opportunite.
     * @param idOpportunite L'id de l'opportunite a supprimer.
     */
    void supprimerOpportunite(Long idOpportunite);

    /**
     * Détermine si une personne possède des opportunités dans certains statuts.
     * @param idPersonne l'identifiant de la personne.
     * @param listeIdsStatuts la liste des statuts à vérifier.
     * @return true si la personne possède au moins une opportunité correspondant aux statuts, false si non.
     */
    boolean hasPersonneOpportuniteByStatuts(Long idPersonne, List<Long> listeIdsStatuts);
}

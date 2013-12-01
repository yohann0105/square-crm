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

import com.square.core.model.Opportunite;
import com.square.core.model.dto.OpportuniteCriteresRechercheDto;

/**
 * Dao sur les opportunités.
 * @author cblanchard - SCUB
 */
public interface OpportuniteDao {

    /**
     * Recherche les opportunités d'une personne.
     * @param criteres les critères
     * @return la liste des opportunités d'une personne
     */
    List<Opportunite> rechercherOpportunitesParCriteres(OpportuniteCriteresRechercheDto criteres);

    /**
     * Compter les opportunités d'une personne.
     * @param criteres les critères
     * @return le nombre d'opportunités
     */
    Integer countOpportunitesParCriteres(OpportuniteCriteresRechercheDto criteres);

    /**
     * Créer une opportunité.
     * @param opportunite l'opportunité à créer
     */
    void creerOpportunite(Opportunite opportunite);

    /**
     * Recherche une opportunité par son identifiant.
     * @param idOpportunite l'identifiant de l'opportunité
     * @return l'opportunité correspondant
     */
    Opportunite rechercherOpportuniteParId(Long idOpportunite);

    /**
     * Rechercher la sequence.
     * @return la sequence.
     */
    Long rechercherSequence();

    /**
     * Supprimer une opportunite.
     * @param idOpportunite l'id de l'opportunite a supprimer.
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

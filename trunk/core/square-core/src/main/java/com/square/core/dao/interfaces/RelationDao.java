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

import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;

import com.square.core.model.Relation;
import com.square.core.model.dto.RelationCriteresRechercheDto;

/**
 * Dao pour les relations.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public interface RelationDao {
    /**
     * Création d'une relation.
     * @param relation creer la relation.
     */
    void creerRelation(Relation relation);

    /**
     * Recherche la liste des relations par critères.
     * @param criteres les criteres de recherche .
     * @return la liste des relations correspondant .
     */
    List<Relation> rechercherRelationsParCriteres(RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteres);

    /**
     * Recherche les ids des relations a desactiver à la date.
     * @param date la date de desactivation
     * @param pagination le nombre à retourner
     * @return la liste des ids relations correspondant
     */
    List<Long> rechercherIdsRelationsADesactiver(Calendar date, int pagination);

    /**
     * Compte le nb de relations par critères.
     * @param criteres les criteres de recherche .
     * @return la liste des relations correspondant .
     */
    int countRelationsParCriteres(RelationCriteresRechercheDto criteres);

    /**
     * Recuperer une relation par son identifiant.
     * @param id le l'identifaint de la relation.
     * @return la relation.
     */
    Relation rechercherRelationParId(final Long id);

    /**
     * Desactive les relations.
     * @param idsRelationsADesactiver idsRelationsADesactiver
     * @return le nombre
     */
    int desactiverRelations(List<Long> idsRelationsADesactiver);
}

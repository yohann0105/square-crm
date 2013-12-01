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

import com.square.core.model.Campagne;
import com.square.core.model.dto.CampagneCriteresRechercheDto;
import com.square.core.model.dto.CampagneCriteresRechercheLibelle;

/**
 * Dao sur les campagnes.
 * @author cblanchard - SCUB
 */
public interface CampagneDao {

    /**
     * Recherche les campagnes par critères.
     * @param criteres les critères de recherche
     * @return la liste des campagnes correspondant.
     */
    List<Campagne> rechercherCampagnesParCriteres(RemotePagingCriteriasDto<CampagneCriteresRechercheDto> criteres);

    /**
     * Renvoi le total de résultat correspondant à la recherche.
     * @param criteres les critères de recherche
     * @return le nombre de résultat correspondant
     */
    int getTotalResultat(RemotePagingCriteriasDto<CampagneCriteresRechercheDto> criteres);

    /**
     * Recherche les campagnes par leur libelle.
     * @param criteres le libelle de la campagne
     * @return la liste de campagnes correspondant
     */
    List<Campagne> rechercheCampagnesParLibelle(CampagneCriteresRechercheLibelle criteres);

    /**
     * Créer une campagne.
     * @param campagne la campagne à créer
     */
    void creerCampagne(Campagne campagne);

    /**
     * Recherche une campagne par identifiant.
     * @param id l'identifiant de la campagne à rechercher
     * @return la campagne retrouvée
     */
    Campagne rechercherCampagneParId(Long id);

    /**
     * Rechercher la sequence.
     * @return la sequence.
     */
    Long rechercherSequence();
}

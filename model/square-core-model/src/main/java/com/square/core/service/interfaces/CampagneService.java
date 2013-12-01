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
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;

import com.square.core.model.dto.CampagneCriteresRechercheDto;
import com.square.core.model.dto.CampagneCriteresRechercheLibelle;
import com.square.core.model.dto.CampagneDto;
import com.square.core.model.dto.CampagneRechercheDto;

/**
 * Services sur les campagnes.
 * @author cblanchard - SCUB
 */
public interface CampagneService {

    /**
     * Recherche les campagnes par critères.
     * @param criteres les critères de recherche
     * @return les campagnes correspondant
     */
    RemotePagingResultsDto<CampagneRechercheDto> rechercherCampagnesParCriteres(RemotePagingCriteriasDto<CampagneCriteresRechercheDto> criteres);

    /**
     * Retourne un fichier contenant les resultats de la recherche.
     * @param criteres le texte à rechercher.
     * @return le contenu du fichier
     */
    FichierDto exporterRechercheCampagnesParCriteres(RemotePagingCriteriasDto<CampagneCriteresRechercheDto> criteres);

    /**
     * Recherche les campagnes par libelle.
     * @param criteres les critères de recherche
     * @return les informations de la campagne correspondant
     */
    List<IdentifiantLibelleDto> rechercherCampagnesParLibelle(CampagneCriteresRechercheLibelle criteres);

    /**
     * Creer une campagne.
     * @param campagneDto la campagne à créer
     * @return la campagne crée
     */
    CampagneDto creerCampagne(CampagneDto campagneDto);

    /**
     * Recherche une campagne par identifiant.
     * @param id l'identifiant de la campagne à rechercher
     * @return la campagne correspondant
     */
    CampagneDto rechercherCampagnesParId(Long id);

    /**
     * Modification d'une campagne.
     * @param campagneDto la campagne contenant les nouvelles informations
     * @return la campagne modifiée
     */
    CampagneDto modifierCampagne(CampagneDto campagneDto);
}

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
/**
 * 
 */
package com.square.adherent.noyau.dao.interfaces.prestation;

import java.util.List;

import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;

import com.square.adherent.noyau.dto.prestation.EntetePrestationResultatRechercheDto;
import com.square.adherent.noyau.dto.prestation.PrestationCriteresRechercheDto;
import com.square.adherent.noyau.dto.prestation.PrestationResultatRechercheDto;

/**
 * Interface recherche de decompte.
 * @author Goumard Stephane (Scub). - SCUB
 */
public interface DecompteDao {
    /**
     * Recherche les prestations par critères.
     * @param criteres les critères de recherche
     * @return la liste des prestations correspondant
     */
    List<PrestationResultatRechercheDto> rechercherPrestationParCritreres(RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres);

    /**
     * Recherche les entetes de prestations par critères.
     * @param criteres les critères de recherche
     * @return la liste des entetes de prestations correspondant
     */
    List<EntetePrestationResultatRechercheDto> rechercherEntetesPrestationParCritreres(RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres);

    /**
     * Compte les prestations par critères.
     * @param criteres les critères de recherche
     * @return le nombre
     */
    Integer getNombreTotalPrestationParCritreres(RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres);

    /**
     * Compte les entetes de prestations par critères.
     * @param criteres les critères de recherche
     * @return le nombre
     */
    Integer getNombreTotalEntetesPrestationParCritreres(RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres);
}

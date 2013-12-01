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
package com.square.adherent.noyau.service.interfaces;

import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;

import com.square.adherent.noyau.dto.cotisation.CodeAiaLibelleDto;
import com.square.adherent.noyau.dto.cotisation.CotisationsCriteresRechercheDto;
import com.square.adherent.noyau.dto.cotisation.RetourCotisationDto;

/**
 * Service de gestion des cotisations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface CotisationService {

    /**
     * Récupère des cotisations.
     * @param criteresCotisations les criteres de recherche de cotisations
     * @return les cotisations
     */
    RetourCotisationDto recupererCotisations(RemotePagingCriteriasDto<CotisationsCriteresRechercheDto> criteresCotisations);

    /**
     * Recherche des modes de paiement par le libelle.
     * @param libelle le libelle de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherModesPaiementParCriteres(String libelle);

    /**
     * Recherche des situations par criteres.
     * @param libelle le libelle de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<CodeAiaLibelleDto> rechercherSituationsParCriteres(String libelle);

}

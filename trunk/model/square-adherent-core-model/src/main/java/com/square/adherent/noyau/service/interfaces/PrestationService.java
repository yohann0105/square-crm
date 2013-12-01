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
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;

import com.square.adherent.noyau.dto.DimensionAdherentCriteresRechercheDto;
import com.square.adherent.noyau.dto.IdentifiantEidLibelleDto;
import com.square.adherent.noyau.dto.adherent.BeneficiairePrestationDto;
import com.square.adherent.noyau.dto.adherent.prestations.CoordonneesBancairesRemboursementDto;
import com.square.adherent.noyau.dto.prestation.EntetePrestationResultatRechercheDto;
import com.square.adherent.noyau.dto.prestation.PrestationCriteresRechercheDto;
import com.square.adherent.noyau.dto.prestation.PrestationResultatRechercheDto;

/**
 * Service de gestion des prestations.
 * @author Mohamed Ouled Amor (mohamed.ouledamor@gmail.com) - SCUB
 */
public interface PrestationService {

    /**
     * Recherche les prestations par critères.
     * @param criteres les critères de recherche
     * @return la liste des prestations correspondant
     */
    RemotePagingResultsDto<PrestationResultatRechercheDto> rechercherPrestationParCritreres(RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres);

    /**
     * Permet une recherche de dimension sur les beneficiaires d'un assure.
     * @param uidAssure identifiant de l'assure.
     * @param prenom une partie du prenom de l'assure.
     * @return la liste des beneficiaires.
     */
    List<BeneficiairePrestationDto> recupererBeneficiairesPrestations(final Long uidAssure, final String prenom);

    /**
     * Recherche des origines de decomptes par criteres.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantLibelleDto> rechercherOriginesDecompteParCriteres(DimensionAdherentCriteresRechercheDto criteres);

    /**
     * Recherche des actes de decomptes par criteres.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     */
    List<IdentifiantEidLibelleDto> rechercherActesDecompteParCriteres(DimensionAdherentCriteresRechercheDto criteres);

    /**
     * Recherche les entetes de prestations par critères.
     * @param criteres les critères de recherche
     * @return la liste des entetes de prestations correspondant
     */
    RemotePagingResultsDto<EntetePrestationResultatRechercheDto> rechercherEntetesPrestationParCritreres(
        RemotePagingCriteriasDto<PrestationCriteresRechercheDto> criteres);

    /**
     * Récupère les coordonnées bancaires utilisées pour rembourser les prestations d'un adhérent et de ses bénéficiaires.
     * @param uidAdherent l'identifiant de l'adhérent
     * @return les coordonnées bancaires utilisées pour rembourser les prestations.
     */
    List<CoordonneesBancairesRemboursementDto> getCoordonneesBancairesRemboursements(Long uidAdherent);
}

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
package com.square.composant.cotisations.server.service;

import java.util.List;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingCriteriasGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;

import com.square.adherent.noyau.dto.adherent.contrat.CritereRechercheContratDto;
import com.square.adherent.noyau.dto.cotisation.CotisationsCriteresRechercheDto;
import com.square.adherent.noyau.dto.cotisation.RetourCotisationDto;
import com.square.adherent.noyau.service.interfaces.ContratService;
import com.square.adherent.noyau.service.interfaces.CotisationService;
import com.square.composant.cotisations.client.model.CodeAiaLibelleModel;
import com.square.composant.cotisations.client.model.CotisationModel;
import com.square.composant.cotisations.client.model.CotisationsCriteresRechercheModel;
import com.square.composant.cotisations.client.model.RetourCotisationModel;
import com.square.composant.cotisations.client.service.CotisationsServiceGwt;

/**
 * Implémentation de l'interface CotisationsServiceGwt.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class CotisationsServiceGwtImpl implements CotisationsServiceGwt {

    private MapperDozerBean mapperDozerBean;

    private CotisationService cotisationService;

    private ContratService contratService;

    @Override
    public RetourCotisationModel recupererCotisations(RemotePagingCriteriasGwt<CotisationsCriteresRechercheModel> criteresCotisations) {
        final CotisationsCriteresRechercheDto criteriasDto = mapperDozerBean.map(criteresCotisations.getCriterias(), CotisationsCriteresRechercheDto.class);
        final RemotePagingCriteriasDto<CotisationsCriteresRechercheDto> criteresDto =
            new RemotePagingCriteriasDto<CotisationsCriteresRechercheDto>(criteriasDto, criteresCotisations.getFirstResult(), criteresCotisations
                    .getMaxResult());
        final RetourCotisationDto retourCotisation = cotisationService.recupererCotisations(criteresDto);

        final List<CotisationModel> listeGwt = mapperDozerBean.mapList(retourCotisation.getResultatsCotisation().getListResults(), CotisationModel.class);
        final RemotePagingResultsGwt<CotisationModel> resultsGwt = new RemotePagingResultsGwt<CotisationModel>();
        resultsGwt.setListResults(listeGwt);
        resultsGwt.setTotalResults(retourCotisation.getResultatsCotisation().getTotalResults());

        final RetourCotisationModel retourCotisationModel = new RetourCotisationModel();
        retourCotisationModel.setResultatsCotisation(resultsGwt);
        retourCotisationModel.setSolde(retourCotisation.getSolde());
        return retourCotisationModel;
    }

    @Override
    public List<IdentifiantLibelleGwt> rechercherModesPaiementParCriteres(String libelle) {
        return mapperDozerBean.mapList(cotisationService.rechercherModesPaiementParCriteres(libelle), IdentifiantLibelleGwt.class);
    }

    @Override
    public List<CodeAiaLibelleModel> rechercherSituationsParCriteres(String libelle) {
        return mapperDozerBean.mapList(cotisationService.rechercherSituationsParCriteres(libelle), CodeAiaLibelleModel.class);
    }

    @Override
    public List<IdentifiantLibelleGwt> recupererContrats(Long uidAssure, String libelle) {
        final CritereRechercheContratDto criteres = new CritereRechercheContratDto();
        criteres.setIdAssure(uidAssure);
        criteres.setNumeroContrat(libelle);
        return mapperDozerBean.mapList(contratService.getContratsSimpleByCriteres(criteres), IdentifiantLibelleGwt.class);
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the cotisationService value.
     * @param cotisationService the cotisationService to set
     */
    public void setCotisationService(CotisationService cotisationService) {
        this.cotisationService = cotisationService;
    }

    /**
     * Set the contratService value.
     * @param contratService the contratService to set
     */
    public void setContratService(ContratService contratService) {
        this.contratService = contratService;
    }

}
